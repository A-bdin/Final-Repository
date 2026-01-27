public class User {
    private String username;
    private String password;
    private String role;
    private boolean banned;
    private String email;

    public User(String username, String password, String role, String email){
        this.username = username;
        this.password = password;
        this.role = role;
        this.email = email;
        this.banned = false;
    }

    public String getUsername(){ return username; }
    public String getPassword(){ return password; }
    public String getRole(){ return role; }
    public boolean isBanned(){ return banned; }
    public void setBanned(boolean banned){ this.banned = banned; }
    public String getEmail(){ return email; }
    public void setEmail(String email){ this.email = email; }

    public String toFileString(){
        return username+","+password+","+role+","+banned+","+email;
    }
}
