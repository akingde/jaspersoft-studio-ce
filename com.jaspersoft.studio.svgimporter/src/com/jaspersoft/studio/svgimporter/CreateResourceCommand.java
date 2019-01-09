/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.svgimporter;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.io.FilenameUtils;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.svgimporter.SVGDocumentLoader;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignImage;

/**
 * Command used to move an image resource from the temp folder to the workspace, in 
 * the parent folder of a specific project. It will also set the expression of 
 * the {@link JRDesignImage } to use the correct expression, the name of the 
 * resource in the workspace
 * 
 * @author Orlandin Marco
 *
 */
public final class CreateResourceCommand extends Command{
	  
	/**
	 * the resource in the temp folder 
	 */
	 private File tempResource;
	 
	 /**
	  * the {@link JRDesignImage} where the image is displayed
	  */
	 private JRDesignImage targetJRElement;
	 
	 /**
	  * The {@link JasperReportsConfiguration} of the report where the {@link JRDesignImage} will be placed
	  */
	 private JasperReportsConfiguration jConfig;
	  
	 /**
	  * The file created in the workspace, can be used for the undo
	  */
	 private IFile destFile = null;
	  
	 /**
	  * Create the command
	  * 
	  * @param tempResource the resource in the temp folder 
	  * @param targetJRElement the {@link JRDesignImage} where the image is displayed
	  * @param jConfig The {@link JasperReportsConfiguration} of the report where the {@link JRDesignImage} will be placed
	  */
	 public CreateResourceCommand(File tempResource, JRDesignImage targetJRElement, JasperReportsConfiguration jConfig) {
		  this.tempResource = tempResource;
		  this.targetJRElement = targetJRElement;
		  this.jConfig = jConfig;
	 }
	 
	 /**
	  * Copy the resource from the temp folder to the workspace, if available it used the same 
	  * name, otherwise it compute a new unique name. It will set also in the related {@link JRDesignImage} 
	  * the expression to reference the resource
	  */
	 @Override
	 public void execute() {
		  IFile mfile = (IFile) jConfig.get(FileUtils.KEY_FILE);
		  IContainer parent = mfile.getParent();
		  int counter = 1;
		  String filename = tempResource.getName();
		  destFile = parent.getFile(new Path(filename));
		  String extension = FilenameUtils.getExtension(filename);
		  while(destFile.exists()) {
			  filename = SVGDocumentLoader.IMPORTED_IMAGE_PREFIX + counter + "." + extension; //$NON-NLS-1$
			  parent.getFile(new Path(filename));
			  counter++;
		  }
		  targetJRElement.setExpression(new JRDesignExpression("\"" + destFile.getName() + "\"")); //$NON-NLS-1$ //$NON-NLS-2$);
		  FileInputStream fileInputStream = null;
		  try {
			fileInputStream = new FileInputStream(tempResource);
			destFile.create(fileInputStream, true, new NullProgressMonitor());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			FileUtils.closeStream(fileInputStream);
		}
	}
	  
	/**
	 * delete the resource from the workspace
	 */
	@Override
	public void undo() {
		if (destFile != null) {
			try {
				destFile.delete(true, new NullProgressMonitor());
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
	}
  }