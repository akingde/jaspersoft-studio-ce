/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.xml.outline;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.text.DefaultPositionUpdater;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IPositionUpdater;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.ui.views.contentoutline.ContentOutlinePage;
import org.xml.sax.helpers.LocatorImpl;

import com.jaspersoft.studio.editor.xml.xml.XMLElement;
import com.jaspersoft.studio.editor.xml.xml.XMLParser;
import com.jaspersoft.studio.utils.ModelUtils;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

 
public class EditorContentOutlinePage extends ContentOutlinePage
{
	
	/**
	 * This thread parse the XML in background, so it doesn't block
	 * the UI to desierialize the XML. If at the end the thread is not
	 * cancelled the method to update the tree is called
	 * 
	 * @author Orlandin Marco
	 *
	 */
	private final class XMLLoaderJob implements Runnable {
		
		private IProgressMonitor monitor;
		
		public XMLLoaderJob() {
			monitor = new NullProgressMonitor();
		}

		@Override
		public void run() {
    	IDocument document = getDocument(input);
    	XMLElement newRoot = null;
			if (document != null && !monitor.isCanceled()) {
				document.addPositionCategory(TAG_POSITIONS);
				document.addPositionUpdater(positionUpdater);
				newRoot = parseRootElement(document, monitor);
			}
			if (!monitor.isCanceled()) {
				root = newRoot;
				setRootIntoTree();
			}
    }
		
		public void cancel(){
			monitor.setCanceled(true);
		}
		
		/**
		 * Parse the current document to create the tree
		 * 
		 * @param document the document to parse
		 * @return the created tree or null if something went wrong during the execution
		 */
		private XMLElement parseRootElement(IDocument document, IProgressMonitor monitor) {
			try {
				XMLParser xmlParser = new XMLParser();
				OutlineContentHandler contentHandler = new OutlineContentHandler(monitor);
				contentHandler.setDocument(document);
				contentHandler.setPositionCategory(TAG_POSITIONS);
				contentHandler.setDocumentLocator(new LocatorImpl());
				xmlParser.setContentHandler(contentHandler);
				xmlParser.doParse(document.get());
				XMLElement root = contentHandler.getRootElement();
				return root;
			} catch (Exception e) {
				return null;
			}
		}
		
		/**
		 * Return the document by the type of the input. The document is
		 * always a new instance since more thread can work on it
		 * 
		 * @param input the input must be a string or an IEditorInput
		 * @return an IDocument build from the input
		 */
		private IDocument getDocument(Object input){
			if (input == null) return null;
			if (input instanceof String) return new Document((String)input);
			else {
				return getDocument(editor.getDocumentProvider().getDocument(input).get());
			}
		}
	};
	
	/**
	 * Fake input node used to show in the outline a loading message
	 */
	private static XMLElement loadingNode;
	
	/**
	 * Fake input node used to show in the outline an error message
	 */
	private static XMLElement noInput;
	
	static{
		//initialize the fake input nodes
		loadingNode = new XMLElement("root");
		loadingNode.addChildElement(new XMLElement("Loading the outline..."));

		noInput = new XMLElement("root"); 
		noInput.addChildElement(new XMLElement("No valid input"));
	}

	private ITextEditor editor;
	
	private Object input;
	
	private OutlineContentProvider outlineContentProvider;
	
	private OutlineLabelProvider outlineLabelProvider;

	protected final static String TAG_POSITIONS = "__tag_positions";
	
	protected IPositionUpdater positionUpdater = new DefaultPositionUpdater(TAG_POSITIONS);
	
	/**
	 * Reference to the current running tree
	 */
	private XMLLoaderJob xmlLoaderJob = null;
	
	/**
	 * The current input
	 */
	private XMLElement root = null;

	public EditorContentOutlinePage(ITextEditor editor)
	{
		super();
		this.editor = editor;
	}

	public void createControl(Composite parent)
	{
		super.createControl(parent);
		TreeViewer viewer = getTreeViewer();
		outlineContentProvider = new OutlineContentProvider();
		viewer.setContentProvider(outlineContentProvider);
		outlineLabelProvider = new OutlineLabelProvider();
		viewer.setLabelProvider(outlineLabelProvider);
		viewer.addSelectionChangedListener(this);

		//control is created after input is set
		setRootIntoTree();
	}

	/**
	 * Sets the input of the outline page
	 */
	public void setInput(Object input)
	{
		if (!ModelUtils.safeEquals(input, this.input)) {
			this.input = input;
			update();
		}
	}

	/*
	 * Change in selection
	 */
	public void selectionChanged(SelectionChangedEvent event)
	{
		super.selectionChanged(event);
		//find out which item in tree viewer we have selected, and set highlight range accordingly

		ISelection selection = event.getSelection();
		if (selection.isEmpty())
			editor.resetHighlightRange();
		else
		{
			XMLElement element = (XMLElement) ((IStructuredSelection) selection).getFirstElement();		
			if (element.getPosition() != null){
				int start = element.getPosition().getOffset();
				int length = element.getPosition().getLength();
				try
				{
					editor.setHighlightRange(start, length, true);
				}
				catch (IllegalArgumentException x)
				{
					editor.resetHighlightRange();
				}
			}
		}
	}


	/**
	 * The editor is saved, so we should refresh representation. It will
	 * start the thread to parse the XML, if there is another one in progress
	 * it is marked for the cancellation
	 * 
	 */
	public void update()
	{
		//set the input so that the outlines parse can be called
		//update the tree viewer state
		final TreeViewer viewer = getTreeViewer();

		if (viewer != null)
		{
			Control control = viewer.getControl();
			if (control != null && !control.isDisposed()){
				//set the informative loading node
				viewer.setInput(loadingNode);	
				if (xmlLoaderJob != null){
					//mark the old thread for the cancellation
					xmlLoaderJob.cancel();
					xmlLoaderJob = null;
				}
				//start the new thread
				xmlLoaderJob = new XMLLoaderJob();
				new Thread(xmlLoaderJob).start();
			}
		}
	}
	
	/**
	 * Set the input inside the tree, it is done with a UI thread
	 * so it is a safe operation
	 */
	private synchronized void setRootIntoTree(){
		UIUtils.getDisplay().syncExec(new Runnable() {
			
			@Override
			public void run() {
				TreeViewer viewer = getTreeViewer();
				if (viewer != null){
					Control control = viewer.getControl();
					if (control != null && !control.isDisposed())
					{
						control.setRedraw(false);
						if (root == null){
							viewer.setInput(noInput);
						} else {
							viewer.setInput(root);
						}
						control.setRedraw(true);
					}
				}
			}
		});
	}
}
