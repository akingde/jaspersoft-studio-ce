package com.jaspersoft.studio.components.chart.property.section.plot;

import net.sf.jasperreports.charts.design.JRDesignPie3DPlot;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.Section;

import com.jaspersoft.studio.components.chart.messages.Messages;
import com.jaspersoft.studio.components.chart.model.MChartItemLabel;
import com.jaspersoft.studio.components.chart.model.plot.MChartPlot;
import com.jaspersoft.studio.components.chart.property.widget.Btn3Boolean;
import com.jaspersoft.studio.components.chart.property.widget.BtnChartItemLabel;
import com.jaspersoft.studio.components.chart.property.widget.BtnNumber;
import com.jaspersoft.studio.components.chart.property.widget.BtnText;
import com.jaspersoft.studio.property.section.AbstractSection;

public class Pie3dPlot extends APlot {

	public Pie3dPlot(Composite parent, AbstractSection section) {
		super(parent, section);
	}

	private Btn3Boolean showLabels;
	private Btn3Boolean circular;
	private BtnText labelFormat;
	private BtnText labelLegendFormat;
	private BtnNumber depthFactor;

	@Override
	protected void createComponent(Composite parent, AbstractSection section) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setBackground(parent.getBackground());
		composite.setLayout(new RowLayout());

		CLabel lbl = section.getWidgetFactory().createCLabel(composite,
				Messages.common_show_labels, SWT.RIGHT);
		RowData rd = new RowData();
		rd.width = 101;
		lbl.setLayoutData(rd);

		showLabels = new Btn3Boolean(composite, section,
				JRDesignPie3DPlot.PROPERTY_SHOW_LABELS,
				Messages.common_show_labels);

		section.getWidgetFactory()
				.createCLabel(
						composite,
						com.jaspersoft.studio.components.chart.messages.Messages.MPie3DPlot_circular_description,
						SWT.RIGHT);

		circular = new Btn3Boolean(
				composite,
				section,
				JRDesignPie3DPlot.PROPERTY_CIRCULAR,
				com.jaspersoft.studio.components.chart.messages.Messages.MPie3DPlot_circular_description);

		composite = new Composite(parent, SWT.NONE);
		composite.setBackground(parent.getBackground());
		composite.setLayout(new RowLayout());

		lbl = section.getWidgetFactory().createCLabel(composite,
				Messages.common_label_format, SWT.RIGHT);
		rd = new RowData();
		rd.width = 101;
		lbl.setLayoutData(rd);

		labelFormat = new BtnText(
				composite,
				section,
				JRDesignPie3DPlot.PROPERTY_LABEL_FORMAT,
				com.jaspersoft.studio.components.chart.messages.Messages.MPie3DPlot_label_format_description);

		section.getWidgetFactory().createCLabel(composite,
				Messages.common_legend_label_format, SWT.RIGHT);

		labelLegendFormat = new BtnText(
				composite,
				section,
				JRDesignPie3DPlot.PROPERTY_LEGEND_LABEL_FORMAT,
				com.jaspersoft.studio.components.chart.messages.Messages.MPie3DPlot_legend_label_format_description);

		composite = new Composite(parent, SWT.NONE);
		composite.setBackground(parent.getBackground());
		composite.setLayout(new RowLayout());

		lbl = section.getWidgetFactory().createCLabel(composite,
				Messages.MPie3DPlot_depth_factor, SWT.RIGHT);
		rd = new RowData();
		rd.width = 101;
		lbl.setLayoutData(rd);

		depthFactor = new BtnNumber(
				composite,
				section,
				JRDesignPie3DPlot.PROPERTY_DEPTH_FACTOR,
				com.jaspersoft.studio.components.chart.messages.Messages.MPie3DPlot_depth_factor_description);

		createItemLabel(parent, section);
	}

	private void createItemLabel(Composite parent, AbstractSection section) {
		Section sectioncmp = section.getWidgetFactory().createSection(
				parent,
				ExpandableComposite.TITLE_BAR | ExpandableComposite.TWISTIE
						| ExpandableComposite.EXPANDED);
		sectioncmp.setText("Item Label");

		parent = new Composite(sectioncmp, SWT.NONE);
		parent.setBackground(sectioncmp.getBackground());
		parent.setLayout(new RowLayout(SWT.VERTICAL));

		sectioncmp.setClient(parent);

		chItemLabel = new BtnChartItemLabel(parent, section,
				JRDesignPie3DPlot.PROPERTY_ITEM_LABEL);

	}

	private BtnChartItemLabel chItemLabel;

	@Override
	public void setData(MChartPlot mplot) {
		MChartItemLabel cil = (MChartItemLabel) mplot
				.getPropertyValue(JRDesignPie3DPlot.PROPERTY_ITEM_LABEL);
		chItemLabel.setData(cil);
		showLabels.setData((Boolean) mplot
				.getPropertyValue(JRDesignPie3DPlot.PROPERTY_SHOW_LABELS));
		circular.setData((Boolean) mplot
				.getPropertyValue(JRDesignPie3DPlot.PROPERTY_CIRCULAR));
		labelFormat.setData((String) mplot
				.getPropertyValue(JRDesignPie3DPlot.PROPERTY_LABEL_FORMAT));
		labelLegendFormat
				.setData((String) mplot
						.getPropertyValue(JRDesignPie3DPlot.PROPERTY_LEGEND_LABEL_FORMAT));
		depthFactor.setData((Double) mplot
				.getPropertyValue(JRDesignPie3DPlot.PROPERTY_DEPTH_FACTOR));
	}

}
