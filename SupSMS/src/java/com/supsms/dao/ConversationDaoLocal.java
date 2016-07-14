package com.supsms.dao;

import com.supsms.entity.ConversationEntity;
import java.util.List;
import javax.ejb.Local;

/**
 * Description des manipulations des conversations avec JPA
 * @author remy
 */
@Local
public interface ConversationDaoLocal {

    /**
     * Obtenir une conversation par le numéro de téléphone de l'interlocuteur
     * @param phone numéro de téléphone de l'interlocuteur
     * @param userId identifiant de l'utilisateur demandant
     * @return conversation
     */
    ConversationEntity getOneByPhone(String phone,long userId);

    /**
     * Persister une conversation
     * @param conversation conversation à persister
     * @return conversation persistée
     */
    ConversationEntity create(ConversationEntity conversation);

    /**
     * Obtenir toutes les conversations d'un utilisateur
     * @param userId identifiant de l'utilisateur souhaité
     * @return collection de conversations
     */
    List<ConversationEntity> getAll(long userId);

    /**
     * Mettre à jour une conversation
     * @param conversation à mettre à jour
     * @return conversation mise à jour
     */
    ConversationEntity update(ConversationEntity conversation);

    /**
     * Obtenir une conversation par son identifiant
     * @param id identifiant de la conversation
     * @return conversation souhaitée
     */
    ConversationEntity getOneById(long id);

    /**
     * Supprimer une conversation
     * @param conversation conversation à supprimer
     */
    void remove(ConversationEntity conversation);
    
}
