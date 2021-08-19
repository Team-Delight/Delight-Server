package com.team.delightserver.service;


import com.team.delightserver.web.domain.mypick.MypickRepository;
import com.team.delightserver.web.dto.response.MypickResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @CreateBy: Min
 * @Date: 2021/08/18
 */

@Slf4j
@RequiredArgsConstructor
@Service
public class ApiMypickService {

    private final MypickRepository mypickRepository;

    @Transactional(readOnly = true)
    public List<MypickResponse> findMypickByUserId(Long id) {
        return mypickRepository.findMypickByUserId(id);
    }
}
