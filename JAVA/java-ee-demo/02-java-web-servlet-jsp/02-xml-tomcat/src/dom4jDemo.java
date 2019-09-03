import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;
import org.xml.sax.SAXException;

public class dom4jDemo {

	@Test
	public void f1() throws DocumentException {
		SAXReader reader = new SAXReader();
		Document doc = reader.read("E:\\Eclipse-Workspace\\java-ee\\xml-tomcat\\xml-demo\\timezones.xml");
		Element rootElement = doc.getRootElement();
		List<Element> elements = rootElement.elements();
		for (Element element : elements) {
			System.out.println(element.getName());
		}

		String attributeValue = rootElement.attributeValue("GeneratedAt");
		System.out.println(attributeValue);
	}

	@Test
//	Xpath
	public void f2() throws SAXException, DocumentException {
		SAXReader reader = new SAXReader();
		Document doc = reader.read("E:\\Eclipse-Workspace\\java-ee\\xml-tomcat\\xml-demo\\timezones.xml");
		Element rootElement = doc.getRootElement();
		List selectNodes = rootElement.selectNodes("//Zone");
		for (Object object : selectNodes) {
			System.out.println(object);
		}
		
	}

}
