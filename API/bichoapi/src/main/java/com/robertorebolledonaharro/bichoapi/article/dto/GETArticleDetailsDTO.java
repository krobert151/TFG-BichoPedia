package com.robertorebolledonaharro.bichoapi.article.dto;

import lombok.Builder;

import java.util.List;
@Builder
public record GETArticleDetailsDTO(

        String id,

        String title,

        String text,

        boolean approved,

        List<String> archives,

        String specieId,

        String createdBy,

        String type


) {
}
