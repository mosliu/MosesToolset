/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.liuxuan.Tools.EsafeNet;

/**
 *
 * @author Moses
 */
public class testtest {

    public static void main(String[] args) {
        
        int total = 60;//总投注
        double pl1 = 1.5;//赔率1
        double pl2 = 30;//赔率2
            helan(total, pl1, pl2);
//        helan(t,total,pl1,pl2);

    }

    private static void helan(int total, double pl1, double pl2) {
        double max=0;
        int maxint =-1;
        for (int t = 0; t < total+1; t++) {
            //一胜
            double rst1 = t * pl1 - total;
            //二胜
            double rst2 = (total - t) * pl2 - total;
            //最少单词赢取
            double min = rst1<rst2?rst1:rst2;
            if(maxint==-1){
                max =min;
                maxint = 0;
            }else if(max<min){
                max = min;
                maxint = t;
            }
        }
        System.out.println("maxint:"+maxint+",MAX:"+max);

    }
}
