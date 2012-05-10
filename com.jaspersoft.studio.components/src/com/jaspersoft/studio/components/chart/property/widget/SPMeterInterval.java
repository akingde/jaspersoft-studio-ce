/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2011 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of JasperReports.
 * 
 * JasperReports is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General
 * Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * JasperReports is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along with JasperReports. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.components.chart.property.widget;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.charts.JRDataRange;
import net.sf.jasperreports.charts.design.JRDesignDataRange;
import net.sf.jasperreports.charts.util.JRMeterInterval;
import net.sf.jasperreports.engine.JRExpression;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.Section;

import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.editor.expression.IExpressionContextSetter;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.color.ColorCellEditor;
import com.jaspersoft.studio.property.descriptor.color.ColorLabelProvider;
import com.jaspersoft.studio.property.descriptor.expression.JRExpressionCellEditor;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.swt.widgets.table.DeleteButton;
import com.jaspersoft.studio.swt.widgets.table.INewElement;
import com.jaspersoft.studio.swt.widgets.table.ListContentProvider;
import com.jaspersoft.studio.swt.widgets.table.ListOrderButtons;
import com.jaspersoft.studio.swt.widgets.table.NewButton;
import com.jaspersoft.studio.utils.Colors;
import com.jaspersoft.studio.utils.Misc;

