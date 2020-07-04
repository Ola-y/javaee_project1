package project1.model;

/**
 * @param
 * @return
 */
public class Admin {
    private Integer id;

    private String email;

    private String pwd;

    private String nickname;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return pwd;
    }

    public void setPassword(String password) {
        this.pwd= password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
