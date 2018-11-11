package com.frequencyDataLoader.repository;

import com.frequencyDataLoader.entity.FileData;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface FileDataRepository {

    void save(FileData customer);
    FileData find(Long id);
    Map<Long, FileData> findAll();
    void update(FileData fileData);
    void delete(Long id);

}
