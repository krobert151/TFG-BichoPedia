package com.robertorebolledonaharro.bichoapi.article.dto;

import lombok.Builder;

import java.util.List;
@Builder
public record ArticleDetailsDTO(

        String id,

        String title,

        String text,

        boolean approved,

        List<String> archives,

        String createdBy,

        String type


) {
}
