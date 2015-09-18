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
package com.jaspersoft.studio.components.map.property;

import java.util.List;

import net.sf.jasperreports.components.map.Item;
import net.sf.jasperreports.components.map.ItemProperty;
import net.sf.jasperreports.components.map.MapComponent;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.engine.JRExpression;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import com.jaspersoft.studio.components.map.property.desc.PathDescriptor;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.util.ItemPropertyUtil;
import com.jaspersoft.studio.property.itemproperty.desc.AItemDataListPropertyDescriptor;
import com.jaspersoft.studio.property.itemproperty.dialog.AItemDialog;
import com.jaspersoft.studio.property.itemproperty.dialog.FormItemDialog;
import com.jaspersoft.studio.property.itemproperty.sp.SPItemDataList;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.utils.Misc;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

/**
 * 
 * @author Veaceslav Chicu (schicu@users.sourceforge.net)
 * 
 */
public class PathPropertyDescriptor extends AItemDataListPropertyDescriptor {

	public PathPropertyDescriptor(Object id, String displayName) {
		super(id, displayName);
	}

	protected SPItemDataList createSPWidget(Composite parent,
			AbstractSection section) {
		return new SPItemDataList(parent, section, this) {
			@Override
			protected AItemDialog createItemDialog() {
				return new FormItemDialog(UIUtils.getShell(), getDescriptor(),
						(JasperReportsConfiguration) section
								.getJasperReportsContext()) {
					private ScrolledComposite sc;

					@Override
					protected void createValue(CTabFolder tabFolder) {
						CTabItem bptab = new CTabItem(tabFolder, SWT.NONE);
						bptab.setText(Messages.ItemDialog_0);

						sc = new ScrolledComposite(tabFolder, SWT.V_SCROLL);

						final Composite cmp = new Composite(sc, SWT.NONE);
						cmp.setLayout(new GridLayout(2, false));

						sc.setContent(cmp);
						sc.setExpandHorizontal(true);
						sc.setExpandVertical(true);
						sc.setAlwaysShowScrollBars(true);
						bptab.setControl(sc);

						createItemProperty(cmp, MapComponent.ITEM_PROPERTY_name);

						Label lbl = new Label(cmp, SWT.NONE);
						GridData gd = new GridData(GridData.FILL_HORIZONTAL);
						gd.horizontalSpan = 2;
						lbl.setLayoutData(gd);
						lbl.setText(com.jaspersoft.studio.messages.Messages.MapSection_1);

						createItemProperty(cmp,
								MapComponent.ITEM_PROPERTY_latitude);
						createItemProperty(cmp,
								MapComponent.ITEM_PROPERTY_longitude);
						createItemProperty(cmp,
								MapComponent.ITEM_PROPERTY_address);

						createSeparator(cmp);

						createItemProperty(cmp,
								MapComponent.ITEM_PROPERTY_style);

						createSeparator(cmp);

						createItemProperty(cmp,
								MapComponent.ITEM_PROPERTY_STYLE_strokeColor);
						createItemProperty(cmp,
								MapComponent.ITEM_PROPERTY_STYLE_strokeOpacity);
						createItemProperty(cmp,
								MapComponent.ITEM_PROPERTY_STYLE_strokeWeight);

						createSeparator(cmp);

						createItemProperty(cmp,
								MapComponent.ITEM_PROPERTY_STYLE_fillColor);
						createItemProperty(cmp,
								MapComponent.ITEM_PROPERTY_STYLE_fillOpacity);

						createSeparator(cmp);

						createItemProperty(cmp,
								MapComponent.ITEM_PROPERTY_STYLE_isPolygon);
						createItemProperty(cmp,
								MapComponent.ITEM_PROPERTY_clickable);
						createItemProperty(cmp,
								MapComponent.ITEM_PROPERTY_STYLE_editable);
						createItemProperty(cmp,
								MapComponent.ITEM_PROPERTY_draggable);
						createItemProperty(cmp,
								MapComponent.ITEM_PROPERTY_STYLE_geodesic);
						createItemProperty(cmp,
								MapComponent.ITEM_PROPERTY_visible);
						createItemProperty(cmp,
								MapComponent.ITEM_PROPERTY_MARKER_zIndex);

						sc.setMinSize(cmp.computeSize(SWT.DEFAULT, SWT.DEFAULT));
						sc.addControlListener(new ControlAdapter() {
							public void controlResized(ControlEvent e) {
								sc.setMinSize(cmp.computeSize(SWT.DEFAULT,
										SWT.DEFAULT));
							}
						});
					}

					private void createSeparator(Composite cmp) {
						GridData gd = new GridData(GridData.FILL_HORIZONTAL);
						gd.horizontalSpan = 2;
						new Label(cmp, SWT.SEPARATOR | SWT.HORIZONTAL)
								.setLayoutData(gd);
					}
				};
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see com.jaspersoft.studio.tibcomaps.property.sp.SPItemDataList#
			 * getElementViewerComparator()
			 */
			@Override
			protected ViewerComparator getElementViewerComparator() {
				return new ViewerComparator() {
					public int compare(Viewer viewer, Object e1, Object e2) {
						Item t1 = (Item) e1;
						Item t2 = (Item) e2;

						ItemProperty ip1 = getItemProperty(t1);
						ItemProperty ip2 = getItemProperty(t2);
						if (ip1 != null && ip2 != null) {
							JRExpression ip1exp = ip1.getValueExpression();
							JRExpression ip2exp = ip2.getValueExpression();
							if (ip1exp != null && ip2exp != null)
								return Misc.nvl(ip1exp.getText()).compareTo(
										Misc.nvl(ip2exp.getText()));
							if (ip1exp != null)
								return -1;
							if (ip2exp != null)
								return 1;
							return Misc.nvl(ip1.getValue()).compareTo(
									Misc.nvl(ip2.getValue()));
						}
						return -1;
					}

					private ItemProperty getItemProperty(Item item) {
						List<ItemProperty> p = item.getProperties();
						if (p != null)
							return ItemPropertyUtil.getProperty(p,
									MapComponent.ITEM_PROPERTY_name);

						return null;
					}
				};
			}
		};
	}

	@Override
	protected void initShowColumns() {
		descriptor = new PathDescriptor();
	}
}
