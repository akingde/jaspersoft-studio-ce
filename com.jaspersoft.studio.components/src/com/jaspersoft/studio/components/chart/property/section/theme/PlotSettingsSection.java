package com.jaspersoft.studio.components.chart.property.section.theme;

import net.sf.jasperreports.chartthemes.simple.PlotSettings;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.components.chart.model.theme.util.PadUtil;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.section.AbstractSection;

public class PlotSettingsSection extends AbstractSection {
	@Override
	public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
		super.createControls(parent, aTabbedPropertySheetPage);

		Composite group = getWidgetFactory().createComposite(parent);
		group.setLayout(new GridLayout(2, false));
		createWidget4Property(group, PlotSettings.PROPERTY_labelRotation);
		createWidget4Property(group, PlotSettings.PROPERTY_foregroundAlpha);
		createWidget4Property(group, PlotSettings.PROPERTY_orientation);

		group = getWidgetFactory().createSection(parent, Messages.common_background, true, 3);
		GridData gd = new GridData();
		gd.horizontalSpan = 2;
		createWidget4Property(group, PlotSettings.PROPERTY_backgroundPaint).getControl().setLayoutData(gd);
		createWidget4Property(group, PlotSettings.PROPERTY_backgroundImage);
		gd = new GridData();
		gd.horizontalSpan = 2;
		createWidget4Property(group, PlotSettings.PROPERTY_backgroundImageAlignment).getControl().setLayoutData(gd);
		createWidget4Property(group, PlotSettings.PROPERTY_backgroundImageAlpha);

		PadUtil.createWidgets4Property(parent, "", Messages.common_padding, this);

		group = getWidgetFactory().createSection(parent, "Domain Grid Line", true, 2);
		gd = new GridData();
		gd.horizontalSpan = 2;
		createWidget4Property(group, PlotSettings.PROPERTY_domainGridlineVisible, false).getControl().setLayoutData(gd);
		createWidget4Property(group, PlotSettings.PROPERTY_domainGridlinePaint);
		createWidget4Property(group, PlotSettings.PROPERTY_domainGridlineStroke);

		group = getWidgetFactory().createSection(parent, "Range Grid Line", true, 2);
		gd = new GridData();
		gd.horizontalSpan = 2;
		createWidget4Property(group, PlotSettings.PROPERTY_rangeGridlineVisible, false).getControl().setLayoutData(gd);
		createWidget4Property(group, PlotSettings.PROPERTY_rangeGridlinePaint);
		createWidget4Property(group, PlotSettings.PROPERTY_rangeGridlineStroke);

		group = getWidgetFactory().createSection(parent, "Outline", true, 2);
		gd = new GridData();
		gd.horizontalSpan = 2;
		createWidget4Property(group, PlotSettings.PROPERTY_outlineVisible, false).getControl().setLayoutData(gd);
		createWidget4Property(group, PlotSettings.PROPERTY_outlinePaint);
		createWidget4Property(group, PlotSettings.PROPERTY_outlineStroke);

		group = getWidgetFactory().createSection(parent, "Series", true, 2);
		createWidget4Property(group, PlotSettings.PROPERTY_seriesStrokeSequence);
		createWidget4Property(group, PlotSettings.PROPERTY_seriesColorSequence);
		createWidget4Property(group, PlotSettings.PROPERTY_seriesGradientPaintSequence);
		createWidget4Property(group, PlotSettings.PROPERTY_seriesOutlinePaintSequence);
		createWidget4Property(group, PlotSettings.PROPERTY_seriesOutlineStrokeSequence);
	}
}
