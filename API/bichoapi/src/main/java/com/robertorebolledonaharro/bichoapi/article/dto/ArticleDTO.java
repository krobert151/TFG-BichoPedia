package com.robertorebolledonaharro.bichoapi.article.dto;

import lombok.Builder;

import java.util.List;
@Builder
public record ArticleDTO(

        String title,

        String description,

        List<String> archives


) {
}
