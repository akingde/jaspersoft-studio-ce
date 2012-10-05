package com.jaspersoft.studio.data.jdbc;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.data.messages.Messages;
import com.jaspersoft.studio.data.querydesigner.sql.SQLLineStyler;
import com.jaspersoft.studio.data.ui.SimpleQueryWizardDataEditorComposite;

public class JDBCWizardDataEditorComposite extends SimpleQueryWizardDataEditorComposite {

	private SQLLineStyler lineStyler = new SQLLineStyler();
	
	public JDBCWizardDataEditorComposite(Composite parent, WizardPage page, JDBCDataAdapterDescriptor dataAdapterDescriptor) {
	
		super(parent, page);
		setQueryLanguage("sql"); //$NON-NLS-1$
		setTitle(Messages.JDBCWizardDataEditorComposite_Title);
		
		this.setDataAdapterDescriptor(dataAdapterDescriptor);
		
		styledText.addLineStyleListener(lineStyler);
		styledText.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				lineStyler.parseBlockComments(styledText.getText());
			}
		});
	}
		
}
