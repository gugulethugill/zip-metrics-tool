package com.gugulethugillz.zip_metrics_tool.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
//@DataJpaTest  // Use for JPA repositories
public class FileMetricRepositoryTest {

    @Autowired
    private FileMetricRepository repository;

    @BeforeEach
    public void init() {

    }

//    @Test
//    public void saveFileMetric() {
//        FileMetric fileMetric = new FileMetric(1L,"file1.txt", 100, 50, 10,
//                5, "2022-03-01", "2022-03-02");
//        repository.save(fileMetric);
//        Assert.assertNotNull(repository.findByFileName("file1.txt"));
//    }

}
