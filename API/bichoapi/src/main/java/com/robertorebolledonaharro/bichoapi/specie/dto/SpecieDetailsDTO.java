package com.robertorebolledonaharro.bichoapi.specie.dto;

import com.robertorebolledonaharro.bichoapi.article.dto.ArticleDTO;
import com.robertorebolledonaharro.bichoapi.article.model.Article;
import lombok.Builder;

import java.util.List;
@Builder
public record SpecieDetailsDTO(
        String ScientificName,
        String danger,
        String mainPhoto,
        List<ArticleDTO> info,
        List<ArticleDTO> identification,
        List<ArticleDTO> cares



        ) {


}
