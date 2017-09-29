/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model.field.command;

import java.util.List;

import org.eclipse.jface.dialogs.Dialog;

import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.command.ADatasetObjectDeleteCommand;
import com.jaspersoft.studio.model.field.DeleteFieldConfirmationDialog;
import com.jaspersoft.studio.model.field.FieldUtils;
import com.jaspersoft.studio.property.SetPropertyValueCommand;
import com.jaspersoft.studio.property.dataset.dialog.DataQueryAdapters;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignField;

/*
 * /* link nodes & together.
 * 
 * @author Chicu Veaceslav
 */
public class DeleteFieldsContainerCommand extends ADatasetObjectDeleteCommand {
	private String path;
	private JSSCompoundCommand c;
	private ANode pnode;

	public DeleteFieldsContainerCommand(JasperReportsConfiguration jContext, JRDesignDataset destNode, String path,
			ANode pnode) {
		super(false);
		this.pnode = pnode;
		this.jContext = jContext;
		jd = jContext.getJasperDesign();
		this.jrDataset = destNode;
		this.path = path;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public void execute() {
		if (c == null) {
			canceled = true;
			String pkey = path;
			int indx = path.lastIndexOf(".");
			if (indx > 0)
				pkey = path.substring(0, indx);

			DeleteFieldConfirmationDialog d = new DeleteFieldConfirmationDialog(UIUtils.getShell(), pkey);
			if (d.open() == Dialog.OK) {
				c = new JSSCompoundCommand(pnode);
				c.enableSelectionRestore(true);
				List<JRField> fields = FieldUtils.getFields(jrDataset, path);
				for (JRField f : fields)
					switch (d.getChoise()) {
					case DeleteFieldConfirmationDialog.DELETE:
						c.add(new DeleteFieldCommand(jContext, jrDataset, (JRDesignField) f, false));
						break;
					case DeleteFieldConfirmationDialog.UNGROUP_TO_PARENT:
						String oldKey = f.getPropertiesMap().getProperty(DataQueryAdapters.FIELD_PATH);
						c.add(new SetPropertyValueCommand(f.getPropertiesMap(), DataQueryAdapters.FIELD_PATH,
								oldKey.replaceFirst(path, pkey), ((JRDesignField) f).getEventSupport()));
						break;
					case DeleteFieldConfirmationDialog.UNGROUP_TO_ROOT:
						oldKey = f.getPropertiesMap().getProperty(DataQueryAdapters.FIELD_PATH);
						c.add(new SetPropertyValueCommand(f.getPropertiesMap(), DataQueryAdapters.FIELD_PATH,
								oldKey.replaceFirst(path, ""), ((JRDesignField) f).getEventSupport()));
						break;
					}
				canceled = false;
			}
		}
		if (!canceled)
			c.execute();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	@Override
	public void undo() {
		if (c != null && !canceled)
			c.undo();
	}

}