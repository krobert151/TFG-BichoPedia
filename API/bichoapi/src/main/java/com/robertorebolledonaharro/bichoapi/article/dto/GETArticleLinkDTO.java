package com.robertorebolledonaharro.bichoapi.article.dto;

import com.robertorebolledonaharro.bichoapi.article.model.Article;
import lombok.Builder;

@Builder
public record GETArticleLinkDTO (

        String id,

        String name

){


}
