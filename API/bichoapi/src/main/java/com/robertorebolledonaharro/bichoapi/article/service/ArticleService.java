package com.robertorebolledonaharro.bichoapi.article.service;

import com.robertorebolledonaharro.bichoapi.article.dto.ArticleDetailsDTO;
import com.robertorebolledonaharro.bichoapi.article.error.ArticleNotFoundException;
import com.robertorebolledonaharro.bichoapi.article.model.Article;
import com.robertorebolledonaharro.bichoapi.article.repo.ArticleRepository;
import com.robertorebolledonaharro.bichoapi.common.service.CommonService;
import com.robertorebolledonaharro.bichoapi.user.model.User;
import com.robertorebolledonaharro.bichoapi.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final UserService userService;
    private final CommonService service;



    public Article getArticleFromStringId(String id){
        UUID uuid = service.stringToUUID(id);

        Optional<Article> articleOptional = articleRepository.findById(uuid);
        if(articleOptional.isEmpty()){
            throw  new ArticleNotFoundException("No article with the id:"+id+" was found.");
        }
        return articleOptional.get();
    }

    public ArticleDetailsDTO getArticleDetailsDTOFromArticle(Article article){

        User user = userService.findUserByUsername(article.getUserData().getUserId());

        return ArticleDetailsDTO.builder()
                .id(article.getId().toString())
                .type(article.getTypeOfArticle().toString())
                .approved(article.isApproved())
                .text(article.getText())
                .title(article.getTitle())
                .archives(article.getMedias())
                .createdBy(user.getUsername())
                .build();

    }

    public ArticleDetailsDTO findArticleDTO(String id){

        Article article = getArticleFromStringId(id);

        User user = userService.findUserByUsername(article.getUserData().getUserId());

        return getArticleDetailsDTOFromArticle(article);

    }

    public ArticleDetailsDTO approvedArticle(String id){

        Article article = getArticleFromStringId(id);

        article.setApproved(true);

        articleRepository.save(article);

        return getArticleDetailsDTOFromArticle(article);


    }

    public ArticleDetailsDTO denyArticle(String id){

        Article article = getArticleFromStringId(id);

        article.setApproved(false);

        articleRepository.save(article);

        return getArticleDetailsDTOFromArticle(article);


    }


}
