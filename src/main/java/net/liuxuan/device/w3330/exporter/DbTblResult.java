/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.liuxuan.device.w3330.exporter;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Moses
 */
public class DbTblResult {

    int RESULTID;
    int TESTID;
    int CELLID;
    double TIMESPAN;
    double TESTTEMPERATURE;
    double ABOVEHUMIDITY;
    double BELOWHUMIDITY;
    double ABOVEPRESSURE;
    double BELOWPRESSURE;
    double FLOWRATE;
    double OPPM;
    double WPPM;
    double OTR;
    double WVTR;
    double SENSORTEMP;
    double AMBTEMP;
    double TESTTYPE;
    double AMTEMPERATURE;
    double AMHUMIDITY;
    Date PRODUCETIME;
    double COFFICIENT;

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd HH:mm:ss");
    DecimalFormat df3xs = new DecimalFormat("0.000");
    DecimalFormat df4xs = new DecimalFormat("0.0000");
    DecimalFormat df4sci = new DecimalFormat("0.###E0");

    public int getRESULTID() {
        return RESULTID;
    }

    public void setRESULTID(int RESULTID) {
        this.RESULTID = RESULTID;
    }

    public int getTESTID() {
        return TESTID;
    }

    public void setTESTID(int TESTID) {
        this.TESTID = TESTID;
    }

    public int getCELLID() {
        return CELLID;
    }

    public void setCELLID(int CELLID) {
        this.CELLID = CELLID;
    }

    public double getTIMESPAN() {
        return TIMESPAN;
    }

    public void setTIMESPAN(double TIMESPAN) {
        this.TIMESPAN = TIMESPAN;
    }

    public double getTESTTEMPERATURE() {
        return TESTTEMPERATURE;
    }

    public void setTESTTEMPERATURE(double TESTTEMPERATURE) {
        this.TESTTEMPERATURE = TESTTEMPERATURE;
    }

    public double getABOVEHUMIDITY() {
        return ABOVEHUMIDITY;
    }

    public void setABOVEHUMIDITY(double ABOVEHUMIDITY) {
        this.ABOVEHUMIDITY = ABOVEHUMIDITY;
    }

    public double getBELOWHUMIDITY() {
        return BELOWHUMIDITY;
    }

    public void setBELOWHUMIDITY(double BELOWHUMIDITY) {
        this.BELOWHUMIDITY = BELOWHUMIDITY;
    }

    public double getABOVEPRESSURE() {
        return ABOVEPRESSURE;
    }

    public void setABOVEPRESSURE(double ABOVEPRESSURE) {
        this.ABOVEPRESSURE = ABOVEPRESSURE;
    }

    public double getBELOWPRESSURE() {
        return BELOWPRESSURE;
    }

    public void setBELOWPRESSURE(double BELOWPRESSURE) {
        this.BELOWPRESSURE = BELOWPRESSURE;
    }

    public double getFLOWRATE() {
        return FLOWRATE;
    }

    public void setFLOWRATE(double FLOWRATE) {
        this.FLOWRATE = FLOWRATE;
    }

    public double getOPPM() {
        return OPPM;
    }

    public void setOPPM(double OPPM) {
        this.OPPM = OPPM;
    }

    public double getWPPM() {
        return WPPM;
    }

    public void setWPPM(double WPPM) {
        this.WPPM = WPPM;
    }

    public double getPPM() {
        if (WPPM == 0) {
            return OPPM;
        } else {
            return WPPM;
        }
    }
    public String getPPM_readable() {
        double temp = 0;
        if (WPPM == 0) {
            temp =  OPPM;
        } else {
            temp =  WPPM;
        }
        return df3xs.format(temp);
    }

    public double getOTR() {
        return OTR;
    }

    public void setOTR(double OTR) {
        this.OTR = OTR;
    }

    public double getWVTR() {
        return WVTR;
    }

    public void setWVTR(double WVTR) {
        this.WVTR = WVTR;
    }

    public double getTR() {
        if (WVTR == 0) {
            return OTR;
        } else {
            return WVTR;
        }
    }
    public String getTR_readable() {
        double temp = 0;
        if (WVTR == 0) {
            temp =  OTR;
        } else {
            temp = WVTR;
        }
        return df4xs.format(temp);
    }

    public double getSENSORTEMP() {
        return SENSORTEMP;
    }

    public void setSENSORTEMP(double SENSORTEMP) {
        this.SENSORTEMP = SENSORTEMP;
    }

    public double getAMBTEMP() {
        return AMBTEMP;
    }

    public void setAMBTEMP(double AMBTEMP) {
        this.AMBTEMP = AMBTEMP;
    }

    public double getTESTTYPE() {
        return TESTTYPE;
    }

    public void setTESTTYPE(double TESTTYPE) {
        this.TESTTYPE = TESTTYPE;
    }

    public double getAMTEMPERATURE() {
        return AMTEMPERATURE;
    }

    public void setAMTEMPERATURE(double AMTEMPERATURE) {
        this.AMTEMPERATURE = AMTEMPERATURE;
    }

    public double getAMHUMIDITY() {
        return AMHUMIDITY;
    }

    public void setAMHUMIDITY(double AMHUMIDITY) {
        this.AMHUMIDITY = AMHUMIDITY;
    }

    public Date getPRODUCETIME() {
        return PRODUCETIME;
    }

    public String getPRODUCETIME_readable() {
        return sdf.format(PRODUCETIME);
    }

    public void setPRODUCETIME(Date PRODUCETIME) {
        this.PRODUCETIME = PRODUCETIME;
    }

    public double getCOFFICIENT() {
        return COFFICIENT;
    }
    public String getCOFFICIENT_readable() {
        return df4sci.format(COFFICIENT);
    }

    public void setCOFFICIENT(double COFFICIENT) {
        this.COFFICIENT = COFFICIENT;
    }

    @Override
    public String toString() {
        return "DbTblResult{" + "RESULTID=" + RESULTID + ", TESTID=" + TESTID + ", CELLID=" + CELLID + ", TIMESPAN=" + TIMESPAN + ", TESTTEMPERATURE=" + TESTTEMPERATURE + ", ABOVEHUMIDITY=" + ABOVEHUMIDITY + ", BELOWHUMIDITY=" + BELOWHUMIDITY + ", ABOVEPRESSURE=" + ABOVEPRESSURE + ", BELOWPRESSURE=" + BELOWPRESSURE + ", FLOWRATE=" + FLOWRATE + ", OPPM=" + OPPM + ", WPPM=" + WPPM + ", OTR=" + OTR + ", WVTR=" + WVTR + ", SENSORTEMP=" + SENSORTEMP + ", AMBTEMP=" + AMBTEMP + ", TESTTYPE=" + TESTTYPE + ", AMTEMPERATURE=" + AMTEMPERATURE + ", AMHUMIDITY=" + AMHUMIDITY + ", PRODUCETIME=" + PRODUCETIME + ", COFFICIENT=" + COFFICIENT + '}';
    }

}
