package com.robertorebolledonaharro.bichoapi.article.controller;


import com.robertorebolledonaharro.bichoapi.specie.dto.SpecieArticlesDTO;
import com.robertorebolledonaharro.bichoapi.specie.dto.SpecieDTO;
import com.robertorebolledonaharro.bichoapi.specie.service.SpecieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/writer/articles")
public class ArticleControllerWriter {

    private final SpecieService specieService;


    @GetMapping("/allArticles")
    public ResponseEntity<List<SpecieArticlesDTO>> findAllByCriteria(
            @RequestParam(value = "search", required = false) String search,
            @RequestParam(value = "c", required = false, defaultValue = "10") int count,
            @RequestParam(value = "p", required = false, defaultValue = "0") int page
    ){
        if(search == null){
            return ResponseEntity.ok(specieService.findAllArticles(page,count));
        }else{
            return ResponseEntity.ok(specieService.findAllSpecieArticlesByAdvPredicate(search));

        }
    }


}
