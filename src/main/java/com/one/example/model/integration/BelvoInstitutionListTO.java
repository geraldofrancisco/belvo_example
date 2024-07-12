package com.one.example.model.integration;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BelvoInstitutionListTO {
    private Long count;
    @JsonProperty("next")
    private String urlNext;
    private List<BelvoInstitutionTO> results;
}
