package com.epam.spring.eventLogger;

import com.epam.spring.beans.Event;

public interface EventLogger {
    public void logEvent(Event event);
}
