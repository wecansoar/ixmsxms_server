package net.flower.ixmsxms_server.utils;

import javax.servlet.http.HttpServletRequest;

public class WebContext {
    public static ThreadLocal<WebContext> local = new ThreadLocal<WebContext>();

    private HttpServletRequest request;

    public HttpServletRequest getRequest() {
        return request;
    }

    public WebContext setRequest(HttpServletRequest request) {
        this.request = request;
        return this;
    }
}
