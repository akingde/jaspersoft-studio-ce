/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of Jaspersoft Open Studio.
 * 
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * Jaspersoft Open Studio is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with Jaspersoft Open Studio. If not,
 * see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.editor.preview.view.control;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.jaspersoft.studio.editor.preview.inputs.dialog.SortFieldSection;
import com.jaspersoft.studio.editor.preview.view.APreview;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class VSorting extends APreview {

	private SortFieldSection sortField;
	private Composite composite;
	private ScrolledComposite scompo;

	public VSorting(Composite parent, JasperReportsConfiguration jContext) {
		super(parent, jContext);
	}

	@Override
	protected Control createControl(Composite parent) {
		scompo = new ScrolledComposite(parent, SWT.V_SCROLL | SWT.H_SCROLL);
		scompo.setExpandHorizontal(true);
		scompo.setExpandVertical(true);
		scompo.setAlwaysShowScrollBars(false);
		scompo.setMinSize(100, 100);

		composite = new Composite(scompo, SWT.NONE);
		composite.setBackground(parent.getBackground());
		composite.setLayout(new GridLayout());
		scompo.setContent(composite);

		composite.addControlListener(new ControlListener() {

			@Override
			public void controlResized(ControlEvent e) {
				scompo.setMinSize(composite.getSize());
			}

			@Override
			public void controlMoved(ControlEvent e) {

			}
		});
		return scompo;
	}

	public SortFieldSection getSortField() {
		if (sortField == null)
			sortField = new SortFieldSection();
		return sortField;
	}

	public void setJasperReports(JasperDesign jDesign, List<JRParameter> prompts, Map<String, Object> params) {
		for (Control c : composite.getChildren())
			c.dispose();

		sortField = getSortField();
		sortField.fillTable(composite, jDesign, prompts, params);
		composite.pack();
		scompo.setMinSize(composite.getSize());
	}

}
