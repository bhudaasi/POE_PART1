import java.util.Scanner;

class login {
    Scanner scanner = new Scanner(System.in);
    public String username;
    public String password;
    public String Firstname;
    public String Lastname;

    public login(String username, String password, String Firstname, String Lastname) {

        this.username = Firstname;
        this.password = Firstname;
        this.Firstname = Firstname;
        this.Lastname = Firstname;
    }

    public boolean login(String registeredUsername, String registeredPass) {
        return registeredUsername.equals(this.username) && registeredPass.equals(this.password);
    }

    public String returnStatus(boolean LoginState) {
        if (LoginState) {
            return ("Login Successful");
        } else {
            return ("Login failed");
        }
    }

    public void LoginUser() {
        String LoginUsername;
        String LoginPassword;
        boolean LoginState = false;

        System.out.println("Enter your username :");
        LoginUsername = scanner.nextLine();
        System.out.println("Enter your password :");
        LoginPassword = scanner.nextLine();

        LoginState = login(LoginUsername, LoginPassword);

        if (LoginState) {
            System.out.println("Login Successfull");
            System.out.println("Welcome" + Firstname + " " + Lastname + "It's great to see you");
        } else {
            System.out.println("Username or password incorrect please try again");
        }
    }
}


