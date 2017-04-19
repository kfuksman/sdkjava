/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pemapiwrapper;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

/**
 *
 * @author kfuksman
 */
public class GetRequest extends Request {

    /**
     *
     * @param token
     * @param account
     * @param url
     */
    public GetRequest(String token, String account, String url) {
        super(token, account, url);
    }

    /**
     *
     * @return
     */
    @Override
    public GetRequest execute() {
        try {
            constructUrl();
            HttpResponse<String> responseApi = Unirest
                    .get(urlBuilder.toString()).header("x-auth-token", token)
                    .asString();
            status = responseApi.getStatus();
            response = responseApi.getBody();
            return this;
        } catch (UnirestException ex) {
        }
        return this;
    }
   
}
