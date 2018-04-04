package com.welch.writer;

import com.welch.factory.TableFactory;
import com.welch.main.Main;
import com.welch.xml.comparator.ControlMComparator;
import com.welch.xml.object.DEFTABLE;
import com.welch.xml.object.ObjectFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;

public class XmlGenerator {

	public static boolean testModel;
	public static String groups;

	private Main main;
	private TableFactory tFactory;
	private ObjectFactory objectFactory;
	private DEFTABLE xml;

	public XmlGenerator(Main main, TableFactory tFactory){
		this.main = main;
		this.tFactory = tFactory;
		this.objectFactory = new ObjectFactory();
		this.xml = this.objectFactory.createDEFTABLE();
	}

	public void generateXml() {
		this.tFactory.addTables(this.xml);
		this.generateFile();
		if(this.main.fullDelta!=null && this.main.fullDelta.equalsIgnoreCase("Delta")){
			this.xml = this.generateDelta();
			this.generateFile();
		}
	}

	private void generateFile() {
		try {
			File file = new File(this.main.outputFile);
			JAXBContext jaxbContext = JAXBContext.newInstance(DEFTABLE.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "ISO-8859-1");
			jaxbMarshaller.setProperty("com.sun.xml.internal.bind.xmlHeaders", "<!DOCTYPE DEFTABLE SYSTEM \"deftable.dtd\">\n");

			StringWriter writer = new StringWriter();
			jaxbMarshaller.marshal(xml, writer);
			FileOutputStream fo = new FileOutputStream(file);
			fo.write(writer.toString().replace("standalone=\"yes\"", "").getBytes());
			fo.flush();
			fo.close();
			writer.close();
			Main.logger.writeToLog("LM_INFOR", "Control M xml file " + this.main.outputFile + " saved successfully");
		} catch (JAXBException e) {
			Main.logger.writeToLog("LM_ERROR", e.getMessage());
		} catch (FileNotFoundException e) {
			Main.logger.writeToLog("LM_ERROR", "File not found " + this.main.outputFile);
			Main.logger.writeToLog("LM_ERROR", e.getMessage());
		} catch (IOException e) {
			Main.logger.writeToLog("LM_ERROR", e.getMessage());
		}
	}
	
	public Element loadXml(String fileName){
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(new File(fileName));
			Element root = doc.getDocumentElement();
			return root;
		} catch (ParserConfigurationException e) {
			Main.logger.writeToLog("LM_ERROR", e.getMessage());
		}	catch (SAXException e) {
			Main.logger.writeToLog("LM_ERROR", e.getMessage());
		} catch (IOException e) {
			Main.logger.writeToLog("LM_ERROR", e.getMessage());
		}
		return null;
	}
	
	private DEFTABLE generateDelta(){
		DEFTABLE xml = this.objectFactory.createDEFTABLE();
		ControlMComparator comparator = new ControlMComparator();
		comparator.compareTable(this.loadXml(this.main.outputFile), this.loadXml(this.main.xmlFile));
		String diffTables = "";
		for(int i=0;i<comparator.getDiffTables().size();i++){
			diffTables += comparator.getDiffTables().get(i)+",";
		}
		Main.logger.writeToLog("LM_INFO", "Different tables are:" + diffTables);
		Main.logger.writeToLog("LM_INFO", "Detail of difference:\n" + comparator.getMsg());
		this.tFactory.addDeltaTables(xml, comparator.getDiffTables());
		return xml;
	}
}
