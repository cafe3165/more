package com.cafe.d4jtest.more;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
 
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
 


/*
 * 创建XML文档
 */
public class XmlGen {
    
    /*
     * 方法generateDocumentByMethod()通过调用方法构建xml文档：
        1.使用DocumentHelper得到Document实例
        Document document = DocumentHelper.createDocument();
        2.创建Processing Instruction
        document.addProcessingInstruction("xml-stylesheet", inMap);
        3.创建元素Element
        Element studentsElement = document.addElement("students");
        4.为元素添加注释Comment
        studentsElement.addComment("An Student Catalog");
        5.为元素添加属性
        studentsElement.addComment("An Student Catalog");
        6.为元素添加文本值Text
        ageElement.setText("18");
     */
    public Document generateDocumentByMethod() {
        
       Document document = DocumentHelper.createDocument();
       // ProcessingInstruction
       Map<String, String> inMap = new HashMap<String, String>();
       inMap.put("type", "text/xsl");
       inMap.put("href", "students.xsl");
       document.addProcessingInstruction("xml-stylesheet", inMap);
       // root element
       Element studentsElement = document.addElement("students");
       studentsElement.addComment("An Student Catalog");
       // son element
       Element stuElement = studentsElement.addElement("student");
       stuElement.addAttribute("sn", "01");
       Element nameElement = stuElement.addElement("name");
       nameElement.setText("sam");
       Element ageElement = stuElement.addElement("age");
       ageElement.setText("18");
       // son element
       Element anotherStuElement = studentsElement.addElement("student");
       anotherStuElement.addAttribute("sn", "02");
       Element anotherNameElement = anotherStuElement.addElement("name");
       anotherNameElement.setText("lin");
       Element anotherAgeElement = anotherStuElement.addElement("age");
       anotherAgeElement.setText("20");
       
    // son element
       Element StuElement2 = studentsElement.addElement("student");
      StuElement2.addAttribute("sn", "03");
       Element NameElement2 = StuElement2.addElement("name");
       NameElement2.setText("Huang");
       Element AgeElement2 = StuElement2.addElement("age");
       AgeElement2.setText("23");
       
       
       
       
       
       
       
 
       return document;
    }
 
    
    /*
     * 方法generateDocumentByString()通过字符串转换直接构建xml文档，使用DocumentHelper.parseText()来实现.
     * document = DocumentHelper.parseText(text);
     */
    public Document generateDocumentByString() {
       String text = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
              "<?xml-stylesheet type=\"text/xsl\" href=\"students.xsl\"?>" +
              "<students><!--An Student Catalog-->   <student sn=\"01\">" +
              "<name>sam</name><age>18</age></student><student sn=\"02\">" +
              "<name>lin</name><age>20</age></student></students>";
       Document document = null;
       try {
           document = DocumentHelper.parseText(text);
       } catch (DocumentException e) {
           e.printStackTrace();
       }
       return document;
    }
 
    public void saveDocument(Document document, File outputXml) {
       try {
           // 美化格式
           OutputFormat format = OutputFormat.createPrettyPrint();
           /*// 缩减格式
           OutputFormat format = OutputFormat.createCompactFormat();*/
           /*// 指定XML编码
            format.setEncoding("GBK");*/
           XMLWriter output = new XMLWriter(new FileWriter(outputXml), format);
           output.write(document);
           output.close();
       } catch (IOException e) {
           System.out.println(e.getMessage());
       }
    }
 
    public static void main(String[] argv) {
       XmlGen dom4j = new XmlGen();
       Document document = null;
        //通过方法生成
        document=dom4j.generateDocumentByMethod();
        
        //通过字符串生成
        //document = dom4j.generateDocumentByString();        
       dom4j.saveDocument(document, new File("students-gen.xml"));
    }
}