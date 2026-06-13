import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Scanner;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Main {
    private static final String STORED_FILE = "stored.json";
    private static Gson gson = new Gson();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== REGISTRATION ===");
        System.out.print("Enter first name: ");
        String firstName = sc.nextLine();
        System.out.print("Enter last name: ");
        String lastName = sc.nextLine();

        Login login = new Login(firstName, lastName);

        System.out.print("Enter username max 5 chars with _: ");
        String username = sc.nextLine();
        System.out.print("Enter password min 8 chars, 1 cap, 1 num, 1 special: ");
        String password = sc.nextLine();
        System.out.print("Enter SA cell +27XXXXXXXXX: ");
        String cell = sc.nextLine();

        System.out.println(login.registerUser(username, password, cell));

        System.out.println("\n=== LOGIN ===");
        System.out.print("Enter username: ");
        String user = sc.nextLine();
        System.out.print("Enter password: ");
        String pass = sc.nextLine();

        boolean loggedIn = login.loginUser(user, pass);
        System.out.println(login.returnLoginStatus(loggedIn));

        if (loggedIn) {
            System.out.println("Welcome to QuickChat.");
            ArrayList<Message> storedMessages = loadMessages();

            System.out.print("How many messages do you wish to enter? ");
            int numMsgs = sc.nextInt();
            sc.nextLine();

            for (int i = 0; i < numMsgs; i++) {
                Message msg = new Message();
                msg.checkMessageID();

                System.out.print("Enter recipient +27XXXXXXXXX: ");
                String recipient = sc.nextLine();
                System.out.println(msg.checkRecipientCell(recipient));

                System.out.print("Enter message <=250 chars: ");
                String text = sc.nextLine();
                System.out.println(msg.SentMessage(text));

                msg.createMessageHash(text);
                System.out.println(msg.printMessages());

                System.out.println("1) Send 2) Store 3) Disregard");
                int choice = sc.nextInt();
                sc.nextLine();

                if (choice == 2) {
                    System.out.println("Message successfully stored");
                    storedMessages.add(msg);
                    saveMessages(storedMessages);
                } else if (choice == 1) {
                    System.out.println("Message successfully sent");
                } else {
                    System.out.println("Press 0 to delete the message");
                }
            }

            boolean running = true;
            while (running) {
                System.out.println("\n=== MENU ===");
                System.out.println("1) Send Messages");
                System.out.println("2) Show recently sent - Coming Soon");
                System.out.println("3) Quit");
                System.out.println("4) Stored Messages");
                System.out.print("Choose option: ");
                int option = sc.nextInt();
                sc.nextLine();

                switch (option) {
                    case 1: System.out.println("Use the flow above to send more"); break;
                    case 2: System.out.println("Coming Soon"); break;
                    case 3: running = false; break;
                    case 4: storedMenu(sc, storedMessages); break;
                    default: System.out.println("Invalid option");
                }
            }
        }
        sc.close();
    }

    private static void storedMenu(Scanner sc, ArrayList<Message> storedMessages) {
        boolean inMenu = true;
        while (inMenu) {
            System.out.println("\n--- STORED MESSAGES ---");
            System.out.println("a) Display sender + recipient");
            System.out.println("b) Display longest message");
            System.out.println("c) Search by message ID");
            System.out.println("d) Search by recipient");
            System.out.println("e) Delete by hash");
            System.out.println("f) Full report");
            System.out.println("x) Back");
            System.out.print("Choose: ");
            String choice = sc.nextLine().toLowerCase();

            switch (choice) {
                case "a":
                    for (Message m : storedMessages) {
                        System.out.println("Recipient: " + m.getRecipient() + " | Message: " + m.getMessage());
                    }
                    break;
                case "b":
                    Message longest = null;
                    for (Message m : storedMessages) {
                        if (longest == null || m.getMessage().length() > longest.getMessage().length()) {
                            longest = m;
                        }
                    }
                    if (longest!= null) System.out.println("Longest: " + longest.getMessage());
                    else System.out.println("No stored messages");
                    break;
                case "c":
                    System.out.print("Enter Message ID: ");
                    String id = sc.nextLine();
                    for (Message m : storedMessages) {
                        if (m.getMessageID().equals(id)) {
                            System.out.println("Recipient: " + m.getRecipient() + "\nMessage: " + m.getMessage());
                        }
                    }
                    break;
                case "d":
                    System.out.print("Enter recipient: ");
                    String rec = sc.nextLine();
                    for (Message m : storedMessages) {
                        if (m.getRecipient().equals(rec)) {
                            System.out.println("ID: " + m.getMessageID() + " | " + m.getMessage());
                        }
                    }
                    break;
                case "e":
                    System.out.print("Enter Message Hash to delete: ");
                    String hash = sc.nextLine();
                    boolean removed = storedMessages.removeIf(m -> m.getMessageHash().equals(hash));
                    if (removed) saveMessages(storedMessages);
                    System.out.println(removed? "Message deleted" : "Hash not found");
                    break;
                case "f":
                    for (Message m : storedMessages) {
                        System.out.println(m.printMessages() + "\n---");
                    }
                    break;
                case "x": inMenu = false; break;
            }
        }
    }

    private static void saveMessages(ArrayList<Message> messages) {
        try (FileWriter writer = new FileWriter(STORED_FILE)) {
            gson.toJson(messages, writer);
        } catch (Exception e) {
            System.out.println("Error saving: " + e.getMessage());
        }
    }

    private static ArrayList<Message> loadMessages() {
        try (FileReader reader = new FileReader(STORED_FILE)) {
            Type listType = new TypeToken<ArrayList<Message>>(){}.getType();
            ArrayList<Message> list = gson.fromJson(reader, listType);
            return list == null? new ArrayList<>() : list;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}