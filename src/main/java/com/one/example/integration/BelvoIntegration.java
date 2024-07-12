package com.one.example.integration;

import com.one.example.model.integration.BelvoInstitutionListTO;
import com.one.example.model.integration.BelvoInstitutionTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class BelvoIntegration {
    @Value("${belvo.username}")
    private String username;

    @Value("${belvo.password}")
    private String password;

    @Value("${belvo.baseUrl}")
    private String baseUrl;

    @Value("${belvo.institutions.getAll}")
    private String pathInstitutionsGetAll;

    @Value("${belvo.institutions.getById}")
    private String pathInstitutionsGetById;

    private final WebClient webClient;

    public Mono<BelvoInstitutionListTO> getAllInstitutions() {
        var uri = UriComponentsBuilder.fromUriString(baseUrl)
                .path(pathInstitutionsGetAll)
                .toUriString();

        return webClient.get()
                .uri(uri)
                .headers(httpHeaders -> httpHeaders.setBasicAuth(username, password))
                .retrieve()
                .bodyToMono(BelvoInstitutionListTO.class);
    }

    public Mono<BelvoInstitutionTO> getInstitutionById(Long id) {
        var uri = UriComponentsBuilder.fromUriString(baseUrl)
                .path(pathInstitutionsGetById)
                .buildAndExpand(id)
                .toUriString();

        return webClient.get()
                .uri(uri)
                .headers(httpHeaders -> httpHeaders.setBasicAuth(username, password))
                .retrieve()
                .bodyToMono(BelvoInstitutionTO.class);
    }
}
