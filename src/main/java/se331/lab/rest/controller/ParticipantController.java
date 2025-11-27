package se331.lab.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import se331.lab.service.ParticipantService;
import se331.lab.util.LabMapper;

@Controller
@CrossOrigin(origins = {"http://localhost:5173","http://127.0.0.1:5173"})
@RequiredArgsConstructor
public class ParticipantController {
    final ParticipantService participantService;

    @GetMapping("/participants")
    public ResponseEntity<?> getParticipants() {
        return ResponseEntity.ok(LabMapper.INSTANCE.getParticipantWithEventsDTO(participantService.getAllParticipants()));
    }
}