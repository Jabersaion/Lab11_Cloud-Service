package se331.lab.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import se331.lab.util.SupabaseStorageService;
import se331.lab.rest.util.StorageFileDto;

@Controller
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:5173","http://127.0.0.1:5173","http://localhost:5174","http://127.0.0.1:5174","https://hoppscotch.io","https://app.apidog.com"}, allowedHeaders = "*")
public class SupabaseController {
    final SupabaseStorageService supabaseStorageService;

    @PostMapping("/uploadFile")
    public ResponseEntity<?> uploadFile(@RequestParam(value = "file", required = false) MultipartFile file,
                                        @RequestParam(value = "image", required = false) MultipartFile image) {
        try {
            MultipartFile part = file != null ? file : image;
            if (part == null) {
                return ResponseEntity.badRequest().body("Required part 'file' or 'image' is not present.");
            }
            String fileUrl = supabaseStorageService.uploadFile(part);
            return ResponseEntity.ok(StorageFileDto.builder().name(fileUrl).build());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error uploading file: " + e.getMessage());
        }
    }

    @PostMapping("/uploadImage")
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file) {
        try {
            StorageFileDto fileUrl = supabaseStorageService.uploadImage(file);
            return ResponseEntity.ok(fileUrl);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error uploading file: " + e.getMessage());
        }
    }
}
