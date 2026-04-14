import java.util.Scanner;

//Declarations

public class Registration {
    String userName;
    String firstName;
    String lastName;
    String passWord;
    String phoneNumber;

    //username Validation
    //must contain "_" and must be =< characters
    public boolean checkUserName(String userName) {
        return userName.length() <= 5 && userName.contains("_");

    }

    // PassWord validation
    //Must be 8 characters, Uppercase, Digit, Special character
    public boolean checkpassWord(String passWord) {
        if (passWord.length() < 8) return false;

        boolean hasUppercase = false;
        boolean hasSpecial = false;
        boolean hasDigit = false;

        // Loop through each character if they were met
        for (char ch : passWord.toCharArray()) {
            if (Character.isUpperCase(ch)) hasUppercase = true;
            if (Character.isLetterOrDigit(ch)) hasSpecial = true;
            if (Character.isDigit(ch)) hasDigit = true;

        }

        return hasUppercase && hasSpecial && hasDigit;

    }

    //Validating the South African phone number format
    //Must star with "+27 or 0" and have 10 digits
    public boolean checkPhoneNo(String phoneNumber) {
        return phoneNumber.matches("(\\+27|0)[0-9]{9}");

    }

    //Registration process
    //Collects and validates the users input
    public void Register() {
        Scanner s = new Scanner(System.in);

        System.out.print("Enter your first name: ");
        firstName = s.nextLine();

        System.out.print("Enter your last name: ");
        lastName = s.nextLine();

        //Username loop (it will repeat until its valid)
        while (true) {
            System.out.print("Enter your username: ");
            userName = s.nextLine();

            if (checkUserName(userName)) {
                System.out.println("Username successfully captured");
                break;
            } else {
                System.out.println("Username invalid (must contain '_' and should be less tham 5 characters long)");
            }
        }

        //Password loop (repeats until its valid)
        while (true) {
            System.out.print("Enter your password: ");
            passWord = s.nextLine();

            if (checkpassWord(passWord)) {
                System.out.println("Password successfully captured");
                break;

            } else {
                System.out.println("Password invalid " + "(must be at least 8 characters long, contsain an uppercase, digit, and special character)");
            }
        }

        //Phone number loop (repeats until its valid)
        while (true) {
            System.out.print("Enter your Phone Number: ");
            phoneNumber = s.nextLine();

            if (checkPhoneNo(phoneNumber)) {
                System.out.println("Phone number successfully captured");
                break;

            } else {
                System.out.println("Phone number invalid (must contain '+27 or 0')");

            }
        }
    }
}
