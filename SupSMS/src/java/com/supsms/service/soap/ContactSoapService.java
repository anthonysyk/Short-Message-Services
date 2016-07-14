package com.supsms.service.soap;

import com.supsms.entity.ContactEntity;
import com.supsms.service.ContactService;
import com.supsms.service.UserService;
import java.util.ArrayList;
import java.util.Date;
import javax.ejb.EJB;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Web service SOAP de manipulation des contacts
 */
@WebService(serviceName = "ContactSoapService")
public class ContactSoapService {
    @EJB
    private UserService userService;
    @EJB
    private ContactService contactService;

    /**
     * Ajouter un contact
     * @param userId identfiant de l'utilisateur auquel ajouter le contact
     * @param phone numéro de telephone du contact
     * @param adress adresse postale du contact
     * @param mail adresse mail du contact
     * @param fname prénom du contact
     * @param lname  nom de famille du contact
     */
    @WebMethod(operationName = "add")
    @Oneway
    public void add(int userId, String phone, String adress, String mail, String fname, String lname) {
        ContactEntity contact = new ContactEntity();
        contact.setAddress(adress);
        contact.setFname(fname);
        contact.setLname(lname);
        contact.setMail(mail);
        contact.setPhone(phone);
        contact.setUser(userService.getUserById(userId));
        contact.setTime(new Date());
        
        contactService.add(contact);
    }

    /**
     * Supprimer un contact
     * @param userId identifiant de l'utilisateur duquel supprimer le contact
     * @param contactId identifiant du contact
     */
    @WebMethod(operationName = "remove")
    @Oneway
    public void remove(int userId, int contactId) {
        contactService.removeContact(contactService.getContactById(userId, contactId));
    }

    /**
     * Mettre à jour un contact
     * @param userId identifiant de l'utilisateur possaidant le contact
     * @param contactId identifiant du contact à mettre à jour
     * @param phone numéro de telephone du contact
     * @param adress adresse postale du contact
     * @param mail adresse mail du contact
     * @param fname prénom du contact
     * @param lname nom de famille du contact
     */
    @WebMethod(operationName = "update")
    @Oneway
    public void update(int userId, int contactId, String phone, String adress, String mail, String fname, String lname) {
        ContactEntity contact = contactService.getContactById(contactId, userId);
        contact.setAddress(adress);
        contact.setFname(fname);
        contact.setLname(lname);
        contact.setMail(mail);
        contact.setPhone(phone);
        
        contactService.updateContact(contact);
    }
    
    /**
     * Otenir la liste de tous les contacts d'un utilisateur
     * @param userId identifiant de l'utilisateur
     * @return collection de contacts
     */
    @WebMethod(operationName = "getAll")
    public ArrayList<ContactEntity> getAll(int userId){
        return new ArrayList<>(contactService.getAllContactsByUserId(userId));
    }
    
    /**
     * Obtenir un contact d'un utilisateur
     * @param userId identifiant de l'utilisateur
     * @param contactId identifiant du contact
     * @return contact souhaité
     */
    @WebMethod(operationName = "getOneById")
    public ContactEntity getOneById(int userId, int contactId){
        return contactService.getContactById(contactId, userId);
    }    
}
