package com.agileapes.tools.jstrings.token.impl;

import com.agileapes.tools.jstrings.token.TagDeterminer;

import java.util.regex.Pattern;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (2013/5/5, 9:04)
 */
public class PatternTagDeterminer implements TagDeterminer {

    private final int tag;
    private final Pattern pattern;

    public PatternTagDeterminer(int tag, Pattern pattern) {
        this.tag = tag;
        this.pattern = pattern;
    }

    @Override
    public Integer getTag() {
        return tag;
    }

    @Override
    public Integer getTag(String token) {
        if (pattern.matcher(token).matches()) {
            return getTag();
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PatternTagDeterminer that = (PatternTagDeterminer) o;
        return tag == that.tag;

    }

    @Override
    public int hashCode() {
        return tag;
    }

}
