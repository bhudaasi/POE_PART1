package LoginTest;

import org.junit.Test;
import static org.junit.Assert.*;

public class MessageTest {

    @Test
    public void testMessageLengthSuccess() {
        Message msg = new Message();
        String result = msg.SentMessage("Hi Mike, can you join us for dinner tonight?");
        assertEquals("Message ready to send.", result);
    }

    @Test
    public void testMessageLengthFailure() {
        Message msg = new Message();
        String longMsg = "a".repeat(251); // 251 chars
        String result = msg.SentMessage(longMsg);
        assertTrue(result.contains("Message exceeds 250 characters by 1"));
    }

    @Test
    public void testRecipientCorrect() {
        Message msg = new Message();
        String result = msg.checkRecipientCell("+27718693002");
        assertEquals("Cell phone number successfully captured.", result);
    }

    @Test
    public void testRecipientIncorrect() {
        Message msg = new Message();
        String result = msg.checkRecipientCell("0857597889"); // missing +27
        assertEquals("Cell phone number incorrectly formatted or does not contain international code. Please correct the number and try again.", result);
    }

    @Test
    public void testMessageIDLength() {
        Message msg = new Message();
        msg.checkMessageID();
        assertEquals(10, msg.getMessageID().length());
    }

    @Test
    public void testMessageHashFormat() {
        Message msg = new Message();
        msg.checkMessageID(); // generates ID first
        msg.SentMessage("Hi Mike, can you join us for dinner tonight?");
        String hash = msg.createMessageHash("Hi Mike, can you join us for dinner tonight?");

        // Expected format: first2ID:0:HI:TONIGHT all caps
        String idPart = msg.getMessageID().substring(0, 2);
        String expected = (idPart + ":0:HI:TONIGHT").toUpperCase();
        assertEquals(expected, hash);
    }

    @Test
    public void testMessageHashLoop() {
        Message msg = new Message();
        msg.checkMessageID();

        String[] testMessages = {
                "Did you get the cake?",
                "Where are you? You are late!",
                "Yohooo, I am at your gate."
        };

        for (int i = 0; i < testMessages.length; i++) {
            msg.SentMessage(testMessages[i]);
            String hash = msg.createMessageHash(testMessages[i]);
            assertTrue(hash.matches("^[0-9]{2}:[0-9]+:[A-Z0-9]+:[A-Z0-9]+$"));
        }
    }

    @Test
    public void testReturnTotalMessages() {
        Message msg = new Message();
        msg.SentMessage("Msg 1");
        msg.SentMessage("Msg 2");
        assertEquals(2, msg.returnTotalMessages());
    }
}