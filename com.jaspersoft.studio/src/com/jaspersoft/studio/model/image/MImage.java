/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model.image;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.editor.defaults.DefaultManager;
import com.jaspersoft.studio.help.HelpReferenceBuilder;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.DefaultValue;
import com.jaspersoft.studio.model.MGraphicElementLineBox;
import com.jaspersoft.studio.model.MHyperLink;
import com.jaspersoft.studio.model.dataset.MDatasetRun;
import com.jaspersoft.studio.model.util.IIconDescriptor;
import com.jaspersoft.studio.model.util.NodeIconDescriptor;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.checkbox.CheckBoxPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.combo.RWComboBoxPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.expression.ExprUtil;
import com.jaspersoft.studio.property.descriptor.expression.JRExpressionPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.expression.JRImageExpressionPropertyDescriptor;
import com.jaspersoft.studio.property.descriptors.ImageHAlignPropertyDescriptor;
import com.jaspersoft.studio.property.descriptors.ImageVAlignPropertyDescriptor;
import com.jaspersoft.studio.property.descriptors.NamedEnumPropertyDescriptor;
import com.jaspersoft.studio.property.descriptors.SpinnerPropertyDescriptor;
import com.jaspersoft.studio.utils.EnumHelper;
import com.jaspersoft.studio.utils.ModelUtils;

import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.JRHyperlinkParameter;
import net.sf.jasperreports.engine.base.JRBaseImage;
import net.sf.jasperreports.engine.base.JRBaseStyle;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignDatasetRun;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignElementDataset;
import net.sf.jasperreports.engine.design.JRDesignHyperlink;
import net.sf.jasperreports.engine.design.JRDesignImage;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.type.EvaluationTimeEnum;
import net.sf.jasperreports.engine.type.FillEnum;
import net.sf.jasperreports.engine.type.OnErrorTypeEnum;
import net.sf.jasperreports.engine.type.ScaleImageEnum;

/*
 * The Class MImage.
 */
public class MImage extends MGraphicElementLineBox {
	
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	
	private static NamedEnumPropertyDescriptor<ScaleImageEnum> scaleImageD;
	
	private static ImageHAlignPropertyDescriptor hAlignD;
	
	private static NamedEnumPropertyDescriptor<FillEnum> fillD;
	
	private static ImageVAlignPropertyDescriptor vAlignD;
	
	private static NamedEnumPropertyDescriptor<OnErrorTypeEnum> onErrorTypeD;
	
	private static NamedEnumPropertyDescriptor<EvaluationTimeEnum> evaluationTimeD;
	
	/** The icon descriptor. */
	private static IIconDescriptor iconDescriptor;

	private IPropertyDescriptor[] descriptors;
	
	private RWComboBoxPropertyDescriptor evalGroupD;
	
	private MHyperLink mHyperLink;
	
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

