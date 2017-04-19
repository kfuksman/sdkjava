/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pemapiwrapper;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;
import org.json.simple.JSONObject;

/**
 *
 * @author kfuksman
 */
public class PemResponse {
    private final JSONObject jsonObject;

    /**
     *
     * @param jsonObject
     */
    public PemResponse(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    /**
     *
     * @param map
     * @param out
     * @throws IOException
     */
    public static void writeJSONString(Map map, Writer out) throws IOException {
        JSONObject.writeJSONString(map, out);
    }

    /**
     *
     * @param out
     * @throws IOException
     */
    public void writeJSONString(Writer out) throws IOException {
        jsonObject.writeJSONString(out);
    }

    /**
     *
     * @param map
     * @return
     */
    public static String toJSONString(Map map) {
        return JSONObject.toJSONString(map);
    }

    /**
     *
     * @return
     */
    public String toJSONString() {
        return jsonObject.toJSONString();
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return jsonObject.toString();
    }

    /**
     *
     * @param key
     * @param value
     * @return
     */
    public static String toString(String key, Object value) {
        return JSONObject.toString(key, value);
    }

    /**
     *
     * @param s
     * @return
     */
    public static String escape(String s) {
        return JSONObject.escape(s);
    }
    
    
   
}
