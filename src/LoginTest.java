import org.junit.Test;
import static org.junit.Assert.*;

public class LoginTest {
    Login login = new Login("Kyle", "Smith");

    @Test public void testUsernameCorrect() {
        assertTrue(login.checkUserName("kyl_1"));
    }

    @Test public void testUsernameIncorrect() {
        assertFalse(login.checkUserName("kyle!!!!!!!"));
    }

    @Test public void testPasswordComplexity() {
        assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99!"));
    }

    @Test public void testPasswordWeak() {
        assertFalse(login.checkPasswordComplexity("password"));
    }

    @Test public void testCellCorrect() {
        assertTrue(login.checkCellPhoneNumber("+27838968976"));
    }

    @Test public void testCellIncorrect() {
        assertFalse(login.checkCellPhoneNumber("08966553"));
    }

    @Test public void testLoginSuccess() {
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertTrue(login.loginUser("kyl_1", "Ch&&sec@ke99!"));
    }

    @Test public void testLoginFailed() {
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertFalse(login.loginUser("kyl_1", "wrong"));
    }
}