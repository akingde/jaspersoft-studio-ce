/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.section.widgets;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.bindings.Binding;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.internal.keys.BindingService;
import org.eclipse.ui.keys.IBindingService;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.utils.inputhistory.InputHistoryCache;

public abstract class AHistorySPropertyWidget<K extends IPropertyDescriptor> extends ASPropertyWidget<K> {

	public static final String HIST_PREFIX = "asproperty.";
	protected CustomAutoCompleteField autocomplete;

	public AHistorySPropertyWidget(Composite parent, AbstractSection section, K pDescriptor) {
		super(parent, section, pDescriptor);
	}

	protected abstract Text getTextControl();

	protected Object getHistoryKey() {
		return HIST_PREFIX + pDescriptor.getId();
	}

	@Override
	protected void handleFocusLost() {
		if (autocomplete != null) {
			autocomplete.setProposals(InputHistoryCache.get(null));
			if (getTextControl() != null)
				InputHistoryCache.put(getHistoryKey(), getTextControl().getText());
		}
		super.handleFocusLost();
		activateHandlers();
	}

	@Override
	protected void handleFocusGained() {
		if (autocomplete != null)
			autocomplete.setProposals(InputHistoryCache.get(getHistoryKey()));
		super.handleFocusGained();
		deactivateHandlers();
	}

	private void activateHandlers() {
		if (getBindingService() == null)
			return;
		ICommandService cs = (ICommandService)PlatformUI.getWorkbench().getService(ICommandService.class);
		if (cs != null)
			bindingService.readRegistryAndPreferences(cs);
	}

	private BindingService bindingService;

	private BindingService getBindingService() {
		if (bindingService == null)
			bindingService = (BindingService) PlatformUI.getWorkbench().getService(IBindingService.class);
		return bindingService;
	}

	public void deactivateHandlers() {
		if (getBindingService() == null)
			return;
		List<Binding> bindings = new ArrayList<Binding>();
		for (Binding b : bindingService.getBindings()) {
			if (b.getParameterizedCommand() == null)
				continue;
			String id = b.getParameterizedCommand().getId();
			if (id != null && (id.equals("org.eclipse.ui.edit.undo") || id.equals("org.eclipse.ui.edit.redo")))
				bindings.add(b);
		}
		for (Binding b : bindings)
			bindingService.removeBinding(b);
	}
}
