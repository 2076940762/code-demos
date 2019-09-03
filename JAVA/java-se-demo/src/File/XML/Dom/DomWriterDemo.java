package File.XML.Dom;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class DomWriterDemo {
	public static void main(String[] args) throws TransformerException {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder DocBilder = dbFactory.newDocumentBuilder();
			Document xmlDoc = DocBilder.newDocument();
			if (xmlDoc == null) {
				throw new RuntimeException("failed to create xml doc .");
			}

			Element root = xmlDoc.createElement("ROOT");
			xmlDoc.appendChild(root);
			{
				Element java = xmlDoc.createElement("JAVA");
				Attr author = xmlDoc.createAttribute("author");
				author.setValue("123");
				java.setAttributeNode(author);

				root.appendChild(java);
			}

			// 写到文件中
			{
				DOMSource src = new DOMSource(xmlDoc);

				File f = new File("./result.xml");
				StreamResult out = new StreamResult(f);
				TransformerFactory tff = TransformerFactory.newInstance();
				Transformer tf = tff.newTransformer();
				tf.transform(src, out);
			}

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}

	}

}
