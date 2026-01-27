import java.io.*;
import java.util.ArrayList;

public class AuthService {

    private UserService userService;

    public AuthService(UserService userService) {
        this.userService = userService;
    }

    // loading users
    public void loadUsers() {
        ArrayList<User> loaded = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("users.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split(",");
                User u;
                switch (p[2]) {
                    case "ADMIN": u = new Admin(p[0], p[1], p[4]); break;
                    case "SELLER": u = new Seller(p[0], p[1], p[4]); break;
                    default: u = new Buyer(p[0], p[1], p[4]); break;
                }
                u.setBanned(Boolean.parseBoolean(p[3]));
                loaded.add(u);
            }
            br.close();
            userService.setUsers(loaded);
        } catch (Exception e) {}
    }

    // saving users
    public void saveUsers() {
        try {
            FileWriter fw = new FileWriter("users.txt");
            ArrayList<User> users = userService.getUsers();
            for (int i = 0; i < users.size(); i++) {
                fw.write(users.get(i).toFileString() + "\n");
            }
            fw.close();
        } catch (Exception e) {}
    }

    // registering
    public void register(String username, String password, String email, String role) {
        if (userService.findUser(username) != null) {
            System.out.println("Username already exists");
            return;
        }
        User u;
        switch (role) {
            case "ADMIN": u = new Admin(username, password, email); break;
            case "SELLER": u = new Seller(username, password, email); break;
            default: u = new Buyer(username, password, email); break;
        }
        userService.addUser(u);
        System.out.println("Registration successful!");
    }

    // logging in
    public User login(String username, String password) {
        User u = userService.findUser(username);
        if (u == null || !u.getPassword().equals(password)) return null;
        if (u.isBanned()) {
            System.out.println("Account banned!");
            return null;
        }
        return u;
    }
}

