package less5.home.terminal;

public interface Terminal {

    boolean checkAccount(Integer numCard, Integer pin);

    int getMoney();

    void putMoney(int money);


}
