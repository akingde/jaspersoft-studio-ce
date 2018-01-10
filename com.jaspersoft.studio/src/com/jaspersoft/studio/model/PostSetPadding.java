/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model;

import java.util.List;

import org.eclipse.gef.commands.Command;
import org.eclipse.ui.views.properties.IPropertySource;

import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.editor.layout.LayoutManager;
import com.jaspersoft.studio.model.style.MStyle;
import com.jaspersoft.studio.property.IPostSetValue;
import com.jaspersoft.studio.utils.ModelUtils;

import net.sf.jasperreports.engine.JRStyle;
import net.sf.jasperreports.engine.base.JRBaseLineBox;

/**
 * When set the padding on an element relayout the content of the element.
 * When set the padding on a style relayout all the elements using that style
 * 
 * @author Orlandin Marco
 */
public class PostSetPadding implements IPostSetValue {

	@Override
	public Command postSetValue(IPropertySource target, Object prop, Object newValue, Object oldValue) {
		if (target instanceof MLineBox && isPaddingOperation(prop)) {
			ANode container = ((MLineBox) target).getContainer();
			//check if it is a style or a normal element
			if (container instanceof MStyle){
				JRStyle style = ((MStyle)container).getValue();
				MReport root = ModelUtils.getReport(container);
				if (root != null){
					//in case of a template style file the root will be null, skip this part of 
					//code since there is no need to refresh the layout into a template style
					List<ANode> nodesUsingStyle = root.getUsedStyles().get(style.getName());
					if (nodesUsingStyle != null){
						JSSCompoundCommand cmd = new JSSCompoundCommand(root);
						for(ANode node : nodesUsingStyle){
							Command layoutCmd = LayoutManager.createRelayoutCommand(node);
							if (layoutCmd != null){
								cmd.add(layoutCmd);
							}
						}
						if (!cmd.isEmpty()) return cmd;
					}
				}
			} else {
				return LayoutManager.createRelayoutCommand(container);
			}
		}
		return null;
	}
	
	/*protected boolean isConstraintOperation(Object prop){
		return (prop.equals(JRDesignElement.PROPERTY_X) ||
						prop.equals(JRDesignElement.PROPERTY_Y) ||
						prop.equals(JRDesignElement.PROPERTY_WIDTH) || 
						prop.equals(JRDesignElement.PROPERTY_HEIGHT));
	}*/
	
	protected boolean isPaddingOperation(Object prop){
		return (prop.equals(JRBaseLineBox.PROPERTY_LEFT_PADDING) || 
						prop.equals(JRBaseLineBox.PROPERTY_BOTTOM_PADDING) || 
						prop.equals(JRBaseLineBox.PROPERTY_RIGHT_PADDING) || 
						prop.equals(JRBaseLineBox.PROPERTY_TOP_PADDING) || 
						prop.equals(JRBaseLineBox.PROPERTY_PADDING));
	}
}
