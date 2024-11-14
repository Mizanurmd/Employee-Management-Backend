package cns.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cns.com.model.FileAttachment;

@Repository
public interface FileAttachmentRepository extends JpaRepository<FileAttachment, Long> {

}
