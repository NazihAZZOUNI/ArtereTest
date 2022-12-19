package services;


import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.map.LRUMap;

import java.util.ArrayList;

public class InMemoryService <K, T> {
    private final long timeToLive;

    private final LRUMap inMemoryMap;
    protected class InMemoryObject {
        public long lastAccessed = System.currentTimeMillis();
        public T value;
        protected InMemoryObject(T value) {
            this.value = value;
        }
    }
    public InMemoryService(Integer inMemoryTimeToLive, Integer inMemoryTimerInterval, Integer maxItems) {
        this.timeToLive = inMemoryTimeToLive * 1000;
        inMemoryMap = new LRUMap(maxItems);
        if (timeToLive > 0 && inMemoryTimerInterval > 0) {
            Thread t = new Thread(new Runnable() {
                public void run() {
                    while (true) {
                        try {
                            Thread.sleep(inMemoryTimerInterval * 1000);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                        inMemoryCleanup();
                    }
                }
            });
            t.setDaemon(true);
            t.start();
        }
    }
    public void put(K key, T value) {
        synchronized (inMemoryMap) {
            inMemoryMap.put(key, new InMemoryObject(value));
        }
    }
    public T get(K key) {
        synchronized (inMemoryMap) {
            InMemoryObject c;
            c = (InMemoryObject) inMemoryMap.get(key);
            if (c == null)
                return null;
            else {
                c.lastAccessed = System.currentTimeMillis();
                return c.value;
            }
        }
    }
    public void remove(K key) {
        synchronized (inMemoryMap) {
            inMemoryMap.remove(key);
        }
    }
    public int size() {
        synchronized (inMemoryMap) {
            return inMemoryMap.size();
        }
    }
    public void inMemoryCleanup() {
        long now = System.currentTimeMillis();
        ArrayList<K> deleteKey = null;
        synchronized (inMemoryMap) {
            MapIterator itr = inMemoryMap.mapIterator();
            deleteKey = new ArrayList<K>((inMemoryMap.size() / 2) + 1);
            K key = null;
            InMemoryObject c = null;
            while (itr.hasNext()) {
                key = (K) itr.next();
                c = (InMemoryObject) itr.getValue();
                if (c != null && (now > (timeToLive + c.lastAccessed))) {
                    deleteKey.add(key);
                }
            }
        }
        for (K key : deleteKey) {
            synchronized (inMemoryMap) {
                inMemoryMap.remove(key);
            }
            Thread.yield();
        }
    }
}
