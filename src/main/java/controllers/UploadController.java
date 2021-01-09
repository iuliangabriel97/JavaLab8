/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import config.ApplicationConfig;
import config.TimeConfig;
import entities.User;
import interceptors.Log;
import java.util.Calendar;
import java.util.Optional;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.SecurityContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import org.primefaces.model.file.UploadedFiles;
import services.DataService;

/**
 *
 * @author gcrisnuta
 */
@Named
@RequestScoped
public class UploadController {
    private UploadedFile file;
    private boolean isPublic;
 
    @Inject
    SecurityContext securityContext;
    
    @Inject
    DataService dataService;
    
    @Inject
    TimeConfig timeConfig;
    
    private Optional<User> currentUser;
    
    @PostConstruct
    public void init()
    {
        this.isPublic = false;
    }
    
    public UploadedFile getFile() {
        return file;
    }
 
    public void setFile(UploadedFile file) {
        this.file = file;
    }
    
    @Log
    public void handleFileUpload(FileUploadEvent event) {
        
        Calendar now = Calendar.getInstance();
        now.clear();
        
        now.set(Calendar.HOUR_OF_DAY, Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
        now.set(Calendar.MINUTE, Calendar.getInstance().get(Calendar.MINUTE));
        
        if (now.getTime().after(timeConfig.getUploadIntervalStart()) && now.getTime().before(timeConfig.getUploadIntervalEnd()))
        {
            String username = securityContext.getCallerPrincipal().getName();
            this.currentUser = dataService.getUser(username);

            dataService.createFile(event.getFile().getFileName(), this.currentUser.get());

            FacesMessage msg = new FacesMessage("Successful", event.getFile().getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        else
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Cannot upload outside of the interval!", null));
        }
    }
}
