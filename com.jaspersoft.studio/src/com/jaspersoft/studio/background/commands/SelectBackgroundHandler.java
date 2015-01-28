package com.jaspersoft.studio.background.commands;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.IEditorPart;

import com.jaspersoft.studio.background.MBackgrounImage;
import com.jaspersoft.studio.editor.JrxmlEditor;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.utils.SelectionHelper;

public class SelectBackgroundHandler extends AbstractHandler {

	private static final String[] FILTER_EXTS = { "*.jpg", "*.png", "*.bmp", "*.*"}; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		FileDialog dialog = new FileDialog(UIUtils.getShell());
		dialog.setText(Messages.MBackgrounImage_imageDialogTitle);
		String homefolder = System.getProperty("user.home"); //$NON-NLS-1$
		dialog.setFilterPath(homefolder);
		dialog.setFilterExtensions(FILTER_EXTS);
		String path = dialog.open();
		if (path != null){
			IEditorPart editor = SelectionHelper.getActiveJRXMLEditor();
			if (editor instanceof JrxmlEditor){
				JrxmlEditor jrxmlEditor = (JrxmlEditor)editor;
				Object value = jrxmlEditor.getReportContainer().getModel().getValue();
				if (value instanceof JasperDesign){
					JasperDesign jd = (JasperDesign)value;
					jrxmlEditor.getReportContainer().setBackgroundImageVisible(true);
					jrxmlEditor.getReportContainer().setBackgroundImageEditable(false);
					jd.setProperty(MBackgrounImage.PROPERTY_PATH, path);
				}
			}
		}
		return null;
	}
	
	@Override
	public boolean isEnabled() {
		IEditorPart currentEditor = SelectionHelper.getActiveJRXMLEditor();
		return (currentEditor instanceof JrxmlEditor);
	}


}
