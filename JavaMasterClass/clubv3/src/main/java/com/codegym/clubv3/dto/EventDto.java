package com.codegym.clubv3.dto;

import com.codegym.clubv3.annotation.ValidTimeRange;
import com.codegym.clubv3.entity.Club;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ValidTimeRange
public class EventDto {
    private Long id;
    @NotBlank(message = "You can't leave club Title blank")
    private String title;
    @NotBlank(message = "You can't leave club content blank")
    private String content;
    @NotBlank(message = "You can't leave club photoUrl blank")
    private String photoUrl;
    @NotNull
    private String startTime;
    @NotNull
    private String endTime;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
    private Club club;
}
