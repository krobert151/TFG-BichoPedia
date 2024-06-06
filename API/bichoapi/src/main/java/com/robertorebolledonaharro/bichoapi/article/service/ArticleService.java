package com.robertorebolledonaharro.bichoapi.article.service;

import com.robertorebolledonaharro.bichoapi.article.dto.*;
import com.robertorebolledonaharro.bichoapi.article.model.TypeOfArticle;
import com.robertorebolledonaharro.bichoapi.common.error.exeptions.ArticleNotFoundException;
import com.robertorebolledonaharro.bichoapi.article.model.Article;
import com.robertorebolledonaharro.bichoapi.article.repo.ArticleRepository;
import com.robertorebolledonaharro.bichoapi.common.service.CommonService;
import com.robertorebolledonaharro.bichoapi.specie.model.Specie;
import com.robertorebolledonaharro.bichoapi.specie.service.SpecieService;
import com.robertorebolledonaharro.bichoapi.user.model.User;
import com.robertorebolledonaharro.bichoapi.user.model.UserData;
import com.robertorebolledonaharro.bichoapi.user.service.UserService;
import org.apache.logging.log4j.spi.CopyOnWrite;
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

    @Transactional
    public GETArticleSimpleDTO createArticle(POSTArticleDTO articleDTO){

        Specie specie = this.specieService.findSpecieById(articleDTO.specieId());
        UserData userData = this.userService.findUserDataFromUserId(articleDTO.userId());

        Article article = Article.builder()
                .title(articleDTO.title())
                .text(articleDTO.text())
                .medias(articleDTO.medias())
                .typeOfArticle(TypeOfArticle.valueOf(articleDTO.type()))
                .approved(false)
                .userData(userData)
                .build();

        specie.getArticles().add(article);
        specieService.save(specie);
        articleRepository.save(article);


        return GETArticleSimpleDTO.builder()
                .title(article.getTitle())
                .build();


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
        return getArticleDetailsDTOFromArticle(article);

    }

    @Transactional
    public GETArticleSimpleDTO edit(PUTArticleDTO articleDTO, String id){

        Article article = getArticleFromStringId(id);
        Specie oldSpecie = this.specieService.findSpecieById(articleDTO.specieId());
        Specie newSpecie = this.specieService.findSpecieById(articleDTO.specieId());

        UserData userData = this.userService.findUserDataFromUserId(articleDTO.userId());

        article.setTypeOfArticle(TypeOfArticle.valueOf(articleDTO.type()));
        article.setText(articleDTO.text());
        article.setApproved(articleDTO.approved());
        article.setMedias(articleDTO.medias());
        article.setUserData(userData);

        oldSpecie.getArticles().remove(article);
        newSpecie.getArticles().add(article);
        specieService.save(oldSpecie);
        specieService.save(newSpecie);
        articleRepository.save(article);
        return GETArticleSimpleDTO.builder()
                .title(article.getTitle())
                .build();

    }

    public GETArticleSimpleDTO changeAprroved(String id){

        Article article = getArticleFromStringId(id);

        article.setApproved(!article.isApproved());

        articleRepository.save(article);

        return GETArticleSimpleDTO.builder()
                .title(article.getTitle())
                .approved(article.isApproved())
                .id(article.getId().toString())
                .type(article.getTypeOfArticle().name())
                .build();
    }

    public GETArticleDetailsDTO getArticleDetailsDTOFromArticle(Article article){

        User user = userService.findUserById(service.stringToUUID(article.getUserData().getUserId()));

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
