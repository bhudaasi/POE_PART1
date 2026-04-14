import java.util.Scanner;

 public class Main {
     public static void main(String[] args) {

         // Register the user
         Registration registration = new Registration();
         registration.Register();

         //create the login object
         //Stores the registered user data for comparison
         Login login = new Login(
                 registration.userName,
                 registration.firstName,
                 registration.lastName,
                 registration.passWord);

         Scanner s = new Scanner(System.in);

         //Username login loop
         //Continue asking until the username is entered
         while (true) {
             System.out.println("Please login: ");

             System.out.println("Please enter username: ");
             String loginUser = s.nextLine();

             if (loginUser.equals(registration.userName)) {
                 System.out.println("Username correct");
                 break;
             } else {
                 System.out.println("Username incorrect,please try again");
             }

         }

         //Password login loop
         //Continue asking until the passwword is correct
         while (true) {
             System.out.println("enter Password: ");
             String loginPass = s.nextLine();

             if (loginPass.equals(registration.passWord)) {
                 System.out.println("password correct");
                 break;
             } else {
                 System.out.println("password incorrect,please try again..");
             }
         }

         //message is successful
         System.out.println("Welcome " + registration.firstName + " " + registration.lastName + "It is great to see you again");

         s.close();

     }
 }


















