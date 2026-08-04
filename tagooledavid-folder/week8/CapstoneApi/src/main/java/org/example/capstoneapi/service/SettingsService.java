package org.example.capstoneapi.service;

import org.example.capstoneapi.configuration.CapstoneApiProperties;
import org.springframework.stereotype.Service;

@Service
public class SettingsService {
    private final CapstoneApiProperties capstoneApiProperties;

    public SettingsService( CapstoneApiProperties capstoneApiProperties){
        this.capstoneApiProperties = capstoneApiProperties;
    }

    public String getGreeting(){
        return capstoneApiProperties.getGreeting();
    }

    public int getPageSize(){
        return capstoneApiProperties.getPagesize();
    }

    
    public String getAllSettings(){
        return "Greeting: " + capstoneApiProperties.getGreeting() + " |        PageSize: " + capstoneApiProperties.getPagesize();
    }
}
