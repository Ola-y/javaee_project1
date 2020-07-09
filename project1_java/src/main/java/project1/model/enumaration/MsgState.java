package project1.model.enumaration;

/**
 * @param
 * @return
 */
public enum MsgState {

    REPLIED(0,"已回复"),
    NOREPLY(1,"未回复");

    private Integer code;

    private String value;

    MsgState(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }}
