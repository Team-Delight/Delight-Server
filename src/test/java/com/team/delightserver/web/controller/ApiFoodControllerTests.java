package com.team.delightserver.web.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.team.delightserver.security.CustomOAuth2UserService;
import com.team.delightserver.security.handler.CustomAuthenticationFailureHandler;
import com.team.delightserver.security.handler.CustomAuthenticationSuccessHandler;
import com.team.delightserver.security.jwt.filter.JWTAuthenticationFilter;
import com.team.delightserver.service.ApiFoodService;
import com.team.delightserver.web.domain.category.Category;
import com.team.delightserver.web.domain.food.Food;
import com.team.delightserver.web.dto.response.RandomFoodsResponse;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

/**
 * @Created by Bloo
 * @Date: 2021/08/06
 */

@Disabled
@AutoConfigureMockMvc
@WebMvcTest(ApiMLRecommendationController.class)
class ApiFoodControllerTests {

    @MockBean
    private ApiFoodService apiFoodService;

    @MockBean
    private CustomOAuth2UserService customOAuth2UserService;

    @MockBean
    private JWTAuthenticationFilter jwtAuthenticationFilter;

    @MockBean
    private CustomAuthenticationSuccessHandler successHandler;

    @MockBean
    private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp ( WebApplicationContext webApplicationContext) {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
            .addFilter(new CharacterEncodingFilter("UTF-8"))
            .alwaysDo(print())
            .build();
    }

    @Disabled
    @DisplayName ("랜덤으로 20개 음식을 가져온다")
    @Test
    void findRandomFoods () throws Exception {
        // given
        Category category = new Category("한식");
            
        Food food_01 = Food.builder()
            .name("라면")
            .imgUrl("www.naver.com")
            .introduce("라면은 맛있다.")
            .category(category)
            .build();

        Food food_02 = Food.builder()
            .name("신라면")
            .imgUrl("www.naver.com")
            .introduce("라면은 맛있다.")
            .category(category)
            .build();

        RandomFoodsResponse randomFoodsResponse = RandomFoodsResponse.of(food_01);
        RandomFoodsResponse randomFoodsResponse2 = RandomFoodsResponse.of(food_02);

        List<RandomFoodsResponse> randomFoodsResponses = new ArrayList<>();
        randomFoodsResponses.add(randomFoodsResponse);
        randomFoodsResponses.add(randomFoodsResponse2);

        // when
        when(apiFoodService.findRandomFoodsForSurvey())
            .thenReturn(randomFoodsResponses);

        // then
        mockMvc.perform(get("/api/recommendations"))
            .andExpect(status().isOk());

    }
}