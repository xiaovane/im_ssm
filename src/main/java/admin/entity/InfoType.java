package admin.entity;

import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Created by xp on 2017/4/14.
 * @author whai
 * @Description 类别实体类
 */
public class InfoType implements Serializable{

    private Integer id;
    private String typeName;
    private Integer orderNum;
    private Integer WebUserCount;

    public InfoType() {
    }

    public InfoType(Integer id, String typeName, Integer orderNum, Integer WebUserCount) {
        this.id = id;
        this.typeName = typeName;
        this.orderNum = orderNum;
        this.WebUserCount = WebUserCount;
    }

    public InfoType(String typeName, Integer orderNum, Integer WebUserCount) {
        this.typeName = typeName;
        this.orderNum = orderNum;
        this.WebUserCount = WebUserCount;
    }

    public InfoType(Integer id, String typeName, Integer orderNum) {
        this.id = id;
        this.typeName = typeName;
        this.orderNum = orderNum;
    }

    public InfoType(String typeName, Integer orderNum) {
        this.typeName = typeName;
        this.orderNum = orderNum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getWebUserCount() {
        return WebUserCount;
    }

    public void setWebUserCount(Integer WebUserCount) {
        this.WebUserCount = WebUserCount;
    }

    @Override
    public String toString() {
        return "InfoType{" +
                "id=" + id +
                ", typeName='" + typeName + '\'' +
                ", orderNum=" + orderNum +
                ", WebUserCount=" + WebUserCount +
                '}';
    }
}
