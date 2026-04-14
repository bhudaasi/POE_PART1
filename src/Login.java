
    public class Login {

    String userName;
    String firstName;
    String lastName;
    String passWord;


//Constructor
public Login (String userName,String firstName, String lastName, String passWord ) {
    this.userName = userName;
    this.firstName = firstName;
    this.lastName = lastName;
    this.passWord = passWord;
}

    //Makes sure that the login details matches the registered details
    //Returns true if correct, false if incorrect

    public boolean loginUser(String Username, String Password){
        return Username.equals(this.userName) && Password.equals(this.passWord);
    }

}




