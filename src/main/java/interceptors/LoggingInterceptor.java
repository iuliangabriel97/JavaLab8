/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interceptors;

import entities.User;
import java.util.Optional;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.security.enterprise.SecurityContext;
import services.DataService;

/**
 *
 * @author gcrisnuta
 */
@Interceptor
@Log  //binding the interceptor here. now any method annotated with @Log would be intercepted by logMethodEntry
public class LoggingInterceptor {

    @Inject
    DataService dataService;

    @Inject
    SecurityContext securityContext;

    @AroundInvoke
    public Object logMethodEntry(InvocationContext ctx) throws Exception {

        Logger logger = Logger.getLogger(LoggingInterceptor.class.getName());
        logger.addHandler(new FileHandler("../logs/submissions.log"));

        
        logger.info("Entering method: " + ctx.getMethod().getName());
        String username = securityContext.getCallerPrincipal().getName();
        
        logger.info(username + " submitted a file");

        return ctx.proceed();
    }
}
