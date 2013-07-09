/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.model.band;

import java.util.List;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignDatasetRun;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.views.properties.IPropertySource;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.IDatasetContainer;
import com.jaspersoft.studio.model.dataset.MDataset;
import com.jaspersoft.studio.model.dataset.MDatasetRun;
import com.jaspersoft.studio.model.dataset.command.DeleteDatasetCommand;
import com.jaspersoft.studio.property.IPostSetValue;

public class PostSetDatasetName implements IPostSetValue {

	private class SetDatasetRunName extends Command{
		
		private MDatasetRun element;
		
		private String oldName;
		
		private String newName;
		
		public SetDatasetRunName(MDatasetRun element, String oldName, String newName){
			this.element = element;
			this.oldName = oldName;
			this.newName = newName;
		}
		
		@Override
		public void execute() {
			element.setPropertyValue(JRDesignDatasetRun.PROPERTY_DATASET_NAME, newName);
		}
		
		@Override
		public void undo() {
			element.setPropertyValue(JRDesignDatasetRun.PROPERTY_DATASET_NAME, oldName);
		}
		
	}
	
	@Override
	public Command postSetValue(IPropertySource target, Object prop, Object newValue, Object oldValue) {
		CompoundCommand c = new CompoundCommand();
		if (target instanceof MDataset && prop.equals(JRDesignDataset.PROPERTY_NAME)) {
			List<IDatasetContainer> references = DeleteDatasetCommand.getDatasetUsage(((MDataset)target).getRoot().getChildren(), oldValue.toString());
			if (references.size()>0){
				MessageDialog dialog = new MessageDialog(UIUtils.getShell(), "Update the references to the dataset", null,
						"One or more elements are using this dataset, would you to keep the reference of this elements updated?", MessageDialog.WARNING, new String[] { Messages.DeleteDatasetCommand_yesOption,Messages.DeleteDatasetCommand_noOption}, 1); 
				int selection = dialog.open();
				if (selection == 0){
					for(IDatasetContainer datasetRun : references){
						MDatasetRun editedDataset = DeleteDatasetCommand.checkContains(datasetRun.getDatasetRunList(), oldValue.toString());
						if (editedDataset != null) c.add(new SetDatasetRunName(editedDataset, oldValue.toString(), newValue.toString()));
					}
				}
			}
		}
		return c;
	}

}
