package com.welch.xml.comparator;

import org.w3c.dom.*;

import java.util.ArrayList;

public class ControlMComparator {
	private StringBuilder msg = new StringBuilder("");
	private ArrayList<String> diffTables = new ArrayList<String>();
	
	/*public static void main(String[] args) {
		System.out.println(new Date());
		ControlMComparator comparator = new ControlMComparator();
		
		String file1 = "C:\\Welch\\Work\\SPARC\\Tooling\\Test\\1.xml";
		String file2 = "C:\\Welch\\Work\\SPARC\\Tooling\\Test\\2.xml";
		
		try{
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			
			Document doc1 = db.parse(new File(file1));
			Element root1 = doc1.getDocumentElement();
			
			Document doc2 = db.parse(new File(file2));
			Element root2 = doc2.getDocumentElement();
			
			System.out.println(new Date());
			
			comparator.compareTable(root1, root2);
			
			System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			System.out.println(comparator.getMsg());
			System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			for(int i=0;i<comparator.getDiffTables().size();i++){
				System.out.println(comparator.getDiffTables().get(i));
			}
			System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			System.out.println(new Date());
		}catch (Exception e){
			e.printStackTrace();
		}
	}*/

	//Compare tale, input is DEFTABLE, key is TABLE_NAME
	public void compareTable(Element src, Element org){
		//System.out.println(src.getNodeName());
		if(!org.getNodeName().equalsIgnoreCase("DEFTABLE")){
			this.msg.append("Please provide a valid control M xml file for comparison.\n");
			return;
		}
		for(int i=0; i<src.getChildNodes().getLength(); i++){
			if(src.getChildNodes().item(i).getNodeType() != Node.ELEMENT_NODE){
				continue;
			}
			boolean isNew = true;
			Element srcTable = (Element) src.getChildNodes().item(i);
			Element orgTalbe = null;
			for(int j=0; j<org.getChildNodes().getLength(); j++){
				//System.out.println(org.getChildNodes().item(j).getNodeType());
				if(org.getChildNodes().item(j).getNodeType() != Node.ELEMENT_NODE){
					continue;
				}
				orgTalbe = (Element) org.getChildNodes().item(j);
				if(srcTable.getAttribute("TABLE_NAME").equals(orgTalbe.getAttribute("TABLE_NAME"))){
					isNew = false;
					break;
				}
			}
			if(isNew){
				this.msg.append("New table is found "+srcTable.getAttribute("TABLE_NAME")+"\n");
				this.diffTables.add(srcTable.getAttribute("TABLE_NAME"));
				continue;
			}else{
				//Compare number of children
				if(srcTable.getChildNodes().getLength()!=orgTalbe.getChildNodes().getLength()){
					this.msg.append("Number of jobs is different on table "+srcTable.getAttribute("TABLE_NAME")+"\n");
					this.diffTables.add(srcTable.getAttribute("TABLE_NAME"));
					continue;
				}
				//Compare table attributes
				NamedNodeMap srcTabAttrs = srcTable.getAttributes();
				NamedNodeMap orgTabAttrs = orgTalbe.getAttributes();
				if(srcTabAttrs.getLength()!=orgTabAttrs.getLength()){
					this.msg.append("Number of attribute is different on table "+srcTable.getAttribute("TABLE_NAME")+"\n");
					this.diffTables.add(srcTable.getAttribute("TABLE_NAME"));
					continue;
				}
				boolean attrDiff = false;
				for(int k=0; k< srcTabAttrs.getLength(); k++){
					Attr attr = (Attr)srcTabAttrs.item(k);
					if(!srcTable.getAttribute(attr.getName()).equals(orgTalbe.getAttribute(attr.getName()))){
						this.msg.append("Difference is found: table = "+srcTable.getAttribute("TABLE_NAME")+" attribute = "+attr.getName()+"\n");
						this.diffTables.add(srcTable.getAttribute("TABLE_NAME"));
						attrDiff = true;
						break;
					}
				}
				if(!attrDiff){
					//Compare Job
					this.compareJob(srcTable, orgTalbe);
				}
			}
		}
	}
	
