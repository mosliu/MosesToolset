/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.liuxuan.temp;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.apache.commons.math3.fitting.CurveFitter;
import org.apache.commons.math3.fitting.PolynomialCurveFitter;
import org.apache.commons.math3.fitting.WeightedObservedPoint;
import org.apache.commons.math3.fitting.WeightedObservedPoints;
import org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer;

/**
 *
 * @author Moses
 */
public class mathtest {

    public static void main(String[] args) {
        
        final CurveFitter fitter = new CurveFitter(new LevenbergMarquardtOptimizer());
        fitter.addObservedPoint(-1.00, 2.021170021833143);
        fitter.addObservedPoint(-0.99, 2.221135431136975);
        fitter.addObservedPoint(-0.98, 2.09985277659314);
        fitter.addObservedPoint(-0.97, 2.0211192647627025);
// ... Lots of lines omitted ...
        fitter.addObservedPoint(0.99, -2.4345814727089854);

// The degree of the polynomial is deduced from the length of the array containing
// the initial guess for the coefficients of the polynomial.
        final double[] init = {12.9, -3.4, 2.1}; // 12.9 - 3.4 x + 2.1 x^2

// Compute optimal coefficients.
        final double[] best = fitter.fit(new PolynomialFunction.Parametric(), init);

// Construct the polynomial that best fits the data.
        final PolynomialFunction fitted = new PolynomialFunction(best);
        System.out.println(fitted.value(-0.995));;
        System.out.println(fitted.value(0.995));;
        System.out.println(fitted.toString());
        ;
        System.out.println("=============================================================");
        PolynomialCurveFitter pcf = PolynomialCurveFitter.create(3);
        WeightedObservedPoints s;
        List<WeightedObservedPoint> points = new ArrayList<WeightedObservedPoint>();
        points.add(new WeightedObservedPoint(1,-1.00,2.021170021833143));
        points.add(new WeightedObservedPoint(1,-0.99,2.221135431136975));
        points.add(new WeightedObservedPoint(1,-0.98,2.09985277659314));
        points.add(new WeightedObservedPoint(1,-0.97,2.0211192647627025));
        points.add(new WeightedObservedPoint(1,0.99,2.4345814727089854));
        double a[] = pcf.fit(points);
        for (int i = 0; i < a.length; i++) {
            double d = a[i];
            System.out.println(d);
        }
        System.out.println(compute(a,-0.995));
        System.out.println(compute(a,0.99));
        System.out.println(compute(a,0.995));
        
        
    }
    public static double compute(double[] s,double in){
        double y=0;
        for (int i = 0; i < s.length; i++) {
            y+= s[i]*Math.pow(in, i);
        }
        return y;
    }
}
