package com.frequencyDataLoader.service;

import com.frequencyDataLoader.common.DateTimeUtil;
import com.frequencyDataLoader.entity.FileData;
import com.frequencyDataLoader.repository.FileDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.frequencyDataLoader.common.StringFrequencyUtil.getStringContentFromLogString;
import static com.frequencyDataLoader.common.StringFrequencyUtil.getTimeStampInEpochFromLogString;

@Service
public class FileDataProcessingService {

    @Autowired
    private FileDataRepository fileDataRepository;

    /**
     *
     * @param fileLines
     * @return  List<FileData>
     */
    public List<FileData> processFileData(List<String> fileLines){

        List<FileData> fileDataList = fileLines.stream().map(content -> getFileDataFromLine(content)).collect(Collectors.toList());

        //persist FileData
        Iterable<FileData> fileDataSaved = fileDataRepository.saveAll(fileDataList);

        return  fileDataSaved!=null ? fileDataList.stream().collect(Collectors.toList()) : null;
    }


    /**
     *
     * @param line
     * @return FileData
     */
    public FileData getFileDataFromLine(String line){

        Long timeStampLogInEpoch = getTimeStampInEpochFromLogString(line);

        String stringContent = getStringContentFromLogString(line);

        FileData fileData = new FileData();

        fileData.setContent(stringContent);
        fileData.setTimestampInEpoch(timeStampLogInEpoch);
        fileData.setAuditTime(DateTimeUtil.getCurrentTimeStampInEpochMillis());

        return fileData;
    }

}
