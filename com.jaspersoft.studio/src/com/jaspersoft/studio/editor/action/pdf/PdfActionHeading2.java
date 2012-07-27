package com.jaspersoft.studio.editor.action.pdf;

import java.util.List;

import org.eclipse.gef.commands.Command;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.property.SetValueCommand;

public class PdfActionHeading2 extends PdfActionAbstact {
	
	/** The Constant ID. */
	public static final String ID_Heading2_Full = "PdfAction_Heading2_Full"; //$NON-NLS-1$
	public static final String ID_Heading2_Start = "PdfAction_Heading2_Start"; //$NON-NLS-1$
	public static final String ID_Heading2_End = "PdfAction_Heading2_End"; //$NON-NLS-1$
	public static final String ID_Heading2_None = "PdfAction_Heading2_None"; //$NON-NLS-1$
	
	public PdfActionHeading2(IWorkbenchPart part,Position action_position) {
		super(part, action_position, ID_Heading2_Full, ID_Heading2_Start, ID_Heading2_End, ID_Heading2_None);
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
