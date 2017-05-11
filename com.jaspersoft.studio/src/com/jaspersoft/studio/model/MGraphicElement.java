/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model;

import java.awt.Color;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.editor.defaults.DefaultManager;
import com.jaspersoft.studio.editor.gef.rulers.ReportRulerGuide;
import com.jaspersoft.studio.help.HelpReferenceBuilder;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.band.MBand;
import com.jaspersoft.studio.model.frame.MFrame;
import com.jaspersoft.studio.model.util.IIconDescriptor;
import com.jaspersoft.studio.model.util.NodeIconDescriptor;
import com.jaspersoft.studio.properties.view.validation.ValidationError;
import com.jaspersoft.studio.property.JSSStyleResolver;
import com.jaspersoft.studio.property.SetValueCommand;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.checkbox.CheckBoxPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.checkbox.NullCheckBoxPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.color.ColorPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.combo.RComboBoxPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.combo.RWStyleComboBoxPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.expression.ExprUtil;
import com.jaspersoft.studio.property.descriptor.expression.JRExpressionPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.propexpr.JPropertyExpressionsDescriptor;
import com.jaspersoft.studio.property.descriptor.propexpr.PropertyExpressionDTO;
import com.jaspersoft.studio.property.descriptor.propexpr.PropertyExpressionsDTO;
import com.jaspersoft.studio.property.descriptor.text.NTextPropertyDescriptor;
import com.jaspersoft.studio.property.descriptors.AbstractJSSCellEditorValidator;
import com.jaspersoft.studio.property.descriptors.JSSPixelLocationValidator;
import com.jaspersoft.studio.property.descriptors.NamedEnumPropertyDescriptor;
import com.jaspersoft.studio.property.descriptors.PixelPropertyDescriptor;
import com.jaspersoft.studio.utils.AlfaRGB;
import com.jaspersoft.studio.utils.Colors;
import com.jaspersoft.studio.utils.ModelUtils;

import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.JRBand;
import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.JRElementGroup;
import net.sf.jasperreports.engine.JRFont;
import net.sf.jasperreports.engine.JRGroup;
import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.engine.JRPropertyExpression;
import net.sf.jasperreports.engine.JRStyle;
import net.sf.jasperreports.engine.JRTextElement;
import net.sf.jasperreports.engine.base.JRBaseElement;
import net.sf.jasperreports.engine.base.JRBaseFont;
import net.sf.jasperreports.engine.base.JRBasePen;
import net.sf.jasperreports.engine.base.JRBaseStyle;
import net.sf.jasperreports.engine.design.JRDesignBand;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignGenericElement;
import net.sf.jasperreports.engine.design.JRDesignGraphicElement;
import net.sf.jasperreports.engine.design.JRDesignPropertyExpression;
import net.sf.jasperreports.engine.design.JRDesignStyle;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.design.events.JRChangeEventsSupport;
import net.sf.jasperreports.engine.type.ModeEnum;
import net.sf.jasperreports.engine.type.PositionTypeEnum;
import net.sf.jasperreports.engine.type.StretchTypeEnum;

/*
 * The Class MGeneric.
 */
