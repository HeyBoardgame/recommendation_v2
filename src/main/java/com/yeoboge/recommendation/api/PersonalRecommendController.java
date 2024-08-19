package com.yeoboge.recommendation.api;

import com.yeoboge.recommendation.application.personal.PersonalRecommendService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/for-me")
@RequiredArgsConstructor
public class PersonalRecommendController {
    private final PersonalRecommendService service;

    @GetMapping("/{id}") // 실제 prod 환경에서는 JWT에서 사용자 인증을 거쳐 ID를 파싱해야 하지만, 해당 프로젝트에서는 생략..
    public ResponseEntity<?> getPersonalRecommendations(@PathVariable(name = "id") final long userId) {
        return ResponseEntity.ok(service.getPersonalRecommendation(userId));
    }
}
