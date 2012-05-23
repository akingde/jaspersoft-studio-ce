package com.jaspersoft.studio.editor.jrexpressions.ui.support;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.expressions.functions.util.FunctionsLibraryUtil;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.resource.EObjectAtOffsetHelper;

import com.jaspersoft.studio.editor.jrexpressions.javaJRExpression.Arguments;
import com.jaspersoft.studio.editor.jrexpressions.javaJRExpression.ExpressionList;
import com.jaspersoft.studio.editor.jrexpressions.javaJRExpression.FullMethodName;
import com.jaspersoft.studio.editor.jrexpressions.javaJRExpression.MethodInvocation;

import de.itemis.xtext.utils.jface.viewers.StyledTextXtextAdapter;

/**
 * Utility object that exposes some methods to work with the current editing area.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class EditingAreaHelper {

	// flag to indicate if an update operation (text modification) is occurring.
	private boolean update;	
	// reference to the adapter for the styled text widget containing the xtext expression
	private StyledTextXtextAdapter xtextAdapter;
	// reference to the text widget with the expression
	private StyledText textArea;	
	// list of listeners
	private List<ObjectCategorySelectionListener> categorySelectionListeners=new ArrayList<ObjectCategorySelectionListener>();

	/**
	 * Creates the helper object.
	 * 
	 * @param xtextAdapter the adapter for the styled text widget
	 * @param textArea the text widget where the expression is currently being edited
	 */
	public EditingAreaHelper(StyledTextXtextAdapter xtextAdapter, StyledText textArea) {
		super();
		this.xtextAdapter = xtextAdapter;
		this.textArea = textArea;
	}
	
	/**
	 * Returns the current function name using the cursor position in the text editor.
	 * 
	 * <p>
	 * This method seeks for library functions so no dotted identifier is considered 
	 * for now. This means that when other kind of method invocation are found this
	 * method returns <code>null</code>.
	 * 
	 * @return the name of the currently selected function, <code>null</code> otherwise
	 */
	public String getCurrentLibraryFunctionName(){
		// TODO - When dotted library functions will be allowed this method must be improved
		Arguments args = getMethodArguments();
		if(args!=null && args.eContainer() instanceof MethodInvocation){
			MethodInvocation methodInv=(MethodInvocation)args.eContainer();
			FullMethodName fullyQualifiedMethodName = methodInv.getFullyQualifiedMethodName();
			if(fullyQualifiedMethodName!=null){
				String methodName = fullyQualifiedMethodName.getMethodName();
				if(FunctionsLibraryUtil.existsFunction(methodName)){
					return methodName;
				}
			}
		}
		return null;
	}
	
	/**
	 * Returns the position of a function argument currently identified by
	 * the cursor position in the editing area.
	 * 
	 * <p>
	 * NOTE: a valid position can be returned only when the cursor is inside a method
	 * invocation element.
	 * 
	 * @return the function argument position if any, <code>-1</code> otherwise
	 */
	public int getArgumentPosition(){
		Arguments args=getMethodArguments();
		int actualNodeOffset = getActualNodeOffset();
		if(args!=null){
			final ExpressionList exprLst=args.getExprLst();
			if(exprLst!=null){
				ICompositeNode expressionsLst = NodeModelUtils.getNode(exprLst);
				if(actualNodeOffset>=expressionsLst.getOffset()){
					int argumentPos=1;
					for (INode child : expressionsLst.getChildren()){
						if(child.getOffset()<actualNodeOffset && 
							child.getGrammarElement() instanceof Keyword && 
							",".equals(((Keyword)child.getGrammarElement()).getValue())){
								argumentPos++;
						}
					}
					
					return argumentPos; 
				}
			}
			else{
				// No arguments found.
				// Potential position for argument 1 (excluded the '(' char)
				if(actualNodeOffset>=NodeModelUtils.getNode(args).getOffset()+1){
					return 1;
				}
			}
		}

		return -1;
	}

	/**
	 * Returns the text for the argument in the specified position, 
	 * of the (potential) selected function.
	 * 
	 * <p>
	 * NOTE: a valid function can be located only when the cursor is inside a method
	 * invocation element.
	 * 
	 * @param position the argument position
	 * @return the text associated to the argument, <code>null</code> otherwise
	 */
	public String getTextForArgument(int position) {
		Arguments args=getMethodArguments();
		if(args!=null){
			final ExpressionList exprLst=args.getExprLst();
			if(exprLst!=null){ 
				if(exprLst.getCommas().size()>0){
					if(position>exprLst.getCommas().size()+1){
						// already out of scope: parameter position not valid
						return null;
					}
			
					int commasNum = exprLst.getCommas().size();
					ICompositeNode argsNode = NodeModelUtils.getNode(args);
					int argsStart = argsNode.getOffset();	// left parenthesis position
					int argsEnd = argsNode.getTotalEndOffset();	// right parenthesis position
					int selectionStart=-1;
					int selectionEnd=-1;
					
					// The parameter index is comprised in the available locations
					// Comma separated expressions (or even blank chars) => get all comma positions
					List<Integer> commasOffsets=new ArrayList<Integer>();									
					for(INode c : NodeModelUtils.findActualNodeFor(exprLst).getChildren()){
						if(c.getGrammarElement() instanceof Keyword && 
								",".equals(((Keyword)c.getGrammarElement()).getValue())){
							commasOffsets.add(c.getOffset());
						}
					}
					
					if(position==1){
						selectionStart=argsStart+1;
						selectionEnd=commasOffsets.get(0);
					}
					else if (position == commasNum+1){
						selectionStart=commasOffsets.get(commasNum-1)+1;
						selectionEnd=argsEnd-1;
					}
					else {
						selectionStart=commasOffsets.get(position-2)+1;
						selectionEnd=commasOffsets.get(position-1);
					}
					
					if((selectionStart!=-1 && selectionEnd!=-1) &&
							selectionEnd>selectionStart){
						try{
							return textArea.getText(selectionStart, selectionEnd-1);
						}
						catch(IllegalArgumentException ex){
							// Text modification is occurred while
							// we were updating the text widget content
							// in the function detail panel => ignore it
							return null;
						}
					}
					else {
						return null;
					}
					
				}
				else{
					// One single potential parameter
					if(exprLst.getExpressions().size()==1 && position==1){
						ICompositeNode exprNode = NodeModelUtils.getNode(exprLst.getExpressions().get(0));
						return NodeModelUtils.getTokenText(exprNode);
					}
				}
			}
		}
		return null;
	}

	/**
	 * Performs the selection of the expression text corresponding to the argument
	 * in the specified position of the (potential) actual function.
	 * 
	 * <p>
	 * NOTE: a valid function can be located only when the cursor is inside a method
	 * invocation element.
	 * 
	 * @param position the argument position
	 */
	public void selectMethodArgument(int position) {
		Arguments args=getMethodArguments();
		if(args!=null){
			final ExpressionList exprLst=args.getExprLst();
			ICompositeNode argsNode = NodeModelUtils.getNode(args);
			int argsStart = argsNode.getOffset();	// left parenthesis position
			int argsEnd = argsNode.getTotalEndOffset();	// right parenthesis position

			int selectionStart=-1;
			int selectionEnd=-1;
			int newPlaceHoldersNum=0;
			
			if(exprLst!=null){
				int commasNum = exprLst.getCommas().size();
				
				if(commasNum==0){	// No commas ==> only one location available i.e FUNCTION() or FUNCTION(arg1)
					if(position==1){
						// Select all parentheses content
						selectionStart=argsStart+1;
						selectionEnd=argsEnd-1;										
					}
					else{
						// Must add commas and spaces as much as needed
						newPlaceHoldersNum=position-1;
					}
				}
				else{
					if(position>commasNum+1){
						// A location for the specified parameter index is not available
						// We need to add the remaining locations 
						newPlaceHoldersNum=position-(commasNum+1);
					}
					else{
						// The parameter index is comprised in the available locations
						// Comma separated expressions (or even blank chars) => get all comma positions
						List<Integer> commasOffsets=new ArrayList<Integer>();									
						for(INode c : NodeModelUtils.findActualNodeFor(exprLst).getChildren()){
							if(c.getGrammarElement() instanceof Keyword && 
									",".equals(((Keyword)c.getGrammarElement()).getValue())){
								commasOffsets.add(c.getOffset());
							}
						}
						
						if(position==1){
							selectionStart=argsStart+1;
							selectionEnd=commasOffsets.get(0);
						}
						else if (position == commasNum+1){
							selectionStart=commasOffsets.get(commasNum-1)+1;
							selectionEnd=argsEnd-1;
						}
						else {
							selectionStart=commasOffsets.get(position-2)+1;
							selectionEnd=commasOffsets.get(position-1);
						}
					}
				}
				
			}
			else{
				newPlaceHoldersNum=position-1;
				selectionStart=argsStart+1;
				selectionEnd=argsEnd-1;
			}
			
			if(newPlaceHoldersNum>0){
				String newParams="";
				for(int i=0;i<newPlaceHoldersNum;i++){
					newParams+=", ";
				}
				int newPosition=argsEnd-1;
				textArea.setSelection(newPosition);
				textArea.insert(newParams);
				selectionStart=newPosition+newParams.length()-1;
				selectionEnd=newPosition+newParams.length();
			}
			
			if(selectionStart!=-1 && selectionEnd!=-1){
				textArea.setSelection(selectionStart,selectionEnd);
			}
		}
	}

	/**
	 * Remove all the useless arguments/commas after a specified
	 * valid position.
	 * 
	 * <p>
	 * NOTE: a valid function can be located only when the cursor is inside a method
	 * invocation element.
	 * 
	 * @param lastPosition the last valid argument position
	 */
	public void removeUselessParameters(int lastPosition) {
		Arguments args=getMethodArguments();
		if(args!=null){
			final ExpressionList exprLst=args.getExprLst();
			ICompositeNode argsNode = NodeModelUtils.getNode(args);
			int argsEnd = argsNode.getTotalEndOffset();	// right parenthesis position
			int selectionStart=-1;
			int selectionEnd=-1;
			
			if(exprLst!=null){
				int commasNum = exprLst.getCommas().size();
				if(commasNum>0 && commasNum>=lastPosition){
					// Comma separated expressions (or even blank chars) => get all comma positions
					List<Integer> commasOffsets=new ArrayList<Integer>();									
					for(INode c : NodeModelUtils.findActualNodeFor(exprLst).getChildren()){
						if(c.getGrammarElement() instanceof Keyword && 
								",".equals(((Keyword)c.getGrammarElement()).getValue())){
							commasOffsets.add(c.getOffset());
						}
					}

					// Get the first non useful comma
					selectionStart=commasOffsets.get(lastPosition-1);
					selectionEnd=argsEnd-1;
				}
			}
			
			if(selectionStart!=-1 && selectionEnd!=-1){
				textArea.setSelection(selectionStart,selectionEnd);
				textArea.insert("");
			}
		}
	}
	
	/*
	 * Given the current position in the editing area, tries to retrieve the semantic element 
	 * representing the arguments of a method invocation.
	 */
	private Arguments getMethodArguments(){
		if(xtextAdapter.getXtextParseResult()!=null){
			ICompositeNode actualNode=getActualNode();
			if(actualNode!=null){
					INode tmpParentNode=actualNode;
					boolean foundParentNode=false;
					while(!foundParentNode && tmpParentNode!=null){
						if(tmpParentNode.getSemanticElement() instanceof Arguments || tmpParentNode.getSemanticElement() instanceof MethodInvocation){
							foundParentNode=true;
						}
						else{
							tmpParentNode=tmpParentNode.getParent();
						}
					}
					if(foundParentNode){
						Arguments args=null;
						if(tmpParentNode.getSemanticElement() instanceof MethodInvocation){
							args=((MethodInvocation)tmpParentNode.getSemanticElement()).getArgs();
						}
						else{
							args=(Arguments)tmpParentNode.getSemanticElement();
						}
						return args;
					}
			}
		}
		
		return null;
	}
	
	/**
	 * Given the current position in the editing area, tries
	 * to recover the corresponding actual node.
	 * 
	 * @return the actual node, <code>null</code> if not possible
	 */
	public ICompositeNode getActualNode(){
		if(xtextAdapter.getXtextParseResult()!=null){
			int caretOffset = textArea.getCaretOffset();
			EObjectAtOffsetHelper eobjHelper=new EObjectAtOffsetHelper();
			EObject resolvedEObj = eobjHelper.resolveElementAt(xtextAdapter.getFakeResourceContext().getFakeResource(), caretOffset);
			return NodeModelUtils.findActualNodeFor(resolvedEObj);
		}
		return null;
	}
	
	/**
	 * Given the current position in the editing area, tries
	 * to recover the corresponding actual node offset.
	 * 
	 * @return the actual node offset, <code>-1</code> if the actual node is not available
	 */
	public int getActualNodeOffset(){
		ICompositeNode actualNode = getActualNode();
		return actualNode!=null ? actualNode.getOffset() : -1;
	}
	
	/**
	 * Inserts new text in the editing area and if specified select also 
	 * the newly inserted text.
	 * 
	 * @param partialExpression the text string to enter in the editing area
	 * @param selectNewText applies or not the selection of the newly inserted text
	 */
	public void insertAtCurrentLocation(String partialExpression,boolean selectNewText){
		int start=textArea.getSelection().x;
		int end=start;
		textArea.insert(partialExpression);
		if(selectNewText){
			end=start+partialExpression.length();
		}
		textArea.setSelection(start, end);
	}
	
	/**
	 * Toggles the update status.
	 * 
	 * @param update flag for update operation status
	 */
	public void setUpdate(boolean update){
		this.update=update;
	}
	
	/**
	 * @return 
	 * 		<code>true</code> if an update operation is currently being performed, 
	 * 		<code>false</code> otherwise
	 */
	public boolean isUpdate(){
		return this.update;
	}
	
	/**
	 * Remove an existing category selection listener.
	 * 
	 * @param listener the listener to be removed
	 */
	public void removeCategorySelectionListener(ObjectCategorySelectionListener listener){
		categorySelectionListeners.remove(listener);
	}
	
	/**
	 * Adds a new category selection listener.
	 * 
	 * @param listener the listener to be added
	 */
	public void addCategorySelectionListener(ObjectCategorySelectionListener listener){
		categorySelectionListeners.add(listener);
	}

	/**
	 * Notifies the selection of a new category.
	 * 
	 * @param selectionEvent the selection event containing the information on the selected category
	 */
	public void notifyCategorySelection(
			ObjectCategorySelectionEvent selectionEvent) {
		for (ObjectCategorySelectionListener l : categorySelectionListeners){
			l.select(selectionEvent);
		}
	}
}
