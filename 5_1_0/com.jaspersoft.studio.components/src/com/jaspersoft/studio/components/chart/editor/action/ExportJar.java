package com.jaspersoft.studio.components.chart.editor.action;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import net.sf.jasperreports.chartthemes.simple.ChartThemeSettings;
import net.sf.jasperreports.chartthemes.simple.XmlChartThemeExtensionsRegistryFactory;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.Action;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.dialogs.SaveAsDialog;

import com.jaspersoft.studio.components.chart.editor.ChartThemeEditor;

public class ExportJar extends Action {
	public static final String ID = "EXPORTCHARTHEMEJAR";
	private ChartThemeEditor editor;

	public ExportJar(ChartThemeEditor editor) {
		super();
		setId(ID);
		this.editor = editor;
	}

	@Override
	public void run() {
		SaveAsDialog saveAsDialog = new SaveAsDialog(Display.getDefault().getActiveShell());
		saveAsDialog.setOriginalName(editor.getEditorInput().getName().replaceFirst(".jrctx", ".jar"));
		saveAsDialog.open();
		IPath path = saveAsDialog.getResult();
		if (path != null) {
			IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(path);
			if (file != null) {
				IProgressMonitor monitor = editor.getEditorSite().getActionBars().getStatusLineManager().getProgressMonitor();
				monitor.beginTask("Exporting Chart Themes to a JAR", IProgressMonitor.UNKNOWN);
				try {
					ChartThemeSettings cts = editor.getChartThemeSettings();
					File f = new File(file.getRawLocationURI());
					f.createNewFile();
					XmlChartThemeExtensionsRegistryFactory.saveToJar(cts, editor.getEditorInput().getName(), f);
					UIUtils.showInformation("Chart Theme was generated");
					file.getProject().refreshLocal(IResource.DEPTH_INFINITE, monitor);
				} catch (UnsupportedEncodingException e) {
					UIUtils.showError(e);
				} catch (IOException e) {
					UIUtils.showError(e);
				} catch (CoreException e) {
					UIUtils.showError(e);
				}
				monitor.done();
			}
		}

	}
}
