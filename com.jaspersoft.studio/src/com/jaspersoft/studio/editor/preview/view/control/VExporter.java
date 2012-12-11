/*******************************************************************************
 * Copyright (C) 2010 - 2012 Jaspersoft Corporation. All rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.editor.preview.view.control;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.preview.view.APreview;
import com.jaspersoft.studio.editor.preview.view.IPreferencePage;
import com.jaspersoft.studio.plugin.IEditorContributor;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class VExporter extends APreview {

	private Composite composite;
	private ScrolledComposite scompo;

	public VExporter(Composite parent, JasperReportsConfiguration jContext) {
		super(parent, jContext);
		pfstore = JaspersoftStudioPlugin.getInstance().getPreferenceStore(
				(IFile) jContext.get(IEditorContributor.KEY_FILE), JaspersoftStudioPlugin.getUniqueIdentifier());
		// pfstore = new JRContextPrefStore(jContext);
		// PreferenceInitializer.initDefaultProperties(pfstore);
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
