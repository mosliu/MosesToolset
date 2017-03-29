/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.liuxuan.device.w3330.exporter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Moses
 */
public class DbTblTest {

    private int TESTID;
    private String TESTTYPE;
    private int DEVICEID;
    private String CELLID;
    private Date STARTTIME;
    private Date ENDTIME;
    private double SETTEMP;
    private String SAMPLETYPE;
    private String AREA;
    private int THICKNESS;
    private String OPERATOR;
    private String SPECITYPE;
    private String SAMPLESOURCE;
    private ArrayList<DbTblResult> Results;

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd HH:mm:ss");
    private static SimpleDateFormat sdfsort = new SimpleDateFormat("yyyy-MM-dd");

    //生成一个indexName供排序分类用
    public String getIndexName() {
        String indexname = DEVICEID + "-" + sdfsort.format(STARTTIME);
        return indexname;
    }

    public ArrayList<DbTblResult> getResults() {
        return Results;
    }

    public void setResults(ArrayList<DbTblResult> Results) {
        this.Results = Results;
    }

    public int getTESTID() {
        return TESTID;
    }

    public void setTESTID(int TESTID) {
        this.TESTID = TESTID;
    }

    public String getTESTTYPE() {
        return TESTTYPE;
    }

    public String getTESTTYPE_readable() {
        if (TESTTYPE.equals("WVTR")) {
            return "透湿试验";
        } else if (TESTTYPE.equals("OTR")) {
            return "透氧试验";

        } else {
            return TESTTYPE;
        }
    }

    public void setTESTTYPE(String TESTTYPE) {
        this.TESTTYPE = TESTTYPE;
    }

    public int getDEVICEID() {
        return DEVICEID;
    }

    public void setDEVICEID(int DEVICEID) {
        this.DEVICEID = DEVICEID;
    }

    public String getCELLID() {
        return CELLID;
    }

    public String getCELLID_readable() {
        if (CELLID.equals("1")) {
            return "A";
        } else if (CELLID.equals("2")) {
            return "B";
        } else if (CELLID.equals("3")) {
            return "C";
        } else {
            return CELLID;
        }
    }

    public void setCELLID(String CELLID) {
        this.CELLID = CELLID;
    }

    public Date getSTARTTIME() {
        return STARTTIME;
    }
    public String getSTARTTIME_readable() {
        return sdf.format(STARTTIME);
    }

    public void setSTARTTIME(Date STARTTIME) {
        this.STARTTIME = STARTTIME;
    }

    public Date getENDTIME() {
        return ENDTIME;
    }
    public String getENDTIME_readable() {
        return sdf.format(ENDTIME);
    }

    public void setENDTIME(Date ENDTIME) {
        this.ENDTIME = ENDTIME;
    }

    public double getSETTEMP() {
        return SETTEMP;
    }

    public void setSETTEMP(double SETTEMP) {
        this.SETTEMP = SETTEMP;
    }

    public String getSAMPLETYPE() {
        return SAMPLETYPE;
    }

    public void setSAMPLETYPE(String SAMPLETYPE) {
        this.SAMPLETYPE = SAMPLETYPE;
    }

    public String getAREA() {
        return AREA;
    }

    public void setAREA(String AREA) {
        this.AREA = AREA;
    }

    public int getTHICKNESS() {
        return THICKNESS;
    }

    public void setTHICKNESS(int THICKNESS) {
        this.THICKNESS = THICKNESS;
    }

    public String getOPERATOR() {
        return OPERATOR;
    }

    public void setOPERATOR(String OPERATOR) {
        this.OPERATOR = OPERATOR;
    }

    public String getSPECITYPE() {
        return SPECITYPE;
    }

    public void setSPECITYPE(String SPECITYPE) {
        this.SPECITYPE = SPECITYPE;
    }

    public String getSAMPLESOURCE() {
        return SAMPLESOURCE;
    }

    public void setSAMPLESOURCE(String SAMPLESOURCE) {
        this.SAMPLESOURCE = SAMPLESOURCE;
    }

    @Override
    public String toString() {
        return "DbTblTest{" + "TESTID=" + TESTID + ", TESTTYPE=" + TESTTYPE + ", DEVICEID=" + DEVICEID + ", CELLID=" + CELLID + ", STARTTIME=" + STARTTIME + ", ENDTIME=" + ENDTIME + ", SETTEMP=" + SETTEMP + ", SAMPLETYPE=" + SAMPLETYPE + ", AREA=" + AREA + ", THICKNESS=" + THICKNESS + ", OPERATOR=" + OPERATOR + ", SPECITYPE=" + SPECITYPE + ", SAMPLESOURCE=" + SAMPLESOURCE + ", ResultsNo=" + Results.size() + '}';
    }

}
