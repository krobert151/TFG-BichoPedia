package com.robertorebolledonaharro.bichoapi.article.dto;

import lombok.Builder;

@Builder
public record GETArticleSimpleDTO(

        String id,

        String articleName,

        boolean active,

        String type


) {
}
