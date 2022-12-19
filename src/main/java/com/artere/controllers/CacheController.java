package com.artere.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import services.Cache;
import services.CacheConfigService;

@RestController
public class CacheController {

    @Autowired
    private CacheConfigService cacheConfigService;

    @PostMapping("/cache-add")
    public String newCache(@RequestBody Cache cache) {
        cacheConfigService.getInMemoryService().put(cache.getKey(), cache.getValue());
        return "key: "+ cache.getKey() + " value: " + cache.getValue() + " added to cache";
    }

    @GetMapping("/cache-get")
    public String getValueFromCache(@RequestParam String key) {
        String message = cacheConfigService.getInMemoryService().get(key) != null ? "Value in cache " + cacheConfigService.getInMemoryService().get(key) : "Not found in cache";
        return message;
    }

    @GetMapping("/cache-remove")
    public String removeFromCache(@RequestParam String key) {
        String message ="";
        if (cacheConfigService.getInMemoryService().get(key) != null) {
            cacheConfigService.getInMemoryService().remove(key);
            message = "The element " + key + " removed from cache";
        } else {
            message = "Not found in cache";
        }
        return message;
    }

}
