package com.jaspersoft.studio.editor.action.pdf;

import java.util.List;

import org.eclipse.gef.commands.Command;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.property.SetValueCommand;

public class PdfActionTableRow extends PdfActionAbstact {
	
	/** The Constant ID. */
	public static final String ID_TableRow_Full = "PdfAction_TableRow_Full"; //$NON-NLS-1$
	public static final String ID_TableRow_Start = "PdfAction_TableRow_Start"; //$NON-NLS-1$
	public static final String ID_TableRow_End = "PdfAction_TableRow_End"; //$NON-NLS-1$
	public static final String ID_TableRow_None = "PdfAction_TableRow_None"; //$NON-NLS-1$
	
	public PdfActionTableRow(IWorkbenchPart part,Position action_position) {
		super(part, action_position, ID_TableRow_Full, ID_TableRow_Start, ID_TableRow_End, ID_TableRow_None);
	}

	
	public Command createCommand(MGraphicElement model){
		return null;
	}

	@Override
	protected boolean calculateEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
}
