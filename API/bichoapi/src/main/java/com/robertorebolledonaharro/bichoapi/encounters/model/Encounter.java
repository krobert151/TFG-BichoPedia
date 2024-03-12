package com.robertorebolledonaharro.bichoapi.encounters.model;

import com.robertorebolledonaharro.bichoapi.media.model.Media;
import com.robertorebolledonaharro.bichoapi.specie.model.Specie;
import com.robertorebolledonaharro.bichoapi.userdata.model.UserData;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import java.time.LocalDate;
import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
public class Encounter {

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

    @ToString.Exclude
    @ManyToOne(optional = false)
    @JoinColumn(name = "specie_id", nullable = false)
    private Specie specie;

    private String location;

    private LocalDate date;

    @Column(length = 1000)
    private String description;

    private int likes;

    @ToString.Exclude
    @ManyToOne()
    @JoinColumn(name = "user_data_id")
    private UserData userData;

    @ToString.Exclude
    @OneToMany(orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Media> medias = new ArrayList<>();

}
