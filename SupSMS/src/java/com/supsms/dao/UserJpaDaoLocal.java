package com.supsms.dao;

import com.supsms.entity.UserEntity;
import java.util.List;
import javax.ejb.Local;

/**
 * Description des manipulations des utilisateurs avec JPA
 */
@Local
public interface UserJpaDaoLocal{
    
    /**
     * Persister un nouvel utilisateur
     * @param user (avec mot de passe en clair)
     * @return user nouvellement persisté
     */
    public UserEntity create(UserEntity user);
    
    /**
     * Obtenir un utilisateur par son numéro de téléphone
     * @param phoneNumber
     * @return  user souhaité
     */
    public UserEntity getOneByPhone(String phoneNumber);
    
    /**
     * Obtenir tous les utilisateurs
     * @return collection d'utilisateurs
     */
    public List<UserEntity> getAll();
    
    /**
     * Obtenir un utilisateur par son identifiant
     * @param id de l'utilisateur
     * @return utilisateur souhaité
     */
    public UserEntity getOneById(long id);
    
    /**
     * Supprimer un utilisateur
     * @param user à supprimer
     */
    public void remove(UserEntity user);
    
    /**
     * Mettre à jour un utilisateur
     * @param user à mettre à jour
     * @return utilisateur modifié
     */
    public UserEntity update(UserEntity user);
    
    /**
     * Obtenir le nombre d'utilisateurs
     * @return nombre d'utilisateurs
     */
    public long count();
}
