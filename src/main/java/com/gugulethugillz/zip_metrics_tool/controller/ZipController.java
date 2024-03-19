package com.gugulethugillz.zip_metrics_tool.controller;

import com.gugulethugillz.zip_metrics_tool.service.ZipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.zip.ZipFile;

@CrossOrigin("*")
@Controller
@RequestMapping("/api/v1/zip")
public class ZipController {
    @Autowired
    ZipService service;

    @PostMapping("get-metrics")
    @ResponseStatus(HttpStatus.CREATED)
    public void getZipMetrics(@RequestParam("file") MultipartFile file) throws IOException {
        ZipFile z = new ZipFile(service.convertMultiPartToFile(file));
        service.getZipMetrics(z);

    }

}
