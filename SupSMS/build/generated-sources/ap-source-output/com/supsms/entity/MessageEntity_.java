package com.supsms.entity;

import com.supsms.entity.ConversationEntity;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-12-19T02:40:42")
@StaticMetamodel(MessageEntity.class)
public class MessageEntity_ { 

    public static volatile SingularAttribute<MessageEntity, Boolean> incoming;
    public static volatile SingularAttribute<MessageEntity, String> phone;
    public static volatile SingularAttribute<MessageEntity, Long> id;
    public static volatile SingularAttribute<MessageEntity, Date> time;
    public static volatile SingularAttribute<MessageEntity, String> message;
    public static volatile SingularAttribute<MessageEntity, ConversationEntity> conversation;

}