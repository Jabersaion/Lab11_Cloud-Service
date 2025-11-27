package se331.lab.service;

import org.springframework.data.domain.Page;
import se331.lab.entity.Participant;

import java.util.List;

public interface ParticipantService {
    Integer getParticipantSize();
    List<Participant> getParticipants(Integer pageSize, Integer page);
    Participant getParticipant(Long id);
    Participant save(Participant participant);
    List<Participant> getAllParticipants();
    Page<Participant> getParticipant(Integer page, Integer pageSize);
}