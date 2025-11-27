package se331.lab.dao;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import se331.lab.entity.Participant;

import java.util.ArrayList;
import java.util.List;

@Repository
@Profile("manual")
public class ParticipantDaoManualImpl implements ParticipantDao {
    List<Participant> participants;

    @PostConstruct
    public void init(){
        participants = new ArrayList<>();
        participants.add(Participant.builder().id(1L).name("Alice").telNo("081-111-1111").build());
        participants.add(Participant.builder().id(2L).name("Bob").telNo("082-222-2222").build());
        participants.add(Participant.builder().id(3L).name("Carol").telNo("083-333-3333").build());
        participants.add(Participant.builder().id(4L).name("Dave").telNo("084-444-4444").build());
        participants.add(Participant.builder().id(5L).name("Eve").telNo("085-555-5555").build());
    }

    @Override
    public Integer getParticipantSize() {
        return participants.size();
    }

    @Override
    public List<Participant> getParticipants(Integer pageSize, Integer page) {
        pageSize = pageSize == null ? participants.size() : pageSize;
        page = page == null ? 1 : page;
        int firstIndex = (page - 1) * pageSize;
        int lastIndex = Math.min(firstIndex + pageSize, participants.size());
        return firstIndex < lastIndex ? participants.subList(firstIndex, lastIndex) : List.of();
    }

    @Override
    public Participant getParticipant(Long id) {
        return participants.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public Participant save(Participant participant) {
        if (participant.getId() == null) {
            Long nextId = participants.isEmpty() ? 1L : participants.get(participants.size() - 1).getId() + 1;
            participant.setId(nextId);
        }
        participants.add(participant);
        return participant;
    }

    @Override
    public Page<Participant> getParticipant(Pageable pageRequest) {
        int pageSize = pageRequest.isPaged() ? pageRequest.getPageSize() : participants.size();
        int page = pageRequest.isPaged() ? pageRequest.getPageNumber() : 0;
        int firstIndex = page * pageSize;
        int lastIndex = Math.min(firstIndex + pageSize, participants.size());
        List<Participant> subList = firstIndex < lastIndex ? participants.subList(firstIndex, lastIndex) : List.of();
        return new PageImpl<>(subList, pageRequest, participants.size());
    }
}