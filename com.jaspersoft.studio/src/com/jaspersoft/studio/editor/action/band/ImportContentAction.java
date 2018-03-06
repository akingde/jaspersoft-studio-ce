/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.action.band;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.eclipse.gef.commands.Command;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.editor.action.ACachedSelectionAction;
import com.jaspersoft.studio.editor.action.imports.svg.ConsolePdfConverter;
import com.jaspersoft.studio.editor.action.imports.svg.SVGDocumentLoader;
import com.jaspersoft.studio.model.band.MBand;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.engine.design.JRDesignBand;
import net.sf.jasperreports.engine.design.JRDesignElement;

public class ImportContentAction extends ACachedSelectionAction {
	
	/** The Constant ID. */
	public static final String ID = "importcontent"; //$NON-NLS-1$
	
	private class ImportElementsCommand extends Command{
		
		private List<JRDesignElement> elementsToCreate = new ArrayList<>();
		
		private JRDesignBand targetBand;
		
		private MBand band;
		
		public ImportElementsCommand(MBand band) {
			this.band = band;
			this.targetBand = band.getValue();
		}
		
		@Override
		public void execute() {
			elementsToCreate.clear();
			FileDialog dialog = new FileDialog(Display.getDefault().getActiveShell(), SWT.OPEN);
			dialog.setFilterExtensions(new String [] {"*.svg", "*.pdf"});
			String result = dialog.open();
			if (result != null) {
				JasperReportsConfiguration jConfig = band.getJasperConfiguration();
				try {
					File resultFile = new File(result);
					String ext = FilenameUtils.getExtension(resultFile.getName());
					File fileToConvert = null;
					if (ext.equalsIgnoreCase("pdf")) {
						ConsolePdfConverter converter = new ConsolePdfConverter(Arrays.asList(resultFile));
						List<File> convertedFiles = converter.getConvertedFiles();
						if (!convertedFiles.isEmpty()) {
							fileToConvert = convertedFiles.get(0);
						}
					} else {
						fileToConvert = resultFile;
					}
					if (fileToConvert != null) {
						List<JRDesignElement> newElements = new SVGDocumentLoader(fileToConvert.toURI().toString(), jConfig).run();
						if (!newElements.isEmpty()) {
							elementsToCreate.addAll(newElements);
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			 }
			for(JRDesignElement element : elementsToCreate) {
				targetBand.addElement(element);
			}
		}
		
		@Override
		public void undo() {
			for(JRDesignElement element : elementsToCreate) {
				targetBand.removeElement(element);
			}
		}
		
		@Override
		public void redo() {
			for(JRDesignElement element : elementsToCreate) {
				targetBand.removeElement(element);
			}
		}
	}

	/**
	 * Constructs a <code>CreateAction</code> using the specified part.
	 * 
	 * @param part
	 *          The part for this action
	 */
	public ImportContentAction(IWorkbenchPart part) {
		super(part);
		setText("Import Contents From File");
		setToolTipText("Import the content of this band from a PDF or SVG file");
		setId(ID);
	}
	
	@Override
	protected boolean calculateEnabled() {
		List<Object> bands = editor.getSelectionCache().getSelectionModelForType(MBand.class);
		return bands.size() == 1;
	}
	
	/**
	 * Create the commands to create a new text field similar to the static text selected
	 * and to delete the static text9
	 * 
	 * @return a compound command with two commands in it, one to remove the selected static texts, and one to 
	 * create in their place similar text fields
	 */
	@Override
	protected Command createCommand() {
		List<Object> bands = editor.getSelectionCache().getSelectionModelForType(MBand.class);
		if (bands.size() == 1) {
			MBand bandNode = (MBand)bands.get(0);
			JSSCompoundCommand command = new JSSCompoundCommand(bandNode);
			command.add(new ImportElementsCommand(bandNode));
			return command;
		}
		return null;
	}

}
