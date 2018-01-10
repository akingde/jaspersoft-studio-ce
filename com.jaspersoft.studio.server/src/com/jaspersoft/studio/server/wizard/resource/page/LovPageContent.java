/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.wizard.resource.page;

import java.util.List;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ListItem;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.server.messages.Messages;
import com.jaspersoft.studio.server.model.AMResource;
import com.jaspersoft.studio.server.wizard.resource.APageContent;
import com.jaspersoft.studio.swt.widgets.table.DeleteButton;
import com.jaspersoft.studio.swt.widgets.table.EditButton;
import com.jaspersoft.studio.swt.widgets.table.IEditElement;
import com.jaspersoft.studio.swt.widgets.table.INewElement;
import com.jaspersoft.studio.swt.widgets.table.ListContentProvider;
import com.jaspersoft.studio.swt.widgets.table.ListOrderButtons;
import com.jaspersoft.studio.swt.widgets.table.NewButton;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.Misc;

public class LovPageContent extends APageContent {

	private static final String VALUE = "VALUE";
	private static final String KEY = "KEY";

	public LovPageContent(ANode parent, AMResource resource, DataBindingContext bindingContext) {
		super(parent, resource, bindingContext);
	}

	public LovPageContent(ANode parent, AMResource resource) {
		super(parent, resource);
	}

	@Override
	public String getPageName() {
		return "com.jaspersoft.studio.server.page.lov"; //$NON-NLS-1$
	}

	@Override
	public String getName() {
		return Messages.LovPageContent_lov;
	}

	@Override
	public void dispose() {
		res.getValue().setListOfValues((List) tableViewer.getInput());
		super.dispose();
	}

	private final class TLabelProvider extends LabelProvider implements ITableLabelProvider {

		public String getColumnText(Object element, int columnIndex) {
			ListItem dto = (ListItem) element;
			if (dto != null)
				switch (columnIndex) {
				case 0:
					return dto.getLabel();
				case 1:
					if (dto.getValue() != null)
						return dto.getValue().toString();
				}
			return ""; //$NON-NLS-1$
		}

		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}
	}

	private Table table;
	private TableViewer tableViewer;

	public Control createContent(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));

		buildTable(composite);

		Composite bGroup = new Composite(composite, SWT.NONE);
		bGroup.setLayout(new GridLayout(1, false));
		bGroup.setLayoutData(new GridData(GridData.FILL_VERTICAL));
		bGroup.setBackground(parent.getBackground());

		NewButton bnew = new NewButton();
		bnew.createNewButtons(bGroup, tableViewer, new INewElement() {

			public Object newElement(List<?> input, int pos) {
				StringValueDialog d = new StringValueDialog(UIUtils.getShell(), getName(), "");
				if (d.open() == Dialog.OK) {
					ListItem li = new ListItem();
					li.setLabel(d.getKey());
					li.setValue(d.getValue());
					return li;
				}
				return null;
			}

			private String getName() {
				String name = "name";
				if (exists(name))
					for (int i = 0; i < 1000; i++) {
						if (!exists(name + "_" + i))
							return name + "_" + i;
					}
				return name;
			}

		});
		EditButton<ListItem> bedit = new EditButton<ListItem>();
		bedit.createEditButtons(bGroup, tableViewer, new IEditElement<ListItem>() {

			@Override
			public void editElement(List<ListItem> input, int pos) {
				ListItem li = input.get(pos);
				StringValueDialog d = new StringValueDialog(UIUtils.getShell(), li.getLabel(), (String) li.getValue());
				if (d.open() == Dialog.OK) {
					li.setLabel(d.getKey());
					li.setValue(d.getValue());
				}
			}
		});
		bedit.editOnDoubleClick();
		DeleteButton bdel = new DeleteButton();
		bdel.createDeleteButton(bGroup, tableViewer);

		ListOrderButtons border = new ListOrderButtons();
		border.createOrderButtons(bGroup, tableViewer);

		tableViewer.setInput(res.getValue().getListOfValues());
		rebind();
		return composite;
	}

	@Override
	protected void rebind() {

	}

	private void buildTable(Composite composite) {
		table = new Table(composite, SWT.BORDER | SWT.SINGLE | SWT.FULL_SELECTION);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.minimumHeight = 200;
		table.setLayoutData(gd);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		tableViewer = new TableViewer(table);
		attachContentProvider(tableViewer);
		attachLabelProvider(tableViewer);

		TableLayout tlayout = new TableLayout();
		tlayout.addColumnData(new ColumnWeightData(50));
		tlayout.addColumnData(new ColumnWeightData(50));
		table.setLayout(tlayout);

		TableColumn[] column = new TableColumn[2];
		column[0] = new TableColumn(table, SWT.NONE);
		column[0].setText("Name");

		column[1] = new TableColumn(table, SWT.NONE);
		column[1].setText("Value");

		for (int i = 0, n = column.length; i < n; i++)
			column[i].pack();

	}

	private void attachLabelProvider(TableViewer viewer) {
		viewer.setLabelProvider(new TLabelProvider());
	}

	private void attachContentProvider(TableViewer viewer) {
		viewer.setContentProvider(new ListContentProvider());
	}

	class StringValueDialog extends Dialog {
		private String key;
		private String value;

		protected StringValueDialog(Shell parentShell, String key, String value) {
			super(parentShell);
			this.key = key;
			this.value = value;
		}

		public String getKey() {
			return key;
		}

		public String getValue() {
			return value;
		}

		@Override
		protected Control createDialogArea(Composite parent) {
			Composite cmp = (Composite) super.createDialogArea(parent);
			cmp.setLayout(new GridLayout(2, false));

			new Label(cmp, SWT.NONE).setText("Name");

			final Text txt = new Text(cmp, SWT.BORDER);
			GridData gd = new GridData(GridData.FILL_HORIZONTAL);
			gd.widthHint = 150;
			txt.setLayoutData(gd);
			txt.setText(Misc.nvl(key));
			txt.addModifyListener(new ModifyListener() {

				@Override
				public void modifyText(ModifyEvent e) {
					key = txt.getText();
					getButton(OK).setEnabled(!Misc.isNullOrEmpty(key));
				}
			});

			new Label(cmp, SWT.NONE).setText("Value");

			final Text vtxt = new Text(cmp, SWT.BORDER);
			gd = new GridData(GridData.FILL_HORIZONTAL);
			gd.widthHint = 150;
			vtxt.setLayoutData(gd);
			vtxt.setText(Misc.nvl(value));
			vtxt.addModifyListener(new ModifyListener() {

				@Override
				public void modifyText(ModifyEvent e) {
					value = vtxt.getText();
					getButton(OK).setEnabled(!Misc.isNullOrEmpty(value));
				}
			});

			return cmp;
		}
	}

	private boolean exists(String value) {
		List<ListItem> v = (List<ListItem>) tableViewer.getInput();
		for (ListItem li : v) {
			if (li.getLabel().equals(value))
				return true;
		}
		return false;
	}

	@Override
	public String getHelpContext() {
		return "com.jaspersoft.studio.doc.editListOfValue";
	}
}
