package com.alex.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JwtToken
 */
public class JwtToken {

    //密钥
    private static final String SECRET = "secret";

    //jackson
    private static ObjectMapper mapper = new ObjectMapper();

    /**
     * header数据
     * @return
     */
    private static Map<String, Object> createHead() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("typ", "JWT");
        map.put("alg", "HS256");
        return map;
    }

    /**
     * 生成token（生成令牌）
     *
     * @param obj    对象数据
     * @param maxAge 有效期
     * @param <T>
     * @return
     */
    public static <T> String sign(T obj, long maxAge) throws UnsupportedEncodingException, JsonProcessingException {
        //构建JWT，创建一个JWTCreate实例，下面的是设置了header和payload
        JWTCreator.Builder builder = JWT.create();
        //mapper.writeValueAsString(obj)的意思是把对象转化为Json格式的字符串
        builder.withHeader(createHead())//header
                .withSubject(mapper.writeValueAsString(obj));  //payload

        if (maxAge >= 0) {
            //返回当前的计算机时间，时间的表达格式为当前计算机时间和GMT时间（格林威治时间）1970年1月1号0时0分0秒所差的毫秒数
            long expMillis = System.currentTimeMillis() + maxAge;
            //把上面的方法强制转换成date类型
            Date exp = new Date(expMillis);
            //在jwt中设置了过期时间，把上面的exp作为参数
            builder.withExpiresAt(exp);
        }
        //设置加密算法为HMAC256（因为前面的header类型是HS256)，并签名后返回
        return builder.sign(Algorithm.HMAC256(SECRET));
    }

    /**
     * 解密（验证令牌）
     * @param token   token字符串
     * @param classT  解密后的类型
     * @param <T>
     * @return
     */
    public static <T> T unsign(String token, Class<T> classT) throws IOException {
       /*
        首先需要通过调用jwt.require()和传递算法实例来创建一个JWTVerifier实例,
        方法build()返回的实例是可重用的，因此您可以定义一次，并使用它来验证不同的标记。
        最后调用verifier.verify()来验证token
        */
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
        try{
            //公共接口DecodeJWT，有这些方法getAudience, getClaim, getClaims, getExpiresAt, getId, getIssuedAt, getIssuer, getNotBefore, getSubject
            DecodedJWT jwt = verifier.verify(token);
            Date exp = jwt.getExpiresAt();//返回该jwt的过期时间转换为date类型,如果在负载中没有定义则返回null
            //测试过期时间是否存在和exp.after(new Date())测试过期时间是否在当前时间之后
            if(exp!=null&&exp.after(new Date())){
                //返回jwt所面向的用户的值,如果没有在负载中定义则返回null
                String subject = jwt.getSubject();
                //mapper.readValue()的意思是把对象转化为字符串
                return mapper.readValue(subject, classT);
            }
        }catch (TokenExpiredException e){
            e.printStackTrace();
        }
        return null;
    }

}
