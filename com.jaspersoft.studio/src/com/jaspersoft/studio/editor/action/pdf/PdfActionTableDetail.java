package com.jaspersoft.studio.editor.action.pdf;

import java.util.List;

import org.eclipse.gef.commands.Command;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.property.SetValueCommand;

public class PdfActionTableDetail extends PdfActionAbstact {
	
	/** The Constant ID. */
	public static final String ID_TableDetail_Full = "PdfAction_TableDetail_Full"; //$NON-NLS-1$
	public static final String ID_TableDetail_Start = "PdfAction_TableDetail_Start"; //$NON-NLS-1$
	public static final String ID_TableDetail_End = "PdfAction_TableDetail_End"; //$NON-NLS-1$
	public static final String ID_TableDetail_None = "PdfAction_TableDetail_None"; //$NON-NLS-1$
	
	public PdfActionTableDetail(IWorkbenchPart part,Position action_position) {
		super(part, action_position, ID_TableDetail_Full, ID_TableDetail_Start, ID_TableDetail_End, ID_TableDetail_None);
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
