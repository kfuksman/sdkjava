/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pemapiwrapper;

import com.mycompany.pemapiwrapper.exceptions.*;
import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONObject;

/**
 *
 * @author kfuksman
 */
public class PerfitApi {
    
    private String token;
    private String account;
    private HashMap<String, Object> params;
    private final String urlPerfit = "https://api.myperfit.com/v2";

    /**
     *
     */
    public PerfitApi() {
        params = new HashMap<>();
    }
    
   
    
    private Boolean login() throws AccountRequired, UnauthorizedLogin{
        JSONObject obj = post("/login", params).execute().asJsonObject();
        if ((Boolean)obj.get("success")){
            JSONObject data = (JSONObject) obj.get("data");
            token = (String) data.get("token");
            try {
                account = (String) data.get("account");
            } catch (Exception e) {
                throw new AccountRequired();
            }
            
            return true;
        }
        throw new UnauthorizedLogin();
    }
    
    /**
     *
     * @param username
     * @param password
     * @return
     * @throws UnauthorizedLogin
     * @throws AccountRequired
     */
    public Boolean login (String username, String password) throws UnauthorizedLogin, AccountRequired{
        params.put("user", username);
        params.put("password", password);
        return login();
    }
    
    /**
     *
     * @param username
     * @param password
     * @param account
     * @return
     * @throws UnauthorizedLogin
     * @throws AccountRequired
     */
    public Boolean login(String username, String password, String account) throws UnauthorizedLogin, AccountRequired{
        params.put("account", account);
        return login(username, password); 
    }
    
    /**
     *
     * @param apikey
     * @return
     * @throws AccountRequired
     * @throws UnauthorizedLogin
     */
    public Boolean login(String apikey) throws AccountRequired, UnauthorizedLogin{
        params.put("apikey", apikey);
        return login();
    }

    /**
     *
     * @param url
     * @param params
     * @return
     */
    public PostRequest post(String url, HashMap<String, Object> params) {
        PostRequest postRequest = post(url);
        postRequest.setParams(params);
        return postRequest;
    }
    
    /**
     *
     * @param url
     * @return
     */
    public PostRequest post(String url) {
        PostRequest postRequest = post();
        postRequest.setUrl(url);
        return postRequest;
    }
    
    /**
     *
     * @return
     */
    public PostRequest post(){
        return new PostRequest(token, account, urlPerfit);
    }
    
    /**
     *
     * @param url
     * @param params
     * @return
     */
    public GetRequest get(String url, Map<String, Object> params) {
        GetRequest getRequest = get(url);
        getRequest.setParams(params);
        return getRequest;
    }
    
    /**
     *
     * @param url
     * @return
     */
    public GetRequest get(String url) {
        GetRequest getRequest = get();
        getRequest.setUrl(url);
        return getRequest;
    }
    
    /**
     *
     * @return
     */
    public GetRequest get(){
        return new GetRequest(token, account, urlPerfit);
    }

    /**
     *
     * @return
     */
    public String getAccount() {
        return account;
    }

    /**
     *
     * @param account
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     *
     * @return
     */
    public String getToken() {
        return token;
    }

    /**
     *
     * @param token
     */
    public void setToken(String token) {
        this.token = token;
    }
}
