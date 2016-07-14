package com.supsms.service.soap;

import com.supsms.entity.MessageEntity;
import com.supsms.entity.UserEntity;
import com.supsms.service.ConversationService;
import com.supsms.service.UserService;
import java.util.ArrayList;
import java.util.Date;
import javax.ejb.EJB;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Web service SOAP de manipulation des messages
 */
@WebService(serviceName = "MessageSoapService")
public class MessageSoapService {
    @EJB
    private UserService userService;
    @EJB
    private ConversationService conversationService;
    
    /**
     * Ajouter un message
     * @param userId identifiant de l'utilisateur auquel ajouter le message
     * @param phone numéro de téléphone du correspondant
     * @param messageTxt contenu du message
     * @param incoming message entrant (true) ou sortant (false)
     * @param time horodatage du message
     */
    @WebMethod(operationName = "add")
    @Oneway
    public void add(long userId, String phone, String messageTxt, boolean incoming, Date time){
        MessageEntity message = new MessageEntity();
        message.setIncoming(incoming);
        message.setMessage(messageTxt);
        message.setPhone(phone);
        message.setTime(time);
        
        conversationService.addMessage(message, userId);
    }
    
    /**
     * Supprimer un message
     * @param userId identifiant de l'utilisateur duquel supprimer le message
     * @param messageId identifiant du message à supprimer
     */
    @WebMethod(operationName = "remove")
    @Oneway
    public void remove(long userId, long messageId){
        conversationService.removeConversation(
                conversationService.getOneConversationById(userId, messageId));
    }
    
    /**
     * Obtenir un message d'un utilisateur
     * @param userId identifiant de l'utilisateur
     * @param messageId identifiant du message
     * @return 
     */
    @WebMethod(operationName = "getOneById")
    public MessageEntity getOneById(long userId, long messageId){
        return conversationService.getOneMessageById(userId, messageId);
    }
    
    /**
     * Obtenir tous les messages d'un utilisateur
     * @param userId identifiant de l'utilisateur
     * @return colleciton de messages
     */
    @WebMethod(operationName = "getAll")
    public ArrayList<MessageEntity> getAll(int userId){
        ArrayList<MessageEntity> messages = new ArrayList<>();
        UserEntity user = userService.getUserById(userId);
        
        user.getConversations().stream().forEach((conversation) -> {
            messages.addAll(conversation.getMessages());
        });
        
        return messages;
    }
}
