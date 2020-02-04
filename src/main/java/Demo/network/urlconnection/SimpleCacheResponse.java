package Demo.network.urlconnection;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.CacheResponse;
import java.net.URLConnection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: su
 * @date: 2020/2/4
 */
public class SimpleCacheResponse extends CacheResponse {
    private Map<String, List<String>> headers;
    private final SimpleCacheRequest request;
    private final Date expires;
    private final CacheControl control;

    public SimpleCacheResponse(SimpleCacheRequest request, CacheControl control, URLConnection uc) {
        this.request = request;
        this.control = control;
        expires = new Date(uc.getDate());
        headers = Collections.unmodifiableMap(uc.getHeaderFields());
    }

    @Override

    public Map<String, List<String>> getHeaders() throws IOException {
        return headers;
    }

    @Override
    public InputStream getBody() throws IOException {
        return new ByteArrayInputStream(request.getData());
    }

    public CacheControl getControl() {
        return control;
    }

    public boolean isExpired() {
        Date now = new Date();
        if (control.getMaxAge().before(now)) return true;
        else if (expires != null && control.getMaxAge() != null) {
            return expires.before(now);
        } else {
            return false;
        }
    }
}
