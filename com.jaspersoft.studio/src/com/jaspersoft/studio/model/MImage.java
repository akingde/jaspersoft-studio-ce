/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of Jaspersoft Open Studio.
 * 
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * Jaspersoft Open Studio is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with Jaspersoft Open Studio. If not,
 * see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.model;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.JRHyperlink;
import net.sf.jasperreports.engine.base.JRBaseImage;
import net.sf.jasperreports.engine.base.JRBaseStyle;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignHyperlink;
import net.sf.jasperreports.engine.design.JRDesignImage;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.type.EvaluationTimeEnum;
import net.sf.jasperreports.engine.type.FillEnum;
import net.sf.jasperreports.engine.type.HorizontalAlignEnum;
import net.sf.jasperreports.engine.type.HyperlinkTypeEnum;
import net.sf.jasperreports.engine.type.ModeEnum;
import net.sf.jasperreports.engine.type.OnErrorTypeEnum;
import net.sf.jasperreports.engine.type.ScaleImageEnum;
import net.sf.jasperreports.engine.type.VerticalAlignEnum;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.checkbox.CheckBoxPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.expression.JRExpressionPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.hyperlink.HyperLinkPropertyDescriptor;
import com.jaspersoft.studio.utils.EnumHelper;

/**
 * The Class MImage.
 */
public class MImage extends MGraphicElementLineBox {

	/** The icon descriptor. */
	private static IIconDescriptor iconDescriptor;

	/**
	 * Gets the icon descriptor.
	 * 
	 * @return the icon descriptor
	 */
	public static IIconDescriptor getIconDescriptor() {
		if (iconDescriptor == null)
			iconDescriptor = new NodeIconDescriptor("image");
		return iconDescriptor;
	}

	/**
	 * Instantiates a new m image.
	 */
	public MImage() {
		super();
	}

	/**
	 * Instantiates a new m image.
	 * 
	 * @param parent
	 *          the parent
	 * @param jrImage
	 *          the jr image
	 * @param newIndex
	 *          the new index
	 */
	public MImage(ANode parent, JRDesignImage jrImage, int newIndex) {
		super(parent, newIndex);
		setValue(jrImage);
	}

	private static IPropertyDescriptor[] descriptors;
	private static Map<String, Object> defaultsMap;

	@Override
	public Map<String, Object> getDefaultsMap() {
		return defaultsMap;
	}

	@Override
	public IPropertyDescriptor[] getDescriptors() {
		return descriptors;
	}

	@Override
	public void setDescriptors(IPropertyDescriptor[] descriptors1, Map<String, Object> defaultsMap1) {
		descriptors = descriptors1;
		defaultsMap = defaultsMap1;
	}

