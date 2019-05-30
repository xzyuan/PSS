package com.example.demo;

import com.example.demo.dao.EventDao;
import com.example.demo.model.Event;
import com.example.demo.util.ShaEncodeUtil;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {




    @Autowired
    private EventDao eventDao;

    @Test
    public void contextLoads() throws JDOMException, IOException {
        SAXBuilder saxBuilder = new SAXBuilder();
        //读取prop.xml资源
        Document doc = saxBuilder.build("C:\\Users\\xie\\Desktop\\demo\\src\\main\\resources\\passwd.xml");
        Element root = doc.getRootElement();
        List<Element> accountList = root.getChildren("account");
        for (int i = 0; i < accountList.size(); i++) {
            String name = accountList.get(i).getChild("name").getValue();
            String pwd = accountList.get(i).getChildren("sha-pwd").get(0).getValue();
            if (pwd == null || pwd.equals("")) {
                accountList.get(i).getChild("sha-pwd").setText(ShaEncodeUtil.sha256Encode("JSQ45678"));
            }
            System.out.println(accountList.get(i).getChild("fullname").getValue());
            System.out.println(accountList.get(i).getChild("email").getValue());
        }
        XMLOutputter outputter=new XMLOutputter();
        outputter.output(doc,new FileOutputStream("C:\\Users\\xie\\Desktop\\demo\\src\\main\\resources\\passwd.xml"));
    }

}
