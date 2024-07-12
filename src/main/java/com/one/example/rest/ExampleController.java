package com.one.example.rest;

import com.one.example.integration.BelvoIntegration;
import com.one.example.model.integration.BelvoInstitutionListTO;
import com.one.example.model.integration.BelvoInstitutionTO;
import com.one.example.model.response.BelvoInstitutionListResponse;
import com.one.example.model.response.BelvoInstitutionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Objects;

@RestController
@RequestMapping("/api/v1/example")
@RequiredArgsConstructor
public class ExampleController {
    private final BelvoIntegration belvoIntegration;

    @GetMapping
    public Mono<BelvoInstitutionListResponse> getAll() {
        return belvoIntegration.getAllInstitutions()
                .map(this::converterListResponse);
    }

    @GetMapping("/{id}")
    public Mono<BelvoInstitutionResponse> getById(
            @PathVariable Long id
    ) {
        return belvoIntegration.getInstitutionById(id)
                .map(this::converterResponse);
    }

    private BelvoInstitutionListResponse converterListResponse(BelvoInstitutionListTO response) {
        return BelvoInstitutionListResponse.builder()
                .count(response.getCount())
                .hasNext(Objects.nonNull(response.getUrlNext()))
                .results(response.getResults().stream().map(this::converterResponse).toList())
                .build();
    }

    private BelvoInstitutionResponse converterResponse(BelvoInstitutionTO response) {
        return BelvoInstitutionResponse.builder()
                .id(response.getId())
                .name(response.getName())
                .build();
    }
}
