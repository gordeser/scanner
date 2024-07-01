package org.gordeser.scanner.dao.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDTO {
    @NotNull(message = "required")
    private Double rating;
    @NotNull(message = "required")
    private String title;
    private String description;
}
