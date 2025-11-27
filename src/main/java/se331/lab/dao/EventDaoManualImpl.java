package se331.lab.dao;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import se331.lab.entity.Event;
import se331.lab.entity.Organizer;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Repository
@Profile("manual")
@RequiredArgsConstructor
public class EventDaoManualImpl implements EventDao {
    List<Event> events;

    @PostConstruct
    public void init(){
        events = new ArrayList<>();
        Organizer org1 = Organizer.builder().id(1L).name("CAMT").build();
        Organizer org2 = Organizer.builder().id(2L).name("CMU").build();
        Organizer org3 = Organizer.builder().id(3L).name("ChiangMai").build();

        events.add(Event.builder()
                .id(30L)
                .category("Academic")
                .title("Midterm Exam")
                .description("A time for taking the exam")
                .location("CAMT Building")
                .date("3rd Sept")
                .time("3.00-4.00 pm.")
                .petAllowed(false)
                .organizer(org1)
                .build());
        events.add(Event.builder()
                .id(31L)
                .category("Academic")
                .title("Commencement Day")
                .description("A time for celebration")
                .location("CMU Convention hall")
                .date("21th Jan")
                .time("8.00am-4.00 pm.")
                .petAllowed(false)
                .organizer(org1)
                .build());
        events.add(Event.builder()
                .id(32L)
                .category("Cultural")
                .title("Loy Krathong")
                .description("A time for Krathong")
                .location("Ping River")
                .date("21th Nov")
                .time("8.00-10.00 pm.")
                .petAllowed(false)
                .organizer(org2)
                .build());
        events.add(Event.builder()
                .id(33L)
                .category("Cultural")
                .title("Songkran")
                .description("Let's Play Water")
                .location("Chiang Mai Moat")
                .date("13th April")
                .time("10.00am - 6.00 pm.")
                .petAllowed(true)
                .organizer(org3)
                .build());
    }

    @Override
    public Integer getEventSize() {
        return events.size();
    }

    @Override
    public Page<Event> getEvents(Integer pageSize, Integer page) {
        pageSize = pageSize == null ? events.size() : pageSize;
        page = page == null ? 1 : page;
        int firstIndex = (page - 1) * pageSize;
        int lastIndex = Math.min(firstIndex + pageSize, events.size());
        List<Event> subList = firstIndex < lastIndex ? events.subList(firstIndex, lastIndex) : List.of();
        return new PageImpl<>(subList, PageRequest.of(page - 1, pageSize), events.size());
    }

    @Override
    public Event getEvent(Long id) {
        return events.stream().filter(e -> e.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public Event save(Event event) {
        if (event.getId() == null) {
            Long nextId = events.isEmpty() ? 1L : events.get(events.size() - 1).getId() + 1;
            event.setId(nextId);
        }
        events.add(event);
        return event;
    }

    @Override
    public Page<Event> getEvents(String title, Pageable page) {
        String q = title == null ? "" : title.toLowerCase(Locale.ROOT);
        List<Event> filtered = events.stream().filter(e ->
                (e.getTitle() != null && e.getTitle().toLowerCase(Locale.ROOT).contains(q))
                        || (e.getDescription() != null && e.getDescription().toLowerCase(Locale.ROOT).contains(q))
                        || (e.getOrganizer() != null && e.getOrganizer().getName() != null && e.getOrganizer().getName().toLowerCase(Locale.ROOT).contains(q))
        ).collect(Collectors.toList());

        int pageSize = page.isPaged() ? page.getPageSize() : filtered.size();
        int pageNum = page.isPaged() ? page.getPageNumber() : 0;
        int firstIndex = pageNum * pageSize;
        int lastIndex = Math.min(firstIndex + pageSize, filtered.size());
        List<Event> subList = firstIndex < lastIndex ? filtered.subList(firstIndex, lastIndex) : List.of();
        return new PageImpl<>(subList, page, filtered.size());
    }
}