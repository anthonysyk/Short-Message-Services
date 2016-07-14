package com.supsms.service;

import com.supsms.dao.ContactJpaDaoLocal;
import com.supsms.dao.UserJpaDaoLocal;
import com.supsms.entity.ContactEntity;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * Manipulations et services liés aux contacts
 */
@Stateless
public class ContactService {
    @EJB
    private ContactJpaDaoLocal contactJpaDao;
    
    @EJB
    private UserJpaDaoLocal userJpaDao;
    
    /**
     * Créer un contact
     * @param contact à créer
     */
    public void add(ContactEntity contact){
    
        contact.setUser(contact.getUser());
        contact.setTime(new Date());
        contactJpaDao.create(contact);
    }
    
    /**
     * Obtenir tous les contacts
     * @return collection de contacts
     */
    public List<ContactEntity> getAllContacts(){
        return contactJpaDao.getAll();
    }
    
    /**
     * Obtenir tous les contacts
     * @param id de l'utilisateur
     * @return collection de contacts
     */
    public List<ContactEntity> getAllContactsByUserId(long id){
        return contactJpaDao.getAllContactsByUserId(id);
    }
    
     /**
     * Obtenir un contact par son identifiant
     * @param id du contact
     * @param userId identifiant de l'utilisateur
     * @return contact trouvé
     */
    public ContactEntity getContactById(int id, long userId){
        return contactJpaDao.getOneById(id, userId);
    }
    
    /**
     * Supprimer un contact
     * @param contact à supprimer 
     */
    public void removeContact(ContactEntity contact){
        contactJpaDao.remove(contact);
    }
    
    /**
     * Mettre à jour un contact
     * @param contact à mettre à jour
     * @return contact modifié
     */
    public ContactEntity updateContact(ContactEntity contact){
        contact.setTime(new Date());
        
        return contactJpaDao.update(contact);
    }
    
    /**
     * Obtenir le nombre de contacts
     * @return nombre de contacts
     */
    public int countAllContacts(){
        return (int) contactJpaDao.count();
    }
    
    /**
     * Obtenir un contact d'un utilisateur par son numéro de téléphone
     * @param phoneNumber numéro du contact
     * @param userId identifiant de l'utilisateur
     * @return contact souhaité
     */
    public ContactEntity getOneContactByPhone(String phoneNumber, Long userId){
        return contactJpaDao.getOneByPhone(phoneNumber, userId);
    }
}
