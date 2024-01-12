package com.codegym.clubv3.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClubDto {
    private Long id;
    @NotBlank(message = "You can't leave club Title blank")
    private String title;
    @NotBlank(message = "You can't leave club content blank")
    private String content;
    @NotBlank(message = "You can't leave club photoUrl blank")
    private String photoUrl;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
    private List<EventDto> eventsDto;
}
