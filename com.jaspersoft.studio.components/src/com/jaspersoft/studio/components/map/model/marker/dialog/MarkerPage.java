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
package com.jaspersoft.studio.components.map.model.marker.dialog;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.components.map.Item;
import net.sf.jasperreports.components.map.ItemProperty;
import net.sf.jasperreports.components.map.StandardItem;
import net.sf.jasperreports.components.map.StandardItemProperty;

import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import com.jaspersoft.studio.components.map.messages.Messages;
import com.jaspersoft.studio.components.map.model.marker.MarkersDTO;
import com.jaspersoft.studio.swt.widgets.table.DeleteButton;
import com.jaspersoft.studio.swt.widgets.table.EditButton;
import com.jaspersoft.studio.swt.widgets.table.IEditElement;
import com.jaspersoft.studio.swt.widgets.table.INewElement;
import com.jaspersoft.studio.swt.widgets.table.ListContentProvider;
import com.jaspersoft.studio.swt.widgets.table.ListOrderButtons;
import com.jaspersoft.studio.swt.widgets.table.NewButton;
import com.jaspersoft.studio.utils.ModelUtils;

public class MarkerPage extends WizardPage {
	private final class EditElement implements IEditElement<Item> {
		@Override
		public void editElement(List<Item> input, int pos) {
			Item v = (Item) input.get(pos);
			if (v == null)
				return;
			v = (Item) v.clone();
			MarkerDialog dialog = new MarkerDialog(Display.getDefault().getActiveShell());

			dialog.setValue((StandardItem) v, ModelUtils.getElementExpressionContext(null, value.getPnode()));
			if (dialog.open() == Window.OK)
				input.set(pos, v);
		}
	}

	private MarkersDTO value;
	private Table table;
	private TableViewer tableViewer;
	private EditButton<Item> editButton;

	public MarkersDTO getValue() {
		return new MarkersDTO(value.getMarkers(), value.getPnode());
	}

	@Override
	public void dispose() {
		// clear all properties
		List<Item> props = (List<Item>) tableViewer.getInput();
		value.setMarkers(props);
		super.dispose();
	}

	public void setValue(MarkersDTO value) {
		this.value = value;
		if (table != null)
			fillTable(table);
	}

	protected MarkerPage(String pageName) {
		super(pageName);
		setTitle(Messages.MarkerPage_Title);
		setDescription(Messages.MarkerPage_Description);
	}

	public void createControl(final Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout(2, false);
		composite.setLayout(layout);
		setControl(composite);

		buildTable(composite);

		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.heightHint = 400;
		table.setLayoutData(gd);

		Composite bGroup = new Composite(composite, SWT.NONE);
		bGroup.setLayout(new GridLayout(1, false));
		bGroup.setLayoutData(new GridData(GridData.FILL_VERTICAL));

		new NewButton().createNewButtons(bGroup, tableViewer, new INewElement() {

			public Object newElement(List<?> input, int pos) {
				ArrayList<ItemProperty> props = new ArrayList<ItemProperty>();

				props.add(new StandardItemProperty("id", "CHANGE_ME_ID", null)); //$NON-NLS-1$ //$NON-NLS-2$
				props.add(new StandardItemProperty("label", "CHANGE_ME_LABEL", null)); //$NON-NLS-1$ //$NON-NLS-2$
				props.add(new StandardItemProperty("latitude", "0", null)); //$NON-NLS-1$ //$NON-NLS-2$
				props.add(new StandardItemProperty("longitude", "0", null)); //$NON-NLS-1$ //$NON-NLS-2$

				StandardItem v = new StandardItem(props);
				MarkerDialog dialog = new MarkerDialog(Display.getDefault().getActiveShell());
				dialog.setValue(v, ModelUtils.getElementExpressionContext(null, value.getPnode()));
				if (dialog.open() == Window.OK)
					return v;
				return null;
			}
		});

		editButton = new EditButton<Item>();
		editButton.createEditButtons(bGroup, tableViewer, new EditElement());
		new DeleteButton().createDeleteButton(bGroup, tableViewer);
		new ListOrderButtons().createOrderButtons(bGroup, tableViewer);
	}

	private void buildTable(Composite composite) {
		table = new Table(composite, SWT.BORDER | SWT.SINGLE | SWT.FULL_SELECTION | SWT.V_SCROLL);
		table.setHeaderVisible(true);
		table.addMouseListener(new MouseListener() {

			@Override
			public void mouseUp(MouseEvent e) {
			}

			@Override
			public void mouseDown(MouseEvent e) {
			}

			@Override
			public void mouseDoubleClick(MouseEvent e) {
				editButton.push();
			}
		});

		tableViewer = new TableViewer(table);
		tableViewer.setContentProvider(new ListContentProvider());
		tableViewer.setLabelProvider(new TMarkerLabelProvider());
		// attachCellEditors(tableViewer, table);

		TableColumn[] column = new TableColumn[4];

		column[0] = new TableColumn(table, SWT.NONE);
		column[0].setText(Messages.MarkerPage_IdColumn);

		column[1] = new TableColumn(table, SWT.NONE);
		column[1].setText(Messages.MarkerPage_LabelColumn);
		
		column[2] = new TableColumn(table, SWT.NONE);
		column[2].setText(Messages.MarkerPage_LatitudeColumn);

		column[3] = new TableColumn(table, SWT.NONE);
		column[3].setText(Messages.MarkerPage_LongitudeColumn);

		fillTable(table);
		for (int i = 0, n = column.length; i < n; i++)
			column[i].pack();

		TableLayout tlayout = new TableLayout();
		tlayout.addColumnData(new ColumnWeightData(20, true));
		tlayout.addColumnData(new ColumnWeightData(30, true));
		tlayout.addColumnData(new ColumnWeightData(25, true));
		tlayout.addColumnData(new ColumnWeightData(25, true));
		table.setLayout(tlayout);
	}

	private void fillTable(Table table) {
		List<Item> props = new ArrayList<Item>();
		props.addAll(value.getMarkers());
		tableViewer.setInput(props);
	}

}
