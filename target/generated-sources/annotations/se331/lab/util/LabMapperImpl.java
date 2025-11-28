package se331.lab.util;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import se331.lab.entity.Event;
import se331.lab.entity.EventDTO;
import se331.lab.entity.EventOrganizerDTO;
import se331.lab.entity.EventSummaryDTO;
import se331.lab.entity.Organizer;
import se331.lab.entity.OrganizerDTO;
import se331.lab.entity.OrganizerOwnEventsDTO;
import se331.lab.entity.Participant;
import se331.lab.entity.ParticipantDTO;
import se331.lab.entity.ParticipantWithEventsDTO;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-28T10:24:55+0800",
    comments = "version: 1.6.0, compiler: Eclipse JDT (IDE) 3.44.0.v20251118-1623, environment: Java 21.0.9 (Eclipse Adoptium)"
)
public class LabMapperImpl implements LabMapper {

    @Override
    public EventDTO getEventDto(Event event) {
        if ( event == null ) {
            return null;
        }

        EventDTO.EventDTOBuilder eventDTO = EventDTO.builder();

        eventDTO.category( event.getCategory() );
        eventDTO.date( event.getDate() );
        eventDTO.description( event.getDescription() );
        eventDTO.id( event.getId() );
        List<String> list = event.getImages();
        if ( list != null ) {
            eventDTO.images( new ArrayList<String>( list ) );
        }
        eventDTO.location( event.getLocation() );
        eventDTO.organizer( organizerToEventOrganizerDTO( event.getOrganizer() ) );
        eventDTO.petAllowed( event.getPetAllowed() );
        eventDTO.time( event.getTime() );
        eventDTO.title( event.getTitle() );

        return eventDTO.build();
    }

    @Override
    public List<EventDTO> getEventDto(List<Event> events) {
        if ( events == null ) {
            return null;
        }

        List<EventDTO> list = new ArrayList<EventDTO>( events.size() );
        for ( Event event : events ) {
            list.add( getEventDto( event ) );
        }

        return list;
    }

    @Override
    public OrganizerDTO getOrganizerDTO(Organizer organizer) {
        if ( organizer == null ) {
            return null;
        }

        OrganizerDTO.OrganizerDTOBuilder organizerDTO = OrganizerDTO.builder();

        organizerDTO.id( organizer.getId() );
        organizerDTO.name( organizer.getName() );
        organizerDTO.ownEvents( eventListToOrganizerOwnEventsDTOList( organizer.getOwnEvents() ) );

        return organizerDTO.build();
    }

    @Override
    public List<OrganizerDTO> getOrganizerDTO(List<Organizer> organizers) {
        if ( organizers == null ) {
            return null;
        }

        List<OrganizerDTO> list = new ArrayList<OrganizerDTO>( organizers.size() );
        for ( Organizer organizer : organizers ) {
            list.add( getOrganizerDTO( organizer ) );
        }

        return list;
    }

    @Override
    public ParticipantDTO getParticipantDTO(Participant participant) {
        if ( participant == null ) {
            return null;
        }

        ParticipantDTO.ParticipantDTOBuilder participantDTO = ParticipantDTO.builder();

        participantDTO.id( participant.getId() );
        participantDTO.name( participant.getName() );
        participantDTO.telNo( participant.getTelNo() );

        return participantDTO.build();
    }

    @Override
    public List<ParticipantDTO> getParticipantDTO(List<Participant> participants) {
        if ( participants == null ) {
            return null;
        }

        List<ParticipantDTO> list = new ArrayList<ParticipantDTO>( participants.size() );
        for ( Participant participant : participants ) {
            list.add( getParticipantDTO( participant ) );
        }

        return list;
    }

    @Override
    public ParticipantWithEventsDTO getParticipantWithEventsDTO(Participant participant) {
        if ( participant == null ) {
            return null;
        }

        ParticipantWithEventsDTO.ParticipantWithEventsDTOBuilder participantWithEventsDTO = ParticipantWithEventsDTO.builder();

        participantWithEventsDTO.eventHistory( getEventSummaryDTO( participant.getEventHistory() ) );
        participantWithEventsDTO.id( participant.getId() );
        participantWithEventsDTO.name( participant.getName() );
        participantWithEventsDTO.telNo( participant.getTelNo() );

        return participantWithEventsDTO.build();
    }

    @Override
    public List<ParticipantWithEventsDTO> getParticipantWithEventsDTO(List<Participant> participants) {
        if ( participants == null ) {
            return null;
        }

        List<ParticipantWithEventsDTO> list = new ArrayList<ParticipantWithEventsDTO>( participants.size() );
        for ( Participant participant : participants ) {
            list.add( getParticipantWithEventsDTO( participant ) );
        }

        return list;
    }

    @Override
    public EventSummaryDTO getEventSummaryDTO(Event event) {
        if ( event == null ) {
            return null;
        }

        EventSummaryDTO.EventSummaryDTOBuilder eventSummaryDTO = EventSummaryDTO.builder();

        eventSummaryDTO.id( event.getId() );
        eventSummaryDTO.title( event.getTitle() );

        return eventSummaryDTO.build();
    }

    @Override
    public List<EventSummaryDTO> getEventSummaryDTO(List<Event> events) {
        if ( events == null ) {
            return null;
        }

        List<EventSummaryDTO> list = new ArrayList<EventSummaryDTO>( events.size() );
        for ( Event event : events ) {
            list.add( getEventSummaryDTO( event ) );
        }

        return list;
    }

    protected EventOrganizerDTO organizerToEventOrganizerDTO(Organizer organizer) {
        if ( organizer == null ) {
            return null;
        }

        EventOrganizerDTO.EventOrganizerDTOBuilder eventOrganizerDTO = EventOrganizerDTO.builder();

        eventOrganizerDTO.id( organizer.getId() );
        eventOrganizerDTO.name( organizer.getName() );

        return eventOrganizerDTO.build();
    }

    protected OrganizerOwnEventsDTO eventToOrganizerOwnEventsDTO(Event event) {
        if ( event == null ) {
            return null;
        }

        OrganizerOwnEventsDTO.OrganizerOwnEventsDTOBuilder organizerOwnEventsDTO = OrganizerOwnEventsDTO.builder();

        organizerOwnEventsDTO.category( event.getCategory() );
        organizerOwnEventsDTO.date( event.getDate() );
        organizerOwnEventsDTO.description( event.getDescription() );
        organizerOwnEventsDTO.id( event.getId() );
        organizerOwnEventsDTO.location( event.getLocation() );
        organizerOwnEventsDTO.participants( getParticipantDTO( event.getParticipants() ) );
        organizerOwnEventsDTO.petAllowed( event.getPetAllowed() );
        organizerOwnEventsDTO.time( event.getTime() );
        organizerOwnEventsDTO.title( event.getTitle() );

        return organizerOwnEventsDTO.build();
    }

    protected List<OrganizerOwnEventsDTO> eventListToOrganizerOwnEventsDTOList(List<Event> list) {
        if ( list == null ) {
            return null;
        }

        List<OrganizerOwnEventsDTO> list1 = new ArrayList<OrganizerOwnEventsDTO>( list.size() );
        for ( Event event : list ) {
            list1.add( eventToOrganizerOwnEventsDTO( event ) );
        }

        return list1;
    }
}
