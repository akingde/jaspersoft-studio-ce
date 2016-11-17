/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.customvisualization.creation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

import com.jaspersoft.studio.components.customvisualization.messages.Messages;

import net.sf.jasperreports.eclipse.ui.util.PersistentLocationDialog;

/**
 * This dialog show the output of a compilation process of a custom visualization component
 * by printing it inside a not editable text area. To avoid to block the dialog by reading
 * the output inside the graphic thread a queue is used. This queue is filled and read by two
 * different thread. The thread that read it, if there is some content to print, then lunch a
 * display thread to print what was in the queue inside the graphic control
 * All the operations on the queue are synchronized
 * 
 * @author Orlandin Marco
 *
 */
public class CompileDialog extends PersistentLocationDialog {

	/**
	 * Area where the output is printed
	 */
	private StyledText textArea;
	
	/**
	 * The process where the compilation is executed
	 */
	private Process process;
	
	/**
	 * The message queue
	 */
	private List<String> messageQueue = new ArrayList<String>();
	
	/**
	 * Boolean flag to say that the compilation is finished
	 */
	private boolean compilationEnded = false;
	
	/**
	 * The thread that read the output from the process and fill the queue
	 */
	private Runnable readProcessOutputThread = new Runnable() {
		
		@Override
		public void run() {
			String newLine = System.getProperty("line.separator"); //$NON-NLS-1$
			pushMessage(Messages.CompileDialog_startCompilation + newLine);
			BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));

			BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));

			// read the output from the command
			String s = null;
			try{	
				while ((s = stdInput.readLine()) != null) {
					pushMessage(s + newLine);
				}
			} catch(Exception ex){
				pushMessage(ex.getMessage());
			}

			// read any errors from the attempted command
			try{
				while ((s = stdError.readLine()) != null) {
					pushMessage(s + newLine);
				}
			} catch (Exception ex){
				pushMessage(ex.getMessage());
			}
			pushMessage(Messages.CompileDialog_endCompilation);
			compilationEnded = true;

		}
	};
	
	/**
	 * The thread that read the queue and, if there is content
	 * inside it, print this content trough a UI sync exec
	 */
	private Runnable checkForMessages = new Runnable() {
		
		@Override
		public void run() {
			while(!compilationEnded || hasMessages()){
				while(hasMessages()){
					final String message = popMessage();
					if (message != null) {
							getShell().getDisplay().syncExec(new Runnable() {
							
							@Override
							public void run() {
								textArea.append(message);
							}
						});
					}
				}
				//If there isn't something to print then wait a bit
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			//Compilation finished and all messages printed, enable the ok button
			//Need to change the button state inside the graphic thread
			getShell().getDisplay().syncExec(new Runnable() {
				@Override
				public void run() {
					getButton(IDialogConstants.OK_ID).setEnabled(true);
				}
			});
		}
	};
	
	/**
	 * Create the dialog
	 * 
	 * @param parentShell a shell
	 * @param process the process where the command is executed
	 */
	public CompileDialog(Shell parentShell, Process process) {
		super(parentShell);
		this.process = process;
	}
	
	/**
	 * Set the title of the shell
	 */
	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText(Messages.CompileDialog_dialogTitle);
	}
	
	/**
	 * Create the text area of the dialog
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite)super.createDialogArea(parent);
		textArea = new StyledText(container, SWT.READ_ONLY | SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
		textArea.setBackground(container.getBackground());
		textArea.setEditable(false);
		GridData textData = new GridData(GridData.FILL_BOTH);
		textData.widthHint = 500;
		textData.heightHint = 200;
		textArea.setLayoutData(textData);
		return container;
	}
	
	/**
	 * When the contents are created start the threads to read
	 * and print the process output
	 */
	@Override
	protected Control createContents(Composite parent) {
		Control contents = super.createContents(parent);
		new Thread(readProcessOutputThread).start();
		new Thread(checkForMessages).start();
		return contents;
	}
	
	/**
	 * Create only the ok button
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,true);
		getButton(IDialogConstants.OK_ID).setEnabled(false);
	}

	/**
	 * Add a message to the queue
	 * 
	 * @param message the message
	 */
	private void pushMessage(String message){
		synchronized (messageQueue) {
			messageQueue.add(message);
		}
	}
	
	/**
	 * Read the older message from the queue and remove it
	 * 
	 * @return the read message or null if the queue was empty
	 */
	private String popMessage(){
		synchronized (messageQueue) {
			if (messageQueue.size()>0)
				return messageQueue.remove(0);
			return null;
		}
	}
	
	/**
	 * Check if the queue has messages
	 * 
	 * @return true if there are messages on the queue, false
	 * otherwise
	 */
	private boolean hasMessages(){
		synchronized (messageQueue) {
			return !messageQueue.isEmpty();
		}
	}
}
