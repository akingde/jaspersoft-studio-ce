/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2011 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of JasperReports.
 * 
 * JasperReports is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General
 * Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * JasperReports is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along with JasperReports. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.components.table.editor;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.rulers.RulerProvider;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.parts.GraphicalViewerKeyHandler;
import org.eclipse.jface.action.IAction;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.components.table.messages.Messages;
import com.jaspersoft.studio.components.table.model.MTable;
import com.jaspersoft.studio.components.table.model.column.action.CreateColumnAfterAction;
import com.jaspersoft.studio.components.table.model.column.action.CreateColumnBeforeAction;
import com.jaspersoft.studio.components.table.model.column.action.CreateColumnBeginAction;
import com.jaspersoft.studio.components.table.model.column.action.CreateColumnCellAction;
import com.jaspersoft.studio.components.table.model.column.action.CreateColumnEndAction;
import com.jaspersoft.studio.components.table.model.columngroup.action.CreateColumnGroupAction;
import com.jaspersoft.studio.components.table.model.columngroup.action.UnGroupColumnsAction;
import com.jaspersoft.studio.editor.gef.parts.JasperDesignEditPartFactory;
import com.jaspersoft.studio.editor.gef.parts.MainDesignerRootEditPart;
import com.jaspersoft.studio.editor.gef.rulers.ReportRuler;
import com.jaspersoft.studio.editor.gef.rulers.ReportRulerProvider;
import com.jaspersoft.studio.editor.report.AbstractVisualEditor;
import com.jaspersoft.studio.preferences.RulersGridPreferencePage;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

/*
 * The Class TableEditor.
 * 
 * @author Chicu Veaceslav
 */
public class TableEditor extends AbstractVisualEditor {
	public TableEditor(JasperReportsConfiguration jrContext) {
		super(jrContext);
		setPartName(Messages.TableEditor_table);
		setPartImage(JaspersoftStudioPlugin.getImage(MTable.getIconDescriptor()
				.getIcon16()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.ui.parts.GraphicalEditor#configureGraphicalViewer()
	 */
	@Override
	protected void configureGraphicalViewer() {
		super.configureGraphicalViewer();
		getGraphicalViewer().getControl().setBackground(ColorConstants.button);

		GraphicalViewer graphicalViewer = getGraphicalViewer();
		MainDesignerRootEditPart rootEditPart = new MainDesignerRootEditPart();

		graphicalViewer.setRootEditPart(rootEditPart);
		// set EditPartFactory
		graphicalViewer.setEditPartFactory(new JasperDesignEditPartFactory());

		// set rulers providers
		RulerProvider provider = new ReportRulerProvider(new ReportRuler(true,
				RulerProvider.UNIT_PIXELS));
		graphicalViewer.setProperty(RulerProvider.PROPERTY_HORIZONTAL_RULER,
				provider);

		provider = new ReportRulerProvider(new ReportRuler(false,
				RulerProvider.UNIT_PIXELS));
		graphicalViewer.setProperty(RulerProvider.PROPERTY_VERTICAL_RULER,
				provider);

		Boolean isRulerVisible = JaspersoftStudioPlugin
				.getInstance()
				.getPreferenceStore()
				.getBoolean(RulersGridPreferencePage.P_PAGE_RULERGRID_SHOWRULER);

		graphicalViewer.setProperty(RulerProvider.PROPERTY_RULER_VISIBILITY,
				isRulerVisible);

		createAdditionalActions();
		graphicalViewer.setKeyHandler(new GraphicalViewerKeyHandler(
				graphicalViewer));
	}

	@Override
	protected List<String> getIgnorePalleteElements() {
		List<String> lst = new ArrayList<String>();
		lst.add("com.jaspersoft.studio.components.crosstab.model.MCrosstab"); //$NON-NLS-1$
		return lst;
	}

	@Override
	protected void createEditorActions(ActionRegistry registry) {
		IAction action = new CreateColumnEndAction(this);
		registry.registerAction(action);
		@SuppressWarnings("unchecked")
		List<String> selectionActions = getSelectionActions();
		selectionActions.add(CreateColumnEndAction.ID);

		action = new CreateColumnBeginAction(this);
		registry.registerAction(action);
		selectionActions.add(CreateColumnBeginAction.ID);

		action = new CreateColumnBeforeAction(this);
		registry.registerAction(action);
		selectionActions.add(CreateColumnBeforeAction.ID);

		action = new CreateColumnAfterAction(this);
		registry.registerAction(action);
		selectionActions.add(CreateColumnAfterAction.ID);

		action = new CreateColumnGroupAction(this);
		registry.registerAction(action);
		selectionActions.add(CreateColumnGroupAction.ID);

		action = new UnGroupColumnsAction(this);
		registry.registerAction(action);
		selectionActions.add(UnGroupColumnsAction.ID);

		action = new CreateColumnCellAction(this);
		registry.registerAction(action);
		selectionActions.add(CreateColumnCellAction.ID);
	}

}
