package com.jaspersoft.studio.book.bundle;

import java.util.Map;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRGroup;
import net.sf.jasperreports.engine.JRPart;
import net.sf.jasperreports.engine.JasperReportsContext;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignSection;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.part.PartComponent;
import net.sf.jasperreports.parts.subreport.StandardSubreportPartComponent;

import com.jaspersoft.studio.templates.engine.DefaultTemplateEngine;
import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.wizards.ReportNewWizard;
import com.jaspersoft.templates.ReportBundle;
import com.jaspersoft.templates.TemplateBundle;
import com.jaspersoft.templates.TemplateEngineException;



/**
 * This template engine just creates a set of jrxml (cover, toc, main, backcover)
 * and put them together in a jasperbook file by fixing the template subreport part expressions.
 * 
 * @author gtoffoli
 *
 */
public class BookTemplateEngine extends DefaultTemplateEngine {

	
	@Override
	public ReportBundle generateReportBundle(TemplateBundle template,
			Map<String, Object> settings, JasperReportsContext jContext)
			throws TemplateEngineException {

	    JasperDesign jdCopy = null;
		try {
			// N.B: We need a fresh new copy of the jasper design!
			jdCopy = ModelUtils.copyJasperDesign(jContext, template.getJasperDesign());
		} catch (JRException e) {
			UIUtils.showError(e);
			return null;
		}
		
		// Set the correct subreport expression for each selected part in the wizard, or
		// remove parts unwanted....
		boolean hasCover = ((Boolean)settings.get(BookTemplateBundle.COVER_SETTING)).booleanValue();
		boolean hasToc = ((Boolean)settings.get(BookTemplateBundle.TOC_SETTING)).booleanValue();
		boolean hasBackCover = ((Boolean)settings.get(BookTemplateBundle.BACK_COVER_SETTING)).booleanValue();
		
		// Get the new jrxml file name
		String templateBaseName = (String) settings.get( ReportNewWizard.FILE_NAME_KEY);
		// Strip out the extension
		templateBaseName = templateBaseName.substring(0, templateBaseName.length() - ".jrxml".length());
		
		
		// Find and adjust parts 
		
		// COVER
		JRPart part = findPartByExpression(jdCopy, "\"cover\"");
		if (!hasCover)
		{
			deletePart(jdCopy, part);
		}
		else
		{
			setPartExpression(part, "\"" + templateBaseName + "_cover.jasper\"");
		}

		
		// TOC
		part = findPartByExpression(jdCopy, "\"toc\"");
		if (!hasToc)
		{
			deletePart(jdCopy, part);
		}
		else
		{
			setPartExpression(part, "\"" + templateBaseName + "_toc.jasper\"");
		}
		
		
		// BACK COVER
		part = findPartByExpression(jdCopy, "\"backcover\"");
		if (!hasBackCover)
		{
			deletePart(jdCopy, part);
		}
		else
		{
			setPartExpression(part, "\"" + templateBaseName + "_backcover.jasper\"");
		}
		
		
//	    // set the section of the template accordingly to the ones selected by the user 
//	    BookTemplateBundle bookBundle = (BookTemplateBundle)template;
//	    if ((Boolean)settings.get(BookTemplateBundle.COVER_SETTING) && bookBundle.getCover() != null){
//	 	   JasperDesign coverJd = bookBundle.getCover();
//	    }
	   
	    ReportBundle rb = new ReportBundle(template);
	    
	    // Set the final jasperdesign to the report bundle.
	    rb.setJasperDesign( jdCopy );
	    
		    
		return rb;
	}

	
	/**
	 * Finds a part in the report design 
	 * 
	 * @param jd
	 * @param expression
	 * @return
	 */
	private JRPart findPartByExpression(JasperDesign jd, String expression) {
		
		for (JRGroup group : jd.getGroups())
		{
			for (JRPart part : group.getGroupHeaderSection().getParts())
			{
				PartComponent partComponent = part.getComponent();
				
				if (partComponent != null && partComponent instanceof StandardSubreportPartComponent){
					StandardSubreportPartComponent subComponent = (StandardSubreportPartComponent)partComponent;
					if (subComponent.getExpression() != null && subComponent.getExpression().getText().equals(expression))
					{
						return part;
					}
				}
			}
			
			for (JRPart part : group.getGroupFooterSection().getParts())
			{
				PartComponent partComponent = part.getComponent();
				
				if (partComponent != null && partComponent instanceof StandardSubreportPartComponent){
					StandardSubreportPartComponent subComponent = (StandardSubreportPartComponent)partComponent;
					if (subComponent.getExpression() != null && subComponent.getExpression().getText().equals(expression))
					{
						return part;
					}
				}
			}
		}
		
		return null;
		
	}

	

	/**
	 * Finds a part in the report design and remove it
	 * 
	 * @param jd
	 * @param part to delete
	 * @return
	 */
	private void deletePart(JasperDesign jd, JRPart partToDelete) {
		
		if (partToDelete == null) return;

		for (JRGroup group : jd.getGroups())
		{
			for (JRPart part : group.getGroupHeaderSection().getParts())
			{
				if (part == partToDelete)
				{
					((JRDesignSection)group.getGroupFooterSection()).removePart(partToDelete);
				}
			}
			
			for (JRPart part : group.getGroupFooterSection().getParts())
			{
				if (part == partToDelete)
				{
					((JRDesignSection)group.getGroupFooterSection()).removePart(partToDelete);
				}
			}
		}
	}

	
	/**
	 * Set the expression for part
	 * 
	 * @param part
	 * @param expression
	 */
	private void setPartExpression(JRPart part, String expression)
	{
		if (part == null) return;
		PartComponent partComponent = part.getComponent();
		
		if (partComponent != null && partComponent instanceof StandardSubreportPartComponent){
			StandardSubreportPartComponent subComponent = (StandardSubreportPartComponent)partComponent;
			subComponent.setExpression(new JRDesignExpression(expression));
		}
	}
	
	
	
	
	
	
}
