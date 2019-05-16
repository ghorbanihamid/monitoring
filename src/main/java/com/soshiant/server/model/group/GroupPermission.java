package com.soshiant.server.model.group;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * UserInfo: Ghorbani
 * Date: 9/20/11
 * Time: 5:38 PM
 */


@Entity
@Table(name = "GROUPPERMISSIONS")
public class GroupPermission implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int groupId;

    private int permissionId;

    private int createDate;

//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinTable(name = "groupright", joinColumns = { @JoinColumn(name = "groupId")},
//            inverseJoinColumns = { @JoinColumn(name = "permissionId")})


    @Column(name = "groupId")
    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    @Column(name = "permissionId")
    public int getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(int permissionId) {
        this.permissionId = permissionId;
    }

    @Column(name = "createDate")
    public int getCreateDate() {
        return createDate;
    }

    public void setCreateDate(int createDate) {
        this.createDate = createDate;
    }


    public GroupPermission() {

    }

    public GroupPermission(int groupId, int permissionId) {
        this.groupId = groupId;
        this.permissionId = permissionId;
//        this.createDate = new Date();
    }


    //@Override
    public String toString() {
        return "GroupPermission [GroupId=" + this.groupId + ", permissionId=" + this.permissionId
                + ", CreateDate" + this.createDate + "]";
    }
}
