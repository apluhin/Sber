package less5.home.terminal;

import less5.home.terminal.impl.TerminalImpl;

public class TestTerminal {
    public static void main(String[] args) throws InterruptedException {


        Terminal terminal = new TerminalImpl();
        terminal.checkAccount(1234, 1111231);
        terminal.checkAccount(1234, 111211);
        terminal.checkAccount(1234, 111112);
        Thread.sleep(6000);

        terminal.checkAccount(1234, 1111);
        System.out.println(terminal.getMoney());
        terminal.putMoney(200);
        System.out.println(terminal.getMoney());


        terminal.checkAccount(1235, 2222);
        System.out.println(terminal.getMoney());
        terminal.putMoney(200);
        System.out.println(terminal.getMoney());


    }
}
