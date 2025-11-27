package se331.lab.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Profile;
import jakarta.transaction.Transactional;
import se331.lab.entity.Event;
import se331.lab.entity.Organizer;
import se331.lab.entity.Participant;
import se331.lab.repository.EventRepository;
import se331.lab.repository.OrganizerRepository;
import se331.lab.repository.ParticipantRepository;
import java.util.ArrayList;

@Component
@Profile("db")
@RequiredArgsConstructor
public class InitApp implements ApplicationListener<ApplicationReadyEvent> {
    final EventRepository eventRepository;
    final OrganizerRepository organizerRepository;
    final ParticipantRepository participantRepository;

    @Override
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        Organizer org1 = organizerRepository.save(Organizer.builder().name("CAMT").build());
        Organizer org2 = organizerRepository.save(Organizer.builder().name("CMU").build());
        Organizer org3 = organizerRepository.save(Organizer.builder().name("ChiangMai").build());

        Event e1 = eventRepository.save(Event.builder()
                .category("Academic")
                .title("Midterm Exam")
                .description("A time for taking the exam")
                .location("CAMT Building")
                .date("3rd Sept")
                .time("3.00-4.00 pm.")
                .petAllowed(false)
                .build());
        e1.setOrganizer(org1);
        org1.getOwnEvents().add(e1);
        eventRepository.save(e1);

        Event e2 = eventRepository.save(Event.builder()
                .category("Academic")
                .title("Commencement Day")
                .description("A time for celebration")
                .location("CMU Convention hall")
                .date("21th Jan")
                .time("8.00am-4.00 pm.")
                .petAllowed(false)
                .build());
        e2.setOrganizer(org1);
        org1.getOwnEvents().add(e2);
        eventRepository.save(e2);

        Event e3 = eventRepository.save(Event.builder()
                .category("Cultural")
                .title("Loy Krathong")
                .description("A time for Krathong")
                .location("Ping River")
                .date("21th Nov")
                .time("8.00-10.00 pm.")
                .petAllowed(false)
                .build());
        e3.setOrganizer(org2);
        org2.getOwnEvents().add(e3);
        eventRepository.save(e3);

        Event e4 = eventRepository.save(Event.builder()
                .category("Cultural")
                .title("Songkran")
                .description("Let's Play Water")
                .location("Chiang Mai Moat")
                .date("13th April")
                .time("10.00am - 6.00 pm.")
                .petAllowed(true)
                .build());
        e4.setOrganizer(org3);
        org3.getOwnEvents().add(e4);
        eventRepository.save(e4);

        Participant p1 = participantRepository.save(Participant.builder().name("Alice").telNo("081-111-1111").build());
        Participant p2 = participantRepository.save(Participant.builder().name("Bob").telNo("082-222-2222").build());
        Participant p3 = participantRepository.save(Participant.builder().name("Carol").telNo("083-333-3333").build());
        Participant p4 = participantRepository.save(Participant.builder().name("Dave").telNo("084-444-4444").build());
        Participant p5 = participantRepository.save(Participant.builder().name("Eve").telNo("085-555-5555").build());

        p1.setEventHistory(new ArrayList<>());
        p1.getEventHistory().add(e1);
        p1.getEventHistory().add(e2);
        p1.getEventHistory().add(e3);
        participantRepository.save(p1);

        p2.setEventHistory(new ArrayList<>());
        p2.getEventHistory().add(e1);
        p2.getEventHistory().add(e2);
        p2.getEventHistory().add(e4);
        participantRepository.save(p2);

        p3.setEventHistory(new ArrayList<>());
        p3.getEventHistory().add(e1);
        p3.getEventHistory().add(e3);
        p3.getEventHistory().add(e4);
        participantRepository.save(p3);

        p4.setEventHistory(new ArrayList<>());
        p4.getEventHistory().add(e2);
        p4.getEventHistory().add(e3);
        participantRepository.save(p4);

        p5.setEventHistory(new ArrayList<>());
        p5.getEventHistory().add(e1);
        p5.getEventHistory().add(e4);
        participantRepository.save(p5);
    }
}
