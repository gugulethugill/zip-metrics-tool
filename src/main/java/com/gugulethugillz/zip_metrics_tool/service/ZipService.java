package com.gugulethugillz.zip_metrics_tool.service;

import com.gugulethugillz.zip_metrics_tool.model.FileMetric;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public interface ZipService {
    void getZipMetrics(ZipFile zip) throws IOException;
    //void getZipMetric(File zip) throws IOException;
    //List<FileMetric> analyzeZip(ZipFile zipFile) throws IOException;
    FileMetric analyzeFile(ZipFile zipFile, ZipEntry entry) throws IOException;
    //int countWords(String content);
    File convertMultiPartToFile(MultipartFile file ) throws IOException;
    void generateExcelReport(List<FileMetric> metrics) throws IOException;
    int countLines(InputStream inputStream) throws IOException;
    int countWords(InputStream inputStream) throws IOException;
    long calculateMedianWordSize(InputStream inputStream) throws IOException;

}
