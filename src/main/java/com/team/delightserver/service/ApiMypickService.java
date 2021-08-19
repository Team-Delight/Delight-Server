package com.team.delightserver.service;

import com.team.delightserver.security.oauth2.OAuth2UserProvider;
import com.team.delightserver.web.domain.food.FoodRepository;
import com.team.delightserver.web.domain.mypick.Mypick;
import com.team.delightserver.web.domain.mypick.MypickRepository;
import com.team.delightserver.web.dto.response.MypickResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @CreateBy: Doe, Min
 * @Date: 2021/08/18
 * @ModifiedDate: 2021/08/19
 */

@RequiredArgsConstructor
@Service
public class ApiMypickService {

    private final MypickRepository mypickRepository;
    private final FoodRepository foodRepository;

    public void saveMypick(OAuth2UserProvider oAuth2User, Long foodId) {
        foodRepository.findById(foodId).ifPresentOrElse(
                (food) -> {
                    Mypick mypick = new Mypick(oAuth2User.toUser(), food);
                    mypickRepository.save(mypick);
                },
                // TODO : exception 따로 클래스를 만들어서 처리하기
                () -> {
                    throw new IllegalArgumentException("해당 foodId 에 맞는 food 가 존재하지 않습니다.");
                }
        );
    }

    @Transactional(readOnly = true)
    public List<MypickResponse> findMypickByUserId(Long id) {
        return mypickRepository.findMypickByUserId(id);
    }
}
