package less5.home.terminal.account;

public class Account {

    private String name;
    private int money;
    private int pin;


    public String getName() {
        return name;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public Account(String name, int money, int pin) {
        this.name = name;
        this.money = money;
        this.pin = pin;
    }

    public int getPin() {
        return pin;
    }
}
