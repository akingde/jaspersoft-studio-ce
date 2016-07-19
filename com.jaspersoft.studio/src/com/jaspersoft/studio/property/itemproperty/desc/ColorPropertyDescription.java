package com.jaspersoft.studio.property.itemproperty.desc;

import net.sf.jasperreports.components.items.StandardItemProperty;
import net.sf.jasperreports.engine.design.JRDesignExpression;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.swt.events.ColorSelectedEvent;
import com.jaspersoft.studio.swt.events.ColorSelectionListener;
import com.jaspersoft.studio.swt.widgets.WColorPicker;
import com.jaspersoft.studio.utils.AlfaRGB;
import com.jaspersoft.studio.utils.Colors;
import com.jaspersoft.studio.utils.Misc;

public class ColorPropertyDescription<T> extends ItemPropertyDescription<T> {

	public ColorPropertyDescription(String name, String description, boolean mandatory) {
		super(name, description, mandatory);
	}

	public ColorPropertyDescription(String name, String description, boolean mandatory, T defaultValue) {
		super(name, description, mandatory, defaultValue);
	}

	public ColorPropertyDescription(String name, String label, String description, boolean mandatory, T defaultValue) {
		super(name, label, description, mandatory, defaultValue);
	}

	public ColorPropertyDescription(String name, String label, String description, boolean mandatory) {
		super(name, label, description, mandatory);
	}

	private boolean transparent = false;

	public void setTransparent(boolean transparent) {
		this.transparent = transparent;
	}

	public void handleEdit(Control txt, StandardItemProperty value) {
		super.handleEdit(txt, value);
		if (txt instanceof WColorPicker) {
			String tvalue = Colors.getHexEncodedRGBColor(((WColorPicker) txt).getSelectedColorAsRGB());
			if (transparent)
				tvalue = Colors.getRGBAEncodedRGBColor(((WColorPicker) txt).getSelectedColorAsRGB());
			if (tvalue != null && tvalue.isEmpty())
				tvalue = null;

			if (value.getValueExpression() != null)
				((JRDesignExpression) value.getValueExpression()).setText(tvalue);
			else
				value.setValue(tvalue);
		}
	}

	public Control createControl(final IWItemProperty wiProp, Composite parent) {
		Composite cmp = new Composite(parent, SWT.NONE);
		StackLayout layout = new StackLayout();
		cmp.setLayout(layout);
		cmp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		super.createControl(wiProp, cmp);

		final WColorPicker cp = new WColorPicker(new AlfaRGB(new RGB(0, 0, 0), 0), cmp);
		cp.setHaveTransparency(transparent);
		cp.addColorSelectionListener(new ColorSelectionListener() {

			@Override
			public void changed(ColorSelectedEvent e) {
				if (wiProp.isRefresh())
					return;
				StandardItemProperty v = wiProp.getValue();
				if (v == null)
					v = new StandardItemProperty(getName(), null, null);
				handleEdit(cp, v);
				wiProp.setValue(wiProp.getValue());
			}
		});
		for (Control c : cp.getChildren())
			setupContextMenu(c, wiProp);
		layout.topControl = cp;

		setupContextMenu(textExpression, wiProp);
		return cmp;
	}

	@Override
	public void setValue(Control c, IWItemProperty wip) {
		Composite cmp = (Composite) wip.getControl();
		StackLayout layout = (StackLayout) cmp.getLayout();
		if (wip.getValue() == null)
			wip.setValue(new StandardItemProperty(getName(), null, null));
		if (wip.getValue().getValueExpression() != null) {
			Text txt = (Text) cmp.getChildren()[0];
			super.setValue(txt, wip);
			layout.topControl = txt;
		} else {
			WColorPicker combo = (WColorPicker) cmp.getChildren()[1];
			layout.topControl = combo;
			String txt = wip.getLabelProvider().getText(wip.getValue());
			String v = wip.getValue().getValue();
			combo.setColor(new AlfaRGB(Colors.decodeHexStringAsSWTRGB(v), 0));

			String tooltip = "";
			if (!Misc.isNullOrEmpty(txt))
				tooltip += "\n\n" + txt;
			tooltip += "\n\n" + getToolTip();
			combo.setToolTipText(tooltip.trim());
		}
		cmp.layout();
	}
}
