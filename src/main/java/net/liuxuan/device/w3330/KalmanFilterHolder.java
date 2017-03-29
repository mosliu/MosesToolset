/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.liuxuan.device.w3330;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.filter.DefaultMeasurementModel;
import org.apache.commons.math3.filter.DefaultProcessModel;
import org.apache.commons.math3.filter.KalmanFilter;
import org.apache.commons.math3.filter.MeasurementModel;
import org.apache.commons.math3.filter.ProcessModel;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.linear.SingularMatrixException;

/**
 *
 * @author Moses
 */
public class KalmanFilterHolder {

    private double constant = 0;
    private double measurementNoise = 0;
    private double processNoise = 0;
    public RealMatrix A;
    public RealMatrix B;
    public RealMatrix H;
    public RealVector x;
    public RealMatrix Q;
    public RealMatrix P0;
    public RealMatrix R;

    private ProcessModel pm;
    private MeasurementModel mm;
    public KalmanFilter filter;

    // process and measurement noise vectors
    public RealVector pNoise = new ArrayRealVector(1);
    public RealVector mNoise = new ArrayRealVector(1);

    // z = H * x + m_noise
//    public RealVector z;

    public KalmanFilterHolder(double _constant, double _measurementNoise, double _processNoise) {
        constant = _constant;
        measurementNoise = _measurementNoise;
        processNoise = _processNoise;
        // A = [ 1 ]
        A = new Array2DRowRealMatrix(new double[]{1d});
// B = null
        B = null;
// H = [ 1 ]
        H = new Array2DRowRealMatrix(new double[]{1d});
// x = [ 10 ]
        x = new ArrayRealVector(new double[]{constant});
// Q = [ 1e-5 ]
        Q = new Array2DRowRealMatrix(new double[]{processNoise});
// P = [ 1 ]
        P0 = new Array2DRowRealMatrix(new double[]{1d});
// R = [ 0.1 ]
        R = new Array2DRowRealMatrix(new double[]{measurementNoise});
//        初始化滤波器
        initfilter();
    }

    public KalmanFilterHolder(double _constant) {
        this(_constant, 0.1d, 1e-5d);
    }

    private void initfilter() {
        pm = new DefaultProcessModel(A, B, Q, x, P0);
        mm = new DefaultMeasurementModel(H, R);
        filter = new KalmanFilter(pm, mm);
//        z = H.operate(x).add(mNoise);
    }

    public double getEstimation(double input){
        //卡尔曼滤波，对温度
            predict();
            // simulate the process
            // pNoise.setEntry(0, processNoise * rand.nextGaussian());
            pNoise.setEntry(0, 0);
            //开尔曼滤波，对ppm
            // x = A * x + p_noise
            x.setEntry(0, input);
            x = A.operate(x ).add(pNoise);
            // simulate the measurement
            //            mNoise.setEntry(0, measurementNoise * rand.nextGaussian());
            mNoise.setEntry(0, 0);
//            correctZ();
            RealVector z = H.operate(x).add(mNoise);
            filter.correct(z);
            
            double rtn  = getStateEstimation()[0];
            return rtn;
    }
    
    public void correctZ() {
        // z = H * x + m_noise
        RealVector z = H.operate(x).add(mNoise);
        filter.correct(z);
    }

    //<editor-fold defaultstate="collapsed" desc="getter and setter">  
    public double getConstant() {
        return constant;
    }

    public void setConstant(double constant) {
        this.constant = constant;
    }

    public double getMeasurementNoise() {
        return measurementNoise;
    }

    public void setMeasurementNoise(double measurementNoise) {
        this.measurementNoise = measurementNoise;
    }

    public double getProcessNoise() {
        return processNoise;
    }

    public void setProcessNoise(double processNoise) {
        this.processNoise = processNoise;
    }

    public RealMatrix getA() {
        return A;
    }

    public void setA(RealMatrix A) {
        this.A = A;
        initfilter();
    }

    public RealMatrix getB() {
        return B;
    }

    public void setB(RealMatrix B) {
        this.B = B;
        initfilter();
    }

    public RealMatrix getH() {
        return H;
    }

    public void setH(RealMatrix H) {
        this.H = H;
        initfilter();
    }

    public RealVector getX() {
        return x;
    }

    public void setX(RealVector x) {
        this.x = x;
        initfilter();
    }

    public RealMatrix getQ() {
        return Q;
    }

    public void setQ(RealMatrix Q) {
        this.Q = Q;
        initfilter();
    }

    public RealMatrix getP0() {
        return P0;
    }

    public void setP0(RealMatrix P0) {
        this.P0 = P0;
        initfilter();
    }

    public RealMatrix getR() {
        return R;
    }

    public void setR(RealMatrix R) {
        this.R = R;
        initfilter();
    }

    public ProcessModel getPm() {
        return pm;
    }

    public void setPm(ProcessModel pm) {
        this.pm = pm;
        initfilter();
    }

    public MeasurementModel getMm() {
        return mm;
    }

    public void setMm(MeasurementModel mm) {
        this.mm = mm;
        initfilter();
    }

    public KalmanFilter getFilter() {
        return filter;
    }

    public void setFilter(KalmanFilter filter) {
        this.filter = filter;
        initfilter();
    }

    public RealVector getpNoise() {
        return pNoise;
    }

    public void setpNoise(RealVector pNoise) {
        this.pNoise = pNoise;
    }

    public RealVector getmNoise() {
        return mNoise;
    }

    public void setmNoise(RealVector mNoise) {
        this.mNoise = mNoise;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="委托方法">  
    public void predict() {
        filter.predict();
    }

    public void predict(double[] u) throws DimensionMismatchException {
        filter.predict(u);
    }

    public void predict(RealVector u) throws DimensionMismatchException {
        filter.predict(u);
    }

    public void correct(double[] z) throws NullArgumentException, DimensionMismatchException, SingularMatrixException {
        filter.correct(z);
    }

    public void correct(RealVector z) throws NullArgumentException, DimensionMismatchException, SingularMatrixException {
        filter.correct(z);
    }

    public double[] getStateEstimation() {
        return filter.getStateEstimation();
    }

    // </editor-fold>
}
