package com.soshiant.server.model.group;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * UserInfo: Mahta-M
 * Date: 8/24/11
 * Time: 7:08 PM
 */


@Entity
@Table(name = "groups")
public class Groups implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int groupId;
    private String groupName;

    public Groups() {
    }

    public Groups(String name) {
        this.groupName = name;
    }

    @Column(name = "groupId")
    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    @Column(name = "groupName")
    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    //@Override
    public String toString() {
        return "UserInfo [GroupId=" + this.groupId + ", GroupName=" + this.groupName;
    }
}