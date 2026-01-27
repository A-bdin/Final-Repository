import java.util.ArrayList;

public class UserService {

    private ArrayList<User> users;

    public UserService() {
        users = new ArrayList<>();
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void removeUser(User user) {
        users.remove(user);
    }

    public User findUser(String username) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(username)) {
                return users.get(i);
            }
        }
        return null;
    }

    public void listAllUsers() {
        System.out.println("----- Users -----");
        for (int i = 0; i < users.size(); i++) {
            User u = users.get(i);
            System.out.println(
                    u.getUsername() + " | Role: " + u.getRole() + " | Banned: " + u.isBanned() + " | Email: " + u.getEmail()
            );
        }
    }

    public void banUser(String username) {
        User u = findUser(username);
        if (u != null) {
            u.setBanned(true);
            System.out.println(username + " has been banned.");
        } else {
            System.out.println("User not found.");
        }
    }

    public void unbanUser(String username) {
        User u = findUser(username);
        if (u != null) {
            u.setBanned(false);
            System.out.println(username + " has been unbanned.");
        } else {
            System.out.println("User not found.");
        }
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> loadedUsers) {
        this.users = loadedUsers;
    }
}
