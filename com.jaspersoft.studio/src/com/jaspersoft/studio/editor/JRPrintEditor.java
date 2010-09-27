package com.jaspersoft.studio.editor;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.util.JRLoader;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gef.internal.InternalImages;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.ide.FileStoreEditorInput;
import org.eclipse.ui.part.EditorPart;

import com.jasperassistant.designer.viewer.ReportViewer;
import com.jasperassistant.designer.viewer.actions.FirstPageAction;
import com.jasperassistant.designer.viewer.actions.LastPageAction;
import com.jasperassistant.designer.viewer.actions.NextPageAction;
import com.jasperassistant.designer.viewer.actions.PageNumberContributionItem;
import com.jasperassistant.designer.viewer.actions.PreviousPageAction;
import com.jasperassistant.designer.viewer.actions.PrintAction;
import com.jasperassistant.designer.viewer.actions.ZoomActualSizeAction;
import com.jasperassistant.designer.viewer.actions.ZoomComboContributionItem;
import com.jasperassistant.designer.viewer.actions.ZoomFitPageAction;
import com.jasperassistant.designer.viewer.actions.ZoomFitPageWidthAction;
import com.jasperassistant.designer.viewer.actions.ZoomInAction;
import com.jasperassistant.designer.viewer.actions.ZoomOutAction;

public class JRPrintEditor extends EditorPart {

	@Override
	public void doSave(IProgressMonitor monitor) {

	}

	@Override
	public void doSaveAs() {

	}

	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		setSite(site);
		setInput(input);
		InputStream in = null;
		try {
			if (input instanceof FileStoreEditorInput) {
				in = new FileInputStream(((FileStoreEditorInput) input).getURI().getPath());
			} else if (input instanceof IFileEditorInput) {
				in = ((IFileEditorInput) input).getFile().getContents();
			} else {
				throw new PartInitException("Invalid Input: Must be IFileEditorInput or FileStoreEditorInput");
			}
			jasperPrint = (JasperPrint) JRLoader.loadObject(in);
			setReportDocument(true);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (CoreException e) {
			e.printStackTrace();
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean isDirty() {
		return false;
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}

	private ReportViewer reportViewer = new ReportViewer(SWT.BORDER | SWT.NO_FOCUS);
	private Control reportViewerControl;

	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout(1, false);
		layout.marginWidth = layout.marginHeight = 0;
		container.setLayout(layout);

		initToolBar(container);

		reportViewerControl = reportViewer.createControl(container);
		reportViewerControl.setLayoutData(new GridData(GridData.FILL_BOTH));
	}

	@Override
	public void setFocus() {
		if (reportViewerControl != null)
			reportViewerControl.setFocus();
	}

	private IToolBarManager tbManager;

	public void initToolBar(Composite parent) {
		ToolBar toolBar = new ToolBar(parent, SWT.FLAT | SWT.WRAP | SWT.RIGHT);
		GridData gd = new GridData();
		toolBar.setLayoutData(gd);

		tbManager = new ToolBarManager(toolBar);

		refreshToolbar();

		toolBar.update();
	}

	private void refreshToolbar() {
		tbManager.removeAll();
		// ExportMenuAction exportMenu = new ExportMenuAction(reportViewer);
		// IAction pdfAction = null;
		// exportMenu.getMenuManager().add(
		// pdfAction = new ExportAsPdfAction(reportViewer));
		// exportMenu.getMenuManager().add(
		// new ExportAsRtfAction(reportViewer));
		// exportMenu.getMenuManager().add(
		// new ExportAsJasperReportsAction(reportViewer));
		// exportMenu.getMenuManager().add(new ExportAsHtmlAction(reportViewer));
		// exportMenu.getMenuManager().add(
		// new ExportAsSingleXlsAction(reportViewer));
		// exportMenu.getMenuManager().add(
		// new ExportAsMultiXlsAction(reportViewer));
		// exportMenu.getMenuManager().add(new ExportAsCsvAction(reportViewer));
		// exportMenu.getMenuManager().add(new ExportAsXmlAction(reportViewer));
		// exportMenu.getMenuManager().add(
		// new ExportAsXmlWithImagesAction(reportViewer));
		// exportMenu.setDefaultAction(pdfAction);
		//
		// tbManager.add(exportMenu);
		tbManager.add(new PrintAction(reportViewer));

		tbManager.add(new Separator());
		tbManager.add(new FirstPageAction(reportViewer));
		tbManager.add(new PreviousPageAction(reportViewer));
		if (SWT.getPlatform().equals("win32")) //$NON-NLS-1$
			tbManager.add(new PageNumberContributionItem(reportViewer));
		tbManager.add(new NextPageAction(reportViewer));
		tbManager.add(new LastPageAction(reportViewer));
		tbManager.add(new Separator());
		tbManager.add(new ZoomActualSizeAction(reportViewer));
		tbManager.add(new ZoomFitPageAction(reportViewer));
		tbManager.add(new ZoomFitPageWidthAction(reportViewer));
		tbManager.add(new Separator());
		ZoomOutAction zoomOutAction = new ZoomOutAction(reportViewer);
		zoomOutAction.setImageDescriptor(InternalImages.DESC_ZOOM_OUT);
		tbManager.add(zoomOutAction);
		tbManager.add(new ZoomComboContributionItem(reportViewer));
		ZoomInAction zoomInAction = new ZoomInAction(reportViewer);
		zoomInAction.setImageDescriptor(InternalImages.DESC_ZOOM_IN);
		tbManager.add(zoomInAction);

		tbManager.update(true);
	}

	private JasperPrint jasperPrint;

	void setReportDocument(final boolean noRun) {
		if (jasperPrint != null) {
			Display.getDefault().syncExec(new Runnable() {
				public void run() {
					if (jasperPrint != null) {
						try {
							reportViewer.setDocument(jasperPrint);
						} catch (Exception e) {
							unsetReportDocument(e2string(e), true);
						}
					}
				}

				private String e2string(Exception e) {
					Writer result = new StringWriter();
					PrintWriter printWriter = new PrintWriter(result);
					e.printStackTrace(printWriter);
					return result.toString();
				}
			});
		}
	}

	void unsetReportDocument(final String msg, final boolean noRun) {
		Display.getDefault().syncExec(new Runnable() {
			public void run() {
				reportViewer.unsetDocument(msg);
			}
		});
	}

}
