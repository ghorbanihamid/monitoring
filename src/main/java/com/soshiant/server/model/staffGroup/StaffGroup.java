package com.soshiant.server.model.staffGroup;

import com.soshiant.server.model.group.Groups;
import com.soshiant.server.model.staff.Staff;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * UserInfo: Mahta-M
 * Date: 8/24/11
 * Time: 7:09 PM
 */
@Entity
@Table(name = "USERGROUPS")
public class StaffGroup implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "groupId")
    private Integer id;

    @OneToOne
    @PrimaryKeyJoinColumn(name = "groupId")
    private Groups group;

//    @OneToOne
//    @PrimaryKeyJoinColumn(name = "staffId")
//    private Staff staff;

    @Column(name = "CreateDate")
    private Date creationDate;

    public StaffGroup() {
    }

    public StaffGroup(Groups group, Staff staff) {
        this.group = group;
//        this.staff = staff;
        this.creationDate = new Date();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


//    public Staff getStaff() {
//        return staff;
//    }
//
//    public void setStaff(Staff staff) {
//        this.staff = staff;
//    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

}