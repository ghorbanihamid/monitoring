package com.soshiant.server.thread;

import com.soshiant.server.periodictasks.SendSmsTask;
import java.util.Timer;

/**
 * Created with IntelliJ IDEA.
 * User: hamid
 * Date: 3/27/12
 * Time: 11:00 PM
 */

public class SendSmsTimer {

    private final static int startTime    = 30 * 1000;                 // starts after 10 minute
//    private final static int startTime    = 10 * 60 * 1000;                 // starts after 10 minute
    private final static int repeatPeriod = 2 * 60 * 60 * 1000;     // repeat every 2 hours.

    public void run(){
        Timer timer = new Timer();
//        timer.scheduleAtFixedRate(new SendSmsTask(), startTime, repeatPeriod);
    }


}
