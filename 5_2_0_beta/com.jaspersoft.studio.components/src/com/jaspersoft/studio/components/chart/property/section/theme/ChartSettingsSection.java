package com.jaspersoft.studio.components.chart.property.section.theme;

import net.sf.jasperreports.chartthemes.simple.ChartSettings;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.components.chart.model.theme.util.PadUtil;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.section.AbstractSection;

public class ChartSettingsSection extends AbstractSection {
	@Override
	public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
		super.createControls(parent, aTabbedPropertySheetPage);

		Composite group = getWidgetFactory().createComposite(parent);
		group.setLayout(new GridLayout());
		createWidget4Property(group, ChartSettings.PROPERTY_antiAlias, false);
		createWidget4Property(group, ChartSettings.PROPERTY_textAntiAlias, false);

		group = getWidgetFactory().createSection(parent, Messages.common_borders, true, 2);

		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 3;
		createWidget4Property(group, ChartSettings.PROPERTY_borderVisible, false).getControl().setLayoutData(gd);
		createWidget4Property(group, ChartSettings.PROPERTY_borderPaint);
		createWidget4Property(group, ChartSettings.PROPERTY_borderStroke);

		group = getWidgetFactory().createSection(parent, Messages.common_background, true, 3);
		gd = new GridData();
		gd.horizontalSpan = 2;
		createWidget4Property(group, ChartSettings.PROPERTY_backgroundPaint).getControl().setLayoutData(gd);
		createWidget4Property(group, ChartSettings.PROPERTY_backgroundImage);
		gd = new GridData();
		gd.horizontalSpan = 2;
		createWidget4Property(group, ChartSettings.PROPERTY_backgroundImageAlignment).getControl().setLayoutData(gd);
		createWidget4Property(group, ChartSettings.PROPERTY_backgroundImageAlpha);

		PadUtil.createWidgets4Property(parent, "", Messages.common_padding, this);
	}
}
