/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.liuxuan.device.w3330;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Moses
 */
public class W3330MetaData {

    private static final SimpleDateFormat sdfx = new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss#]");

    Date time;
    double wppm;
    double zero;
    double flux;
    double ppmzero;
    double wvtr;
    double temp1;
    double temp2;
    double temp3;
    double status;
    double filter1;
    double filter2;
    double fitting1;
    double fitting2;

    public static W3330MetaData parseString(String s) {
        if (!s.startsWith("[")) {
            return null;
        }
        //"[2014-06-09 14:01:21#] _ppm:11.170 rezero:0.000 flux:0.0 ppm:0.000 wvtr:0.0000  temp1:38.000 temp2:900.100 temp3:24.200 status:0.0  ";
        W3330MetaData data = new W3330MetaData();
        try {
            data.time = sdfx.parse(s);
            s = s.substring(s.indexOf("_ppm")).trim();//从_ppm开始分析
            String[] sl = s.split("\\s+");//从空白分割
            if (sl.length >= 9) {
                //9段才处理
                //得到数据
                data.wppm = Double.parseDouble(sl[0].substring(sl[0].indexOf(":") + 1));
                data.zero = Double.parseDouble(sl[1].substring(sl[1].indexOf(":") + 1));
                data.flux = Double.parseDouble(sl[2].substring(sl[2].indexOf(":") + 1));
                data.ppmzero = Double.parseDouble(sl[3].substring(sl[3].indexOf(":") + 1));
                data.wvtr = Double.parseDouble(sl[4].substring(sl[4].indexOf(":") + 1));
                data.temp1 = Double.parseDouble(sl[5].substring(sl[5].indexOf(":") + 1));
                data.temp2 = Double.parseDouble(sl[6].substring(sl[6].indexOf(":") + 1));
                data.temp3 = Double.parseDouble(sl[7].substring(sl[7].indexOf(":") + 1));
                data.status = Double.parseDouble(sl[8].substring(sl[8].indexOf(":") + 1));
            } else {
                return null;
            }
        } catch (ParseException ex) {
            Logger.getLogger(W3330MetaData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }
}
