package project1.model.vo;

/**
 *  bo:
 *  请求报文需要用到的对象
 *  vo:
 *  响应报文需要用到的对象
 *
 *  管理员登陆成功，返回vo
 * @param
 * @return
 */
public class AdminLoginVO {

    private String token;

    private String name;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
