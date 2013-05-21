package com.jaspersoft.studio.data.ui.contentassist.antlr.internal; 

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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_SIGNED_DOUBLE", "RULE_SINGED_LONG", "RULE_STRING", "RULE_DATE", "RULE_BOOL", "RULE_INT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'in'", "'not in'", "'<'", "'>'", "'<='", "'>='", "'='", "'!='", "'like'", "'not like'", "'SELECT'", "'FROM'", "'WHERE'", "','", "'AS'", "'.'", "'OR'", "'AND'", "'('", "')'", "'['", "']'", "'?'", "'null'", "'true'", "'false'"
    };
    public static final int RULE_ID=4;
    public static final int T__40=40;
    public static final int RULE_DATE=8;
    public static final int T__29=29;
    public static final int T__28=28;
    public static final int T__27=27;
    public static final int T__26=26;
    public static final int T__25=25;
    public static final int T__24=24;
    public static final int T__23=23;
    public static final int T__22=22;
    public static final int RULE_ANY_OTHER=14;
    public static final int T__21=21;
    public static final int T__20=20;
    public static final int RULE_SINGED_LONG=6;
    public static final int RULE_BOOL=9;
    public static final int RULE_SL_COMMENT=12;
    public static final int EOF=-1;
    public static final int RULE_ML_COMMENT=11;
    public static final int T__30=30;
    public static final int T__19=19;
    public static final int T__31=31;
    public static final int RULE_STRING=7;
    public static final int T__32=32;
    public static final int T__33=33;
    public static final int T__16=16;
    public static final int T__34=34;
    public static final int T__15=15;
    public static final int T__35=35;
    public static final int T__18=18;
    public static final int T__36=36;
    public static final int T__17=17;
    public static final int T__37=37;
    public static final int T__38=38;
    public static final int T__39=39;
    public static final int RULE_INT=10;
    public static final int RULE_SIGNED_DOUBLE=5;
    public static final int RULE_WS=13;

    // delegates
    // delegators


        public InternalSqlParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalSqlParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalSqlParser.tokenNames; }
    public String getGrammarFileName() { return "../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g"; }


     
     	private SqlGrammarAccess grammarAccess;
     	
        public void setGrammarAccess(SqlGrammarAccess grammarAccess) {
        	this.grammarAccess = grammarAccess;
        }
        
        @Override
        protected Grammar getGrammar() {
        	return grammarAccess.getGrammar();
        }
        
        @Override
        protected String getValueForTokenName(String tokenName) {
        	return tokenName;
        }




    // $ANTLR start "entryRuleModel"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:60:1: entryRuleModel : ruleModel EOF ;
    public final void entryRuleModel() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:61:1: ( ruleModel EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:62:1: ruleModel EOF
            {
             before(grammarAccess.getModelRule()); 
            pushFollow(FOLLOW_ruleModel_in_entryRuleModel61);
            ruleModel();

            state._fsp--;

             after(grammarAccess.getModelRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleModel68); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:69:1: ruleModel : ( ( rule__Model__Group__0 ) ) ;
    public final void ruleModel() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:73:2: ( ( ( rule__Model__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:74:1: ( ( rule__Model__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:74:1: ( ( rule__Model__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:75:1: ( rule__Model__Group__0 )
            {
             before(grammarAccess.getModelAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:76:1: ( rule__Model__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:76:2: rule__Model__Group__0
            {
            pushFollow(FOLLOW_rule__Model__Group__0_in_ruleModel94);
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


    // $ANTLR start "entryRuleColumns"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:88:1: entryRuleColumns : ruleColumns EOF ;
    public final void entryRuleColumns() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:89:1: ( ruleColumns EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:90:1: ruleColumns EOF
            {
             before(grammarAccess.getColumnsRule()); 
            pushFollow(FOLLOW_ruleColumns_in_entryRuleColumns121);
            ruleColumns();

            state._fsp--;

             after(grammarAccess.getColumnsRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleColumns128); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:97:1: ruleColumns : ( ( rule__Columns__Group__0 ) ) ;
    public final void ruleColumns() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:101:2: ( ( ( rule__Columns__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:102:1: ( ( rule__Columns__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:102:1: ( ( rule__Columns__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:103:1: ( rule__Columns__Group__0 )
            {
             before(grammarAccess.getColumnsAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:104:1: ( rule__Columns__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:104:2: rule__Columns__Group__0
            {
            pushFollow(FOLLOW_rule__Columns__Group__0_in_ruleColumns154);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:116:1: entryRuleColumnOrAlias : ruleColumnOrAlias EOF ;
    public final void entryRuleColumnOrAlias() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:117:1: ( ruleColumnOrAlias EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:118:1: ruleColumnOrAlias EOF
            {
             before(grammarAccess.getColumnOrAliasRule()); 
            pushFollow(FOLLOW_ruleColumnOrAlias_in_entryRuleColumnOrAlias181);
            ruleColumnOrAlias();

            state._fsp--;

             after(grammarAccess.getColumnOrAliasRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleColumnOrAlias188); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:125:1: ruleColumnOrAlias : ( ( rule__ColumnOrAlias__Alternatives ) ) ;
    public final void ruleColumnOrAlias() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:129:2: ( ( ( rule__ColumnOrAlias__Alternatives ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:130:1: ( ( rule__ColumnOrAlias__Alternatives ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:130:1: ( ( rule__ColumnOrAlias__Alternatives ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:131:1: ( rule__ColumnOrAlias__Alternatives )
            {
             before(grammarAccess.getColumnOrAliasAccess().getAlternatives()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:132:1: ( rule__ColumnOrAlias__Alternatives )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:132:2: rule__ColumnOrAlias__Alternatives
            {
            pushFollow(FOLLOW_rule__ColumnOrAlias__Alternatives_in_ruleColumnOrAlias214);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:144:1: entryRuleColumnFull : ruleColumnFull EOF ;
    public final void entryRuleColumnFull() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:145:1: ( ruleColumnFull EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:146:1: ruleColumnFull EOF
            {
             before(grammarAccess.getColumnFullRule()); 
            pushFollow(FOLLOW_ruleColumnFull_in_entryRuleColumnFull241);
            ruleColumnFull();

            state._fsp--;

             after(grammarAccess.getColumnFullRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleColumnFull248); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:153:1: ruleColumnFull : ( ( rule__ColumnFull__Alternatives ) ) ;
    public final void ruleColumnFull() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:157:2: ( ( ( rule__ColumnFull__Alternatives ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:158:1: ( ( rule__ColumnFull__Alternatives ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:158:1: ( ( rule__ColumnFull__Alternatives ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:159:1: ( rule__ColumnFull__Alternatives )
            {
             before(grammarAccess.getColumnFullAccess().getAlternatives()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:160:1: ( rule__ColumnFull__Alternatives )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:160:2: rule__ColumnFull__Alternatives
            {
            pushFollow(FOLLOW_rule__ColumnFull__Alternatives_in_ruleColumnFull274);
            rule__ColumnFull__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getColumnFullAccess().getAlternatives()); 

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


    // $ANTLR start "entryRuleColumnAlias"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:172:1: entryRuleColumnAlias : ruleColumnAlias EOF ;
    public final void entryRuleColumnAlias() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:173:1: ( ruleColumnAlias EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:174:1: ruleColumnAlias EOF
            {
             before(grammarAccess.getColumnAliasRule()); 
            pushFollow(FOLLOW_ruleColumnAlias_in_entryRuleColumnAlias301);
            ruleColumnAlias();

            state._fsp--;

             after(grammarAccess.getColumnAliasRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleColumnAlias308); 

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
    // $ANTLR end "entryRuleColumnAlias"


    // $ANTLR start "ruleColumnAlias"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:181:1: ruleColumnAlias : ( ( rule__ColumnAlias__ColAliasAssignment ) ) ;
    public final void ruleColumnAlias() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:185:2: ( ( ( rule__ColumnAlias__ColAliasAssignment ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:186:1: ( ( rule__ColumnAlias__ColAliasAssignment ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:186:1: ( ( rule__ColumnAlias__ColAliasAssignment ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:187:1: ( rule__ColumnAlias__ColAliasAssignment )
            {
             before(grammarAccess.getColumnAliasAccess().getColAliasAssignment()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:188:1: ( rule__ColumnAlias__ColAliasAssignment )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:188:2: rule__ColumnAlias__ColAliasAssignment
            {
            pushFollow(FOLLOW_rule__ColumnAlias__ColAliasAssignment_in_ruleColumnAlias334);
            rule__ColumnAlias__ColAliasAssignment();

            state._fsp--;


            }

             after(grammarAccess.getColumnAliasAccess().getColAliasAssignment()); 

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
    // $ANTLR end "ruleColumnAlias"


    // $ANTLR start "entryRuleColumn"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:200:1: entryRuleColumn : ruleColumn EOF ;
    public final void entryRuleColumn() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:201:1: ( ruleColumn EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:202:1: ruleColumn EOF
            {
             before(grammarAccess.getColumnRule()); 
            pushFollow(FOLLOW_ruleColumn_in_entryRuleColumn361);
            ruleColumn();

            state._fsp--;

             after(grammarAccess.getColumnRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleColumn368); 

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
    // $ANTLR end "entryRuleColumn"


    // $ANTLR start "ruleColumn"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:209:1: ruleColumn : ( ( rule__Column__ColNameAssignment ) ) ;
    public final void ruleColumn() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:213:2: ( ( ( rule__Column__ColNameAssignment ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:214:1: ( ( rule__Column__ColNameAssignment ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:214:1: ( ( rule__Column__ColNameAssignment ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:215:1: ( rule__Column__ColNameAssignment )
            {
             before(grammarAccess.getColumnAccess().getColNameAssignment()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:216:1: ( rule__Column__ColNameAssignment )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:216:2: rule__Column__ColNameAssignment
            {
            pushFollow(FOLLOW_rule__Column__ColNameAssignment_in_ruleColumn394);
            rule__Column__ColNameAssignment();

            state._fsp--;


            }

             after(grammarAccess.getColumnAccess().getColNameAssignment()); 

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
    // $ANTLR end "ruleColumn"


    // $ANTLR start "entryRuleTables"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:228:1: entryRuleTables : ruleTables EOF ;
    public final void entryRuleTables() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:229:1: ( ruleTables EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:230:1: ruleTables EOF
            {
             before(grammarAccess.getTablesRule()); 
            pushFollow(FOLLOW_ruleTables_in_entryRuleTables421);
            ruleTables();

            state._fsp--;

             after(grammarAccess.getTablesRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleTables428); 

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
    // $ANTLR end "entryRuleTables"


    // $ANTLR start "ruleTables"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:237:1: ruleTables : ( ( rule__Tables__Group__0 ) ) ;
    public final void ruleTables() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:241:2: ( ( ( rule__Tables__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:242:1: ( ( rule__Tables__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:242:1: ( ( rule__Tables__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:243:1: ( rule__Tables__Group__0 )
            {
             before(grammarAccess.getTablesAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:244:1: ( rule__Tables__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:244:2: rule__Tables__Group__0
            {
            pushFollow(FOLLOW_rule__Tables__Group__0_in_ruleTables454);
            rule__Tables__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getTablesAccess().getGroup()); 

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
    // $ANTLR end "ruleTables"


    // $ANTLR start "entryRuleTableOrAlias"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:256:1: entryRuleTableOrAlias : ruleTableOrAlias EOF ;
    public final void entryRuleTableOrAlias() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:257:1: ( ruleTableOrAlias EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:258:1: ruleTableOrAlias EOF
            {
             before(grammarAccess.getTableOrAliasRule()); 
            pushFollow(FOLLOW_ruleTableOrAlias_in_entryRuleTableOrAlias481);
            ruleTableOrAlias();

            state._fsp--;

             after(grammarAccess.getTableOrAliasRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleTableOrAlias488); 

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
    // $ANTLR end "entryRuleTableOrAlias"


    // $ANTLR start "ruleTableOrAlias"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:265:1: ruleTableOrAlias : ( ( rule__TableOrAlias__Alternatives ) ) ;
    public final void ruleTableOrAlias() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:269:2: ( ( ( rule__TableOrAlias__Alternatives ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:270:1: ( ( rule__TableOrAlias__Alternatives ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:270:1: ( ( rule__TableOrAlias__Alternatives ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:271:1: ( rule__TableOrAlias__Alternatives )
            {
             before(grammarAccess.getTableOrAliasAccess().getAlternatives()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:272:1: ( rule__TableOrAlias__Alternatives )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:272:2: rule__TableOrAlias__Alternatives
            {
            pushFollow(FOLLOW_rule__TableOrAlias__Alternatives_in_ruleTableOrAlias514);
            rule__TableOrAlias__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getTableOrAliasAccess().getAlternatives()); 

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
    // $ANTLR end "ruleTableOrAlias"


    // $ANTLR start "entryRuleTableFull"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:284:1: entryRuleTableFull : ruleTableFull EOF ;
    public final void entryRuleTableFull() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:285:1: ( ruleTableFull EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:286:1: ruleTableFull EOF
            {
             before(grammarAccess.getTableFullRule()); 
            pushFollow(FOLLOW_ruleTableFull_in_entryRuleTableFull541);
            ruleTableFull();

            state._fsp--;

             after(grammarAccess.getTableFullRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleTableFull548); 

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
    // $ANTLR end "entryRuleTableFull"


    // $ANTLR start "ruleTableFull"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:293:1: ruleTableFull : ( ( rule__TableFull__Alternatives ) ) ;
    public final void ruleTableFull() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:297:2: ( ( ( rule__TableFull__Alternatives ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:298:1: ( ( rule__TableFull__Alternatives ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:298:1: ( ( rule__TableFull__Alternatives ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:299:1: ( rule__TableFull__Alternatives )
            {
             before(grammarAccess.getTableFullAccess().getAlternatives()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:300:1: ( rule__TableFull__Alternatives )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:300:2: rule__TableFull__Alternatives
            {
            pushFollow(FOLLOW_rule__TableFull__Alternatives_in_ruleTableFull574);
            rule__TableFull__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getTableFullAccess().getAlternatives()); 

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
    // $ANTLR end "ruleTableFull"


    // $ANTLR start "entryRuleTable"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:312:1: entryRuleTable : ruleTable EOF ;
    public final void entryRuleTable() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:313:1: ( ruleTable EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:314:1: ruleTable EOF
            {
             before(grammarAccess.getTableRule()); 
            pushFollow(FOLLOW_ruleTable_in_entryRuleTable601);
            ruleTable();

            state._fsp--;

             after(grammarAccess.getTableRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleTable608); 

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
    // $ANTLR end "entryRuleTable"


    // $ANTLR start "ruleTable"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:321:1: ruleTable : ( ( rule__Table__TblAssignment ) ) ;
    public final void ruleTable() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:325:2: ( ( ( rule__Table__TblAssignment ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:326:1: ( ( rule__Table__TblAssignment ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:326:1: ( ( rule__Table__TblAssignment ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:327:1: ( rule__Table__TblAssignment )
            {
             before(grammarAccess.getTableAccess().getTblAssignment()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:328:1: ( rule__Table__TblAssignment )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:328:2: rule__Table__TblAssignment
            {
            pushFollow(FOLLOW_rule__Table__TblAssignment_in_ruleTable634);
            rule__Table__TblAssignment();

            state._fsp--;


            }

             after(grammarAccess.getTableAccess().getTblAssignment()); 

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
    // $ANTLR end "ruleTable"


    // $ANTLR start "entryRuleTableAlias"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:340:1: entryRuleTableAlias : ruleTableAlias EOF ;
    public final void entryRuleTableAlias() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:341:1: ( ruleTableAlias EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:342:1: ruleTableAlias EOF
            {
             before(grammarAccess.getTableAliasRule()); 
            pushFollow(FOLLOW_ruleTableAlias_in_entryRuleTableAlias661);
            ruleTableAlias();

            state._fsp--;

             after(grammarAccess.getTableAliasRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleTableAlias668); 

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
    // $ANTLR end "entryRuleTableAlias"


    // $ANTLR start "ruleTableAlias"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:349:1: ruleTableAlias : ( ( rule__TableAlias__TblAliasAssignment ) ) ;
    public final void ruleTableAlias() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:353:2: ( ( ( rule__TableAlias__TblAliasAssignment ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:354:1: ( ( rule__TableAlias__TblAliasAssignment ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:354:1: ( ( rule__TableAlias__TblAliasAssignment ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:355:1: ( rule__TableAlias__TblAliasAssignment )
            {
             before(grammarAccess.getTableAliasAccess().getTblAliasAssignment()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:356:1: ( rule__TableAlias__TblAliasAssignment )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:356:2: rule__TableAlias__TblAliasAssignment
            {
            pushFollow(FOLLOW_rule__TableAlias__TblAliasAssignment_in_ruleTableAlias694);
            rule__TableAlias__TblAliasAssignment();

            state._fsp--;


            }

             after(grammarAccess.getTableAliasAccess().getTblAliasAssignment()); 

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
    // $ANTLR end "ruleTableAlias"


    // $ANTLR start "entryRuleSchema"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:368:1: entryRuleSchema : ruleSchema EOF ;
    public final void entryRuleSchema() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:369:1: ( ruleSchema EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:370:1: ruleSchema EOF
            {
             before(grammarAccess.getSchemaRule()); 
            pushFollow(FOLLOW_ruleSchema_in_entryRuleSchema721);
            ruleSchema();

            state._fsp--;

             after(grammarAccess.getSchemaRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSchema728); 

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
    // $ANTLR end "entryRuleSchema"


    // $ANTLR start "ruleSchema"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:377:1: ruleSchema : ( ( rule__Schema__Alternatives ) ) ;
    public final void ruleSchema() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:381:2: ( ( ( rule__Schema__Alternatives ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:382:1: ( ( rule__Schema__Alternatives ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:382:1: ( ( rule__Schema__Alternatives ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:383:1: ( rule__Schema__Alternatives )
            {
             before(grammarAccess.getSchemaAccess().getAlternatives()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:384:1: ( rule__Schema__Alternatives )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:384:2: rule__Schema__Alternatives
            {
            pushFollow(FOLLOW_rule__Schema__Alternatives_in_ruleSchema754);
            rule__Schema__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getSchemaAccess().getAlternatives()); 

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
    // $ANTLR end "ruleSchema"


    // $ANTLR start "entryRuleDatabase"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:396:1: entryRuleDatabase : ruleDatabase EOF ;
    public final void entryRuleDatabase() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:397:1: ( ruleDatabase EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:398:1: ruleDatabase EOF
            {
             before(grammarAccess.getDatabaseRule()); 
            pushFollow(FOLLOW_ruleDatabase_in_entryRuleDatabase781);
            ruleDatabase();

            state._fsp--;

             after(grammarAccess.getDatabaseRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleDatabase788); 

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
    // $ANTLR end "entryRuleDatabase"


    // $ANTLR start "ruleDatabase"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:405:1: ruleDatabase : ( ( rule__Database__DbNameAssignment ) ) ;
    public final void ruleDatabase() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:409:2: ( ( ( rule__Database__DbNameAssignment ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:410:1: ( ( rule__Database__DbNameAssignment ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:410:1: ( ( rule__Database__DbNameAssignment ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:411:1: ( rule__Database__DbNameAssignment )
            {
             before(grammarAccess.getDatabaseAccess().getDbNameAssignment()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:412:1: ( rule__Database__DbNameAssignment )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:412:2: rule__Database__DbNameAssignment
            {
            pushFollow(FOLLOW_rule__Database__DbNameAssignment_in_ruleDatabase814);
            rule__Database__DbNameAssignment();

            state._fsp--;


            }

             after(grammarAccess.getDatabaseAccess().getDbNameAssignment()); 

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
    // $ANTLR end "ruleDatabase"


    // $ANTLR start "entryRuleWhereEntry"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:424:1: entryRuleWhereEntry : ruleWhereEntry EOF ;
    public final void entryRuleWhereEntry() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:425:1: ( ruleWhereEntry EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:426:1: ruleWhereEntry EOF
            {
             before(grammarAccess.getWhereEntryRule()); 
            pushFollow(FOLLOW_ruleWhereEntry_in_entryRuleWhereEntry841);
            ruleWhereEntry();

            state._fsp--;

             after(grammarAccess.getWhereEntryRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleWhereEntry848); 

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
    // $ANTLR end "entryRuleWhereEntry"


    // $ANTLR start "ruleWhereEntry"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:433:1: ruleWhereEntry : ( ( rule__WhereEntry__Group__0 ) ) ;
    public final void ruleWhereEntry() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:437:2: ( ( ( rule__WhereEntry__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:438:1: ( ( rule__WhereEntry__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:438:1: ( ( rule__WhereEntry__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:439:1: ( rule__WhereEntry__Group__0 )
            {
             before(grammarAccess.getWhereEntryAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:440:1: ( rule__WhereEntry__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:440:2: rule__WhereEntry__Group__0
            {
            pushFollow(FOLLOW_rule__WhereEntry__Group__0_in_ruleWhereEntry874);
            rule__WhereEntry__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getWhereEntryAccess().getGroup()); 

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
    // $ANTLR end "ruleWhereEntry"


    // $ANTLR start "entryRuleAndWhereEntry"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:452:1: entryRuleAndWhereEntry : ruleAndWhereEntry EOF ;
    public final void entryRuleAndWhereEntry() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:453:1: ( ruleAndWhereEntry EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:454:1: ruleAndWhereEntry EOF
            {
             before(grammarAccess.getAndWhereEntryRule()); 
            pushFollow(FOLLOW_ruleAndWhereEntry_in_entryRuleAndWhereEntry901);
            ruleAndWhereEntry();

            state._fsp--;

             after(grammarAccess.getAndWhereEntryRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAndWhereEntry908); 

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
    // $ANTLR end "entryRuleAndWhereEntry"


    // $ANTLR start "ruleAndWhereEntry"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:461:1: ruleAndWhereEntry : ( ( rule__AndWhereEntry__Group__0 ) ) ;
    public final void ruleAndWhereEntry() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:465:2: ( ( ( rule__AndWhereEntry__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:466:1: ( ( rule__AndWhereEntry__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:466:1: ( ( rule__AndWhereEntry__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:467:1: ( rule__AndWhereEntry__Group__0 )
            {
             before(grammarAccess.getAndWhereEntryAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:468:1: ( rule__AndWhereEntry__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:468:2: rule__AndWhereEntry__Group__0
            {
            pushFollow(FOLLOW_rule__AndWhereEntry__Group__0_in_ruleAndWhereEntry934);
            rule__AndWhereEntry__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getAndWhereEntryAccess().getGroup()); 

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
    // $ANTLR end "ruleAndWhereEntry"


    // $ANTLR start "entryRuleConcreteWhereEntry"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:480:1: entryRuleConcreteWhereEntry : ruleConcreteWhereEntry EOF ;
    public final void entryRuleConcreteWhereEntry() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:481:1: ( ruleConcreteWhereEntry EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:482:1: ruleConcreteWhereEntry EOF
            {
             before(grammarAccess.getConcreteWhereEntryRule()); 
            pushFollow(FOLLOW_ruleConcreteWhereEntry_in_entryRuleConcreteWhereEntry961);
            ruleConcreteWhereEntry();

            state._fsp--;

             after(grammarAccess.getConcreteWhereEntryRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleConcreteWhereEntry968); 

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
    // $ANTLR end "entryRuleConcreteWhereEntry"


    // $ANTLR start "ruleConcreteWhereEntry"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:489:1: ruleConcreteWhereEntry : ( ( rule__ConcreteWhereEntry__Alternatives ) ) ;
    public final void ruleConcreteWhereEntry() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:493:2: ( ( ( rule__ConcreteWhereEntry__Alternatives ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:494:1: ( ( rule__ConcreteWhereEntry__Alternatives ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:494:1: ( ( rule__ConcreteWhereEntry__Alternatives ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:495:1: ( rule__ConcreteWhereEntry__Alternatives )
            {
             before(grammarAccess.getConcreteWhereEntryAccess().getAlternatives()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:496:1: ( rule__ConcreteWhereEntry__Alternatives )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:496:2: rule__ConcreteWhereEntry__Alternatives
            {
            pushFollow(FOLLOW_rule__ConcreteWhereEntry__Alternatives_in_ruleConcreteWhereEntry994);
            rule__ConcreteWhereEntry__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getConcreteWhereEntryAccess().getAlternatives()); 

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
    // $ANTLR end "ruleConcreteWhereEntry"


    // $ANTLR start "entryRuleParWhereEntry"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:508:1: entryRuleParWhereEntry : ruleParWhereEntry EOF ;
    public final void entryRuleParWhereEntry() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:509:1: ( ruleParWhereEntry EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:510:1: ruleParWhereEntry EOF
            {
             before(grammarAccess.getParWhereEntryRule()); 
            pushFollow(FOLLOW_ruleParWhereEntry_in_entryRuleParWhereEntry1021);
            ruleParWhereEntry();

            state._fsp--;

             after(grammarAccess.getParWhereEntryRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleParWhereEntry1028); 

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
    // $ANTLR end "entryRuleParWhereEntry"


    // $ANTLR start "ruleParWhereEntry"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:517:1: ruleParWhereEntry : ( ( rule__ParWhereEntry__Group__0 ) ) ;
    public final void ruleParWhereEntry() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:521:2: ( ( ( rule__ParWhereEntry__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:522:1: ( ( rule__ParWhereEntry__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:522:1: ( ( rule__ParWhereEntry__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:523:1: ( rule__ParWhereEntry__Group__0 )
            {
             before(grammarAccess.getParWhereEntryAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:524:1: ( rule__ParWhereEntry__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:524:2: rule__ParWhereEntry__Group__0
            {
            pushFollow(FOLLOW_rule__ParWhereEntry__Group__0_in_ruleParWhereEntry1054);
            rule__ParWhereEntry__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getParWhereEntryAccess().getGroup()); 

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
    // $ANTLR end "ruleParWhereEntry"


    // $ANTLR start "entryRuleExpressionWhereEntry"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:536:1: entryRuleExpressionWhereEntry : ruleExpressionWhereEntry EOF ;
    public final void entryRuleExpressionWhereEntry() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:537:1: ( ruleExpressionWhereEntry EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:538:1: ruleExpressionWhereEntry EOF
            {
             before(grammarAccess.getExpressionWhereEntryRule()); 
            pushFollow(FOLLOW_ruleExpressionWhereEntry_in_entryRuleExpressionWhereEntry1081);
            ruleExpressionWhereEntry();

            state._fsp--;

             after(grammarAccess.getExpressionWhereEntryRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpressionWhereEntry1088); 

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
    // $ANTLR end "entryRuleExpressionWhereEntry"


    // $ANTLR start "ruleExpressionWhereEntry"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:545:1: ruleExpressionWhereEntry : ( ( rule__ExpressionWhereEntry__Alternatives ) ) ;
    public final void ruleExpressionWhereEntry() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:549:2: ( ( ( rule__ExpressionWhereEntry__Alternatives ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:550:1: ( ( rule__ExpressionWhereEntry__Alternatives ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:550:1: ( ( rule__ExpressionWhereEntry__Alternatives ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:551:1: ( rule__ExpressionWhereEntry__Alternatives )
            {
             before(grammarAccess.getExpressionWhereEntryAccess().getAlternatives()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:552:1: ( rule__ExpressionWhereEntry__Alternatives )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:552:2: rule__ExpressionWhereEntry__Alternatives
            {
            pushFollow(FOLLOW_rule__ExpressionWhereEntry__Alternatives_in_ruleExpressionWhereEntry1114);
            rule__ExpressionWhereEntry__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getExpressionWhereEntryAccess().getAlternatives()); 

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
    // $ANTLR end "ruleExpressionWhereEntry"


    // $ANTLR start "entryRuleSingleExpressionWhereEntry"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:564:1: entryRuleSingleExpressionWhereEntry : ruleSingleExpressionWhereEntry EOF ;
    public final void entryRuleSingleExpressionWhereEntry() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:565:1: ( ruleSingleExpressionWhereEntry EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:566:1: ruleSingleExpressionWhereEntry EOF
            {
             before(grammarAccess.getSingleExpressionWhereEntryRule()); 
            pushFollow(FOLLOW_ruleSingleExpressionWhereEntry_in_entryRuleSingleExpressionWhereEntry1141);
            ruleSingleExpressionWhereEntry();

            state._fsp--;

             after(grammarAccess.getSingleExpressionWhereEntryRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSingleExpressionWhereEntry1148); 

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
    // $ANTLR end "entryRuleSingleExpressionWhereEntry"


    // $ANTLR start "ruleSingleExpressionWhereEntry"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:573:1: ruleSingleExpressionWhereEntry : ( ( rule__SingleExpressionWhereEntry__Group__0 ) ) ;
    public final void ruleSingleExpressionWhereEntry() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:577:2: ( ( ( rule__SingleExpressionWhereEntry__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:578:1: ( ( rule__SingleExpressionWhereEntry__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:578:1: ( ( rule__SingleExpressionWhereEntry__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:579:1: ( rule__SingleExpressionWhereEntry__Group__0 )
            {
             before(grammarAccess.getSingleExpressionWhereEntryAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:580:1: ( rule__SingleExpressionWhereEntry__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:580:2: rule__SingleExpressionWhereEntry__Group__0
            {
            pushFollow(FOLLOW_rule__SingleExpressionWhereEntry__Group__0_in_ruleSingleExpressionWhereEntry1174);
            rule__SingleExpressionWhereEntry__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getSingleExpressionWhereEntryAccess().getGroup()); 

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
    // $ANTLR end "ruleSingleExpressionWhereEntry"


    // $ANTLR start "entryRuleExpression"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:592:1: entryRuleExpression : ruleExpression EOF ;
    public final void entryRuleExpression() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:593:1: ( ruleExpression EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:594:1: ruleExpression EOF
            {
             before(grammarAccess.getExpressionRule()); 
            pushFollow(FOLLOW_ruleExpression_in_entryRuleExpression1201);
            ruleExpression();

            state._fsp--;

             after(grammarAccess.getExpressionRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpression1208); 

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
    // $ANTLR end "entryRuleExpression"


    // $ANTLR start "ruleExpression"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:601:1: ruleExpression : ( ( rule__Expression__Alternatives ) ) ;
    public final void ruleExpression() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:605:2: ( ( ( rule__Expression__Alternatives ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:606:1: ( ( rule__Expression__Alternatives ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:606:1: ( ( rule__Expression__Alternatives ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:607:1: ( rule__Expression__Alternatives )
            {
             before(grammarAccess.getExpressionAccess().getAlternatives()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:608:1: ( rule__Expression__Alternatives )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:608:2: rule__Expression__Alternatives
            {
            pushFollow(FOLLOW_rule__Expression__Alternatives_in_ruleExpression1234);
            rule__Expression__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getExpressionAccess().getAlternatives()); 

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
    // $ANTLR end "ruleExpression"


    // $ANTLR start "entryRuleReplacableValue"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:620:1: entryRuleReplacableValue : ruleReplacableValue EOF ;
    public final void entryRuleReplacableValue() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:621:1: ( ruleReplacableValue EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:622:1: ruleReplacableValue EOF
            {
             before(grammarAccess.getReplacableValueRule()); 
            pushFollow(FOLLOW_ruleReplacableValue_in_entryRuleReplacableValue1261);
            ruleReplacableValue();

            state._fsp--;

             after(grammarAccess.getReplacableValueRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleReplacableValue1268); 

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
    // $ANTLR end "entryRuleReplacableValue"


    // $ANTLR start "ruleReplacableValue"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:629:1: ruleReplacableValue : ( ( rule__ReplacableValue__ValueAssignment ) ) ;
    public final void ruleReplacableValue() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:633:2: ( ( ( rule__ReplacableValue__ValueAssignment ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:634:1: ( ( rule__ReplacableValue__ValueAssignment ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:634:1: ( ( rule__ReplacableValue__ValueAssignment ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:635:1: ( rule__ReplacableValue__ValueAssignment )
            {
             before(grammarAccess.getReplacableValueAccess().getValueAssignment()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:636:1: ( rule__ReplacableValue__ValueAssignment )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:636:2: rule__ReplacableValue__ValueAssignment
            {
            pushFollow(FOLLOW_rule__ReplacableValue__ValueAssignment_in_ruleReplacableValue1294);
            rule__ReplacableValue__ValueAssignment();

            state._fsp--;


            }

             after(grammarAccess.getReplacableValueAccess().getValueAssignment()); 

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
    // $ANTLR end "ruleReplacableValue"


    // $ANTLR start "entryRuleDoubleExpression"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:648:1: entryRuleDoubleExpression : ruleDoubleExpression EOF ;
    public final void entryRuleDoubleExpression() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:649:1: ( ruleDoubleExpression EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:650:1: ruleDoubleExpression EOF
            {
             before(grammarAccess.getDoubleExpressionRule()); 
            pushFollow(FOLLOW_ruleDoubleExpression_in_entryRuleDoubleExpression1321);
            ruleDoubleExpression();

            state._fsp--;

             after(grammarAccess.getDoubleExpressionRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleDoubleExpression1328); 

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
    // $ANTLR end "entryRuleDoubleExpression"


    // $ANTLR start "ruleDoubleExpression"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:657:1: ruleDoubleExpression : ( ( rule__DoubleExpression__ValueAssignment ) ) ;
    public final void ruleDoubleExpression() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:661:2: ( ( ( rule__DoubleExpression__ValueAssignment ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:662:1: ( ( rule__DoubleExpression__ValueAssignment ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:662:1: ( ( rule__DoubleExpression__ValueAssignment ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:663:1: ( rule__DoubleExpression__ValueAssignment )
            {
             before(grammarAccess.getDoubleExpressionAccess().getValueAssignment()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:664:1: ( rule__DoubleExpression__ValueAssignment )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:664:2: rule__DoubleExpression__ValueAssignment
            {
            pushFollow(FOLLOW_rule__DoubleExpression__ValueAssignment_in_ruleDoubleExpression1354);
            rule__DoubleExpression__ValueAssignment();

            state._fsp--;


            }

             after(grammarAccess.getDoubleExpressionAccess().getValueAssignment()); 

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
    // $ANTLR end "ruleDoubleExpression"


    // $ANTLR start "entryRuleLongExpression"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:676:1: entryRuleLongExpression : ruleLongExpression EOF ;
    public final void entryRuleLongExpression() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:677:1: ( ruleLongExpression EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:678:1: ruleLongExpression EOF
            {
             before(grammarAccess.getLongExpressionRule()); 
            pushFollow(FOLLOW_ruleLongExpression_in_entryRuleLongExpression1381);
            ruleLongExpression();

            state._fsp--;

             after(grammarAccess.getLongExpressionRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleLongExpression1388); 

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
    // $ANTLR end "entryRuleLongExpression"


    // $ANTLR start "ruleLongExpression"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:685:1: ruleLongExpression : ( ( rule__LongExpression__ValueAssignment ) ) ;
    public final void ruleLongExpression() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:689:2: ( ( ( rule__LongExpression__ValueAssignment ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:690:1: ( ( rule__LongExpression__ValueAssignment ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:690:1: ( ( rule__LongExpression__ValueAssignment ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:691:1: ( rule__LongExpression__ValueAssignment )
            {
             before(grammarAccess.getLongExpressionAccess().getValueAssignment()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:692:1: ( rule__LongExpression__ValueAssignment )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:692:2: rule__LongExpression__ValueAssignment
            {
            pushFollow(FOLLOW_rule__LongExpression__ValueAssignment_in_ruleLongExpression1414);
            rule__LongExpression__ValueAssignment();

            state._fsp--;


            }

             after(grammarAccess.getLongExpressionAccess().getValueAssignment()); 

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
    // $ANTLR end "ruleLongExpression"


    // $ANTLR start "entryRuleStringExpression"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:704:1: entryRuleStringExpression : ruleStringExpression EOF ;
    public final void entryRuleStringExpression() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:705:1: ( ruleStringExpression EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:706:1: ruleStringExpression EOF
            {
             before(grammarAccess.getStringExpressionRule()); 
            pushFollow(FOLLOW_ruleStringExpression_in_entryRuleStringExpression1441);
            ruleStringExpression();

            state._fsp--;

             after(grammarAccess.getStringExpressionRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleStringExpression1448); 

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
    // $ANTLR end "entryRuleStringExpression"


    // $ANTLR start "ruleStringExpression"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:713:1: ruleStringExpression : ( ( rule__StringExpression__ValueAssignment ) ) ;
    public final void ruleStringExpression() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:717:2: ( ( ( rule__StringExpression__ValueAssignment ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:718:1: ( ( rule__StringExpression__ValueAssignment ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:718:1: ( ( rule__StringExpression__ValueAssignment ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:719:1: ( rule__StringExpression__ValueAssignment )
            {
             before(grammarAccess.getStringExpressionAccess().getValueAssignment()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:720:1: ( rule__StringExpression__ValueAssignment )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:720:2: rule__StringExpression__ValueAssignment
            {
            pushFollow(FOLLOW_rule__StringExpression__ValueAssignment_in_ruleStringExpression1474);
            rule__StringExpression__ValueAssignment();

            state._fsp--;


            }

             after(grammarAccess.getStringExpressionAccess().getValueAssignment()); 

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
    // $ANTLR end "ruleStringExpression"


    // $ANTLR start "entryRuleNullExpression"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:732:1: entryRuleNullExpression : ruleNullExpression EOF ;
    public final void entryRuleNullExpression() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:733:1: ( ruleNullExpression EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:734:1: ruleNullExpression EOF
            {
             before(grammarAccess.getNullExpressionRule()); 
            pushFollow(FOLLOW_ruleNullExpression_in_entryRuleNullExpression1501);
            ruleNullExpression();

            state._fsp--;

             after(grammarAccess.getNullExpressionRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleNullExpression1508); 

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
    // $ANTLR end "entryRuleNullExpression"


    // $ANTLR start "ruleNullExpression"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:741:1: ruleNullExpression : ( ( rule__NullExpression__ValueAssignment ) ) ;
    public final void ruleNullExpression() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:745:2: ( ( ( rule__NullExpression__ValueAssignment ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:746:1: ( ( rule__NullExpression__ValueAssignment ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:746:1: ( ( rule__NullExpression__ValueAssignment ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:747:1: ( rule__NullExpression__ValueAssignment )
            {
             before(grammarAccess.getNullExpressionAccess().getValueAssignment()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:748:1: ( rule__NullExpression__ValueAssignment )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:748:2: rule__NullExpression__ValueAssignment
            {
            pushFollow(FOLLOW_rule__NullExpression__ValueAssignment_in_ruleNullExpression1534);
            rule__NullExpression__ValueAssignment();

            state._fsp--;


            }

             after(grammarAccess.getNullExpressionAccess().getValueAssignment()); 

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
    // $ANTLR end "ruleNullExpression"


    // $ANTLR start "entryRuleDateExpression"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:760:1: entryRuleDateExpression : ruleDateExpression EOF ;
    public final void entryRuleDateExpression() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:761:1: ( ruleDateExpression EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:762:1: ruleDateExpression EOF
            {
             before(grammarAccess.getDateExpressionRule()); 
            pushFollow(FOLLOW_ruleDateExpression_in_entryRuleDateExpression1561);
            ruleDateExpression();

            state._fsp--;

             after(grammarAccess.getDateExpressionRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleDateExpression1568); 

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
    // $ANTLR end "entryRuleDateExpression"


    // $ANTLR start "ruleDateExpression"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:769:1: ruleDateExpression : ( ( rule__DateExpression__ValueAssignment ) ) ;
    public final void ruleDateExpression() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:773:2: ( ( ( rule__DateExpression__ValueAssignment ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:774:1: ( ( rule__DateExpression__ValueAssignment ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:774:1: ( ( rule__DateExpression__ValueAssignment ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:775:1: ( rule__DateExpression__ValueAssignment )
            {
             before(grammarAccess.getDateExpressionAccess().getValueAssignment()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:776:1: ( rule__DateExpression__ValueAssignment )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:776:2: rule__DateExpression__ValueAssignment
            {
            pushFollow(FOLLOW_rule__DateExpression__ValueAssignment_in_ruleDateExpression1594);
            rule__DateExpression__ValueAssignment();

            state._fsp--;


            }

             after(grammarAccess.getDateExpressionAccess().getValueAssignment()); 

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
    // $ANTLR end "ruleDateExpression"


    // $ANTLR start "entryRuleBooleanExpression"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:788:1: entryRuleBooleanExpression : ruleBooleanExpression EOF ;
    public final void entryRuleBooleanExpression() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:789:1: ( ruleBooleanExpression EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:790:1: ruleBooleanExpression EOF
            {
             before(grammarAccess.getBooleanExpressionRule()); 
            pushFollow(FOLLOW_ruleBooleanExpression_in_entryRuleBooleanExpression1621);
            ruleBooleanExpression();

            state._fsp--;

             after(grammarAccess.getBooleanExpressionRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleBooleanExpression1628); 

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
    // $ANTLR end "entryRuleBooleanExpression"


    // $ANTLR start "ruleBooleanExpression"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:797:1: ruleBooleanExpression : ( ( rule__BooleanExpression__Alternatives ) ) ;
    public final void ruleBooleanExpression() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:801:2: ( ( ( rule__BooleanExpression__Alternatives ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:802:1: ( ( rule__BooleanExpression__Alternatives ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:802:1: ( ( rule__BooleanExpression__Alternatives ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:803:1: ( rule__BooleanExpression__Alternatives )
            {
             before(grammarAccess.getBooleanExpressionAccess().getAlternatives()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:804:1: ( rule__BooleanExpression__Alternatives )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:804:2: rule__BooleanExpression__Alternatives
            {
            pushFollow(FOLLOW_rule__BooleanExpression__Alternatives_in_ruleBooleanExpression1654);
            rule__BooleanExpression__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getBooleanExpressionAccess().getAlternatives()); 

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
    // $ANTLR end "ruleBooleanExpression"


    // $ANTLR start "entryRuleMultiExpressionWhereEntry"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:816:1: entryRuleMultiExpressionWhereEntry : ruleMultiExpressionWhereEntry EOF ;
    public final void entryRuleMultiExpressionWhereEntry() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:817:1: ( ruleMultiExpressionWhereEntry EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:818:1: ruleMultiExpressionWhereEntry EOF
            {
             before(grammarAccess.getMultiExpressionWhereEntryRule()); 
            pushFollow(FOLLOW_ruleMultiExpressionWhereEntry_in_entryRuleMultiExpressionWhereEntry1681);
            ruleMultiExpressionWhereEntry();

            state._fsp--;

             after(grammarAccess.getMultiExpressionWhereEntryRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleMultiExpressionWhereEntry1688); 

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
    // $ANTLR end "entryRuleMultiExpressionWhereEntry"


    // $ANTLR start "ruleMultiExpressionWhereEntry"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:825:1: ruleMultiExpressionWhereEntry : ( ( rule__MultiExpressionWhereEntry__Group__0 ) ) ;
    public final void ruleMultiExpressionWhereEntry() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:829:2: ( ( ( rule__MultiExpressionWhereEntry__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:830:1: ( ( rule__MultiExpressionWhereEntry__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:830:1: ( ( rule__MultiExpressionWhereEntry__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:831:1: ( rule__MultiExpressionWhereEntry__Group__0 )
            {
             before(grammarAccess.getMultiExpressionWhereEntryAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:832:1: ( rule__MultiExpressionWhereEntry__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:832:2: rule__MultiExpressionWhereEntry__Group__0
            {
            pushFollow(FOLLOW_rule__MultiExpressionWhereEntry__Group__0_in_ruleMultiExpressionWhereEntry1714);
            rule__MultiExpressionWhereEntry__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getMultiExpressionWhereEntryAccess().getGroup()); 

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
    // $ANTLR end "ruleMultiExpressionWhereEntry"


    // $ANTLR start "entryRuleArrayExpression"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:844:1: entryRuleArrayExpression : ruleArrayExpression EOF ;
    public final void entryRuleArrayExpression() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:845:1: ( ruleArrayExpression EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:846:1: ruleArrayExpression EOF
            {
             before(grammarAccess.getArrayExpressionRule()); 
            pushFollow(FOLLOW_ruleArrayExpression_in_entryRuleArrayExpression1741);
            ruleArrayExpression();

            state._fsp--;

             after(grammarAccess.getArrayExpressionRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleArrayExpression1748); 

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
    // $ANTLR end "entryRuleArrayExpression"


    // $ANTLR start "ruleArrayExpression"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:853:1: ruleArrayExpression : ( ( rule__ArrayExpression__Alternatives ) ) ;
    public final void ruleArrayExpression() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:857:2: ( ( ( rule__ArrayExpression__Alternatives ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:858:1: ( ( rule__ArrayExpression__Alternatives ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:858:1: ( ( rule__ArrayExpression__Alternatives ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:859:1: ( rule__ArrayExpression__Alternatives )
            {
             before(grammarAccess.getArrayExpressionAccess().getAlternatives()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:860:1: ( rule__ArrayExpression__Alternatives )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:860:2: rule__ArrayExpression__Alternatives
            {
            pushFollow(FOLLOW_rule__ArrayExpression__Alternatives_in_ruleArrayExpression1774);
            rule__ArrayExpression__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getArrayExpressionAccess().getAlternatives()); 

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
    // $ANTLR end "ruleArrayExpression"


    // $ANTLR start "entryRuleDoubleArrayExpression"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:872:1: entryRuleDoubleArrayExpression : ruleDoubleArrayExpression EOF ;
    public final void entryRuleDoubleArrayExpression() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:873:1: ( ruleDoubleArrayExpression EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:874:1: ruleDoubleArrayExpression EOF
            {
             before(grammarAccess.getDoubleArrayExpressionRule()); 
            pushFollow(FOLLOW_ruleDoubleArrayExpression_in_entryRuleDoubleArrayExpression1801);
            ruleDoubleArrayExpression();

            state._fsp--;

             after(grammarAccess.getDoubleArrayExpressionRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleDoubleArrayExpression1808); 

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
    // $ANTLR end "entryRuleDoubleArrayExpression"


    // $ANTLR start "ruleDoubleArrayExpression"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:881:1: ruleDoubleArrayExpression : ( ( rule__DoubleArrayExpression__Group__0 ) ) ;
    public final void ruleDoubleArrayExpression() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:885:2: ( ( ( rule__DoubleArrayExpression__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:886:1: ( ( rule__DoubleArrayExpression__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:886:1: ( ( rule__DoubleArrayExpression__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:887:1: ( rule__DoubleArrayExpression__Group__0 )
            {
             before(grammarAccess.getDoubleArrayExpressionAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:888:1: ( rule__DoubleArrayExpression__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:888:2: rule__DoubleArrayExpression__Group__0
            {
            pushFollow(FOLLOW_rule__DoubleArrayExpression__Group__0_in_ruleDoubleArrayExpression1834);
            rule__DoubleArrayExpression__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getDoubleArrayExpressionAccess().getGroup()); 

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
    // $ANTLR end "ruleDoubleArrayExpression"


    // $ANTLR start "entryRuleLongArrayExpression"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:900:1: entryRuleLongArrayExpression : ruleLongArrayExpression EOF ;
    public final void entryRuleLongArrayExpression() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:901:1: ( ruleLongArrayExpression EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:902:1: ruleLongArrayExpression EOF
            {
             before(grammarAccess.getLongArrayExpressionRule()); 
            pushFollow(FOLLOW_ruleLongArrayExpression_in_entryRuleLongArrayExpression1861);
            ruleLongArrayExpression();

            state._fsp--;

             after(grammarAccess.getLongArrayExpressionRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleLongArrayExpression1868); 

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
    // $ANTLR end "entryRuleLongArrayExpression"


    // $ANTLR start "ruleLongArrayExpression"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:909:1: ruleLongArrayExpression : ( ( rule__LongArrayExpression__Group__0 ) ) ;
    public final void ruleLongArrayExpression() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:913:2: ( ( ( rule__LongArrayExpression__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:914:1: ( ( rule__LongArrayExpression__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:914:1: ( ( rule__LongArrayExpression__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:915:1: ( rule__LongArrayExpression__Group__0 )
            {
             before(grammarAccess.getLongArrayExpressionAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:916:1: ( rule__LongArrayExpression__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:916:2: rule__LongArrayExpression__Group__0
            {
            pushFollow(FOLLOW_rule__LongArrayExpression__Group__0_in_ruleLongArrayExpression1894);
            rule__LongArrayExpression__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getLongArrayExpressionAccess().getGroup()); 

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
    // $ANTLR end "ruleLongArrayExpression"


    // $ANTLR start "entryRuleStringArrayExpression"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:928:1: entryRuleStringArrayExpression : ruleStringArrayExpression EOF ;
    public final void entryRuleStringArrayExpression() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:929:1: ( ruleStringArrayExpression EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:930:1: ruleStringArrayExpression EOF
            {
             before(grammarAccess.getStringArrayExpressionRule()); 
            pushFollow(FOLLOW_ruleStringArrayExpression_in_entryRuleStringArrayExpression1921);
            ruleStringArrayExpression();

            state._fsp--;

             after(grammarAccess.getStringArrayExpressionRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleStringArrayExpression1928); 

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
    // $ANTLR end "entryRuleStringArrayExpression"


    // $ANTLR start "ruleStringArrayExpression"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:937:1: ruleStringArrayExpression : ( ( rule__StringArrayExpression__Group__0 ) ) ;
    public final void ruleStringArrayExpression() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:941:2: ( ( ( rule__StringArrayExpression__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:942:1: ( ( rule__StringArrayExpression__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:942:1: ( ( rule__StringArrayExpression__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:943:1: ( rule__StringArrayExpression__Group__0 )
            {
             before(grammarAccess.getStringArrayExpressionAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:944:1: ( rule__StringArrayExpression__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:944:2: rule__StringArrayExpression__Group__0
            {
            pushFollow(FOLLOW_rule__StringArrayExpression__Group__0_in_ruleStringArrayExpression1954);
            rule__StringArrayExpression__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getStringArrayExpressionAccess().getGroup()); 

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
    // $ANTLR end "ruleStringArrayExpression"


    // $ANTLR start "entryRuleNullArrayExpression"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:956:1: entryRuleNullArrayExpression : ruleNullArrayExpression EOF ;
    public final void entryRuleNullArrayExpression() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:957:1: ( ruleNullArrayExpression EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:958:1: ruleNullArrayExpression EOF
            {
             before(grammarAccess.getNullArrayExpressionRule()); 
            pushFollow(FOLLOW_ruleNullArrayExpression_in_entryRuleNullArrayExpression1981);
            ruleNullArrayExpression();

            state._fsp--;

             after(grammarAccess.getNullArrayExpressionRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleNullArrayExpression1988); 

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
    // $ANTLR end "entryRuleNullArrayExpression"


    // $ANTLR start "ruleNullArrayExpression"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:965:1: ruleNullArrayExpression : ( ( rule__NullArrayExpression__Group__0 ) ) ;
    public final void ruleNullArrayExpression() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:969:2: ( ( ( rule__NullArrayExpression__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:970:1: ( ( rule__NullArrayExpression__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:970:1: ( ( rule__NullArrayExpression__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:971:1: ( rule__NullArrayExpression__Group__0 )
            {
             before(grammarAccess.getNullArrayExpressionAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:972:1: ( rule__NullArrayExpression__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:972:2: rule__NullArrayExpression__Group__0
            {
            pushFollow(FOLLOW_rule__NullArrayExpression__Group__0_in_ruleNullArrayExpression2014);
            rule__NullArrayExpression__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getNullArrayExpressionAccess().getGroup()); 

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
    // $ANTLR end "ruleNullArrayExpression"


    // $ANTLR start "entryRuleDateArrayExpression"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:984:1: entryRuleDateArrayExpression : ruleDateArrayExpression EOF ;
    public final void entryRuleDateArrayExpression() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:985:1: ( ruleDateArrayExpression EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:986:1: ruleDateArrayExpression EOF
            {
             before(grammarAccess.getDateArrayExpressionRule()); 
            pushFollow(FOLLOW_ruleDateArrayExpression_in_entryRuleDateArrayExpression2041);
            ruleDateArrayExpression();

            state._fsp--;

             after(grammarAccess.getDateArrayExpressionRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleDateArrayExpression2048); 

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
    // $ANTLR end "entryRuleDateArrayExpression"


    // $ANTLR start "ruleDateArrayExpression"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:993:1: ruleDateArrayExpression : ( ( rule__DateArrayExpression__Group__0 ) ) ;
    public final void ruleDateArrayExpression() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:997:2: ( ( ( rule__DateArrayExpression__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:998:1: ( ( rule__DateArrayExpression__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:998:1: ( ( rule__DateArrayExpression__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:999:1: ( rule__DateArrayExpression__Group__0 )
            {
             before(grammarAccess.getDateArrayExpressionAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1000:1: ( rule__DateArrayExpression__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1000:2: rule__DateArrayExpression__Group__0
            {
            pushFollow(FOLLOW_rule__DateArrayExpression__Group__0_in_ruleDateArrayExpression2074);
            rule__DateArrayExpression__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getDateArrayExpressionAccess().getGroup()); 

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
    // $ANTLR end "ruleDateArrayExpression"


    // $ANTLR start "entryRuleBooleanArrayExpression"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1012:1: entryRuleBooleanArrayExpression : ruleBooleanArrayExpression EOF ;
    public final void entryRuleBooleanArrayExpression() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1013:1: ( ruleBooleanArrayExpression EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1014:1: ruleBooleanArrayExpression EOF
            {
             before(grammarAccess.getBooleanArrayExpressionRule()); 
            pushFollow(FOLLOW_ruleBooleanArrayExpression_in_entryRuleBooleanArrayExpression2101);
            ruleBooleanArrayExpression();

            state._fsp--;

             after(grammarAccess.getBooleanArrayExpressionRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleBooleanArrayExpression2108); 

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
    // $ANTLR end "entryRuleBooleanArrayExpression"


    // $ANTLR start "ruleBooleanArrayExpression"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1021:1: ruleBooleanArrayExpression : ( ( rule__BooleanArrayExpression__Group__0 ) ) ;
    public final void ruleBooleanArrayExpression() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1025:2: ( ( ( rule__BooleanArrayExpression__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1026:1: ( ( rule__BooleanArrayExpression__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1026:1: ( ( rule__BooleanArrayExpression__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1027:1: ( rule__BooleanArrayExpression__Group__0 )
            {
             before(grammarAccess.getBooleanArrayExpressionAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1028:1: ( rule__BooleanArrayExpression__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1028:2: rule__BooleanArrayExpression__Group__0
            {
            pushFollow(FOLLOW_rule__BooleanArrayExpression__Group__0_in_ruleBooleanArrayExpression2134);
            rule__BooleanArrayExpression__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getBooleanArrayExpressionAccess().getGroup()); 

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
    // $ANTLR end "ruleBooleanArrayExpression"


    // $ANTLR start "ruleArrayOperator"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1041:1: ruleArrayOperator : ( ( rule__ArrayOperator__Alternatives ) ) ;
    public final void ruleArrayOperator() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1045:1: ( ( ( rule__ArrayOperator__Alternatives ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1046:1: ( ( rule__ArrayOperator__Alternatives ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1046:1: ( ( rule__ArrayOperator__Alternatives ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1047:1: ( rule__ArrayOperator__Alternatives )
            {
             before(grammarAccess.getArrayOperatorAccess().getAlternatives()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1048:1: ( rule__ArrayOperator__Alternatives )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1048:2: rule__ArrayOperator__Alternatives
            {
            pushFollow(FOLLOW_rule__ArrayOperator__Alternatives_in_ruleArrayOperator2171);
            rule__ArrayOperator__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getArrayOperatorAccess().getAlternatives()); 

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
    // $ANTLR end "ruleArrayOperator"


    // $ANTLR start "ruleOperator"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1060:1: ruleOperator : ( ( rule__Operator__Alternatives ) ) ;
    public final void ruleOperator() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1064:1: ( ( ( rule__Operator__Alternatives ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1065:1: ( ( rule__Operator__Alternatives ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1065:1: ( ( rule__Operator__Alternatives ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1066:1: ( rule__Operator__Alternatives )
            {
             before(grammarAccess.getOperatorAccess().getAlternatives()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1067:1: ( rule__Operator__Alternatives )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1067:2: rule__Operator__Alternatives
            {
            pushFollow(FOLLOW_rule__Operator__Alternatives_in_ruleOperator2207);
            rule__Operator__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getOperatorAccess().getAlternatives()); 

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
    // $ANTLR end "ruleOperator"


    // $ANTLR start "rule__ColumnOrAlias__Alternatives"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1078:1: rule__ColumnOrAlias__Alternatives : ( ( ruleColumnFull ) | ( ( rule__ColumnOrAlias__Group_1__0 ) ) | ( ( rule__ColumnOrAlias__Group_2__0 ) ) );
    public final void rule__ColumnOrAlias__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1082:1: ( ( ruleColumnFull ) | ( ( rule__ColumnOrAlias__Group_1__0 ) ) | ( ( rule__ColumnOrAlias__Group_2__0 ) ) )
            int alt1=3;
            alt1 = dfa1.predict(input);
            switch (alt1) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1083:1: ( ruleColumnFull )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1083:1: ( ruleColumnFull )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1084:1: ruleColumnFull
                    {
                     before(grammarAccess.getColumnOrAliasAccess().getColumnFullParserRuleCall_0()); 
                    pushFollow(FOLLOW_ruleColumnFull_in_rule__ColumnOrAlias__Alternatives2242);
                    ruleColumnFull();

                    state._fsp--;

                     after(grammarAccess.getColumnOrAliasAccess().getColumnFullParserRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1089:6: ( ( rule__ColumnOrAlias__Group_1__0 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1089:6: ( ( rule__ColumnOrAlias__Group_1__0 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1090:1: ( rule__ColumnOrAlias__Group_1__0 )
                    {
                     before(grammarAccess.getColumnOrAliasAccess().getGroup_1()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1091:1: ( rule__ColumnOrAlias__Group_1__0 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1091:2: rule__ColumnOrAlias__Group_1__0
                    {
                    pushFollow(FOLLOW_rule__ColumnOrAlias__Group_1__0_in_rule__ColumnOrAlias__Alternatives2259);
                    rule__ColumnOrAlias__Group_1__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getColumnOrAliasAccess().getGroup_1()); 

                    }


                    }
                    break;
                case 3 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1095:6: ( ( rule__ColumnOrAlias__Group_2__0 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1095:6: ( ( rule__ColumnOrAlias__Group_2__0 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1096:1: ( rule__ColumnOrAlias__Group_2__0 )
                    {
                     before(grammarAccess.getColumnOrAliasAccess().getGroup_2()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1097:1: ( rule__ColumnOrAlias__Group_2__0 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1097:2: rule__ColumnOrAlias__Group_2__0
                    {
                    pushFollow(FOLLOW_rule__ColumnOrAlias__Group_2__0_in_rule__ColumnOrAlias__Alternatives2277);
                    rule__ColumnOrAlias__Group_2__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getColumnOrAliasAccess().getGroup_2()); 

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


    // $ANTLR start "rule__ColumnFull__Alternatives"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1106:1: rule__ColumnFull__Alternatives : ( ( ( rule__ColumnFull__ColNameAssignment_0 ) ) | ( ( rule__ColumnFull__Group_1__0 ) ) );
    public final void rule__ColumnFull__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1110:1: ( ( ( rule__ColumnFull__ColNameAssignment_0 ) ) | ( ( rule__ColumnFull__Group_1__0 ) ) )
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==RULE_ID) ) {
                int LA2_1 = input.LA(2);

                if ( (LA2_1==30) ) {
                    alt2=2;
                }
                else if ( (LA2_1==EOF||LA2_1==RULE_ID||LA2_1==26||(LA2_1>=28 && LA2_1<=29)) ) {
                    alt2=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 2, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }
            switch (alt2) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1111:1: ( ( rule__ColumnFull__ColNameAssignment_0 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1111:1: ( ( rule__ColumnFull__ColNameAssignment_0 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1112:1: ( rule__ColumnFull__ColNameAssignment_0 )
                    {
                     before(grammarAccess.getColumnFullAccess().getColNameAssignment_0()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1113:1: ( rule__ColumnFull__ColNameAssignment_0 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1113:2: rule__ColumnFull__ColNameAssignment_0
                    {
                    pushFollow(FOLLOW_rule__ColumnFull__ColNameAssignment_0_in_rule__ColumnFull__Alternatives2310);
                    rule__ColumnFull__ColNameAssignment_0();

                    state._fsp--;


                    }

                     after(grammarAccess.getColumnFullAccess().getColNameAssignment_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1117:6: ( ( rule__ColumnFull__Group_1__0 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1117:6: ( ( rule__ColumnFull__Group_1__0 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1118:1: ( rule__ColumnFull__Group_1__0 )
                    {
                     before(grammarAccess.getColumnFullAccess().getGroup_1()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1119:1: ( rule__ColumnFull__Group_1__0 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1119:2: rule__ColumnFull__Group_1__0
                    {
                    pushFollow(FOLLOW_rule__ColumnFull__Group_1__0_in_rule__ColumnFull__Alternatives2328);
                    rule__ColumnFull__Group_1__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getColumnFullAccess().getGroup_1()); 

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
    // $ANTLR end "rule__ColumnFull__Alternatives"


    // $ANTLR start "rule__TableOrAlias__Alternatives"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1128:1: rule__TableOrAlias__Alternatives : ( ( ruleTableFull ) | ( ( rule__TableOrAlias__Group_1__0 ) ) | ( ( rule__TableOrAlias__Group_2__0 ) ) );
    public final void rule__TableOrAlias__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1132:1: ( ( ruleTableFull ) | ( ( rule__TableOrAlias__Group_1__0 ) ) | ( ( rule__TableOrAlias__Group_2__0 ) ) )
            int alt3=3;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==RULE_ID) ) {
                switch ( input.LA(2) ) {
                case 30:
                    {
                    int LA3_2 = input.LA(3);

                    if ( (LA3_2==RULE_ID) ) {
                        switch ( input.LA(4) ) {
                        case EOF:
                        case 27:
                        case 28:
                            {
                            alt3=1;
                            }
                            break;
                        case 29:
                            {
                            alt3=2;
                            }
                            break;
                        case 30:
                            {
                            int LA3_7 = input.LA(5);

                            if ( (LA3_7==RULE_ID) ) {
                                switch ( input.LA(6) ) {
                                case EOF:
                                case 27:
                                case 28:
                                    {
                                    alt3=1;
                                    }
                                    break;
                                case 29:
                                    {
                                    alt3=2;
                                    }
                                    break;
                                case RULE_ID:
                                    {
                                    alt3=3;
                                    }
                                    break;
                                default:
                                    NoViableAltException nvae =
                                        new NoViableAltException("", 3, 8, input);

                                    throw nvae;
                                }

                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("", 3, 7, input);

                                throw nvae;
                            }
                            }
                            break;
                        case RULE_ID:
                            {
                            alt3=3;
                            }
                            break;
                        default:
                            NoViableAltException nvae =
                                new NoViableAltException("", 3, 6, input);

                            throw nvae;
                        }

                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 3, 2, input);

                        throw nvae;
                    }
                    }
                    break;
                case RULE_ID:
                    {
                    alt3=3;
                    }
                    break;
                case 29:
                    {
                    alt3=2;
                    }
                    break;
                case EOF:
                case 27:
                case 28:
                    {
                    alt3=1;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("", 3, 1, input);

                    throw nvae;
                }

            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }
            switch (alt3) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1133:1: ( ruleTableFull )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1133:1: ( ruleTableFull )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1134:1: ruleTableFull
                    {
                     before(grammarAccess.getTableOrAliasAccess().getTableFullParserRuleCall_0()); 
                    pushFollow(FOLLOW_ruleTableFull_in_rule__TableOrAlias__Alternatives2361);
                    ruleTableFull();

                    state._fsp--;

                     after(grammarAccess.getTableOrAliasAccess().getTableFullParserRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1139:6: ( ( rule__TableOrAlias__Group_1__0 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1139:6: ( ( rule__TableOrAlias__Group_1__0 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1140:1: ( rule__TableOrAlias__Group_1__0 )
                    {
                     before(grammarAccess.getTableOrAliasAccess().getGroup_1()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1141:1: ( rule__TableOrAlias__Group_1__0 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1141:2: rule__TableOrAlias__Group_1__0
                    {
                    pushFollow(FOLLOW_rule__TableOrAlias__Group_1__0_in_rule__TableOrAlias__Alternatives2378);
                    rule__TableOrAlias__Group_1__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getTableOrAliasAccess().getGroup_1()); 

                    }


                    }
                    break;
                case 3 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1145:6: ( ( rule__TableOrAlias__Group_2__0 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1145:6: ( ( rule__TableOrAlias__Group_2__0 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1146:1: ( rule__TableOrAlias__Group_2__0 )
                    {
                     before(grammarAccess.getTableOrAliasAccess().getGroup_2()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1147:1: ( rule__TableOrAlias__Group_2__0 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1147:2: rule__TableOrAlias__Group_2__0
                    {
                    pushFollow(FOLLOW_rule__TableOrAlias__Group_2__0_in_rule__TableOrAlias__Alternatives2396);
                    rule__TableOrAlias__Group_2__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getTableOrAliasAccess().getGroup_2()); 

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
    // $ANTLR end "rule__TableOrAlias__Alternatives"


    // $ANTLR start "rule__TableFull__Alternatives"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1156:1: rule__TableFull__Alternatives : ( ( ( rule__TableFull__Group_0__0 ) ) | ( ( rule__TableFull__TblAssignment_1 ) ) );
    public final void rule__TableFull__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1160:1: ( ( ( rule__TableFull__Group_0__0 ) ) | ( ( rule__TableFull__TblAssignment_1 ) ) )
            int alt4=2;
            alt4 = dfa4.predict(input);
            switch (alt4) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1161:1: ( ( rule__TableFull__Group_0__0 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1161:1: ( ( rule__TableFull__Group_0__0 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1162:1: ( rule__TableFull__Group_0__0 )
                    {
                     before(grammarAccess.getTableFullAccess().getGroup_0()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1163:1: ( rule__TableFull__Group_0__0 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1163:2: rule__TableFull__Group_0__0
                    {
                    pushFollow(FOLLOW_rule__TableFull__Group_0__0_in_rule__TableFull__Alternatives2429);
                    rule__TableFull__Group_0__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getTableFullAccess().getGroup_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1167:6: ( ( rule__TableFull__TblAssignment_1 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1167:6: ( ( rule__TableFull__TblAssignment_1 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1168:1: ( rule__TableFull__TblAssignment_1 )
                    {
                     before(grammarAccess.getTableFullAccess().getTblAssignment_1()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1169:1: ( rule__TableFull__TblAssignment_1 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1169:2: rule__TableFull__TblAssignment_1
                    {
                    pushFollow(FOLLOW_rule__TableFull__TblAssignment_1_in_rule__TableFull__Alternatives2447);
                    rule__TableFull__TblAssignment_1();

                    state._fsp--;


                    }

                     after(grammarAccess.getTableFullAccess().getTblAssignment_1()); 

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
    // $ANTLR end "rule__TableFull__Alternatives"


    // $ANTLR start "rule__Schema__Alternatives"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1178:1: rule__Schema__Alternatives : ( ( ( rule__Schema__Group_0__0 ) ) | ( ( rule__Schema__SchemAssignment_1 ) ) );
    public final void rule__Schema__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1182:1: ( ( ( rule__Schema__Group_0__0 ) ) | ( ( rule__Schema__SchemAssignment_1 ) ) )
            int alt5=2;
            alt5 = dfa5.predict(input);
            switch (alt5) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1183:1: ( ( rule__Schema__Group_0__0 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1183:1: ( ( rule__Schema__Group_0__0 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1184:1: ( rule__Schema__Group_0__0 )
                    {
                     before(grammarAccess.getSchemaAccess().getGroup_0()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1185:1: ( rule__Schema__Group_0__0 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1185:2: rule__Schema__Group_0__0
                    {
                    pushFollow(FOLLOW_rule__Schema__Group_0__0_in_rule__Schema__Alternatives2480);
                    rule__Schema__Group_0__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getSchemaAccess().getGroup_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1189:6: ( ( rule__Schema__SchemAssignment_1 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1189:6: ( ( rule__Schema__SchemAssignment_1 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1190:1: ( rule__Schema__SchemAssignment_1 )
                    {
                     before(grammarAccess.getSchemaAccess().getSchemAssignment_1()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1191:1: ( rule__Schema__SchemAssignment_1 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1191:2: rule__Schema__SchemAssignment_1
                    {
                    pushFollow(FOLLOW_rule__Schema__SchemAssignment_1_in_rule__Schema__Alternatives2498);
                    rule__Schema__SchemAssignment_1();

                    state._fsp--;


                    }

                     after(grammarAccess.getSchemaAccess().getSchemAssignment_1()); 

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
    // $ANTLR end "rule__Schema__Alternatives"


    // $ANTLR start "rule__ConcreteWhereEntry__Alternatives"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1200:1: rule__ConcreteWhereEntry__Alternatives : ( ( ruleParWhereEntry ) | ( ruleExpressionWhereEntry ) );
    public final void rule__ConcreteWhereEntry__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1204:1: ( ( ruleParWhereEntry ) | ( ruleExpressionWhereEntry ) )
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==33) ) {
                alt6=1;
            }
            else if ( (LA6_0==RULE_ID) ) {
                alt6=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }
            switch (alt6) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1205:1: ( ruleParWhereEntry )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1205:1: ( ruleParWhereEntry )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1206:1: ruleParWhereEntry
                    {
                     before(grammarAccess.getConcreteWhereEntryAccess().getParWhereEntryParserRuleCall_0()); 
                    pushFollow(FOLLOW_ruleParWhereEntry_in_rule__ConcreteWhereEntry__Alternatives2531);
                    ruleParWhereEntry();

                    state._fsp--;

                     after(grammarAccess.getConcreteWhereEntryAccess().getParWhereEntryParserRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1211:6: ( ruleExpressionWhereEntry )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1211:6: ( ruleExpressionWhereEntry )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1212:1: ruleExpressionWhereEntry
                    {
                     before(grammarAccess.getConcreteWhereEntryAccess().getExpressionWhereEntryParserRuleCall_1()); 
                    pushFollow(FOLLOW_ruleExpressionWhereEntry_in_rule__ConcreteWhereEntry__Alternatives2548);
                    ruleExpressionWhereEntry();

                    state._fsp--;

                     after(grammarAccess.getConcreteWhereEntryAccess().getExpressionWhereEntryParserRuleCall_1()); 

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
    // $ANTLR end "rule__ConcreteWhereEntry__Alternatives"


    // $ANTLR start "rule__ExpressionWhereEntry__Alternatives"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1222:1: rule__ExpressionWhereEntry__Alternatives : ( ( ruleSingleExpressionWhereEntry ) | ( ruleMultiExpressionWhereEntry ) );
    public final void rule__ExpressionWhereEntry__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1226:1: ( ( ruleSingleExpressionWhereEntry ) | ( ruleMultiExpressionWhereEntry ) )
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==RULE_ID) ) {
                switch ( input.LA(2) ) {
                case 15:
                    {
                    int LA7_2 = input.LA(3);

                    if ( ((LA7_2>=RULE_SIGNED_DOUBLE && LA7_2<=RULE_DATE)||(LA7_2>=37 && LA7_2<=40)) ) {
                        alt7=1;
                    }
                    else if ( (LA7_2==35) ) {
                        alt7=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 7, 2, input);

                        throw nvae;
                    }
                    }
                    break;
                case 16:
                    {
                    int LA7_3 = input.LA(3);

                    if ( (LA7_3==35) ) {
                        alt7=2;
                    }
                    else if ( ((LA7_3>=RULE_SIGNED_DOUBLE && LA7_3<=RULE_DATE)||(LA7_3>=37 && LA7_3<=40)) ) {
                        alt7=1;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 7, 3, input);

                        throw nvae;
                    }
                    }
                    break;
                case 17:
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                    {
                    alt7=1;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("", 7, 1, input);

                    throw nvae;
                }

            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;
            }
            switch (alt7) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1227:1: ( ruleSingleExpressionWhereEntry )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1227:1: ( ruleSingleExpressionWhereEntry )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1228:1: ruleSingleExpressionWhereEntry
                    {
                     before(grammarAccess.getExpressionWhereEntryAccess().getSingleExpressionWhereEntryParserRuleCall_0()); 
                    pushFollow(FOLLOW_ruleSingleExpressionWhereEntry_in_rule__ExpressionWhereEntry__Alternatives2580);
                    ruleSingleExpressionWhereEntry();

                    state._fsp--;

                     after(grammarAccess.getExpressionWhereEntryAccess().getSingleExpressionWhereEntryParserRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1233:6: ( ruleMultiExpressionWhereEntry )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1233:6: ( ruleMultiExpressionWhereEntry )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1234:1: ruleMultiExpressionWhereEntry
                    {
                     before(grammarAccess.getExpressionWhereEntryAccess().getMultiExpressionWhereEntryParserRuleCall_1()); 
                    pushFollow(FOLLOW_ruleMultiExpressionWhereEntry_in_rule__ExpressionWhereEntry__Alternatives2597);
                    ruleMultiExpressionWhereEntry();

                    state._fsp--;

                     after(grammarAccess.getExpressionWhereEntryAccess().getMultiExpressionWhereEntryParserRuleCall_1()); 

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
    // $ANTLR end "rule__ExpressionWhereEntry__Alternatives"


    // $ANTLR start "rule__Expression__Alternatives"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1244:1: rule__Expression__Alternatives : ( ( ruleDoubleExpression ) | ( ruleLongExpression ) | ( ruleStringExpression ) | ( ruleNullExpression ) | ( ruleDateExpression ) | ( ruleBooleanExpression ) | ( ruleReplacableValue ) );
    public final void rule__Expression__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1248:1: ( ( ruleDoubleExpression ) | ( ruleLongExpression ) | ( ruleStringExpression ) | ( ruleNullExpression ) | ( ruleDateExpression ) | ( ruleBooleanExpression ) | ( ruleReplacableValue ) )
            int alt8=7;
            switch ( input.LA(1) ) {
            case RULE_SIGNED_DOUBLE:
                {
                alt8=1;
                }
                break;
            case RULE_SINGED_LONG:
                {
                alt8=2;
                }
                break;
            case RULE_STRING:
                {
                alt8=3;
                }
                break;
            case 38:
                {
                alt8=4;
                }
                break;
            case RULE_DATE:
                {
                alt8=5;
                }
                break;
            case 39:
            case 40:
                {
                alt8=6;
                }
                break;
            case 37:
                {
                alt8=7;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;
            }

            switch (alt8) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1249:1: ( ruleDoubleExpression )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1249:1: ( ruleDoubleExpression )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1250:1: ruleDoubleExpression
                    {
                     before(grammarAccess.getExpressionAccess().getDoubleExpressionParserRuleCall_0()); 
                    pushFollow(FOLLOW_ruleDoubleExpression_in_rule__Expression__Alternatives2629);
                    ruleDoubleExpression();

                    state._fsp--;

                     after(grammarAccess.getExpressionAccess().getDoubleExpressionParserRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1255:6: ( ruleLongExpression )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1255:6: ( ruleLongExpression )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1256:1: ruleLongExpression
                    {
                     before(grammarAccess.getExpressionAccess().getLongExpressionParserRuleCall_1()); 
                    pushFollow(FOLLOW_ruleLongExpression_in_rule__Expression__Alternatives2646);
                    ruleLongExpression();

                    state._fsp--;

                     after(grammarAccess.getExpressionAccess().getLongExpressionParserRuleCall_1()); 

                    }


                    }
                    break;
                case 3 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1261:6: ( ruleStringExpression )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1261:6: ( ruleStringExpression )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1262:1: ruleStringExpression
                    {
                     before(grammarAccess.getExpressionAccess().getStringExpressionParserRuleCall_2()); 
                    pushFollow(FOLLOW_ruleStringExpression_in_rule__Expression__Alternatives2663);
                    ruleStringExpression();

                    state._fsp--;

                     after(grammarAccess.getExpressionAccess().getStringExpressionParserRuleCall_2()); 

                    }


                    }
                    break;
                case 4 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1267:6: ( ruleNullExpression )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1267:6: ( ruleNullExpression )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1268:1: ruleNullExpression
                    {
                     before(grammarAccess.getExpressionAccess().getNullExpressionParserRuleCall_3()); 
                    pushFollow(FOLLOW_ruleNullExpression_in_rule__Expression__Alternatives2680);
                    ruleNullExpression();

                    state._fsp--;

                     after(grammarAccess.getExpressionAccess().getNullExpressionParserRuleCall_3()); 

                    }


                    }
                    break;
                case 5 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1273:6: ( ruleDateExpression )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1273:6: ( ruleDateExpression )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1274:1: ruleDateExpression
                    {
                     before(grammarAccess.getExpressionAccess().getDateExpressionParserRuleCall_4()); 
                    pushFollow(FOLLOW_ruleDateExpression_in_rule__Expression__Alternatives2697);
                    ruleDateExpression();

                    state._fsp--;

                     after(grammarAccess.getExpressionAccess().getDateExpressionParserRuleCall_4()); 

                    }


                    }
                    break;
                case 6 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1279:6: ( ruleBooleanExpression )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1279:6: ( ruleBooleanExpression )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1280:1: ruleBooleanExpression
                    {
                     before(grammarAccess.getExpressionAccess().getBooleanExpressionParserRuleCall_5()); 
                    pushFollow(FOLLOW_ruleBooleanExpression_in_rule__Expression__Alternatives2714);
                    ruleBooleanExpression();

                    state._fsp--;

                     after(grammarAccess.getExpressionAccess().getBooleanExpressionParserRuleCall_5()); 

                    }


                    }
                    break;
                case 7 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1285:6: ( ruleReplacableValue )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1285:6: ( ruleReplacableValue )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1286:1: ruleReplacableValue
                    {
                     before(grammarAccess.getExpressionAccess().getReplacableValueParserRuleCall_6()); 
                    pushFollow(FOLLOW_ruleReplacableValue_in_rule__Expression__Alternatives2731);
                    ruleReplacableValue();

                    state._fsp--;

                     after(grammarAccess.getExpressionAccess().getReplacableValueParserRuleCall_6()); 

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
    // $ANTLR end "rule__Expression__Alternatives"


    // $ANTLR start "rule__BooleanExpression__Alternatives"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1296:1: rule__BooleanExpression__Alternatives : ( ( ( rule__BooleanExpression__TrueAssignment_0 ) ) | ( ( rule__BooleanExpression__TrueAssignment_1 ) ) );
    public final void rule__BooleanExpression__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1300:1: ( ( ( rule__BooleanExpression__TrueAssignment_0 ) ) | ( ( rule__BooleanExpression__TrueAssignment_1 ) ) )
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==39) ) {
                alt9=1;
            }
            else if ( (LA9_0==40) ) {
                alt9=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                throw nvae;
            }
            switch (alt9) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1301:1: ( ( rule__BooleanExpression__TrueAssignment_0 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1301:1: ( ( rule__BooleanExpression__TrueAssignment_0 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1302:1: ( rule__BooleanExpression__TrueAssignment_0 )
                    {
                     before(grammarAccess.getBooleanExpressionAccess().getTrueAssignment_0()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1303:1: ( rule__BooleanExpression__TrueAssignment_0 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1303:2: rule__BooleanExpression__TrueAssignment_0
                    {
                    pushFollow(FOLLOW_rule__BooleanExpression__TrueAssignment_0_in_rule__BooleanExpression__Alternatives2763);
                    rule__BooleanExpression__TrueAssignment_0();

                    state._fsp--;


                    }

                     after(grammarAccess.getBooleanExpressionAccess().getTrueAssignment_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1307:6: ( ( rule__BooleanExpression__TrueAssignment_1 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1307:6: ( ( rule__BooleanExpression__TrueAssignment_1 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1308:1: ( rule__BooleanExpression__TrueAssignment_1 )
                    {
                     before(grammarAccess.getBooleanExpressionAccess().getTrueAssignment_1()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1309:1: ( rule__BooleanExpression__TrueAssignment_1 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1309:2: rule__BooleanExpression__TrueAssignment_1
                    {
                    pushFollow(FOLLOW_rule__BooleanExpression__TrueAssignment_1_in_rule__BooleanExpression__Alternatives2781);
                    rule__BooleanExpression__TrueAssignment_1();

                    state._fsp--;


                    }

                     after(grammarAccess.getBooleanExpressionAccess().getTrueAssignment_1()); 

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
    // $ANTLR end "rule__BooleanExpression__Alternatives"


    // $ANTLR start "rule__ArrayExpression__Alternatives"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1318:1: rule__ArrayExpression__Alternatives : ( ( ruleDoubleArrayExpression ) | ( ruleLongArrayExpression ) | ( ruleStringArrayExpression ) | ( ruleNullArrayExpression ) | ( ruleDateArrayExpression ) | ( ruleBooleanArrayExpression ) );
    public final void rule__ArrayExpression__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1322:1: ( ( ruleDoubleArrayExpression ) | ( ruleLongArrayExpression ) | ( ruleStringArrayExpression ) | ( ruleNullArrayExpression ) | ( ruleDateArrayExpression ) | ( ruleBooleanArrayExpression ) )
            int alt10=6;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==35) ) {
                switch ( input.LA(2) ) {
                case RULE_DATE:
                    {
                    alt10=5;
                    }
                    break;
                case RULE_BOOL:
                    {
                    alt10=6;
                    }
                    break;
                case 38:
                    {
                    alt10=4;
                    }
                    break;
                case RULE_STRING:
                    {
                    alt10=3;
                    }
                    break;
                case RULE_SINGED_LONG:
                    {
                    alt10=2;
                    }
                    break;
                case RULE_SIGNED_DOUBLE:
                    {
                    alt10=1;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("", 10, 1, input);

                    throw nvae;
                }

            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 10, 0, input);

                throw nvae;
            }
            switch (alt10) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1323:1: ( ruleDoubleArrayExpression )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1323:1: ( ruleDoubleArrayExpression )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1324:1: ruleDoubleArrayExpression
                    {
                     before(grammarAccess.getArrayExpressionAccess().getDoubleArrayExpressionParserRuleCall_0()); 
                    pushFollow(FOLLOW_ruleDoubleArrayExpression_in_rule__ArrayExpression__Alternatives2814);
                    ruleDoubleArrayExpression();

                    state._fsp--;

                     after(grammarAccess.getArrayExpressionAccess().getDoubleArrayExpressionParserRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1329:6: ( ruleLongArrayExpression )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1329:6: ( ruleLongArrayExpression )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1330:1: ruleLongArrayExpression
                    {
                     before(grammarAccess.getArrayExpressionAccess().getLongArrayExpressionParserRuleCall_1()); 
                    pushFollow(FOLLOW_ruleLongArrayExpression_in_rule__ArrayExpression__Alternatives2831);
                    ruleLongArrayExpression();

                    state._fsp--;

                     after(grammarAccess.getArrayExpressionAccess().getLongArrayExpressionParserRuleCall_1()); 

                    }


                    }
                    break;
                case 3 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1335:6: ( ruleStringArrayExpression )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1335:6: ( ruleStringArrayExpression )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1336:1: ruleStringArrayExpression
                    {
                     before(grammarAccess.getArrayExpressionAccess().getStringArrayExpressionParserRuleCall_2()); 
                    pushFollow(FOLLOW_ruleStringArrayExpression_in_rule__ArrayExpression__Alternatives2848);
                    ruleStringArrayExpression();

                    state._fsp--;

                     after(grammarAccess.getArrayExpressionAccess().getStringArrayExpressionParserRuleCall_2()); 

                    }


                    }
                    break;
                case 4 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1341:6: ( ruleNullArrayExpression )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1341:6: ( ruleNullArrayExpression )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1342:1: ruleNullArrayExpression
                    {
                     before(grammarAccess.getArrayExpressionAccess().getNullArrayExpressionParserRuleCall_3()); 
                    pushFollow(FOLLOW_ruleNullArrayExpression_in_rule__ArrayExpression__Alternatives2865);
                    ruleNullArrayExpression();

                    state._fsp--;

                     after(grammarAccess.getArrayExpressionAccess().getNullArrayExpressionParserRuleCall_3()); 

                    }


                    }
                    break;
                case 5 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1347:6: ( ruleDateArrayExpression )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1347:6: ( ruleDateArrayExpression )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1348:1: ruleDateArrayExpression
                    {
                     before(grammarAccess.getArrayExpressionAccess().getDateArrayExpressionParserRuleCall_4()); 
                    pushFollow(FOLLOW_ruleDateArrayExpression_in_rule__ArrayExpression__Alternatives2882);
                    ruleDateArrayExpression();

                    state._fsp--;

                     after(grammarAccess.getArrayExpressionAccess().getDateArrayExpressionParserRuleCall_4()); 

                    }


                    }
                    break;
                case 6 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1353:6: ( ruleBooleanArrayExpression )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1353:6: ( ruleBooleanArrayExpression )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1354:1: ruleBooleanArrayExpression
                    {
                     before(grammarAccess.getArrayExpressionAccess().getBooleanArrayExpressionParserRuleCall_5()); 
                    pushFollow(FOLLOW_ruleBooleanArrayExpression_in_rule__ArrayExpression__Alternatives2899);
                    ruleBooleanArrayExpression();

                    state._fsp--;

                     after(grammarAccess.getArrayExpressionAccess().getBooleanArrayExpressionParserRuleCall_5()); 

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
    // $ANTLR end "rule__ArrayExpression__Alternatives"


    // $ANTLR start "rule__ArrayOperator__Alternatives"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1364:1: rule__ArrayOperator__Alternatives : ( ( ( 'in' ) ) | ( ( 'not in' ) ) );
    public final void rule__ArrayOperator__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1368:1: ( ( ( 'in' ) ) | ( ( 'not in' ) ) )
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==15) ) {
                alt11=1;
            }
            else if ( (LA11_0==16) ) {
                alt11=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 11, 0, input);

                throw nvae;
            }
            switch (alt11) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1369:1: ( ( 'in' ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1369:1: ( ( 'in' ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1370:1: ( 'in' )
                    {
                     before(grammarAccess.getArrayOperatorAccess().getSql_inEnumLiteralDeclaration_0()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1371:1: ( 'in' )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1371:3: 'in'
                    {
                    match(input,15,FOLLOW_15_in_rule__ArrayOperator__Alternatives2932); 

                    }

                     after(grammarAccess.getArrayOperatorAccess().getSql_inEnumLiteralDeclaration_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1376:6: ( ( 'not in' ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1376:6: ( ( 'not in' ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1377:1: ( 'not in' )
                    {
                     before(grammarAccess.getArrayOperatorAccess().getSql_notInEnumLiteralDeclaration_1()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1378:1: ( 'not in' )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1378:3: 'not in'
                    {
                    match(input,16,FOLLOW_16_in_rule__ArrayOperator__Alternatives2953); 

                    }

                     after(grammarAccess.getArrayOperatorAccess().getSql_notInEnumLiteralDeclaration_1()); 

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
    // $ANTLR end "rule__ArrayOperator__Alternatives"


    // $ANTLR start "rule__Operator__Alternatives"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1388:1: rule__Operator__Alternatives : ( ( ( '<' ) ) | ( ( '>' ) ) | ( ( '<=' ) ) | ( ( '>=' ) ) | ( ( '=' ) ) | ( ( '!=' ) ) | ( ( 'like' ) ) | ( ( 'not like' ) ) | ( ( 'not in' ) ) | ( ( 'in' ) ) );
    public final void rule__Operator__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1392:1: ( ( ( '<' ) ) | ( ( '>' ) ) | ( ( '<=' ) ) | ( ( '>=' ) ) | ( ( '=' ) ) | ( ( '!=' ) ) | ( ( 'like' ) ) | ( ( 'not like' ) ) | ( ( 'not in' ) ) | ( ( 'in' ) ) )
            int alt12=10;
            switch ( input.LA(1) ) {
            case 17:
                {
                alt12=1;
                }
                break;
            case 18:
                {
                alt12=2;
                }
                break;
            case 19:
                {
                alt12=3;
                }
                break;
            case 20:
                {
                alt12=4;
                }
                break;
            case 21:
                {
                alt12=5;
                }
                break;
            case 22:
                {
                alt12=6;
                }
                break;
            case 23:
                {
                alt12=7;
                }
                break;
            case 24:
                {
                alt12=8;
                }
                break;
            case 16:
                {
                alt12=9;
                }
                break;
            case 15:
                {
                alt12=10;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 12, 0, input);

                throw nvae;
            }

            switch (alt12) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1393:1: ( ( '<' ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1393:1: ( ( '<' ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1394:1: ( '<' )
                    {
                     before(grammarAccess.getOperatorAccess().getLessThenEnumLiteralDeclaration_0()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1395:1: ( '<' )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1395:3: '<'
                    {
                    match(input,17,FOLLOW_17_in_rule__Operator__Alternatives2989); 

                    }

                     after(grammarAccess.getOperatorAccess().getLessThenEnumLiteralDeclaration_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1400:6: ( ( '>' ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1400:6: ( ( '>' ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1401:1: ( '>' )
                    {
                     before(grammarAccess.getOperatorAccess().getGreaterThenEnumLiteralDeclaration_1()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1402:1: ( '>' )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1402:3: '>'
                    {
                    match(input,18,FOLLOW_18_in_rule__Operator__Alternatives3010); 

                    }

                     after(grammarAccess.getOperatorAccess().getGreaterThenEnumLiteralDeclaration_1()); 

                    }


                    }
                    break;
                case 3 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1407:6: ( ( '<=' ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1407:6: ( ( '<=' ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1408:1: ( '<=' )
                    {
                     before(grammarAccess.getOperatorAccess().getLessEqualEnumLiteralDeclaration_2()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1409:1: ( '<=' )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1409:3: '<='
                    {
                    match(input,19,FOLLOW_19_in_rule__Operator__Alternatives3031); 

                    }

                     after(grammarAccess.getOperatorAccess().getLessEqualEnumLiteralDeclaration_2()); 

                    }


                    }
                    break;
                case 4 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1414:6: ( ( '>=' ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1414:6: ( ( '>=' ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1415:1: ( '>=' )
                    {
                     before(grammarAccess.getOperatorAccess().getGreaterEqualEnumLiteralDeclaration_3()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1416:1: ( '>=' )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1416:3: '>='
                    {
                    match(input,20,FOLLOW_20_in_rule__Operator__Alternatives3052); 

                    }

                     after(grammarAccess.getOperatorAccess().getGreaterEqualEnumLiteralDeclaration_3()); 

                    }


                    }
                    break;
                case 5 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1421:6: ( ( '=' ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1421:6: ( ( '=' ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1422:1: ( '=' )
                    {
                     before(grammarAccess.getOperatorAccess().getEqualEnumLiteralDeclaration_4()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1423:1: ( '=' )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1423:3: '='
                    {
                    match(input,21,FOLLOW_21_in_rule__Operator__Alternatives3073); 

                    }

                     after(grammarAccess.getOperatorAccess().getEqualEnumLiteralDeclaration_4()); 

                    }


                    }
                    break;
                case 6 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1428:6: ( ( '!=' ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1428:6: ( ( '!=' ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1429:1: ( '!=' )
                    {
                     before(grammarAccess.getOperatorAccess().getNotEqualEnumLiteralDeclaration_5()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1430:1: ( '!=' )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1430:3: '!='
                    {
                    match(input,22,FOLLOW_22_in_rule__Operator__Alternatives3094); 

                    }

                     after(grammarAccess.getOperatorAccess().getNotEqualEnumLiteralDeclaration_5()); 

                    }


                    }
                    break;
                case 7 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1435:6: ( ( 'like' ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1435:6: ( ( 'like' ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1436:1: ( 'like' )
                    {
                     before(grammarAccess.getOperatorAccess().getLikeEnumLiteralDeclaration_6()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1437:1: ( 'like' )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1437:3: 'like'
                    {
                    match(input,23,FOLLOW_23_in_rule__Operator__Alternatives3115); 

                    }

                     after(grammarAccess.getOperatorAccess().getLikeEnumLiteralDeclaration_6()); 

                    }


                    }
                    break;
                case 8 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1442:6: ( ( 'not like' ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1442:6: ( ( 'not like' ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1443:1: ( 'not like' )
                    {
                     before(grammarAccess.getOperatorAccess().getNotLikeEnumLiteralDeclaration_7()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1444:1: ( 'not like' )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1444:3: 'not like'
                    {
                    match(input,24,FOLLOW_24_in_rule__Operator__Alternatives3136); 

                    }

                     after(grammarAccess.getOperatorAccess().getNotLikeEnumLiteralDeclaration_7()); 

                    }


                    }
                    break;
                case 9 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1449:6: ( ( 'not in' ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1449:6: ( ( 'not in' ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1450:1: ( 'not in' )
                    {
                     before(grammarAccess.getOperatorAccess().getNotInEnumLiteralDeclaration_8()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1451:1: ( 'not in' )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1451:3: 'not in'
                    {
                    match(input,16,FOLLOW_16_in_rule__Operator__Alternatives3157); 

                    }

                     after(grammarAccess.getOperatorAccess().getNotInEnumLiteralDeclaration_8()); 

                    }


                    }
                    break;
                case 10 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1456:6: ( ( 'in' ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1456:6: ( ( 'in' ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1457:1: ( 'in' )
                    {
                     before(grammarAccess.getOperatorAccess().getInEnumLiteralDeclaration_9()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1458:1: ( 'in' )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1458:3: 'in'
                    {
                    match(input,15,FOLLOW_15_in_rule__Operator__Alternatives3178); 

                    }

                     after(grammarAccess.getOperatorAccess().getInEnumLiteralDeclaration_9()); 

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
    // $ANTLR end "rule__Operator__Alternatives"


    // $ANTLR start "rule__Model__Group__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1470:1: rule__Model__Group__0 : rule__Model__Group__0__Impl rule__Model__Group__1 ;
    public final void rule__Model__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1474:1: ( rule__Model__Group__0__Impl rule__Model__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1475:2: rule__Model__Group__0__Impl rule__Model__Group__1
            {
            pushFollow(FOLLOW_rule__Model__Group__0__Impl_in_rule__Model__Group__03211);
            rule__Model__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Model__Group__1_in_rule__Model__Group__03214);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1482:1: rule__Model__Group__0__Impl : ( 'SELECT' ) ;
    public final void rule__Model__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1486:1: ( ( 'SELECT' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1487:1: ( 'SELECT' )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1487:1: ( 'SELECT' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1488:1: 'SELECT'
            {
             before(grammarAccess.getModelAccess().getSELECTKeyword_0()); 
            match(input,25,FOLLOW_25_in_rule__Model__Group__0__Impl3242); 
             after(grammarAccess.getModelAccess().getSELECTKeyword_0()); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1501:1: rule__Model__Group__1 : rule__Model__Group__1__Impl rule__Model__Group__2 ;
    public final void rule__Model__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1505:1: ( rule__Model__Group__1__Impl rule__Model__Group__2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1506:2: rule__Model__Group__1__Impl rule__Model__Group__2
            {
            pushFollow(FOLLOW_rule__Model__Group__1__Impl_in_rule__Model__Group__13273);
            rule__Model__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Model__Group__2_in_rule__Model__Group__13276);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1513:1: rule__Model__Group__1__Impl : ( ( rule__Model__ColAssignment_1 )? ) ;
    public final void rule__Model__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1517:1: ( ( ( rule__Model__ColAssignment_1 )? ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1518:1: ( ( rule__Model__ColAssignment_1 )? )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1518:1: ( ( rule__Model__ColAssignment_1 )? )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1519:1: ( rule__Model__ColAssignment_1 )?
            {
             before(grammarAccess.getModelAccess().getColAssignment_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1520:1: ( rule__Model__ColAssignment_1 )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==RULE_ID) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1520:2: rule__Model__ColAssignment_1
                    {
                    pushFollow(FOLLOW_rule__Model__ColAssignment_1_in_rule__Model__Group__1__Impl3303);
                    rule__Model__ColAssignment_1();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getModelAccess().getColAssignment_1()); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1530:1: rule__Model__Group__2 : rule__Model__Group__2__Impl rule__Model__Group__3 ;
    public final void rule__Model__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1534:1: ( rule__Model__Group__2__Impl rule__Model__Group__3 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1535:2: rule__Model__Group__2__Impl rule__Model__Group__3
            {
            pushFollow(FOLLOW_rule__Model__Group__2__Impl_in_rule__Model__Group__23334);
            rule__Model__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Model__Group__3_in_rule__Model__Group__23337);
            rule__Model__Group__3();

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1542:1: rule__Model__Group__2__Impl : ( 'FROM' ) ;
    public final void rule__Model__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1546:1: ( ( 'FROM' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1547:1: ( 'FROM' )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1547:1: ( 'FROM' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1548:1: 'FROM'
            {
             before(grammarAccess.getModelAccess().getFROMKeyword_2()); 
            match(input,26,FOLLOW_26_in_rule__Model__Group__2__Impl3365); 
             after(grammarAccess.getModelAccess().getFROMKeyword_2()); 

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


    // $ANTLR start "rule__Model__Group__3"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1561:1: rule__Model__Group__3 : rule__Model__Group__3__Impl rule__Model__Group__4 ;
    public final void rule__Model__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1565:1: ( rule__Model__Group__3__Impl rule__Model__Group__4 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1566:2: rule__Model__Group__3__Impl rule__Model__Group__4
            {
            pushFollow(FOLLOW_rule__Model__Group__3__Impl_in_rule__Model__Group__33396);
            rule__Model__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Model__Group__4_in_rule__Model__Group__33399);
            rule__Model__Group__4();

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
    // $ANTLR end "rule__Model__Group__3"


    // $ANTLR start "rule__Model__Group__3__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1573:1: rule__Model__Group__3__Impl : ( ( rule__Model__TblAssignment_3 ) ) ;
    public final void rule__Model__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1577:1: ( ( ( rule__Model__TblAssignment_3 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1578:1: ( ( rule__Model__TblAssignment_3 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1578:1: ( ( rule__Model__TblAssignment_3 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1579:1: ( rule__Model__TblAssignment_3 )
            {
             before(grammarAccess.getModelAccess().getTblAssignment_3()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1580:1: ( rule__Model__TblAssignment_3 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1580:2: rule__Model__TblAssignment_3
            {
            pushFollow(FOLLOW_rule__Model__TblAssignment_3_in_rule__Model__Group__3__Impl3426);
            rule__Model__TblAssignment_3();

            state._fsp--;


            }

             after(grammarAccess.getModelAccess().getTblAssignment_3()); 

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
    // $ANTLR end "rule__Model__Group__3__Impl"


    // $ANTLR start "rule__Model__Group__4"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1590:1: rule__Model__Group__4 : rule__Model__Group__4__Impl ;
    public final void rule__Model__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1594:1: ( rule__Model__Group__4__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1595:2: rule__Model__Group__4__Impl
            {
            pushFollow(FOLLOW_rule__Model__Group__4__Impl_in_rule__Model__Group__43456);
            rule__Model__Group__4__Impl();

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
    // $ANTLR end "rule__Model__Group__4"


    // $ANTLR start "rule__Model__Group__4__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1601:1: rule__Model__Group__4__Impl : ( ( rule__Model__Group_4__0 )? ) ;
    public final void rule__Model__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1605:1: ( ( ( rule__Model__Group_4__0 )? ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1606:1: ( ( rule__Model__Group_4__0 )? )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1606:1: ( ( rule__Model__Group_4__0 )? )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1607:1: ( rule__Model__Group_4__0 )?
            {
             before(grammarAccess.getModelAccess().getGroup_4()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1608:1: ( rule__Model__Group_4__0 )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==27) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1608:2: rule__Model__Group_4__0
                    {
                    pushFollow(FOLLOW_rule__Model__Group_4__0_in_rule__Model__Group__4__Impl3483);
                    rule__Model__Group_4__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getModelAccess().getGroup_4()); 

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
    // $ANTLR end "rule__Model__Group__4__Impl"


    // $ANTLR start "rule__Model__Group_4__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1628:1: rule__Model__Group_4__0 : rule__Model__Group_4__0__Impl rule__Model__Group_4__1 ;
    public final void rule__Model__Group_4__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1632:1: ( rule__Model__Group_4__0__Impl rule__Model__Group_4__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1633:2: rule__Model__Group_4__0__Impl rule__Model__Group_4__1
            {
            pushFollow(FOLLOW_rule__Model__Group_4__0__Impl_in_rule__Model__Group_4__03524);
            rule__Model__Group_4__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Model__Group_4__1_in_rule__Model__Group_4__03527);
            rule__Model__Group_4__1();

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
    // $ANTLR end "rule__Model__Group_4__0"


    // $ANTLR start "rule__Model__Group_4__0__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1640:1: rule__Model__Group_4__0__Impl : ( 'WHERE' ) ;
    public final void rule__Model__Group_4__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1644:1: ( ( 'WHERE' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1645:1: ( 'WHERE' )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1645:1: ( 'WHERE' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1646:1: 'WHERE'
            {
             before(grammarAccess.getModelAccess().getWHEREKeyword_4_0()); 
            match(input,27,FOLLOW_27_in_rule__Model__Group_4__0__Impl3555); 
             after(grammarAccess.getModelAccess().getWHEREKeyword_4_0()); 

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
    // $ANTLR end "rule__Model__Group_4__0__Impl"


    // $ANTLR start "rule__Model__Group_4__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1659:1: rule__Model__Group_4__1 : rule__Model__Group_4__1__Impl ;
    public final void rule__Model__Group_4__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1663:1: ( rule__Model__Group_4__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1664:2: rule__Model__Group_4__1__Impl
            {
            pushFollow(FOLLOW_rule__Model__Group_4__1__Impl_in_rule__Model__Group_4__13586);
            rule__Model__Group_4__1__Impl();

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
    // $ANTLR end "rule__Model__Group_4__1"


    // $ANTLR start "rule__Model__Group_4__1__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1670:1: rule__Model__Group_4__1__Impl : ( ( rule__Model__WhereEntryAssignment_4_1 ) ) ;
    public final void rule__Model__Group_4__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1674:1: ( ( ( rule__Model__WhereEntryAssignment_4_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1675:1: ( ( rule__Model__WhereEntryAssignment_4_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1675:1: ( ( rule__Model__WhereEntryAssignment_4_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1676:1: ( rule__Model__WhereEntryAssignment_4_1 )
            {
             before(grammarAccess.getModelAccess().getWhereEntryAssignment_4_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1677:1: ( rule__Model__WhereEntryAssignment_4_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1677:2: rule__Model__WhereEntryAssignment_4_1
            {
            pushFollow(FOLLOW_rule__Model__WhereEntryAssignment_4_1_in_rule__Model__Group_4__1__Impl3613);
            rule__Model__WhereEntryAssignment_4_1();

            state._fsp--;


            }

             after(grammarAccess.getModelAccess().getWhereEntryAssignment_4_1()); 

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
    // $ANTLR end "rule__Model__Group_4__1__Impl"


    // $ANTLR start "rule__Columns__Group__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1691:1: rule__Columns__Group__0 : rule__Columns__Group__0__Impl rule__Columns__Group__1 ;
    public final void rule__Columns__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1695:1: ( rule__Columns__Group__0__Impl rule__Columns__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1696:2: rule__Columns__Group__0__Impl rule__Columns__Group__1
            {
            pushFollow(FOLLOW_rule__Columns__Group__0__Impl_in_rule__Columns__Group__03647);
            rule__Columns__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Columns__Group__1_in_rule__Columns__Group__03650);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1703:1: rule__Columns__Group__0__Impl : ( ruleColumnOrAlias ) ;
    public final void rule__Columns__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1707:1: ( ( ruleColumnOrAlias ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1708:1: ( ruleColumnOrAlias )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1708:1: ( ruleColumnOrAlias )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1709:1: ruleColumnOrAlias
            {
             before(grammarAccess.getColumnsAccess().getColumnOrAliasParserRuleCall_0()); 
            pushFollow(FOLLOW_ruleColumnOrAlias_in_rule__Columns__Group__0__Impl3677);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1720:1: rule__Columns__Group__1 : rule__Columns__Group__1__Impl ;
    public final void rule__Columns__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1724:1: ( rule__Columns__Group__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1725:2: rule__Columns__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__Columns__Group__1__Impl_in_rule__Columns__Group__13706);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1731:1: rule__Columns__Group__1__Impl : ( ( rule__Columns__Group_1__0 )? ) ;
    public final void rule__Columns__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1735:1: ( ( ( rule__Columns__Group_1__0 )? ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1736:1: ( ( rule__Columns__Group_1__0 )? )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1736:1: ( ( rule__Columns__Group_1__0 )? )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1737:1: ( rule__Columns__Group_1__0 )?
            {
             before(grammarAccess.getColumnsAccess().getGroup_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1738:1: ( rule__Columns__Group_1__0 )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==28) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1738:2: rule__Columns__Group_1__0
                    {
                    pushFollow(FOLLOW_rule__Columns__Group_1__0_in_rule__Columns__Group__1__Impl3733);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1752:1: rule__Columns__Group_1__0 : rule__Columns__Group_1__0__Impl rule__Columns__Group_1__1 ;
    public final void rule__Columns__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1756:1: ( rule__Columns__Group_1__0__Impl rule__Columns__Group_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1757:2: rule__Columns__Group_1__0__Impl rule__Columns__Group_1__1
            {
            pushFollow(FOLLOW_rule__Columns__Group_1__0__Impl_in_rule__Columns__Group_1__03768);
            rule__Columns__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Columns__Group_1__1_in_rule__Columns__Group_1__03771);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1764:1: rule__Columns__Group_1__0__Impl : ( () ) ;
    public final void rule__Columns__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1768:1: ( ( () ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1769:1: ( () )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1769:1: ( () )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1770:1: ()
            {
             before(grammarAccess.getColumnsAccess().getOrColumnEntriesAction_1_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1771:1: ()
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1773:1: 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1783:1: rule__Columns__Group_1__1 : rule__Columns__Group_1__1__Impl ;
    public final void rule__Columns__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1787:1: ( rule__Columns__Group_1__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1788:2: rule__Columns__Group_1__1__Impl
            {
            pushFollow(FOLLOW_rule__Columns__Group_1__1__Impl_in_rule__Columns__Group_1__13829);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1794:1: rule__Columns__Group_1__1__Impl : ( ( ( rule__Columns__Group_1_1__0 ) ) ( ( rule__Columns__Group_1_1__0 )* ) ) ;
    public final void rule__Columns__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1798:1: ( ( ( ( rule__Columns__Group_1_1__0 ) ) ( ( rule__Columns__Group_1_1__0 )* ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1799:1: ( ( ( rule__Columns__Group_1_1__0 ) ) ( ( rule__Columns__Group_1_1__0 )* ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1799:1: ( ( ( rule__Columns__Group_1_1__0 ) ) ( ( rule__Columns__Group_1_1__0 )* ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1800:1: ( ( rule__Columns__Group_1_1__0 ) ) ( ( rule__Columns__Group_1_1__0 )* )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1800:1: ( ( rule__Columns__Group_1_1__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1801:1: ( rule__Columns__Group_1_1__0 )
            {
             before(grammarAccess.getColumnsAccess().getGroup_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1802:1: ( rule__Columns__Group_1_1__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1802:2: rule__Columns__Group_1_1__0
            {
            pushFollow(FOLLOW_rule__Columns__Group_1_1__0_in_rule__Columns__Group_1__1__Impl3858);
            rule__Columns__Group_1_1__0();

            state._fsp--;


            }

             after(grammarAccess.getColumnsAccess().getGroup_1_1()); 

            }

            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1805:1: ( ( rule__Columns__Group_1_1__0 )* )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1806:1: ( rule__Columns__Group_1_1__0 )*
            {
             before(grammarAccess.getColumnsAccess().getGroup_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1807:1: ( rule__Columns__Group_1_1__0 )*
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( (LA16_0==28) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1807:2: rule__Columns__Group_1_1__0
            	    {
            	    pushFollow(FOLLOW_rule__Columns__Group_1_1__0_in_rule__Columns__Group_1__1__Impl3870);
            	    rule__Columns__Group_1_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop16;
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1822:1: rule__Columns__Group_1_1__0 : rule__Columns__Group_1_1__0__Impl rule__Columns__Group_1_1__1 ;
    public final void rule__Columns__Group_1_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1826:1: ( rule__Columns__Group_1_1__0__Impl rule__Columns__Group_1_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1827:2: rule__Columns__Group_1_1__0__Impl rule__Columns__Group_1_1__1
            {
            pushFollow(FOLLOW_rule__Columns__Group_1_1__0__Impl_in_rule__Columns__Group_1_1__03907);
            rule__Columns__Group_1_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Columns__Group_1_1__1_in_rule__Columns__Group_1_1__03910);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1834:1: rule__Columns__Group_1_1__0__Impl : ( ',' ) ;
    public final void rule__Columns__Group_1_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1838:1: ( ( ',' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1839:1: ( ',' )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1839:1: ( ',' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1840:1: ','
            {
             before(grammarAccess.getColumnsAccess().getCommaKeyword_1_1_0()); 
            match(input,28,FOLLOW_28_in_rule__Columns__Group_1_1__0__Impl3938); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1853:1: rule__Columns__Group_1_1__1 : rule__Columns__Group_1_1__1__Impl ;
    public final void rule__Columns__Group_1_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1857:1: ( rule__Columns__Group_1_1__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1858:2: rule__Columns__Group_1_1__1__Impl
            {
            pushFollow(FOLLOW_rule__Columns__Group_1_1__1__Impl_in_rule__Columns__Group_1_1__13969);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1864:1: rule__Columns__Group_1_1__1__Impl : ( ( rule__Columns__EntriesAssignment_1_1_1 ) ) ;
    public final void rule__Columns__Group_1_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1868:1: ( ( ( rule__Columns__EntriesAssignment_1_1_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1869:1: ( ( rule__Columns__EntriesAssignment_1_1_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1869:1: ( ( rule__Columns__EntriesAssignment_1_1_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1870:1: ( rule__Columns__EntriesAssignment_1_1_1 )
            {
             before(grammarAccess.getColumnsAccess().getEntriesAssignment_1_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1871:1: ( rule__Columns__EntriesAssignment_1_1_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1871:2: rule__Columns__EntriesAssignment_1_1_1
            {
            pushFollow(FOLLOW_rule__Columns__EntriesAssignment_1_1_1_in_rule__Columns__Group_1_1__1__Impl3996);
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


    // $ANTLR start "rule__ColumnOrAlias__Group_1__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1885:1: rule__ColumnOrAlias__Group_1__0 : rule__ColumnOrAlias__Group_1__0__Impl rule__ColumnOrAlias__Group_1__1 ;
    public final void rule__ColumnOrAlias__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1889:1: ( rule__ColumnOrAlias__Group_1__0__Impl rule__ColumnOrAlias__Group_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1890:2: rule__ColumnOrAlias__Group_1__0__Impl rule__ColumnOrAlias__Group_1__1
            {
            pushFollow(FOLLOW_rule__ColumnOrAlias__Group_1__0__Impl_in_rule__ColumnOrAlias__Group_1__04030);
            rule__ColumnOrAlias__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ColumnOrAlias__Group_1__1_in_rule__ColumnOrAlias__Group_1__04033);
            rule__ColumnOrAlias__Group_1__1();

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
    // $ANTLR end "rule__ColumnOrAlias__Group_1__0"


    // $ANTLR start "rule__ColumnOrAlias__Group_1__0__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1897:1: rule__ColumnOrAlias__Group_1__0__Impl : ( ruleColumnFull ) ;
    public final void rule__ColumnOrAlias__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1901:1: ( ( ruleColumnFull ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1902:1: ( ruleColumnFull )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1902:1: ( ruleColumnFull )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1903:1: ruleColumnFull
            {
             before(grammarAccess.getColumnOrAliasAccess().getColumnFullParserRuleCall_1_0()); 
            pushFollow(FOLLOW_ruleColumnFull_in_rule__ColumnOrAlias__Group_1__0__Impl4060);
            ruleColumnFull();

            state._fsp--;

             after(grammarAccess.getColumnOrAliasAccess().getColumnFullParserRuleCall_1_0()); 

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
    // $ANTLR end "rule__ColumnOrAlias__Group_1__0__Impl"


    // $ANTLR start "rule__ColumnOrAlias__Group_1__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1914:1: rule__ColumnOrAlias__Group_1__1 : rule__ColumnOrAlias__Group_1__1__Impl rule__ColumnOrAlias__Group_1__2 ;
    public final void rule__ColumnOrAlias__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1918:1: ( rule__ColumnOrAlias__Group_1__1__Impl rule__ColumnOrAlias__Group_1__2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1919:2: rule__ColumnOrAlias__Group_1__1__Impl rule__ColumnOrAlias__Group_1__2
            {
            pushFollow(FOLLOW_rule__ColumnOrAlias__Group_1__1__Impl_in_rule__ColumnOrAlias__Group_1__14089);
            rule__ColumnOrAlias__Group_1__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ColumnOrAlias__Group_1__2_in_rule__ColumnOrAlias__Group_1__14092);
            rule__ColumnOrAlias__Group_1__2();

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
    // $ANTLR end "rule__ColumnOrAlias__Group_1__1"


    // $ANTLR start "rule__ColumnOrAlias__Group_1__1__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1926:1: rule__ColumnOrAlias__Group_1__1__Impl : ( 'AS' ) ;
    public final void rule__ColumnOrAlias__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1930:1: ( ( 'AS' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1931:1: ( 'AS' )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1931:1: ( 'AS' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1932:1: 'AS'
            {
             before(grammarAccess.getColumnOrAliasAccess().getASKeyword_1_1()); 
            match(input,29,FOLLOW_29_in_rule__ColumnOrAlias__Group_1__1__Impl4120); 
             after(grammarAccess.getColumnOrAliasAccess().getASKeyword_1_1()); 

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
    // $ANTLR end "rule__ColumnOrAlias__Group_1__1__Impl"


    // $ANTLR start "rule__ColumnOrAlias__Group_1__2"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1945:1: rule__ColumnOrAlias__Group_1__2 : rule__ColumnOrAlias__Group_1__2__Impl ;
    public final void rule__ColumnOrAlias__Group_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1949:1: ( rule__ColumnOrAlias__Group_1__2__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1950:2: rule__ColumnOrAlias__Group_1__2__Impl
            {
            pushFollow(FOLLOW_rule__ColumnOrAlias__Group_1__2__Impl_in_rule__ColumnOrAlias__Group_1__24151);
            rule__ColumnOrAlias__Group_1__2__Impl();

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
    // $ANTLR end "rule__ColumnOrAlias__Group_1__2"


    // $ANTLR start "rule__ColumnOrAlias__Group_1__2__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1956:1: rule__ColumnOrAlias__Group_1__2__Impl : ( ( rule__ColumnOrAlias__ColAliasAssignment_1_2 ) ) ;
    public final void rule__ColumnOrAlias__Group_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1960:1: ( ( ( rule__ColumnOrAlias__ColAliasAssignment_1_2 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1961:1: ( ( rule__ColumnOrAlias__ColAliasAssignment_1_2 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1961:1: ( ( rule__ColumnOrAlias__ColAliasAssignment_1_2 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1962:1: ( rule__ColumnOrAlias__ColAliasAssignment_1_2 )
            {
             before(grammarAccess.getColumnOrAliasAccess().getColAliasAssignment_1_2()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1963:1: ( rule__ColumnOrAlias__ColAliasAssignment_1_2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1963:2: rule__ColumnOrAlias__ColAliasAssignment_1_2
            {
            pushFollow(FOLLOW_rule__ColumnOrAlias__ColAliasAssignment_1_2_in_rule__ColumnOrAlias__Group_1__2__Impl4178);
            rule__ColumnOrAlias__ColAliasAssignment_1_2();

            state._fsp--;


            }

             after(grammarAccess.getColumnOrAliasAccess().getColAliasAssignment_1_2()); 

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
    // $ANTLR end "rule__ColumnOrAlias__Group_1__2__Impl"


    // $ANTLR start "rule__ColumnOrAlias__Group_2__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1979:1: rule__ColumnOrAlias__Group_2__0 : rule__ColumnOrAlias__Group_2__0__Impl rule__ColumnOrAlias__Group_2__1 ;
    public final void rule__ColumnOrAlias__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1983:1: ( rule__ColumnOrAlias__Group_2__0__Impl rule__ColumnOrAlias__Group_2__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1984:2: rule__ColumnOrAlias__Group_2__0__Impl rule__ColumnOrAlias__Group_2__1
            {
            pushFollow(FOLLOW_rule__ColumnOrAlias__Group_2__0__Impl_in_rule__ColumnOrAlias__Group_2__04214);
            rule__ColumnOrAlias__Group_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ColumnOrAlias__Group_2__1_in_rule__ColumnOrAlias__Group_2__04217);
            rule__ColumnOrAlias__Group_2__1();

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
    // $ANTLR end "rule__ColumnOrAlias__Group_2__0"


    // $ANTLR start "rule__ColumnOrAlias__Group_2__0__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1991:1: rule__ColumnOrAlias__Group_2__0__Impl : ( ruleColumnFull ) ;
    public final void rule__ColumnOrAlias__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1995:1: ( ( ruleColumnFull ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1996:1: ( ruleColumnFull )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1996:1: ( ruleColumnFull )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1997:1: ruleColumnFull
            {
             before(grammarAccess.getColumnOrAliasAccess().getColumnFullParserRuleCall_2_0()); 
            pushFollow(FOLLOW_ruleColumnFull_in_rule__ColumnOrAlias__Group_2__0__Impl4244);
            ruleColumnFull();

            state._fsp--;

             after(grammarAccess.getColumnOrAliasAccess().getColumnFullParserRuleCall_2_0()); 

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
    // $ANTLR end "rule__ColumnOrAlias__Group_2__0__Impl"


    // $ANTLR start "rule__ColumnOrAlias__Group_2__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2008:1: rule__ColumnOrAlias__Group_2__1 : rule__ColumnOrAlias__Group_2__1__Impl ;
    public final void rule__ColumnOrAlias__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2012:1: ( rule__ColumnOrAlias__Group_2__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2013:2: rule__ColumnOrAlias__Group_2__1__Impl
            {
            pushFollow(FOLLOW_rule__ColumnOrAlias__Group_2__1__Impl_in_rule__ColumnOrAlias__Group_2__14273);
            rule__ColumnOrAlias__Group_2__1__Impl();

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
    // $ANTLR end "rule__ColumnOrAlias__Group_2__1"


    // $ANTLR start "rule__ColumnOrAlias__Group_2__1__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2019:1: rule__ColumnOrAlias__Group_2__1__Impl : ( ( rule__ColumnOrAlias__ColAliasAssignment_2_1 ) ) ;
    public final void rule__ColumnOrAlias__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2023:1: ( ( ( rule__ColumnOrAlias__ColAliasAssignment_2_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2024:1: ( ( rule__ColumnOrAlias__ColAliasAssignment_2_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2024:1: ( ( rule__ColumnOrAlias__ColAliasAssignment_2_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2025:1: ( rule__ColumnOrAlias__ColAliasAssignment_2_1 )
            {
             before(grammarAccess.getColumnOrAliasAccess().getColAliasAssignment_2_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2026:1: ( rule__ColumnOrAlias__ColAliasAssignment_2_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2026:2: rule__ColumnOrAlias__ColAliasAssignment_2_1
            {
            pushFollow(FOLLOW_rule__ColumnOrAlias__ColAliasAssignment_2_1_in_rule__ColumnOrAlias__Group_2__1__Impl4300);
            rule__ColumnOrAlias__ColAliasAssignment_2_1();

            state._fsp--;


            }

             after(grammarAccess.getColumnOrAliasAccess().getColAliasAssignment_2_1()); 

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
    // $ANTLR end "rule__ColumnOrAlias__Group_2__1__Impl"


    // $ANTLR start "rule__ColumnFull__Group_1__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2040:1: rule__ColumnFull__Group_1__0 : rule__ColumnFull__Group_1__0__Impl rule__ColumnFull__Group_1__1 ;
    public final void rule__ColumnFull__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2044:1: ( rule__ColumnFull__Group_1__0__Impl rule__ColumnFull__Group_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2045:2: rule__ColumnFull__Group_1__0__Impl rule__ColumnFull__Group_1__1
            {
            pushFollow(FOLLOW_rule__ColumnFull__Group_1__0__Impl_in_rule__ColumnFull__Group_1__04334);
            rule__ColumnFull__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ColumnFull__Group_1__1_in_rule__ColumnFull__Group_1__04337);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2052:1: rule__ColumnFull__Group_1__0__Impl : ( ruleTableFull ) ;
    public final void rule__ColumnFull__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2056:1: ( ( ruleTableFull ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2057:1: ( ruleTableFull )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2057:1: ( ruleTableFull )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2058:1: ruleTableFull
            {
             before(grammarAccess.getColumnFullAccess().getTableFullParserRuleCall_1_0()); 
            pushFollow(FOLLOW_ruleTableFull_in_rule__ColumnFull__Group_1__0__Impl4364);
            ruleTableFull();

            state._fsp--;

             after(grammarAccess.getColumnFullAccess().getTableFullParserRuleCall_1_0()); 

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
    // $ANTLR end "rule__ColumnFull__Group_1__0__Impl"


    // $ANTLR start "rule__ColumnFull__Group_1__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2069:1: rule__ColumnFull__Group_1__1 : rule__ColumnFull__Group_1__1__Impl rule__ColumnFull__Group_1__2 ;
    public final void rule__ColumnFull__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2073:1: ( rule__ColumnFull__Group_1__1__Impl rule__ColumnFull__Group_1__2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2074:2: rule__ColumnFull__Group_1__1__Impl rule__ColumnFull__Group_1__2
            {
            pushFollow(FOLLOW_rule__ColumnFull__Group_1__1__Impl_in_rule__ColumnFull__Group_1__14393);
            rule__ColumnFull__Group_1__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ColumnFull__Group_1__2_in_rule__ColumnFull__Group_1__14396);
            rule__ColumnFull__Group_1__2();

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2081:1: rule__ColumnFull__Group_1__1__Impl : ( '.' ) ;
    public final void rule__ColumnFull__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2085:1: ( ( '.' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2086:1: ( '.' )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2086:1: ( '.' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2087:1: '.'
            {
             before(grammarAccess.getColumnFullAccess().getFullStopKeyword_1_1()); 
            match(input,30,FOLLOW_30_in_rule__ColumnFull__Group_1__1__Impl4424); 
             after(grammarAccess.getColumnFullAccess().getFullStopKeyword_1_1()); 

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


    // $ANTLR start "rule__ColumnFull__Group_1__2"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2100:1: rule__ColumnFull__Group_1__2 : rule__ColumnFull__Group_1__2__Impl ;
    public final void rule__ColumnFull__Group_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2104:1: ( rule__ColumnFull__Group_1__2__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2105:2: rule__ColumnFull__Group_1__2__Impl
            {
            pushFollow(FOLLOW_rule__ColumnFull__Group_1__2__Impl_in_rule__ColumnFull__Group_1__24455);
            rule__ColumnFull__Group_1__2__Impl();

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
    // $ANTLR end "rule__ColumnFull__Group_1__2"


    // $ANTLR start "rule__ColumnFull__Group_1__2__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2111:1: rule__ColumnFull__Group_1__2__Impl : ( ( rule__ColumnFull__ColNameAssignment_1_2 ) ) ;
    public final void rule__ColumnFull__Group_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2115:1: ( ( ( rule__ColumnFull__ColNameAssignment_1_2 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2116:1: ( ( rule__ColumnFull__ColNameAssignment_1_2 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2116:1: ( ( rule__ColumnFull__ColNameAssignment_1_2 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2117:1: ( rule__ColumnFull__ColNameAssignment_1_2 )
            {
             before(grammarAccess.getColumnFullAccess().getColNameAssignment_1_2()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2118:1: ( rule__ColumnFull__ColNameAssignment_1_2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2118:2: rule__ColumnFull__ColNameAssignment_1_2
            {
            pushFollow(FOLLOW_rule__ColumnFull__ColNameAssignment_1_2_in_rule__ColumnFull__Group_1__2__Impl4482);
            rule__ColumnFull__ColNameAssignment_1_2();

            state._fsp--;


            }

             after(grammarAccess.getColumnFullAccess().getColNameAssignment_1_2()); 

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
    // $ANTLR end "rule__ColumnFull__Group_1__2__Impl"


    // $ANTLR start "rule__Tables__Group__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2134:1: rule__Tables__Group__0 : rule__Tables__Group__0__Impl rule__Tables__Group__1 ;
    public final void rule__Tables__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2138:1: ( rule__Tables__Group__0__Impl rule__Tables__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2139:2: rule__Tables__Group__0__Impl rule__Tables__Group__1
            {
            pushFollow(FOLLOW_rule__Tables__Group__0__Impl_in_rule__Tables__Group__04518);
            rule__Tables__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Tables__Group__1_in_rule__Tables__Group__04521);
            rule__Tables__Group__1();

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
    // $ANTLR end "rule__Tables__Group__0"


    // $ANTLR start "rule__Tables__Group__0__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2146:1: rule__Tables__Group__0__Impl : ( ruleTableOrAlias ) ;
    public final void rule__Tables__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2150:1: ( ( ruleTableOrAlias ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2151:1: ( ruleTableOrAlias )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2151:1: ( ruleTableOrAlias )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2152:1: ruleTableOrAlias
            {
             before(grammarAccess.getTablesAccess().getTableOrAliasParserRuleCall_0()); 
            pushFollow(FOLLOW_ruleTableOrAlias_in_rule__Tables__Group__0__Impl4548);
            ruleTableOrAlias();

            state._fsp--;

             after(grammarAccess.getTablesAccess().getTableOrAliasParserRuleCall_0()); 

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
    // $ANTLR end "rule__Tables__Group__0__Impl"


    // $ANTLR start "rule__Tables__Group__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2163:1: rule__Tables__Group__1 : rule__Tables__Group__1__Impl ;
    public final void rule__Tables__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2167:1: ( rule__Tables__Group__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2168:2: rule__Tables__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__Tables__Group__1__Impl_in_rule__Tables__Group__14577);
            rule__Tables__Group__1__Impl();

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
    // $ANTLR end "rule__Tables__Group__1"


    // $ANTLR start "rule__Tables__Group__1__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2174:1: rule__Tables__Group__1__Impl : ( ( rule__Tables__Group_1__0 )? ) ;
    public final void rule__Tables__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2178:1: ( ( ( rule__Tables__Group_1__0 )? ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2179:1: ( ( rule__Tables__Group_1__0 )? )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2179:1: ( ( rule__Tables__Group_1__0 )? )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2180:1: ( rule__Tables__Group_1__0 )?
            {
             before(grammarAccess.getTablesAccess().getGroup_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2181:1: ( rule__Tables__Group_1__0 )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==28) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2181:2: rule__Tables__Group_1__0
                    {
                    pushFollow(FOLLOW_rule__Tables__Group_1__0_in_rule__Tables__Group__1__Impl4604);
                    rule__Tables__Group_1__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getTablesAccess().getGroup_1()); 

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
    // $ANTLR end "rule__Tables__Group__1__Impl"


    // $ANTLR start "rule__Tables__Group_1__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2195:1: rule__Tables__Group_1__0 : rule__Tables__Group_1__0__Impl rule__Tables__Group_1__1 ;
    public final void rule__Tables__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2199:1: ( rule__Tables__Group_1__0__Impl rule__Tables__Group_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2200:2: rule__Tables__Group_1__0__Impl rule__Tables__Group_1__1
            {
            pushFollow(FOLLOW_rule__Tables__Group_1__0__Impl_in_rule__Tables__Group_1__04639);
            rule__Tables__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Tables__Group_1__1_in_rule__Tables__Group_1__04642);
            rule__Tables__Group_1__1();

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
    // $ANTLR end "rule__Tables__Group_1__0"


    // $ANTLR start "rule__Tables__Group_1__0__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2207:1: rule__Tables__Group_1__0__Impl : ( () ) ;
    public final void rule__Tables__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2211:1: ( ( () ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2212:1: ( () )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2212:1: ( () )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2213:1: ()
            {
             before(grammarAccess.getTablesAccess().getOrTableEntriesAction_1_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2214:1: ()
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2216:1: 
            {
            }

             after(grammarAccess.getTablesAccess().getOrTableEntriesAction_1_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Tables__Group_1__0__Impl"


    // $ANTLR start "rule__Tables__Group_1__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2226:1: rule__Tables__Group_1__1 : rule__Tables__Group_1__1__Impl ;
    public final void rule__Tables__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2230:1: ( rule__Tables__Group_1__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2231:2: rule__Tables__Group_1__1__Impl
            {
            pushFollow(FOLLOW_rule__Tables__Group_1__1__Impl_in_rule__Tables__Group_1__14700);
            rule__Tables__Group_1__1__Impl();

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
    // $ANTLR end "rule__Tables__Group_1__1"


    // $ANTLR start "rule__Tables__Group_1__1__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2237:1: rule__Tables__Group_1__1__Impl : ( ( ( rule__Tables__Group_1_1__0 ) ) ( ( rule__Tables__Group_1_1__0 )* ) ) ;
    public final void rule__Tables__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2241:1: ( ( ( ( rule__Tables__Group_1_1__0 ) ) ( ( rule__Tables__Group_1_1__0 )* ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2242:1: ( ( ( rule__Tables__Group_1_1__0 ) ) ( ( rule__Tables__Group_1_1__0 )* ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2242:1: ( ( ( rule__Tables__Group_1_1__0 ) ) ( ( rule__Tables__Group_1_1__0 )* ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2243:1: ( ( rule__Tables__Group_1_1__0 ) ) ( ( rule__Tables__Group_1_1__0 )* )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2243:1: ( ( rule__Tables__Group_1_1__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2244:1: ( rule__Tables__Group_1_1__0 )
            {
             before(grammarAccess.getTablesAccess().getGroup_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2245:1: ( rule__Tables__Group_1_1__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2245:2: rule__Tables__Group_1_1__0
            {
            pushFollow(FOLLOW_rule__Tables__Group_1_1__0_in_rule__Tables__Group_1__1__Impl4729);
            rule__Tables__Group_1_1__0();

            state._fsp--;


            }

             after(grammarAccess.getTablesAccess().getGroup_1_1()); 

            }

            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2248:1: ( ( rule__Tables__Group_1_1__0 )* )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2249:1: ( rule__Tables__Group_1_1__0 )*
            {
             before(grammarAccess.getTablesAccess().getGroup_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2250:1: ( rule__Tables__Group_1_1__0 )*
            loop18:
            do {
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( (LA18_0==28) ) {
                    alt18=1;
                }


                switch (alt18) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2250:2: rule__Tables__Group_1_1__0
            	    {
            	    pushFollow(FOLLOW_rule__Tables__Group_1_1__0_in_rule__Tables__Group_1__1__Impl4741);
            	    rule__Tables__Group_1_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop18;
                }
            } while (true);

             after(grammarAccess.getTablesAccess().getGroup_1_1()); 

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
    // $ANTLR end "rule__Tables__Group_1__1__Impl"


    // $ANTLR start "rule__Tables__Group_1_1__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2265:1: rule__Tables__Group_1_1__0 : rule__Tables__Group_1_1__0__Impl rule__Tables__Group_1_1__1 ;
    public final void rule__Tables__Group_1_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2269:1: ( rule__Tables__Group_1_1__0__Impl rule__Tables__Group_1_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2270:2: rule__Tables__Group_1_1__0__Impl rule__Tables__Group_1_1__1
            {
            pushFollow(FOLLOW_rule__Tables__Group_1_1__0__Impl_in_rule__Tables__Group_1_1__04778);
            rule__Tables__Group_1_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Tables__Group_1_1__1_in_rule__Tables__Group_1_1__04781);
            rule__Tables__Group_1_1__1();

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
    // $ANTLR end "rule__Tables__Group_1_1__0"


    // $ANTLR start "rule__Tables__Group_1_1__0__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2277:1: rule__Tables__Group_1_1__0__Impl : ( ',' ) ;
    public final void rule__Tables__Group_1_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2281:1: ( ( ',' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2282:1: ( ',' )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2282:1: ( ',' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2283:1: ','
            {
             before(grammarAccess.getTablesAccess().getCommaKeyword_1_1_0()); 
            match(input,28,FOLLOW_28_in_rule__Tables__Group_1_1__0__Impl4809); 
             after(grammarAccess.getTablesAccess().getCommaKeyword_1_1_0()); 

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
    // $ANTLR end "rule__Tables__Group_1_1__0__Impl"


    // $ANTLR start "rule__Tables__Group_1_1__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2296:1: rule__Tables__Group_1_1__1 : rule__Tables__Group_1_1__1__Impl ;
    public final void rule__Tables__Group_1_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2300:1: ( rule__Tables__Group_1_1__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2301:2: rule__Tables__Group_1_1__1__Impl
            {
            pushFollow(FOLLOW_rule__Tables__Group_1_1__1__Impl_in_rule__Tables__Group_1_1__14840);
            rule__Tables__Group_1_1__1__Impl();

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
    // $ANTLR end "rule__Tables__Group_1_1__1"


    // $ANTLR start "rule__Tables__Group_1_1__1__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2307:1: rule__Tables__Group_1_1__1__Impl : ( ( rule__Tables__EntriesAssignment_1_1_1 ) ) ;
    public final void rule__Tables__Group_1_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2311:1: ( ( ( rule__Tables__EntriesAssignment_1_1_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2312:1: ( ( rule__Tables__EntriesAssignment_1_1_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2312:1: ( ( rule__Tables__EntriesAssignment_1_1_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2313:1: ( rule__Tables__EntriesAssignment_1_1_1 )
            {
             before(grammarAccess.getTablesAccess().getEntriesAssignment_1_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2314:1: ( rule__Tables__EntriesAssignment_1_1_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2314:2: rule__Tables__EntriesAssignment_1_1_1
            {
            pushFollow(FOLLOW_rule__Tables__EntriesAssignment_1_1_1_in_rule__Tables__Group_1_1__1__Impl4867);
            rule__Tables__EntriesAssignment_1_1_1();

            state._fsp--;


            }

             after(grammarAccess.getTablesAccess().getEntriesAssignment_1_1_1()); 

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
    // $ANTLR end "rule__Tables__Group_1_1__1__Impl"


    // $ANTLR start "rule__TableOrAlias__Group_1__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2328:1: rule__TableOrAlias__Group_1__0 : rule__TableOrAlias__Group_1__0__Impl rule__TableOrAlias__Group_1__1 ;
    public final void rule__TableOrAlias__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2332:1: ( rule__TableOrAlias__Group_1__0__Impl rule__TableOrAlias__Group_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2333:2: rule__TableOrAlias__Group_1__0__Impl rule__TableOrAlias__Group_1__1
            {
            pushFollow(FOLLOW_rule__TableOrAlias__Group_1__0__Impl_in_rule__TableOrAlias__Group_1__04901);
            rule__TableOrAlias__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__TableOrAlias__Group_1__1_in_rule__TableOrAlias__Group_1__04904);
            rule__TableOrAlias__Group_1__1();

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
    // $ANTLR end "rule__TableOrAlias__Group_1__0"


    // $ANTLR start "rule__TableOrAlias__Group_1__0__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2340:1: rule__TableOrAlias__Group_1__0__Impl : ( ruleTableFull ) ;
    public final void rule__TableOrAlias__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2344:1: ( ( ruleTableFull ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2345:1: ( ruleTableFull )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2345:1: ( ruleTableFull )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2346:1: ruleTableFull
            {
             before(grammarAccess.getTableOrAliasAccess().getTableFullParserRuleCall_1_0()); 
            pushFollow(FOLLOW_ruleTableFull_in_rule__TableOrAlias__Group_1__0__Impl4931);
            ruleTableFull();

            state._fsp--;

             after(grammarAccess.getTableOrAliasAccess().getTableFullParserRuleCall_1_0()); 

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
    // $ANTLR end "rule__TableOrAlias__Group_1__0__Impl"


    // $ANTLR start "rule__TableOrAlias__Group_1__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2357:1: rule__TableOrAlias__Group_1__1 : rule__TableOrAlias__Group_1__1__Impl rule__TableOrAlias__Group_1__2 ;
    public final void rule__TableOrAlias__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2361:1: ( rule__TableOrAlias__Group_1__1__Impl rule__TableOrAlias__Group_1__2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2362:2: rule__TableOrAlias__Group_1__1__Impl rule__TableOrAlias__Group_1__2
            {
            pushFollow(FOLLOW_rule__TableOrAlias__Group_1__1__Impl_in_rule__TableOrAlias__Group_1__14960);
            rule__TableOrAlias__Group_1__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__TableOrAlias__Group_1__2_in_rule__TableOrAlias__Group_1__14963);
            rule__TableOrAlias__Group_1__2();

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
    // $ANTLR end "rule__TableOrAlias__Group_1__1"


    // $ANTLR start "rule__TableOrAlias__Group_1__1__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2369:1: rule__TableOrAlias__Group_1__1__Impl : ( 'AS' ) ;
    public final void rule__TableOrAlias__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2373:1: ( ( 'AS' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2374:1: ( 'AS' )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2374:1: ( 'AS' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2375:1: 'AS'
            {
             before(grammarAccess.getTableOrAliasAccess().getASKeyword_1_1()); 
            match(input,29,FOLLOW_29_in_rule__TableOrAlias__Group_1__1__Impl4991); 
             after(grammarAccess.getTableOrAliasAccess().getASKeyword_1_1()); 

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
    // $ANTLR end "rule__TableOrAlias__Group_1__1__Impl"


    // $ANTLR start "rule__TableOrAlias__Group_1__2"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2388:1: rule__TableOrAlias__Group_1__2 : rule__TableOrAlias__Group_1__2__Impl ;
    public final void rule__TableOrAlias__Group_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2392:1: ( rule__TableOrAlias__Group_1__2__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2393:2: rule__TableOrAlias__Group_1__2__Impl
            {
            pushFollow(FOLLOW_rule__TableOrAlias__Group_1__2__Impl_in_rule__TableOrAlias__Group_1__25022);
            rule__TableOrAlias__Group_1__2__Impl();

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
    // $ANTLR end "rule__TableOrAlias__Group_1__2"


    // $ANTLR start "rule__TableOrAlias__Group_1__2__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2399:1: rule__TableOrAlias__Group_1__2__Impl : ( ( rule__TableOrAlias__TblAliasAssignment_1_2 ) ) ;
    public final void rule__TableOrAlias__Group_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2403:1: ( ( ( rule__TableOrAlias__TblAliasAssignment_1_2 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2404:1: ( ( rule__TableOrAlias__TblAliasAssignment_1_2 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2404:1: ( ( rule__TableOrAlias__TblAliasAssignment_1_2 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2405:1: ( rule__TableOrAlias__TblAliasAssignment_1_2 )
            {
             before(grammarAccess.getTableOrAliasAccess().getTblAliasAssignment_1_2()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2406:1: ( rule__TableOrAlias__TblAliasAssignment_1_2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2406:2: rule__TableOrAlias__TblAliasAssignment_1_2
            {
            pushFollow(FOLLOW_rule__TableOrAlias__TblAliasAssignment_1_2_in_rule__TableOrAlias__Group_1__2__Impl5049);
            rule__TableOrAlias__TblAliasAssignment_1_2();

            state._fsp--;


            }

             after(grammarAccess.getTableOrAliasAccess().getTblAliasAssignment_1_2()); 

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
    // $ANTLR end "rule__TableOrAlias__Group_1__2__Impl"


    // $ANTLR start "rule__TableOrAlias__Group_2__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2422:1: rule__TableOrAlias__Group_2__0 : rule__TableOrAlias__Group_2__0__Impl rule__TableOrAlias__Group_2__1 ;
    public final void rule__TableOrAlias__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2426:1: ( rule__TableOrAlias__Group_2__0__Impl rule__TableOrAlias__Group_2__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2427:2: rule__TableOrAlias__Group_2__0__Impl rule__TableOrAlias__Group_2__1
            {
            pushFollow(FOLLOW_rule__TableOrAlias__Group_2__0__Impl_in_rule__TableOrAlias__Group_2__05085);
            rule__TableOrAlias__Group_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__TableOrAlias__Group_2__1_in_rule__TableOrAlias__Group_2__05088);
            rule__TableOrAlias__Group_2__1();

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
    // $ANTLR end "rule__TableOrAlias__Group_2__0"


    // $ANTLR start "rule__TableOrAlias__Group_2__0__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2434:1: rule__TableOrAlias__Group_2__0__Impl : ( ruleTableFull ) ;
    public final void rule__TableOrAlias__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2438:1: ( ( ruleTableFull ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2439:1: ( ruleTableFull )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2439:1: ( ruleTableFull )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2440:1: ruleTableFull
            {
             before(grammarAccess.getTableOrAliasAccess().getTableFullParserRuleCall_2_0()); 
            pushFollow(FOLLOW_ruleTableFull_in_rule__TableOrAlias__Group_2__0__Impl5115);
            ruleTableFull();

            state._fsp--;

             after(grammarAccess.getTableOrAliasAccess().getTableFullParserRuleCall_2_0()); 

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
    // $ANTLR end "rule__TableOrAlias__Group_2__0__Impl"


    // $ANTLR start "rule__TableOrAlias__Group_2__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2451:1: rule__TableOrAlias__Group_2__1 : rule__TableOrAlias__Group_2__1__Impl ;
    public final void rule__TableOrAlias__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2455:1: ( rule__TableOrAlias__Group_2__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2456:2: rule__TableOrAlias__Group_2__1__Impl
            {
            pushFollow(FOLLOW_rule__TableOrAlias__Group_2__1__Impl_in_rule__TableOrAlias__Group_2__15144);
            rule__TableOrAlias__Group_2__1__Impl();

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
    // $ANTLR end "rule__TableOrAlias__Group_2__1"


    // $ANTLR start "rule__TableOrAlias__Group_2__1__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2462:1: rule__TableOrAlias__Group_2__1__Impl : ( ( rule__TableOrAlias__TblAliasAssignment_2_1 ) ) ;
    public final void rule__TableOrAlias__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2466:1: ( ( ( rule__TableOrAlias__TblAliasAssignment_2_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2467:1: ( ( rule__TableOrAlias__TblAliasAssignment_2_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2467:1: ( ( rule__TableOrAlias__TblAliasAssignment_2_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2468:1: ( rule__TableOrAlias__TblAliasAssignment_2_1 )
            {
             before(grammarAccess.getTableOrAliasAccess().getTblAliasAssignment_2_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2469:1: ( rule__TableOrAlias__TblAliasAssignment_2_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2469:2: rule__TableOrAlias__TblAliasAssignment_2_1
            {
            pushFollow(FOLLOW_rule__TableOrAlias__TblAliasAssignment_2_1_in_rule__TableOrAlias__Group_2__1__Impl5171);
            rule__TableOrAlias__TblAliasAssignment_2_1();

            state._fsp--;


            }

             after(grammarAccess.getTableOrAliasAccess().getTblAliasAssignment_2_1()); 

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
    // $ANTLR end "rule__TableOrAlias__Group_2__1__Impl"


    // $ANTLR start "rule__TableFull__Group_0__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2483:1: rule__TableFull__Group_0__0 : rule__TableFull__Group_0__0__Impl rule__TableFull__Group_0__1 ;
    public final void rule__TableFull__Group_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2487:1: ( rule__TableFull__Group_0__0__Impl rule__TableFull__Group_0__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2488:2: rule__TableFull__Group_0__0__Impl rule__TableFull__Group_0__1
            {
            pushFollow(FOLLOW_rule__TableFull__Group_0__0__Impl_in_rule__TableFull__Group_0__05205);
            rule__TableFull__Group_0__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__TableFull__Group_0__1_in_rule__TableFull__Group_0__05208);
            rule__TableFull__Group_0__1();

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
    // $ANTLR end "rule__TableFull__Group_0__0"


    // $ANTLR start "rule__TableFull__Group_0__0__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2495:1: rule__TableFull__Group_0__0__Impl : ( ruleSchema ) ;
    public final void rule__TableFull__Group_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2499:1: ( ( ruleSchema ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2500:1: ( ruleSchema )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2500:1: ( ruleSchema )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2501:1: ruleSchema
            {
             before(grammarAccess.getTableFullAccess().getSchemaParserRuleCall_0_0()); 
            pushFollow(FOLLOW_ruleSchema_in_rule__TableFull__Group_0__0__Impl5235);
            ruleSchema();

            state._fsp--;

             after(grammarAccess.getTableFullAccess().getSchemaParserRuleCall_0_0()); 

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
    // $ANTLR end "rule__TableFull__Group_0__0__Impl"


    // $ANTLR start "rule__TableFull__Group_0__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2512:1: rule__TableFull__Group_0__1 : rule__TableFull__Group_0__1__Impl rule__TableFull__Group_0__2 ;
    public final void rule__TableFull__Group_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2516:1: ( rule__TableFull__Group_0__1__Impl rule__TableFull__Group_0__2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2517:2: rule__TableFull__Group_0__1__Impl rule__TableFull__Group_0__2
            {
            pushFollow(FOLLOW_rule__TableFull__Group_0__1__Impl_in_rule__TableFull__Group_0__15264);
            rule__TableFull__Group_0__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__TableFull__Group_0__2_in_rule__TableFull__Group_0__15267);
            rule__TableFull__Group_0__2();

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
    // $ANTLR end "rule__TableFull__Group_0__1"


    // $ANTLR start "rule__TableFull__Group_0__1__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2524:1: rule__TableFull__Group_0__1__Impl : ( '.' ) ;
    public final void rule__TableFull__Group_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2528:1: ( ( '.' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2529:1: ( '.' )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2529:1: ( '.' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2530:1: '.'
            {
             before(grammarAccess.getTableFullAccess().getFullStopKeyword_0_1()); 
            match(input,30,FOLLOW_30_in_rule__TableFull__Group_0__1__Impl5295); 
             after(grammarAccess.getTableFullAccess().getFullStopKeyword_0_1()); 

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
    // $ANTLR end "rule__TableFull__Group_0__1__Impl"


    // $ANTLR start "rule__TableFull__Group_0__2"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2543:1: rule__TableFull__Group_0__2 : rule__TableFull__Group_0__2__Impl ;
    public final void rule__TableFull__Group_0__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2547:1: ( rule__TableFull__Group_0__2__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2548:2: rule__TableFull__Group_0__2__Impl
            {
            pushFollow(FOLLOW_rule__TableFull__Group_0__2__Impl_in_rule__TableFull__Group_0__25326);
            rule__TableFull__Group_0__2__Impl();

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
    // $ANTLR end "rule__TableFull__Group_0__2"


    // $ANTLR start "rule__TableFull__Group_0__2__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2554:1: rule__TableFull__Group_0__2__Impl : ( ( rule__TableFull__TblAssignment_0_2 ) ) ;
    public final void rule__TableFull__Group_0__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2558:1: ( ( ( rule__TableFull__TblAssignment_0_2 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2559:1: ( ( rule__TableFull__TblAssignment_0_2 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2559:1: ( ( rule__TableFull__TblAssignment_0_2 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2560:1: ( rule__TableFull__TblAssignment_0_2 )
            {
             before(grammarAccess.getTableFullAccess().getTblAssignment_0_2()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2561:1: ( rule__TableFull__TblAssignment_0_2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2561:2: rule__TableFull__TblAssignment_0_2
            {
            pushFollow(FOLLOW_rule__TableFull__TblAssignment_0_2_in_rule__TableFull__Group_0__2__Impl5353);
            rule__TableFull__TblAssignment_0_2();

            state._fsp--;


            }

             after(grammarAccess.getTableFullAccess().getTblAssignment_0_2()); 

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
    // $ANTLR end "rule__TableFull__Group_0__2__Impl"


    // $ANTLR start "rule__Schema__Group_0__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2577:1: rule__Schema__Group_0__0 : rule__Schema__Group_0__0__Impl rule__Schema__Group_0__1 ;
    public final void rule__Schema__Group_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2581:1: ( rule__Schema__Group_0__0__Impl rule__Schema__Group_0__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2582:2: rule__Schema__Group_0__0__Impl rule__Schema__Group_0__1
            {
            pushFollow(FOLLOW_rule__Schema__Group_0__0__Impl_in_rule__Schema__Group_0__05389);
            rule__Schema__Group_0__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Schema__Group_0__1_in_rule__Schema__Group_0__05392);
            rule__Schema__Group_0__1();

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
    // $ANTLR end "rule__Schema__Group_0__0"


    // $ANTLR start "rule__Schema__Group_0__0__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2589:1: rule__Schema__Group_0__0__Impl : ( ruleDatabase ) ;
    public final void rule__Schema__Group_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2593:1: ( ( ruleDatabase ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2594:1: ( ruleDatabase )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2594:1: ( ruleDatabase )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2595:1: ruleDatabase
            {
             before(grammarAccess.getSchemaAccess().getDatabaseParserRuleCall_0_0()); 
            pushFollow(FOLLOW_ruleDatabase_in_rule__Schema__Group_0__0__Impl5419);
            ruleDatabase();

            state._fsp--;

             after(grammarAccess.getSchemaAccess().getDatabaseParserRuleCall_0_0()); 

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
    // $ANTLR end "rule__Schema__Group_0__0__Impl"


    // $ANTLR start "rule__Schema__Group_0__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2606:1: rule__Schema__Group_0__1 : rule__Schema__Group_0__1__Impl rule__Schema__Group_0__2 ;
    public final void rule__Schema__Group_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2610:1: ( rule__Schema__Group_0__1__Impl rule__Schema__Group_0__2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2611:2: rule__Schema__Group_0__1__Impl rule__Schema__Group_0__2
            {
            pushFollow(FOLLOW_rule__Schema__Group_0__1__Impl_in_rule__Schema__Group_0__15448);
            rule__Schema__Group_0__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Schema__Group_0__2_in_rule__Schema__Group_0__15451);
            rule__Schema__Group_0__2();

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
    // $ANTLR end "rule__Schema__Group_0__1"


    // $ANTLR start "rule__Schema__Group_0__1__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2618:1: rule__Schema__Group_0__1__Impl : ( '.' ) ;
    public final void rule__Schema__Group_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2622:1: ( ( '.' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2623:1: ( '.' )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2623:1: ( '.' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2624:1: '.'
            {
             before(grammarAccess.getSchemaAccess().getFullStopKeyword_0_1()); 
            match(input,30,FOLLOW_30_in_rule__Schema__Group_0__1__Impl5479); 
             after(grammarAccess.getSchemaAccess().getFullStopKeyword_0_1()); 

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
    // $ANTLR end "rule__Schema__Group_0__1__Impl"


    // $ANTLR start "rule__Schema__Group_0__2"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2637:1: rule__Schema__Group_0__2 : rule__Schema__Group_0__2__Impl ;
    public final void rule__Schema__Group_0__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2641:1: ( rule__Schema__Group_0__2__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2642:2: rule__Schema__Group_0__2__Impl
            {
            pushFollow(FOLLOW_rule__Schema__Group_0__2__Impl_in_rule__Schema__Group_0__25510);
            rule__Schema__Group_0__2__Impl();

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
    // $ANTLR end "rule__Schema__Group_0__2"


    // $ANTLR start "rule__Schema__Group_0__2__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2648:1: rule__Schema__Group_0__2__Impl : ( ( rule__Schema__SchemAssignment_0_2 ) ) ;
    public final void rule__Schema__Group_0__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2652:1: ( ( ( rule__Schema__SchemAssignment_0_2 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2653:1: ( ( rule__Schema__SchemAssignment_0_2 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2653:1: ( ( rule__Schema__SchemAssignment_0_2 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2654:1: ( rule__Schema__SchemAssignment_0_2 )
            {
             before(grammarAccess.getSchemaAccess().getSchemAssignment_0_2()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2655:1: ( rule__Schema__SchemAssignment_0_2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2655:2: rule__Schema__SchemAssignment_0_2
            {
            pushFollow(FOLLOW_rule__Schema__SchemAssignment_0_2_in_rule__Schema__Group_0__2__Impl5537);
            rule__Schema__SchemAssignment_0_2();

            state._fsp--;


            }

             after(grammarAccess.getSchemaAccess().getSchemAssignment_0_2()); 

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
    // $ANTLR end "rule__Schema__Group_0__2__Impl"


    // $ANTLR start "rule__WhereEntry__Group__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2671:1: rule__WhereEntry__Group__0 : rule__WhereEntry__Group__0__Impl rule__WhereEntry__Group__1 ;
    public final void rule__WhereEntry__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2675:1: ( rule__WhereEntry__Group__0__Impl rule__WhereEntry__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2676:2: rule__WhereEntry__Group__0__Impl rule__WhereEntry__Group__1
            {
            pushFollow(FOLLOW_rule__WhereEntry__Group__0__Impl_in_rule__WhereEntry__Group__05573);
            rule__WhereEntry__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__WhereEntry__Group__1_in_rule__WhereEntry__Group__05576);
            rule__WhereEntry__Group__1();

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
    // $ANTLR end "rule__WhereEntry__Group__0"


    // $ANTLR start "rule__WhereEntry__Group__0__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2683:1: rule__WhereEntry__Group__0__Impl : ( ruleAndWhereEntry ) ;
    public final void rule__WhereEntry__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2687:1: ( ( ruleAndWhereEntry ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2688:1: ( ruleAndWhereEntry )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2688:1: ( ruleAndWhereEntry )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2689:1: ruleAndWhereEntry
            {
             before(grammarAccess.getWhereEntryAccess().getAndWhereEntryParserRuleCall_0()); 
            pushFollow(FOLLOW_ruleAndWhereEntry_in_rule__WhereEntry__Group__0__Impl5603);
            ruleAndWhereEntry();

            state._fsp--;

             after(grammarAccess.getWhereEntryAccess().getAndWhereEntryParserRuleCall_0()); 

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
    // $ANTLR end "rule__WhereEntry__Group__0__Impl"


    // $ANTLR start "rule__WhereEntry__Group__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2700:1: rule__WhereEntry__Group__1 : rule__WhereEntry__Group__1__Impl ;
    public final void rule__WhereEntry__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2704:1: ( rule__WhereEntry__Group__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2705:2: rule__WhereEntry__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__WhereEntry__Group__1__Impl_in_rule__WhereEntry__Group__15632);
            rule__WhereEntry__Group__1__Impl();

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
    // $ANTLR end "rule__WhereEntry__Group__1"


    // $ANTLR start "rule__WhereEntry__Group__1__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2711:1: rule__WhereEntry__Group__1__Impl : ( ( rule__WhereEntry__Group_1__0 )? ) ;
    public final void rule__WhereEntry__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2715:1: ( ( ( rule__WhereEntry__Group_1__0 )? ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2716:1: ( ( rule__WhereEntry__Group_1__0 )? )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2716:1: ( ( rule__WhereEntry__Group_1__0 )? )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2717:1: ( rule__WhereEntry__Group_1__0 )?
            {
             before(grammarAccess.getWhereEntryAccess().getGroup_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2718:1: ( rule__WhereEntry__Group_1__0 )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==31) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2718:2: rule__WhereEntry__Group_1__0
                    {
                    pushFollow(FOLLOW_rule__WhereEntry__Group_1__0_in_rule__WhereEntry__Group__1__Impl5659);
                    rule__WhereEntry__Group_1__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getWhereEntryAccess().getGroup_1()); 

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
    // $ANTLR end "rule__WhereEntry__Group__1__Impl"


    // $ANTLR start "rule__WhereEntry__Group_1__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2732:1: rule__WhereEntry__Group_1__0 : rule__WhereEntry__Group_1__0__Impl rule__WhereEntry__Group_1__1 ;
    public final void rule__WhereEntry__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2736:1: ( rule__WhereEntry__Group_1__0__Impl rule__WhereEntry__Group_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2737:2: rule__WhereEntry__Group_1__0__Impl rule__WhereEntry__Group_1__1
            {
            pushFollow(FOLLOW_rule__WhereEntry__Group_1__0__Impl_in_rule__WhereEntry__Group_1__05694);
            rule__WhereEntry__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__WhereEntry__Group_1__1_in_rule__WhereEntry__Group_1__05697);
            rule__WhereEntry__Group_1__1();

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
    // $ANTLR end "rule__WhereEntry__Group_1__0"


    // $ANTLR start "rule__WhereEntry__Group_1__0__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2744:1: rule__WhereEntry__Group_1__0__Impl : ( () ) ;
    public final void rule__WhereEntry__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2748:1: ( ( () ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2749:1: ( () )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2749:1: ( () )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2750:1: ()
            {
             before(grammarAccess.getWhereEntryAccess().getOrWhereEntryEntriesAction_1_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2751:1: ()
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2753:1: 
            {
            }

             after(grammarAccess.getWhereEntryAccess().getOrWhereEntryEntriesAction_1_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__WhereEntry__Group_1__0__Impl"


    // $ANTLR start "rule__WhereEntry__Group_1__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2763:1: rule__WhereEntry__Group_1__1 : rule__WhereEntry__Group_1__1__Impl ;
    public final void rule__WhereEntry__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2767:1: ( rule__WhereEntry__Group_1__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2768:2: rule__WhereEntry__Group_1__1__Impl
            {
            pushFollow(FOLLOW_rule__WhereEntry__Group_1__1__Impl_in_rule__WhereEntry__Group_1__15755);
            rule__WhereEntry__Group_1__1__Impl();

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
    // $ANTLR end "rule__WhereEntry__Group_1__1"


    // $ANTLR start "rule__WhereEntry__Group_1__1__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2774:1: rule__WhereEntry__Group_1__1__Impl : ( ( ( rule__WhereEntry__Group_1_1__0 ) ) ( ( rule__WhereEntry__Group_1_1__0 )* ) ) ;
    public final void rule__WhereEntry__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2778:1: ( ( ( ( rule__WhereEntry__Group_1_1__0 ) ) ( ( rule__WhereEntry__Group_1_1__0 )* ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2779:1: ( ( ( rule__WhereEntry__Group_1_1__0 ) ) ( ( rule__WhereEntry__Group_1_1__0 )* ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2779:1: ( ( ( rule__WhereEntry__Group_1_1__0 ) ) ( ( rule__WhereEntry__Group_1_1__0 )* ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2780:1: ( ( rule__WhereEntry__Group_1_1__0 ) ) ( ( rule__WhereEntry__Group_1_1__0 )* )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2780:1: ( ( rule__WhereEntry__Group_1_1__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2781:1: ( rule__WhereEntry__Group_1_1__0 )
            {
             before(grammarAccess.getWhereEntryAccess().getGroup_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2782:1: ( rule__WhereEntry__Group_1_1__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2782:2: rule__WhereEntry__Group_1_1__0
            {
            pushFollow(FOLLOW_rule__WhereEntry__Group_1_1__0_in_rule__WhereEntry__Group_1__1__Impl5784);
            rule__WhereEntry__Group_1_1__0();

            state._fsp--;


            }

             after(grammarAccess.getWhereEntryAccess().getGroup_1_1()); 

            }

            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2785:1: ( ( rule__WhereEntry__Group_1_1__0 )* )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2786:1: ( rule__WhereEntry__Group_1_1__0 )*
            {
             before(grammarAccess.getWhereEntryAccess().getGroup_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2787:1: ( rule__WhereEntry__Group_1_1__0 )*
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( (LA20_0==31) ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2787:2: rule__WhereEntry__Group_1_1__0
            	    {
            	    pushFollow(FOLLOW_rule__WhereEntry__Group_1_1__0_in_rule__WhereEntry__Group_1__1__Impl5796);
            	    rule__WhereEntry__Group_1_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop20;
                }
            } while (true);

             after(grammarAccess.getWhereEntryAccess().getGroup_1_1()); 

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
    // $ANTLR end "rule__WhereEntry__Group_1__1__Impl"


    // $ANTLR start "rule__WhereEntry__Group_1_1__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2802:1: rule__WhereEntry__Group_1_1__0 : rule__WhereEntry__Group_1_1__0__Impl rule__WhereEntry__Group_1_1__1 ;
    public final void rule__WhereEntry__Group_1_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2806:1: ( rule__WhereEntry__Group_1_1__0__Impl rule__WhereEntry__Group_1_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2807:2: rule__WhereEntry__Group_1_1__0__Impl rule__WhereEntry__Group_1_1__1
            {
            pushFollow(FOLLOW_rule__WhereEntry__Group_1_1__0__Impl_in_rule__WhereEntry__Group_1_1__05833);
            rule__WhereEntry__Group_1_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__WhereEntry__Group_1_1__1_in_rule__WhereEntry__Group_1_1__05836);
            rule__WhereEntry__Group_1_1__1();

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
    // $ANTLR end "rule__WhereEntry__Group_1_1__0"


    // $ANTLR start "rule__WhereEntry__Group_1_1__0__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2814:1: rule__WhereEntry__Group_1_1__0__Impl : ( 'OR' ) ;
    public final void rule__WhereEntry__Group_1_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2818:1: ( ( 'OR' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2819:1: ( 'OR' )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2819:1: ( 'OR' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2820:1: 'OR'
            {
             before(grammarAccess.getWhereEntryAccess().getORKeyword_1_1_0()); 
            match(input,31,FOLLOW_31_in_rule__WhereEntry__Group_1_1__0__Impl5864); 
             after(grammarAccess.getWhereEntryAccess().getORKeyword_1_1_0()); 

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
    // $ANTLR end "rule__WhereEntry__Group_1_1__0__Impl"


    // $ANTLR start "rule__WhereEntry__Group_1_1__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2833:1: rule__WhereEntry__Group_1_1__1 : rule__WhereEntry__Group_1_1__1__Impl ;
    public final void rule__WhereEntry__Group_1_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2837:1: ( rule__WhereEntry__Group_1_1__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2838:2: rule__WhereEntry__Group_1_1__1__Impl
            {
            pushFollow(FOLLOW_rule__WhereEntry__Group_1_1__1__Impl_in_rule__WhereEntry__Group_1_1__15895);
            rule__WhereEntry__Group_1_1__1__Impl();

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
    // $ANTLR end "rule__WhereEntry__Group_1_1__1"


    // $ANTLR start "rule__WhereEntry__Group_1_1__1__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2844:1: rule__WhereEntry__Group_1_1__1__Impl : ( ( rule__WhereEntry__EntriesAssignment_1_1_1 ) ) ;
    public final void rule__WhereEntry__Group_1_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2848:1: ( ( ( rule__WhereEntry__EntriesAssignment_1_1_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2849:1: ( ( rule__WhereEntry__EntriesAssignment_1_1_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2849:1: ( ( rule__WhereEntry__EntriesAssignment_1_1_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2850:1: ( rule__WhereEntry__EntriesAssignment_1_1_1 )
            {
             before(grammarAccess.getWhereEntryAccess().getEntriesAssignment_1_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2851:1: ( rule__WhereEntry__EntriesAssignment_1_1_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2851:2: rule__WhereEntry__EntriesAssignment_1_1_1
            {
            pushFollow(FOLLOW_rule__WhereEntry__EntriesAssignment_1_1_1_in_rule__WhereEntry__Group_1_1__1__Impl5922);
            rule__WhereEntry__EntriesAssignment_1_1_1();

            state._fsp--;


            }

             after(grammarAccess.getWhereEntryAccess().getEntriesAssignment_1_1_1()); 

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
    // $ANTLR end "rule__WhereEntry__Group_1_1__1__Impl"


    // $ANTLR start "rule__AndWhereEntry__Group__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2865:1: rule__AndWhereEntry__Group__0 : rule__AndWhereEntry__Group__0__Impl rule__AndWhereEntry__Group__1 ;
    public final void rule__AndWhereEntry__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2869:1: ( rule__AndWhereEntry__Group__0__Impl rule__AndWhereEntry__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2870:2: rule__AndWhereEntry__Group__0__Impl rule__AndWhereEntry__Group__1
            {
            pushFollow(FOLLOW_rule__AndWhereEntry__Group__0__Impl_in_rule__AndWhereEntry__Group__05956);
            rule__AndWhereEntry__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__AndWhereEntry__Group__1_in_rule__AndWhereEntry__Group__05959);
            rule__AndWhereEntry__Group__1();

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
    // $ANTLR end "rule__AndWhereEntry__Group__0"


    // $ANTLR start "rule__AndWhereEntry__Group__0__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2877:1: rule__AndWhereEntry__Group__0__Impl : ( ruleConcreteWhereEntry ) ;
    public final void rule__AndWhereEntry__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2881:1: ( ( ruleConcreteWhereEntry ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2882:1: ( ruleConcreteWhereEntry )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2882:1: ( ruleConcreteWhereEntry )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2883:1: ruleConcreteWhereEntry
            {
             before(grammarAccess.getAndWhereEntryAccess().getConcreteWhereEntryParserRuleCall_0()); 
            pushFollow(FOLLOW_ruleConcreteWhereEntry_in_rule__AndWhereEntry__Group__0__Impl5986);
            ruleConcreteWhereEntry();

            state._fsp--;

             after(grammarAccess.getAndWhereEntryAccess().getConcreteWhereEntryParserRuleCall_0()); 

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
    // $ANTLR end "rule__AndWhereEntry__Group__0__Impl"


    // $ANTLR start "rule__AndWhereEntry__Group__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2894:1: rule__AndWhereEntry__Group__1 : rule__AndWhereEntry__Group__1__Impl ;
    public final void rule__AndWhereEntry__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2898:1: ( rule__AndWhereEntry__Group__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2899:2: rule__AndWhereEntry__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__AndWhereEntry__Group__1__Impl_in_rule__AndWhereEntry__Group__16015);
            rule__AndWhereEntry__Group__1__Impl();

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
    // $ANTLR end "rule__AndWhereEntry__Group__1"


    // $ANTLR start "rule__AndWhereEntry__Group__1__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2905:1: rule__AndWhereEntry__Group__1__Impl : ( ( rule__AndWhereEntry__Group_1__0 )? ) ;
    public final void rule__AndWhereEntry__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2909:1: ( ( ( rule__AndWhereEntry__Group_1__0 )? ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2910:1: ( ( rule__AndWhereEntry__Group_1__0 )? )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2910:1: ( ( rule__AndWhereEntry__Group_1__0 )? )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2911:1: ( rule__AndWhereEntry__Group_1__0 )?
            {
             before(grammarAccess.getAndWhereEntryAccess().getGroup_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2912:1: ( rule__AndWhereEntry__Group_1__0 )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==32) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2912:2: rule__AndWhereEntry__Group_1__0
                    {
                    pushFollow(FOLLOW_rule__AndWhereEntry__Group_1__0_in_rule__AndWhereEntry__Group__1__Impl6042);
                    rule__AndWhereEntry__Group_1__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getAndWhereEntryAccess().getGroup_1()); 

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
    // $ANTLR end "rule__AndWhereEntry__Group__1__Impl"


    // $ANTLR start "rule__AndWhereEntry__Group_1__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2926:1: rule__AndWhereEntry__Group_1__0 : rule__AndWhereEntry__Group_1__0__Impl rule__AndWhereEntry__Group_1__1 ;
    public final void rule__AndWhereEntry__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2930:1: ( rule__AndWhereEntry__Group_1__0__Impl rule__AndWhereEntry__Group_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2931:2: rule__AndWhereEntry__Group_1__0__Impl rule__AndWhereEntry__Group_1__1
            {
            pushFollow(FOLLOW_rule__AndWhereEntry__Group_1__0__Impl_in_rule__AndWhereEntry__Group_1__06077);
            rule__AndWhereEntry__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__AndWhereEntry__Group_1__1_in_rule__AndWhereEntry__Group_1__06080);
            rule__AndWhereEntry__Group_1__1();

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
    // $ANTLR end "rule__AndWhereEntry__Group_1__0"


    // $ANTLR start "rule__AndWhereEntry__Group_1__0__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2938:1: rule__AndWhereEntry__Group_1__0__Impl : ( () ) ;
    public final void rule__AndWhereEntry__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2942:1: ( ( () ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2943:1: ( () )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2943:1: ( () )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2944:1: ()
            {
             before(grammarAccess.getAndWhereEntryAccess().getAndWhereEntryEntriesAction_1_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2945:1: ()
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2947:1: 
            {
            }

             after(grammarAccess.getAndWhereEntryAccess().getAndWhereEntryEntriesAction_1_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AndWhereEntry__Group_1__0__Impl"


    // $ANTLR start "rule__AndWhereEntry__Group_1__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2957:1: rule__AndWhereEntry__Group_1__1 : rule__AndWhereEntry__Group_1__1__Impl ;
    public final void rule__AndWhereEntry__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2961:1: ( rule__AndWhereEntry__Group_1__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2962:2: rule__AndWhereEntry__Group_1__1__Impl
            {
            pushFollow(FOLLOW_rule__AndWhereEntry__Group_1__1__Impl_in_rule__AndWhereEntry__Group_1__16138);
            rule__AndWhereEntry__Group_1__1__Impl();

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
    // $ANTLR end "rule__AndWhereEntry__Group_1__1"


    // $ANTLR start "rule__AndWhereEntry__Group_1__1__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2968:1: rule__AndWhereEntry__Group_1__1__Impl : ( ( ( rule__AndWhereEntry__Group_1_1__0 ) ) ( ( rule__AndWhereEntry__Group_1_1__0 )* ) ) ;
    public final void rule__AndWhereEntry__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2972:1: ( ( ( ( rule__AndWhereEntry__Group_1_1__0 ) ) ( ( rule__AndWhereEntry__Group_1_1__0 )* ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2973:1: ( ( ( rule__AndWhereEntry__Group_1_1__0 ) ) ( ( rule__AndWhereEntry__Group_1_1__0 )* ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2973:1: ( ( ( rule__AndWhereEntry__Group_1_1__0 ) ) ( ( rule__AndWhereEntry__Group_1_1__0 )* ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2974:1: ( ( rule__AndWhereEntry__Group_1_1__0 ) ) ( ( rule__AndWhereEntry__Group_1_1__0 )* )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2974:1: ( ( rule__AndWhereEntry__Group_1_1__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2975:1: ( rule__AndWhereEntry__Group_1_1__0 )
            {
             before(grammarAccess.getAndWhereEntryAccess().getGroup_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2976:1: ( rule__AndWhereEntry__Group_1_1__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2976:2: rule__AndWhereEntry__Group_1_1__0
            {
            pushFollow(FOLLOW_rule__AndWhereEntry__Group_1_1__0_in_rule__AndWhereEntry__Group_1__1__Impl6167);
            rule__AndWhereEntry__Group_1_1__0();

            state._fsp--;


            }

             after(grammarAccess.getAndWhereEntryAccess().getGroup_1_1()); 

            }

            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2979:1: ( ( rule__AndWhereEntry__Group_1_1__0 )* )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2980:1: ( rule__AndWhereEntry__Group_1_1__0 )*
            {
             before(grammarAccess.getAndWhereEntryAccess().getGroup_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2981:1: ( rule__AndWhereEntry__Group_1_1__0 )*
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( (LA22_0==32) ) {
                    alt22=1;
                }


                switch (alt22) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2981:2: rule__AndWhereEntry__Group_1_1__0
            	    {
            	    pushFollow(FOLLOW_rule__AndWhereEntry__Group_1_1__0_in_rule__AndWhereEntry__Group_1__1__Impl6179);
            	    rule__AndWhereEntry__Group_1_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop22;
                }
            } while (true);

             after(grammarAccess.getAndWhereEntryAccess().getGroup_1_1()); 

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
    // $ANTLR end "rule__AndWhereEntry__Group_1__1__Impl"


    // $ANTLR start "rule__AndWhereEntry__Group_1_1__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2996:1: rule__AndWhereEntry__Group_1_1__0 : rule__AndWhereEntry__Group_1_1__0__Impl rule__AndWhereEntry__Group_1_1__1 ;
    public final void rule__AndWhereEntry__Group_1_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3000:1: ( rule__AndWhereEntry__Group_1_1__0__Impl rule__AndWhereEntry__Group_1_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3001:2: rule__AndWhereEntry__Group_1_1__0__Impl rule__AndWhereEntry__Group_1_1__1
            {
            pushFollow(FOLLOW_rule__AndWhereEntry__Group_1_1__0__Impl_in_rule__AndWhereEntry__Group_1_1__06216);
            rule__AndWhereEntry__Group_1_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__AndWhereEntry__Group_1_1__1_in_rule__AndWhereEntry__Group_1_1__06219);
            rule__AndWhereEntry__Group_1_1__1();

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
    // $ANTLR end "rule__AndWhereEntry__Group_1_1__0"


    // $ANTLR start "rule__AndWhereEntry__Group_1_1__0__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3008:1: rule__AndWhereEntry__Group_1_1__0__Impl : ( 'AND' ) ;
    public final void rule__AndWhereEntry__Group_1_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3012:1: ( ( 'AND' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3013:1: ( 'AND' )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3013:1: ( 'AND' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3014:1: 'AND'
            {
             before(grammarAccess.getAndWhereEntryAccess().getANDKeyword_1_1_0()); 
            match(input,32,FOLLOW_32_in_rule__AndWhereEntry__Group_1_1__0__Impl6247); 
             after(grammarAccess.getAndWhereEntryAccess().getANDKeyword_1_1_0()); 

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
    // $ANTLR end "rule__AndWhereEntry__Group_1_1__0__Impl"


    // $ANTLR start "rule__AndWhereEntry__Group_1_1__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3027:1: rule__AndWhereEntry__Group_1_1__1 : rule__AndWhereEntry__Group_1_1__1__Impl ;
    public final void rule__AndWhereEntry__Group_1_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3031:1: ( rule__AndWhereEntry__Group_1_1__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3032:2: rule__AndWhereEntry__Group_1_1__1__Impl
            {
            pushFollow(FOLLOW_rule__AndWhereEntry__Group_1_1__1__Impl_in_rule__AndWhereEntry__Group_1_1__16278);
            rule__AndWhereEntry__Group_1_1__1__Impl();

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
    // $ANTLR end "rule__AndWhereEntry__Group_1_1__1"


    // $ANTLR start "rule__AndWhereEntry__Group_1_1__1__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3038:1: rule__AndWhereEntry__Group_1_1__1__Impl : ( ( rule__AndWhereEntry__EntriesAssignment_1_1_1 ) ) ;
    public final void rule__AndWhereEntry__Group_1_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3042:1: ( ( ( rule__AndWhereEntry__EntriesAssignment_1_1_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3043:1: ( ( rule__AndWhereEntry__EntriesAssignment_1_1_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3043:1: ( ( rule__AndWhereEntry__EntriesAssignment_1_1_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3044:1: ( rule__AndWhereEntry__EntriesAssignment_1_1_1 )
            {
             before(grammarAccess.getAndWhereEntryAccess().getEntriesAssignment_1_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3045:1: ( rule__AndWhereEntry__EntriesAssignment_1_1_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3045:2: rule__AndWhereEntry__EntriesAssignment_1_1_1
            {
            pushFollow(FOLLOW_rule__AndWhereEntry__EntriesAssignment_1_1_1_in_rule__AndWhereEntry__Group_1_1__1__Impl6305);
            rule__AndWhereEntry__EntriesAssignment_1_1_1();

            state._fsp--;


            }

             after(grammarAccess.getAndWhereEntryAccess().getEntriesAssignment_1_1_1()); 

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
    // $ANTLR end "rule__AndWhereEntry__Group_1_1__1__Impl"


    // $ANTLR start "rule__ParWhereEntry__Group__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3059:1: rule__ParWhereEntry__Group__0 : rule__ParWhereEntry__Group__0__Impl rule__ParWhereEntry__Group__1 ;
    public final void rule__ParWhereEntry__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3063:1: ( rule__ParWhereEntry__Group__0__Impl rule__ParWhereEntry__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3064:2: rule__ParWhereEntry__Group__0__Impl rule__ParWhereEntry__Group__1
            {
            pushFollow(FOLLOW_rule__ParWhereEntry__Group__0__Impl_in_rule__ParWhereEntry__Group__06339);
            rule__ParWhereEntry__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ParWhereEntry__Group__1_in_rule__ParWhereEntry__Group__06342);
            rule__ParWhereEntry__Group__1();

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
    // $ANTLR end "rule__ParWhereEntry__Group__0"


    // $ANTLR start "rule__ParWhereEntry__Group__0__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3071:1: rule__ParWhereEntry__Group__0__Impl : ( '(' ) ;
    public final void rule__ParWhereEntry__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3075:1: ( ( '(' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3076:1: ( '(' )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3076:1: ( '(' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3077:1: '('
            {
             before(grammarAccess.getParWhereEntryAccess().getLeftParenthesisKeyword_0()); 
            match(input,33,FOLLOW_33_in_rule__ParWhereEntry__Group__0__Impl6370); 
             after(grammarAccess.getParWhereEntryAccess().getLeftParenthesisKeyword_0()); 

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
    // $ANTLR end "rule__ParWhereEntry__Group__0__Impl"


    // $ANTLR start "rule__ParWhereEntry__Group__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3090:1: rule__ParWhereEntry__Group__1 : rule__ParWhereEntry__Group__1__Impl rule__ParWhereEntry__Group__2 ;
    public final void rule__ParWhereEntry__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3094:1: ( rule__ParWhereEntry__Group__1__Impl rule__ParWhereEntry__Group__2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3095:2: rule__ParWhereEntry__Group__1__Impl rule__ParWhereEntry__Group__2
            {
            pushFollow(FOLLOW_rule__ParWhereEntry__Group__1__Impl_in_rule__ParWhereEntry__Group__16401);
            rule__ParWhereEntry__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ParWhereEntry__Group__2_in_rule__ParWhereEntry__Group__16404);
            rule__ParWhereEntry__Group__2();

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
    // $ANTLR end "rule__ParWhereEntry__Group__1"


    // $ANTLR start "rule__ParWhereEntry__Group__1__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3102:1: rule__ParWhereEntry__Group__1__Impl : ( ruleWhereEntry ) ;
    public final void rule__ParWhereEntry__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3106:1: ( ( ruleWhereEntry ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3107:1: ( ruleWhereEntry )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3107:1: ( ruleWhereEntry )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3108:1: ruleWhereEntry
            {
             before(grammarAccess.getParWhereEntryAccess().getWhereEntryParserRuleCall_1()); 
            pushFollow(FOLLOW_ruleWhereEntry_in_rule__ParWhereEntry__Group__1__Impl6431);
            ruleWhereEntry();

            state._fsp--;

             after(grammarAccess.getParWhereEntryAccess().getWhereEntryParserRuleCall_1()); 

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
    // $ANTLR end "rule__ParWhereEntry__Group__1__Impl"


    // $ANTLR start "rule__ParWhereEntry__Group__2"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3119:1: rule__ParWhereEntry__Group__2 : rule__ParWhereEntry__Group__2__Impl ;
    public final void rule__ParWhereEntry__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3123:1: ( rule__ParWhereEntry__Group__2__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3124:2: rule__ParWhereEntry__Group__2__Impl
            {
            pushFollow(FOLLOW_rule__ParWhereEntry__Group__2__Impl_in_rule__ParWhereEntry__Group__26460);
            rule__ParWhereEntry__Group__2__Impl();

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
    // $ANTLR end "rule__ParWhereEntry__Group__2"


    // $ANTLR start "rule__ParWhereEntry__Group__2__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3130:1: rule__ParWhereEntry__Group__2__Impl : ( ')' ) ;
    public final void rule__ParWhereEntry__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3134:1: ( ( ')' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3135:1: ( ')' )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3135:1: ( ')' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3136:1: ')'
            {
             before(grammarAccess.getParWhereEntryAccess().getRightParenthesisKeyword_2()); 
            match(input,34,FOLLOW_34_in_rule__ParWhereEntry__Group__2__Impl6488); 
             after(grammarAccess.getParWhereEntryAccess().getRightParenthesisKeyword_2()); 

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
    // $ANTLR end "rule__ParWhereEntry__Group__2__Impl"


    // $ANTLR start "rule__SingleExpressionWhereEntry__Group__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3155:1: rule__SingleExpressionWhereEntry__Group__0 : rule__SingleExpressionWhereEntry__Group__0__Impl rule__SingleExpressionWhereEntry__Group__1 ;
    public final void rule__SingleExpressionWhereEntry__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3159:1: ( rule__SingleExpressionWhereEntry__Group__0__Impl rule__SingleExpressionWhereEntry__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3160:2: rule__SingleExpressionWhereEntry__Group__0__Impl rule__SingleExpressionWhereEntry__Group__1
            {
            pushFollow(FOLLOW_rule__SingleExpressionWhereEntry__Group__0__Impl_in_rule__SingleExpressionWhereEntry__Group__06525);
            rule__SingleExpressionWhereEntry__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__SingleExpressionWhereEntry__Group__1_in_rule__SingleExpressionWhereEntry__Group__06528);
            rule__SingleExpressionWhereEntry__Group__1();

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
    // $ANTLR end "rule__SingleExpressionWhereEntry__Group__0"


    // $ANTLR start "rule__SingleExpressionWhereEntry__Group__0__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3167:1: rule__SingleExpressionWhereEntry__Group__0__Impl : ( ( rule__SingleExpressionWhereEntry__NameAssignment_0 ) ) ;
    public final void rule__SingleExpressionWhereEntry__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3171:1: ( ( ( rule__SingleExpressionWhereEntry__NameAssignment_0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3172:1: ( ( rule__SingleExpressionWhereEntry__NameAssignment_0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3172:1: ( ( rule__SingleExpressionWhereEntry__NameAssignment_0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3173:1: ( rule__SingleExpressionWhereEntry__NameAssignment_0 )
            {
             before(grammarAccess.getSingleExpressionWhereEntryAccess().getNameAssignment_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3174:1: ( rule__SingleExpressionWhereEntry__NameAssignment_0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3174:2: rule__SingleExpressionWhereEntry__NameAssignment_0
            {
            pushFollow(FOLLOW_rule__SingleExpressionWhereEntry__NameAssignment_0_in_rule__SingleExpressionWhereEntry__Group__0__Impl6555);
            rule__SingleExpressionWhereEntry__NameAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getSingleExpressionWhereEntryAccess().getNameAssignment_0()); 

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
    // $ANTLR end "rule__SingleExpressionWhereEntry__Group__0__Impl"


    // $ANTLR start "rule__SingleExpressionWhereEntry__Group__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3184:1: rule__SingleExpressionWhereEntry__Group__1 : rule__SingleExpressionWhereEntry__Group__1__Impl rule__SingleExpressionWhereEntry__Group__2 ;
    public final void rule__SingleExpressionWhereEntry__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3188:1: ( rule__SingleExpressionWhereEntry__Group__1__Impl rule__SingleExpressionWhereEntry__Group__2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3189:2: rule__SingleExpressionWhereEntry__Group__1__Impl rule__SingleExpressionWhereEntry__Group__2
            {
            pushFollow(FOLLOW_rule__SingleExpressionWhereEntry__Group__1__Impl_in_rule__SingleExpressionWhereEntry__Group__16585);
            rule__SingleExpressionWhereEntry__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__SingleExpressionWhereEntry__Group__2_in_rule__SingleExpressionWhereEntry__Group__16588);
            rule__SingleExpressionWhereEntry__Group__2();

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
    // $ANTLR end "rule__SingleExpressionWhereEntry__Group__1"


    // $ANTLR start "rule__SingleExpressionWhereEntry__Group__1__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3196:1: rule__SingleExpressionWhereEntry__Group__1__Impl : ( ( rule__SingleExpressionWhereEntry__OperatorAssignment_1 ) ) ;
    public final void rule__SingleExpressionWhereEntry__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3200:1: ( ( ( rule__SingleExpressionWhereEntry__OperatorAssignment_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3201:1: ( ( rule__SingleExpressionWhereEntry__OperatorAssignment_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3201:1: ( ( rule__SingleExpressionWhereEntry__OperatorAssignment_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3202:1: ( rule__SingleExpressionWhereEntry__OperatorAssignment_1 )
            {
             before(grammarAccess.getSingleExpressionWhereEntryAccess().getOperatorAssignment_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3203:1: ( rule__SingleExpressionWhereEntry__OperatorAssignment_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3203:2: rule__SingleExpressionWhereEntry__OperatorAssignment_1
            {
            pushFollow(FOLLOW_rule__SingleExpressionWhereEntry__OperatorAssignment_1_in_rule__SingleExpressionWhereEntry__Group__1__Impl6615);
            rule__SingleExpressionWhereEntry__OperatorAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getSingleExpressionWhereEntryAccess().getOperatorAssignment_1()); 

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
    // $ANTLR end "rule__SingleExpressionWhereEntry__Group__1__Impl"


    // $ANTLR start "rule__SingleExpressionWhereEntry__Group__2"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3213:1: rule__SingleExpressionWhereEntry__Group__2 : rule__SingleExpressionWhereEntry__Group__2__Impl ;
    public final void rule__SingleExpressionWhereEntry__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3217:1: ( rule__SingleExpressionWhereEntry__Group__2__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3218:2: rule__SingleExpressionWhereEntry__Group__2__Impl
            {
            pushFollow(FOLLOW_rule__SingleExpressionWhereEntry__Group__2__Impl_in_rule__SingleExpressionWhereEntry__Group__26645);
            rule__SingleExpressionWhereEntry__Group__2__Impl();

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
    // $ANTLR end "rule__SingleExpressionWhereEntry__Group__2"


    // $ANTLR start "rule__SingleExpressionWhereEntry__Group__2__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3224:1: rule__SingleExpressionWhereEntry__Group__2__Impl : ( ( rule__SingleExpressionWhereEntry__RhsAssignment_2 ) ) ;
    public final void rule__SingleExpressionWhereEntry__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3228:1: ( ( ( rule__SingleExpressionWhereEntry__RhsAssignment_2 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3229:1: ( ( rule__SingleExpressionWhereEntry__RhsAssignment_2 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3229:1: ( ( rule__SingleExpressionWhereEntry__RhsAssignment_2 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3230:1: ( rule__SingleExpressionWhereEntry__RhsAssignment_2 )
            {
             before(grammarAccess.getSingleExpressionWhereEntryAccess().getRhsAssignment_2()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3231:1: ( rule__SingleExpressionWhereEntry__RhsAssignment_2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3231:2: rule__SingleExpressionWhereEntry__RhsAssignment_2
            {
            pushFollow(FOLLOW_rule__SingleExpressionWhereEntry__RhsAssignment_2_in_rule__SingleExpressionWhereEntry__Group__2__Impl6672);
            rule__SingleExpressionWhereEntry__RhsAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getSingleExpressionWhereEntryAccess().getRhsAssignment_2()); 

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
    // $ANTLR end "rule__SingleExpressionWhereEntry__Group__2__Impl"


    // $ANTLR start "rule__MultiExpressionWhereEntry__Group__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3247:1: rule__MultiExpressionWhereEntry__Group__0 : rule__MultiExpressionWhereEntry__Group__0__Impl rule__MultiExpressionWhereEntry__Group__1 ;
    public final void rule__MultiExpressionWhereEntry__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3251:1: ( rule__MultiExpressionWhereEntry__Group__0__Impl rule__MultiExpressionWhereEntry__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3252:2: rule__MultiExpressionWhereEntry__Group__0__Impl rule__MultiExpressionWhereEntry__Group__1
            {
            pushFollow(FOLLOW_rule__MultiExpressionWhereEntry__Group__0__Impl_in_rule__MultiExpressionWhereEntry__Group__06708);
            rule__MultiExpressionWhereEntry__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__MultiExpressionWhereEntry__Group__1_in_rule__MultiExpressionWhereEntry__Group__06711);
            rule__MultiExpressionWhereEntry__Group__1();

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
    // $ANTLR end "rule__MultiExpressionWhereEntry__Group__0"


    // $ANTLR start "rule__MultiExpressionWhereEntry__Group__0__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3259:1: rule__MultiExpressionWhereEntry__Group__0__Impl : ( ( rule__MultiExpressionWhereEntry__NameAssignment_0 ) ) ;
    public final void rule__MultiExpressionWhereEntry__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3263:1: ( ( ( rule__MultiExpressionWhereEntry__NameAssignment_0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3264:1: ( ( rule__MultiExpressionWhereEntry__NameAssignment_0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3264:1: ( ( rule__MultiExpressionWhereEntry__NameAssignment_0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3265:1: ( rule__MultiExpressionWhereEntry__NameAssignment_0 )
            {
             before(grammarAccess.getMultiExpressionWhereEntryAccess().getNameAssignment_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3266:1: ( rule__MultiExpressionWhereEntry__NameAssignment_0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3266:2: rule__MultiExpressionWhereEntry__NameAssignment_0
            {
            pushFollow(FOLLOW_rule__MultiExpressionWhereEntry__NameAssignment_0_in_rule__MultiExpressionWhereEntry__Group__0__Impl6738);
            rule__MultiExpressionWhereEntry__NameAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getMultiExpressionWhereEntryAccess().getNameAssignment_0()); 

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
    // $ANTLR end "rule__MultiExpressionWhereEntry__Group__0__Impl"


    // $ANTLR start "rule__MultiExpressionWhereEntry__Group__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3276:1: rule__MultiExpressionWhereEntry__Group__1 : rule__MultiExpressionWhereEntry__Group__1__Impl rule__MultiExpressionWhereEntry__Group__2 ;
    public final void rule__MultiExpressionWhereEntry__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3280:1: ( rule__MultiExpressionWhereEntry__Group__1__Impl rule__MultiExpressionWhereEntry__Group__2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3281:2: rule__MultiExpressionWhereEntry__Group__1__Impl rule__MultiExpressionWhereEntry__Group__2
            {
            pushFollow(FOLLOW_rule__MultiExpressionWhereEntry__Group__1__Impl_in_rule__MultiExpressionWhereEntry__Group__16768);
            rule__MultiExpressionWhereEntry__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__MultiExpressionWhereEntry__Group__2_in_rule__MultiExpressionWhereEntry__Group__16771);
            rule__MultiExpressionWhereEntry__Group__2();

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
    // $ANTLR end "rule__MultiExpressionWhereEntry__Group__1"


    // $ANTLR start "rule__MultiExpressionWhereEntry__Group__1__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3288:1: rule__MultiExpressionWhereEntry__Group__1__Impl : ( ( rule__MultiExpressionWhereEntry__OperatorAssignment_1 ) ) ;
    public final void rule__MultiExpressionWhereEntry__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3292:1: ( ( ( rule__MultiExpressionWhereEntry__OperatorAssignment_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3293:1: ( ( rule__MultiExpressionWhereEntry__OperatorAssignment_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3293:1: ( ( rule__MultiExpressionWhereEntry__OperatorAssignment_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3294:1: ( rule__MultiExpressionWhereEntry__OperatorAssignment_1 )
            {
             before(grammarAccess.getMultiExpressionWhereEntryAccess().getOperatorAssignment_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3295:1: ( rule__MultiExpressionWhereEntry__OperatorAssignment_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3295:2: rule__MultiExpressionWhereEntry__OperatorAssignment_1
            {
            pushFollow(FOLLOW_rule__MultiExpressionWhereEntry__OperatorAssignment_1_in_rule__MultiExpressionWhereEntry__Group__1__Impl6798);
            rule__MultiExpressionWhereEntry__OperatorAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getMultiExpressionWhereEntryAccess().getOperatorAssignment_1()); 

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
    // $ANTLR end "rule__MultiExpressionWhereEntry__Group__1__Impl"


    // $ANTLR start "rule__MultiExpressionWhereEntry__Group__2"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3305:1: rule__MultiExpressionWhereEntry__Group__2 : rule__MultiExpressionWhereEntry__Group__2__Impl ;
    public final void rule__MultiExpressionWhereEntry__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3309:1: ( rule__MultiExpressionWhereEntry__Group__2__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3310:2: rule__MultiExpressionWhereEntry__Group__2__Impl
            {
            pushFollow(FOLLOW_rule__MultiExpressionWhereEntry__Group__2__Impl_in_rule__MultiExpressionWhereEntry__Group__26828);
            rule__MultiExpressionWhereEntry__Group__2__Impl();

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
    // $ANTLR end "rule__MultiExpressionWhereEntry__Group__2"


    // $ANTLR start "rule__MultiExpressionWhereEntry__Group__2__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3316:1: rule__MultiExpressionWhereEntry__Group__2__Impl : ( ( rule__MultiExpressionWhereEntry__RhsAssignment_2 ) ) ;
    public final void rule__MultiExpressionWhereEntry__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3320:1: ( ( ( rule__MultiExpressionWhereEntry__RhsAssignment_2 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3321:1: ( ( rule__MultiExpressionWhereEntry__RhsAssignment_2 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3321:1: ( ( rule__MultiExpressionWhereEntry__RhsAssignment_2 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3322:1: ( rule__MultiExpressionWhereEntry__RhsAssignment_2 )
            {
             before(grammarAccess.getMultiExpressionWhereEntryAccess().getRhsAssignment_2()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3323:1: ( rule__MultiExpressionWhereEntry__RhsAssignment_2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3323:2: rule__MultiExpressionWhereEntry__RhsAssignment_2
            {
            pushFollow(FOLLOW_rule__MultiExpressionWhereEntry__RhsAssignment_2_in_rule__MultiExpressionWhereEntry__Group__2__Impl6855);
            rule__MultiExpressionWhereEntry__RhsAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getMultiExpressionWhereEntryAccess().getRhsAssignment_2()); 

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
    // $ANTLR end "rule__MultiExpressionWhereEntry__Group__2__Impl"


    // $ANTLR start "rule__DoubleArrayExpression__Group__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3339:1: rule__DoubleArrayExpression__Group__0 : rule__DoubleArrayExpression__Group__0__Impl rule__DoubleArrayExpression__Group__1 ;
    public final void rule__DoubleArrayExpression__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3343:1: ( rule__DoubleArrayExpression__Group__0__Impl rule__DoubleArrayExpression__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3344:2: rule__DoubleArrayExpression__Group__0__Impl rule__DoubleArrayExpression__Group__1
            {
            pushFollow(FOLLOW_rule__DoubleArrayExpression__Group__0__Impl_in_rule__DoubleArrayExpression__Group__06891);
            rule__DoubleArrayExpression__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__DoubleArrayExpression__Group__1_in_rule__DoubleArrayExpression__Group__06894);
            rule__DoubleArrayExpression__Group__1();

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
    // $ANTLR end "rule__DoubleArrayExpression__Group__0"


    // $ANTLR start "rule__DoubleArrayExpression__Group__0__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3351:1: rule__DoubleArrayExpression__Group__0__Impl : ( '[' ) ;
    public final void rule__DoubleArrayExpression__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3355:1: ( ( '[' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3356:1: ( '[' )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3356:1: ( '[' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3357:1: '['
            {
             before(grammarAccess.getDoubleArrayExpressionAccess().getLeftSquareBracketKeyword_0()); 
            match(input,35,FOLLOW_35_in_rule__DoubleArrayExpression__Group__0__Impl6922); 
             after(grammarAccess.getDoubleArrayExpressionAccess().getLeftSquareBracketKeyword_0()); 

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
    // $ANTLR end "rule__DoubleArrayExpression__Group__0__Impl"


    // $ANTLR start "rule__DoubleArrayExpression__Group__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3370:1: rule__DoubleArrayExpression__Group__1 : rule__DoubleArrayExpression__Group__1__Impl rule__DoubleArrayExpression__Group__2 ;
    public final void rule__DoubleArrayExpression__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3374:1: ( rule__DoubleArrayExpression__Group__1__Impl rule__DoubleArrayExpression__Group__2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3375:2: rule__DoubleArrayExpression__Group__1__Impl rule__DoubleArrayExpression__Group__2
            {
            pushFollow(FOLLOW_rule__DoubleArrayExpression__Group__1__Impl_in_rule__DoubleArrayExpression__Group__16953);
            rule__DoubleArrayExpression__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__DoubleArrayExpression__Group__2_in_rule__DoubleArrayExpression__Group__16956);
            rule__DoubleArrayExpression__Group__2();

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
    // $ANTLR end "rule__DoubleArrayExpression__Group__1"


    // $ANTLR start "rule__DoubleArrayExpression__Group__1__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3382:1: rule__DoubleArrayExpression__Group__1__Impl : ( ( rule__DoubleArrayExpression__ValuesAssignment_1 ) ) ;
    public final void rule__DoubleArrayExpression__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3386:1: ( ( ( rule__DoubleArrayExpression__ValuesAssignment_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3387:1: ( ( rule__DoubleArrayExpression__ValuesAssignment_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3387:1: ( ( rule__DoubleArrayExpression__ValuesAssignment_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3388:1: ( rule__DoubleArrayExpression__ValuesAssignment_1 )
            {
             before(grammarAccess.getDoubleArrayExpressionAccess().getValuesAssignment_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3389:1: ( rule__DoubleArrayExpression__ValuesAssignment_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3389:2: rule__DoubleArrayExpression__ValuesAssignment_1
            {
            pushFollow(FOLLOW_rule__DoubleArrayExpression__ValuesAssignment_1_in_rule__DoubleArrayExpression__Group__1__Impl6983);
            rule__DoubleArrayExpression__ValuesAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getDoubleArrayExpressionAccess().getValuesAssignment_1()); 

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
    // $ANTLR end "rule__DoubleArrayExpression__Group__1__Impl"


    // $ANTLR start "rule__DoubleArrayExpression__Group__2"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3399:1: rule__DoubleArrayExpression__Group__2 : rule__DoubleArrayExpression__Group__2__Impl rule__DoubleArrayExpression__Group__3 ;
    public final void rule__DoubleArrayExpression__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3403:1: ( rule__DoubleArrayExpression__Group__2__Impl rule__DoubleArrayExpression__Group__3 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3404:2: rule__DoubleArrayExpression__Group__2__Impl rule__DoubleArrayExpression__Group__3
            {
            pushFollow(FOLLOW_rule__DoubleArrayExpression__Group__2__Impl_in_rule__DoubleArrayExpression__Group__27013);
            rule__DoubleArrayExpression__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__DoubleArrayExpression__Group__3_in_rule__DoubleArrayExpression__Group__27016);
            rule__DoubleArrayExpression__Group__3();

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
    // $ANTLR end "rule__DoubleArrayExpression__Group__2"


    // $ANTLR start "rule__DoubleArrayExpression__Group__2__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3411:1: rule__DoubleArrayExpression__Group__2__Impl : ( ( rule__DoubleArrayExpression__Group_2__0 )* ) ;
    public final void rule__DoubleArrayExpression__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3415:1: ( ( ( rule__DoubleArrayExpression__Group_2__0 )* ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3416:1: ( ( rule__DoubleArrayExpression__Group_2__0 )* )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3416:1: ( ( rule__DoubleArrayExpression__Group_2__0 )* )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3417:1: ( rule__DoubleArrayExpression__Group_2__0 )*
            {
             before(grammarAccess.getDoubleArrayExpressionAccess().getGroup_2()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3418:1: ( rule__DoubleArrayExpression__Group_2__0 )*
            loop23:
            do {
                int alt23=2;
                int LA23_0 = input.LA(1);

                if ( (LA23_0==28) ) {
                    alt23=1;
                }


                switch (alt23) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3418:2: rule__DoubleArrayExpression__Group_2__0
            	    {
            	    pushFollow(FOLLOW_rule__DoubleArrayExpression__Group_2__0_in_rule__DoubleArrayExpression__Group__2__Impl7043);
            	    rule__DoubleArrayExpression__Group_2__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop23;
                }
            } while (true);

             after(grammarAccess.getDoubleArrayExpressionAccess().getGroup_2()); 

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
    // $ANTLR end "rule__DoubleArrayExpression__Group__2__Impl"


    // $ANTLR start "rule__DoubleArrayExpression__Group__3"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3428:1: rule__DoubleArrayExpression__Group__3 : rule__DoubleArrayExpression__Group__3__Impl ;
    public final void rule__DoubleArrayExpression__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3432:1: ( rule__DoubleArrayExpression__Group__3__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3433:2: rule__DoubleArrayExpression__Group__3__Impl
            {
            pushFollow(FOLLOW_rule__DoubleArrayExpression__Group__3__Impl_in_rule__DoubleArrayExpression__Group__37074);
            rule__DoubleArrayExpression__Group__3__Impl();

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
    // $ANTLR end "rule__DoubleArrayExpression__Group__3"


    // $ANTLR start "rule__DoubleArrayExpression__Group__3__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3439:1: rule__DoubleArrayExpression__Group__3__Impl : ( ']' ) ;
    public final void rule__DoubleArrayExpression__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3443:1: ( ( ']' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3444:1: ( ']' )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3444:1: ( ']' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3445:1: ']'
            {
             before(grammarAccess.getDoubleArrayExpressionAccess().getRightSquareBracketKeyword_3()); 
            match(input,36,FOLLOW_36_in_rule__DoubleArrayExpression__Group__3__Impl7102); 
             after(grammarAccess.getDoubleArrayExpressionAccess().getRightSquareBracketKeyword_3()); 

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
    // $ANTLR end "rule__DoubleArrayExpression__Group__3__Impl"


    // $ANTLR start "rule__DoubleArrayExpression__Group_2__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3466:1: rule__DoubleArrayExpression__Group_2__0 : rule__DoubleArrayExpression__Group_2__0__Impl rule__DoubleArrayExpression__Group_2__1 ;
    public final void rule__DoubleArrayExpression__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3470:1: ( rule__DoubleArrayExpression__Group_2__0__Impl rule__DoubleArrayExpression__Group_2__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3471:2: rule__DoubleArrayExpression__Group_2__0__Impl rule__DoubleArrayExpression__Group_2__1
            {
            pushFollow(FOLLOW_rule__DoubleArrayExpression__Group_2__0__Impl_in_rule__DoubleArrayExpression__Group_2__07141);
            rule__DoubleArrayExpression__Group_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__DoubleArrayExpression__Group_2__1_in_rule__DoubleArrayExpression__Group_2__07144);
            rule__DoubleArrayExpression__Group_2__1();

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
    // $ANTLR end "rule__DoubleArrayExpression__Group_2__0"


    // $ANTLR start "rule__DoubleArrayExpression__Group_2__0__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3478:1: rule__DoubleArrayExpression__Group_2__0__Impl : ( ',' ) ;
    public final void rule__DoubleArrayExpression__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3482:1: ( ( ',' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3483:1: ( ',' )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3483:1: ( ',' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3484:1: ','
            {
             before(grammarAccess.getDoubleArrayExpressionAccess().getCommaKeyword_2_0()); 
            match(input,28,FOLLOW_28_in_rule__DoubleArrayExpression__Group_2__0__Impl7172); 
             after(grammarAccess.getDoubleArrayExpressionAccess().getCommaKeyword_2_0()); 

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
    // $ANTLR end "rule__DoubleArrayExpression__Group_2__0__Impl"


    // $ANTLR start "rule__DoubleArrayExpression__Group_2__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3497:1: rule__DoubleArrayExpression__Group_2__1 : rule__DoubleArrayExpression__Group_2__1__Impl ;
    public final void rule__DoubleArrayExpression__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3501:1: ( rule__DoubleArrayExpression__Group_2__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3502:2: rule__DoubleArrayExpression__Group_2__1__Impl
            {
            pushFollow(FOLLOW_rule__DoubleArrayExpression__Group_2__1__Impl_in_rule__DoubleArrayExpression__Group_2__17203);
            rule__DoubleArrayExpression__Group_2__1__Impl();

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
    // $ANTLR end "rule__DoubleArrayExpression__Group_2__1"


    // $ANTLR start "rule__DoubleArrayExpression__Group_2__1__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3508:1: rule__DoubleArrayExpression__Group_2__1__Impl : ( ( rule__DoubleArrayExpression__ValuesAssignment_2_1 ) ) ;
    public final void rule__DoubleArrayExpression__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3512:1: ( ( ( rule__DoubleArrayExpression__ValuesAssignment_2_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3513:1: ( ( rule__DoubleArrayExpression__ValuesAssignment_2_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3513:1: ( ( rule__DoubleArrayExpression__ValuesAssignment_2_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3514:1: ( rule__DoubleArrayExpression__ValuesAssignment_2_1 )
            {
             before(grammarAccess.getDoubleArrayExpressionAccess().getValuesAssignment_2_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3515:1: ( rule__DoubleArrayExpression__ValuesAssignment_2_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3515:2: rule__DoubleArrayExpression__ValuesAssignment_2_1
            {
            pushFollow(FOLLOW_rule__DoubleArrayExpression__ValuesAssignment_2_1_in_rule__DoubleArrayExpression__Group_2__1__Impl7230);
            rule__DoubleArrayExpression__ValuesAssignment_2_1();

            state._fsp--;


            }

             after(grammarAccess.getDoubleArrayExpressionAccess().getValuesAssignment_2_1()); 

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
    // $ANTLR end "rule__DoubleArrayExpression__Group_2__1__Impl"


    // $ANTLR start "rule__LongArrayExpression__Group__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3529:1: rule__LongArrayExpression__Group__0 : rule__LongArrayExpression__Group__0__Impl rule__LongArrayExpression__Group__1 ;
    public final void rule__LongArrayExpression__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3533:1: ( rule__LongArrayExpression__Group__0__Impl rule__LongArrayExpression__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3534:2: rule__LongArrayExpression__Group__0__Impl rule__LongArrayExpression__Group__1
            {
            pushFollow(FOLLOW_rule__LongArrayExpression__Group__0__Impl_in_rule__LongArrayExpression__Group__07264);
            rule__LongArrayExpression__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__LongArrayExpression__Group__1_in_rule__LongArrayExpression__Group__07267);
            rule__LongArrayExpression__Group__1();

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
    // $ANTLR end "rule__LongArrayExpression__Group__0"


    // $ANTLR start "rule__LongArrayExpression__Group__0__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3541:1: rule__LongArrayExpression__Group__0__Impl : ( '[' ) ;
    public final void rule__LongArrayExpression__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3545:1: ( ( '[' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3546:1: ( '[' )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3546:1: ( '[' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3547:1: '['
            {
             before(grammarAccess.getLongArrayExpressionAccess().getLeftSquareBracketKeyword_0()); 
            match(input,35,FOLLOW_35_in_rule__LongArrayExpression__Group__0__Impl7295); 
             after(grammarAccess.getLongArrayExpressionAccess().getLeftSquareBracketKeyword_0()); 

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
    // $ANTLR end "rule__LongArrayExpression__Group__0__Impl"


    // $ANTLR start "rule__LongArrayExpression__Group__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3560:1: rule__LongArrayExpression__Group__1 : rule__LongArrayExpression__Group__1__Impl rule__LongArrayExpression__Group__2 ;
    public final void rule__LongArrayExpression__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3564:1: ( rule__LongArrayExpression__Group__1__Impl rule__LongArrayExpression__Group__2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3565:2: rule__LongArrayExpression__Group__1__Impl rule__LongArrayExpression__Group__2
            {
            pushFollow(FOLLOW_rule__LongArrayExpression__Group__1__Impl_in_rule__LongArrayExpression__Group__17326);
            rule__LongArrayExpression__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__LongArrayExpression__Group__2_in_rule__LongArrayExpression__Group__17329);
            rule__LongArrayExpression__Group__2();

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
    // $ANTLR end "rule__LongArrayExpression__Group__1"


    // $ANTLR start "rule__LongArrayExpression__Group__1__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3572:1: rule__LongArrayExpression__Group__1__Impl : ( ( rule__LongArrayExpression__ValuesAssignment_1 ) ) ;
    public final void rule__LongArrayExpression__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3576:1: ( ( ( rule__LongArrayExpression__ValuesAssignment_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3577:1: ( ( rule__LongArrayExpression__ValuesAssignment_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3577:1: ( ( rule__LongArrayExpression__ValuesAssignment_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3578:1: ( rule__LongArrayExpression__ValuesAssignment_1 )
            {
             before(grammarAccess.getLongArrayExpressionAccess().getValuesAssignment_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3579:1: ( rule__LongArrayExpression__ValuesAssignment_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3579:2: rule__LongArrayExpression__ValuesAssignment_1
            {
            pushFollow(FOLLOW_rule__LongArrayExpression__ValuesAssignment_1_in_rule__LongArrayExpression__Group__1__Impl7356);
            rule__LongArrayExpression__ValuesAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getLongArrayExpressionAccess().getValuesAssignment_1()); 

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
    // $ANTLR end "rule__LongArrayExpression__Group__1__Impl"


    // $ANTLR start "rule__LongArrayExpression__Group__2"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3589:1: rule__LongArrayExpression__Group__2 : rule__LongArrayExpression__Group__2__Impl rule__LongArrayExpression__Group__3 ;
    public final void rule__LongArrayExpression__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3593:1: ( rule__LongArrayExpression__Group__2__Impl rule__LongArrayExpression__Group__3 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3594:2: rule__LongArrayExpression__Group__2__Impl rule__LongArrayExpression__Group__3
            {
            pushFollow(FOLLOW_rule__LongArrayExpression__Group__2__Impl_in_rule__LongArrayExpression__Group__27386);
            rule__LongArrayExpression__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__LongArrayExpression__Group__3_in_rule__LongArrayExpression__Group__27389);
            rule__LongArrayExpression__Group__3();

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
    // $ANTLR end "rule__LongArrayExpression__Group__2"


    // $ANTLR start "rule__LongArrayExpression__Group__2__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3601:1: rule__LongArrayExpression__Group__2__Impl : ( ( rule__LongArrayExpression__Group_2__0 )* ) ;
    public final void rule__LongArrayExpression__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3605:1: ( ( ( rule__LongArrayExpression__Group_2__0 )* ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3606:1: ( ( rule__LongArrayExpression__Group_2__0 )* )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3606:1: ( ( rule__LongArrayExpression__Group_2__0 )* )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3607:1: ( rule__LongArrayExpression__Group_2__0 )*
            {
             before(grammarAccess.getLongArrayExpressionAccess().getGroup_2()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3608:1: ( rule__LongArrayExpression__Group_2__0 )*
            loop24:
            do {
                int alt24=2;
                int LA24_0 = input.LA(1);

                if ( (LA24_0==28) ) {
                    alt24=1;
                }


                switch (alt24) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3608:2: rule__LongArrayExpression__Group_2__0
            	    {
            	    pushFollow(FOLLOW_rule__LongArrayExpression__Group_2__0_in_rule__LongArrayExpression__Group__2__Impl7416);
            	    rule__LongArrayExpression__Group_2__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop24;
                }
            } while (true);

             after(grammarAccess.getLongArrayExpressionAccess().getGroup_2()); 

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
    // $ANTLR end "rule__LongArrayExpression__Group__2__Impl"


    // $ANTLR start "rule__LongArrayExpression__Group__3"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3618:1: rule__LongArrayExpression__Group__3 : rule__LongArrayExpression__Group__3__Impl ;
    public final void rule__LongArrayExpression__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3622:1: ( rule__LongArrayExpression__Group__3__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3623:2: rule__LongArrayExpression__Group__3__Impl
            {
            pushFollow(FOLLOW_rule__LongArrayExpression__Group__3__Impl_in_rule__LongArrayExpression__Group__37447);
            rule__LongArrayExpression__Group__3__Impl();

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
    // $ANTLR end "rule__LongArrayExpression__Group__3"


    // $ANTLR start "rule__LongArrayExpression__Group__3__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3629:1: rule__LongArrayExpression__Group__3__Impl : ( ']' ) ;
    public final void rule__LongArrayExpression__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3633:1: ( ( ']' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3634:1: ( ']' )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3634:1: ( ']' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3635:1: ']'
            {
             before(grammarAccess.getLongArrayExpressionAccess().getRightSquareBracketKeyword_3()); 
            match(input,36,FOLLOW_36_in_rule__LongArrayExpression__Group__3__Impl7475); 
             after(grammarAccess.getLongArrayExpressionAccess().getRightSquareBracketKeyword_3()); 

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
    // $ANTLR end "rule__LongArrayExpression__Group__3__Impl"


    // $ANTLR start "rule__LongArrayExpression__Group_2__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3656:1: rule__LongArrayExpression__Group_2__0 : rule__LongArrayExpression__Group_2__0__Impl rule__LongArrayExpression__Group_2__1 ;
    public final void rule__LongArrayExpression__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3660:1: ( rule__LongArrayExpression__Group_2__0__Impl rule__LongArrayExpression__Group_2__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3661:2: rule__LongArrayExpression__Group_2__0__Impl rule__LongArrayExpression__Group_2__1
            {
            pushFollow(FOLLOW_rule__LongArrayExpression__Group_2__0__Impl_in_rule__LongArrayExpression__Group_2__07514);
            rule__LongArrayExpression__Group_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__LongArrayExpression__Group_2__1_in_rule__LongArrayExpression__Group_2__07517);
            rule__LongArrayExpression__Group_2__1();

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
    // $ANTLR end "rule__LongArrayExpression__Group_2__0"


    // $ANTLR start "rule__LongArrayExpression__Group_2__0__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3668:1: rule__LongArrayExpression__Group_2__0__Impl : ( ',' ) ;
    public final void rule__LongArrayExpression__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3672:1: ( ( ',' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3673:1: ( ',' )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3673:1: ( ',' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3674:1: ','
            {
             before(grammarAccess.getLongArrayExpressionAccess().getCommaKeyword_2_0()); 
            match(input,28,FOLLOW_28_in_rule__LongArrayExpression__Group_2__0__Impl7545); 
             after(grammarAccess.getLongArrayExpressionAccess().getCommaKeyword_2_0()); 

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
    // $ANTLR end "rule__LongArrayExpression__Group_2__0__Impl"


    // $ANTLR start "rule__LongArrayExpression__Group_2__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3687:1: rule__LongArrayExpression__Group_2__1 : rule__LongArrayExpression__Group_2__1__Impl ;
    public final void rule__LongArrayExpression__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3691:1: ( rule__LongArrayExpression__Group_2__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3692:2: rule__LongArrayExpression__Group_2__1__Impl
            {
            pushFollow(FOLLOW_rule__LongArrayExpression__Group_2__1__Impl_in_rule__LongArrayExpression__Group_2__17576);
            rule__LongArrayExpression__Group_2__1__Impl();

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
    // $ANTLR end "rule__LongArrayExpression__Group_2__1"


    // $ANTLR start "rule__LongArrayExpression__Group_2__1__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3698:1: rule__LongArrayExpression__Group_2__1__Impl : ( ( rule__LongArrayExpression__ValuesAssignment_2_1 ) ) ;
    public final void rule__LongArrayExpression__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3702:1: ( ( ( rule__LongArrayExpression__ValuesAssignment_2_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3703:1: ( ( rule__LongArrayExpression__ValuesAssignment_2_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3703:1: ( ( rule__LongArrayExpression__ValuesAssignment_2_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3704:1: ( rule__LongArrayExpression__ValuesAssignment_2_1 )
            {
             before(grammarAccess.getLongArrayExpressionAccess().getValuesAssignment_2_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3705:1: ( rule__LongArrayExpression__ValuesAssignment_2_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3705:2: rule__LongArrayExpression__ValuesAssignment_2_1
            {
            pushFollow(FOLLOW_rule__LongArrayExpression__ValuesAssignment_2_1_in_rule__LongArrayExpression__Group_2__1__Impl7603);
            rule__LongArrayExpression__ValuesAssignment_2_1();

            state._fsp--;


            }

             after(grammarAccess.getLongArrayExpressionAccess().getValuesAssignment_2_1()); 

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
    // $ANTLR end "rule__LongArrayExpression__Group_2__1__Impl"


    // $ANTLR start "rule__StringArrayExpression__Group__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3719:1: rule__StringArrayExpression__Group__0 : rule__StringArrayExpression__Group__0__Impl rule__StringArrayExpression__Group__1 ;
    public final void rule__StringArrayExpression__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3723:1: ( rule__StringArrayExpression__Group__0__Impl rule__StringArrayExpression__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3724:2: rule__StringArrayExpression__Group__0__Impl rule__StringArrayExpression__Group__1
            {
            pushFollow(FOLLOW_rule__StringArrayExpression__Group__0__Impl_in_rule__StringArrayExpression__Group__07637);
            rule__StringArrayExpression__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__StringArrayExpression__Group__1_in_rule__StringArrayExpression__Group__07640);
            rule__StringArrayExpression__Group__1();

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
    // $ANTLR end "rule__StringArrayExpression__Group__0"


    // $ANTLR start "rule__StringArrayExpression__Group__0__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3731:1: rule__StringArrayExpression__Group__0__Impl : ( '[' ) ;
    public final void rule__StringArrayExpression__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3735:1: ( ( '[' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3736:1: ( '[' )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3736:1: ( '[' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3737:1: '['
            {
             before(grammarAccess.getStringArrayExpressionAccess().getLeftSquareBracketKeyword_0()); 
            match(input,35,FOLLOW_35_in_rule__StringArrayExpression__Group__0__Impl7668); 
             after(grammarAccess.getStringArrayExpressionAccess().getLeftSquareBracketKeyword_0()); 

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
    // $ANTLR end "rule__StringArrayExpression__Group__0__Impl"


    // $ANTLR start "rule__StringArrayExpression__Group__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3750:1: rule__StringArrayExpression__Group__1 : rule__StringArrayExpression__Group__1__Impl rule__StringArrayExpression__Group__2 ;
    public final void rule__StringArrayExpression__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3754:1: ( rule__StringArrayExpression__Group__1__Impl rule__StringArrayExpression__Group__2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3755:2: rule__StringArrayExpression__Group__1__Impl rule__StringArrayExpression__Group__2
            {
            pushFollow(FOLLOW_rule__StringArrayExpression__Group__1__Impl_in_rule__StringArrayExpression__Group__17699);
            rule__StringArrayExpression__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__StringArrayExpression__Group__2_in_rule__StringArrayExpression__Group__17702);
            rule__StringArrayExpression__Group__2();

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
    // $ANTLR end "rule__StringArrayExpression__Group__1"


    // $ANTLR start "rule__StringArrayExpression__Group__1__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3762:1: rule__StringArrayExpression__Group__1__Impl : ( ( rule__StringArrayExpression__ValuesAssignment_1 ) ) ;
    public final void rule__StringArrayExpression__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3766:1: ( ( ( rule__StringArrayExpression__ValuesAssignment_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3767:1: ( ( rule__StringArrayExpression__ValuesAssignment_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3767:1: ( ( rule__StringArrayExpression__ValuesAssignment_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3768:1: ( rule__StringArrayExpression__ValuesAssignment_1 )
            {
             before(grammarAccess.getStringArrayExpressionAccess().getValuesAssignment_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3769:1: ( rule__StringArrayExpression__ValuesAssignment_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3769:2: rule__StringArrayExpression__ValuesAssignment_1
            {
            pushFollow(FOLLOW_rule__StringArrayExpression__ValuesAssignment_1_in_rule__StringArrayExpression__Group__1__Impl7729);
            rule__StringArrayExpression__ValuesAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getStringArrayExpressionAccess().getValuesAssignment_1()); 

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
    // $ANTLR end "rule__StringArrayExpression__Group__1__Impl"


    // $ANTLR start "rule__StringArrayExpression__Group__2"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3779:1: rule__StringArrayExpression__Group__2 : rule__StringArrayExpression__Group__2__Impl rule__StringArrayExpression__Group__3 ;
    public final void rule__StringArrayExpression__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3783:1: ( rule__StringArrayExpression__Group__2__Impl rule__StringArrayExpression__Group__3 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3784:2: rule__StringArrayExpression__Group__2__Impl rule__StringArrayExpression__Group__3
            {
            pushFollow(FOLLOW_rule__StringArrayExpression__Group__2__Impl_in_rule__StringArrayExpression__Group__27759);
            rule__StringArrayExpression__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__StringArrayExpression__Group__3_in_rule__StringArrayExpression__Group__27762);
            rule__StringArrayExpression__Group__3();

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
    // $ANTLR end "rule__StringArrayExpression__Group__2"


    // $ANTLR start "rule__StringArrayExpression__Group__2__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3791:1: rule__StringArrayExpression__Group__2__Impl : ( ( rule__StringArrayExpression__Group_2__0 )* ) ;
    public final void rule__StringArrayExpression__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3795:1: ( ( ( rule__StringArrayExpression__Group_2__0 )* ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3796:1: ( ( rule__StringArrayExpression__Group_2__0 )* )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3796:1: ( ( rule__StringArrayExpression__Group_2__0 )* )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3797:1: ( rule__StringArrayExpression__Group_2__0 )*
            {
             before(grammarAccess.getStringArrayExpressionAccess().getGroup_2()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3798:1: ( rule__StringArrayExpression__Group_2__0 )*
            loop25:
            do {
                int alt25=2;
                int LA25_0 = input.LA(1);

                if ( (LA25_0==28) ) {
                    alt25=1;
                }


                switch (alt25) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3798:2: rule__StringArrayExpression__Group_2__0
            	    {
            	    pushFollow(FOLLOW_rule__StringArrayExpression__Group_2__0_in_rule__StringArrayExpression__Group__2__Impl7789);
            	    rule__StringArrayExpression__Group_2__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop25;
                }
            } while (true);

             after(grammarAccess.getStringArrayExpressionAccess().getGroup_2()); 

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
    // $ANTLR end "rule__StringArrayExpression__Group__2__Impl"


    // $ANTLR start "rule__StringArrayExpression__Group__3"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3808:1: rule__StringArrayExpression__Group__3 : rule__StringArrayExpression__Group__3__Impl ;
    public final void rule__StringArrayExpression__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3812:1: ( rule__StringArrayExpression__Group__3__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3813:2: rule__StringArrayExpression__Group__3__Impl
            {
            pushFollow(FOLLOW_rule__StringArrayExpression__Group__3__Impl_in_rule__StringArrayExpression__Group__37820);
            rule__StringArrayExpression__Group__3__Impl();

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
    // $ANTLR end "rule__StringArrayExpression__Group__3"


    // $ANTLR start "rule__StringArrayExpression__Group__3__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3819:1: rule__StringArrayExpression__Group__3__Impl : ( ']' ) ;
    public final void rule__StringArrayExpression__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3823:1: ( ( ']' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3824:1: ( ']' )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3824:1: ( ']' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3825:1: ']'
            {
             before(grammarAccess.getStringArrayExpressionAccess().getRightSquareBracketKeyword_3()); 
            match(input,36,FOLLOW_36_in_rule__StringArrayExpression__Group__3__Impl7848); 
             after(grammarAccess.getStringArrayExpressionAccess().getRightSquareBracketKeyword_3()); 

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
    // $ANTLR end "rule__StringArrayExpression__Group__3__Impl"


    // $ANTLR start "rule__StringArrayExpression__Group_2__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3846:1: rule__StringArrayExpression__Group_2__0 : rule__StringArrayExpression__Group_2__0__Impl rule__StringArrayExpression__Group_2__1 ;
    public final void rule__StringArrayExpression__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3850:1: ( rule__StringArrayExpression__Group_2__0__Impl rule__StringArrayExpression__Group_2__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3851:2: rule__StringArrayExpression__Group_2__0__Impl rule__StringArrayExpression__Group_2__1
            {
            pushFollow(FOLLOW_rule__StringArrayExpression__Group_2__0__Impl_in_rule__StringArrayExpression__Group_2__07887);
            rule__StringArrayExpression__Group_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__StringArrayExpression__Group_2__1_in_rule__StringArrayExpression__Group_2__07890);
            rule__StringArrayExpression__Group_2__1();

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
    // $ANTLR end "rule__StringArrayExpression__Group_2__0"


    // $ANTLR start "rule__StringArrayExpression__Group_2__0__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3858:1: rule__StringArrayExpression__Group_2__0__Impl : ( ',' ) ;
    public final void rule__StringArrayExpression__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3862:1: ( ( ',' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3863:1: ( ',' )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3863:1: ( ',' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3864:1: ','
            {
             before(grammarAccess.getStringArrayExpressionAccess().getCommaKeyword_2_0()); 
            match(input,28,FOLLOW_28_in_rule__StringArrayExpression__Group_2__0__Impl7918); 
             after(grammarAccess.getStringArrayExpressionAccess().getCommaKeyword_2_0()); 

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
    // $ANTLR end "rule__StringArrayExpression__Group_2__0__Impl"


    // $ANTLR start "rule__StringArrayExpression__Group_2__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3877:1: rule__StringArrayExpression__Group_2__1 : rule__StringArrayExpression__Group_2__1__Impl ;
    public final void rule__StringArrayExpression__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3881:1: ( rule__StringArrayExpression__Group_2__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3882:2: rule__StringArrayExpression__Group_2__1__Impl
            {
            pushFollow(FOLLOW_rule__StringArrayExpression__Group_2__1__Impl_in_rule__StringArrayExpression__Group_2__17949);
            rule__StringArrayExpression__Group_2__1__Impl();

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
    // $ANTLR end "rule__StringArrayExpression__Group_2__1"


    // $ANTLR start "rule__StringArrayExpression__Group_2__1__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3888:1: rule__StringArrayExpression__Group_2__1__Impl : ( ( rule__StringArrayExpression__ValuesAssignment_2_1 ) ) ;
    public final void rule__StringArrayExpression__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3892:1: ( ( ( rule__StringArrayExpression__ValuesAssignment_2_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3893:1: ( ( rule__StringArrayExpression__ValuesAssignment_2_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3893:1: ( ( rule__StringArrayExpression__ValuesAssignment_2_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3894:1: ( rule__StringArrayExpression__ValuesAssignment_2_1 )
            {
             before(grammarAccess.getStringArrayExpressionAccess().getValuesAssignment_2_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3895:1: ( rule__StringArrayExpression__ValuesAssignment_2_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3895:2: rule__StringArrayExpression__ValuesAssignment_2_1
            {
            pushFollow(FOLLOW_rule__StringArrayExpression__ValuesAssignment_2_1_in_rule__StringArrayExpression__Group_2__1__Impl7976);
            rule__StringArrayExpression__ValuesAssignment_2_1();

            state._fsp--;


            }

             after(grammarAccess.getStringArrayExpressionAccess().getValuesAssignment_2_1()); 

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
    // $ANTLR end "rule__StringArrayExpression__Group_2__1__Impl"


    // $ANTLR start "rule__NullArrayExpression__Group__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3909:1: rule__NullArrayExpression__Group__0 : rule__NullArrayExpression__Group__0__Impl rule__NullArrayExpression__Group__1 ;
    public final void rule__NullArrayExpression__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3913:1: ( rule__NullArrayExpression__Group__0__Impl rule__NullArrayExpression__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3914:2: rule__NullArrayExpression__Group__0__Impl rule__NullArrayExpression__Group__1
            {
            pushFollow(FOLLOW_rule__NullArrayExpression__Group__0__Impl_in_rule__NullArrayExpression__Group__08010);
            rule__NullArrayExpression__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__NullArrayExpression__Group__1_in_rule__NullArrayExpression__Group__08013);
            rule__NullArrayExpression__Group__1();

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
    // $ANTLR end "rule__NullArrayExpression__Group__0"


    // $ANTLR start "rule__NullArrayExpression__Group__0__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3921:1: rule__NullArrayExpression__Group__0__Impl : ( '[' ) ;
    public final void rule__NullArrayExpression__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3925:1: ( ( '[' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3926:1: ( '[' )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3926:1: ( '[' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3927:1: '['
            {
             before(grammarAccess.getNullArrayExpressionAccess().getLeftSquareBracketKeyword_0()); 
            match(input,35,FOLLOW_35_in_rule__NullArrayExpression__Group__0__Impl8041); 
             after(grammarAccess.getNullArrayExpressionAccess().getLeftSquareBracketKeyword_0()); 

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
    // $ANTLR end "rule__NullArrayExpression__Group__0__Impl"


    // $ANTLR start "rule__NullArrayExpression__Group__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3940:1: rule__NullArrayExpression__Group__1 : rule__NullArrayExpression__Group__1__Impl rule__NullArrayExpression__Group__2 ;
    public final void rule__NullArrayExpression__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3944:1: ( rule__NullArrayExpression__Group__1__Impl rule__NullArrayExpression__Group__2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3945:2: rule__NullArrayExpression__Group__1__Impl rule__NullArrayExpression__Group__2
            {
            pushFollow(FOLLOW_rule__NullArrayExpression__Group__1__Impl_in_rule__NullArrayExpression__Group__18072);
            rule__NullArrayExpression__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__NullArrayExpression__Group__2_in_rule__NullArrayExpression__Group__18075);
            rule__NullArrayExpression__Group__2();

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
    // $ANTLR end "rule__NullArrayExpression__Group__1"


    // $ANTLR start "rule__NullArrayExpression__Group__1__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3952:1: rule__NullArrayExpression__Group__1__Impl : ( ( rule__NullArrayExpression__ValuesAssignment_1 ) ) ;
    public final void rule__NullArrayExpression__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3956:1: ( ( ( rule__NullArrayExpression__ValuesAssignment_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3957:1: ( ( rule__NullArrayExpression__ValuesAssignment_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3957:1: ( ( rule__NullArrayExpression__ValuesAssignment_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3958:1: ( rule__NullArrayExpression__ValuesAssignment_1 )
            {
             before(grammarAccess.getNullArrayExpressionAccess().getValuesAssignment_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3959:1: ( rule__NullArrayExpression__ValuesAssignment_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3959:2: rule__NullArrayExpression__ValuesAssignment_1
            {
            pushFollow(FOLLOW_rule__NullArrayExpression__ValuesAssignment_1_in_rule__NullArrayExpression__Group__1__Impl8102);
            rule__NullArrayExpression__ValuesAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getNullArrayExpressionAccess().getValuesAssignment_1()); 

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
    // $ANTLR end "rule__NullArrayExpression__Group__1__Impl"


    // $ANTLR start "rule__NullArrayExpression__Group__2"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3969:1: rule__NullArrayExpression__Group__2 : rule__NullArrayExpression__Group__2__Impl rule__NullArrayExpression__Group__3 ;
    public final void rule__NullArrayExpression__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3973:1: ( rule__NullArrayExpression__Group__2__Impl rule__NullArrayExpression__Group__3 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3974:2: rule__NullArrayExpression__Group__2__Impl rule__NullArrayExpression__Group__3
            {
            pushFollow(FOLLOW_rule__NullArrayExpression__Group__2__Impl_in_rule__NullArrayExpression__Group__28132);
            rule__NullArrayExpression__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__NullArrayExpression__Group__3_in_rule__NullArrayExpression__Group__28135);
            rule__NullArrayExpression__Group__3();

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
    // $ANTLR end "rule__NullArrayExpression__Group__2"


    // $ANTLR start "rule__NullArrayExpression__Group__2__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3981:1: rule__NullArrayExpression__Group__2__Impl : ( ( rule__NullArrayExpression__Group_2__0 )* ) ;
    public final void rule__NullArrayExpression__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3985:1: ( ( ( rule__NullArrayExpression__Group_2__0 )* ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3986:1: ( ( rule__NullArrayExpression__Group_2__0 )* )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3986:1: ( ( rule__NullArrayExpression__Group_2__0 )* )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3987:1: ( rule__NullArrayExpression__Group_2__0 )*
            {
             before(grammarAccess.getNullArrayExpressionAccess().getGroup_2()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3988:1: ( rule__NullArrayExpression__Group_2__0 )*
            loop26:
            do {
                int alt26=2;
                int LA26_0 = input.LA(1);

                if ( (LA26_0==28) ) {
                    alt26=1;
                }


                switch (alt26) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3988:2: rule__NullArrayExpression__Group_2__0
            	    {
            	    pushFollow(FOLLOW_rule__NullArrayExpression__Group_2__0_in_rule__NullArrayExpression__Group__2__Impl8162);
            	    rule__NullArrayExpression__Group_2__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop26;
                }
            } while (true);

             after(grammarAccess.getNullArrayExpressionAccess().getGroup_2()); 

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
    // $ANTLR end "rule__NullArrayExpression__Group__2__Impl"


    // $ANTLR start "rule__NullArrayExpression__Group__3"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3998:1: rule__NullArrayExpression__Group__3 : rule__NullArrayExpression__Group__3__Impl ;
    public final void rule__NullArrayExpression__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4002:1: ( rule__NullArrayExpression__Group__3__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4003:2: rule__NullArrayExpression__Group__3__Impl
            {
            pushFollow(FOLLOW_rule__NullArrayExpression__Group__3__Impl_in_rule__NullArrayExpression__Group__38193);
            rule__NullArrayExpression__Group__3__Impl();

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
    // $ANTLR end "rule__NullArrayExpression__Group__3"


    // $ANTLR start "rule__NullArrayExpression__Group__3__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4009:1: rule__NullArrayExpression__Group__3__Impl : ( ']' ) ;
    public final void rule__NullArrayExpression__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4013:1: ( ( ']' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4014:1: ( ']' )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4014:1: ( ']' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4015:1: ']'
            {
             before(grammarAccess.getNullArrayExpressionAccess().getRightSquareBracketKeyword_3()); 
            match(input,36,FOLLOW_36_in_rule__NullArrayExpression__Group__3__Impl8221); 
             after(grammarAccess.getNullArrayExpressionAccess().getRightSquareBracketKeyword_3()); 

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
    // $ANTLR end "rule__NullArrayExpression__Group__3__Impl"


    // $ANTLR start "rule__NullArrayExpression__Group_2__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4036:1: rule__NullArrayExpression__Group_2__0 : rule__NullArrayExpression__Group_2__0__Impl rule__NullArrayExpression__Group_2__1 ;
    public final void rule__NullArrayExpression__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4040:1: ( rule__NullArrayExpression__Group_2__0__Impl rule__NullArrayExpression__Group_2__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4041:2: rule__NullArrayExpression__Group_2__0__Impl rule__NullArrayExpression__Group_2__1
            {
            pushFollow(FOLLOW_rule__NullArrayExpression__Group_2__0__Impl_in_rule__NullArrayExpression__Group_2__08260);
            rule__NullArrayExpression__Group_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__NullArrayExpression__Group_2__1_in_rule__NullArrayExpression__Group_2__08263);
            rule__NullArrayExpression__Group_2__1();

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
    // $ANTLR end "rule__NullArrayExpression__Group_2__0"


    // $ANTLR start "rule__NullArrayExpression__Group_2__0__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4048:1: rule__NullArrayExpression__Group_2__0__Impl : ( ',' ) ;
    public final void rule__NullArrayExpression__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4052:1: ( ( ',' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4053:1: ( ',' )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4053:1: ( ',' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4054:1: ','
            {
             before(grammarAccess.getNullArrayExpressionAccess().getCommaKeyword_2_0()); 
            match(input,28,FOLLOW_28_in_rule__NullArrayExpression__Group_2__0__Impl8291); 
             after(grammarAccess.getNullArrayExpressionAccess().getCommaKeyword_2_0()); 

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
    // $ANTLR end "rule__NullArrayExpression__Group_2__0__Impl"


    // $ANTLR start "rule__NullArrayExpression__Group_2__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4067:1: rule__NullArrayExpression__Group_2__1 : rule__NullArrayExpression__Group_2__1__Impl ;
    public final void rule__NullArrayExpression__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4071:1: ( rule__NullArrayExpression__Group_2__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4072:2: rule__NullArrayExpression__Group_2__1__Impl
            {
            pushFollow(FOLLOW_rule__NullArrayExpression__Group_2__1__Impl_in_rule__NullArrayExpression__Group_2__18322);
            rule__NullArrayExpression__Group_2__1__Impl();

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
    // $ANTLR end "rule__NullArrayExpression__Group_2__1"


    // $ANTLR start "rule__NullArrayExpression__Group_2__1__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4078:1: rule__NullArrayExpression__Group_2__1__Impl : ( ( rule__NullArrayExpression__ValuesAssignment_2_1 ) ) ;
    public final void rule__NullArrayExpression__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4082:1: ( ( ( rule__NullArrayExpression__ValuesAssignment_2_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4083:1: ( ( rule__NullArrayExpression__ValuesAssignment_2_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4083:1: ( ( rule__NullArrayExpression__ValuesAssignment_2_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4084:1: ( rule__NullArrayExpression__ValuesAssignment_2_1 )
            {
             before(grammarAccess.getNullArrayExpressionAccess().getValuesAssignment_2_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4085:1: ( rule__NullArrayExpression__ValuesAssignment_2_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4085:2: rule__NullArrayExpression__ValuesAssignment_2_1
            {
            pushFollow(FOLLOW_rule__NullArrayExpression__ValuesAssignment_2_1_in_rule__NullArrayExpression__Group_2__1__Impl8349);
            rule__NullArrayExpression__ValuesAssignment_2_1();

            state._fsp--;


            }

             after(grammarAccess.getNullArrayExpressionAccess().getValuesAssignment_2_1()); 

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
    // $ANTLR end "rule__NullArrayExpression__Group_2__1__Impl"


    // $ANTLR start "rule__DateArrayExpression__Group__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4099:1: rule__DateArrayExpression__Group__0 : rule__DateArrayExpression__Group__0__Impl rule__DateArrayExpression__Group__1 ;
    public final void rule__DateArrayExpression__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4103:1: ( rule__DateArrayExpression__Group__0__Impl rule__DateArrayExpression__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4104:2: rule__DateArrayExpression__Group__0__Impl rule__DateArrayExpression__Group__1
            {
            pushFollow(FOLLOW_rule__DateArrayExpression__Group__0__Impl_in_rule__DateArrayExpression__Group__08383);
            rule__DateArrayExpression__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__DateArrayExpression__Group__1_in_rule__DateArrayExpression__Group__08386);
            rule__DateArrayExpression__Group__1();

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
    // $ANTLR end "rule__DateArrayExpression__Group__0"


    // $ANTLR start "rule__DateArrayExpression__Group__0__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4111:1: rule__DateArrayExpression__Group__0__Impl : ( '[' ) ;
    public final void rule__DateArrayExpression__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4115:1: ( ( '[' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4116:1: ( '[' )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4116:1: ( '[' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4117:1: '['
            {
             before(grammarAccess.getDateArrayExpressionAccess().getLeftSquareBracketKeyword_0()); 
            match(input,35,FOLLOW_35_in_rule__DateArrayExpression__Group__0__Impl8414); 
             after(grammarAccess.getDateArrayExpressionAccess().getLeftSquareBracketKeyword_0()); 

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
    // $ANTLR end "rule__DateArrayExpression__Group__0__Impl"


    // $ANTLR start "rule__DateArrayExpression__Group__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4130:1: rule__DateArrayExpression__Group__1 : rule__DateArrayExpression__Group__1__Impl rule__DateArrayExpression__Group__2 ;
    public final void rule__DateArrayExpression__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4134:1: ( rule__DateArrayExpression__Group__1__Impl rule__DateArrayExpression__Group__2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4135:2: rule__DateArrayExpression__Group__1__Impl rule__DateArrayExpression__Group__2
            {
            pushFollow(FOLLOW_rule__DateArrayExpression__Group__1__Impl_in_rule__DateArrayExpression__Group__18445);
            rule__DateArrayExpression__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__DateArrayExpression__Group__2_in_rule__DateArrayExpression__Group__18448);
            rule__DateArrayExpression__Group__2();

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
    // $ANTLR end "rule__DateArrayExpression__Group__1"


    // $ANTLR start "rule__DateArrayExpression__Group__1__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4142:1: rule__DateArrayExpression__Group__1__Impl : ( ( rule__DateArrayExpression__ValuesAssignment_1 ) ) ;
    public final void rule__DateArrayExpression__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4146:1: ( ( ( rule__DateArrayExpression__ValuesAssignment_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4147:1: ( ( rule__DateArrayExpression__ValuesAssignment_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4147:1: ( ( rule__DateArrayExpression__ValuesAssignment_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4148:1: ( rule__DateArrayExpression__ValuesAssignment_1 )
            {
             before(grammarAccess.getDateArrayExpressionAccess().getValuesAssignment_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4149:1: ( rule__DateArrayExpression__ValuesAssignment_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4149:2: rule__DateArrayExpression__ValuesAssignment_1
            {
            pushFollow(FOLLOW_rule__DateArrayExpression__ValuesAssignment_1_in_rule__DateArrayExpression__Group__1__Impl8475);
            rule__DateArrayExpression__ValuesAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getDateArrayExpressionAccess().getValuesAssignment_1()); 

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
    // $ANTLR end "rule__DateArrayExpression__Group__1__Impl"


    // $ANTLR start "rule__DateArrayExpression__Group__2"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4159:1: rule__DateArrayExpression__Group__2 : rule__DateArrayExpression__Group__2__Impl rule__DateArrayExpression__Group__3 ;
    public final void rule__DateArrayExpression__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4163:1: ( rule__DateArrayExpression__Group__2__Impl rule__DateArrayExpression__Group__3 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4164:2: rule__DateArrayExpression__Group__2__Impl rule__DateArrayExpression__Group__3
            {
            pushFollow(FOLLOW_rule__DateArrayExpression__Group__2__Impl_in_rule__DateArrayExpression__Group__28505);
            rule__DateArrayExpression__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__DateArrayExpression__Group__3_in_rule__DateArrayExpression__Group__28508);
            rule__DateArrayExpression__Group__3();

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
    // $ANTLR end "rule__DateArrayExpression__Group__2"


    // $ANTLR start "rule__DateArrayExpression__Group__2__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4171:1: rule__DateArrayExpression__Group__2__Impl : ( ( rule__DateArrayExpression__Group_2__0 )* ) ;
    public final void rule__DateArrayExpression__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4175:1: ( ( ( rule__DateArrayExpression__Group_2__0 )* ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4176:1: ( ( rule__DateArrayExpression__Group_2__0 )* )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4176:1: ( ( rule__DateArrayExpression__Group_2__0 )* )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4177:1: ( rule__DateArrayExpression__Group_2__0 )*
            {
             before(grammarAccess.getDateArrayExpressionAccess().getGroup_2()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4178:1: ( rule__DateArrayExpression__Group_2__0 )*
            loop27:
            do {
                int alt27=2;
                int LA27_0 = input.LA(1);

                if ( (LA27_0==28) ) {
                    alt27=1;
                }


                switch (alt27) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4178:2: rule__DateArrayExpression__Group_2__0
            	    {
            	    pushFollow(FOLLOW_rule__DateArrayExpression__Group_2__0_in_rule__DateArrayExpression__Group__2__Impl8535);
            	    rule__DateArrayExpression__Group_2__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop27;
                }
            } while (true);

             after(grammarAccess.getDateArrayExpressionAccess().getGroup_2()); 

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
    // $ANTLR end "rule__DateArrayExpression__Group__2__Impl"


    // $ANTLR start "rule__DateArrayExpression__Group__3"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4188:1: rule__DateArrayExpression__Group__3 : rule__DateArrayExpression__Group__3__Impl ;
    public final void rule__DateArrayExpression__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4192:1: ( rule__DateArrayExpression__Group__3__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4193:2: rule__DateArrayExpression__Group__3__Impl
            {
            pushFollow(FOLLOW_rule__DateArrayExpression__Group__3__Impl_in_rule__DateArrayExpression__Group__38566);
            rule__DateArrayExpression__Group__3__Impl();

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
    // $ANTLR end "rule__DateArrayExpression__Group__3"


    // $ANTLR start "rule__DateArrayExpression__Group__3__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4199:1: rule__DateArrayExpression__Group__3__Impl : ( ']' ) ;
    public final void rule__DateArrayExpression__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4203:1: ( ( ']' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4204:1: ( ']' )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4204:1: ( ']' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4205:1: ']'
            {
             before(grammarAccess.getDateArrayExpressionAccess().getRightSquareBracketKeyword_3()); 
            match(input,36,FOLLOW_36_in_rule__DateArrayExpression__Group__3__Impl8594); 
             after(grammarAccess.getDateArrayExpressionAccess().getRightSquareBracketKeyword_3()); 

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
    // $ANTLR end "rule__DateArrayExpression__Group__3__Impl"


    // $ANTLR start "rule__DateArrayExpression__Group_2__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4226:1: rule__DateArrayExpression__Group_2__0 : rule__DateArrayExpression__Group_2__0__Impl rule__DateArrayExpression__Group_2__1 ;
    public final void rule__DateArrayExpression__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4230:1: ( rule__DateArrayExpression__Group_2__0__Impl rule__DateArrayExpression__Group_2__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4231:2: rule__DateArrayExpression__Group_2__0__Impl rule__DateArrayExpression__Group_2__1
            {
            pushFollow(FOLLOW_rule__DateArrayExpression__Group_2__0__Impl_in_rule__DateArrayExpression__Group_2__08633);
            rule__DateArrayExpression__Group_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__DateArrayExpression__Group_2__1_in_rule__DateArrayExpression__Group_2__08636);
            rule__DateArrayExpression__Group_2__1();

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
    // $ANTLR end "rule__DateArrayExpression__Group_2__0"


    // $ANTLR start "rule__DateArrayExpression__Group_2__0__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4238:1: rule__DateArrayExpression__Group_2__0__Impl : ( ',' ) ;
    public final void rule__DateArrayExpression__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4242:1: ( ( ',' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4243:1: ( ',' )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4243:1: ( ',' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4244:1: ','
            {
             before(grammarAccess.getDateArrayExpressionAccess().getCommaKeyword_2_0()); 
            match(input,28,FOLLOW_28_in_rule__DateArrayExpression__Group_2__0__Impl8664); 
             after(grammarAccess.getDateArrayExpressionAccess().getCommaKeyword_2_0()); 

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
    // $ANTLR end "rule__DateArrayExpression__Group_2__0__Impl"


    // $ANTLR start "rule__DateArrayExpression__Group_2__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4257:1: rule__DateArrayExpression__Group_2__1 : rule__DateArrayExpression__Group_2__1__Impl ;
    public final void rule__DateArrayExpression__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4261:1: ( rule__DateArrayExpression__Group_2__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4262:2: rule__DateArrayExpression__Group_2__1__Impl
            {
            pushFollow(FOLLOW_rule__DateArrayExpression__Group_2__1__Impl_in_rule__DateArrayExpression__Group_2__18695);
            rule__DateArrayExpression__Group_2__1__Impl();

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
    // $ANTLR end "rule__DateArrayExpression__Group_2__1"


    // $ANTLR start "rule__DateArrayExpression__Group_2__1__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4268:1: rule__DateArrayExpression__Group_2__1__Impl : ( ( rule__DateArrayExpression__ValuesAssignment_2_1 ) ) ;
    public final void rule__DateArrayExpression__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4272:1: ( ( ( rule__DateArrayExpression__ValuesAssignment_2_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4273:1: ( ( rule__DateArrayExpression__ValuesAssignment_2_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4273:1: ( ( rule__DateArrayExpression__ValuesAssignment_2_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4274:1: ( rule__DateArrayExpression__ValuesAssignment_2_1 )
            {
             before(grammarAccess.getDateArrayExpressionAccess().getValuesAssignment_2_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4275:1: ( rule__DateArrayExpression__ValuesAssignment_2_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4275:2: rule__DateArrayExpression__ValuesAssignment_2_1
            {
            pushFollow(FOLLOW_rule__DateArrayExpression__ValuesAssignment_2_1_in_rule__DateArrayExpression__Group_2__1__Impl8722);
            rule__DateArrayExpression__ValuesAssignment_2_1();

            state._fsp--;


            }

             after(grammarAccess.getDateArrayExpressionAccess().getValuesAssignment_2_1()); 

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
    // $ANTLR end "rule__DateArrayExpression__Group_2__1__Impl"


    // $ANTLR start "rule__BooleanArrayExpression__Group__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4289:1: rule__BooleanArrayExpression__Group__0 : rule__BooleanArrayExpression__Group__0__Impl rule__BooleanArrayExpression__Group__1 ;
    public final void rule__BooleanArrayExpression__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4293:1: ( rule__BooleanArrayExpression__Group__0__Impl rule__BooleanArrayExpression__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4294:2: rule__BooleanArrayExpression__Group__0__Impl rule__BooleanArrayExpression__Group__1
            {
            pushFollow(FOLLOW_rule__BooleanArrayExpression__Group__0__Impl_in_rule__BooleanArrayExpression__Group__08756);
            rule__BooleanArrayExpression__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__BooleanArrayExpression__Group__1_in_rule__BooleanArrayExpression__Group__08759);
            rule__BooleanArrayExpression__Group__1();

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
    // $ANTLR end "rule__BooleanArrayExpression__Group__0"


    // $ANTLR start "rule__BooleanArrayExpression__Group__0__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4301:1: rule__BooleanArrayExpression__Group__0__Impl : ( '[' ) ;
    public final void rule__BooleanArrayExpression__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4305:1: ( ( '[' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4306:1: ( '[' )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4306:1: ( '[' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4307:1: '['
            {
             before(grammarAccess.getBooleanArrayExpressionAccess().getLeftSquareBracketKeyword_0()); 
            match(input,35,FOLLOW_35_in_rule__BooleanArrayExpression__Group__0__Impl8787); 
             after(grammarAccess.getBooleanArrayExpressionAccess().getLeftSquareBracketKeyword_0()); 

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
    // $ANTLR end "rule__BooleanArrayExpression__Group__0__Impl"


    // $ANTLR start "rule__BooleanArrayExpression__Group__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4320:1: rule__BooleanArrayExpression__Group__1 : rule__BooleanArrayExpression__Group__1__Impl rule__BooleanArrayExpression__Group__2 ;
    public final void rule__BooleanArrayExpression__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4324:1: ( rule__BooleanArrayExpression__Group__1__Impl rule__BooleanArrayExpression__Group__2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4325:2: rule__BooleanArrayExpression__Group__1__Impl rule__BooleanArrayExpression__Group__2
            {
            pushFollow(FOLLOW_rule__BooleanArrayExpression__Group__1__Impl_in_rule__BooleanArrayExpression__Group__18818);
            rule__BooleanArrayExpression__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__BooleanArrayExpression__Group__2_in_rule__BooleanArrayExpression__Group__18821);
            rule__BooleanArrayExpression__Group__2();

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
    // $ANTLR end "rule__BooleanArrayExpression__Group__1"


    // $ANTLR start "rule__BooleanArrayExpression__Group__1__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4332:1: rule__BooleanArrayExpression__Group__1__Impl : ( ( rule__BooleanArrayExpression__ValuesAssignment_1 ) ) ;
    public final void rule__BooleanArrayExpression__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4336:1: ( ( ( rule__BooleanArrayExpression__ValuesAssignment_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4337:1: ( ( rule__BooleanArrayExpression__ValuesAssignment_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4337:1: ( ( rule__BooleanArrayExpression__ValuesAssignment_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4338:1: ( rule__BooleanArrayExpression__ValuesAssignment_1 )
            {
             before(grammarAccess.getBooleanArrayExpressionAccess().getValuesAssignment_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4339:1: ( rule__BooleanArrayExpression__ValuesAssignment_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4339:2: rule__BooleanArrayExpression__ValuesAssignment_1
            {
            pushFollow(FOLLOW_rule__BooleanArrayExpression__ValuesAssignment_1_in_rule__BooleanArrayExpression__Group__1__Impl8848);
            rule__BooleanArrayExpression__ValuesAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getBooleanArrayExpressionAccess().getValuesAssignment_1()); 

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
    // $ANTLR end "rule__BooleanArrayExpression__Group__1__Impl"


    // $ANTLR start "rule__BooleanArrayExpression__Group__2"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4349:1: rule__BooleanArrayExpression__Group__2 : rule__BooleanArrayExpression__Group__2__Impl rule__BooleanArrayExpression__Group__3 ;
    public final void rule__BooleanArrayExpression__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4353:1: ( rule__BooleanArrayExpression__Group__2__Impl rule__BooleanArrayExpression__Group__3 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4354:2: rule__BooleanArrayExpression__Group__2__Impl rule__BooleanArrayExpression__Group__3
            {
            pushFollow(FOLLOW_rule__BooleanArrayExpression__Group__2__Impl_in_rule__BooleanArrayExpression__Group__28878);
            rule__BooleanArrayExpression__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__BooleanArrayExpression__Group__3_in_rule__BooleanArrayExpression__Group__28881);
            rule__BooleanArrayExpression__Group__3();

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
    // $ANTLR end "rule__BooleanArrayExpression__Group__2"


    // $ANTLR start "rule__BooleanArrayExpression__Group__2__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4361:1: rule__BooleanArrayExpression__Group__2__Impl : ( ( rule__BooleanArrayExpression__Group_2__0 )* ) ;
    public final void rule__BooleanArrayExpression__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4365:1: ( ( ( rule__BooleanArrayExpression__Group_2__0 )* ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4366:1: ( ( rule__BooleanArrayExpression__Group_2__0 )* )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4366:1: ( ( rule__BooleanArrayExpression__Group_2__0 )* )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4367:1: ( rule__BooleanArrayExpression__Group_2__0 )*
            {
             before(grammarAccess.getBooleanArrayExpressionAccess().getGroup_2()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4368:1: ( rule__BooleanArrayExpression__Group_2__0 )*
            loop28:
            do {
                int alt28=2;
                int LA28_0 = input.LA(1);

                if ( (LA28_0==28) ) {
                    alt28=1;
                }


                switch (alt28) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4368:2: rule__BooleanArrayExpression__Group_2__0
            	    {
            	    pushFollow(FOLLOW_rule__BooleanArrayExpression__Group_2__0_in_rule__BooleanArrayExpression__Group__2__Impl8908);
            	    rule__BooleanArrayExpression__Group_2__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop28;
                }
            } while (true);

             after(grammarAccess.getBooleanArrayExpressionAccess().getGroup_2()); 

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
    // $ANTLR end "rule__BooleanArrayExpression__Group__2__Impl"


    // $ANTLR start "rule__BooleanArrayExpression__Group__3"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4378:1: rule__BooleanArrayExpression__Group__3 : rule__BooleanArrayExpression__Group__3__Impl ;
    public final void rule__BooleanArrayExpression__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4382:1: ( rule__BooleanArrayExpression__Group__3__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4383:2: rule__BooleanArrayExpression__Group__3__Impl
            {
            pushFollow(FOLLOW_rule__BooleanArrayExpression__Group__3__Impl_in_rule__BooleanArrayExpression__Group__38939);
            rule__BooleanArrayExpression__Group__3__Impl();

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
    // $ANTLR end "rule__BooleanArrayExpression__Group__3"


    // $ANTLR start "rule__BooleanArrayExpression__Group__3__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4389:1: rule__BooleanArrayExpression__Group__3__Impl : ( ']' ) ;
    public final void rule__BooleanArrayExpression__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4393:1: ( ( ']' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4394:1: ( ']' )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4394:1: ( ']' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4395:1: ']'
            {
             before(grammarAccess.getBooleanArrayExpressionAccess().getRightSquareBracketKeyword_3()); 
            match(input,36,FOLLOW_36_in_rule__BooleanArrayExpression__Group__3__Impl8967); 
             after(grammarAccess.getBooleanArrayExpressionAccess().getRightSquareBracketKeyword_3()); 

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
    // $ANTLR end "rule__BooleanArrayExpression__Group__3__Impl"


    // $ANTLR start "rule__BooleanArrayExpression__Group_2__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4416:1: rule__BooleanArrayExpression__Group_2__0 : rule__BooleanArrayExpression__Group_2__0__Impl rule__BooleanArrayExpression__Group_2__1 ;
    public final void rule__BooleanArrayExpression__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4420:1: ( rule__BooleanArrayExpression__Group_2__0__Impl rule__BooleanArrayExpression__Group_2__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4421:2: rule__BooleanArrayExpression__Group_2__0__Impl rule__BooleanArrayExpression__Group_2__1
            {
            pushFollow(FOLLOW_rule__BooleanArrayExpression__Group_2__0__Impl_in_rule__BooleanArrayExpression__Group_2__09006);
            rule__BooleanArrayExpression__Group_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__BooleanArrayExpression__Group_2__1_in_rule__BooleanArrayExpression__Group_2__09009);
            rule__BooleanArrayExpression__Group_2__1();

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
    // $ANTLR end "rule__BooleanArrayExpression__Group_2__0"


    // $ANTLR start "rule__BooleanArrayExpression__Group_2__0__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4428:1: rule__BooleanArrayExpression__Group_2__0__Impl : ( ',' ) ;
    public final void rule__BooleanArrayExpression__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4432:1: ( ( ',' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4433:1: ( ',' )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4433:1: ( ',' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4434:1: ','
            {
             before(grammarAccess.getBooleanArrayExpressionAccess().getCommaKeyword_2_0()); 
            match(input,28,FOLLOW_28_in_rule__BooleanArrayExpression__Group_2__0__Impl9037); 
             after(grammarAccess.getBooleanArrayExpressionAccess().getCommaKeyword_2_0()); 

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
    // $ANTLR end "rule__BooleanArrayExpression__Group_2__0__Impl"


    // $ANTLR start "rule__BooleanArrayExpression__Group_2__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4447:1: rule__BooleanArrayExpression__Group_2__1 : rule__BooleanArrayExpression__Group_2__1__Impl ;
    public final void rule__BooleanArrayExpression__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4451:1: ( rule__BooleanArrayExpression__Group_2__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4452:2: rule__BooleanArrayExpression__Group_2__1__Impl
            {
            pushFollow(FOLLOW_rule__BooleanArrayExpression__Group_2__1__Impl_in_rule__BooleanArrayExpression__Group_2__19068);
            rule__BooleanArrayExpression__Group_2__1__Impl();

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
    // $ANTLR end "rule__BooleanArrayExpression__Group_2__1"


    // $ANTLR start "rule__BooleanArrayExpression__Group_2__1__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4458:1: rule__BooleanArrayExpression__Group_2__1__Impl : ( ( rule__BooleanArrayExpression__ValuesAssignment_2_1 ) ) ;
    public final void rule__BooleanArrayExpression__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4462:1: ( ( ( rule__BooleanArrayExpression__ValuesAssignment_2_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4463:1: ( ( rule__BooleanArrayExpression__ValuesAssignment_2_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4463:1: ( ( rule__BooleanArrayExpression__ValuesAssignment_2_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4464:1: ( rule__BooleanArrayExpression__ValuesAssignment_2_1 )
            {
             before(grammarAccess.getBooleanArrayExpressionAccess().getValuesAssignment_2_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4465:1: ( rule__BooleanArrayExpression__ValuesAssignment_2_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4465:2: rule__BooleanArrayExpression__ValuesAssignment_2_1
            {
            pushFollow(FOLLOW_rule__BooleanArrayExpression__ValuesAssignment_2_1_in_rule__BooleanArrayExpression__Group_2__1__Impl9095);
            rule__BooleanArrayExpression__ValuesAssignment_2_1();

            state._fsp--;


            }

             after(grammarAccess.getBooleanArrayExpressionAccess().getValuesAssignment_2_1()); 

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
    // $ANTLR end "rule__BooleanArrayExpression__Group_2__1__Impl"


    // $ANTLR start "rule__Model__ColAssignment_1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4480:1: rule__Model__ColAssignment_1 : ( ruleColumns ) ;
    public final void rule__Model__ColAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4484:1: ( ( ruleColumns ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4485:1: ( ruleColumns )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4485:1: ( ruleColumns )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4486:1: ruleColumns
            {
             before(grammarAccess.getModelAccess().getColColumnsParserRuleCall_1_0()); 
            pushFollow(FOLLOW_ruleColumns_in_rule__Model__ColAssignment_19134);
            ruleColumns();

            state._fsp--;

             after(grammarAccess.getModelAccess().getColColumnsParserRuleCall_1_0()); 

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
    // $ANTLR end "rule__Model__ColAssignment_1"


    // $ANTLR start "rule__Model__TblAssignment_3"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4495:1: rule__Model__TblAssignment_3 : ( ruleTables ) ;
    public final void rule__Model__TblAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4499:1: ( ( ruleTables ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4500:1: ( ruleTables )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4500:1: ( ruleTables )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4501:1: ruleTables
            {
             before(grammarAccess.getModelAccess().getTblTablesParserRuleCall_3_0()); 
            pushFollow(FOLLOW_ruleTables_in_rule__Model__TblAssignment_39165);
            ruleTables();

            state._fsp--;

             after(grammarAccess.getModelAccess().getTblTablesParserRuleCall_3_0()); 

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
    // $ANTLR end "rule__Model__TblAssignment_3"


    // $ANTLR start "rule__Model__WhereEntryAssignment_4_1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4510:1: rule__Model__WhereEntryAssignment_4_1 : ( ruleWhereEntry ) ;
    public final void rule__Model__WhereEntryAssignment_4_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4514:1: ( ( ruleWhereEntry ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4515:1: ( ruleWhereEntry )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4515:1: ( ruleWhereEntry )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4516:1: ruleWhereEntry
            {
             before(grammarAccess.getModelAccess().getWhereEntryWhereEntryParserRuleCall_4_1_0()); 
            pushFollow(FOLLOW_ruleWhereEntry_in_rule__Model__WhereEntryAssignment_4_19196);
            ruleWhereEntry();

            state._fsp--;

             after(grammarAccess.getModelAccess().getWhereEntryWhereEntryParserRuleCall_4_1_0()); 

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
    // $ANTLR end "rule__Model__WhereEntryAssignment_4_1"


    // $ANTLR start "rule__Columns__EntriesAssignment_1_1_1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4525:1: rule__Columns__EntriesAssignment_1_1_1 : ( ruleColumnOrAlias ) ;
    public final void rule__Columns__EntriesAssignment_1_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4529:1: ( ( ruleColumnOrAlias ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4530:1: ( ruleColumnOrAlias )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4530:1: ( ruleColumnOrAlias )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4531:1: ruleColumnOrAlias
            {
             before(grammarAccess.getColumnsAccess().getEntriesColumnOrAliasParserRuleCall_1_1_1_0()); 
            pushFollow(FOLLOW_ruleColumnOrAlias_in_rule__Columns__EntriesAssignment_1_1_19227);
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


    // $ANTLR start "rule__ColumnOrAlias__ColAliasAssignment_1_2"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4540:1: rule__ColumnOrAlias__ColAliasAssignment_1_2 : ( ruleColumnAlias ) ;
    public final void rule__ColumnOrAlias__ColAliasAssignment_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4544:1: ( ( ruleColumnAlias ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4545:1: ( ruleColumnAlias )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4545:1: ( ruleColumnAlias )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4546:1: ruleColumnAlias
            {
             before(grammarAccess.getColumnOrAliasAccess().getColAliasColumnAliasParserRuleCall_1_2_0()); 
            pushFollow(FOLLOW_ruleColumnAlias_in_rule__ColumnOrAlias__ColAliasAssignment_1_29258);
            ruleColumnAlias();

            state._fsp--;

             after(grammarAccess.getColumnOrAliasAccess().getColAliasColumnAliasParserRuleCall_1_2_0()); 

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
    // $ANTLR end "rule__ColumnOrAlias__ColAliasAssignment_1_2"


    // $ANTLR start "rule__ColumnOrAlias__ColAliasAssignment_2_1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4555:1: rule__ColumnOrAlias__ColAliasAssignment_2_1 : ( ruleColumnAlias ) ;
    public final void rule__ColumnOrAlias__ColAliasAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4559:1: ( ( ruleColumnAlias ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4560:1: ( ruleColumnAlias )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4560:1: ( ruleColumnAlias )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4561:1: ruleColumnAlias
            {
             before(grammarAccess.getColumnOrAliasAccess().getColAliasColumnAliasParserRuleCall_2_1_0()); 
            pushFollow(FOLLOW_ruleColumnAlias_in_rule__ColumnOrAlias__ColAliasAssignment_2_19289);
            ruleColumnAlias();

            state._fsp--;

             after(grammarAccess.getColumnOrAliasAccess().getColAliasColumnAliasParserRuleCall_2_1_0()); 

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
    // $ANTLR end "rule__ColumnOrAlias__ColAliasAssignment_2_1"


    // $ANTLR start "rule__ColumnFull__ColNameAssignment_0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4570:1: rule__ColumnFull__ColNameAssignment_0 : ( ruleColumn ) ;
    public final void rule__ColumnFull__ColNameAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4574:1: ( ( ruleColumn ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4575:1: ( ruleColumn )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4575:1: ( ruleColumn )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4576:1: ruleColumn
            {
             before(grammarAccess.getColumnFullAccess().getColNameColumnParserRuleCall_0_0()); 
            pushFollow(FOLLOW_ruleColumn_in_rule__ColumnFull__ColNameAssignment_09320);
            ruleColumn();

            state._fsp--;

             after(grammarAccess.getColumnFullAccess().getColNameColumnParserRuleCall_0_0()); 

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
    // $ANTLR end "rule__ColumnFull__ColNameAssignment_0"


    // $ANTLR start "rule__ColumnFull__ColNameAssignment_1_2"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4585:1: rule__ColumnFull__ColNameAssignment_1_2 : ( ruleColumn ) ;
    public final void rule__ColumnFull__ColNameAssignment_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4589:1: ( ( ruleColumn ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4590:1: ( ruleColumn )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4590:1: ( ruleColumn )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4591:1: ruleColumn
            {
             before(grammarAccess.getColumnFullAccess().getColNameColumnParserRuleCall_1_2_0()); 
            pushFollow(FOLLOW_ruleColumn_in_rule__ColumnFull__ColNameAssignment_1_29351);
            ruleColumn();

            state._fsp--;

             after(grammarAccess.getColumnFullAccess().getColNameColumnParserRuleCall_1_2_0()); 

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
    // $ANTLR end "rule__ColumnFull__ColNameAssignment_1_2"


    // $ANTLR start "rule__ColumnAlias__ColAliasAssignment"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4600:1: rule__ColumnAlias__ColAliasAssignment : ( RULE_ID ) ;
    public final void rule__ColumnAlias__ColAliasAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4604:1: ( ( RULE_ID ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4605:1: ( RULE_ID )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4605:1: ( RULE_ID )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4606:1: RULE_ID
            {
             before(grammarAccess.getColumnAliasAccess().getColAliasIDTerminalRuleCall_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__ColumnAlias__ColAliasAssignment9382); 
             after(grammarAccess.getColumnAliasAccess().getColAliasIDTerminalRuleCall_0()); 

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
    // $ANTLR end "rule__ColumnAlias__ColAliasAssignment"


    // $ANTLR start "rule__Column__ColNameAssignment"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4615:1: rule__Column__ColNameAssignment : ( RULE_ID ) ;
    public final void rule__Column__ColNameAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4619:1: ( ( RULE_ID ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4620:1: ( RULE_ID )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4620:1: ( RULE_ID )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4621:1: RULE_ID
            {
             before(grammarAccess.getColumnAccess().getColNameIDTerminalRuleCall_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__Column__ColNameAssignment9413); 
             after(grammarAccess.getColumnAccess().getColNameIDTerminalRuleCall_0()); 

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
    // $ANTLR end "rule__Column__ColNameAssignment"


    // $ANTLR start "rule__Tables__EntriesAssignment_1_1_1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4630:1: rule__Tables__EntriesAssignment_1_1_1 : ( ruleTableOrAlias ) ;
    public final void rule__Tables__EntriesAssignment_1_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4634:1: ( ( ruleTableOrAlias ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4635:1: ( ruleTableOrAlias )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4635:1: ( ruleTableOrAlias )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4636:1: ruleTableOrAlias
            {
             before(grammarAccess.getTablesAccess().getEntriesTableOrAliasParserRuleCall_1_1_1_0()); 
            pushFollow(FOLLOW_ruleTableOrAlias_in_rule__Tables__EntriesAssignment_1_1_19444);
            ruleTableOrAlias();

            state._fsp--;

             after(grammarAccess.getTablesAccess().getEntriesTableOrAliasParserRuleCall_1_1_1_0()); 

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
    // $ANTLR end "rule__Tables__EntriesAssignment_1_1_1"


    // $ANTLR start "rule__TableOrAlias__TblAliasAssignment_1_2"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4645:1: rule__TableOrAlias__TblAliasAssignment_1_2 : ( ruleTableAlias ) ;
    public final void rule__TableOrAlias__TblAliasAssignment_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4649:1: ( ( ruleTableAlias ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4650:1: ( ruleTableAlias )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4650:1: ( ruleTableAlias )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4651:1: ruleTableAlias
            {
             before(grammarAccess.getTableOrAliasAccess().getTblAliasTableAliasParserRuleCall_1_2_0()); 
            pushFollow(FOLLOW_ruleTableAlias_in_rule__TableOrAlias__TblAliasAssignment_1_29475);
            ruleTableAlias();

            state._fsp--;

             after(grammarAccess.getTableOrAliasAccess().getTblAliasTableAliasParserRuleCall_1_2_0()); 

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
    // $ANTLR end "rule__TableOrAlias__TblAliasAssignment_1_2"


    // $ANTLR start "rule__TableOrAlias__TblAliasAssignment_2_1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4660:1: rule__TableOrAlias__TblAliasAssignment_2_1 : ( ruleTableAlias ) ;
    public final void rule__TableOrAlias__TblAliasAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4664:1: ( ( ruleTableAlias ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4665:1: ( ruleTableAlias )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4665:1: ( ruleTableAlias )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4666:1: ruleTableAlias
            {
             before(grammarAccess.getTableOrAliasAccess().getTblAliasTableAliasParserRuleCall_2_1_0()); 
            pushFollow(FOLLOW_ruleTableAlias_in_rule__TableOrAlias__TblAliasAssignment_2_19506);
            ruleTableAlias();

            state._fsp--;

             after(grammarAccess.getTableOrAliasAccess().getTblAliasTableAliasParserRuleCall_2_1_0()); 

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
    // $ANTLR end "rule__TableOrAlias__TblAliasAssignment_2_1"


    // $ANTLR start "rule__TableFull__TblAssignment_0_2"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4675:1: rule__TableFull__TblAssignment_0_2 : ( ruleTable ) ;
    public final void rule__TableFull__TblAssignment_0_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4679:1: ( ( ruleTable ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4680:1: ( ruleTable )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4680:1: ( ruleTable )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4681:1: ruleTable
            {
             before(grammarAccess.getTableFullAccess().getTblTableParserRuleCall_0_2_0()); 
            pushFollow(FOLLOW_ruleTable_in_rule__TableFull__TblAssignment_0_29537);
            ruleTable();

            state._fsp--;

             after(grammarAccess.getTableFullAccess().getTblTableParserRuleCall_0_2_0()); 

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
    // $ANTLR end "rule__TableFull__TblAssignment_0_2"


    // $ANTLR start "rule__TableFull__TblAssignment_1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4690:1: rule__TableFull__TblAssignment_1 : ( ruleTable ) ;
    public final void rule__TableFull__TblAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4694:1: ( ( ruleTable ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4695:1: ( ruleTable )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4695:1: ( ruleTable )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4696:1: ruleTable
            {
             before(grammarAccess.getTableFullAccess().getTblTableParserRuleCall_1_0()); 
            pushFollow(FOLLOW_ruleTable_in_rule__TableFull__TblAssignment_19568);
            ruleTable();

            state._fsp--;

             after(grammarAccess.getTableFullAccess().getTblTableParserRuleCall_1_0()); 

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
    // $ANTLR end "rule__TableFull__TblAssignment_1"


    // $ANTLR start "rule__Table__TblAssignment"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4705:1: rule__Table__TblAssignment : ( RULE_ID ) ;
    public final void rule__Table__TblAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4709:1: ( ( RULE_ID ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4710:1: ( RULE_ID )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4710:1: ( RULE_ID )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4711:1: RULE_ID
            {
             before(grammarAccess.getTableAccess().getTblIDTerminalRuleCall_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__Table__TblAssignment9599); 
             after(grammarAccess.getTableAccess().getTblIDTerminalRuleCall_0()); 

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
    // $ANTLR end "rule__Table__TblAssignment"


    // $ANTLR start "rule__TableAlias__TblAliasAssignment"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4720:1: rule__TableAlias__TblAliasAssignment : ( RULE_ID ) ;
    public final void rule__TableAlias__TblAliasAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4724:1: ( ( RULE_ID ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4725:1: ( RULE_ID )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4725:1: ( RULE_ID )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4726:1: RULE_ID
            {
             before(grammarAccess.getTableAliasAccess().getTblAliasIDTerminalRuleCall_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__TableAlias__TblAliasAssignment9630); 
             after(grammarAccess.getTableAliasAccess().getTblAliasIDTerminalRuleCall_0()); 

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
    // $ANTLR end "rule__TableAlias__TblAliasAssignment"


    // $ANTLR start "rule__Schema__SchemAssignment_0_2"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4735:1: rule__Schema__SchemAssignment_0_2 : ( RULE_ID ) ;
    public final void rule__Schema__SchemAssignment_0_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4739:1: ( ( RULE_ID ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4740:1: ( RULE_ID )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4740:1: ( RULE_ID )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4741:1: RULE_ID
            {
             before(grammarAccess.getSchemaAccess().getSchemIDTerminalRuleCall_0_2_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__Schema__SchemAssignment_0_29661); 
             after(grammarAccess.getSchemaAccess().getSchemIDTerminalRuleCall_0_2_0()); 

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
    // $ANTLR end "rule__Schema__SchemAssignment_0_2"


    // $ANTLR start "rule__Schema__SchemAssignment_1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4750:1: rule__Schema__SchemAssignment_1 : ( RULE_ID ) ;
    public final void rule__Schema__SchemAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4754:1: ( ( RULE_ID ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4755:1: ( RULE_ID )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4755:1: ( RULE_ID )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4756:1: RULE_ID
            {
             before(grammarAccess.getSchemaAccess().getSchemIDTerminalRuleCall_1_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__Schema__SchemAssignment_19692); 
             after(grammarAccess.getSchemaAccess().getSchemIDTerminalRuleCall_1_0()); 

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
    // $ANTLR end "rule__Schema__SchemAssignment_1"


    // $ANTLR start "rule__Database__DbNameAssignment"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4765:1: rule__Database__DbNameAssignment : ( RULE_ID ) ;
    public final void rule__Database__DbNameAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4769:1: ( ( RULE_ID ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4770:1: ( RULE_ID )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4770:1: ( RULE_ID )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4771:1: RULE_ID
            {
             before(grammarAccess.getDatabaseAccess().getDbNameIDTerminalRuleCall_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__Database__DbNameAssignment9723); 
             after(grammarAccess.getDatabaseAccess().getDbNameIDTerminalRuleCall_0()); 

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
    // $ANTLR end "rule__Database__DbNameAssignment"


    // $ANTLR start "rule__WhereEntry__EntriesAssignment_1_1_1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4780:1: rule__WhereEntry__EntriesAssignment_1_1_1 : ( ruleAndWhereEntry ) ;
    public final void rule__WhereEntry__EntriesAssignment_1_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4784:1: ( ( ruleAndWhereEntry ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4785:1: ( ruleAndWhereEntry )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4785:1: ( ruleAndWhereEntry )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4786:1: ruleAndWhereEntry
            {
             before(grammarAccess.getWhereEntryAccess().getEntriesAndWhereEntryParserRuleCall_1_1_1_0()); 
            pushFollow(FOLLOW_ruleAndWhereEntry_in_rule__WhereEntry__EntriesAssignment_1_1_19754);
            ruleAndWhereEntry();

            state._fsp--;

             after(grammarAccess.getWhereEntryAccess().getEntriesAndWhereEntryParserRuleCall_1_1_1_0()); 

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
    // $ANTLR end "rule__WhereEntry__EntriesAssignment_1_1_1"


    // $ANTLR start "rule__AndWhereEntry__EntriesAssignment_1_1_1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4795:1: rule__AndWhereEntry__EntriesAssignment_1_1_1 : ( ruleConcreteWhereEntry ) ;
    public final void rule__AndWhereEntry__EntriesAssignment_1_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4799:1: ( ( ruleConcreteWhereEntry ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4800:1: ( ruleConcreteWhereEntry )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4800:1: ( ruleConcreteWhereEntry )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4801:1: ruleConcreteWhereEntry
            {
             before(grammarAccess.getAndWhereEntryAccess().getEntriesConcreteWhereEntryParserRuleCall_1_1_1_0()); 
            pushFollow(FOLLOW_ruleConcreteWhereEntry_in_rule__AndWhereEntry__EntriesAssignment_1_1_19785);
            ruleConcreteWhereEntry();

            state._fsp--;

             after(grammarAccess.getAndWhereEntryAccess().getEntriesConcreteWhereEntryParserRuleCall_1_1_1_0()); 

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
    // $ANTLR end "rule__AndWhereEntry__EntriesAssignment_1_1_1"


    // $ANTLR start "rule__SingleExpressionWhereEntry__NameAssignment_0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4810:1: rule__SingleExpressionWhereEntry__NameAssignment_0 : ( RULE_ID ) ;
    public final void rule__SingleExpressionWhereEntry__NameAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4814:1: ( ( RULE_ID ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4815:1: ( RULE_ID )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4815:1: ( RULE_ID )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4816:1: RULE_ID
            {
             before(grammarAccess.getSingleExpressionWhereEntryAccess().getNameIDTerminalRuleCall_0_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__SingleExpressionWhereEntry__NameAssignment_09816); 
             after(grammarAccess.getSingleExpressionWhereEntryAccess().getNameIDTerminalRuleCall_0_0()); 

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
    // $ANTLR end "rule__SingleExpressionWhereEntry__NameAssignment_0"


    // $ANTLR start "rule__SingleExpressionWhereEntry__OperatorAssignment_1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4825:1: rule__SingleExpressionWhereEntry__OperatorAssignment_1 : ( ruleOperator ) ;
    public final void rule__SingleExpressionWhereEntry__OperatorAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4829:1: ( ( ruleOperator ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4830:1: ( ruleOperator )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4830:1: ( ruleOperator )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4831:1: ruleOperator
            {
             before(grammarAccess.getSingleExpressionWhereEntryAccess().getOperatorOperatorEnumRuleCall_1_0()); 
            pushFollow(FOLLOW_ruleOperator_in_rule__SingleExpressionWhereEntry__OperatorAssignment_19847);
            ruleOperator();

            state._fsp--;

             after(grammarAccess.getSingleExpressionWhereEntryAccess().getOperatorOperatorEnumRuleCall_1_0()); 

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
    // $ANTLR end "rule__SingleExpressionWhereEntry__OperatorAssignment_1"


    // $ANTLR start "rule__SingleExpressionWhereEntry__RhsAssignment_2"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4840:1: rule__SingleExpressionWhereEntry__RhsAssignment_2 : ( ruleExpression ) ;
    public final void rule__SingleExpressionWhereEntry__RhsAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4844:1: ( ( ruleExpression ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4845:1: ( ruleExpression )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4845:1: ( ruleExpression )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4846:1: ruleExpression
            {
             before(grammarAccess.getSingleExpressionWhereEntryAccess().getRhsExpressionParserRuleCall_2_0()); 
            pushFollow(FOLLOW_ruleExpression_in_rule__SingleExpressionWhereEntry__RhsAssignment_29878);
            ruleExpression();

            state._fsp--;

             after(grammarAccess.getSingleExpressionWhereEntryAccess().getRhsExpressionParserRuleCall_2_0()); 

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
    // $ANTLR end "rule__SingleExpressionWhereEntry__RhsAssignment_2"


    // $ANTLR start "rule__ReplacableValue__ValueAssignment"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4855:1: rule__ReplacableValue__ValueAssignment : ( ( '?' ) ) ;
    public final void rule__ReplacableValue__ValueAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4859:1: ( ( ( '?' ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4860:1: ( ( '?' ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4860:1: ( ( '?' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4861:1: ( '?' )
            {
             before(grammarAccess.getReplacableValueAccess().getValueQuestionMarkKeyword_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4862:1: ( '?' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4863:1: '?'
            {
             before(grammarAccess.getReplacableValueAccess().getValueQuestionMarkKeyword_0()); 
            match(input,37,FOLLOW_37_in_rule__ReplacableValue__ValueAssignment9914); 
             after(grammarAccess.getReplacableValueAccess().getValueQuestionMarkKeyword_0()); 

            }

             after(grammarAccess.getReplacableValueAccess().getValueQuestionMarkKeyword_0()); 

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
    // $ANTLR end "rule__ReplacableValue__ValueAssignment"


    // $ANTLR start "rule__DoubleExpression__ValueAssignment"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4878:1: rule__DoubleExpression__ValueAssignment : ( RULE_SIGNED_DOUBLE ) ;
    public final void rule__DoubleExpression__ValueAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4882:1: ( ( RULE_SIGNED_DOUBLE ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4883:1: ( RULE_SIGNED_DOUBLE )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4883:1: ( RULE_SIGNED_DOUBLE )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4884:1: RULE_SIGNED_DOUBLE
            {
             before(grammarAccess.getDoubleExpressionAccess().getValueSIGNED_DOUBLETerminalRuleCall_0()); 
            match(input,RULE_SIGNED_DOUBLE,FOLLOW_RULE_SIGNED_DOUBLE_in_rule__DoubleExpression__ValueAssignment9953); 
             after(grammarAccess.getDoubleExpressionAccess().getValueSIGNED_DOUBLETerminalRuleCall_0()); 

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
    // $ANTLR end "rule__DoubleExpression__ValueAssignment"


    // $ANTLR start "rule__LongExpression__ValueAssignment"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4893:1: rule__LongExpression__ValueAssignment : ( RULE_SINGED_LONG ) ;
    public final void rule__LongExpression__ValueAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4897:1: ( ( RULE_SINGED_LONG ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4898:1: ( RULE_SINGED_LONG )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4898:1: ( RULE_SINGED_LONG )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4899:1: RULE_SINGED_LONG
            {
             before(grammarAccess.getLongExpressionAccess().getValueSINGED_LONGTerminalRuleCall_0()); 
            match(input,RULE_SINGED_LONG,FOLLOW_RULE_SINGED_LONG_in_rule__LongExpression__ValueAssignment9984); 
             after(grammarAccess.getLongExpressionAccess().getValueSINGED_LONGTerminalRuleCall_0()); 

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
    // $ANTLR end "rule__LongExpression__ValueAssignment"


    // $ANTLR start "rule__StringExpression__ValueAssignment"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4908:1: rule__StringExpression__ValueAssignment : ( RULE_STRING ) ;
    public final void rule__StringExpression__ValueAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4912:1: ( ( RULE_STRING ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4913:1: ( RULE_STRING )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4913:1: ( RULE_STRING )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4914:1: RULE_STRING
            {
             before(grammarAccess.getStringExpressionAccess().getValueSTRINGTerminalRuleCall_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__StringExpression__ValueAssignment10015); 
             after(grammarAccess.getStringExpressionAccess().getValueSTRINGTerminalRuleCall_0()); 

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
    // $ANTLR end "rule__StringExpression__ValueAssignment"


    // $ANTLR start "rule__NullExpression__ValueAssignment"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4923:1: rule__NullExpression__ValueAssignment : ( ( 'null' ) ) ;
    public final void rule__NullExpression__ValueAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4927:1: ( ( ( 'null' ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4928:1: ( ( 'null' ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4928:1: ( ( 'null' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4929:1: ( 'null' )
            {
             before(grammarAccess.getNullExpressionAccess().getValueNullKeyword_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4930:1: ( 'null' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4931:1: 'null'
            {
             before(grammarAccess.getNullExpressionAccess().getValueNullKeyword_0()); 
            match(input,38,FOLLOW_38_in_rule__NullExpression__ValueAssignment10051); 
             after(grammarAccess.getNullExpressionAccess().getValueNullKeyword_0()); 

            }

             after(grammarAccess.getNullExpressionAccess().getValueNullKeyword_0()); 

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
    // $ANTLR end "rule__NullExpression__ValueAssignment"


    // $ANTLR start "rule__DateExpression__ValueAssignment"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4946:1: rule__DateExpression__ValueAssignment : ( RULE_DATE ) ;
    public final void rule__DateExpression__ValueAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4950:1: ( ( RULE_DATE ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4951:1: ( RULE_DATE )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4951:1: ( RULE_DATE )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4952:1: RULE_DATE
            {
             before(grammarAccess.getDateExpressionAccess().getValueDATETerminalRuleCall_0()); 
            match(input,RULE_DATE,FOLLOW_RULE_DATE_in_rule__DateExpression__ValueAssignment10090); 
             after(grammarAccess.getDateExpressionAccess().getValueDATETerminalRuleCall_0()); 

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
    // $ANTLR end "rule__DateExpression__ValueAssignment"


    // $ANTLR start "rule__BooleanExpression__TrueAssignment_0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4961:1: rule__BooleanExpression__TrueAssignment_0 : ( ( 'true' ) ) ;
    public final void rule__BooleanExpression__TrueAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4965:1: ( ( ( 'true' ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4966:1: ( ( 'true' ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4966:1: ( ( 'true' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4967:1: ( 'true' )
            {
             before(grammarAccess.getBooleanExpressionAccess().getTrueTrueKeyword_0_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4968:1: ( 'true' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4969:1: 'true'
            {
             before(grammarAccess.getBooleanExpressionAccess().getTrueTrueKeyword_0_0()); 
            match(input,39,FOLLOW_39_in_rule__BooleanExpression__TrueAssignment_010126); 
             after(grammarAccess.getBooleanExpressionAccess().getTrueTrueKeyword_0_0()); 

            }

             after(grammarAccess.getBooleanExpressionAccess().getTrueTrueKeyword_0_0()); 

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
    // $ANTLR end "rule__BooleanExpression__TrueAssignment_0"


    // $ANTLR start "rule__BooleanExpression__TrueAssignment_1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4984:1: rule__BooleanExpression__TrueAssignment_1 : ( ( 'false' ) ) ;
    public final void rule__BooleanExpression__TrueAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4988:1: ( ( ( 'false' ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4989:1: ( ( 'false' ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4989:1: ( ( 'false' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4990:1: ( 'false' )
            {
             before(grammarAccess.getBooleanExpressionAccess().getTrueFalseKeyword_1_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4991:1: ( 'false' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4992:1: 'false'
            {
             before(grammarAccess.getBooleanExpressionAccess().getTrueFalseKeyword_1_0()); 
            match(input,40,FOLLOW_40_in_rule__BooleanExpression__TrueAssignment_110170); 
             after(grammarAccess.getBooleanExpressionAccess().getTrueFalseKeyword_1_0()); 

            }

             after(grammarAccess.getBooleanExpressionAccess().getTrueFalseKeyword_1_0()); 

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
    // $ANTLR end "rule__BooleanExpression__TrueAssignment_1"


    // $ANTLR start "rule__MultiExpressionWhereEntry__NameAssignment_0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5007:1: rule__MultiExpressionWhereEntry__NameAssignment_0 : ( RULE_ID ) ;
    public final void rule__MultiExpressionWhereEntry__NameAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5011:1: ( ( RULE_ID ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5012:1: ( RULE_ID )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5012:1: ( RULE_ID )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5013:1: RULE_ID
            {
             before(grammarAccess.getMultiExpressionWhereEntryAccess().getNameIDTerminalRuleCall_0_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__MultiExpressionWhereEntry__NameAssignment_010209); 
             after(grammarAccess.getMultiExpressionWhereEntryAccess().getNameIDTerminalRuleCall_0_0()); 

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
    // $ANTLR end "rule__MultiExpressionWhereEntry__NameAssignment_0"


    // $ANTLR start "rule__MultiExpressionWhereEntry__OperatorAssignment_1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5022:1: rule__MultiExpressionWhereEntry__OperatorAssignment_1 : ( ruleArrayOperator ) ;
    public final void rule__MultiExpressionWhereEntry__OperatorAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5026:1: ( ( ruleArrayOperator ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5027:1: ( ruleArrayOperator )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5027:1: ( ruleArrayOperator )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5028:1: ruleArrayOperator
            {
             before(grammarAccess.getMultiExpressionWhereEntryAccess().getOperatorArrayOperatorEnumRuleCall_1_0()); 
            pushFollow(FOLLOW_ruleArrayOperator_in_rule__MultiExpressionWhereEntry__OperatorAssignment_110240);
            ruleArrayOperator();

            state._fsp--;

             after(grammarAccess.getMultiExpressionWhereEntryAccess().getOperatorArrayOperatorEnumRuleCall_1_0()); 

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
    // $ANTLR end "rule__MultiExpressionWhereEntry__OperatorAssignment_1"


    // $ANTLR start "rule__MultiExpressionWhereEntry__RhsAssignment_2"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5037:1: rule__MultiExpressionWhereEntry__RhsAssignment_2 : ( ruleArrayExpression ) ;
    public final void rule__MultiExpressionWhereEntry__RhsAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5041:1: ( ( ruleArrayExpression ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5042:1: ( ruleArrayExpression )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5042:1: ( ruleArrayExpression )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5043:1: ruleArrayExpression
            {
             before(grammarAccess.getMultiExpressionWhereEntryAccess().getRhsArrayExpressionParserRuleCall_2_0()); 
            pushFollow(FOLLOW_ruleArrayExpression_in_rule__MultiExpressionWhereEntry__RhsAssignment_210271);
            ruleArrayExpression();

            state._fsp--;

             after(grammarAccess.getMultiExpressionWhereEntryAccess().getRhsArrayExpressionParserRuleCall_2_0()); 

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
    // $ANTLR end "rule__MultiExpressionWhereEntry__RhsAssignment_2"


    // $ANTLR start "rule__DoubleArrayExpression__ValuesAssignment_1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5052:1: rule__DoubleArrayExpression__ValuesAssignment_1 : ( RULE_SIGNED_DOUBLE ) ;
    public final void rule__DoubleArrayExpression__ValuesAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5056:1: ( ( RULE_SIGNED_DOUBLE ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5057:1: ( RULE_SIGNED_DOUBLE )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5057:1: ( RULE_SIGNED_DOUBLE )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5058:1: RULE_SIGNED_DOUBLE
            {
             before(grammarAccess.getDoubleArrayExpressionAccess().getValuesSIGNED_DOUBLETerminalRuleCall_1_0()); 
            match(input,RULE_SIGNED_DOUBLE,FOLLOW_RULE_SIGNED_DOUBLE_in_rule__DoubleArrayExpression__ValuesAssignment_110302); 
             after(grammarAccess.getDoubleArrayExpressionAccess().getValuesSIGNED_DOUBLETerminalRuleCall_1_0()); 

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
    // $ANTLR end "rule__DoubleArrayExpression__ValuesAssignment_1"


    // $ANTLR start "rule__DoubleArrayExpression__ValuesAssignment_2_1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5067:1: rule__DoubleArrayExpression__ValuesAssignment_2_1 : ( RULE_SIGNED_DOUBLE ) ;
    public final void rule__DoubleArrayExpression__ValuesAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5071:1: ( ( RULE_SIGNED_DOUBLE ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5072:1: ( RULE_SIGNED_DOUBLE )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5072:1: ( RULE_SIGNED_DOUBLE )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5073:1: RULE_SIGNED_DOUBLE
            {
             before(grammarAccess.getDoubleArrayExpressionAccess().getValuesSIGNED_DOUBLETerminalRuleCall_2_1_0()); 
            match(input,RULE_SIGNED_DOUBLE,FOLLOW_RULE_SIGNED_DOUBLE_in_rule__DoubleArrayExpression__ValuesAssignment_2_110333); 
             after(grammarAccess.getDoubleArrayExpressionAccess().getValuesSIGNED_DOUBLETerminalRuleCall_2_1_0()); 

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
    // $ANTLR end "rule__DoubleArrayExpression__ValuesAssignment_2_1"


    // $ANTLR start "rule__LongArrayExpression__ValuesAssignment_1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5082:1: rule__LongArrayExpression__ValuesAssignment_1 : ( RULE_SINGED_LONG ) ;
    public final void rule__LongArrayExpression__ValuesAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5086:1: ( ( RULE_SINGED_LONG ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5087:1: ( RULE_SINGED_LONG )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5087:1: ( RULE_SINGED_LONG )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5088:1: RULE_SINGED_LONG
            {
             before(grammarAccess.getLongArrayExpressionAccess().getValuesSINGED_LONGTerminalRuleCall_1_0()); 
            match(input,RULE_SINGED_LONG,FOLLOW_RULE_SINGED_LONG_in_rule__LongArrayExpression__ValuesAssignment_110364); 
             after(grammarAccess.getLongArrayExpressionAccess().getValuesSINGED_LONGTerminalRuleCall_1_0()); 

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
    // $ANTLR end "rule__LongArrayExpression__ValuesAssignment_1"


    // $ANTLR start "rule__LongArrayExpression__ValuesAssignment_2_1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5097:1: rule__LongArrayExpression__ValuesAssignment_2_1 : ( RULE_SINGED_LONG ) ;
    public final void rule__LongArrayExpression__ValuesAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5101:1: ( ( RULE_SINGED_LONG ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5102:1: ( RULE_SINGED_LONG )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5102:1: ( RULE_SINGED_LONG )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5103:1: RULE_SINGED_LONG
            {
             before(grammarAccess.getLongArrayExpressionAccess().getValuesSINGED_LONGTerminalRuleCall_2_1_0()); 
            match(input,RULE_SINGED_LONG,FOLLOW_RULE_SINGED_LONG_in_rule__LongArrayExpression__ValuesAssignment_2_110395); 
             after(grammarAccess.getLongArrayExpressionAccess().getValuesSINGED_LONGTerminalRuleCall_2_1_0()); 

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
    // $ANTLR end "rule__LongArrayExpression__ValuesAssignment_2_1"


    // $ANTLR start "rule__StringArrayExpression__ValuesAssignment_1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5112:1: rule__StringArrayExpression__ValuesAssignment_1 : ( RULE_STRING ) ;
    public final void rule__StringArrayExpression__ValuesAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5116:1: ( ( RULE_STRING ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5117:1: ( RULE_STRING )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5117:1: ( RULE_STRING )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5118:1: RULE_STRING
            {
             before(grammarAccess.getStringArrayExpressionAccess().getValuesSTRINGTerminalRuleCall_1_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__StringArrayExpression__ValuesAssignment_110426); 
             after(grammarAccess.getStringArrayExpressionAccess().getValuesSTRINGTerminalRuleCall_1_0()); 

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
    // $ANTLR end "rule__StringArrayExpression__ValuesAssignment_1"


    // $ANTLR start "rule__StringArrayExpression__ValuesAssignment_2_1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5127:1: rule__StringArrayExpression__ValuesAssignment_2_1 : ( RULE_STRING ) ;
    public final void rule__StringArrayExpression__ValuesAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5131:1: ( ( RULE_STRING ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5132:1: ( RULE_STRING )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5132:1: ( RULE_STRING )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5133:1: RULE_STRING
            {
             before(grammarAccess.getStringArrayExpressionAccess().getValuesSTRINGTerminalRuleCall_2_1_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__StringArrayExpression__ValuesAssignment_2_110457); 
             after(grammarAccess.getStringArrayExpressionAccess().getValuesSTRINGTerminalRuleCall_2_1_0()); 

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
    // $ANTLR end "rule__StringArrayExpression__ValuesAssignment_2_1"


    // $ANTLR start "rule__NullArrayExpression__ValuesAssignment_1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5142:1: rule__NullArrayExpression__ValuesAssignment_1 : ( ( 'null' ) ) ;
    public final void rule__NullArrayExpression__ValuesAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5146:1: ( ( ( 'null' ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5147:1: ( ( 'null' ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5147:1: ( ( 'null' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5148:1: ( 'null' )
            {
             before(grammarAccess.getNullArrayExpressionAccess().getValuesNullKeyword_1_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5149:1: ( 'null' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5150:1: 'null'
            {
             before(grammarAccess.getNullArrayExpressionAccess().getValuesNullKeyword_1_0()); 
            match(input,38,FOLLOW_38_in_rule__NullArrayExpression__ValuesAssignment_110493); 
             after(grammarAccess.getNullArrayExpressionAccess().getValuesNullKeyword_1_0()); 

            }

             after(grammarAccess.getNullArrayExpressionAccess().getValuesNullKeyword_1_0()); 

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
    // $ANTLR end "rule__NullArrayExpression__ValuesAssignment_1"


    // $ANTLR start "rule__NullArrayExpression__ValuesAssignment_2_1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5165:1: rule__NullArrayExpression__ValuesAssignment_2_1 : ( ( 'null' ) ) ;
    public final void rule__NullArrayExpression__ValuesAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5169:1: ( ( ( 'null' ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5170:1: ( ( 'null' ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5170:1: ( ( 'null' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5171:1: ( 'null' )
            {
             before(grammarAccess.getNullArrayExpressionAccess().getValuesNullKeyword_2_1_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5172:1: ( 'null' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5173:1: 'null'
            {
             before(grammarAccess.getNullArrayExpressionAccess().getValuesNullKeyword_2_1_0()); 
            match(input,38,FOLLOW_38_in_rule__NullArrayExpression__ValuesAssignment_2_110537); 
             after(grammarAccess.getNullArrayExpressionAccess().getValuesNullKeyword_2_1_0()); 

            }

             after(grammarAccess.getNullArrayExpressionAccess().getValuesNullKeyword_2_1_0()); 

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
    // $ANTLR end "rule__NullArrayExpression__ValuesAssignment_2_1"


    // $ANTLR start "rule__DateArrayExpression__ValuesAssignment_1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5188:1: rule__DateArrayExpression__ValuesAssignment_1 : ( RULE_DATE ) ;
    public final void rule__DateArrayExpression__ValuesAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5192:1: ( ( RULE_DATE ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5193:1: ( RULE_DATE )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5193:1: ( RULE_DATE )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5194:1: RULE_DATE
            {
             before(grammarAccess.getDateArrayExpressionAccess().getValuesDATETerminalRuleCall_1_0()); 
            match(input,RULE_DATE,FOLLOW_RULE_DATE_in_rule__DateArrayExpression__ValuesAssignment_110576); 
             after(grammarAccess.getDateArrayExpressionAccess().getValuesDATETerminalRuleCall_1_0()); 

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
    // $ANTLR end "rule__DateArrayExpression__ValuesAssignment_1"


    // $ANTLR start "rule__DateArrayExpression__ValuesAssignment_2_1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5203:1: rule__DateArrayExpression__ValuesAssignment_2_1 : ( RULE_DATE ) ;
    public final void rule__DateArrayExpression__ValuesAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5207:1: ( ( RULE_DATE ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5208:1: ( RULE_DATE )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5208:1: ( RULE_DATE )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5209:1: RULE_DATE
            {
             before(grammarAccess.getDateArrayExpressionAccess().getValuesDATETerminalRuleCall_2_1_0()); 
            match(input,RULE_DATE,FOLLOW_RULE_DATE_in_rule__DateArrayExpression__ValuesAssignment_2_110607); 
             after(grammarAccess.getDateArrayExpressionAccess().getValuesDATETerminalRuleCall_2_1_0()); 

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
    // $ANTLR end "rule__DateArrayExpression__ValuesAssignment_2_1"


    // $ANTLR start "rule__BooleanArrayExpression__ValuesAssignment_1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5218:1: rule__BooleanArrayExpression__ValuesAssignment_1 : ( RULE_BOOL ) ;
    public final void rule__BooleanArrayExpression__ValuesAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5222:1: ( ( RULE_BOOL ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5223:1: ( RULE_BOOL )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5223:1: ( RULE_BOOL )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5224:1: RULE_BOOL
            {
             before(grammarAccess.getBooleanArrayExpressionAccess().getValuesBOOLTerminalRuleCall_1_0()); 
            match(input,RULE_BOOL,FOLLOW_RULE_BOOL_in_rule__BooleanArrayExpression__ValuesAssignment_110638); 
             after(grammarAccess.getBooleanArrayExpressionAccess().getValuesBOOLTerminalRuleCall_1_0()); 

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
    // $ANTLR end "rule__BooleanArrayExpression__ValuesAssignment_1"


    // $ANTLR start "rule__BooleanArrayExpression__ValuesAssignment_2_1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5233:1: rule__BooleanArrayExpression__ValuesAssignment_2_1 : ( RULE_BOOL ) ;
    public final void rule__BooleanArrayExpression__ValuesAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5237:1: ( ( RULE_BOOL ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5238:1: ( RULE_BOOL )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5238:1: ( RULE_BOOL )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5239:1: RULE_BOOL
            {
             before(grammarAccess.getBooleanArrayExpressionAccess().getValuesBOOLTerminalRuleCall_2_1_0()); 
            match(input,RULE_BOOL,FOLLOW_RULE_BOOL_in_rule__BooleanArrayExpression__ValuesAssignment_2_110669); 
             after(grammarAccess.getBooleanArrayExpressionAccess().getValuesBOOLTerminalRuleCall_2_1_0()); 

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
    // $ANTLR end "rule__BooleanArrayExpression__ValuesAssignment_2_1"

    // Delegated rules


    protected DFA1 dfa1 = new DFA1(this);
    protected DFA4 dfa4 = new DFA4(this);
    protected DFA5 dfa5 = new DFA5(this);
    static final String DFA1_eotS =
        "\13\uffff";
    static final String DFA1_eofS =
        "\1\uffff\1\4\4\uffff\1\4\1\uffff\1\4\1\uffff\1\4";
    static final String DFA1_minS =
        "\3\4\3\uffff\5\4";
    static final String DFA1_maxS =
        "\1\4\1\36\1\4\3\uffff\1\36\1\4\1\36\1\4\1\35";
    static final String DFA1_acceptS =
        "\3\uffff\1\2\1\1\1\3\5\uffff";
    static final String DFA1_specialS =
        "\13\uffff}>";
    static final String[] DFA1_transitionS = {
            "\1\1",
            "\1\5\25\uffff\1\4\1\uffff\1\4\1\3\1\2",
            "\1\6",
            "",
            "",
            "",
            "\1\5\25\uffff\1\4\1\uffff\1\4\1\3\1\7",
            "\1\10",
            "\1\5\25\uffff\1\4\1\uffff\1\4\1\3\1\11",
            "\1\12",
            "\1\5\25\uffff\1\4\1\uffff\1\4\1\3"
    };

    static final short[] DFA1_eot = DFA.unpackEncodedString(DFA1_eotS);
    static final short[] DFA1_eof = DFA.unpackEncodedString(DFA1_eofS);
    static final char[] DFA1_min = DFA.unpackEncodedStringToUnsignedChars(DFA1_minS);
    static final char[] DFA1_max = DFA.unpackEncodedStringToUnsignedChars(DFA1_maxS);
    static final short[] DFA1_accept = DFA.unpackEncodedString(DFA1_acceptS);
    static final short[] DFA1_special = DFA.unpackEncodedString(DFA1_specialS);
    static final short[][] DFA1_transition;

    static {
        int numStates = DFA1_transitionS.length;
        DFA1_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA1_transition[i] = DFA.unpackEncodedString(DFA1_transitionS[i]);
        }
    }

    class DFA1 extends DFA {

        public DFA1(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 1;
            this.eot = DFA1_eot;
            this.eof = DFA1_eof;
            this.min = DFA1_min;
            this.max = DFA1_max;
            this.accept = DFA1_accept;
            this.special = DFA1_special;
            this.transition = DFA1_transition;
        }
        public String getDescription() {
            return "1078:1: rule__ColumnOrAlias__Alternatives : ( ( ruleColumnFull ) | ( ( rule__ColumnOrAlias__Group_1__0 ) ) | ( ( rule__ColumnOrAlias__Group_2__0 ) ) );";
        }
    }
    static final String DFA4_eotS =
        "\41\uffff";
    static final String DFA4_eofS =
        "\1\uffff\1\3\2\uffff\1\5\1\12\3\uffff\1\15\1\uffff\1\22\1\15\1\23"+
        "\2\uffff\1\22\1\uffff\1\27\1\uffff\3\22\4\uffff\4\22\1\uffff\1\22";
    static final String DFA4_minS =
        "\3\4\1\uffff\1\4\1\0\1\4\1\uffff\1\4\1\32\1\uffff\1\4\1\32\1\0\2"+
        "\4\1\32\1\4\1\0\1\uffff\1\32\2\4\1\uffff\3\4\1\32\1\4\1\32\3\4";
    static final String DFA4_maxS =
        "\1\4\1\36\1\4\1\uffff\1\36\1\0\1\4\1\uffff\1\4\1\34\1\uffff\1\36"+
        "\1\34\1\0\2\4\1\34\1\4\1\0\1\uffff\1\34\2\36\1\uffff\3\4\1\34\1"+
        "\36\1\34\1\36\1\4\1\36";
    static final String DFA4_acceptS =
        "\3\uffff\1\2\3\uffff\1\1\2\uffff\1\1\10\uffff\1\1\3\uffff\1\1\11"+
        "\uffff";
    static final String DFA4_specialS =
        "\41\uffff}>";
    static final String[] DFA4_transitionS = {
            "\1\1",
            "\1\3\26\uffff\3\3\1\2",
            "\1\4",
            "",
            "\1\11\25\uffff\1\3\1\7\1\6\1\10\1\7",
            "\1\uffff",
            "\1\13",
            "",
            "\1\14",
            "\1\3\1\7\1\6",
            "",
            "\1\20\25\uffff\1\3\1\7\1\21\1\16\1\17",
            "\1\3\1\7\1\6",
            "\1\uffff",
            "\1\24",
            "\1\25",
            "\1\3\1\7\1\21",
            "\1\26",
            "\1\uffff",
            "",
            "\1\3\1\7\1\21",
            "\1\20\25\uffff\1\3\1\7\1\21\1\16\1\30",
            "\1\33\25\uffff\1\3\1\7\1\21\1\31\1\32",
            "",
            "\1\34",
            "\1\35",
            "\1\36",
            "\1\3\1\7\1\21",
            "\1\20\25\uffff\1\3\1\7\1\21\1\16\1\3",
            "\1\3\1\7\1\21",
            "\1\33\25\uffff\1\3\1\7\1\21\1\31\1\37",
            "\1\40",
            "\1\33\25\uffff\1\3\1\7\1\21\1\31\1\3"
    };

    static final short[] DFA4_eot = DFA.unpackEncodedString(DFA4_eotS);
    static final short[] DFA4_eof = DFA.unpackEncodedString(DFA4_eofS);
    static final char[] DFA4_min = DFA.unpackEncodedStringToUnsignedChars(DFA4_minS);
    static final char[] DFA4_max = DFA.unpackEncodedStringToUnsignedChars(DFA4_maxS);
    static final short[] DFA4_accept = DFA.unpackEncodedString(DFA4_acceptS);
    static final short[] DFA4_special = DFA.unpackEncodedString(DFA4_specialS);
    static final short[][] DFA4_transition;

    static {
        int numStates = DFA4_transitionS.length;
        DFA4_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA4_transition[i] = DFA.unpackEncodedString(DFA4_transitionS[i]);
        }
    }

    class DFA4 extends DFA {

        public DFA4(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 4;
            this.eot = DFA4_eot;
            this.eof = DFA4_eof;
            this.min = DFA4_min;
            this.max = DFA4_max;
            this.accept = DFA4_accept;
            this.special = DFA4_special;
            this.transition = DFA4_transition;
        }
        public String getDescription() {
            return "1156:1: rule__TableFull__Alternatives : ( ( ( rule__TableFull__Group_0__0 ) ) | ( ( rule__TableFull__TblAssignment_1 ) ) );";
        }
    }
    static final String DFA5_eotS =
        "\44\uffff";
    static final String DFA5_eofS =
        "\1\uffff\1\2\2\uffff\1\5\1\7\2\uffff\1\11\1\15\2\uffff\1\20\1\uffff"+
        "\1\25\1\20\1\26\2\uffff\1\25\1\uffff\1\32\1\uffff\3\25\4\uffff\4"+
        "\25\1\uffff\1\25";
    static final String DFA5_minS =
        "\1\4\1\36\1\uffff\2\4\1\0\1\4\1\uffff\1\4\1\0\2\4\1\32\1\uffff\1"+
        "\4\1\32\1\0\2\4\1\32\1\4\1\0\1\uffff\1\32\2\4\1\uffff\3\4\1\32\1"+
        "\4\1\32\3\4";
    static final String DFA5_maxS =
        "\1\4\1\36\1\uffff\1\4\1\36\1\0\1\4\1\uffff\1\36\1\0\2\4\1\34\1\uffff"+
        "\1\36\1\34\1\0\2\4\1\34\1\4\1\0\1\uffff\1\34\2\36\1\uffff\3\4\1"+
        "\34\1\36\1\34\1\36\1\4\1\36";
    static final String DFA5_acceptS =
        "\2\uffff\1\2\4\uffff\1\1\5\uffff\1\1\10\uffff\1\1\3\uffff\1\1\11"+
        "\uffff";
    static final String DFA5_specialS =
        "\44\uffff}>";
    static final String[] DFA5_transitionS = {
            "\1\1",
            "\1\3",
            "",
            "\1\4",
            "\1\2\26\uffff\3\2\1\6",
            "\1\uffff",
            "\1\10",
            "",
            "\1\14\25\uffff\1\2\1\7\1\12\1\13\1\7",
            "\1\uffff",
            "\1\16",
            "\1\17",
            "\1\2\1\7\1\12",
            "",
            "\1\23\25\uffff\1\2\1\15\1\24\1\21\1\22",
            "\1\2\1\15\1\12",
            "\1\uffff",
            "\1\27",
            "\1\30",
            "\1\2\1\15\1\24",
            "\1\31",
            "\1\uffff",
            "",
            "\1\2\1\26\1\24",
            "\1\23\25\uffff\1\2\1\26\1\24\1\21\1\33",
            "\1\36\25\uffff\1\2\1\26\1\24\1\34\1\35",
            "",
            "\1\37",
            "\1\40",
            "\1\41",
            "\1\2\1\32\1\24",
            "\1\23\25\uffff\1\2\1\32\1\24\1\21\1\2",
            "\1\2\1\32\1\24",
            "\1\36\25\uffff\1\2\1\32\1\24\1\34\1\42",
            "\1\43",
            "\1\36\25\uffff\1\2\1\32\1\24\1\34\1\2"
    };

    static final short[] DFA5_eot = DFA.unpackEncodedString(DFA5_eotS);
    static final short[] DFA5_eof = DFA.unpackEncodedString(DFA5_eofS);
    static final char[] DFA5_min = DFA.unpackEncodedStringToUnsignedChars(DFA5_minS);
    static final char[] DFA5_max = DFA.unpackEncodedStringToUnsignedChars(DFA5_maxS);
    static final short[] DFA5_accept = DFA.unpackEncodedString(DFA5_acceptS);
    static final short[] DFA5_special = DFA.unpackEncodedString(DFA5_specialS);
    static final short[][] DFA5_transition;

    static {
        int numStates = DFA5_transitionS.length;
        DFA5_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA5_transition[i] = DFA.unpackEncodedString(DFA5_transitionS[i]);
        }
    }

    class DFA5 extends DFA {

        public DFA5(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 5;
            this.eot = DFA5_eot;
            this.eof = DFA5_eof;
            this.min = DFA5_min;
            this.max = DFA5_max;
            this.accept = DFA5_accept;
            this.special = DFA5_special;
            this.transition = DFA5_transition;
        }
        public String getDescription() {
            return "1178:1: rule__Schema__Alternatives : ( ( ( rule__Schema__Group_0__0 ) ) | ( ( rule__Schema__SchemAssignment_1 ) ) );";
        }
    }
 

    public static final BitSet FOLLOW_ruleModel_in_entryRuleModel61 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleModel68 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Model__Group__0_in_ruleModel94 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumns_in_entryRuleColumns121 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleColumns128 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Columns__Group__0_in_ruleColumns154 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnOrAlias_in_entryRuleColumnOrAlias181 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleColumnOrAlias188 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ColumnOrAlias__Alternatives_in_ruleColumnOrAlias214 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnFull_in_entryRuleColumnFull241 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleColumnFull248 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ColumnFull__Alternatives_in_ruleColumnFull274 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnAlias_in_entryRuleColumnAlias301 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleColumnAlias308 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ColumnAlias__ColAliasAssignment_in_ruleColumnAlias334 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumn_in_entryRuleColumn361 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleColumn368 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Column__ColNameAssignment_in_ruleColumn394 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTables_in_entryRuleTables421 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleTables428 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Tables__Group__0_in_ruleTables454 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTableOrAlias_in_entryRuleTableOrAlias481 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleTableOrAlias488 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TableOrAlias__Alternatives_in_ruleTableOrAlias514 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTableFull_in_entryRuleTableFull541 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleTableFull548 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TableFull__Alternatives_in_ruleTableFull574 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTable_in_entryRuleTable601 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleTable608 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Table__TblAssignment_in_ruleTable634 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTableAlias_in_entryRuleTableAlias661 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleTableAlias668 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TableAlias__TblAliasAssignment_in_ruleTableAlias694 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSchema_in_entryRuleSchema721 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSchema728 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Schema__Alternatives_in_ruleSchema754 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDatabase_in_entryRuleDatabase781 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleDatabase788 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Database__DbNameAssignment_in_ruleDatabase814 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleWhereEntry_in_entryRuleWhereEntry841 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleWhereEntry848 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__WhereEntry__Group__0_in_ruleWhereEntry874 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAndWhereEntry_in_entryRuleAndWhereEntry901 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAndWhereEntry908 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__AndWhereEntry__Group__0_in_ruleAndWhereEntry934 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleConcreteWhereEntry_in_entryRuleConcreteWhereEntry961 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleConcreteWhereEntry968 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ConcreteWhereEntry__Alternatives_in_ruleConcreteWhereEntry994 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleParWhereEntry_in_entryRuleParWhereEntry1021 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleParWhereEntry1028 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ParWhereEntry__Group__0_in_ruleParWhereEntry1054 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpressionWhereEntry_in_entryRuleExpressionWhereEntry1081 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpressionWhereEntry1088 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ExpressionWhereEntry__Alternatives_in_ruleExpressionWhereEntry1114 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSingleExpressionWhereEntry_in_entryRuleSingleExpressionWhereEntry1141 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSingleExpressionWhereEntry1148 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SingleExpressionWhereEntry__Group__0_in_ruleSingleExpressionWhereEntry1174 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression_in_entryRuleExpression1201 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpression1208 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Expression__Alternatives_in_ruleExpression1234 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleReplacableValue_in_entryRuleReplacableValue1261 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleReplacableValue1268 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ReplacableValue__ValueAssignment_in_ruleReplacableValue1294 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDoubleExpression_in_entryRuleDoubleExpression1321 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleDoubleExpression1328 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__DoubleExpression__ValueAssignment_in_ruleDoubleExpression1354 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLongExpression_in_entryRuleLongExpression1381 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleLongExpression1388 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__LongExpression__ValueAssignment_in_ruleLongExpression1414 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleStringExpression_in_entryRuleStringExpression1441 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleStringExpression1448 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__StringExpression__ValueAssignment_in_ruleStringExpression1474 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNullExpression_in_entryRuleNullExpression1501 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleNullExpression1508 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NullExpression__ValueAssignment_in_ruleNullExpression1534 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDateExpression_in_entryRuleDateExpression1561 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleDateExpression1568 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__DateExpression__ValueAssignment_in_ruleDateExpression1594 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBooleanExpression_in_entryRuleBooleanExpression1621 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleBooleanExpression1628 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__BooleanExpression__Alternatives_in_ruleBooleanExpression1654 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleMultiExpressionWhereEntry_in_entryRuleMultiExpressionWhereEntry1681 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleMultiExpressionWhereEntry1688 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__MultiExpressionWhereEntry__Group__0_in_ruleMultiExpressionWhereEntry1714 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleArrayExpression_in_entryRuleArrayExpression1741 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleArrayExpression1748 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ArrayExpression__Alternatives_in_ruleArrayExpression1774 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDoubleArrayExpression_in_entryRuleDoubleArrayExpression1801 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleDoubleArrayExpression1808 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__DoubleArrayExpression__Group__0_in_ruleDoubleArrayExpression1834 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLongArrayExpression_in_entryRuleLongArrayExpression1861 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleLongArrayExpression1868 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__LongArrayExpression__Group__0_in_ruleLongArrayExpression1894 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleStringArrayExpression_in_entryRuleStringArrayExpression1921 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleStringArrayExpression1928 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__StringArrayExpression__Group__0_in_ruleStringArrayExpression1954 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNullArrayExpression_in_entryRuleNullArrayExpression1981 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleNullArrayExpression1988 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NullArrayExpression__Group__0_in_ruleNullArrayExpression2014 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDateArrayExpression_in_entryRuleDateArrayExpression2041 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleDateArrayExpression2048 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__DateArrayExpression__Group__0_in_ruleDateArrayExpression2074 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBooleanArrayExpression_in_entryRuleBooleanArrayExpression2101 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleBooleanArrayExpression2108 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__BooleanArrayExpression__Group__0_in_ruleBooleanArrayExpression2134 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ArrayOperator__Alternatives_in_ruleArrayOperator2171 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Operator__Alternatives_in_ruleOperator2207 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnFull_in_rule__ColumnOrAlias__Alternatives2242 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ColumnOrAlias__Group_1__0_in_rule__ColumnOrAlias__Alternatives2259 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ColumnOrAlias__Group_2__0_in_rule__ColumnOrAlias__Alternatives2277 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ColumnFull__ColNameAssignment_0_in_rule__ColumnFull__Alternatives2310 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ColumnFull__Group_1__0_in_rule__ColumnFull__Alternatives2328 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTableFull_in_rule__TableOrAlias__Alternatives2361 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TableOrAlias__Group_1__0_in_rule__TableOrAlias__Alternatives2378 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TableOrAlias__Group_2__0_in_rule__TableOrAlias__Alternatives2396 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TableFull__Group_0__0_in_rule__TableFull__Alternatives2429 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TableFull__TblAssignment_1_in_rule__TableFull__Alternatives2447 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Schema__Group_0__0_in_rule__Schema__Alternatives2480 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Schema__SchemAssignment_1_in_rule__Schema__Alternatives2498 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleParWhereEntry_in_rule__ConcreteWhereEntry__Alternatives2531 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpressionWhereEntry_in_rule__ConcreteWhereEntry__Alternatives2548 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSingleExpressionWhereEntry_in_rule__ExpressionWhereEntry__Alternatives2580 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleMultiExpressionWhereEntry_in_rule__ExpressionWhereEntry__Alternatives2597 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDoubleExpression_in_rule__Expression__Alternatives2629 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLongExpression_in_rule__Expression__Alternatives2646 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleStringExpression_in_rule__Expression__Alternatives2663 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNullExpression_in_rule__Expression__Alternatives2680 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDateExpression_in_rule__Expression__Alternatives2697 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBooleanExpression_in_rule__Expression__Alternatives2714 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleReplacableValue_in_rule__Expression__Alternatives2731 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__BooleanExpression__TrueAssignment_0_in_rule__BooleanExpression__Alternatives2763 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__BooleanExpression__TrueAssignment_1_in_rule__BooleanExpression__Alternatives2781 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDoubleArrayExpression_in_rule__ArrayExpression__Alternatives2814 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLongArrayExpression_in_rule__ArrayExpression__Alternatives2831 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleStringArrayExpression_in_rule__ArrayExpression__Alternatives2848 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNullArrayExpression_in_rule__ArrayExpression__Alternatives2865 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDateArrayExpression_in_rule__ArrayExpression__Alternatives2882 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBooleanArrayExpression_in_rule__ArrayExpression__Alternatives2899 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_rule__ArrayOperator__Alternatives2932 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_rule__ArrayOperator__Alternatives2953 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_17_in_rule__Operator__Alternatives2989 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_rule__Operator__Alternatives3010 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_rule__Operator__Alternatives3031 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_rule__Operator__Alternatives3052 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_rule__Operator__Alternatives3073 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_22_in_rule__Operator__Alternatives3094 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_rule__Operator__Alternatives3115 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_rule__Operator__Alternatives3136 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_rule__Operator__Alternatives3157 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_rule__Operator__Alternatives3178 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Model__Group__0__Impl_in_rule__Model__Group__03211 = new BitSet(new long[]{0x0000000004000010L});
    public static final BitSet FOLLOW_rule__Model__Group__1_in_rule__Model__Group__03214 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_rule__Model__Group__0__Impl3242 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Model__Group__1__Impl_in_rule__Model__Group__13273 = new BitSet(new long[]{0x0000000004000010L});
    public static final BitSet FOLLOW_rule__Model__Group__2_in_rule__Model__Group__13276 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Model__ColAssignment_1_in_rule__Model__Group__1__Impl3303 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Model__Group__2__Impl_in_rule__Model__Group__23334 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__Model__Group__3_in_rule__Model__Group__23337 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_rule__Model__Group__2__Impl3365 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Model__Group__3__Impl_in_rule__Model__Group__33396 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_rule__Model__Group__4_in_rule__Model__Group__33399 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Model__TblAssignment_3_in_rule__Model__Group__3__Impl3426 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Model__Group__4__Impl_in_rule__Model__Group__43456 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Model__Group_4__0_in_rule__Model__Group__4__Impl3483 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Model__Group_4__0__Impl_in_rule__Model__Group_4__03524 = new BitSet(new long[]{0x0000000200000010L});
    public static final BitSet FOLLOW_rule__Model__Group_4__1_in_rule__Model__Group_4__03527 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_27_in_rule__Model__Group_4__0__Impl3555 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Model__Group_4__1__Impl_in_rule__Model__Group_4__13586 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Model__WhereEntryAssignment_4_1_in_rule__Model__Group_4__1__Impl3613 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Columns__Group__0__Impl_in_rule__Columns__Group__03647 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_rule__Columns__Group__1_in_rule__Columns__Group__03650 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnOrAlias_in_rule__Columns__Group__0__Impl3677 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Columns__Group__1__Impl_in_rule__Columns__Group__13706 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Columns__Group_1__0_in_rule__Columns__Group__1__Impl3733 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Columns__Group_1__0__Impl_in_rule__Columns__Group_1__03768 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_rule__Columns__Group_1__1_in_rule__Columns__Group_1__03771 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Columns__Group_1__1__Impl_in_rule__Columns__Group_1__13829 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Columns__Group_1_1__0_in_rule__Columns__Group_1__1__Impl3858 = new BitSet(new long[]{0x0000000010000002L});
    public static final BitSet FOLLOW_rule__Columns__Group_1_1__0_in_rule__Columns__Group_1__1__Impl3870 = new BitSet(new long[]{0x0000000010000002L});
    public static final BitSet FOLLOW_rule__Columns__Group_1_1__0__Impl_in_rule__Columns__Group_1_1__03907 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__Columns__Group_1_1__1_in_rule__Columns__Group_1_1__03910 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_rule__Columns__Group_1_1__0__Impl3938 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Columns__Group_1_1__1__Impl_in_rule__Columns__Group_1_1__13969 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Columns__EntriesAssignment_1_1_1_in_rule__Columns__Group_1_1__1__Impl3996 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ColumnOrAlias__Group_1__0__Impl_in_rule__ColumnOrAlias__Group_1__04030 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_rule__ColumnOrAlias__Group_1__1_in_rule__ColumnOrAlias__Group_1__04033 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnFull_in_rule__ColumnOrAlias__Group_1__0__Impl4060 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ColumnOrAlias__Group_1__1__Impl_in_rule__ColumnOrAlias__Group_1__14089 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__ColumnOrAlias__Group_1__2_in_rule__ColumnOrAlias__Group_1__14092 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_29_in_rule__ColumnOrAlias__Group_1__1__Impl4120 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ColumnOrAlias__Group_1__2__Impl_in_rule__ColumnOrAlias__Group_1__24151 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ColumnOrAlias__ColAliasAssignment_1_2_in_rule__ColumnOrAlias__Group_1__2__Impl4178 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ColumnOrAlias__Group_2__0__Impl_in_rule__ColumnOrAlias__Group_2__04214 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__ColumnOrAlias__Group_2__1_in_rule__ColumnOrAlias__Group_2__04217 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnFull_in_rule__ColumnOrAlias__Group_2__0__Impl4244 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ColumnOrAlias__Group_2__1__Impl_in_rule__ColumnOrAlias__Group_2__14273 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ColumnOrAlias__ColAliasAssignment_2_1_in_rule__ColumnOrAlias__Group_2__1__Impl4300 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ColumnFull__Group_1__0__Impl_in_rule__ColumnFull__Group_1__04334 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_rule__ColumnFull__Group_1__1_in_rule__ColumnFull__Group_1__04337 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTableFull_in_rule__ColumnFull__Group_1__0__Impl4364 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ColumnFull__Group_1__1__Impl_in_rule__ColumnFull__Group_1__14393 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__ColumnFull__Group_1__2_in_rule__ColumnFull__Group_1__14396 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_30_in_rule__ColumnFull__Group_1__1__Impl4424 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ColumnFull__Group_1__2__Impl_in_rule__ColumnFull__Group_1__24455 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ColumnFull__ColNameAssignment_1_2_in_rule__ColumnFull__Group_1__2__Impl4482 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Tables__Group__0__Impl_in_rule__Tables__Group__04518 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_rule__Tables__Group__1_in_rule__Tables__Group__04521 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTableOrAlias_in_rule__Tables__Group__0__Impl4548 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Tables__Group__1__Impl_in_rule__Tables__Group__14577 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Tables__Group_1__0_in_rule__Tables__Group__1__Impl4604 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Tables__Group_1__0__Impl_in_rule__Tables__Group_1__04639 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_rule__Tables__Group_1__1_in_rule__Tables__Group_1__04642 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Tables__Group_1__1__Impl_in_rule__Tables__Group_1__14700 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Tables__Group_1_1__0_in_rule__Tables__Group_1__1__Impl4729 = new BitSet(new long[]{0x0000000010000002L});
    public static final BitSet FOLLOW_rule__Tables__Group_1_1__0_in_rule__Tables__Group_1__1__Impl4741 = new BitSet(new long[]{0x0000000010000002L});
    public static final BitSet FOLLOW_rule__Tables__Group_1_1__0__Impl_in_rule__Tables__Group_1_1__04778 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__Tables__Group_1_1__1_in_rule__Tables__Group_1_1__04781 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_rule__Tables__Group_1_1__0__Impl4809 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Tables__Group_1_1__1__Impl_in_rule__Tables__Group_1_1__14840 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Tables__EntriesAssignment_1_1_1_in_rule__Tables__Group_1_1__1__Impl4867 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TableOrAlias__Group_1__0__Impl_in_rule__TableOrAlias__Group_1__04901 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_rule__TableOrAlias__Group_1__1_in_rule__TableOrAlias__Group_1__04904 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTableFull_in_rule__TableOrAlias__Group_1__0__Impl4931 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TableOrAlias__Group_1__1__Impl_in_rule__TableOrAlias__Group_1__14960 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__TableOrAlias__Group_1__2_in_rule__TableOrAlias__Group_1__14963 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_29_in_rule__TableOrAlias__Group_1__1__Impl4991 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TableOrAlias__Group_1__2__Impl_in_rule__TableOrAlias__Group_1__25022 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TableOrAlias__TblAliasAssignment_1_2_in_rule__TableOrAlias__Group_1__2__Impl5049 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TableOrAlias__Group_2__0__Impl_in_rule__TableOrAlias__Group_2__05085 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__TableOrAlias__Group_2__1_in_rule__TableOrAlias__Group_2__05088 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTableFull_in_rule__TableOrAlias__Group_2__0__Impl5115 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TableOrAlias__Group_2__1__Impl_in_rule__TableOrAlias__Group_2__15144 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TableOrAlias__TblAliasAssignment_2_1_in_rule__TableOrAlias__Group_2__1__Impl5171 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TableFull__Group_0__0__Impl_in_rule__TableFull__Group_0__05205 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_rule__TableFull__Group_0__1_in_rule__TableFull__Group_0__05208 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSchema_in_rule__TableFull__Group_0__0__Impl5235 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TableFull__Group_0__1__Impl_in_rule__TableFull__Group_0__15264 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__TableFull__Group_0__2_in_rule__TableFull__Group_0__15267 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_30_in_rule__TableFull__Group_0__1__Impl5295 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TableFull__Group_0__2__Impl_in_rule__TableFull__Group_0__25326 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TableFull__TblAssignment_0_2_in_rule__TableFull__Group_0__2__Impl5353 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Schema__Group_0__0__Impl_in_rule__Schema__Group_0__05389 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_rule__Schema__Group_0__1_in_rule__Schema__Group_0__05392 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDatabase_in_rule__Schema__Group_0__0__Impl5419 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Schema__Group_0__1__Impl_in_rule__Schema__Group_0__15448 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__Schema__Group_0__2_in_rule__Schema__Group_0__15451 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_30_in_rule__Schema__Group_0__1__Impl5479 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Schema__Group_0__2__Impl_in_rule__Schema__Group_0__25510 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Schema__SchemAssignment_0_2_in_rule__Schema__Group_0__2__Impl5537 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__WhereEntry__Group__0__Impl_in_rule__WhereEntry__Group__05573 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_rule__WhereEntry__Group__1_in_rule__WhereEntry__Group__05576 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAndWhereEntry_in_rule__WhereEntry__Group__0__Impl5603 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__WhereEntry__Group__1__Impl_in_rule__WhereEntry__Group__15632 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__WhereEntry__Group_1__0_in_rule__WhereEntry__Group__1__Impl5659 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__WhereEntry__Group_1__0__Impl_in_rule__WhereEntry__Group_1__05694 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_rule__WhereEntry__Group_1__1_in_rule__WhereEntry__Group_1__05697 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__WhereEntry__Group_1__1__Impl_in_rule__WhereEntry__Group_1__15755 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__WhereEntry__Group_1_1__0_in_rule__WhereEntry__Group_1__1__Impl5784 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_rule__WhereEntry__Group_1_1__0_in_rule__WhereEntry__Group_1__1__Impl5796 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_rule__WhereEntry__Group_1_1__0__Impl_in_rule__WhereEntry__Group_1_1__05833 = new BitSet(new long[]{0x0000000200000010L});
    public static final BitSet FOLLOW_rule__WhereEntry__Group_1_1__1_in_rule__WhereEntry__Group_1_1__05836 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_rule__WhereEntry__Group_1_1__0__Impl5864 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__WhereEntry__Group_1_1__1__Impl_in_rule__WhereEntry__Group_1_1__15895 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__WhereEntry__EntriesAssignment_1_1_1_in_rule__WhereEntry__Group_1_1__1__Impl5922 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__AndWhereEntry__Group__0__Impl_in_rule__AndWhereEntry__Group__05956 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_rule__AndWhereEntry__Group__1_in_rule__AndWhereEntry__Group__05959 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleConcreteWhereEntry_in_rule__AndWhereEntry__Group__0__Impl5986 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__AndWhereEntry__Group__1__Impl_in_rule__AndWhereEntry__Group__16015 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__AndWhereEntry__Group_1__0_in_rule__AndWhereEntry__Group__1__Impl6042 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__AndWhereEntry__Group_1__0__Impl_in_rule__AndWhereEntry__Group_1__06077 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_rule__AndWhereEntry__Group_1__1_in_rule__AndWhereEntry__Group_1__06080 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__AndWhereEntry__Group_1__1__Impl_in_rule__AndWhereEntry__Group_1__16138 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__AndWhereEntry__Group_1_1__0_in_rule__AndWhereEntry__Group_1__1__Impl6167 = new BitSet(new long[]{0x0000000100000002L});
    public static final BitSet FOLLOW_rule__AndWhereEntry__Group_1_1__0_in_rule__AndWhereEntry__Group_1__1__Impl6179 = new BitSet(new long[]{0x0000000100000002L});
    public static final BitSet FOLLOW_rule__AndWhereEntry__Group_1_1__0__Impl_in_rule__AndWhereEntry__Group_1_1__06216 = new BitSet(new long[]{0x0000000200000010L});
    public static final BitSet FOLLOW_rule__AndWhereEntry__Group_1_1__1_in_rule__AndWhereEntry__Group_1_1__06219 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_rule__AndWhereEntry__Group_1_1__0__Impl6247 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__AndWhereEntry__Group_1_1__1__Impl_in_rule__AndWhereEntry__Group_1_1__16278 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__AndWhereEntry__EntriesAssignment_1_1_1_in_rule__AndWhereEntry__Group_1_1__1__Impl6305 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ParWhereEntry__Group__0__Impl_in_rule__ParWhereEntry__Group__06339 = new BitSet(new long[]{0x0000000200000010L});
    public static final BitSet FOLLOW_rule__ParWhereEntry__Group__1_in_rule__ParWhereEntry__Group__06342 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_33_in_rule__ParWhereEntry__Group__0__Impl6370 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ParWhereEntry__Group__1__Impl_in_rule__ParWhereEntry__Group__16401 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_rule__ParWhereEntry__Group__2_in_rule__ParWhereEntry__Group__16404 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleWhereEntry_in_rule__ParWhereEntry__Group__1__Impl6431 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ParWhereEntry__Group__2__Impl_in_rule__ParWhereEntry__Group__26460 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_34_in_rule__ParWhereEntry__Group__2__Impl6488 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SingleExpressionWhereEntry__Group__0__Impl_in_rule__SingleExpressionWhereEntry__Group__06525 = new BitSet(new long[]{0x0000000001FF8000L});
    public static final BitSet FOLLOW_rule__SingleExpressionWhereEntry__Group__1_in_rule__SingleExpressionWhereEntry__Group__06528 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SingleExpressionWhereEntry__NameAssignment_0_in_rule__SingleExpressionWhereEntry__Group__0__Impl6555 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SingleExpressionWhereEntry__Group__1__Impl_in_rule__SingleExpressionWhereEntry__Group__16585 = new BitSet(new long[]{0x000001E0000001E0L});
    public static final BitSet FOLLOW_rule__SingleExpressionWhereEntry__Group__2_in_rule__SingleExpressionWhereEntry__Group__16588 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SingleExpressionWhereEntry__OperatorAssignment_1_in_rule__SingleExpressionWhereEntry__Group__1__Impl6615 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SingleExpressionWhereEntry__Group__2__Impl_in_rule__SingleExpressionWhereEntry__Group__26645 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SingleExpressionWhereEntry__RhsAssignment_2_in_rule__SingleExpressionWhereEntry__Group__2__Impl6672 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__MultiExpressionWhereEntry__Group__0__Impl_in_rule__MultiExpressionWhereEntry__Group__06708 = new BitSet(new long[]{0x0000000000018000L});
    public static final BitSet FOLLOW_rule__MultiExpressionWhereEntry__Group__1_in_rule__MultiExpressionWhereEntry__Group__06711 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__MultiExpressionWhereEntry__NameAssignment_0_in_rule__MultiExpressionWhereEntry__Group__0__Impl6738 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__MultiExpressionWhereEntry__Group__1__Impl_in_rule__MultiExpressionWhereEntry__Group__16768 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_rule__MultiExpressionWhereEntry__Group__2_in_rule__MultiExpressionWhereEntry__Group__16771 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__MultiExpressionWhereEntry__OperatorAssignment_1_in_rule__MultiExpressionWhereEntry__Group__1__Impl6798 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__MultiExpressionWhereEntry__Group__2__Impl_in_rule__MultiExpressionWhereEntry__Group__26828 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__MultiExpressionWhereEntry__RhsAssignment_2_in_rule__MultiExpressionWhereEntry__Group__2__Impl6855 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__DoubleArrayExpression__Group__0__Impl_in_rule__DoubleArrayExpression__Group__06891 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__DoubleArrayExpression__Group__1_in_rule__DoubleArrayExpression__Group__06894 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_35_in_rule__DoubleArrayExpression__Group__0__Impl6922 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__DoubleArrayExpression__Group__1__Impl_in_rule__DoubleArrayExpression__Group__16953 = new BitSet(new long[]{0x0000001010000000L});
    public static final BitSet FOLLOW_rule__DoubleArrayExpression__Group__2_in_rule__DoubleArrayExpression__Group__16956 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__DoubleArrayExpression__ValuesAssignment_1_in_rule__DoubleArrayExpression__Group__1__Impl6983 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__DoubleArrayExpression__Group__2__Impl_in_rule__DoubleArrayExpression__Group__27013 = new BitSet(new long[]{0x0000001010000000L});
    public static final BitSet FOLLOW_rule__DoubleArrayExpression__Group__3_in_rule__DoubleArrayExpression__Group__27016 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__DoubleArrayExpression__Group_2__0_in_rule__DoubleArrayExpression__Group__2__Impl7043 = new BitSet(new long[]{0x0000000010000002L});
    public static final BitSet FOLLOW_rule__DoubleArrayExpression__Group__3__Impl_in_rule__DoubleArrayExpression__Group__37074 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_36_in_rule__DoubleArrayExpression__Group__3__Impl7102 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__DoubleArrayExpression__Group_2__0__Impl_in_rule__DoubleArrayExpression__Group_2__07141 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__DoubleArrayExpression__Group_2__1_in_rule__DoubleArrayExpression__Group_2__07144 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_rule__DoubleArrayExpression__Group_2__0__Impl7172 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__DoubleArrayExpression__Group_2__1__Impl_in_rule__DoubleArrayExpression__Group_2__17203 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__DoubleArrayExpression__ValuesAssignment_2_1_in_rule__DoubleArrayExpression__Group_2__1__Impl7230 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__LongArrayExpression__Group__0__Impl_in_rule__LongArrayExpression__Group__07264 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_rule__LongArrayExpression__Group__1_in_rule__LongArrayExpression__Group__07267 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_35_in_rule__LongArrayExpression__Group__0__Impl7295 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__LongArrayExpression__Group__1__Impl_in_rule__LongArrayExpression__Group__17326 = new BitSet(new long[]{0x0000001010000000L});
    public static final BitSet FOLLOW_rule__LongArrayExpression__Group__2_in_rule__LongArrayExpression__Group__17329 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__LongArrayExpression__ValuesAssignment_1_in_rule__LongArrayExpression__Group__1__Impl7356 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__LongArrayExpression__Group__2__Impl_in_rule__LongArrayExpression__Group__27386 = new BitSet(new long[]{0x0000001010000000L});
    public static final BitSet FOLLOW_rule__LongArrayExpression__Group__3_in_rule__LongArrayExpression__Group__27389 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__LongArrayExpression__Group_2__0_in_rule__LongArrayExpression__Group__2__Impl7416 = new BitSet(new long[]{0x0000000010000002L});
    public static final BitSet FOLLOW_rule__LongArrayExpression__Group__3__Impl_in_rule__LongArrayExpression__Group__37447 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_36_in_rule__LongArrayExpression__Group__3__Impl7475 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__LongArrayExpression__Group_2__0__Impl_in_rule__LongArrayExpression__Group_2__07514 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_rule__LongArrayExpression__Group_2__1_in_rule__LongArrayExpression__Group_2__07517 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_rule__LongArrayExpression__Group_2__0__Impl7545 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__LongArrayExpression__Group_2__1__Impl_in_rule__LongArrayExpression__Group_2__17576 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__LongArrayExpression__ValuesAssignment_2_1_in_rule__LongArrayExpression__Group_2__1__Impl7603 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__StringArrayExpression__Group__0__Impl_in_rule__StringArrayExpression__Group__07637 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_rule__StringArrayExpression__Group__1_in_rule__StringArrayExpression__Group__07640 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_35_in_rule__StringArrayExpression__Group__0__Impl7668 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__StringArrayExpression__Group__1__Impl_in_rule__StringArrayExpression__Group__17699 = new BitSet(new long[]{0x0000001010000000L});
    public static final BitSet FOLLOW_rule__StringArrayExpression__Group__2_in_rule__StringArrayExpression__Group__17702 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__StringArrayExpression__ValuesAssignment_1_in_rule__StringArrayExpression__Group__1__Impl7729 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__StringArrayExpression__Group__2__Impl_in_rule__StringArrayExpression__Group__27759 = new BitSet(new long[]{0x0000001010000000L});
    public static final BitSet FOLLOW_rule__StringArrayExpression__Group__3_in_rule__StringArrayExpression__Group__27762 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__StringArrayExpression__Group_2__0_in_rule__StringArrayExpression__Group__2__Impl7789 = new BitSet(new long[]{0x0000000010000002L});
    public static final BitSet FOLLOW_rule__StringArrayExpression__Group__3__Impl_in_rule__StringArrayExpression__Group__37820 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_36_in_rule__StringArrayExpression__Group__3__Impl7848 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__StringArrayExpression__Group_2__0__Impl_in_rule__StringArrayExpression__Group_2__07887 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_rule__StringArrayExpression__Group_2__1_in_rule__StringArrayExpression__Group_2__07890 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_rule__StringArrayExpression__Group_2__0__Impl7918 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__StringArrayExpression__Group_2__1__Impl_in_rule__StringArrayExpression__Group_2__17949 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__StringArrayExpression__ValuesAssignment_2_1_in_rule__StringArrayExpression__Group_2__1__Impl7976 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NullArrayExpression__Group__0__Impl_in_rule__NullArrayExpression__Group__08010 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_rule__NullArrayExpression__Group__1_in_rule__NullArrayExpression__Group__08013 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_35_in_rule__NullArrayExpression__Group__0__Impl8041 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NullArrayExpression__Group__1__Impl_in_rule__NullArrayExpression__Group__18072 = new BitSet(new long[]{0x0000001010000000L});
    public static final BitSet FOLLOW_rule__NullArrayExpression__Group__2_in_rule__NullArrayExpression__Group__18075 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NullArrayExpression__ValuesAssignment_1_in_rule__NullArrayExpression__Group__1__Impl8102 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NullArrayExpression__Group__2__Impl_in_rule__NullArrayExpression__Group__28132 = new BitSet(new long[]{0x0000001010000000L});
    public static final BitSet FOLLOW_rule__NullArrayExpression__Group__3_in_rule__NullArrayExpression__Group__28135 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NullArrayExpression__Group_2__0_in_rule__NullArrayExpression__Group__2__Impl8162 = new BitSet(new long[]{0x0000000010000002L});
    public static final BitSet FOLLOW_rule__NullArrayExpression__Group__3__Impl_in_rule__NullArrayExpression__Group__38193 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_36_in_rule__NullArrayExpression__Group__3__Impl8221 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NullArrayExpression__Group_2__0__Impl_in_rule__NullArrayExpression__Group_2__08260 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_rule__NullArrayExpression__Group_2__1_in_rule__NullArrayExpression__Group_2__08263 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_rule__NullArrayExpression__Group_2__0__Impl8291 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NullArrayExpression__Group_2__1__Impl_in_rule__NullArrayExpression__Group_2__18322 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NullArrayExpression__ValuesAssignment_2_1_in_rule__NullArrayExpression__Group_2__1__Impl8349 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__DateArrayExpression__Group__0__Impl_in_rule__DateArrayExpression__Group__08383 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_rule__DateArrayExpression__Group__1_in_rule__DateArrayExpression__Group__08386 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_35_in_rule__DateArrayExpression__Group__0__Impl8414 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__DateArrayExpression__Group__1__Impl_in_rule__DateArrayExpression__Group__18445 = new BitSet(new long[]{0x0000001010000000L});
    public static final BitSet FOLLOW_rule__DateArrayExpression__Group__2_in_rule__DateArrayExpression__Group__18448 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__DateArrayExpression__ValuesAssignment_1_in_rule__DateArrayExpression__Group__1__Impl8475 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__DateArrayExpression__Group__2__Impl_in_rule__DateArrayExpression__Group__28505 = new BitSet(new long[]{0x0000001010000000L});
    public static final BitSet FOLLOW_rule__DateArrayExpression__Group__3_in_rule__DateArrayExpression__Group__28508 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__DateArrayExpression__Group_2__0_in_rule__DateArrayExpression__Group__2__Impl8535 = new BitSet(new long[]{0x0000000010000002L});
    public static final BitSet FOLLOW_rule__DateArrayExpression__Group__3__Impl_in_rule__DateArrayExpression__Group__38566 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_36_in_rule__DateArrayExpression__Group__3__Impl8594 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__DateArrayExpression__Group_2__0__Impl_in_rule__DateArrayExpression__Group_2__08633 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_rule__DateArrayExpression__Group_2__1_in_rule__DateArrayExpression__Group_2__08636 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_rule__DateArrayExpression__Group_2__0__Impl8664 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__DateArrayExpression__Group_2__1__Impl_in_rule__DateArrayExpression__Group_2__18695 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__DateArrayExpression__ValuesAssignment_2_1_in_rule__DateArrayExpression__Group_2__1__Impl8722 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__BooleanArrayExpression__Group__0__Impl_in_rule__BooleanArrayExpression__Group__08756 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_rule__BooleanArrayExpression__Group__1_in_rule__BooleanArrayExpression__Group__08759 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_35_in_rule__BooleanArrayExpression__Group__0__Impl8787 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__BooleanArrayExpression__Group__1__Impl_in_rule__BooleanArrayExpression__Group__18818 = new BitSet(new long[]{0x0000001010000000L});
    public static final BitSet FOLLOW_rule__BooleanArrayExpression__Group__2_in_rule__BooleanArrayExpression__Group__18821 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__BooleanArrayExpression__ValuesAssignment_1_in_rule__BooleanArrayExpression__Group__1__Impl8848 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__BooleanArrayExpression__Group__2__Impl_in_rule__BooleanArrayExpression__Group__28878 = new BitSet(new long[]{0x0000001010000000L});
    public static final BitSet FOLLOW_rule__BooleanArrayExpression__Group__3_in_rule__BooleanArrayExpression__Group__28881 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__BooleanArrayExpression__Group_2__0_in_rule__BooleanArrayExpression__Group__2__Impl8908 = new BitSet(new long[]{0x0000000010000002L});
    public static final BitSet FOLLOW_rule__BooleanArrayExpression__Group__3__Impl_in_rule__BooleanArrayExpression__Group__38939 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_36_in_rule__BooleanArrayExpression__Group__3__Impl8967 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__BooleanArrayExpression__Group_2__0__Impl_in_rule__BooleanArrayExpression__Group_2__09006 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_rule__BooleanArrayExpression__Group_2__1_in_rule__BooleanArrayExpression__Group_2__09009 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_rule__BooleanArrayExpression__Group_2__0__Impl9037 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__BooleanArrayExpression__Group_2__1__Impl_in_rule__BooleanArrayExpression__Group_2__19068 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__BooleanArrayExpression__ValuesAssignment_2_1_in_rule__BooleanArrayExpression__Group_2__1__Impl9095 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumns_in_rule__Model__ColAssignment_19134 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTables_in_rule__Model__TblAssignment_39165 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleWhereEntry_in_rule__Model__WhereEntryAssignment_4_19196 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnOrAlias_in_rule__Columns__EntriesAssignment_1_1_19227 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnAlias_in_rule__ColumnOrAlias__ColAliasAssignment_1_29258 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnAlias_in_rule__ColumnOrAlias__ColAliasAssignment_2_19289 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumn_in_rule__ColumnFull__ColNameAssignment_09320 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumn_in_rule__ColumnFull__ColNameAssignment_1_29351 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__ColumnAlias__ColAliasAssignment9382 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__Column__ColNameAssignment9413 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTableOrAlias_in_rule__Tables__EntriesAssignment_1_1_19444 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTableAlias_in_rule__TableOrAlias__TblAliasAssignment_1_29475 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTableAlias_in_rule__TableOrAlias__TblAliasAssignment_2_19506 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTable_in_rule__TableFull__TblAssignment_0_29537 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTable_in_rule__TableFull__TblAssignment_19568 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__Table__TblAssignment9599 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__TableAlias__TblAliasAssignment9630 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__Schema__SchemAssignment_0_29661 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__Schema__SchemAssignment_19692 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__Database__DbNameAssignment9723 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAndWhereEntry_in_rule__WhereEntry__EntriesAssignment_1_1_19754 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleConcreteWhereEntry_in_rule__AndWhereEntry__EntriesAssignment_1_1_19785 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__SingleExpressionWhereEntry__NameAssignment_09816 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperator_in_rule__SingleExpressionWhereEntry__OperatorAssignment_19847 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression_in_rule__SingleExpressionWhereEntry__RhsAssignment_29878 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_37_in_rule__ReplacableValue__ValueAssignment9914 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_SIGNED_DOUBLE_in_rule__DoubleExpression__ValueAssignment9953 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_SINGED_LONG_in_rule__LongExpression__ValueAssignment9984 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__StringExpression__ValueAssignment10015 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_38_in_rule__NullExpression__ValueAssignment10051 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_DATE_in_rule__DateExpression__ValueAssignment10090 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_39_in_rule__BooleanExpression__TrueAssignment_010126 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_40_in_rule__BooleanExpression__TrueAssignment_110170 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__MultiExpressionWhereEntry__NameAssignment_010209 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleArrayOperator_in_rule__MultiExpressionWhereEntry__OperatorAssignment_110240 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleArrayExpression_in_rule__MultiExpressionWhereEntry__RhsAssignment_210271 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_SIGNED_DOUBLE_in_rule__DoubleArrayExpression__ValuesAssignment_110302 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_SIGNED_DOUBLE_in_rule__DoubleArrayExpression__ValuesAssignment_2_110333 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_SINGED_LONG_in_rule__LongArrayExpression__ValuesAssignment_110364 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_SINGED_LONG_in_rule__LongArrayExpression__ValuesAssignment_2_110395 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__StringArrayExpression__ValuesAssignment_110426 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__StringArrayExpression__ValuesAssignment_2_110457 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_38_in_rule__NullArrayExpression__ValuesAssignment_110493 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_38_in_rule__NullArrayExpression__ValuesAssignment_2_110537 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_DATE_in_rule__DateArrayExpression__ValuesAssignment_110576 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_DATE_in_rule__DateArrayExpression__ValuesAssignment_2_110607 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_BOOL_in_rule__BooleanArrayExpression__ValuesAssignment_110638 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_BOOL_in_rule__BooleanArrayExpression__ValuesAssignment_2_110669 = new BitSet(new long[]{0x0000000000000002L});

}