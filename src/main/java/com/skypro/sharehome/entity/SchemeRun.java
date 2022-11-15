package com.skypro.sharehome.entity;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

/**
 * Класс для хранения схемы проезда к приюту
 */
@Entity
public class SchemeRun {
    @Id
    @GeneratedValue
    private Long id;
    private String filePath;
    private long fileSize;
    private String mediaType;

    @Lob
    private byte[] data;

    @OneToOne
    private ShareHome shareHome;

    public SchemeRun() {
    }

    public SchemeRun(Long id, String filePath, long fileSize, String mediaType, ShareHome shareHome) {
        this.id = id;
        this.filePath = filePath;
        this.fileSize = fileSize;
        this.mediaType = mediaType;
        this.shareHome = shareHome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SchemeRun schemeRun = (SchemeRun) o;
        return fileSize == schemeRun.fileSize && Objects.equals(id, schemeRun.id) && Objects.equals(filePath, schemeRun.filePath) && Objects.equals(mediaType, schemeRun.mediaType) && Arrays.equals(data, schemeRun.data) && Objects.equals(shareHome, schemeRun.shareHome);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, filePath, fileSize, mediaType, shareHome);
        result = 31 * result + Arrays.hashCode(data);
        return result;
    }

    @Override
    public String toString() {
        return "SchemeRun{" +
                "id=" + id +
                ", filePath='" + filePath + '\'' +
                ", fileSize=" + fileSize +
                ", mediaType='" + mediaType + '\'' +
                ", data=" + Arrays.toString(data) +
                ", shareHome=" + shareHome +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public ShareHome getShareHome() {
        return shareHome;
    }

    public void setShareHome(ShareHome shareHome) {
        this.shareHome = shareHome;
    }
}
