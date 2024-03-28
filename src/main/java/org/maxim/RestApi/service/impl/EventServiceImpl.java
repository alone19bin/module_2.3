package org.maxim.RestApi.service.impl;



import org.maxim.RestApi.model.Event;
import org.maxim.RestApi.repository.EventRepository;
import org.maxim.RestApi.repository.hiber.EventRepositoryImpl;

import java.util.List;

public class EventServiceImpl  {

    private EventRepository eventRepository = new EventRepositoryImpl();

    public List<Event> getAll() {
        return eventRepository.getAll();
    }

    public Event getById(Integer id) {
        return eventRepository.getById(id);
    }

    public Event save(Event event) {
        return eventRepository.save(event);
    }

    public boolean deleteById(Integer id) {
        return eventRepository.deleteById(id);
    }
}
