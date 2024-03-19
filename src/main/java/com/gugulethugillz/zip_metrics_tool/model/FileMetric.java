package com.gugulethugillz.zip_metrics_tool.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "file_metric")
public class FileMetric {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    private String fileName;
    private long size;
    private long wordCount;
    private long medianWordSize;
    private long lineCount;
    private String created;
    private String lastModified;

}
