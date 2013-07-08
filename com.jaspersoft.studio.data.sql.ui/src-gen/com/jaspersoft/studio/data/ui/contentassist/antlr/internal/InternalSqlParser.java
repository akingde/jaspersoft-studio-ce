package com.jaspersoft.studio.data.ui.contentassist.antlr.internal; 

import java.util.Map;
import java.util.HashMap;

import java.io.InputStream;
import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.DFA;
import com.jaspersoft.studio.data.services.SqlGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalSqlParser extends AbstractInternalContentAssistParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "KEYWORD_8", "KEYWORD_9", "KEYWORD_7", "KEYWORD_6", "KEYWORD_5", "KEYWORD_4", "KEYWORD_3", "KEYWORD_1", "KEYWORD_2", "RULE_STAR", "RULE_SL_COMMENT", "RULE_ID", "RULE_INT", "RULE_STRING", "RULE_ML_COMMENT", "RULE_WS", "RULE_ANY_OTHER"
    };
    public static final int RULE_ID=15;
    public static final int RULE_STRING=17;
    public static final int RULE_STAR=13;
    public static final int KEYWORD_6=7;
    public static final int RULE_ANY_OTHER=20;
    public static final int KEYWORD_7=6;
    public static final int KEYWORD_8=4;
    public static final int KEYWORD_9=5;
    public static final int KEYWORD_1=11;
    public static final int RULE_INT=16;
    public static final int KEYWORD_5=8;
    public static final int KEYWORD_4=9;
    public static final int KEYWORD_3=10;
    public static final int KEYWORD_2=12;
    public static final int RULE_WS=19;
    public static final int RULE_SL_COMMENT=14;
    public static final int EOF=-1;
    public static final int RULE_ML_COMMENT=18;

    // delegates
    // delegators


        public InternalSqlParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalSqlParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalSqlParser.tokenNames; }
    public String getGrammarFileName() { return "../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g"; }


     
     	private SqlGrammarAccess grammarAccess;
     	
     	private final Map<String, String> tokenNameToValue = new HashMap<String, String>();
     	
     	{
    		tokenNameToValue.put("KEYWORD_1", "','");
    		tokenNameToValue.put("KEYWORD_2", "'.'");
    		tokenNameToValue.put("KEYWORD_3", "'AS'");
    		tokenNameToValue.put("KEYWORD_4", "'ASC'");
    		tokenNameToValue.put("KEYWORD_5", "'DESC'");
    		tokenNameToValue.put("KEYWORD_6", "'UNION'");
    		tokenNameToValue.put("KEYWORD_7", "'SELECT'");
    		tokenNameToValue.put("KEYWORD_8", "'DISTINCT'");
    		tokenNameToValue.put("KEYWORD_9", "'ORDER BY'");
     	}
     	
        public void setGrammarAccess(SqlGrammarAccess grammarAccess) {
        	this.grammarAccess = grammarAccess;
        }
        
        @Override
        protected Grammar getGrammar() {
        	return grammarAccess.getGrammar();
        }

    	@Override
        protected String getValueForTokenName(String tokenName) {
        	String result = tokenNameToValue.get(tokenName);
        	if (result == null)
        		result = tokenName;
        	return result;
        }



    // $ANTLR start "entryRuleModel"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:72:1: entryRuleModel : ruleModel EOF ;
    public final void entryRuleModel() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:73:1: ( ruleModel EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:74:1: ruleModel EOF
            {
             before(grammarAccess.getModelRule()); 
            pushFollow(FOLLOW_ruleModel_in_entryRuleModel54);
            ruleModel();

            state._fsp--;

             after(grammarAccess.getModelRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleModel61); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleModel"


    // $ANTLR start "ruleModel"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:81:1: ruleModel : ( ( rule__Model__Group__0 ) ) ;
    public final void ruleModel() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:85:5: ( ( ( rule__Model__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:86:1: ( ( rule__Model__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:86:1: ( ( rule__Model__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:87:1: ( rule__Model__Group__0 )
            {
             before(grammarAccess.getModelAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:88:1: ( rule__Model__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:88:2: rule__Model__Group__0
            {
            pushFollow(FOLLOW_rule__Model__Group__0_in_ruleModel91);
            rule__Model__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getModelAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleModel"


    // $ANTLR start "entryRuleSelect"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:100:1: entryRuleSelect : ruleSelect EOF ;
    public final void entryRuleSelect() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:101:1: ( ruleSelect EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:102:1: ruleSelect EOF
            {
             before(grammarAccess.getSelectRule()); 
            pushFollow(FOLLOW_ruleSelect_in_entryRuleSelect118);
            ruleSelect();

            state._fsp--;

             after(grammarAccess.getSelectRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSelect125); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleSelect"


    // $ANTLR start "ruleSelect"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:109:1: ruleSelect : ( ( rule__Select__Group__0 ) ) ;
    public final void ruleSelect() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:113:5: ( ( ( rule__Select__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:114:1: ( ( rule__Select__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:114:1: ( ( rule__Select__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:115:1: ( rule__Select__Group__0 )
            {
             before(grammarAccess.getSelectAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:116:1: ( rule__Select__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:116:2: rule__Select__Group__0
            {
            pushFollow(FOLLOW_rule__Select__Group__0_in_ruleSelect155);
            rule__Select__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getSelectAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleSelect"


    // $ANTLR start "entryRuleColumns"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:128:1: entryRuleColumns : ruleColumns EOF ;
    public final void entryRuleColumns() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:129:1: ( ruleColumns EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:130:1: ruleColumns EOF
            {
             before(grammarAccess.getColumnsRule()); 
            pushFollow(FOLLOW_ruleColumns_in_entryRuleColumns182);
            ruleColumns();

            state._fsp--;

             after(grammarAccess.getColumnsRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleColumns189); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleColumns"


    // $ANTLR start "ruleColumns"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:137:1: ruleColumns : ( ( rule__Columns__Group__0 ) ) ;
    public final void ruleColumns() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:141:5: ( ( ( rule__Columns__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:142:1: ( ( rule__Columns__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:142:1: ( ( rule__Columns__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:143:1: ( rule__Columns__Group__0 )
            {
             before(grammarAccess.getColumnsAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:144:1: ( rule__Columns__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:144:2: rule__Columns__Group__0
            {
            pushFollow(FOLLOW_rule__Columns__Group__0_in_ruleColumns219);
            rule__Columns__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getColumnsAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleColumns"


    // $ANTLR start "entryRuleColumnOrAlias"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:156:1: entryRuleColumnOrAlias : ruleColumnOrAlias EOF ;
    public final void entryRuleColumnOrAlias() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:157:1: ( ruleColumnOrAlias EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:158:1: ruleColumnOrAlias EOF
            {
             before(grammarAccess.getColumnOrAliasRule()); 
            pushFollow(FOLLOW_ruleColumnOrAlias_in_entryRuleColumnOrAlias246);
            ruleColumnOrAlias();

            state._fsp--;

             after(grammarAccess.getColumnOrAliasRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleColumnOrAlias253); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleColumnOrAlias"


    // $ANTLR start "ruleColumnOrAlias"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:165:1: ruleColumnOrAlias : ( ( rule__ColumnOrAlias__Alternatives ) ) ;
    public final void ruleColumnOrAlias() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:169:5: ( ( ( rule__ColumnOrAlias__Alternatives ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:170:1: ( ( rule__ColumnOrAlias__Alternatives ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:170:1: ( ( rule__ColumnOrAlias__Alternatives ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:171:1: ( rule__ColumnOrAlias__Alternatives )
            {
             before(grammarAccess.getColumnOrAliasAccess().getAlternatives()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:172:1: ( rule__ColumnOrAlias__Alternatives )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:172:2: rule__ColumnOrAlias__Alternatives
            {
            pushFollow(FOLLOW_rule__ColumnOrAlias__Alternatives_in_ruleColumnOrAlias283);
            rule__ColumnOrAlias__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getColumnOrAliasAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleColumnOrAlias"


    // $ANTLR start "entryRuleColumnFull"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:184:1: entryRuleColumnFull : ruleColumnFull EOF ;
    public final void entryRuleColumnFull() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:185:1: ( ruleColumnFull EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:186:1: ruleColumnFull EOF
            {
             before(grammarAccess.getColumnFullRule()); 
            pushFollow(FOLLOW_ruleColumnFull_in_entryRuleColumnFull310);
            ruleColumnFull();

            state._fsp--;

             after(grammarAccess.getColumnFullRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleColumnFull317); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleColumnFull"


    // $ANTLR start "ruleColumnFull"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:193:1: ruleColumnFull : ( ( rule__ColumnFull__Group__0 ) ) ;
    public final void ruleColumnFull() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:197:5: ( ( ( rule__ColumnFull__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:198:1: ( ( rule__ColumnFull__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:198:1: ( ( rule__ColumnFull__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:199:1: ( rule__ColumnFull__Group__0 )
            {
             before(grammarAccess.getColumnFullAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:200:1: ( rule__ColumnFull__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:200:2: rule__ColumnFull__Group__0
            {
            pushFollow(FOLLOW_rule__ColumnFull__Group__0_in_ruleColumnFull347);
            rule__ColumnFull__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getColumnFullAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleColumnFull"


    // $ANTLR start "entryRuleDbObjectName"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:212:1: entryRuleDbObjectName : ruleDbObjectName EOF ;
    public final void entryRuleDbObjectName() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:213:1: ( ruleDbObjectName EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:214:1: ruleDbObjectName EOF
            {
             before(grammarAccess.getDbObjectNameRule()); 
            pushFollow(FOLLOW_ruleDbObjectName_in_entryRuleDbObjectName374);
            ruleDbObjectName();

            state._fsp--;

             after(grammarAccess.getDbObjectNameRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleDbObjectName381); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleDbObjectName"


    // $ANTLR start "ruleDbObjectName"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:221:1: ruleDbObjectName : ( ( rule__DbObjectName__DbnameAssignment ) ) ;
    public final void ruleDbObjectName() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:225:5: ( ( ( rule__DbObjectName__DbnameAssignment ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:226:1: ( ( rule__DbObjectName__DbnameAssignment ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:226:1: ( ( rule__DbObjectName__DbnameAssignment ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:227:1: ( rule__DbObjectName__DbnameAssignment )
            {
             before(grammarAccess.getDbObjectNameAccess().getDbnameAssignment()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:228:1: ( rule__DbObjectName__DbnameAssignment )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:228:2: rule__DbObjectName__DbnameAssignment
            {
            pushFollow(FOLLOW_rule__DbObjectName__DbnameAssignment_in_ruleDbObjectName411);
            rule__DbObjectName__DbnameAssignment();

            state._fsp--;


            }

             after(grammarAccess.getDbObjectNameAccess().getDbnameAssignment()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleDbObjectName"


    // $ANTLR start "entryRuleOrderByColumns"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:240:1: entryRuleOrderByColumns : ruleOrderByColumns EOF ;
    public final void entryRuleOrderByColumns() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:241:1: ( ruleOrderByColumns EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:242:1: ruleOrderByColumns EOF
            {
             before(grammarAccess.getOrderByColumnsRule()); 
            pushFollow(FOLLOW_ruleOrderByColumns_in_entryRuleOrderByColumns438);
            ruleOrderByColumns();

            state._fsp--;

             after(grammarAccess.getOrderByColumnsRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOrderByColumns445); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleOrderByColumns"


    // $ANTLR start "ruleOrderByColumns"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:249:1: ruleOrderByColumns : ( ( rule__OrderByColumns__Group__0 ) ) ;
    public final void ruleOrderByColumns() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:253:5: ( ( ( rule__OrderByColumns__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:254:1: ( ( rule__OrderByColumns__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:254:1: ( ( rule__OrderByColumns__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:255:1: ( rule__OrderByColumns__Group__0 )
            {
             before(grammarAccess.getOrderByColumnsAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:256:1: ( rule__OrderByColumns__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:256:2: rule__OrderByColumns__Group__0
            {
            pushFollow(FOLLOW_rule__OrderByColumns__Group__0_in_ruleOrderByColumns475);
            rule__OrderByColumns__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getOrderByColumnsAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleOrderByColumns"


    // $ANTLR start "entryRuleOrderByColumnFull"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:268:1: entryRuleOrderByColumnFull : ruleOrderByColumnFull EOF ;
    public final void entryRuleOrderByColumnFull() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:269:1: ( ruleOrderByColumnFull EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:270:1: ruleOrderByColumnFull EOF
            {
             before(grammarAccess.getOrderByColumnFullRule()); 
            pushFollow(FOLLOW_ruleOrderByColumnFull_in_entryRuleOrderByColumnFull502);
            ruleOrderByColumnFull();

            state._fsp--;

             after(grammarAccess.getOrderByColumnFullRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOrderByColumnFull509); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleOrderByColumnFull"


    // $ANTLR start "ruleOrderByColumnFull"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:277:1: ruleOrderByColumnFull : ( ( rule__OrderByColumnFull__Group__0 ) ) ;
    public final void ruleOrderByColumnFull() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:281:5: ( ( ( rule__OrderByColumnFull__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:282:1: ( ( rule__OrderByColumnFull__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:282:1: ( ( rule__OrderByColumnFull__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:283:1: ( rule__OrderByColumnFull__Group__0 )
            {
             before(grammarAccess.getOrderByColumnFullAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:284:1: ( rule__OrderByColumnFull__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:284:2: rule__OrderByColumnFull__Group__0
            {
            pushFollow(FOLLOW_rule__OrderByColumnFull__Group__0_in_ruleOrderByColumnFull539);
            rule__OrderByColumnFull__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getOrderByColumnFullAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleOrderByColumnFull"


    // $ANTLR start "rule__ColumnOrAlias__Alternatives"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:296:1: rule__ColumnOrAlias__Alternatives : ( ( ( rule__ColumnOrAlias__Group_0__0 ) ) | ( ( rule__ColumnOrAlias__AllColsAssignment_1 ) ) );
    public final void rule__ColumnOrAlias__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:300:1: ( ( ( rule__ColumnOrAlias__Group_0__0 ) ) | ( ( rule__ColumnOrAlias__AllColsAssignment_1 ) ) )
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==RULE_ID) ) {
                alt1=1;
            }
            else if ( (LA1_0==RULE_STAR) ) {
                alt1=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;
            }
            switch (alt1) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:301:1: ( ( rule__ColumnOrAlias__Group_0__0 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:301:1: ( ( rule__ColumnOrAlias__Group_0__0 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:302:1: ( rule__ColumnOrAlias__Group_0__0 )
                    {
                     before(grammarAccess.getColumnOrAliasAccess().getGroup_0()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:303:1: ( rule__ColumnOrAlias__Group_0__0 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:303:2: rule__ColumnOrAlias__Group_0__0
                    {
                    pushFollow(FOLLOW_rule__ColumnOrAlias__Group_0__0_in_rule__ColumnOrAlias__Alternatives575);
                    rule__ColumnOrAlias__Group_0__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getColumnOrAliasAccess().getGroup_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:307:6: ( ( rule__ColumnOrAlias__AllColsAssignment_1 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:307:6: ( ( rule__ColumnOrAlias__AllColsAssignment_1 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:308:1: ( rule__ColumnOrAlias__AllColsAssignment_1 )
                    {
                     before(grammarAccess.getColumnOrAliasAccess().getAllColsAssignment_1()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:309:1: ( rule__ColumnOrAlias__AllColsAssignment_1 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:309:2: rule__ColumnOrAlias__AllColsAssignment_1
                    {
                    pushFollow(FOLLOW_rule__ColumnOrAlias__AllColsAssignment_1_in_rule__ColumnOrAlias__Alternatives593);
                    rule__ColumnOrAlias__AllColsAssignment_1();

                    state._fsp--;


                    }

                     after(grammarAccess.getColumnOrAliasAccess().getAllColsAssignment_1()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnOrAlias__Alternatives"


    // $ANTLR start "rule__OrderByColumnFull__Alternatives_1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:318:1: rule__OrderByColumnFull__Alternatives_1 : ( ( KEYWORD_4 ) | ( KEYWORD_5 ) );
    public final void rule__OrderByColumnFull__Alternatives_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:322:1: ( ( KEYWORD_4 ) | ( KEYWORD_5 ) )
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==KEYWORD_4) ) {
                alt2=1;
            }
            else if ( (LA2_0==KEYWORD_5) ) {
                alt2=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }
            switch (alt2) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:323:1: ( KEYWORD_4 )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:323:1: ( KEYWORD_4 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:324:1: KEYWORD_4
                    {
                     before(grammarAccess.getOrderByColumnFullAccess().getASCKeyword_1_0()); 
                    match(input,KEYWORD_4,FOLLOW_KEYWORD_4_in_rule__OrderByColumnFull__Alternatives_1627); 
                     after(grammarAccess.getOrderByColumnFullAccess().getASCKeyword_1_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:331:6: ( KEYWORD_5 )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:331:6: ( KEYWORD_5 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:332:1: KEYWORD_5
                    {
                     before(grammarAccess.getOrderByColumnFullAccess().getDESCKeyword_1_1()); 
                    match(input,KEYWORD_5,FOLLOW_KEYWORD_5_in_rule__OrderByColumnFull__Alternatives_1647); 
                     after(grammarAccess.getOrderByColumnFullAccess().getDESCKeyword_1_1()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OrderByColumnFull__Alternatives_1"


    // $ANTLR start "rule__Model__Group__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:346:1: rule__Model__Group__0 : rule__Model__Group__0__Impl rule__Model__Group__1 ;
    public final void rule__Model__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:350:1: ( rule__Model__Group__0__Impl rule__Model__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:351:2: rule__Model__Group__0__Impl rule__Model__Group__1
            {
            pushFollow(FOLLOW_rule__Model__Group__0__Impl_in_rule__Model__Group__0679);
            rule__Model__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Model__Group__1_in_rule__Model__Group__0682);
            rule__Model__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Model__Group__0"


    // $ANTLR start "rule__Model__Group__0__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:358:1: rule__Model__Group__0__Impl : ( ruleSelect ) ;
    public final void rule__Model__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:362:1: ( ( ruleSelect ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:363:1: ( ruleSelect )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:363:1: ( ruleSelect )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:364:1: ruleSelect
            {
             before(grammarAccess.getModelAccess().getSelectParserRuleCall_0()); 
            pushFollow(FOLLOW_ruleSelect_in_rule__Model__Group__0__Impl709);
            ruleSelect();

            state._fsp--;

             after(grammarAccess.getModelAccess().getSelectParserRuleCall_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Model__Group__0__Impl"


    // $ANTLR start "rule__Model__Group__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:375:1: rule__Model__Group__1 : rule__Model__Group__1__Impl rule__Model__Group__2 ;
    public final void rule__Model__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:379:1: ( rule__Model__Group__1__Impl rule__Model__Group__2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:380:2: rule__Model__Group__1__Impl rule__Model__Group__2
            {
            pushFollow(FOLLOW_rule__Model__Group__1__Impl_in_rule__Model__Group__1738);
            rule__Model__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Model__Group__2_in_rule__Model__Group__1741);
            rule__Model__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Model__Group__1"


    // $ANTLR start "rule__Model__Group__1__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:387:1: rule__Model__Group__1__Impl : ( ( rule__Model__Group_1__0 )? ) ;
    public final void rule__Model__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:391:1: ( ( ( rule__Model__Group_1__0 )? ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:392:1: ( ( rule__Model__Group_1__0 )? )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:392:1: ( ( rule__Model__Group_1__0 )? )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:393:1: ( rule__Model__Group_1__0 )?
            {
             before(grammarAccess.getModelAccess().getGroup_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:394:1: ( rule__Model__Group_1__0 )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==KEYWORD_6) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:394:2: rule__Model__Group_1__0
                    {
                    pushFollow(FOLLOW_rule__Model__Group_1__0_in_rule__Model__Group__1__Impl768);
                    rule__Model__Group_1__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getModelAccess().getGroup_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Model__Group__1__Impl"


    // $ANTLR start "rule__Model__Group__2"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:404:1: rule__Model__Group__2 : rule__Model__Group__2__Impl ;
    public final void rule__Model__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:408:1: ( rule__Model__Group__2__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:409:2: rule__Model__Group__2__Impl
            {
            pushFollow(FOLLOW_rule__Model__Group__2__Impl_in_rule__Model__Group__2799);
            rule__Model__Group__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Model__Group__2"


    // $ANTLR start "rule__Model__Group__2__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:415:1: rule__Model__Group__2__Impl : ( ( rule__Model__Group_2__0 )? ) ;
    public final void rule__Model__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:419:1: ( ( ( rule__Model__Group_2__0 )? ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:420:1: ( ( rule__Model__Group_2__0 )? )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:420:1: ( ( rule__Model__Group_2__0 )? )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:421:1: ( rule__Model__Group_2__0 )?
            {
             before(grammarAccess.getModelAccess().getGroup_2()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:422:1: ( rule__Model__Group_2__0 )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==KEYWORD_9) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:422:2: rule__Model__Group_2__0
                    {
                    pushFollow(FOLLOW_rule__Model__Group_2__0_in_rule__Model__Group__2__Impl826);
                    rule__Model__Group_2__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getModelAccess().getGroup_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Model__Group__2__Impl"


    // $ANTLR start "rule__Model__Group_1__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:438:1: rule__Model__Group_1__0 : rule__Model__Group_1__0__Impl rule__Model__Group_1__1 ;
    public final void rule__Model__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:442:1: ( rule__Model__Group_1__0__Impl rule__Model__Group_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:443:2: rule__Model__Group_1__0__Impl rule__Model__Group_1__1
            {
            pushFollow(FOLLOW_rule__Model__Group_1__0__Impl_in_rule__Model__Group_1__0863);
            rule__Model__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Model__Group_1__1_in_rule__Model__Group_1__0866);
            rule__Model__Group_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Model__Group_1__0"


    // $ANTLR start "rule__Model__Group_1__0__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:450:1: rule__Model__Group_1__0__Impl : ( () ) ;
    public final void rule__Model__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:454:1: ( ( () ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:455:1: ( () )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:455:1: ( () )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:456:1: ()
            {
             before(grammarAccess.getModelAccess().getOrSelectEntriesAction_1_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:457:1: ()
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:459:1: 
            {
            }

             after(grammarAccess.getModelAccess().getOrSelectEntriesAction_1_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Model__Group_1__0__Impl"


    // $ANTLR start "rule__Model__Group_1__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:469:1: rule__Model__Group_1__1 : rule__Model__Group_1__1__Impl ;
    public final void rule__Model__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:473:1: ( rule__Model__Group_1__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:474:2: rule__Model__Group_1__1__Impl
            {
            pushFollow(FOLLOW_rule__Model__Group_1__1__Impl_in_rule__Model__Group_1__1924);
            rule__Model__Group_1__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Model__Group_1__1"


    // $ANTLR start "rule__Model__Group_1__1__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:480:1: rule__Model__Group_1__1__Impl : ( ( ( rule__Model__Group_1_1__0 ) ) ( ( rule__Model__Group_1_1__0 )* ) ) ;
    public final void rule__Model__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:484:1: ( ( ( ( rule__Model__Group_1_1__0 ) ) ( ( rule__Model__Group_1_1__0 )* ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:485:1: ( ( ( rule__Model__Group_1_1__0 ) ) ( ( rule__Model__Group_1_1__0 )* ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:485:1: ( ( ( rule__Model__Group_1_1__0 ) ) ( ( rule__Model__Group_1_1__0 )* ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:486:1: ( ( rule__Model__Group_1_1__0 ) ) ( ( rule__Model__Group_1_1__0 )* )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:486:1: ( ( rule__Model__Group_1_1__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:487:1: ( rule__Model__Group_1_1__0 )
            {
             before(grammarAccess.getModelAccess().getGroup_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:488:1: ( rule__Model__Group_1_1__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:488:2: rule__Model__Group_1_1__0
            {
            pushFollow(FOLLOW_rule__Model__Group_1_1__0_in_rule__Model__Group_1__1__Impl953);
            rule__Model__Group_1_1__0();

            state._fsp--;


            }

             after(grammarAccess.getModelAccess().getGroup_1_1()); 

            }

            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:491:1: ( ( rule__Model__Group_1_1__0 )* )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:492:1: ( rule__Model__Group_1_1__0 )*
            {
             before(grammarAccess.getModelAccess().getGroup_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:493:1: ( rule__Model__Group_1_1__0 )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==KEYWORD_6) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:493:2: rule__Model__Group_1_1__0
            	    {
            	    pushFollow(FOLLOW_rule__Model__Group_1_1__0_in_rule__Model__Group_1__1__Impl965);
            	    rule__Model__Group_1_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);

             after(grammarAccess.getModelAccess().getGroup_1_1()); 

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Model__Group_1__1__Impl"


    // $ANTLR start "rule__Model__Group_1_1__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:508:1: rule__Model__Group_1_1__0 : rule__Model__Group_1_1__0__Impl rule__Model__Group_1_1__1 ;
    public final void rule__Model__Group_1_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:512:1: ( rule__Model__Group_1_1__0__Impl rule__Model__Group_1_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:513:2: rule__Model__Group_1_1__0__Impl rule__Model__Group_1_1__1
            {
            pushFollow(FOLLOW_rule__Model__Group_1_1__0__Impl_in_rule__Model__Group_1_1__01002);
            rule__Model__Group_1_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Model__Group_1_1__1_in_rule__Model__Group_1_1__01005);
            rule__Model__Group_1_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Model__Group_1_1__0"


    // $ANTLR start "rule__Model__Group_1_1__0__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:520:1: rule__Model__Group_1_1__0__Impl : ( KEYWORD_6 ) ;
    public final void rule__Model__Group_1_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:524:1: ( ( KEYWORD_6 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:525:1: ( KEYWORD_6 )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:525:1: ( KEYWORD_6 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:526:1: KEYWORD_6
            {
             before(grammarAccess.getModelAccess().getUNIONKeyword_1_1_0()); 
            match(input,KEYWORD_6,FOLLOW_KEYWORD_6_in_rule__Model__Group_1_1__0__Impl1033); 
             after(grammarAccess.getModelAccess().getUNIONKeyword_1_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Model__Group_1_1__0__Impl"


    // $ANTLR start "rule__Model__Group_1_1__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:539:1: rule__Model__Group_1_1__1 : rule__Model__Group_1_1__1__Impl ;
    public final void rule__Model__Group_1_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:543:1: ( rule__Model__Group_1_1__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:544:2: rule__Model__Group_1_1__1__Impl
            {
            pushFollow(FOLLOW_rule__Model__Group_1_1__1__Impl_in_rule__Model__Group_1_1__11064);
            rule__Model__Group_1_1__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Model__Group_1_1__1"


    // $ANTLR start "rule__Model__Group_1_1__1__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:550:1: rule__Model__Group_1_1__1__Impl : ( ( rule__Model__EntriesAssignment_1_1_1 ) ) ;
    public final void rule__Model__Group_1_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:554:1: ( ( ( rule__Model__EntriesAssignment_1_1_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:555:1: ( ( rule__Model__EntriesAssignment_1_1_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:555:1: ( ( rule__Model__EntriesAssignment_1_1_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:556:1: ( rule__Model__EntriesAssignment_1_1_1 )
            {
             before(grammarAccess.getModelAccess().getEntriesAssignment_1_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:557:1: ( rule__Model__EntriesAssignment_1_1_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:557:2: rule__Model__EntriesAssignment_1_1_1
            {
            pushFollow(FOLLOW_rule__Model__EntriesAssignment_1_1_1_in_rule__Model__Group_1_1__1__Impl1091);
            rule__Model__EntriesAssignment_1_1_1();

            state._fsp--;


            }

             after(grammarAccess.getModelAccess().getEntriesAssignment_1_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Model__Group_1_1__1__Impl"


    // $ANTLR start "rule__Model__Group_2__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:571:1: rule__Model__Group_2__0 : rule__Model__Group_2__0__Impl rule__Model__Group_2__1 ;
    public final void rule__Model__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:575:1: ( rule__Model__Group_2__0__Impl rule__Model__Group_2__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:576:2: rule__Model__Group_2__0__Impl rule__Model__Group_2__1
            {
            pushFollow(FOLLOW_rule__Model__Group_2__0__Impl_in_rule__Model__Group_2__01125);
            rule__Model__Group_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Model__Group_2__1_in_rule__Model__Group_2__01128);
            rule__Model__Group_2__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Model__Group_2__0"


    // $ANTLR start "rule__Model__Group_2__0__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:583:1: rule__Model__Group_2__0__Impl : ( KEYWORD_9 ) ;
    public final void rule__Model__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:587:1: ( ( KEYWORD_9 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:588:1: ( KEYWORD_9 )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:588:1: ( KEYWORD_9 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:589:1: KEYWORD_9
            {
             before(grammarAccess.getModelAccess().getORDERBYKeyword_2_0()); 
            match(input,KEYWORD_9,FOLLOW_KEYWORD_9_in_rule__Model__Group_2__0__Impl1156); 
             after(grammarAccess.getModelAccess().getORDERBYKeyword_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Model__Group_2__0__Impl"


    // $ANTLR start "rule__Model__Group_2__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:602:1: rule__Model__Group_2__1 : rule__Model__Group_2__1__Impl ;
    public final void rule__Model__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:606:1: ( rule__Model__Group_2__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:607:2: rule__Model__Group_2__1__Impl
            {
            pushFollow(FOLLOW_rule__Model__Group_2__1__Impl_in_rule__Model__Group_2__11187);
            rule__Model__Group_2__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Model__Group_2__1"


    // $ANTLR start "rule__Model__Group_2__1__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:613:1: rule__Model__Group_2__1__Impl : ( ( rule__Model__OrderByEntryAssignment_2_1 ) ) ;
    public final void rule__Model__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:617:1: ( ( ( rule__Model__OrderByEntryAssignment_2_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:618:1: ( ( rule__Model__OrderByEntryAssignment_2_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:618:1: ( ( rule__Model__OrderByEntryAssignment_2_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:619:1: ( rule__Model__OrderByEntryAssignment_2_1 )
            {
             before(grammarAccess.getModelAccess().getOrderByEntryAssignment_2_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:620:1: ( rule__Model__OrderByEntryAssignment_2_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:620:2: rule__Model__OrderByEntryAssignment_2_1
            {
            pushFollow(FOLLOW_rule__Model__OrderByEntryAssignment_2_1_in_rule__Model__Group_2__1__Impl1214);
            rule__Model__OrderByEntryAssignment_2_1();

            state._fsp--;


            }

             after(grammarAccess.getModelAccess().getOrderByEntryAssignment_2_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Model__Group_2__1__Impl"


    // $ANTLR start "rule__Select__Group__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:634:1: rule__Select__Group__0 : rule__Select__Group__0__Impl rule__Select__Group__1 ;
    public final void rule__Select__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:638:1: ( rule__Select__Group__0__Impl rule__Select__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:639:2: rule__Select__Group__0__Impl rule__Select__Group__1
            {
            pushFollow(FOLLOW_rule__Select__Group__0__Impl_in_rule__Select__Group__01248);
            rule__Select__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Select__Group__1_in_rule__Select__Group__01251);
            rule__Select__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Select__Group__0"


    // $ANTLR start "rule__Select__Group__0__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:646:1: rule__Select__Group__0__Impl : ( ( rule__Select__SelectAssignment_0 ) ) ;
    public final void rule__Select__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:650:1: ( ( ( rule__Select__SelectAssignment_0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:651:1: ( ( rule__Select__SelectAssignment_0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:651:1: ( ( rule__Select__SelectAssignment_0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:652:1: ( rule__Select__SelectAssignment_0 )
            {
             before(grammarAccess.getSelectAccess().getSelectAssignment_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:653:1: ( rule__Select__SelectAssignment_0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:653:2: rule__Select__SelectAssignment_0
            {
            pushFollow(FOLLOW_rule__Select__SelectAssignment_0_in_rule__Select__Group__0__Impl1278);
            rule__Select__SelectAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getSelectAccess().getSelectAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Select__Group__0__Impl"


    // $ANTLR start "rule__Select__Group__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:663:1: rule__Select__Group__1 : rule__Select__Group__1__Impl rule__Select__Group__2 ;
    public final void rule__Select__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:667:1: ( rule__Select__Group__1__Impl rule__Select__Group__2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:668:2: rule__Select__Group__1__Impl rule__Select__Group__2
            {
            pushFollow(FOLLOW_rule__Select__Group__1__Impl_in_rule__Select__Group__11308);
            rule__Select__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Select__Group__2_in_rule__Select__Group__11311);
            rule__Select__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Select__Group__1"


    // $ANTLR start "rule__Select__Group__1__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:675:1: rule__Select__Group__1__Impl : ( ( KEYWORD_8 )? ) ;
    public final void rule__Select__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:679:1: ( ( ( KEYWORD_8 )? ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:680:1: ( ( KEYWORD_8 )? )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:680:1: ( ( KEYWORD_8 )? )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:681:1: ( KEYWORD_8 )?
            {
             before(grammarAccess.getSelectAccess().getDISTINCTKeyword_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:682:1: ( KEYWORD_8 )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==KEYWORD_8) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:683:2: KEYWORD_8
                    {
                    match(input,KEYWORD_8,FOLLOW_KEYWORD_8_in_rule__Select__Group__1__Impl1340); 

                    }
                    break;

            }

             after(grammarAccess.getSelectAccess().getDISTINCTKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Select__Group__1__Impl"


    // $ANTLR start "rule__Select__Group__2"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:694:1: rule__Select__Group__2 : rule__Select__Group__2__Impl ;
    public final void rule__Select__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:698:1: ( rule__Select__Group__2__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:699:2: rule__Select__Group__2__Impl
            {
            pushFollow(FOLLOW_rule__Select__Group__2__Impl_in_rule__Select__Group__21373);
            rule__Select__Group__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Select__Group__2"


    // $ANTLR start "rule__Select__Group__2__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:705:1: rule__Select__Group__2__Impl : ( ( rule__Select__ColsAssignment_2 ) ) ;
    public final void rule__Select__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:709:1: ( ( ( rule__Select__ColsAssignment_2 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:710:1: ( ( rule__Select__ColsAssignment_2 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:710:1: ( ( rule__Select__ColsAssignment_2 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:711:1: ( rule__Select__ColsAssignment_2 )
            {
             before(grammarAccess.getSelectAccess().getColsAssignment_2()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:712:1: ( rule__Select__ColsAssignment_2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:712:2: rule__Select__ColsAssignment_2
            {
            pushFollow(FOLLOW_rule__Select__ColsAssignment_2_in_rule__Select__Group__2__Impl1400);
            rule__Select__ColsAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getSelectAccess().getColsAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Select__Group__2__Impl"


    // $ANTLR start "rule__Columns__Group__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:728:1: rule__Columns__Group__0 : rule__Columns__Group__0__Impl rule__Columns__Group__1 ;
    public final void rule__Columns__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:732:1: ( rule__Columns__Group__0__Impl rule__Columns__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:733:2: rule__Columns__Group__0__Impl rule__Columns__Group__1
            {
            pushFollow(FOLLOW_rule__Columns__Group__0__Impl_in_rule__Columns__Group__01436);
            rule__Columns__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Columns__Group__1_in_rule__Columns__Group__01439);
            rule__Columns__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Columns__Group__0"


    // $ANTLR start "rule__Columns__Group__0__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:740:1: rule__Columns__Group__0__Impl : ( ruleColumnOrAlias ) ;
    public final void rule__Columns__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:744:1: ( ( ruleColumnOrAlias ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:745:1: ( ruleColumnOrAlias )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:745:1: ( ruleColumnOrAlias )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:746:1: ruleColumnOrAlias
            {
             before(grammarAccess.getColumnsAccess().getColumnOrAliasParserRuleCall_0()); 
            pushFollow(FOLLOW_ruleColumnOrAlias_in_rule__Columns__Group__0__Impl1466);
            ruleColumnOrAlias();

            state._fsp--;

             after(grammarAccess.getColumnsAccess().getColumnOrAliasParserRuleCall_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Columns__Group__0__Impl"


    // $ANTLR start "rule__Columns__Group__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:757:1: rule__Columns__Group__1 : rule__Columns__Group__1__Impl ;
    public final void rule__Columns__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:761:1: ( rule__Columns__Group__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:762:2: rule__Columns__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__Columns__Group__1__Impl_in_rule__Columns__Group__11495);
            rule__Columns__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Columns__Group__1"


    // $ANTLR start "rule__Columns__Group__1__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:768:1: rule__Columns__Group__1__Impl : ( ( rule__Columns__Group_1__0 )? ) ;
    public final void rule__Columns__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:772:1: ( ( ( rule__Columns__Group_1__0 )? ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:773:1: ( ( rule__Columns__Group_1__0 )? )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:773:1: ( ( rule__Columns__Group_1__0 )? )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:774:1: ( rule__Columns__Group_1__0 )?
            {
             before(grammarAccess.getColumnsAccess().getGroup_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:775:1: ( rule__Columns__Group_1__0 )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==KEYWORD_1) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:775:2: rule__Columns__Group_1__0
                    {
                    pushFollow(FOLLOW_rule__Columns__Group_1__0_in_rule__Columns__Group__1__Impl1522);
                    rule__Columns__Group_1__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getColumnsAccess().getGroup_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Columns__Group__1__Impl"


    // $ANTLR start "rule__Columns__Group_1__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:789:1: rule__Columns__Group_1__0 : rule__Columns__Group_1__0__Impl rule__Columns__Group_1__1 ;
    public final void rule__Columns__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:793:1: ( rule__Columns__Group_1__0__Impl rule__Columns__Group_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:794:2: rule__Columns__Group_1__0__Impl rule__Columns__Group_1__1
            {
            pushFollow(FOLLOW_rule__Columns__Group_1__0__Impl_in_rule__Columns__Group_1__01557);
            rule__Columns__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Columns__Group_1__1_in_rule__Columns__Group_1__01560);
            rule__Columns__Group_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Columns__Group_1__0"


    // $ANTLR start "rule__Columns__Group_1__0__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:801:1: rule__Columns__Group_1__0__Impl : ( () ) ;
    public final void rule__Columns__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:805:1: ( ( () ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:806:1: ( () )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:806:1: ( () )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:807:1: ()
            {
             before(grammarAccess.getColumnsAccess().getOrColumnEntriesAction_1_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:808:1: ()
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:810:1: 
            {
            }

             after(grammarAccess.getColumnsAccess().getOrColumnEntriesAction_1_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Columns__Group_1__0__Impl"


    // $ANTLR start "rule__Columns__Group_1__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:820:1: rule__Columns__Group_1__1 : rule__Columns__Group_1__1__Impl ;
    public final void rule__Columns__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:824:1: ( rule__Columns__Group_1__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:825:2: rule__Columns__Group_1__1__Impl
            {
            pushFollow(FOLLOW_rule__Columns__Group_1__1__Impl_in_rule__Columns__Group_1__11618);
            rule__Columns__Group_1__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Columns__Group_1__1"


    // $ANTLR start "rule__Columns__Group_1__1__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:831:1: rule__Columns__Group_1__1__Impl : ( ( ( rule__Columns__Group_1_1__0 ) ) ( ( rule__Columns__Group_1_1__0 )* ) ) ;
    public final void rule__Columns__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:835:1: ( ( ( ( rule__Columns__Group_1_1__0 ) ) ( ( rule__Columns__Group_1_1__0 )* ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:836:1: ( ( ( rule__Columns__Group_1_1__0 ) ) ( ( rule__Columns__Group_1_1__0 )* ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:836:1: ( ( ( rule__Columns__Group_1_1__0 ) ) ( ( rule__Columns__Group_1_1__0 )* ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:837:1: ( ( rule__Columns__Group_1_1__0 ) ) ( ( rule__Columns__Group_1_1__0 )* )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:837:1: ( ( rule__Columns__Group_1_1__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:838:1: ( rule__Columns__Group_1_1__0 )
            {
             before(grammarAccess.getColumnsAccess().getGroup_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:839:1: ( rule__Columns__Group_1_1__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:839:2: rule__Columns__Group_1_1__0
            {
            pushFollow(FOLLOW_rule__Columns__Group_1_1__0_in_rule__Columns__Group_1__1__Impl1647);
            rule__Columns__Group_1_1__0();

            state._fsp--;


            }

             after(grammarAccess.getColumnsAccess().getGroup_1_1()); 

            }

            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:842:1: ( ( rule__Columns__Group_1_1__0 )* )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:843:1: ( rule__Columns__Group_1_1__0 )*
            {
             before(grammarAccess.getColumnsAccess().getGroup_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:844:1: ( rule__Columns__Group_1_1__0 )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==KEYWORD_1) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:844:2: rule__Columns__Group_1_1__0
            	    {
            	    pushFollow(FOLLOW_rule__Columns__Group_1_1__0_in_rule__Columns__Group_1__1__Impl1659);
            	    rule__Columns__Group_1_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);

             after(grammarAccess.getColumnsAccess().getGroup_1_1()); 

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Columns__Group_1__1__Impl"


    // $ANTLR start "rule__Columns__Group_1_1__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:859:1: rule__Columns__Group_1_1__0 : rule__Columns__Group_1_1__0__Impl rule__Columns__Group_1_1__1 ;
    public final void rule__Columns__Group_1_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:863:1: ( rule__Columns__Group_1_1__0__Impl rule__Columns__Group_1_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:864:2: rule__Columns__Group_1_1__0__Impl rule__Columns__Group_1_1__1
            {
            pushFollow(FOLLOW_rule__Columns__Group_1_1__0__Impl_in_rule__Columns__Group_1_1__01696);
            rule__Columns__Group_1_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Columns__Group_1_1__1_in_rule__Columns__Group_1_1__01699);
            rule__Columns__Group_1_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Columns__Group_1_1__0"


    // $ANTLR start "rule__Columns__Group_1_1__0__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:871:1: rule__Columns__Group_1_1__0__Impl : ( KEYWORD_1 ) ;
    public final void rule__Columns__Group_1_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:875:1: ( ( KEYWORD_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:876:1: ( KEYWORD_1 )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:876:1: ( KEYWORD_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:877:1: KEYWORD_1
            {
             before(grammarAccess.getColumnsAccess().getCommaKeyword_1_1_0()); 
            match(input,KEYWORD_1,FOLLOW_KEYWORD_1_in_rule__Columns__Group_1_1__0__Impl1727); 
             after(grammarAccess.getColumnsAccess().getCommaKeyword_1_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Columns__Group_1_1__0__Impl"


    // $ANTLR start "rule__Columns__Group_1_1__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:890:1: rule__Columns__Group_1_1__1 : rule__Columns__Group_1_1__1__Impl ;
    public final void rule__Columns__Group_1_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:894:1: ( rule__Columns__Group_1_1__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:895:2: rule__Columns__Group_1_1__1__Impl
            {
            pushFollow(FOLLOW_rule__Columns__Group_1_1__1__Impl_in_rule__Columns__Group_1_1__11758);
            rule__Columns__Group_1_1__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Columns__Group_1_1__1"


    // $ANTLR start "rule__Columns__Group_1_1__1__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:901:1: rule__Columns__Group_1_1__1__Impl : ( ( rule__Columns__EntriesAssignment_1_1_1 ) ) ;
    public final void rule__Columns__Group_1_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:905:1: ( ( ( rule__Columns__EntriesAssignment_1_1_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:906:1: ( ( rule__Columns__EntriesAssignment_1_1_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:906:1: ( ( rule__Columns__EntriesAssignment_1_1_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:907:1: ( rule__Columns__EntriesAssignment_1_1_1 )
            {
             before(grammarAccess.getColumnsAccess().getEntriesAssignment_1_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:908:1: ( rule__Columns__EntriesAssignment_1_1_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:908:2: rule__Columns__EntriesAssignment_1_1_1
            {
            pushFollow(FOLLOW_rule__Columns__EntriesAssignment_1_1_1_in_rule__Columns__Group_1_1__1__Impl1785);
            rule__Columns__EntriesAssignment_1_1_1();

            state._fsp--;


            }

             after(grammarAccess.getColumnsAccess().getEntriesAssignment_1_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Columns__Group_1_1__1__Impl"


    // $ANTLR start "rule__ColumnOrAlias__Group_0__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:922:1: rule__ColumnOrAlias__Group_0__0 : rule__ColumnOrAlias__Group_0__0__Impl rule__ColumnOrAlias__Group_0__1 ;
    public final void rule__ColumnOrAlias__Group_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:926:1: ( rule__ColumnOrAlias__Group_0__0__Impl rule__ColumnOrAlias__Group_0__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:927:2: rule__ColumnOrAlias__Group_0__0__Impl rule__ColumnOrAlias__Group_0__1
            {
            pushFollow(FOLLOW_rule__ColumnOrAlias__Group_0__0__Impl_in_rule__ColumnOrAlias__Group_0__01819);
            rule__ColumnOrAlias__Group_0__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ColumnOrAlias__Group_0__1_in_rule__ColumnOrAlias__Group_0__01822);
            rule__ColumnOrAlias__Group_0__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnOrAlias__Group_0__0"


    // $ANTLR start "rule__ColumnOrAlias__Group_0__0__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:934:1: rule__ColumnOrAlias__Group_0__0__Impl : ( ruleColumnFull ) ;
    public final void rule__ColumnOrAlias__Group_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:938:1: ( ( ruleColumnFull ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:939:1: ( ruleColumnFull )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:939:1: ( ruleColumnFull )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:940:1: ruleColumnFull
            {
             before(grammarAccess.getColumnOrAliasAccess().getColumnFullParserRuleCall_0_0()); 
            pushFollow(FOLLOW_ruleColumnFull_in_rule__ColumnOrAlias__Group_0__0__Impl1849);
            ruleColumnFull();

            state._fsp--;

             after(grammarAccess.getColumnOrAliasAccess().getColumnFullParserRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnOrAlias__Group_0__0__Impl"


    // $ANTLR start "rule__ColumnOrAlias__Group_0__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:951:1: rule__ColumnOrAlias__Group_0__1 : rule__ColumnOrAlias__Group_0__1__Impl rule__ColumnOrAlias__Group_0__2 ;
    public final void rule__ColumnOrAlias__Group_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:955:1: ( rule__ColumnOrAlias__Group_0__1__Impl rule__ColumnOrAlias__Group_0__2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:956:2: rule__ColumnOrAlias__Group_0__1__Impl rule__ColumnOrAlias__Group_0__2
            {
            pushFollow(FOLLOW_rule__ColumnOrAlias__Group_0__1__Impl_in_rule__ColumnOrAlias__Group_0__11878);
            rule__ColumnOrAlias__Group_0__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ColumnOrAlias__Group_0__2_in_rule__ColumnOrAlias__Group_0__11881);
            rule__ColumnOrAlias__Group_0__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnOrAlias__Group_0__1"


    // $ANTLR start "rule__ColumnOrAlias__Group_0__1__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:963:1: rule__ColumnOrAlias__Group_0__1__Impl : ( ( KEYWORD_3 )? ) ;
    public final void rule__ColumnOrAlias__Group_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:967:1: ( ( ( KEYWORD_3 )? ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:968:1: ( ( KEYWORD_3 )? )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:968:1: ( ( KEYWORD_3 )? )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:969:1: ( KEYWORD_3 )?
            {
             before(grammarAccess.getColumnOrAliasAccess().getASKeyword_0_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:970:1: ( KEYWORD_3 )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==KEYWORD_3) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:971:2: KEYWORD_3
                    {
                    match(input,KEYWORD_3,FOLLOW_KEYWORD_3_in_rule__ColumnOrAlias__Group_0__1__Impl1910); 

                    }
                    break;

            }

             after(grammarAccess.getColumnOrAliasAccess().getASKeyword_0_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnOrAlias__Group_0__1__Impl"


    // $ANTLR start "rule__ColumnOrAlias__Group_0__2"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:982:1: rule__ColumnOrAlias__Group_0__2 : rule__ColumnOrAlias__Group_0__2__Impl ;
    public final void rule__ColumnOrAlias__Group_0__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:986:1: ( rule__ColumnOrAlias__Group_0__2__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:987:2: rule__ColumnOrAlias__Group_0__2__Impl
            {
            pushFollow(FOLLOW_rule__ColumnOrAlias__Group_0__2__Impl_in_rule__ColumnOrAlias__Group_0__21943);
            rule__ColumnOrAlias__Group_0__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnOrAlias__Group_0__2"


    // $ANTLR start "rule__ColumnOrAlias__Group_0__2__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:993:1: rule__ColumnOrAlias__Group_0__2__Impl : ( ( rule__ColumnOrAlias__ColAliasAssignment_0_2 )? ) ;
    public final void rule__ColumnOrAlias__Group_0__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:997:1: ( ( ( rule__ColumnOrAlias__ColAliasAssignment_0_2 )? ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:998:1: ( ( rule__ColumnOrAlias__ColAliasAssignment_0_2 )? )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:998:1: ( ( rule__ColumnOrAlias__ColAliasAssignment_0_2 )? )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:999:1: ( rule__ColumnOrAlias__ColAliasAssignment_0_2 )?
            {
             before(grammarAccess.getColumnOrAliasAccess().getColAliasAssignment_0_2()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1000:1: ( rule__ColumnOrAlias__ColAliasAssignment_0_2 )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==RULE_ID) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1000:2: rule__ColumnOrAlias__ColAliasAssignment_0_2
                    {
                    pushFollow(FOLLOW_rule__ColumnOrAlias__ColAliasAssignment_0_2_in_rule__ColumnOrAlias__Group_0__2__Impl1970);
                    rule__ColumnOrAlias__ColAliasAssignment_0_2();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getColumnOrAliasAccess().getColAliasAssignment_0_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnOrAlias__Group_0__2__Impl"


    // $ANTLR start "rule__ColumnFull__Group__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1016:1: rule__ColumnFull__Group__0 : rule__ColumnFull__Group__0__Impl rule__ColumnFull__Group__1 ;
    public final void rule__ColumnFull__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1020:1: ( rule__ColumnFull__Group__0__Impl rule__ColumnFull__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1021:2: rule__ColumnFull__Group__0__Impl rule__ColumnFull__Group__1
            {
            pushFollow(FOLLOW_rule__ColumnFull__Group__0__Impl_in_rule__ColumnFull__Group__02007);
            rule__ColumnFull__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ColumnFull__Group__1_in_rule__ColumnFull__Group__02010);
            rule__ColumnFull__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnFull__Group__0"


    // $ANTLR start "rule__ColumnFull__Group__0__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1028:1: rule__ColumnFull__Group__0__Impl : ( ruleDbObjectName ) ;
    public final void rule__ColumnFull__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1032:1: ( ( ruleDbObjectName ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1033:1: ( ruleDbObjectName )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1033:1: ( ruleDbObjectName )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1034:1: ruleDbObjectName
            {
             before(grammarAccess.getColumnFullAccess().getDbObjectNameParserRuleCall_0()); 
            pushFollow(FOLLOW_ruleDbObjectName_in_rule__ColumnFull__Group__0__Impl2037);
            ruleDbObjectName();

            state._fsp--;

             after(grammarAccess.getColumnFullAccess().getDbObjectNameParserRuleCall_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnFull__Group__0__Impl"


    // $ANTLR start "rule__ColumnFull__Group__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1045:1: rule__ColumnFull__Group__1 : rule__ColumnFull__Group__1__Impl ;
    public final void rule__ColumnFull__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1049:1: ( rule__ColumnFull__Group__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1050:2: rule__ColumnFull__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__ColumnFull__Group__1__Impl_in_rule__ColumnFull__Group__12066);
            rule__ColumnFull__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnFull__Group__1"


    // $ANTLR start "rule__ColumnFull__Group__1__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1056:1: rule__ColumnFull__Group__1__Impl : ( ( rule__ColumnFull__Group_1__0 )? ) ;
    public final void rule__ColumnFull__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1060:1: ( ( ( rule__ColumnFull__Group_1__0 )? ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1061:1: ( ( rule__ColumnFull__Group_1__0 )? )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1061:1: ( ( rule__ColumnFull__Group_1__0 )? )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1062:1: ( rule__ColumnFull__Group_1__0 )?
            {
             before(grammarAccess.getColumnFullAccess().getGroup_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1063:1: ( rule__ColumnFull__Group_1__0 )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==KEYWORD_2) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1063:2: rule__ColumnFull__Group_1__0
                    {
                    pushFollow(FOLLOW_rule__ColumnFull__Group_1__0_in_rule__ColumnFull__Group__1__Impl2093);
                    rule__ColumnFull__Group_1__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getColumnFullAccess().getGroup_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnFull__Group__1__Impl"


    // $ANTLR start "rule__ColumnFull__Group_1__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1077:1: rule__ColumnFull__Group_1__0 : rule__ColumnFull__Group_1__0__Impl rule__ColumnFull__Group_1__1 ;
    public final void rule__ColumnFull__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1081:1: ( rule__ColumnFull__Group_1__0__Impl rule__ColumnFull__Group_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1082:2: rule__ColumnFull__Group_1__0__Impl rule__ColumnFull__Group_1__1
            {
            pushFollow(FOLLOW_rule__ColumnFull__Group_1__0__Impl_in_rule__ColumnFull__Group_1__02128);
            rule__ColumnFull__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ColumnFull__Group_1__1_in_rule__ColumnFull__Group_1__02131);
            rule__ColumnFull__Group_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnFull__Group_1__0"


    // $ANTLR start "rule__ColumnFull__Group_1__0__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1089:1: rule__ColumnFull__Group_1__0__Impl : ( () ) ;
    public final void rule__ColumnFull__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1093:1: ( ( () ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1094:1: ( () )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1094:1: ( () )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1095:1: ()
            {
             before(grammarAccess.getColumnFullAccess().getColEntriesAction_1_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1096:1: ()
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1098:1: 
            {
            }

             after(grammarAccess.getColumnFullAccess().getColEntriesAction_1_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnFull__Group_1__0__Impl"


    // $ANTLR start "rule__ColumnFull__Group_1__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1108:1: rule__ColumnFull__Group_1__1 : rule__ColumnFull__Group_1__1__Impl ;
    public final void rule__ColumnFull__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1112:1: ( rule__ColumnFull__Group_1__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1113:2: rule__ColumnFull__Group_1__1__Impl
            {
            pushFollow(FOLLOW_rule__ColumnFull__Group_1__1__Impl_in_rule__ColumnFull__Group_1__12189);
            rule__ColumnFull__Group_1__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnFull__Group_1__1"


    // $ANTLR start "rule__ColumnFull__Group_1__1__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1119:1: rule__ColumnFull__Group_1__1__Impl : ( ( ( rule__ColumnFull__Group_1_1__0 ) ) ( ( rule__ColumnFull__Group_1_1__0 )* ) ) ;
    public final void rule__ColumnFull__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1123:1: ( ( ( ( rule__ColumnFull__Group_1_1__0 ) ) ( ( rule__ColumnFull__Group_1_1__0 )* ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1124:1: ( ( ( rule__ColumnFull__Group_1_1__0 ) ) ( ( rule__ColumnFull__Group_1_1__0 )* ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1124:1: ( ( ( rule__ColumnFull__Group_1_1__0 ) ) ( ( rule__ColumnFull__Group_1_1__0 )* ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1125:1: ( ( rule__ColumnFull__Group_1_1__0 ) ) ( ( rule__ColumnFull__Group_1_1__0 )* )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1125:1: ( ( rule__ColumnFull__Group_1_1__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1126:1: ( rule__ColumnFull__Group_1_1__0 )
            {
             before(grammarAccess.getColumnFullAccess().getGroup_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1127:1: ( rule__ColumnFull__Group_1_1__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1127:2: rule__ColumnFull__Group_1_1__0
            {
            pushFollow(FOLLOW_rule__ColumnFull__Group_1_1__0_in_rule__ColumnFull__Group_1__1__Impl2218);
            rule__ColumnFull__Group_1_1__0();

            state._fsp--;


            }

             after(grammarAccess.getColumnFullAccess().getGroup_1_1()); 

            }

            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1130:1: ( ( rule__ColumnFull__Group_1_1__0 )* )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1131:1: ( rule__ColumnFull__Group_1_1__0 )*
            {
             before(grammarAccess.getColumnFullAccess().getGroup_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1132:1: ( rule__ColumnFull__Group_1_1__0 )*
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( (LA12_0==KEYWORD_2) ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1132:2: rule__ColumnFull__Group_1_1__0
            	    {
            	    pushFollow(FOLLOW_rule__ColumnFull__Group_1_1__0_in_rule__ColumnFull__Group_1__1__Impl2230);
            	    rule__ColumnFull__Group_1_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop12;
                }
            } while (true);

             after(grammarAccess.getColumnFullAccess().getGroup_1_1()); 

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnFull__Group_1__1__Impl"


    // $ANTLR start "rule__ColumnFull__Group_1_1__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1147:1: rule__ColumnFull__Group_1_1__0 : rule__ColumnFull__Group_1_1__0__Impl rule__ColumnFull__Group_1_1__1 ;
    public final void rule__ColumnFull__Group_1_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1151:1: ( rule__ColumnFull__Group_1_1__0__Impl rule__ColumnFull__Group_1_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1152:2: rule__ColumnFull__Group_1_1__0__Impl rule__ColumnFull__Group_1_1__1
            {
            pushFollow(FOLLOW_rule__ColumnFull__Group_1_1__0__Impl_in_rule__ColumnFull__Group_1_1__02267);
            rule__ColumnFull__Group_1_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ColumnFull__Group_1_1__1_in_rule__ColumnFull__Group_1_1__02270);
            rule__ColumnFull__Group_1_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnFull__Group_1_1__0"


    // $ANTLR start "rule__ColumnFull__Group_1_1__0__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1159:1: rule__ColumnFull__Group_1_1__0__Impl : ( KEYWORD_2 ) ;
    public final void rule__ColumnFull__Group_1_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1163:1: ( ( KEYWORD_2 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1164:1: ( KEYWORD_2 )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1164:1: ( KEYWORD_2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1165:1: KEYWORD_2
            {
             before(grammarAccess.getColumnFullAccess().getFullStopKeyword_1_1_0()); 
            match(input,KEYWORD_2,FOLLOW_KEYWORD_2_in_rule__ColumnFull__Group_1_1__0__Impl2298); 
             after(grammarAccess.getColumnFullAccess().getFullStopKeyword_1_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnFull__Group_1_1__0__Impl"


    // $ANTLR start "rule__ColumnFull__Group_1_1__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1178:1: rule__ColumnFull__Group_1_1__1 : rule__ColumnFull__Group_1_1__1__Impl ;
    public final void rule__ColumnFull__Group_1_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1182:1: ( rule__ColumnFull__Group_1_1__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1183:2: rule__ColumnFull__Group_1_1__1__Impl
            {
            pushFollow(FOLLOW_rule__ColumnFull__Group_1_1__1__Impl_in_rule__ColumnFull__Group_1_1__12329);
            rule__ColumnFull__Group_1_1__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnFull__Group_1_1__1"


    // $ANTLR start "rule__ColumnFull__Group_1_1__1__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1189:1: rule__ColumnFull__Group_1_1__1__Impl : ( ( rule__ColumnFull__EntriesAssignment_1_1_1 ) ) ;
    public final void rule__ColumnFull__Group_1_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1193:1: ( ( ( rule__ColumnFull__EntriesAssignment_1_1_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1194:1: ( ( rule__ColumnFull__EntriesAssignment_1_1_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1194:1: ( ( rule__ColumnFull__EntriesAssignment_1_1_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1195:1: ( rule__ColumnFull__EntriesAssignment_1_1_1 )
            {
             before(grammarAccess.getColumnFullAccess().getEntriesAssignment_1_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1196:1: ( rule__ColumnFull__EntriesAssignment_1_1_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1196:2: rule__ColumnFull__EntriesAssignment_1_1_1
            {
            pushFollow(FOLLOW_rule__ColumnFull__EntriesAssignment_1_1_1_in_rule__ColumnFull__Group_1_1__1__Impl2356);
            rule__ColumnFull__EntriesAssignment_1_1_1();

            state._fsp--;


            }

             after(grammarAccess.getColumnFullAccess().getEntriesAssignment_1_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnFull__Group_1_1__1__Impl"


    // $ANTLR start "rule__OrderByColumns__Group__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1210:1: rule__OrderByColumns__Group__0 : rule__OrderByColumns__Group__0__Impl rule__OrderByColumns__Group__1 ;
    public final void rule__OrderByColumns__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1214:1: ( rule__OrderByColumns__Group__0__Impl rule__OrderByColumns__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1215:2: rule__OrderByColumns__Group__0__Impl rule__OrderByColumns__Group__1
            {
            pushFollow(FOLLOW_rule__OrderByColumns__Group__0__Impl_in_rule__OrderByColumns__Group__02390);
            rule__OrderByColumns__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__OrderByColumns__Group__1_in_rule__OrderByColumns__Group__02393);
            rule__OrderByColumns__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OrderByColumns__Group__0"


    // $ANTLR start "rule__OrderByColumns__Group__0__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1222:1: rule__OrderByColumns__Group__0__Impl : ( ruleOrderByColumnFull ) ;
    public final void rule__OrderByColumns__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1226:1: ( ( ruleOrderByColumnFull ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1227:1: ( ruleOrderByColumnFull )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1227:1: ( ruleOrderByColumnFull )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1228:1: ruleOrderByColumnFull
            {
             before(grammarAccess.getOrderByColumnsAccess().getOrderByColumnFullParserRuleCall_0()); 
            pushFollow(FOLLOW_ruleOrderByColumnFull_in_rule__OrderByColumns__Group__0__Impl2420);
            ruleOrderByColumnFull();

            state._fsp--;

             after(grammarAccess.getOrderByColumnsAccess().getOrderByColumnFullParserRuleCall_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OrderByColumns__Group__0__Impl"


    // $ANTLR start "rule__OrderByColumns__Group__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1239:1: rule__OrderByColumns__Group__1 : rule__OrderByColumns__Group__1__Impl ;
    public final void rule__OrderByColumns__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1243:1: ( rule__OrderByColumns__Group__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1244:2: rule__OrderByColumns__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__OrderByColumns__Group__1__Impl_in_rule__OrderByColumns__Group__12449);
            rule__OrderByColumns__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OrderByColumns__Group__1"


    // $ANTLR start "rule__OrderByColumns__Group__1__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1250:1: rule__OrderByColumns__Group__1__Impl : ( ( rule__OrderByColumns__Group_1__0 )? ) ;
    public final void rule__OrderByColumns__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1254:1: ( ( ( rule__OrderByColumns__Group_1__0 )? ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1255:1: ( ( rule__OrderByColumns__Group_1__0 )? )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1255:1: ( ( rule__OrderByColumns__Group_1__0 )? )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1256:1: ( rule__OrderByColumns__Group_1__0 )?
            {
             before(grammarAccess.getOrderByColumnsAccess().getGroup_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1257:1: ( rule__OrderByColumns__Group_1__0 )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==KEYWORD_1) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1257:2: rule__OrderByColumns__Group_1__0
                    {
                    pushFollow(FOLLOW_rule__OrderByColumns__Group_1__0_in_rule__OrderByColumns__Group__1__Impl2476);
                    rule__OrderByColumns__Group_1__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getOrderByColumnsAccess().getGroup_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OrderByColumns__Group__1__Impl"


    // $ANTLR start "rule__OrderByColumns__Group_1__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1271:1: rule__OrderByColumns__Group_1__0 : rule__OrderByColumns__Group_1__0__Impl rule__OrderByColumns__Group_1__1 ;
    public final void rule__OrderByColumns__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1275:1: ( rule__OrderByColumns__Group_1__0__Impl rule__OrderByColumns__Group_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1276:2: rule__OrderByColumns__Group_1__0__Impl rule__OrderByColumns__Group_1__1
            {
            pushFollow(FOLLOW_rule__OrderByColumns__Group_1__0__Impl_in_rule__OrderByColumns__Group_1__02511);
            rule__OrderByColumns__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__OrderByColumns__Group_1__1_in_rule__OrderByColumns__Group_1__02514);
            rule__OrderByColumns__Group_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OrderByColumns__Group_1__0"


    // $ANTLR start "rule__OrderByColumns__Group_1__0__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1283:1: rule__OrderByColumns__Group_1__0__Impl : ( () ) ;
    public final void rule__OrderByColumns__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1287:1: ( ( () ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1288:1: ( () )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1288:1: ( () )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1289:1: ()
            {
             before(grammarAccess.getOrderByColumnsAccess().getOrOrderByColumnEntriesAction_1_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1290:1: ()
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1292:1: 
            {
            }

             after(grammarAccess.getOrderByColumnsAccess().getOrOrderByColumnEntriesAction_1_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OrderByColumns__Group_1__0__Impl"


    // $ANTLR start "rule__OrderByColumns__Group_1__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1302:1: rule__OrderByColumns__Group_1__1 : rule__OrderByColumns__Group_1__1__Impl ;
    public final void rule__OrderByColumns__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1306:1: ( rule__OrderByColumns__Group_1__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1307:2: rule__OrderByColumns__Group_1__1__Impl
            {
            pushFollow(FOLLOW_rule__OrderByColumns__Group_1__1__Impl_in_rule__OrderByColumns__Group_1__12572);
            rule__OrderByColumns__Group_1__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OrderByColumns__Group_1__1"


    // $ANTLR start "rule__OrderByColumns__Group_1__1__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1313:1: rule__OrderByColumns__Group_1__1__Impl : ( ( ( rule__OrderByColumns__Group_1_1__0 ) ) ( ( rule__OrderByColumns__Group_1_1__0 )* ) ) ;
    public final void rule__OrderByColumns__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1317:1: ( ( ( ( rule__OrderByColumns__Group_1_1__0 ) ) ( ( rule__OrderByColumns__Group_1_1__0 )* ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1318:1: ( ( ( rule__OrderByColumns__Group_1_1__0 ) ) ( ( rule__OrderByColumns__Group_1_1__0 )* ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1318:1: ( ( ( rule__OrderByColumns__Group_1_1__0 ) ) ( ( rule__OrderByColumns__Group_1_1__0 )* ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1319:1: ( ( rule__OrderByColumns__Group_1_1__0 ) ) ( ( rule__OrderByColumns__Group_1_1__0 )* )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1319:1: ( ( rule__OrderByColumns__Group_1_1__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1320:1: ( rule__OrderByColumns__Group_1_1__0 )
            {
             before(grammarAccess.getOrderByColumnsAccess().getGroup_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1321:1: ( rule__OrderByColumns__Group_1_1__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1321:2: rule__OrderByColumns__Group_1_1__0
            {
            pushFollow(FOLLOW_rule__OrderByColumns__Group_1_1__0_in_rule__OrderByColumns__Group_1__1__Impl2601);
            rule__OrderByColumns__Group_1_1__0();

            state._fsp--;


            }

             after(grammarAccess.getOrderByColumnsAccess().getGroup_1_1()); 

            }

            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1324:1: ( ( rule__OrderByColumns__Group_1_1__0 )* )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1325:1: ( rule__OrderByColumns__Group_1_1__0 )*
            {
             before(grammarAccess.getOrderByColumnsAccess().getGroup_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1326:1: ( rule__OrderByColumns__Group_1_1__0 )*
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( (LA14_0==KEYWORD_1) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1326:2: rule__OrderByColumns__Group_1_1__0
            	    {
            	    pushFollow(FOLLOW_rule__OrderByColumns__Group_1_1__0_in_rule__OrderByColumns__Group_1__1__Impl2613);
            	    rule__OrderByColumns__Group_1_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop14;
                }
            } while (true);

             after(grammarAccess.getOrderByColumnsAccess().getGroup_1_1()); 

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OrderByColumns__Group_1__1__Impl"


    // $ANTLR start "rule__OrderByColumns__Group_1_1__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1341:1: rule__OrderByColumns__Group_1_1__0 : rule__OrderByColumns__Group_1_1__0__Impl rule__OrderByColumns__Group_1_1__1 ;
    public final void rule__OrderByColumns__Group_1_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1345:1: ( rule__OrderByColumns__Group_1_1__0__Impl rule__OrderByColumns__Group_1_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1346:2: rule__OrderByColumns__Group_1_1__0__Impl rule__OrderByColumns__Group_1_1__1
            {
            pushFollow(FOLLOW_rule__OrderByColumns__Group_1_1__0__Impl_in_rule__OrderByColumns__Group_1_1__02650);
            rule__OrderByColumns__Group_1_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__OrderByColumns__Group_1_1__1_in_rule__OrderByColumns__Group_1_1__02653);
            rule__OrderByColumns__Group_1_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OrderByColumns__Group_1_1__0"


    // $ANTLR start "rule__OrderByColumns__Group_1_1__0__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1353:1: rule__OrderByColumns__Group_1_1__0__Impl : ( KEYWORD_1 ) ;
    public final void rule__OrderByColumns__Group_1_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1357:1: ( ( KEYWORD_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1358:1: ( KEYWORD_1 )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1358:1: ( KEYWORD_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1359:1: KEYWORD_1
            {
             before(grammarAccess.getOrderByColumnsAccess().getCommaKeyword_1_1_0()); 
            match(input,KEYWORD_1,FOLLOW_KEYWORD_1_in_rule__OrderByColumns__Group_1_1__0__Impl2681); 
             after(grammarAccess.getOrderByColumnsAccess().getCommaKeyword_1_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OrderByColumns__Group_1_1__0__Impl"


    // $ANTLR start "rule__OrderByColumns__Group_1_1__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1372:1: rule__OrderByColumns__Group_1_1__1 : rule__OrderByColumns__Group_1_1__1__Impl ;
    public final void rule__OrderByColumns__Group_1_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1376:1: ( rule__OrderByColumns__Group_1_1__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1377:2: rule__OrderByColumns__Group_1_1__1__Impl
            {
            pushFollow(FOLLOW_rule__OrderByColumns__Group_1_1__1__Impl_in_rule__OrderByColumns__Group_1_1__12712);
            rule__OrderByColumns__Group_1_1__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OrderByColumns__Group_1_1__1"


    // $ANTLR start "rule__OrderByColumns__Group_1_1__1__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1383:1: rule__OrderByColumns__Group_1_1__1__Impl : ( ( rule__OrderByColumns__EntriesAssignment_1_1_1 ) ) ;
    public final void rule__OrderByColumns__Group_1_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1387:1: ( ( ( rule__OrderByColumns__EntriesAssignment_1_1_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1388:1: ( ( rule__OrderByColumns__EntriesAssignment_1_1_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1388:1: ( ( rule__OrderByColumns__EntriesAssignment_1_1_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1389:1: ( rule__OrderByColumns__EntriesAssignment_1_1_1 )
            {
             before(grammarAccess.getOrderByColumnsAccess().getEntriesAssignment_1_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1390:1: ( rule__OrderByColumns__EntriesAssignment_1_1_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1390:2: rule__OrderByColumns__EntriesAssignment_1_1_1
            {
            pushFollow(FOLLOW_rule__OrderByColumns__EntriesAssignment_1_1_1_in_rule__OrderByColumns__Group_1_1__1__Impl2739);
            rule__OrderByColumns__EntriesAssignment_1_1_1();

            state._fsp--;


            }

             after(grammarAccess.getOrderByColumnsAccess().getEntriesAssignment_1_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OrderByColumns__Group_1_1__1__Impl"


    // $ANTLR start "rule__OrderByColumnFull__Group__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1404:1: rule__OrderByColumnFull__Group__0 : rule__OrderByColumnFull__Group__0__Impl rule__OrderByColumnFull__Group__1 ;
    public final void rule__OrderByColumnFull__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1408:1: ( rule__OrderByColumnFull__Group__0__Impl rule__OrderByColumnFull__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1409:2: rule__OrderByColumnFull__Group__0__Impl rule__OrderByColumnFull__Group__1
            {
            pushFollow(FOLLOW_rule__OrderByColumnFull__Group__0__Impl_in_rule__OrderByColumnFull__Group__02773);
            rule__OrderByColumnFull__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__OrderByColumnFull__Group__1_in_rule__OrderByColumnFull__Group__02776);
            rule__OrderByColumnFull__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OrderByColumnFull__Group__0"


    // $ANTLR start "rule__OrderByColumnFull__Group__0__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1416:1: rule__OrderByColumnFull__Group__0__Impl : ( ( rule__OrderByColumnFull__ColOrderAssignment_0 ) ) ;
    public final void rule__OrderByColumnFull__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1420:1: ( ( ( rule__OrderByColumnFull__ColOrderAssignment_0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1421:1: ( ( rule__OrderByColumnFull__ColOrderAssignment_0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1421:1: ( ( rule__OrderByColumnFull__ColOrderAssignment_0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1422:1: ( rule__OrderByColumnFull__ColOrderAssignment_0 )
            {
             before(grammarAccess.getOrderByColumnFullAccess().getColOrderAssignment_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1423:1: ( rule__OrderByColumnFull__ColOrderAssignment_0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1423:2: rule__OrderByColumnFull__ColOrderAssignment_0
            {
            pushFollow(FOLLOW_rule__OrderByColumnFull__ColOrderAssignment_0_in_rule__OrderByColumnFull__Group__0__Impl2803);
            rule__OrderByColumnFull__ColOrderAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getOrderByColumnFullAccess().getColOrderAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OrderByColumnFull__Group__0__Impl"


    // $ANTLR start "rule__OrderByColumnFull__Group__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1433:1: rule__OrderByColumnFull__Group__1 : rule__OrderByColumnFull__Group__1__Impl ;
    public final void rule__OrderByColumnFull__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1437:1: ( rule__OrderByColumnFull__Group__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1438:2: rule__OrderByColumnFull__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__OrderByColumnFull__Group__1__Impl_in_rule__OrderByColumnFull__Group__12833);
            rule__OrderByColumnFull__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OrderByColumnFull__Group__1"


    // $ANTLR start "rule__OrderByColumnFull__Group__1__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1444:1: rule__OrderByColumnFull__Group__1__Impl : ( ( rule__OrderByColumnFull__Alternatives_1 )? ) ;
    public final void rule__OrderByColumnFull__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1448:1: ( ( ( rule__OrderByColumnFull__Alternatives_1 )? ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1449:1: ( ( rule__OrderByColumnFull__Alternatives_1 )? )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1449:1: ( ( rule__OrderByColumnFull__Alternatives_1 )? )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1450:1: ( rule__OrderByColumnFull__Alternatives_1 )?
            {
             before(grammarAccess.getOrderByColumnFullAccess().getAlternatives_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1451:1: ( rule__OrderByColumnFull__Alternatives_1 )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( ((LA15_0>=KEYWORD_5 && LA15_0<=KEYWORD_4)) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1451:2: rule__OrderByColumnFull__Alternatives_1
                    {
                    pushFollow(FOLLOW_rule__OrderByColumnFull__Alternatives_1_in_rule__OrderByColumnFull__Group__1__Impl2860);
                    rule__OrderByColumnFull__Alternatives_1();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getOrderByColumnFullAccess().getAlternatives_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OrderByColumnFull__Group__1__Impl"


    // $ANTLR start "rule__Model__EntriesAssignment_1_1_1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1466:1: rule__Model__EntriesAssignment_1_1_1 : ( ruleSelect ) ;
    public final void rule__Model__EntriesAssignment_1_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1470:1: ( ( ruleSelect ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1471:1: ( ruleSelect )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1471:1: ( ruleSelect )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1472:1: ruleSelect
            {
             before(grammarAccess.getModelAccess().getEntriesSelectParserRuleCall_1_1_1_0()); 
            pushFollow(FOLLOW_ruleSelect_in_rule__Model__EntriesAssignment_1_1_12900);
            ruleSelect();

            state._fsp--;

             after(grammarAccess.getModelAccess().getEntriesSelectParserRuleCall_1_1_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Model__EntriesAssignment_1_1_1"


    // $ANTLR start "rule__Model__OrderByEntryAssignment_2_1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1481:1: rule__Model__OrderByEntryAssignment_2_1 : ( ruleOrderByColumns ) ;
    public final void rule__Model__OrderByEntryAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1485:1: ( ( ruleOrderByColumns ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1486:1: ( ruleOrderByColumns )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1486:1: ( ruleOrderByColumns )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1487:1: ruleOrderByColumns
            {
             before(grammarAccess.getModelAccess().getOrderByEntryOrderByColumnsParserRuleCall_2_1_0()); 
            pushFollow(FOLLOW_ruleOrderByColumns_in_rule__Model__OrderByEntryAssignment_2_12931);
            ruleOrderByColumns();

            state._fsp--;

             after(grammarAccess.getModelAccess().getOrderByEntryOrderByColumnsParserRuleCall_2_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Model__OrderByEntryAssignment_2_1"


    // $ANTLR start "rule__Select__SelectAssignment_0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1496:1: rule__Select__SelectAssignment_0 : ( ( KEYWORD_7 ) ) ;
    public final void rule__Select__SelectAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1500:1: ( ( ( KEYWORD_7 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1501:1: ( ( KEYWORD_7 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1501:1: ( ( KEYWORD_7 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1502:1: ( KEYWORD_7 )
            {
             before(grammarAccess.getSelectAccess().getSelectSELECTKeyword_0_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1503:1: ( KEYWORD_7 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1504:1: KEYWORD_7
            {
             before(grammarAccess.getSelectAccess().getSelectSELECTKeyword_0_0()); 
            match(input,KEYWORD_7,FOLLOW_KEYWORD_7_in_rule__Select__SelectAssignment_02967); 
             after(grammarAccess.getSelectAccess().getSelectSELECTKeyword_0_0()); 

            }

             after(grammarAccess.getSelectAccess().getSelectSELECTKeyword_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Select__SelectAssignment_0"


    // $ANTLR start "rule__Select__ColsAssignment_2"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1519:1: rule__Select__ColsAssignment_2 : ( ruleColumns ) ;
    public final void rule__Select__ColsAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1523:1: ( ( ruleColumns ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1524:1: ( ruleColumns )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1524:1: ( ruleColumns )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1525:1: ruleColumns
            {
             before(grammarAccess.getSelectAccess().getColsColumnsParserRuleCall_2_0()); 
            pushFollow(FOLLOW_ruleColumns_in_rule__Select__ColsAssignment_23006);
            ruleColumns();

            state._fsp--;

             after(grammarAccess.getSelectAccess().getColsColumnsParserRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Select__ColsAssignment_2"


    // $ANTLR start "rule__Columns__EntriesAssignment_1_1_1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1534:1: rule__Columns__EntriesAssignment_1_1_1 : ( ruleColumnOrAlias ) ;
    public final void rule__Columns__EntriesAssignment_1_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1538:1: ( ( ruleColumnOrAlias ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1539:1: ( ruleColumnOrAlias )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1539:1: ( ruleColumnOrAlias )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1540:1: ruleColumnOrAlias
            {
             before(grammarAccess.getColumnsAccess().getEntriesColumnOrAliasParserRuleCall_1_1_1_0()); 
            pushFollow(FOLLOW_ruleColumnOrAlias_in_rule__Columns__EntriesAssignment_1_1_13037);
            ruleColumnOrAlias();

            state._fsp--;

             after(grammarAccess.getColumnsAccess().getEntriesColumnOrAliasParserRuleCall_1_1_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Columns__EntriesAssignment_1_1_1"


    // $ANTLR start "rule__ColumnOrAlias__ColAliasAssignment_0_2"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1549:1: rule__ColumnOrAlias__ColAliasAssignment_0_2 : ( ruleDbObjectName ) ;
    public final void rule__ColumnOrAlias__ColAliasAssignment_0_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1553:1: ( ( ruleDbObjectName ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1554:1: ( ruleDbObjectName )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1554:1: ( ruleDbObjectName )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1555:1: ruleDbObjectName
            {
             before(grammarAccess.getColumnOrAliasAccess().getColAliasDbObjectNameParserRuleCall_0_2_0()); 
            pushFollow(FOLLOW_ruleDbObjectName_in_rule__ColumnOrAlias__ColAliasAssignment_0_23068);
            ruleDbObjectName();

            state._fsp--;

             after(grammarAccess.getColumnOrAliasAccess().getColAliasDbObjectNameParserRuleCall_0_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnOrAlias__ColAliasAssignment_0_2"


    // $ANTLR start "rule__ColumnOrAlias__AllColsAssignment_1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1564:1: rule__ColumnOrAlias__AllColsAssignment_1 : ( RULE_STAR ) ;
    public final void rule__ColumnOrAlias__AllColsAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1568:1: ( ( RULE_STAR ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1569:1: ( RULE_STAR )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1569:1: ( RULE_STAR )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1570:1: RULE_STAR
            {
             before(grammarAccess.getColumnOrAliasAccess().getAllColsSTARTerminalRuleCall_1_0()); 
            match(input,RULE_STAR,FOLLOW_RULE_STAR_in_rule__ColumnOrAlias__AllColsAssignment_13099); 
             after(grammarAccess.getColumnOrAliasAccess().getAllColsSTARTerminalRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnOrAlias__AllColsAssignment_1"


    // $ANTLR start "rule__ColumnFull__EntriesAssignment_1_1_1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1579:1: rule__ColumnFull__EntriesAssignment_1_1_1 : ( ruleDbObjectName ) ;
    public final void rule__ColumnFull__EntriesAssignment_1_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1583:1: ( ( ruleDbObjectName ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1584:1: ( ruleDbObjectName )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1584:1: ( ruleDbObjectName )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1585:1: ruleDbObjectName
            {
             before(grammarAccess.getColumnFullAccess().getEntriesDbObjectNameParserRuleCall_1_1_1_0()); 
            pushFollow(FOLLOW_ruleDbObjectName_in_rule__ColumnFull__EntriesAssignment_1_1_13130);
            ruleDbObjectName();

            state._fsp--;

             after(grammarAccess.getColumnFullAccess().getEntriesDbObjectNameParserRuleCall_1_1_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnFull__EntriesAssignment_1_1_1"


    // $ANTLR start "rule__DbObjectName__DbnameAssignment"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1594:1: rule__DbObjectName__DbnameAssignment : ( RULE_ID ) ;
    public final void rule__DbObjectName__DbnameAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1598:1: ( ( RULE_ID ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1599:1: ( RULE_ID )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1599:1: ( RULE_ID )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1600:1: RULE_ID
            {
             before(grammarAccess.getDbObjectNameAccess().getDbnameIDTerminalRuleCall_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__DbObjectName__DbnameAssignment3161); 
             after(grammarAccess.getDbObjectNameAccess().getDbnameIDTerminalRuleCall_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DbObjectName__DbnameAssignment"


    // $ANTLR start "rule__OrderByColumns__EntriesAssignment_1_1_1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1609:1: rule__OrderByColumns__EntriesAssignment_1_1_1 : ( ruleOrderByColumnFull ) ;
    public final void rule__OrderByColumns__EntriesAssignment_1_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1613:1: ( ( ruleOrderByColumnFull ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1614:1: ( ruleOrderByColumnFull )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1614:1: ( ruleOrderByColumnFull )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1615:1: ruleOrderByColumnFull
            {
             before(grammarAccess.getOrderByColumnsAccess().getEntriesOrderByColumnFullParserRuleCall_1_1_1_0()); 
            pushFollow(FOLLOW_ruleOrderByColumnFull_in_rule__OrderByColumns__EntriesAssignment_1_1_13192);
            ruleOrderByColumnFull();

            state._fsp--;

             after(grammarAccess.getOrderByColumnsAccess().getEntriesOrderByColumnFullParserRuleCall_1_1_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OrderByColumns__EntriesAssignment_1_1_1"


    // $ANTLR start "rule__OrderByColumnFull__ColOrderAssignment_0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1624:1: rule__OrderByColumnFull__ColOrderAssignment_0 : ( ruleColumnFull ) ;
    public final void rule__OrderByColumnFull__ColOrderAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1628:1: ( ( ruleColumnFull ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1629:1: ( ruleColumnFull )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1629:1: ( ruleColumnFull )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1630:1: ruleColumnFull
            {
             before(grammarAccess.getOrderByColumnFullAccess().getColOrderColumnFullParserRuleCall_0_0()); 
            pushFollow(FOLLOW_ruleColumnFull_in_rule__OrderByColumnFull__ColOrderAssignment_03223);
            ruleColumnFull();

            state._fsp--;

             after(grammarAccess.getOrderByColumnFullAccess().getColOrderColumnFullParserRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OrderByColumnFull__ColOrderAssignment_0"

    // Delegated rules


 

    public static final BitSet FOLLOW_ruleModel_in_entryRuleModel54 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleModel61 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Model__Group__0_in_ruleModel91 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSelect_in_entryRuleSelect118 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSelect125 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Select__Group__0_in_ruleSelect155 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumns_in_entryRuleColumns182 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleColumns189 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Columns__Group__0_in_ruleColumns219 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnOrAlias_in_entryRuleColumnOrAlias246 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleColumnOrAlias253 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ColumnOrAlias__Alternatives_in_ruleColumnOrAlias283 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnFull_in_entryRuleColumnFull310 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleColumnFull317 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ColumnFull__Group__0_in_ruleColumnFull347 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDbObjectName_in_entryRuleDbObjectName374 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleDbObjectName381 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__DbObjectName__DbnameAssignment_in_ruleDbObjectName411 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOrderByColumns_in_entryRuleOrderByColumns438 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOrderByColumns445 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OrderByColumns__Group__0_in_ruleOrderByColumns475 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOrderByColumnFull_in_entryRuleOrderByColumnFull502 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOrderByColumnFull509 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OrderByColumnFull__Group__0_in_ruleOrderByColumnFull539 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ColumnOrAlias__Group_0__0_in_rule__ColumnOrAlias__Alternatives575 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ColumnOrAlias__AllColsAssignment_1_in_rule__ColumnOrAlias__Alternatives593 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_4_in_rule__OrderByColumnFull__Alternatives_1627 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_5_in_rule__OrderByColumnFull__Alternatives_1647 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Model__Group__0__Impl_in_rule__Model__Group__0679 = new BitSet(new long[]{0x00000000000000A0L});
    public static final BitSet FOLLOW_rule__Model__Group__1_in_rule__Model__Group__0682 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSelect_in_rule__Model__Group__0__Impl709 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Model__Group__1__Impl_in_rule__Model__Group__1738 = new BitSet(new long[]{0x00000000000000A0L});
    public static final BitSet FOLLOW_rule__Model__Group__2_in_rule__Model__Group__1741 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Model__Group_1__0_in_rule__Model__Group__1__Impl768 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Model__Group__2__Impl_in_rule__Model__Group__2799 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Model__Group_2__0_in_rule__Model__Group__2__Impl826 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Model__Group_1__0__Impl_in_rule__Model__Group_1__0863 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_rule__Model__Group_1__1_in_rule__Model__Group_1__0866 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Model__Group_1__1__Impl_in_rule__Model__Group_1__1924 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Model__Group_1_1__0_in_rule__Model__Group_1__1__Impl953 = new BitSet(new long[]{0x0000000000000082L});
    public static final BitSet FOLLOW_rule__Model__Group_1_1__0_in_rule__Model__Group_1__1__Impl965 = new BitSet(new long[]{0x0000000000000082L});
    public static final BitSet FOLLOW_rule__Model__Group_1_1__0__Impl_in_rule__Model__Group_1_1__01002 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_rule__Model__Group_1_1__1_in_rule__Model__Group_1_1__01005 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_6_in_rule__Model__Group_1_1__0__Impl1033 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Model__Group_1_1__1__Impl_in_rule__Model__Group_1_1__11064 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Model__EntriesAssignment_1_1_1_in_rule__Model__Group_1_1__1__Impl1091 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Model__Group_2__0__Impl_in_rule__Model__Group_2__01125 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_rule__Model__Group_2__1_in_rule__Model__Group_2__01128 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_9_in_rule__Model__Group_2__0__Impl1156 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Model__Group_2__1__Impl_in_rule__Model__Group_2__11187 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Model__OrderByEntryAssignment_2_1_in_rule__Model__Group_2__1__Impl1214 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Select__Group__0__Impl_in_rule__Select__Group__01248 = new BitSet(new long[]{0x000000000000A010L});
    public static final BitSet FOLLOW_rule__Select__Group__1_in_rule__Select__Group__01251 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Select__SelectAssignment_0_in_rule__Select__Group__0__Impl1278 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Select__Group__1__Impl_in_rule__Select__Group__11308 = new BitSet(new long[]{0x000000000000A010L});
    public static final BitSet FOLLOW_rule__Select__Group__2_in_rule__Select__Group__11311 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_8_in_rule__Select__Group__1__Impl1340 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Select__Group__2__Impl_in_rule__Select__Group__21373 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Select__ColsAssignment_2_in_rule__Select__Group__2__Impl1400 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Columns__Group__0__Impl_in_rule__Columns__Group__01436 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_rule__Columns__Group__1_in_rule__Columns__Group__01439 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnOrAlias_in_rule__Columns__Group__0__Impl1466 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Columns__Group__1__Impl_in_rule__Columns__Group__11495 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Columns__Group_1__0_in_rule__Columns__Group__1__Impl1522 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Columns__Group_1__0__Impl_in_rule__Columns__Group_1__01557 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_rule__Columns__Group_1__1_in_rule__Columns__Group_1__01560 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Columns__Group_1__1__Impl_in_rule__Columns__Group_1__11618 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Columns__Group_1_1__0_in_rule__Columns__Group_1__1__Impl1647 = new BitSet(new long[]{0x0000000000000802L});
    public static final BitSet FOLLOW_rule__Columns__Group_1_1__0_in_rule__Columns__Group_1__1__Impl1659 = new BitSet(new long[]{0x0000000000000802L});
    public static final BitSet FOLLOW_rule__Columns__Group_1_1__0__Impl_in_rule__Columns__Group_1_1__01696 = new BitSet(new long[]{0x000000000000A010L});
    public static final BitSet FOLLOW_rule__Columns__Group_1_1__1_in_rule__Columns__Group_1_1__01699 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_1_in_rule__Columns__Group_1_1__0__Impl1727 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Columns__Group_1_1__1__Impl_in_rule__Columns__Group_1_1__11758 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Columns__EntriesAssignment_1_1_1_in_rule__Columns__Group_1_1__1__Impl1785 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ColumnOrAlias__Group_0__0__Impl_in_rule__ColumnOrAlias__Group_0__01819 = new BitSet(new long[]{0x0000000000008400L});
    public static final BitSet FOLLOW_rule__ColumnOrAlias__Group_0__1_in_rule__ColumnOrAlias__Group_0__01822 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnFull_in_rule__ColumnOrAlias__Group_0__0__Impl1849 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ColumnOrAlias__Group_0__1__Impl_in_rule__ColumnOrAlias__Group_0__11878 = new BitSet(new long[]{0x0000000000008400L});
    public static final BitSet FOLLOW_rule__ColumnOrAlias__Group_0__2_in_rule__ColumnOrAlias__Group_0__11881 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_3_in_rule__ColumnOrAlias__Group_0__1__Impl1910 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ColumnOrAlias__Group_0__2__Impl_in_rule__ColumnOrAlias__Group_0__21943 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ColumnOrAlias__ColAliasAssignment_0_2_in_rule__ColumnOrAlias__Group_0__2__Impl1970 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ColumnFull__Group__0__Impl_in_rule__ColumnFull__Group__02007 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_rule__ColumnFull__Group__1_in_rule__ColumnFull__Group__02010 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDbObjectName_in_rule__ColumnFull__Group__0__Impl2037 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ColumnFull__Group__1__Impl_in_rule__ColumnFull__Group__12066 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ColumnFull__Group_1__0_in_rule__ColumnFull__Group__1__Impl2093 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ColumnFull__Group_1__0__Impl_in_rule__ColumnFull__Group_1__02128 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_rule__ColumnFull__Group_1__1_in_rule__ColumnFull__Group_1__02131 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ColumnFull__Group_1__1__Impl_in_rule__ColumnFull__Group_1__12189 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ColumnFull__Group_1_1__0_in_rule__ColumnFull__Group_1__1__Impl2218 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_rule__ColumnFull__Group_1_1__0_in_rule__ColumnFull__Group_1__1__Impl2230 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_rule__ColumnFull__Group_1_1__0__Impl_in_rule__ColumnFull__Group_1_1__02267 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_rule__ColumnFull__Group_1_1__1_in_rule__ColumnFull__Group_1_1__02270 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_2_in_rule__ColumnFull__Group_1_1__0__Impl2298 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ColumnFull__Group_1_1__1__Impl_in_rule__ColumnFull__Group_1_1__12329 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ColumnFull__EntriesAssignment_1_1_1_in_rule__ColumnFull__Group_1_1__1__Impl2356 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OrderByColumns__Group__0__Impl_in_rule__OrderByColumns__Group__02390 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_rule__OrderByColumns__Group__1_in_rule__OrderByColumns__Group__02393 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOrderByColumnFull_in_rule__OrderByColumns__Group__0__Impl2420 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OrderByColumns__Group__1__Impl_in_rule__OrderByColumns__Group__12449 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OrderByColumns__Group_1__0_in_rule__OrderByColumns__Group__1__Impl2476 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OrderByColumns__Group_1__0__Impl_in_rule__OrderByColumns__Group_1__02511 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_rule__OrderByColumns__Group_1__1_in_rule__OrderByColumns__Group_1__02514 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OrderByColumns__Group_1__1__Impl_in_rule__OrderByColumns__Group_1__12572 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OrderByColumns__Group_1_1__0_in_rule__OrderByColumns__Group_1__1__Impl2601 = new BitSet(new long[]{0x0000000000000802L});
    public static final BitSet FOLLOW_rule__OrderByColumns__Group_1_1__0_in_rule__OrderByColumns__Group_1__1__Impl2613 = new BitSet(new long[]{0x0000000000000802L});
    public static final BitSet FOLLOW_rule__OrderByColumns__Group_1_1__0__Impl_in_rule__OrderByColumns__Group_1_1__02650 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_rule__OrderByColumns__Group_1_1__1_in_rule__OrderByColumns__Group_1_1__02653 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_1_in_rule__OrderByColumns__Group_1_1__0__Impl2681 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OrderByColumns__Group_1_1__1__Impl_in_rule__OrderByColumns__Group_1_1__12712 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OrderByColumns__EntriesAssignment_1_1_1_in_rule__OrderByColumns__Group_1_1__1__Impl2739 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OrderByColumnFull__Group__0__Impl_in_rule__OrderByColumnFull__Group__02773 = new BitSet(new long[]{0x0000000000000300L});
    public static final BitSet FOLLOW_rule__OrderByColumnFull__Group__1_in_rule__OrderByColumnFull__Group__02776 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OrderByColumnFull__ColOrderAssignment_0_in_rule__OrderByColumnFull__Group__0__Impl2803 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OrderByColumnFull__Group__1__Impl_in_rule__OrderByColumnFull__Group__12833 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OrderByColumnFull__Alternatives_1_in_rule__OrderByColumnFull__Group__1__Impl2860 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSelect_in_rule__Model__EntriesAssignment_1_1_12900 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOrderByColumns_in_rule__Model__OrderByEntryAssignment_2_12931 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_7_in_rule__Select__SelectAssignment_02967 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumns_in_rule__Select__ColsAssignment_23006 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnOrAlias_in_rule__Columns__EntriesAssignment_1_1_13037 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDbObjectName_in_rule__ColumnOrAlias__ColAliasAssignment_0_23068 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STAR_in_rule__ColumnOrAlias__AllColsAssignment_13099 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDbObjectName_in_rule__ColumnFull__EntriesAssignment_1_1_13130 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__DbObjectName__DbnameAssignment3161 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOrderByColumnFull_in_rule__OrderByColumns__EntriesAssignment_1_1_13192 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnFull_in_rule__OrderByColumnFull__ColOrderAssignment_03223 = new BitSet(new long[]{0x0000000000000002L});

}