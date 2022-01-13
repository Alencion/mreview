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

    private String filename;
    private String uuid;
    private String folderPath;

    public String getImagePath() {
        return URLEncoder.encode(folderPath + File.separator + uuid + "_" + filename, StandardCharsets.UTF_8);
    }
}

