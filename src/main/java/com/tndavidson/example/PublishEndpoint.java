package com.tndavidson.example;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.bus.BusProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *  endpoint that triggers our custom bus event
 *
 * @author Tim Davidson
 */
@RestController
public class PublishEndpoint {

    private ApplicationContext context;
    private BusProperties busProperties;

    @Autowired
    public PublishEndpoint(ApplicationContext context, BusProperties busProperties) {
        this.context = context; this.busProperties=busProperties;
    }

    @RequestMapping(value="/publish",method= RequestMethod.POST)
    public String publish() {

        final MyCustomRemoteEvent event =
                new MyCustomRemoteEvent(this, busProperties.getId(), "hello world");

        context.publishEvent(event);

        return "event published";
    }
}