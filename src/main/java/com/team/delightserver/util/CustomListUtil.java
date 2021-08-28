package com.team.delightserver.util;

import com.team.delightserver.util.enumclass.RedisCommonConstant;
import java.util.ArrayList;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @Created by Doe
 * @Date: 2021/08/17
 */


public class CustomListUtil {

    public static List<Long> categoryIds = new ArrayList<>();

    public static <T extends List<?>> T applyPageableToList(T list, Pageable pageable){
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), list.size());
        return (T) list.subList(start, end);
    }

    public static List<Long> setCategoryIdsAndReturnIds () {
        categoryIds.add(RedisCommonConstant.CATEGORY_KOREA_ID);
        categoryIds.add(RedisCommonConstant.CATEGORY_JAPANESE_ID);
        categoryIds.add(RedisCommonConstant.CATEGORY_JAPANESE_ID);
        categoryIds.add(RedisCommonConstant.CATEGORY_CHINESE_ID);
        categoryIds.add(RedisCommonConstant.CATEGORY_WESTERN_ID);
        categoryIds.add(RedisCommonConstant.CATEGORY_SNACK_ID);
        return categoryIds;
    }
}
