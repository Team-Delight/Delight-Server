package com.team.delightserver.service;

import com.team.delightserver.exception.FoodNotFoundException;
import com.team.delightserver.security.oauth2.OAuth2UserProvider;
import com.team.delightserver.web.domain.food.FoodRepository;
import com.team.delightserver.web.domain.mypick.Mypick;
import com.team.delightserver.web.domain.mypick.MypickRepository;
import com.team.delightserver.web.dto.request.SaveMypickRequest;
import com.team.delightserver.web.dto.response.MypickResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @CreateBy: Doe, Min
 * @Date: 2021/08/18
 * @ModifiedDate: 2021/08/21
 */

@RequiredArgsConstructor
@Service
public class ApiMypickService {

    private final MypickRepository mypickRepository;
    private final FoodRepository foodRepository;

    public void saveMypick(OAuth2UserProvider oAuth2User, SaveMypickRequest saveMypickRequest) {
        foodRepository.findByName(saveMypickRequest.getFoodName()).ifPresentOrElse(
                (food) -> {
                    Mypick mypick = new Mypick(oAuth2User.toUser(), food);
                    mypickRepository.save(mypick);
                },
                () -> {
                    throw new FoodNotFoundException();
                }
        );
    }

    @Transactional(readOnly = true)
    public List<MypickResponse> findMypickByUserId(Long id) {
        return mypickRepository.findMypickByUserId(id);
    }
}
