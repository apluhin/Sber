package less5.home.terminal;

public class TestTerminal {
    public static void main(String[] args) throws InterruptedException {


        Bank bank = new Bank();
        bank.checkAccount(1234, 1111231);
        bank.checkAccount(1234, 111211);
        bank.checkAccount(1234, 111112);
        Thread.sleep(100);
        bank.checkAccount(1234, 111112);
        Thread.sleep(100);
        bank.checkAccount(1234, 111112);
        Thread.sleep(100);
        bank.checkAccount(1234, 111112);
        bank.checkAccount(1234, 111112);
        Thread.sleep(10000);

        bank.checkAccount(1234, 1111);
        System.out.println(bank.getMoney());
        bank.putMoney(200);
        System.out.println(bank.getMoney());


        bank.checkAccount(1235, 2222);
        System.out.println(bank.getMoney());
        bank.putMoney(200);
        System.out.println(bank.getMoney());


    }
}
