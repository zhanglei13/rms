/**   
 * 简述
 * <p>详细说明第一行<br>    
 * 详细说明第二行 
 * @date 2015年4月7日 下午9:08:35   
 * @author zhanglei   
 * @version V1.0   
 */
package com.lenovo.rms.common.util;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializerProvider;

/**
 * 简述
 * <p>
 * 详细说明第一行<br>
 * 详细说明第二行
 * 
 * @date 2015年4月7日 下午9:08:35
 * @author zhanglei
 * @version V1.0
 */
public class ObjectMappingCustomer extends ObjectMapper {

	public ObjectMappingCustomer() {
		super();
		// // 允许单引号
		// this.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		// // 字段和值都加引号
		// this.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		// // 数字也加引号
		// this.configure(JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS, true);
		// this.configure(JsonGenerator.Feature.QUOTE_NON_NUMERIC_NUMBERS,
		// true);
		// 空值处理为空串
		this.getSerializerProvider().setNullValueSerializer(
				new JsonSerializer<Object>() {
					@Override
					public void serialize(Object value, JsonGenerator jg,
							SerializerProvider sp) throws IOException,
							JsonProcessingException {
						jg.writeString("");
					}
				});
	}
}