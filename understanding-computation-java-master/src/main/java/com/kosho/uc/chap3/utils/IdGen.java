package com.kosho.uc.chap3.utils;

/**
 * Id生成
 *
 * @author Kosho
 * @since 2020-04-20
 */
public abstract class IdGen {
    private static Integer cur = 0;

    public static Integer gen() {
        return cur++;
    }
}
