package com.soshiant.server.model.user;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * User: Hubert
 * Date: 8/24/11
 * Time: 7:09 PM
 */
@Entity
@Table(name = "usergroups")
public class UserGroups implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private int userId;

    private short groupId;

    private int createDate;


    @Column(name = "userId")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Column(name = "groupId")
    public short getGroupId() {
        return groupId;
    }

    public void setGroupId(short groupId) {
        this.groupId = groupId;
    }

    @Column(name = "createDate")
    public int getCreateDate() {
        return createDate;
    }

    public void setCreateDate(int createDate) {
        this.createDate = createDate;
    }

    public UserGroups() {

    }

    //@Override
    public String toString() {
        return "UserInfo [GroupId=" + this.groupId + ", groupId=" + this.groupId
                + ", CreateDate" + this.createDate + "]";
    }

}