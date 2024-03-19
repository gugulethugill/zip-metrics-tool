package com.gugulethugillz.zip_metrics_tool.repository;

import com.gugulethugillz.zip_metrics_tool.model.FileMetric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FileMetricRepository extends JpaRepository<FileMetric, Long> {
    Optional<FileMetric> findByFileName(String fileName);
    List<FileMetric> findAll();
}
