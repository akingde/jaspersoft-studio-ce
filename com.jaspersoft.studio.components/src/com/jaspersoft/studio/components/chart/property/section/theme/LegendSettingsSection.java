package com.jaspersoft.studio.components.chart.property.section.theme;

import net.sf.jasperreports.chartthemes.simple.LegendSettings;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.components.chart.model.theme.util.PadUtil;
import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.section.AbstractSection;

public class LegendSettingsSection extends AbstractSection {
	@Override
	public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
		super.createControls(parent, aTabbedPropertySheetPage);

		Composite group = getWidgetFactory().createComposite(parent);
		group.setLayout(new GridLayout(2, false));

		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		createWidget4Property(group, LegendSettings.PROPERTY_showLegend, false).getControl().setLayoutData(gd);
		createWidget4Property(group, LegendSettings.PROPERTY_position);
		createWidget4Property(group, LegendSettings.PROPERTY_horizontalAlignment);
		createWidget4Property(group, LegendSettings.PROPERTY_verticalAlignment);
		createWidget4Property(group, LegendSettings.PROPERTY_foregroundPaint);
		createWidget4Property(group, LegendSettings.PROPERTY_backgroundPaint);

		PadUtil.createWidgets4Property(parent, LegendSettings.PROPERTY_blockFrame, "Block Frame", this);
	}
}
