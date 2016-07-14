package com.supsms.service;

import com.supsms.dao.UserJpaDaoLocal;
import com.supsms.entity.UserEntity;
import com.supsms.web.servlet.user.SigninServlet;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * Manipulations et services liés aux utilisateurs
 */
@Stateless
public class UserService {
    @EJB
    private UserJpaDaoLocal userJpaDao;
    
    /**
     * Créer un utilisateur
     * @param user à créer (avec mot de passe en clair)
     */
    public void add(UserEntity user){
        try {
            user.setPassword(this.hashPassword(user.getPassword(), user.getSalt()));
        } catch (NoSuchAlgorithmException ex) {
            // L'algo SHA-512 n'est pas disponible
            Logger.getLogger(SigninServlet.class.getName())
                    .log(Level.SEVERE, "Algorithme de hachage des mots de passe non reconnue", ex);
        }
        
        userJpaDao.create(user);
    }
    
    /**
     * Obtenir un utilisateur par son numéro de téléphone et son mot de passe
     * @param phoneNumber
     * @param password
     * @return user trouvé
     */
    public UserEntity getUserByLogin(String phoneNumber, String password){
        UserEntity user = null;
        String givenPassword = null;
                
        try {
            user = userJpaDao.getOneByPhone(phoneNumber);
            givenPassword = this.hashPassword(password, user.getSalt());
            
        } catch (NoSuchAlgorithmException | NullPointerException e) {}
        
        // Test de l'utilisateur et comparaison des mots de passe
        return (user != null && user.getPassword().equals(givenPassword)) ? user : null;
    }
    
    /**
     * Obtenir tous les utilisateurs
     * @return collection d'utilisateurs
     */
    public List<UserEntity> getAllUsers(){
        return userJpaDao.getAll();
    }
    
    /**
     * Hachage d'un mot de passe avec son salt
     * @param clearPassword mot de passe en clair
     * @param salt du mot de passe
     * @return mot de passe haché
     * @throws NoSuchAlgorithmException 
     */
    public String hashPassword(String clearPassword, String salt) throws NoSuchAlgorithmException{
        MessageDigest encoder = MessageDigest.getInstance("SHA-512");
        encoder.update((clearPassword + "OCIJ398D972HIOUJFD98AS" + salt).getBytes());
        
        // Conversion du hash en String
        byte[] byteEncoded = encoder.digest();
        String encoded = "";
        for (int i=0; i < byteEncoded.length; i++) {
          encoded += Integer.toString( ( byteEncoded[i] & 0xff ) + 0x100, 16).substring( 1 );
        }
        
        return encoded;
    }
    
    /**
     * Obtenir un utilisateur par son identifiant
     * @param id de l'utilisateur
     * @return utilisateur trouvé
     */
    public UserEntity getUserById(int id){
        return userJpaDao.getOneById(id);
    }
    
    /**
     * Supprimer un utilisateur
     * @param user à supprimer 
     */
    public void removeUser(UserEntity user){
        userJpaDao.remove(user);
    }
    
    /**
     * Mettre à jour un utilisateur
     * @param user utilisateur à mettre à jour
     * @param passwordChanged le mot de passe a été modifié (true)
     * @return utilisateur modifié
     */
    public UserEntity updateUser(UserEntity user, boolean passwordChanged){
        if(passwordChanged){
            try {
                user.setPassword(this.hashPassword(user.getPassword(), user.getSalt()));
            } catch (NoSuchAlgorithmException ex) {
                // L'algo SHA-512 n'est pas disponible
                Logger.getLogger(SigninServlet.class.getName())
                        .log(Level.SEVERE, "Algorithme de hachage des mots de passe non reconnue", ex);
            }
        }
        
        return userJpaDao.update(user);
    }
    
    /**
     * Obtenir le nombre d'utilisateurs
     * @return nombre d'utilisateurs
     */
    public int countAllUsers(){
        return (int) userJpaDao.count();
    }
}
