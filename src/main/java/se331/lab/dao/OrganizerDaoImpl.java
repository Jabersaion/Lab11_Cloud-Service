package se331.lab.dao;

import jakarta.annotation.PostConstruct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.context.annotation.Profile;
import se331.lab.entity.Organizer;

import java.util.ArrayList;
import java.util.List;

@Repository
@Profile("manual")
public class OrganizerDaoImpl implements OrganizerDao {
    List<Organizer> organizers;

    @PostConstruct
    public void init(){
        organizers = new ArrayList<>();
        organizers.add(Organizer.builder().id(1L).name("City Runners Club").build());
        organizers.add(Organizer.builder().id(2L).name("Chef Collective").build());
        organizers.add(Organizer.builder().id(3L).name("Readers Circle").build());
        organizers.add(Organizer.builder().id(4L).name("Kat Laydee Foundation").build());
        organizers.add(Organizer.builder().id(5L).name("Fern Pollin Gardeners").build());
    }

    @Override
    public Integer getOrganizerSize() {
        return organizers.size();
    }

    @Override
    public List<Organizer> getOrganizers(Integer pageSize, Integer page) {
        pageSize = pageSize == null ? organizers.size() : pageSize;
        page = page == null ? 1 : page;
        int firstIndex = (page - 1) * pageSize;
        return organizers.subList(firstIndex, firstIndex + pageSize);
    }

    @Override
    public Organizer getOrganizer(Long id) {
        return organizers.stream().filter(o -> o.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public Organizer save(Organizer organizer) {
        if (organizer.getId() == null) {
            Long nextId = organizers.isEmpty() ? 1L : organizers.get(organizers.size() - 1).getId() + 1;
            organizer.setId(nextId);
        }
        organizers.add(organizer);
        return organizer;
    }

    @Override
    public Page<Organizer> getOrganizer(Pageable pageRequest) {
        int pageSize = pageRequest.isPaged() ? pageRequest.getPageSize() : organizers.size();
        int page = pageRequest.isPaged() ? pageRequest.getPageNumber() : 0;
        int firstIndex = page * pageSize;
        int lastIndex = Math.min(firstIndex + pageSize, organizers.size());
        List<Organizer> subList = organizers.subList(firstIndex, lastIndex);
        return new PageImpl<>(subList, pageRequest, organizers.size());
    }
}
