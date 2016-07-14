package com.supsms.web.form;

import com.supsms.entity.UserEntity;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

/**
 * Validation des formulaires utilisateur
 */
public class UserFormValidator {
    public static enum UserFormsNames {
        SIGNIN, PROFIL_INFO, PROFIL_PASSWORD
    }
    
    private static final String _fieldPhone = "inputPhone";
    private static final String _fieldPassword1 = "inputPassword1";
    private static final String _fieldPassword2 = "inputPassword2";
    private static final String _fieldMail = "inputMail";
    private static final String _fieldFname = "inputFname";
    private static final String _fieldLname = "inputLname";
    private static final String _fieldCcard = "inputCcard";
    
    private ArrayList<String> error;
    UserEntity user = null;
    
    /**
     * Valider un formulaire utilisateur par son wrapper de requête
     * @param request wrapper de requête
     * @param formName nom du formulaire à valider
     */
    public UserFormValidator(HttpServletRequest request, UserFormsNames formName){
        this.user = new UserEntity();
        this.error = new ArrayList<>();
        this.validate(request, formName);
    }
    
    /**
     * Valider un formulaire utilisateur par son wrapper de requête
     * @param request wrapper de requête
     * @param formName nom du formulaire à valider
     * @param user utilisateur à mettre à jour
     */
    public UserFormValidator(HttpServletRequest request, UserFormsNames formName, UserEntity user){
        this.user = user;
        this.error = new ArrayList<>();
        this.validate(request, formName);
    }
    
    /**
     * Déclencher la validation
     * @param request
     * @param formName 
     */
    private void validate(HttpServletRequest request, UserFormsNames formName){        
        String phone = FormValidator.getField(request, _fieldPhone);
        String password1 = FormValidator.getField(request, _fieldPassword1);
        String password2 = FormValidator.getField(request, _fieldPassword2);
        String mail = FormValidator.getField(request, _fieldMail);
        String fname = FormValidator.getField(request, _fieldFname);
        String lname = FormValidator.getField(request, _fieldLname);
        String ccard = FormValidator.getField(request, _fieldCcard);
        
        switch(formName){
            case SIGNIN:
                validatePhone(phone);
                validatePassword(password1, password2);
                validateMail(mail);
                validateLname(lname);
                validateFname(fname);
                validateCcard(ccard);
                break;
            case PROFIL_INFO:
                validateMail(mail);
                validateLname(lname);
                validateFname(fname);
                validateCcard(ccard);
                break;
            case PROFIL_PASSWORD:
                validatePassword(password1, password2);
                break;
        }
    }
    
    /**
     * Valider le numéro de téléphone
     */
    private void validatePhone(String phone){
        try{
            this.user.setPhone(FormValidator.validatePhone(phone, false));
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
     * Valider les mot de passe
     */
    private void validatePassword(String password1, String password2){
        try{
            this.user.setPassword(FormValidator.validatePassword(password1, password2, false, 5));
        } catch(FormValidatorException e){
            
            switch(e.getIssue()){
                case TOO_SHORT:
                    this.error.add("mot de passe trop court");
                    break;
                case DIFFERENT:
                    this.error.add("mots de passe différents");
                    break;
                case NULL:
                    this.error.add("renseigner un mot de passe");
                    break;
            }
        }
    }
    
    /**
     * Valider l'adresse mail
     */
    private void validateMail(String mail){
        try{
            this.user.setMail(FormValidator.validateEmail(mail, false));
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
            this.user.setFname(FormValidator.validateString(fname, false, 1));
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
            this.user.setLname(FormValidator.validateString(lname, false, 1));
        } catch(FormValidatorException e){
            
            switch(e.getIssue()){
                case NULL:
                    this.error.add("renseigner un nom de famille");
                    break;
            }
        }
    }
    
    /**
     * Valider la carte de crédit
     */
    private void validateCcard(String ccard){
        try{
            this.user.setCcard(FormValidator.validateString(ccard, false, 1));
        } catch(FormValidatorException e){
            
            switch(e.getIssue()){
                case NULL:
                    this.error.add("renseigner un numéro de carte de crédit");
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
     * Obtenir l'utilisateur issue de la validation
     * @return utilisateur validé (mais pas forcément valide)
     */
    public UserEntity getUser(){
        return this.user;
    }
}
