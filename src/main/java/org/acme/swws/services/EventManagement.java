package org.acme.swws.services;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.jboss.logging.Logger;

import org.acme.swws.model.CloudEvent;
import org.acme.swws.model.Subscription;
import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import jakarta.inject.Singleton;

@Singleton
public class EventManagement {

    private static final Logger LOG = Logger.getLogger("SWWS_EVENT_BROKER");
    private HashMap<String,List<Notifier>> subscribersByType;

    public EventManagement(){
        this.subscribersByType = new HashMap<String,List<Notifier>>();
    }

    public void subscribe(Subscription subscription){
        Notifier notifier = RestClientBuilder.newBuilder()
            .baseUri(URI.create(subscription.getUrl()))
            .build(Notifier.class);
        if (subscribersByType.containsKey(subscription.getType())) {
            List<Notifier> subscribedUrls = subscribersByType.get(subscription.getType());
            subscribedUrls.add(notifier);
        } else {
            List<Notifier> subscribers = new ArrayList<Notifier>();
            subscribers.add(notifier);
            subscribersByType.put(subscription.getType(), subscribers);
        }
    }

    public void notify(CloudEvent event){
        List<Notifier> subscribers = subscribersByType.get(event.getType());
        if (subscribers == null) {
            LOG.info("[EVENT BROKER] no subscribers for event: " + event.getId() + "  - of type: " + event.getType());
        }
        if (subscribers != null) {
            for (Notifier subscriber : subscribers) {
                subscriber.inform(event);
            }
        }
    }

}