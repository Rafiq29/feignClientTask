package org.herb.feignclienttask.controller;

import lombok.RequiredArgsConstructor;
import org.herb.feignclienttask.util.FeignServiceUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;

@RestController
@RequiredArgsConstructor
public class FeignController {
    private final FeignServiceUtil serviceUtil;

    @GetMapping("/convert")
    public double convert(String date, String curr, double amount) {
        double converted = 0;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new InputSource(new StringReader(serviceUtil.getXML(date))));
            doc.getDocumentElement().normalize();
            NodeList nodes = doc.getElementsByTagName("Valute");
            for (int i = 0; i < nodes.getLength(); i++) {
                Node node = nodes.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    if (element.getAttribute("Code").equals(curr.toUpperCase())) {
                        double value = Double.parseDouble(
                                element.getElementsByTagName("Value").item(0).getTextContent());
                        converted = amount / value;
                        break;
                    }
                }
            }
        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new RuntimeException(e);
        }
        return converted;
    }
}
