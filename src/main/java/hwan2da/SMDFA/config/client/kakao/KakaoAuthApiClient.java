package hwan2da.SMDFA.config.client.kakao;

import hwan2da.SMDFA.config.client.kakao.dto.KakaoProfileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Component
@RequiredArgsConstructor
public class KakaoAuthApiClient {
    private final WebClient webClient;

    @Transactional
    public KakaoProfileResponse getProfileInfo(@RequestHeader("Authorization") String accessToken) {
        return webClient.get()
                .uri("https://kapi.kakao.com/v2/user/me")
                .header("Authorization", accessToken)
                .retrieve()
                .bodyToFlux(KakaoProfileResponse.class)
                .blockFirst();
    }
}
