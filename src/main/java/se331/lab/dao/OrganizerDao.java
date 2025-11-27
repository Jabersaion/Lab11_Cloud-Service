package se331.lab.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import se331.lab.entity.Organizer;

import java.util.List;

public interface OrganizerDao {
    Integer getOrganizerSize();
    List<Organizer> getOrganizers(Integer pageSize, Integer page);
    Organizer getOrganizer(Long id);
    Organizer save(Organizer organizer);
    Page<Organizer> getOrganizer(Pageable pageRequest);
}
