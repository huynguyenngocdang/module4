package com.codegym.clubv2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClubDto {
    private Long id;
    private String title;
    private String content;
    private byte[] clubImage;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
}
