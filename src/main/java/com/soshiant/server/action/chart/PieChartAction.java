package com.soshiant.server.action.chart;

import com.opensymphony.xwork2.ActionSupport;
import com.soshiant.common.result.ResultObject;
import com.soshiant.server.constants.ActionConstants;
import com.soshiant.server.constants.ServerConstants;
import com.soshiant.server.facade.log.AppLogFacade;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.validation.SkipValidation;

import java.awt.*;
import java.util.*;
import java.util.List;

/*
 * Created by Hamid on 11/27/15.
 */

public class PieChartAction extends ActionSupport {

    private static final long     serialVersionUID = 4851863957798371834L;

    Logger log = Logger.getLogger(PieChartAction.class);
    private String actionName;

    private String logMessage = "";

    private List<Point>           points;
    private List<Point>           pointsWithNull;
    private Map<Integer, Integer> pointsFromMap;
    private Map<Date, Integer>    dateFromMap;
    private String                minTime;
    private String                maxTime;



    private AppLogFacade appLogFacade;

    public void setAppLogFacade(AppLogFacade appLogFacade) {
        this.appLogFacade = appLogFacade;
    }


    //======================================================================================================================
    @SkipValidation
    public String showPieChartInputForm() throws Exception {

        this.actionName = "showPieChartInputForm()";

        try {
            return INPUT;
        }
        catch (Exception e) {
            this.logMessage = this.actionName + " method Exception:";
            log.error(this.logMessage + e.getMessage());
            if (ServerConstants.isAppLogEnabled)
                appLogFacade.logInfo(ServerConstants.ActionMethodIsNotMainOperation, 0, 0, -1, 0, ActionConstants.LOGIN_ACTION_PROCESS_CODE, "", "Server", this.actionName, ServerConstants.NoSqlText, this.logMessage, e.getMessage(), ServerConstants.EmptyModel);
            addActionError(getText("error.common.systemCouldNotRespond1"));
            return ERROR;
        }
    }
    //======================================================================================================================
    @Actions( {
            @Action(value = "/charts", results = {
                @Result(location = "chart.jsp", name = "success")
            }),
            @Action(value = "/jsonChartData", results = {
                @Result(location = "chart.jsp", name = "success")
            })
    })

    public String execute() throws Exception {

        points = new LinkedList<Point>();

        points.add(new Point(0, 3));
        points.add(new Point(4, 8));
        points.add(new Point(8, 5));
        points.add(new Point(9, 13));

        pointsWithNull = new LinkedList<Point>();

        pointsWithNull.add(new Point(0, 12));
        pointsWithNull.add(new Point(7, 12));
        pointsWithNull.add(null);
        pointsWithNull.add(new Point(7, 2));
        pointsWithNull.add(new Point(12, 2));

        pointsFromMap = new HashMap<Integer, Integer>();
        pointsFromMap.put(2, 5);
        pointsFromMap.put(3, 6);
        pointsFromMap.put(4, 7);
        pointsFromMap.put(5, 8);
        pointsFromMap.put(6, 7);
        pointsFromMap.put(7, 6);

        dateFromMap = new TreeMap<Date, Integer>();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -2);

        minTime = "" + calendar.getTime().getTime();
        System.out.println("minTime : " + minTime);

        Random generator = new Random();
        for (int i = 1; i <= 24; i++) {
            dateFromMap.put(calendar.getTime(), generator.nextInt(100));
            calendar.add(Calendar.MONTH, +1);
        }
        maxTime = "" + calendar.getTime().getTime();
        System.out.println("maxTime : " + maxTime);

        return SUCCESS;
    }

    public List<Point> getPoints()
    {
        return points;
    }

    public List<Point> getPointsWithNull()
    {
        return pointsWithNull;
    }

    public Map<Integer, Integer> getPointsFromMap()
    {
        return pointsFromMap;
    }

    public Map<Date, Integer> getDateFromMap()
    {
        return dateFromMap;
    }

    public String getMinTime()
    {
        return minTime;
    }

    public String getMaxTime()
    {
        return maxTime;
    }

}
