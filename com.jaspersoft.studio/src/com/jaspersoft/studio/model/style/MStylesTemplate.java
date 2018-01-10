/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model.style;

import java.beans.PropertyChangeEvent;

import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRReportTemplate;
import net.sf.jasperreports.engine.JRSimpleTemplate;
import net.sf.jasperreports.engine.JRTemplateReference;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignReportTemplate;
import net.sf.jasperreports.engine.design.JRDesignStyle;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.design.events.CollectionElementAddedEvent;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.resource.ImageDescriptor;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.IContainerEditPart;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.IPastable;
import com.jaspersoft.studio.model.util.IIconDescriptor;
import com.jaspersoft.studio.model.util.NodeIconDescriptor;
import com.jaspersoft.studio.utils.ExpressionUtil;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

/*
 * The Class MStyles.
 * 
 * @author Chicu Veaceslav
 */
public class MStylesTemplate extends ANode implements IPastable, IContainerEditPart {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	/** The icon descriptor. */
	private static IIconDescriptor iconDescriptor;
	private IFile file;

	/**
	 * Gets the icon descriptor.
	 * 
	 * @return the icon descriptor
	 */
	public static IIconDescriptor getIconDescriptor() {
		if (iconDescriptor == null)
			iconDescriptor = new NodeIconDescriptor("styles"); //$NON-NLS-1$
		return iconDescriptor;
	}

	/**
	 * Instantiates a new m styles.
	 * 
	 * @param parent
	 *          the parent
	 */
	public MStylesTemplate(ANode parent, IFile file) {
		super(parent, -1);
		setValue(getJasperDesign());
		this.file = file;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.INode#getDisplayText()
	 */
	public String getDisplayText() {
		return getIconDescriptor().getTitle();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.INode#getImagePath()
	 */
	public ImageDescriptor getImagePath() {
		return getIconDescriptor().getIcon16();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.INode#getToolTip()
	 */
	@Override
	public String getToolTip() {
		return getIconDescriptor().getToolTip();
	}
	
	/**
	 * Called when an add event happens (new value not null and old
	 * value null). Depend from the values added, but essentially this method
	 * update the JasperDesign associated to this element to keep it in synch
	 * with the model elemenets
	 */
	protected void propertyAddEvent(PropertyChangeEvent evt){
		//Style added
		int newIndex = -1;
		if (evt instanceof CollectionElementAddedEvent) {
			newIndex = ((CollectionElementAddedEvent) evt).getAddedIndex();
		}
		if (evt.getNewValue() instanceof JRDesignReportTemplate) {
			newIndex = 0;
			//Add the style reference to the JasperDesign if it is available, to keep it in synch
			JasperDesign jd = getJasperDesign();
			if (jd != null) {
				jd.addTemplate((JRDesignReportTemplate)evt.getNewValue());
			}
		} else if (evt.getNewValue() instanceof JRDesignStyle){
			//Add the style to the JasperDesign map if it is available, to keep it in synch
			JasperDesign jd = getJasperDesign();
			if (jd != null) {
				try {
					jd.addStyle((JRDesignStyle)evt.getNewValue());
				} catch (JRException e) {
					e.printStackTrace();
				}
			} 
		} else if (evt.getNewValue() instanceof JRTemplateReference){
			//Add the resource to the list of the external styles
			JasperReportsConfiguration jConfig = getJasperConfiguration();
			JasperDesign jd = getJasperDesign();
			if (jd != null && jConfig != null) {
				JRTemplateReference externalNode = (JRTemplateReference)evt.getNewValue();
				JRDesignReportTemplate jrTemplate = MStyleTemplate.createJRTemplate();
				JRDesignExpression jre = new JRDesignExpression();
				jre.setText("\"" + StyleTemplateFactory.getStylePath(externalNode, jConfig)  + "\"");//$NON-NLS-1$ //$NON-NLS-2$
				((JRDesignReportTemplate) jrTemplate).setSourceExpression(jre);
				jd.addTemplate(jrTemplate);
			}
		}
		//Create the model for the new style
		StyleTemplateFactory.createNode(this, evt.getNewValue(), newIndex, file, (JRSimpleTemplate) getValue());
	}
	
	/**
	 * Called when a remove event happens (new value null and old
	 * value not null). Depend from the values added, but essentially this method
	 * update the JasperDesign associated to this element to keep it in synch
	 * with the model elemenets
	 */
	protected void propertyRemoveEvent(PropertyChangeEvent evt){
		if (evt.getOldValue() instanceof JRDesignStyle){
			//Remove the style to the JasperDesign map if it is available, to keep it in synch
			JasperDesign jd = getJasperDesign();
			if (jd != null) {
				jd.removeStyle((JRDesignStyle)evt.getOldValue());
			}
		} else if(evt.getOldValue() instanceof JRDesignReportTemplate){
			//remove the style reference to the JasperDesign if it is available, to keep it in synch
			JasperDesign jd = getJasperDesign();
			if (jd != null) {
				jd.removeTemplate((JRDesignReportTemplate)evt.getOldValue());
			}
		} else  if (evt.getOldValue() instanceof JRTemplateReference){
			//remove the resource from the list of the external styles
			JasperReportsConfiguration jConfig = getJasperConfiguration();
			JasperDesign jd = getJasperDesign();
			JRTemplateReference externalNode = (JRTemplateReference)evt.getOldValue();
			JRDesignExpression jre = new JRDesignExpression();
			jre.setText("\"" + StyleTemplateFactory.getStylePath(externalNode, jConfig)  + "\"");//$NON-NLS-1$ //$NON-NLS-2$
			if (jd != null && jConfig != null) {
				for(JRReportTemplate template : jd.getTemplates()){
					if (ExpressionUtil.ExpressionEquals(jre, template.getSourceExpression())){
						jd.removeTemplate(template);
						break;
					}
				}
			}
		}	
		// style deleted 
		for (INode n : getChildren()) {
			if (n.getValue() == evt.getOldValue()) {
				removeChild((ANode) n);
				break;
			}
		}
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.ANode#propertyChange(java.beans.PropertyChangeEvent)
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals(JRSimpleTemplate.PROPERTY_STYLE) || evt.getPropertyName().equals(JRSimpleTemplate.PROPERTY_INCLUDED_TEMPLATES) && evt.getSource() == getValue()) {
			if (evt.getOldValue() == null && evt.getNewValue() != null) {
				propertyAddEvent(evt);
			} else if (evt.getOldValue() != null && evt.getNewValue() == null) {
				propertyRemoveEvent(evt);
			} else {
				// changed
				for (INode n : getChildren()) {
					if (n.getValue() == evt.getOldValue())
						n.setValue(evt.getNewValue());
				}
			}
		}
		super.propertyChange(evt);
	}

}
