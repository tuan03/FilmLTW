package ptithcm.dto;

import ptithcm.entity.User;

public class UserDTO {
    private String email;
    private String fullname;
    private User.Role role;

    // Constructors
    public UserDTO() {
    }

    public UserDTO(String email, String fullname, User.Role role) {
        this.email = email;
        this.fullname = fullname;
        this.role = role;
    }

    // Getters and Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public User.Role getRole() {
        return role;
    }

    public void setRole(User.Role role) {
        this.role = role;
    }
}
