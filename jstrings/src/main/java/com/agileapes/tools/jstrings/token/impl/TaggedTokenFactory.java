package com.agileapes.tools.jstrings.token.impl;

import com.agileapes.tools.jstrings.token.TagDeterminer;
import com.agileapes.tools.jstrings.token.TokenFactory;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (2013/5/5, 9:02)
 */
public class TaggedTokenFactory implements TokenFactory<TaggedToken> {

    private final static TagDeterminer DEFAULT = new PatternTagDeterminer(0, Pattern.compile(".*"));
    private final Map<Integer, TagDeterminer> tagDeterminers = new HashMap<Integer, TagDeterminer>();

    public TaggedTokenFactory(TagDeterminer ... determiners) {
        for (TagDeterminer determiner : determiners) {
            register(determiner);
        }
    }

    @Override
    public TaggedToken getToken(String value, long position) {
        return new TaggedToken(value, position, getTag(value));
    }

    private Integer getTag(String token) {
        final Set<Integer> tags = new HashSet<Integer>();
        for (Integer tag : tagDeterminers.keySet()) {
            if (tagDeterminers.get(tag).getTag(token) != null) {
                tags.add(tag);
            }
        }
        if (tags.isEmpty()) {
            return DEFAULT.getTag();
        }
        if (tags.size() == 1) {
            return tags.iterator().next();
        }
        throw new IllegalStateException("More than one tag determined for token: " + token);
    }

    public void register(TagDeterminer tagDeterminer) {
        if (DEFAULT.getTag().equals(tagDeterminer.getTag()) || tagDeterminers.containsKey(tagDeterminer.getTag())) {
            throw new IllegalArgumentException(String.format("A determiner for tag %d already exists", tagDeterminer.getTag()));
        }
        tagDeterminers.put(tagDeterminer.getTag(), tagDeterminer);
    }

}
