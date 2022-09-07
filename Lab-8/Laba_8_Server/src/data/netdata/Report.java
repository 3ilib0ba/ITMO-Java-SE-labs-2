package data.netdata;

import typesfiles.Flat;

import java.io.Serializable;
import java.util.TreeMap;

public class Report implements Serializable {
    private ReportState reportState;
    private String reportBody;
    private TreeMap<Integer, Flat> myMap;

    public Report(ReportState reportState, String reportBody, TreeMap<Integer, Flat> myMap) {
        this.reportState = reportState;
        this.reportBody = reportBody;
        this.myMap = myMap;
    }

    public ReportState getReportState() {
        return reportState;
    }

    public String getReportBody() {
        return reportBody;
    }

    public TreeMap<Integer, Flat> getMyMap() {
        return myMap;
    }
}
