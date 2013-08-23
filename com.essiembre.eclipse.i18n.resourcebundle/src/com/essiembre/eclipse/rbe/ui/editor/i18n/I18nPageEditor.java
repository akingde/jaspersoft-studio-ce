package com.essiembre.eclipse.rbe.ui.editor.i18n;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.text.IFindReplaceTarget;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.texteditor.AbstractTextEditor;
import org.eclipse.ui.texteditor.FindNextAction;
import org.eclipse.ui.texteditor.FindReplaceAction;
import org.eclipse.ui.texteditor.IWorkbenchActionDefinitionIds;

import com.essiembre.eclipse.rbe.RBEPlugin;
import com.essiembre.eclipse.rbe.ui.editor.resources.ResourceManager;


public class I18nPageEditor extends AbstractTextEditor {

   private I18nPage        _i18nPage;
   private ResourceManager _resourceMediator;
   
   private FindReplaceAction _findReplaceAction;
   private FindNextAction _findNextAction;
   private FindNextAction _findPreviousAction;


   public I18nPageEditor( ResourceManager resourceMediator ) {
      _resourceMediator = resourceMediator;
   }

   public I18nPage getI18nPage() {
      return _i18nPage;
   }
   
   @Override
   public void createPartControl( Composite parent ) {
      _i18nPage = new I18nPage(parent, SWT.NONE, _resourceMediator);
      
      _findReplaceAction = new FindReplaceAction(RBEPlugin.getDefault().getResourceBundle(), null, _i18nPage.getShell(), _i18nPage.getReplaceTarget()) {
         @Override
         public void run() {
            _i18nPage.findActionStart();
            super.run();
         }
      };
      
      _findNextAction = new FindNextAction(RBEPlugin.getDefault().getResourceBundle(), null, this, true){
         @Override
         public void run() {
            _i18nPage.findActionStart();
            super.run();
         }
      };
      _findNextAction.setActionDefinitionId(IWorkbenchActionDefinitionIds.FIND_NEXT);
      
      _findPreviousAction = new FindNextAction(RBEPlugin.getDefault().getResourceBundle(), null, this, false){
         @Override
         public void run() {
            _i18nPage.findActionStart();
            super.run();
         }
      };
      _findPreviousAction.setActionDefinitionId(IWorkbenchActionDefinitionIds.FIND_PREVIOUS);
   }
   
   @Override
   public Object getAdapter( Class required ) {
      if ( required.equals(IFindReplaceTarget.class) ) {
         return _i18nPage.getReplaceTarget();
      }
      return super.getAdapter(required);
   }
   
   public IAction getFindReplaceAction() {
      return _findReplaceAction;
   }

   public IAction getFindNextAction() {
      return _findNextAction;
   }

   public IAction getFindPreviousAction() {
      return _findPreviousAction;
   }
}
