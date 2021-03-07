package com.epam.spring;

import com.epam.spring.beans.Client;
import com.epam.spring.beans.Event;
import com.epam.spring.config.AppConfig;
import com.epam.spring.config.EventLoggerConfig;
import com.epam.spring.eventLogger.EventLogger;
import com.epam.spring.utility.EventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class App {

    @Autowired
    private Client client;

    @Resource(name = "loggerMap")
    private Map<EventType, EventLogger> loggersMap;

    @Resource(name = "defaultLogger")
    private EventLogger defaultLogger;

    public App() {
    }

    App(Client client, EventLogger eventLogger, Map<EventType, EventLogger> loggersMap) {
        super();
        this.client = client;
        this.defaultLogger = eventLogger;
        this.loggersMap = loggersMap;
    }

    //-------------------------------------------------
    public static void main(String[] args) {
        Event event;

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(AppConfig.class, EventLoggerConfig.class);
        ctx.scan("com.epam.spring");
        ctx.refresh();

        App app = (App) ctx.getBean("app");

        Client client = ctx.getBean(Client.class);
        System.out.println("Client says: " + client.getGreeting());

        for (int i = 0; i < 19; i++) {
            event = ctx.getBean(Event.class);
            app.logEvent(EventType.ERROR, event, "some event for 1");
        }
        app.getCacheContent();
        ctx.close();
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
    }

    private void getCacheContent() {
        defaultLogger.toString();
    }

}
