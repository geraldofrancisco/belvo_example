package com.one.example.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BelvoInstitutionListResponse {
    private Long count;
    private boolean hasNext;
    private List<BelvoInstitutionResponse> results;
}
