package com.supsms.web.form;

import com.supsms.entity.MessageEntity;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

/**
 * Validation des formulaires conversation
 */
public class ConversationFormValidator {
    public static enum ConversationFormsNames {
        NEW_MSG_PHONE
    }
    
    private static final String _fieldPhone = "inputPhone";
    private static final String _fieldMessage = "inputMessage";
    
    private ArrayList<String> error;
    MessageEntity message = null;
    
    /**
     * Valider un formulaire conversation par son wrapper de requête
     * @param request wrapper de requête
     * @param formName nom du formulaire à valider
     */
    public ConversationFormValidator(HttpServletRequest request, ConversationFormsNames formName){
        this.message = new MessageEntity();
        this.error = new ArrayList<>();
        this.validate(request, formName);
    }
    
    /**
     * Déclancher la validation
     * @param request
     * @param formName 
     */
    private void validate(HttpServletRequest request, ConversationFormsNames formName){        
        String phone = FormValidator.getField(request, _fieldPhone);        
        String msgText = FormValidator.getField(request, _fieldMessage);
        
        switch(formName){
            case NEW_MSG_PHONE:
                validatePhone(phone);
                validateMessage(msgText);
                break;
        }
    }
    
    /**
     * Valider le numéro de téléphone
     */
    private void validatePhone(String phone){
        try{
            this.message.setPhone(FormValidator.validatePhone(phone, false));
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
     * Valider le message
     */
    private void validateMessage(String message){
        try{
            this.message.setMessage(FormValidator.validateString(message, false, 1));
        } catch(FormValidatorException e){
            
            switch(e.getIssue()){
                case NULL:
                    this.error.add("renseigner un message");
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
     * Obtenir le message issue de la validation
     * @return message validé (mais pas forcément valide)
     */
    public MessageEntity getMessage(){
        return this.message;
    }
}
