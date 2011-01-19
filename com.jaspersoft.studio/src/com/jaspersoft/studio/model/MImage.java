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
import net.sf.jasperreports.engine.JRHyperlinkParameter;
import net.sf.jasperreports.engine.base.JRBaseImage;
import net.sf.jasperreports.engine.base.JRBaseStyle;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignHyperlink;
import net.sf.jasperreports.engine.design.JRDesignImage;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.type.EvaluationTimeEnum;
import net.sf.jasperreports.engine.type.FillEnum;
import net.sf.jasperreports.engine.type.HorizontalAlignEnum;
import net.sf.jasperreports.engine.type.OnErrorTypeEnum;
import net.sf.jasperreports.engine.type.ScaleImageEnum;
import net.sf.jasperreports.engine.type.VerticalAlignEnum;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.util.IIconDescriptor;
import com.jaspersoft.studio.model.util.NodeIconDescriptor;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.checkbox.CheckBoxPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.expression.JRExpressionPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.hyperlink.parameter.dialog.ParameterDTO;
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
			iconDescriptor = new NodeIconDescriptor("image"); //$NON-NLS-1$
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
				Messages.common_expression);
		expressionD.setDescription(Messages.MImage_expression_description);
		desc.add(expressionD);

		ComboBoxPropertyDescriptor fillD = new ComboBoxPropertyDescriptor(JRBaseStyle.PROPERTY_FILL, Messages.common_fill,
				EnumHelper.getEnumNames(FillEnum.values(), NullEnum.INHERITED));
		fillD.setDescription(Messages.MImage_fill_description);
		desc.add(fillD);

		ComboBoxPropertyDescriptor scaleImageD = new ComboBoxPropertyDescriptor(JRBaseStyle.PROPERTY_SCALE_IMAGE,
				Messages.MImage_scale_image, EnumHelper.getEnumNames(ScaleImageEnum.values(), NullEnum.INHERITED));
		scaleImageD.setDescription(Messages.MImage_scale_image_description);
		desc.add(scaleImageD);

		ComboBoxPropertyDescriptor hAlignD = new ComboBoxPropertyDescriptor(JRBaseStyle.PROPERTY_HORIZONTAL_ALIGNMENT,
				Messages.common_horizontal_alignment, EnumHelper.getEnumNames(HorizontalAlignEnum.values(), NullEnum.INHERITED));
		hAlignD.setDescription(Messages.MImage_horizontal_alignment_description);
		desc.add(hAlignD);

		ComboBoxPropertyDescriptor vAlignD = new ComboBoxPropertyDescriptor(JRBaseStyle.PROPERTY_VERTICAL_ALIGNMENT,
				Messages.common_vertical_alignment, EnumHelper.getEnumNames(VerticalAlignEnum.values(), NullEnum.INHERITED));
		vAlignD.setDescription(Messages.MImage_vertical_alignment_description);
		desc.add(vAlignD);

		ComboBoxPropertyDescriptor onErrorTypeD = new ComboBoxPropertyDescriptor(JRBaseImage.PROPERTY_ON_ERROR_TYPE,
				Messages.MImage_on_error_type, EnumHelper.getEnumNames(OnErrorTypeEnum.values(), NullEnum.NULL));
		onErrorTypeD.setDescription(Messages.MImage_on_error_type_description);
		desc.add(onErrorTypeD);

		ComboBoxPropertyDescriptor evaluationTimeD = new ComboBoxPropertyDescriptor(JRDesignImage.PROPERTY_EVALUATION_TIME,
				Messages.MImage_evaluation_type, EnumHelper.getEnumNames(EvaluationTimeEnum.values(), NullEnum.NULL));
		evaluationTimeD.setDescription(Messages.MImage_evaluation_type_description);
		desc.add(evaluationTimeD);

		CheckBoxPropertyDescriptor usingCacheD = new CheckBoxPropertyDescriptor(JRBaseImage.PROPERTY_USING_CACHE,
				Messages.common_using_cache, NullEnum.INHERITED);
		usingCacheD.setDescription(Messages.MImage_using_cache_description);
		desc.add(usingCacheD);

		CheckBoxPropertyDescriptor lazyD = new CheckBoxPropertyDescriptor(JRBaseImage.PROPERTY_LAZY, Messages.MImage_lazy,
				NullEnum.NOTNULL);
		lazyD.setDescription(Messages.MImage_lazy_description);
		desc.add(lazyD);

		if (mHyperLink == null)
			mHyperLink = new MHyperLink(null);
		mHyperLink.createPropertyDescriptors(desc, defaultsMap);

		evaluationTimeD.setCategory(Messages.MImage_image_properties_category);
		onErrorTypeD.setCategory(Messages.MImage_image_properties_category);
		scaleImageD.setCategory(Messages.MImage_image_properties_category);
		expressionD.setCategory(Messages.MImage_image_properties_category);

		hAlignD.setCategory(Messages.MImage_image_properties_category);
		vAlignD.setCategory(Messages.MImage_image_properties_category);
		usingCacheD.setCategory(Messages.MImage_image_properties_category);
		lazyD.setCategory(Messages.MImage_image_properties_category);

		defaultsMap.put(JRBaseStyle.PROPERTY_FILL, null);
		defaultsMap.put(JRBaseStyle.PROPERTY_SCALE_IMAGE, null);
		defaultsMap.put(JRBaseStyle.PROPERTY_HORIZONTAL_ALIGNMENT, null);
		defaultsMap.put(JRBaseStyle.PROPERTY_VERTICAL_ALIGNMENT, null);
		defaultsMap.put(JRBaseImage.PROPERTY_ON_ERROR_TYPE, EnumHelper.getValue(OnErrorTypeEnum.ERROR, 1, true));
		defaultsMap.put(JRDesignImage.PROPERTY_EVALUATION_TIME, EnumHelper.getValue(EvaluationTimeEnum.NOW, 1, true));
		defaultsMap.put(JRDesignImage.PROPERTY_EXPRESSION, "java.lang.String"); //$NON-NLS-1$
		defaultsMap.put(JRBaseImage.PROPERTY_LAZY, Boolean.FALSE);
	}

	private MExpression mExpression;
	private MHyperLink mHyperLink;

	private MExpression mAnchorExpression;
	private MExpression mPageExpression;
	private MExpression mReferenceExpression;
	private MExpression mToolTipExpression;
	private ParameterDTO propertyDTO;

	@Override
	public Object getPropertyValue(Object id) {
		JRDesignImage jrElement = (JRDesignImage) getValue();
		if (id.equals(JRBaseStyle.PROPERTY_FILL))
			return EnumHelper.getValue(jrElement.getOwnFillValue(), 1, true);

		if (id.equals(JRBaseStyle.PROPERTY_SCALE_IMAGE))
			return EnumHelper.getValue(jrElement.getOwnScaleImageValue(), 1, true);
		if (id.equals(JRBaseStyle.PROPERTY_HORIZONTAL_ALIGNMENT))
			return EnumHelper.getValue(jrElement.getOwnHorizontalAlignmentValue(), 1, true);
		if (id.equals(JRBaseStyle.PROPERTY_VERTICAL_ALIGNMENT))
			return EnumHelper.getValue(jrElement.getOwnVerticalAlignmentValue(), 1, true);
		if (id.equals(JRBaseImage.PROPERTY_ON_ERROR_TYPE))
			return EnumHelper.getValue(jrElement.getOnErrorTypeValue(), 1, true);
		if (id.equals(JRDesignImage.PROPERTY_EVALUATION_TIME))
			return EnumHelper.getValue(jrElement.getEvaluationTimeValue(), 1, true);
		if (id.equals(JRDesignImage.PROPERTY_EXPRESSION)) {
			if (mExpression == null) {
				mExpression = new MExpression(jrElement.getExpression());
				setChildListener(mExpression);
			}
			return mExpression;
		}
		if (id.equals(JRDesignHyperlink.PROPERTY_HYPERLINK_PARAMETERS)) {
			if (propertyDTO == null) {
				propertyDTO = new ParameterDTO();
				propertyDTO.setJasperDesign(getJasperDesign());
				propertyDTO.setValue(jrElement.getHyperlinkParameters());
			}
			return propertyDTO;
		}
		if (id.equals(JRBaseImage.PROPERTY_USING_CACHE))
			return jrElement.isOwnUsingCache();
		if (id.equals(JRBaseImage.PROPERTY_LAZY))
			return new Boolean(jrElement.isLazy());
		// hyperlink --------------------------------------
		if (id.equals(JRDesignHyperlink.PROPERTY_LINK_TARGET))
			return jrElement.getLinkTarget();
		if (id.equals(JRDesignHyperlink.PROPERTY_LINK_TYPE))
			return jrElement.getLinkType();
		if (id.equals(JRDesignHyperlink.PROPERTY_HYPERLINK_ANCHOR_EXPRESSION)) {
			if (mAnchorExpression == null) {
				mAnchorExpression = new MExpression(jrElement.getHyperlinkAnchorExpression());
				setChildListener(mAnchorExpression);
			}
			return mAnchorExpression;
		}
		if (id.equals(JRDesignHyperlink.PROPERTY_HYPERLINK_PAGE_EXPRESSION)) {
			if (mPageExpression == null) {
				mPageExpression = new MExpression(jrElement.getHyperlinkPageExpression());
				setChildListener(mPageExpression);
			}
			return mPageExpression;
		}
		if (id.equals(JRDesignHyperlink.PROPERTY_HYPERLINK_REFERENCE_EXPRESSION)) {
			if (mReferenceExpression == null) {
				mReferenceExpression = new MExpression(jrElement.getHyperlinkReferenceExpression());
				setChildListener(mReferenceExpression);
			}
			return mReferenceExpression;
		}
		if (id.equals(JRDesignHyperlink.PROPERTY_HYPERLINK_TOOLTIP_EXPRESSION)) {
			if (mToolTipExpression == null) {
				mToolTipExpression = new MExpression(jrElement.getHyperlinkTooltipExpression());
				setChildListener(mToolTipExpression);
			}
			return mToolTipExpression;
		}
		return super.getPropertyValue(id);
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		JRDesignImage jrElement = (JRDesignImage) getValue();
		if (id.equals(JRBaseStyle.PROPERTY_FILL))
			jrElement.setFill((FillEnum) EnumHelper.getSetValue(FillEnum.values(), value, 1, true));

		else if (id.equals(JRBaseStyle.PROPERTY_SCALE_IMAGE))
			jrElement.setScaleImage((ScaleImageEnum) EnumHelper.getSetValue(ScaleImageEnum.values(), value, 1, true));
		else if (id.equals(JRBaseStyle.PROPERTY_HORIZONTAL_ALIGNMENT))
			jrElement.setHorizontalAlignment((HorizontalAlignEnum) EnumHelper.getSetValue(HorizontalAlignEnum.values(),
					value, 1, true));
		else if (id.equals(JRBaseStyle.PROPERTY_VERTICAL_ALIGNMENT))
			jrElement.setVerticalAlignment((VerticalAlignEnum) EnumHelper.getSetValue(VerticalAlignEnum.values(), value, 1,
					true));
		else if (id.equals(JRBaseImage.PROPERTY_ON_ERROR_TYPE))
			jrElement.setOnErrorType((OnErrorTypeEnum) EnumHelper.getSetValue(OnErrorTypeEnum.values(), value, 1, true));
		else if (id.equals(JRDesignImage.PROPERTY_EVALUATION_TIME))
			jrElement.setEvaluationTime((EvaluationTimeEnum) EnumHelper.getSetValue(EvaluationTimeEnum.values(), value, 1,
					true));
		else if (id.equals(JRDesignImage.PROPERTY_EXPRESSION)) {
			if (value instanceof MExpression) {
				mExpression = (MExpression) value;
				setChildListener(mExpression);
				JRExpression expression = (JRExpression) mExpression.getValue();
				jrElement.setExpression(expression);
			}
		} else if (id.equals(JRBaseImage.PROPERTY_USING_CACHE))
			jrElement.setUsingCache((Boolean) value);
		else if (id.equals(JRBaseImage.PROPERTY_LAZY))
			jrElement.setLazy(((Boolean) value).booleanValue());
		else if (id.equals(JRDesignHyperlink.PROPERTY_LINK_TARGET))
			jrElement.setLinkTarget((String) value);
		else if (id.equals(JRDesignHyperlink.PROPERTY_LINK_TYPE))
			jrElement.setLinkType((String) value);
		else if (id.equals(JRDesignHyperlink.PROPERTY_HYPERLINK_ANCHOR_EXPRESSION)) {
			if (value instanceof MExpression) {
				mAnchorExpression = (MExpression) value;
				setChildListener(mAnchorExpression);
				JRExpression expression = (JRExpression) mAnchorExpression.getValue();
				jrElement.setHyperlinkAnchorExpression(expression);
			}
		} else if (id.equals(JRDesignHyperlink.PROPERTY_HYPERLINK_PAGE_EXPRESSION)) {
			if (value instanceof MExpression) {
				mPageExpression = (MExpression) value;
				setChildListener(mPageExpression);
				JRExpression expression = (JRExpression) mPageExpression.getValue();
				jrElement.setHyperlinkPageExpression(expression);
			}
		} else if (id.equals(JRDesignHyperlink.PROPERTY_HYPERLINK_REFERENCE_EXPRESSION)) {
			if (value instanceof MExpression) {
				mReferenceExpression = (MExpression) value;
				setChildListener(mReferenceExpression);
				JRExpression expression = (JRExpression) mReferenceExpression.getValue();
				jrElement.setHyperlinkReferenceExpression(expression);
			}
		} else if (id.equals(JRDesignHyperlink.PROPERTY_HYPERLINK_TOOLTIP_EXPRESSION)) {
			if (value instanceof MExpression) {
				mToolTipExpression = (MExpression) value;
				setChildListener(mToolTipExpression);
				JRExpression expression = (JRExpression) mToolTipExpression.getValue();
				jrElement.setHyperlinkTooltipExpression(expression);
			}
		} else if (id.equals(JRDesignHyperlink.PROPERTY_HYPERLINK_PARAMETERS)) {
			if (value instanceof ParameterDTO) {
				ParameterDTO v = (ParameterDTO) value;

				JRHyperlinkParameter[] hyperlinkParameters = jrElement.getHyperlinkParameters();
				if (hyperlinkParameters != null)
					for (JRHyperlinkParameter prm : hyperlinkParameters)
						jrElement.removeHyperlinkParameter(prm);

				for (JRHyperlinkParameter param : v.getValue())
					jrElement.addHyperlinkParameter(param);

				propertyDTO = v;
			}
		}
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
