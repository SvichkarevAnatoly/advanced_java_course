package edu.technopolis.homework;

import java.util.*;

public class LfuCache<Key, Value> implements Map<Key, Value> {

    private final Map<Key, CacheNode<Key, Value>> cache;
    private final LinkedHashSet[] frequencyList;
    private int lowestFrequency;
    private int maxFrequency;

    private final int maxCacheSize;
    private final float evictionFactor;

    public LfuCache(int maxCacheSize, float evictionFactor) {
        if (evictionFactor <= 0 || evictionFactor >= 1) {
            throw new IllegalArgumentException("Eviction factor must be greater than 0 and lesser than or equal to 1");
        }
        cache = new HashMap<>(maxCacheSize);
        frequencyList = new LinkedHashSet[maxCacheSize];
        lowestFrequency = 0;
        maxFrequency = maxCacheSize - 1;
        this.maxCacheSize = maxCacheSize;
        this.evictionFactor = evictionFactor;
        initFrequencyList();
    }

    @SuppressWarnings("unchecked")
    public Value put(Key k, Value v) {
        Value oldValue = null;
        CacheNode<Key, Value> currentNode = cache.get(k);
        if (currentNode == null) {
            if (cache.size() == maxCacheSize) {
                doEviction();
            }
            LinkedHashSet<CacheNode<Key, Value>> nodes = frequencyList[0];
            currentNode = new CacheNode(k, v, 0);
            nodes.add(currentNode);
            cache.put(k, currentNode);
            lowestFrequency = 0;
        } else {
            oldValue = currentNode.v;
            currentNode.v = v;
        }
        return oldValue;
    }


    public void putAll(Map<? extends Key, ? extends Value> map) {
        for (Map.Entry<? extends Key, ? extends Value> me : map.entrySet()) {
            put(me.getKey(), me.getValue());
        }
    }

    @SuppressWarnings("unchecked")
    public Value get(Object k) {
        CacheNode<Key, Value> currentNode = cache.get(k);
        if (currentNode != null) {
            int currentFrequency = currentNode.frequency;
            if (currentFrequency < maxFrequency) {
                int nextFrequency = currentFrequency + 1;
                LinkedHashSet<CacheNode<Key, Value>> currentNodes = frequencyList[currentFrequency];
                LinkedHashSet<CacheNode<Key, Value>> newNodes = frequencyList[nextFrequency];
                moveToNextFrequency(currentNode, nextFrequency, currentNodes, newNodes);
                cache.put((Key) k, currentNode);
                if (lowestFrequency == currentFrequency && currentNodes.isEmpty()) {
                    lowestFrequency = nextFrequency;
                }
            } else {
                // Hybrid with LRU: put most recently accessed ahead of others:
                LinkedHashSet<CacheNode<Key, Value>> nodes = frequencyList[currentFrequency];
                nodes.remove(currentNode);
                nodes.add(currentNode);
            }
            return currentNode.v;
        } else {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public Value remove(Object k) {
        CacheNode<Key, Value> currentNode = cache.remove(k);
        if (currentNode != null) {
            LinkedHashSet<CacheNode<Key, Value>> nodes = frequencyList[currentNode.frequency];
            nodes.remove(currentNode);
            if (lowestFrequency == currentNode.frequency) {
                findNextLowestFrequency();
            }
            return currentNode.v;
        } else {
            return null;
        }
    }

    public int frequencyOf(Key k) {
        CacheNode<Key, Value> node = cache.get(k);
        if (node != null) {
            return node.frequency + 1;
        } else {
            return 0;
        }
    }

    public void clear() {
        for (int i = 0; i <= maxFrequency; i++) {
            frequencyList[i].clear();
        }
        cache.clear();
        lowestFrequency = 0;
    }

    public Set<Key> keySet() {
        return cache.keySet();
    }

    public Collection<Value> values() {
        final Collection<Value> values = new ArrayList<>(cache.size());
        for (CacheNode<Key, Value> node : cache.values()) {
            values.add(node.v);
        }
        return values;
    }

    @Override
    public Set<Entry<Key, Value>> entrySet() {
        final Set<Entry<Key, Value>> set = new HashSet<>();
        for (CacheNode<Key, Value> node : cache.values()) {
            Entry<Key, Value> entry = new CacheEntry<>(node.k, node.v);
            set.add(entry);
        }
        return set;
    }

    public int size() {
        return cache.size();
    }

    public boolean isEmpty() {
        return cache.isEmpty();
    }

    public boolean containsKey(Object o) {
        return cache.containsKey(o);
    }

    @SuppressWarnings("unchecked")
    public boolean containsValue(Object o) {
        if (o == null) {
            return false;
        } else if (cache.isEmpty()) {
            return false;
        } else {
            for (CacheNode<Key, Value> node : cache.values()) {
                if (o.equals(node.v)) {
                    return true;
                }
            }
        }
        return false;
    }


    private void initFrequencyList() {
        for (int i = 0; i <= maxFrequency; i++) {
            frequencyList[i] = new LinkedHashSet<CacheNode<Key, Value>>();
        }
    }

    @SuppressWarnings("unchecked")
    private void doEviction() {
        int currentlyDeleted = 0;
        float target = maxCacheSize * evictionFactor;
        while (currentlyDeleted < target) {
            LinkedHashSet<CacheNode<Key, Value>> nodes = frequencyList[lowestFrequency];
            if (nodes.isEmpty()) {
                throw new IllegalStateException("Lowest frequency constraint violated!");
            } else {
                Iterator<CacheNode<Key, Value>> it = nodes.iterator();
                while (it.hasNext() && currentlyDeleted++ < target) {
                    CacheNode<Key, Value> node = it.next();
                    it.remove();
                    cache.remove(node.k);
                }
                if (!it.hasNext()) {
                    findNextLowestFrequency();
                }
            }
        }
    }

    private void moveToNextFrequency(CacheNode<Key, Value> currentNode, int nextFrequency,
                                     LinkedHashSet<CacheNode<Key, Value>> currentNodes,
                                     LinkedHashSet<CacheNode<Key, Value>> newNodes) {
        currentNodes.remove(currentNode);
        newNodes.add(currentNode);
        currentNode.frequency = nextFrequency;
    }

    private void findNextLowestFrequency() {
        while (lowestFrequency <= maxFrequency && frequencyList[lowestFrequency].isEmpty()) {
            lowestFrequency++;
        }
        if (lowestFrequency > maxFrequency) {
            lowestFrequency = 0;
        }
    }

    private static class CacheNode<Key, Value> {
        final Key k;
        Value v;
        int frequency;

        CacheNode(Key k, Value v, int frequency) {
            this.k = k;
            this.v = v;
            this.frequency = frequency;
        }
    }

    class CacheEntry<Key, Value> implements Entry<Key, Value>{
        private Key key;
        private Value value;

        public CacheEntry(Key key, Value value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public Key getKey() {
            return key;
        }

        @Override
        public Value getValue() {
            return value;
        }

        @Override
        public Value setValue(Value value) {
            final Value oldValue = this.value;
            this.value = value;
            return oldValue;
        }
    }
}
