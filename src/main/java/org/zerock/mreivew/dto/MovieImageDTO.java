package org.zerock.mreivew.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieImageDTO {

    private String uuid;

    private String imgName;

    private String path;

    public String getImagePath() {
        return URLDecoder.decode(path + File.separator + uuid + "_" + imgName, StandardCharsets.UTF_8);
    }

    public String getThumbnailPath() {
        return URLDecoder.decode(path + File.separator + "s_" + uuid + "_" + imgName, StandardCharsets.UTF_8);
    }
}
