package com.epam.spring.config;

import com.epam.spring.eventLogger.*;
import com.epam.spring.utility.EventType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class EventLoggerConfig {

    @Resource(name = "cacheFileEventLogger")
    private CacheFileEventLogger cacheFileEventLogger;

    @Resource(name = "consoleEventLogger")
    private ConsoleEventLogger consoleEventLogger;

//    @Resource(name = "fileEventLogger")
//    private FileEventLogger fileEventLogger;

    @Resource(name = "combinedEventLogger")
    private CombinedEventLogger combinedEventLogger;

    @Bean
    public Collection<EventLogger> combinedLogger() {
        Collection<EventLogger> loggers = new ArrayList<>(2);
        loggers.add(consoleEventLogger);
        loggers.add(cacheFileEventLogger);
        return loggers;
    }

    @Bean
    public Map<EventType, EventLogger> loggerMap(){
        Map<EventType,EventLogger> map = new HashMap<>();
        map.put(EventType.INFO, consoleEventLogger);
        map.put(EventType.ERROR, combinedEventLogger);
        return map;
    }

    @Bean
    public EventLogger defaultLogger(){
        return cacheFileEventLogger;
    }
}
