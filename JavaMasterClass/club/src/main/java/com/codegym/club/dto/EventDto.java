package com.codegym.club.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventDto {
    private Long id;
    private String name;
    private String type;
    private String photoUrl;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
}
