package com.one.example.model.integration;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BelvoInstitutionTO {
    private Long id;
    @JsonProperty("display_name")
    private String name;
}
