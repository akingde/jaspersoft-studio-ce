package com.jaspersoft.studio.server.wizard.resource.page;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.wizard.WizardPageSupport;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.server.model.MResource;
import com.jaspersoft.studio.server.wizard.validator.EmptyStringValidator;
import com.jaspersoft.studio.utils.UIUtils;

public abstract class AResourcePage extends WizardPage {
	protected ANode parent;
	protected MResource res;
	protected DataBindingContext bindingContext;

	public AResourcePage(String pageName, ANode parent, MResource resource) {
		super(pageName);
		setTitle("Resource Editor");
		setDescription("Read and modify resource.");
		this.res = resource;
		this.parent = parent;
	}

	public void createControl(Composite parent) {
		bindingContext = new DataBindingContext();
		WizardPageSupport.create(this, bindingContext);

		TabFolder tabFolder = new TabFolder(parent, SWT.TOP);
		tabFolder.setLayoutData(new GridData(GridData.FILL_BOTH));

		createTabs(tabFolder);
		setControl(tabFolder);
	}

	protected void createTabs(TabFolder tabFolder) {
		createGeneralTab(tabFolder);
	}

	protected void createGeneralTab(TabFolder tabFolder) {
		TabItem item = new TabItem(tabFolder, SWT.NONE);
		item.setText("General");

		Composite composite = new Composite(tabFolder, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		item.setControl(composite);

		UIUtils.createLabel(composite, "Parent Folder");
		Text tparent = new Text(composite, SWT.BORDER | SWT.READ_ONLY);
		tparent.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		UIUtils.createLabel(composite, "Type");
		Text ttype = new Text(composite, SWT.BORDER | SWT.READ_ONLY);
		ttype.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		UIUtils.createLabel(composite, "Creation Date:");
		Text tcdate = new Text(composite, SWT.BORDER | SWT.READ_ONLY);
		tcdate.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		UIUtils.createSeparator(composite, 2);

		UIUtils.createLabel(composite, "ID");
		Text tid = new Text(composite, SWT.BORDER);
		tid.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		UIUtils.createLabel(composite, "Name");
		Text tname = new Text(composite, SWT.BORDER);
		tname.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		UIUtils.createLabel(composite, "Description");
		Text tdesc = new Text(composite, SWT.BORDER | SWT.MULTI | SWT.WRAP);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.minimumHeight = 100;
		tdesc.setLayoutData(gd);

		ResourceDescriptor rd = res.getValue();
		bindingContext.bindValue(SWTObservables.observeText(tparent, SWT.NONE),
				PojoObservables.observeValue(rd, "parentFolder"));

		bindingContext.bindValue(SWTObservables.observeText(tcdate, SWT.NONE),
				PojoObservables.observeValue(rd, "creationDate"));

		bindingContext.bindValue(SWTObservables.observeText(ttype, SWT.NONE),
				PojoObservables.observeValue(rd, "wsType"));

		bindingContext.bindValue(SWTObservables.observeText(tid, SWT.Modify),
				PojoObservables.observeValue(rd, "name"),
				new UpdateValueStrategy()
						.setAfterConvertValidator(new EmptyStringValidator()),
				null);

		bindingContext.bindValue(SWTObservables.observeText(tname, SWT.Modify),
				PojoObservables.observeValue(rd, "label"),
				new UpdateValueStrategy()
						.setAfterConvertValidator(new EmptyStringValidator()),
				null);
		bindingContext.bindValue(SWTObservables.observeText(tdesc, SWT.Modify),
				PojoObservables.observeValue(rd, "description"));

		tid.setEditable(rd.getIsNew());
		if (rd.getIsNew())
			rd.setLabel(rd.getName());
		bindingContext.updateTargets();
	}

}
