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
/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of iReport.
 * 
 * iReport is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public
 * License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * iReport is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with iReport. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.model.subreport.command.wizard;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignSubreport;
import net.sf.jasperreports.engine.design.JRDesignSubreportParameter;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.FilteredResourcesSelectionDialog;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.subreport.MSubreport;
import com.jaspersoft.studio.wizards.ReportNewWizard;

public class WizardNewSubreportPage extends WizardPage {
	private MSubreport subreport;
	private Text etxt;

	public MSubreport getSubreport() {
		return subreport;
	}

	public void setSubreport(MSubreport subreport) {
		this.subreport = subreport;
	}

	public WizardNewSubreportPage() {
		super("newsubreport"); //$NON-NLS-1$
		setTitle(Messages.common_subreport);
		setDescription(Messages.WizardNewSubreportPage_description);
		setImageDescriptor(MSubreport.getIconDescriptor().getIcon32());
	}

	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 3;
		composite.setLayout(layout);
		setControl(composite);

		Label lbl = new Label(composite, SWT.NONE);
		lbl.setText(Messages.WizardNewSubreportPage_subreport_path + ":"); //$NON-NLS-1$
		GridData gd = new GridData();
		gd.horizontalSpan = 3;
		lbl.setLayoutData(gd);

		etxt = new Text(composite, SWT.BORDER | SWT.MULTI | SWT.READ_ONLY);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		// gd.heightHint = 60;
		etxt.setLayoutData(gd);

		final Button fbut = new Button(composite, SWT.PUSH);
		fbut.setText(Messages.common_browse + "..."); //$NON-NLS-1$
		fbut.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING));
		fbut.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				FilteredResourcesSelectionDialog wizard = new FilteredResourcesSelectionDialog(Display.getCurrent()
						.getActiveShell(), false, ResourcesPlugin.getWorkspace().getRoot(), IResource.FILE);
				wizard.setInitialPattern("*.jrxml");//$NON-NLS-1$
				if (wizard.open() == Dialog.OK)
					setUpSubreport((IFile) wizard.getFirstResult());
			}

			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});
		final Button newrep = new Button(composite, SWT.PUSH);
		newrep.setText(Messages.common_new);
		newrep.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING));
		newrep.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				ReportNewWizard wizard = new ReportNewWizard();
				wizard.init(workbench, selection);
				WizardDialog dialog = new WizardDialog(Display.getCurrent().getActiveShell(), wizard);
				dialog.create();
				if (dialog.open() == Dialog.OK)
					setUpSubreport(wizard.getReportFile());
			}

			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		PlatformUI.getWorkbench().getHelpSystem().setHelp(getControl(), "Jaspersoft.wizard");//$NON-NLS-1$
	}

	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.selection = selection;
		this.workbench = workbench;
	}

	private IWorkbench workbench;
	private IStructuredSelection selection;

	private void setUpSubreport(IFile file) {
		JRDesignExpression jre = new JRDesignExpression();
		jre.setText("\"" + file.getProjectRelativePath().toPortableString() + "\"");//$NON-NLS-1$ //$NON-NLS-2$
		subreport.setPropertyValue(JRDesignSubreport.PROPERTY_EXPRESSION, jre);
		JRDesignSubreport s = (JRDesignSubreport) subreport.getValue();
		try {
			InputStream io = file.getContents();
			JasperDesign jd = JRXmlLoader.load(io);
			List<JRParameter> prms = jd.getParametersList();
			for (JRParameter p : prms) {
				if (!p.isSystemDefined()) {
					JRDesignSubreportParameter sp = new JRDesignSubreportParameter();
					sp.setName(p.getName());

					JRDesignExpression jrep = new JRDesignExpression();
					jrep.setText("");
					sp.setExpression(jrep);

					s.addParameter(sp);
				}
			}
			if (io != null)
				io.close();
		} catch (JRException e) {
			e.printStackTrace();
		} catch (CoreException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		etxt.setText(jre.getText());
	}
}
