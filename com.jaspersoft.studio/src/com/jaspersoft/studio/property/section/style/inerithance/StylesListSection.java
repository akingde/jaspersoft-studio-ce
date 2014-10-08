/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.property.section.style.inerithance;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.JRStyle;
import net.sf.jasperreports.engine.design.JRDesignStyle;
import net.sf.jasperreports.engine.type.JREnum;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.progress.WorkbenchJob;
import org.eclipse.wb.swt.ResourceCache;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;

import com.jaspersoft.studio.ExternalStylesManager;
import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.gef.parts.EditableFigureEditPart;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.messages.MessagesByKeys;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.DefaultValuesMap;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.MLineBox;
import com.jaspersoft.studio.model.MLinePen;
import com.jaspersoft.studio.model.MPage;
import com.jaspersoft.studio.model.style.MConditionalStyle;
import com.jaspersoft.studio.model.style.MStyle;
import com.jaspersoft.studio.model.style.MStyleTemplate;
import com.jaspersoft.studio.model.style.MStyles;
import com.jaspersoft.studio.model.style.MStylesTemplate;
import com.jaspersoft.studio.model.text.MParagraph;
import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.utils.Colors;
import com.jaspersoft.studio.utils.GridDataUtil;

/**
 * Class that paint the widget where are shown the attributes of element, those inherited by the it's styles, and the
 * default values
 * 
 * @author Orlandin Marco
 * 
 */
public class StylesListSection extends AbstractSection {

	/**
	 * Enumeration to describe the container of an attribute, it 
	 * can be an attribute of the selected element, of one 
	 * of the styles inherited by the element or a default value
	 * 
	 * @author Orlandin Marco
	 *
	 */
	private enum AttributeParent{ELEMENT, STYLE, DEFAULT};
	
	/**
	 * Color for the text that represent the attribute name
	 */
	private static Color leftStringColor = null;
	
	/**
	 * Width of a line where the value of an attribute is shown
	 */
	private static final int ITEM_WIDTH = 150;
	
	/**
	 * Height of a line where the value of an attribute is shown
	 */
	private static final int ITEM_HEIGHT = 20;
	
	/**
	 * Image show to remove an attribute
	 */
	private static Image image = ResourceManager.getPluginImage(JaspersoftStudioPlugin.PLUGIN_ID, "icons/resources/remove-16.png"); //$NON-NLS-1$

	/**
	 * Map of all the styles, where the key is the style value for the internal style and
	 * the style name for the external ones
	 */
	protected HashMap<Object, StyleContainer> styleMaps;

	/**
	 * List of attributes overridden by others of an upper level in the hierarchy (the hierarchy is element-styles-default
	 * style-default attribute)
	 */
	private HashSet<String> ovverridenAttributes;

	/**
	 * Reference to the default style
	 */
	private MStyle defaultStyle = null;
	
	private Composite mainComposite = null; 

	/**
	 * The map of the element own attributes
	 */
	private HashMap<String, Object> elementAttributes = null;

	/**
	 * Manager for the events binded to the mouse
	 */
	private IconMouseTracker trackerListener = new IconMouseTracker();

	/**
	 * When a property of the element change an update job is runned. This update job is delayed and cancelled
	 * if another update job is requested before it start
	 */
	private UpdatePanelJob updatePanelJob = new UpdatePanelJob();
	
	
	/**
	 * Job to update the panel UI when expression text changes or
	 * when caret is moved. 
	 * This job is supposed to be delayed in order not to call
	 * UI-update events too often (avoiding flickering effects).
	 */
	private class UpdatePanelJob extends WorkbenchJob {
		
		public UpdatePanelJob(){
			super("Update Styles Tab");
			setSystem(true);
		}
		
		@Override
		public IStatus runInUIThread(IProgressMonitor monitor) {
			if(shown){
				monitor.beginTask("Update Styles Tab", IProgressMonitor.UNKNOWN);
				createContent();
				monitor.done();
				return Status.OK_STATUS;
			}
			else{
				return Status.CANCEL_STATUS;
			}
		}
	}
	
