package less5.home.terminal.server;

import less5.home.terminal.account.Account;
import less5.home.terminal.exception.*;
import less5.home.terminal.validator.PinValidator;

public class TerminalServer {

    private Account workAccount;
    private final PinValidator pinValidator;

    private long waitSecond = 0;

    public TerminalServer() {
        this.pinValidator = new PinValidator();
    }

    public boolean checkAccount(Integer numCard, Integer pin) {
        if (!checkBlock()) return false;
        try {
            Account account = pinValidator.checkAccount(numCard, pin);
            if (workAccount == null || !workAccount.equals(account)) {
                this.workAccount = pinValidator.checkAccount(numCard, pin);
            }
            System.out.println(workAccount != null ? "OK" : "Check fall");
        } catch (PinValidatorException e) {
            this.waitSecond = System.currentTimeMillis();
            throw new TerminalException("Error during check", e);
        }

        return workAccount != null;
    }


    public int getMoney() {
        if (!checkBlock()) return 0;
        if (Math.random() * 10 > 9) {
            throw new ServerError("Error during transaction");
        }
        if (workAccount != null) {
            return workAccount.getMoney();
        } else {
            throw new ServerError("need authorization");
        }

    }


    public void putMoney(int money) {
        if (!checkBlock()) return;
        if (workAccount != null) {
            workAccount.setMoney(workAccount.getMoney() + money);
        } else {
            throw new MoneyFormatException("need authorization");
        }
    }


    public boolean checkBlock() {

        if (waitSecond != 0 && System.currentTimeMillis() - waitSecond < 5000) {

            throw new AccountIsLockedException("Wait " + (5000 - (System.currentTimeMillis() - waitSecond) + " ms"));

        } else if (waitSecond != 0 && System.currentTimeMillis() - waitSecond > 5000) {
            this.waitSecond = 0;
            pinValidator.countError = 0;
            return true;
        }
        return true;
    }



}
