package less5.home.terminal;


import less5.home.terminal.impl.TerminalImpl;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class TerminalTest {

    Terminal terminal;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        terminal = new TerminalImpl();
    }

    @Test
    public void checkAccount() throws Exception {

        assertEquals(terminal.checkAccount(1234, 1111), true);

        assertEquals(terminal.checkAccount(1234, 11211), false);

    }

    @Test
    public void getMoney() throws Exception {
        terminal.checkAccount(1234, 1111);
        terminal.getMoney();
        terminal.getMoney();
        terminal.getMoney();
        terminal.getMoney();
        terminal.getMoney();
    }

    @Test
    public void putMoney() throws Exception {

        terminal.checkAccount(1234, 1111);
        int a = terminal.getMoney();
        terminal.putMoney(200);
        terminal.putMoney(200);
        terminal.putMoney(200);
        terminal.putMoney(200);
        terminal.putMoney(200);
        terminal.putMoney(200);
//        assertEquals(terminal.getMoney() - 200, a);

    }


}