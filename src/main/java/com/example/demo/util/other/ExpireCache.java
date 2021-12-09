package com.example.demo.util.other;

import java.util.concurrent.ConcurrentHashMap;

public class ExpireCache {
    private static final ConcurrentHashMap<String, CacheBody> cache = new ConcurrentHashMap<>();

    public static boolean containsKey(String key) {
        return cache.containsKey(key);
    }

    /**
     *
     * @param key
     * @param value
     * @param expireIn 有效期 毫秒
     */
    public static void put(String key, String value, long expireIn) {
        CacheBody body = new CacheBody();
        body.setData(value);
        long nowTime = System.currentTimeMillis();
        body.setCreateTime(nowTime);
        if(expireIn <= 0) {
            body.setPermanent(true);
        }else {
            body.setExpiresTime(nowTime + expireIn );
        }
        cache.put(key, body);
    }

    public static String get(String key) {
        CacheBody body = cache.get(key);
        if(body == null) {
            return null;
        }
        if(body.isPermanent()) {
            return body.getData();
        }

        if(body.getExpiresTime() < System.currentTimeMillis()) {
            body = null;
            cache.remove(key);
            return null;
        }
        return body.getData();
    }

    private static class CacheBody {
        private Long createTime;
        private Long expiresTime;
        private boolean permanent = false; // 是不是永久的,默认为false
        private String data;

        public Long getCreateTime() {
            return createTime;
        }

        public boolean isPermanent() {
            return permanent;
        }


        public void setPermanent(boolean permanent) {
            this.permanent = permanent;
        }

        public void setCreateTime(Long createTime) {
            this.createTime = createTime;
        }

        public Long getExpiresTime() {
            return expiresTime;
        }

        public void setExpiresTime(Long expiresTime) {
            this.expiresTime = expiresTime;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }
    }
}
