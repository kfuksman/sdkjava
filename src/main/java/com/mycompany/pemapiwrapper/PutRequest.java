/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pemapiwrapper;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.body.MultipartBody;
import com.mashape.unirest.request.body.RequestBodyEntity;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kfuksman
 */
public class PutRequest extends Request {
    private String body;

    /**
     *
     * @param token
     * @param account
     * @param url
     */
    public PutRequest(String token, String account, String url) {
        super(token, account, url);
    }
    
    /**
     *
     * @param body
     * @return
     */
    public PutRequest body(String body){
        this.body = body;
        return this;
    }

    /**
     *
     * @return
     */
    @Override
    public PutRequest execute() {
        HttpResponse<String> responseApi  = null;
        try {
            constructUrl();
            if (body != null){
                RequestBodyEntity request = Unirest.put(urlBuilder.toString())
                        .header("x-auth-token", token).body(body);
                responseApi = request.asString();
            } else {
                MultipartBody request = Unirest.put(urlBuilder.toString())
                        .header("x-auth-token", token).fields(params);
                responseApi = request.asString();
            }
            status = responseApi.getStatus();
            response = responseApi.getBody();
            return this;
        } catch (UnirestException ex) {
            Logger.getLogger(PostRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this;
    }
}
