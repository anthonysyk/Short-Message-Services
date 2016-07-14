package com.supsms.service;

import com.supsms.dao.InvoiceJpaDaoLocal;
import com.supsms.entity.InvoiceEntity;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * Manipulations et services liés aux factures
 */
@Stateless
public class InvoiceService {
    @EJB
    private InvoiceJpaDaoLocal invoiceJpaDao;
    
    /**
     * Créer une facture
     * @param invoice facture à créer
     * @return facture créée
     */
    public InvoiceEntity add(InvoiceEntity invoice){
        return invoiceJpaDao.create(invoice);
    }
    
    /**
     * Obtenir toutes les factures d'un utilisateur
     * @param userId utilisateur facturé
     * @return collection de factures
     */
    public List<InvoiceEntity> getAllInvoices(Long userId){
        return invoiceJpaDao.getAllInvoicesByUserId(userId);
    }
    
    /**
     * Obtenir une facture particulière
     * @param invoiceId identifiant de la facture
     * @param userId identifiant de l'utilisateur facturé
     * @return facture souhaitée
     */
    public InvoiceEntity getOneById(Long invoiceId, Long userId){
        return invoiceJpaDao.getOneById(invoiceId, userId);
    }
    
    /**
     * Obtenir toutes les factures
     * @return collection de factures
     */
    public List<InvoiceEntity> getAllInvoices(){
        return invoiceJpaDao.getAll();
    }
}