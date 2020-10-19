package com.kosho.uc.chap3.utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Set工具
 *
 * @author Kosho
 * @since 2020-04-20
 */
public abstract class Sets {
    public static <T> Set<T> create(T... elements) {
        return new HashSet<>(Arrays.asList(elements));
    }
}
