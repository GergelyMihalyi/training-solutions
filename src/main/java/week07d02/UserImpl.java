package week07d02;

public class UserImpl implements User{

    private String userName;
    private String firstName;
    private String lastName;

    public UserImpl(String userName, String firstName, String lastName) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }


}
