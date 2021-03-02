package com.epam.spring.eventLogger;

import com.epam.spring.beans.Event;

import java.util.ArrayList;
import java.util.List;

public class CacheFileEventLogger extends FileEventLogger {
    private int cacheSize;
    ArrayList<Event> cache;

    public void setCacheSize(int cacheSize) {
        this.cacheSize = cacheSize;
    }

    public List<Event> getCache() {
        return cache;
    }

    public CacheFileEventLogger(String fileName) {
        super(fileName);
        cache = new ArrayList<Event>();
    }

    public void logEvent(Event event) {
        cache.add(event);
        if (cache.size() == cacheSize) {
            writeEventsFromCache(cache);
            cache.clear();
            System.out.println("cache has cleaned");
        }
    }
    public void destroy(){
        if(!cache.isEmpty()){
            writeEventsFromCache(cache);
        }
    }


    private void writeEventsFromCache(ArrayList<Event> cache) {
        super.logEvent(cache);
    }

    public void cacheContent(){
        cache.forEach((e)-> System.out.println("inside the cache: "+e));
    }
}
