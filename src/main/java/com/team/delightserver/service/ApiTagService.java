package com.team.delightserver.service;

import com.team.delightserver.util.enumclass.TagType;
import com.team.delightserver.web.domain.tag.TagRepository;
import com.team.delightserver.web.dto.response.TagResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @CreateBy: Doe
 * @Date: 2021/08/13
 * @ModifiedDate: 2021/08/18
 */

@RequiredArgsConstructor
@Service
public class ApiTagService {

    private final TagRepository tagRepository;

    @Transactional(readOnly = true)
    public List<TagResponse> findAllTags() {
        return tagRepository.findAll()
                .stream()
                .map(TagResponse::of)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<TagResponse> findAllTagsByType(TagType tagType) {
        return tagRepository.findAllByType(tagType)
                .stream().map(TagResponse::of).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public TagResponse findMostFrequentTagByUserId(Long id, TagType tagType) {
        return tagRepository.findMostFrequentTagByUserId(id, tagType)
                .map(TagResponse::of).orElse(null);
    }
}
