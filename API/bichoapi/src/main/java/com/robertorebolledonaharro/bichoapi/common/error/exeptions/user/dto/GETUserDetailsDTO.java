package com.robertorebolledonaharro.bichoapi.common.error.exeptions.user.dto;

import com.robertorebolledonaharro.bichoapi.article.dto.GETArticleLinkDTO;
import com.robertorebolledonaharro.bichoapi.encounters.dto.GETEncounterLinkDTO;
import com.robertorebolledonaharro.bichoapi.savedlist.dto.GETSavedListLinkDTO;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;
@Builder
public record GETUserDetailsDTO(

        String id,

        String username,

        String email,

        String profilePhoto,

        List<String> roles,

        List<GETEncounterLinkDTO> encounters,

        List<GETArticleLinkDTO> articles,

        List<GETSavedListLinkDTO> savedLists,

        int level,

        int exp,

        int percentExp,

        boolean accountNonExpired,

        boolean accountNonLocked,

        boolean credentialsNonExpired,

        boolean enabled,

        String createdAt,

        String old,

        String passwordExpiredAt

) {
}
