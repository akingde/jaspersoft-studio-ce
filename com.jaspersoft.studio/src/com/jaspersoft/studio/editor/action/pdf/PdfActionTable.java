package com.jaspersoft.studio.editor.action.pdf;

import java.util.List;

import org.eclipse.gef.commands.Command;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.property.SetValueCommand;

public class PdfActionTable extends PdfActionAbstact {
	
	/** The Constant ID. */
	public static final String ID_Table_Full = "PdfAction_Table_Full"; //$NON-NLS-1$
	public static final String ID_Table_Start = "PdfAction_Table_Start"; //$NON-NLS-1$
	public static final String ID_Table_End = "PdfAction_Table_End"; //$NON-NLS-1$
	public static final String ID_Table_None = "PdfAction_Table_None"; //$NON-NLS-1$
	
	public PdfActionTable(IWorkbenchPart part,Position action_position) {
		super(part, action_position, ID_Table_Full, ID_Table_Start, ID_Table_End, ID_Table_None);
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
