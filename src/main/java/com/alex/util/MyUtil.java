package com.alex.util;

import com.alex.bean.User;
import com.alex.interceptor.LoginInterceptor;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.security.AlgorithmParameters;
import java.security.Security;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyUtil {
    /**
     * 根据当前时间生成字符串id
     */
    public static String getStringID() {
        String id;
        //获取当前时间，并使用SimpleDateFormat转换时间格式
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        id = sdf.format(date);
        return id;
    }

    /**
     * 生成100000000内的随机数字符串
     */
    public static String getRandom() {
        int n = (int) (Math.random() * 100000000);
        return n + "";
    }

    /**
     * 判断token是否存在或过期
     *
     * @param token
     * @return
     */
    public static int judgeToken(String token) throws IOException {
        if (token == null) {
            return 1;
        } else {
        	/*
        	获取class对象的方法有三种：
        	1、调用Object类的getClass()方法来得到Class对象
        	2、使用Class类的中静态forName()方法获得与字符串对应的Class对象
        	  3、获取Class类型对象的第三个方法非常简单。如果T是一个Java类型，那么T.class就代表了匹配的类对象。
        	 */
            User user = JwtToken.unsign(token, User.class);
            if (user == null) {
                return 1;
            } else if(user.getRole() != 1){
                return 2;
            } else{
                return 0;
            }
        }
    }

    /**
     * 获取文件扩展名
     */
    public static String getFileExtendsName(String filenname) {
        int lastPointIndex = filenname.lastIndexOf(".");
        //从"."符号开始索引到结束位置
        String extendsName = filenname.substring(lastPointIndex);
        return extendsName;
    }

    /**
     * （汉字）字符串转换为UTF-8编码
     */
    public static String toUTF8String(String str) {
        StringBuffer sb = new StringBuffer();
        int len = str.length();
        for (int i = 0; i < len; i++) {
            //遍历字符串
            char c = str.charAt(i);
            //Unicode 0-255 保持不变
            if (c >= 0 && c <= 255) {
                //append(c)方法的作用是追加内容到当前StringBuffer对象的末尾，类似于字符串的连接。
                // 比String更加节约内容，例如应用于数据库SQL语句的连接，用StringBuffer对象来做sql语句拼接的话会节省很多的内存
                sb.append(c);
            } else {//转换其它的字符
                byte b[];
                try {
                    //Character 类在对象中包装一个基本类型 char 的值
                    //String的getBytes()方法是得到一个操作系统默认的编码格式的字节数组。
                    // String.getBytes(String decode)方法会根据指定的decode编码返回某字符串在该编码下的byte数组表示
                    b = Character.toString(c).getBytes("UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    b = null;
                }
                //转换为无符号integer
                for (int j = 0; j < b.length; j++) {
                    int k = b[j];
                    if (k < 0) {
                        k &= 255;//=k=k&255;k&255=k&0x000000FF,作用是如果k是负数，则会清除前面24个零，正的byte整型不受影响
                    }//k|0xFFFFFF00的作用是，如果k是正数，则置前24位为一，这样toHexString输出一个小于等于15的byte整型的十六进制时，倒数第二位为零且不会被丢弃，这样可以通过substring方法进行截取最后两位即可。
                    //返回整数参数的字符串表示形式 作为十六进制（base16）中的无符号整数
                    //该值以十六进制（base16）转换为ASCII数字的字符串
                    sb.append("%" + Integer.toHexString(k).toUpperCase());
                }
            }
        }
        return sb.toString();
    }

    public static String getTime() {
        SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间
        sdf.applyPattern("yyyy-MM-dd HH:mm:ss");// a为am/pm的标记
        Date date = new Date();// 获取当前时间
        //System.out.println("现在时间：" + sdf.format(date)); // 输出已经格式化的现在时间（24小时制）
        return sdf.format(date);
    }

    public static Date getDate(String date) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.parse(date);
    }

    /**
     * 发起get请求的方法。
     *
     * @param url
     * @return
     */
    public static String GET(String url) {
        String result = "";
        BufferedReader in = null;//字符输入缓冲流
        InputStream is = null;//字节输入流
        InputStreamReader isr = null;//字符输入缓冲流的子类
        try {
			/*
			创建一个和URL的连接，需要如下几个步骤：
            1、创建URL对象，并通过调用openConnection方法获得URLConnection对象；
            2、设置URLConnection参数和普通请求属性；
            3、向远程资源发送请求；
            4、远程资源变为可用，程序可以访问远程资源的头字段和通过输入流来读取远程资源返回的信息。
			 这里需要重点讨论一下第三步：如果只是发送GET方式请求，使用connect方法建立和远程资源的连接即可；
			 如果是需要发送POST方式的请求，则需要获取URLConnection对象所对应的输出流来发送请求。
			 这里需要注意的是，由于GET方法的参数传递方式是将参数显式追加在地址后面，那么在构造URL对象时的参数就应当是包含了参数的完整URL地址，
			 而在获得了URLConnection对象之后，就直接调用connect方法即可发送请求。而POST方法传递参数时仅仅需要页面URL，而参数通过需要通过输出流来传递。另外还需要设置头字段。
			 */
            URL realUrl = new URL(url);
            URLConnection conn = realUrl.openConnection();
            conn.connect();
            Map<String, List<String>> map = conn.getHeaderFields();
            is = conn.getInputStream();
            isr = new InputStreamReader(is);
            in = new BufferedReader(isr);
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            // 异常记录
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (is != null) {
                    is.close();
                }
                if (isr != null) {
                    isr.close();
                }
            } catch (Exception e2) {
                // 异常记录
            }
        }
        return result;
    }

    /**
     * 获取小程序端的code
     *
     * @param code
     * @return
     */
    public static JSONObject getSessionKeyOrOpenId(String code) {
        //微信端登录code
        String wxCode = code;
        String WX_URL =
                "https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code";
        Map<String, String> requestUrlParam = new HashMap<String, String>();
        //替换appid，appsecret，和code
        String requestUrl = WX_URL.replace("APPID", "wxde3f1afb7b27789d").//填写自己的appid
                                                                                  replace("SECRET",
                                                                                          "53bea54de83149417ab0806ea4ccfca8")
                                                                          .replace("JSCODE", code).//填写自己的appsecret，
                                                                                                           replace(
                "authorization_code", "authorization_code");

        //调用get方法发起get请求，并把返回值赋值给returnvalue
        String returnvalue = GET(requestUrl);
        //测试用
        //System.out.println(requestUrl);//打印发起请求的url
        //System.out.println(returnvalue);//打印调用GET方法返回值
        //定义一个json对象。
        JSONObject convertvalue = new JSONObject();

        //将得到的字符串转换为json
        convertvalue = (JSONObject) JSON.parse(returnvalue);
        return convertvalue;
    }

    /**
     * 解密手机号
     *
     * @param session_key
     * @param encryptedData
     * @param iv
     * @return
     */
    public JSONObject getPhoneNumber(String session_key, String encryptedData, String iv) {
        byte[] dataByte = Base64.decode(encryptedData);
        byte[] keyByte = Base64.decode(session_key);
        byte[] ivByte = Base64.decode(iv);
        try {
            int base = 16;
            if (keyByte.length % base != 0) {
                int groups = keyByte.length / base + (keyByte.length % base != 0? 1 : 0);
                byte[] temp = new byte[groups * base];
                Arrays.fill(temp, (byte) 0);
                System.arraycopy(keyByte, 0, temp, 0, keyByte.length);
                keyByte = temp;
            }
            // 初始化
            Security.addProvider(new BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
            AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
            parameters.init(new IvParameterSpec(ivByte));
            cipher.init(Cipher.DECRYPT_MODE, spec, parameters);
            byte[] resultByte = cipher.doFinal(dataByte);
            if (null != resultByte && resultByte.length > 0) {
                String result = new String(resultByte, "UTF-8");
                return JSONObject.parseObject(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 返回手机号
     *
     * @param session_key
     * @param encryptedData
     * @param iv
     * @return
     */
    public String getPhone(String session_key, String encryptedData, String iv) {
        JSONObject obj = getPhoneNumber(session_key, encryptedData, iv);
        System.out.println(obj);
        String phone = obj.get("phoneNumber").toString();
        return phone;

    }

    public static boolean authority() throws IOException {
        String token = LoginInterceptor.globalToken;
        User user = JwtToken.unsign(token, User.class);
        return user == null;
    }
}
