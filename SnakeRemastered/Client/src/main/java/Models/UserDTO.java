package Models;

public class UserDTO {
    private String username;
    private String password;

    public UserDTO(){

    }

    public UserDTO(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername(){
        return this.username;
    }

    public String getPassword(){
        return this.password;
    }

    @Override
    public String toString() {
        return "username: " + username + " password: " + password;
    }
}
