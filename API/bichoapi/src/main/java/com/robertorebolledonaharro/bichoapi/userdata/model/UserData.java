package com.robertorebolledonaharro.bichoapi.userdata.model;

import com.robertorebolledonaharro.bichoapi.article.model.Article;
import com.robertorebolledonaharro.bichoapi.encounters.model.Encounter;
import com.robertorebolledonaharro.bichoapi.level.model.Level;
import com.robertorebolledonaharro.bichoapi.savedlist.model.SavedList;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Builder
public class UserData {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator",
            parameters = {
                    @Parameter(
                            name = "uuid_gen_strategy_class",
                            value = "org.hibernate.id.uuid.CustomVersionOneStrategy"
                    )
            }
    )
    @Column(columnDefinition = "uuid")
    private UUID id;

    private String currentLocation;

    private String userId;



    @OneToMany(mappedBy = "userData", orphanRemoval = true)
    private List<Encounter> encounters = new ArrayList<>();

    @OneToMany(mappedBy = "userData", orphanRemoval = true)
    private List<Encounter> articles = new ArrayList<>();

    private String profilePhoto;

    private int exp;

    @ToString.Exclude
    @OneToMany(orphanRemoval = true)
    private List<SavedList> savedLists = new ArrayList<>();

}
