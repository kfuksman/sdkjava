/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pemapiwrapper;

import java.util.HashMap;

/**
 *
 * @author kfuksman
 */
public class PemError {
    private Integer status;
    private String type;
    private String userMessage;
    private String moreInfo;
    private HashMap validationErrors;

    /**
     *
     */
    public PemError() {
        validationErrors = new HashMap();
    }

    /**
     *
     * @return
     */
    public Integer getStatus() {
        return status;
    }

    /**
     *
     * @param status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     *
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     *
     * @return
     */
    public String getUserMessage() {
        return userMessage;
    }

    /**
     *
     * @param userMessage
     */
    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    /**
     *
     * @return
     */
    public String getMoreInfo() {
        return moreInfo;
    }

    /**
     *
     * @param moreInfo
     */
    public void setMoreInfo(String moreInfo) {
        this.moreInfo = moreInfo;
    }

    /**
     *
     * @return
     */
    public HashMap getValidationErrors() {
        return validationErrors;
    }

    /**
     *
     * @param validationErrors
     */
    public void setValidationErrors(HashMap validationErrors) {
        this.validationErrors = validationErrors;
    }
}
