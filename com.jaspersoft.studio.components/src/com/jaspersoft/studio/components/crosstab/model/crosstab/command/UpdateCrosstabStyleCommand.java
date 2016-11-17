/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.crosstab.model.crosstab.command;
import java.util.ArrayList;
import java.util.Arrays;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.components.crosstab.model.MCrosstab;
import com.jaspersoft.studio.components.crosstab.model.dialog.ApplyCrosstabStyleAction;
import com.jaspersoft.studio.components.crosstab.model.dialog.CrosstabStyle;

import net.sf.jasperreports.engine.design.JRDesignStyle;
import net.sf.jasperreports.engine.design.JasperDesign;

/**
 * The command to update the CrosstabStyle of a Crosstab, support the undo
 * 
 * @author Orlandin Marco
 *
 */
public class UpdateCrosstabStyleCommand extends Command{
	
	/**
	 * The model of the Crosstab
	 */
	private MCrosstab crosstab;
	
	/**
	 * The styles of the Crosstab before the change
	 */
	private JRDesignStyle[] oldStyles;
	
	/**
	 * The new styles
	 */
	private CrosstabStyle newStyleTemplate;
	
	/**
	 * True if the new styles will overwrite the old ones, false if the old ones will keep and 
	 * the new ones will have a different name
	 */
	private boolean updateOldStyles;
	
	/**
	 * Create the command to change the CrosstabStyle of a Crosstab
	 * 
	 * @param crosstab The model of the Crosstab
	 * @param newStyle The new styles to apply to the Crosstab
	 * @param updateOldStyles True if the new styles will overwrite the old ones, false if the old ones will keep and 
	 * the new ones will have a different name and the Table element will use these new ones
	 */
	public UpdateCrosstabStyleCommand(MCrosstab crosstab, CrosstabStyle newStyle, boolean updateOldStyles){
		this.crosstab = crosstab;
		this.newStyleTemplate = newStyle;
		oldStyles = null;
		this.updateOldStyles = updateOldStyles;
	}
	

	@Override
	public void execute() {
		ApplyCrosstabStyleAction applyAction = new ApplyCrosstabStyleAction(newStyleTemplate, crosstab.getValue());
		JasperDesign jd = crosstab.getJasperDesign();
		//Save the old styles for the undo
		JRDesignStyle[] tableStyles  = applyAction.getStylesFromCrosstab(crosstab.getValue().getPropertiesMap(), jd);
		oldStyles = new JRDesignStyle[tableStyles.length];
		for(int i = 0; i < tableStyles.length; i++){
			JRDesignStyle currentStyle = tableStyles[i];
			if (currentStyle != null){
				oldStyles[i] = (JRDesignStyle)currentStyle.clone();
			}
		}
		//Apply the new style, the old one if not overwritten are not removed
		applyAction.updateStyle(jd, newStyleTemplate, updateOldStyles, false);
		crosstab.setChangedProperty(true);
	}
	
	@Override
	public void undo() {
		ArrayList<JRDesignStyle> styles =  new ArrayList<JRDesignStyle>(Arrays.asList(oldStyles));
		ApplyCrosstabStyleAction applyAction = new ApplyCrosstabStyleAction(styles, crosstab.getValue()); 
		//Restore the new style, if the update has created new styles they will be also removed
		applyAction.updateStyle(crosstab.getJasperDesign(), styles, updateOldStyles, true);
		oldStyles = null;
		crosstab.setChangedProperty(true);
	}
	
	/**
	 * Undo is available if the Crosstab and the styles previous the update are available 
	 */
	@Override
	public boolean canUndo() {
		return (crosstab != null && oldStyles != null);
	}

}