public class SPMeterInterval implements IExpressionContextSetter{
	private final class TLabelProvider extends LabelProvider implements
			ITableLabelProvider {
		private ColorLabelProvider colorLabel = new ColorLabelProvider(
				NullEnum.NULL);

		public Image getColumnImage(Object element, int columnIndex) {
			JRMeterInterval mi = (JRMeterInterval) element;
			switch (columnIndex) {
			case 1:
				return colorLabel.getImage(Colors.getSWTRGB4AWTGBColor(mi
						.getBackgroundColor()));
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
				return colorLabel.getText(Colors.getSWTRGB4AWTGBColor(mi
						.getBackgroundColor()));
			case 2:
				if (mi.getAlphaDouble() != null)
					return mi.getAlphaDouble().toString();
				break;
			case 3:
				if (dataRange != null) {
					JRExpression lowe = dataRange.getLowExpression();
					return lowe != null ? lowe.getText() : "";
				}
				break;
			case 4:
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
	private AbstractSection section;
	private String property;
	private ExpressionContext expContext;

	public SPMeterInterval(Composite parent, AbstractSection section,
			String property, String tooltip) {
		this.section = section;
		this.property = property;
		createComponent(parent, section, property, tooltip);
	}

	public void createComponent(Composite parent,
			final AbstractSection section, final String property, String tooltip) {
		Section sectioncmp = section.getWidgetFactory().createSection(parent,
				ExpandableComposite.TITLE_BAR | ExpandableComposite.TWISTIE);
		sectioncmp.setText("Meter Intervals");

		parent = new Composite(sectioncmp, SWT.NONE);
		parent.setBackground(sectioncmp.getBackground());
		parent.setLayout(new RowLayout(SWT.VERTICAL));

		sectioncmp.setClient(parent);

		Composite composite = new Composite(parent, SWT.NONE);
		composite.setBackground(parent.getBackground());
		composite.setLayout(new GridLayout(2, false));

		buildTable(composite);

		Composite bGroup = new Composite(composite, SWT.NONE);
		bGroup.setLayout(new GridLayout(1, false));
		bGroup.setLayoutData(new GridData(GridData.FILL_VERTICAL));
		bGroup.setBackground(composite.getBackground());

		new NewButton().createNewButtons(bGroup, tableViewer,
				new INewElement() {

					public Object newElement(List<?> input, int pos) {
						JRMeterInterval jrm = new JRMeterInterval();
						jrm.setDataRange(new JRDesignDataRange(null));
						jrm.setLabel("Interval");
						return jrm;
					}

				});

		new DeleteButton().createDeleteButton(bGroup, tableViewer);

		new ListOrderButtons().createOrderButtons(bGroup, tableViewer);

		table.setToolTipText(tooltip);
	}

	private void buildTable(Composite composite) {
		table = new Table(composite, SWT.BORDER | SWT.SINGLE
				| SWT.FULL_SELECTION);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.heightHint = 200;
		gd.widthHint = 580;
		table.setLayoutData(gd);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		tableViewer = new TableViewer(table) {
			@Override
			protected void inputChanged(Object input, Object oldInput) {
				if (!(input != null && oldInput != null && input
						.equals(oldInput)))
					propertyChange(section, property);
				super.inputChanged(input, oldInput);
			}
		};
		attachContentProvider(tableViewer);
		attachLabelProvider(tableViewer);
		attachCellEditors(tableViewer, table);

		TableLayout tlayout = new TableLayout();
		tlayout.addColumnData(new ColumnWeightData(25));
		tlayout.addColumnData(new ColumnWeightData(15));
		tlayout.addColumnData(new ColumnWeightData(10));
		tlayout.addColumnData(new ColumnWeightData(25));
		tlayout.addColumnData(new ColumnWeightData(25));
		table.setLayout(tlayout);

		TableColumn[] column = new TableColumn[5];
		column[0] = new TableColumn(table, SWT.NONE);
		column[0].setText("Label");

		column[1] = new TableColumn(table, SWT.NONE);
		column[1].setText("Background");

		column[2] = new TableColumn(table, SWT.NONE);
		column[2].setText("Alpha");

		column[3] = new TableColumn(table, SWT.NONE);
		column[3].setText("Low Expression");

		column[4] = new TableColumn(table, SWT.NONE);
		column[4].setText("High Expression");

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
				if (property.equals("ALPHA")) //$NON-NLS-1$
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
				if (property.equals("COLOR"))//$NON-NLS-1$
					return Colors.getSWTRGB4AWTGBColor(mi.getBackgroundColor());
				if (property.equals("ALPHA"))//$NON-NLS-1$
					return Misc.nvl(mi.getAlphaDouble(), "");
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
					mi.setBackgroundColor(Colors
							.getAWT4SWTRGBColor((RGB) value));
				}
				if (property.equals("ALPHA")) {//$NON-NLS-1$
					try {
						mi.setAlpha(new Double((String) value));
					} catch (NumberFormatException nfe) {
						mi.setAlpha(null);
					}
				}
				if (property.equals("HIGH")) {//$NON-NLS-1$
					((JRDesignDataRange) mi.getDataRange())
							.setHighExpression((JRExpression) value);
				}
				if (property.equals("LOW")) {//$NON-NLS-1$
					((JRDesignDataRange) mi.getDataRange())
							.setLowExpression((JRExpression) value);
				}
				tableViewer.update(element, new String[] { property });
				tableViewer.refresh();
				propertyChange(section, SPMeterInterval.this.property);
			}
		});

		JRExpressionCellEditor exprCellEditor = new JRExpressionCellEditor(parent);
		exprCellEditor.setExpressionContext(expContext);
		viewer.setCellEditors(new CellEditor[] { new TextCellEditor(parent),
				new ColorCellEditor(parent), new TextCellEditor(parent),
				exprCellEditor,
				exprCellEditor });
		viewer.setColumnProperties(new String[] {
				"LABEL", "COLOR", "ALPHA", "LOW", "HIGH" }); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$

	}

	private void propertyChange(AbstractSection section, String property) {
		section.changeProperty(property, tableViewer.getInput());
	}

	public void setData(List<JRMeterInterval> ilist) {
		if (ilist == null) {
			ilist = new ArrayList<JRMeterInterval>();
		} else {
			List<JRMeterInterval> tmp = new ArrayList<JRMeterInterval>(
					ilist.size());
			for (JRMeterInterval mi : ilist) {
				tmp.add((JRMeterInterval) mi.clone());
			}
			ilist = tmp;
		}

		tableViewer.setInput(ilist);
	}

	public void setExpressionContext(ExpressionContext expContext) {
		this.expContext=expContext;
	}
}
