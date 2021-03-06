package com.epam.spring.beans;

import java.text.DateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class Event {

    private static final AtomicInteger AUTO_ID = new AtomicInteger(0);

    private final int id;
    private String msg;
    private final Date date;
    private final DateFormat dateFormat;

    public Event(Date date, DateFormat dateFormat) {
        this.id = AUTO_ID.getAndIncrement();
        this.date = date;
        this.dateFormat = dateFormat;
    }

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public String getMsg() {

        return msg;
    }

    public void setMsg(String msg) {

        this.msg = msg;
    }

    public static Boolean isDay(Integer startHoursRange, Integer endHoursRange) {
        LocalTime localTime = LocalTime.now();
        System.out.println("current hour: " + localTime.getHour());
        return localTime.getHour() > startHoursRange && localTime.getHour() < endHoursRange;
    }

    @Override
    public String toString() {
        return "Event{id=" + id + " msg=" + msg + " date= " + dateFormat.format(date) + " }";
    }
}
