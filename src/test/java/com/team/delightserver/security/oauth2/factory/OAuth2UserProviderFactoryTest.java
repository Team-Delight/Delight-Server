package com.team.delightserver.security.oauth2.factory;

import com.team.delightserver.security.oauth2.GoogleOAuth2UserProvider;
import com.team.delightserver.security.oauth2.KakaoOAuth2UserProvider;
import com.team.delightserver.security.oauth2.NaverOAuth2UserProvider;
import com.team.delightserver.security.oauth2.OAuth2UserProvider;
import com.team.delightserver.security.oauth2.exception.InvalidOAuth2AttributesException;
import com.team.delightserver.util.CustomRandomUtil;
import com.team.delightserver.util.enumclass.Social;
import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class OAuth2UserProviderFactoryTest {

    private static final String INVALID_ATTRIBUTES_NAME = "INVALID_NAME";
    private static final String INVALID_ATTRIBUTES_EMAIL = "INVALID_EMAIL";
    private static final String INVALID_ATTRIBUTES_KEY = "INVALID_KEY";
    private static final String INVALID_ATTRIBUTES_PICTURE = "INVALID_PICTURE";

    private static final String VALID_NAME = CustomRandomUtil.getRandomString();
    private static final String VALID_EMAIL = CustomRandomUtil.getRandomString();
    private static final String VALID_KEY = CustomRandomUtil.getRandomString();
    private static final String VALID_PICTURE = CustomRandomUtil.getRandomString();

    //Given
    private final Map<Social, Map<String, Object>> validSocialAttributes = new HashMap<>();
    private final Map<Social, Map<String, Object>> invalidSocialAttributes = new HashMap<>();
    private final Map<Social, Map<String, Object>> emptySocialAttributes = new HashMap<>();
    private final Map<Social, Map<String, Object>> nullSocialAttributes = new HashMap<>();

    @BeforeAll
    public void beforeAll() {
        //Naver
        Map<String, Object> validNaverOuterAttributes = new HashMap<>();
        Map<String, Object> validNaverInnerAttributes = new HashMap<>();
        validNaverInnerAttributes.put(NaverOAuth2UserProvider.ATTRIBUTES_NAME, VALID_NAME);
        validNaverInnerAttributes.put(NaverOAuth2UserProvider.ATTRIBUTES_EMAIL, VALID_EMAIL);
        validNaverInnerAttributes.put(NaverOAuth2UserProvider.ATTRIBUTES_KEY, VALID_KEY);
        validNaverInnerAttributes.put(NaverOAuth2UserProvider.ATTRIBUTES_PICTURE, VALID_PICTURE);
        validNaverOuterAttributes.put(NaverOAuth2UserProvider.ATTRIBUTES, validNaverInnerAttributes);
        validSocialAttributes.put(Social.NAVER, validNaverOuterAttributes);

        //Google
        Map<String, Object> validGoogleOuterAttributes = new HashMap<>();
        validGoogleOuterAttributes.put(GoogleOAuth2UserProvider.ATTRIBUTES_NAME, VALID_NAME);
        validGoogleOuterAttributes.put(GoogleOAuth2UserProvider.ATTRIBUTES_EMAIL, VALID_EMAIL);
        validGoogleOuterAttributes.put(GoogleOAuth2UserProvider.ATTRIBUTES_KEY, VALID_KEY);
        validGoogleOuterAttributes.put(GoogleOAuth2UserProvider.ATTRIBUTES_PICTURE, VALID_PICTURE);
        validSocialAttributes.put(Social.GOOGLE, validGoogleOuterAttributes);

        //KaKao
        Map<String, Object> validKakaoOuterAttributes = new HashMap<>();
        Map<String, Object> validKakaoInnerAttributes = new HashMap<>();
        validKakaoInnerAttributes.put(KakaoOAuth2UserProvider.ATTRIBUTES_NAME, VALID_NAME);
        validKakaoInnerAttributes.put(KakaoOAuth2UserProvider.ATTRIBUTES_EMAIL, VALID_EMAIL);
        validKakaoInnerAttributes.put(KakaoOAuth2UserProvider.ATTRIBUTES_PICTURE, VALID_PICTURE);
        validKakaoOuterAttributes.put(KakaoOAuth2UserProvider.ATTRIBUTES_KEY, VALID_KEY);
        validKakaoOuterAttributes.put(KakaoOAuth2UserProvider.ATTRIBUTES, validKakaoInnerAttributes);
        validSocialAttributes.put(Social.KAKAO, validKakaoOuterAttributes);

        //Invalid
        Map<String, Object> invalidOuterAttributes = new HashMap<>();
        invalidOuterAttributes.put(INVALID_ATTRIBUTES_NAME, VALID_NAME);
        invalidOuterAttributes.put(INVALID_ATTRIBUTES_EMAIL, VALID_EMAIL);
        invalidOuterAttributes.put(INVALID_ATTRIBUTES_PICTURE, VALID_PICTURE);
        invalidOuterAttributes.put(INVALID_ATTRIBUTES_KEY, VALID_KEY);
        Arrays.stream(Social.values()).forEach(
                social -> invalidSocialAttributes.put(social, invalidOuterAttributes)
        );

        //empty
        Arrays.stream(Social.values()).forEach(
                social -> emptySocialAttributes.put(social, null)
        );

        //null
        Arrays.stream(Social.values()).forEach(
                social -> nullSocialAttributes.put(social, null)
        );
    }

    @Nested
    class of {

        @Test
        void validAttributes() {
            //Given
            //When
            validSocialAttributes.forEach((social, attributes) -> {
                OAuth2UserProvider oAuth2UserProvider =
                        OAuth2UserProviderFactory.of(social.toString(), attributes);
                //Then
                Assertions.assertEquals(oAuth2UserProvider.getName(), VALID_NAME);
                Assertions.assertEquals(oAuth2UserProvider.getEmail(), VALID_EMAIL);
                Assertions.assertEquals(oAuth2UserProvider.getSocialProviderKey(), VALID_KEY);
                Assertions.assertEquals(oAuth2UserProvider.getProfileImg(), VALID_PICTURE);
            });
        }

        @Test
        void inValidAttributes() {
            //Given
            //When
            invalidSocialAttributes.forEach((social, attributes) -> {
                //Then
                Assertions.assertThrows(
                        InvalidOAuth2AttributesException.class,
                        () -> OAuth2UserProviderFactory.of(social.toString(), attributes)
                );
            });
        }

        @Test
        void emptyAttributes() {
            //Given
            //When
            emptySocialAttributes.forEach((social, attributes) -> {
                //Then
                Assertions.assertThrows(
                        InvalidOAuth2AttributesException.class,
                        () -> OAuth2UserProviderFactory.of(social.toString(), attributes)
                );
            });
        }

        @Test
        void NullAttributes() {
            //Given
            //When
            nullSocialAttributes.forEach((social, attributes) -> {
                //Then
                Assertions.assertThrows(
                        InvalidOAuth2AttributesException.class,
                        () -> OAuth2UserProviderFactory.of(social.toString(), attributes)
                );
            });
        }
    }
}