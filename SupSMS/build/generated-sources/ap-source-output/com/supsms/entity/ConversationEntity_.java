package com.supsms.entity;

import com.supsms.entity.MessageEntity;
import com.supsms.entity.UserEntity;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-12-19T02:40:42")
@StaticMetamodel(ConversationEntity.class)
public class ConversationEntity_ { 

    public static volatile SingularAttribute<ConversationEntity, String> phone;
    public static volatile ListAttribute<ConversationEntity, MessageEntity> messages;
    public static volatile SingularAttribute<ConversationEntity, Long> id;
    public static volatile SingularAttribute<ConversationEntity, Date> time;
    public static volatile SingularAttribute<ConversationEntity, UserEntity> user;

}