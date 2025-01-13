package net.yasha.lrucache;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

// implementation of LRU cache with max capacity
public class LRUCache {
    
    int maxCapacity=0;
    Map<Integer, CacheNode> cacheNodeMap;
    CacheNode rootNode;
    CacheNode tailNode;
    int currentCount = 0;

    public LRUCache(int capacity) {
        maxCapacity = capacity;
        cacheNodeMap = new HashMap<Integer, CacheNode>();
        rootNode = new CacheNode();
        tailNode = new CacheNode();
        rootNode.next = tailNode;
        tailNode.previous = rootNode;
    }

    public CacheNode get(Integer key) {
        CacheNode node = cacheNodeMap.get(key);
        if (node == null) {
            return null;
        }
        // found it, move it to be most recently used
        moveToHead(node);
        return node;
    }

    public CacheNode put(Integer key, String value) {

        CacheNode node = cacheNodeMap.get(key);
        if (node == null) {
            node = new CacheNode(key, value, null, null);
            if (currentCount == maxCapacity) {
                // remove before adding
                removeLast();
            }
            cacheNodeMap.put(key, node);
            this.currentCount++;
        } else {
            // found it, update value and move to top;
            node.value = value;
        }
        // found it, move it to be most recently used
        moveToHead(node);
        return node;
    }

    private void removeLast() {
        if (tailNode.previous != rootNode) {
            CacheNode last = tailNode.previous;
            last.next = tailNode;
            tailNode.previous = last.previous;
            last.next = null;
            last.previous = null;
            currentCount--;
            cacheNodeMap.remove(last.key);
        }
    }

    private void moveToHead(CacheNode node) {
        CacheNode currentTop = rootNode.next;
        currentTop.previous = node;
        node.next = currentTop;
        rootNode.next = node;
    }

    @AllArgsConstructor
    class CacheNode {
        Integer key;
        String value;
        CacheNode next;
        CacheNode previous;

        public CacheNode() {
            key = null;
            value = null;
            next = null;
            previous = null;
        }
        
    }
}
