package com.robertorebolledonaharro.bichoapi.media.service;

import com.robertorebolledonaharro.bichoapi.media.model.Media;
import com.robertorebolledonaharro.bichoapi.media.repository.MediaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MediaService {


    private final MediaRepository repository;


    public Media save(Media media){
        return  repository.save(media);

    }



}
