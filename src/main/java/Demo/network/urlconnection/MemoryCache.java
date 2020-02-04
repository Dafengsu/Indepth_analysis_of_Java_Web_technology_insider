package Demo.network.urlconnection;

import java.io.IOException;
import java.net.*;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description:
 * @author: su
 * @date: 2020/2/4
 */
public class MemoryCache extends ResponseCache {
    private final Map<URI, SimpleCacheResponse> responses = new ConcurrentHashMap<>();
    private final int maxEntries;

    public MemoryCache() {
        this(100);
    }

    public MemoryCache(int maxEntries) {
        this.maxEntries = maxEntries;
    }

    @Override
    public CacheResponse get(URI uri, String rqstMethod, Map<String, List<String>> rqstHeaders) throws IOException {
        if ("GET".equals(rqstMethod)) {
            SimpleCacheResponse response = responses.get(uri);
            if (response != null && response.isExpired()) {
                responses.remove(response);
                response = null;
            }
            return response;
        } else {
            return null;
        }
    }

    @Override
    public CacheRequest put(URI uri, URLConnection conn) throws IOException {
        if (responses.size() >= maxEntries) {
            return null;
        }
        CacheControl control = new CacheControl(conn.getHeaderField("Cache-Control"));
        if (control.noStore() || conn.getHeaderField(0).startsWith("GET ")) {
            return null;
        }
        SimpleCacheRequest request = new SimpleCacheRequest();
        SimpleCacheResponse response = new SimpleCacheResponse(request, control, conn);
        responses.put(uri, response);
        return request;
    }
}
