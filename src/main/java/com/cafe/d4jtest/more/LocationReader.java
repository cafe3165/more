package com.cafe.d4jtest.more;

import java.io.File;
import java.util.Iterator;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class LocationReader {

	private File inputXml;

	public LocationReader(File inputXml) {
		this.inputXml = inputXml;
	}

	public static void main(String[] argv) {
		LocationReader dom4jParser = new LocationReader(new File("Location.xml"));
		dom4jParser.traversalDocumentByIterator();
	}

	public Element getRootElement() {
		return getDocument().getRootElement();
	}

	public Document getDocument() {
		SAXReader saxReader = new SAXReader();
		Document document = null;
		try {
			document = saxReader.read(inputXml);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return document;
	}

	public void traversalDocumentByIterator() {
		Element root = getRootElement();
		// 枚举根节点下所有子节点
		for (Iterator ie = root.elementIterator(); ie.hasNext();) {
			System.out.println("======");
			Element element = (Element) ie.next();
			System.out.println(element.getName());
			// 枚举属性
			for (Iterator ia = element.attributeIterator(); ia.hasNext();) {
				Attribute attribute = (Attribute) ia.next();
				System.out.println(attribute.getName() + ":" + attribute.getData());
			}
			for (Iterator ieson = element.elementIterator(); ieson.hasNext();) {
				Element elementSon = (Element) ieson.next();
				System.out.println(elementSon.getName() + ":" + elementSon.getText());

			}
		}

	}

}
