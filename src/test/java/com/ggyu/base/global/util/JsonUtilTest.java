package com.ggyu.base.global.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;


@Slf4j
class JsonUtilTest {
    @Test
    void convertTestDtoToJson() {
        Src src = Src.builder()
                .title("AAA")
                .cnt(123)
                .build();

        // log.info(JsonUtil.toJson(src));
        log.info(JsonUtil.GSON.toJson(src));
    }

    @AllArgsConstructor
    @Builder
    static class Src {
        private String title;
        private Integer cnt;
    }
}