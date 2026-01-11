package com.sesame.pds.entity;

import com.sesame.pds.entity.base.BaseEntity;
import com.sesame.pds.enums.UserReadingLevel;
import lombok.*;

import jakarta.persistence.*;
import java.util.List;
import static jakarta.persistence.CascadeType.*;

/**
 * @author KHAZRI OMAR
 * @since 10/11/2022
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user_reading_info", schema = "public")
public class UserReadingInfo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "reading_level", nullable = false)
    private UserReadingLevel readingLevel;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "userReadingInfo", fetch = FetchType.EAGER, cascade = {MERGE, PERSIST, DETACH, REFRESH})
    private List<UserBookCategory> userBookCategories;
}
