package com.cafe.d4jtest.more;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
 
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
 
/*
 * 这里使用xpath来定位待修改的元素和属性，需要jaxen的支持。
 *示例中将students-gen.xml的第一个student元素的sn属性改为001，其子元素name内容改为jeff。
 *XmlMod.java
 */
public class XmlMod {
    
    /*
     * 1.使用File定位文件资源，并基于此获得Document实例
     *SAXReader saxReader = new SAXReader();
     *Document document = saxReader.read(inputXml);
     *
     *2.Document实例的selectNodes方法可以传入xpath，并返回一个List实例，基于此使用迭代器，完成特定的应用
     *List list = document.selectNodes("//students/student/@sn");
     */
    public void modifyDocument(File inputXml) {
       try {
           SAXReader saxReader = new SAXReader();
           Document document = saxReader.read(inputXml);
           
           List list = document.selectNodes("//students/student/@sn");
           Iterator iter = list.iterator();
           while (iter.hasNext()) {
              Attribute attribute = (Attribute) iter.next();
              if (attribute.getValue().equals("01"))
                  attribute.setValue("001");
           }
           list = document.selectNodes("//students/student");
           iter = list.iterator();
           while (iter.hasNext()) {
              Element element = (Element) iter.next();
              Iterator iterator = element.elementIterator("name");
              while (iterator.hasNext()) {
                  Element nameElement = (Element) iterator.next();
                  if (nameElement.getText().equals("sam"))
                     nameElement.setText("jeff");
              }
           }
           XMLWriter output = new XMLWriter(new FileWriter(new File(
                  "students-modified.xml")));
           output.write(document);
           output.close();
       }
 
       catch (DocumentException e) {
           System.out.println(e.getMessage());
       } catch (IOException e) {
           System.out.println(e.getMessage());
       }
    }
 
    /*
     * selectSingleNode如果有多个只取第一个
     */
    public void modifyDocument2(File inputXml){
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(inputXml);
            Node nodeAttr=document.selectSingleNode("//students/student/@sn");
            System.out.println(nodeAttr.getText());
            nodeAttr.setText("nodeAttr");
            
            Node nodeEle=document.selectSingleNode("//students/student/name");
            System.out.println(nodeEle.getText());
            nodeEle.setText("nodeEle");    
            
               XMLWriter output = new XMLWriter(new FileWriter(new File(
                       "students-modified2.xml")));
                output.write(document);            
                output.close();        
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }
    
    
    
    
    public static void main(String[] argv) {
       XmlMod dom4jParser = new XmlMod();
       //dom4jParser.modifyDocument(new File("students-gen.xml"));
       dom4jParser. modifyDocument(new File("students-gen.xml"));
    }
}