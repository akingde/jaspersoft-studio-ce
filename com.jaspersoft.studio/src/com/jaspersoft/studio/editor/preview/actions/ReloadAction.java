package com.jaspersoft.studio.editor.preview.actions;

import org.eclipse.jface.action.Action;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.preview.PreviewEditor;

public class ReloadAction extends Action {
	public static final String ID = "PREVIEWRELOADACTION";
	private PreviewEditor editor;

	public ReloadAction(PreviewEditor editor) {
		super();
		this.editor = editor;
		setId(ID);
		setText("Reload");
		setDescription("Reload");
		setToolTipText("Reload");
		setImageDescriptor(JaspersoftStudioPlugin.getImageDescriptor("icons/eclipseicons/reload.gif"));
		setDisabledImageDescriptor(JaspersoftStudioPlugin.getImageDescriptor("icons/eclipseicons/reloadd.gif"));
	}

	@Override
	public boolean isEnabled() {
		return super.isEnabled() && editor.isNorun();
	}

	@Override
	public void run() {
		editor.runReport(null);
	}
}