	/**
	 * The delay of the update job
	 */
	private static final int UPDATE_DELAY=100;

	/**
	 * Boolean flag to take trace it the tab is shown
	 */
	private boolean shown = false;

	/**
	 * Color used from the label as background
	 */
	private static final Color labelBackgroundColor = SWTResourceManager.getColor(240, 240, 240);;

	/**
	 * map cache of the colors, they are disposed when the section is disposed
	 */
	private ResourceCache colorCache = new ResourceCache();

	/**
	 * Return the key of the parent style of an element, check also if the style is an 
	 * internal of an external one. In the first case the key is the jrstyle, in the second 
	 * one it is the name of the style
	 * 
	 * @param child a JRElement or a JRStyle
	 * @return the key of the style for the passed parameter, independently if it is
	 * external or internal. Null if it has not a parent style of it has a not valid internal
	 * parent style (a style that was removed)
	 */
	private Object getStyleKey(APropertyNode element){
		if (element.getValue() instanceof JRElement){
			JRElement jrElement = (JRElement)element.getValue();
			if (jrElement.getStyle() != null) return jrElement.getStyle();
			else return jrElement.getStyleNameReference();
		} else if (element.getValue() instanceof JRStyle){
			JRStyle jrStyle = (JRStyle)element.getValue();
			if (jrStyle.getStyle() != null) return jrStyle.getStyle();
			else return jrStyle.getStyleNameReference();
		}
		return null;
	}

	/**
	 * Build the hierarchy of styles of an element
	 * 
	 * @param element
	 *          Element from which the styles list will be generated
	 * @return A list of MStyle, where the first is the style assigned to the element, the second is the style assigned to
	 *         the first item of the list and so on
	 */
	private LinkedList<MStyle> buildStylesGerarchy(APropertyNode element) {
		LinkedList<MStyle> result = new LinkedList<MStyle>();
		Object styleKey;
		if (element instanceof MConditionalStyle){
			//The external style dosen't allow a conditional style, so the parent must be a jrstyle
			styleKey = ((MStyle) element.getParent()).getValue();
		} else {
			styleKey = getStyleKey(element);
		}
		StyleContainer styleContainer = styleMaps.get(styleKey);
		if (styleContainer != null) {
			MStyle styleModel = styleContainer.getStyle();
			result.addLast(styleModel);
			Object nextStylKey = getStyleKey(styleModel);
			while (nextStylKey != null) {
				styleModel = styleMaps.get(nextStylKey).getStyle();
				result.addLast(styleModel);
				nextStylKey = getStyleKey(styleModel);
			}

		}
		return result;
	}
	

	/**
	 * Add to a styledtext a new style to made the text with a middle black line (strike trought)
	 * 
	 * @param valueText
	 *          StyledText widget where the new style will be applied
	 */
	private void strikeStyledText(StyledText valueText) {
		StyleRange style1 = new StyleRange();
		style1.strikeout = true;
		style1.start = 0;
		style1.length = valueText.getText().length();
		valueText.setStyleRange(style1);
	}

