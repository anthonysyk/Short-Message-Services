package com.supsms.dao;

import com.supsms.entity.InvoiceEntity;
import java.util.List;
import javax.ejb.Local;

/**
 * Description des manipulations des factures avec JPA
 */
@Local
public interface InvoiceJpaDaoLocal {
    
    /**
     * Persister une nouvelle facture
     * @param invoice à persister
     * @return invoice nouvellement persistée
     */
    InvoiceEntity create(InvoiceEntity invoice);
    
    /**
     * Obtenir toutes les factures
     * @return collection de factures
     */
    public List<InvoiceEntity> getAll();
    
    /**
     * Obtenir toutes les factures de l'utilisateur
     * @param userId identifiant de l'utilisateur
     * @return collection de facture
     */
    public List<InvoiceEntity> getAllInvoicesByUserId(long userId);
    
    /**
     * Obtenir une facture par son identifiant et l'identifiant de l'utilsiateur
     * @param id de la facture
     * @param userId identifiant de l'utilisateur
     * @return invoice souhaitée
     */
    public InvoiceEntity getOneById(long id, long userId);
    
    /**
     * Obtenir une facture par son ID
     * @param id de la facture
     * @return invoice souhaitée
     */
    public InvoiceEntity getOneById(long id);
    
    /**
     * Supprimer une facture
     * @param invoice à supprimer
     */
    public void remove(InvoiceEntity invoice);
    
    /**
     * Mettre à jour une facture
     * @param invoice à mettre à jour
     * @return facture modifiée
     */
    public InvoiceEntity update(InvoiceEntity invoice);
    
    /**
     * Obtenir le nombre de factures
     * @return nombre de factures
     */
    public long count();
    
}
