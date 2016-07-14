package com.supsms.entity;

import com.supsms.entity.UserEntity;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-12-19T02:40:42")
@StaticMetamodel(ContactEntity.class)
public class ContactEntity_ { 

    public static volatile SingularAttribute<ContactEntity, String> fname;
    public static volatile SingularAttribute<ContactEntity, String> lname;
    public static volatile SingularAttribute<ContactEntity, String> address;
    public static volatile SingularAttribute<ContactEntity, String> mail;
    public static volatile SingularAttribute<ContactEntity, String> phone;
    public static volatile SingularAttribute<ContactEntity, Long> id;
    public static volatile SingularAttribute<ContactEntity, Date> time;
    public static volatile SingularAttribute<ContactEntity, UserEntity> user;

}