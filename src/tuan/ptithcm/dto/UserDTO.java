package ptithcm.dto;

import ptithcm.entity.User;

public class UserDTO {
	private Long id;
    private String email;
    private String fullname;
    private User.Role role;

    // Constructors
    public UserDTO() {
    }

    public UserDTO(Long id,String email, String fullname, User.Role role) {
        this.id = id;
    	this.email = email;
        this.fullname = fullname;
        this.role = role;
    }

    // Getters and Setters
    public Long getId() {
		return id;
	}
    public void setId(Long id) {
		this.id = id;
	}
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
