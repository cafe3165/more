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
 

public class XMLGenforScen {


    /**
     * @return
     */
    public Document generateDocumentByMethod() {
        
       Document document = DocumentHelper.createDocument();
       // ProcessingInstruction
       Map<String, String> inMap = new HashMap<String, String>();
       inMap.put("type", "text/xsl");
       inMap.put("href", "devices.xsl");
       document.addProcessingInstruction("xml-stylesheet", inMap);
       // root element
       Element devicesElement = document.addElement("Devices");
       devicesElement.addComment("An Devices Catalog");
       // 空调
       Element devElement = devicesElement.addElement("Device");
       devElement.addAttribute("Dno", "01");
       Element DNameElement = devElement.addElement("DName");
       DNameElement.setText("Air-Condition");
       Element LNameElement = devElement.addElement("LName");
       LNameElement.setText("Bedroom");
       Element CTypeElement = devElement.addElement("CTpye");
       CTypeElement.setText("Temperature");
       
       Element FuntionsElement = devElement.addElement("Functions");
       //devicesElement.addComment("An Devices Catalog");
       Element funElement1 = FuntionsElement.addElement("Funtion");
       funElement1.addAttribute("fn", "1");
       Element funNameElement1 = funElement1.addElement("Fname");
       funNameElement1.setText("Increse");
       
       Element funElement2 = FuntionsElement.addElement("Funtion");
       funElement2.addAttribute("fn", "2");
       Element funNameElement2 = funElement2.addElement("Fname");
       funNameElement2.setText("Reduce");
       
       Element funElement3 = FuntionsElement.addElement("Funtion");
       funElement3.addAttribute("fn", "3");
       Element funNameElement3 = funElement3.addElement("Fname");
       funNameElement3.setText("Assign");
       
       
       Element funElement4 = FuntionsElement.addElement("Funtion");
       funElement4.addAttribute("fn", "4");
       Element funNameElement4 = funElement4.addElement("Fname");
       funNameElement4.setText("Monitor");
		   /*
		    * 智能电灯
		    */
      
       
       Element devElement2 = devicesElement.addElement("Device");
       devElement2.addAttribute("Dno", "02");
       Element DNameElement2 = devElement2.addElement("DName");
       DNameElement2.setText("Smart-Light");
       Element LNameElement2 = devElement2.addElement("LName");
       LNameElement2.setText("Sitting-room");
       Element CTypeElement2 = devElement2.addElement("CTpye");
       CTypeElement2.setText("Brightness");
       
       Element FuntionsElement2 = devElement2.addElement("Functions");
       //devicesElement.addComment("An Devices Catalog");
       Element funElement12 = FuntionsElement2.addElement("Funtion");
       funElement12.addAttribute("fn", "1");
       Element funNameElement12 = funElement12.addElement("Fname");
       funNameElement12.setText("Increse");
       
       Element funElement22 = FuntionsElement2.addElement("Funtion");
       funElement22.addAttribute("fn", "2");
       Element funNameElement22 = funElement22.addElement("Fname");
       funNameElement22.setText("Reduce");
   
       Element funElement32 = FuntionsElement2.addElement("Funtion");
       funElement32.addAttribute("fn", "3");
       Element funNameElement32 = funElement32.addElement("Fname");
       funNameElement32.setText("Monitor");
//       
       /*
	    * 智能水泵
	    */
  
       Element devElement3 = devicesElement.addElement("Device");
       devElement3.addAttribute("Dno", "03");
       Element DNameElement3 = devElement3.addElement("DName");
       DNameElement3.setText("Smart-Water-Pump");
       Element LNameElement3 = devElement3.addElement("LName");
       LNameElement3.setText("Balcony");
       Element CTypeElement3 = devElement3.addElement("CTpye");
       CTypeElement3.setText("Humidty");
       
       Element FuntionsElement3 = devElement3.addElement("Functions");
       //devicesElement.addComment("An Devices Catalog");
       Element funElement13 = FuntionsElement3.addElement("Funtion");
       funElement13.addAttribute("fn", "1");
       Element funNameElement13 = funElement13.addElement("Fname");
       funNameElement13.setText("Increse");
       
       
       
       
 
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
       XmlGenforDevice dom4j = new XmlGenforDevice();
       Document document = null;
        //通过方法生成
        document=dom4j.generateDocumentByMethod();
        
        //通过字符串生成
        //document = dom4j.generateDocumentByString();        
       dom4j.saveDocument(document, new File("devices-gen.xml"));
    }
}