	@Override
	public JRDesignImage getValue() {
		return (JRDesignImage) super.getValue();
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
	protected void setGroupItems(String[] items) {
		super.setGroupItems(items);
		if (evalGroupD != null)
			evalGroupD.setItems(items);
	}

	@Override
	public JRDataset getElementDataset() {
		return getElementDataset(this);
	}

	/**
	 * Return the dataset nearest to this element
	 * 
	 * @param node
	 *          the actual node
	 * @return the dataset nearest to this element or null if it can't be found
	 */
	private JRDataset getElementDataset(ANode node) {
		if (node instanceof APropertyNode) {
			APropertyNode pnode = (APropertyNode) node;
			MDatasetRun mdataset = (MDatasetRun) pnode.getPropertyValue(JRDesignElementDataset.PROPERTY_DATASET_RUN);
			if (mdataset != null) {
				JRDesignDatasetRun datasetRun = mdataset.getValue();
				if (datasetRun != null) {
					String dsname = datasetRun.getDatasetName();
					return getJasperDesign().getDatasetMap().get(dsname);
				}
			}
		}
		ANode parent = node.getParent();
		if (parent != null) {
			return getElementDataset(parent);
		} else if (getJasperDesign() != null) {
			return getJasperDesign().getMainDataset();
		} else {
			return null;
		}
	}

	/**
	 * Creates the property descriptors.
	 * 
	 * @param desc
	 *          the desc
	 */
	@Override
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc) {
		super.createPropertyDescriptors(desc);

		JRImageExpressionPropertyDescriptor expressionD = new JRImageExpressionPropertyDescriptor(JRDesignImage.PROPERTY_EXPRESSION,
				Messages.common_expression);
		expressionD.setDescription(Messages.MImage_expression_description);
		desc.add(expressionD);
		expressionD.setHelpRefBuilder(new HelpReferenceBuilder(
				"net.sf.jasperreports.doc/docs/schema.reference.html?cp=0_1#imageExpression"));

		fillD = new NamedEnumPropertyDescriptor<FillEnum>(JRBaseStyle.PROPERTY_FILL, Messages.common_fill, FillEnum.SOLID,
				NullEnum.INHERITED);
		fillD.setDescription(Messages.MImage_fill_description);
		desc.add(fillD);
		fillD.setHelpRefBuilder(new HelpReferenceBuilder(
				"net.sf.jasperreports.doc/docs/schema.reference.html?cp=0_1#style_fill"));

		scaleImageD = new NamedEnumPropertyDescriptor<ScaleImageEnum>(JRBaseStyle.PROPERTY_SCALE_IMAGE,
				Messages.MImage_scale_image, ScaleImageEnum.CLIP, NullEnum.INHERITED);
		scaleImageD.setDescription(Messages.MImage_scale_image_description);
		desc.add(scaleImageD);

		hAlignD = new ImageHAlignPropertyDescriptor(JRBaseStyle.PROPERTY_HORIZONTAL_IMAGE_ALIGNMENT,
				Messages.common_horizontal_alignment, NullEnum.INHERITED);
		hAlignD.setDescription(Messages.MImage_horizontal_alignment_description);
		desc.add(hAlignD);

		vAlignD = new ImageVAlignPropertyDescriptor(JRBaseStyle.PROPERTY_VERTICAL_IMAGE_ALIGNMENT,
				Messages.common_vertical_alignment, NullEnum.INHERITED);
		vAlignD.setDescription(Messages.MImage_vertical_alignment_description);
		desc.add(vAlignD);

		onErrorTypeD = new NamedEnumPropertyDescriptor<OnErrorTypeEnum>(JRBaseImage.PROPERTY_ON_ERROR_TYPE,
				Messages.MImage_on_error_type, OnErrorTypeEnum.BLANK, NullEnum.NULL);
		onErrorTypeD.setDescription(Messages.MImage_on_error_type_description);
		desc.add(onErrorTypeD);

		evaluationTimeD = new NamedEnumPropertyDescriptor<EvaluationTimeEnum>(JRDesignImage.PROPERTY_EVALUATION_TIME,
				Messages.MImage_evaluation_type, EvaluationTimeEnum.AUTO, NullEnum.NOTNULL);
		evaluationTimeD.setDescription(Messages.MImage_evaluation_type_description);
		desc.add(evaluationTimeD);

		evalGroupD = new RWComboBoxPropertyDescriptor(JRDesignImage.PROPERTY_EVALUATION_GROUP,
				Messages.MTextField_evaluation_group, new String[] { "" }, NullEnum.NULL); //$NON-NLS-1$
		evalGroupD.setDescription(Messages.MTextField_evaluation_group_description);
		desc.add(evalGroupD);

		CheckBoxPropertyDescriptor usingCacheD = new CheckBoxPropertyDescriptor(JRBaseImage.PROPERTY_USING_CACHE,
				Messages.common_using_cache, NullEnum.INHERITED);
		usingCacheD.setDescription(Messages.MImage_using_cache_description);
		desc.add(usingCacheD);

		CheckBoxPropertyDescriptor lazyD = new CheckBoxPropertyDescriptor(JRBaseImage.PROPERTY_LAZY, Messages.MImage_lazy,
				NullEnum.NOTNULL);
		lazyD.setDescription(Messages.MImage_lazy_description);
		desc.add(lazyD);

		setHelpPrefix(desc, "net.sf.jasperreports.doc/docs/schema.reference.html?cp=0_1#image");

		JRExpressionPropertyDescriptor anchorNameExp = new JRExpressionPropertyDescriptor(
				JRDesignImage.PROPERTY_ANCHOR_NAME_EXPRESSION, Messages.MTextField_anchorNameLabel);
		anchorNameExp.setDescription(Messages.MTextField_anchorNameDescription);
		desc.add(anchorNameExp);

		SpinnerPropertyDescriptor bookmarkLevel = new SpinnerPropertyDescriptor(JRDesignImage.PROPERTY_BOOKMARK_LEVEL,
				Messages.MTextField_bookmarkLevelLabel);
		bookmarkLevel.setDescription(Messages.MTextField_bookmarkLevelDescription);
		bookmarkLevel.setHelpRefBuilder(new HelpReferenceBuilder(
				"net.sf.jasperreports.doc/docs/schema.reference.html?cp=0_1#chart_bookmarkLevel")); //$NON-NLS-1$
		desc.add(bookmarkLevel);

		if (mHyperLink == null)
			mHyperLink = new MHyperLink(null);
		mHyperLink.createPropertyDescriptors(desc);

		setHelpPrefix(desc, "net.sf.jasperreports.doc/docs/schema.reference.html?cp=0_1#image");

		evaluationTimeD.setCategory(Messages.MImage_image_properties_category);
		evalGroupD.setCategory(Messages.MImage_image_properties_category);
		onErrorTypeD.setCategory(Messages.MImage_image_properties_category);
		scaleImageD.setCategory(Messages.MImage_image_properties_category);
		expressionD.setCategory(Messages.MImage_image_properties_category);

		hAlignD.setCategory(Messages.MImage_image_properties_category);
		vAlignD.setCategory(Messages.MImage_image_properties_category);
		usingCacheD.setCategory(Messages.MImage_image_properties_category);
		lazyD.setCategory(Messages.MImage_image_properties_category);
	}
	
