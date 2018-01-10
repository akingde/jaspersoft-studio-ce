/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model.field.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.gef.commands.Command;
import org.eclipse.jface.dialogs.Dialog;

import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.jface.dialogs.NameDialog;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.field.FieldUtils;
import com.jaspersoft.studio.model.field.MField;
import com.jaspersoft.studio.model.field.MFields;
import com.jaspersoft.studio.model.field.MFieldsContainer;
import com.jaspersoft.studio.property.SetPropertyValueCommand;
import com.jaspersoft.studio.property.dataset.dialog.DataQueryAdapters;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignField;

/*
 * link nodes & together.
 * 
 * @author Chicu Veaceslav
 */
public class CreateFieldsContainerCommand extends Command {
	private JRDesignDataset jrDataSet;
	private JSSCompoundCommand c;
	private Boolean canceled = false;
	private List<ANode> srcNodes;
	private boolean typeAdd;
	private MFields parent;

	public CreateFieldsContainerCommand(List<ANode> srcNodes) {
		super();
		this.srcNodes = srcNodes;
		this.typeAdd = false;
	}

	public CreateFieldsContainerCommand(MFields parent, List<ANode> srcNodes, boolean typeAdd) {
		super();
		this.parent = parent;
		this.srcNodes = srcNodes;
		this.typeAdd = typeAdd;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public void execute() {
		if (c == null) {
			canceled = false;
			String pkey = null;
			if (typeAdd) {
				pkey = ""; //$NON-NLS-1$
				if (parent instanceof MFieldsContainer)
					pkey = ((MFieldsContainer) parent).getKey();
			} else {
				NameDialog d = new NameDialog(UIUtils.getShell(), Messages.CreateFieldsContainerCommand_1);
				if (d.open() == Dialog.OK)
					pkey = d.getName();
				else {
					canceled = true;
					return;
				}
			}
			Map<String, List<JRField>> map = new HashMap<>();
			String path = null;
			c = new JSSCompoundCommand(null);
			c.enableSelectionRestore(true);
			for (ANode n : srcNodes) {
				if (n instanceof MField) {
					setupDataset((MFields) n.getParent());
					String p = setupMap(((MField) n).getValue(), map);
					if (path == null)
						path = p;
				} else if (n instanceof MFieldsContainer) {
					setupDataset((MFields) n);
					if (path == null) {
						path = ((MFieldsContainer) n).getKey();
						int indx = path.lastIndexOf('.');
						if (indx > 0)
							path = path.substring(0, indx);
					}
					for (MField mf : FieldUtils.getFields((MFields) n))
						setupMap(mf.getValue(), map);
				}
			}
			String npath = "";
			if (typeAdd || path.isEmpty())
				npath = pkey;
			else {
				if (Misc.isNullOrEmpty(path))
					npath = pkey;
				else
					npath = path + "." + pkey; //$NON-NLS-1$
			}

			for (Map.Entry<String, List<JRField>> entry : map.entrySet()) {
				String p = path;
				if (!entry.getKey().startsWith(path)) {
					int indx = entry.getKey().lastIndexOf('.');
					if (indx > 0)
						p = entry.getKey().substring(0, indx);
				}
				for (JRField f : entry.getValue()) {
					String k = entry.getKey().replaceFirst(p, npath);

					c.add(new SetPropertyValueCommand(f.getPropertiesMap(), DataQueryAdapters.FIELD_PATH, k,
							((JRDesignField) f).getEventSupport()));
				}
			}
		}
		if (!canceled)
			c.execute();
	}

	private String setupMap(JRField f, Map<String, List<JRField>> map) {
		String fpath = Misc.nvl(f.getPropertiesMap().getProperty(DataQueryAdapters.FIELD_PATH));
		List<JRField> fields = map.get(fpath);
		if (fields == null)
			fields = new ArrayList<>();
		fields.add(f);
		map.put(fpath, fields);
		return fpath;
	}

	private void setupDataset(MFields fields) {
		if (jrDataSet == null)
			jrDataSet = fields.getValue();
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
