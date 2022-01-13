package org.zerock.mreivew.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.File;
import java.io.Serializable;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Data
@AllArgsConstructor
public class UploadResultDTO implements Serializable {

    private static final long serialVersionUID = -9065886505342363929L;

    private String fileName;
    private String uuid;
    private String folderPath;

    public String getImagePath() {
        return URLEncoder.encode(folderPath + File.separator + uuid + "_" + fileName, StandardCharsets.UTF_8);
    }

    public String getThumbnailPath() {
        return URLEncoder.encode(folderPath +File.separator + "s_" + uuid + "_" + fileName, StandardCharsets.UTF_8);
    }
}

