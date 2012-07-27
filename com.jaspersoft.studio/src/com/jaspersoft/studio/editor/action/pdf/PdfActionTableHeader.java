package com.jaspersoft.studio.editor.action.pdf;

import java.util.List;

import org.eclipse.gef.commands.Command;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.property.SetValueCommand;


public class PdfActionTableHeader extends PdfActionAbstact {

	/** The Constant ID. */
	public static final String ID_TableHeader_Full = "PdfAction_TableHeader_Full"; //$NON-NLS-1$
	public static final String ID_TableHeader_Start = "PdfAction_TableHeader_Start"; //$NON-NLS-1$
	public static final String ID_TableHeader_End = "PdfAction_TableHeader_End"; //$NON-NLS-1$
	public static final String ID_TableHeader_None = "PdfAction_TableHeader_None"; //$NON-NLS-1$
	
	public PdfActionTableHeader(IWorkbenchPart part,Position action_position) {
		super(part, action_position, ID_TableHeader_Full, ID_TableHeader_Start, ID_TableHeader_End, ID_TableHeader_None);
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
