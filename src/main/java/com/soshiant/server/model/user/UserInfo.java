package com.soshiant.server.model.user;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: hubert
 * Date: 7/17/11
 * Time: 6:05 PM
 */

@Entity
@Table(name = "USERSINFO", uniqueConstraints = {
        @UniqueConstraint(columnNames = "userName")})
public class UserInfo implements java.io.Serializable {

    @Id
    private String username;

    private boolean enabled;

    private byte userType;

    private int userId;

    private int groupId;

    private int passChgDate;

    private String password;

    private String salt;


    @Column(name = "UserName", unique = true, nullable = false, length = 20)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "Enabled", unique = false, nullable = false)
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Column(name = "UserType", unique = false, nullable = false)
    public byte getUserType() {
        return userType;
    }

    public void setUserType(byte userType) {
        this.userType = userType;
    }

    @Column(name = "UserId", unique = false, nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Column(name = "GroupId", unique = false, nullable = false)
    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    @Column(name = "Password", unique = false, nullable = false, length = 200)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "Salt", unique = false, nullable = false, length = 25)
    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Column(name = "PassChgDate", unique = false, nullable = false)
    public int getPassChgDate() {
        return passChgDate;
    }

    public void setPassChgDate(int passChgDate) {
        this.passChgDate = passChgDate;
    }


    public UserInfo() {

    }

    public UserInfo(String username,String password, String saltValue, boolean userIsEnabled, Integer userId, byte userType) {
        this.userId = userId;
        this.username = username;
        this.enabled = userIsEnabled;
        this.userType = userType;
        this.password = password;
        this.salt = saltValue;
    }

    //@Override
    public String toString() {
        return "UserInfo [UserName = " + username + "," +
                     "UserId = " + userId + "," +
                     "UserType = " + userType + "," +
                     "enabled" + enabled + "," +
                     "]";
    }

}