	/**
	 * Return the hexadecimal representation of a color
	 * 
	 * @param color
	 *          The color
	 * @return The color hexadecimal representation
	 */
	private String getHexFromRGB(RGB color) {
		int r = color.red;
		int g = color.green;
		int b = color.blue;
		String s = Integer.toHexString(r) + Integer.toHexString(g) + Integer.toHexString(b);
		return "#" + StringUtils.rightPad(s, 6, "0").toUpperCase(); //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * Paint on the main widget the fields to show the value of an attribute color. the color will be expressed in textual
	 * form, as RGB values, but with a tooltip that show the visually the color
	 * 
	 * @param parent
	 *          Reference to the widget composite
	 * @param colorValue
	 *          Value of the color
	 * @param colorName
	 *          Name of the attribute
	 * @param gData
	 *          Grid data for the layout
	 * @param addLine
	 *          true if a stroke line is needed
	 * @param toolTip
	 *          tooltip text of the element name label
	 * @return The button where the click handle will be added
	 */
	private Control paintColor(Composite parent, final RGB colorValue, String colorName, GridData gData,
			boolean addLine, String toolTip) {
		String stringValue = getHexFromRGB(colorValue);
		Composite nameComp = new Composite(parent, SWT.NONE);
		RowLayout layout = new RowLayout();
		nameComp.setLayout(layout);
		nameComp.setLayoutData(GridDataUtil.clone(gData));

		Label imageLabel = new Label(nameComp, SWT.NONE);
		imageLabel.setImage(image);
		imageLabel.setVisible(false);

		StyledText label = new StyledText(nameComp, SWT.NONE);
		label.setText(colorName); //$NON-NLS-1$
		label.setForeground(leftStringColor);
		label.setEditable(false);
		label.setEnabled(false);
		nameComp.setToolTipText(toolTip);

		Composite valueComp = new Composite(parent, SWT.NONE);
		RowLayout inLineLayout = new RowLayout();
		valueComp.setLayout(inLineLayout);
		valueComp.setLayoutData(GridDataUtil.clone(gData));

		StyledText valueText = new StyledText(valueComp, SWT.NONE);
		valueText.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent e) {
				e.gc.setBackground(colorCache.getColor(colorValue));
				e.gc.drawRectangle(0, 0, 13, 13);
				e.gc.fillRectangle(1, 1, 12, 12);

			}
		});
		valueText.setLeftMargin(18);
		valueText.setText(stringValue);
		valueText.setAlignment(SWT.LEFT);
		valueText.setEditable(false);
		valueText.setEnabled(true);
		RowData valueText_RD = new RowData();
		valueText_RD.height = 15;
		valueText.setLayoutData(valueText_RD);
		if (addLine) {
			strikeStyledText(valueText);
			strikeStyledText(label);
		}
		return imageLabel;
	}

	/**
	 * Paint a couple of string as two label
	 * 
	 * @param parent
	 *          Reference to the widget composite
	 * @param name
	 *          The name of the attribute
	 * @param value
	 *          The value of the attribute
	 * @param gData
	 *          Grid data for the layout
	 * @param addLine
	 *          true if a stroke line is needed
	 * @param toolTip
	 *          tooltip text of the element name label
	 * @return The button where the click handle will be added
	 */
	private Control printLabels(Composite parent, String name, String value, GridData gData, boolean addLine,	String toolTip) {
		Composite valueComp = new Composite(parent, SWT.NONE);
		valueComp.setLayout(new RowLayout());
		valueComp.setLayoutData(gData);

		Label imageLabel = new Label(valueComp, SWT.NONE);
		imageLabel.setImage(image);
		imageLabel.setVisible(false);

		StyledText label = new StyledText(valueComp, SWT.NONE);
		label.setText(name); //$NON-NLS-1$
		label.setForeground(leftStringColor);
		label.setEditable(false);
		label.setEnabled(false);
		valueComp.setToolTipText(toolTip);

		StyledText valueText = new StyledText(parent, SWT.NONE);
		valueText.setText(value);
		valueText.setEditable(false);
		valueText.setEnabled(false);
		if (addLine) {
			strikeStyledText(valueText);
			strikeStyledText(label);
		}
		return imageLabel;
	}

	/**
	 * Paint a boolean attribute, it's value is expressed as text
	 * 
	 * @param parent
	 *          Reference to the widget composite
	 * @param name
	 *          The name of the attribute
	 * @param checked
	 *          true if the attribute has value true, false otherwise
	 * @param gData
	 *          Grid data for the layout
	 * @param addLine
	 *          true if a stoke line is needed
	 * @param toolTip
	 *          tooltip text of the element name label
	 * @return The button where the click handle will be added
	 */
	private Control paintCheckBox(Composite parent, String name, boolean checked, GridData gData, boolean addLine, String toolTip) {
		String stringValue = MessagesByKeys.getString("common_boolean_" + checked); //$NON-NLS-1$
		return printLabels(parent, name, stringValue, gData, addLine, toolTip);
	}


	/**
	 * Print a generic object attribute with the appropriate widgets on the main composite
	 * 
	 * @param keyPrefix optional prefix to the key represented by the name
	 * @param name Name and key of the attribute
	 * @param value value of the attribute
	 * @param parent main widget container
	 * @param gData grid layout data for the generated controls
	 * @param printLine True if the printed attribute need a strike thought line
	 * @param actualElement element that contain the attribute
	 * @param parentType the type of the container of the printed attribute, essentially say to who this attribute belong. It
	 * can be the selected element or a style of the element or also a default attribute
	 */
	private void printObject(String namePrefix, String name, Object value, Composite parent, GridData gData, boolean printLine, APropertyNode actualElement, AttributeParent parentType, HashMap<String, Object> localContext) {
		if (value instanceof Color) {
			RGB valImage = ((Color) value).getRGB();
			Control label = paintColor(parent, valImage, MessagesByKeys.getString("common".concat(namePrefix).concat("_").concat(name)), gData, printLine, actualElement.getPropertyDescriptor(name).getDescription()); //$NON-NLS-1$ //$NON-NLS-2$
			addListeners(label, actualElement, name, parentType);
		} else if (value instanceof java.awt.Color) {
			java.awt.Color valImage = (java.awt.Color) value;
			Control label = paintColor(parent, getSWTColorFromAWT(valImage), MessagesByKeys.getString("common".concat(namePrefix).concat("_").concat(name)), gData, printLine, actualElement.getPropertyDescriptor(name).getDescription()); //$NON-NLS-1$ //$NON-NLS-2$
			addListeners(label, actualElement, name, parentType);
		} else if (value instanceof JREnum) {
			JREnum enumValue = (JREnum) value;
			Control label = printLabels(parent, MessagesByKeys.getString("common".concat(namePrefix).concat("_").concat(name)), enumValue.getName(), gData, printLine, actualElement.getPropertyDescriptor(name).getDescription()); //$NON-NLS-1$ //$NON-NLS-2$
			addListeners(label, actualElement, name, parentType);
		} else if (value instanceof Boolean) {
			Control label = paintCheckBox(parent, MessagesByKeys.getString("common".concat(namePrefix).concat("_").concat(name)), (Boolean) value, gData, printLine, actualElement.getPropertyDescriptor(name).getDescription()); //$NON-NLS-1$ //$NON-NLS-2$
			addListeners(label, actualElement, name, parentType);
		} else if (value instanceof MLinePen) {
			MLinePen lineValue = (MLinePen) value;
			// I need to pass a new context for the linepen because it's a composite value, so in the main hashmap i have only
			// the complex value, not all it's fields
			printStyleAttribute(parent, lineValue, null, namePrefix.concat("_").concat(name), ((MLinePen) localContext.get(name)).getStylesDescriptors(), parentType); //$NON-NLS-1$
		} else if (value instanceof MParagraph) {
			MParagraph lineValue = (MParagraph) value;
			MParagraph ctxMParagraph = (MParagraph) localContext.get(name);
			HashMap<String, Object> stylesDescriptors = null;
			if(ctxMParagraph!=null){
				stylesDescriptors = ctxMParagraph.getStylesDescriptors();
			}
			printStyleAttribute(parent,	lineValue, null, namePrefix.concat("_").concat(name), stylesDescriptors, parentType); //$NON-NLS-1$
		} else if (value instanceof MLineBox) {
			MLineBox lineValue = (MLineBox) value;
			printStyleAttribute(parent, lineValue, null, namePrefix.concat("_").concat(name), ((MLineBox) localContext.get(name)).getStylesDescriptors(), parentType); //$NON-NLS-1$
		} else {
			Control label = printLabels(parent, MessagesByKeys.getString("common".concat(namePrefix).concat("_").concat(name)), value.toString(), gData, printLine, actualElement.getPropertyDescriptor(name).getDescription()); //$NON-NLS-1$ //$NON-NLS-2$
			addListeners(label, actualElement, name, parentType);
		}
	}
	
	/**
	 * Add the mouse move listener to an element, it's father and it's brothers. This listener allow to show
	 * and hide the delete attribute button only when the mouse is over the attribute
	 * In  addition it add also the correct listener to remove the attribute depending on the container of the attribute, 
	 * when the delete attribute button is pressed.
	 * This listener are not added if the value of an attribute came from a default, since a default value
	 * can't be removed from the hierarchy
	 * 
	 * @param control control where the listeners are added
	 * @param node from where the attributes are contained
	 * @param id of the attribute is removed when the delete button is pressed
	 * @param parentType  the type of the container of the printed attribute, essentially say to who this attribute belong. It
	 * can be the selected element or a style of the element or also a default attribute
	 */
	private void addListeners(Control control, APropertyNode actualElement, String attributeId, AttributeParent parentType) {
		if (parentType != AttributeParent.DEFAULT){
			Composite parent = control.getParent();
			parent.addMouseTrackListener(trackerListener);
			for (Control son : parent.getChildren()) {
				son.addMouseTrackListener(trackerListener);
			}
			control.setToolTipText(Messages.StylesListSection_removeAttribure_tooltip);
			if (parentType == AttributeParent.ELEMENT){ 
				control.addMouseListener(new ElementClickListener(attributeId, this));
			} else {
				control.addMouseListener(new StyleClickListener(actualElement, attributeId, this));
			}
		}
	}

	/**
	 * Print a title label, gray that take a whole line
	 * 
	 * @param parent composite of the main widget
	 * @param value text to put into the label
	 */
	private Label printTitle(Composite parent, String value) {
		Composite container = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout(2,false);
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		layout.verticalSpacing = 0;
		container.setLayout(layout);
		container.setBackground(labelBackgroundColor);
		
		GridData gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.horizontalSpan = 2;
		gridData.widthHint = SWT.FILL;
		gridData.heightHint = ITEM_HEIGHT;
		container.setLayoutData(gridData);
		
		Label label = new Label(container, SWT.NONE);
		gridData = new GridData();
		gridData.heightHint = ITEM_HEIGHT;
		gridData.verticalAlignment = SWT.CENTER;
		gridData.horizontalAlignment = SWT.LEFT;
		label.setBackground(labelBackgroundColor);
		label.setText(" " + value); //$NON-NLS-1$
		label.setLayoutData(gridData);
		
		return label;
	}
	
	/**
	 * Print a title label, gray that take a whole line, with a button to open
	 * the element contextual menu
	 * 
	 * @param parent composite of the main widget
	 * @param value text to put into the label
	 */
	private Button printTitleWithButton(Composite parent, String value){
		Composite buttonContainer = printTitle(parent, value).getParent();
		Button btn = new Button(buttonContainer, SWT.ARROW | SWT.DOWN);
		GridData gridData = new GridData();
		gridData.horizontalAlignment = SWT.LEFT;
		gridData.heightHint = ITEM_HEIGHT-2;
		btn.setLayoutData(gridData);
		final ElementContextualMenu contextualOpener = new ElementContextualMenu(this);
		btn.addSelectionListener(contextualOpener);
		//Add dispose listener on the button to dispose when necessary the contextual menu
		btn.addDisposeListener(new DisposeListener() {
			@Override
			public void widgetDisposed(DisposeEvent e) {
				contextualOpener.dispose();
			}
		});
		return btn;
	}

	/**
	 * Print the attributes of an element store in the elementAttributes hash map
	 * 
	 * @param parent
	 *          composite of the main widget
	 * @param element
	 *          The selected element
	 * @param titleValue
	 *          The title to print for this element
	 */
	private void printElementAttribute(Composite parent, APropertyNode element, String titleValue) {
		if (titleValue != null) {
			printTitleWithButton(parent, titleValue);
		}
		GridData sameSizeGridData = new GridData();
		sameSizeGridData.verticalAlignment = SWT.CENTER;
		sameSizeGridData.heightHint = ITEM_HEIGHT;
		sameSizeGridData.widthHint = ITEM_WIDTH;
		Iterator<String> it = elementAttributes.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			Object elementAttribute = elementAttributes.get(key);
			if (elementAttribute != null) {
				printObject("", key, elementAttribute, parent, sameSizeGridData, ovverridenAttributes.contains(key), element, AttributeParent.ELEMENT, elementAttributes); //$NON-NLS-1$
				ovverridenAttributes.add(key);
			}
		}
	}

	/**
	 * Print the attributes that belong to a styles
	 * 
	 * @param parent
	 *          composite of the main widget
	 * @param element
	 *          The selected element
	 * @param titleValue
	 *          The title to print for this element
	 * @param keyPrefix
	 *          optional prefix of the attribute key
	 * @param localElementAttributes
	 *          Map that contains the attribute of the element associated with this style
	 */
	private void printStyleAttribute(Composite parent, APropertyNode element, String titleValue, String keyPrefix, HashMap<String, Object> localElementAttributes, AttributeParent parentType) {
		if (titleValue != null) {
			Label titleLabel = printTitle(parent, titleValue);
			final StyleContainer styleReference = styleMaps.get(getStyleKey(element));
			if (styleReference != null && styleReference.isExternal()) {
				// If the style is external i made its editor open by double clicking on the style title
				titleLabel.setText(titleLabel.getText().concat(Messages.StylesListSection_NotEditable_Visual_Marker));
				titleLabel.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseDoubleClick(MouseEvent e) {
						EditableFigureEditPart.openEditor(styleReference.getTemplateValue(), ((DefaultEditDomain) getEditDomain()).getEditorPart(), styleReference.getTemplate());
					}
				});
			}
		}
		GridData sameSizeGridData = new GridData();
		sameSizeGridData.verticalAlignment = SWT.CENTER;
		sameSizeGridData.heightHint = ITEM_HEIGHT;
		sameSizeGridData.widthHint = ITEM_WIDTH;
		HashMap<String, Object> properties = element.getStylesDescriptors();
		Iterator<String> it = properties.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			Object elementAttribute = properties.get(key);
			if (elementAttribute != null && localElementAttributes.containsKey(key)) {
				printObject(keyPrefix, key, elementAttribute, parent, sameSizeGridData, ovverridenAttributes.contains(keyPrefix + key), element, parentType, localElementAttributes);
				ovverridenAttributes.add(keyPrefix + key);
			}
		}
	}

	/**
	 * Print the attributes of all the styles of the element
	 * 
	 * @param styles List of styles
	 * @param parent composite of the main widget
	 */
	private void printStyles(LinkedList<MStyle> styles, Composite parent) {
		ListIterator<MStyle> itr = styles.listIterator();
		boolean hasDefaultStyleInGerarchy = false;
		while (itr.hasNext()) {
			MStyle style = itr.next();
			printStyleAttribute(parent, style,
					Messages.StylesSectionList_Inherited_From_Style + style.getPropertyValue(JRDesignStyle.PROPERTY_NAME),
					"", elementAttributes, AttributeParent.STYLE); //$NON-NLS-1$
			if (style == defaultStyle)
				hasDefaultStyleInGerarchy = true;
		}
		if (!hasDefaultStyleInGerarchy && defaultStyle != null && defaultStyle != getElement())
			printStyleAttribute(
					parent,
					defaultStyle,
					Messages.StylesListSection_Inherited_From_Default_Style.concat(defaultStyle.getPropertyValue(
							JRDesignStyle.PROPERTY_NAME).toString()), "", elementAttributes, AttributeParent.STYLE); //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * Add the external list to the styles map, but only those with a different name from the one already added
	 * 
	 * @param stylesList the list of styles to add to the stylemap
	 * @param parentReference  the parent of the list of styles. It's typically null, but it is valorized when the 
	 * styles in the list are children of an external style
	 * @param the current default style
	 */
	private void recursiveReadStyles(List<INode> stylesList, MStyleTemplate parentReference, JRStyle defaultValue) {
		for (INode style : stylesList) {
			if (style instanceof MStyle) {
				MStyle element = (MStyle) style;
				String name = element.getPropertyValue(JRDesignStyle.PROPERTY_NAME).toString();
				if (element.getValue() == defaultValue) defaultStyle = element;
				styleMaps.put(name, new StyleContainer(element, parentReference));
			} else if (style instanceof MStyleTemplate) {
				recursiveReadStyles(style.getChildren(), (MStyleTemplate) style, defaultValue);
			}
		}
	}

	/**
	 * Initialize the map of the styles
	 */
	private void initStyleMaps() {
		styleMaps = new HashMap<Object, StyleContainer>();
		ovverridenAttributes = new HashSet<String>();
		if (leftStringColor == null) {
			leftStringColor = SWTResourceManager.getColor(42, 96, 213);
		}
		JRStyle defaultValue = getElement().getJasperDesign().getDefaultStyle();
		List<INode> list = getStylesRoot(getElement()).getChildren();
		List<INode> externalList = new ArrayList<INode>();
		for (INode style : list) {
			if (style instanceof MStyle) {
				MStyle element = (MStyle) style;
				styleMaps.put(element.getValue(), new StyleContainer(element));
				if (element.getValue() == defaultValue) defaultStyle = element;
			} else if (style instanceof MStyleTemplate) {
				externalList.add(style);
			}
		}
		recursiveReadStyles(externalList, null,defaultValue);
	}

	/**
	 * Print the default values of an element, the aren't editable
	 * 
	 * @param parent composite of the main widget
	 * @param defaultValues map of the default values
	 */
	private void printDefaultValues(Composite parent, Map<String, Object> defaultValues) {
		printTitle(parent, Messages.StylesListSection_defaultAttributesTitle);
		GridData sameSizeGridData = new GridData();
		sameSizeGridData.verticalAlignment = SWT.CENTER;
		sameSizeGridData.heightHint = ITEM_HEIGHT;
		sameSizeGridData.widthHint = ITEM_WIDTH;
		Iterator<String> it = defaultValues.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			Object elementAttribute = defaultValues.get(key);
			if (elementAttribute != null && elementAttributes.containsKey(key)) {
				printObject("", key, elementAttribute, parent, sameSizeGridData, ovverridenAttributes.contains(key), getElement(), AttributeParent.DEFAULT, elementAttributes); //$NON-NLS-1$
			}
		}
	}
	
	/**
	 * Return a reference to the node father of all the styles
	 *  
	 * @param element a node of the model. It will be used to get the root and then explored to 
	 * get the styles
	 * @return reference to the father of all the styles
	 */
	private ANode getStylesRoot(APropertyNode element) {
		List<INode> children = element.getRoot().getChildren();
		Iterator<INode> it = children.iterator();
		ANode stylesClass = null;
		while (it.hasNext() && stylesClass == null) {
			INode childElement = it.next();
			if (childElement instanceof MStyles || childElement instanceof MStylesTemplate)
				stylesClass = (ANode) childElement;
			// The root is a subreport or a table, i need to move into an upper level
			if (childElement instanceof MPage) {
				children = childElement.getChildren();
				it = children.iterator();
			}
		}
		return stylesClass;
	}
	

	/**
	 * Add not only this node to the notify handler but all it's root, so even change in it's styles will be notified
	 */
	public void aboutToBeShown() {
		if (getElement() != null) {
			getElement().getRoot().getPropertyChangeSupport().removePropertyChangeListener(this);
			getElement().getRoot().getPropertyChangeSupport().addPropertyChangeListener(this);
			// Set the handler for every style also because in this way an update of the style is immediately reflected
			for (INode style : getStylesRoot(getElement()).getChildren()) {
				style.getPropertyChangeSupport().removePropertyChangeListener(this);
				style.getPropertyChangeSupport().addPropertyChangeListener(this);
			}
		}
		shown = true;
		createContent();
	}

	/**
	 * Add not only this node to the notify handler but all it's root, so even change in it's styles will be notified
	 */
	public void aboutToBeHidden() {
		if (getElement() != null && getElement().getRoot() != null) {
			getElement().getRoot().getPropertyChangeSupport().removePropertyChangeListener(this);
			ANode styles = getStylesRoot(getElement());
			// check in case the selected element was deleted
			if (styles != null) {
				for (INode style : styles.getChildren()) {
					style.getPropertyChangeSupport().removePropertyChangeListener(this);
				}
			}
		}
		shown = false;
	}

	/**
	 * Override of the property change handler, check if the last event received was already notified
	 */
	public void propertyChange(PropertyChangeEvent evt) {
		if (!evt.getPropertyName().equals(ExternalStylesManager.STYLE_FOUND_EVENT)){
			updatePanelJob.cancel();
			updatePanelJob.schedule(UPDATE_DELAY);
		}
	}

	/**
	 * convert an awt color to an swt rgb
	 * 
	 * @param awtColor the color, must be not null
	 * @return the rgb fo the color
	 */
	private RGB getSWTColorFromAWT(java.awt.Color awtColor) {
		return Colors.getSWTRGB4AWTGBColor(awtColor).getRgb();
	}

	/**
	 * Create the title label of the section
	 * 
	 * @param parent parent of the label
	 */
	private void printSectionTitle(Composite parent) {
		StyledText label = new StyledText(parent, SWT.WRAP);
		GridData gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.verticalAlignment = SWT.TOP;
		gridData.horizontalSpan = 2;
		gridData.horizontalIndent = 5;
		label.setLayoutData(gridData);
		label.setFont(ResourceManager.getBoldFont(label.getFont()));
		label.setText(Messages.StylesListSection_Title);
		label.setEnabled(false);
	}
	
	/**
	 * Clear and populate the inheritance container where the controls
	 * are shown
	 */
	private void createContent(){
		if (!isRefreshing()){
			setRefreshing(true);
			trackerListener.refresh();
			elementAttributes = getElement().getStylesDescriptors();
			
			// Dispose the old widgets
			clearOldContent();
			
			// Recreate the main composite
			mainComposite = new Composite(parent, SWT.NONE);			
			GridLayout compositeLayout = new GridLayout(2, false);
			compositeLayout.marginWidth = 0;
			compositeLayout.marginHeight = 2;
			mainComposite.setLayout(compositeLayout);
			mainComposite.setLayoutData(new GridData(GridData.FILL_BOTH));
			
			//Load the hierarchy
			initStyleMaps();
			LinkedList<MStyle> styles = buildStylesGerarchy(getElement());
			
			//Print the hierarchy attributes
			printSectionTitle(mainComposite);
			printElementAttribute(mainComposite, getElement(), Messages.StylesSectionList_Element_Attributes);
			printStyles(styles, mainComposite);
			printDefaultValues(mainComposite, DefaultValuesMap.getPropertiesByType(getElement()));
			
			//Refresh the parent
			parent.layout();
			setRefreshing(false);
		}
	}

	/**
	 * Delete the old controls created for the tab
	 */
	private void clearOldContent(){
		if (mainComposite != null && !mainComposite.isDisposed()){
			mainComposite.dispose();
		}
		mainComposite = null;
	}
	
	/**
	 * Generate a command to set an attribute of the target element to null
	 * 
	 * @param targetElement the target element
	 * @param propertyId the id of a property of the element
	 * @return the command to set the property of the element to null or null if 
	 * the command can't be generated
	 */
	protected Command generateSetAttributeCommand(APropertyNode targetElement, String propertyId){
		return getChangePropertyCommand(propertyId, null, targetElement);
	}
	
	/**
	 * Execute the provided command and then refresh the section
	 * 
	 * @param commantToExecute command that will be executed before the refresh
	 */
	protected void executeAndRefresh(Command commantToExecute){
		CommandStack cs = getEditDomain().getCommandStack();
		cs.execute(commantToExecute);
		// Force a refresh
		createContent();
		parent.setFocus();
	}
	
	/**
	 * Initialize the styles widget, create the control and set them
	 */
	@Override
	public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);
		createContent();
	}
	
	@Override
	public void dispose() {
		super.dispose();
		colorCache.dispose();
		updatePanelJob.cancel();
	}
}
