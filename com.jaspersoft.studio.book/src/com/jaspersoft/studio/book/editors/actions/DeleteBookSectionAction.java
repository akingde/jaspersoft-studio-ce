/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.book.editors.actions;

import java.util.List;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.book.JRBookActivator;
import com.jaspersoft.studio.book.messages.Messages;
import com.jaspersoft.studio.book.model.MGroupReportPartContainer;
import com.jaspersoft.studio.book.model.MReportPartContainer;
import com.jaspersoft.studio.book.model.commands.RemoveSectionCommand;
import com.jaspersoft.studio.editor.action.ACachedSelectionAction;

import net.sf.jasperreports.engine.type.BandTypeEnum;

public class DeleteBookSectionAction extends ACachedSelectionAction {

	public static final String ID = "delete_book_section"; //$NON-NLS-1$
	
	public DeleteBookSectionAction(IWorkbenchPart part) {
		super(part);
	}

	@Override
	protected void init() {
		super.init();
		setText(Messages.DeleteBookSectionAction_actionTitle);
		setToolTipText(Messages.DeleteBookSectionAction_actionTooltip);
		setId(ID);
		setImageDescriptor(JRBookActivator.getDefault().getImageDescriptor("/icons/delete.gif"));
		setEnabled(false);
	}
	
	@Override
	protected Command createCommand() {
		List<Object> parts = editor.getSelectionCache().getSelectionModelForType(MGroupReportPartContainer.class);
		CompoundCommand cc = new CompoundCommand();
		for(Object rawPart : parts){
			MGroupReportPartContainer part = (MGroupReportPartContainer)rawPart;
			BandTypeEnum bandType = (BandTypeEnum)part.getPropertyValue(MReportPartContainer.PROPERTY_CONTAINER_TYPE);
			if (!BandTypeEnum.DETAIL.equals(bandType)){
				RemoveSectionCommand removeCommand = new RemoveSectionCommand(part);
				cc.add(removeCommand);
			}
		}
		return cc.isEmpty() ? null : cc;
	}

}
