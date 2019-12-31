package Demo.nio;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.io.DocumentResult;
import org.dom4j.io.DocumentSource;
import org.dom4j.io.HTMLWriter;
import org.dom4j.io.OutputFormat;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.io.StringWriter;

public class XMLTransferHtml {
    public static String getHTMLString(String xmlString, String xslPath) {
        String htmlStr = "";
        try {
            Document doc = DocumentHelper.parseText(xmlString);
            Document transformDoc = transformDocument(doc, xslPath);
            htmlStr = writeString(transformDoc);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return htmlStr;
    }

    private static String writeString(Document transformDoc) {
        StringWriter strWriter = new StringWriter();
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("UTF-8");
        format.setXHTML(true);
        HTMLWriter htmlWriter = new HTMLWriter(strWriter, format);
        format.setExpandEmptyElements(false);
        try {
            htmlWriter.write(transformDoc);
            htmlWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strWriter.toString();
    }

    private static Document transformDocument(Document doc, String xslPath) {
        TransformerFactory factory = TransformerFactory.newInstance();
        Document transformDoc = null;
        try {
            Transformer transformer = factory.newTransformer(new StreamSource(xslPath));
            DocumentSource docSource = new DocumentSource(doc);
            DocumentResult docResult = new DocumentResult();
            transformer.transform(docSource, docResult);
            transformDoc = docResult.getDocument();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
        return transformDoc;
    }
}
