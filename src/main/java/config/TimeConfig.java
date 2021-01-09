/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import java.util.Calendar;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Razvan
 */
@ApplicationScoped
@Named
public class TimeConfig {
    private Date uploadIntervalStart;
    private Date uploadIntervalEnd;
    private Date registerIntervalStart;
    private Date registerIntervalEnd;

    @PostConstruct
    public void initialize() {
        Calendar tmp = Calendar.getInstance();
        tmp.clear();
        tmp.set(Calendar.HOUR_OF_DAY, 10);
        tmp.set(Calendar.MINUTE, 0);
        this.uploadIntervalStart = tmp.getTime();
        this.registerIntervalStart = tmp.getTime();
        
        tmp.set(Calendar.HOUR_OF_DAY, 18);
        this.uploadIntervalEnd = tmp.getTime();
        this.registerIntervalEnd = tmp.getTime();
    }
    
    public void updateTimes(){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Updated intervals!", null));
    }
    
    /**
     * @return the uploadIntervalStart
     */
    public Date getUploadIntervalStart() {
        return uploadIntervalStart;
    }

    /**
     * @param uploadIntervalStart the uploadIntervalStart to set
     */
    public void setUploadIntervalStart(Date uploadIntervalStart) {
        this.uploadIntervalStart = uploadIntervalStart;
    }

    /**
     * @return the uploadIntervalEnd
     */
    public Date getUploadIntervalEnd() {
        return uploadIntervalEnd;
    }

    /**
     * @param uploadIntervalEnd the uploadIntervalEnd to set
     */
    public void setUploadIntervalEnd(Date uploadIntervalEnd) {
        this.uploadIntervalEnd = uploadIntervalEnd;
    }

    /**
     * @return the registerIntervalStart
     */
    public Date getRegisterIntervalStart() {
        return registerIntervalStart;
    }

    /**
     * @param registerIntervalStart the registerIntervalStart to set
     */
    public void setRegisterIntervalStart(Date registerIntervalStart) {
        this.registerIntervalStart = registerIntervalStart;
    }

    /**
     * @return the registerIntervalEnd
     */
    public Date getRegisterIntervalEnd() {
        return registerIntervalEnd;
    }

    /**
     * @param registerIntervalEnd the registerIntervalEnd to set
     */
    public void setRegisterIntervalEnd(Date registerIntervalEnd) {
        this.registerIntervalEnd = registerIntervalEnd;
    }
}
