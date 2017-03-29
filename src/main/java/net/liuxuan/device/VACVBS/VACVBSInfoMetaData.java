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
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Moses
 */
public class VACVBSInfoMetaData {

    String CompanyName;
    String TestName;
    String TestBatch;
    String SampleName;
    String SampleNo;
    String SampleFrom;
    String SampleFactory;
    String TestArea;
    String Thickness;
    String SamplePrepare;
    String SampleAmount;
    String Device;
    String Standard;
    String TestTemp;
    String TestHumi;
    String TestDate;
    String GTR2000;
    String GTRP2000;
    String GTR;
    String GTRP;
    String RD;
    String RS;
    String dataamount;
    String TestTime;
    String VacTime;
    String Ymax;
    String Xmax;
    ArrayList<VACVBSMetaData> datas;

    public static void main(String[] args) throws IOException {
//        VACVBSMetaData meta = new VACVBSMetaData();
//        meta.HP = 100;
//        meta.Humidity = 20;
//        meta.LP = 200;
//        meta.Temprature = 100;
//        meta.Time = 3;
////        meta.date1="";
//        meta.date1 = new Date();
//        meta.num = 0;
//        meta.reserved1 = 0;
//
        Gson gson = new Gson();
//        String a = gson.toJson(meta);
//        System.out.println(a);
//        String b = "{ \"num\":\"0\", \"HP\":\"0\", \"LP\":\"10\", \"Humidity\":\"39\", \"Temprature\":\"23.5\", \"date1\":\"2014-8-16 16:27:54  \", \"Time\":\"6\"}";
//        VACVBSMetaData d =gson.fromJson(b, VACVBSMetaData.class);
//        System.out.println(d.LP);
//        System.out.println(d.date1);

        File filea = new File("F:\\MosesData\\Desktop\\调试数据\\VAC-VBS data\\VBS\\新建文件夹\\bbb.VBSGTR.txt");
        String fileContent = Files.toString(filea, Charsets.ISO_8859_1);
//        System.out.println(fileContent);
        VACVBSInfoMetaData imd = gson.fromJson(fileContent, VACVBSInfoMetaData.class);
        System.out.println(imd.Device);
        System.out.println(imd.datas.size());
        System.out.println(imd.datas.get(0).date1);
//        JsonParser parser = new JsonParser();
//        JsonElement je = parser.parse(fileContent);
//
//        JsonArray array = je.getAsJsonArray();
//        System.out.println(array.size());

    }
}
