/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.svgimporter.action;

import java.io.File;
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
import com.jaspersoft.studio.editor.layout.ChangeLayoutCommand;
import com.jaspersoft.studio.editor.layout.FreeLayout;
import com.jaspersoft.studio.model.band.MBand;
import com.jaspersoft.studio.svgimporter.ConsolePdfConverter;
import com.jaspersoft.studio.svgimporter.SVGDocumentLoader;
import com.jaspersoft.studio.svgimporter.messages.Messages;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.engine.design.JRDesignBand;
import net.sf.jasperreports.engine.design.JRDesignElement;

/**
 * Action to import the content of a PDF or of an SVG into the current band.
 * If the file is a PDF it is tried a conversion into SVG using inkscape, because
 * the PDF is not supported natively
 * 
 * @author Orlandin Marco
 *
 */
public class ImportContentAction extends ACachedSelectionAction {
	
	/** The Constant ID. */
	public static final String ID = "importcontent"; //$NON-NLS-1$
	
	/**
	 * The command used to load the file and store the element into the band
	 * 
	 * @author Orlandin Marco
	 *
	 */
	private class ImportElementsCommand extends Command{
		
		/**
		 * The element that will be created in the band
		 */
		private List<JRDesignElement> elementsToCreate = new ArrayList<>();
		
		/**
		 * The band where the element will be created
		 */
		private JRDesignBand targetBand;
		
		/**
		 * The model for the band
		 */
		private MBand band;
		
		private Command createResourceCommand;
		
		private ChangeLayoutCommand changeLayoutCommand;
		
		/**
		 * The model for the band where the element will be stored
		 * 
		 * @param band a not null band
		 */
		public ImportElementsCommand(MBand band) {
			this.band = band;
			this.targetBand = band.getValue();
			this.changeLayoutCommand = new ChangeLayoutCommand(band, new FreeLayout());
		}
		
		@Override
		public void execute() {
			elementsToCreate.clear();
			FileDialog dialog = new FileDialog(Display.getDefault().getActiveShell(), SWT.OPEN);
			dialog.setFilterExtensions(new String [] {"*.svg", "*.pdf"}); //$NON-NLS-1$ //$NON-NLS-2$
			String result = dialog.open();
			if (result != null) {
				JasperReportsConfiguration jConfig = band.getJasperConfiguration();
				try {
					File resultFile = new File(result);
					String ext = FilenameUtils.getExtension(resultFile.getName());
					File fileToConvert = null;
					//check the extension to convert the file in case of PDF
					if (ext.equalsIgnoreCase("pdf")) { //$NON-NLS-1$
						ConsolePdfConverter converter = new ConsolePdfConverter(Arrays.asList(resultFile));
						List<File> convertedFiles = converter.getConvertedFiles();
						if (!convertedFiles.isEmpty()) {
							fileToConvert = convertedFiles.get(0);
						}
					} else {
						fileToConvert = resultFile;
					}
					if (fileToConvert != null) {
						SVGDocumentLoader loader = new SVGDocumentLoader(fileToConvert.toURI().toString(), jConfig);
						elementsToCreate.addAll(loader.getJRElements());
						//set the layout to free layout
						changeLayoutCommand.execute();
						//execute the command to create the resources
						createResourceCommand = loader.getResourceCreationCommands();
						if (createResourceCommand != null) {
							createResourceCommand.execute();
						}
						//add the elements to the band
						for(JRDesignElement element : elementsToCreate) {
							targetBand.addElement(element);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			 }
		}
		
		@Override
		public void undo() {
			if (createResourceCommand != null) {
				createResourceCommand.undo();
			}
			for(JRDesignElement element : elementsToCreate) {
				targetBand.removeElement(element);
			}
			changeLayoutCommand.undo();
		}
		
		@Override
		public void redo() {
			changeLayoutCommand.execute();
			if (createResourceCommand != null) {
				createResourceCommand.redo();
			}
			for(JRDesignElement element : elementsToCreate) {
				targetBand.addElement(element);
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
		setText(Messages.ImportContentAction_actionName);
		setToolTipText(Messages.ImportContentAction_actionTooltip);
		setId(ID);
	}
	
	@Override
	protected boolean calculateEnabled() {
		List<Object> bands = editor.getSelectionCache().getSelectionModelForType(MBand.class);
		return bands.size() == 1;
	}
	
	/**
	 * Create the command if there is at least a band selected
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
