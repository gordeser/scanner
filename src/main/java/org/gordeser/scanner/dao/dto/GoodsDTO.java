package org.gordeser.scanner.dao.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsDTO {
    @NotNull(message = "required")
    private String name;
    private List<String> categories = new ArrayList<>();
}
