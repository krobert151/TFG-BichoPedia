package com.robertorebolledonaharro.bichoapi.level.dto;

import lombok.Builder;

@Builder
public record LevelDTO (

        int level,

        int percent

){
}
