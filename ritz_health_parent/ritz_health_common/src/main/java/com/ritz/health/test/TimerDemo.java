package com.ritz.health.test;

import java.util.Timer;
import java.util.TimerTask;

public class TimerDemo {//1.它的任务是固定的 不能执行动态任务

    public static void main(String[] args) {
        TimerTask timerTask = new TimerTask() {

            @Override
            public void run() {
                System.out.println("小王 ok...");
            }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask,1,1);
    }
}
