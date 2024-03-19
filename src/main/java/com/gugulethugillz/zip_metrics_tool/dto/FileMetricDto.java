package com.gugulethugillz.zip_metrics_tool.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileMetricDto {
    private long id;
    private String fileName;
    private long size;
    private long wordCount;
    private long medianWordSize;
    private long lineCount;
    private String created;
    private String lastModified;
}
