package cns.com.service;

import java.io.IOException;
import java.util.Base64;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cns.com.model.FileAttachment;
import cns.com.repository.FileAttachmentRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FileService {
	  private final FileAttachmentRepository fileRepository;

	

	    public FileAttachment saveFile(MultipartFile file) throws IOException {
	        FileAttachment fileAttachment = new FileAttachment();
	        fileAttachment.setName(file.getOriginalFilename());
	        fileAttachment.setType(file.getContentType());
	        fileAttachment.setSize(file.getSize());

	        // Store the raw file data as byte[]
	        fileAttachment.setData(file.getBytes());


	        return fileRepository.save(fileAttachment);
	    }
	    // Method to retrieve file by ID
	    public Optional<FileAttachment> getFileById(Long id) {
	        return fileRepository.findById(id);
	    }
	    

}
