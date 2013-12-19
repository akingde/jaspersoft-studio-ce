/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.components.chart.property.widget;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.charts.JRDataRange;
import net.sf.jasperreports.charts.design.JRDesignDataRange;
import net.sf.jasperreports.charts.util.JRMeterInterval;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.engine.JRExpression;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.color.ColorCellEditor;
import com.jaspersoft.studio.property.descriptor.color.ColorLabelProvider;
import com.jaspersoft.studio.property.descriptor.expression.JRExpressionCellEditor;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.ASPropertyWidget;
import com.jaspersoft.studio.swt.widgets.table.DeleteButton;
import com.jaspersoft.studio.swt.widgets.table.INewElement;
import com.jaspersoft.studio.swt.widgets.table.ListContentProvider;
import com.jaspersoft.studio.swt.widgets.table.ListOrderButtons;
import com.jaspersoft.studio.swt.widgets.table.NewButton;
import com.jaspersoft.studio.utils.AlfaRGB;
import com.jaspersoft.studio.utils.Colors;
import com.jaspersoft.studio.utils.Misc;

public class SPMeterInterval extends ASPropertyWidget {
	private final class TLabelProvider extends LabelProvider implements ITableLabelProvider {
		private ColorLabelProvider colorLabel = new ColorLabelProvider(NullEnum.NULL);

		public Image getColumnImage(Object element, int columnIndex) {
			JRMeterInterval mi = (JRMeterInterval) element;
			switch (columnIndex) {
			case 1:
				AlfaRGB color = Colors.getSWTRGB4AWTGBColor(mi.getBackgroundColor());
				Double alfa = mi.getAlphaDouble();
				color.setAlfa(alfa != null ? alfa : 1.0d);
				return colorLabel.getImage(color);
			}
			return null;
		}

		public String getColumnText(Object element, int columnIndex) {
			JRMeterInterval mi = (JRMeterInterval) element;
			JRDataRange dataRange = mi.getDataRange();

			switch (columnIndex) {
			case 0:
				return Misc.nvl(mi.getLabel(), "");
			case 1:
				AlfaRGB color = Colors.getSWTRGB4AWTGBColor(mi.getBackgroundColor());
				Double alfa = mi.getAlphaDouble();
				color.setAlfa(alfa != null ? alfa : 1.0d);
				RGB rgb = color.getRgb();
				return "RGBA (" + rgb.red + "," + rgb.green + "," + rgb.blue + "," + color.getAlfa()+")";
			case 2:
				if (dataRange != null) {
					JRExpression lowe = dataRange.getLowExpression();
					return lowe != null ? lowe.getText() : "";
				}
				break;
			case 3:
				if (dataRange != null) {
					JRExpression highe = dataRange.getHighExpression();
					return highe != null ? highe.getText() : "";
				}
				break;
			}
			return "";
		}
	}

	private Table table;
	private TableViewer tableViewer;
	private Composite sectioncmp;
	private Composite composite;
	private JRExpressionCellEditor lowExp;
	private JRExpressionCellEditor highExp;

	public SPMeterInterval(Composite parent, AbstractSection section, IPropertyDescriptor pDescriptor) {
		super(parent, section, pDescriptor);
	}

	@Override
	public Control getControl() {
		return sectioncmp;
	}

	public void createComponent(Composite parent) {
		composite = section.getWidgetFactory().createSection(parent, "Meter Intervals", true, 2);
		sectioncmp = composite.getParent();

		Composite bGroup = new Composite(composite, SWT.NONE);
		bGroup.setLayout(new GridLayout(1, false));
		bGroup.setLayoutData(new GridData(GridData.FILL_VERTICAL));
		
		buildTable(composite);
	
		new NewButton().createNewButtons(bGroup, tableViewer, new INewElement() {

			public Object newElement(List<?> input, int pos) {
				NewMeterIntervalWizard wizard = new NewMeterIntervalWizard();
				WizardDialog dialog = new WizardDialog(UIUtils.getShell(), wizard);
				if (dialog.open() == WizardDialog.OK){
					return wizard.getMeterInterval();
				} else return null;
			}
		});

		new DeleteButton().createDeleteButton(bGroup, tableViewer);

		new ListOrderButtons().createOrderButtons(bGroup, tableViewer);

		table.setToolTipText(pDescriptor.getDescription());
	}

	private void buildTable(Composite composite) {
		table = new Table(composite, SWT.BORDER | SWT.SINGLE | SWT.FULL_SELECTION);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.heightHint = 200;
		gd.widthHint = 580;
		table.setLayoutData(gd);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		tableViewer = new TableViewer(table) {
			@Override
			protected void inputChanged(Object input, Object oldInput) {
				if (!(input != null && oldInput != null && input.equals(oldInput)))
					propertyChange(section, pDescriptor.getId());
				super.inputChanged(input, oldInput);
			}
		};
		attachContentProvider(tableViewer);
		attachLabelProvider(tableViewer);
		attachCellEditors(tableViewer, table);

		TableLayout tlayout = new TableLayout();
		tlayout.addColumnData(new ColumnWeightData(25));
		tlayout.addColumnData(new ColumnWeightData(25));
		tlayout.addColumnData(new ColumnWeightData(25));
		tlayout.addColumnData(new ColumnWeightData(25));
		table.setLayout(tlayout);

		TableColumn[] column = new TableColumn[4];
		column[0] = new TableColumn(table, SWT.NONE);
		column[0].setText("Label");

		column[1] = new TableColumn(table, SWT.NONE);
		column[1].setText("Background");

		column[2] = new TableColumn(table, SWT.NONE);
		column[2].setText("Low Expression");

		column[3] = new TableColumn(table, SWT.NONE);
		column[3].setText("High Expression");

		for (int i = 0, n = column.length; i < n; i++)
			column[i].pack();
	}

