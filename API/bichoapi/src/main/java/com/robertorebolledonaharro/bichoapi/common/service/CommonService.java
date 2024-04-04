package com.robertorebolledonaharro.bichoapi.common.service;

import com.robertorebolledonaharro.bichoapi.common.errror.UUIDIlegalFormatException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CommonService {

    public UUID stringToUUID(String id){

        UUID uuid;

        try {
            uuid = UUID.fromString(id);
        }catch (IllegalArgumentException e){
            throw  new UUIDIlegalFormatException(id+" id is in the wrong format");
        };

        return uuid;
    }

}
