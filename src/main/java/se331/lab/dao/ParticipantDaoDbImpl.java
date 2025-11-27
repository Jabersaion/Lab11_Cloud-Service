package se331.lab.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import se331.lab.entity.Participant;
import se331.lab.repository.ParticipantRepository;

import java.util.List;

@Repository
@Profile("db")
@RequiredArgsConstructor
public class ParticipantDaoDbImpl implements ParticipantDao {
    final ParticipantRepository participantRepository;

    @Override
    public Integer getParticipantSize() {
        return Math.toIntExact(participantRepository.count());
    }

    @Override
    public List<Participant> getParticipants(Integer pageSize, Integer page) {
        pageSize = pageSize == null ? Math.toIntExact(participantRepository.count()) : pageSize;
        page = page == null ? 1 : page;
        return participantRepository.findAll(PageRequest.of(page - 1, pageSize)).getContent();
    }

    @Override
    public Participant getParticipant(Long id) {
        return participantRepository.findById(id).orElse(null);
    }

    @Override
    public Participant save(Participant participant) {
        return participantRepository.save(participant);
    }

    @Override
    public Page<Participant> getParticipant(Pageable pageRequest) {
        return participantRepository.findAll(pageRequest);
    }
}