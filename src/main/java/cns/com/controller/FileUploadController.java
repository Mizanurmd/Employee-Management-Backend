package cns.com.controller;

import java.io.IOException;
import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cns.com.model.FileAttachment;
import cns.com.service.FileService;

@RestController
@RequestMapping("/api/files")
public class FileUploadController {
	private final FileService fileService;

	public FileUploadController(FileService fileService) {
		this.fileService = fileService;
	}

	@PostMapping("/upload")
	public ResponseEntity<FileAttachment> uploadFile(@RequestParam("file") MultipartFile file) {
		try {
			FileAttachment savedFile = fileService.saveFile(file);
			return new ResponseEntity<>(savedFile, HttpStatus.CREATED);
		} catch (IOException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	 // GET method to retrieve a file by ID
    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable Long id) {
        Optional<FileAttachment> fileAttachment = fileService.getFileById(id);
        if (fileAttachment.isPresent()) {
            FileAttachment file = fileAttachment.get();
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                    .header(HttpHeaders.CONTENT_TYPE, file.getType())
                    .header(HttpHeaders.CONTENT_LENGTH, String.valueOf(file.getSize()))
                    .body(file.getData()); // Return the file data as byte[]
        } else {
            return ResponseEntity.notFound().build(); // Return 404 if file not found
        }
    }
	

}
