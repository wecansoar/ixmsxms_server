package net.flower.ixmsxms_server.utils;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.FastDateFormat;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CoreUtil extends StringUtils {

    private static final String DAUM_IMAGE_REG_EX = "http://(cfile\\d+).uf.daum.net";
    private static final String IMG_TAG = "\\{img\\}";

    public static String unescapeHtml(String str) {
        return str.replaceAll("&quot;", "\"")
                .replaceAll("&lt;", "<").replaceAll("&gt;", ">");
    }

    public static String escapeHtmlForSummary(String str, int length) {
        String content = str.replaceAll("\"", "&quot;")
                .replaceAll("<", "&lt;").replaceAll(">", "&gt;");
        if (content.length() > length) {
            return content.substring(0, length);
        } else {
            return content;
        }
    }

    public static String replaceNewLineToBr(String str) {
        return str.replaceAll("(\r\n|\r|\n|\n\r)", "<br>");
    }

    public static String getUrlString(String url) {
        HttpClient hc = new HttpClient();
        //hc.getHttpConnectionManager().getParams().setSoTimeout(1000);
        //hc.getHttpConnectionManager().getParams().setConnectionTimeout(1000);
        HttpMethod m = new GetMethod(url);
        m.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(1, false));
        StringBuffer message = new StringBuffer();
        String str = "";
        InputStream is = null;
        try {
            int statusCode = hc.executeMethod(m);
            if (statusCode != HttpStatus.SC_OK) {
                System.err.println("Method failed: " + m.getStatusLine());
            }else {
                is = m.getResponseBodyAsStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "utf-8"));
                while ((str=reader.readLine())!=null) {
                    message.append(str);
                    message.append("\n");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return message.toString();

    }

    /*
     * url 형태의 string 에서 파라미터의 값을 찾아낸다.
     * 예 : http://aaa/bbb?bbsid=33&...
     * 	getParameter(str, "bbsid") = 33
     */
    public static String getParameter(String url, String param) {

        if (url == null)
            return null;

        url = url.toLowerCase();
        param = param.toLowerCase();

        int idx = url.indexOf("?" + param + "=");

        if (idx == -1)
            idx =  url.indexOf("&" + param + "=");

        if (idx == -1)
            return null;

        String remainder = url.substring(idx + param.length() + 2);

        if (remainder.indexOf("&") != -1)
            return remainder.substring(0, remainder.indexOf("&"));
        else
            return remainder;
    }

    public static boolean hasImageTag(String text) {
        if (text == null) {
            return false;
        }

        return Pattern.compile(IMG_TAG).matcher(text).find();
    }

    public static String removeImgTag(String text) {
        return text.replaceAll(IMG_TAG, "");
    }

    public static String removeTag(String html) {
        Document doc = Jsoup.parse(html);
        String text = doc.body().text();
        return removeImgTag(text);
    }

    public static String cookiesToString(javax.servlet.http.Cookie[] cookies) {
        StringBuilder sb = new StringBuilder();
        if (cookies != null) {
            for (javax.servlet.http.Cookie cookie : cookies) {
                sb.append(cookie.getName()).append("=").append(cookie.getValue()).append("; ");
            }
        }
        return sb.toString();
    }

    public static String[] split(String str, String reg) {
        return str.split(reg);
    }

    public static boolean containsParameter(String sourceURL, String targetURL) {
        return containsParameter(sourceURL, getQueryParameterMap(targetURL));
    }

    public static boolean containsParameter(String url, Map<String, String> param) {
        Map<String, String> newParam = getQueryParameterMap(url);
        String[] paramNames = param.keySet().toArray(new String[0]);

        for (String name : paramNames) {
            String value = param.get(name);
            if (!value.equals(newParam.get(name))) {
                return false;
            }
        }
        return true;
    }

    public static Map<String, String> getQueryParameterMap(String url) {
        Map<String, String> map = new HashMap<String, String>(0);
        if (url == null) {
            return map;
        }

        int index = url.indexOf("?");
        if (index < 0) {
            return map;
        }

        String query = url.substring(index + 1);
        index = url.lastIndexOf("#");
        if (index > 0) {
            url = url.substring(0, index - 1);
        }

        String[] params = query.split("(&|#)");
        for (String param : params) {
            String[] token = param.split("=");
            if (token != null && token.length == 2) {
                String name = token[0];
                String value = token[1];
                map.put(name, value);
            }
        }
        return map;
    }

    public static String escapeXML(String str) {
        return StringEscapeUtils.escapeXml(str);
    }

    public static String toJsonString(Object object) {
        return GsonHttpMessageConverter.getDefaulGsonBuilder(false).create().toJson(object);
    }

    public static String toJsonPrettyString(Object object) {
        return GsonHttpMessageConverter.getDefaulGsonBuilder(true).create().toJson(object);
    }

    public static long currentTimeMillis() {
        return System.currentTimeMillis();
    }

    public static boolean conatins(String values, String target) {
        String[] v = values.split(",");
        for (int i = 0; i < v.length; i++) {
            if (target.startsWith(v[i])) {
                return true;
            }
        }

        return false;
    }

    public static String getRemoveNonValidXMLCharacters(InputStream in) throws Exception {
        return getRemoveNonValidXMLCharacters(IOUtils.toString(in));
    }

    public static InputStream getRemoveNonValidXMLInputStream(InputStream in) throws Exception {
        return IOUtils.toInputStream(getRemoveNonValidXMLCharacters(IOUtils.toString(in)));
    }

    public static String getRemoveNonValidXMLCharacters(String in) {
        StringBuffer out = new StringBuffer(); // Used to hold the output.
        char current; // Used to reference the current character.

        if (in == null || ("".equals(in))) return ""; // vacancy test.
        for (int i = 0; i < in.length(); i++) {
            current = in.charAt(i); // NOTE: No IndexOutOfBoundsException caught here; it should not happen.
            if ((current == 0x9) ||
                    (current == 0xA) ||
                    (current == 0xD) ||
                    ((current >= 0x20) && (current <= 0xD7FF)) ||
                    ((current >= 0xE000) && (current <= 0xFFFD)) ||
                    ((current >= 0x10000) && (current <= 0x10FFFF)))
                out.append(current);
        }
        return out.toString();
    }

    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static boolean startsWith(String source, String target) {
        if (source == null) {
            return false;
        }

        if (target == null) {
            return false;
        }

        String[] items = source.split(",");
        for (int i = 0; i < items.length; i++) {
            if (target.startsWith(items[i])) {
                return true;
            }
        }

        return false;
    }

    public static String getListPage(HttpServletRequest request) {
        String uri = request.getRequestURI();
        return getParentPage(uri) + "list.daum";
    }

    private static String getParentPage(String uri) {
        return uri.substring(0, uri.lastIndexOf("/") + 1);
    }

    public static String getDeletePage(HttpServletRequest request) {
        String uri = request.getRequestURI();
        return getParentPage(uri) + "delete.daum";
    }

    public static String getBasePage(HttpServletRequest request) {
        String uri = request.getRequestURI();
        return getParentPage(uri);
    }

    public static String getRedirect(String url) {
        return "redirect:" + url;
    }

    public static String escapeHtml(Integer str) {
        return StringEscapeUtils.escapeHtml(str.toString());
    }

    public static String escapeHtml(Long str) {
        return StringEscapeUtils.escapeHtml(str.toString());
    }

    public static String escapeHtml(String str) {
        return StringEscapeUtils.escapeHtml(str);
    }

    public static String escapeHtml(StringBuffer str) {
        return StringEscapeUtils.escapeHtml(str.toString());
    }

    public static String escapeReferer(HttpServletRequest request) {
        return StringEscapeUtils.escapeHtml(request.getRequestURI() + "?" + request.getQueryString());
    }

    public static boolean isDaumImage(String url) {
        return url.matches(DAUM_IMAGE_REG_EX + "(.*?)");
    }

    public static String getThumbnail(String url, String size) {
        if (url == null) {
            return "";
        }

        int cdnIndex = 1;
        if (Math.random() > 0.5) {
            cdnIndex = 2;
        }

        if (isDaumImage(url)) {
            return url.replaceAll(DAUM_IMAGE_REG_EX + "/(image|attach)", "http://i" + cdnIndex + ".daumcdn.net/$1/" + size);
        } else {
            return url;
        }
    }

    public static String getThumbnail(String url) {
        return getThumbnail(url, "P120x87");
    }

    public static String getHTML(String text) {
        if (text == null) {
            return "";
        }

        return "<img src=\"" + getThumbnail(text, "P120x87") + "\">";
    }

    public static String getPrimaryKeyParam(Object object, String[] primaryKeyFields) {
        try {
            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i < primaryKeyFields.length; i++) {
                if (buffer.length() == 0) {
                    buffer.append("?");
                } else {
                    buffer.append("&");
                }

                buffer.append(primaryKeyFields[i]);
                buffer.append("=");
                Field field = object.getClass().getDeclaredField(primaryKeyFields[i]);
                field.setAccessible(true);
                buffer.append(field.get(object));
            }

            return buffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    public static String getWebColor(int value, int max) {
        int color = (int) (511F * (float)value/(float)max);
        String rgb = null;
        if (color < 256) {
            rgb = Integer.toHexString(new Color(color, 255, 0).getRGB());
        } else {
            rgb = Integer.toHexString(new Color(255, 511 - color, 0).getRGB());
        }
        return "#" + rgb.substring(2, rgb.length());
    }

    public static String getRemoteAddr(HttpServletRequest request){
        String realIP = request.getHeader("X-Daum-IP");
        if(CoreUtil.isEmpty(realIP)){
            realIP = request.getRemoteAddr();
        }
        return realIP;
    }

    public static String getCurDateTime() {
        return getFormat(new Date());
    }

    public static String getFormat(Date date) {
        if (date == null) {
            return "";
        }

        FastDateFormat formatInstance1 = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");
        return  formatInstance1.format(date);
    }

    public static String getNumberFormat(Integer num) {
        return getNumberFormat(new Long(num));
    }

    public static String getNumberFormat(Long num) {
        if (num == null) {
            return "0";
        }
        return new DecimalFormat("#,##0").format(num);
    }

    @SuppressWarnings("rawtypes")
    public static String getFieldValue(Object item, String name) {
        try {
            if (item instanceof Map) {
                return ((Map)item).get(name).toString();
            }

            Field field = item.getClass().getDeclaredField(name);
            field.setAccessible(true);
            return field.get(item).toString();
        } catch (Exception e) {
            return "";
        }
    }
}