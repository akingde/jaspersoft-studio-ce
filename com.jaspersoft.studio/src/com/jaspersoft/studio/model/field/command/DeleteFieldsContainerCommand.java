/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model.field.command;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.command.ADatasetObjectDeleteCommand;
import com.jaspersoft.studio.model.field.FieldUtils;
import com.jaspersoft.studio.property.SetPropertyValueCommand;
import com.jaspersoft.studio.property.dataset.dialog.DataQueryAdapters;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

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
	public static final int DELETE = 0;
	public static final int UNGROUP_TO_PARENT = 1;
	public static final int UNGROUP_TO_ROOT = 2;
	public static final int UNGROUP_TO_PARENT_ALL = 3;
	private int mode = DELETE;

	public DeleteFieldsContainerCommand(JasperReportsConfiguration jContext, JRDesignDataset destNode, String path,
			ANode pnode, int mode) {
		super(false);
		this.pnode = pnode;
		this.jContext = jContext;
		jd = jContext.getJasperDesign();
		this.jrDataset = destNode;
		this.path = path;
		this.mode = mode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public void execute() {
		if (c == null) {
			int indx = path.lastIndexOf('.');
			String pkey = indx > 0 ? path.substring(0, indx) : "";

			c = new JSSCompoundCommand(pnode);
			c.enableSelectionRestore(true);
			List<JRField> fields = FieldUtils.getFields(jrDataset, path);
			for (JRField f : fields)
				switch (mode) {
				case DELETE:
					c.add(new DeleteFieldCommand(jContext, jrDataset, (JRDesignField) f, false));
					break;
				case UNGROUP_TO_PARENT:
					String oldKey = f.getPropertiesMap().getProperty(DataQueryAdapters.FIELD_PATH);
					c.add(new SetPropertyValueCommand(f.getPropertiesMap(), DataQueryAdapters.FIELD_PATH,
							StringUtils.removeStart(oldKey.replaceFirst(path, pkey), "."),
							((JRDesignField) f).getEventSupport()));
					break;
				case UNGROUP_TO_ROOT:
					oldKey = f.getPropertiesMap().getProperty(DataQueryAdapters.FIELD_PATH);
					c.add(new SetPropertyValueCommand(f.getPropertiesMap(), DataQueryAdapters.FIELD_PATH,
							StringUtils.removeStart(oldKey.replaceFirst(path, ""), "."),
							((JRDesignField) f).getEventSupport()));
					break;
				case UNGROUP_TO_PARENT_ALL:
					// oldKey = f.getPropertiesMap().getProperty(DataQueryAdapters.FIELD_PATH);
					c.add(new SetPropertyValueCommand(f.getPropertiesMap(), DataQueryAdapters.FIELD_PATH, pkey,
							((JRDesignField) f).getEventSupport()));
					break;
				}
		}
		c.execute();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	@Override
	public void undo() {
		if (c != null)
			c.undo();
	}

}
