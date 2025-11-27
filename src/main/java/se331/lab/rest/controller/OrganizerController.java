package se331.lab.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import se331.lab.entity.Organizer;
import se331.lab.service.OrganizerService;
import se331.lab.util.LabMapper;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrganizerController {
    final OrganizerService organizerService;

    @GetMapping("organizer")
    public ResponseEntity<?> getOrganizers(@RequestParam(value = "_limit", required = false) Integer perPage,
                                           @RequestParam(value = "_page", required = false) Integer page){
        List<Organizer> output = null;
        Integer total = organizerService.getOrganizerSize();
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-total-count", String.valueOf(total));
        try {
            output = organizerService.getOrganizers(perPage, page);
            return new ResponseEntity<>(output, headers, HttpStatus.OK);
        } catch (IndexOutOfBoundsException ex) {
            return new ResponseEntity<>(output, headers, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("organizer/{id}")
    public ResponseEntity<?> getOrganizer(@PathVariable("id") Long id) {
        Organizer output = organizerService.getOrganizer(id);
        if (output != null){
            return ResponseEntity.ok(output);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The given id is not found");
        }
    }
    @PostMapping("organizer")
    public ResponseEntity<?> addOrganizer(@RequestBody Organizer organizer) {
        Organizer saved = organizerService.save(organizer);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/organizers")
    public ResponseEntity<?> getOrganizersAll(){
        return ResponseEntity.ok(LabMapper.INSTANCE.getOrganizerDTO(organizerService.getAllOrganizer()));
    }
}