	@Override
	protected Map<String, DefaultValue> createDefaultsMap() {
		Map<String, DefaultValue> defaultsMap = super.createDefaultsMap();
		
		defaultsMap.put(JRBaseStyle.PROPERTY_FILL, new DefaultValue(null, true));
		defaultsMap.put(JRBaseStyle.PROPERTY_SCALE_IMAGE, new DefaultValue(null, true));
		defaultsMap.put(JRBaseStyle.PROPERTY_HORIZONTAL_IMAGE_ALIGNMENT, new DefaultValue(null, true));
		defaultsMap.put(JRBaseStyle.PROPERTY_VERTICAL_IMAGE_ALIGNMENT, new DefaultValue(null, true));
		defaultsMap.put(JRDesignImage.PROPERTY_EXPRESSION, new DefaultValue("java.lang.String", false)); //$NON-NLS-1$
		defaultsMap.put(JRBaseImage.PROPERTY_LAZY, new DefaultValue(Boolean.FALSE, false));
		defaultsMap.put(JRDesignImage.PROPERTY_EVALUATION_TIME, new DefaultValue(EvaluationTimeEnum.NOW, false));
		
		int onErrorValue = NamedEnumPropertyDescriptor.getIntValue(OnErrorTypeEnum.ERROR, NullEnum.NULL, OnErrorTypeEnum.ERROR);
		defaultsMap.put(JRBaseImage.PROPERTY_ON_ERROR_TYPE, new DefaultValue(onErrorValue, true));

		defaultsMap.putAll(new MHyperLink(null).getDefaultsPropertiesMap());	
		
		return defaultsMap;
	}

