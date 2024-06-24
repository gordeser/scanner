package org.gordeser.scanner.dao.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageDTO {
    @NotNull(message = "required")
    private String path;
    @NotNull(message = "required")
    private String hash;
}
