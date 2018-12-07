package com.cafe.d4jtest.more;


import java.io.File;
import java.util.Iterator;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/*
 * 方法traversalDocumentByIterator()提供一种基于迭代的遍历实现，
 * 每个Element通过elementIterator()和attributeIterator()取代其子元素和属性的迭代器。
 */
public class XmlIterator {

    private File inputXml;
    
    public XmlIterator(File inputXml) {
       this.inputXml = inputXml;
    }

    public static void main(String[] argv) {
        XmlIterator dom4jParser = new XmlIterator(new File("students-gen.xml"));
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
    
    /*
     * 该方法只是枚举了两层，如果一直挖的话要用递归方法
     */
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
               System.out.println(attribute.getName() + ":"
                      + attribute.getData());
            }
            
            // 枚举当前节点下所有子节点
            for (Iterator ieson = element.elementIterator(); ieson.hasNext();) {
               Element elementSon = (Element) ieson.next();
               System.out.println(elementSon.getName() + ":"+ elementSon.getText());
            }
        }
     }
    
    
    
    
}