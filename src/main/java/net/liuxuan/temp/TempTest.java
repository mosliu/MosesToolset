/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.liuxuan.temp;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Moses
 */
public class TempTest {
    public static void main(String[] args) {
        double fee = 500;
        double percent = 0.04;
        double time = 15;
        double total = 0;
        for (int i = 0; i < time; i++) {
            total += fee*12;
            total = total*(1+percent);
            System.out.println("第"+(i+1)+"年："+total);
        }
        System.out.println("交："+time*12*fee);
        System.out.println("定期："+total);
    }
    public static void main2(String[] args) {
        File file = new File("c:/a.txt");
        List<String> sl = null;
        try {
            sl = Files.readLines(file, Charsets.UTF_8);
        } catch (IOException ex) {
            Logger.getLogger(TempTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("aa");
        if (sl != null) {
            long recv = -1;
            long sent = -1;
            long recvinterval = -1;
            long sentinterval = -1;
            long recv_sent = -1;
            long time = -1;
            long temp = -1;
            int count = 0;
            int maxcount = 0;
            for (Iterator<String> it = sl.iterator(); it.hasNext();) {
                String string = it.next();
                if (string.startsWith("收到")) {
                    String s_time = string.substring(string.indexOf('1'));
                    time = Long.parseLong(s_time);
                    if (recv == -1) {
                        recv = time;
                    } else {
                        recvinterval = time - recv;
                        recv = time;
                    }
                    count++;
                }
                if (string.startsWith("发出")) {
                    String s_time = string.substring(string.indexOf('1'));
                    time = Long.parseLong(s_time);
                    if (sent == -1) {
                        sent = time;
                    } else {
                        sentinterval = time - sent;
                        sent = time;
                    }
                    if (count == 1) {
                    } else {
                        maxcount = maxcount > count ? maxcount : count;
                    }
                    if (sent > 0 && recv > 0) {
                        temp = sent - recv;
                    }
                    recv_sent = recv_sent > temp ? recv_sent : temp;
                    count--;
                }

            }
            System.out.println("recvinterval:" + recvinterval);
            System.out.println("sentinterval:" + sentinterval);
            System.out.println("recv_sent:" + recv_sent);
            System.out.println("maxStor:" + maxcount);
        } else {
            System.out.println("List null");
        }

    }
}
