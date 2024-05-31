package com.robertorebolledonaharro.bichoapi.article.controller;


import com.robertorebolledonaharro.bichoapi.article.dto.GETArticleDetailsDTO;
import com.robertorebolledonaharro.bichoapi.article.dto.GETArticleSimpleDTO;
import com.robertorebolledonaharro.bichoapi.article.service.ArticleService;
import com.robertorebolledonaharro.bichoapi.specie.dto.SpecieArticlesDTO;
import com.robertorebolledonaharro.bichoapi.specie.service.SpecieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/writer/articles")
public class ArticleControllerWriter {

    private final SpecieService specieService;
    private final ArticleService articleService;

    @GetMapping("/allArticles")
    public ResponseEntity<List<GETArticleSimpleDTO>> findAllByCriteria(
            @RequestParam(value = "search", required = false) String search,
            @RequestParam(value = "c", required = false, defaultValue = "10") int count,
            @RequestParam(value = "p", required = false, defaultValue = "0") int page
    ) {
        if (search == null) {
            return ResponseEntity.ok(specieService.findAllArticles(page, count));
        } else {
            return ResponseEntity.ok(specieService.findAllSpecieArticlesByAdvPredicate(search));

        }
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<GETArticleDetailsDTO> findArticlesDetails(@PathVariable String id) {

        return ResponseEntity.ok(articleService.findArticleDTO(id));

    }

    @PutMapping("/approve/{id}")
    public ResponseEntity<GETArticleDetailsDTO> approveArticle(@PathVariable String id) {

        return ResponseEntity.ok(articleService.approvedArticle(id));

    }

    @PutMapping("/deny/{id}")
    public ResponseEntity<GETArticleDetailsDTO> denyArticle(@PathVariable String id) {

        return ResponseEntity.ok(articleService.denyArticle(id));

    }

}