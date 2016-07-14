package com.supsms.web.form;

import com.supsms.entity.ContactEntity;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

/**
 * Validation des formulaires contact
 */
public class ContactFormValidator {
    public static enum ContactFormsNames{
        CONTACT
    }

    private static final String _fieldPhone = "inputPhone";
    private static final String _fieldFname = "inputFname";
    private static final String _fieldLname = "inputLname";
    private static final String _fieldAddress = "inputAddress";
    private static final String _fieldMail = "inputMail";
    
    private ArrayList<String> error;
    ContactEntity contact = null;
    
    /**
     * Valider un formulaire contact par son wrapper de requête
     * @param request wrapper de requête
     * @param formName nom du formulaire à valider
     */
    public ContactFormValidator(HttpServletRequest request, ContactFormsNames formName){
        this.contact = new ContactEntity();
        this.error = new ArrayList<>();
        
        String phone = FormValidator.getField(request, _fieldPhone);
        String fname = FormValidator.getField(request, _fieldFname);
        String lname = FormValidator.getField(request, _fieldLname);
        String address = FormValidator.getField(request, _fieldAddress);
        String mail = FormValidator.getField(request, _fieldMail);
                
        switch(formName){
            case CONTACT:
                validatePhone(phone);
                validateLname(lname);
                validateFname(fname);
                validateMail(mail);
                validateAddress(address);
                break;
        }
    }
    
    /**
     * Valider un formulaire contact par son wrapper de requête
     * @param request wrapper de requête
     * @param formName nom du formulaire à valider
     * @param contact contact à mettre à jour
     */
    public ContactFormValidator(HttpServletRequest request, ContactFormsNames formName, ContactEntity contact){
        this.contact = contact;
        this.error = new ArrayList<>();
        
        String phone = FormValidator.getField(request, _fieldPhone);
        String fname = FormValidator.getField(request, _fieldFname);
        String lname = FormValidator.getField(request, _fieldLname);
        String address = FormValidator.getField(request, _fieldAddress);
        String mail = FormValidator.getField(request, _fieldMail);
                
        switch(formName){
            case CONTACT:
                validatePhone(phone);
                validateLname(lname);
                validateFname(fname);
                validateMail(mail);
                validateAddress(address);
                break;
        }
    }
    
    /**
     * Valider le numéro de téléphone
     */
    private void validatePhone(String phone){
        try{
            this.contact.setPhone(FormValidator.validatePhone(phone, false));
        } catch(FormValidatorException e){
            
            switch(e.getIssue()){
                case ANOMALY:
                    this.error.add("numéro de téléphone invalide");
                    break;
                case NULL:
                    this.error.add("renseigner un numéro de téléphone");
                    break;
            }
        }
    }
    
    /**
     * Valider l'adresse mail
     */
    private void validateMail(String mail){
        try{
            this.contact.setMail(FormValidator.validateEmail(mail, false));
        } catch(FormValidatorException e){
            
            switch(e.getIssue()){
                case ANOMALY:
                    this.error.add("adresse mail invalide");
                    break;
                case NULL:
                    this.error.add("renseigner une adresse mail");
                    break;
            }
        }
    }
    
    /**
     * Valider le prénom
     */
    private void validateFname(String fname){
        try{
            this.contact.setFname(FormValidator.validateString(fname, false, 1));
        } catch(FormValidatorException e){
            
            switch(e.getIssue()){
                case NULL:
                    this.error.add("renseigner un prénom");
                    break;
            }
        }
    }
    
    /**
     * Valider le nom de famille
     */
    private void validateLname(String lname){
        try{
            this.contact.setLname(FormValidator.validateString(lname, false, 1));
        } catch(FormValidatorException e){
            
            switch(e.getIssue()){
                case NULL:
                    this.error.add("renseigner un nom de famille");
                    break;
            }
        }
    }
    
    /**
     * Valider l'adresse postale
     */
    private void validateAddress(String address){
        try{
            this.contact.setAddress(FormValidator.validateString(address, false, 1));
        } catch(FormValidatorException e){
            
            switch(e.getIssue()){
                case NULL:
                    this.error.add("renseigner une addresse postale");
                    break;
            }
        }
    }
    
    /**
     * Obtenir les erreurs du formulaire
     * @return collection de messages d'erreurs
     */
    public ArrayList<String> getError() {
        return this.error;
    }

    /**
     * Obtenir le contact issue de la validation
     * @return contact validé (mais pas forcément valide)
     */
    public ContactEntity getContact(){
        return this.contact;
    }
}
