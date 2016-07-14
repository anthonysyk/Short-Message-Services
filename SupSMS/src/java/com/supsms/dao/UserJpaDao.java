package com.supsms.dao;

import com.supsms.entity.UserEntity;
import com.supsms.entity.UserEntity_;
import java.util.List;
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
 * Manipulation des utilisateurs avec JPA
 */
@Stateless
public class UserJpaDao implements UserJpaDaoLocal {
    @PersistenceContext(unitName = "SupSMS2PU")
    private EntityManager em;
    
    /**
     * @see UserJpaDaoLocal
     */
    @Override
    public UserEntity create(UserEntity user){
        em.persist(user);
        return user;
    }

    /**
     * @see UserJpaDaoLocal
     */
    @Override
    public List<UserEntity> getAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        
        // Création de la requête avec Criteria
        CriteriaQuery<UserEntity> criteriaQuery = cb.createQuery(UserEntity.class);
        Root<UserEntity> userRoot = criteriaQuery.from(UserEntity.class);
        
        return (List<UserEntity>) em.createQuery(criteriaQuery.select(userRoot)).getResultList();
    }

    /**
     * @see UserJpaDaoLocal
     */
    @Override
    public UserEntity getOneByPhone(String phoneNumber) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        
        // Création de la requête avec Criteria et MetaModel
        CriteriaQuery<UserEntity> criteriaQuery = cb.createQuery(UserEntity.class);
        Root<UserEntity> userRoot = criteriaQuery.from(UserEntity.class);
        criteriaQuery.where(
                cb.equal(
                        userRoot.get
        (UserEntity_.phone), 
                        phoneNumber));
        
        try {
            return em.createQuery(criteriaQuery).getSingleResult();
        } catch (NoResultException e){
            return null;
        }
    }

    /**
     * @see UserJpaDaoLocal
     */
    @Override
    public UserEntity getOneById(long id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        
        // Création de la requête avec Criteria et MetaModel
        CriteriaQuery<UserEntity> criteriaQuery = cb.createQuery(UserEntity.class);
        Root<UserEntity> userRoot = criteriaQuery.from(UserEntity.class);
        criteriaQuery.where(cb.equal(userRoot.get(UserEntity_.id), id));
        
        try {
            return em.createQuery(criteriaQuery).getSingleResult();
        } catch (NoResultException e){
            return null;
        }
    }

    /**
     * @see UserJpaDaoLocal
     */
    @Override
    public void remove(UserEntity user) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        
        // Création de la requête avec Criteria et MetaModel
        CriteriaDelete<UserEntity> criteriaQuery = cb.createCriteriaDelete(UserEntity.class);
        Root<UserEntity> userRoot = criteriaQuery.from(UserEntity.class);
        criteriaQuery.where(cb.equal(userRoot.get(UserEntity_.id), user.getId()));
        
        em.createQuery(criteriaQuery).executeUpdate();
    }

    /**
     * @see UserJpaDaoLocal
     */
    @Override
    public UserEntity update(UserEntity user) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        
        // Création de la requête avec Criteria et MetaModel
        CriteriaUpdate<UserEntity> criteriaQuery = cb.createCriteriaUpdate(UserEntity.class);
        Root<UserEntity> userRoot = criteriaQuery.from(UserEntity.class);
        
        criteriaQuery.set(UserEntity_.phone, user.getPhone());
        criteriaQuery.set(UserEntity_.password, user.getPassword());
        criteriaQuery.set(UserEntity_.fname, user.getFname());
        criteriaQuery.set(UserEntity_.lname, user.getLname());
        criteriaQuery.set(UserEntity_.mail, user.getMail());
        criteriaQuery.set(UserEntity_.prenium, user.isPrenium());
        criteriaQuery.set(UserEntity_.admin, user.isAdmin());
        criteriaQuery.set(UserEntity_.ccard, user.getCcard());
        
        criteriaQuery.where(cb.equal(userRoot.get(UserEntity_.id), user.getId()));
        em.createQuery(criteriaQuery).executeUpdate();
        
        return this.getOneById(user.getId());
    }

    /**
     * @see UserJpaDaoLocal
     */
    @Override
    public long count() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        
        // Création de la requête avec Criteria
        CriteriaQuery<Long> criteriaQuery = cb.createQuery(Long.class);
        
        return em.createQuery(criteriaQuery.select(cb.count(criteriaQuery.from(UserEntity.class)))).getSingleResult();
    }
}
