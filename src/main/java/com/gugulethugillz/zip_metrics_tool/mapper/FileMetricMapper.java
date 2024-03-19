package com.gugulethugillz.zip_metrics_tool.mapper;

import com.gugulethugillz.zip_metrics_tool.common.GenericMapper;
import com.gugulethugillz.zip_metrics_tool.dto.FileMetricDto;
import com.gugulethugillz.zip_metrics_tool.model.FileMetric;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface FileMetricMapper extends GenericMapper<FileMetric, FileMetricDto> {
    @Override
    @Mapping(target = "id", expression = "java(fileMetric.getId())")
    @Mapping(target = "fileName", expression = "java(fileMetric.getFileName())")
    @Mapping(target = "size", expression = "java(fileMetric.getSize())")
    @Mapping(target = "wordCount", expression = "java(fileMetric.getWordCount())")
    @Mapping(target = "medianWordSize", expression = "java(fileMetric.getMedianWordSize())")
    @Mapping(target = "lineCount", expression = "java(fileMetric.getLineCount())")
    @Mapping(target = "created", expression = "java(fileMetric.getCreated())")
    @Mapping(target = "lastModified", expression = "java(fileMetric.getLastModified())")

    FileMetricDto asDTO(FileMetric fileMetric);
    FileMetric asEntity(FileMetricDto dto);

    List<FileMetricDto> asDTOList(List<FileMetric> fileMetrics);

    List<FileMetric> asEntityList(List<FileMetricDto> dtoList);

    Set<FileMetricDto> asDTOSet(Set<FileMetric> dtoSet);
}
