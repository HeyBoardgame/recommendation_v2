package com.yeoboge.recommendation.application.personal;

import com.yeoboge.recommendation.application.personal.annotation.Recommender;
import com.yeoboge.recommendation.application.personal.dto.PersonalRecommendationDto;
import com.yeoboge.recommendation.application.personal.dto.RecommendationContextDto;
import com.yeoboge.recommendation.application.personal.recommender.AIRecommendService;
import com.yeoboge.recommendation.application.personal.recommender.RecommendService;
import com.yeoboge.recommendation.core.boardgame.Genre;
import com.yeoboge.recommendation.core.boardgame.dto.BoardGameThumbnailDto;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

@Recommender
@RequiredArgsConstructor
public class AsyncRecommendService {
    private final List<RecommendService> recommenders;
    private final ExecutorService executorService = Executors.newCachedThreadPool();

    public PersonalRecommendationDto getAsyncPersonalRecommendation(long userId, List<Genre> favoriteGenres) {
        PersonalRecommendationDto recommendation = new PersonalRecommendationDto(new ConcurrentHashMap<>());
        List<CompletableFuture<Void>> futures = recommenders.stream()
                .flatMap(service -> {
                    if (service instanceof AIRecommendService) {
                        return processAIRecommendation(service, recommendation, favoriteGenres, userId).stream();
                    } else {
                        return Stream.of(processNonAIRecommendation(service, recommendation, userId));
                    }
                }).toList();
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

        return recommendation;
    }

    private List<CompletableFuture<Void>> processAIRecommendation(
            RecommendService service, PersonalRecommendationDto recommendation, List<Genre> favoriteGenres, long userId
    ) {
        return favoriteGenres.stream().map(fg -> {
            RecommendationContextDto context = new RecommendationContextDto(userId, fg.getCode());
            return CompletableFuture.supplyAsync(() -> service.getRecommendations(context), executorService)
                    .thenAccept(recommended -> {
                        if (recommended.isEmpty()) return;
                        recommendation.boardGames().put(service.getCategory(context), recommended);
                    });
        }).toList();
    }

    private CompletableFuture<Void> processNonAIRecommendation(
            RecommendService service, PersonalRecommendationDto recommendation, long userId
    ) {
        return CompletableFuture.runAsync(() -> {
            RecommendationContextDto context = new RecommendationContextDto(userId, null);
            List<BoardGameThumbnailDto> recommended = service.getRecommendations(context);
            if (recommended.isEmpty()) return;
            recommendation.boardGames().put(service.getCategory(context), recommended);
        }, executorService);
    }

    @PreDestroy
    public void shutDownExecutorService() {
        executorService.shutdown();
    }
}
