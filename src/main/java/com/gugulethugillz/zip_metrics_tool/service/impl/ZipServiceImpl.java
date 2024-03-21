package com.gugulethugillz.zip_metrics_tool.service.impl;

import com.gugulethugillz.zip_metrics_tool.model.FileMetric;
import com.gugulethugillz.zip_metrics_tool.repository.FileMetricRepository;
import com.gugulethugillz.zip_metrics_tool.service.ZipService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

@Service
@Slf4j
@RequiredArgsConstructor
public class ZipServiceImpl implements ZipService{
    private final FileMetricRepository repository;


    public void getZipMetrics(ZipFile zip) throws IOException {
        List<FileMetric> metrics = analyzeZip(zip);
       generateExcelReport(metrics);
    }

    public List<FileMetric> analyzeZip(ZipFile zipFile) throws IOException {
        List<FileMetric> metrics = new ArrayList<>();
        try (zipFile) {
            for (ZipEntry entry : Collections.list(zipFile.entries())) {
                if (!entry.isDirectory()) {
                    FileMetric metric = analyzeFile(zipFile, entry);
                    metrics.add(metric);
                }
            }
        }
        return metrics;
    }

    public FileMetric analyzeFile(ZipFile zipFile, ZipEntry entry) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(zipFile.getInputStream(entry)));
        String line;
        while ((line = reader.readLine()) != null) {
            //System.out.println(line);
            String[] words = line.split("\\s+");
        }

        FileMetric metric = new FileMetric();
        metric.setFileName(entry.getName());
        metric.setSize(entry.getSize());
        metric.setLineCount(countLines(zipFile.getInputStream(entry)));
        metric.setWordCount(countWords(zipFile.getInputStream(entry)));
        metric.setMedianWordSize(calculateMedianWordSize(zipFile.getInputStream(entry)));
        metric.setCreated(entry.getCreationTime().toString());
        metric.setLastModified(entry.getLastModifiedTime().toString());
        repository.save(metric);

        return metric;
    }

    public void generateExcelReport(List<FileMetric> metrics) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Zip Metrics Report");

        int rowNum = 0;
        Row headerRow = sheet.createRow(rowNum++);

        // Create headers
        headerRow.createCell(0).setCellValue("File Name");
        headerRow.createCell(1).setCellValue("Size (bytes)");
        headerRow.createCell(2).setCellValue("Word Count");
        headerRow.createCell(3).setCellValue("Median Word Size");
        headerRow.createCell(4).setCellValue("Line Count");
        headerRow.createCell(6).setCellValue("Created");
        headerRow.createCell(7).setCellValue("Last Modified");

        // Add data for each file
        for (FileMetric metric : metrics) {
            Row dataRow = sheet.createRow(rowNum++);
            dataRow.createCell(0).setCellValue(metric.getFileName());
            dataRow.createCell(1).setCellValue(metric.getSize());
            dataRow.createCell(2).setCellValue(metric.getWordCount());
            dataRow.createCell(3).setCellValue(metric.getMedianWordSize());
            dataRow.createCell(4).setCellValue(metric.getLineCount());
            dataRow.createCell(6).setCellValue(metric.getCreated());
            dataRow.createCell(7).setCellValue(metric.getLastModified());
        }

        //Add summary row
        Row summaryRow = sheet.createRow(rowNum);
        summaryRow.createCell(0).setCellValue("Total Files");
        summaryRow.createCell(1).setCellValue(metrics.size());

        //Write the workbook to a temporary file
        File tempFile = File.createTempFile("Metrics", ".xlsx");
        try (FileOutputStream fileOut = new FileOutputStream(tempFile)) {
            workbook.write(fileOut);
        }

        // Write to Excel file
        try (FileOutputStream outputStream = new FileOutputStream("Metrics.xlsx")) {
            workbook.write(outputStream);
            workbook.close();
        }
        System.out.println("Metrics report generated in Metrics.xlsx");
    }

    public File convertMultiPartToFile(MultipartFile file ) throws IOException {
        File convFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        FileOutputStream fos = new FileOutputStream( convFile );
        fos.write( file.getBytes() );
        fos.close();
        return convFile;
    }
public int countLines(InputStream inputStream) throws IOException {
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
        int lines = 0;
        while (reader.readLine() != null) {
            lines++;
        }
        return lines;
    }
}

    public int countWords(InputStream inputStream) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            int wordCount = 0;
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+");
                wordCount += words.length;
            }
            return wordCount;
        }
    }

    public long calculateMedianWordSize(InputStream inputStream) throws IOException {
        List<Integer> wordSizes = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+");
                for (String word : words) {
                    wordSizes.add(word.length());
                }
            }
        }

        Collections.sort(wordSizes);
        int size = wordSizes.size();

        if (size == 0) {
            return 0; // No words in the file
        }

        if (size % 2 == 0) {
            return (long) ((wordSizes.get(size / 2 - 1) + wordSizes.get(size / 2)) / 2.0);
        } else {
            return wordSizes.get(size / 2);
        }
    }
}

