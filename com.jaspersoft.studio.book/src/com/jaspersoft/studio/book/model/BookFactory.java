/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.book.model;

import java.util.ArrayList;
import java.util.List;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.MRoot;
import com.jaspersoft.studio.model.dataset.MDataset;
import com.jaspersoft.studio.model.util.ReportFactory;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRGroup;
import net.sf.jasperreports.engine.JRPart;
import net.sf.jasperreports.engine.JRSection;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignSection;
import net.sf.jasperreports.engine.design.JasperDesign;

public class BookFactory {
	
	public static INode createReport(JasperReportsConfiguration jConfig) {
		JasperDesign jd = jConfig.getJasperDesign();
		ANode node = new MRoot(null, jd);
		ANode report = new MBookReport(node, jConfig);

		// create datasets
		ReportFactory.createDataset(report, jd.getMainDesignDataset(), false);

		if (jd.getDatasetsList() != null) {
			for (JRDataset jrDataset : jd.getDatasetsList()) {
				ReportFactory.createDataset(new MDataset(report, (JRDesignDataset) jrDataset, -1), (JRDesignDataset) jrDataset, true);
			}

		}

		createReportParts(jd, report);

		return node;
	}
	
	private static void createReportParts(JasperDesign jd, ANode report) {
		// Create Part(s) inside Group Header section(s)
		if (jd.getGroupsList() != null) {
			for (JRGroup gr : jd.getGroupsList()) {
				MGroupReportPartContainer grpHeader = null;
				if (gr.getGroupHeaderSection() != null) {
					List<JRPart> grphParts = ((JRDesignSection) gr.getGroupHeaderSection()).getPartsList();
					grpHeader = new MGroupReportPartContainer(report, gr.getGroupHeaderSection() , -1);
					grpHeader.setJRGroup(gr);
					if (grphParts != null) {
						for (JRPart p : grphParts) {
							ReportFactory.createNode(grpHeader, p, -1);
						}
					}
				}
			}
		}
		
		// Create Part(s) inside the Detail section
		JRSection detailSection = jd.getDetailSection();
		if(detailSection!=null){
			MReportPartContainer partSection = new MReportPartContainer(report, detailSection, -1);
			JRPart[] parts = detailSection.getParts();
			for(JRPart part : parts){
				ReportFactory.createNode(partSection, part, -1);
			}
		}
		
		// Create Part(s) inside Group Footer section(s)
		if (jd.getGroupsList() != null) {
			//The groups must be build in the reverse order
			for (int i = jd.getGroupsList().size()-1; i>=0; i--) {
				JRGroup gr = jd.getGroupsList().get(i);
				MGroupReportPartContainer grpFooter = null;
				if (gr.getGroupFooterSection()!= null) {
					List<JRPart> grphParts = ((JRDesignSection) gr.getGroupFooterSection()).getPartsList();
					grpFooter = new MGroupReportPartContainer(report, gr.getGroupFooterSection(), -1);
					grpFooter.setJRGroup(gr);
					if (grphParts != null) {
						for(JRPart p : grphParts) {
							ReportFactory.createNode(grpFooter, p, -1);
						}
					}
				}
			}
		}
	}
	
	//UTILS METHODS
	
	/**
	 * Checks if the report contains parts instead of bands.
	 * Please recall that a JRXML can have either bands or parts.
	 * They can not be mixed.
	 * 
	 * NOTE: Parts, as stated in the official model schema definition can
	 * be contained inside:
	 * <ul>
	 * 	<li>Detail Section</li>
	 * 	<li>Group Header Section</li>
	 * 	<li>Group Footer Section</li>
	 * </ul>
	 * 
	 * @param jd the Jasper Design of the report
	 * @return <code>true</code>if the report uses parts, <code>false</code> otherwise
	 */
	public static boolean reportContainsParts(JasperDesign jd) {
		List<JRSection> sections = new ArrayList<JRSection>();
		JRSection detailSection = jd.getDetailSection();
		if(detailSection!=null){
			sections.add(detailSection);
		}
		for (JRGroup grp : jd.getGroupsList()) {
			JRSection groupFooterSection = grp.getGroupFooterSection();
			if(groupFooterSection!=null){
				sections.add(groupFooterSection);
			}
			JRSection groupHeaderSection = grp.getGroupHeaderSection();
			if(groupHeaderSection!=null){
				sections.add(groupHeaderSection);
			}
		}
		for(JRSection sect : sections){
			if(sectionContainsParts(sect)){
				return true;
			}
		}
		return false;
	}

	/*
	 * Checks if the single section contains parts.
	 */
	private static boolean sectionContainsParts(JRSection sect) {
		if(sect!=null) {
			JRPart[] parts = sect.getParts();
			if(parts!=null && parts.length>0) {
				return true;
			}
		}
		return false;
	}
	
}
