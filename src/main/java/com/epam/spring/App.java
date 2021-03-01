package com.epam.spring;

import com.epam.spring.beans.Client;
import com.epam.spring.beans.Event;
import com.epam.spring.eventLogger.EventLogger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    private Client client;
    EventLogger eventLogger;

    public App(Client client, EventLogger eventLogger) {
        super();
        this.client = client;
        this.eventLogger = eventLogger;
    }

    //-------------------------------------------------
    public static void main(String[] args) {
        Event event;

        ApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        App app = context.getBean("app", App.class);

        event = context.getBean("event", Event.class);
        app.logEvent(event, "Some event for 1");

    }
//-------------------------------------------------

    private void logEvent(Event event, String msg) {
        String message = msg.replaceAll(client.getId(), client.getFullName());
        event.setMsg(message);
        eventLogger.logEvent(event);
    }

}
