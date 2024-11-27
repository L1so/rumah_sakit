package PSI;

public abstract class User {
    private int userId;
    private String password;

    public User(int userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public abstract void showRole();
}
