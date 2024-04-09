package com.robertorebolledonaharro.bichoapi.article.service;

import com.robertorebolledonaharro.bichoapi.article.dto.GETArticleDetailsDTO;
import com.robertorebolledonaharro.bichoapi.article.dto.GETArticleLinkDTO;
import com.robertorebolledonaharro.bichoapi.common.error.exeptions.ArticleNotFoundException;
import com.robertorebolledonaharro.bichoapi.article.model.Article;
import com.robertorebolledonaharro.bichoapi.article.repo.ArticleRepository;
import com.robertorebolledonaharro.bichoapi.common.service.CommonService;
import com.robertorebolledonaharro.bichoapi.specie.model.Specie;
import com.robertorebolledonaharro.bichoapi.specie.service.SpecieService;
import com.robertorebolledonaharro.bichoapi.common.error.exeptions.user.model.User;
import com.robertorebolledonaharro.bichoapi.common.error.exeptions.user.service.UserService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final UserService userService;
    private final CommonService service;

    private final SpecieService specieService;


    public ArticleService (@Lazy ArticleRepository articleRepository, @Lazy UserService userService, @Lazy CommonService commonService,@Lazy SpecieService specieService){
        this.articleRepository=articleRepository;
        this.userService=userService;
        this.service=commonService;
        this.specieService=specieService;

    }

    public Article getArticleFromStringId(String id){
        UUID uuid = service.stringToUUID(id);

        Optional<Article> articleOptional = articleRepository.findById(uuid);
        if(articleOptional.isEmpty()){
            throw  new ArticleNotFoundException("No article with the id:"+id+" was found.");
        }
        return articleOptional.get();
    }



    public GETArticleDetailsDTO findArticleDTO(String id){

        Article article = getArticleFromStringId(id);

        User user = userService.findUserByUsername(article.getUserData().getUserId());

        return getArticleDetailsDTOFromArticle(article);

    }

    public GETArticleDetailsDTO approvedArticle(String id){

        Article article = getArticleFromStringId(id);

        article.setApproved(true);

        articleRepository.save(article);

        return getArticleDetailsDTOFromArticle(article);


    }

    public GETArticleDetailsDTO denyArticle(String id){

        Article article = getArticleFromStringId(id);

        article.setApproved(false);

        articleRepository.save(article);

        return getArticleDetailsDTOFromArticle(article);


    }

    public GETArticleDetailsDTO getArticleDetailsDTOFromArticle(Article article){

        User user = userService.findUserByUsername(article.getUserData().getUserId());

        return GETArticleDetailsDTO.builder()
                .id(article.getId().toString())
                .type(article.getTypeOfArticle().toString())
                .approved(article.isApproved())
                .text(article.getText())
                .title(article.getTitle())
                .archives(article.getMedias())
                .createdBy(user.getUsername())
                .build();

    }
    @Transactional
    public GETArticleLinkDTO ofGetArticleLinkDTO(Article a){

        Specie s = specieService.findSpecieFromArticleId(a.getId());

        return GETArticleLinkDTO.builder()
                .id(a.getId().toString())
                .name(s.getScientificName()+":"+a.getTypeOfArticle().name()+"_"+a.getTitle())
                .build();

    }

    public boolean deleteArticle(Article article){
        articleRepository.delete(article);
        return true;
    }


}
