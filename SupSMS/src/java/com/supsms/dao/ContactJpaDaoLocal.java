package com.supsms.dao;

import com.supsms.entity.ContactEntity;
import java.util.List;
import javax.ejb.Local;

/**
 * Description des manipulations des contacts avec JPA
 */
@Local
public interface ContactJpaDaoLocal {
    
    /**
     * Persister un nouveau contact
     * @param contact à persister
     * @return contact nouvellement persisté
     */
    ContactEntity create(ContactEntity contact);
    
    
    /**
     * Obtenir un contact par son numéro de téléphone
     * @param phoneNumber numéro de téléphone recherché
     * @param userId identifiant de l'utilisateur possédant le contact
     * @return  user ou null si introuvable
     */
    public ContactEntity getOneByPhone(String phoneNumber, long userId);
    
    /**
     * Obtenir tous les contacts
     * @return collection de contacts
     */
    public List<ContactEntity> getAll();
    
    /**
     * Obtenir tous les contacts
     * @param userId identifiant de l'utilisateur
     * @return collection de contacts
     */
    public List<ContactEntity> getAllContactsByUserId(long userId);
    
    /**
     * Obtenir un contact par son identifiant et l'identifiant de l'utilsiateur
     * @param id du contact
     * @param userId identifiant de l'utilisateur
     * @return contact souhaité
     */
    public ContactEntity getOneById(long id, long userId);
    
    /**
     * Obtenir un contact par son identifiant
     * @param id du contact
     * @return contact souhaité
     */
    public ContactEntity getOneById(long id);
    
    /**
     * Supprimer un contact
     * @param contact à supprimer
     */
    public void remove(ContactEntity contact);
    
    /**
     * Mettre à jour un contact
     * @param contact à mettre à jour
     * @return contact modifié
     */
    public ContactEntity update(ContactEntity contact);
    
    /**
     * Obtenir le nombre de contacts
     * @return nombre de contacts
     */
    public long count();
    
}
