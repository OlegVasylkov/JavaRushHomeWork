package com.javarush.test.level33.lesson10.bonus01;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;
import java.util.regex.Pattern;

import org.w3c.dom.*;


/* Комментарий внутри xml
Реализовать метод toXmlWithComment, который должен возвращать строку - xml представление объекта obj.
В строке перед каждым тэгом tagName должен быть вставлен комментарий comment.
Сериализация obj в xml может содержать CDATA с искомым тегом. Перед ним вставлять комментарий не нужно.

Пример вызова:  toXmlWithComment(firstSecondObject, "second", "it's a comment")
Пример результата:
<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<first>
    <!--it's a comment-->
    <second>some string</second>
    <!--it's a comment-->
    <second>some string</second>
    <!--it's a comment-->
    <second><![CDATA[need CDATA because of < and >]]></second>
    <!--it's a comment-->
    <second/>
</first>
*/
public class Solution {

    public static void main(String[] args) throws Exception
    {
        String result = toXmlWithComment(new AnExample(), "needCDATA", "it's a comment - <needCDATA>");
        System.out.println(result);
    }


    @XmlType(name = "anExample")
    @XmlRootElement
    public static class AnExample {
        public String[] needCDATA = new String[]{"need CDATA because of < and >", "", "some string"};
    }

    public static String toXmlWithComment(Object obj, String tagName, String comment) throws Exception
    {
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

        JAXBContext context = JAXBContext.newInstance(obj.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        marshaller.marshal(obj, doc);

        NodeList nodeList = doc.getElementsByTagName("*");
        for (int i = 0; i < nodeList.getLength(); i++){
            Node node = nodeList.item(i);
            if (node.getNodeName().equals(tagName))
            {
                node.getParentNode().insertBefore(doc.createComment(comment), node);
            }
            replaceTextWithCDATA(node, doc);
        }

        Transformer tf = TransformerFactory.newInstance().newTransformer();
        tf.setOutputProperty(OutputKeys.INDENT, "yes");
        doc.getAttributes();

        tf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

        StringWriter out = new StringWriter();
        tf.transform(new DOMSource(doc), new StreamResult(out));
        return out.toString();
    }

    private static void replaceTextWithCDATA(Node node, Document doc) {
        if ((node.getNodeType() == 3) && (Pattern.compile("[<>&'\"]").matcher(node.getTextContent()).find())) {
            Node cnode = doc.createCDATASection(node.getNodeValue());
            node.getParentNode().replaceChild(cnode, node);
        }
        NodeList list = node.getChildNodes();
        for (int i = 0; i < list.getLength(); i++) {
            replaceTextWithCDATA(list.item(i), doc);
        }
    }
}

