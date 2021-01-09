package config;


import java.util.Calendar;
import java.util.Date;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Model;
import javax.faces.annotation.FacesConfig;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;
import javax.security.enterprise.authentication.mechanism.http.LoginToContinue;
import javax.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gcrisnuta
 */
@DatabaseIdentityStoreDefinition(
dataSourceLookup = "jdbc/lab8",
        callerQuery = "select user_password from users where username = ?",
        groupsQuery = "select user_group from users where username = ?")
@CustomFormAuthenticationMechanismDefinition(
        loginToContinue = @LoginToContinue(
                loginPage = "/login.xhtml", 
                errorPage = "", 
                useForwardToLogin=false
        )
)
@FacesConfig
@ApplicationScoped
public class ApplicationConfig {
    
}
