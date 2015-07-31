package com.facerecognition.common.parser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.facerecognition.common.model.SubjectLabel;

public class ClassifierXmlParser {

	public static List<SubjectLabel> getLabelsForClassifier(File classifierPath) {
		List<SubjectLabel> labelList = new ArrayList<SubjectLabel>();
		try {
			DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
			Document doc = dBuilder.parse(classifierPath);
			XPath xPath = XPathFactory.newInstance().newXPath();
			String expression = "/opencv_storage/labelsInfo/*";
			NodeList subjectLabelNode = (NodeList) xPath.compile(expression)
					.evaluate(doc, XPathConstants.NODESET);
			for (int i = 0; i < subjectLabelNode.getLength(); i++) {
				Node pair = subjectLabelNode.item(i);
				Node label = pair.getChildNodes().item(1);
				Node name = pair.getLastChild();
				labelList.add(new SubjectLabel(Integer.parseInt(label
						.getTextContent()), name.getTextContent()));
			}
		} catch (SAXException | IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		return labelList;
	}
}
