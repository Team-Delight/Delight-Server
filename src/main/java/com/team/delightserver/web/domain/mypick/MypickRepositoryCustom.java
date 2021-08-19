package com.team.delightserver.web.domain.mypick;

import com.team.delightserver.web.dto.response.MypickResponse;

import java.util.List;

/**
 * @CreateBy: Min
 * @Date: 2021/08/18
 */

public interface MypickRepositoryCustom {

    List<MypickResponse> findMypickByUserId(Long userId);
}
