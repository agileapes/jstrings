package com.agileapes.tools.jstrings.token.impl;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (2013/5/5, 9:02)
 */
public class TaggedToken extends ValueToken {

    private final Integer tag;

    public TaggedToken(String value, long position, Integer tag) {
        super(value, position);
        this.tag = tag;
    }

    public Integer getTag() {
        return tag;
    }

}
