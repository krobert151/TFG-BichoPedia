package com.robertorebolledonaharro.bichoapi.article.service;

import com.robertorebolledonaharro.bichoapi.article.dto.ArticleDetailsDTO;
import com.robertorebolledonaharro.bichoapi.article.error.ArticleNotFoundException;
import com.robertorebolledonaharro.bichoapi.article.model.Article;
import com.robertorebolledonaharro.bichoapi.article.repo.ArticleRepository;
import com.robertorebolledonaharro.bichoapi.common.service.CommonService;
import com.robertorebolledonaharro.bichoapi.user.model.User;
import com.robertorebolledonaharro.bichoapi.user.service.UserService;
import com.robertorebolledonaharro.bichoapi.userdata.service.UserDataService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final UserDataService userService;
    private final CommonService service;

    public ArticleDetailsDTO findArticleDTO(String id){

        UUID uuid = service.stringToUUID(id);

        Optional<Article> articleOptional = articleRepository.findById(uuid);
        Article article;
        if(articleOptional.isEmpty()){
            throw  new ArticleNotFoundException("No article with the id:"+id+" was found.");
        }
        article = articleOptional.get();

        User user = userService.findUserByUserdataId(article.getUserData().getUserId());

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


}
