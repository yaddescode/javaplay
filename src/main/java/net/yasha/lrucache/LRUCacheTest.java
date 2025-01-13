package net.yasha.lrucache;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LRUCacheTest {

    @Test
    void moveToTopTest() {
        LRUCache cache = new LRUCache(3);
        cache.put(1,"1");
        cache.put(2,"2");
        cache.put(3,"3");
        for (int i = 1; i <= 3; i++) {
            LRUCache.CacheNode result = cache.get(i);

            Assertions.assertNotNull(result);
        }
        Assertions.assertEquals(3, cache.currentCount);

        // add one more, this should evict "1"
        cache.put(4,"4");
        Assertions.assertEquals(3, cache.currentCount);
        LRUCache.CacheNode result = cache.get(1);
        Assertions.assertNull(result);
        Assertions.assertNotNull(cache.get(4));
        Assertions.assertNotNull(cache.get(2));
        Assertions.assertNotNull(cache.get(3));
    }
}
