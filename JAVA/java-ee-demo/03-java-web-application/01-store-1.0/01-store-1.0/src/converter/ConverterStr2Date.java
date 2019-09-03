package converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.commons.beanutils.Converter;

/**
 * 转换string为date
 * 
 * @author qingtian
 *
 */
public class ConverterStr2Date implements Converter {

	@Override
//    * @param type Data type to which this value should be converted
//    * @param value The input value to be converted
//    * @return The converted value
	public Object convert(Class class1, Object value) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			return format.parse((String) value);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

}
