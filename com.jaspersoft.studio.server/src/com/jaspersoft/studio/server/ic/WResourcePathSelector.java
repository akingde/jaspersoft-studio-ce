/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.ic;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.property.dataset.fields.table.widget.AWTextButton;
import com.jaspersoft.studio.property.dataset.fields.table.widget.AWidget;
import com.jaspersoft.studio.server.ServerManager;
import com.jaspersoft.studio.server.export.AExporter;
import com.jaspersoft.studio.server.model.AMResource;
import com.jaspersoft.studio.server.model.server.MServerProfile;
import com.jaspersoft.studio.server.properties.dialog.RepositoryDialog;
import com.jaspersoft.studio.server.protocol.Feature;
import com.jaspersoft.studio.server.selector.SelectServerWizard;
import com.jaspersoft.studio.server.wizard.find.FindResourceJob;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.engine.design.JasperDesign;

public class WResourcePathSelector extends AWTextButton {

	public WResourcePathSelector(AWidget aw) {
		super(aw);
	}

	@Override
	protected void createButton(Composite cmp) {
		super.createButton(cmp);
		btn.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				JasperDesign jDesign = aw.getjConfig().getJasperDesign();
				String servURL = jDesign.getProperty(AExporter.PROP_SERVERURL);
				String servUser = jDesign.getProperty(AExporter.PROP_USER);

				MServerProfile msp = ServerManager.getServerByUrl(servURL, servUser);
				if (msp == null) {
					SelectServerWizard wizard = new SelectServerWizard();
					if (new WizardDialog(btn.getShell(), wizard).open() == Dialog.OK) {
						msp = wizard.getValue();
						try {
							jDesign.setProperty(AExporter.PROP_SERVERURL, msp.getValue().getUrl());
							jDesign.setProperty(AExporter.PROP_USER, AExporter.encodeUsr(msp.getValue()));
						} catch (MalformedURLException | URISyntaxException e1) {
							UIUtils.showError(e1);
						}
					}
				}
				if (msp != null)
					showFindDialog(msp);
			}

		});
	}

	protected void showFindDialog(MServerProfile msp) {
		if (msp.isSupported(Feature.SEARCHREPOSITORY)) {
			ResourceDescriptor rd = FindResourceJob.doFindResource(msp, getCompatibleResources(), null, true,
					getName());
			if (rd == null)
				return;
			postSelection(rd);
		} else {
			RepositoryDialog rd = new RepositoryDialog(btn.getShell(), msp) {

				@Override
				public boolean isResourceCompatible(AMResource r) {
					return WResourcePathSelector.this.isResourceCompatible(r);
				}
			};
			if (rd.open() == Dialog.OK) {
				AMResource rs = rd.getResource();
				postSelection(rs.getValue());
			}
		}
		fillValue();
	}

	protected String getName() {
		return null;
	}

	protected void postSelection(ResourceDescriptor rd) {
		if (rd != null)
			aw.setValue(rd.getUriString());
		else
			aw.setValue(null);
	}

	protected String[] getCompatibleResources() {
		return new String[0];
	}

	protected boolean isResourceCompatible(AMResource r) {
		return true;
	}
}