	private void attachLabelProvider(TableViewer viewer) {
		viewer.setLabelProvider(new TLabelProvider());
	}

	private void attachContentProvider(TableViewer viewer) {
		viewer.setContentProvider(new ListContentProvider());
	}

	private void attachCellEditors(final TableViewer viewer, Composite parent) {
		viewer.setCellModifier(new ICellModifier() {
			public boolean canModify(Object element, String property) {
				if (property.equals("LABEL")) //$NON-NLS-1$
					return true;
				if (property.equals("COLOR")) //$NON-NLS-1$
					return true;
				if (property.equals("HIGH")) //$NON-NLS-1$
					return true;
				if (property.equals("LOW")) //$NON-NLS-1$
					return true;
				return false;
			}

			public Object getValue(Object element, String property) {
				JRMeterInterval mi = (JRMeterInterval) element;
				if (property.equals("LABEL"))//$NON-NLS-1$
					return mi.getLabel();
				if (property.equals("COLOR")){//$NON-NLS-1$
					AlfaRGB color = Colors.getSWTRGB4AWTGBColor(mi.getBackgroundColor());
					Double alfa = mi.getAlphaDouble();
					color.setAlfa(alfa != null ? alfa : 1.0d);
					return color;
				}
				if (property.equals("HIGH"))//$NON-NLS-1$
					return mi.getDataRange().getHighExpression();
				if (property.equals("LOW"))//$NON-NLS-1$
					return mi.getDataRange().getLowExpression();
				return null;
			}

			public void modify(Object element, String property, Object value) {
				TableItem ti = (TableItem) element;
				JRMeterInterval mi = (JRMeterInterval) ti.getData();
				if (property.equals("LABEL")) {//$NON-NLS-1$
					mi.setLabel((String) value);
				}
				if (property.equals("COLOR")) {//$NON-NLS-1$
					AlfaRGB argb = (AlfaRGB) value;
					mi.setBackgroundColor(Colors.getAWT4SWTRGBColor(argb));
					mi.setAlpha(argb.getAlfa() / 255.0d);
				}
				if (property.equals("HIGH")) {//$NON-NLS-1$
					((JRDesignDataRange) mi.getDataRange()).setHighExpression((JRExpression) value);
				}
				if (property.equals("LOW")) {//$NON-NLS-1$
					((JRDesignDataRange) mi.getDataRange()).setLowExpression((JRExpression) value);
				}
				tableViewer.update(element, new String[] { property });
				tableViewer.refresh();
				propertyChange(section, pDescriptor.getId());
			}
		});

		lowExp = new JRExpressionCellEditor(parent, null);
		highExp = new JRExpressionCellEditor(parent, null);
		ColorCellEditor argbColor = new ColorCellEditor(parent){
			@Override
			protected void updateContents(Object value) {
				AlfaRGB argb = (AlfaRGB) value;
				// XXX: We don't have a value the first time this method is called".
				if (argb == null) {
					rgbLabel.setText(""); //$NON-NLS-1$
					// rgb = new RGB(0, 0, 0);
				} else {
					RGB rgb = argb.getRgb();
					rgbLabel.setText("RGBA (" + rgb.red + "," + rgb.green + "," + rgb.blue + "," + argb.getAlfa()+")");//$NON-NLS-4$//$NON-NLS-3$//$NON-NLS-2$//$NON-NLS-1$
				}
			}
		};
		viewer.setCellEditors(new CellEditor[] { new TextCellEditor(parent), argbColor, lowExp, highExp });
		viewer.setColumnProperties(new String[] { "LABEL", "COLOR",  "LOW", "HIGH" }); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$

	}

	private void propertyChange(AbstractSection section, Object property) {
		section.changeProperty(property, tableViewer.getInput());
	}

	public void setData(APropertyNode pnode, Object value) {
		if (pnode != null) {
			ExpressionContext expContext = new ExpressionContext(pnode.getJasperConfiguration());
			lowExp.setExpressionContext(expContext);
			highExp.setExpressionContext(expContext);
		}
		List<?> ilist = (List<?>) value;
		if (ilist == null) {
			ilist = new ArrayList<JRMeterInterval>();
		} else {
			List<JRMeterInterval> tmp = new ArrayList<JRMeterInterval>(ilist.size());
			for (Object mi : ilist) {
				if (mi instanceof JRMeterInterval) {
					JRMeterInterval interval = (JRMeterInterval) mi;
					tmp.add((JRMeterInterval) interval.clone());
				}
			}
			ilist = tmp;
		}

		tableViewer.setInput(ilist);
	}
}
