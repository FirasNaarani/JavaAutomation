package Framework;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class XMLReader {
    private String xmlFilePath;

    public XMLReader(String xmlFilePath) {
        this.xmlFilePath = xmlFilePath;
    }
    public String getValueByName(String sectionName, String tagName, String nameAttribute) {
        try {
            // Parse the XML file
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputStream inputStream = new FileInputStream(new File(xmlFilePath));
            Document document = builder.parse(inputStream);

            // Get the node list of the specified section
            NodeList sectionNodes = document.getElementsByTagName(sectionName);
            for (int i = 0; i < sectionNodes.getLength(); i++) {
                Element sectionElement = (Element) sectionNodes.item(i);

                // Get the node list of elements within the section
                NodeList elements = sectionElement.getElementsByTagName(tagName);

                // Iterate through the elements to find the one with the specified name attribute
                for (int j = 0; j < elements.getLength(); j++) {
                    Element element = (Element) elements.item(j);
                    String name = element.getAttribute("name");
                    if (name.equals(nameAttribute)) {
                        return element.getAttribute("value");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}