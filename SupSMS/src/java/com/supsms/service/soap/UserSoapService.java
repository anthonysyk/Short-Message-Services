package com.supsms.service.soap;

import com.supsms.entity.UserEntity;
import com.supsms.service.UserService;
import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Web service SOAP de manipulation des utilisateurs
 */
@WebService(serviceName = "UserSoapService")
public class UserSoapService {
    @EJB
    private UserService userService;
    
    /**
     * Obtenir les informations d'un utilisateur par ses identifiants
     * @param phone numéro de telephone de l'utilisateur
     * @param password mot de passe de l'utilisateur en clair
     * @return utilisateur souhaité si connexion réussie
     */
    @WebMethod(operationName = "getUserIdByLogin")
    public UserEntity getUserIdByLogin(String phone, String password){
        UserEntity user = userService.getUserByLogin(phone, password);
        
        if(user != null){
            user.setPassword(null);
            user.setSalt(null);
            user.setCcard(null);
        }
        
        return user;
    }
}
