package com.supsms.entity;

import com.supsms.entity.ConversationEntity;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-12-19T02:40:42")
@StaticMetamodel(UserEntity.class)
public class UserEntity_ { 

    public static volatile SingularAttribute<UserEntity, String> fname;
    public static volatile SingularAttribute<UserEntity, String> lname;
    public static volatile SingularAttribute<UserEntity, String> ccard;
    public static volatile SingularAttribute<UserEntity, String> password;
    public static volatile SingularAttribute<UserEntity, String> salt;
    public static volatile SingularAttribute<UserEntity, Boolean> prenium;
    public static volatile SingularAttribute<UserEntity, String> mail;
    public static volatile SingularAttribute<UserEntity, String> phone;
    public static volatile SingularAttribute<UserEntity, Boolean> admin;
    public static volatile SingularAttribute<UserEntity, Long> id;
    public static volatile ListAttribute<UserEntity, ConversationEntity> conversations;
    public static volatile SingularAttribute<UserEntity, Date> updated;

}