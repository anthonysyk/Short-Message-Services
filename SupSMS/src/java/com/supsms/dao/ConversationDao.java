package com.supsms.dao;

import com.supsms.entity.ConversationEntity;
import com.supsms.entity.ConversationEntity_;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

/**
 * Manipulation des conversations avec JPA
 */
@Stateless
public class ConversationDao implements ConversationDaoLocal {
    @EJB
    private MessageDaoLocal messageDao;
    @EJB
    private UserJpaDaoLocal userJpaDao;
    
    @PersistenceContext(unitName = "SupSMS2PU")
    private EntityManager em;

    /**
     * @see ConversationDaoLocal
     */
    @Override
    public ConversationEntity getOneByPhone(String phone, long userId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        
        // Création de la requête avec Criteria
        CriteriaQuery<ConversationEntity> criteriaQuery = cb.createQuery(ConversationEntity.class);
        Root<ConversationEntity> conversationRoot = criteriaQuery.from(ConversationEntity.class);
        criteriaQuery.where(cb.equal(conversationRoot.get(ConversationEntity_.user), userJpaDao.getOneById(userId)));
        criteriaQuery.where(cb.equal(conversationRoot.get(ConversationEntity_.phone), phone));
        
        ConversationEntity result = null;
        try {
            result = em.createQuery(criteriaQuery.select(conversationRoot)).getSingleResult();
        } catch (NoResultException e) {}
        
        return result;
    }

    /**
     * @see ConversationDaoLocal
     */
    @Override
    public ConversationEntity create(ConversationEntity conversation) {
        em.persist(conversation);
        return conversation;
    }

    /**
     * @see ConversationDaoLocal
     */
    @Override
    public List<ConversationEntity> getAll(long userId) {
        em.getEntityManagerFactory().getCache().evictAll();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        
        // Création de la requête avec Criteria
        CriteriaQuery<ConversationEntity> criteriaQuery = cb.createQuery(ConversationEntity.class);
        Root<ConversationEntity> conversationRoot = criteriaQuery.from(ConversationEntity.class);
        criteriaQuery.where(cb.equal(
                conversationRoot.get(ConversationEntity_.user), 
                userJpaDao.getOneById(userId)));
        criteriaQuery.orderBy(cb.desc(conversationRoot.get(ConversationEntity_.time)));
        
        return (List<ConversationEntity>) em.createQuery(criteriaQuery.select(conversationRoot)).getResultList();
    }

    /**
     * @see ConversationDaoLocal
     */
    @Override
    public ConversationEntity update(ConversationEntity conversation) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        
        // Création de la requête avec Criteria et MetaModel
        CriteriaUpdate<ConversationEntity> criteriaQuery = cb.createCriteriaUpdate(ConversationEntity.class);
        Root<ConversationEntity> conversationRoot = criteriaQuery.from(ConversationEntity.class);
        
        criteriaQuery.set(ConversationEntity_.phone, conversation.getPhone());
        criteriaQuery.set(ConversationEntity_.time, conversation.getTime());
        criteriaQuery.set(ConversationEntity_.user, conversation.getUser());
        
        criteriaQuery.where(cb.equal(conversationRoot.get(ConversationEntity_.id), conversation.getId()));
        em.createQuery(criteriaQuery).executeUpdate();
        
        return this.getOneById(conversation.getId());
    }

    /**
     * @see ConversationDaoLocal
     */
    @Override
    public ConversationEntity getOneById(long id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        
        // Création de la requête avec Criteria
        CriteriaQuery<ConversationEntity> criteriaQuery = cb.createQuery(ConversationEntity.class);
        Root<ConversationEntity> conversationRoot = criteriaQuery.from(ConversationEntity.class);
        criteriaQuery.where(cb.equal(
                conversationRoot.get(ConversationEntity_.id), id));
        
        ConversationEntity result = null;
        
        try {
            result = em.createQuery(criteriaQuery.select(conversationRoot)).getSingleResult();
        } catch (NoResultException e) {}
        
        return result;
    }

    /**
     * @see ConversationDaoLocal
     */
    @Override
    public void remove(ConversationEntity conversation) {
        messageDao.removeAllByConversation(conversation);
        
        CriteriaBuilder cb = em.getCriteriaBuilder();
        
        // Création de la requête avec Criteria et MetaModel
        CriteriaDelete<ConversationEntity> criteriaQuery = cb.createCriteriaDelete(ConversationEntity.class);
        Root<ConversationEntity> conversationRoot = criteriaQuery.from(ConversationEntity.class);
        criteriaQuery.where(cb.equal(conversationRoot.get(ConversationEntity_.id), conversation.getId()));
        
        em.createQuery(criteriaQuery).executeUpdate();
    }
}