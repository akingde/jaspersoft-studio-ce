/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.ic;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.jasperserver.dto.resources.ResourceMediaType;
import com.jaspersoft.studio.property.dataset.fields.table.widget.AWidget;
import com.jaspersoft.studio.property.dataset.fields.table.widget.WText;
import com.jaspersoft.studio.server.ServerManager;
import com.jaspersoft.studio.server.export.AExporter;
import com.jaspersoft.studio.server.model.AMResource;
import com.jaspersoft.studio.server.model.server.MServerProfile;
import com.jaspersoft.studio.server.properties.dialog.RepositoryDialog;
import com.jaspersoft.studio.server.protocol.Feature;
import com.jaspersoft.studio.server.selector.SelectServerWizard;
import com.jaspersoft.studio.server.wizard.find.FindResourceJob;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.engine.design.JRDesignParameter;
import net.sf.jasperreports.engine.design.JasperDesign;

public class WInputControlPathSelector extends WText {

	public WInputControlPathSelector(AWidget aw) {
		super(aw);
	}

	@Override
	protected void createControl(Composite parent) {
		Composite cmp = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout(2, false);
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		cmp.setLayout(layout);
		cmp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		super.createControl(cmp);

		Button b = new Button(cmp, SWT.PUSH);
		b.setText("...");
		b.addSelectionListener(new SelectionAdapter() {

			private MServerProfile msp;

			@Override
			public void widgetSelected(SelectionEvent e) {
				JasperDesign jDesign = ((JasperReportsConfiguration) aw.getTColumn().getValue()).getJasperDesign();
				String servURL = jDesign.getProperty(AExporter.PROP_SERVERURL);
				String servUser = jDesign.getProperty(AExporter.PROP_USER);

				msp = ServerManager.getServerByUrl(servURL, servUser);
				if (msp == null) {
					SelectServerWizard wizard = new SelectServerWizard();
					WizardDialog w = new WizardDialog(UIUtils.getShell(), wizard);
					if (w.open() == Dialog.OK) {
						msp = wizard.getValue();
						try {
							jDesign.setProperty(AExporter.PROP_SERVERURL, msp.getValue().getUrl());
							jDesign.setProperty(AExporter.PROP_USER,
									msp.getValue().getUser() + (msp.getValue().getOrganisation() != null
											? "|" + msp.getValue().getOrganisation() : "")); //$NON-NLS-1$ //$NON-NLS-2$
						} catch (MalformedURLException e1) {
							e1.printStackTrace();
						} catch (URISyntaxException e1) {
							e1.printStackTrace();
						}
					}
				}
				if (msp != null)
					showFindDialog(msp);
			}

			protected void showFindDialog(MServerProfile msp) {
				JRDesignParameter p = null;
				if (aw.getElement() instanceof JRDesignParameter)
					p = (JRDesignParameter) aw.getElement();
				else if (aw.getTColumn().getValue1() instanceof JRDesignParameter)
					p = (JRDesignParameter) aw.getTColumn().getValue1();
				if (p != null) {
					final String name = p.getName();
					if (msp.isSupported(Feature.SEARCHREPOSITORY)) {
						String[] incl = new String[] { ResourceMediaType.INPUT_CONTROL_CLIENT_TYPE };
						ResourceDescriptor rd = FindResourceJob.doFindResource(msp, incl, null, true, name);
						if (rd != null) {
							if (rd.getName().equals(name))
								aw.setValue(rd.getUriString());
							else
								UIUtils.showWarning(
										"Input Control name must be the same as parameter name to work on Jaspersoft Server.");
						}
						fillValue();
					} else {
						RepositoryDialog rd = new RepositoryDialog(UIUtils.getShell(), msp) {

							@Override
							public boolean isResourceCompatible(AMResource r) {
								return r.getValue().getWsType().equals(ResourceDescriptor.TYPE_INPUT_CONTROL)
										&& r.getValue().getName().equals(name);
							}
						};
						if (rd.open() == Dialog.OK) {
							AMResource rs = rd.getResource();
							if (rs != null)
								aw.setValue(rs.getValue().getUriString());
							else
								aw.setValue(null);
						}
						fillValue();
					}
				}
			}
		});
	}
}
