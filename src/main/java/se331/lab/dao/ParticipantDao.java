package se331.lab.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import se331.lab.entity.Participant;

import java.util.List;

public interface ParticipantDao {
    Integer getParticipantSize();
    List<Participant> getParticipants(Integer pageSize, Integer page);
    Participant getParticipant(Long id);
    Participant save(Participant participant);
    Page<Participant> getParticipant(Pageable pageRequest);
}