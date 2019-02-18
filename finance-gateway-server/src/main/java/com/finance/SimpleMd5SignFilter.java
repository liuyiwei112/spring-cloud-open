/**
 * Copyright (c) 2016, 指端科技.
 */

package com.finance;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

;

/**
 * @author wdx
 * @ClassName: SimpleMd5SignFilter
 * @Description: 验签拦截器
 * @date 2016年4月19日 下午1:13:34
 */
//@ConfigurationProperties(prefix = "app")
public class SimpleMd5SignFilter extends OncePerRequestFilter {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private static Map<String, String> keys = new HashMap<>();

    static {
        keys.put("web.operation", "t6880eac0528w97a62db334ea9v16g10");
        keys.put("ios.consumer", "t6880eac0528w97a62db334ea9v16g10");
        keys.put("android.consumer", "t6880eac0528w97a62db334ea9v16g10");
        keys.put("web.consumer", "t6880eac0528w97a62db334ea9v16g10");
        keys.put("ios.servant", "t6880eac0528w97a62db334ea9v16g10");
        keys.put("android.servant", "t6880eac0528w97a62db334ea9v16g10");
        keys.put("ios.merchant", "t6880eac0528w97a62db334ea9v16g10");
        keys.put("android.merchant", "t6880eac0528w97a62db334ea9v16g10");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        response.addHeader("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);

        Map<String, String> parameterMap = getParameterMap(request);

        String appid = request.getParameter("appid");
        String sign = request.getParameter("sign");
        String version = request.getParameter("version");
        String timestamp = request.getParameter("timestamp");

        logger.info("appid={},sign={},version={},timestamp={}", appid, sign, version, timestamp);

//        if (StringUtils.isEmpty(appid) || StringUtils.equalsIgnoreCase(appid, "null")) {
//            logger.error("签名验证失败:appid为空");
//            String json = JsonUtils.translateToJson(new Result(false, "request.sign.error", "appid为空",null));
//            response.getWriter().write(json);
//            return;
//        }
//        if (StringUtils.isEmpty(sign) || StringUtils.equalsIgnoreCase(sign, "null")) {
//            logger.error("签名验证失败:sign为空");
//            String json = JsonUtils.translateToJson(new Result(false, "request.sign.error", "sign为空",null));
//            response.getWriter().write(json);
//            return;
//        }
//
//        if (StringUtils.isEmpty(version) || StringUtils.equalsIgnoreCase(version, "null")) {
//            logger.error("签名验证失败:version为空");
//            String json = JsonUtils.translateToJson(new Result(false, "request.sign.error", "version为空",null));
//            response.getWriter().write(json);
//            return;
//        }
//        if (StringUtils.isEmpty(timestamp) || StringUtils.equalsIgnoreCase(timestamp, "null")) {
//            logger.error("签名验证失败:timestamp为空");
//            String json = JsonUtils.translateToJson(new Result(false, "request.sign.error", "timestamp为空",null));
//            response.getWriter().write(json);
//            return;
//        }
//
//        String appKey = keys.get(appid);
//        if (StringUtils.isEmpty(appKey)) {
//            logger.error("签名验证失败:appKey不存在");
//            String json = JsonUtils.translateToJson(new Result(false, "request.sign.error", "appkey不存在",null));
//            response.getWriter().write(json);
//            return;
//        }
//
//        // 开始校验签名是否正确
//        parameterMap.remove("sign");
//
//        String localSign = getMd5Sign(parameterMap, appKey);
//        if (!sign.equals(localSign)) {
//            logger.error("签名验证失败");
//            String json = JsonUtils.translateToJson(new Result(false, "request.sign.error", "签名验证失败",null));
//            response.getWriter().write(json);
//        } else {
//            filterChain.doFilter(request, response);
//        }
        filterChain.doFilter(request,response);
    }

    private Map<String, String> getParameterMap(HttpServletRequest request) {
        Map<String, String[]> params = request.getParameterMap();
        Map<String, String> paramMap = new HashMap<>();
        for (Map.Entry<String, String[]> stringEntry : params.entrySet()) {
            StringBuilder value = new StringBuilder();
            String name = stringEntry.getKey();
            if (stringEntry.getValue() == null) {
                value = new StringBuilder();
            } else if (stringEntry.getValue() instanceof String[]) {
                String[] values = stringEntry.getValue();
                for (String item : values) {
                    value.append(item).append(",");
                }
                value = new StringBuilder(value.substring(0, value.length() - 1));
            } else {
                value = new StringBuilder(Arrays.toString(stringEntry.getValue()));
            }
            paramMap.put(name, value.toString());
        }
        return paramMap;
    }

    /**
     * @param @param  parms 请求参数
     * @param @param  appkey
     * @param @return 参数
     * @return String    返回类型
     * @throws
     * @Method: getMd5Sign
     * @Description: 获得md5签名
     * @author wdx
     * @date 2016年4月2日 下午7:19:27
     **/

    private String getMd5Sign(Map<String, String> parms, String appkey) {

        StringBuilder sb = new StringBuilder();
        if (parms == null || parms.size() < 1) {
            return "";
        }
        List<String> keys = new ArrayList<String>(parms.keySet());

        //参数排序 升序
        Collections.sort(keys);

        for (String key : keys) {
            if (parms.get(key) != null) {
                sb.append(key).append(parms.get(key));
            }
        }
        ;
        return getMd5Sign(sb.toString(), appkey);
    }

    //md5加密
    private String getMd5Sign(String source, String appkey) {
        logger.info("sign_string:{}, sign_salt:{}", source, appkey);
        return DigestUtils.md5Hex(source + appkey);
    }
}
