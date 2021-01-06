/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.File;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import services.DataService;

/**
 *
 * @author Razvan
 */
@RequestScoped
@Named
public class FilesController {
    @Inject
    DataService dataService;
    
    private List<File> allFiles;
    
    @PostConstruct
    public void initialize(){
        this.allFiles = dataService.getAllFiles();
    }

    public List<File> getAllFiles() {
        return allFiles;
    }
    
    
    
}
