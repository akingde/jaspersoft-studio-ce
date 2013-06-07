package com.jaspersoft.studio.formatting.actions;

import java.util.List;

import net.sf.jasperreports.engine.design.JRDesignElement;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.SetValueCommand;


public class AlignMarginTopAction extends AbstractFormattingAction {

	/** The Constant ID. */
	public static final String ID = "aligntotop"; //$NON-NLS-1$
	
	
	public AlignMarginTopAction(IWorkbenchPart part) {
		super(part);
		setText("Align to Top");
		setId(ID);
	}

	public static CompoundCommand generateCommand(List<APropertyNode> nodes){
		CompoundCommand command = new CompoundCommand();
    // Find the smallest one...
		for (APropertyNode element : nodes)
    {
			SetValueCommand setCommand = new SetValueCommand();
			setCommand.setTarget(element);
			setCommand.setPropertyId(JRDesignElement.PROPERTY_Y);
			setCommand.setPropertyValue(0);
			command.add(setCommand);
    }
		
		return command;
	}

	protected Command createAlignmentCommand() {
			List<APropertyNode> nodes = getOperationSet();
			CompoundCommand command = null;
			if (nodes.isEmpty()) 
				command = new CompoundCommand();
			else {
				command = generateCommand(nodes);
			}
			command.setDebugLabel(getText());
			return command;
	}
	
	@Override
	protected boolean calculateEnabled() {
		return true;
	}

}