	//Compare job, input is TABLE, key is JOBNAME
	private void compareJob(Element srcTable, Element orgTalbe){
		//Check is new job
		for(int i=0; i<srcTable.getChildNodes().getLength(); i++){
			if(srcTable.getChildNodes().item(i).getNodeType() != Node.ELEMENT_NODE){
				continue;
			}
			boolean isNew = true;
			Element srcJob = (Element) srcTable.getChildNodes().item(i);
			Element orgJob = null;
			for(int j=0; j<orgTalbe.getChildNodes().getLength(); j++){
				if(orgTalbe.getChildNodes().item(j).getNodeType() != Node.ELEMENT_NODE){
					continue;
				}
				orgJob = (Element) orgTalbe.getChildNodes().item(j);
				if(srcJob.getAttribute("JOBNAME").equals(orgJob.getAttribute("JOBNAME"))){
					isNew = false;
					break;
				}
			}
			if(isNew){
				this.msg.append("New job "+srcJob.getAttribute("JOBNAME")+" is found from "+srcTable.getAttribute("TABLE_NAME")+"\n");
				this.diffTables.add(srcTable.getAttribute("TABLE_NAME"));
				return;
			}else{
				//Compare number of children
				if(srcJob.getChildNodes().getLength()!=orgJob.getChildNodes().getLength()){
					this.msg.append("Number of children is different on job = "+srcJob.getAttribute("JOBNAME")+" on table "+srcTable.getAttribute("TABLE_NAME")+"\n");
					this.diffTables.add(srcTable.getAttribute("TABLE_NAME"));
					return;
				}
				//Compare number of attribute
				NamedNodeMap srcJobAttrs = srcJob.getAttributes();
				NamedNodeMap orgJobAttrs = orgJob.getAttributes();
				if(srcJobAttrs.getLength()!=orgJobAttrs.getLength()){
					this.msg.append("Number of attribute is different on job = "+srcJob.getAttribute("JOBNAME")+" on table "+srcTable.getAttribute("TABLE_NAME")+"\n");
					this.diffTables.add(srcTable.getAttribute("TABLE_NAME"));
					return;
				}
				//Compare attribute
				boolean attrDiff = false;
				for(int k=0; k< srcJobAttrs.getLength(); k++){
					Attr attr = (Attr)srcJobAttrs.item(k);
					if(!srcJob.getAttribute(attr.getName()).equals(orgJob.getAttribute(attr.getName()))){
						this.msg.append("Difference is found: table = "+srcTable.getAttribute("TABLE_NAME")+", job = "+srcJob.getAttribute("JOBNAME")+", attribute = "+attr.getName()+"\n");
						this.diffTables.add(srcTable.getAttribute("TABLE_NAME"));
						attrDiff = true;
						return;
					}
				}
				if(!attrDiff){
					//Compare childrenOfJob
					if (this.compareChildrenOfJob(srcJob, orgJob)){
						return;
					}
				}
			}
		}
	}
	
	//Compare children of job, e.g. INCOND/OUTCOND/ON, input is JOB, key is all columns
	private boolean compareChildrenOfJob(Element srcJob, Element orgJob){
		NodeList srcChildren = srcJob.getChildNodes();
		NodeList orgChildren = orgJob.getChildNodes();
		for(int i=0; i< srcChildren.getLength(); i++){
			if(srcChildren.item(i).getNodeType() != Node.ELEMENT_NODE){
				continue;
			}
			boolean isNew = true;
			Element srcChild = (Element) srcChildren.item(i);
			Element orgChild = null;
			NamedNodeMap srcChildAttrs = srcChild.getAttributes();
			NamedNodeMap orgChildAttrs = null;
			String srcKeyValue = "";
			String orgKeyValue = "";
			//combine the key value, all columns
			for(int m=0; m<srcChildAttrs.getLength(); m++){
				Attr attr = (Attr)srcChildAttrs.item(m);
				srcKeyValue += srcChild.getAttribute(attr.getName());
			}
			for(int j=0; j<orgChildren.getLength(); j++){
				if(orgChildren.item(j).getNodeType() != Node.ELEMENT_NODE){
					continue;
				}
				orgChild = (Element) orgChildren.item(j);
				orgChildAttrs = orgChild.getAttributes();
				if(srcChild.getNodeName().equals(orgChild.getNodeName())){
					//combine the key value, all columns
					orgKeyValue = "";
					for(int n=0; n<srcChildAttrs.getLength(); n++){
						Attr attr = (Attr)srcChildAttrs.item(n);
						orgKeyValue += orgChild.getAttribute(attr.getName());;
					}
					//System.out.println(srcKeyValue);
					if(srcKeyValue.equals(orgKeyValue)){
						isNew = false;
						break;
					}
				}
			}
			if(isNew){
				this.msg.append("Difference is found on node = "+srcChild.getNodeName()+", job="+this.getParentElement(srcChild, "JOB").getAttribute("JOBNAME")+", table = "+this.getParentElement(srcChild, "TABLE").getAttribute("TABLE_NAME")+"\n");
				this.diffTables.add(this.getParentElement(srcChild, "TABLE").getAttribute("TABLE_NAME"));
				return true;
			}else{
				//Compare number of children
				if(srcChild.getChildNodes().getLength()!=orgChild.getChildNodes().getLength()){
					this.msg.append("Number of children is different on node = "+srcChild.getNodeName()+", job="+this.getParentElement(srcChild, "JOB").getAttribute("JOBNAME")+", table = "+this.getParentElement(srcChild, "TABLE").getAttribute("TABLE_NAME")+"\n");
					this.diffTables.add(this.getParentElement(srcChild, "TABLE").getAttribute("TABLE_NAME"));
					return true;
				}
				//Compare number of attribute
				if(srcChildAttrs.getLength()!=orgChildAttrs.getLength()){
					this.msg.append("Number of attribute is different on node = "+srcChild.getNodeName()+", job="+this.getParentElement(srcChild, "JOB").getAttribute("JOBNAME")+", table = "+this.getParentElement(srcChild, "TABLE").getAttribute("TABLE_NAME")+"\n");
					this.diffTables.add(this.getParentElement(srcChild, "TABLE").getAttribute("TABLE_NAME"));
					return true;
				}
				//Compare children
				if(srcChild.getChildNodes().getLength()>0){
					if(this.compareChildrenOfJob(srcChild, orgChild)){
						return true;
					}
				}
			}
		}
		return false;
	}
	
	private Element getParentElement(Element element, String nodeName){
		if(element.getNodeName().equalsIgnoreCase(nodeName)){
			return element;
		}else{
			return this.getParentElement((Element) element.getParentNode(), nodeName);
		}
	}

	public String getMsg() {
		return msg.toString();
	}

	public ArrayList<String> getDiffTables() {
		return diffTables;
	}
}