	@Override
	public Object getPropertyValue(Object id) {
		JRDesignImage jrElement = (JRDesignImage) getValue();
		if (id.equals(JRBaseStyle.PROPERTY_FILL))
			return fillD.getIntValue(jrElement.getOwnFillValue());
		if (id.equals(JRBaseStyle.PROPERTY_SCALE_IMAGE))
			return scaleImageD.getIntValue(jrElement.getOwnScaleImageValue());
		if (id.equals(JRBaseStyle.PROPERTY_HORIZONTAL_IMAGE_ALIGNMENT))
			return hAlignD.getIntValue(jrElement.getOwnHorizontalImageAlign());
		if (id.equals(JRBaseStyle.PROPERTY_VERTICAL_IMAGE_ALIGNMENT))
			return vAlignD.getIntValue(jrElement.getOwnVerticalImageAlign());
		if (id.equals(JRBaseImage.PROPERTY_ON_ERROR_TYPE))
			return onErrorTypeD.getIntValue(jrElement.getOnErrorTypeValue());
		if (id.equals(JRDesignImage.PROPERTY_EVALUATION_TIME))
			return jrElement.getEvaluationTimeValue();
		if (id.equals(JRDesignImage.PROPERTY_EXPRESSION))
			return ExprUtil.getExpression(jrElement.getExpression());

		if (id.equals(JRDesignImage.PROPERTY_EVALUATION_GROUP)) {
			if (jrElement.getEvaluationGroup() != null)
				return jrElement.getEvaluationGroup().getName();
			return ""; //$NON-NLS-1$
		}

		if (id.equals(JRDesignHyperlink.PROPERTY_HYPERLINK_PARAMETERS)) {
			return jrElement.getHyperlinkParameters();
		}
		if (id.equals(JRBaseImage.PROPERTY_USING_CACHE))
			return jrElement.getUsingCache();
		if (id.equals(JRBaseImage.PROPERTY_LAZY))
			return new Boolean(jrElement.isLazy());
		// hyperlink --------------------------------------
		if (id.equals(JRDesignHyperlink.PROPERTY_LINK_TARGET))
			return jrElement.getLinkTarget();
		if (id.equals(JRDesignHyperlink.PROPERTY_LINK_TYPE))
			return jrElement.getLinkType();
		if (id.equals(JRDesignHyperlink.PROPERTY_HYPERLINK_ANCHOR_EXPRESSION))
			return ExprUtil.getExpression(jrElement.getHyperlinkAnchorExpression());
		if (id.equals(JRDesignHyperlink.PROPERTY_HYPERLINK_PAGE_EXPRESSION))
			return ExprUtil.getExpression(jrElement.getHyperlinkPageExpression());
		if (id.equals(JRDesignHyperlink.PROPERTY_HYPERLINK_REFERENCE_EXPRESSION))
			return ExprUtil.getExpression(jrElement.getHyperlinkReferenceExpression());
		if (id.equals(JRDesignHyperlink.PROPERTY_HYPERLINK_TOOLTIP_EXPRESSION))
			return ExprUtil.getExpression(jrElement.getHyperlinkTooltipExpression());
		if (id.equals(JRDesignHyperlink.PROPERTY_HYPERLINK_WHEN_EXPRESSION)) {
			return ExprUtil.getExpression(jrElement.getHyperlinkWhenExpression());
		}
		if (id.equals(JRDesignImage.PROPERTY_ANCHOR_NAME_EXPRESSION)) {
			return ExprUtil.getExpression(jrElement.getAnchorNameExpression());
		}
		if (id.equals(JRDesignImage.PROPERTY_BOOKMARK_LEVEL)) {
			return jrElement.getBookmarkLevel();
		}
		return super.getPropertyValue(id);
	}

