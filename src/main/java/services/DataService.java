/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.File;
import entities.User;
import java.util.List;
import java.util.Optional;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;
import javax.transaction.Transactional;

/**
 *
 * @author gcrisnuta
 */
@ApplicationScoped
public class DataService {
    @PersistenceContext(unitName="lab8")
    EntityManager em;
    
    @Inject
    Pbkdf2PasswordHash passwordHasher;
    
    @Transactional
    public User createUser(String name, String username, String password, String group){
        User newUser = new User(name, username, passwordHasher.generate(password.toCharArray()), group);
        em.persist(newUser);
        em.flush();
        return newUser;
    }
    
    @Transactional
    public File createFile(String description, User user){
        File newFile = new File(description, user);
        em.persist(newFile);
        em.flush();
        return newFile;
    }
    
    public List<User> getAllUsers(){
        return em.createNamedQuery("User.all", User.class).getResultList();
    }
    
    public List<File> getAllFiles(){
        return em.createNamedQuery("File.all", File.class).getResultList();
    }
    
    public Optional<User> getUser(String username){
        return em.createNamedQuery("User.byUsername", User.class).setParameter("username", username).getResultList().stream().findFirst();
    }
    
    public List<File> getFiles(User user){
        return em.createNamedQuery("File.byUser", File.class).setParameter("userId", user.getId()).getResultList();
    }
    
}
