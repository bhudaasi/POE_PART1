import java.util.Random;

public class Message {
    private String messageID;
    private String messageHash;
    private String recipient;
    private String message;
    private int numMessagesSent = 0;

    public Message() {} // CRITICAL: Gson needs no-arg constructor

    public boolean checkMessageID() {
        Random rand = new Random();
        long id = 1000000L + (long)(rand.nextDouble() * 9000000L); // 10 digits
        this.messageID = String.valueOf(id);
        return messageID.length() == 10;
    }

    public String checkRecipientCell(String recipient) {
        if (recipient!= null && recipient.matches("^\\+27[0-9]{9}$")) {
            this.recipient = recipient;
            return "Cell phone number successfully captured.";
        }
        return "Cell phone number incorrectly formatted or does not contain international code. Please correct the number and try again.";
    }

    public String createMessageHash(String message) {
        if (message == null || message.trim().isEmpty() || messageID == null) return "";
        String[] words = message.trim().split("\\s+");
        String first = words[0].replaceAll("[^A-Za-z0-9]", "").toUpperCase();
        String last = words[words.length - 1].replaceAll("[^A-Za-z0-9]", "").toUpperCase();
        String idPart = messageID.substring(0, 2);
        this.messageHash = (idPart + ":" + numMessagesSent + ":" + first + ":" + last).toUpperCase();
        return this.messageHash;
    }

    public String SentMessage(String message) {
        if (message.length() > 250) {
            return "Please enter a message of less than 250 characters. Message exceeds 250 characters by " + (message.length() - 250) + "; please reduce the size.";
        }
        this.message = message;
        this.numMessagesSent++;
        return "Message ready to send.";
    }

    public String printMessages() {
        return "Message ID: " + messageID +
                "\nMessage Hash: " + messageHash +
                "\nRecipient: " + recipient +
                "\nMessage: " + message;
    }

    public int returnTotalMessages() {
        return numMessagesSent;
    }

    public String getMessageID() { return messageID; }
    public String getMessageHash() { return messageHash; }
    public String getRecipient() { return recipient; }
    public String getMessage() { return message; }
}