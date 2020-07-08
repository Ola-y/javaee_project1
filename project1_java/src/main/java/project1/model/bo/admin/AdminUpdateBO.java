package project1.model.bo.admin;

/**
 * @param
 * @return
 */
public class AdminUpdateBO {

    private Integer id;

    private String email;

    private String nickanme;

    private String pwd;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickanme() {
        return nickanme;
    }

    public void setNickanme(String nickanme) {
        this.nickanme = nickanme;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