	@Override
	public Object getPropertyActualValue(Object id) {
		JRDesignImage jrElement = (JRDesignImage) getValue();
		if (id.equals(JRBaseStyle.PROPERTY_FILL))
			return fillD.getIntValue(jrElement.getFillValue());
		if (id.equals(JRBaseStyle.PROPERTY_SCALE_IMAGE))
			return scaleImageD.getIntValue(jrElement.getScaleImageValue());
		if (id.equals(JRBaseStyle.PROPERTY_HORIZONTAL_IMAGE_ALIGNMENT))
			return hAlignD.getIntValue(jrElement.getHorizontalImageAlign());
		if (id.equals(JRBaseStyle.PROPERTY_VERTICAL_IMAGE_ALIGNMENT))
			return vAlignD.getIntValue(jrElement.getVerticalImageAlign());
		if (id.equals(JRBaseImage.PROPERTY_ON_ERROR_TYPE))
			return onErrorTypeD.getIntValue(jrElement.getOnErrorTypeValue());
		if (id.equals(JRDesignImage.PROPERTY_EVALUATION_TIME))
			return jrElement.getEvaluationTimeValue();
		if (id.equals(JRDesignImage.PROPERTY_EXPRESSION))
			return ExprUtil.getExpression(jrElement.getExpression());

		if (id.equals(JRDesignImage.PROPERTY_EVALUATION_GROUP)) {
			if (jrElement.getEvaluationGroup() != null)
				return jrElement.getEvaluationGroup().getName();
			return ""; //$NON-NLS-1$
		}
		if (id.equals(JRBaseImage.PROPERTY_USING_CACHE))
			return jrElement.getUsingCache();
		if (id.equals(JRBaseImage.PROPERTY_LAZY))
			return new Boolean(jrElement.isLazy());
		// hyperlink --------------------------------------
		if (id.equals(JRDesignHyperlink.PROPERTY_LINK_TARGET))
			return jrElement.getLinkTarget();
		if (id.equals(JRDesignHyperlink.PROPERTY_LINK_TYPE))
			return jrElement.getLinkType();
		if (id.equals(JRDesignHyperlink.PROPERTY_HYPERLINK_ANCHOR_EXPRESSION))
			return ExprUtil.getExpression(jrElement.getHyperlinkAnchorExpression());
		if (id.equals(JRDesignHyperlink.PROPERTY_HYPERLINK_PAGE_EXPRESSION))
			return ExprUtil.getExpression(jrElement.getHyperlinkPageExpression());
		if (id.equals(JRDesignHyperlink.PROPERTY_HYPERLINK_REFERENCE_EXPRESSION))
			return ExprUtil.getExpression(jrElement.getHyperlinkReferenceExpression());
		if (id.equals(JRDesignHyperlink.PROPERTY_HYPERLINK_TOOLTIP_EXPRESSION))
			return ExprUtil.getExpression(jrElement.getHyperlinkTooltipExpression());
		if (id.equals(JRDesignHyperlink.PROPERTY_HYPERLINK_WHEN_EXPRESSION))
			return ExprUtil.getExpression(jrElement.getHyperlinkWhenExpression());
		return super.getPropertyActualValue(id);
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		JRDesignImage jrElement = (JRDesignImage) getValue();
		if (id.equals(JRBaseStyle.PROPERTY_FILL))
			jrElement.setFill(fillD.getEnumValue(value));
		else if (id.equals(JRBaseStyle.PROPERTY_SCALE_IMAGE))
			jrElement.setScaleImage(scaleImageD.getEnumValue(value));
		else if (id.equals(JRBaseStyle.PROPERTY_HORIZONTAL_IMAGE_ALIGNMENT))
			jrElement.setHorizontalImageAlign(hAlignD.getEnumValue(value));
		else if (id.equals(JRBaseStyle.PROPERTY_VERTICAL_IMAGE_ALIGNMENT))
			jrElement.setVerticalImageAlign(vAlignD.getEnumValue(value));
		else if (id.equals(JRBaseImage.PROPERTY_ON_ERROR_TYPE))
			jrElement.setOnErrorType(onErrorTypeD.getEnumValue(value));
		else if (id.equals(JRDesignImage.PROPERTY_EVALUATION_TIME)) {
			EvaluationTimeEnum evalTime = EnumHelper.getEnumByObjectValue(EvaluationTimeEnum.values(), value);
			jrElement.setEvaluationTime(evalTime);
			if(evalTime != null && !evalTime.equals(EvaluationTimeEnum.GROUP)) {
				jrElement.setEvaluationGroup(null);
			}			
		}
		else if (id.equals(JRDesignImage.PROPERTY_EVALUATION_GROUP)) {
			jrElement.setEvaluationGroup(
					ModelUtils.getGroupForProperty(value, (JRDesignDataset) getElementDataset()));
		} else if (id.equals(JRDesignImage.PROPERTY_EXPRESSION))
			jrElement.setExpression(ExprUtil.setValues(jrElement.getExpression(), value));
		else if (id.equals(JRBaseImage.PROPERTY_USING_CACHE))
			jrElement.setUsingCache((Boolean) value);
		else if (id.equals(JRBaseImage.PROPERTY_LAZY))
			jrElement.setLazy(((Boolean) value).booleanValue());
		else if (id.equals(JRDesignHyperlink.PROPERTY_LINK_TARGET))
			jrElement.setLinkTarget((String) value);
		else if (id.equals(JRDesignHyperlink.PROPERTY_LINK_TYPE))
			jrElement.setLinkType((String) value);
		else if (id.equals(JRDesignHyperlink.PROPERTY_HYPERLINK_ANCHOR_EXPRESSION))
			jrElement.setHyperlinkAnchorExpression(ExprUtil.setValues(jrElement.getHyperlinkAnchorExpression(), value));
		else if (id.equals(JRDesignHyperlink.PROPERTY_HYPERLINK_PAGE_EXPRESSION))
			jrElement.setHyperlinkPageExpression(ExprUtil.setValues(jrElement.getHyperlinkPageExpression(), value));
		else if (id.equals(JRDesignHyperlink.PROPERTY_HYPERLINK_REFERENCE_EXPRESSION))
			jrElement.setHyperlinkReferenceExpression(ExprUtil.setValues(jrElement.getHyperlinkReferenceExpression(), value));
		else if (id.equals(JRDesignHyperlink.PROPERTY_HYPERLINK_TOOLTIP_EXPRESSION))
			jrElement.setHyperlinkTooltipExpression(ExprUtil.setValues(jrElement.getHyperlinkTooltipExpression(), value));
		else if (id.equals(JRDesignHyperlink.PROPERTY_HYPERLINK_WHEN_EXPRESSION)) {
			jrElement.setHyperlinkWhenExpression(ExprUtil.setValues(jrElement.getHyperlinkWhenExpression(), value));
		} else if (id.equals(JRDesignImage.PROPERTY_ANCHOR_NAME_EXPRESSION))
			jrElement.setAnchorNameExpression(ExprUtil.setValues(jrElement.getAnchorNameExpression(), value));
		else if (id.equals(JRDesignImage.PROPERTY_BOOKMARK_LEVEL))
			jrElement.setBookmarkLevel(value != null ? Integer.parseInt(value.toString()) : 0);
		else if (id.equals(JRDesignHyperlink.PROPERTY_HYPERLINK_PARAMETERS)) {
			JRHyperlinkParameter[] oldParameters = jrElement.getHyperlinkParameters();
			JRHyperlinkParameter[] newParameters = (JRHyperlinkParameter[]) value;
			if (oldParameters != null) {
				for (JRHyperlinkParameter prm : oldParameters) {
					jrElement.removeHyperlinkParameter(prm);
				}
			}
			for (JRHyperlinkParameter param : newParameters) {
				jrElement.addHyperlinkParameter(param);
			}
		}
		super.setPropertyValue(id, value);
	}

