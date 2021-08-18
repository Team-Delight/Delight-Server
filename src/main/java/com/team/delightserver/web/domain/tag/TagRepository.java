package com.team.delightserver.web.domain.tag;

import com.team.delightserver.util.enumclass.TagType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @CreateBy: Doe
 * @Date: 2021/08/17
 * @ModifiedDate: 2021/08/18
 */

public interface TagRepository extends JpaRepository<Tag, Long>, TagRepositoryCustom{
    List<Tag> findAllByType(TagType tagType);
}
