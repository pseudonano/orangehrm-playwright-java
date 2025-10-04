package OrangeHRM.domain;

public class User {
    private String username;
    private String password;
    private String expected;

    public User(String username, String password, String expected) {
        this.username = username;
        this.password = password;
        this.expected = expected;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getExpected(){
        return expected;
    }
}
