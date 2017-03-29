/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.liuxuan.temp;

/**
 *
 * @author Moses
 */
public class AlgorithmsTest {

    public static void main(String[] args) {
        AlgorithmsTest test = new AlgorithmsTest();
        test.test();
    }
    int len = 10;
    int top[] = new int[len];
    int bottom[] = new int[len];
    boolean success;

    public  AlgorithmsTest() {
        success = false;
        //format top   
        for (int i = 0; i < len; i++) {
            top[i] = i;
        }
        for (int i = 0; i < len; i++) {
            bottom[i] = i;
        }
    }

    private int[] getBottom() {
        int i = 0;
        while (!success) {
            i++;
            setNextBottom();
            printbottom();
        }
        return bottom;
    }

//set next bottom   
    private void setNextBottom() {
        boolean reB = true;

        for (int i = 0; i < len; i++) {
            int frequecy = getFrequecy(i);

            if (bottom[i] != frequecy) {
                bottom[i] = frequecy;
                reB = false;
            }
        }
        success = reB;
    }

    //get frequency in bottom   
    private int getFrequecy(int num) //此处的num即指上排的数 i
    {
        int count = 0;

        for (int i = 0; i < len; i++) {
            if (bottom[i] == num) {
                count++;
            }
        }
        return count;    //cout即对应 frequecy
    }

    private void printbottom(){
        for (int i = 0; i < len; i++) {
            System.out.print(top[i]);
        }
        System.out.println("");
        for (int i = 0; i < len; i++) {
            System.out.print(bottom[i]);
        }
        System.out.println("");
        System.out.println("========================");
    }
    
    private void test() {
        int[] result = getBottom();

        for (int i = 0; i < len; i++) {
            System.out.println(result[i]);
        }
    }
}
