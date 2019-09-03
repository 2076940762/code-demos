package File.XML.Dom;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class treeXml_Dom {
	public static String strPrefix = "";

	public static void main(String[] args) {
		String strFile = "";
		Scanner sc = new Scanner(System.in);
		System.out.println("please input xml file name :");
		strFile = sc.nextLine();
		showXmlTree(strFile);
		sc.close();
	}

	public static void showXmlTree(String strFilePath) {
		File xmlFile = new File(strFilePath);
		if (!xmlFile.exists()) {
			throw new RuntimeException(strFilePath + " is not exists.");
		}

		DocumentBuilderFactory docBuilderFac = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder docBulider = docBuilderFac.newDocumentBuilder();
			Document xmlDoc = docBulider.parse(strFilePath);
			showXmlTree(xmlDoc);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException | IOException e) {
			e.printStackTrace();
		}
	}

	private static void showXmlTree(Document xmlDoc) {
		NodeList subNodeLst = xmlDoc.getChildNodes();
		for (int i = 0; i < subNodeLst.getLength(); i++) {
			Node subNode = subNodeLst.item(i);

			// ½ÚµãÔªËØ
			if (subNode.getNodeType() == Node.ELEMENT_NODE) {
				showNode(subNode);
				strPrefix += "|----";
				showXmlTree(subNode);
				if (strPrefix.length() > 4) {
					strPrefix = "|" + strPrefix.substring(4);
				}
			}
		}
	}

	private static void showXmlTree(Node currNode) {
		NodeList subNodeList = currNode.getChildNodes();
		for (int j = 0; j < subNodeList.getLength(); j++) {
			Node subNode = subNodeList.item(j);
			if (subNode.getNodeType() == Node.ELEMENT_NODE) {
				showNode(subNode);
				strPrefix += "----";
				showXmlTree(subNode);
				if (strPrefix.length() > 4) {
					strPrefix = strPrefix.substring(0, strPrefix.length() - 4);
				}
			}
		}

	}

	private static void showNode(Node n) {
		{
			String strNodeName = n.getNodeName().replaceAll("\\s", "");
			String strContent = "";
			if (n.hasChildNodes() && n.getFirstChild().getNodeType() == Node.TEXT_NODE
					&& n.getFirstChild().hasChildNodes() == false) {
				strContent = n.getFirstChild().getNodeValue().replaceAll("\\s", "");
			}
			NamedNodeMap attrMap = n.getAttributes();
			String strAttrs = "[";
			for (int i = 0; i < attrMap.getLength(); i++) {
				Node attr = attrMap.item(i);
				strAttrs += attr.getNodeName() + "=" + attr.getNodeValue() + ",";
			}
			if (strAttrs.length() > 1) {
				strAttrs = strAttrs.substring(0, strAttrs.length() - 1) + "]";
			} else {
				strAttrs = "";
			}
			System.out.println(strPrefix + strNodeName + strAttrs + ":" + strContent);
		}
	}
}
