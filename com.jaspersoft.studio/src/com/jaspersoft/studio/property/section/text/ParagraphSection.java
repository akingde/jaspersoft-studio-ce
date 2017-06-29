/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.section.text;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.forms.widgets.ExpandableComposite;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.style.MStyle;
import com.jaspersoft.studio.model.text.MParagraph;
import com.jaspersoft.studio.model.text.MTextElement;
import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.section.AbstractRealValueSection;

import net.sf.jasperreports.engine.base.JRBaseParagraph;

/*
 * The location section on the location tab.
 * 
 * @author Chicu Veaceslav
 */
public class ParagraphSection extends AbstractRealValueSection {

	private ExpandableComposite section;

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ITabbedPropertySection#createControls(org.eclipse.swt.widgets.Composite,
	 *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);

		parent = getWidgetFactory().createSection(parent, "Paragraph", true, 2);
		section = (ExpandableComposite) parent.getParent();

		createWidget4Property(parent, JRBaseParagraph.PROPERTY_LINE_SPACING);
		createWidget4Property(parent, JRBaseParagraph.PROPERTY_LINE_SPACING_SIZE);
		createWidget4Property(parent, JRBaseParagraph.PROPERTY_FIRST_LINE_INDENT);
		createWidget4Property(parent, JRBaseParagraph.PROPERTY_LEFT_INDENT);
		createWidget4Property(parent, JRBaseParagraph.PROPERTY_RIGHT_INDENT);
		createWidget4Property(parent, JRBaseParagraph.PROPERTY_SPACING_BEFORE);
		createWidget4Property(parent, JRBaseParagraph.PROPERTY_SPACING_AFTER);
		createWidget4Property(parent, JRBaseParagraph.PROPERTY_TAB_STOP_WIDTH);
		new Label(parent, SWT.NONE);
		createWidget4Property(parent, JRBaseParagraph.PROPERTY_TAB_STOPS, false);
	}

	@Override
	protected void initializeProvidedProperties() {
		super.initializeProvidedProperties();
		addProvidedProperties(JRBaseParagraph.PROPERTY_LINE_SPACING, Messages.common_line_spacing);
		addProvidedProperties(JRBaseParagraph.PROPERTY_LINE_SPACING_SIZE, Messages.MParagraph_lineSpacingSizeTitle);
		addProvidedProperties(JRBaseParagraph.PROPERTY_FIRST_LINE_INDENT, Messages.MParagraph_firstIdentTitle);
		addProvidedProperties(JRBaseParagraph.PROPERTY_LEFT_INDENT, Messages.MParagraph_leftIdentTitle);
		addProvidedProperties(JRBaseParagraph.PROPERTY_SPACING_BEFORE, Messages.MParagraph_spacingBeforeTitle);
		addProvidedProperties(JRBaseParagraph.PROPERTY_SPACING_AFTER, Messages.MParagraph_spacingAfterTitle);
		addProvidedProperties(JRBaseParagraph.PROPERTY_TAB_STOP_WIDTH, Messages.MParagraph_tabStopWidthTitle);
		addProvidedProperties(JRBaseParagraph.PROPERTY_RIGHT_INDENT, Messages.MParagraph_rightIdentTitle);
		addProvidedProperties(JRBaseParagraph.PROPERTY_TAB_STOPS, Messages.MParagraph_tabStopsTitle);
	}

	@Override
	public void expandForProperty(Object propertyId) {
		if (section != null && !section.isExpanded())
			section.setExpanded(true);
	}

	@Override
	protected APropertyNode getModelFromEditPart(Object item) {
		APropertyNode md = super.getModelFromEditPart(item);
		if (md instanceof MTextElement) {
			MParagraph paragraph = (MParagraph) md.getPropertyValue(MTextElement.PARAGRAPH);
			return paragraph;
		} else if (md instanceof MStyle) {
			MParagraph paragraph = (MParagraph) md.getPropertyValue(MStyle.PARAGRAPH);
			return paragraph;
		}
		return md;
	}

}
