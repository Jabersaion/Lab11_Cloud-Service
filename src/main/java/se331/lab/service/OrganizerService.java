package se331.lab.service;

import org.springframework.data.domain.Page;
import se331.lab.entity.Organizer;

import java.util.List;

public interface OrganizerService {
    Integer getOrganizerSize();
    List<Organizer> getOrganizers(Integer pageSize, Integer page);
    Organizer getOrganizer(Long id);
    Organizer save(Organizer organizer);
    List<Organizer> getAllOrganizer();
    Page<Organizer> getOrganizer(Integer page, Integer pageSize);
}
