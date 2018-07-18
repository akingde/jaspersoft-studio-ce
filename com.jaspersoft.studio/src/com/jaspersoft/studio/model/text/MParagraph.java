/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model.text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.DefaultValue;
import com.jaspersoft.studio.model.IPropertiesHolder;
import com.jaspersoft.studio.property.JSSStyleResolver;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.tabstops.TabStopsPropertyDescriptor;
import com.jaspersoft.studio.property.descriptors.FloatPropertyDescriptor;
import com.jaspersoft.studio.property.descriptors.NamedEnumPropertyDescriptor;
import com.jaspersoft.studio.property.descriptors.PixelPropertyDescriptor;

import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRPropertiesHolder;
import net.sf.jasperreports.engine.TabStop;
import net.sf.jasperreports.engine.base.JRBaseParagraph;
import net.sf.jasperreports.engine.type.LineSpacingEnum;

public class MParagraph extends APropertyNode implements IPropertiesHolder {

	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	private static IPropertyDescriptor[] descriptors;

	private static NamedEnumPropertyDescriptor<LineSpacingEnum> lineSpacingD;
	private APropertyNode pHolder;

	public MParagraph(ANode parent, JRBaseParagraph bParagraph) {
		super();
		if (parent.getValue() instanceof JRPropertiesHolder && parent instanceof APropertyNode)
			this.pHolder = (APropertyNode) parent;
		setJasperConfiguration(parent.getJasperConfiguration());
		setValue(bParagraph);
	}

	public APropertyNode getPropertiesHolder() {
		return pHolder;
	}

	/*
	 * @Override public HashMap<String,Object> getStylesDescriptors() {
	 * HashMap<String, Object> result = new HashMap<String, Object>(); if
	 * (getValue() == null) return result; JRBaseParagraph jrElement =
	 * (JRBaseParagraph) getValue();
	 * result.put(JRBaseParagraph.PROPERTY_SPACING_BEFORE,
	 * jrElement.getOwnSpacingBefore());
	 * result.put(JRBaseParagraph.PROPERTY_SPACING_AFTER,
	 * jrElement.getOwnSpacingAfter());
	 * result.put(JRBaseParagraph.PROPERTY_FIRST_LINE_INDENT,
	 * jrElement.getOwnFirstLineIndent());
	 * result.put(JRBaseParagraph.PROPERTY_LEFT_INDENT,
	 * jrElement.getOwnLeftIndent());
	 * //result.put(JRBaseParagraph.PROPERTY_LINE_SPACING,
	 * jrElement.getOwnLineSpacing());
	 * result.put(JRBaseParagraph.PROPERTY_LINE_SPACING_SIZE,
	 * jrElement.getOwnLineSpacingSize());
	 * result.put(JRBaseParagraph.PROPERTY_RIGHT_INDENT,
	 * jrElement.getOwnRightIndent());
	 * result.put(JRBaseParagraph.PROPERTY_TAB_STOP_WIDTH,
	 * jrElement.getOwnTabStopWidth()); return result; }
	 */

