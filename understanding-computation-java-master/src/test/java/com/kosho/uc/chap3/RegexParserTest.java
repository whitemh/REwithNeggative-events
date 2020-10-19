package com.kosho.uc.chap3;

import com.kosho.uc.chap3.regex.Pattern;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class RegexParserTest {
    @Test
    public void parse() {
        Pattern pattern = Pattern.compile("a(b|c)*");
        Assert.assertEquals("/a(b|c)*/", pattern.inspect());
        Assert.assertTrue(pattern.matches("abcbccbcc"));
        Assert.assertFalse(pattern.matches("abcbccca"));
    }

    @Test
    public void parse2() {
        Pattern pattern = Pattern.compile("a(b)*c");
        Assert.assertEquals("/ab*c/", pattern.inspect());
        Assert.assertTrue(pattern.matches("abbbc"));
        Assert.assertFalse(pattern.matches("abbbcb"));
    }

    @Test
    public void parse3() {
        Pattern pattern = Pattern.compile("(a(|b))*");
        Assert.assertEquals("/(a(|b))*/", pattern.inspect());
        Assert.assertTrue(pattern.matches("ababa"));
        Assert.assertFalse(pattern.matches("ababac"));
    }
}