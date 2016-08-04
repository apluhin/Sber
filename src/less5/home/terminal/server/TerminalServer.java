package less5.home.terminal.server;

import less5.home.terminal.account.Account;
import less5.home.terminal.exception.MoneyFormatException;
import less5.home.terminal.exception.ServerError;
import less5.home.terminal.validator.PinValidator;

public class TerminalServer {

    private Account workAccount;
    private final PinValidator pinValidator;

    public TerminalServer() {
        this.pinValidator = new PinValidator();
    }

    public boolean checkAccount(Integer numCard, Integer pin) {
        if (workAccount == null || !workAccount.equals(pinValidator.checkAccount(numCard, pin))) {
            this.workAccount = pinValidator.checkAccount(numCard, pin);
        }
        System.out.println(workAccount != null ? "OK" : "Check fall");
        return workAccount != null;
    }


    public int getMoney() {

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
        if (workAccount != null) {
            workAccount.setMoney(workAccount.getMoney() + money);
        } else {
            throw new MoneyFormatException("need authorization");
        }
    }


}
