package com.lh.servicegetway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.logging.Logger;

/**
 * @author 梁昊
 * @date:2019/10/14
 */
public class MyGlobalFilter implements GlobalFilter, Ordered {
    private final String TokenName = "accessToken";
    private final String UseId = "useId";
    private final String UseType = "useType";
    private final String ClientType = "clientType";

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        Logger logger = Logger.getLogger("chapter07");
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        String uri = request.getURI().toString();
        logger.info("uri:" + uri);
        String path = request.getPath().toString();
        logger.info("path:" + path);
        String ipAddress = getIpAddress(request);
        logger.info("ipAddress:" + ipAddress);
//        String hostName = request.getRemoteAddress().getHostName();
//        logger.info("hostName:" + hostName);
//        String remoteAddress = request.getRemoteAddress().toString();
//        logger.info("remoteAddress:" + remoteAddress);
//        String hostString = request.getRemoteAddress().getHostString();
//        logger.info("hostString:" + hostString);
//        int port = request.getRemoteAddress().getPort();
//        logger.info("port:" + port);

        if (false) {
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }
        return chain.filter(exchange);
    }

    private String getIpAddress(ServerHttpRequest request) {
        HttpHeaders headers = request.getHeaders();
        String ip = headers.getFirst("x-forwarded-for");
        if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个ip值，第一个ip才是真实ip
            if (ip.indexOf(",") != -1) {
                ip = ip.split(",")[0];
            }
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = headers.getFirst("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = headers.getFirst("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = headers.getFirst("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = headers.getFirst("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = headers.getFirst("X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddress().getAddress().getHostAddress();
        }
        return ip;
    }

}
