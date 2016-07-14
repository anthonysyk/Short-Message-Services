package com.supsms.dao;

import com.supsms.entity.InvoiceEntity;
import com.supsms.entity.InvoiceEntity_;
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
 * Manipulation des factures avec JPA
 */
@Stateless
public class InvoiceJpaDao implements InvoiceJpaDaoLocal {
    @EJB
    private UserJpaDaoLocal userJpaDao;
    @PersistenceContext(unitName = "SupSMS2PU")
    private EntityManager em;
    
    /**
     * @see InvoiceJpaDaoLocal
     */
    @Override
    public InvoiceEntity create(InvoiceEntity invoice){
        em.persist(invoice);
        return invoice;
    }
    
    /**
     * @see ContactJpaDaoLocal
     */
    @Override
    public List<InvoiceEntity> getAll(){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        
        // Création de la requête avec Criteria
        CriteriaQuery<InvoiceEntity> criteriaQuery = cb.createQuery(InvoiceEntity.class);
        Root<InvoiceEntity> invoiceRoot = criteriaQuery.from(InvoiceEntity.class);
        
        return (List<InvoiceEntity>) em.createQuery(criteriaQuery.select(invoiceRoot)).getResultList();
    }
    
    /**
     * @see ContactJpaDaoLocal
     */
    @Override
    public List<InvoiceEntity> getAllInvoicesByUserId(long userId){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        
        // Création de la requête avec Criteria
        CriteriaQuery<InvoiceEntity> criteriaQuery = cb.createQuery(InvoiceEntity.class);
        Root<InvoiceEntity> invoiceRoot = criteriaQuery.from(InvoiceEntity.class);
        criteriaQuery.where(cb.equal(invoiceRoot.get(InvoiceEntity_.user), userJpaDao.getOneById(userId)));
        
        
        return (List<InvoiceEntity>) em.createQuery(criteriaQuery.select(invoiceRoot)).getResultList();
    }
    
    /**
     * @see ContactJpaDaoLocal
     */
    @Override
    public InvoiceEntity getOneById(long id, long userId){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        
        // Création de la requête avec Criteria et MetaModel
        CriteriaQuery<InvoiceEntity> criteriaQuery = cb.createQuery(InvoiceEntity.class);
        Root<InvoiceEntity> invoiceRoot = criteriaQuery.from(InvoiceEntity.class);
        criteriaQuery.where(cb.equal(invoiceRoot.get(InvoiceEntity_.user), userJpaDao.getOneById(userId)));
        criteriaQuery.where(cb.equal(invoiceRoot.get(InvoiceEntity_.id), id));
    
        try {
            return em.createQuery(criteriaQuery.select(invoiceRoot)).getSingleResult();
        } catch (NoResultException e){
            return null;
        }
    }
    
    /**
     * @see ContactJpaDaoLocal
     */
    @Override
    public InvoiceEntity getOneById(long id){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        
        // Création de la requête avec Criteria et MetaModel
        CriteriaQuery<InvoiceEntity> criteriaQuery = cb.createQuery(InvoiceEntity.class);
        Root<InvoiceEntity> invoiceRoot = criteriaQuery.from(InvoiceEntity.class);
        criteriaQuery.where(cb.equal(invoiceRoot.get(InvoiceEntity_.id), id));
    
        try {
            return em.createQuery(criteriaQuery.select(invoiceRoot)).getSingleResult();
        } catch (NoResultException e){
            return null;
        }
    }
    
    /**
     * @see ContactJpaDaoLocal
     */
    @Override
    public void remove(InvoiceEntity invoice){
    CriteriaBuilder cb = em.getCriteriaBuilder();
    
    // Création de la requête Criteria et MetaModel
    CriteriaDelete<InvoiceEntity> criteriaQuery = cb.createCriteriaDelete(InvoiceEntity.class);
    Root<InvoiceEntity> invoiceRoot = criteriaQuery.from(InvoiceEntity.class);
    criteriaQuery.where(cb.equal(invoiceRoot.get(InvoiceEntity_.id), invoice.getId()));
    
    em.createQuery(criteriaQuery).executeUpdate();
    }
    
    /**
     * @see ContactJpaDaoLocal
     */
    @Override
    public InvoiceEntity update(InvoiceEntity invoice){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        
        // Création de la requête avec Criteria et MetaModel
        CriteriaUpdate<InvoiceEntity> criteriaQuery = cb.createCriteriaUpdate(InvoiceEntity.class);
        Root<InvoiceEntity> contactRoot = criteriaQuery.from(InvoiceEntity.class);
        
        criteriaQuery.set(InvoiceEntity_.date, invoice.getDate());
        criteriaQuery.set(InvoiceEntity_.price, invoice.getPrice());
        criteriaQuery.set(InvoiceEntity_.taxe, invoice.getTaxe());
        
        criteriaQuery.where(cb.equal(contactRoot.get(InvoiceEntity_.id), invoice.getId()));
        em.createQuery(criteriaQuery).executeUpdate();
        
        return this.getOneById(invoice.getId());
    }
    
    /**
     * @see ContactJpaDaoLocal
     */
    @Override
    public long count() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        
        // Création de la requête avec Criteria
        CriteriaQuery<Long> criteriaQuery = cb.createQuery(Long.class);
        
        return em.createQuery(criteriaQuery.select(cb.count(criteriaQuery.from(InvoiceEntity.class)))).getSingleResult();
    }
    
}
