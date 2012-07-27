package com.jaspersoft.studio.components.crosstab.property;

import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabBucket;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabGroup;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.components.crosstab.model.MBucket;
import com.jaspersoft.studio.components.crosstab.model.MCrosstabGroup;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.section.AbstractSection;

public class CrosstabBucketSection extends AbstractSection {

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ITabbedPropertySection#createControls(org.eclipse.swt.widgets.Composite,
	 *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	public void createControls(Composite parent,
			TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);

		parent = getWidgetFactory().createSection(parent, "Bucket", false, 3);

		GridData gd = new GridData();
		gd.horizontalSpan = 2;
		createWidget4Property(parent, JRDesignCrosstabBucket.PROPERTY_ORDER)
				.getControl().setLayoutData(gd);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		createWidget4Property(parent,
				JRDesignCrosstabBucket.PROPERTY_ORDER_BY_EXPRESSION)
				.getControl().setLayoutData(gd);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		createWidget4Property(parent,
				JRDesignCrosstabBucket.PROPERTY_COMPARATOR_EXPRESSION)
				.getControl().setLayoutData(gd);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		createWidget4Property(parent,
				JRDesignCrosstabBucket.PROPERTY_EXPRESSION).getControl()
				.setLayoutData(gd);
		createWidget4Property(parent,
				JRDesignCrosstabBucket.PROPERTY_VALUE_CLASS);
	}

	@Override
	protected APropertyNode getModelFromEditPart(Object item) {
		APropertyNode md = super.getModelFromEditPart(item);
		if (md instanceof MCrosstabGroup) {
			MBucket dataset = (MBucket) md
					.getPropertyValue(JRDesignCrosstabGroup.PROPERTY_BUCKET);
			return dataset;
		}
		return md;
	}
}
