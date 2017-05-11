/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.itemproperty.label;

import java.util.Collection;

import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.property.itemproperty.desc.ADescriptor;
import com.jaspersoft.studio.property.itemproperty.desc.DescriptorPropertyLabelProvider;
import com.jaspersoft.studio.widgets.framework.ui.ItemPropertyDescription;

import net.sf.jasperreports.components.items.Item;
import net.sf.jasperreports.components.items.ItemData;
import net.sf.jasperreports.components.items.ItemProperty;
import net.sf.jasperreports.components.items.StandardItem;
import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.JRDatasetRun;
import net.sf.jasperreports.engine.JRElementDataset;
import net.sf.jasperreports.engine.type.IncrementTypeEnum;
import net.sf.jasperreports.engine.type.ResetTypeEnum;

public class ItemLabelProvider extends ColumnLabelProvider implements ITableLabelProvider {
	private DescriptorPropertyLabelProvider iplp;
	private ADescriptor descriptor;

	public ItemLabelProvider(ADescriptor showProperties) {
		this.descriptor = showProperties;
		iplp = new DescriptorPropertyLabelProvider(descriptor);
	}

	@Override
	public String getText(Object element) {
		if (element instanceof Collection<?>) {
			return ((Collection<?>) element).size() + Messages.ItemLabelProvider_1;
		} else if (element instanceof JRElementDataset) {
			JRDatasetRun dsrun = ((JRElementDataset) element).getDatasetRun();
			if (dsrun == null)
				return Messages.ItemLabelProvider_3;
			return getText(dsrun);
		} else if (element instanceof JRDatasetRun) {
			return Misc.nvl(((JRDatasetRun) element).getDatasetName(), Messages.ItemLabelProvider_3);
		} else if (element instanceof ItemData) {
			return getText4ItemData((ItemData) element);
		} else if (element instanceof Item) {
			StandardItem item = (StandardItem) element;
			if (!Misc.isNullOrEmpty(item.getProperties())) {
				String str = ""; //$NON-NLS-1$
				if (descriptor.isShowAllProperties() && Misc.isNullOrEmpty(descriptor.getItemPropertyDescriptors()))
					for (ItemProperty ip : item.getProperties())
						str += ip.getName() + ": " + iplp.getText(ip) //$NON-NLS-1$
								+ "; "; //$NON-NLS-1$
				else
					for (ItemPropertyDescription<?> ipd : descriptor.getItemPropertyDescriptors()) {
						// if (ipd.isMandatory())
						for (ItemProperty ip : item.getProperties()) {
							if (ip.getName().equals(ipd.getName()))
								str += ipd.getName() + ": " + iplp.getText(ip) //$NON-NLS-1$
										+ "; "; //$NON-NLS-1$
						}
					}
				return str;
			}
			return "- Empty -"; //$NON-NLS-1$
		}
		return super.getText(element);
	}

	public String getText4ItemData(ItemData element) {
		ItemData id = (ItemData) element;
		JRElementDataset ds = id.getDataset();
		return (ds != null ? getText(ds) + " ; " + id.getItems().size() + " Items" : Messages.ItemLabelProvider_2);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.ColumnLabelProvider#getImage(java.lang.Object)
	 */
	@Override
	public Image getImage(Object element) {
		if (descriptor != null)
			return descriptor.getIcon(element);
		return null;
	}

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		if (descriptor != null)
			return descriptor.getIcon(element);
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		if (element instanceof ItemData)
			return getColumnText4ItemData((ItemData) element, columnIndex);
		if (element instanceof StandardItem && descriptor.getItemPropertyDescriptors().length > columnIndex) {
			StandardItem item = (StandardItem) element;
			if (!Misc.isNullOrEmpty(item.getProperties())) {
				ItemPropertyDescription<?> showProperty = descriptor.getItemPropertyDescriptors()[columnIndex];
				for (ItemProperty ip : item.getProperties())
					if (ip.getName().equals(showProperty.getName()))
						return iplp.getText(ip);
				return "NULL"; //$NON-NLS-1$
			}
		}
		return ""; //$NON-NLS-1$
	}

	protected String getColumnText4ItemData(ItemData element, int columnIndex) {
		if (columnIndex == 0)
			return Messages.ItemLabelProvider_7 + " " + (columnIndex + 1);
		return "";
	}

	@Override
	public void update(ViewerCell cell) {
		super.update(cell);
	}

	@Override
	public String getToolTipText(Object element) {
		if (element instanceof JRElementDataset) {
			JRElementDataset ed = (JRElementDataset) element;
			String str = ed.getDatasetRun() == null ? Messages.ItemLabelProvider_3 : getText(ed.getDatasetRun()) + "\n"; //$NON-NLS-1$
			str += "\n"; //$NON-NLS-1$
			str += Messages.ItemLabelProvider_11 + ed.getResetTypeValue();
			if (ed.getResetTypeValue() == ResetTypeEnum.GROUP && ed.getResetGroup() != null)
				str += ed.getResetGroup().getName();
			str += "\n"; //$NON-NLS-1$
			str += Messages.ItemLabelProvider_13 + ed.getIncrementTypeValue();
			if (ed.getIncrementTypeValue() == IncrementTypeEnum.GROUP && ed.getIncrementGroup() != null)
				str += ed.getIncrementGroup().getName();

			if (ed.getIncrementWhenExpression() != null) {
				str += "\n"; //$NON-NLS-1$
				str += Messages.ItemLabelProvider_15 + ed.getIncrementWhenExpression().getText();
			}
			return str;
		}
		if (element instanceof ItemData) {
			return getToolTipText4ItemData(element);
		}
		if (element instanceof Item) {
			StandardItem item = (StandardItem) element;
			if (!Misc.isNullOrEmpty(item.getProperties())) {
				String str = ""; //$NON-NLS-1$
				if (descriptor.isShowAllProperties() && Misc.isNullOrEmpty(descriptor.getItemPropertyDescriptors()))
					for (ItemProperty ip : item.getProperties())
						str += ip.getName() + ": " + iplp.getText(ip) //$NON-NLS-1$
								+ "; "; //$NON-NLS-1$
				else
					for (ItemPropertyDescription<?> ipd : descriptor.getItemPropertyDescriptors()) {
						for (ItemProperty ip : item.getProperties()) {
							if (ip.getName().equals(ipd.getName()))
								str += ipd.getName() + ":" + iplp.getText(ip) //$NON-NLS-1$
										+ "\n"; //$NON-NLS-1$
						}
					}
				return str;
			}
			return ""; //$NON-NLS-1$
		}
		return super.getToolTipText(element);
	}

	protected String getToolTipText4ItemData(Object element) {
		ItemData id = (ItemData) element;
		JRElementDataset ds = id.getDataset();
		return (ds != null ? getToolTipText(ds) + "\n" + id.getItems().size() + " Items" : Messages.ItemLabelProvider_18);
	}

	@Override
	public Point getToolTipShift(Object object) {
		return new Point(5, 5);
	}

	@Override
	public int getToolTipDisplayDelayTime(Object object) {
		return 100; // msec
	}

	@Override
	public int getToolTipTimeDisplayed(Object object) {
		return 5000; // msec
	}
}