public class MGraphicElement extends APropertyNode
		implements IGraphicElement, ICopyable, IGuidebleElement, IDragable, IDesignDragable, IGraphicalPropertiesHandler {

	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	private static RComboBoxPropertyDescriptor groupChangesD;

	private static NamedEnumPropertyDescriptor<PositionTypeEnum> positionTypeD;

	private static NamedEnumPropertyDescriptor<StretchTypeEnum> stretchTypeD;

	private IPropertyDescriptor[] descriptors;

	private ReportRulerGuide verticalGuide, horizontalGuide;

	/**
	 * Validators for the element size and position. they avoid to provide a negative value for the size of an element and
	 * a position too far from the editor
	 */
	private List<AbstractJSSCellEditorValidator> positionValidators = new ArrayList<AbstractJSSCellEditorValidator>();

	/**
	 * Special propery id to force the refresh of the graphic element
	 */
	public static String FORCE_GRAPHICAL_REFRESH = "forceGraphicalRefresh";

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.IGuidebleElement#getVerticalGuide()
	 */
	public ReportRulerGuide getVerticalGuide() {
		return verticalGuide;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.IGuidebleElement#setVerticalGuide(com.jaspersoft.studio.editor.gef.rulers.
	 * ReportRulerGuide )
	 */
	public void setVerticalGuide(ReportRulerGuide verticalGuide) {
		this.verticalGuide = verticalGuide;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.IGuidebleElement#getHorizontalGuide()
	 */
	public ReportRulerGuide getHorizontalGuide() {
		return horizontalGuide;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.IGuidebleElement#setHorizontalGuide(com.jaspersoft.studio.editor.gef.rulers.
	 * ReportRulerGuide)
	 */
	public void setHorizontalGuide(ReportRulerGuide horizontalGuide) {
		this.horizontalGuide = horizontalGuide;
	}

	@Override
	public void setParent(ANode parent, int newIndex) {
		if (parent instanceof MGraphicElement) {
			IGuidebleElement p = (IGuidebleElement) parent;
			if (p.getVerticalGuide() != null)
				p.getVerticalGuide().detachPart(p);
			if (p.getHorizontalGuide() != null)
				p.getHorizontalGuide().detachPart(p);
		}
		super.setParent(parent, newIndex);
	}

	public INode getBand() {
		INode node = this;
		while (!(node instanceof MBand) && !(node instanceof MRoot)) {
			if (node == null || node.getParent() == null)
				return this;
			node = node.getParent();
		}
		return node;
	}

	/** The icon descriptor. */
	private static IIconDescriptor iconDescriptor;

	/**
	 * Gets the icon descriptor.
	 * 
	 * @return the icon descriptor
	 */
	public static IIconDescriptor getIconDescriptor() {
		if (iconDescriptor == null)
			iconDescriptor = new NodeIconDescriptor("generic"); //$NON-NLS-1$
		return iconDescriptor;
	}

	/**
	 * Instantiates a new m generic.
	 */
	public MGraphicElement() {
		super();
	}

	/**
	 * Instantiates a new m generic.
	 * 
	 * @param parent
	 *          the parent
	 * @param newIndex
	 *          the new index
	 */
	public MGraphicElement(ANode parent, int newIndex) {
		super(parent, newIndex);
	}

	/**
	 * Instantiates a new m generic.
	 * 
	 * @param parent
	 *          the parent
	 * @param jrLine
	 *          the jr line
	 * @param newIndex
	 *          the new index
	 */
	public MGraphicElement(ANode parent, JRDesignElement jrLine, int newIndex) {
		super(parent, newIndex);
		setValue(jrLine);
	}

	@Override
	public JRDesignElement getValue() {
		return (JRDesignElement) super.getValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.ANode#setValue(java.lang.Object)
	 */
	@Override
	public void setValue(Object value) {
		if (getValue() != null && getValue() instanceof JRDesignGraphicElement)
			((JRBasePen) ((JRDesignGraphicElement) getValue()).getLinePen()).getEventSupport()
					.removePropertyChangeListener(this);
		else if (value != null && value instanceof JRDesignGraphicElement)
			((JRBasePen) ((JRDesignGraphicElement) value).getLinePen()).getEventSupport().addPropertyChangeListener(this);
		super.setValue(value);
	}

	public int getDefaultHeight() {
		Object defaultValue = DefaultManager.INSTANCE.getDefaultPropertiesValue(this.getClass(),
				JRDesignElement.PROPERTY_HEIGHT);
		return defaultValue != null ? (Integer) defaultValue : 30;
	}

	public int getDefaultWidth() {
		Object defaultValue = DefaultManager.INSTANCE.getDefaultPropertiesValue(this.getClass(),
				JRDesignElement.PROPERTY_WIDTH);
		return defaultValue != null ? (Integer) defaultValue : 100;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.IGraphicElement#createJRElement(net.sf.jasperreports.engine.design.JasperDesign)
	 */
	public JRDesignElement createJRElement(JasperDesign jasperDesign) {
		JRDesignGenericElement jrDesignGenericElement = new JRDesignGenericElement(jasperDesign);
		return jrDesignGenericElement;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.IGraphicElement#getBounds()
	 */
	public Rectangle getBounds() {
		JRElement jr = (JRElement) getValue();
		INode node = getParent();
		while (node != null) {
			if (node instanceof MPage) {
				return new Rectangle(0, 0, jr.getWidth(), jr.getHeight());
			} else if (node instanceof IGraphicElement) {
				Rectangle b = ((IGraphicElement) node).getBounds();
				if (b == null) {
					// FIXME - Need to be verified, temporary solve the issue reported here:
					// http://community.jaspersoft.com/questions/826441/javalangnullpointerexception-crosstabs
					return new Rectangle(jr.getX(), jr.getY(), jr.getWidth(), jr.getHeight());
				} else {
					b = new Rectangle(((IGraphicElement) node).getBounds());
				}
				if (node instanceof IGraphicElementContainer) {
					int x = ((IGraphicElementContainer) node).getLeftPadding();
					int y = ((IGraphicElementContainer) node).getTopPadding();
					b.setLocation(b.x + x, b.y + y);
				}
				return new Rectangle(b.x + jr.getX(), b.y + jr.getY(), jr.getWidth(), jr.getHeight());
			}
			node = node.getParent();
		}
		return new Rectangle(0, 0, jr.getWidth(), jr.getHeight());
	}

	@Override
	public Rectangle getJRBounds() {
		JRElement jr = (JRElement) getValue();
		return new Rectangle(jr.getX(), jr.getY(), jr.getWidth(), jr.getHeight());
	}

	@Override
	public IPropertyDescriptor[] getDescriptors() {
		return descriptors;
	}

	@Override
	public void setDescriptors(IPropertyDescriptor[] descriptors1) {
		descriptors = descriptors1;
	}

	@Override
	protected void postDescriptors(IPropertyDescriptor[] descriptors) {
		super.postDescriptors(descriptors);
		// initialize style
		JasperDesign jd = getJasperDesign();
		if (jd != null && getValue() != null) {
			JRDataset dataset = getElementDataset();
			// Calculate the groups list for the current element
			if (dataset != null) {
				JRGroup[] groups = dataset.getGroups();
				String[] items = new String[groups.length + 1];
				items[0] = ""; // always add empty for <NULL>
				for (int j = 0; j < groups.length; j++) {
					items[j + 1] = groups[j].getName();
				}
				setGroupItems(items);
			}
		}
		for (AbstractJSSCellEditorValidator validator : positionValidators) {
			validator.setTargetNode(this);
		}
	}

	/**
	 * Return the dataset used by the element
	 * 
	 * @return the dataset nearest to this element
	 */
	public JRDataset getElementDataset() {
		JRDataset dataset = ModelUtils.getDataset(this);
		if (dataset == null && getJasperDesign() != null) {
			dataset = getJasperDesign().getMainDataset();
		}
		return dataset;
	}

	protected void setGroupItems(String[] items) {
		if (groupChangesD != null) {
			groupChangesD.setItems(items);
		}
	}

	@Override
	public HashMap<String, Object> getStylesDescriptors() {
		HashMap<String, Object> result = new HashMap<String, Object>();
		if (getValue() == null)
			return result;
		JRDesignElement element = (JRDesignElement) getValue();
		result.put(JRDesignStyle.PROPERTY_BACKCOLOR, element.getOwnBackcolor());
		result.put(JRDesignStyle.PROPERTY_FORECOLOR, element.getOwnForecolor());
		result.put(JRDesignStyle.PROPERTY_MODE, element.getOwnModeValue());
		return result;
	}

	/**
	 * Creates the property descriptors.
	 * 
	 * @param desc
	 *          the desc
	 */
	@Override
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc) {
		RWStyleComboBoxPropertyDescriptor styleD = new RWStyleComboBoxPropertyDescriptor(
				JRDesignElement.PROPERTY_PARENT_STYLE, Messages.common_parent_style, new String[] { "" }, NullEnum.NULL); //$NON-NLS-1$
		styleD.setDescription(Messages.MGraphicElement_parent_style_description);
		desc.add(styleD);
		styleD.setHelpRefBuilder(
				new HelpReferenceBuilder("net.sf.jasperreports.doc/docs/schema.reference.html?cp=0_1#reportElement_style"));

		groupChangesD = new RComboBoxPropertyDescriptor(JRDesignElement.PROPERTY_PRINT_WHEN_GROUP_CHANGES,
				Messages.MGraphicElement_print_when_group_changes, new String[] { "" }); //$NON-NLS-1$
		groupChangesD.setDescription(Messages.MGraphicElement_print_when_group_changes_description);
		groupChangesD.setCategory(Messages.MGraphicElement_print_when);
		desc.add(groupChangesD);

		NTextPropertyDescriptor keyD = new NTextPropertyDescriptor(JRDesignElement.PROPERTY_KEY, Messages.common_key);
		keyD.setDescription(Messages.MGraphicElement_key_description);
		desc.add(keyD);

		// bounds
		JSSPixelLocationValidator heightValidator = new JSSPixelLocationValidator(JRDesignElement.PROPERTY_HEIGHT);
		heightValidator.setTargetNode(this);
		PixelPropertyDescriptor heightD = new PixelPropertyDescriptor(JRDesignElement.PROPERTY_HEIGHT,
				Messages.common_height);
		heightD.setCategory(Messages.common_size);
		heightD.setDescription(Messages.MGraphicElement_height_description);
		heightD.setValidator(heightValidator);
		desc.add(heightD);
		positionValidators.add(heightValidator);

		JSSPixelLocationValidator widthValidator = new JSSPixelLocationValidator(JRDesignElement.PROPERTY_WIDTH);
		widthValidator.setTargetNode(this);
		PixelPropertyDescriptor widthD = new PixelPropertyDescriptor(JRBaseElement.PROPERTY_WIDTH,
				Messages.MGraphicElement_width);
		widthD.setCategory(Messages.common_size);
		widthD.setDescription(Messages.MGraphicElement_width_description);
		widthD.setValidator(widthValidator);
		desc.add(widthD);
		positionValidators.add(widthValidator);

		JSSPixelLocationValidator xValidator = new JSSPixelLocationValidator(JRBaseElement.PROPERTY_X);
		xValidator.setTargetNode(this);
		PixelPropertyDescriptor xD = new PixelPropertyDescriptor(JRBaseElement.PROPERTY_X, Messages.common_left);
		xD.setCategory(Messages.MGraphicElement_location_category);
		xD.setDescription(Messages.MGraphicElement_left_description);
		xD.setValidator(xValidator);
		desc.add(xD);
		positionValidators.add(xValidator);

		JSSPixelLocationValidator yValidator = new JSSPixelLocationValidator(JRDesignElement.PROPERTY_Y);
		yValidator.setTargetNode(this);
		PixelPropertyDescriptor yD = new PixelPropertyDescriptor(JRDesignElement.PROPERTY_Y, Messages.common_top);
		yD.setCategory(Messages.MGraphicElement_location_category);
		yD.setDescription(Messages.MGraphicElement_top_description);
		yD.setValidator(yValidator);
		desc.add(yD);
		positionValidators.add(yValidator);

		// colors
		ColorPropertyDescriptor backcolorD = new ColorPropertyDescriptor(JRBaseStyle.PROPERTY_BACKCOLOR,
				Messages.common_backcolor, NullEnum.INHERITED);
		backcolorD.setDescription(Messages.MGraphicElement_backcolor_description);
		desc.add(backcolorD);

		ColorPropertyDescriptor forecolorD = new ColorPropertyDescriptor(JRBaseStyle.PROPERTY_FORECOLOR,
				Messages.common_forecolor, NullEnum.INHERITED);
		forecolorD.setDescription(Messages.MGraphicElement_forecolor_description);
		desc.add(forecolorD);

		// OpaqueModePropertyDescriptor opaqueD = new OpaqueModePropertyDescriptor(JRBaseStyle.PROPERTY_MODE,
		// Messages.common_opaque, NullEnum.INHERITED);
		// opaqueD.setDescription(Messages.MGraphicElement_opaque_description);
		// opaqueD.setCategory(Messages.common_graphic);
		// desc.add(opaqueD);

		NullCheckBoxPropertyDescriptor opaqueDBool = new NullCheckBoxPropertyDescriptor(JRBaseStyle.PROPERTY_MODE,
				Messages.common_opaque);
		opaqueDBool.setDescription(Messages.MGraphicElement_opaque_description);
		desc.add(opaqueDBool);

		positionTypeD = new NamedEnumPropertyDescriptor<PositionTypeEnum>(JRDesignElement.PROPERTY_POSITION_TYPE,
				Messages.common_position_type, PositionTypeEnum.FIX_RELATIVE_TO_BOTTOM, NullEnum.NOTNULL);
		positionTypeD.setDescription(Messages.MGraphicElement_position_type_description);
		desc.add(positionTypeD);
		positionTypeD.setCategory(Messages.MGraphicElement_location_category);

		CheckBoxPropertyDescriptor printRVAlueD = new CheckBoxPropertyDescriptor(
				JRDesignElement.PROPERTY_PRINT_REPEATED_VALUES, Messages.MGraphicElement_print_repeated_values);
		printRVAlueD.setDescription(Messages.MGraphicElement_print_repeated_values_description);
		desc.add(printRVAlueD);

		CheckBoxPropertyDescriptor rmLineWBlankD = new CheckBoxPropertyDescriptor(
				JRDesignElement.PROPERTY_REMOVE_LINE_WHEN_BLANK, Messages.MGraphicElement_remove_line_when_blank);
		rmLineWBlankD.setDescription(Messages.MGraphicElement_remove_line_when_blank_description);
		desc.add(rmLineWBlankD);

		CheckBoxPropertyDescriptor printInFirstWholeBandD = new CheckBoxPropertyDescriptor(
				JRDesignElement.PROPERTY_PRINT_IN_FIRST_WHOLE_BAND, Messages.MGraphicElement_print_in_first_whole_band);
		printInFirstWholeBandD.setDescription(Messages.MGraphicElement_print_in_first_whole_band_description);
		desc.add(printInFirstWholeBandD);

		CheckBoxPropertyDescriptor printWhenDetailOverflowsD = new CheckBoxPropertyDescriptor(
				JRDesignElement.PROPERTY_PRINT_WHEN_DETAIL_OVERFLOWS, Messages.MGraphicElement_print_when_detail_overflows);
		printWhenDetailOverflowsD.setDescription(Messages.MGraphicElement_print_when_detail_overflows_desription);
		printWhenDetailOverflowsD.setCategory(Messages.MGraphicElement_print_when);
		desc.add(printWhenDetailOverflowsD);

		JRExpressionPropertyDescriptor printWhenExprD = new JRExpressionPropertyDescriptor(
				JRDesignElement.PROPERTY_PRINT_WHEN_EXPRESSION, Messages.common_print_when_expression);
		printWhenExprD.setDescription(Messages.MGraphicElement_print_when_expression_description);
		printWhenExprD.setCategory(Messages.MGraphicElement_print_when);
		desc.add(printWhenExprD);
		printWhenExprD.setHelpRefBuilder(
				new HelpReferenceBuilder("net.sf.jasperreports.doc/docs/schema.reference.html?cp=0_1#printWhenExpression"));

		JPropertyExpressionsDescriptor propertiesD = new JPropertyExpressionsDescriptor(
				JRDesignElement.PROPERTY_PROPERTY_EXPRESSIONS, Messages.MGraphicElement_property_expressions);
		propertiesD.setDescription(Messages.MGraphicElement_property_expressions_description);
		desc.add(propertiesD);

		setHelpPrefix(desc, "net.sf.jasperreports.doc/docs/schema.reference.html?cp=0_1#reportElement");

		stretchTypeD = new NamedEnumPropertyDescriptor<StretchTypeEnum>(JRDesignElement.PROPERTY_STRETCH_TYPE,
				Messages.common_stretch_type, StretchTypeEnum.NO_STRETCH, NullEnum.NOTNULL);
		stretchTypeD.setCategory(Messages.common_size);
		stretchTypeD.setDescription(Messages.MGraphicElement_stretch_type_description);
		desc.add(stretchTypeD);

		setHelpPrefix(desc, "net.sf.jasperreports.doc/docs/schema.reference.html?cp=0_1#graphicElement");

		// JPropertiesPropertyDescriptor propertiesMapD = new JPropertiesPropertyDescriptor(PROPERTY_MAP,
		// Messages.common_properties);
		// propertiesMapD.setDescription(Messages.common_properties);
		// desc.add(propertiesMapD);

		forecolorD.setCategory(Messages.common_graphic);
		backcolorD.setCategory(Messages.common_graphic);
		styleD.setCategory(Messages.common_graphic);
	}

	@Override
	protected Map<String, DefaultValue> createDefaultsMap() {
		Map<String, DefaultValue> defaultsMap = super.createDefaultsMap();
		defaultsMap.put(JRDesignElement.PROPERTY_PARENT_STYLE, new DefaultValue(null, true));
		defaultsMap.put(JRBaseStyle.PROPERTY_FORECOLOR, new DefaultValue(null, true));
		defaultsMap.put(JRBaseStyle.PROPERTY_BACKCOLOR, new DefaultValue(null, true));

		defaultsMap.put(JRBaseStyle.PROPERTY_MODE, new DefaultValue(Boolean.FALSE, true));

		int positionDefault = NamedEnumPropertyDescriptor.getIntValue(PositionTypeEnum.FIX_RELATIVE_TO_TOP,
				NullEnum.NOTNULL, PositionTypeEnum.FIX_RELATIVE_TO_TOP);
		defaultsMap.put(JRDesignElement.PROPERTY_POSITION_TYPE, new DefaultValue(positionDefault, false));

		StretchTypeEnum stretchDefault = NamedEnumPropertyDescriptor.getEnumValue(StretchTypeEnum.NO_STRETCH,
				NullEnum.NOTNULL, StretchTypeEnum.NO_STRETCH);
		defaultsMap.put(JRDesignElement.PROPERTY_STRETCH_TYPE, new DefaultValue(stretchDefault, false));

		defaultsMap.put(JRDesignElement.PROPERTY_PRINT_REPEATED_VALUES, new DefaultValue(Boolean.TRUE, true));
		defaultsMap.put(JRDesignElement.PROPERTY_REMOVE_LINE_WHEN_BLANK, new DefaultValue(Boolean.FALSE, true));
		defaultsMap.put(JRDesignElement.PROPERTY_PRINT_IN_FIRST_WHOLE_BAND, new DefaultValue(Boolean.FALSE, true));
		defaultsMap.put(JRDesignElement.PROPERTY_PRINT_WHEN_DETAIL_OVERFLOWS, new DefaultValue(Boolean.FALSE, true));
		defaultsMap.put(JRDesignElement.PROPERTY_PRINT_WHEN_EXPRESSION, new DefaultValue(null, true));
		return defaultsMap;
	}

	/**
	 * Return the internal style used. If the internal style is a reference to a removed style then it is also removed
	 * from the element
	 */
	protected JRStyle getActualStyle() {
		JRDesignElement jrElement = (JRDesignElement) getValue();
		// Check if the used style is valid otherwise set it to null
		if (jrElement.getStyle() != null && !getJasperDesign().getStylesMap().containsKey(jrElement.getStyle().getName())) {
			setPropertyValue(JRDesignElement.PROPERTY_PARENT_STYLE, null);
		}
		if (jrElement.getStyle() != null) {
			return jrElement.getStyle();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#getPropertyValue(java.lang.Object)
	 */
	public Object getPropertyValue(Object id) {
		JRDesignElement jrElement = (JRDesignElement) getValue();
		if (id.equals(JRDesignElement.PROPERTY_KEY))
			return jrElement.getKey();
		if (id.equals(JRDesignElement.PROPERTY_PRINT_WHEN_EXPRESSION))
			return ExprUtil.getExpression(jrElement.getPrintWhenExpression());
		if (id.equals(JRDesignElement.PROPERTY_PARENT_STYLE)) {
			if (jrElement.getStyleNameReference() != null)
				return jrElement.getStyleNameReference();
			JRStyle actualStyle = getActualStyle();
			return actualStyle != null ? actualStyle.getName() : ""; //$NON-NLS-1$
		}
		if (id.equals(JRDesignElement.PROPERTY_PRINT_WHEN_GROUP_CHANGES)) {
			if (jrElement.getPrintWhenGroupChanges() != null)
				return jrElement.getPrintWhenGroupChanges().getName();
			return ""; //$NON-NLS-1$
		}
		if (id.equals(JRDesignElement.PROPERTY_PROPERTY_EXPRESSIONS)) {
			JRPropertyExpression[] propertyExpressions = jrElement.getPropertyExpressions();
			if (propertyExpressions != null)
				propertyExpressions = propertyExpressions.clone();
			return new PropertyExpressionsDTO(propertyExpressions, getPropertiesMapClone(jrElement), getValue(),
					ModelUtils.getExpressionContext(this));
		}
		if (id.equals(PROPERTY_MAP))
			return getPropertiesMapClone(jrElement);
		if (id.equals(JRDesignElement.PROPERTY_HEIGHT))
			return new Integer(jrElement.getHeight());
		if (id.equals(JRDesignElement.PROPERTY_WIDTH))
			return new Integer(jrElement.getWidth());
		if (id.equals(JRDesignElement.PROPERTY_X))
			return new Integer(jrElement.getX());
		if (id.equals(JRDesignElement.PROPERTY_Y))
			return new Integer(jrElement.getY());
		// colors
		if (id.equals(JRBaseStyle.PROPERTY_BACKCOLOR))
			return Colors.getSWTRGB4AWTGBColor(jrElement.getOwnBackcolor());
		if (id.equals(JRBaseStyle.PROPERTY_FORECOLOR))
			return Colors.getSWTRGB4AWTGBColor(jrElement.getOwnForecolor());
		// opacity
		if (id.equals(JRBaseStyle.PROPERTY_MODE)) {
			ModeEnum modeValue = jrElement.getOwnModeValue();
			return modeValue != null ? modeValue.equals(ModeEnum.TRANSPARENT) : null;
		}
		if (id.equals(JRDesignElement.PROPERTY_POSITION_TYPE))
			return positionTypeD.getIntValue(jrElement.getPositionTypeValue());
		if (id.equals(JRDesignElement.PROPERTY_STRETCH_TYPE))
			return stretchTypeD.getIntValue(jrElement.getStretchTypeValue());

		if (id.equals(JRDesignElement.PROPERTY_PRINT_REPEATED_VALUES))
			return new Boolean(jrElement.isPrintRepeatedValues());
		if (id.equals(JRDesignElement.PROPERTY_REMOVE_LINE_WHEN_BLANK))
			return new Boolean(jrElement.isRemoveLineWhenBlank());
		if (id.equals(JRDesignElement.PROPERTY_PRINT_IN_FIRST_WHOLE_BAND))
			return new Boolean(jrElement.isPrintInFirstWholeBand());
		if (id.equals(JRDesignElement.PROPERTY_PRINT_WHEN_DETAIL_OVERFLOWS))
			return new Boolean(jrElement.isPrintWhenDetailOverflows());

		return null;
	}

	protected JRPropertiesMap getPropertiesMapClone(JRDesignElement jrElement) {
		JRPropertiesMap propertiesMap = jrElement.getPropertiesMap();
		if (propertiesMap != null)
			propertiesMap = propertiesMap.cloneProperties();
		return propertiesMap;
	}

	public JRPropertiesMap getPropertiesMap() {
		JRDesignElement jrElement = (JRDesignElement) getValue();
		return jrElement.getPropertiesMap();
	}

	public Object getPropertyActualValue(Object id) {
		JRDesignElement jrElement = (JRDesignElement) getValue();
		JSSStyleResolver resolver = getStyleResolver();
		if (id.equals(JRBaseStyle.PROPERTY_BACKCOLOR)) {
			Color backcolor = resolver.getBackcolor(jrElement);
			return Colors.getSWTRGB4AWTGBColor(backcolor);
		}
		if (id.equals(JRBaseStyle.PROPERTY_FORECOLOR)) {
			Color forecolor = resolver.getForecolor(jrElement);
			return Colors.getSWTRGB4AWTGBColor(forecolor);
		}
		if (id.equals(JRBaseStyle.PROPERTY_MODE)) {
			return ModeEnum.TRANSPARENT.equals(resolver.getMode(jrElement, ModeEnum.OPAQUE));
		}
		return super.getPropertyActualValue(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#setPropertyValue(java.lang.Object, java.lang.Object)
	 */
	public void setPropertyValue(Object id, Object value) {
		JRDesignElement jrElement = (JRDesignElement) getValue();
		if (id.equals(JRDesignElement.PROPERTY_KEY))
			jrElement.setKey((String) value);
		else if (id.equals(JRDesignElement.PROPERTY_PARENT_STYLE)) {
			if (value != null && !((String) value).trim().isEmpty()) {
				if (!value.equals("")) { //$NON-NLS-1$
					JRStyle style = (JRStyle) getJasperDesign().getStylesMap().get(value);
					if (style != null) {
						// FIXME: It is important to set a null first the external style, because it is returned first on the
						// getPropertyValue and this raise a lot of events
						jrElement.setStyleNameReference(null);
						jrElement.setStyle(style);
					} else {
						jrElement.setStyleNameReference((String) value);
						// The local style is set to null so the external one will be used
						jrElement.setStyle(null);
					}
				}
			} else {
				jrElement.setStyleNameReference(null);
				jrElement.setStyle(null);
			}
		} else if (id.equals(JRDesignElement.PROPERTY_PRINT_WHEN_EXPRESSION))
			jrElement.setPrintWhenExpression(ExprUtil.setValues(jrElement.getPrintWhenExpression(), value));
		else if (id.equals(JRDesignElement.PROPERTY_PRINT_WHEN_GROUP_CHANGES)) {
			if (!value.equals("")) { //$NON-NLS-1$
				JRDesignDataset jrDataset = (JRDesignDataset) getElementDataset();
				JRGroup group = jrDataset.getGroupsMap().get(value);
				jrElement.setPrintWhenGroupChanges(group);
			} else {
				jrElement.setPrintWhenGroupChanges(null);
			}
		} else if (id.equals(JRDesignElement.PROPERTY_PROPERTY_EXPRESSIONS)) {
			if (value instanceof PropertyExpressionsDTO) {
				PropertyExpressionsDTO dto = (PropertyExpressionsDTO) value;
				JRPropertyExpression[] expr = jrElement.getPropertyExpressions();
				// Remove the old expression properties if any
				if (expr != null) {
					for (JRPropertyExpression ex : expr)
						jrElement.removePropertyExpression(ex);
				}
				// Add the new expression properties
				for (PropertyExpressionDTO p : dto.getProperties()) {
					if (p.isExpression()) {
						JRDesignPropertyExpression newExp = new JRDesignPropertyExpression();
						newExp.setName(p.getName());
						newExp.setValueExpression(p.getValueAsExpression());
						jrElement.addPropertyExpression(newExp);
					}
				}
				// now change properties, first remove the old ones if any
				JRPropertiesMap originalMap = jrElement.getPropertiesMap().cloneProperties();
				String[] names = jrElement.getPropertiesMap().getPropertyNames();
				for (int i = 0; i < names.length; i++) {
					jrElement.getPropertiesMap().removeProperty(names[i]);
				}
				// now add the new properties
				for (PropertyExpressionDTO p : dto.getProperties()) {
					if (!p.isExpression()) {
						jrElement.getPropertiesMap().setProperty(p.getName(), p.getValue());
					}
				}
				// really important to trigger the property with source the JR object and not the node
				// using the node could cause problem with the refresh of the advanced properties view
				firePropertyChange(new PropertyChangeEvent(jrElement, PROPERTY_MAP, originalMap, jrElement.getPropertiesMap()));
			}
		} else if (id.equals(JRDesignElement.PROPERTY_HEIGHT)) {
			jrElement.setHeight((Integer) Misc.nvl(value, Integer.valueOf(0)));
		} else if (id.equals(JRDesignElement.PROPERTY_WIDTH)) {
			jrElement.setWidth((Integer) Misc.nvl(value, Integer.valueOf(0)));
		} else if (id.equals(JRDesignElement.PROPERTY_X)) {
			jrElement.setX((Integer) Misc.nvl(value, Integer.valueOf(0)));
		} else if (id.equals(JRDesignElement.PROPERTY_Y)) {
			jrElement.setY((Integer) Misc.nvl(value, Integer.valueOf(0)));
		} else
		// colors
		if (id.equals(JRBaseStyle.PROPERTY_FORECOLOR)) {
			jrElement.setForecolor(Colors.getAWT4SWTRGBColor((AlfaRGB) value));
		} else if (id.equals(JRBaseStyle.PROPERTY_BACKCOLOR)) {
			jrElement.setBackcolor(Colors.getAWT4SWTRGBColor((AlfaRGB) value));
		} else
		// opacity
		if (id.equals(JRBaseStyle.PROPERTY_MODE))
			if (value == null)
				jrElement.setMode(null);
			else if ((Boolean) value)
				jrElement.setMode(ModeEnum.TRANSPARENT);
			else
				jrElement.setMode(ModeEnum.OPAQUE);
		else if (id.equals(JRDesignElement.PROPERTY_POSITION_TYPE)) {
			if (positionTypeD == null)
				getPropertyDescriptors();
			jrElement.setPositionType(positionTypeD.getEnumValue(value));
		} else if (id.equals(JRDesignElement.PROPERTY_STRETCH_TYPE))
			jrElement.setStretchType(stretchTypeD.getEnumValue(value));

		else if (id.equals(JRDesignElement.PROPERTY_PRINT_REPEATED_VALUES))
			jrElement.setPrintRepeatedValues(((Boolean) value).booleanValue());
		else if (id.equals(JRDesignElement.PROPERTY_REMOVE_LINE_WHEN_BLANK))
			jrElement.setRemoveLineWhenBlank(((Boolean) value).booleanValue());
		else if (id.equals(JRDesignElement.PROPERTY_PRINT_IN_FIRST_WHOLE_BAND))
			jrElement.setPrintInFirstWholeBand(((Boolean) value).booleanValue());
		else if (id.equals(JRDesignElement.PROPERTY_PRINT_WHEN_DETAIL_OVERFLOWS))
			jrElement.setPrintWhenDetailOverflows(((Boolean) value).booleanValue());
		else if (id.equals(PROPERTY_MAP)) {
			JRPropertiesMap originalMap = jrElement.getPropertiesMap().cloneProperties();
			JRPropertiesMap v = (JRPropertiesMap) value;
			String[] names = jrElement.getPropertiesMap().getPropertyNames();
			for (int i = 0; i < names.length; i++) {
				jrElement.getPropertiesMap().removeProperty(names[i]);
			}
			names = v.getPropertyNames();
			for (int i = 0; i < names.length; i++) {
				jrElement.getPropertiesMap().setProperty(names[i], v.getProperty(names[i]));
			}
			// really important to trigger the property with source the JR object and not the node
			// using the node could cause problem with the refresh of the advanced properties view
			firePropertyChange(new PropertyChangeEvent(jrElement, PROPERTY_MAP, originalMap, jrElement.getPropertiesMap()));
		}
	}

	public ICopyable.RESULT isCopyable2(Object parent) {
		if (parent instanceof MElementGroup || parent instanceof IPastableGraphic)
			return ICopyable.RESULT.COPYABLE;
		return ICopyable.RESULT.CHECK_PARENT;
	}

	/**
	 * Flag changed when some property that has graphical impact on the element is changed. This is used to redraw the
	 * elemnt only when something graphical is changed isndie it, all the other times can just be copied
	 */
	private boolean visualPropertyChanged = true;

	/**
	 * Return the graphical properties for an MGraphicalElement
	 */
	public HashSet<String> generateGraphicalProperties() {
		HashSet<String> result = new HashSet<String>();
		result.add(FORCE_GRAPHICAL_REFRESH);
		result.add(JRDesignElement.PROPERTY_PARENT_STYLE);
		result.add(JRDesignElement.PROPERTY_HEIGHT);
		result.add(JRDesignElement.PROPERTY_WIDTH);
		result.add(JRDesignElement.PROPERTY_X);
		result.add(JRDesignElement.PROPERTY_Y);
		result.add(JRBaseStyle.PROPERTY_FORECOLOR);
		result.add(JRBaseStyle.PROPERTY_BACKCOLOR);
		result.add(JRBaseStyle.PROPERTY_MODE);
		return result;
	}

	/**
	 * Static cache map of the graphic properties for every type of element. The cache is created when the element
	 * graphical properties are requested
	 */
	private static HashMap<Class<?>, HashSet<String>> cachedGraphicalProperties = new HashMap<Class<?>, HashSet<String>>();

	/**
	 * Return the graphical property for the actual type of element. If the are stored inside the cache then the cached
	 * version is returned. Otherwise they are calculated, cached an returned
	 * 
	 * @return an hashset of string that contains the graphical properties of the actual type of element. The graphical
	 *         properties of an element are those properties that affect the appearance of an element when changed
	 */
	@Override
	public HashSet<String> getGraphicalProperties() {
		HashSet<String> result = cachedGraphicalProperties.get(this.getClass());
		if (result == null) {
			result = generateGraphicalProperties();
			cachedGraphicalProperties.put(this.getClass(), result);
		}
		return result;
	}

	/**
	 * True if some graphical property is changed for the element, false otherwise
	 */
	@Override
	public boolean hasChangedProperty() {
		synchronized (this) {
			return visualPropertyChanged;
		}
	}

	/**
	 * The style requested refresh is the same to set the changed property to true in the standard elements
	 */
	@Override
	public void setStyleChangedProperty() {
		setChangedProperty(true);
	}

	/**
	 * Set the actual state of the property change flag
	 */
	@Override
	public void setChangedProperty(boolean value) {
		synchronized (this) {
			visualPropertyChanged = value;
		}
		if (value) {
			ANode parent = getParent();
			while (parent != null) {
				if (parent.getValue() != null && parent.getValue() instanceof JRChangeEventsSupport) {
					// We can't set the property on the element directly because even if it follow the hierarchy
					// there will be problem with elements inside the subeditors. Firing an event on the jr object
					// instead will end to propagate the update to every model binded to the jr object
					JRChangeEventsSupport parentEvents = (JRChangeEventsSupport) parent.getValue();
					parentEvents.getEventSupport().firePropertyChange(MGraphicElement.FORCE_GRAPHICAL_REFRESH, null, null);
					// We can exit the cycle since the setChangedProperty on the parent will propagate the
					// refresh on the upper levels
					break;
				} else {
					parent = parent.getParent();
				}
			}
		}
	}

	/**
	 * When a property change event occur, if the changed property is a graphical one then the visual property change flag
	 * is set to true
	 * 
	 * @param evt
	 *          the change event
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// check if it is already marked to be refreshed, in this case skip the check of the maps
		if (!visualPropertyChanged) {
			HashSet<String> graphicalProperties = getGraphicalProperties();
			if (graphicalProperties.contains(evt.getPropertyName())) {
				setChangedProperty(true);
			}
		}
		super.propertyChange(evt);
	}

	/**
	 * Return the styles used by this element and eventually by its children.
	 * 
	 * @return a not null map with the names of all the styles used by this element or one of its children. The value
	 *         corresponding to each style is the reference to the element that is using the style
	 */
	@Override
	public HashMap<String, List<ANode>> getUsedStyles() {
		HashMap<String, List<ANode>> result = super.getUsedStyles();
		JRStyle style = getValue().getStyle();
		addElementStyle(style, result);
		return result;
	}

	/**
	 * Set the style on the {@link JRDesignElement} of this node
	 */
	@Override
	public void setStyle(JRStyle style) {
		if (getValue() != null) {
			getValue().setStyle(style);
		}
	}

	protected SetValueCommand generateSetCommand(APropertyNode target, String propertyId, Object value) {
		SetValueCommand result = new SetValueCommand();
		result.setTarget(target);
		result.setPropertyId(propertyId);
		result.setPropertyValue(value);
		return result;
	}

	protected Color getColorClone(Color source) {
		if (source == null)
			return null;
		else
			return new Color(source.getRed(), source.getGreen(), source.getBlue(), source.getAlpha());
	}

	protected String getStringClone(String source) {
		if (source == null)
			return null;
		else
			return new String(source);
	}

	protected JRFont getFontClone(JRFont sourceFont) {
		if (sourceFont == null)
			return null;
		if (sourceFont instanceof JRBaseFont) {
			return (JRBaseFont) ((JRBaseFont) sourceFont).clone();
		}
		if (sourceFont instanceof JRTextElement) {
			return (JRTextElement) ((JRTextElement) sourceFont).clone();
		}
		return null;
	}

	/**
	 * Copy all the report independent properties from this element to the target one. The target must have the same type,
	 * or a subtype, of the value of this element. The report dependent properties are expressions, groups and styles
	 * essentially
	 * 
	 * @param target
	 *          the target of the copy
	 */
	public void trasnferProperties(JRElement target) {
		JRDesignElement jrTarget = (JRDesignElement) target;
		JRDesignElement jrSource = getValue();
		jrTarget.setKey(getStringClone(jrSource.getKey()));
		jrTarget.setWidth(jrSource.getWidth());
		jrTarget.setHeight(jrSource.getHeight());
		jrTarget.setBackcolor(jrSource.getOwnBackcolor());
		jrTarget.setForecolor(jrSource.getOwnForecolor());
		jrTarget.setMode(jrSource.getOwnModeValue());
		jrTarget.setPositionType(jrSource.getPositionTypeValue());
		jrTarget.setStretchType(jrSource.getStretchTypeValue());
		jrTarget.setPrintRepeatedValues(jrSource.isPrintRepeatedValues());
		jrTarget.setRemoveLineWhenBlank(jrSource.isRemoveLineWhenBlank());
		jrTarget.setPrintInFirstWholeBand(jrSource.isPrintInFirstWholeBand());
		jrTarget.setPrintWhenDetailOverflows(jrSource.isPrintWhenDetailOverflows());
	}

	/**
	 * Evaluate if the current element is between the bounds of the father. This is not done if the parent is a frame,
	 * since a frame has no constraints on the position of the children
	 * 
	 * @return a list with an informative message if the position is not valid, null if it is valid
	 */
	@Override
	protected List<ValidationError> doValidation() {
		boolean isValidPosition = true;
		ANode parent = getParent();
		if (parent instanceof APropertyNode && !(parent instanceof MFrame)) {
			JRDesignElement item = getValue();
			int x = item.getX();
			int y = item.getY();
			int w = item.getWidth();
			int h = item.getHeight();

			int fh = Integer.MAX_VALUE;
			int fw = Integer.MAX_VALUE;

			if (parent instanceof MGraphicElement) {
				MGraphicElement fatherModel = (MGraphicElement) getParent();
				JRDesignElement fitem = fatherModel.getValue();

				fh = fitem.getHeight();
				fw = fitem.getWidth();
			} else if (parent instanceof MBand) {
				JRDesignBand band = ((MBand) getParent()).getValue();
				fh = band.getHeight();
				fw = getJasperDesign().getPageWidth();
			} else if (parent instanceof MPage) {
				// I'm into a separate editor, here the relative dimensions inside the band dosen't count
				x = 0;
				y = 0;
				MPage ge = (MPage) getParent();
				JasperDesign jd = (JasperDesign) ge.getValue();
				fh = jd.getPageHeight();
				fw = jd.getPageWidth();
			} else if (parent instanceof IGraphicElement) {
				IGraphicElement ge = (IGraphicElement) getParent();
				Rectangle r = ge.getBounds();
				if (r != null) {
					fh = r.height;
					fw = r.width;
				}
			}
			JRElementGroup elementGroup = getValue().getElementGroup();
			if (elementGroup instanceof JRBand) {
				// Integer father_width = itemModel.getRoot().getJasperDesign().getColumnWidth();
				isValidPosition = y + h <= fh;
			} else if (elementGroup != null) {
				isValidPosition = fh >= h + y && x >= 0 && y >= 0 && fw >= x + w;
			}
		}
		if (!isValidPosition) {
			List<ValidationError> error = new ArrayList<ValidationError>();
			List<String> lst = new ArrayList<String>();
			lst.add(JRDesignElement.PROPERTY_HEIGHT);
			lst.add(JRDesignElement.PROPERTY_WIDTH);
			lst.add(JRDesignElement.PROPERTY_X);
			lst.add(JRDesignElement.PROPERTY_Y);
			error.add(new ValidationError(lst, Messages.ErrorDecorator_PositionErrorToolTip));
			return error;
		} else {
			return null;
		}
	}

	/**
	 * Check if the current element is inside a frame, and if that frame has the attribute to hide the out of bound
	 * contents check if this element is inside or outside that bounds
	 * 
	 * @return true if the element should be visible, false otherwise
	 */
	protected boolean checkVisibleFrame() {
		ANode parent = getParent();
		boolean visible = true;
		JRDesignElement currentEelement = getValue();
		int relative_X = currentEelement.getX();
		int relative_Y = currentEelement.getY();
		while (parent != null && visible) {
			if (parent instanceof MFrame) {
				boolean allowOutside = (Boolean) ((MFrame) parent).getPropertyValue(MFrame.PROPERTY_SHOW_OUT_OF_BOUND);
				if (!allowOutside) {
					JRDesignElement frame = (JRDesignElement) parent.getValue();
					Rectangle rect = new Rectangle(0, 0, frame.getWidth(), frame.getHeight());
					if (!rect.contains(relative_X, relative_Y)) {
						visible = false;
					}
				}
			}
			if (parent.getValue() instanceof JRDesignElement) {
				JRDesignElement jrParent = (JRDesignElement) parent.getValue();
				relative_Y += jrParent.getY();
				relative_X += jrParent.getX();
			}
			parent = parent.getParent();
		}
		return visible;
	}

	/**
	 * Check also if the element is inside a frame that hide the content out of his bounds
	 */
	@Override
	public boolean isVisible() {
		return super.isVisible() && checkVisibleFrame();
	}

	@Override
	public boolean isCuttable(ISelection currentSelection) {
		return true;
	}
}
