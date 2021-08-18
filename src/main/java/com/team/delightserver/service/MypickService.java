package com.team.delightserver.service;

import com.team.delightserver.security.oauth2.ProviderOAuth2User;
import com.team.delightserver.web.domain.food.FoodRepository;
import com.team.delightserver.web.domain.mypick.Mypick;
import com.team.delightserver.web.domain.mypick.MypickRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @CreateBy: Doe
 * @Date: 2021/08/18
 */

@RequiredArgsConstructor
@Service
public class MypickService {

    private final MypickRepository mypickRepository;
    private final FoodRepository foodRepository;

    public void saveMypick(ProviderOAuth2User oAuth2User, Long foodId){
        foodRepository.findById(foodId).ifPresentOrElse(
                (food) -> {
                    Mypick mypick = new Mypick(oAuth2User.toUser(), food);
                    mypickRepository.save(mypick);
                    },
                // TODO : exception 따로 클래스를 만들어서 처리하기
                () -> { throw new IllegalArgumentException("해당 foodId 에 맞는 food 가 존재하지 않습니다."); }
        );
    }
}
