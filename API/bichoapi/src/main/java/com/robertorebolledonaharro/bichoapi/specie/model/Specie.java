package com.robertorebolledonaharro.bichoapi.specie.model;

import com.robertorebolledonaharro.bichoapi.article.model.Article;
import com.robertorebolledonaharro.bichoapi.media.model.Media;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Specie {

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


    @Column(unique = true)
    private String scientificName;

    @Enumerated
    @Column(name = "danger")
    private Danger danger;

    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "specie_")
    private List<Article> articles = new ArrayList<>();

    @ToString.Exclude
    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "media_id")
    private Media media;


    private String type;

}
