package net.flower.ixmsxms_server.domain;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by hyeyoungkang on 2014. 7. 16..
 */
public class UploadImageFile {

    private List<MultipartFile> files;

    public List<MultipartFile> getFiles() {
        return files;
    }

    public void setFiles(List<MultipartFile> files) {
        this.files = files;
    }
}
