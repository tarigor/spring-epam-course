package com.epam.spring;

import com.epam.spring.beans.Client;
import com.epam.spring.beans.Event;
import com.epam.spring.eventLogger.CacheFileEventLogger;
import com.epam.spring.eventLogger.EventLogger;
import com.epam.spring.eventLogger.FileEventLogger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    private Client client;
    EventLogger eventLogger;
    FileEventLogger fileEventLogger;
    CacheFileEventLogger cacheFileEventLogger;

    public App(Client client, EventLogger eventLogger, FileEventLogger fileEventLogger, CacheFileEventLogger cacheFileEventLogger) {
        super();
        this.client = client;
        this.eventLogger = eventLogger;
        this.fileEventLogger = fileEventLogger;
        this.cacheFileEventLogger = cacheFileEventLogger;
    }

    //-------------------------------------------------
    public static void main(String[] args) {
        Event event;


        ConfigurableApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        App app = context.getBean("app", App.class);

//        event = context.getBean("event", Event.class);
//        app.logEvent(event, "Some event for 1");
//
//        event = context.getBean("event", Event.class);
//        app.logEvent(event, "Some event for 1");

        for (int i = 0; i < 19; i++) {
            event = context.getBean("event", Event.class);
            app.fileEvent(event, "some event for 1");
        }
        app.getCacheContent();
        context.close();
    }
//-------------------------------------------------

    private void logEvent(Event event, String msg) {
        String message = msg.replaceAll(client.getId(), client.getFullName());
        event.setMsg(message);
        eventLogger.logEvent(event);
    }

    private void fileEvent(Event event, String msg) {
        String message = msg.replaceAll(client.getId(), client.getFullName());
        event.setMsg(message);
        cacheFileEventLogger.logEvent(event);
    }

    private void getCacheContent(){
        cacheFileEventLogger.cacheContent();
    }

}