	@Override
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc) {
		lineSpacingD = new NamedEnumPropertyDescriptor<LineSpacingEnum>(JRBaseParagraph.PROPERTY_LINE_SPACING,
				Messages.common_line_spacing, LineSpacingEnum.AT_LEAST, NullEnum.INHERITED);
		lineSpacingD.setDescription(Messages.MTextElement_line_spacing_description);
		desc.add(lineSpacingD);

		FloatPropertyDescriptor lineSpacingSize = new FloatPropertyDescriptor(
				JRBaseParagraph.PROPERTY_LINE_SPACING_SIZE, Messages.MParagraph_lineSpacingSizeTitle);
		lineSpacingSize.setDescription(Messages.MParagraph_lineSpacingSizeDescription);
		desc.add(lineSpacingSize);

		PixelPropertyDescriptor firstLineIdent = new PixelPropertyDescriptor(JRBaseParagraph.PROPERTY_FIRST_LINE_INDENT,
				Messages.MParagraph_firstIdentTitle);
		firstLineIdent.setDescription(Messages.MParagraph_firstIdentDescription);
		desc.add(firstLineIdent);

		PixelPropertyDescriptor leftIdent = new PixelPropertyDescriptor(JRBaseParagraph.PROPERTY_LEFT_INDENT,
				Messages.MParagraph_leftIdentTitle);
		leftIdent.setDescription(Messages.MParagraph_leftIdentDescription);
		desc.add(leftIdent);

		PixelPropertyDescriptor rightIdent = new PixelPropertyDescriptor(JRBaseParagraph.PROPERTY_RIGHT_INDENT,
				Messages.MParagraph_rightIdentTitle);
		rightIdent.setDescription(Messages.MParagraph_rightIdentDescription);
		desc.add(rightIdent);

		PixelPropertyDescriptor spacingBefore = new PixelPropertyDescriptor(JRBaseParagraph.PROPERTY_SPACING_BEFORE,
				Messages.MParagraph_spacingBeforeTitle);
		spacingBefore.setDescription(Messages.MParagraph_spacingBeforeDescription);
		desc.add(spacingBefore);

		PixelPropertyDescriptor spacingAfter = new PixelPropertyDescriptor(JRBaseParagraph.PROPERTY_SPACING_AFTER,
				Messages.MParagraph_spacingAfterTitle);
		spacingAfter.setDescription(Messages.MParagraph_spacingAfterDescription);
		desc.add(spacingAfter);

		PixelPropertyDescriptor tabStopWidth = new PixelPropertyDescriptor(JRBaseParagraph.PROPERTY_TAB_STOP_WIDTH,
				Messages.MParagraph_tabStopWidthTitle);
		tabStopWidth.setDescription(Messages.MParagraph_tabStopWidthDescription);
		desc.add(tabStopWidth);

		TabStopsPropertyDescriptor tabStops = new TabStopsPropertyDescriptor(JRBaseParagraph.PROPERTY_TAB_STOPS,
				Messages.MParagraph_tabStopsTitle);
		tabStops.setDescription(Messages.MParagraph_tabStopsDescription);
		desc.add(tabStops);

		tabStops.setCategory("Paragraph"); //$NON-NLS-1$
		lineSpacingD.setCategory("Paragraph"); //$NON-NLS-1$
		lineSpacingSize.setCategory("Paragraph"); //$NON-NLS-1$
		firstLineIdent.setCategory("Paragraph"); //$NON-NLS-1$
		leftIdent.setCategory("Paragraph"); //$NON-NLS-1$
		rightIdent.setCategory("Paragraph"); //$NON-NLS-1$
		spacingBefore.setCategory("Paragraph"); //$NON-NLS-1$
		spacingAfter.setCategory("Paragraph"); //$NON-NLS-1$
		tabStopWidth.setCategory("Paragraph"); //$NON-NLS-1$

		setHelpPrefix(desc, "net.sf.jasperreports.doc/docs/schema.reference.html?cp=0_1#paragraph"); //$NON-NLS-1$
	}

	@Override
	public IPropertyDescriptor[] getDescriptors() {
		return descriptors;
	}

	@Override
	public void setDescriptors(IPropertyDescriptor[] descriptors1) {
		descriptors = descriptors1;
	}

	public Object getPropertyActualValue(Object id) {
		JSSStyleResolver resolver = getStyleResolver();
		JRBaseParagraph jrElement = (JRBaseParagraph) getValue();
		if (jrElement != null) {
			if (id.equals(JRBaseParagraph.PROPERTY_LINE_SPACING)) {
				LineSpacingEnum spacingEnum = resolver.getLineSpacing(jrElement);
				return lineSpacingD.getIntValue(spacingEnum);
			}
			if (id.equals(JRBaseParagraph.PROPERTY_LINE_SPACING_SIZE))
				return resolver.getLineSpacingSize(jrElement);

			if (id.equals(JRBaseParagraph.PROPERTY_FIRST_LINE_INDENT))
				return resolver.getFirstLineIndent(jrElement);

			if (id.equals(JRBaseParagraph.PROPERTY_LEFT_INDENT))
				return resolver.getLeftIndent(jrElement);
			if (id.equals(JRBaseParagraph.PROPERTY_RIGHT_INDENT))
				return resolver.getRightIndent(jrElement);

			if (id.equals(JRBaseParagraph.PROPERTY_SPACING_BEFORE))
				return resolver.getSpacingBefore(jrElement);
			if (id.equals(JRBaseParagraph.PROPERTY_SPACING_AFTER))
				return resolver.getSpacingAfter(jrElement);
			if (id.equals(JRBaseParagraph.PROPERTY_TAB_STOP_WIDTH))
				return resolver.getTabStopWidth(jrElement);
		}
		return super.getPropertyActualValue(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.views.properties.IPropertySource#getPropertyValue(java.lang.
	 * Object)
	 */
	public Object getPropertyValue(Object id) {
		JRBaseParagraph jrElement = (JRBaseParagraph) getValue();
		if (jrElement != null) {
			if (id.equals(JRBaseParagraph.PROPERTY_LINE_SPACING)) {
				LineSpacingEnum ownLineSpacing = jrElement.getOwnLineSpacing();
				return ownLineSpacing != null ? lineSpacingD.getIntValue(jrElement.getOwnLineSpacing()) : null;
			}
			if (id.equals(JRBaseParagraph.PROPERTY_LINE_SPACING_SIZE))
				return jrElement.getOwnLineSpacingSize();

			if (id.equals(JRBaseParagraph.PROPERTY_FIRST_LINE_INDENT))
				return jrElement.getOwnFirstLineIndent();

			if (id.equals(JRBaseParagraph.PROPERTY_LEFT_INDENT))
				return jrElement.getOwnLeftIndent();
			if (id.equals(JRBaseParagraph.PROPERTY_RIGHT_INDENT))
				return jrElement.getOwnRightIndent();

			if (id.equals(JRBaseParagraph.PROPERTY_SPACING_BEFORE))
				return jrElement.getOwnSpacingBefore();
			if (id.equals(JRBaseParagraph.PROPERTY_SPACING_AFTER))
				return jrElement.getOwnSpacingAfter();
			if (id.equals(JRBaseParagraph.PROPERTY_TAB_STOP_WIDTH))
				return jrElement.getOwnTabStopWidth();
			if (id.equals(JRBaseParagraph.PROPERTY_TAB_STOPS)) {
				TabStop[] tabStops = jrElement.getTabStops();
				if (tabStops != null)
					return new ArrayList<TabStop>(Arrays.asList(tabStops));
				return new ArrayList<TabStop>();
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.views.properties.IPropertySource#setPropertyValue(java.lang.
	 * Object, java.lang.Object)
	 */
	public void setPropertyValue(Object id, Object value) {
		JRBaseParagraph jrElement = (JRBaseParagraph) getValue();
		if (jrElement != null) {
			if (id.equals(JRBaseParagraph.PROPERTY_LINE_SPACING))
				jrElement.setLineSpacing(lineSpacingD.getEnumValue(value));
			if (id.equals(JRBaseParagraph.PROPERTY_LINE_SPACING_SIZE))
				jrElement.setLineSpacingSize((Float) value);
			if (id.equals(JRBaseParagraph.PROPERTY_FIRST_LINE_INDENT))
				jrElement.setFirstLineIndent((Integer) value);
			if (id.equals(JRBaseParagraph.PROPERTY_LEFT_INDENT))
				jrElement.setLeftIndent((Integer) value);
			if (id.equals(JRBaseParagraph.PROPERTY_RIGHT_INDENT))
				jrElement.setRightIndent((Integer) value);

			if (id.equals(JRBaseParagraph.PROPERTY_SPACING_BEFORE))
				jrElement.setSpacingBefore((Integer) value);
			if (id.equals(JRBaseParagraph.PROPERTY_SPACING_AFTER))
				jrElement.setSpacingAfter((Integer) value);

			if (id.equals(JRBaseParagraph.PROPERTY_TAB_STOP_WIDTH))
				jrElement.setTabStopWidth((Integer) value);
			if (id.equals(JRBaseParagraph.PROPERTY_TAB_STOPS)) {
				if (jrElement.getTabStops() != null)
					for (TabStop ts : jrElement.getTabStops())
						jrElement.removeTabStop(ts);
				if (value instanceof List)
					for (TabStop ts : (List<TabStop>) value)
						jrElement.addTabStop(ts);
			}
		}
	}

	@Override
	public HashMap<String, Object> getStylesDescriptors() {
		HashMap<String, Object> result = new HashMap<String, Object>();
		if (getValue() == null)
			return result;
		JRBaseParagraph jrElement = (JRBaseParagraph) getValue();
		result.put(JRBaseParagraph.PROPERTY_LINE_SPACING, jrElement.getOwnLineSpacing());
		result.put(JRBaseParagraph.PROPERTY_LINE_SPACING_SIZE, jrElement.getOwnLineSpacingSize());
		result.put(JRBaseParagraph.PROPERTY_FIRST_LINE_INDENT, jrElement.getOwnFirstLineIndent());
		result.put(JRBaseParagraph.PROPERTY_LEFT_INDENT, jrElement.getOwnLeftIndent());
		result.put(JRBaseParagraph.PROPERTY_RIGHT_INDENT, jrElement.getOwnRightIndent());
		result.put(JRBaseParagraph.PROPERTY_SPACING_BEFORE, jrElement.getOwnSpacingBefore());
		result.put(JRBaseParagraph.PROPERTY_SPACING_AFTER, jrElement.getOwnSpacingAfter());
		result.put(JRBaseParagraph.PROPERTY_TAB_STOP_WIDTH, jrElement.getOwnTabStopWidth());
		return result;
	}

	@Override
	protected Map<String, DefaultValue> createDefaultsMap() {
		Map<String, DefaultValue> defaults = super.createDefaultsMap();
		defaults.put(JRBaseParagraph.PROPERTY_LINE_SPACING_SIZE, new DefaultValue(true));
		defaults.put(JRBaseParagraph.PROPERTY_FIRST_LINE_INDENT, new DefaultValue(true));
		defaults.put(JRBaseParagraph.PROPERTY_LEFT_INDENT, new DefaultValue(true));
		defaults.put(JRBaseParagraph.PROPERTY_RIGHT_INDENT, new DefaultValue(true));
		defaults.put(JRBaseParagraph.PROPERTY_SPACING_BEFORE, new DefaultValue(true));
		defaults.put(JRBaseParagraph.PROPERTY_SPACING_AFTER, new DefaultValue(true));
		defaults.put(JRBaseParagraph.PROPERTY_TAB_STOP_WIDTH, new DefaultValue(true));
		return defaults;
	}

	public String getDisplayText() {
		return null;
	}

	public ImageDescriptor getImagePath() {
		return null;
	}

}
