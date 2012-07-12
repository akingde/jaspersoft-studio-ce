package com.jaspersoft.studio.property.section.graphic;

import net.sf.jasperreports.engine.JRPropertiesHolder;
import net.sf.jasperreports.engine.JRPropertiesMap;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.editor.layout.ILayout;
import com.jaspersoft.studio.editor.layout.LayoutManager;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.descriptors.JSSComboPropertyDescriptor;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.SPReadCombo;

public class LayoutSection extends AbstractSection {
	private JSSComboPropertyDescriptor pd;
	private ILayout[] layouts;

	public LayoutSection() {
		super();
		layouts = LayoutManager.getAllLayouts();
		String[] labels = new String[layouts.length];
		for (int i = 0; i < layouts.length; i++)
			labels[i] = layouts[i].getName();
		pd = new JSSComboPropertyDescriptor(MGraphicElement.PROPERTY_MAP, "Layout", labels);
		pd.setDescription("Set Layout Manager for container");
	}

	@Override
	public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
		super.createControls(parent, aTabbedPropertySheetPage);

		parent = getWidgetFactory().createSection(parent, "Layout", false, 2);

		getWidgetFactory().createCLabel(parent, "Layouts", SWT.RIGHT);

		widgets.put(pd.getId(), new SPReadCombo(parent, this, pd) {
			protected void handlePropertyChange() {
				int index = combo.getSelectionIndex();
				JRPropertiesMap pmap = (JRPropertiesMap) pnode.getPropertyValue(MGraphicElement.PROPERTY_MAP);
				pmap.setProperty(ILayout.KEY, layouts[index].getClass().getName());
				section.changeProperty(MGraphicElement.PROPERTY_MAP, pmap);
			}

			private APropertyNode pnode;

			@Override
			public void setData(APropertyNode pnode, Object b) {
				this.pnode = pnode;
				int index = 0;
				Object obj = pnode.getValue();
				if (obj != null && obj instanceof JRPropertiesHolder) {
					JRPropertiesMap pmap = (JRPropertiesMap) pnode.getPropertyValue(MGraphicElement.PROPERTY_MAP);
					String str = pmap.getProperty(ILayout.KEY);
					if (str != null) {
						for (int i = 0; i < layouts.length; i++) {
							if (layouts[i].getName().equals(str)) {
								index = i;
								break;
							}
						}
					}
				}
				combo.select(index);
			}
		});
	}
}
