/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.liuxuan.utils.algorithm.fourier;

/**
 *  From: http://hxfcalf.blog.163.com/blog/static/2157554820137248215424/
 * @author Moses
 */
public class RFFT extends FFT {

    public static Complex[] rfft(double[] r) {
        int N = r.length;
        Complex[] x = toComplex(r);
        Complex[] y = fft(x);
        return y;
    }
    
    public static Complex[] toComplex(double[] r){
        int N = r.length;
        Complex[] x = new Complex[N];
        for (int i = 0; i < N; i++) {
            x[i] = new Complex(r[i], 0);
        }
        return x;
    }
    public static double[] toFloat_bak(Complex[] c){
        int N = c.length*2;
        double[] x = new double[N];
        int k=0;
        for (int i = 0; i < c.length; i++) {
            x[k++] = (double)c[i].re();
            x[k++] = (double)c[i].im();
        }
        return x;
    }
    public static double[] toFloat(Complex[] c){
        int N = c.length;
        double[] x = new double[N];
        int k=0;
        for (int i = 0; i < c.length; i++) {
            x[i] = (double)c[i].re();
        }
        return x;
    }
    public static double[] toFloat3(Complex[] c){
        int N = c.length;
        double[] x = new double[N];
        int k=0;
        for (int i = 0; i < c.length; i++) {
            x[i] = (double)c[i].abs();
        }
        return x;
    }
    
    public static double[] conv(double[] x, double[] y) {
        int Ly = x.length + y.length - 1;
        int Ly2 = pow2(nextpow2(Ly));
        x = patch(x, Ly2);
        y = patch(y, Ly2);
        Complex[] dat = toComplex(x);
        Complex[] h = toComplex(y);
        Complex[] c = convolve(dat,h);
        double[] rc = toFloat(c);
        rc = cut(rc, Ly);
        return rc;
    }

    public static double[] conv_bak(double[] x, double[] y) {
        int Ly = x.length + y.length-1;
        int Ly2 = pow2(nextpow2(Ly));
        x = patch(x, Ly2);
        y = patch(y, Ly2);
        Complex[] X = rfft(x);
        Complex[] H = rfft(y);
        Complex[] Y = dotMult(X, H);
        Complex[] yy = ifft(Y);
        double[] z = real(yy);
        //z = wkeep(z, Ly);
        //z = cut(z, Ly);
        return z;
    }
    //返回序列中间部分--见Matlab wkeep

    public static double[] wkeep(double[] d, int len) {
        if (len >= d.length) {
            return d;
        }
        int dif = d.length - len,
                start = dif / 2;
        if ((start == 0) && (dif > 0)) {
            start = 1;
        }
        double[] keep = new double[len];
        for (int i = 0; i < len; i++) {
            keep[i] = d[i + start];
        }
        return keep;
    }

    public static int pow2(int p) {
        return (int) Math.pow(2, p);
    }

    public static int nextpow2(int n) {
        double log2 = (Math.log(n) / Math.log(2));
        if ((int) log2 < log2) {
            log2 += 1;
        }
        return (int) log2;
    }

    public static double[] real(Complex[] x) {
        int N = x.length;
        double[] r = new double[N];
        for (int i = 0; i < r.length; i++) {
            r[i] = (double) x[i].re();
        }
        return r;
    }

    public static Complex[] dotMult(Complex[] x, Complex[] h) {
        Complex[] xh = new Complex[x.length];
        for (int i = 0; i < xh.length; i++) {
            xh[i] = x[i].times(h[i]);
        }
        return xh;
    }

    public static double[] patch(double[] x, int len) {
        double[] xx = new double[len];
        for (int i = 0; i < x.length; i++) {
            xx[i] = x[i];
        }
        return xx;
    }

    public static double[] cut(double[] x, int len) {
        if (x.length > len) {
            double[] xx = new double[len];
            for (int i = 0; i < len; i++) {
                xx[i] = x[i];
            }
            return xx;
        } else {
            return x;
        }
    }

    // display an array of Complex numbers to standard output
    public static void show(double[] x, String title) {
        System.out.println(title);
        System.out.println("-------------------");
        for (int i = 0; i < x.length; i++) {
            System.out.println(x[i]);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int N = 16;
//        double[] x = new double[N];
//        double[] x = {3,4,3,3,4,3,3,3,4,3,3,3,3,4,3,3};
        double[] x = {0.3,-0.42,-0.3,-0.41,0.3,0.87,0.1,-0.23,-0.3,0.45,0.3,0.46,0.3,0.53,0.3,0.43};
//        for (int i = 0; i < N; i++) {
//            x[i] = (double) (-2 * Math.random() + 1.0);
//        }
        show(x, "x");
        // FFT of original data
        Complex[] y = rfft(x);
        show(y, "y = fft(x)");
        show(toFloat3(y), "y2 ======================= irfft(y)");
        // take inverse FFT
        Complex[] z = ifft(y);
        show(z, "z = irfft(y)");
        

        double[] a = {4, 5, 6, 7};
        double[] b = {7, 6, 5, 4};
        double[] c = conv(a, b);
        show(c, "c = conv(a,b)");

    }
}