package com.jaspersoft.studio.components.chart.property.section.theme;

import net.sf.jasperreports.chartthemes.simple.TitleSettings;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.components.chart.model.theme.util.PadUtil;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.section.AbstractSection;

public class TitleSettingsSection extends AbstractSection {
	@Override
	public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
		super.createControls(parent, aTabbedPropertySheetPage);

		Composite group = getWidgetFactory().createComposite(parent);
		group.setLayout(new GridLayout(2, false));

		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		createWidget4Property(group, TitleSettings.PROPERTY_showTitle, false).getControl().setLayoutData(gd);
		createWidget4Property(group, TitleSettings.PROPERTY_position);
		createWidget4Property(group, TitleSettings.PROPERTY_horizontalAlignment);
		createWidget4Property(group, TitleSettings.PROPERTY_verticalAlignment);
		createWidget4Property(group, TitleSettings.PROPERTY_foregroundPaint);
		createWidget4Property(group, TitleSettings.PROPERTY_backgroundPaint);

		PadUtil.createWidgets4Property(parent, "", Messages.common_padding, this);
	}
}
