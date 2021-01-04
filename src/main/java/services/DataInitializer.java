/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.User;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

/**
 *
 * @author gcrisnuta
 */

@ApplicationScoped
public class DataInitializer {
    @Inject
    DataService dataService;
    
    public void execute(@Observes @Initialized(ApplicationScoped.class) Object event){
        if(dataService.getAllUsers().isEmpty()){
            User dummyUser = dataService.createUser("Dummy User", "dummy_user", "dummy_password", "user");
            User adminUser = dataService.createUser("Admin", "admin", "admin", "admin");
            
            
            dataService.createFile("file1", adminUser);
            dataService.createFile("file2", dummyUser);
            
        }
    }
}
