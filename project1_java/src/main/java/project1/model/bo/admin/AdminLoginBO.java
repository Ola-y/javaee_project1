package project1.model.bo.admin;

/**
 * bo:
 * 请求报文需要用到的对象
 * vo:
 * 响应报文需要用到的对象
 */
public class AdminLoginBO {

    private String email;

    private String pwd;


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
        this.pwd = password;
    }

    @Override
    public String toString() {
        return "AdminLoginBO{" +
                "email='" + email + '\'' +
                ", password='" + pwd + '\'' +
                '}';
    }
}