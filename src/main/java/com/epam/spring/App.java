package com.epam.spring;

import com.epam.spring.beans.Client;
import com.epam.spring.beans.Event;
import com.epam.spring.eventLogger.CacheFileEventLogger;
import com.epam.spring.eventLogger.EventLogger;
import com.epam.spring.utility.EventType;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

public class App {
    CacheFileEventLogger cacheFileEventLogger;
    private final Client client;
    private final Map<EventType, EventLogger> loggersMap;
    private final EventLogger defaultLogger;


    public App(Client client, EventLogger eventLogger, Map<EventType, EventLogger> loggersMap) {
        super();
        this.client = client;
        this.defaultLogger = eventLogger;
        this.loggersMap = loggersMap;
    }

    //-------------------------------------------------
    public static void main(String[] args) {
        Event event;


        ConfigurableApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        App app = context.getBean("app", App.class);

        for (int i = 0; i < 19; i++) {
            event = context.getBean("event", Event.class);
            app.logEvent(EventType.ERROR, event, "some event for 1");
        }

        app.getCacheContent();
        context.close();
    }
//-------------------------------------------------

    private void logEvent(EventType eventType, Event event, String msg) {
        String message = msg.replaceAll(client.getId(), client.getFullName());
        event.setMsg(message);

        EventLogger logger = loggersMap.get(eventType);

        if (loggersMap.get(eventType) == null) {
            logger = defaultLogger;
        }
        logger.logEvent(event);

        System.out.println("Logger name: " + logger.getName());
    }

    private void getCacheContent() {
        defaultLogger.toString();
    }

}