	@Override
	public int getDefaultHeight() {
		Object defaultValue = DefaultManager.INSTANCE.getDefaultPropertiesValue(this.getClass(),
				JRDesignElement.PROPERTY_HEIGHT);
		return defaultValue != null ? (Integer) defaultValue : 50;
	}

	@Override
	public int getDefaultWidth() {
		Object defaultValue = DefaultManager.INSTANCE.getDefaultPropertiesValue(this.getClass(),
				JRDesignElement.PROPERTY_WIDTH);
		return defaultValue != null ? (Integer) defaultValue : 50;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.MGeneric#createJRElement(net.sf.jasperreports.engine.design.JasperDesign)
	 */
	@Override
	public JRDesignElement createJRElement(JasperDesign jasperDesign) {
		JRDesignElement jrDesignElement = new JRDesignImage(jasperDesign);

		DefaultManager.INSTANCE.applyDefault(this.getClass(), jrDesignElement);

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
		JRDesignImage value = getValue();
		if (value != null) {
			String tip = "";
			if (value.getExpression() != null)
				tip += value.getExpression().getText();
			if (tip.trim().isEmpty()) return getIconDescriptor().getTitle() + ": " + Messages.common_noExpressionMessage;
			else return getIconDescriptor().getTitle() + ": " + tip;
		}
		return getIconDescriptor().getToolTip();
	}

	/**
	 * Return the graphical properties for an MImage
	 */
	public HashSet<String> generateGraphicalProperties() {
		HashSet<String> result = super.generateGraphicalProperties();
		result.add(JRBaseStyle.PROPERTY_FILL);
		result.add(JRBaseStyle.PROPERTY_SCALE_IMAGE);
		result.add(JRBaseStyle.PROPERTY_HORIZONTAL_IMAGE_ALIGNMENT);
		result.add(JRBaseStyle.PROPERTY_VERTICAL_IMAGE_ALIGNMENT);
		result.add(JRDesignImage.PROPERTY_EXPRESSION);
		return result;
	}

	@Override
	public void trasnferProperties(JRElement target) {
		super.trasnferProperties(target);

		JRDesignImage jrSource = (JRDesignImage) getValue();
		if (jrSource != null) {
			JRDesignImage jrTarget = (JRDesignImage) target;
			jrTarget.setFill(jrSource.getOwnFillValue());
			jrTarget.setScaleImage(jrSource.getOwnScaleImageValue());
			jrTarget.setHorizontalImageAlign(jrSource.getOwnHorizontalImageAlign());
			jrTarget.setVerticalImageAlign(jrSource.getOwnVerticalImageAlign());
			jrTarget.setOnErrorType(jrSource.getOnErrorTypeValue());
			jrTarget.setUsingCache(jrSource.getUsingCache());
			jrTarget.setLazy(jrSource.isLazy());
		}
	}
}
