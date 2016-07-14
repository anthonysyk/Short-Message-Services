package com.supsms.dao;

import com.supsms.entity.ConversationEntity;
import com.supsms.entity.MessageEntity;
import com.supsms.entity.MessageEntity_;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/** 
 * Manipulation des messages avec JPA
 */
@Stateless
public class MessageDao implements MessageDaoLocal{
    @EJB
    private UserJpaDaoLocal userJpaDao;
    
    @PersistenceContext(unitName = "SupSMS2PU")
    private EntityManager em;

    /**
     * @see MessageDaoLocal
     */
    @Override
    public MessageEntity create(MessageEntity message) {
        em.persist(message);
        return message;
    }
    
    /**
     * @see MessageDaoLocal
     */
    @Override
    public void removeAllByConversation(ConversationEntity conversation) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        
        // Création de la requête avec Criteria et MetaModel
        CriteriaDelete<MessageEntity> criteriaQuery = cb.createCriteriaDelete(MessageEntity.class);
        Root<MessageEntity> messageRoot = criteriaQuery.from(MessageEntity.class);
        criteriaQuery.where(cb.equal(messageRoot.get(MessageEntity_.conversation), conversation));
        
        em.createQuery(criteriaQuery).executeUpdate();
    }
    
    /**
     * @see MessageDaoLocal
     */
    @Override
    public MessageEntity getOneById(long messageId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        
        // Création de la requête avec Criteria
        CriteriaQuery<MessageEntity> criteriaQuery = cb.createQuery(MessageEntity.class);
        Root<MessageEntity> messageRoot = criteriaQuery.from(MessageEntity.class);
        criteriaQuery.where(cb.equal(
                messageRoot.get(MessageEntity_.id), messageId));
        
        MessageEntity result = null;
        
        try {
            result = em.createQuery(criteriaQuery.select(messageRoot)).getSingleResult();
        } catch (NoResultException e) {}
        
        return result;
    }

    /**
     * @see MessageDaoLocal
     */
    @Override
    public long count() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        
        // Création de la requête avec Criteria
        CriteriaQuery<Long> criteriaQuery = cb.createQuery(Long.class);
        
        return em.createQuery(criteriaQuery.select(cb.count(criteriaQuery.from(MessageEntity.class)))).getSingleResult();
    }    
}
