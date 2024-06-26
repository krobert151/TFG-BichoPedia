package com.robertorebolledonaharro.bichoapi.specie.dto;

import com.robertorebolledonaharro.bichoapi.article.dto.GETArticleSimpleDTO;
import lombok.Builder;

import java.util.List;

@Builder
public record SpecieArticlesDTO(

        String id,

        String scientificName,

        List<GETArticleSimpleDTO> articles


) {


}
