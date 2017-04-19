/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pemapiwrapper;

/**
 *
 * @author kfuksman
 */
public class URLBuilder {
    StringBuilder sb;

    /**
     *
     */
    public URLBuilder() {
        sb = new StringBuilder();
    }

    /**
     *
     * @param string
     */
    public URLBuilder(String string) {
        this.sb = new StringBuilder(string);
    }
    
    /**
     *
     * @param string
     * @return
     */
    public URLBuilder append(String string){
        if (string == null) return this;
        if (!sb.toString().endsWith("/") && ! string.startsWith("/")){
            sb.append("/");
        } 
        if (sb.toString().endsWith("/") && string.startsWith("/")){
            string = string.substring(1);
        }
        sb.append(string);
        return this;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        String result = sb.toString();
        return result;
    }

    /**
     *
     * @param id
     * @return
     */
    public URLBuilder append(Long id) {
        if (id == null) return this;
        return append(id.toString());
    }
    
    
    
    
    
    
}
