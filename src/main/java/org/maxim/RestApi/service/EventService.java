package org.maxim.RestApi.service;


import org.maxim.RestApi.model.Event;
import org.maxim.RestApi.repository.EventRepository;
import org.maxim.RestApi.repository.hiber.EventHib;

import java.util.List;

public class EventService {
    private EventRepository eventRepository = new EventHib();

    public List<Event> getAll() {
        return    eventRepository.getAll();
    }

    public  Event save(Event event) {
        return eventRepository.save(event);
    }

    public Event update(Event event) {
        return eventRepository.save(event);
    }

    public boolean deleteById(Integer id) {
        return eventRepository.deleteById(id);
    }

    public Event getById(Integer id) {
        return eventRepository.getById(id);
    }

}
