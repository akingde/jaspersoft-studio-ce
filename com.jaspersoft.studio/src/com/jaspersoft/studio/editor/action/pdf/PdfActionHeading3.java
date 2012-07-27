package com.jaspersoft.studio.editor.action.pdf;

import java.util.List;

import org.eclipse.gef.commands.Command;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.property.SetValueCommand;


public class PdfActionHeading3 extends PdfActionAbstact {
	
	/** The Constant ID. */
	public static final String ID_Heading3_Full = "PdfAction_Heading3_Full"; //$NON-NLS-1$
	public static final String ID_Heading3_Start = "PdfAction_Heading3_Start"; //$NON-NLS-1$
	public static final String ID_Heading3_End = "PdfAction_Heading3_End"; //$NON-NLS-1$
	public static final String ID_Heading3_None = "PdfAction_Heading3_None"; //$NON-NLS-1$
	
	public PdfActionHeading3(IWorkbenchPart part,Position action_position) {
		super(part, action_position, ID_Heading3_Full, ID_Heading3_Start, ID_Heading3_End, ID_Heading3_None);
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
