package com.jaspersoft.studio.wizards;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.commands.Command;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;

import com.jaspersoft.studio.utils.UIUtils;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public abstract class JSSWizard extends Wizard {
	private IWizard parentWizard;
	private IWizardPage fallbackPage;
	private List<IWizard> childWizards = new ArrayList<IWizard>();

	public JSSWizard() {
		super();
	}

	public JSSWizard(IWizard parentWizard, IWizardPage fallbackPage) {
		this();
		this.parentWizard = parentWizard;
		this.fallbackPage = fallbackPage;
	}

	public IWizardPage hasNextPage(IWizardPage page) {
		IWizardPage wpage = super.getNextPage(page);
		if (wpage == null && parentWizard != null && fallbackPage != null) {
			return fallbackPage;
		}
		return wpage;
	}

	protected void addChild(IWizard w) {
		if (!childWizards.contains(w))
			childWizards.add(w);
	}

	@Override
	public IWizardPage getNextPage(IWizardPage page) {
		IWizardPage wpage = super.getNextPage(page);
		if (wpage == null && parentWizard != null && fallbackPage != null) {
			if (parentWizard instanceof JSSWizard)
				((JSSWizard) parentWizard).addChild(this);
			return fallbackPage;
		}
		return wpage;
	}

	@Override
	public boolean performFinish() {
		for (IWizard w : childWizards)
			w.performFinish();
		return true;
	}

	private JasperReportsConfiguration config;

	public void setConfig(JasperReportsConfiguration config) {
		this.config = config;
	}

	public JasperReportsConfiguration getConfig() {
		if (config == null && parentWizard instanceof JSSWizard)
			config = ((JSSWizard) parentWizard).getConfig();
		return config;
	}

	public void run(boolean fork, boolean cancelable, IRunnableWithProgress runnable) {
		try {
			getContainer().run(true, true, runnable);
		} catch (InvocationTargetException e) {
			UIUtils.showError(e.getCause());
		} catch (InterruptedException e) {
			UIUtils.showError(e.getCause());
		}
	}

	public void init(JasperReportsConfiguration jConfig) {
		setConfig(jConfig);
	}

	private List<Command> commands;

	public void removeCommand(Command command) {
		if (parentWizard != null && parentWizard instanceof JSSWizard)
			((JSSWizard) parentWizard).removeCommand(command);
		if (commands != null)
			commands.remove(command);
	}

	public void addCommand(Command command) {
		if (parentWizard != null && parentWizard instanceof JSSWizard)
			((JSSWizard) parentWizard).addCommand(command);
		if (commands == null)
			commands = new ArrayList<Command>();
		commands.add(command);
	}

	public List<Command> getCommands() {
		return commands;
	}
}
