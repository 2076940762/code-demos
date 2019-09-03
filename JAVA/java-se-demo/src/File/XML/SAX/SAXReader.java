package File.XML.SAX;

import java.io.IOException;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

public class SAXReader {
	public static void main(String[] args) throws SAXException, IOException {
		XMLReader reader = XMLReaderFactory.createXMLReader();
		reader.setContentHandler(new ReaderHander());
		reader.parse("E:\\Code-Demo\\JavaSEDemo\\XmlDoc\\books.xml");

	}

	public static class ReaderHander extends DefaultHandler {
		// xml�ĵ�����ʱ
		public void startDocument() throws SAXException {
			System.out.println("begain");
		}

		// �ĵ���������
		public void endDocument() throws SAXException {
			System.err.println("end123");
		}

		// ����ĳһ��Ԫ��
		public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
			
		}

		// ��������Ԫ��
		public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
		}

		// ����Ԫ������
		public void characters(char[] ch, int start, int length) {
			System.out.println(new String(ch,start,length));
		}
	}
}