	/**
	 * Creates the property descriptors.
	 * 
	 * @param desc
	 *          the desc
	 */
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc, Map<String, Object> defaultsMap) {
		super.createPropertyDescriptors(desc, defaultsMap);

		JRExpressionPropertyDescriptor expressionD = new JRExpressionPropertyDescriptor(JRDesignImage.PROPERTY_EXPRESSION,
				"Expression");
		expressionD
				.setDescription("Definition of the expression that will be used to determine the image to be displayed.");
		desc.add(expressionD);

		ComboBoxPropertyDescriptor fillD = new ComboBoxPropertyDescriptor(JRBaseStyle.PROPERTY_FILL, "Fill", EnumHelper
				.getEnumNames(FillEnum.values(), NullEnum.INHERITED));
		fillD.setDescription("Type of the fill pattern used to fill objects.");
		desc.add(fillD);

		ComboBoxPropertyDescriptor scaleImageD = new ComboBoxPropertyDescriptor(JRBaseStyle.PROPERTY_SCALE_IMAGE,
				"Scale Image", EnumHelper.getEnumNames(ScaleImageEnum.values(), NullEnum.INHERITED));
		scaleImageD.setDescription("Image displaying type.");
		desc.add(scaleImageD);

		ComboBoxPropertyDescriptor hAlignD = new ComboBoxPropertyDescriptor(JRBaseStyle.PROPERTY_HORIZONTAL_ALIGNMENT,
				"Horizontal Alignment", EnumHelper.getEnumNames(HorizontalAlignEnum.values(), NullEnum.INHERITED));
		hAlignD.setDescription("Horizontal image alignment.");
		desc.add(hAlignD);

		ComboBoxPropertyDescriptor vAlignD = new ComboBoxPropertyDescriptor(JRBaseStyle.PROPERTY_VERTICAL_ALIGNMENT,
				"Vertical Alignment", EnumHelper.getEnumNames(VerticalAlignEnum.values(), NullEnum.INHERITED));
		vAlignD.setDescription("Vertical image alignment.");
		desc.add(vAlignD);

		ComboBoxPropertyDescriptor onErrorTypeD = new ComboBoxPropertyDescriptor(JRBaseImage.PROPERTY_ON_ERROR_TYPE,
				"On Error Type", EnumHelper.getEnumNames(OnErrorTypeEnum.values(), NullEnum.NULL));
		onErrorTypeD.setDescription("Controls the behavior of the engine in case the image is not available.");
		desc.add(onErrorTypeD);

		ComboBoxPropertyDescriptor evaluationTimeD = new ComboBoxPropertyDescriptor(JRDesignImage.PROPERTY_EVALUATION_TIME,
				"Evaluation Time", EnumHelper.getEnumNames(EvaluationTimeEnum.values(), NullEnum.NULL));
		evaluationTimeD
				.setDescription("The image to be printed is supplied by the associated expression. This expression can be evaluated at a specified moment. This could be useful, for example, when we want to have on the first page a chart that will be generated only after fetching all the data source rows.");
		desc.add(evaluationTimeD);

		CheckBoxPropertyDescriptor usingCacheD = new CheckBoxPropertyDescriptor(JRBaseImage.PROPERTY_USING_CACHE,
				"Using Cache", NullEnum.INHERITED);
		usingCacheD
				.setDescription("If true, tells the report engine to cache the images that are loaded from the same location.");
		desc.add(usingCacheD);

		CheckBoxPropertyDescriptor lazyD = new CheckBoxPropertyDescriptor(JRBaseImage.PROPERTY_LAZY, "Lazy",
				NullEnum.NOTNULL);
		lazyD.setDescription("Gives control over when the images are retrieved from their specified location.");
		desc.add(lazyD);

		mHyperLink = new MHyperLink((JRHyperlink) getValue());
		mHyperLink.createPropertyDescriptors(desc, defaultsMap);

		evaluationTimeD.setCategory("Image Properties");
		onErrorTypeD.setCategory("Image Properties");
		scaleImageD.setCategory("Image Properties");
		expressionD.setCategory("Image Properties");

		hAlignD.setCategory("Image Properties");
		vAlignD.setCategory("Image Properties");
		usingCacheD.setCategory("Image Properties");
		lazyD.setCategory("Image Properties");

		defaultsMap.put(JRBaseStyle.PROPERTY_FILL, null);
		defaultsMap.put(JRBaseStyle.PROPERTY_SCALE_IMAGE, null);
		defaultsMap.put(JRBaseStyle.PROPERTY_HORIZONTAL_ALIGNMENT, null);
		defaultsMap.put(JRBaseStyle.PROPERTY_VERTICAL_ALIGNMENT, null);
		defaultsMap.put(JRBaseImage.PROPERTY_ON_ERROR_TYPE, EnumHelper.getValue(ModeEnum.OPAQUE, 1, true));
		defaultsMap.put(JRDesignImage.PROPERTY_EVALUATION_TIME, EnumHelper.getValue(EvaluationTimeEnum.NOW, 1, true));
		defaultsMap.put(JRDesignImage.PROPERTY_EXPRESSION, "java.lang.String");
		defaultsMap.put(JRBaseImage.PROPERTY_LAZY, Boolean.FALSE);
	}

	private MExpression mExpression;
	private MHyperLink mHyperLink;

	@Override
	public Object getPropertyValue(Object id) {
		JRDesignImage jrElement = (JRDesignImage) getValue();
		if (id.equals(JRBaseStyle.PROPERTY_FILL))
			return EnumHelper.getValue(jrElement.getOwnFillValue());

		if (id.equals(JRBaseStyle.PROPERTY_SCALE_IMAGE))
			return EnumHelper.getValue(jrElement.getOwnScaleImageValue());
		if (id.equals(JRBaseStyle.PROPERTY_HORIZONTAL_ALIGNMENT))
			return EnumHelper.getValue(jrElement.getOwnHorizontalAlignmentValue());
		if (id.equals(JRBaseStyle.PROPERTY_VERTICAL_ALIGNMENT))
			return EnumHelper.getValue(jrElement.getOwnVerticalAlignmentValue());
		if (id.equals(JRBaseImage.PROPERTY_ON_ERROR_TYPE))
			return EnumHelper.getValue(jrElement.getOnErrorTypeValue());
		if (id.equals(JRDesignImage.PROPERTY_EVALUATION_TIME))
			return EnumHelper.getValue(jrElement.getEvaluationTimeValue());
		if (id.equals(JRDesignImage.PROPERTY_EXPRESSION)) {
			if (mExpression == null)
				mExpression = new MExpression(jrElement.getExpression());
			return mExpression;
		}
		if (id.equals(JRBaseImage.PROPERTY_USING_CACHE))
			return jrElement.isOwnUsingCache();
		if (id.equals(JRBaseImage.PROPERTY_LAZY))
			return new Boolean(jrElement.isLazy());
		if (mHyperLink == null)
			mHyperLink = new MHyperLink((JRHyperlink) getValue());
		Object val = mHyperLink.getPropertyValue(id);
		if (val != null)
			return val;
		return super.getPropertyValue(id);
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		JRDesignImage jrElement = (JRDesignImage) getValue();
		if (id.equals(JRBaseStyle.PROPERTY_FILL))
			jrElement.setFill(FillEnum.getByValue(EnumHelper.getSetValue((Integer) value)));

		else if (id.equals(JRBaseStyle.PROPERTY_SCALE_IMAGE))
			jrElement.setScaleImage(ScaleImageEnum.getByValue(EnumHelper.getSetValue((Integer) value)));
		else if (id.equals(JRBaseStyle.PROPERTY_HORIZONTAL_ALIGNMENT))
			jrElement.setHorizontalAlignment(HorizontalAlignEnum.getByValue(EnumHelper.getSetValue((Integer) value)));
		else if (id.equals(JRBaseStyle.PROPERTY_VERTICAL_ALIGNMENT))
			jrElement.setVerticalAlignment(VerticalAlignEnum.getByValue(EnumHelper.getSetValue((Integer) value)));
		else if (id.equals(JRBaseImage.PROPERTY_ON_ERROR_TYPE))
			jrElement.setOnErrorType(OnErrorTypeEnum.getByValue(EnumHelper.getSetValue((Integer) value)));
		else if (id.equals(JRDesignImage.PROPERTY_EVALUATION_TIME))
			jrElement.setEvaluationTime(EvaluationTimeEnum.getByValue(EnumHelper.getSetValue((Integer) value)));
		else if (id.equals(JRDesignImage.PROPERTY_EXPRESSION))
			jrElement.setExpression((JRExpression) value);

		else if (id.equals(JRBaseImage.PROPERTY_USING_CACHE))
			jrElement.setUsingCache((Boolean) value);
		else if (id.equals(JRBaseImage.PROPERTY_LAZY))
			jrElement.setLazy(((Boolean) value).booleanValue());
		else if (id.equals(JRDesignHyperlink.PROPERTY_LINK_TARGET))
			jrElement.setLinkTarget((String) value);
		else if (id.equals(JRDesignHyperlink.PROPERTY_LINK_TYPE))
			jrElement.setHyperlinkType((HyperlinkTypeEnum) EnumHelper
					.getSetValue(HyperlinkTypeEnum.values(), value, 0, false));
		else
			super.setPropertyValue(id, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.MGeneric#getDefaultHeight()
	 */
	@Override
	public int getDefaultHeight() {
		return 50;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.MGeneric#getDefaultWidth()
	 */
	@Override
	public int getDefaultWidth() {
		return 50;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.MGeneric#createJRElement(net.sf.jasperreports.engine.design.JasperDesign)
	 */
	@Override
	public JRDesignElement createJRElement(JasperDesign jasperDesign) {
		JRDesignElement jrDesignElement = new JRDesignImage(jasperDesign);
		jrDesignElement.setWidth(getDefaultWidth());
		jrDesignElement.setHeight(getDefaultHeight());
		return jrDesignElement;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.MGeneric#getDisplayText()
	 */
	@Override
	public String getDisplayText() {
		return getIconDescriptor().getTitle();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.MGeneric#getImagePath()
	 */
	@Override
	public ImageDescriptor getImagePath() {
		return getIconDescriptor().getIcon16();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.MGeneric#getToolTip()
	 */
	@Override
	public String getToolTip() {
		return getIconDescriptor().getToolTip();
	}

}
