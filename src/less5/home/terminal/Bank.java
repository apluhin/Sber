package less5.home.terminal;

import less5.home.terminal.exception.AccountIsLockedException;
import less5.home.terminal.exception.TerminalException;
import less5.home.terminal.impl.TerminalImpl;

public class Bank {
    private final Terminal terminal = new TerminalImpl();

    boolean checkAccount(Integer numCard, Integer pin) {
        try {
            return terminal.checkAccount(numCard, pin);
        } catch (TerminalException | AccountIsLockedException e) {
            trace(e);
            System.out.println();
        }
        return false;
    }

    int getMoney() {
        try {
            return terminal.getMoney();

        } catch (TerminalException | AccountIsLockedException e) {
            trace(e);

        }
        return -1;
    }

    void putMoney(int money) {
        try {
            terminal.putMoney(money);
        } catch (TerminalException | AccountIsLockedException e) {
            trace(e);

        }
    }


    void trace(Throwable e) {

        if (e.getCause() != null) {
            System.out.print(e.getMessage() + ", ");
            trace(e.getCause());
        } else {
            System.out.print(e.getMessage() + " ");
        }
    }


}
