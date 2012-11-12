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

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.jaspersoft.studio.editor.preview.view.APreview;
import com.jaspersoft.studio.editor.preview.view.IPreferencePage;
import com.jaspersoft.studio.preferences.PreferenceInitializer;
import com.jaspersoft.studio.preferences.util.JRContextPrefStore;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class VExporter extends APreview {

	private Composite composite;
	private ScrolledComposite scompo;

	public VExporter(Composite parent, JasperReportsConfiguration jContext) {
		super(parent, jContext);
		pfstore = new JRContextPrefStore(jContext);
		PreferenceInitializer.initDefaultProperties(pfstore);
	}

	@Override
	protected Control createControl(Composite parent) {
		scompo = new ScrolledComposite(parent, SWT.V_SCROLL | SWT.H_SCROLL);
		scompo.setExpandHorizontal(true);
		scompo.setExpandVertical(true);
		scompo.setAlwaysShowScrollBars(false);
		scompo.setMinSize(100, 100);

		composite = new Composite(scompo, SWT.BORDER);
		composite.setBackgroundMode(SWT.INHERIT_FORCE);
		composite.setBackground(parent.getBackground());
		GridLayout layout = new GridLayout();
		layout.marginBottom = 20;
		composite.setLayout(layout);
		scompo.setContent(composite);

		return scompo;
	}

	private PreferencePage page;
	private IPreferenceStore pfstore;

	public void setPreferencesPage(APreview preview) {
		if (preview instanceof VSimpleErrorPreview)
			return;
		if (page != null) {
			page.dispose();
			page.getControl().dispose();
			page = null;
		}
		if (preview != null && preview instanceof IPreferencePage) {
			page = ((IPreferencePage) preview).getPreferencePage();
			if (page != null) {
				page.setPreferenceStore(pfstore);
				page.createControl(composite);
				Control pageControl = page.getControl();
				pageControl.setLayoutData(new GridData(GridData.FILL_BOTH));

			}
		}
		composite.layout();
		scompo.update();
		scompo.layout();
	}
}
