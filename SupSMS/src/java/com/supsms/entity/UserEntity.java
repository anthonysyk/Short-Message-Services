package com.supsms.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name = "USER")
public class UserEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique=true)
    private String phone;
    private String fname;
    private String lname;
    private String mail;
    private String ccard;
    private String password;
    private String salt;
    private boolean admin;
    private boolean prenium;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date updated;
    @OneToMany(mappedBy = "user")
    @OrderBy("time DESC")
    private List<ConversationEntity> conversations;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getCcard() {
        return ccard;
    }

    public void setCcard(String ccard) {
        this.ccard = ccard;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isPrenium() {
        return prenium;
    }

    public void setPrenium(boolean prenium) {
        this.prenium = prenium;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
    
    public UserEntity(){
        this.salt = UUID.randomUUID().toString();
    }

    public List<ConversationEntity> getConversations() {
        return conversations;
    }

    public void setConversations(List<ConversationEntity> conversations) {
        this.conversations = conversations;
    }
}
