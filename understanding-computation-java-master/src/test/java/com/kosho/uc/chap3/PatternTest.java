package com.kosho.uc.chap3;

import com.kosho.uc.chap3.regex.*;
import org.junit.Assert;
import org.junit.Test;

public class PatternTest {
    @Test
    public void testToString() {
        Pattern repeat = new Repeat(
                new Choose(
                        new Concat(
                                new Literal('a'),
                                new Literal('b')),
                        new Literal('a')));
        Assert.assertEquals(repeat.inspect(), "/(ab|a)*/");
    }

    @Test
    public void testEmpty() {
        Assert.assertTrue(new Empty().matches(""));
        Assert.assertFalse(new Empty().matches("a"));
    }

    @Test
    public void testLiteral() {
        Assert.assertTrue(new Literal('a').matches("a"));
        Assert.assertFalse(new Literal('a').matches("b"));
    }

    @Test
    public void testConcatenate() {
        Concat concatenate = new Concat(new Literal('a'), new Literal('b'));
        Assert.assertTrue(concatenate.matches("ab"));
        Assert.assertFalse(concatenate.matches("abc"));

        Concat concatenate2 = new Concat(
                new Literal('a'),
                new Concat(new Literal('b'), new Literal('c')));
        Assert.assertTrue(concatenate2.matches("abc"));
        Assert.assertFalse(concatenate2.matches("ab"));
    }

    @Test
    public void testChoose() {
        Choose choose = new Choose(new Literal('a'), new Literal('b'));
        Assert.assertTrue(choose.matches("a"));
        Assert.assertTrue(choose.matches("b"));
        Assert.assertFalse(choose.matches("c"));
    }

    @Test
    public void testRepeat() {
        Repeat repeat = new Repeat(new Literal('a'));
        Assert.assertTrue(repeat.matches(""));
        Assert.assertTrue(repeat.matches("a"));
        Assert.assertTrue(repeat.matches("aaaa"));
        Assert.assertFalse(repeat.matches("b"));
        Assert.assertFalse(repeat.matches("ab"));
    }

    @Test
    public void testPattern() {
        Pattern repeat = new Repeat(
                new Choose(
                        new Concat(
                                new Literal('a'),
                                new Literal('b')),
                        new Literal('a')));
        Assert.assertEquals(repeat.inspect(), "/(ab|a)*/");
        Assert.assertTrue(repeat.matches("ababa"));
    }

    @Test
    public void testPattern2() {
        Pattern repeat = new Repeat(
                new Concat(
                        new Literal('a'),
                        new Choose(
                                new Empty(),
                                new Literal('b'))));
        Assert.assertEquals(repeat.inspect(), "/(a(|b))*/");
        Assert.assertTrue(repeat.matches("abab"));
    }
}