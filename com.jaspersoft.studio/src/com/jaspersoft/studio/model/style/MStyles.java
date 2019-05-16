/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model.style;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRConditionalStyle;
import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRStyle;
import net.sf.jasperreports.engine.design.JRDesignReportTemplate;
import net.sf.jasperreports.engine.design.JRDesignStyle;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.design.events.CollectionElementAddedEvent;

import org.eclipse.jface.resource.ImageDescriptor;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.IContainerEditPart;
import com.jaspersoft.studio.model.IGraphicalPropertiesHandler;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.IPastable;
import com.jaspersoft.studio.model.util.IIconDescriptor;
import com.jaspersoft.studio.model.util.NodeIconDescriptor;
import com.jaspersoft.studio.model.util.ReportFactory;

/*
 * The Class MStyles.
 * 
 * @author Chicu Veaceslav
 */
public class MStyles extends ANode implements IPastable, IContainerEditPart {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	/** The icon descriptor. */
	private static IIconDescriptor iconDescriptor;

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
	public MStyles(ANode parent) {
		this(parent, -1);
	}

	public MStyles(ANode parent, int index) {
		super(parent, index);
		setValue(getJasperDesign());
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
	 * Starting from the children of the Mstyles search the 
	 * first default style and return it
	 * 
	 * @param children the recursive children (of the mstyles or mstyle reference)
	 * @return the current jrsrtle of the default style or null if there isn't a default style
	 */
	private JRStyle getDefaultStyle(List<INode> children){
		for(INode node : children){
			if (node instanceof MStyleTemplate){
				JRStyle result = getDefaultStyle(node.getChildren());
				if (result != null) return result;
			} else if (node instanceof MStyle){
				JRStyle style = (JRStyle)node.getValue();
				if (style.isDefault()) return style;
			}
		}
		return null;
	}
	
	/**
	 * When something that can change the default styles changes it check
	 * if the default style is changed, and if yes it is updated in the
	 * jasperdesign and the redrwan of the elements is requested
	 */
	public void updateDefaulStyle(){
		JasperDesign jd = getJasperDesign();
		if (jd != null){
			JRStyle oldDefault = jd.getDefaultStyle();
			JRStyle newDefault = getDefaultStyle(getChildren());
			if (oldDefault != newDefault){
				//remove the flag from the old style, but only if it is an internal style
				if (oldDefault != null && jd.getStylesList().contains(oldDefault)){
					//If the style is internal by setting the default value will cause already the refresh of the elements
					((JRDesignStyle)oldDefault).setDefault(false);
				} else {
					//If the style is external must be refresh all the elements
					fireUpdateForElements();
				}
				jd.setDefaultStyle(newDefault);
			}
		}
	}
	
	/**
	 * Set the flag to repaint the element for each element
	 * 
	 * @param childerns the actual children
	 */
	private void refreshAllElements(List<INode> childerns){
		for(INode child : childerns){
			if (child instanceof IGraphicalPropertiesHandler){
				IGraphicalPropertiesHandler graphicalElement = (IGraphicalPropertiesHandler)child;
				graphicalElement.setChangedProperty(true);
			}
			refreshAllElements(new ArrayList<INode>(child.getChildren()));
		}
	}
	
	/**
	 * Set the refresh of  the elements that are using this styles and mark them for the refresh, in background
	 * to speed up the application
	 */
	private void fireUpdateForElements(){
		Runnable notifier = new Runnable() {
	    public void run() {
	  		//Avoid the refresh if the style is not in the hierarchy
	    	INode root = getRoot();
	    	if (root != null) {
	    		refreshAllElements(new ArrayList<INode>(root.getChildren()));
	  		}
	    }
		};
		new Thread(notifier).start();
	}
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.ANode#propertyChange(java.beans.PropertyChangeEvent)
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals(JasperDesign.PROPERTY_STYLES) || evt.getPropertyName().equals(JasperDesign.PROPERTY_TEMPLATES) && evt.getSource() == getValue()) {
			if (evt.getOldValue() == null && evt.getNewValue() != null) {
				int newIndex = -1;
				if (evt instanceof CollectionElementAddedEvent) {
					newIndex = ((CollectionElementAddedEvent) evt).getAddedIndex();
				}
				if (evt.getNewValue() instanceof JRDesignReportTemplate)
					newIndex = 0;
				// add the node to this parent
				ANode style = ReportFactory.createNode(this, evt.getNewValue(), newIndex);
				if (evt.getNewValue() instanceof JRDesignStyle) {
					JRDesignStyle jrStyle = (JRDesignStyle) evt.getNewValue();
					for (JRConditionalStyle it : jrStyle.getConditionalStyleList()) {
						ReportFactory.createNode(style, it, -1);
					}
				}
			} else if (evt.getOldValue() != null && evt.getNewValue() == null) {
				// delete
				for (INode n : getChildren()) {
					if (n.getValue() == evt.getOldValue()) {
						removeChild((ANode) n);
						break;
					}
				}
			} else {
				// changed
				for (INode n : getChildren()) {
					if (n.getValue() == evt.getOldValue())
						n.setValue(evt.getNewValue());
				}
			}
		} 
		if (evt.getPropertyName().equals(JRDesignStyle.PROPERTY_DEFAULT) || evt.getPropertyName().equals(JasperDesign.PROPERTY_TEMPLATES) || evt.getPropertyName().equals(JasperDesign.PROPERTY_STYLES)){
			//A style default flag has been changed or a external style has been added removed, need to update the default styles
			updateDefaulStyle();
		}
		// Bug #JSS-867. Check the event source: if this event is fired from the MStyles it 
		// would arrive to the MReport (parent of mstyles) twice.
		// One from the source of the event (typically caused by an add/remove band) and one from here.
		// This causes an unexpected behavior when a band delete operation is undone.		
		if (!(evt.getPropertyName().equals(JasperDesign.PROPERTY_TITLE)
			|| evt.getPropertyName().equals(JasperDesign.PROPERTY_PAGE_HEADER)
			|| evt.getPropertyName().equals(JasperDesign.PROPERTY_COLUMN_HEADER)
			|| evt.getPropertyName().equals(JasperDesign.PROPERTY_COLUMN_FOOTER)
			|| evt.getPropertyName().equals(JasperDesign.PROPERTY_PAGE_FOOTER)
			|| evt.getPropertyName().equals(JasperDesign.PROPERTY_LAST_PAGE_FOOTER)
			|| evt.getPropertyName().equals(JasperDesign.PROPERTY_SUMMARY)
			|| evt.getPropertyName().equals(JasperDesign.PROPERTY_NO_DATA)
			|| evt.getPropertyName().equals(JasperDesign.PROPERTY_DETAIL)
			|| evt.getPropertyName().equals(JasperDesign.PROPERTY_BACKGROUND))) {
				super.propertyChange(evt);
		}
	}

}
