package com.team.delightserver.web.domain.tag;

import com.team.delightserver.util.enumclass.TagType;

import java.util.Optional;

/**
 * @CreateBy: Doe
 * @Date: 2021/08/18
 */

public interface TagRepositoryCustom {

    Optional<Tag> findMostFrequentTagByUserId(Long userId, TagType tagType);
}