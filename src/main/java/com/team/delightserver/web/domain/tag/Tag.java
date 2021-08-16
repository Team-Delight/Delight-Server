package com.team.delightserver.web.domain.tag;

import com.team.delightserver.util.enumclass.TagType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

/**
 * @CreateBy: Doe
 * @Date: 2021/08/10
 * @ModifiedDate : 2021/08/17
 */

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(nullable = false)
    private String name;

    @NonNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TagType type;
}
