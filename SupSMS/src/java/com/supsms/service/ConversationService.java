package com.supsms.service;

import com.supsms.dao.ConversationDaoLocal;
import com.supsms.dao.MessageDaoLocal;
import com.supsms.dao.UserJpaDaoLocal;
import com.supsms.entity.ConversationEntity;
import com.supsms.entity.MessageEntity;
import com.supsms.entity.UserEntity;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * Manipulations et services liés aux conversations et aux messages
 */
@Stateless
public class ConversationService {
    @Resource(mappedName = "SupSmsJms")
    private Destination supSmsJms;
    @Resource(mappedName = "SupSmsJmsFactory")
    private ConnectionFactory supSmsJmsFactory;
    @EJB
    private UserJpaDaoLocal userJpaDao;
    @EJB
    private ConversationDaoLocal conversationDao;
    @EJB
    private MessageDaoLocal messageDao;
    
    /**
     * Envoyer un message
     * @param message message à envoyer
     * @param userId identifiant de l'expéditeur
     * @return message envoyé
     * @throws javax.jms.JMSException
     */
    public MessageEntity sendMessage(MessageEntity message, Long userId) throws JMSException{
        message.setTime(new Date());
        message.setIncoming(false);
        UserEntity user = userJpaDao.getOneById(userId);
        
        // Envoi du message avec JMS
        Connection connection = supSmsJmsFactory.createConnection();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        MessageProducer producer = session.createProducer(supSmsJms);
        TextMessage jsmMsg = session.createTextMessage(
                user.getPhone() + "\n\r" + 
                message.getMessage() + "\n\r" + 
                message.getPhone());
        producer.send(jsmMsg);
        
        return this.addMessage(message, userId);
    }
    
    /**
     * Ajouter un message au système (sans l'envoyer)
     * @param message message à ajouter
     * @param userId identifiant de l'utilisateur au quel le rattacher
     * @return message nouvellement ajouté
     */
    public MessageEntity addMessage(MessageEntity message, Long userId){
        ConversationEntity conversation = conversationDao.getOneByPhone(message.getPhone(), userId);
        
        if(conversation == null){
            UserEntity user = userJpaDao.getOneById(userId);
            conversation = new ConversationEntity();
            conversation.setUser(user);
            conversation.setPhone(message.getPhone());
            conversationDao.create(conversation);
        }
        
        message.setConversation(conversation);
        conversation.setTime(new Date());
        conversationDao.update(conversation);
        
        return (MessageEntity) messageDao.create(message);
    }
    
    /**
     * Obtenir toutes les conversations d'un utilisateur
     * @param userId identifiant de l'utilisateur
     * @return collection des conversations
     */
    public List<ConversationEntity> getAllConversations(Long userId){
        return conversationDao.getAll(userId);
    }
    
    /**
     * Obtenir une conversation par son identifiant
     * @param userId identifiant de l'utilisateur demandant la conversation
     * @param conversationId identifiant de la conversation
     * @return conversation
     */
    public ConversationEntity getOneConversationById(Long userId, Long conversationId){
        ConversationEntity conversation = conversationDao.getOneById(conversationId);
        
        return (conversation != null && conversation.getUser().getId().equals(userId)) ? conversation : null;
    }
    
    /**
     * Supprimer une conversation
     * @param conversation conversation à supprimer
     */
    public void removeConversation(ConversationEntity conversation){
        conversationDao.remove(conversation);
    }
    
    /**
     * Obtenir un message par son identifiant
     * @param userId identifiant de l'utilisateur demandant la conversation
     * @param messageId identifiant du message
     * @return message
     */
    public MessageEntity getOneMessageById(Long userId, Long messageId){
        MessageEntity message = messageDao.getOneById(messageId);
        
        return (message != null && message.getConversation().getUser().getId().equals(userId)) ? message : null;
    }
    
    /**
     * Obtenir une conversation par le numéro de téléphone
     * @param userId identifiant de l'utilisateur
     * @param phone numéro de téléphone de la conversation
     * @return conversation
     */
    public ConversationEntity getOneConversationByPhone(Long userId, String phone){
        return conversationDao.getOneByPhone(phone, userId);
    }
    
    /**
     * Obtenir le nombre de messages du système
     * @return  nombre de messages du système
     */
    public int countAllMessages(){
        return (int) messageDao.count();
    }
}
