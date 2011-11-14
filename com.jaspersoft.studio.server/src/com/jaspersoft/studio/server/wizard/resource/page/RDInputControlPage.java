package com.jaspersoft.studio.server.wizard.resource.page;

import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.server.model.MInputControl;
import com.jaspersoft.studio.server.model.MListOfValues;
import com.jaspersoft.studio.server.model.MRQuery;
import com.jaspersoft.studio.server.model.MResource;
import com.jaspersoft.studio.server.properties.dialog.RepositoryDialog;
import com.jaspersoft.studio.utils.UIUtils;

public class RDInputControlPage extends AResourcePage {

	public RDInputControlPage(ANode parent, MInputControl resource) {
		super("rdinputcontrol", parent, resource);
		setTitle("Input Control");
		setDescription("Input Control");
	}

	@Override
	protected void createTabs(TabFolder tabFolder) {
		super.createTabs(tabFolder);
		createDatasourceTab(tabFolder);
	}

	protected void createDatasourceTab(TabFolder tabFolder) {
		TabItem item = new TabItem(tabFolder, SWT.NONE);
		item.setText("Input Control");

		Composite composite = new Composite(tabFolder, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		item.setControl(composite);

		new Label(composite, SWT.NONE);

		Composite cmp = new Composite(composite, SWT.NONE);
		cmp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		cmp.setLayout(new RowLayout());
		cmp.setBackground(parent.getBackground());

		Button bmand = new Button(cmp, SWT.CHECK);
		bmand.setText("Mandatory");

		Button bread = new Button(cmp, SWT.CHECK);
		bread.setText("Read Only");

		Button bvisible = new Button(cmp, SWT.CHECK);
		bvisible.setText("Visible");

		UIUtils.createLabel(composite, "Type");

		CCombo ctype = new CCombo(composite, SWT.BORDER | SWT.READ_ONLY);
		ctype.setItems(new String[] { "Boolean", "Single Value",
				"Single Select List of Values",
				"Single Select List of Values (Radio)",
				"Multi Select List of Values",
				"Multi Select List of Values (Checkbox)",
				"Single Select Query", "Single Select Query (Radio)",
				"Multi Select Query", "Multi Select Query (Checkbox)" });

		UIUtils.createLabel(composite, "Referenced List of values");

		cmp = new Composite(composite, SWT.NONE);
		cmp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		GridLayout layout = new GridLayout(2, false);
		layout.marginWidth = 0;
		cmp.setLayout(layout);
		cmp.setBackground(parent.getBackground());

		Text trefuri = new Text(cmp, SWT.BORDER | SWT.READ_ONLY);
		trefuri.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Button bbrowse = new Button(cmp, SWT.PUSH);
		bbrowse.setText("...");
		bbrowse.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {
				Shell shell = Display.getDefault().getActiveShell();
				RepositoryDialog rd = new RepositoryDialog(shell, res.getRoot()) {

					@Override
					public boolean isResourceCompatible(MResource r) {
						return (r instanceof MListOfValues)
								|| (r instanceof MRQuery);
					}

				};
				if (rd.open() == Dialog.OK) {
					MResource rs = rd.getResource();
					if (rs != null) {
						res.getValue().setReferenceUri(
								rs.getValue().getUriString());
						bindingContext.updateTargets();
					}
				}
			}

		});

		bindingContext.bindValue(SWTObservables
				.observeSingleSelectionIndex(ctype), PojoObservables
				.observeValue(getProxy(res.getValue()), "controlType"));

		bindingContext.bindValue(SWTObservables.observeSelection(bmand),
				PojoObservables.observeValue(res.getValue(), "mandatory"));
		bindingContext.bindValue(SWTObservables.observeSelection(bread),
				PojoObservables.observeValue(res.getValue(), "readOnly"));
		bindingContext.bindValue(SWTObservables.observeSelection(bvisible),
				PojoObservables.observeValue(res.getValue(), "visible"));
	}

	private ShiftMapProxy getProxy(ResourceDescriptor rd) {
		proxy.setResourceDescriptor(rd);
		return proxy;
	}

	private ShiftMapProxy proxy = new ShiftMapProxy();

	class ShiftMapProxy {
		private ResourceDescriptor rd;
		private final int[] shift = new int[] { 1, 2, 3, 8, 6, 10, 4, 9, 7, 11 };

		public void setResourceDescriptor(ResourceDescriptor rd) {
			this.rd = rd;
		}

		public void setControlType(int type) {
			rd.setControlType((byte) shift[type]);
		}

		public int getControlType() {
			for (int i = 0; i < shift.length; i++)
				if (shift[i] == rd.getControlType())
					return i;
			return -1;
		}
	}
}
