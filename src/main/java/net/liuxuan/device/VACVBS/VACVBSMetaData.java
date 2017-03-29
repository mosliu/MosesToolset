/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.liuxuan.device.VACVBS;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Moses
 */
public class VACVBSMetaData {

    private static final SimpleDateFormat sdfx = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    Date date1;
    double num;
    double HP;
    double LP;
    double Humidity;
    double Temprature;
    double Time;
    double reserved1;
    double reserved2;
    double reserved3;


    
    
   
}
