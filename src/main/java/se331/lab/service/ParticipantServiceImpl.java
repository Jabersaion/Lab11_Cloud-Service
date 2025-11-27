package se331.lab.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import se331.lab.dao.ParticipantDao;
import se331.lab.entity.Participant;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParticipantServiceImpl implements ParticipantService {
    final ParticipantDao participantDao;

    @Override
    public Integer getParticipantSize() {
        return participantDao.getParticipantSize();
    }

    @Override
    public List<Participant> getParticipants(Integer pageSize, Integer page) {
        return participantDao.getParticipants(pageSize, page);
    }

    @Override
    public Participant getParticipant(Long id) {
        return participantDao.getParticipant(id);
    }

    @Override
    public Participant save(Participant participant) {
        return participantDao.save(participant);
    }

    @Override
    public List<Participant> getAllParticipants() {
        return participantDao.getParticipant(Pageable.unpaged()).getContent();
    }

    @Override
    public Page<Participant> getParticipant(Integer page, Integer pageSize) {
        page = page == null ? 0 : page;
        pageSize = pageSize == null ? Integer.MAX_VALUE : pageSize;
        return participantDao.getParticipant(PageRequest.of(page, pageSize));
    }
}