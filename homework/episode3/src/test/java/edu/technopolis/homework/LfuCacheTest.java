package edu.technopolis.homework;

import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map.Entry;
import java.util.Set;

import static org.hamcrest.core.AnyOf.anyOf;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

public class LfuCacheTest {
    private LfuCache<String, String> cache;

    @Before
    public void setUp() throws Exception {
        cache = new LfuCache<>(20, 0.5f);
    }

    @Test
    public void create() throws Exception {
    }

    @Test
    public void isEmpty() throws Exception {
        assertThat(cache.isEmpty(), is(true));
        cache.put("1", "first");
        assertThat(cache.isEmpty(), is(false));
    }

    @Test
    public void size() throws Exception {
        assertThat(cache.size(), is(0));
        cache.put("1", "first");
        assertThat(cache.size(), is(1));
    }

    @Test
    public void containsKey() throws Exception {
        assertThat(cache.containsKey("1"), is(false));
        cache.put("1", "first");
        assertThat(cache.containsKey("1"), is(true));
    }

    @Test
    public void containsValue() throws Exception {
        final String value = "first";
        assertThat(cache.containsValue(null), is(false));
        assertThat(cache.containsValue(value), is(false));
        cache.put("1", value);
        assertThat(cache.containsValue(value), is(true));
    }

    @Test
    public void putTwice() throws Exception {
        final String value = "first";
        assertThat(cache.put("1", value), is(nullValue()));
        assertThat(cache.put("1", value), is(value));
    }

    @Test
    public void putAll() throws Exception {
        final HashMap<String, String> map = new HashMap<>();
        map.put("1", "first");
        map.put("2", "second");
        cache.putAll(map);
        assertThat(cache.size(), is(2));
    }

    @Test
    public void keySet() throws Exception {
        cache.put("1", "first");
        cache.put("2", "second");

        Set<String> expected = new LinkedHashSet<>();
        expected.add("1");
        expected.add("2");

        assertThat(cache.keySet(), is(expected));
    }

    @Test
    public void clear() throws Exception {
        cache.put("1", "first");
        assertThat(cache.isEmpty(), is(false));
        cache.clear();
        assertThat(cache.isEmpty(), is(true));
    }

    @Test
    public void get() throws Exception {
        cache.put("1", "first");
        assertThat(cache.get("1"), is("first"));
        assertThat(cache.get("2"), is(nullValue()));
    }

    @Test
    public void remove() throws Exception {
        cache.put("1", "first");
        assertThat(cache.remove("1"), is("first"));
    }

    @Test
    public void frequencyOf() throws Exception {
        assertThat(cache.frequencyOf("1"), is(0));
        cache.put("1", "first");
        assertThat(cache.frequencyOf("1"), is(1));
        cache.get("1");
        assertThat(cache.frequencyOf("1"), is(2));
    }

    @Test
    public void values() throws Exception {
        cache.put("1", "first");
        cache.put("2", "second");

        final Collection<String> values = cache.values();
        assertThat(values.size(), is(2));
        assertThat(values.contains("first"), is(true));
        assertThat(values.contains("second"), is(true));
    }

    @Test
    public void entrySet() throws Exception {
        cache.put("1", "first");
        cache.put("2", "second");

        final Set<Entry<String, String>> set = cache.entrySet();

        assertThat(set.size(), is(2));
        Iterator<Entry<String, String>> it = set.iterator();

        Entry<String, String> entry = it.next();
        assertThat(entry.getKey(), anyOf(is("1"), is("2")));
        assertThat(entry.getValue(), anyOf(is("first"), is("second")));

        entry = it.next();
        assertThat(entry.getKey(), anyOf(is("1"), is("2")));
        assertThat(entry.getValue(), anyOf(is("first"), is("second")));
    }
}
