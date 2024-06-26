package com.robertorebolledonaharro.bichoapi.article.dto;

import com.robertorebolledonaharro.bichoapi.article.model.Article;
import lombok.Builder;

import java.util.List;
@Builder
public record GETArticleDTO(

        String title,

        String description,


        List<String> archives


) {

}
