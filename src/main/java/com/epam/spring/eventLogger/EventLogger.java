package com.epam.spring.eventLogger;

import com.epam.spring.beans.Event;

public interface EventLogger {

    void logEvent(Event event);

    public String getName();
}
