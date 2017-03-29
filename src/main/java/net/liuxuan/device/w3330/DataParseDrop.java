/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.liuxuan.device.w3330;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;
import net.liuxuan.UI.component.ExtensionFileFilter;

/**
 * 文件处理
 * @author Moses
 */
public class DataParseDrop {

    public static void main(String[] args) {
//        String s = "[2014-06-09 14:01:21#] _ppm:11.170 rezero:0.000 flux:0.0 ppm:0.000 wvtr:0.0000  temp1:38.000 temp2:900.100 temp3:24.200 status:0.0  ";
//        parsestr(s);
        FileWriter fw = null;
        try {
            fw = new FileWriter(new File("d:/aaaa.txt"));
        } catch (IOException ex) {
            Logger.getLogger(DataParseDrop.class.getName()).log(Level.SEVERE, null, ex);
        }
        SimpleDateFormat sdfx = new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss#]");

        ExtensionFileFilter filter = new ExtensionFileFilter("log,txt", true, true);
        filter.setDescription("信号记录文件");

        JFileChooser jfc = new JFileChooser();
        FileSystemView fsv = FileSystemView.getFileSystemView();
        //得到桌面路径
        jfc.setCurrentDirectory(fsv.getHomeDirectory());

        jfc.setDialogTitle("选择文件位置");
        jfc.setMultiSelectionEnabled(false);
        jfc.setDialogType(JFileChooser.OPEN_DIALOG);
        jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);//JFileChooser.FILES_AND_DIRECTORIES
        jfc.setFileFilter(filter);
        int result = jfc.showOpenDialog(null);  // 打开"打开文件"对话框
//        String content;
        List<String> fileContentStringList = null;
        if (result == JFileChooser.APPROVE_OPTION) {
//            String filepath = jfc.getSelectedFile().getAbsolutePath();
            if (jfc.getSelectedFile() == null) {
                //未选中文件
                System.out.println("未选中文件!!");
                return;
            }
            try {
                fileContentStringList = Files.readLines(jfc.getSelectedFile(), Charsets.ISO_8859_1);
            } catch (IOException ex) {
                Logger.getLogger(JIF_DrawChart_w3330.class.getName()).log(Level.SEVERE, null, ex);
            }

//            content = FilePlus.ReadTextFileToString(new File(filepath), "\r\n", "utf-8");
        } else {
//            content = null;
            System.out.println("无内容!!");
            return;
        }
//        String[] sa = content.split("\r\n");
        String[] sa = (String[]) fileContentStringList.toArray(new String[fileContentStringList.size()]);
        //找到第一个正确的行
        int startno = 0;
        while (!sa[startno].startsWith("[")) {
            startno++;
        }
        //开始整理数据
        try {

            Date starttime = sdfx.parse(sa[startno]);
            double current[] = new double[9];
            double max[] = new double[9];
            int count = 0;
            for (int indexi = startno; indexi < sa.length; indexi++) {
//            for (int i = startno; i < startno + 1; i++) {

                String s = sa[indexi];
                if (s.startsWith("[")) {
                    Date time = sdfx.parse(s);
                    //"[2014-06-09 14:01:21#] _ppm:11.170 rezero:0.000 flux:0.0 ppm:0.000 wvtr:0.0000  temp1:38.000 temp2:900.100 temp3:24.200 status:0.0  ";
//                    double wppm = 0;
//                    double zero = 0;
//                    double flux = 0;
//                    double ppmzero = 0;
//                    double wvtr = 0;
//                    double temp1 = 0;
//                    double temp2 = 0;
//                    double temp3 = 0;
//                    double status = 0;
                    s = s.substring(s.indexOf("_ppm")).trim();//从_ppm开始分析
//                    System.out.println(s);
                    String[] sl = s.split("\\s+");//从空白分割
//                    System.out.println(sl.length);
                    if (sl.length == 9) {
                        count++;
                        //9段才处理
                        //得到数据
                        for (int j = 0; j < sl.length; j++) {
//                            String string = sl[j];
                            current[j] = Double.parseDouble(sl[j].substring(sl[j].indexOf(":") + 1));
                            max[j] = current[j] > max[j] ? current[j] : max[j];
                        }
//                        wppm = Double.parseDouble(sl[0].substring(sl[0].indexOf(":") + 1));
//                        zero = Double.parseDouble(sl[1].substring(sl[1].indexOf(":") + 1));
//                        flux = Double.parseDouble(sl[2].substring(sl[2].indexOf(":") + 1));
//                        ppmzero = Double.parseDouble(sl[3].substring(sl[3].indexOf(":") + 1));
//                        wvtr = Double.parseDouble(sl[4].substring(sl[4].indexOf(":") + 1));
//                        temp1 = Double.parseDouble(sl[5].substring(sl[5].indexOf(":") + 1));
//                        temp2 = Double.parseDouble(sl[6].substring(sl[6].indexOf(":") + 1));
//                        temp3 = Double.parseDouble(sl[7].substring(sl[7].indexOf(":") + 1));
//                        status = Double.parseDouble(sl[8].substring(sl[8].indexOf(":") + 1));

                        if ((count - 1) % 30 == 0 || indexi > sa.length - 3) {
                            StringBuilder sb1 = new StringBuilder();
                            sb1.append(sa[indexi].substring(0, sa[indexi].indexOf("] ") + 2));
                            for (int j = 0; j < 8; j++) {
                                sb1.append(sl[j].substring(0, sl[j].indexOf(":") + 1));
                                sb1.append(String.format("%.3f ", max[j]));
                            }
                            sb1.append(sl[8].substring(0, sl[8].indexOf(":") + 1));
                            sb1.append(String.format("%.1f ", max[8]));
                            sb1.append("\r\n");
//                            System.out.println(sb1);

                            fw.append(sb1);
                            for (int j = 0; j < max.length; j++) {
                                max[j] = -100;
                            }
                        }
                    }
                }
            }
        } catch (ParseException ex) {
//                System.out.println(s);
            ex.printStackTrace();
        } catch (IOException ex) {
            Logger.getLogger(DataParseDrop.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            fw.flush();
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(DataParseDrop.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void parsestr(String s) {
        s = s.substring(s.indexOf("_ppm"));//从_ppm开始分析
        System.out.println(s);
        String[] sl = s.split("\\s+");//从空白分割
        System.out.println(sl.length);
    }

}
