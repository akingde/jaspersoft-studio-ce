package com.jaspersoft.studio.editor.preview.view.report.html;

import java.io.File;

import net.sf.jasperreports.engine.JasperPrint;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.editor.preview.actions.export.AbstractExportAction;
import com.jaspersoft.studio.editor.preview.view.APreview;
import com.jaspersoft.studio.editor.preview.view.report.ExportMenu;
import com.jaspersoft.studio.editor.preview.view.report.IJRPrintable;
import com.jaspersoft.studio.editor.preview.view.report.swt.ReportViewer;
import com.jaspersoft.studio.preferences.util.PropertiesHelper;
import com.jaspersoft.studio.utils.FileUtils;

public abstract class AFileViewer extends APreview implements IJRPrintable {

	private ReportViewer rptviewer;
	private Text browser;

	public AFileViewer(Composite parent, PropertiesHelper ph) {
		super(parent, ph);
	}

	protected abstract AbstractExportAction createExporter(ReportViewer rptv);

	protected abstract String getExtension();

	@Override
	public void contribute2ToolBar(IToolBarManager tmanager) {
		if (jrprint != null) {
			tmanager.add(ExportMenu.getExportMenu(rptviewer, getPropertiesHelper()));
		}
	}

	@Override
	protected Control createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		composite.setLayout(layout);

		browser = new Text(composite, SWT.MULTI | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.READ_ONLY);
		browser.setLayoutData(new GridData(GridData.FILL_BOTH));

		rptviewer = new ReportViewer();
		return composite;
	}

	private JasperPrint jrprint;

	public void setJRPRint(JasperPrint jrprint) throws Exception {
		if (this.jrprint == null || this.jrprint != jrprint) {
			rptviewer.setDocument(jrprint);

			File tmpFile = File.createTempFile("report", getExtension());

			AbstractExportAction exp = createExporter(rptviewer);
			exp.export(tmpFile);

			browser.setText(FileUtils.readFileAsAString(tmpFile));
		}
		this.jrprint = jrprint;
	}

}
