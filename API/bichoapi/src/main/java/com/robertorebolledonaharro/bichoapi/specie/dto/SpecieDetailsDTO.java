package com.robertorebolledonaharro.bichoapi.specie.dto;

import com.robertorebolledonaharro.bichoapi.article.dto.ArticleDTO;
import lombok.Builder;

import java.util.List;
@Builder
public record SpecieDetailsDTO(
        String scientificName,
        String danger,
        String mainPhoto,
        List<ArticleDTO> info,
        List<ArticleDTO> identification,
        List<ArticleDTO> cares



        ) {


}
