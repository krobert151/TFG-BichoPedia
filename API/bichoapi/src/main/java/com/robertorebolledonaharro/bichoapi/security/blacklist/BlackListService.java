package com.robertorebolledonaharro.bichoapi.security.blacklist;

import com.robertorebolledonaharro.bichoapi.security.errorhandling.BlackListTokenException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class BlackListService  {

    private final BlackListRepository repository;

    public void isBlacklisted (String token) throws BlackListTokenException {

        if (repository.findById(token).isPresent())
            throw  new BlackListTokenException();

    }

    public void addToBlackList (String token){

        TokenBlackList tokenBlackList = new TokenBlackList(token);

        repository.save(tokenBlackList);

    }

    public List<TokenBlackList> findAll(){
        return repository.findAll();
    }

    public void deleteById(String token){
        repository.deleteById(token);
    }





}