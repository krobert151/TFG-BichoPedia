package com.robertorebolledonaharro.bichoapi.article.dto;

import java.util.List;

public record POSTArticleDTO (

        String title,

        String text,

        List<String> medias,

        String userId,

        String specieId,

        String type


){
}
