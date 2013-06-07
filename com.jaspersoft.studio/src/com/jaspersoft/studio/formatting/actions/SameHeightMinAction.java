package com.jaspersoft.studio.formatting.actions;

import java.util.List;

import net.sf.jasperreports.engine.design.JRDesignElement;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.SetValueCommand;

public class SameHeightMinAction extends AbstractFormattingAction{

	public SameHeightMinAction(IWorkbenchPart part) {
		super(part);
		setText("SameHeightMinAction");
	}

	@Override
	protected boolean calculateEnabled() {
		return true;
	}
	
	public static CompoundCommand generateCommand(List<APropertyNode> nodes){
		CompoundCommand command = new CompoundCommand();
		
		int height = (Integer) nodes.get(0).getPropertyValue(JRDesignElement.PROPERTY_HEIGHT);
    
    // Find the smallest one...
    for (int i=1; i<nodes.size(); ++i)
    {
    		JRDesignElement element = (JRDesignElement)nodes.get(i).getValue();
        if (height > element.getHeight())
        {
            height = element.getHeight();
        }
    }
    
    for (APropertyNode node : nodes)
    {
  			SetValueCommand setCommand = new SetValueCommand();
  			setCommand.setTarget(node);
  			setCommand.setPropertyId(JRDesignElement.PROPERTY_HEIGHT);
  			setCommand.setPropertyValue(height);
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
	
}
