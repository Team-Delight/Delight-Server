package com.team.delightserver.util;

import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @Created by Doe
 * @Date: 2021/08/17
 */


public class CustomListUtil {

    public static <T extends List<?>> T applyPageableToList(T list, Pageable pageable){
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), list.size());
        return (T) list.subList(start, end);
    }
}
