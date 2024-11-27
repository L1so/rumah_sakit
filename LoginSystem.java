package PSI;

import java.util.ArrayList;

public class LoginSystem {
    private ArrayList<User> users = new ArrayList<>();

    public void addUser(User user) {
        users.add(user);
    }

    public User login(int userId, String password) {
        for (User user : users) {
            if (user.getUserId() == userId && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null; // Jika tidak ada pengguna yang cocok
    }


    public User getUserById(int userId) {
        for (User user : users) {
            if (user.getUserId() == userId) {
                return user;
            }
        }
        return null;
    }
}
