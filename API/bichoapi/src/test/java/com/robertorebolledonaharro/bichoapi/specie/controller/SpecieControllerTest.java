package com.robertorebolledonaharro.bichoapi.specie.controller;

import com.robertorebolledonaharro.bichoapi.specie.dto.SpecieDTO;
import com.robertorebolledonaharro.bichoapi.specie.error.SpecieNotFoundException;
import com.robertorebolledonaharro.bichoapi.specie.model.Danger;
import com.robertorebolledonaharro.bichoapi.specie.service.SpecieService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class SpecieControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @InjectMocks
    private SpecieController specieController;

    @MockBean
    private SpecieService specieService;

    @Test
    @WithMockUser(username = "username", roles = {"USER","ADMIN"})
    void findAllWithoutCriteria() throws Exception {
        List<SpecieDTO> list = List.of(
                SpecieDTO.builder()
                        .id(UUID.fromString("8115de50-ee0c-44c2-a777-f5045faa4902"))
                        .url("gallipato.jpg")
                        .danger(Danger.CR.name())
                        .type("Amphibian")
                        .scientificName("Pleurodelest walts")
                        .build());

        when(specieService.findAll(0,10)).thenReturn(list);

        mockMvc.perform(MockMvcRequestBuilders.get("/species/allspecies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("8115de50-ee0c-44c2-a777-f5045faa4902"));
    }

    @Test
    @WithMockUser(username = "username", roles = {"USER","ADMIN"})
    void findAllByCriteria() throws Exception {
        List<SpecieDTO> list = List.of(
                SpecieDTO.builder()
                        .id(UUID.fromString("8115de50-ee0c-44c2-a777-f5045faa4902"))
                        .url("gallipato.jpg")
                        .danger(Danger.CR.name())
                        .type("Amphibian")
                        .scientificName("Pleurodelest walts")
                        .build());

        when(specieService.findAllByAdvPredicate("scientificName:*le* and ( type:Salamander or type:Bird )")).thenReturn(list);

        mockMvc.perform(MockMvcRequestBuilders.get("/species/allspecies?search=scientificName:*le* and ( type:Salamander or type:Bird )"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("8115de50-ee0c-44c2-a777-f5045faa4902"));
    }

    @Test
    @WithMockUser(username = "username", roles = {"USER","ADMIN"})
    void findAllNotFoundByCriteria() throws Exception {
        String errorMessage = "No Species was found with scientificName:*leooo* and ( type:Salamander or type:Bird )";

        when(specieService.findAllByAdvPredicate("scientificName:*leooo* and ( type:Salamander or type:Bird )"))
                .thenThrow(new SpecieNotFoundException(errorMessage));

        mockMvc.perform(MockMvcRequestBuilders.get("/species/allspecies?search=scientificName:*leooo* and ( type:Salamander or type:Bird )"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(errorMessage));
    }

    @Test
    @WithMockUser(username = "username", roles = {"USER","ADMIN"})
    void findAllNotFoundWithoutCriteria() throws Exception {
        String errorMessage = "No Species was found on page 2";

        when(specieService.findAll(2,10))
                .thenThrow(new SpecieNotFoundException(errorMessage));

        mockMvc.perform(MockMvcRequestBuilders.get("/species/allspecies?c=10&p=2"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(errorMessage));
    }


}