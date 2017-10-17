/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.background.action;

import java.util.List;

import org.eclipse.gef.commands.Command;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.background.MBackgrounImage;
import com.jaspersoft.studio.editor.action.ACachedSelectionAction;
import com.jaspersoft.studio.property.SetValueCommand;

/**
 * Action to set the transparency of the background images from a contextual
 * menu. It can be created with various level of fixed transparencies
 * 
 * @author Orlandin Marco
 * 
 */
public class BackgroundTransparencyAction extends ACachedSelectionAction {
  
	/**
	 * Base id used to create the id of the action along with the transparency level
	 */
  private static final String BASE_ID = "BackgroundTransaparencyTo"; //$NON-NLS-1$
  
  /**
   * Id to use on the constructor of the action to have a transparency of the 5%
   */
  public static final String TRANSPARENCY_5 = BASE_ID + "5"; //$NON-NLS-1$
  
  /**
   * Id to use on the constructor of the action to have a transparency of the 10%
   */
  public static final String TRANSPARENCY_10 = BASE_ID + "10"; //$NON-NLS-1$
  
  /**
   * Id to use on the constructor of the action to have a transparency of the 15%
   */
  public static final String TRANSPARENCY_15 = BASE_ID + "15"; //$NON-NLS-1$
  
  /**
   * Id to use on the constructor of the action to have a transparency of the 20%
   */
  public static final String TRANSPARENCY_20 = BASE_ID + "20"; //$NON-NLS-1$
  
  /**
   * Id to use on the constructor of the action to have a transparency of the 25%
   */
  public static final String TRANSPARENCY_25 = BASE_ID + "25"; //$NON-NLS-1$
  
  /**
   * Id to use on the constructor of the action to have a transparency of the 30%
   */
  public static final String TRANSPARENCY_30 = BASE_ID + "30"; //$NON-NLS-1$
  
  /**
   * Id to use on the constructor of the action to have a transparency of the 40%
   */
  public static final String TRANSPARENCY_40 = BASE_ID + "40"; //$NON-NLS-1$
  
  /**
   * Id to use on the constructor of the action to have a transparency of the 50%
   */
  public static final String TRANSPARENCY_50 = BASE_ID + "50"; //$NON-NLS-1$
  
  /**
   * Id to use on the constructor of the action to have a transparency of the 75%
   */
  public static final String TRANSPARENCY_75 = BASE_ID + "75"; //$NON-NLS-1$
  
  /**
   * Id to use on the constructor of the action to have a transparency of the 100%
   */
  public static final String TRANSPARENCY_100 = BASE_ID + "100"; //$NON-NLS-1$
	
  /**
   * Transparency value of the current action
   */
  private float transparencyValue;
  
  /**
   * Construction of the action
   * 
   * @param part workbench part
   * @param ID id of the action, must be one of the static id provided by the 
   * BackgroundTransparencyAction class. Otherwise it will be used a a transparency
   *  100% action
   */
	public BackgroundTransparencyAction(IWorkbenchPart part, String ID) {
		super(part);
		setLazyEnablementCalculation(true);
		if (TRANSPARENCY_5.equals(ID)){
			setId(TRANSPARENCY_5);
			transparencyValue = 0.05f;
		} else if (TRANSPARENCY_10.equals(ID)){
			setId(TRANSPARENCY_10);
			transparencyValue = 0.10f;
		}  else if (TRANSPARENCY_15.equals(ID)){
			setId(TRANSPARENCY_15);
			transparencyValue = 0.15f;
		}  else if (TRANSPARENCY_20.equals(ID)){
			setId(TRANSPARENCY_20);
			transparencyValue = 0.20f;
		}  else if (TRANSPARENCY_25.equals(ID)){
			setId(TRANSPARENCY_25);
			transparencyValue = 0.25f;
		}  else if (TRANSPARENCY_30.equals(ID)){
			setId(TRANSPARENCY_30);
			transparencyValue = 0.30f;
		}  else if (TRANSPARENCY_40.equals(ID)){
			setId(TRANSPARENCY_40);
			transparencyValue = 0.40f;
		}  else if (TRANSPARENCY_50.equals(ID)){
			setId(TRANSPARENCY_50);
			transparencyValue = 0.50f;
		}  else if (TRANSPARENCY_75.equals(ID)){
			setId(TRANSPARENCY_75);
			transparencyValue = 0.75f;
		}  else {
			setId(TRANSPARENCY_100);
			transparencyValue = 1.0f;
		}  
		String percentage = String.valueOf(Math.round(transparencyValue*100));
		setText(percentage+"%");
	}
	
	@Override
	protected void init() {
		super.init();
		setEnabled(false);
	}

	@Override
	protected Command createCommand() {
		List<Object> background = editor.getSelectionCache().getSelectionModelForType(MBackgrounImage.class);
		if (background.isEmpty()) return null;
		SetValueCommand command = new SetValueCommand();
		command.setTarget((MBackgrounImage)background.get(0));
		command.setPropertyId(MBackgrounImage.PROPERTY_ALPHA);
		command.setPropertyValue(transparencyValue);
		return command;
	}
}
