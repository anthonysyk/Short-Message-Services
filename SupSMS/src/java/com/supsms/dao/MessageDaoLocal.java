package com.supsms.dao;

import com.supsms.entity.ConversationEntity;
import com.supsms.entity.MessageEntity;
import javax.ejb.Local;

/**
 * Description des manipulations des messages avec JPA
 */
@Local
public interface MessageDaoLocal {

    /**
     * Persister un nouveau message
     * @param message à persister
     * @return message nouvellement persisté
     */
    MessageEntity create(MessageEntity message);

    /**
     * Supprimer tous les messages d'une conversation
     * @param conversation conversation contenant les messages
     */
    void removeAllByConversation(ConversationEntity conversation);

    /**
     * Obtenir un message par son identifiant
     * @param messageId identifiant de message
     * @return message
     */
    MessageEntity getOneById(long messageId);

    /**
     * Obtenir le nombre de messages
     * @return nombre de messages
     */
    long count();
}
