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
public class ContextIter {

    private File inputXml;
    
    public ContextIter(File inputXml) {
       this.inputXml = inputXml;
    }

    public static void main(String[] argv) {
        XmlIterator dom4jParser = new XmlIterator(new File("Context.xml"));
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
    /**
     * 
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
//             枚举当前节点下所有子节点
//            for (Iterator ieson = element.elementIterator(); ieson.hasNext();) {
//               Element elementSon = (Element) ieson.next();
//               System.out.println(elementSon.getName() + ":"+ elementSon.getText());
//            }
            
            for (Iterator ieson = element.elementIterator(); ieson.hasNext();) {
            	
            	
                Element elementSon = (Element) ieson.next();
                if(elementSon.getName()!="Functions")
                	System.out.println(elementSon.getName() + ":"+ elementSon.getText());
                else {
                	//如果是Funtions的话，就以Funtions为根节点，迭代遍历其下的各个Funtion
                	for(Iterator ifun = elementSon.elementIterator(); ifun.hasNext();) {
                		Element efun=(Element)ifun.next();
                		System.out.println(efun.getName());
                		
                		//遍历Function属性值 如 fn:1
                		for (Iterator ifuna = efun.attributeIterator(); ifuna.hasNext();) {
                            Attribute funattribute = (Attribute) ifuna.next();
                            System.out.println(funattribute.getName() + ":"
                                   + funattribute.getData());
                         }
                		//遍历Funtion下的各个功能 如 Fname:Increse
                		 for (Iterator ifunson = efun.elementIterator(); ifunson.hasNext();) {
                			 
                			 Element efunSon = (Element) ifunson.next();
                             if(efunSon.getName()!="Functions")
                             	System.out.println(efunSon.getName() + ":"+ efunSon.getText());
                		 }
                		 
                		
                		
                	}

                	
                }
                //end if-else
                
                
                
                
                
                
             }
            
        }
     }
    
      
    
}