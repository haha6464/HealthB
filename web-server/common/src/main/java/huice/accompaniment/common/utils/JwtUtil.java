package huice.accompaniment.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 哈尔滨广厦学院JWT系统
 */
public class JwtUtil {

    /**
     * 生成密文
     * @return
     */
    public static String createJWT(String id){

        //构建jwt令牌
        JwtBuilder builder = Jwts.builder();
        builder.setIssuer("huicejitua1"); //颁发者
        builder.setIssuedAt(new Date()); //颁发时间
        builder.setSubject("huicejitua1");   //主题
        builder.setExpiration(new Date(System.currentTimeMillis()+3600000*24*7));  //过期时间 设置3600秒

        //自定义信息 自定义载荷
        Map<String,Object> map = new HashMap<>();
        //防止不能存储Long
        String idString = String.valueOf(id);
        map.put("id",idString);

        builder.addClaims(map); //添加载荷

        //1.算法 2.密钥
        builder.signWith(SignatureAlgorithm.HS256, "huicejitua1");

        //生成密文
        String jstString = builder.compact();


        return jstString;
    }


    /**
     * 解析
     * @param token
     * @return
     */
    public static Map<String,Object> analysis(String token){
        Claims body = Jwts.parser()
                //密钥
                .setSigningKey("huicejitua1")
                //要解析的令牌
                .parseClaimsJws(token)
                //获取解析后的数据
                .getBody();
        return body;
    }
}