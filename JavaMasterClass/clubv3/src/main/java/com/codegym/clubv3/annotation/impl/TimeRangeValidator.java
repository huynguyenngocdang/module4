package com.codegym.clubv3.annotation.impl;

import com.codegym.clubv3.annotation.ValidTimeRange;
import com.codegym.clubv3.dto.EventDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDateTime;

public class TimeRangeValidator implements ConstraintValidator<ValidTimeRange, EventDto> {
    @Override
    public boolean isValid(EventDto eventDto, ConstraintValidatorContext context) {
        if (eventDto.getStartTime() == null || eventDto.getEndTime() == null) {
            return true; // Let @NotNull handle null cases
        }
        return LocalDateTime.parse( eventDto.getEndTime()).isAfter(LocalDateTime.parse( eventDto.getStartTime()));
    }
}
