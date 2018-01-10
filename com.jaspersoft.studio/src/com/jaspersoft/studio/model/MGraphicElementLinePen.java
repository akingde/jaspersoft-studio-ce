/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.property.descriptor.pen.PenPropertyDescriptor;

import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.JRPen;
import net.sf.jasperreports.engine.base.JRBasePen;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignGraphicElement;

/*
 * The Class MGeneric.
 */
public abstract class MGraphicElementLinePen extends MGraphicElement implements IGraphicElement {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	public static final String LINE_PEN = "LinePen"; //$NON-NLS-1$

	public MGraphicElementLinePen() {
		super();
	}

	public MGraphicElementLinePen(ANode parent, int newIndex) {
		super(parent, newIndex);
	}

	public MGraphicElementLinePen(ANode parent, JRDesignElement jrLine, int newIndex) {
		super(parent, jrLine, newIndex);
	}

	@Override
	public HashMap<String, Object> getStylesDescriptors() {
		HashMap<String, Object> result = super.getStylesDescriptors();
		if (getValue() == null)
			return result;
		MLinePen linepen = (MLinePen) getPropertyValue(LINE_PEN);
		// result.putAll(linepen.getStylesDescriptors());
		result.put(LINE_PEN, linepen);
		return result;
	}

	@Override
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc) {
		super.createPropertyDescriptors(desc);

		PenPropertyDescriptor linePenD = new PenPropertyDescriptor(LINE_PEN, Messages.common_line_pen);
		linePenD.setDescription(Messages.MGraphicElementLinePen_line_pen_description);
		desc.add(linePenD);
		linePenD.setCategory(Messages.common_graphic);

		setHelpPrefix(desc, "net.sf.jasperreports.doc/docs/schema.reference.html?cp=0_1#graphicElement");
	}

	private MLinePen linePen;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#getPropertyValue(java.lang.Object)
	 */
	@Override
	public Object getPropertyValue(Object id) {
		// pen
		if (id.equals(LINE_PEN)) {
				return getLinePen();
		}
		return super.getPropertyValue(id);
	}
	
	public MLinePen getLinePen(){
		if (linePen == null) {
			JRDesignGraphicElement jrGraphicElement = (JRDesignGraphicElement) getValue();
			linePen = new MLinePen(jrGraphicElement.getLinePen());
			linePen.setJasperConfiguration(getJasperConfiguration());
			linePen.getPropertyChangeSupport().addPropertyChangeListener(this);
		}
		return linePen;
	}
	
	@Override
	public void trasnferProperties(JRElement target){
		super.trasnferProperties(target);
		JRDesignGraphicElement jrSourceElement = (JRDesignGraphicElement) getValue();
		JRPen jrSourceBox = (JRPen)jrSourceElement.getLinePen();
		
		JRDesignGraphicElement jrTargetElement = (JRDesignGraphicElement) target;
		JRPen jrTargetBox = (JRPen)jrTargetElement.getLinePen();
		if (jrSourceBox != null && jrTargetBox != null){
			jrTargetBox.setLineColor(getColorClone(jrSourceBox.getOwnLineColor()));
			jrTargetBox.setLineStyle(jrSourceBox.getOwnLineStyleValue());
			jrTargetBox.setLineWidth(jrSourceBox.getOwnLineWidth());
		}
	}

	/**
	 * Return the graphical properties for an MGraphicalElementLinePen 
	 */
	public HashSet<String> generateGraphicalProperties(){
		HashSet<String> result = super.generateGraphicalProperties();
		result.add(JRBasePen.PROPERTY_LINE_COLOR);
		result.add(JRBasePen.PROPERTY_LINE_STYLE);
		result.add(JRBasePen.PROPERTY_LINE_WIDTH);
		return result;
	}
	
	
	@Override
	public void setEditable(boolean editable) {
		super.setEditable(editable);
		MLinePen linePen = (MLinePen)getPropertyValue(LINE_PEN);
		linePen.setEditable(editable);
	}

}
