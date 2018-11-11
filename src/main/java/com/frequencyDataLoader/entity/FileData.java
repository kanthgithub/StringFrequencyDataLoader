package com.frequencyDataLoader.entity;

import lombok.*;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
@RedisHash("fileData")
public class FileData {

    @Id
    private Long id;
    private Long timestampInEpoch;
    private Long auditTime;
    private String content;

}
