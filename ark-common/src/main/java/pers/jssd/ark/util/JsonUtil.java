package pers.jssd.ark.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

/**
 * JSON转换工具类
 * 依赖jackson
 *
 * @author jssdjing@gmail.com
 */
public class JsonUtil {
    
    /**
     * 定义jackson对象
     */
    private static final ObjectMapper MAPPER = new ObjectMapper();
    
    /**
     * 将对象转换成json字符串。 如果要转换的对象为null, 则转换成的json字符串为null
     * <p>Title: pojoToJson</p>
     * <p>Description: </p>
     *
     * @param data 需要转换的java对象
     * @return 返回一个json串
     */
    public static String objectToJson(Object data) {
        try {
            return MAPPER.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 将json结果集转化为对象
     *
     * @param jsonData json数据
     * @param beanType 对象中的object类型
     * @return 返回一个队形的java对象
     */
    public static <T> T jsonToPojo(String jsonData, Class<T> beanType) {
        try {
            return MAPPER.readValue(jsonData, beanType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 将json数据转换成pojo对象list
     *
     * <p>Title: jsonToList</p>
     * <p>Description:
     * </p>
     *
     * @param jsonData json数据
     * @param beanType 转换成的javaBean数据类型
     * @return 返回一个存储对应javaBean数据类型的List
     */
    public static <T> List<T> jsonToList(String jsonData, Class<T> beanType) {
        JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, beanType);
        try {
            return MAPPER.readValue(jsonData, javaType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
}
