package com.example.wxtest.hello.util;

import com.alibaba.fastjson2.JSONObject;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.Iterator;

/**
 * @author 木子的昼夜编程
 */
public class XmlUtil {


  /**
   * 最简单的解析  单层 不嵌套
  * */
  public static JSONObject parsXml(String xml){
    JSONObject res =new JSONObject();
    try {
      Document document = DocumentHelper.parseText(xml);
      Element root = document.getRootElement();    //获得根节点
      for(Iterator i = root.elementIterator(); i.hasNext();){
        Element element = (Element) i.next();
        res.put(element.getName(), element.getText());
      }
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("解析失败！！");
    }
    return res;
  }
}
