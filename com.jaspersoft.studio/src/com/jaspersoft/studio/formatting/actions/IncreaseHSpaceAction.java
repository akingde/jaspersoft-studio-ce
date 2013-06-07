package com.jaspersoft.studio.formatting.actions;

import java.util.List;

import net.sf.jasperreports.engine.design.JRDesignElement;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.SetBoundCommand;

public class IncreaseHSpaceAction extends AbstractFormattingAction {

	public IncreaseHSpaceAction(IWorkbenchPart part) {
		super(part);
		setText("IncreaseHSpaceAction");
	}
	
	public static CompoundCommand generateCommand(List<APropertyNode> nodes){
		CompoundCommand command = new CompoundCommand();
		
		List<APropertyNode> sortedElements = sortXY(nodes);
    
    for (int i=1; i<sortedElements.size(); ++i)
    {
    		APropertyNode actualNode = sortedElements.get(i);
        JRDesignElement element = (JRDesignElement)actualNode.getValue();
        Rectangle oldBounds = getElementBounds(element);
        element.setX( element.getX() + 5*i);
        SetBoundCommand newCommand = new SetBoundCommand();
	      newCommand.setContext(actualNode, oldBounds);
	      command.add(newCommand);
    }
		
		return command;
	}

	@Override
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
