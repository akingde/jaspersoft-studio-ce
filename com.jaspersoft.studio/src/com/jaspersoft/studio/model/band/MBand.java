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
package com.jaspersoft.studio.model.band;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRBand;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.design.JRDesignBand;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.type.BandTypeEnum;
import net.sf.jasperreports.engine.type.SplitTypeEnum;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Color;
import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.IContainer;
import com.jaspersoft.studio.model.IContainerEditPart;
import com.jaspersoft.studio.model.IGraphicElement;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.IPastable;
import com.jaspersoft.studio.model.IPastableGraphic;
import com.jaspersoft.studio.model.MExpression;
import com.jaspersoft.studio.model.util.IIconDescriptor;
import com.jaspersoft.studio.model.util.NodeIconDescriptor;
import com.jaspersoft.studio.property.descriptor.IntegerPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.expression.JRExpressionPropertyDescriptor;
import com.jaspersoft.studio.utils.EnumHelper;

/**
 * The Class MBand.
 * 
 * @author Chicu Veaceslav
 */
public class MBand extends APropertyNode implements IGraphicElement, IPastable, IPastableGraphic, IContainer,
		IContainerEditPart {

	private static final Integer CONST_HEIGHT = new Integer(50);
	/** The icon descriptor. */
	private static IIconDescriptor iconDescriptor;

	/**
	 * Gets the icon descriptor.
	 * 
	 * @return the icon descriptor
	 */
	public static IIconDescriptor getIconDescriptor() {
		if (iconDescriptor == null)
			iconDescriptor = new NodeIconDescriptor("band"); //$NON-NLS-1$
		return iconDescriptor;
	}

	/**
	 * Gets the band type.
	 * 
	 * @return the band type
	 */
	public BandTypeEnum getBandType() {
		return bandType;
	}

	/** The band type. */
	private BandTypeEnum bandType;

	/**
	 * Instantiates a new m band.
	 */
	public MBand() {
		super();
	}

	/**
	 * Instantiates a new m band.
	 * 
	 * @param parent
	 *          the parent
	 * @param jrband
	 *          the jrband
	 * @param bandtype
	 *          the bandtype
	 * @param newIndex
	 *          the new index
	 */
	public MBand(ANode parent, JRBand jrband, BandTypeEnum bandtype, int newIndex) {
		super(parent, newIndex);
		setValue(jrband);
		this.bandType = bandtype;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.INode#getDisplayText()
	 */
	public String getDisplayText() {
		JRDesignBand value = (JRDesignBand) getValue();
		if (bandType.equals(BandTypeEnum.DETAIL)) {
			if (value != null)
				return Messages.MBand_detail + " [" + value.getHeight() + "px] ";// + value.hashCode(); //$NON-NLS-1$ //$NON-NLS-2$
			else
				return Messages.MBand_detail + " "; //$NON-NLS-1$
		}
		if (value == null)
			return bandType.getName();
		return value.getOrigin().getBandTypeValue().getName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.INode#getForeground()
	 */
	@Override
	public Color getForeground() {
		if (getValue() == null)
			return ColorConstants.lightGray;
		return ColorConstants.black;
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
		if (getValue() != null)
			return Messages.MBand_band + ": " + getDisplayText() + " 	"; //$NON-NLS-1$ //$NON-NLS-2$

		return getIconDescriptor().getToolTip();
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
		IntegerPropertyDescriptor heightD = new IntegerPropertyDescriptor(JRDesignBand.PROPERTY_HEIGHT,
				Messages.common_height);
		heightD.setDescription(Messages.MBand_height_description);
		desc.add(heightD);

		ComboBoxPropertyDescriptor splitStyleD = new ComboBoxPropertyDescriptor(JRDesignBand.PROPERTY_SPLIT_TYPE,
				Messages.common_split_type, EnumHelper.getEnumNames(SplitTypeEnum.values(), NullEnum.NULL));
		splitStyleD.setDescription(Messages.MBand_split_type_dscription);
		desc.add(splitStyleD);

		JRExpressionPropertyDescriptor printWhenExpD = new JRExpressionPropertyDescriptor(
				JRDesignBand.PROPERTY_PRINT_WHEN_EXPRESSION, Messages.common_print_when_expression);
		printWhenExpD.setDescription(Messages.MBand_print_when_expression_desription);
		desc.add(printWhenExpD);

		defaultsMap.put(JRDesignBand.PROPERTY_HEIGHT, CONST_HEIGHT);
		defaultsMap.put(JRDesignBand.PROPERTY_SPLIT_TYPE, null);
	}

	private MExpression mExpression;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#getPropertyValue(java.lang.Object)
	 */
	public Object getPropertyValue(Object id) {
		JRDesignBand jrband = (JRDesignBand) getValue();
		if (jrband != null) {
			if (id.equals(JRDesignBand.PROPERTY_HEIGHT))
				return new Integer(jrband.getHeight());
			if (id.equals(JRDesignBand.PROPERTY_SPLIT_TYPE))
				return EnumHelper.getValue(jrband.getSplitTypeValue(), 1, true);
			if (id.equals(JRDesignBand.PROPERTY_PRINT_WHEN_EXPRESSION)) {
				if (mExpression == null) {
					mExpression = new MExpression(jrband.getPrintWhenExpression());
					setChildListener(mExpression);
				}
				return mExpression;
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#setPropertyValue(java.lang.Object, java.lang.Object)
	 */
	public void setPropertyValue(Object id, Object value) {
		JRDesignBand jrband = (JRDesignBand) getValue();
		if (jrband != null) {
			if (id.equals(JRDesignBand.PROPERTY_HEIGHT))
				jrband.setHeight(((Integer) value).intValue());
			else if (id.equals(JRDesignBand.PROPERTY_SPLIT_TYPE))
				jrband.setSplitType((SplitTypeEnum) EnumHelper.getSetValue(SplitTypeEnum.values(), value, 1, true));
			else if (id.equals(JRDesignBand.PROPERTY_PRINT_WHEN_EXPRESSION)) {
				if (value instanceof MExpression) {
					mExpression = (MExpression) value;
					setChildListener(mExpression);
					JRExpression expression = (JRExpression) mExpression.getValue();
					jrband.setPrintWhenExpression(expression);
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.IGraphicElement#getDefaultHeight()
	 */
	public int getDefaultHeight() {
		return 50;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.IGraphicElement#getDefaultWidth()
	 */
	public int getDefaultWidth() {
		return 800;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.IGraphicElement#createJRElement(net.sf.jasperreports.engine.design.JasperDesign)
	 */
	public JRDesignElement createJRElement(JasperDesign jasperDesign) {
		return null;
	}

	/**
	 * Creates the jr band.
	 * 
	 * @return the jR design band
	 */
	public static JRDesignBand createJRBand() {
		JRDesignBand jrBand = new JRDesignBand();
		jrBand.setHeight(50);
		return jrBand;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.IGraphicElement#getBounds()
	 */
	public Rectangle getBounds() {
		INode parent = getParent();
		Rectangle parentBounds = ((IGraphicElement) parent).getBounds();
		Rectangle bounds = new Rectangle(parentBounds);
		if (getValue() == null) {
			return parentBounds;
		} else {
			bounds.setSize(parentBounds.width, ((JRDesignBand) getValue()).getHeight());
			int h = 0;
			for (INode b : parent.getChildren()) {
				if (b == this)
					break;
				if (b instanceof MBand) {
					if (b.getValue() != null)
						h += ((JRDesignBand) b.getValue()).getHeight() + BAND_GAP;
				}
			}
			bounds.setLocation(parentBounds.x, h + getJasperDesign().getTopMargin());
		}
		return bounds;
	}

	private static int BAND_GAP = 0;
}
