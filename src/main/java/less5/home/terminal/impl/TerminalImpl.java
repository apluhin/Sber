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
        try {
            if (server.checkBlock()) {
                check = server.checkAccount(numCard, pin);
            }
        } catch (AccountIsLockedException | PinValidatorException e) {
            throw new TerminalException("Error during check acount", e);
        }
        return check;
    }


    @Override
    public int getMoney() {
        int money = 0;
        try {

            if (server.checkBlock()) {

                    money = server.getMoney();

            }
        } catch (ServerError e) {
            throw new TerminalException("Error during put money", e);
        }

        return money;
    }

    @Override
    public void putMoney(int money) {
        try {

            if (server.checkBlock()) {
                if (money % 100 != 0) {
                    throw new MoneyFormatException("Используйте купюры кратные 100");
                }
                server.putMoney(money);
            }

        } catch (MoneyFormatException | ServerError e) {
            throw new TerminalException("Error during put money", e);

        }
    }


}
