package less5.home.terminal.validator;

import less5.home.terminal.account.Account;
import less5.home.terminal.db.Db;
import less5.home.terminal.exception.PinValidatorException;

import java.util.Map;

public class PinValidator {

    private final Map<Integer, Account> db = Db.getInstance().getHashMap();
    public int countError = 0;

    public Account checkAccount(Integer numCard, Integer pin) throws PinValidatorException {
        if (Math.random() * 10 > 9) {
            throw new PinValidatorException("Error during check, try again");
        }
        Account account;
        if (db.get(numCard).getPin() == pin) {
            return db.get(numCard);
        } else {

            if (countError >= 3) {
                throw new PinValidatorException("3 error pin wait 5 second");
            }
            countError++;
        }

        return null;

    }
}
