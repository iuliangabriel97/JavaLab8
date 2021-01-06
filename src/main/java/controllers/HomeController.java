/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.File;
import entities.User;
import java.util.List;
import java.util.Optional;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.SecurityContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import services.DataService;

/**
 *
 * @author gcrisnuta
 */
@RequestScoped
@Named
public class HomeController {

    @Inject
    DataService dataService;

    @Inject
    SecurityContext securityContext;

    @Inject
    FacesContext facesContext;

    private Optional<User> currentUser;
    private List<File> currentFiles;

    @PostConstruct
    public void initialize() {
        String username = securityContext.getCallerPrincipal().getName();
        this.currentUser = dataService.getUser(username);
        this.currentUser.ifPresent(user -> {
            this.currentFiles = dataService.getFiles(user);
        });
    }

    public User getCurrentUser() {
        return this.currentUser.orElse(null);
    }

    public List<File> getCurrentFiles() {
        return currentFiles;
    }

    public String logout() throws ServletException {
        ExternalContext ec = facesContext.getExternalContext();
        ((HttpServletRequest) ec.getRequest()).logout();
        return "/login.xhtml?faces-redirect=true";
    }

    public boolean isAllowedToSeeUsers() {
//        System.out.println(securityContext.getCallerPrincipal().getName());
//        System.out.println("??ADMIN?? " + securityContext.isCallerInRole("admin"));
//        System.out.println("??ADMIN?? " + securityContext.isUserI("admin"))
        return "admin".equals(securityContext.getCallerPrincipal().getName());
//        return securityContext.isCallerInRole("admin");
    }

}
