package com.kosho.uc.chap3.utils;

/**
 * KV对
 *
 * @author Kosho
 * @since 2020-04-20
 */
public class Pair<K, V> {
    private K left;

    private V right;

    public V getRight() {
        return right;
    }

    public K getLeft() {
        return left;
    }

    public Pair(K key, V right) {
        this.left = key;
        this.right = right;
    }

    @Override
    public String toString() {
        return "left=" + left + ", " + "right=" + right;
    }

    @Override
    public int hashCode() {
        return left.hashCode() * 13 + (right == null ? 0 : right.hashCode());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o instanceof Pair) {
            Pair pair = (Pair) o;
            if (left != null ? !left.equals(pair.left) : pair.left != null) {
                return false;
            }
            return right != null ? right.equals(pair.right) : pair.right == null;
        }
        return false;
    }
}
