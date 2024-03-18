package com.robertorebolledonaharro.bichoapi.article.model;

import com.robertorebolledonaharro.bichoapi.userdata.model.UserData;
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
public class Article {

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
    private String title;

    @Column(length = 1000)
    private String text;

    private boolean approved;

    private List<String> medias = new ArrayList<>();

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "user_data_id")
    private UserData userData;

    @Enumerated
    @Column(name = "type")
    private TypeOfArticle typeOfArticle;

}
