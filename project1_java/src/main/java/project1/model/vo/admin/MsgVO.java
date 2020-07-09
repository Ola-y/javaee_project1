package project1.model.vo.admin;

import project1.model.Goods;
import project1.model.User;
import project1.model.enumaration.MsgState;
import project1.model.enumaration.OrderState;

import java.sql.Date;
import java.util.List;

/**
 * @param
 * @return
 */
public class MsgVO {
    private Integer id;

    private Integer userId;

    private Integer goodsId;

    private String content;

    private String replyContent;

    private Integer state;

    private Date createtime;

    private  MsgGoodsVO goods =new MsgGoodsVO();

    private  MsgUserVO user=new MsgUserVO();

    public MsgVO(Integer id, int userId, int goodsId, String content, Integer state, MsgGoodsVO goods, MsgUserVO user) {
        this.id = id;
        this.userId = userId;
        this.goodsId = goodsId;
        this.content = content;
        this.state = state;
        this.createtime = createtime;
        this.goods = goods;
        this.user = user;
    }

    public MsgVO(Integer id, int userId, int goodsId, String content, String replyContent, Integer state, MsgGoodsVO goods, MsgUserVO user) {
        this.id = id;
        this.userId = userId;
        this.goodsId = goodsId;
        this.content = content;
        this.state = state;
        this.replyContent=replyContent;
        this.createtime = createtime;
        this.goods = goods;
        this.user = user;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public MsgGoodsVO getGoods() {
        return goods;
    }

    public void setGoods(MsgGoodsVO goods) {
        this.goods = goods;
    }

    public MsgUserVO getUser() {
        return user;
    }

    public void setUser(MsgUserVO user) {
        this.user = user;
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

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }
}
