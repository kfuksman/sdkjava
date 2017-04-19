/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pemapiwrapper;

import com.mycompany.pemapiwrapper.exceptions.PemRuntimeException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.pemapiwrapper.exceptions.CheckedConsumer;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author kfuksman
 */
public abstract class Request {
    protected String token;
    protected String account;
    protected Map<String, Object> params;
    protected String namespace;
    protected String urlPerfit;
    protected String url;
    protected Long id;
    protected String action;
    protected URLBuilder urlBuilder;
    protected Integer status;
    protected String response;

    /**
     *
     * @param token
     * @param account
     * @param url
     */
    public Request(String token, String account, String url) {
        this.token = token == null ? "" : token;
        this.account = account == null ? "" : account;
        this.urlPerfit = url;
    }

    /**
     *
     * @return
     */
    public abstract Request execute();
   
    public String asString(){
        return response;
    }
    
    public JSONObject asJsonObject(){
        try {
            return parseJson(response);
        } catch (ParseException ex) {
        }
        return null;
    }
    /**
     *
     * @param params
     */
    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    /**
     *
     * @return
     */
    public Map<String, Object> getParams() {
        return params;
    }

    /**
     *
     * @param params
     */
    public void params(HashMap<String, Object> params) {
        this.params.putAll(params);
    }

    /**
     *
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
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
     * @param response
     * @return
     * @throws ParseException
     */
    protected JSONObject parseJson(String response) throws ParseException {
        if (response == null) return null;
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(response);
        JSONObject jsonObject = (JSONObject) obj;
        return jsonObject;
    }

    /**
     *
     */
    protected void constructUrl() {
        urlBuilder = new URLBuilder(urlPerfit);
        if (namespace != null) {
            urlBuilder.append(account).append(namespace);
        } else {
            urlBuilder.append(account).append(url);
        }
        
        if (id != null) {
            urlBuilder.append(id);
        }
        if (action != null) {
            urlBuilder.append(action);
        }
    }
    
    /**
     *
     * @param json
     * @return
     */
    protected PemError parseErrorFromJson(JSONObject json){
        try {
            ObjectMapper mapper = new ObjectMapper();
            PemError result = mapper.readValue(json.toJSONString(), PemError.class);
            return result;
        } catch (IOException ex) {
        }
        return new PemError();
    }
    
    /**
     *
     * @param jSONObject
     * @return
     */
    protected PemResponse parseResponseFromJson(JSONObject jSONObject){
       if (jSONObject != null){
           return new PemResponse(jSONObject);
       }
       return null;
    }
    
    /**
     *
     * @param error
     * @param success
     */
    public void execute(CheckedConsumer<PemError, PemRuntimeException> error, CheckedConsumer<PemResponse, PemRuntimeException> success){
            JSONObject jSONObject = execute().asJsonObject();
            if (haveError(jSONObject) && error != null){
                error.accept(parseErrorFromJson(jSONObject));
            }
            if(success != null){
                success.accept(parseResponseFromJson(jSONObject));
            }
    }

    /**
     *
     * @param id
     * @return
     */
    public Request id(Long id) {
        this.id = id;
        return this;
    }

    /**
     *
     * @param id
     * @return
     */
    public Request id(Integer id) {
        this.id = id.longValue();
        return this;
    }
    
    public Request action(String action){
        this.action = action;
        return this;
    }

    /**
     *
     * @param limit
     * @return
     */
    public Request limit(Integer limit) {
        this.params.put("limit", limit);
        return this;
    }

    /**
     *
     * @param offset
     * @return
     */
    public Request offset(Integer offset) {
        this.params.put("offset", offset);
        return this;
    }

    /**
     *
     * @param sortBy
     * @param sortDir
     * @return
     */
    public Request offset(String sortBy, String sortDir) {
        this.params.put("sortBy", sortBy);
        if (sortDir != null) {
            this.params.put("sortDir", sortDir);
        }
        return this;
    }

    /**
     *
     * @return
     */
    public Request accounts() {
        this.namespace = "accounts";
        return this;
    }

    /**
     *
     * @return
     */
    public Request contacts() {
        this.namespace = "contacts";
        return this;
    }

    /**
     *
     * @return
     */
    public Request lists() {
        this.namespace = "lists";
        return this;
    }

    /**
     *
     * @return
     */
    public Request fields() {
        this.namespace = "fields";
        return this;
    }

    /**
     *
     * @return
     */
    public Request interests() {
        this.namespace = "interests";
        return this;
    }

    /**
     *
     * @return
     */
    public Request optins() {
        this.namespace = "optins";
        return this;
    }

    /**
     *
     * @return
     */
    public Request tasks() {
        this.namespace = "tasks";
        return this;
    }

    /**
     *
     * @return
     */
    public Request users() {
        this.namespace = "users";
        return this;
    }

    /**
     *
     * @return
     */
    public Request alerts() {
        this.namespace = "alerts";
        return this;
    }

    /**
     *
     * @return
     */
    public Request images() {
        this.namespace = "images";
        return this;
    }

    /**
     *
     * @return
     */
    public Request templates() {
        this.namespace = "templates";
        return this;
    }

    /**
     *
     * @return
     */
    public Request campaigns() {
        this.namespace = "campaigns";
        return this;
    }

    /**
     *
     * @return
     */
    public Request plan() {
        this.namespace = "plan";
        return this;
    }

    /**
     *
     * @return
     */
    public Request invoices() {
        this.namespace = "invoices";
        return this;
    }

    /**
     *
     * @return
     */
    public Request logo() {
        this.namespace = "logo";
        return this;
    }

    /**
     *
     * @return
     */
    public Request dashboard() {
        this.namespace = "dashboard";
        return this;
    }

    /**
     *
     * @return
     */
    public Request froms() {
        this.namespace = "froms";
        return this;
    }

    private boolean haveError(JSONObject json) {
       return json.containsKey("error") || status >= 300;
    }
}
