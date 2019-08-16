package com.example.demo.vo;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public abstract class ResponseBuilder {
    public static ResponseBuilder success(){
        return new DefaultResponseBuilder(true);
    }

    public static ResponseBuilder failure(){
        return new DefaultResponseBuilder(false);
    }

    public abstract ResponseBuilder setMessage(String message);

    public abstract ResponseBuilder add(Object object);

    public abstract ResponseBuilder addAll(List<Object> list);

    public abstract ResponseBuilder put(String key, Object object);

    public abstract ResponseBuilder putAll(Map<String, Object> map);

    public abstract Response build();


    public static class DefaultResponseBuilder extends ResponseBuilder {
        private boolean success;// 是否成功

        private String message;// 提示信息

        private Object object;// 其他信息


        DefaultResponseBuilder(boolean success) {
            this.success = success;
        }

        @Override
        public ResponseBuilder setMessage(String message) {
            this.message = message;
            return this;
        }

        @Override
        public ResponseBuilder add(Object object) {
            if (this.object == null) {
                this.object = object;
            } else if (this.object instanceof List) {
                ((List<Object>) this.object).add(object);
            } else {
                this.object = Arrays.asList(this.object, object);
            }
            return this;
        }

        @Override
        public ResponseBuilder addAll(List<Object> list) {
            this.object = list;
            return this;
        }

        @Override
        public ResponseBuilder put(String key, Object object) {
            if (this.object == null) {
                this.object = new LinkedHashMap<String,Object>();
                ((LinkedHashMap<String,Object>)this.object).put(key, object);
            }
            return this;
        }

        @Override
        public ResponseBuilder putAll(Map<String, Object> map) {
            this.object = map;
            return this;
        }

        @Override
        public Response build() {
            return new Response(this.success, this.message, this.object);
        }
    }
}
