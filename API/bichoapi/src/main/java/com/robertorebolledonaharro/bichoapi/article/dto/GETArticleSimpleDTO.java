package com.robertorebolledonaharro.bichoapi.article.dto;

import com.robertorebolledonaharro.bichoapi.article.model.Article;
import lombok.Builder;

@Builder
public record GETArticleSimpleDTO(

        String id,

        String specieName,

        String title,

        boolean approved,

        String type


) {




}
