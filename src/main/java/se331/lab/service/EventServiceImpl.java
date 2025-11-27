package se331.lab.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Profile;
import se331.lab.dao.EventDao;
import se331.lab.entity.Event;

@Service
@Profile("db")
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    final EventDao eventDao;

    @Override
    public Integer getEventSize() {
        return eventDao.getEventSize();
    }

    @Override
    public Page<Event> getEvents(Integer pageSize, Integer page) {
        return eventDao.getEvents(pageSize, page);
    }

    @Override
    public Event getEvent(Long id) {
        return eventDao.getEvent(id);
    }

    @Override
    public Event save(Event event) {
        return eventDao.save(event);
    }

    @Override
    public Page<Event> getEvents(String title, Pageable pageable) {
        return eventDao.getEvents(title, pageable);
    }
}
