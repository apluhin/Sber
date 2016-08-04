package less5.home.terminal.impl;

import less5.home.terminal.Terminal;
import less5.home.terminal.exception.*;
import less5.home.terminal.server.TerminalServer;

public class TerminalImpl implements Terminal {

    private final TerminalServer server;


    private long waitSecond = 0;

    public TerminalImpl() {
        this.server = new TerminalServer();


    }


    @Override
    public boolean checkAccount(Integer numCard, Integer pin) {
        boolean check = false;
        if (checkBlock()) {
            check = authorization(numCard, pin);
        }
        return check;
    }


    @Override
    public int getMoney() {
        int money = 0;
        try {

            if (checkBlock()) {
                try {
                    money = server.getMoney();
                } catch (ServerError serverError) {
                    throw new TerminalException("Error during transaction", serverError);
                }
            }
        } catch (TerminalException e) {
            System.out.println(e.getMessage());
        }

        return money;
    }

    @Override
    public void putMoney(int money) {
        try {
            try {
                if (checkBlock()) {
                    if (money % 100 != 0) {
                        throw new MoneyFormatException("Используйте купюры кратные 100");
                    }
                    server.putMoney(money);
                }

            } catch (MoneyFormatException | ServerError e) {
                throw new TerminalException("Error during put money", e);
            }
        } catch (TerminalException e) {
            System.out.println(e.getMessage());
        }
    }

    private boolean authorization(Integer numCard, Integer pin) {
        try {
            return server.checkAccount(numCard, pin);
        } catch (AccountIsLockedException e) {
            this.waitSecond = System.currentTimeMillis();
            System.out.println(e.getMessage());
        } catch (PinValidatorException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }


    private boolean checkBlock() {

        if (waitSecond != 0 && System.currentTimeMillis() - waitSecond < 5000) {
            try {
                throw new AccountIsLockedException("Wait " + (5000 - (System.currentTimeMillis() - waitSecond) + " ms"));
            } catch (AccountIsLockedException e) {
                System.out.println(e.getMessage());
            }
        } else if (waitSecond != 0 && System.currentTimeMillis() - waitSecond > 5000) {
            this.waitSecond = 0;
            return true;
        }
        return true;
    }


}
