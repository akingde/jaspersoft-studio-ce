/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.editor.defaults.DefaultManager;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.property.descriptor.box.BoxPropertyDescriptor;

import net.sf.jasperreports.engine.JRBoxContainer;
import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.JRLineBox;
import net.sf.jasperreports.engine.JRPen;
import net.sf.jasperreports.engine.base.JRBaseLineBox;
import net.sf.jasperreports.engine.base.JRBasePen;
import net.sf.jasperreports.engine.design.JRDesignElement;

/*
 * The Class MGeneric.
 */
public abstract class MGraphicElementLineBox extends MGraphicElement implements IGraphicElement, ILineBox {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	public static final String LINE_BOX = "LineBox"; //$NON-NLS-1$

	
	public MGraphicElementLineBox() {
		super();
	}

	public MGraphicElementLineBox(ANode parent, int newIndex) {
		super(parent, newIndex);
	}

	public MGraphicElementLineBox(ANode parent, JRDesignElement jrLine, int newIndex) {
		super(parent, jrLine, newIndex);
	}

	@Override
	public HashMap<String, Object> getStylesDescriptors() {
		HashMap<String, Object> result = super.getStylesDescriptors();
		if (getValue() == null)
			return result;
		MLineBox element = (MLineBox) getPropertyValue(LINE_BOX);
		// result.putAll(element.getStylesDescriptors());
		result.put(LINE_BOX, element);
		return result;
	}

	@Override
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc) {
		super.createPropertyDescriptors(desc);

		BoxPropertyDescriptor lineBoxD = new BoxPropertyDescriptor(LINE_BOX, Messages.common_line_box);
		lineBoxD.setDescription(Messages.MGraphicElementLineBox_line_box_description);
		desc.add(lineBoxD);
		lineBoxD.setCategory(Messages.common_graphic);

		setHelpPrefix(desc, "net.sf.jasperreports.doc/docs/schema.reference.html?cp=0_1#box");
	}

	private MLineBox lineBox;

	
	public MLineBox getLineBox(){
		if (lineBox == null) {
			JRBoxContainer jrGraphicElement = (JRBoxContainer) getValue();
			lineBox = new MLineBox(jrGraphicElement.getLineBox(), this);
			lineBox.setJasperConfiguration(getJasperConfiguration());
			setChildListener(lineBox);
		}
		return lineBox;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#getPropertyValue(java.lang.Object)
	 */
	@Override
	public Object getPropertyValue(Object id) {
		// pen
		if (id.equals(LINE_BOX)) {
			return getLineBox();
		}
		return super.getPropertyValue(id);
	}

	public JRBoxContainer getBoxContainer() {
		return (JRBoxContainer) getValue();
	}
	
	/**
	 * Return the graphical properties for an MGraphicalElementLineBox
	 */
	public HashSet<String> generateGraphicalProperties(){
		HashSet<String> result = super.generateGraphicalProperties();
		result.add(JRBaseLineBox.PROPERTY_BOTTOM_PADDING);
		result.add(JRBaseLineBox.PROPERTY_LEFT_PADDING);
		result.add(JRBaseLineBox.PROPERTY_PADDING);
		result.add(JRBaseLineBox.PROPERTY_RIGHT_PADDING);
		result.add(JRBaseLineBox.PROPERTY_TOP_PADDING);
		result.add(JRBasePen.PROPERTY_LINE_COLOR);
		result.add(JRBasePen.PROPERTY_LINE_STYLE);
		result.add(JRBasePen.PROPERTY_LINE_WIDTH);
		return result;
	}
	
	private void transferLinePenProeprties(JRPen jrTarget, JRPen source){
		if (jrTarget != null && source != null){
			jrTarget.setLineColor(getColorClone(source.getOwnLineColor()));
			jrTarget.setLineStyle(source.getOwnLineStyleValue());
			Float lineWidth = source.getOwnLineWidth();
			jrTarget.setLineWidth(lineWidth != null ?  lineWidth.floatValue(): null);
		}
	}
	
	@Override
	public void trasnferProperties(JRElement target){
		super.trasnferProperties(target);
		
		JRLineBox jrSourceBox = (JRLineBox) getBoxContainer().getLineBox();
		if (jrSourceBox != null && target instanceof JRBoxContainer){
			JRLineBox jrTargetBox = ((JRBoxContainer) target).getLineBox();
			
			jrTargetBox.setPadding(jrSourceBox.getOwnPadding());
			jrTargetBox.setTopPadding(jrSourceBox.getOwnTopPadding());
			jrTargetBox.setBottomPadding(jrSourceBox.getOwnBottomPadding());
			jrTargetBox.setLeftPadding(jrSourceBox.getOwnLeftPadding());
			jrTargetBox.setRightPadding(jrSourceBox.getOwnRightPadding());

			transferLinePenProeprties(jrTargetBox.getPen(), jrSourceBox.getPen());
			transferLinePenProeprties(jrTargetBox.getLeftPen(), jrSourceBox.getLeftPen());
			transferLinePenProeprties(jrTargetBox.getRightPen(), jrSourceBox.getRightPen());
			transferLinePenProeprties(jrTargetBox.getTopPen(), jrSourceBox.getTopPen());
			transferLinePenProeprties(jrTargetBox.getBottomPen(), jrSourceBox.getBottomPen());
		}
	}
	
	@Override
	public void setEditable(boolean editable) {
		super.setEditable(editable);
		MLineBox lineBox = (MLineBox)getPropertyValue(LINE_BOX);
		lineBox.setEditable(editable);
	}
	
	protected void applyDefaultValue(){
		if (DefaultManager.INSTANCE.hasDefault()){
			
		}
	}
	
	/**
	 * Since this property aggregate more complex object this will allow to reset
	 * also the children when the reset action happen on a LINE_BOX
	 */
	@Override
	public boolean forcePropertyChildrenReset(Object id) {
		if (id.equals(LINE_BOX)) {
			return true;
		} else return super.forcePropertyChildrenReset(id);
	}
}
