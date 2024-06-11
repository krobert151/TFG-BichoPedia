package com.robertorebolledonaharro.bichoapi.article.dto;

import java.util.List;

public record PUTArticleDTO(

        boolean approved,

        String text,

        List<String> medias,

        String userId,

        String specieId,

        String type


) {
}
