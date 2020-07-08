package project1.model.vo.admin;

import project1.model.Goods;
import project1.model.User;

import java.sql.Date;
import java.util.List;

/**
 * @param
 * @return
 */
public class ReplyVO {
    private Integer id;

    private Integer userId;

    private Integer goodsId;

    private String content;

    private Integer stateId;

    private Date createtime;

    private List<Goods> goods;

    private List<User> user;

    public ReplyVO(Integer id, Integer userId, Integer goodsId, String content, Integer stateId, Date createtime, Goods goods, User user) {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getStateId() {
        return stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public List<Goods> getGoods() {
        return goods;
    }

    public void setGoods(List<Goods> goods) {
        this.goods = goods;
    }

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }
}
