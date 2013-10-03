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

package com.jaspersoft.studio.translation;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.wizard.WizardDialog;

import com.jaspersoft.studio.translation.wizard.GenerateFragmentWizard;
import com.jaspersoft.translation.action.ProvideSelectedTranslation;
import com.jaspersoft.translation.resources.TranslationInformation;

public class CreateTranslationFragmentCommand implements IHandler {
	
	/**
	 * 
	 * 
	 * @param baseInfo
	 * @param qualifiedVersion
	 * @param vendorName
	 * @return
	 */
	public static ExtendedTranslationInformation generateExtendedInfo(TranslationInformation baseInfo, String qualifiedVersion, String vendorName){
		ExtendedTranslationInformation extendedInfo = new ExtendedTranslationInformation(baseInfo);
		String qualifiedName = baseInfo.getPluginName()+"_translation";
		String version = Platform.getBundle(baseInfo.getPluginName()).getHeaders().get("Bundle-Version");
		version = version.replaceAll("\\.qualifier", "");
		extendedInfo.setBundleVersion(qualifiedVersion);
		extendedInfo.setBundleProducer(vendorName);
		extendedInfo.setBundleName(qualifiedName);
		extendedInfo.setHostPluginName(baseInfo.getPluginName());
		extendedInfo.setHostPluginVersion(version);
		return extendedInfo;
	}
	
	private List<ExtendedTranslationInformation> generateExtendedInfos(List<TranslationInformation> baseInfos){
		String qualifiedVersion = FragmentCreationUtil.generateQualifier();
		String vendorName = System.getProperty("user.name"); 
		List<ExtendedTranslationInformation> result = new ArrayList<ExtendedTranslationInformation>();
		for(TranslationInformation baseInfo : baseInfos){
			result.add(generateExtendedInfo(baseInfo, qualifiedVersion, vendorName));
		}
		return result;
	}

	/**
	 * Open the wizard to create and export the translation fragments
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ProvideSelectedTranslation translationCommand = new ProvideSelectedTranslation();
		List<TranslationInformation> translations = translationCommand.execute();
		List<ExtendedTranslationInformation> extendedInfo = generateExtendedInfos(translations);
		
		GenerateFragmentWizard wizard = new GenerateFragmentWizard(extendedInfo);
		WizardDialog dialog = new WizardDialog(UIUtils.getShell(), wizard);
		dialog.create();
		UIUtils.resizeAndCenterShell(dialog.getShell(), 800,  600);
		dialog.open();
		return null;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean isHandled() {
		return true;
	}

	@Override
	public void removeHandlerListener(IHandlerListener handlerListener) {}
	
	@Override
	public void addHandlerListener(IHandlerListener handlerListener) {
	}

	@Override
	public void dispose() {
	}
	
}
