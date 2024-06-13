package com.robertorebolledonaharro.bichoapi.article.controller;


import com.robertorebolledonaharro.bichoapi.article.dto.GETArticleDetailsDTO;
import com.robertorebolledonaharro.bichoapi.article.dto.GETArticleSimpleDTO;
import com.robertorebolledonaharro.bichoapi.article.dto.POSTArticleDTO;
import com.robertorebolledonaharro.bichoapi.article.dto.PUTArticleDTO;
import com.robertorebolledonaharro.bichoapi.article.service.ArticleService;
import com.robertorebolledonaharro.bichoapi.specie.dto.SpecieArticlesDTO;
import com.robertorebolledonaharro.bichoapi.specie.service.SpecieService;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequiredArgsConstructor
@RequestMapping("/writer/articles")
public class ArticleControllerWriter {

    private final SpecieService specieService;
    private final ArticleService articleService;
    @Operation(summary = "Find all articles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of articles",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GETArticleSimpleDTO.class)))
            ),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/allArticles")
    public ResponseEntity<List<GETArticleSimpleDTO>> findAllByCriteria(
            @RequestParam(value = "search", required = false) String search,
            @RequestParam(value = "c", required = false, defaultValue = "100") int count,
            @RequestParam(value = "p", required = false, defaultValue = "0") int page
    ) {
        if (search == null) {
            return ResponseEntity.ok(specieService.findAllArticles(page, count));
        } else {
            return ResponseEntity.ok(specieService.findAllSpecieArticlesByAdvPredicate(search));
        }
    }

    @Operation(summary = "Create article")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Article created successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GETArticleSimpleDTO.class))
            ),
            @ApiResponse(responseCode = "400", description = "Invalid article data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/create")
    public ResponseEntity<GETArticleSimpleDTO>createArticle(@RequestBody POSTArticleDTO postArticleDTO){
        return ResponseEntity.ok(articleService.createArticle(postArticleDTO));
    }

    @Operation(summary = "Edit article by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Article edited successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GETArticleSimpleDTO.class))
            ),
            @ApiResponse(responseCode = "400", description = "Invalid article data"),
            @ApiResponse(responseCode = "404", description = "Article not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/edit/{id}")
    public ResponseEntity<GETArticleSimpleDTO> editArticle(@RequestBody PUTArticleDTO articleDTO, @PathVariable String id ){
        return ResponseEntity.ok(articleService.edit(articleDTO,id));
    }

    @Operation(summary = "Change approval status of article by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Article approval status changed successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GETArticleSimpleDTO.class))
            ),
            @ApiResponse(responseCode = "404", description = "Article not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/changeApprovedArticle/{id}")
    public ResponseEntity<GETArticleSimpleDTO> approveArticle(@PathVariable String id) {
        return ResponseEntity.ok(articleService.changeAprroved(id));
    }

    @Operation(summary = "Find article details by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved article details",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GETArticleDetailsDTO.class))
            ),
            @ApiResponse(responseCode = "404", description = "Article not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/details/{id}")
    public ResponseEntity<GETArticleDetailsDTO> findArticlesDetails(@PathVariable String id) {
        return ResponseEntity.ok(articleService.findArticleDTO(id));
    }




}