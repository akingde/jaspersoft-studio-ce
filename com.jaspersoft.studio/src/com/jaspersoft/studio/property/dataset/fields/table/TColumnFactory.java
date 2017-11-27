/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.dataset.fields.table;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.property.dataset.DatasetUtil;
import com.jaspersoft.studio.property.dataset.da.widgets.ParameterPropertyWidget;
import com.jaspersoft.studio.property.dataset.fields.table.column.CheckboxColumnSupport;
import com.jaspersoft.studio.property.dataset.fields.table.column.ClassNameColumnSupport;
import com.jaspersoft.studio.property.dataset.fields.table.column.ExpressionColumnSupport;
import com.jaspersoft.studio.property.dataset.fields.table.column.FieldNameColumnSupport;
import com.jaspersoft.studio.property.dataset.fields.table.column.JRPropertiesColumnSupport;
import com.jaspersoft.studio.property.dataset.fields.table.column.JRPropertyColumnSupport;
import com.jaspersoft.studio.property.dataset.fields.table.column.PropertyColumnSupport;
import com.jaspersoft.studio.property.dataset.fields.table.widget.AWidget;
import com.jaspersoft.studio.property.dataset.fields.table.widget.WFieldName;
import com.jaspersoft.studio.property.dataset.fields.table.widget.WJRProperties;
import com.jaspersoft.studio.property.dataset.fields.table.widget.WJRProperty;
import com.jaspersoft.studio.property.dataset.fields.table.widget.WProperty;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.properties.PropertyMetadata;

public class TColumnFactory {

	public static TableViewerColumn addColumn(final TColumn c, final TableViewer tviewer) {
		return addColumn(c, tviewer, getEditingSupport(c, tviewer));
	}

	public static TableViewerColumn addColumn(final TColumn c, final TableViewer tviewer,
			final PropertyColumnSupport cs) {
		TableViewerColumn tcol = new TableViewerColumn(tviewer, SWT.NONE);
		tcol.getColumn().setText(c.getLabel());

		tcol.setEditingSupport(cs);
		tcol.setLabelProvider(new AColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return cs.getText(element);
			}

			@Override
			public Image getImage(Object element) {
				return cs.getImage(element);
			}

			@Override
			public String getToolTipText(Object element) {
				return getText(element) + "\n" + c.getDescription();
			}
		});

		((TableLayout) tviewer.getTable().getLayout()).addColumnData(new ColumnWeightData(c.getWeight()));
		return tcol;
	}

	public static TreeViewerColumn addColumn(final TColumn c, final TreeViewer tviewer) {
		return addColumn(c, tviewer, getEditingSupport(c, tviewer));
	}

	public static TreeViewerColumn addColumn(final TColumn c, final TreeViewer tviewer,
			final PropertyColumnSupport cs) {
		TreeViewerColumn tcol = new TreeViewerColumn(tviewer, SWT.NONE);
		tcol.getColumn().setText(c.getLabel());
		tcol.getColumn().setWidth(200);

		tcol.setLabelProvider(new AColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return cs.getText(element);
			}

			@Override
			public Image getImage(Object element) {
				return cs.getImage(element);
			}

			@Override
			public String getToolTipText(Object element) {
				return cs.getToolTipText(element);
			}
		});

		return tcol;
	}

	private static PropertyColumnSupport getEditingSupport(final TColumn c, final ColumnViewer tviewer) {
		if (c.getType().equals("property"))
			return new PropertyColumnSupport(tviewer, c);
		if (c.getType().equals("classTypeCombo"))
			return new ClassNameColumnSupport(tviewer, c);
		if (c.getType().equals("properties"))
			return new JRPropertiesColumnSupport(tviewer, c);
		if (c.getType().equals("fieldName"))
			return new FieldNameColumnSupport(tviewer, c);
		if (c.getType().equals("jrProperty"))
			return new JRPropertyColumnSupport(tviewer, c);
		if (c.getType().equals("checkbox"))
			return new CheckboxColumnSupport(tviewer, c);
		if (c.getType().equals("expression"))
			return new ExpressionColumnSupport(tviewer, c);
		return null;
	}

	public static void addWidget(TColumn c, Composite parent, Object element, JasperReportsConfiguration jConfig) {
		Class<? extends AWidget> clazz = wmap.get(c.getType());
		if (clazz != null) {
			try {
				Constructor<? extends AWidget> constr = clazz.asSubclass(AWidget.class).getConstructor(Composite.class,
						TColumn.class, Object.class, JasperReportsConfiguration.class);
				constr.newInstance(parent, c, element, jConfig);
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
	}

	private static Map<String, Class<? extends AWidget>> wmap = new HashMap<>();
	static {
		wmap.put("property", WProperty.class);
		wmap.put("checkbox", WProperty.class);
		wmap.put("classTypeCombo", WProperty.class);
		wmap.put("expression", WProperty.class);
		wmap.put("properties", WJRProperties.class);

		wmap.put("jrProperty", WJRProperty.class);
		wmap.put("fieldName", WFieldName.class);
	}

	public static void addControlType(String key, Class<? extends AWidget> wcnt) {
		wmap.put(key, wcnt);
	}

	public static ParameterPropertyWidget createParameterPropertyWidget(String p, Composite parent, Image icon,
			String da, JRDesignDataset dataset, JasperReportsConfiguration jConfig) {
		return createParameterPropertyWidget(p, parent, icon, da, dataset, -1, jConfig);
	}

	public static ParameterPropertyWidget createParameterPropertyWidget(String p, Composite parent, Image icon,
			String da, JRDesignDataset dataset, int width, JasperReportsConfiguration jConfig) {
		PropertyMetadata pm = DatasetUtil.getPmap(jConfig).get(p);
		if (pm != null) {
			TColumn c = TColumnFactory.getTColumn(pm);
			c.setWeight(width);
			return new ParameterPropertyWidget(c, parent, icon, da, dataset);
		}
		return null;
	}

	public static TColumn getTColumn(PropertyMetadata pm) {
		TColumn c = new TColumn();
		c.setPropertyMetadata(pm);
		c.setType("jrProperty");
		return c;
	}
}
