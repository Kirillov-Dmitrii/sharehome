package com.skypro.sharehome.service;

import com.skypro.sharehome.entity.Animal;
import com.skypro.sharehome.entity.Avatar;
import com.skypro.sharehome.entity.Report;
import com.skypro.sharehome.repository.AvatarRepository;
import com.skypro.sharehome.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
@Transactional
public class AvatarService {

    @Value("${path.to.avatars.folder}")
    private String avatarsDir;

    private final ReportRepository reportRepository;
    private final AvatarRepository avatarRepository;

    public AvatarService(ReportRepository reportRepository, AvatarRepository avatarRepository) {
        this.reportRepository = reportRepository;
        this.avatarRepository = avatarRepository;
    }

public void uploadAvatar(Long id, MultipartFile file) throws IOException{
    Report report = reportRepository.getById(id);

    Path filePath = Path.of(avatarsDir, report + "." +
    getExtension(Objects.requireNonNull(file.getOriginalFilename())));
    Files.createDirectories(filePath.getParent());
    Files.deleteIfExists(filePath);

    try (InputStream is = file.getInputStream();
         OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
         BufferedInputStream bis = new BufferedInputStream(is, 1024);
         BufferedOutputStream bos = new BufferedOutputStream(os, 1024)
         ) {
         bis.transferTo(bos);
    }
    Avatar avatar = avatarRepository.findById(id).orElseGet(Avatar::new);
    avatar.setReport(report);
    avatar.setFilePath(filePath.toString());
    avatar.setFileSize(file.getSize());
    avatar.setMediaType(file.getContentType());
    avatar.setData(file.getBytes());
    avatarRepository.save(avatar);
    }
    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }


}

