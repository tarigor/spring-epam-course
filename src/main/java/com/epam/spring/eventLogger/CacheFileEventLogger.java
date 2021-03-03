package com.epam.spring.eventLogger;

import com.epam.spring.beans.Event;

import java.util.ArrayList;
import java.util.List;

public class CacheFileEventLogger extends FileEventLogger {
    ArrayList<Event> cache;
    private final int cacheSize;

    public CacheFileEventLogger(String fileName, int cacheSize) {
        super(fileName);
        this.cacheSize = cacheSize;
        cache = new ArrayList<Event>();
    }

    public List<Event> getCache() {
        return cache;
    }

    @Override
    public void logEvent(Event event) {
        cache.add(event);
        System.out.println("cache size: " + cacheSize);
        if (cache.size() == cacheSize) {
            writeEventsFromCache(cache);
            cache.clear();
            System.out.println("cache has cleaned");
        }
    }

    public void destroy() {
//        if (!cache.isEmpty()) {
//            writeEventsFromCache(cache);
//        }
    }

    private void writeEventsFromCache(ArrayList<Event> cache) {
        cache.stream().forEach(super::logEvent);
    }

    @Override
    public String toString() {
        cache.forEach((e) -> System.out.println("inside the cache: " + e));
        return "";
    }

    public void cacheContent() {
        cache.forEach((e) -> System.out.println("inside the cache: " + e));
    }
}
