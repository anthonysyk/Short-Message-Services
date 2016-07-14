package com.supsms.dao;

import com.supsms.entity.ContactEntity;
import com.supsms.entity.ContactEntity_;
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
 * Manipulation des contacts avec JPA
 */
@Stateless
public class ContactJpaDao implements ContactJpaDaoLocal {
    @EJB
    private UserJpaDaoLocal userJpaDao;
    
    @PersistenceContext(unitName = "SupSMS2PU")
    private EntityManager em;
    
    /**
     * @see ContactJpaDaoLocal
     */
    @Override
    public ContactEntity create(ContactEntity contact){
        em.persist(contact);
        return contact;
    }
    
    /**
     * @see ContactJpaDaoLocal
     */
    @Override
    public List<ContactEntity> getAll(){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        
        // Création de la requête avec Criteria
        CriteriaQuery<ContactEntity> criteriaQuery = cb.createQuery(ContactEntity.class);
        Root<ContactEntity> contactRoot = criteriaQuery.from(ContactEntity.class);
        
        return (List<ContactEntity>) em.createQuery(criteriaQuery.select(contactRoot)).getResultList();
    }
    
    /**
     * @see ContactJpaDaoLocal
     */
    @Override
    public List<ContactEntity> getAllContactsByUserId(long userId){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        
        // Création de la requête avec Criteria
        CriteriaQuery<ContactEntity> criteriaQuery = cb.createQuery(ContactEntity.class);
        Root<ContactEntity> contactRoot = criteriaQuery.from(ContactEntity.class);
        criteriaQuery.where(cb.equal(contactRoot.get(ContactEntity_.user), userJpaDao.getOneById(userId)));
        
        
        return (List<ContactEntity>) em.createQuery(criteriaQuery.select(contactRoot)).getResultList();
    }
    
    /**
     * @see ContactJpaDaoLocal
     */
    @Override
    public ContactEntity getOneByPhone(String phoneNumber, long userId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        
        // Création de la requête avec Criteria et MetaModel
        CriteriaQuery<ContactEntity> criteriaQuery = cb.createQuery(ContactEntity.class);
        Root<ContactEntity> contactRoot = criteriaQuery.from(ContactEntity.class);
        criteriaQuery.where(
                cb.equal(contactRoot.get(ContactEntity_.user), userJpaDao.getOneById(userId)));
        criteriaQuery.where(
                cb.equal(contactRoot.get(ContactEntity_.phone), phoneNumber));
        
        try {
            return em.createQuery(criteriaQuery.select(contactRoot)).getSingleResult();
        } catch (NoResultException e){
            return null;
        }
    }
    
    /**
     * @see ContactJpaDaoLocal
     */
    @Override
    public ContactEntity getOneById(long id, long userId){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        
        // Création de la requête avec Criteria et MetaModel
        CriteriaQuery<ContactEntity> criteriaQuery = cb.createQuery(ContactEntity.class);
        Root<ContactEntity> contactRoot = criteriaQuery.from(ContactEntity.class);
        criteriaQuery.where(cb.equal(contactRoot.get(ContactEntity_.id), id));
    
        try {
            return em.createQuery(criteriaQuery).getSingleResult();
        } catch (NoResultException e){
            return null;
        }
    }
    
    /**
     * @see ContactJpaDaoLocal
     */
    @Override
    public ContactEntity getOneById(long id){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        
        // Création de la requête avec Criteria et MetaModel
        CriteriaQuery<ContactEntity> criteriaQuery = cb.createQuery(ContactEntity.class);
        Root<ContactEntity> contactRoot = criteriaQuery.from(ContactEntity.class);
        criteriaQuery.where(cb.equal(contactRoot.get(ContactEntity_.id), id));
    
        try {
            return em.createQuery(criteriaQuery).getSingleResult();
        } catch (NoResultException e){
            return null;
        }
    }
    
    /**
     * @see ContactJpaDaoLocal
     */
    @Override
    public void remove(ContactEntity contact){
    CriteriaBuilder cb = em.getCriteriaBuilder();
    
    // Création de la requête Criteria et MetaModel
    CriteriaDelete<ContactEntity> criteriaQuery = cb.createCriteriaDelete(ContactEntity.class);
    Root<ContactEntity> contactRoot = criteriaQuery.from(ContactEntity.class);
    criteriaQuery.where(cb.equal(contactRoot.get(ContactEntity_.id), contact.getId()));
    
    em.createQuery(criteriaQuery).executeUpdate();
    }
    
    /**
     * @see ContactJpaDaoLocal
     */
    @Override
    public ContactEntity update(ContactEntity contact){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        
        // Création de la requête avec Criteria et MetaModel
        CriteriaUpdate<ContactEntity> criteriaQuery = cb.createCriteriaUpdate(ContactEntity.class);
        Root<ContactEntity> contactRoot = criteriaQuery.from(ContactEntity.class);
        
        criteriaQuery.set(ContactEntity_.phone, contact.getPhone());
        criteriaQuery.set(ContactEntity_.fname, contact.getFname());
        criteriaQuery.set(ContactEntity_.lname, contact.getLname());
        criteriaQuery.set(ContactEntity_.address, contact.getAddress());
        criteriaQuery.set(ContactEntity_.mail, contact.getMail());
        
        criteriaQuery.where(cb.equal(contactRoot.get(ContactEntity_.id), contact.getId()));
        em.createQuery(criteriaQuery).executeUpdate();
        
        return this.getOneById(contact.getId());
    }
    
    /**
     * @see ContactJpaDaoLocal
     * @return 
     */
    @Override
    public long count() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        
        // Création de la requête avec Criteria
        CriteriaQuery<Long> criteriaQuery = cb.createQuery(Long.class);
        
        return em.createQuery(criteriaQuery.select(cb.count(criteriaQuery.from(ContactEntity.class)))).getSingleResult();
    }
}
