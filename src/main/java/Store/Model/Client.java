package Store.Model;

public class Client {
    private String firstName,lastName,email,userpassword;
    private int clientID;

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public int getClientID() {
        return clientID;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserpassword() {
        return userpassword;
    }
}
