package com.gugulethugillz.zip_metrics_tool.service;

import com.gugulethugillz.zip_metrics_tool.model.FileMetric;
import com.gugulethugillz.zip_metrics_tool.repository.FileMetricRepository;
import com.gugulethugillz.zip_metrics_tool.service.impl.ZipServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class ZipServiceTest {

    @Mock
    private FileMetricRepository repository;
    @InjectMocks
    ZipServiceImpl zipService;

    @Test
    void generateExcelReport() throws IOException {
        // Mock data
        List<FileMetric> metrics = List.of(
                new FileMetric(1L,"file1.txt", 100, 50, 10, 5, "2022-03-01", "2022-03-02"),
                new FileMetric(2L,"file2.txt", 200, 100, 15, 8, "2022-03-03", "2022-03-04")
        );
        // Test
        zipService.generateExcelReport(metrics);
        // Verify that Excel file is generated
        assertTrue(Files.exists(Paths.get("Metrics.xlsx")));
    }

    @Test
    void convertMultiPartToFile() throws IOException {
        // Mock MultipartFile
        byte[] content = "test".getBytes();
        MockMultipartFile multipartFile = new MockMultipartFile("file", "test.zip", "application/zip", content);

        // Test
        File file = zipService.convertMultiPartToFile(multipartFile);

        // Verify
        assertEquals("test.zip", file.getName());
        assertEquals(content.length, Files.size(file.toPath()));
    }

    @Test
    public void testCountLines() throws IOException {
        // Test with empty stream
        ByteArrayInputStream emptyStream = new ByteArrayInputStream(new byte[0]);
        assertEquals(0, zipService.countLines(emptyStream));

        // Test with single line
        String content = "This is a test line";
        ByteArrayInputStream singleLineStream = new ByteArrayInputStream(content.getBytes());
        assertEquals(1, zipService.countLines(singleLineStream));

        // Test with multiple lines
        String multiLineContent = "Line 1\nLine 2\nLine 3";
        ByteArrayInputStream multiLineStream = new ByteArrayInputStream(multiLineContent.getBytes());
        assertEquals(3, zipService.countLines(multiLineStream));
    }

}
