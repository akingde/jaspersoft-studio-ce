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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_SIGNED_DOUBLE", "RULE_SINGED_LONG", "RULE_STRING", "RULE_DATE", "RULE_BOOL", "RULE_INT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'in'", "'not in'", "'<'", "'>'", "'<='", "'>='", "'='", "'!='", "'like'", "'not like'", "'SELECT'", "'FROM'", "'WHERE'", "'GROUP BY'", "'HAVING'", "'ORDER BY'", "','", "'.'", "'AS'", "'OR'", "'AND'", "'('", "')'", "'['", "']'", "'?'", "'null'", "'true'", "'false'"
    };
    public static final int RULE_ID=4;
    public static final int T__29=29;
    public static final int RULE_DATE=8;
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
    public static final int RULE_BOOL=9;
    public static final int EOF=-1;
    public static final int T__19=19;
    public static final int T__16=16;
    public static final int T__15=15;
    public static final int T__18=18;
    public static final int T__17=17;
    public static final int RULE_SIGNED_DOUBLE=5;
    public static final int RULE_INT=10;
    public static final int T__42=42;
    public static final int T__43=43;
    public static final int T__40=40;
    public static final int T__41=41;
    public static final int RULE_SINGED_LONG=6;
    public static final int RULE_SL_COMMENT=12;
    public static final int RULE_ML_COMMENT=11;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int T__32=32;
    public static final int RULE_STRING=7;
    public static final int T__33=33;
    public static final int T__34=34;
    public static final int T__35=35;
    public static final int T__36=36;
    public static final int T__37=37;
    public static final int T__38=38;
    public static final int T__39=39;
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


    // $ANTLR start "entryRuleOrderByColumns"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:88:1: entryRuleOrderByColumns : ruleOrderByColumns EOF ;
    public final void entryRuleOrderByColumns() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:89:1: ( ruleOrderByColumns EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:90:1: ruleOrderByColumns EOF
            {
             before(grammarAccess.getOrderByColumnsRule()); 
            pushFollow(FOLLOW_ruleOrderByColumns_in_entryRuleOrderByColumns121);
            ruleOrderByColumns();

            state._fsp--;

             after(grammarAccess.getOrderByColumnsRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOrderByColumns128); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:97:1: ruleOrderByColumns : ( ( rule__OrderByColumns__Group__0 ) ) ;
    public final void ruleOrderByColumns() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:101:2: ( ( ( rule__OrderByColumns__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:102:1: ( ( rule__OrderByColumns__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:102:1: ( ( rule__OrderByColumns__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:103:1: ( rule__OrderByColumns__Group__0 )
            {
             before(grammarAccess.getOrderByColumnsAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:104:1: ( rule__OrderByColumns__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:104:2: rule__OrderByColumns__Group__0
            {
            pushFollow(FOLLOW_rule__OrderByColumns__Group__0_in_ruleOrderByColumns154);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:116:1: entryRuleOrderByColumnFull : ruleOrderByColumnFull EOF ;
    public final void entryRuleOrderByColumnFull() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:117:1: ( ruleOrderByColumnFull EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:118:1: ruleOrderByColumnFull EOF
            {
             before(grammarAccess.getOrderByColumnFullRule()); 
            pushFollow(FOLLOW_ruleOrderByColumnFull_in_entryRuleOrderByColumnFull181);
            ruleOrderByColumnFull();

            state._fsp--;

             after(grammarAccess.getOrderByColumnFullRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOrderByColumnFull188); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:125:1: ruleOrderByColumnFull : ( ( rule__OrderByColumnFull__Alternatives ) ) ;
    public final void ruleOrderByColumnFull() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:129:2: ( ( ( rule__OrderByColumnFull__Alternatives ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:130:1: ( ( rule__OrderByColumnFull__Alternatives ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:130:1: ( ( rule__OrderByColumnFull__Alternatives ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:131:1: ( rule__OrderByColumnFull__Alternatives )
            {
             before(grammarAccess.getOrderByColumnFullAccess().getAlternatives()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:132:1: ( rule__OrderByColumnFull__Alternatives )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:132:2: rule__OrderByColumnFull__Alternatives
            {
            pushFollow(FOLLOW_rule__OrderByColumnFull__Alternatives_in_ruleOrderByColumnFull214);
            rule__OrderByColumnFull__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getOrderByColumnFullAccess().getAlternatives()); 

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


    // $ANTLR start "entryRuleGroupByColumns"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:144:1: entryRuleGroupByColumns : ruleGroupByColumns EOF ;
    public final void entryRuleGroupByColumns() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:145:1: ( ruleGroupByColumns EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:146:1: ruleGroupByColumns EOF
            {
             before(grammarAccess.getGroupByColumnsRule()); 
            pushFollow(FOLLOW_ruleGroupByColumns_in_entryRuleGroupByColumns241);
            ruleGroupByColumns();

            state._fsp--;

             after(grammarAccess.getGroupByColumnsRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleGroupByColumns248); 

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
    // $ANTLR end "entryRuleGroupByColumns"


    // $ANTLR start "ruleGroupByColumns"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:153:1: ruleGroupByColumns : ( ( rule__GroupByColumns__Group__0 ) ) ;
    public final void ruleGroupByColumns() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:157:2: ( ( ( rule__GroupByColumns__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:158:1: ( ( rule__GroupByColumns__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:158:1: ( ( rule__GroupByColumns__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:159:1: ( rule__GroupByColumns__Group__0 )
            {
             before(grammarAccess.getGroupByColumnsAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:160:1: ( rule__GroupByColumns__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:160:2: rule__GroupByColumns__Group__0
            {
            pushFollow(FOLLOW_rule__GroupByColumns__Group__0_in_ruleGroupByColumns274);
            rule__GroupByColumns__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getGroupByColumnsAccess().getGroup()); 

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
    // $ANTLR end "ruleGroupByColumns"


    // $ANTLR start "entryRuleGroupByColumnFull"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:172:1: entryRuleGroupByColumnFull : ruleGroupByColumnFull EOF ;
    public final void entryRuleGroupByColumnFull() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:173:1: ( ruleGroupByColumnFull EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:174:1: ruleGroupByColumnFull EOF
            {
             before(grammarAccess.getGroupByColumnFullRule()); 
            pushFollow(FOLLOW_ruleGroupByColumnFull_in_entryRuleGroupByColumnFull301);
            ruleGroupByColumnFull();

            state._fsp--;

             after(grammarAccess.getGroupByColumnFullRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleGroupByColumnFull308); 

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
    // $ANTLR end "entryRuleGroupByColumnFull"


    // $ANTLR start "ruleGroupByColumnFull"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:181:1: ruleGroupByColumnFull : ( ( rule__GroupByColumnFull__Alternatives ) ) ;
    public final void ruleGroupByColumnFull() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:185:2: ( ( ( rule__GroupByColumnFull__Alternatives ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:186:1: ( ( rule__GroupByColumnFull__Alternatives ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:186:1: ( ( rule__GroupByColumnFull__Alternatives ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:187:1: ( rule__GroupByColumnFull__Alternatives )
            {
             before(grammarAccess.getGroupByColumnFullAccess().getAlternatives()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:188:1: ( rule__GroupByColumnFull__Alternatives )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:188:2: rule__GroupByColumnFull__Alternatives
            {
            pushFollow(FOLLOW_rule__GroupByColumnFull__Alternatives_in_ruleGroupByColumnFull334);
            rule__GroupByColumnFull__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getGroupByColumnFullAccess().getAlternatives()); 

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
    // $ANTLR end "ruleGroupByColumnFull"


    // $ANTLR start "entryRuleColumns"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:200:1: entryRuleColumns : ruleColumns EOF ;
    public final void entryRuleColumns() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:201:1: ( ruleColumns EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:202:1: ruleColumns EOF
            {
             before(grammarAccess.getColumnsRule()); 
            pushFollow(FOLLOW_ruleColumns_in_entryRuleColumns361);
            ruleColumns();

            state._fsp--;

             after(grammarAccess.getColumnsRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleColumns368); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:209:1: ruleColumns : ( ( rule__Columns__Group__0 ) ) ;
    public final void ruleColumns() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:213:2: ( ( ( rule__Columns__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:214:1: ( ( rule__Columns__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:214:1: ( ( rule__Columns__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:215:1: ( rule__Columns__Group__0 )
            {
             before(grammarAccess.getColumnsAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:216:1: ( rule__Columns__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:216:2: rule__Columns__Group__0
            {
            pushFollow(FOLLOW_rule__Columns__Group__0_in_ruleColumns394);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:228:1: entryRuleColumnOrAlias : ruleColumnOrAlias EOF ;
    public final void entryRuleColumnOrAlias() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:229:1: ( ruleColumnOrAlias EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:230:1: ruleColumnOrAlias EOF
            {
             before(grammarAccess.getColumnOrAliasRule()); 
            pushFollow(FOLLOW_ruleColumnOrAlias_in_entryRuleColumnOrAlias421);
            ruleColumnOrAlias();

            state._fsp--;

             after(grammarAccess.getColumnOrAliasRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleColumnOrAlias428); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:237:1: ruleColumnOrAlias : ( ( rule__ColumnOrAlias__Alternatives ) ) ;
    public final void ruleColumnOrAlias() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:241:2: ( ( ( rule__ColumnOrAlias__Alternatives ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:242:1: ( ( rule__ColumnOrAlias__Alternatives ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:242:1: ( ( rule__ColumnOrAlias__Alternatives ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:243:1: ( rule__ColumnOrAlias__Alternatives )
            {
             before(grammarAccess.getColumnOrAliasAccess().getAlternatives()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:244:1: ( rule__ColumnOrAlias__Alternatives )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:244:2: rule__ColumnOrAlias__Alternatives
            {
            pushFollow(FOLLOW_rule__ColumnOrAlias__Alternatives_in_ruleColumnOrAlias454);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:256:1: entryRuleColumnFull : ruleColumnFull EOF ;
    public final void entryRuleColumnFull() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:257:1: ( ruleColumnFull EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:258:1: ruleColumnFull EOF
            {
             before(grammarAccess.getColumnFullRule()); 
            pushFollow(FOLLOW_ruleColumnFull_in_entryRuleColumnFull481);
            ruleColumnFull();

            state._fsp--;

             after(grammarAccess.getColumnFullRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleColumnFull488); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:265:1: ruleColumnFull : ( ( rule__ColumnFull__Alternatives ) ) ;
    public final void ruleColumnFull() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:269:2: ( ( ( rule__ColumnFull__Alternatives ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:270:1: ( ( rule__ColumnFull__Alternatives ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:270:1: ( ( rule__ColumnFull__Alternatives ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:271:1: ( rule__ColumnFull__Alternatives )
            {
             before(grammarAccess.getColumnFullAccess().getAlternatives()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:272:1: ( rule__ColumnFull__Alternatives )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:272:2: rule__ColumnFull__Alternatives
            {
            pushFollow(FOLLOW_rule__ColumnFull__Alternatives_in_ruleColumnFull514);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:284:1: entryRuleColumnAlias : ruleColumnAlias EOF ;
    public final void entryRuleColumnAlias() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:285:1: ( ruleColumnAlias EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:286:1: ruleColumnAlias EOF
            {
             before(grammarAccess.getColumnAliasRule()); 
            pushFollow(FOLLOW_ruleColumnAlias_in_entryRuleColumnAlias541);
            ruleColumnAlias();

            state._fsp--;

             after(grammarAccess.getColumnAliasRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleColumnAlias548); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:293:1: ruleColumnAlias : ( ( rule__ColumnAlias__ColAliasAssignment ) ) ;
    public final void ruleColumnAlias() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:297:2: ( ( ( rule__ColumnAlias__ColAliasAssignment ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:298:1: ( ( rule__ColumnAlias__ColAliasAssignment ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:298:1: ( ( rule__ColumnAlias__ColAliasAssignment ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:299:1: ( rule__ColumnAlias__ColAliasAssignment )
            {
             before(grammarAccess.getColumnAliasAccess().getColAliasAssignment()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:300:1: ( rule__ColumnAlias__ColAliasAssignment )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:300:2: rule__ColumnAlias__ColAliasAssignment
            {
            pushFollow(FOLLOW_rule__ColumnAlias__ColAliasAssignment_in_ruleColumnAlias574);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:312:1: entryRuleColumn : ruleColumn EOF ;
    public final void entryRuleColumn() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:313:1: ( ruleColumn EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:314:1: ruleColumn EOF
            {
             before(grammarAccess.getColumnRule()); 
            pushFollow(FOLLOW_ruleColumn_in_entryRuleColumn601);
            ruleColumn();

            state._fsp--;

             after(grammarAccess.getColumnRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleColumn608); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:321:1: ruleColumn : ( ( rule__Column__ColNameAssignment ) ) ;
    public final void ruleColumn() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:325:2: ( ( ( rule__Column__ColNameAssignment ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:326:1: ( ( rule__Column__ColNameAssignment ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:326:1: ( ( rule__Column__ColNameAssignment ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:327:1: ( rule__Column__ColNameAssignment )
            {
             before(grammarAccess.getColumnAccess().getColNameAssignment()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:328:1: ( rule__Column__ColNameAssignment )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:328:2: rule__Column__ColNameAssignment
            {
            pushFollow(FOLLOW_rule__Column__ColNameAssignment_in_ruleColumn634);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:340:1: entryRuleTables : ruleTables EOF ;
    public final void entryRuleTables() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:341:1: ( ruleTables EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:342:1: ruleTables EOF
            {
             before(grammarAccess.getTablesRule()); 
            pushFollow(FOLLOW_ruleTables_in_entryRuleTables661);
            ruleTables();

            state._fsp--;

             after(grammarAccess.getTablesRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleTables668); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:349:1: ruleTables : ( ( rule__Tables__Group__0 ) ) ;
    public final void ruleTables() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:353:2: ( ( ( rule__Tables__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:354:1: ( ( rule__Tables__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:354:1: ( ( rule__Tables__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:355:1: ( rule__Tables__Group__0 )
            {
             before(grammarAccess.getTablesAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:356:1: ( rule__Tables__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:356:2: rule__Tables__Group__0
            {
            pushFollow(FOLLOW_rule__Tables__Group__0_in_ruleTables694);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:368:1: entryRuleTableOrAlias : ruleTableOrAlias EOF ;
    public final void entryRuleTableOrAlias() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:369:1: ( ruleTableOrAlias EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:370:1: ruleTableOrAlias EOF
            {
             before(grammarAccess.getTableOrAliasRule()); 
            pushFollow(FOLLOW_ruleTableOrAlias_in_entryRuleTableOrAlias721);
            ruleTableOrAlias();

            state._fsp--;

             after(grammarAccess.getTableOrAliasRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleTableOrAlias728); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:377:1: ruleTableOrAlias : ( ( rule__TableOrAlias__Alternatives ) ) ;
    public final void ruleTableOrAlias() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:381:2: ( ( ( rule__TableOrAlias__Alternatives ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:382:1: ( ( rule__TableOrAlias__Alternatives ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:382:1: ( ( rule__TableOrAlias__Alternatives ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:383:1: ( rule__TableOrAlias__Alternatives )
            {
             before(grammarAccess.getTableOrAliasAccess().getAlternatives()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:384:1: ( rule__TableOrAlias__Alternatives )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:384:2: rule__TableOrAlias__Alternatives
            {
            pushFollow(FOLLOW_rule__TableOrAlias__Alternatives_in_ruleTableOrAlias754);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:396:1: entryRuleTableFull : ruleTableFull EOF ;
    public final void entryRuleTableFull() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:397:1: ( ruleTableFull EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:398:1: ruleTableFull EOF
            {
             before(grammarAccess.getTableFullRule()); 
            pushFollow(FOLLOW_ruleTableFull_in_entryRuleTableFull781);
            ruleTableFull();

            state._fsp--;

             after(grammarAccess.getTableFullRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleTableFull788); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:405:1: ruleTableFull : ( ( rule__TableFull__Alternatives ) ) ;
    public final void ruleTableFull() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:409:2: ( ( ( rule__TableFull__Alternatives ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:410:1: ( ( rule__TableFull__Alternatives ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:410:1: ( ( rule__TableFull__Alternatives ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:411:1: ( rule__TableFull__Alternatives )
            {
             before(grammarAccess.getTableFullAccess().getAlternatives()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:412:1: ( rule__TableFull__Alternatives )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:412:2: rule__TableFull__Alternatives
            {
            pushFollow(FOLLOW_rule__TableFull__Alternatives_in_ruleTableFull814);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:424:1: entryRuleTable : ruleTable EOF ;
    public final void entryRuleTable() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:425:1: ( ruleTable EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:426:1: ruleTable EOF
            {
             before(grammarAccess.getTableRule()); 
            pushFollow(FOLLOW_ruleTable_in_entryRuleTable841);
            ruleTable();

            state._fsp--;

             after(grammarAccess.getTableRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleTable848); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:433:1: ruleTable : ( ( rule__Table__TblAssignment ) ) ;
    public final void ruleTable() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:437:2: ( ( ( rule__Table__TblAssignment ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:438:1: ( ( rule__Table__TblAssignment ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:438:1: ( ( rule__Table__TblAssignment ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:439:1: ( rule__Table__TblAssignment )
            {
             before(grammarAccess.getTableAccess().getTblAssignment()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:440:1: ( rule__Table__TblAssignment )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:440:2: rule__Table__TblAssignment
            {
            pushFollow(FOLLOW_rule__Table__TblAssignment_in_ruleTable874);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:452:1: entryRuleTableAlias : ruleTableAlias EOF ;
    public final void entryRuleTableAlias() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:453:1: ( ruleTableAlias EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:454:1: ruleTableAlias EOF
            {
             before(grammarAccess.getTableAliasRule()); 
            pushFollow(FOLLOW_ruleTableAlias_in_entryRuleTableAlias901);
            ruleTableAlias();

            state._fsp--;

             after(grammarAccess.getTableAliasRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleTableAlias908); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:461:1: ruleTableAlias : ( ( rule__TableAlias__TblAliasAssignment ) ) ;
    public final void ruleTableAlias() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:465:2: ( ( ( rule__TableAlias__TblAliasAssignment ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:466:1: ( ( rule__TableAlias__TblAliasAssignment ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:466:1: ( ( rule__TableAlias__TblAliasAssignment ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:467:1: ( rule__TableAlias__TblAliasAssignment )
            {
             before(grammarAccess.getTableAliasAccess().getTblAliasAssignment()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:468:1: ( rule__TableAlias__TblAliasAssignment )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:468:2: rule__TableAlias__TblAliasAssignment
            {
            pushFollow(FOLLOW_rule__TableAlias__TblAliasAssignment_in_ruleTableAlias934);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:480:1: entryRuleSchema : ruleSchema EOF ;
    public final void entryRuleSchema() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:481:1: ( ruleSchema EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:482:1: ruleSchema EOF
            {
             before(grammarAccess.getSchemaRule()); 
            pushFollow(FOLLOW_ruleSchema_in_entryRuleSchema961);
            ruleSchema();

            state._fsp--;

             after(grammarAccess.getSchemaRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSchema968); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:489:1: ruleSchema : ( ( rule__Schema__Alternatives ) ) ;
    public final void ruleSchema() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:493:2: ( ( ( rule__Schema__Alternatives ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:494:1: ( ( rule__Schema__Alternatives ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:494:1: ( ( rule__Schema__Alternatives ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:495:1: ( rule__Schema__Alternatives )
            {
             before(grammarAccess.getSchemaAccess().getAlternatives()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:496:1: ( rule__Schema__Alternatives )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:496:2: rule__Schema__Alternatives
            {
            pushFollow(FOLLOW_rule__Schema__Alternatives_in_ruleSchema994);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:508:1: entryRuleDatabase : ruleDatabase EOF ;
    public final void entryRuleDatabase() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:509:1: ( ruleDatabase EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:510:1: ruleDatabase EOF
            {
             before(grammarAccess.getDatabaseRule()); 
            pushFollow(FOLLOW_ruleDatabase_in_entryRuleDatabase1021);
            ruleDatabase();

            state._fsp--;

             after(grammarAccess.getDatabaseRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleDatabase1028); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:517:1: ruleDatabase : ( ( rule__Database__DbNameAssignment ) ) ;
    public final void ruleDatabase() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:521:2: ( ( ( rule__Database__DbNameAssignment ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:522:1: ( ( rule__Database__DbNameAssignment ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:522:1: ( ( rule__Database__DbNameAssignment ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:523:1: ( rule__Database__DbNameAssignment )
            {
             before(grammarAccess.getDatabaseAccess().getDbNameAssignment()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:524:1: ( rule__Database__DbNameAssignment )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:524:2: rule__Database__DbNameAssignment
            {
            pushFollow(FOLLOW_rule__Database__DbNameAssignment_in_ruleDatabase1054);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:536:1: entryRuleWhereEntry : ruleWhereEntry EOF ;
    public final void entryRuleWhereEntry() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:537:1: ( ruleWhereEntry EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:538:1: ruleWhereEntry EOF
            {
             before(grammarAccess.getWhereEntryRule()); 
            pushFollow(FOLLOW_ruleWhereEntry_in_entryRuleWhereEntry1081);
            ruleWhereEntry();

            state._fsp--;

             after(grammarAccess.getWhereEntryRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleWhereEntry1088); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:545:1: ruleWhereEntry : ( ( rule__WhereEntry__Group__0 ) ) ;
    public final void ruleWhereEntry() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:549:2: ( ( ( rule__WhereEntry__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:550:1: ( ( rule__WhereEntry__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:550:1: ( ( rule__WhereEntry__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:551:1: ( rule__WhereEntry__Group__0 )
            {
             before(grammarAccess.getWhereEntryAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:552:1: ( rule__WhereEntry__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:552:2: rule__WhereEntry__Group__0
            {
            pushFollow(FOLLOW_rule__WhereEntry__Group__0_in_ruleWhereEntry1114);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:564:1: entryRuleAndWhereEntry : ruleAndWhereEntry EOF ;
    public final void entryRuleAndWhereEntry() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:565:1: ( ruleAndWhereEntry EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:566:1: ruleAndWhereEntry EOF
            {
             before(grammarAccess.getAndWhereEntryRule()); 
            pushFollow(FOLLOW_ruleAndWhereEntry_in_entryRuleAndWhereEntry1141);
            ruleAndWhereEntry();

            state._fsp--;

             after(grammarAccess.getAndWhereEntryRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAndWhereEntry1148); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:573:1: ruleAndWhereEntry : ( ( rule__AndWhereEntry__Group__0 ) ) ;
    public final void ruleAndWhereEntry() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:577:2: ( ( ( rule__AndWhereEntry__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:578:1: ( ( rule__AndWhereEntry__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:578:1: ( ( rule__AndWhereEntry__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:579:1: ( rule__AndWhereEntry__Group__0 )
            {
             before(grammarAccess.getAndWhereEntryAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:580:1: ( rule__AndWhereEntry__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:580:2: rule__AndWhereEntry__Group__0
            {
            pushFollow(FOLLOW_rule__AndWhereEntry__Group__0_in_ruleAndWhereEntry1174);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:592:1: entryRuleConcreteWhereEntry : ruleConcreteWhereEntry EOF ;
    public final void entryRuleConcreteWhereEntry() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:593:1: ( ruleConcreteWhereEntry EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:594:1: ruleConcreteWhereEntry EOF
            {
             before(grammarAccess.getConcreteWhereEntryRule()); 
            pushFollow(FOLLOW_ruleConcreteWhereEntry_in_entryRuleConcreteWhereEntry1201);
            ruleConcreteWhereEntry();

            state._fsp--;

             after(grammarAccess.getConcreteWhereEntryRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleConcreteWhereEntry1208); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:601:1: ruleConcreteWhereEntry : ( ( rule__ConcreteWhereEntry__Alternatives ) ) ;
    public final void ruleConcreteWhereEntry() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:605:2: ( ( ( rule__ConcreteWhereEntry__Alternatives ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:606:1: ( ( rule__ConcreteWhereEntry__Alternatives ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:606:1: ( ( rule__ConcreteWhereEntry__Alternatives ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:607:1: ( rule__ConcreteWhereEntry__Alternatives )
            {
             before(grammarAccess.getConcreteWhereEntryAccess().getAlternatives()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:608:1: ( rule__ConcreteWhereEntry__Alternatives )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:608:2: rule__ConcreteWhereEntry__Alternatives
            {
            pushFollow(FOLLOW_rule__ConcreteWhereEntry__Alternatives_in_ruleConcreteWhereEntry1234);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:620:1: entryRuleParWhereEntry : ruleParWhereEntry EOF ;
    public final void entryRuleParWhereEntry() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:621:1: ( ruleParWhereEntry EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:622:1: ruleParWhereEntry EOF
            {
             before(grammarAccess.getParWhereEntryRule()); 
            pushFollow(FOLLOW_ruleParWhereEntry_in_entryRuleParWhereEntry1261);
            ruleParWhereEntry();

            state._fsp--;

             after(grammarAccess.getParWhereEntryRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleParWhereEntry1268); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:629:1: ruleParWhereEntry : ( ( rule__ParWhereEntry__Group__0 ) ) ;
    public final void ruleParWhereEntry() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:633:2: ( ( ( rule__ParWhereEntry__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:634:1: ( ( rule__ParWhereEntry__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:634:1: ( ( rule__ParWhereEntry__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:635:1: ( rule__ParWhereEntry__Group__0 )
            {
             before(grammarAccess.getParWhereEntryAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:636:1: ( rule__ParWhereEntry__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:636:2: rule__ParWhereEntry__Group__0
            {
            pushFollow(FOLLOW_rule__ParWhereEntry__Group__0_in_ruleParWhereEntry1294);
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


    // $ANTLR start "entryRuleHavingEntry"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:648:1: entryRuleHavingEntry : ruleHavingEntry EOF ;
    public final void entryRuleHavingEntry() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:649:1: ( ruleHavingEntry EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:650:1: ruleHavingEntry EOF
            {
             before(grammarAccess.getHavingEntryRule()); 
            pushFollow(FOLLOW_ruleHavingEntry_in_entryRuleHavingEntry1321);
            ruleHavingEntry();

            state._fsp--;

             after(grammarAccess.getHavingEntryRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleHavingEntry1328); 

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
    // $ANTLR end "entryRuleHavingEntry"


    // $ANTLR start "ruleHavingEntry"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:657:1: ruleHavingEntry : ( ( rule__HavingEntry__Group__0 ) ) ;
    public final void ruleHavingEntry() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:661:2: ( ( ( rule__HavingEntry__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:662:1: ( ( rule__HavingEntry__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:662:1: ( ( rule__HavingEntry__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:663:1: ( rule__HavingEntry__Group__0 )
            {
             before(grammarAccess.getHavingEntryAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:664:1: ( rule__HavingEntry__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:664:2: rule__HavingEntry__Group__0
            {
            pushFollow(FOLLOW_rule__HavingEntry__Group__0_in_ruleHavingEntry1354);
            rule__HavingEntry__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getHavingEntryAccess().getGroup()); 

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
    // $ANTLR end "ruleHavingEntry"


    // $ANTLR start "entryRuleAndHavingEntry"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:676:1: entryRuleAndHavingEntry : ruleAndHavingEntry EOF ;
    public final void entryRuleAndHavingEntry() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:677:1: ( ruleAndHavingEntry EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:678:1: ruleAndHavingEntry EOF
            {
             before(grammarAccess.getAndHavingEntryRule()); 
            pushFollow(FOLLOW_ruleAndHavingEntry_in_entryRuleAndHavingEntry1381);
            ruleAndHavingEntry();

            state._fsp--;

             after(grammarAccess.getAndHavingEntryRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAndHavingEntry1388); 

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
    // $ANTLR end "entryRuleAndHavingEntry"


    // $ANTLR start "ruleAndHavingEntry"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:685:1: ruleAndHavingEntry : ( ( rule__AndHavingEntry__Group__0 ) ) ;
    public final void ruleAndHavingEntry() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:689:2: ( ( ( rule__AndHavingEntry__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:690:1: ( ( rule__AndHavingEntry__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:690:1: ( ( rule__AndHavingEntry__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:691:1: ( rule__AndHavingEntry__Group__0 )
            {
             before(grammarAccess.getAndHavingEntryAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:692:1: ( rule__AndHavingEntry__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:692:2: rule__AndHavingEntry__Group__0
            {
            pushFollow(FOLLOW_rule__AndHavingEntry__Group__0_in_ruleAndHavingEntry1414);
            rule__AndHavingEntry__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getAndHavingEntryAccess().getGroup()); 

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
    // $ANTLR end "ruleAndHavingEntry"


    // $ANTLR start "entryRuleConcreteHavingEntry"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:704:1: entryRuleConcreteHavingEntry : ruleConcreteHavingEntry EOF ;
    public final void entryRuleConcreteHavingEntry() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:705:1: ( ruleConcreteHavingEntry EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:706:1: ruleConcreteHavingEntry EOF
            {
             before(grammarAccess.getConcreteHavingEntryRule()); 
            pushFollow(FOLLOW_ruleConcreteHavingEntry_in_entryRuleConcreteHavingEntry1441);
            ruleConcreteHavingEntry();

            state._fsp--;

             after(grammarAccess.getConcreteHavingEntryRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleConcreteHavingEntry1448); 

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
    // $ANTLR end "entryRuleConcreteHavingEntry"


    // $ANTLR start "ruleConcreteHavingEntry"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:713:1: ruleConcreteHavingEntry : ( ( rule__ConcreteHavingEntry__Alternatives ) ) ;
    public final void ruleConcreteHavingEntry() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:717:2: ( ( ( rule__ConcreteHavingEntry__Alternatives ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:718:1: ( ( rule__ConcreteHavingEntry__Alternatives ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:718:1: ( ( rule__ConcreteHavingEntry__Alternatives ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:719:1: ( rule__ConcreteHavingEntry__Alternatives )
            {
             before(grammarAccess.getConcreteHavingEntryAccess().getAlternatives()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:720:1: ( rule__ConcreteHavingEntry__Alternatives )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:720:2: rule__ConcreteHavingEntry__Alternatives
            {
            pushFollow(FOLLOW_rule__ConcreteHavingEntry__Alternatives_in_ruleConcreteHavingEntry1474);
            rule__ConcreteHavingEntry__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getConcreteHavingEntryAccess().getAlternatives()); 

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
    // $ANTLR end "ruleConcreteHavingEntry"


    // $ANTLR start "entryRuleParHavingEntry"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:732:1: entryRuleParHavingEntry : ruleParHavingEntry EOF ;
    public final void entryRuleParHavingEntry() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:733:1: ( ruleParHavingEntry EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:734:1: ruleParHavingEntry EOF
            {
             before(grammarAccess.getParHavingEntryRule()); 
            pushFollow(FOLLOW_ruleParHavingEntry_in_entryRuleParHavingEntry1501);
            ruleParHavingEntry();

            state._fsp--;

             after(grammarAccess.getParHavingEntryRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleParHavingEntry1508); 

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
    // $ANTLR end "entryRuleParHavingEntry"


    // $ANTLR start "ruleParHavingEntry"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:741:1: ruleParHavingEntry : ( ( rule__ParHavingEntry__Group__0 ) ) ;
    public final void ruleParHavingEntry() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:745:2: ( ( ( rule__ParHavingEntry__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:746:1: ( ( rule__ParHavingEntry__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:746:1: ( ( rule__ParHavingEntry__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:747:1: ( rule__ParHavingEntry__Group__0 )
            {
             before(grammarAccess.getParHavingEntryAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:748:1: ( rule__ParHavingEntry__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:748:2: rule__ParHavingEntry__Group__0
            {
            pushFollow(FOLLOW_rule__ParHavingEntry__Group__0_in_ruleParHavingEntry1534);
            rule__ParHavingEntry__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getParHavingEntryAccess().getGroup()); 

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
    // $ANTLR end "ruleParHavingEntry"


    // $ANTLR start "entryRuleExpressionWhereEntry"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:760:1: entryRuleExpressionWhereEntry : ruleExpressionWhereEntry EOF ;
    public final void entryRuleExpressionWhereEntry() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:761:1: ( ruleExpressionWhereEntry EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:762:1: ruleExpressionWhereEntry EOF
            {
             before(grammarAccess.getExpressionWhereEntryRule()); 
            pushFollow(FOLLOW_ruleExpressionWhereEntry_in_entryRuleExpressionWhereEntry1561);
            ruleExpressionWhereEntry();

            state._fsp--;

             after(grammarAccess.getExpressionWhereEntryRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpressionWhereEntry1568); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:769:1: ruleExpressionWhereEntry : ( ( rule__ExpressionWhereEntry__Alternatives ) ) ;
    public final void ruleExpressionWhereEntry() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:773:2: ( ( ( rule__ExpressionWhereEntry__Alternatives ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:774:1: ( ( rule__ExpressionWhereEntry__Alternatives ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:774:1: ( ( rule__ExpressionWhereEntry__Alternatives ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:775:1: ( rule__ExpressionWhereEntry__Alternatives )
            {
             before(grammarAccess.getExpressionWhereEntryAccess().getAlternatives()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:776:1: ( rule__ExpressionWhereEntry__Alternatives )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:776:2: rule__ExpressionWhereEntry__Alternatives
            {
            pushFollow(FOLLOW_rule__ExpressionWhereEntry__Alternatives_in_ruleExpressionWhereEntry1594);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:788:1: entryRuleSingleExpressionWhereEntry : ruleSingleExpressionWhereEntry EOF ;
    public final void entryRuleSingleExpressionWhereEntry() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:789:1: ( ruleSingleExpressionWhereEntry EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:790:1: ruleSingleExpressionWhereEntry EOF
            {
             before(grammarAccess.getSingleExpressionWhereEntryRule()); 
            pushFollow(FOLLOW_ruleSingleExpressionWhereEntry_in_entryRuleSingleExpressionWhereEntry1621);
            ruleSingleExpressionWhereEntry();

            state._fsp--;

             after(grammarAccess.getSingleExpressionWhereEntryRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSingleExpressionWhereEntry1628); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:797:1: ruleSingleExpressionWhereEntry : ( ( rule__SingleExpressionWhereEntry__Group__0 ) ) ;
    public final void ruleSingleExpressionWhereEntry() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:801:2: ( ( ( rule__SingleExpressionWhereEntry__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:802:1: ( ( rule__SingleExpressionWhereEntry__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:802:1: ( ( rule__SingleExpressionWhereEntry__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:803:1: ( rule__SingleExpressionWhereEntry__Group__0 )
            {
             before(grammarAccess.getSingleExpressionWhereEntryAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:804:1: ( rule__SingleExpressionWhereEntry__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:804:2: rule__SingleExpressionWhereEntry__Group__0
            {
            pushFollow(FOLLOW_rule__SingleExpressionWhereEntry__Group__0_in_ruleSingleExpressionWhereEntry1654);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:816:1: entryRuleExpression : ruleExpression EOF ;
    public final void entryRuleExpression() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:817:1: ( ruleExpression EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:818:1: ruleExpression EOF
            {
             before(grammarAccess.getExpressionRule()); 
            pushFollow(FOLLOW_ruleExpression_in_entryRuleExpression1681);
            ruleExpression();

            state._fsp--;

             after(grammarAccess.getExpressionRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpression1688); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:825:1: ruleExpression : ( ( rule__Expression__Alternatives ) ) ;
    public final void ruleExpression() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:829:2: ( ( ( rule__Expression__Alternatives ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:830:1: ( ( rule__Expression__Alternatives ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:830:1: ( ( rule__Expression__Alternatives ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:831:1: ( rule__Expression__Alternatives )
            {
             before(grammarAccess.getExpressionAccess().getAlternatives()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:832:1: ( rule__Expression__Alternatives )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:832:2: rule__Expression__Alternatives
            {
            pushFollow(FOLLOW_rule__Expression__Alternatives_in_ruleExpression1714);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:844:1: entryRuleReplacableValue : ruleReplacableValue EOF ;
    public final void entryRuleReplacableValue() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:845:1: ( ruleReplacableValue EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:846:1: ruleReplacableValue EOF
            {
             before(grammarAccess.getReplacableValueRule()); 
            pushFollow(FOLLOW_ruleReplacableValue_in_entryRuleReplacableValue1741);
            ruleReplacableValue();

            state._fsp--;

             after(grammarAccess.getReplacableValueRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleReplacableValue1748); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:853:1: ruleReplacableValue : ( ( rule__ReplacableValue__ValueAssignment ) ) ;
    public final void ruleReplacableValue() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:857:2: ( ( ( rule__ReplacableValue__ValueAssignment ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:858:1: ( ( rule__ReplacableValue__ValueAssignment ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:858:1: ( ( rule__ReplacableValue__ValueAssignment ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:859:1: ( rule__ReplacableValue__ValueAssignment )
            {
             before(grammarAccess.getReplacableValueAccess().getValueAssignment()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:860:1: ( rule__ReplacableValue__ValueAssignment )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:860:2: rule__ReplacableValue__ValueAssignment
            {
            pushFollow(FOLLOW_rule__ReplacableValue__ValueAssignment_in_ruleReplacableValue1774);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:872:1: entryRuleDoubleExpression : ruleDoubleExpression EOF ;
    public final void entryRuleDoubleExpression() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:873:1: ( ruleDoubleExpression EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:874:1: ruleDoubleExpression EOF
            {
             before(grammarAccess.getDoubleExpressionRule()); 
            pushFollow(FOLLOW_ruleDoubleExpression_in_entryRuleDoubleExpression1801);
            ruleDoubleExpression();

            state._fsp--;

             after(grammarAccess.getDoubleExpressionRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleDoubleExpression1808); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:881:1: ruleDoubleExpression : ( ( rule__DoubleExpression__ValueAssignment ) ) ;
    public final void ruleDoubleExpression() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:885:2: ( ( ( rule__DoubleExpression__ValueAssignment ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:886:1: ( ( rule__DoubleExpression__ValueAssignment ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:886:1: ( ( rule__DoubleExpression__ValueAssignment ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:887:1: ( rule__DoubleExpression__ValueAssignment )
            {
             before(grammarAccess.getDoubleExpressionAccess().getValueAssignment()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:888:1: ( rule__DoubleExpression__ValueAssignment )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:888:2: rule__DoubleExpression__ValueAssignment
            {
            pushFollow(FOLLOW_rule__DoubleExpression__ValueAssignment_in_ruleDoubleExpression1834);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:900:1: entryRuleLongExpression : ruleLongExpression EOF ;
    public final void entryRuleLongExpression() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:901:1: ( ruleLongExpression EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:902:1: ruleLongExpression EOF
            {
             before(grammarAccess.getLongExpressionRule()); 
            pushFollow(FOLLOW_ruleLongExpression_in_entryRuleLongExpression1861);
            ruleLongExpression();

            state._fsp--;

             after(grammarAccess.getLongExpressionRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleLongExpression1868); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:909:1: ruleLongExpression : ( ( rule__LongExpression__ValueAssignment ) ) ;
    public final void ruleLongExpression() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:913:2: ( ( ( rule__LongExpression__ValueAssignment ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:914:1: ( ( rule__LongExpression__ValueAssignment ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:914:1: ( ( rule__LongExpression__ValueAssignment ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:915:1: ( rule__LongExpression__ValueAssignment )
            {
             before(grammarAccess.getLongExpressionAccess().getValueAssignment()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:916:1: ( rule__LongExpression__ValueAssignment )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:916:2: rule__LongExpression__ValueAssignment
            {
            pushFollow(FOLLOW_rule__LongExpression__ValueAssignment_in_ruleLongExpression1894);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:928:1: entryRuleStringExpression : ruleStringExpression EOF ;
    public final void entryRuleStringExpression() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:929:1: ( ruleStringExpression EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:930:1: ruleStringExpression EOF
            {
             before(grammarAccess.getStringExpressionRule()); 
            pushFollow(FOLLOW_ruleStringExpression_in_entryRuleStringExpression1921);
            ruleStringExpression();

            state._fsp--;

             after(grammarAccess.getStringExpressionRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleStringExpression1928); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:937:1: ruleStringExpression : ( ( rule__StringExpression__ValueAssignment ) ) ;
    public final void ruleStringExpression() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:941:2: ( ( ( rule__StringExpression__ValueAssignment ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:942:1: ( ( rule__StringExpression__ValueAssignment ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:942:1: ( ( rule__StringExpression__ValueAssignment ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:943:1: ( rule__StringExpression__ValueAssignment )
            {
             before(grammarAccess.getStringExpressionAccess().getValueAssignment()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:944:1: ( rule__StringExpression__ValueAssignment )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:944:2: rule__StringExpression__ValueAssignment
            {
            pushFollow(FOLLOW_rule__StringExpression__ValueAssignment_in_ruleStringExpression1954);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:956:1: entryRuleNullExpression : ruleNullExpression EOF ;
    public final void entryRuleNullExpression() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:957:1: ( ruleNullExpression EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:958:1: ruleNullExpression EOF
            {
             before(grammarAccess.getNullExpressionRule()); 
            pushFollow(FOLLOW_ruleNullExpression_in_entryRuleNullExpression1981);
            ruleNullExpression();

            state._fsp--;

             after(grammarAccess.getNullExpressionRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleNullExpression1988); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:965:1: ruleNullExpression : ( ( rule__NullExpression__ValueAssignment ) ) ;
    public final void ruleNullExpression() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:969:2: ( ( ( rule__NullExpression__ValueAssignment ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:970:1: ( ( rule__NullExpression__ValueAssignment ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:970:1: ( ( rule__NullExpression__ValueAssignment ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:971:1: ( rule__NullExpression__ValueAssignment )
            {
             before(grammarAccess.getNullExpressionAccess().getValueAssignment()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:972:1: ( rule__NullExpression__ValueAssignment )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:972:2: rule__NullExpression__ValueAssignment
            {
            pushFollow(FOLLOW_rule__NullExpression__ValueAssignment_in_ruleNullExpression2014);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:984:1: entryRuleDateExpression : ruleDateExpression EOF ;
    public final void entryRuleDateExpression() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:985:1: ( ruleDateExpression EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:986:1: ruleDateExpression EOF
            {
             before(grammarAccess.getDateExpressionRule()); 
            pushFollow(FOLLOW_ruleDateExpression_in_entryRuleDateExpression2041);
            ruleDateExpression();

            state._fsp--;

             after(grammarAccess.getDateExpressionRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleDateExpression2048); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:993:1: ruleDateExpression : ( ( rule__DateExpression__ValueAssignment ) ) ;
    public final void ruleDateExpression() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:997:2: ( ( ( rule__DateExpression__ValueAssignment ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:998:1: ( ( rule__DateExpression__ValueAssignment ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:998:1: ( ( rule__DateExpression__ValueAssignment ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:999:1: ( rule__DateExpression__ValueAssignment )
            {
             before(grammarAccess.getDateExpressionAccess().getValueAssignment()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1000:1: ( rule__DateExpression__ValueAssignment )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1000:2: rule__DateExpression__ValueAssignment
            {
            pushFollow(FOLLOW_rule__DateExpression__ValueAssignment_in_ruleDateExpression2074);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1012:1: entryRuleBooleanExpression : ruleBooleanExpression EOF ;
    public final void entryRuleBooleanExpression() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1013:1: ( ruleBooleanExpression EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1014:1: ruleBooleanExpression EOF
            {
             before(grammarAccess.getBooleanExpressionRule()); 
            pushFollow(FOLLOW_ruleBooleanExpression_in_entryRuleBooleanExpression2101);
            ruleBooleanExpression();

            state._fsp--;

             after(grammarAccess.getBooleanExpressionRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleBooleanExpression2108); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1021:1: ruleBooleanExpression : ( ( rule__BooleanExpression__Alternatives ) ) ;
    public final void ruleBooleanExpression() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1025:2: ( ( ( rule__BooleanExpression__Alternatives ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1026:1: ( ( rule__BooleanExpression__Alternatives ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1026:1: ( ( rule__BooleanExpression__Alternatives ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1027:1: ( rule__BooleanExpression__Alternatives )
            {
             before(grammarAccess.getBooleanExpressionAccess().getAlternatives()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1028:1: ( rule__BooleanExpression__Alternatives )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1028:2: rule__BooleanExpression__Alternatives
            {
            pushFollow(FOLLOW_rule__BooleanExpression__Alternatives_in_ruleBooleanExpression2134);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1040:1: entryRuleMultiExpressionWhereEntry : ruleMultiExpressionWhereEntry EOF ;
    public final void entryRuleMultiExpressionWhereEntry() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1041:1: ( ruleMultiExpressionWhereEntry EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1042:1: ruleMultiExpressionWhereEntry EOF
            {
             before(grammarAccess.getMultiExpressionWhereEntryRule()); 
            pushFollow(FOLLOW_ruleMultiExpressionWhereEntry_in_entryRuleMultiExpressionWhereEntry2161);
            ruleMultiExpressionWhereEntry();

            state._fsp--;

             after(grammarAccess.getMultiExpressionWhereEntryRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleMultiExpressionWhereEntry2168); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1049:1: ruleMultiExpressionWhereEntry : ( ( rule__MultiExpressionWhereEntry__Group__0 ) ) ;
    public final void ruleMultiExpressionWhereEntry() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1053:2: ( ( ( rule__MultiExpressionWhereEntry__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1054:1: ( ( rule__MultiExpressionWhereEntry__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1054:1: ( ( rule__MultiExpressionWhereEntry__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1055:1: ( rule__MultiExpressionWhereEntry__Group__0 )
            {
             before(grammarAccess.getMultiExpressionWhereEntryAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1056:1: ( rule__MultiExpressionWhereEntry__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1056:2: rule__MultiExpressionWhereEntry__Group__0
            {
            pushFollow(FOLLOW_rule__MultiExpressionWhereEntry__Group__0_in_ruleMultiExpressionWhereEntry2194);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1068:1: entryRuleArrayExpression : ruleArrayExpression EOF ;
    public final void entryRuleArrayExpression() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1069:1: ( ruleArrayExpression EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1070:1: ruleArrayExpression EOF
            {
             before(grammarAccess.getArrayExpressionRule()); 
            pushFollow(FOLLOW_ruleArrayExpression_in_entryRuleArrayExpression2221);
            ruleArrayExpression();

            state._fsp--;

             after(grammarAccess.getArrayExpressionRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleArrayExpression2228); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1077:1: ruleArrayExpression : ( ( rule__ArrayExpression__Alternatives ) ) ;
    public final void ruleArrayExpression() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1081:2: ( ( ( rule__ArrayExpression__Alternatives ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1082:1: ( ( rule__ArrayExpression__Alternatives ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1082:1: ( ( rule__ArrayExpression__Alternatives ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1083:1: ( rule__ArrayExpression__Alternatives )
            {
             before(grammarAccess.getArrayExpressionAccess().getAlternatives()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1084:1: ( rule__ArrayExpression__Alternatives )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1084:2: rule__ArrayExpression__Alternatives
            {
            pushFollow(FOLLOW_rule__ArrayExpression__Alternatives_in_ruleArrayExpression2254);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1096:1: entryRuleDoubleArrayExpression : ruleDoubleArrayExpression EOF ;
    public final void entryRuleDoubleArrayExpression() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1097:1: ( ruleDoubleArrayExpression EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1098:1: ruleDoubleArrayExpression EOF
            {
             before(grammarAccess.getDoubleArrayExpressionRule()); 
            pushFollow(FOLLOW_ruleDoubleArrayExpression_in_entryRuleDoubleArrayExpression2281);
            ruleDoubleArrayExpression();

            state._fsp--;

             after(grammarAccess.getDoubleArrayExpressionRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleDoubleArrayExpression2288); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1105:1: ruleDoubleArrayExpression : ( ( rule__DoubleArrayExpression__Group__0 ) ) ;
    public final void ruleDoubleArrayExpression() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1109:2: ( ( ( rule__DoubleArrayExpression__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1110:1: ( ( rule__DoubleArrayExpression__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1110:1: ( ( rule__DoubleArrayExpression__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1111:1: ( rule__DoubleArrayExpression__Group__0 )
            {
             before(grammarAccess.getDoubleArrayExpressionAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1112:1: ( rule__DoubleArrayExpression__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1112:2: rule__DoubleArrayExpression__Group__0
            {
            pushFollow(FOLLOW_rule__DoubleArrayExpression__Group__0_in_ruleDoubleArrayExpression2314);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1124:1: entryRuleLongArrayExpression : ruleLongArrayExpression EOF ;
    public final void entryRuleLongArrayExpression() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1125:1: ( ruleLongArrayExpression EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1126:1: ruleLongArrayExpression EOF
            {
             before(grammarAccess.getLongArrayExpressionRule()); 
            pushFollow(FOLLOW_ruleLongArrayExpression_in_entryRuleLongArrayExpression2341);
            ruleLongArrayExpression();

            state._fsp--;

             after(grammarAccess.getLongArrayExpressionRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleLongArrayExpression2348); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1133:1: ruleLongArrayExpression : ( ( rule__LongArrayExpression__Group__0 ) ) ;
    public final void ruleLongArrayExpression() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1137:2: ( ( ( rule__LongArrayExpression__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1138:1: ( ( rule__LongArrayExpression__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1138:1: ( ( rule__LongArrayExpression__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1139:1: ( rule__LongArrayExpression__Group__0 )
            {
             before(grammarAccess.getLongArrayExpressionAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1140:1: ( rule__LongArrayExpression__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1140:2: rule__LongArrayExpression__Group__0
            {
            pushFollow(FOLLOW_rule__LongArrayExpression__Group__0_in_ruleLongArrayExpression2374);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1152:1: entryRuleStringArrayExpression : ruleStringArrayExpression EOF ;
    public final void entryRuleStringArrayExpression() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1153:1: ( ruleStringArrayExpression EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1154:1: ruleStringArrayExpression EOF
            {
             before(grammarAccess.getStringArrayExpressionRule()); 
            pushFollow(FOLLOW_ruleStringArrayExpression_in_entryRuleStringArrayExpression2401);
            ruleStringArrayExpression();

            state._fsp--;

             after(grammarAccess.getStringArrayExpressionRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleStringArrayExpression2408); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1161:1: ruleStringArrayExpression : ( ( rule__StringArrayExpression__Group__0 ) ) ;
    public final void ruleStringArrayExpression() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1165:2: ( ( ( rule__StringArrayExpression__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1166:1: ( ( rule__StringArrayExpression__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1166:1: ( ( rule__StringArrayExpression__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1167:1: ( rule__StringArrayExpression__Group__0 )
            {
             before(grammarAccess.getStringArrayExpressionAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1168:1: ( rule__StringArrayExpression__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1168:2: rule__StringArrayExpression__Group__0
            {
            pushFollow(FOLLOW_rule__StringArrayExpression__Group__0_in_ruleStringArrayExpression2434);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1180:1: entryRuleNullArrayExpression : ruleNullArrayExpression EOF ;
    public final void entryRuleNullArrayExpression() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1181:1: ( ruleNullArrayExpression EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1182:1: ruleNullArrayExpression EOF
            {
             before(grammarAccess.getNullArrayExpressionRule()); 
            pushFollow(FOLLOW_ruleNullArrayExpression_in_entryRuleNullArrayExpression2461);
            ruleNullArrayExpression();

            state._fsp--;

             after(grammarAccess.getNullArrayExpressionRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleNullArrayExpression2468); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1189:1: ruleNullArrayExpression : ( ( rule__NullArrayExpression__Group__0 ) ) ;
    public final void ruleNullArrayExpression() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1193:2: ( ( ( rule__NullArrayExpression__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1194:1: ( ( rule__NullArrayExpression__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1194:1: ( ( rule__NullArrayExpression__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1195:1: ( rule__NullArrayExpression__Group__0 )
            {
             before(grammarAccess.getNullArrayExpressionAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1196:1: ( rule__NullArrayExpression__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1196:2: rule__NullArrayExpression__Group__0
            {
            pushFollow(FOLLOW_rule__NullArrayExpression__Group__0_in_ruleNullArrayExpression2494);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1208:1: entryRuleDateArrayExpression : ruleDateArrayExpression EOF ;
    public final void entryRuleDateArrayExpression() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1209:1: ( ruleDateArrayExpression EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1210:1: ruleDateArrayExpression EOF
            {
             before(grammarAccess.getDateArrayExpressionRule()); 
            pushFollow(FOLLOW_ruleDateArrayExpression_in_entryRuleDateArrayExpression2521);
            ruleDateArrayExpression();

            state._fsp--;

             after(grammarAccess.getDateArrayExpressionRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleDateArrayExpression2528); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1217:1: ruleDateArrayExpression : ( ( rule__DateArrayExpression__Group__0 ) ) ;
    public final void ruleDateArrayExpression() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1221:2: ( ( ( rule__DateArrayExpression__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1222:1: ( ( rule__DateArrayExpression__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1222:1: ( ( rule__DateArrayExpression__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1223:1: ( rule__DateArrayExpression__Group__0 )
            {
             before(grammarAccess.getDateArrayExpressionAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1224:1: ( rule__DateArrayExpression__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1224:2: rule__DateArrayExpression__Group__0
            {
            pushFollow(FOLLOW_rule__DateArrayExpression__Group__0_in_ruleDateArrayExpression2554);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1236:1: entryRuleBooleanArrayExpression : ruleBooleanArrayExpression EOF ;
    public final void entryRuleBooleanArrayExpression() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1237:1: ( ruleBooleanArrayExpression EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1238:1: ruleBooleanArrayExpression EOF
            {
             before(grammarAccess.getBooleanArrayExpressionRule()); 
            pushFollow(FOLLOW_ruleBooleanArrayExpression_in_entryRuleBooleanArrayExpression2581);
            ruleBooleanArrayExpression();

            state._fsp--;

             after(grammarAccess.getBooleanArrayExpressionRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleBooleanArrayExpression2588); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1245:1: ruleBooleanArrayExpression : ( ( rule__BooleanArrayExpression__Group__0 ) ) ;
    public final void ruleBooleanArrayExpression() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1249:2: ( ( ( rule__BooleanArrayExpression__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1250:1: ( ( rule__BooleanArrayExpression__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1250:1: ( ( rule__BooleanArrayExpression__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1251:1: ( rule__BooleanArrayExpression__Group__0 )
            {
             before(grammarAccess.getBooleanArrayExpressionAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1252:1: ( rule__BooleanArrayExpression__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1252:2: rule__BooleanArrayExpression__Group__0
            {
            pushFollow(FOLLOW_rule__BooleanArrayExpression__Group__0_in_ruleBooleanArrayExpression2614);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1265:1: ruleArrayOperator : ( ( rule__ArrayOperator__Alternatives ) ) ;
    public final void ruleArrayOperator() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1269:1: ( ( ( rule__ArrayOperator__Alternatives ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1270:1: ( ( rule__ArrayOperator__Alternatives ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1270:1: ( ( rule__ArrayOperator__Alternatives ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1271:1: ( rule__ArrayOperator__Alternatives )
            {
             before(grammarAccess.getArrayOperatorAccess().getAlternatives()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1272:1: ( rule__ArrayOperator__Alternatives )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1272:2: rule__ArrayOperator__Alternatives
            {
            pushFollow(FOLLOW_rule__ArrayOperator__Alternatives_in_ruleArrayOperator2651);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1284:1: ruleOperator : ( ( rule__Operator__Alternatives ) ) ;
    public final void ruleOperator() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1288:1: ( ( ( rule__Operator__Alternatives ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1289:1: ( ( rule__Operator__Alternatives ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1289:1: ( ( rule__Operator__Alternatives ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1290:1: ( rule__Operator__Alternatives )
            {
             before(grammarAccess.getOperatorAccess().getAlternatives()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1291:1: ( rule__Operator__Alternatives )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1291:2: rule__Operator__Alternatives
            {
            pushFollow(FOLLOW_rule__Operator__Alternatives_in_ruleOperator2687);
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


    // $ANTLR start "rule__OrderByColumnFull__Alternatives"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1302:1: rule__OrderByColumnFull__Alternatives : ( ( ( rule__OrderByColumnFull__ColOrderAssignment_0 ) ) | ( ( rule__OrderByColumnFull__Group_1__0 ) ) );
    public final void rule__OrderByColumnFull__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1306:1: ( ( ( rule__OrderByColumnFull__ColOrderAssignment_0 ) ) | ( ( rule__OrderByColumnFull__Group_1__0 ) ) )
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==RULE_ID) ) {
                int LA1_1 = input.LA(2);

                if ( (LA1_1==32) ) {
                    alt1=2;
                }
                else if ( (LA1_1==EOF||LA1_1==31) ) {
                    alt1=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 1, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;
            }
            switch (alt1) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1307:1: ( ( rule__OrderByColumnFull__ColOrderAssignment_0 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1307:1: ( ( rule__OrderByColumnFull__ColOrderAssignment_0 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1308:1: ( rule__OrderByColumnFull__ColOrderAssignment_0 )
                    {
                     before(grammarAccess.getOrderByColumnFullAccess().getColOrderAssignment_0()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1309:1: ( rule__OrderByColumnFull__ColOrderAssignment_0 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1309:2: rule__OrderByColumnFull__ColOrderAssignment_0
                    {
                    pushFollow(FOLLOW_rule__OrderByColumnFull__ColOrderAssignment_0_in_rule__OrderByColumnFull__Alternatives2722);
                    rule__OrderByColumnFull__ColOrderAssignment_0();

                    state._fsp--;


                    }

                     after(grammarAccess.getOrderByColumnFullAccess().getColOrderAssignment_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1313:6: ( ( rule__OrderByColumnFull__Group_1__0 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1313:6: ( ( rule__OrderByColumnFull__Group_1__0 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1314:1: ( rule__OrderByColumnFull__Group_1__0 )
                    {
                     before(grammarAccess.getOrderByColumnFullAccess().getGroup_1()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1315:1: ( rule__OrderByColumnFull__Group_1__0 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1315:2: rule__OrderByColumnFull__Group_1__0
                    {
                    pushFollow(FOLLOW_rule__OrderByColumnFull__Group_1__0_in_rule__OrderByColumnFull__Alternatives2740);
                    rule__OrderByColumnFull__Group_1__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getOrderByColumnFullAccess().getGroup_1()); 

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
    // $ANTLR end "rule__OrderByColumnFull__Alternatives"


    // $ANTLR start "rule__GroupByColumnFull__Alternatives"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1324:1: rule__GroupByColumnFull__Alternatives : ( ( ( rule__GroupByColumnFull__GroupByColumnAssignment_0 ) ) | ( ( rule__GroupByColumnFull__Group_1__0 ) ) );
    public final void rule__GroupByColumnFull__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1328:1: ( ( ( rule__GroupByColumnFull__GroupByColumnAssignment_0 ) ) | ( ( rule__GroupByColumnFull__Group_1__0 ) ) )
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==RULE_ID) ) {
                int LA2_1 = input.LA(2);

                if ( (LA2_1==32) ) {
                    alt2=2;
                }
                else if ( (LA2_1==EOF||(LA2_1>=29 && LA2_1<=31)) ) {
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
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1329:1: ( ( rule__GroupByColumnFull__GroupByColumnAssignment_0 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1329:1: ( ( rule__GroupByColumnFull__GroupByColumnAssignment_0 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1330:1: ( rule__GroupByColumnFull__GroupByColumnAssignment_0 )
                    {
                     before(grammarAccess.getGroupByColumnFullAccess().getGroupByColumnAssignment_0()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1331:1: ( rule__GroupByColumnFull__GroupByColumnAssignment_0 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1331:2: rule__GroupByColumnFull__GroupByColumnAssignment_0
                    {
                    pushFollow(FOLLOW_rule__GroupByColumnFull__GroupByColumnAssignment_0_in_rule__GroupByColumnFull__Alternatives2773);
                    rule__GroupByColumnFull__GroupByColumnAssignment_0();

                    state._fsp--;


                    }

                     after(grammarAccess.getGroupByColumnFullAccess().getGroupByColumnAssignment_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1335:6: ( ( rule__GroupByColumnFull__Group_1__0 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1335:6: ( ( rule__GroupByColumnFull__Group_1__0 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1336:1: ( rule__GroupByColumnFull__Group_1__0 )
                    {
                     before(grammarAccess.getGroupByColumnFullAccess().getGroup_1()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1337:1: ( rule__GroupByColumnFull__Group_1__0 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1337:2: rule__GroupByColumnFull__Group_1__0
                    {
                    pushFollow(FOLLOW_rule__GroupByColumnFull__Group_1__0_in_rule__GroupByColumnFull__Alternatives2791);
                    rule__GroupByColumnFull__Group_1__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getGroupByColumnFullAccess().getGroup_1()); 

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
    // $ANTLR end "rule__GroupByColumnFull__Alternatives"


    // $ANTLR start "rule__ColumnOrAlias__Alternatives"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1346:1: rule__ColumnOrAlias__Alternatives : ( ( ruleColumnFull ) | ( ( rule__ColumnOrAlias__Group_1__0 ) ) | ( ( rule__ColumnOrAlias__Group_2__0 ) ) );
    public final void rule__ColumnOrAlias__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1350:1: ( ( ruleColumnFull ) | ( ( rule__ColumnOrAlias__Group_1__0 ) ) | ( ( rule__ColumnOrAlias__Group_2__0 ) ) )
            int alt3=3;
            alt3 = dfa3.predict(input);
            switch (alt3) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1351:1: ( ruleColumnFull )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1351:1: ( ruleColumnFull )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1352:1: ruleColumnFull
                    {
                     before(grammarAccess.getColumnOrAliasAccess().getColumnFullParserRuleCall_0()); 
                    pushFollow(FOLLOW_ruleColumnFull_in_rule__ColumnOrAlias__Alternatives2824);
                    ruleColumnFull();

                    state._fsp--;

                     after(grammarAccess.getColumnOrAliasAccess().getColumnFullParserRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1357:6: ( ( rule__ColumnOrAlias__Group_1__0 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1357:6: ( ( rule__ColumnOrAlias__Group_1__0 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1358:1: ( rule__ColumnOrAlias__Group_1__0 )
                    {
                     before(grammarAccess.getColumnOrAliasAccess().getGroup_1()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1359:1: ( rule__ColumnOrAlias__Group_1__0 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1359:2: rule__ColumnOrAlias__Group_1__0
                    {
                    pushFollow(FOLLOW_rule__ColumnOrAlias__Group_1__0_in_rule__ColumnOrAlias__Alternatives2841);
                    rule__ColumnOrAlias__Group_1__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getColumnOrAliasAccess().getGroup_1()); 

                    }


                    }
                    break;
                case 3 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1363:6: ( ( rule__ColumnOrAlias__Group_2__0 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1363:6: ( ( rule__ColumnOrAlias__Group_2__0 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1364:1: ( rule__ColumnOrAlias__Group_2__0 )
                    {
                     before(grammarAccess.getColumnOrAliasAccess().getGroup_2()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1365:1: ( rule__ColumnOrAlias__Group_2__0 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1365:2: rule__ColumnOrAlias__Group_2__0
                    {
                    pushFollow(FOLLOW_rule__ColumnOrAlias__Group_2__0_in_rule__ColumnOrAlias__Alternatives2859);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1374:1: rule__ColumnFull__Alternatives : ( ( ( rule__ColumnFull__ColNameAssignment_0 ) ) | ( ( rule__ColumnFull__Group_1__0 ) ) );
    public final void rule__ColumnFull__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1378:1: ( ( ( rule__ColumnFull__ColNameAssignment_0 ) ) | ( ( rule__ColumnFull__Group_1__0 ) ) )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==RULE_ID) ) {
                int LA4_1 = input.LA(2);

                if ( (LA4_1==32) ) {
                    alt4=2;
                }
                else if ( (LA4_1==EOF||LA4_1==RULE_ID||LA4_1==26||LA4_1==31||LA4_1==33) ) {
                    alt4=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 4, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }
            switch (alt4) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1379:1: ( ( rule__ColumnFull__ColNameAssignment_0 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1379:1: ( ( rule__ColumnFull__ColNameAssignment_0 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1380:1: ( rule__ColumnFull__ColNameAssignment_0 )
                    {
                     before(grammarAccess.getColumnFullAccess().getColNameAssignment_0()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1381:1: ( rule__ColumnFull__ColNameAssignment_0 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1381:2: rule__ColumnFull__ColNameAssignment_0
                    {
                    pushFollow(FOLLOW_rule__ColumnFull__ColNameAssignment_0_in_rule__ColumnFull__Alternatives2892);
                    rule__ColumnFull__ColNameAssignment_0();

                    state._fsp--;


                    }

                     after(grammarAccess.getColumnFullAccess().getColNameAssignment_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1385:6: ( ( rule__ColumnFull__Group_1__0 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1385:6: ( ( rule__ColumnFull__Group_1__0 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1386:1: ( rule__ColumnFull__Group_1__0 )
                    {
                     before(grammarAccess.getColumnFullAccess().getGroup_1()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1387:1: ( rule__ColumnFull__Group_1__0 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1387:2: rule__ColumnFull__Group_1__0
                    {
                    pushFollow(FOLLOW_rule__ColumnFull__Group_1__0_in_rule__ColumnFull__Alternatives2910);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1396:1: rule__TableOrAlias__Alternatives : ( ( ruleTableFull ) | ( ( rule__TableOrAlias__Group_1__0 ) ) | ( ( rule__TableOrAlias__Group_2__0 ) ) );
    public final void rule__TableOrAlias__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1400:1: ( ( ruleTableFull ) | ( ( rule__TableOrAlias__Group_1__0 ) ) | ( ( rule__TableOrAlias__Group_2__0 ) ) )
            int alt5=3;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==RULE_ID) ) {
                switch ( input.LA(2) ) {
                case EOF:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                    {
                    alt5=1;
                    }
                    break;
                case 32:
                    {
                    int LA5_3 = input.LA(3);

                    if ( (LA5_3==RULE_ID) ) {
                        switch ( input.LA(4) ) {
                        case 33:
                            {
                            alt5=2;
                            }
                            break;
                        case RULE_ID:
                            {
                            alt5=3;
                            }
                            break;
                        case 32:
                            {
                            int LA5_7 = input.LA(5);

                            if ( (LA5_7==RULE_ID) ) {
                                switch ( input.LA(6) ) {
                                case 33:
                                    {
                                    alt5=2;
                                    }
                                    break;
                                case RULE_ID:
                                    {
                                    alt5=3;
                                    }
                                    break;
                                case EOF:
                                case 27:
                                case 28:
                                case 29:
                                case 30:
                                case 31:
                                    {
                                    alt5=1;
                                    }
                                    break;
                                default:
                                    NoViableAltException nvae =
                                        new NoViableAltException("", 5, 8, input);

                                    throw nvae;
                                }

                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("", 5, 7, input);

                                throw nvae;
                            }
                            }
                            break;
                        case EOF:
                        case 27:
                        case 28:
                        case 29:
                        case 30:
                        case 31:
                            {
                            alt5=1;
                            }
                            break;
                        default:
                            NoViableAltException nvae =
                                new NoViableAltException("", 5, 6, input);

                            throw nvae;
                        }

                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 5, 3, input);

                        throw nvae;
                    }
                    }
                    break;
                case RULE_ID:
                    {
                    alt5=3;
                    }
                    break;
                case 33:
                    {
                    alt5=2;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("", 5, 1, input);

                    throw nvae;
                }

            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }
            switch (alt5) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1401:1: ( ruleTableFull )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1401:1: ( ruleTableFull )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1402:1: ruleTableFull
                    {
                     before(grammarAccess.getTableOrAliasAccess().getTableFullParserRuleCall_0()); 
                    pushFollow(FOLLOW_ruleTableFull_in_rule__TableOrAlias__Alternatives2943);
                    ruleTableFull();

                    state._fsp--;

                     after(grammarAccess.getTableOrAliasAccess().getTableFullParserRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1407:6: ( ( rule__TableOrAlias__Group_1__0 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1407:6: ( ( rule__TableOrAlias__Group_1__0 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1408:1: ( rule__TableOrAlias__Group_1__0 )
                    {
                     before(grammarAccess.getTableOrAliasAccess().getGroup_1()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1409:1: ( rule__TableOrAlias__Group_1__0 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1409:2: rule__TableOrAlias__Group_1__0
                    {
                    pushFollow(FOLLOW_rule__TableOrAlias__Group_1__0_in_rule__TableOrAlias__Alternatives2960);
                    rule__TableOrAlias__Group_1__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getTableOrAliasAccess().getGroup_1()); 

                    }


                    }
                    break;
                case 3 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1413:6: ( ( rule__TableOrAlias__Group_2__0 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1413:6: ( ( rule__TableOrAlias__Group_2__0 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1414:1: ( rule__TableOrAlias__Group_2__0 )
                    {
                     before(grammarAccess.getTableOrAliasAccess().getGroup_2()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1415:1: ( rule__TableOrAlias__Group_2__0 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1415:2: rule__TableOrAlias__Group_2__0
                    {
                    pushFollow(FOLLOW_rule__TableOrAlias__Group_2__0_in_rule__TableOrAlias__Alternatives2978);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1424:1: rule__TableFull__Alternatives : ( ( ( rule__TableFull__Group_0__0 ) ) | ( ( rule__TableFull__TblAssignment_1 ) ) );
    public final void rule__TableFull__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1428:1: ( ( ( rule__TableFull__Group_0__0 ) ) | ( ( rule__TableFull__TblAssignment_1 ) ) )
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==RULE_ID) ) {
                int LA6_1 = input.LA(2);

                if ( (LA6_1==EOF||LA6_1==RULE_ID||(LA6_1>=27 && LA6_1<=31)||LA6_1==33) ) {
                    alt6=2;
                }
                else if ( (LA6_1==32) ) {
                    int LA6_3 = input.LA(3);

                    if ( (LA6_3==RULE_ID) ) {
                        alt6=1;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 6, 3, input);

                        throw nvae;
                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 6, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }
            switch (alt6) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1429:1: ( ( rule__TableFull__Group_0__0 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1429:1: ( ( rule__TableFull__Group_0__0 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1430:1: ( rule__TableFull__Group_0__0 )
                    {
                     before(grammarAccess.getTableFullAccess().getGroup_0()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1431:1: ( rule__TableFull__Group_0__0 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1431:2: rule__TableFull__Group_0__0
                    {
                    pushFollow(FOLLOW_rule__TableFull__Group_0__0_in_rule__TableFull__Alternatives3011);
                    rule__TableFull__Group_0__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getTableFullAccess().getGroup_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1435:6: ( ( rule__TableFull__TblAssignment_1 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1435:6: ( ( rule__TableFull__TblAssignment_1 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1436:1: ( rule__TableFull__TblAssignment_1 )
                    {
                     before(grammarAccess.getTableFullAccess().getTblAssignment_1()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1437:1: ( rule__TableFull__TblAssignment_1 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1437:2: rule__TableFull__TblAssignment_1
                    {
                    pushFollow(FOLLOW_rule__TableFull__TblAssignment_1_in_rule__TableFull__Alternatives3029);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1446:1: rule__Schema__Alternatives : ( ( ( rule__Schema__Group_0__0 ) ) | ( ( rule__Schema__SchemAssignment_1 ) ) );
    public final void rule__Schema__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1450:1: ( ( ( rule__Schema__Group_0__0 ) ) | ( ( rule__Schema__SchemAssignment_1 ) ) )
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==RULE_ID) ) {
                int LA7_1 = input.LA(2);

                if ( (LA7_1==EOF) ) {
                    alt7=2;
                }
                else if ( (LA7_1==32) ) {
                    int LA7_3 = input.LA(3);

                    if ( (LA7_3==RULE_ID) ) {
                        switch ( input.LA(4) ) {
                        case EOF:
                            {
                            int LA7_5 = input.LA(5);

                            if ( (LA7_5==EOF) ) {
                                alt7=1;
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("", 7, 5, input);

                                throw nvae;
                            }
                            }
                            break;
                        case RULE_ID:
                        case 27:
                        case 28:
                        case 29:
                        case 30:
                        case 31:
                        case 33:
                            {
                            alt7=2;
                            }
                            break;
                        case 32:
                            {
                            int LA7_6 = input.LA(5);

                            if ( (LA7_6==RULE_ID) ) {
                                alt7=1;
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("", 7, 6, input);

                                throw nvae;
                            }
                            }
                            break;
                        default:
                            NoViableAltException nvae =
                                new NoViableAltException("", 7, 4, input);

                            throw nvae;
                        }

                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 7, 3, input);

                        throw nvae;
                    }
                }
                else {
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
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1451:1: ( ( rule__Schema__Group_0__0 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1451:1: ( ( rule__Schema__Group_0__0 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1452:1: ( rule__Schema__Group_0__0 )
                    {
                     before(grammarAccess.getSchemaAccess().getGroup_0()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1453:1: ( rule__Schema__Group_0__0 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1453:2: rule__Schema__Group_0__0
                    {
                    pushFollow(FOLLOW_rule__Schema__Group_0__0_in_rule__Schema__Alternatives3062);
                    rule__Schema__Group_0__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getSchemaAccess().getGroup_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1457:6: ( ( rule__Schema__SchemAssignment_1 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1457:6: ( ( rule__Schema__SchemAssignment_1 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1458:1: ( rule__Schema__SchemAssignment_1 )
                    {
                     before(grammarAccess.getSchemaAccess().getSchemAssignment_1()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1459:1: ( rule__Schema__SchemAssignment_1 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1459:2: rule__Schema__SchemAssignment_1
                    {
                    pushFollow(FOLLOW_rule__Schema__SchemAssignment_1_in_rule__Schema__Alternatives3080);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1468:1: rule__ConcreteWhereEntry__Alternatives : ( ( ruleParWhereEntry ) | ( ruleExpressionWhereEntry ) );
    public final void rule__ConcreteWhereEntry__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1472:1: ( ( ruleParWhereEntry ) | ( ruleExpressionWhereEntry ) )
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==36) ) {
                alt8=1;
            }
            else if ( (LA8_0==RULE_ID) ) {
                alt8=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;
            }
            switch (alt8) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1473:1: ( ruleParWhereEntry )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1473:1: ( ruleParWhereEntry )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1474:1: ruleParWhereEntry
                    {
                     before(grammarAccess.getConcreteWhereEntryAccess().getParWhereEntryParserRuleCall_0()); 
                    pushFollow(FOLLOW_ruleParWhereEntry_in_rule__ConcreteWhereEntry__Alternatives3113);
                    ruleParWhereEntry();

                    state._fsp--;

                     after(grammarAccess.getConcreteWhereEntryAccess().getParWhereEntryParserRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1479:6: ( ruleExpressionWhereEntry )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1479:6: ( ruleExpressionWhereEntry )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1480:1: ruleExpressionWhereEntry
                    {
                     before(grammarAccess.getConcreteWhereEntryAccess().getExpressionWhereEntryParserRuleCall_1()); 
                    pushFollow(FOLLOW_ruleExpressionWhereEntry_in_rule__ConcreteWhereEntry__Alternatives3130);
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


    // $ANTLR start "rule__ConcreteHavingEntry__Alternatives"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1490:1: rule__ConcreteHavingEntry__Alternatives : ( ( ruleParHavingEntry ) | ( ruleExpressionWhereEntry ) );
    public final void rule__ConcreteHavingEntry__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1494:1: ( ( ruleParHavingEntry ) | ( ruleExpressionWhereEntry ) )
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==36) ) {
                alt9=1;
            }
            else if ( (LA9_0==RULE_ID) ) {
                alt9=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                throw nvae;
            }
            switch (alt9) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1495:1: ( ruleParHavingEntry )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1495:1: ( ruleParHavingEntry )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1496:1: ruleParHavingEntry
                    {
                     before(grammarAccess.getConcreteHavingEntryAccess().getParHavingEntryParserRuleCall_0()); 
                    pushFollow(FOLLOW_ruleParHavingEntry_in_rule__ConcreteHavingEntry__Alternatives3162);
                    ruleParHavingEntry();

                    state._fsp--;

                     after(grammarAccess.getConcreteHavingEntryAccess().getParHavingEntryParserRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1501:6: ( ruleExpressionWhereEntry )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1501:6: ( ruleExpressionWhereEntry )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1502:1: ruleExpressionWhereEntry
                    {
                     before(grammarAccess.getConcreteHavingEntryAccess().getExpressionWhereEntryParserRuleCall_1()); 
                    pushFollow(FOLLOW_ruleExpressionWhereEntry_in_rule__ConcreteHavingEntry__Alternatives3179);
                    ruleExpressionWhereEntry();

                    state._fsp--;

                     after(grammarAccess.getConcreteHavingEntryAccess().getExpressionWhereEntryParserRuleCall_1()); 

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
    // $ANTLR end "rule__ConcreteHavingEntry__Alternatives"


    // $ANTLR start "rule__ExpressionWhereEntry__Alternatives"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1512:1: rule__ExpressionWhereEntry__Alternatives : ( ( ruleSingleExpressionWhereEntry ) | ( ruleMultiExpressionWhereEntry ) );
    public final void rule__ExpressionWhereEntry__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1516:1: ( ( ruleSingleExpressionWhereEntry ) | ( ruleMultiExpressionWhereEntry ) )
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==RULE_ID) ) {
                switch ( input.LA(2) ) {
                case 17:
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                    {
                    alt10=1;
                    }
                    break;
                case 16:
                    {
                    int LA10_3 = input.LA(3);

                    if ( ((LA10_3>=RULE_SIGNED_DOUBLE && LA10_3<=RULE_DATE)||(LA10_3>=40 && LA10_3<=43)) ) {
                        alt10=1;
                    }
                    else if ( (LA10_3==38) ) {
                        alt10=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 10, 3, input);

                        throw nvae;
                    }
                    }
                    break;
                case 15:
                    {
                    int LA10_4 = input.LA(3);

                    if ( (LA10_4==38) ) {
                        alt10=2;
                    }
                    else if ( ((LA10_4>=RULE_SIGNED_DOUBLE && LA10_4<=RULE_DATE)||(LA10_4>=40 && LA10_4<=43)) ) {
                        alt10=1;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 10, 4, input);

                        throw nvae;
                    }
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
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1517:1: ( ruleSingleExpressionWhereEntry )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1517:1: ( ruleSingleExpressionWhereEntry )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1518:1: ruleSingleExpressionWhereEntry
                    {
                     before(grammarAccess.getExpressionWhereEntryAccess().getSingleExpressionWhereEntryParserRuleCall_0()); 
                    pushFollow(FOLLOW_ruleSingleExpressionWhereEntry_in_rule__ExpressionWhereEntry__Alternatives3211);
                    ruleSingleExpressionWhereEntry();

                    state._fsp--;

                     after(grammarAccess.getExpressionWhereEntryAccess().getSingleExpressionWhereEntryParserRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1523:6: ( ruleMultiExpressionWhereEntry )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1523:6: ( ruleMultiExpressionWhereEntry )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1524:1: ruleMultiExpressionWhereEntry
                    {
                     before(grammarAccess.getExpressionWhereEntryAccess().getMultiExpressionWhereEntryParserRuleCall_1()); 
                    pushFollow(FOLLOW_ruleMultiExpressionWhereEntry_in_rule__ExpressionWhereEntry__Alternatives3228);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1534:1: rule__Expression__Alternatives : ( ( ruleDoubleExpression ) | ( ruleLongExpression ) | ( ruleStringExpression ) | ( ruleNullExpression ) | ( ruleDateExpression ) | ( ruleBooleanExpression ) | ( ruleReplacableValue ) );
    public final void rule__Expression__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1538:1: ( ( ruleDoubleExpression ) | ( ruleLongExpression ) | ( ruleStringExpression ) | ( ruleNullExpression ) | ( ruleDateExpression ) | ( ruleBooleanExpression ) | ( ruleReplacableValue ) )
            int alt11=7;
            switch ( input.LA(1) ) {
            case RULE_SIGNED_DOUBLE:
                {
                alt11=1;
                }
                break;
            case RULE_SINGED_LONG:
                {
                alt11=2;
                }
                break;
            case RULE_STRING:
                {
                alt11=3;
                }
                break;
            case 41:
                {
                alt11=4;
                }
                break;
            case RULE_DATE:
                {
                alt11=5;
                }
                break;
            case 42:
            case 43:
                {
                alt11=6;
                }
                break;
            case 40:
                {
                alt11=7;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 11, 0, input);

                throw nvae;
            }

            switch (alt11) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1539:1: ( ruleDoubleExpression )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1539:1: ( ruleDoubleExpression )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1540:1: ruleDoubleExpression
                    {
                     before(grammarAccess.getExpressionAccess().getDoubleExpressionParserRuleCall_0()); 
                    pushFollow(FOLLOW_ruleDoubleExpression_in_rule__Expression__Alternatives3260);
                    ruleDoubleExpression();

                    state._fsp--;

                     after(grammarAccess.getExpressionAccess().getDoubleExpressionParserRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1545:6: ( ruleLongExpression )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1545:6: ( ruleLongExpression )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1546:1: ruleLongExpression
                    {
                     before(grammarAccess.getExpressionAccess().getLongExpressionParserRuleCall_1()); 
                    pushFollow(FOLLOW_ruleLongExpression_in_rule__Expression__Alternatives3277);
                    ruleLongExpression();

                    state._fsp--;

                     after(grammarAccess.getExpressionAccess().getLongExpressionParserRuleCall_1()); 

                    }


                    }
                    break;
                case 3 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1551:6: ( ruleStringExpression )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1551:6: ( ruleStringExpression )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1552:1: ruleStringExpression
                    {
                     before(grammarAccess.getExpressionAccess().getStringExpressionParserRuleCall_2()); 
                    pushFollow(FOLLOW_ruleStringExpression_in_rule__Expression__Alternatives3294);
                    ruleStringExpression();

                    state._fsp--;

                     after(grammarAccess.getExpressionAccess().getStringExpressionParserRuleCall_2()); 

                    }


                    }
                    break;
                case 4 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1557:6: ( ruleNullExpression )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1557:6: ( ruleNullExpression )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1558:1: ruleNullExpression
                    {
                     before(grammarAccess.getExpressionAccess().getNullExpressionParserRuleCall_3()); 
                    pushFollow(FOLLOW_ruleNullExpression_in_rule__Expression__Alternatives3311);
                    ruleNullExpression();

                    state._fsp--;

                     after(grammarAccess.getExpressionAccess().getNullExpressionParserRuleCall_3()); 

                    }


                    }
                    break;
                case 5 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1563:6: ( ruleDateExpression )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1563:6: ( ruleDateExpression )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1564:1: ruleDateExpression
                    {
                     before(grammarAccess.getExpressionAccess().getDateExpressionParserRuleCall_4()); 
                    pushFollow(FOLLOW_ruleDateExpression_in_rule__Expression__Alternatives3328);
                    ruleDateExpression();

                    state._fsp--;

                     after(grammarAccess.getExpressionAccess().getDateExpressionParserRuleCall_4()); 

                    }


                    }
                    break;
                case 6 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1569:6: ( ruleBooleanExpression )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1569:6: ( ruleBooleanExpression )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1570:1: ruleBooleanExpression
                    {
                     before(grammarAccess.getExpressionAccess().getBooleanExpressionParserRuleCall_5()); 
                    pushFollow(FOLLOW_ruleBooleanExpression_in_rule__Expression__Alternatives3345);
                    ruleBooleanExpression();

                    state._fsp--;

                     after(grammarAccess.getExpressionAccess().getBooleanExpressionParserRuleCall_5()); 

                    }


                    }
                    break;
                case 7 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1575:6: ( ruleReplacableValue )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1575:6: ( ruleReplacableValue )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1576:1: ruleReplacableValue
                    {
                     before(grammarAccess.getExpressionAccess().getReplacableValueParserRuleCall_6()); 
                    pushFollow(FOLLOW_ruleReplacableValue_in_rule__Expression__Alternatives3362);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1586:1: rule__BooleanExpression__Alternatives : ( ( ( rule__BooleanExpression__TrueAssignment_0 ) ) | ( ( rule__BooleanExpression__TrueAssignment_1 ) ) );
    public final void rule__BooleanExpression__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1590:1: ( ( ( rule__BooleanExpression__TrueAssignment_0 ) ) | ( ( rule__BooleanExpression__TrueAssignment_1 ) ) )
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==42) ) {
                alt12=1;
            }
            else if ( (LA12_0==43) ) {
                alt12=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 12, 0, input);

                throw nvae;
            }
            switch (alt12) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1591:1: ( ( rule__BooleanExpression__TrueAssignment_0 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1591:1: ( ( rule__BooleanExpression__TrueAssignment_0 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1592:1: ( rule__BooleanExpression__TrueAssignment_0 )
                    {
                     before(grammarAccess.getBooleanExpressionAccess().getTrueAssignment_0()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1593:1: ( rule__BooleanExpression__TrueAssignment_0 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1593:2: rule__BooleanExpression__TrueAssignment_0
                    {
                    pushFollow(FOLLOW_rule__BooleanExpression__TrueAssignment_0_in_rule__BooleanExpression__Alternatives3394);
                    rule__BooleanExpression__TrueAssignment_0();

                    state._fsp--;


                    }

                     after(grammarAccess.getBooleanExpressionAccess().getTrueAssignment_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1597:6: ( ( rule__BooleanExpression__TrueAssignment_1 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1597:6: ( ( rule__BooleanExpression__TrueAssignment_1 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1598:1: ( rule__BooleanExpression__TrueAssignment_1 )
                    {
                     before(grammarAccess.getBooleanExpressionAccess().getTrueAssignment_1()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1599:1: ( rule__BooleanExpression__TrueAssignment_1 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1599:2: rule__BooleanExpression__TrueAssignment_1
                    {
                    pushFollow(FOLLOW_rule__BooleanExpression__TrueAssignment_1_in_rule__BooleanExpression__Alternatives3412);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1608:1: rule__ArrayExpression__Alternatives : ( ( ruleDoubleArrayExpression ) | ( ruleLongArrayExpression ) | ( ruleStringArrayExpression ) | ( ruleNullArrayExpression ) | ( ruleDateArrayExpression ) | ( ruleBooleanArrayExpression ) );
    public final void rule__ArrayExpression__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1612:1: ( ( ruleDoubleArrayExpression ) | ( ruleLongArrayExpression ) | ( ruleStringArrayExpression ) | ( ruleNullArrayExpression ) | ( ruleDateArrayExpression ) | ( ruleBooleanArrayExpression ) )
            int alt13=6;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==38) ) {
                switch ( input.LA(2) ) {
                case RULE_DATE:
                    {
                    alt13=5;
                    }
                    break;
                case RULE_SIGNED_DOUBLE:
                    {
                    alt13=1;
                    }
                    break;
                case RULE_BOOL:
                    {
                    alt13=6;
                    }
                    break;
                case RULE_STRING:
                    {
                    alt13=3;
                    }
                    break;
                case 41:
                    {
                    alt13=4;
                    }
                    break;
                case RULE_SINGED_LONG:
                    {
                    alt13=2;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("", 13, 1, input);

                    throw nvae;
                }

            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 13, 0, input);

                throw nvae;
            }
            switch (alt13) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1613:1: ( ruleDoubleArrayExpression )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1613:1: ( ruleDoubleArrayExpression )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1614:1: ruleDoubleArrayExpression
                    {
                     before(grammarAccess.getArrayExpressionAccess().getDoubleArrayExpressionParserRuleCall_0()); 
                    pushFollow(FOLLOW_ruleDoubleArrayExpression_in_rule__ArrayExpression__Alternatives3445);
                    ruleDoubleArrayExpression();

                    state._fsp--;

                     after(grammarAccess.getArrayExpressionAccess().getDoubleArrayExpressionParserRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1619:6: ( ruleLongArrayExpression )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1619:6: ( ruleLongArrayExpression )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1620:1: ruleLongArrayExpression
                    {
                     before(grammarAccess.getArrayExpressionAccess().getLongArrayExpressionParserRuleCall_1()); 
                    pushFollow(FOLLOW_ruleLongArrayExpression_in_rule__ArrayExpression__Alternatives3462);
                    ruleLongArrayExpression();

                    state._fsp--;

                     after(grammarAccess.getArrayExpressionAccess().getLongArrayExpressionParserRuleCall_1()); 

                    }


                    }
                    break;
                case 3 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1625:6: ( ruleStringArrayExpression )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1625:6: ( ruleStringArrayExpression )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1626:1: ruleStringArrayExpression
                    {
                     before(grammarAccess.getArrayExpressionAccess().getStringArrayExpressionParserRuleCall_2()); 
                    pushFollow(FOLLOW_ruleStringArrayExpression_in_rule__ArrayExpression__Alternatives3479);
                    ruleStringArrayExpression();

                    state._fsp--;

                     after(grammarAccess.getArrayExpressionAccess().getStringArrayExpressionParserRuleCall_2()); 

                    }


                    }
                    break;
                case 4 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1631:6: ( ruleNullArrayExpression )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1631:6: ( ruleNullArrayExpression )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1632:1: ruleNullArrayExpression
                    {
                     before(grammarAccess.getArrayExpressionAccess().getNullArrayExpressionParserRuleCall_3()); 
                    pushFollow(FOLLOW_ruleNullArrayExpression_in_rule__ArrayExpression__Alternatives3496);
                    ruleNullArrayExpression();

                    state._fsp--;

                     after(grammarAccess.getArrayExpressionAccess().getNullArrayExpressionParserRuleCall_3()); 

                    }


                    }
                    break;
                case 5 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1637:6: ( ruleDateArrayExpression )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1637:6: ( ruleDateArrayExpression )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1638:1: ruleDateArrayExpression
                    {
                     before(grammarAccess.getArrayExpressionAccess().getDateArrayExpressionParserRuleCall_4()); 
                    pushFollow(FOLLOW_ruleDateArrayExpression_in_rule__ArrayExpression__Alternatives3513);
                    ruleDateArrayExpression();

                    state._fsp--;

                     after(grammarAccess.getArrayExpressionAccess().getDateArrayExpressionParserRuleCall_4()); 

                    }


                    }
                    break;
                case 6 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1643:6: ( ruleBooleanArrayExpression )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1643:6: ( ruleBooleanArrayExpression )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1644:1: ruleBooleanArrayExpression
                    {
                     before(grammarAccess.getArrayExpressionAccess().getBooleanArrayExpressionParserRuleCall_5()); 
                    pushFollow(FOLLOW_ruleBooleanArrayExpression_in_rule__ArrayExpression__Alternatives3530);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1654:1: rule__ArrayOperator__Alternatives : ( ( ( 'in' ) ) | ( ( 'not in' ) ) );
    public final void rule__ArrayOperator__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1658:1: ( ( ( 'in' ) ) | ( ( 'not in' ) ) )
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==15) ) {
                alt14=1;
            }
            else if ( (LA14_0==16) ) {
                alt14=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 14, 0, input);

                throw nvae;
            }
            switch (alt14) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1659:1: ( ( 'in' ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1659:1: ( ( 'in' ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1660:1: ( 'in' )
                    {
                     before(grammarAccess.getArrayOperatorAccess().getSql_inEnumLiteralDeclaration_0()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1661:1: ( 'in' )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1661:3: 'in'
                    {
                    match(input,15,FOLLOW_15_in_rule__ArrayOperator__Alternatives3563); 

                    }

                     after(grammarAccess.getArrayOperatorAccess().getSql_inEnumLiteralDeclaration_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1666:6: ( ( 'not in' ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1666:6: ( ( 'not in' ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1667:1: ( 'not in' )
                    {
                     before(grammarAccess.getArrayOperatorAccess().getSql_notInEnumLiteralDeclaration_1()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1668:1: ( 'not in' )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1668:3: 'not in'
                    {
                    match(input,16,FOLLOW_16_in_rule__ArrayOperator__Alternatives3584); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1678:1: rule__Operator__Alternatives : ( ( ( '<' ) ) | ( ( '>' ) ) | ( ( '<=' ) ) | ( ( '>=' ) ) | ( ( '=' ) ) | ( ( '!=' ) ) | ( ( 'like' ) ) | ( ( 'not like' ) ) | ( ( 'not in' ) ) | ( ( 'in' ) ) );
    public final void rule__Operator__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1682:1: ( ( ( '<' ) ) | ( ( '>' ) ) | ( ( '<=' ) ) | ( ( '>=' ) ) | ( ( '=' ) ) | ( ( '!=' ) ) | ( ( 'like' ) ) | ( ( 'not like' ) ) | ( ( 'not in' ) ) | ( ( 'in' ) ) )
            int alt15=10;
            switch ( input.LA(1) ) {
            case 17:
                {
                alt15=1;
                }
                break;
            case 18:
                {
                alt15=2;
                }
                break;
            case 19:
                {
                alt15=3;
                }
                break;
            case 20:
                {
                alt15=4;
                }
                break;
            case 21:
                {
                alt15=5;
                }
                break;
            case 22:
                {
                alt15=6;
                }
                break;
            case 23:
                {
                alt15=7;
                }
                break;
            case 24:
                {
                alt15=8;
                }
                break;
            case 16:
                {
                alt15=9;
                }
                break;
            case 15:
                {
                alt15=10;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 15, 0, input);

                throw nvae;
            }

            switch (alt15) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1683:1: ( ( '<' ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1683:1: ( ( '<' ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1684:1: ( '<' )
                    {
                     before(grammarAccess.getOperatorAccess().getLessThenEnumLiteralDeclaration_0()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1685:1: ( '<' )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1685:3: '<'
                    {
                    match(input,17,FOLLOW_17_in_rule__Operator__Alternatives3620); 

                    }

                     after(grammarAccess.getOperatorAccess().getLessThenEnumLiteralDeclaration_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1690:6: ( ( '>' ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1690:6: ( ( '>' ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1691:1: ( '>' )
                    {
                     before(grammarAccess.getOperatorAccess().getGreaterThenEnumLiteralDeclaration_1()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1692:1: ( '>' )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1692:3: '>'
                    {
                    match(input,18,FOLLOW_18_in_rule__Operator__Alternatives3641); 

                    }

                     after(grammarAccess.getOperatorAccess().getGreaterThenEnumLiteralDeclaration_1()); 

                    }


                    }
                    break;
                case 3 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1697:6: ( ( '<=' ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1697:6: ( ( '<=' ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1698:1: ( '<=' )
                    {
                     before(grammarAccess.getOperatorAccess().getLessEqualEnumLiteralDeclaration_2()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1699:1: ( '<=' )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1699:3: '<='
                    {
                    match(input,19,FOLLOW_19_in_rule__Operator__Alternatives3662); 

                    }

                     after(grammarAccess.getOperatorAccess().getLessEqualEnumLiteralDeclaration_2()); 

                    }


                    }
                    break;
                case 4 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1704:6: ( ( '>=' ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1704:6: ( ( '>=' ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1705:1: ( '>=' )
                    {
                     before(grammarAccess.getOperatorAccess().getGreaterEqualEnumLiteralDeclaration_3()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1706:1: ( '>=' )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1706:3: '>='
                    {
                    match(input,20,FOLLOW_20_in_rule__Operator__Alternatives3683); 

                    }

                     after(grammarAccess.getOperatorAccess().getGreaterEqualEnumLiteralDeclaration_3()); 

                    }


                    }
                    break;
                case 5 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1711:6: ( ( '=' ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1711:6: ( ( '=' ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1712:1: ( '=' )
                    {
                     before(grammarAccess.getOperatorAccess().getEqualEnumLiteralDeclaration_4()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1713:1: ( '=' )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1713:3: '='
                    {
                    match(input,21,FOLLOW_21_in_rule__Operator__Alternatives3704); 

                    }

                     after(grammarAccess.getOperatorAccess().getEqualEnumLiteralDeclaration_4()); 

                    }


                    }
                    break;
                case 6 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1718:6: ( ( '!=' ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1718:6: ( ( '!=' ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1719:1: ( '!=' )
                    {
                     before(grammarAccess.getOperatorAccess().getNotEqualEnumLiteralDeclaration_5()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1720:1: ( '!=' )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1720:3: '!='
                    {
                    match(input,22,FOLLOW_22_in_rule__Operator__Alternatives3725); 

                    }

                     after(grammarAccess.getOperatorAccess().getNotEqualEnumLiteralDeclaration_5()); 

                    }


                    }
                    break;
                case 7 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1725:6: ( ( 'like' ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1725:6: ( ( 'like' ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1726:1: ( 'like' )
                    {
                     before(grammarAccess.getOperatorAccess().getLikeEnumLiteralDeclaration_6()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1727:1: ( 'like' )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1727:3: 'like'
                    {
                    match(input,23,FOLLOW_23_in_rule__Operator__Alternatives3746); 

                    }

                     after(grammarAccess.getOperatorAccess().getLikeEnumLiteralDeclaration_6()); 

                    }


                    }
                    break;
                case 8 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1732:6: ( ( 'not like' ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1732:6: ( ( 'not like' ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1733:1: ( 'not like' )
                    {
                     before(grammarAccess.getOperatorAccess().getNotLikeEnumLiteralDeclaration_7()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1734:1: ( 'not like' )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1734:3: 'not like'
                    {
                    match(input,24,FOLLOW_24_in_rule__Operator__Alternatives3767); 

                    }

                     after(grammarAccess.getOperatorAccess().getNotLikeEnumLiteralDeclaration_7()); 

                    }


                    }
                    break;
                case 9 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1739:6: ( ( 'not in' ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1739:6: ( ( 'not in' ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1740:1: ( 'not in' )
                    {
                     before(grammarAccess.getOperatorAccess().getNotInEnumLiteralDeclaration_8()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1741:1: ( 'not in' )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1741:3: 'not in'
                    {
                    match(input,16,FOLLOW_16_in_rule__Operator__Alternatives3788); 

                    }

                     after(grammarAccess.getOperatorAccess().getNotInEnumLiteralDeclaration_8()); 

                    }


                    }
                    break;
                case 10 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1746:6: ( ( 'in' ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1746:6: ( ( 'in' ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1747:1: ( 'in' )
                    {
                     before(grammarAccess.getOperatorAccess().getInEnumLiteralDeclaration_9()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1748:1: ( 'in' )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1748:3: 'in'
                    {
                    match(input,15,FOLLOW_15_in_rule__Operator__Alternatives3809); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1760:1: rule__Model__Group__0 : rule__Model__Group__0__Impl rule__Model__Group__1 ;
    public final void rule__Model__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1764:1: ( rule__Model__Group__0__Impl rule__Model__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1765:2: rule__Model__Group__0__Impl rule__Model__Group__1
            {
            pushFollow(FOLLOW_rule__Model__Group__0__Impl_in_rule__Model__Group__03842);
            rule__Model__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Model__Group__1_in_rule__Model__Group__03845);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1772:1: rule__Model__Group__0__Impl : ( 'SELECT' ) ;
    public final void rule__Model__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1776:1: ( ( 'SELECT' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1777:1: ( 'SELECT' )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1777:1: ( 'SELECT' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1778:1: 'SELECT'
            {
             before(grammarAccess.getModelAccess().getSELECTKeyword_0()); 
            match(input,25,FOLLOW_25_in_rule__Model__Group__0__Impl3873); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1791:1: rule__Model__Group__1 : rule__Model__Group__1__Impl rule__Model__Group__2 ;
    public final void rule__Model__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1795:1: ( rule__Model__Group__1__Impl rule__Model__Group__2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1796:2: rule__Model__Group__1__Impl rule__Model__Group__2
            {
            pushFollow(FOLLOW_rule__Model__Group__1__Impl_in_rule__Model__Group__13904);
            rule__Model__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Model__Group__2_in_rule__Model__Group__13907);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1803:1: rule__Model__Group__1__Impl : ( ( rule__Model__ColAssignment_1 )? ) ;
    public final void rule__Model__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1807:1: ( ( ( rule__Model__ColAssignment_1 )? ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1808:1: ( ( rule__Model__ColAssignment_1 )? )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1808:1: ( ( rule__Model__ColAssignment_1 )? )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1809:1: ( rule__Model__ColAssignment_1 )?
            {
             before(grammarAccess.getModelAccess().getColAssignment_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1810:1: ( rule__Model__ColAssignment_1 )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==RULE_ID) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1810:2: rule__Model__ColAssignment_1
                    {
                    pushFollow(FOLLOW_rule__Model__ColAssignment_1_in_rule__Model__Group__1__Impl3934);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1820:1: rule__Model__Group__2 : rule__Model__Group__2__Impl rule__Model__Group__3 ;
    public final void rule__Model__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1824:1: ( rule__Model__Group__2__Impl rule__Model__Group__3 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1825:2: rule__Model__Group__2__Impl rule__Model__Group__3
            {
            pushFollow(FOLLOW_rule__Model__Group__2__Impl_in_rule__Model__Group__23965);
            rule__Model__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Model__Group__3_in_rule__Model__Group__23968);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1832:1: rule__Model__Group__2__Impl : ( 'FROM' ) ;
    public final void rule__Model__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1836:1: ( ( 'FROM' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1837:1: ( 'FROM' )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1837:1: ( 'FROM' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1838:1: 'FROM'
            {
             before(grammarAccess.getModelAccess().getFROMKeyword_2()); 
            match(input,26,FOLLOW_26_in_rule__Model__Group__2__Impl3996); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1851:1: rule__Model__Group__3 : rule__Model__Group__3__Impl rule__Model__Group__4 ;
    public final void rule__Model__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1855:1: ( rule__Model__Group__3__Impl rule__Model__Group__4 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1856:2: rule__Model__Group__3__Impl rule__Model__Group__4
            {
            pushFollow(FOLLOW_rule__Model__Group__3__Impl_in_rule__Model__Group__34027);
            rule__Model__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Model__Group__4_in_rule__Model__Group__34030);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1863:1: rule__Model__Group__3__Impl : ( ( rule__Model__TblAssignment_3 ) ) ;
    public final void rule__Model__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1867:1: ( ( ( rule__Model__TblAssignment_3 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1868:1: ( ( rule__Model__TblAssignment_3 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1868:1: ( ( rule__Model__TblAssignment_3 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1869:1: ( rule__Model__TblAssignment_3 )
            {
             before(grammarAccess.getModelAccess().getTblAssignment_3()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1870:1: ( rule__Model__TblAssignment_3 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1870:2: rule__Model__TblAssignment_3
            {
            pushFollow(FOLLOW_rule__Model__TblAssignment_3_in_rule__Model__Group__3__Impl4057);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1880:1: rule__Model__Group__4 : rule__Model__Group__4__Impl rule__Model__Group__5 ;
    public final void rule__Model__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1884:1: ( rule__Model__Group__4__Impl rule__Model__Group__5 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1885:2: rule__Model__Group__4__Impl rule__Model__Group__5
            {
            pushFollow(FOLLOW_rule__Model__Group__4__Impl_in_rule__Model__Group__44087);
            rule__Model__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Model__Group__5_in_rule__Model__Group__44090);
            rule__Model__Group__5();

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1892:1: rule__Model__Group__4__Impl : ( ( rule__Model__Group_4__0 )? ) ;
    public final void rule__Model__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1896:1: ( ( ( rule__Model__Group_4__0 )? ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1897:1: ( ( rule__Model__Group_4__0 )? )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1897:1: ( ( rule__Model__Group_4__0 )? )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1898:1: ( rule__Model__Group_4__0 )?
            {
             before(grammarAccess.getModelAccess().getGroup_4()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1899:1: ( rule__Model__Group_4__0 )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==27) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1899:2: rule__Model__Group_4__0
                    {
                    pushFollow(FOLLOW_rule__Model__Group_4__0_in_rule__Model__Group__4__Impl4117);
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


    // $ANTLR start "rule__Model__Group__5"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1909:1: rule__Model__Group__5 : rule__Model__Group__5__Impl rule__Model__Group__6 ;
    public final void rule__Model__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1913:1: ( rule__Model__Group__5__Impl rule__Model__Group__6 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1914:2: rule__Model__Group__5__Impl rule__Model__Group__6
            {
            pushFollow(FOLLOW_rule__Model__Group__5__Impl_in_rule__Model__Group__54148);
            rule__Model__Group__5__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Model__Group__6_in_rule__Model__Group__54151);
            rule__Model__Group__6();

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
    // $ANTLR end "rule__Model__Group__5"


    // $ANTLR start "rule__Model__Group__5__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1921:1: rule__Model__Group__5__Impl : ( ( rule__Model__Group_5__0 )? ) ;
    public final void rule__Model__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1925:1: ( ( ( rule__Model__Group_5__0 )? ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1926:1: ( ( rule__Model__Group_5__0 )? )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1926:1: ( ( rule__Model__Group_5__0 )? )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1927:1: ( rule__Model__Group_5__0 )?
            {
             before(grammarAccess.getModelAccess().getGroup_5()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1928:1: ( rule__Model__Group_5__0 )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==28) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1928:2: rule__Model__Group_5__0
                    {
                    pushFollow(FOLLOW_rule__Model__Group_5__0_in_rule__Model__Group__5__Impl4178);
                    rule__Model__Group_5__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getModelAccess().getGroup_5()); 

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
    // $ANTLR end "rule__Model__Group__5__Impl"


    // $ANTLR start "rule__Model__Group__6"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1938:1: rule__Model__Group__6 : rule__Model__Group__6__Impl rule__Model__Group__7 ;
    public final void rule__Model__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1942:1: ( rule__Model__Group__6__Impl rule__Model__Group__7 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1943:2: rule__Model__Group__6__Impl rule__Model__Group__7
            {
            pushFollow(FOLLOW_rule__Model__Group__6__Impl_in_rule__Model__Group__64209);
            rule__Model__Group__6__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Model__Group__7_in_rule__Model__Group__64212);
            rule__Model__Group__7();

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
    // $ANTLR end "rule__Model__Group__6"


    // $ANTLR start "rule__Model__Group__6__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1950:1: rule__Model__Group__6__Impl : ( ( rule__Model__Group_6__0 )? ) ;
    public final void rule__Model__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1954:1: ( ( ( rule__Model__Group_6__0 )? ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1955:1: ( ( rule__Model__Group_6__0 )? )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1955:1: ( ( rule__Model__Group_6__0 )? )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1956:1: ( rule__Model__Group_6__0 )?
            {
             before(grammarAccess.getModelAccess().getGroup_6()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1957:1: ( rule__Model__Group_6__0 )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==29) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1957:2: rule__Model__Group_6__0
                    {
                    pushFollow(FOLLOW_rule__Model__Group_6__0_in_rule__Model__Group__6__Impl4239);
                    rule__Model__Group_6__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getModelAccess().getGroup_6()); 

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
    // $ANTLR end "rule__Model__Group__6__Impl"


    // $ANTLR start "rule__Model__Group__7"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1967:1: rule__Model__Group__7 : rule__Model__Group__7__Impl ;
    public final void rule__Model__Group__7() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1971:1: ( rule__Model__Group__7__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1972:2: rule__Model__Group__7__Impl
            {
            pushFollow(FOLLOW_rule__Model__Group__7__Impl_in_rule__Model__Group__74270);
            rule__Model__Group__7__Impl();

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
    // $ANTLR end "rule__Model__Group__7"


    // $ANTLR start "rule__Model__Group__7__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1978:1: rule__Model__Group__7__Impl : ( ( rule__Model__Group_7__0 )? ) ;
    public final void rule__Model__Group__7__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1982:1: ( ( ( rule__Model__Group_7__0 )? ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1983:1: ( ( rule__Model__Group_7__0 )? )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1983:1: ( ( rule__Model__Group_7__0 )? )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1984:1: ( rule__Model__Group_7__0 )?
            {
             before(grammarAccess.getModelAccess().getGroup_7()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1985:1: ( rule__Model__Group_7__0 )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==30) ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:1985:2: rule__Model__Group_7__0
                    {
                    pushFollow(FOLLOW_rule__Model__Group_7__0_in_rule__Model__Group__7__Impl4297);
                    rule__Model__Group_7__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getModelAccess().getGroup_7()); 

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
    // $ANTLR end "rule__Model__Group__7__Impl"


    // $ANTLR start "rule__Model__Group_4__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2011:1: rule__Model__Group_4__0 : rule__Model__Group_4__0__Impl rule__Model__Group_4__1 ;
    public final void rule__Model__Group_4__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2015:1: ( rule__Model__Group_4__0__Impl rule__Model__Group_4__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2016:2: rule__Model__Group_4__0__Impl rule__Model__Group_4__1
            {
            pushFollow(FOLLOW_rule__Model__Group_4__0__Impl_in_rule__Model__Group_4__04344);
            rule__Model__Group_4__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Model__Group_4__1_in_rule__Model__Group_4__04347);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2023:1: rule__Model__Group_4__0__Impl : ( 'WHERE' ) ;
    public final void rule__Model__Group_4__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2027:1: ( ( 'WHERE' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2028:1: ( 'WHERE' )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2028:1: ( 'WHERE' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2029:1: 'WHERE'
            {
             before(grammarAccess.getModelAccess().getWHEREKeyword_4_0()); 
            match(input,27,FOLLOW_27_in_rule__Model__Group_4__0__Impl4375); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2042:1: rule__Model__Group_4__1 : rule__Model__Group_4__1__Impl ;
    public final void rule__Model__Group_4__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2046:1: ( rule__Model__Group_4__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2047:2: rule__Model__Group_4__1__Impl
            {
            pushFollow(FOLLOW_rule__Model__Group_4__1__Impl_in_rule__Model__Group_4__14406);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2053:1: rule__Model__Group_4__1__Impl : ( ( rule__Model__WhereEntryAssignment_4_1 ) ) ;
    public final void rule__Model__Group_4__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2057:1: ( ( ( rule__Model__WhereEntryAssignment_4_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2058:1: ( ( rule__Model__WhereEntryAssignment_4_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2058:1: ( ( rule__Model__WhereEntryAssignment_4_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2059:1: ( rule__Model__WhereEntryAssignment_4_1 )
            {
             before(grammarAccess.getModelAccess().getWhereEntryAssignment_4_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2060:1: ( rule__Model__WhereEntryAssignment_4_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2060:2: rule__Model__WhereEntryAssignment_4_1
            {
            pushFollow(FOLLOW_rule__Model__WhereEntryAssignment_4_1_in_rule__Model__Group_4__1__Impl4433);
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


    // $ANTLR start "rule__Model__Group_5__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2074:1: rule__Model__Group_5__0 : rule__Model__Group_5__0__Impl rule__Model__Group_5__1 ;
    public final void rule__Model__Group_5__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2078:1: ( rule__Model__Group_5__0__Impl rule__Model__Group_5__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2079:2: rule__Model__Group_5__0__Impl rule__Model__Group_5__1
            {
            pushFollow(FOLLOW_rule__Model__Group_5__0__Impl_in_rule__Model__Group_5__04467);
            rule__Model__Group_5__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Model__Group_5__1_in_rule__Model__Group_5__04470);
            rule__Model__Group_5__1();

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
    // $ANTLR end "rule__Model__Group_5__0"


    // $ANTLR start "rule__Model__Group_5__0__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2086:1: rule__Model__Group_5__0__Impl : ( 'GROUP BY' ) ;
    public final void rule__Model__Group_5__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2090:1: ( ( 'GROUP BY' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2091:1: ( 'GROUP BY' )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2091:1: ( 'GROUP BY' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2092:1: 'GROUP BY'
            {
             before(grammarAccess.getModelAccess().getGROUPBYKeyword_5_0()); 
            match(input,28,FOLLOW_28_in_rule__Model__Group_5__0__Impl4498); 
             after(grammarAccess.getModelAccess().getGROUPBYKeyword_5_0()); 

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
    // $ANTLR end "rule__Model__Group_5__0__Impl"


    // $ANTLR start "rule__Model__Group_5__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2105:1: rule__Model__Group_5__1 : rule__Model__Group_5__1__Impl ;
    public final void rule__Model__Group_5__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2109:1: ( rule__Model__Group_5__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2110:2: rule__Model__Group_5__1__Impl
            {
            pushFollow(FOLLOW_rule__Model__Group_5__1__Impl_in_rule__Model__Group_5__14529);
            rule__Model__Group_5__1__Impl();

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
    // $ANTLR end "rule__Model__Group_5__1"


    // $ANTLR start "rule__Model__Group_5__1__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2116:1: rule__Model__Group_5__1__Impl : ( ( rule__Model__GroupByEntryAssignment_5_1 ) ) ;
    public final void rule__Model__Group_5__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2120:1: ( ( ( rule__Model__GroupByEntryAssignment_5_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2121:1: ( ( rule__Model__GroupByEntryAssignment_5_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2121:1: ( ( rule__Model__GroupByEntryAssignment_5_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2122:1: ( rule__Model__GroupByEntryAssignment_5_1 )
            {
             before(grammarAccess.getModelAccess().getGroupByEntryAssignment_5_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2123:1: ( rule__Model__GroupByEntryAssignment_5_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2123:2: rule__Model__GroupByEntryAssignment_5_1
            {
            pushFollow(FOLLOW_rule__Model__GroupByEntryAssignment_5_1_in_rule__Model__Group_5__1__Impl4556);
            rule__Model__GroupByEntryAssignment_5_1();

            state._fsp--;


            }

             after(grammarAccess.getModelAccess().getGroupByEntryAssignment_5_1()); 

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
    // $ANTLR end "rule__Model__Group_5__1__Impl"


    // $ANTLR start "rule__Model__Group_6__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2137:1: rule__Model__Group_6__0 : rule__Model__Group_6__0__Impl rule__Model__Group_6__1 ;
    public final void rule__Model__Group_6__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2141:1: ( rule__Model__Group_6__0__Impl rule__Model__Group_6__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2142:2: rule__Model__Group_6__0__Impl rule__Model__Group_6__1
            {
            pushFollow(FOLLOW_rule__Model__Group_6__0__Impl_in_rule__Model__Group_6__04590);
            rule__Model__Group_6__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Model__Group_6__1_in_rule__Model__Group_6__04593);
            rule__Model__Group_6__1();

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
    // $ANTLR end "rule__Model__Group_6__0"


    // $ANTLR start "rule__Model__Group_6__0__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2149:1: rule__Model__Group_6__0__Impl : ( 'HAVING' ) ;
    public final void rule__Model__Group_6__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2153:1: ( ( 'HAVING' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2154:1: ( 'HAVING' )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2154:1: ( 'HAVING' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2155:1: 'HAVING'
            {
             before(grammarAccess.getModelAccess().getHAVINGKeyword_6_0()); 
            match(input,29,FOLLOW_29_in_rule__Model__Group_6__0__Impl4621); 
             after(grammarAccess.getModelAccess().getHAVINGKeyword_6_0()); 

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
    // $ANTLR end "rule__Model__Group_6__0__Impl"


    // $ANTLR start "rule__Model__Group_6__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2168:1: rule__Model__Group_6__1 : rule__Model__Group_6__1__Impl ;
    public final void rule__Model__Group_6__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2172:1: ( rule__Model__Group_6__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2173:2: rule__Model__Group_6__1__Impl
            {
            pushFollow(FOLLOW_rule__Model__Group_6__1__Impl_in_rule__Model__Group_6__14652);
            rule__Model__Group_6__1__Impl();

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
    // $ANTLR end "rule__Model__Group_6__1"


    // $ANTLR start "rule__Model__Group_6__1__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2179:1: rule__Model__Group_6__1__Impl : ( ( rule__Model__HavingEntryAssignment_6_1 ) ) ;
    public final void rule__Model__Group_6__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2183:1: ( ( ( rule__Model__HavingEntryAssignment_6_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2184:1: ( ( rule__Model__HavingEntryAssignment_6_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2184:1: ( ( rule__Model__HavingEntryAssignment_6_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2185:1: ( rule__Model__HavingEntryAssignment_6_1 )
            {
             before(grammarAccess.getModelAccess().getHavingEntryAssignment_6_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2186:1: ( rule__Model__HavingEntryAssignment_6_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2186:2: rule__Model__HavingEntryAssignment_6_1
            {
            pushFollow(FOLLOW_rule__Model__HavingEntryAssignment_6_1_in_rule__Model__Group_6__1__Impl4679);
            rule__Model__HavingEntryAssignment_6_1();

            state._fsp--;


            }

             after(grammarAccess.getModelAccess().getHavingEntryAssignment_6_1()); 

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
    // $ANTLR end "rule__Model__Group_6__1__Impl"


    // $ANTLR start "rule__Model__Group_7__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2200:1: rule__Model__Group_7__0 : rule__Model__Group_7__0__Impl rule__Model__Group_7__1 ;
    public final void rule__Model__Group_7__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2204:1: ( rule__Model__Group_7__0__Impl rule__Model__Group_7__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2205:2: rule__Model__Group_7__0__Impl rule__Model__Group_7__1
            {
            pushFollow(FOLLOW_rule__Model__Group_7__0__Impl_in_rule__Model__Group_7__04713);
            rule__Model__Group_7__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Model__Group_7__1_in_rule__Model__Group_7__04716);
            rule__Model__Group_7__1();

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
    // $ANTLR end "rule__Model__Group_7__0"


    // $ANTLR start "rule__Model__Group_7__0__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2212:1: rule__Model__Group_7__0__Impl : ( 'ORDER BY' ) ;
    public final void rule__Model__Group_7__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2216:1: ( ( 'ORDER BY' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2217:1: ( 'ORDER BY' )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2217:1: ( 'ORDER BY' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2218:1: 'ORDER BY'
            {
             before(grammarAccess.getModelAccess().getORDERBYKeyword_7_0()); 
            match(input,30,FOLLOW_30_in_rule__Model__Group_7__0__Impl4744); 
             after(grammarAccess.getModelAccess().getORDERBYKeyword_7_0()); 

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
    // $ANTLR end "rule__Model__Group_7__0__Impl"


    // $ANTLR start "rule__Model__Group_7__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2231:1: rule__Model__Group_7__1 : rule__Model__Group_7__1__Impl ;
    public final void rule__Model__Group_7__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2235:1: ( rule__Model__Group_7__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2236:2: rule__Model__Group_7__1__Impl
            {
            pushFollow(FOLLOW_rule__Model__Group_7__1__Impl_in_rule__Model__Group_7__14775);
            rule__Model__Group_7__1__Impl();

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
    // $ANTLR end "rule__Model__Group_7__1"


    // $ANTLR start "rule__Model__Group_7__1__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2242:1: rule__Model__Group_7__1__Impl : ( ( rule__Model__OrderByEntryAssignment_7_1 ) ) ;
    public final void rule__Model__Group_7__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2246:1: ( ( ( rule__Model__OrderByEntryAssignment_7_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2247:1: ( ( rule__Model__OrderByEntryAssignment_7_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2247:1: ( ( rule__Model__OrderByEntryAssignment_7_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2248:1: ( rule__Model__OrderByEntryAssignment_7_1 )
            {
             before(grammarAccess.getModelAccess().getOrderByEntryAssignment_7_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2249:1: ( rule__Model__OrderByEntryAssignment_7_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2249:2: rule__Model__OrderByEntryAssignment_7_1
            {
            pushFollow(FOLLOW_rule__Model__OrderByEntryAssignment_7_1_in_rule__Model__Group_7__1__Impl4802);
            rule__Model__OrderByEntryAssignment_7_1();

            state._fsp--;


            }

             after(grammarAccess.getModelAccess().getOrderByEntryAssignment_7_1()); 

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
    // $ANTLR end "rule__Model__Group_7__1__Impl"


    // $ANTLR start "rule__OrderByColumns__Group__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2263:1: rule__OrderByColumns__Group__0 : rule__OrderByColumns__Group__0__Impl rule__OrderByColumns__Group__1 ;
    public final void rule__OrderByColumns__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2267:1: ( rule__OrderByColumns__Group__0__Impl rule__OrderByColumns__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2268:2: rule__OrderByColumns__Group__0__Impl rule__OrderByColumns__Group__1
            {
            pushFollow(FOLLOW_rule__OrderByColumns__Group__0__Impl_in_rule__OrderByColumns__Group__04836);
            rule__OrderByColumns__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__OrderByColumns__Group__1_in_rule__OrderByColumns__Group__04839);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2275:1: rule__OrderByColumns__Group__0__Impl : ( ruleOrderByColumnFull ) ;
    public final void rule__OrderByColumns__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2279:1: ( ( ruleOrderByColumnFull ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2280:1: ( ruleOrderByColumnFull )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2280:1: ( ruleOrderByColumnFull )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2281:1: ruleOrderByColumnFull
            {
             before(grammarAccess.getOrderByColumnsAccess().getOrderByColumnFullParserRuleCall_0()); 
            pushFollow(FOLLOW_ruleOrderByColumnFull_in_rule__OrderByColumns__Group__0__Impl4866);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2292:1: rule__OrderByColumns__Group__1 : rule__OrderByColumns__Group__1__Impl ;
    public final void rule__OrderByColumns__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2296:1: ( rule__OrderByColumns__Group__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2297:2: rule__OrderByColumns__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__OrderByColumns__Group__1__Impl_in_rule__OrderByColumns__Group__14895);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2303:1: rule__OrderByColumns__Group__1__Impl : ( ( rule__OrderByColumns__Group_1__0 )? ) ;
    public final void rule__OrderByColumns__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2307:1: ( ( ( rule__OrderByColumns__Group_1__0 )? ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2308:1: ( ( rule__OrderByColumns__Group_1__0 )? )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2308:1: ( ( rule__OrderByColumns__Group_1__0 )? )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2309:1: ( rule__OrderByColumns__Group_1__0 )?
            {
             before(grammarAccess.getOrderByColumnsAccess().getGroup_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2310:1: ( rule__OrderByColumns__Group_1__0 )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==31) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2310:2: rule__OrderByColumns__Group_1__0
                    {
                    pushFollow(FOLLOW_rule__OrderByColumns__Group_1__0_in_rule__OrderByColumns__Group__1__Impl4922);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2324:1: rule__OrderByColumns__Group_1__0 : rule__OrderByColumns__Group_1__0__Impl rule__OrderByColumns__Group_1__1 ;
    public final void rule__OrderByColumns__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2328:1: ( rule__OrderByColumns__Group_1__0__Impl rule__OrderByColumns__Group_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2329:2: rule__OrderByColumns__Group_1__0__Impl rule__OrderByColumns__Group_1__1
            {
            pushFollow(FOLLOW_rule__OrderByColumns__Group_1__0__Impl_in_rule__OrderByColumns__Group_1__04957);
            rule__OrderByColumns__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__OrderByColumns__Group_1__1_in_rule__OrderByColumns__Group_1__04960);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2336:1: rule__OrderByColumns__Group_1__0__Impl : ( () ) ;
    public final void rule__OrderByColumns__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2340:1: ( ( () ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2341:1: ( () )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2341:1: ( () )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2342:1: ()
            {
             before(grammarAccess.getOrderByColumnsAccess().getOrOrderByColumnEntriesAction_1_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2343:1: ()
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2345:1: 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2355:1: rule__OrderByColumns__Group_1__1 : rule__OrderByColumns__Group_1__1__Impl ;
    public final void rule__OrderByColumns__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2359:1: ( rule__OrderByColumns__Group_1__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2360:2: rule__OrderByColumns__Group_1__1__Impl
            {
            pushFollow(FOLLOW_rule__OrderByColumns__Group_1__1__Impl_in_rule__OrderByColumns__Group_1__15018);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2366:1: rule__OrderByColumns__Group_1__1__Impl : ( ( ( rule__OrderByColumns__Group_1_1__0 ) ) ( ( rule__OrderByColumns__Group_1_1__0 )* ) ) ;
    public final void rule__OrderByColumns__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2370:1: ( ( ( ( rule__OrderByColumns__Group_1_1__0 ) ) ( ( rule__OrderByColumns__Group_1_1__0 )* ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2371:1: ( ( ( rule__OrderByColumns__Group_1_1__0 ) ) ( ( rule__OrderByColumns__Group_1_1__0 )* ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2371:1: ( ( ( rule__OrderByColumns__Group_1_1__0 ) ) ( ( rule__OrderByColumns__Group_1_1__0 )* ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2372:1: ( ( rule__OrderByColumns__Group_1_1__0 ) ) ( ( rule__OrderByColumns__Group_1_1__0 )* )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2372:1: ( ( rule__OrderByColumns__Group_1_1__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2373:1: ( rule__OrderByColumns__Group_1_1__0 )
            {
             before(grammarAccess.getOrderByColumnsAccess().getGroup_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2374:1: ( rule__OrderByColumns__Group_1_1__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2374:2: rule__OrderByColumns__Group_1_1__0
            {
            pushFollow(FOLLOW_rule__OrderByColumns__Group_1_1__0_in_rule__OrderByColumns__Group_1__1__Impl5047);
            rule__OrderByColumns__Group_1_1__0();

            state._fsp--;


            }

             after(grammarAccess.getOrderByColumnsAccess().getGroup_1_1()); 

            }

            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2377:1: ( ( rule__OrderByColumns__Group_1_1__0 )* )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2378:1: ( rule__OrderByColumns__Group_1_1__0 )*
            {
             before(grammarAccess.getOrderByColumnsAccess().getGroup_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2379:1: ( rule__OrderByColumns__Group_1_1__0 )*
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( (LA22_0==31) ) {
                    alt22=1;
                }


                switch (alt22) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2379:2: rule__OrderByColumns__Group_1_1__0
            	    {
            	    pushFollow(FOLLOW_rule__OrderByColumns__Group_1_1__0_in_rule__OrderByColumns__Group_1__1__Impl5059);
            	    rule__OrderByColumns__Group_1_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop22;
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2394:1: rule__OrderByColumns__Group_1_1__0 : rule__OrderByColumns__Group_1_1__0__Impl rule__OrderByColumns__Group_1_1__1 ;
    public final void rule__OrderByColumns__Group_1_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2398:1: ( rule__OrderByColumns__Group_1_1__0__Impl rule__OrderByColumns__Group_1_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2399:2: rule__OrderByColumns__Group_1_1__0__Impl rule__OrderByColumns__Group_1_1__1
            {
            pushFollow(FOLLOW_rule__OrderByColumns__Group_1_1__0__Impl_in_rule__OrderByColumns__Group_1_1__05096);
            rule__OrderByColumns__Group_1_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__OrderByColumns__Group_1_1__1_in_rule__OrderByColumns__Group_1_1__05099);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2406:1: rule__OrderByColumns__Group_1_1__0__Impl : ( ',' ) ;
    public final void rule__OrderByColumns__Group_1_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2410:1: ( ( ',' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2411:1: ( ',' )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2411:1: ( ',' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2412:1: ','
            {
             before(grammarAccess.getOrderByColumnsAccess().getCommaKeyword_1_1_0()); 
            match(input,31,FOLLOW_31_in_rule__OrderByColumns__Group_1_1__0__Impl5127); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2425:1: rule__OrderByColumns__Group_1_1__1 : rule__OrderByColumns__Group_1_1__1__Impl ;
    public final void rule__OrderByColumns__Group_1_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2429:1: ( rule__OrderByColumns__Group_1_1__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2430:2: rule__OrderByColumns__Group_1_1__1__Impl
            {
            pushFollow(FOLLOW_rule__OrderByColumns__Group_1_1__1__Impl_in_rule__OrderByColumns__Group_1_1__15158);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2436:1: rule__OrderByColumns__Group_1_1__1__Impl : ( ( rule__OrderByColumns__EntriesAssignment_1_1_1 ) ) ;
    public final void rule__OrderByColumns__Group_1_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2440:1: ( ( ( rule__OrderByColumns__EntriesAssignment_1_1_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2441:1: ( ( rule__OrderByColumns__EntriesAssignment_1_1_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2441:1: ( ( rule__OrderByColumns__EntriesAssignment_1_1_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2442:1: ( rule__OrderByColumns__EntriesAssignment_1_1_1 )
            {
             before(grammarAccess.getOrderByColumnsAccess().getEntriesAssignment_1_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2443:1: ( rule__OrderByColumns__EntriesAssignment_1_1_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2443:2: rule__OrderByColumns__EntriesAssignment_1_1_1
            {
            pushFollow(FOLLOW_rule__OrderByColumns__EntriesAssignment_1_1_1_in_rule__OrderByColumns__Group_1_1__1__Impl5185);
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


    // $ANTLR start "rule__OrderByColumnFull__Group_1__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2457:1: rule__OrderByColumnFull__Group_1__0 : rule__OrderByColumnFull__Group_1__0__Impl rule__OrderByColumnFull__Group_1__1 ;
    public final void rule__OrderByColumnFull__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2461:1: ( rule__OrderByColumnFull__Group_1__0__Impl rule__OrderByColumnFull__Group_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2462:2: rule__OrderByColumnFull__Group_1__0__Impl rule__OrderByColumnFull__Group_1__1
            {
            pushFollow(FOLLOW_rule__OrderByColumnFull__Group_1__0__Impl_in_rule__OrderByColumnFull__Group_1__05219);
            rule__OrderByColumnFull__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__OrderByColumnFull__Group_1__1_in_rule__OrderByColumnFull__Group_1__05222);
            rule__OrderByColumnFull__Group_1__1();

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
    // $ANTLR end "rule__OrderByColumnFull__Group_1__0"


    // $ANTLR start "rule__OrderByColumnFull__Group_1__0__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2469:1: rule__OrderByColumnFull__Group_1__0__Impl : ( ruleTableFull ) ;
    public final void rule__OrderByColumnFull__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2473:1: ( ( ruleTableFull ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2474:1: ( ruleTableFull )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2474:1: ( ruleTableFull )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2475:1: ruleTableFull
            {
             before(grammarAccess.getOrderByColumnFullAccess().getTableFullParserRuleCall_1_0()); 
            pushFollow(FOLLOW_ruleTableFull_in_rule__OrderByColumnFull__Group_1__0__Impl5249);
            ruleTableFull();

            state._fsp--;

             after(grammarAccess.getOrderByColumnFullAccess().getTableFullParserRuleCall_1_0()); 

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
    // $ANTLR end "rule__OrderByColumnFull__Group_1__0__Impl"


    // $ANTLR start "rule__OrderByColumnFull__Group_1__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2486:1: rule__OrderByColumnFull__Group_1__1 : rule__OrderByColumnFull__Group_1__1__Impl rule__OrderByColumnFull__Group_1__2 ;
    public final void rule__OrderByColumnFull__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2490:1: ( rule__OrderByColumnFull__Group_1__1__Impl rule__OrderByColumnFull__Group_1__2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2491:2: rule__OrderByColumnFull__Group_1__1__Impl rule__OrderByColumnFull__Group_1__2
            {
            pushFollow(FOLLOW_rule__OrderByColumnFull__Group_1__1__Impl_in_rule__OrderByColumnFull__Group_1__15278);
            rule__OrderByColumnFull__Group_1__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__OrderByColumnFull__Group_1__2_in_rule__OrderByColumnFull__Group_1__15281);
            rule__OrderByColumnFull__Group_1__2();

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
    // $ANTLR end "rule__OrderByColumnFull__Group_1__1"


    // $ANTLR start "rule__OrderByColumnFull__Group_1__1__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2498:1: rule__OrderByColumnFull__Group_1__1__Impl : ( '.' ) ;
    public final void rule__OrderByColumnFull__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2502:1: ( ( '.' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2503:1: ( '.' )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2503:1: ( '.' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2504:1: '.'
            {
             before(grammarAccess.getOrderByColumnFullAccess().getFullStopKeyword_1_1()); 
            match(input,32,FOLLOW_32_in_rule__OrderByColumnFull__Group_1__1__Impl5309); 
             after(grammarAccess.getOrderByColumnFullAccess().getFullStopKeyword_1_1()); 

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
    // $ANTLR end "rule__OrderByColumnFull__Group_1__1__Impl"


    // $ANTLR start "rule__OrderByColumnFull__Group_1__2"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2517:1: rule__OrderByColumnFull__Group_1__2 : rule__OrderByColumnFull__Group_1__2__Impl ;
    public final void rule__OrderByColumnFull__Group_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2521:1: ( rule__OrderByColumnFull__Group_1__2__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2522:2: rule__OrderByColumnFull__Group_1__2__Impl
            {
            pushFollow(FOLLOW_rule__OrderByColumnFull__Group_1__2__Impl_in_rule__OrderByColumnFull__Group_1__25340);
            rule__OrderByColumnFull__Group_1__2__Impl();

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
    // $ANTLR end "rule__OrderByColumnFull__Group_1__2"


    // $ANTLR start "rule__OrderByColumnFull__Group_1__2__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2528:1: rule__OrderByColumnFull__Group_1__2__Impl : ( ( rule__OrderByColumnFull__ColOrderAssignment_1_2 ) ) ;
    public final void rule__OrderByColumnFull__Group_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2532:1: ( ( ( rule__OrderByColumnFull__ColOrderAssignment_1_2 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2533:1: ( ( rule__OrderByColumnFull__ColOrderAssignment_1_2 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2533:1: ( ( rule__OrderByColumnFull__ColOrderAssignment_1_2 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2534:1: ( rule__OrderByColumnFull__ColOrderAssignment_1_2 )
            {
             before(grammarAccess.getOrderByColumnFullAccess().getColOrderAssignment_1_2()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2535:1: ( rule__OrderByColumnFull__ColOrderAssignment_1_2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2535:2: rule__OrderByColumnFull__ColOrderAssignment_1_2
            {
            pushFollow(FOLLOW_rule__OrderByColumnFull__ColOrderAssignment_1_2_in_rule__OrderByColumnFull__Group_1__2__Impl5367);
            rule__OrderByColumnFull__ColOrderAssignment_1_2();

            state._fsp--;


            }

             after(grammarAccess.getOrderByColumnFullAccess().getColOrderAssignment_1_2()); 

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
    // $ANTLR end "rule__OrderByColumnFull__Group_1__2__Impl"


    // $ANTLR start "rule__GroupByColumns__Group__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2551:1: rule__GroupByColumns__Group__0 : rule__GroupByColumns__Group__0__Impl rule__GroupByColumns__Group__1 ;
    public final void rule__GroupByColumns__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2555:1: ( rule__GroupByColumns__Group__0__Impl rule__GroupByColumns__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2556:2: rule__GroupByColumns__Group__0__Impl rule__GroupByColumns__Group__1
            {
            pushFollow(FOLLOW_rule__GroupByColumns__Group__0__Impl_in_rule__GroupByColumns__Group__05403);
            rule__GroupByColumns__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__GroupByColumns__Group__1_in_rule__GroupByColumns__Group__05406);
            rule__GroupByColumns__Group__1();

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
    // $ANTLR end "rule__GroupByColumns__Group__0"


    // $ANTLR start "rule__GroupByColumns__Group__0__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2563:1: rule__GroupByColumns__Group__0__Impl : ( ruleGroupByColumnFull ) ;
    public final void rule__GroupByColumns__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2567:1: ( ( ruleGroupByColumnFull ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2568:1: ( ruleGroupByColumnFull )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2568:1: ( ruleGroupByColumnFull )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2569:1: ruleGroupByColumnFull
            {
             before(grammarAccess.getGroupByColumnsAccess().getGroupByColumnFullParserRuleCall_0()); 
            pushFollow(FOLLOW_ruleGroupByColumnFull_in_rule__GroupByColumns__Group__0__Impl5433);
            ruleGroupByColumnFull();

            state._fsp--;

             after(grammarAccess.getGroupByColumnsAccess().getGroupByColumnFullParserRuleCall_0()); 

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
    // $ANTLR end "rule__GroupByColumns__Group__0__Impl"


    // $ANTLR start "rule__GroupByColumns__Group__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2580:1: rule__GroupByColumns__Group__1 : rule__GroupByColumns__Group__1__Impl ;
    public final void rule__GroupByColumns__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2584:1: ( rule__GroupByColumns__Group__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2585:2: rule__GroupByColumns__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__GroupByColumns__Group__1__Impl_in_rule__GroupByColumns__Group__15462);
            rule__GroupByColumns__Group__1__Impl();

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
    // $ANTLR end "rule__GroupByColumns__Group__1"


    // $ANTLR start "rule__GroupByColumns__Group__1__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2591:1: rule__GroupByColumns__Group__1__Impl : ( ( rule__GroupByColumns__Group_1__0 )? ) ;
    public final void rule__GroupByColumns__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2595:1: ( ( ( rule__GroupByColumns__Group_1__0 )? ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2596:1: ( ( rule__GroupByColumns__Group_1__0 )? )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2596:1: ( ( rule__GroupByColumns__Group_1__0 )? )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2597:1: ( rule__GroupByColumns__Group_1__0 )?
            {
             before(grammarAccess.getGroupByColumnsAccess().getGroup_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2598:1: ( rule__GroupByColumns__Group_1__0 )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==31) ) {
                alt23=1;
            }
            switch (alt23) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2598:2: rule__GroupByColumns__Group_1__0
                    {
                    pushFollow(FOLLOW_rule__GroupByColumns__Group_1__0_in_rule__GroupByColumns__Group__1__Impl5489);
                    rule__GroupByColumns__Group_1__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getGroupByColumnsAccess().getGroup_1()); 

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
    // $ANTLR end "rule__GroupByColumns__Group__1__Impl"


    // $ANTLR start "rule__GroupByColumns__Group_1__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2612:1: rule__GroupByColumns__Group_1__0 : rule__GroupByColumns__Group_1__0__Impl rule__GroupByColumns__Group_1__1 ;
    public final void rule__GroupByColumns__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2616:1: ( rule__GroupByColumns__Group_1__0__Impl rule__GroupByColumns__Group_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2617:2: rule__GroupByColumns__Group_1__0__Impl rule__GroupByColumns__Group_1__1
            {
            pushFollow(FOLLOW_rule__GroupByColumns__Group_1__0__Impl_in_rule__GroupByColumns__Group_1__05524);
            rule__GroupByColumns__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__GroupByColumns__Group_1__1_in_rule__GroupByColumns__Group_1__05527);
            rule__GroupByColumns__Group_1__1();

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
    // $ANTLR end "rule__GroupByColumns__Group_1__0"


    // $ANTLR start "rule__GroupByColumns__Group_1__0__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2624:1: rule__GroupByColumns__Group_1__0__Impl : ( () ) ;
    public final void rule__GroupByColumns__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2628:1: ( ( () ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2629:1: ( () )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2629:1: ( () )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2630:1: ()
            {
             before(grammarAccess.getGroupByColumnsAccess().getOrGroupByColumnEntriesAction_1_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2631:1: ()
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2633:1: 
            {
            }

             after(grammarAccess.getGroupByColumnsAccess().getOrGroupByColumnEntriesAction_1_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__GroupByColumns__Group_1__0__Impl"


    // $ANTLR start "rule__GroupByColumns__Group_1__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2643:1: rule__GroupByColumns__Group_1__1 : rule__GroupByColumns__Group_1__1__Impl ;
    public final void rule__GroupByColumns__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2647:1: ( rule__GroupByColumns__Group_1__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2648:2: rule__GroupByColumns__Group_1__1__Impl
            {
            pushFollow(FOLLOW_rule__GroupByColumns__Group_1__1__Impl_in_rule__GroupByColumns__Group_1__15585);
            rule__GroupByColumns__Group_1__1__Impl();

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
    // $ANTLR end "rule__GroupByColumns__Group_1__1"


    // $ANTLR start "rule__GroupByColumns__Group_1__1__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2654:1: rule__GroupByColumns__Group_1__1__Impl : ( ( ( rule__GroupByColumns__Group_1_1__0 ) ) ( ( rule__GroupByColumns__Group_1_1__0 )* ) ) ;
    public final void rule__GroupByColumns__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2658:1: ( ( ( ( rule__GroupByColumns__Group_1_1__0 ) ) ( ( rule__GroupByColumns__Group_1_1__0 )* ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2659:1: ( ( ( rule__GroupByColumns__Group_1_1__0 ) ) ( ( rule__GroupByColumns__Group_1_1__0 )* ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2659:1: ( ( ( rule__GroupByColumns__Group_1_1__0 ) ) ( ( rule__GroupByColumns__Group_1_1__0 )* ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2660:1: ( ( rule__GroupByColumns__Group_1_1__0 ) ) ( ( rule__GroupByColumns__Group_1_1__0 )* )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2660:1: ( ( rule__GroupByColumns__Group_1_1__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2661:1: ( rule__GroupByColumns__Group_1_1__0 )
            {
             before(grammarAccess.getGroupByColumnsAccess().getGroup_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2662:1: ( rule__GroupByColumns__Group_1_1__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2662:2: rule__GroupByColumns__Group_1_1__0
            {
            pushFollow(FOLLOW_rule__GroupByColumns__Group_1_1__0_in_rule__GroupByColumns__Group_1__1__Impl5614);
            rule__GroupByColumns__Group_1_1__0();

            state._fsp--;


            }

             after(grammarAccess.getGroupByColumnsAccess().getGroup_1_1()); 

            }

            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2665:1: ( ( rule__GroupByColumns__Group_1_1__0 )* )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2666:1: ( rule__GroupByColumns__Group_1_1__0 )*
            {
             before(grammarAccess.getGroupByColumnsAccess().getGroup_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2667:1: ( rule__GroupByColumns__Group_1_1__0 )*
            loop24:
            do {
                int alt24=2;
                int LA24_0 = input.LA(1);

                if ( (LA24_0==31) ) {
                    alt24=1;
                }


                switch (alt24) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2667:2: rule__GroupByColumns__Group_1_1__0
            	    {
            	    pushFollow(FOLLOW_rule__GroupByColumns__Group_1_1__0_in_rule__GroupByColumns__Group_1__1__Impl5626);
            	    rule__GroupByColumns__Group_1_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop24;
                }
            } while (true);

             after(grammarAccess.getGroupByColumnsAccess().getGroup_1_1()); 

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
    // $ANTLR end "rule__GroupByColumns__Group_1__1__Impl"


    // $ANTLR start "rule__GroupByColumns__Group_1_1__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2682:1: rule__GroupByColumns__Group_1_1__0 : rule__GroupByColumns__Group_1_1__0__Impl rule__GroupByColumns__Group_1_1__1 ;
    public final void rule__GroupByColumns__Group_1_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2686:1: ( rule__GroupByColumns__Group_1_1__0__Impl rule__GroupByColumns__Group_1_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2687:2: rule__GroupByColumns__Group_1_1__0__Impl rule__GroupByColumns__Group_1_1__1
            {
            pushFollow(FOLLOW_rule__GroupByColumns__Group_1_1__0__Impl_in_rule__GroupByColumns__Group_1_1__05663);
            rule__GroupByColumns__Group_1_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__GroupByColumns__Group_1_1__1_in_rule__GroupByColumns__Group_1_1__05666);
            rule__GroupByColumns__Group_1_1__1();

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
    // $ANTLR end "rule__GroupByColumns__Group_1_1__0"


    // $ANTLR start "rule__GroupByColumns__Group_1_1__0__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2694:1: rule__GroupByColumns__Group_1_1__0__Impl : ( ',' ) ;
    public final void rule__GroupByColumns__Group_1_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2698:1: ( ( ',' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2699:1: ( ',' )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2699:1: ( ',' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2700:1: ','
            {
             before(grammarAccess.getGroupByColumnsAccess().getCommaKeyword_1_1_0()); 
            match(input,31,FOLLOW_31_in_rule__GroupByColumns__Group_1_1__0__Impl5694); 
             after(grammarAccess.getGroupByColumnsAccess().getCommaKeyword_1_1_0()); 

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
    // $ANTLR end "rule__GroupByColumns__Group_1_1__0__Impl"


    // $ANTLR start "rule__GroupByColumns__Group_1_1__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2713:1: rule__GroupByColumns__Group_1_1__1 : rule__GroupByColumns__Group_1_1__1__Impl ;
    public final void rule__GroupByColumns__Group_1_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2717:1: ( rule__GroupByColumns__Group_1_1__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2718:2: rule__GroupByColumns__Group_1_1__1__Impl
            {
            pushFollow(FOLLOW_rule__GroupByColumns__Group_1_1__1__Impl_in_rule__GroupByColumns__Group_1_1__15725);
            rule__GroupByColumns__Group_1_1__1__Impl();

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
    // $ANTLR end "rule__GroupByColumns__Group_1_1__1"


    // $ANTLR start "rule__GroupByColumns__Group_1_1__1__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2724:1: rule__GroupByColumns__Group_1_1__1__Impl : ( ( rule__GroupByColumns__EntriesAssignment_1_1_1 ) ) ;
    public final void rule__GroupByColumns__Group_1_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2728:1: ( ( ( rule__GroupByColumns__EntriesAssignment_1_1_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2729:1: ( ( rule__GroupByColumns__EntriesAssignment_1_1_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2729:1: ( ( rule__GroupByColumns__EntriesAssignment_1_1_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2730:1: ( rule__GroupByColumns__EntriesAssignment_1_1_1 )
            {
             before(grammarAccess.getGroupByColumnsAccess().getEntriesAssignment_1_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2731:1: ( rule__GroupByColumns__EntriesAssignment_1_1_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2731:2: rule__GroupByColumns__EntriesAssignment_1_1_1
            {
            pushFollow(FOLLOW_rule__GroupByColumns__EntriesAssignment_1_1_1_in_rule__GroupByColumns__Group_1_1__1__Impl5752);
            rule__GroupByColumns__EntriesAssignment_1_1_1();

            state._fsp--;


            }

             after(grammarAccess.getGroupByColumnsAccess().getEntriesAssignment_1_1_1()); 

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
    // $ANTLR end "rule__GroupByColumns__Group_1_1__1__Impl"


    // $ANTLR start "rule__GroupByColumnFull__Group_1__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2745:1: rule__GroupByColumnFull__Group_1__0 : rule__GroupByColumnFull__Group_1__0__Impl rule__GroupByColumnFull__Group_1__1 ;
    public final void rule__GroupByColumnFull__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2749:1: ( rule__GroupByColumnFull__Group_1__0__Impl rule__GroupByColumnFull__Group_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2750:2: rule__GroupByColumnFull__Group_1__0__Impl rule__GroupByColumnFull__Group_1__1
            {
            pushFollow(FOLLOW_rule__GroupByColumnFull__Group_1__0__Impl_in_rule__GroupByColumnFull__Group_1__05786);
            rule__GroupByColumnFull__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__GroupByColumnFull__Group_1__1_in_rule__GroupByColumnFull__Group_1__05789);
            rule__GroupByColumnFull__Group_1__1();

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
    // $ANTLR end "rule__GroupByColumnFull__Group_1__0"


    // $ANTLR start "rule__GroupByColumnFull__Group_1__0__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2757:1: rule__GroupByColumnFull__Group_1__0__Impl : ( ruleTableFull ) ;
    public final void rule__GroupByColumnFull__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2761:1: ( ( ruleTableFull ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2762:1: ( ruleTableFull )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2762:1: ( ruleTableFull )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2763:1: ruleTableFull
            {
             before(grammarAccess.getGroupByColumnFullAccess().getTableFullParserRuleCall_1_0()); 
            pushFollow(FOLLOW_ruleTableFull_in_rule__GroupByColumnFull__Group_1__0__Impl5816);
            ruleTableFull();

            state._fsp--;

             after(grammarAccess.getGroupByColumnFullAccess().getTableFullParserRuleCall_1_0()); 

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
    // $ANTLR end "rule__GroupByColumnFull__Group_1__0__Impl"


    // $ANTLR start "rule__GroupByColumnFull__Group_1__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2774:1: rule__GroupByColumnFull__Group_1__1 : rule__GroupByColumnFull__Group_1__1__Impl rule__GroupByColumnFull__Group_1__2 ;
    public final void rule__GroupByColumnFull__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2778:1: ( rule__GroupByColumnFull__Group_1__1__Impl rule__GroupByColumnFull__Group_1__2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2779:2: rule__GroupByColumnFull__Group_1__1__Impl rule__GroupByColumnFull__Group_1__2
            {
            pushFollow(FOLLOW_rule__GroupByColumnFull__Group_1__1__Impl_in_rule__GroupByColumnFull__Group_1__15845);
            rule__GroupByColumnFull__Group_1__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__GroupByColumnFull__Group_1__2_in_rule__GroupByColumnFull__Group_1__15848);
            rule__GroupByColumnFull__Group_1__2();

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
    // $ANTLR end "rule__GroupByColumnFull__Group_1__1"


    // $ANTLR start "rule__GroupByColumnFull__Group_1__1__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2786:1: rule__GroupByColumnFull__Group_1__1__Impl : ( '.' ) ;
    public final void rule__GroupByColumnFull__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2790:1: ( ( '.' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2791:1: ( '.' )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2791:1: ( '.' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2792:1: '.'
            {
             before(grammarAccess.getGroupByColumnFullAccess().getFullStopKeyword_1_1()); 
            match(input,32,FOLLOW_32_in_rule__GroupByColumnFull__Group_1__1__Impl5876); 
             after(grammarAccess.getGroupByColumnFullAccess().getFullStopKeyword_1_1()); 

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
    // $ANTLR end "rule__GroupByColumnFull__Group_1__1__Impl"


    // $ANTLR start "rule__GroupByColumnFull__Group_1__2"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2805:1: rule__GroupByColumnFull__Group_1__2 : rule__GroupByColumnFull__Group_1__2__Impl ;
    public final void rule__GroupByColumnFull__Group_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2809:1: ( rule__GroupByColumnFull__Group_1__2__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2810:2: rule__GroupByColumnFull__Group_1__2__Impl
            {
            pushFollow(FOLLOW_rule__GroupByColumnFull__Group_1__2__Impl_in_rule__GroupByColumnFull__Group_1__25907);
            rule__GroupByColumnFull__Group_1__2__Impl();

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
    // $ANTLR end "rule__GroupByColumnFull__Group_1__2"


    // $ANTLR start "rule__GroupByColumnFull__Group_1__2__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2816:1: rule__GroupByColumnFull__Group_1__2__Impl : ( ( rule__GroupByColumnFull__GroupByColumnAssignment_1_2 ) ) ;
    public final void rule__GroupByColumnFull__Group_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2820:1: ( ( ( rule__GroupByColumnFull__GroupByColumnAssignment_1_2 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2821:1: ( ( rule__GroupByColumnFull__GroupByColumnAssignment_1_2 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2821:1: ( ( rule__GroupByColumnFull__GroupByColumnAssignment_1_2 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2822:1: ( rule__GroupByColumnFull__GroupByColumnAssignment_1_2 )
            {
             before(grammarAccess.getGroupByColumnFullAccess().getGroupByColumnAssignment_1_2()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2823:1: ( rule__GroupByColumnFull__GroupByColumnAssignment_1_2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2823:2: rule__GroupByColumnFull__GroupByColumnAssignment_1_2
            {
            pushFollow(FOLLOW_rule__GroupByColumnFull__GroupByColumnAssignment_1_2_in_rule__GroupByColumnFull__Group_1__2__Impl5934);
            rule__GroupByColumnFull__GroupByColumnAssignment_1_2();

            state._fsp--;


            }

             after(grammarAccess.getGroupByColumnFullAccess().getGroupByColumnAssignment_1_2()); 

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
    // $ANTLR end "rule__GroupByColumnFull__Group_1__2__Impl"


    // $ANTLR start "rule__Columns__Group__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2839:1: rule__Columns__Group__0 : rule__Columns__Group__0__Impl rule__Columns__Group__1 ;
    public final void rule__Columns__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2843:1: ( rule__Columns__Group__0__Impl rule__Columns__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2844:2: rule__Columns__Group__0__Impl rule__Columns__Group__1
            {
            pushFollow(FOLLOW_rule__Columns__Group__0__Impl_in_rule__Columns__Group__05970);
            rule__Columns__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Columns__Group__1_in_rule__Columns__Group__05973);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2851:1: rule__Columns__Group__0__Impl : ( ruleColumnOrAlias ) ;
    public final void rule__Columns__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2855:1: ( ( ruleColumnOrAlias ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2856:1: ( ruleColumnOrAlias )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2856:1: ( ruleColumnOrAlias )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2857:1: ruleColumnOrAlias
            {
             before(grammarAccess.getColumnsAccess().getColumnOrAliasParserRuleCall_0()); 
            pushFollow(FOLLOW_ruleColumnOrAlias_in_rule__Columns__Group__0__Impl6000);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2868:1: rule__Columns__Group__1 : rule__Columns__Group__1__Impl ;
    public final void rule__Columns__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2872:1: ( rule__Columns__Group__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2873:2: rule__Columns__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__Columns__Group__1__Impl_in_rule__Columns__Group__16029);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2879:1: rule__Columns__Group__1__Impl : ( ( rule__Columns__Group_1__0 )? ) ;
    public final void rule__Columns__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2883:1: ( ( ( rule__Columns__Group_1__0 )? ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2884:1: ( ( rule__Columns__Group_1__0 )? )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2884:1: ( ( rule__Columns__Group_1__0 )? )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2885:1: ( rule__Columns__Group_1__0 )?
            {
             before(grammarAccess.getColumnsAccess().getGroup_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2886:1: ( rule__Columns__Group_1__0 )?
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==31) ) {
                alt25=1;
            }
            switch (alt25) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2886:2: rule__Columns__Group_1__0
                    {
                    pushFollow(FOLLOW_rule__Columns__Group_1__0_in_rule__Columns__Group__1__Impl6056);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2900:1: rule__Columns__Group_1__0 : rule__Columns__Group_1__0__Impl rule__Columns__Group_1__1 ;
    public final void rule__Columns__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2904:1: ( rule__Columns__Group_1__0__Impl rule__Columns__Group_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2905:2: rule__Columns__Group_1__0__Impl rule__Columns__Group_1__1
            {
            pushFollow(FOLLOW_rule__Columns__Group_1__0__Impl_in_rule__Columns__Group_1__06091);
            rule__Columns__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Columns__Group_1__1_in_rule__Columns__Group_1__06094);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2912:1: rule__Columns__Group_1__0__Impl : ( () ) ;
    public final void rule__Columns__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2916:1: ( ( () ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2917:1: ( () )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2917:1: ( () )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2918:1: ()
            {
             before(grammarAccess.getColumnsAccess().getOrColumnEntriesAction_1_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2919:1: ()
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2921:1: 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2931:1: rule__Columns__Group_1__1 : rule__Columns__Group_1__1__Impl ;
    public final void rule__Columns__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2935:1: ( rule__Columns__Group_1__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2936:2: rule__Columns__Group_1__1__Impl
            {
            pushFollow(FOLLOW_rule__Columns__Group_1__1__Impl_in_rule__Columns__Group_1__16152);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2942:1: rule__Columns__Group_1__1__Impl : ( ( ( rule__Columns__Group_1_1__0 ) ) ( ( rule__Columns__Group_1_1__0 )* ) ) ;
    public final void rule__Columns__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2946:1: ( ( ( ( rule__Columns__Group_1_1__0 ) ) ( ( rule__Columns__Group_1_1__0 )* ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2947:1: ( ( ( rule__Columns__Group_1_1__0 ) ) ( ( rule__Columns__Group_1_1__0 )* ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2947:1: ( ( ( rule__Columns__Group_1_1__0 ) ) ( ( rule__Columns__Group_1_1__0 )* ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2948:1: ( ( rule__Columns__Group_1_1__0 ) ) ( ( rule__Columns__Group_1_1__0 )* )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2948:1: ( ( rule__Columns__Group_1_1__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2949:1: ( rule__Columns__Group_1_1__0 )
            {
             before(grammarAccess.getColumnsAccess().getGroup_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2950:1: ( rule__Columns__Group_1_1__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2950:2: rule__Columns__Group_1_1__0
            {
            pushFollow(FOLLOW_rule__Columns__Group_1_1__0_in_rule__Columns__Group_1__1__Impl6181);
            rule__Columns__Group_1_1__0();

            state._fsp--;


            }

             after(grammarAccess.getColumnsAccess().getGroup_1_1()); 

            }

            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2953:1: ( ( rule__Columns__Group_1_1__0 )* )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2954:1: ( rule__Columns__Group_1_1__0 )*
            {
             before(grammarAccess.getColumnsAccess().getGroup_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2955:1: ( rule__Columns__Group_1_1__0 )*
            loop26:
            do {
                int alt26=2;
                int LA26_0 = input.LA(1);

                if ( (LA26_0==31) ) {
                    alt26=1;
                }


                switch (alt26) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2955:2: rule__Columns__Group_1_1__0
            	    {
            	    pushFollow(FOLLOW_rule__Columns__Group_1_1__0_in_rule__Columns__Group_1__1__Impl6193);
            	    rule__Columns__Group_1_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop26;
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2970:1: rule__Columns__Group_1_1__0 : rule__Columns__Group_1_1__0__Impl rule__Columns__Group_1_1__1 ;
    public final void rule__Columns__Group_1_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2974:1: ( rule__Columns__Group_1_1__0__Impl rule__Columns__Group_1_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2975:2: rule__Columns__Group_1_1__0__Impl rule__Columns__Group_1_1__1
            {
            pushFollow(FOLLOW_rule__Columns__Group_1_1__0__Impl_in_rule__Columns__Group_1_1__06230);
            rule__Columns__Group_1_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Columns__Group_1_1__1_in_rule__Columns__Group_1_1__06233);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2982:1: rule__Columns__Group_1_1__0__Impl : ( ',' ) ;
    public final void rule__Columns__Group_1_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2986:1: ( ( ',' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2987:1: ( ',' )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2987:1: ( ',' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:2988:1: ','
            {
             before(grammarAccess.getColumnsAccess().getCommaKeyword_1_1_0()); 
            match(input,31,FOLLOW_31_in_rule__Columns__Group_1_1__0__Impl6261); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3001:1: rule__Columns__Group_1_1__1 : rule__Columns__Group_1_1__1__Impl ;
    public final void rule__Columns__Group_1_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3005:1: ( rule__Columns__Group_1_1__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3006:2: rule__Columns__Group_1_1__1__Impl
            {
            pushFollow(FOLLOW_rule__Columns__Group_1_1__1__Impl_in_rule__Columns__Group_1_1__16292);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3012:1: rule__Columns__Group_1_1__1__Impl : ( ( rule__Columns__EntriesAssignment_1_1_1 ) ) ;
    public final void rule__Columns__Group_1_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3016:1: ( ( ( rule__Columns__EntriesAssignment_1_1_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3017:1: ( ( rule__Columns__EntriesAssignment_1_1_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3017:1: ( ( rule__Columns__EntriesAssignment_1_1_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3018:1: ( rule__Columns__EntriesAssignment_1_1_1 )
            {
             before(grammarAccess.getColumnsAccess().getEntriesAssignment_1_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3019:1: ( rule__Columns__EntriesAssignment_1_1_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3019:2: rule__Columns__EntriesAssignment_1_1_1
            {
            pushFollow(FOLLOW_rule__Columns__EntriesAssignment_1_1_1_in_rule__Columns__Group_1_1__1__Impl6319);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3033:1: rule__ColumnOrAlias__Group_1__0 : rule__ColumnOrAlias__Group_1__0__Impl rule__ColumnOrAlias__Group_1__1 ;
    public final void rule__ColumnOrAlias__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3037:1: ( rule__ColumnOrAlias__Group_1__0__Impl rule__ColumnOrAlias__Group_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3038:2: rule__ColumnOrAlias__Group_1__0__Impl rule__ColumnOrAlias__Group_1__1
            {
            pushFollow(FOLLOW_rule__ColumnOrAlias__Group_1__0__Impl_in_rule__ColumnOrAlias__Group_1__06353);
            rule__ColumnOrAlias__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ColumnOrAlias__Group_1__1_in_rule__ColumnOrAlias__Group_1__06356);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3045:1: rule__ColumnOrAlias__Group_1__0__Impl : ( ruleColumnFull ) ;
    public final void rule__ColumnOrAlias__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3049:1: ( ( ruleColumnFull ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3050:1: ( ruleColumnFull )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3050:1: ( ruleColumnFull )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3051:1: ruleColumnFull
            {
             before(grammarAccess.getColumnOrAliasAccess().getColumnFullParserRuleCall_1_0()); 
            pushFollow(FOLLOW_ruleColumnFull_in_rule__ColumnOrAlias__Group_1__0__Impl6383);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3062:1: rule__ColumnOrAlias__Group_1__1 : rule__ColumnOrAlias__Group_1__1__Impl rule__ColumnOrAlias__Group_1__2 ;
    public final void rule__ColumnOrAlias__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3066:1: ( rule__ColumnOrAlias__Group_1__1__Impl rule__ColumnOrAlias__Group_1__2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3067:2: rule__ColumnOrAlias__Group_1__1__Impl rule__ColumnOrAlias__Group_1__2
            {
            pushFollow(FOLLOW_rule__ColumnOrAlias__Group_1__1__Impl_in_rule__ColumnOrAlias__Group_1__16412);
            rule__ColumnOrAlias__Group_1__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ColumnOrAlias__Group_1__2_in_rule__ColumnOrAlias__Group_1__16415);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3074:1: rule__ColumnOrAlias__Group_1__1__Impl : ( 'AS' ) ;
    public final void rule__ColumnOrAlias__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3078:1: ( ( 'AS' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3079:1: ( 'AS' )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3079:1: ( 'AS' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3080:1: 'AS'
            {
             before(grammarAccess.getColumnOrAliasAccess().getASKeyword_1_1()); 
            match(input,33,FOLLOW_33_in_rule__ColumnOrAlias__Group_1__1__Impl6443); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3093:1: rule__ColumnOrAlias__Group_1__2 : rule__ColumnOrAlias__Group_1__2__Impl ;
    public final void rule__ColumnOrAlias__Group_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3097:1: ( rule__ColumnOrAlias__Group_1__2__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3098:2: rule__ColumnOrAlias__Group_1__2__Impl
            {
            pushFollow(FOLLOW_rule__ColumnOrAlias__Group_1__2__Impl_in_rule__ColumnOrAlias__Group_1__26474);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3104:1: rule__ColumnOrAlias__Group_1__2__Impl : ( ( rule__ColumnOrAlias__ColAliasAssignment_1_2 ) ) ;
    public final void rule__ColumnOrAlias__Group_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3108:1: ( ( ( rule__ColumnOrAlias__ColAliasAssignment_1_2 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3109:1: ( ( rule__ColumnOrAlias__ColAliasAssignment_1_2 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3109:1: ( ( rule__ColumnOrAlias__ColAliasAssignment_1_2 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3110:1: ( rule__ColumnOrAlias__ColAliasAssignment_1_2 )
            {
             before(grammarAccess.getColumnOrAliasAccess().getColAliasAssignment_1_2()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3111:1: ( rule__ColumnOrAlias__ColAliasAssignment_1_2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3111:2: rule__ColumnOrAlias__ColAliasAssignment_1_2
            {
            pushFollow(FOLLOW_rule__ColumnOrAlias__ColAliasAssignment_1_2_in_rule__ColumnOrAlias__Group_1__2__Impl6501);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3127:1: rule__ColumnOrAlias__Group_2__0 : rule__ColumnOrAlias__Group_2__0__Impl rule__ColumnOrAlias__Group_2__1 ;
    public final void rule__ColumnOrAlias__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3131:1: ( rule__ColumnOrAlias__Group_2__0__Impl rule__ColumnOrAlias__Group_2__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3132:2: rule__ColumnOrAlias__Group_2__0__Impl rule__ColumnOrAlias__Group_2__1
            {
            pushFollow(FOLLOW_rule__ColumnOrAlias__Group_2__0__Impl_in_rule__ColumnOrAlias__Group_2__06537);
            rule__ColumnOrAlias__Group_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ColumnOrAlias__Group_2__1_in_rule__ColumnOrAlias__Group_2__06540);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3139:1: rule__ColumnOrAlias__Group_2__0__Impl : ( ruleColumnFull ) ;
    public final void rule__ColumnOrAlias__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3143:1: ( ( ruleColumnFull ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3144:1: ( ruleColumnFull )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3144:1: ( ruleColumnFull )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3145:1: ruleColumnFull
            {
             before(grammarAccess.getColumnOrAliasAccess().getColumnFullParserRuleCall_2_0()); 
            pushFollow(FOLLOW_ruleColumnFull_in_rule__ColumnOrAlias__Group_2__0__Impl6567);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3156:1: rule__ColumnOrAlias__Group_2__1 : rule__ColumnOrAlias__Group_2__1__Impl ;
    public final void rule__ColumnOrAlias__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3160:1: ( rule__ColumnOrAlias__Group_2__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3161:2: rule__ColumnOrAlias__Group_2__1__Impl
            {
            pushFollow(FOLLOW_rule__ColumnOrAlias__Group_2__1__Impl_in_rule__ColumnOrAlias__Group_2__16596);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3167:1: rule__ColumnOrAlias__Group_2__1__Impl : ( ( rule__ColumnOrAlias__ColAliasAssignment_2_1 ) ) ;
    public final void rule__ColumnOrAlias__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3171:1: ( ( ( rule__ColumnOrAlias__ColAliasAssignment_2_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3172:1: ( ( rule__ColumnOrAlias__ColAliasAssignment_2_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3172:1: ( ( rule__ColumnOrAlias__ColAliasAssignment_2_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3173:1: ( rule__ColumnOrAlias__ColAliasAssignment_2_1 )
            {
             before(grammarAccess.getColumnOrAliasAccess().getColAliasAssignment_2_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3174:1: ( rule__ColumnOrAlias__ColAliasAssignment_2_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3174:2: rule__ColumnOrAlias__ColAliasAssignment_2_1
            {
            pushFollow(FOLLOW_rule__ColumnOrAlias__ColAliasAssignment_2_1_in_rule__ColumnOrAlias__Group_2__1__Impl6623);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3188:1: rule__ColumnFull__Group_1__0 : rule__ColumnFull__Group_1__0__Impl rule__ColumnFull__Group_1__1 ;
    public final void rule__ColumnFull__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3192:1: ( rule__ColumnFull__Group_1__0__Impl rule__ColumnFull__Group_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3193:2: rule__ColumnFull__Group_1__0__Impl rule__ColumnFull__Group_1__1
            {
            pushFollow(FOLLOW_rule__ColumnFull__Group_1__0__Impl_in_rule__ColumnFull__Group_1__06657);
            rule__ColumnFull__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ColumnFull__Group_1__1_in_rule__ColumnFull__Group_1__06660);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3200:1: rule__ColumnFull__Group_1__0__Impl : ( ruleTableFull ) ;
    public final void rule__ColumnFull__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3204:1: ( ( ruleTableFull ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3205:1: ( ruleTableFull )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3205:1: ( ruleTableFull )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3206:1: ruleTableFull
            {
             before(grammarAccess.getColumnFullAccess().getTableFullParserRuleCall_1_0()); 
            pushFollow(FOLLOW_ruleTableFull_in_rule__ColumnFull__Group_1__0__Impl6687);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3217:1: rule__ColumnFull__Group_1__1 : rule__ColumnFull__Group_1__1__Impl rule__ColumnFull__Group_1__2 ;
    public final void rule__ColumnFull__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3221:1: ( rule__ColumnFull__Group_1__1__Impl rule__ColumnFull__Group_1__2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3222:2: rule__ColumnFull__Group_1__1__Impl rule__ColumnFull__Group_1__2
            {
            pushFollow(FOLLOW_rule__ColumnFull__Group_1__1__Impl_in_rule__ColumnFull__Group_1__16716);
            rule__ColumnFull__Group_1__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ColumnFull__Group_1__2_in_rule__ColumnFull__Group_1__16719);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3229:1: rule__ColumnFull__Group_1__1__Impl : ( '.' ) ;
    public final void rule__ColumnFull__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3233:1: ( ( '.' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3234:1: ( '.' )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3234:1: ( '.' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3235:1: '.'
            {
             before(grammarAccess.getColumnFullAccess().getFullStopKeyword_1_1()); 
            match(input,32,FOLLOW_32_in_rule__ColumnFull__Group_1__1__Impl6747); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3248:1: rule__ColumnFull__Group_1__2 : rule__ColumnFull__Group_1__2__Impl ;
    public final void rule__ColumnFull__Group_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3252:1: ( rule__ColumnFull__Group_1__2__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3253:2: rule__ColumnFull__Group_1__2__Impl
            {
            pushFollow(FOLLOW_rule__ColumnFull__Group_1__2__Impl_in_rule__ColumnFull__Group_1__26778);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3259:1: rule__ColumnFull__Group_1__2__Impl : ( ( rule__ColumnFull__ColNameAssignment_1_2 ) ) ;
    public final void rule__ColumnFull__Group_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3263:1: ( ( ( rule__ColumnFull__ColNameAssignment_1_2 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3264:1: ( ( rule__ColumnFull__ColNameAssignment_1_2 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3264:1: ( ( rule__ColumnFull__ColNameAssignment_1_2 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3265:1: ( rule__ColumnFull__ColNameAssignment_1_2 )
            {
             before(grammarAccess.getColumnFullAccess().getColNameAssignment_1_2()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3266:1: ( rule__ColumnFull__ColNameAssignment_1_2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3266:2: rule__ColumnFull__ColNameAssignment_1_2
            {
            pushFollow(FOLLOW_rule__ColumnFull__ColNameAssignment_1_2_in_rule__ColumnFull__Group_1__2__Impl6805);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3282:1: rule__Tables__Group__0 : rule__Tables__Group__0__Impl rule__Tables__Group__1 ;
    public final void rule__Tables__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3286:1: ( rule__Tables__Group__0__Impl rule__Tables__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3287:2: rule__Tables__Group__0__Impl rule__Tables__Group__1
            {
            pushFollow(FOLLOW_rule__Tables__Group__0__Impl_in_rule__Tables__Group__06841);
            rule__Tables__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Tables__Group__1_in_rule__Tables__Group__06844);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3294:1: rule__Tables__Group__0__Impl : ( ruleTableOrAlias ) ;
    public final void rule__Tables__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3298:1: ( ( ruleTableOrAlias ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3299:1: ( ruleTableOrAlias )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3299:1: ( ruleTableOrAlias )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3300:1: ruleTableOrAlias
            {
             before(grammarAccess.getTablesAccess().getTableOrAliasParserRuleCall_0()); 
            pushFollow(FOLLOW_ruleTableOrAlias_in_rule__Tables__Group__0__Impl6871);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3311:1: rule__Tables__Group__1 : rule__Tables__Group__1__Impl ;
    public final void rule__Tables__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3315:1: ( rule__Tables__Group__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3316:2: rule__Tables__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__Tables__Group__1__Impl_in_rule__Tables__Group__16900);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3322:1: rule__Tables__Group__1__Impl : ( ( rule__Tables__Group_1__0 )? ) ;
    public final void rule__Tables__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3326:1: ( ( ( rule__Tables__Group_1__0 )? ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3327:1: ( ( rule__Tables__Group_1__0 )? )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3327:1: ( ( rule__Tables__Group_1__0 )? )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3328:1: ( rule__Tables__Group_1__0 )?
            {
             before(grammarAccess.getTablesAccess().getGroup_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3329:1: ( rule__Tables__Group_1__0 )?
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==31) ) {
                alt27=1;
            }
            switch (alt27) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3329:2: rule__Tables__Group_1__0
                    {
                    pushFollow(FOLLOW_rule__Tables__Group_1__0_in_rule__Tables__Group__1__Impl6927);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3343:1: rule__Tables__Group_1__0 : rule__Tables__Group_1__0__Impl rule__Tables__Group_1__1 ;
    public final void rule__Tables__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3347:1: ( rule__Tables__Group_1__0__Impl rule__Tables__Group_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3348:2: rule__Tables__Group_1__0__Impl rule__Tables__Group_1__1
            {
            pushFollow(FOLLOW_rule__Tables__Group_1__0__Impl_in_rule__Tables__Group_1__06962);
            rule__Tables__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Tables__Group_1__1_in_rule__Tables__Group_1__06965);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3355:1: rule__Tables__Group_1__0__Impl : ( () ) ;
    public final void rule__Tables__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3359:1: ( ( () ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3360:1: ( () )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3360:1: ( () )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3361:1: ()
            {
             before(grammarAccess.getTablesAccess().getOrTableEntriesAction_1_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3362:1: ()
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3364:1: 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3374:1: rule__Tables__Group_1__1 : rule__Tables__Group_1__1__Impl ;
    public final void rule__Tables__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3378:1: ( rule__Tables__Group_1__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3379:2: rule__Tables__Group_1__1__Impl
            {
            pushFollow(FOLLOW_rule__Tables__Group_1__1__Impl_in_rule__Tables__Group_1__17023);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3385:1: rule__Tables__Group_1__1__Impl : ( ( ( rule__Tables__Group_1_1__0 ) ) ( ( rule__Tables__Group_1_1__0 )* ) ) ;
    public final void rule__Tables__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3389:1: ( ( ( ( rule__Tables__Group_1_1__0 ) ) ( ( rule__Tables__Group_1_1__0 )* ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3390:1: ( ( ( rule__Tables__Group_1_1__0 ) ) ( ( rule__Tables__Group_1_1__0 )* ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3390:1: ( ( ( rule__Tables__Group_1_1__0 ) ) ( ( rule__Tables__Group_1_1__0 )* ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3391:1: ( ( rule__Tables__Group_1_1__0 ) ) ( ( rule__Tables__Group_1_1__0 )* )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3391:1: ( ( rule__Tables__Group_1_1__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3392:1: ( rule__Tables__Group_1_1__0 )
            {
             before(grammarAccess.getTablesAccess().getGroup_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3393:1: ( rule__Tables__Group_1_1__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3393:2: rule__Tables__Group_1_1__0
            {
            pushFollow(FOLLOW_rule__Tables__Group_1_1__0_in_rule__Tables__Group_1__1__Impl7052);
            rule__Tables__Group_1_1__0();

            state._fsp--;


            }

             after(grammarAccess.getTablesAccess().getGroup_1_1()); 

            }

            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3396:1: ( ( rule__Tables__Group_1_1__0 )* )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3397:1: ( rule__Tables__Group_1_1__0 )*
            {
             before(grammarAccess.getTablesAccess().getGroup_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3398:1: ( rule__Tables__Group_1_1__0 )*
            loop28:
            do {
                int alt28=2;
                int LA28_0 = input.LA(1);

                if ( (LA28_0==31) ) {
                    alt28=1;
                }


                switch (alt28) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3398:2: rule__Tables__Group_1_1__0
            	    {
            	    pushFollow(FOLLOW_rule__Tables__Group_1_1__0_in_rule__Tables__Group_1__1__Impl7064);
            	    rule__Tables__Group_1_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop28;
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3413:1: rule__Tables__Group_1_1__0 : rule__Tables__Group_1_1__0__Impl rule__Tables__Group_1_1__1 ;
    public final void rule__Tables__Group_1_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3417:1: ( rule__Tables__Group_1_1__0__Impl rule__Tables__Group_1_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3418:2: rule__Tables__Group_1_1__0__Impl rule__Tables__Group_1_1__1
            {
            pushFollow(FOLLOW_rule__Tables__Group_1_1__0__Impl_in_rule__Tables__Group_1_1__07101);
            rule__Tables__Group_1_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Tables__Group_1_1__1_in_rule__Tables__Group_1_1__07104);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3425:1: rule__Tables__Group_1_1__0__Impl : ( ',' ) ;
    public final void rule__Tables__Group_1_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3429:1: ( ( ',' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3430:1: ( ',' )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3430:1: ( ',' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3431:1: ','
            {
             before(grammarAccess.getTablesAccess().getCommaKeyword_1_1_0()); 
            match(input,31,FOLLOW_31_in_rule__Tables__Group_1_1__0__Impl7132); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3444:1: rule__Tables__Group_1_1__1 : rule__Tables__Group_1_1__1__Impl ;
    public final void rule__Tables__Group_1_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3448:1: ( rule__Tables__Group_1_1__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3449:2: rule__Tables__Group_1_1__1__Impl
            {
            pushFollow(FOLLOW_rule__Tables__Group_1_1__1__Impl_in_rule__Tables__Group_1_1__17163);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3455:1: rule__Tables__Group_1_1__1__Impl : ( ( rule__Tables__EntriesAssignment_1_1_1 ) ) ;
    public final void rule__Tables__Group_1_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3459:1: ( ( ( rule__Tables__EntriesAssignment_1_1_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3460:1: ( ( rule__Tables__EntriesAssignment_1_1_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3460:1: ( ( rule__Tables__EntriesAssignment_1_1_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3461:1: ( rule__Tables__EntriesAssignment_1_1_1 )
            {
             before(grammarAccess.getTablesAccess().getEntriesAssignment_1_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3462:1: ( rule__Tables__EntriesAssignment_1_1_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3462:2: rule__Tables__EntriesAssignment_1_1_1
            {
            pushFollow(FOLLOW_rule__Tables__EntriesAssignment_1_1_1_in_rule__Tables__Group_1_1__1__Impl7190);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3476:1: rule__TableOrAlias__Group_1__0 : rule__TableOrAlias__Group_1__0__Impl rule__TableOrAlias__Group_1__1 ;
    public final void rule__TableOrAlias__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3480:1: ( rule__TableOrAlias__Group_1__0__Impl rule__TableOrAlias__Group_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3481:2: rule__TableOrAlias__Group_1__0__Impl rule__TableOrAlias__Group_1__1
            {
            pushFollow(FOLLOW_rule__TableOrAlias__Group_1__0__Impl_in_rule__TableOrAlias__Group_1__07224);
            rule__TableOrAlias__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__TableOrAlias__Group_1__1_in_rule__TableOrAlias__Group_1__07227);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3488:1: rule__TableOrAlias__Group_1__0__Impl : ( ruleTableFull ) ;
    public final void rule__TableOrAlias__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3492:1: ( ( ruleTableFull ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3493:1: ( ruleTableFull )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3493:1: ( ruleTableFull )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3494:1: ruleTableFull
            {
             before(grammarAccess.getTableOrAliasAccess().getTableFullParserRuleCall_1_0()); 
            pushFollow(FOLLOW_ruleTableFull_in_rule__TableOrAlias__Group_1__0__Impl7254);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3505:1: rule__TableOrAlias__Group_1__1 : rule__TableOrAlias__Group_1__1__Impl rule__TableOrAlias__Group_1__2 ;
    public final void rule__TableOrAlias__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3509:1: ( rule__TableOrAlias__Group_1__1__Impl rule__TableOrAlias__Group_1__2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3510:2: rule__TableOrAlias__Group_1__1__Impl rule__TableOrAlias__Group_1__2
            {
            pushFollow(FOLLOW_rule__TableOrAlias__Group_1__1__Impl_in_rule__TableOrAlias__Group_1__17283);
            rule__TableOrAlias__Group_1__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__TableOrAlias__Group_1__2_in_rule__TableOrAlias__Group_1__17286);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3517:1: rule__TableOrAlias__Group_1__1__Impl : ( 'AS' ) ;
    public final void rule__TableOrAlias__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3521:1: ( ( 'AS' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3522:1: ( 'AS' )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3522:1: ( 'AS' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3523:1: 'AS'
            {
             before(grammarAccess.getTableOrAliasAccess().getASKeyword_1_1()); 
            match(input,33,FOLLOW_33_in_rule__TableOrAlias__Group_1__1__Impl7314); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3536:1: rule__TableOrAlias__Group_1__2 : rule__TableOrAlias__Group_1__2__Impl ;
    public final void rule__TableOrAlias__Group_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3540:1: ( rule__TableOrAlias__Group_1__2__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3541:2: rule__TableOrAlias__Group_1__2__Impl
            {
            pushFollow(FOLLOW_rule__TableOrAlias__Group_1__2__Impl_in_rule__TableOrAlias__Group_1__27345);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3547:1: rule__TableOrAlias__Group_1__2__Impl : ( ( rule__TableOrAlias__TblAliasAssignment_1_2 ) ) ;
    public final void rule__TableOrAlias__Group_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3551:1: ( ( ( rule__TableOrAlias__TblAliasAssignment_1_2 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3552:1: ( ( rule__TableOrAlias__TblAliasAssignment_1_2 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3552:1: ( ( rule__TableOrAlias__TblAliasAssignment_1_2 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3553:1: ( rule__TableOrAlias__TblAliasAssignment_1_2 )
            {
             before(grammarAccess.getTableOrAliasAccess().getTblAliasAssignment_1_2()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3554:1: ( rule__TableOrAlias__TblAliasAssignment_1_2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3554:2: rule__TableOrAlias__TblAliasAssignment_1_2
            {
            pushFollow(FOLLOW_rule__TableOrAlias__TblAliasAssignment_1_2_in_rule__TableOrAlias__Group_1__2__Impl7372);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3570:1: rule__TableOrAlias__Group_2__0 : rule__TableOrAlias__Group_2__0__Impl rule__TableOrAlias__Group_2__1 ;
    public final void rule__TableOrAlias__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3574:1: ( rule__TableOrAlias__Group_2__0__Impl rule__TableOrAlias__Group_2__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3575:2: rule__TableOrAlias__Group_2__0__Impl rule__TableOrAlias__Group_2__1
            {
            pushFollow(FOLLOW_rule__TableOrAlias__Group_2__0__Impl_in_rule__TableOrAlias__Group_2__07408);
            rule__TableOrAlias__Group_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__TableOrAlias__Group_2__1_in_rule__TableOrAlias__Group_2__07411);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3582:1: rule__TableOrAlias__Group_2__0__Impl : ( ruleTableFull ) ;
    public final void rule__TableOrAlias__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3586:1: ( ( ruleTableFull ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3587:1: ( ruleTableFull )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3587:1: ( ruleTableFull )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3588:1: ruleTableFull
            {
             before(grammarAccess.getTableOrAliasAccess().getTableFullParserRuleCall_2_0()); 
            pushFollow(FOLLOW_ruleTableFull_in_rule__TableOrAlias__Group_2__0__Impl7438);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3599:1: rule__TableOrAlias__Group_2__1 : rule__TableOrAlias__Group_2__1__Impl ;
    public final void rule__TableOrAlias__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3603:1: ( rule__TableOrAlias__Group_2__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3604:2: rule__TableOrAlias__Group_2__1__Impl
            {
            pushFollow(FOLLOW_rule__TableOrAlias__Group_2__1__Impl_in_rule__TableOrAlias__Group_2__17467);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3610:1: rule__TableOrAlias__Group_2__1__Impl : ( ( rule__TableOrAlias__TblAliasAssignment_2_1 ) ) ;
    public final void rule__TableOrAlias__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3614:1: ( ( ( rule__TableOrAlias__TblAliasAssignment_2_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3615:1: ( ( rule__TableOrAlias__TblAliasAssignment_2_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3615:1: ( ( rule__TableOrAlias__TblAliasAssignment_2_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3616:1: ( rule__TableOrAlias__TblAliasAssignment_2_1 )
            {
             before(grammarAccess.getTableOrAliasAccess().getTblAliasAssignment_2_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3617:1: ( rule__TableOrAlias__TblAliasAssignment_2_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3617:2: rule__TableOrAlias__TblAliasAssignment_2_1
            {
            pushFollow(FOLLOW_rule__TableOrAlias__TblAliasAssignment_2_1_in_rule__TableOrAlias__Group_2__1__Impl7494);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3631:1: rule__TableFull__Group_0__0 : rule__TableFull__Group_0__0__Impl rule__TableFull__Group_0__1 ;
    public final void rule__TableFull__Group_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3635:1: ( rule__TableFull__Group_0__0__Impl rule__TableFull__Group_0__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3636:2: rule__TableFull__Group_0__0__Impl rule__TableFull__Group_0__1
            {
            pushFollow(FOLLOW_rule__TableFull__Group_0__0__Impl_in_rule__TableFull__Group_0__07528);
            rule__TableFull__Group_0__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__TableFull__Group_0__1_in_rule__TableFull__Group_0__07531);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3643:1: rule__TableFull__Group_0__0__Impl : ( ruleSchema ) ;
    public final void rule__TableFull__Group_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3647:1: ( ( ruleSchema ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3648:1: ( ruleSchema )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3648:1: ( ruleSchema )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3649:1: ruleSchema
            {
             before(grammarAccess.getTableFullAccess().getSchemaParserRuleCall_0_0()); 
            pushFollow(FOLLOW_ruleSchema_in_rule__TableFull__Group_0__0__Impl7558);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3660:1: rule__TableFull__Group_0__1 : rule__TableFull__Group_0__1__Impl rule__TableFull__Group_0__2 ;
    public final void rule__TableFull__Group_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3664:1: ( rule__TableFull__Group_0__1__Impl rule__TableFull__Group_0__2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3665:2: rule__TableFull__Group_0__1__Impl rule__TableFull__Group_0__2
            {
            pushFollow(FOLLOW_rule__TableFull__Group_0__1__Impl_in_rule__TableFull__Group_0__17587);
            rule__TableFull__Group_0__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__TableFull__Group_0__2_in_rule__TableFull__Group_0__17590);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3672:1: rule__TableFull__Group_0__1__Impl : ( '.' ) ;
    public final void rule__TableFull__Group_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3676:1: ( ( '.' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3677:1: ( '.' )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3677:1: ( '.' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3678:1: '.'
            {
             before(grammarAccess.getTableFullAccess().getFullStopKeyword_0_1()); 
            match(input,32,FOLLOW_32_in_rule__TableFull__Group_0__1__Impl7618); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3691:1: rule__TableFull__Group_0__2 : rule__TableFull__Group_0__2__Impl ;
    public final void rule__TableFull__Group_0__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3695:1: ( rule__TableFull__Group_0__2__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3696:2: rule__TableFull__Group_0__2__Impl
            {
            pushFollow(FOLLOW_rule__TableFull__Group_0__2__Impl_in_rule__TableFull__Group_0__27649);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3702:1: rule__TableFull__Group_0__2__Impl : ( ( rule__TableFull__TblAssignment_0_2 ) ) ;
    public final void rule__TableFull__Group_0__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3706:1: ( ( ( rule__TableFull__TblAssignment_0_2 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3707:1: ( ( rule__TableFull__TblAssignment_0_2 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3707:1: ( ( rule__TableFull__TblAssignment_0_2 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3708:1: ( rule__TableFull__TblAssignment_0_2 )
            {
             before(grammarAccess.getTableFullAccess().getTblAssignment_0_2()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3709:1: ( rule__TableFull__TblAssignment_0_2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3709:2: rule__TableFull__TblAssignment_0_2
            {
            pushFollow(FOLLOW_rule__TableFull__TblAssignment_0_2_in_rule__TableFull__Group_0__2__Impl7676);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3725:1: rule__Schema__Group_0__0 : rule__Schema__Group_0__0__Impl rule__Schema__Group_0__1 ;
    public final void rule__Schema__Group_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3729:1: ( rule__Schema__Group_0__0__Impl rule__Schema__Group_0__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3730:2: rule__Schema__Group_0__0__Impl rule__Schema__Group_0__1
            {
            pushFollow(FOLLOW_rule__Schema__Group_0__0__Impl_in_rule__Schema__Group_0__07712);
            rule__Schema__Group_0__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Schema__Group_0__1_in_rule__Schema__Group_0__07715);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3737:1: rule__Schema__Group_0__0__Impl : ( ruleDatabase ) ;
    public final void rule__Schema__Group_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3741:1: ( ( ruleDatabase ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3742:1: ( ruleDatabase )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3742:1: ( ruleDatabase )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3743:1: ruleDatabase
            {
             before(grammarAccess.getSchemaAccess().getDatabaseParserRuleCall_0_0()); 
            pushFollow(FOLLOW_ruleDatabase_in_rule__Schema__Group_0__0__Impl7742);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3754:1: rule__Schema__Group_0__1 : rule__Schema__Group_0__1__Impl rule__Schema__Group_0__2 ;
    public final void rule__Schema__Group_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3758:1: ( rule__Schema__Group_0__1__Impl rule__Schema__Group_0__2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3759:2: rule__Schema__Group_0__1__Impl rule__Schema__Group_0__2
            {
            pushFollow(FOLLOW_rule__Schema__Group_0__1__Impl_in_rule__Schema__Group_0__17771);
            rule__Schema__Group_0__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Schema__Group_0__2_in_rule__Schema__Group_0__17774);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3766:1: rule__Schema__Group_0__1__Impl : ( '.' ) ;
    public final void rule__Schema__Group_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3770:1: ( ( '.' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3771:1: ( '.' )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3771:1: ( '.' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3772:1: '.'
            {
             before(grammarAccess.getSchemaAccess().getFullStopKeyword_0_1()); 
            match(input,32,FOLLOW_32_in_rule__Schema__Group_0__1__Impl7802); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3785:1: rule__Schema__Group_0__2 : rule__Schema__Group_0__2__Impl ;
    public final void rule__Schema__Group_0__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3789:1: ( rule__Schema__Group_0__2__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3790:2: rule__Schema__Group_0__2__Impl
            {
            pushFollow(FOLLOW_rule__Schema__Group_0__2__Impl_in_rule__Schema__Group_0__27833);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3796:1: rule__Schema__Group_0__2__Impl : ( ( rule__Schema__SchemAssignment_0_2 ) ) ;
    public final void rule__Schema__Group_0__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3800:1: ( ( ( rule__Schema__SchemAssignment_0_2 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3801:1: ( ( rule__Schema__SchemAssignment_0_2 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3801:1: ( ( rule__Schema__SchemAssignment_0_2 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3802:1: ( rule__Schema__SchemAssignment_0_2 )
            {
             before(grammarAccess.getSchemaAccess().getSchemAssignment_0_2()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3803:1: ( rule__Schema__SchemAssignment_0_2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3803:2: rule__Schema__SchemAssignment_0_2
            {
            pushFollow(FOLLOW_rule__Schema__SchemAssignment_0_2_in_rule__Schema__Group_0__2__Impl7860);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3819:1: rule__WhereEntry__Group__0 : rule__WhereEntry__Group__0__Impl rule__WhereEntry__Group__1 ;
    public final void rule__WhereEntry__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3823:1: ( rule__WhereEntry__Group__0__Impl rule__WhereEntry__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3824:2: rule__WhereEntry__Group__0__Impl rule__WhereEntry__Group__1
            {
            pushFollow(FOLLOW_rule__WhereEntry__Group__0__Impl_in_rule__WhereEntry__Group__07896);
            rule__WhereEntry__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__WhereEntry__Group__1_in_rule__WhereEntry__Group__07899);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3831:1: rule__WhereEntry__Group__0__Impl : ( ruleAndWhereEntry ) ;
    public final void rule__WhereEntry__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3835:1: ( ( ruleAndWhereEntry ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3836:1: ( ruleAndWhereEntry )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3836:1: ( ruleAndWhereEntry )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3837:1: ruleAndWhereEntry
            {
             before(grammarAccess.getWhereEntryAccess().getAndWhereEntryParserRuleCall_0()); 
            pushFollow(FOLLOW_ruleAndWhereEntry_in_rule__WhereEntry__Group__0__Impl7926);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3848:1: rule__WhereEntry__Group__1 : rule__WhereEntry__Group__1__Impl ;
    public final void rule__WhereEntry__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3852:1: ( rule__WhereEntry__Group__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3853:2: rule__WhereEntry__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__WhereEntry__Group__1__Impl_in_rule__WhereEntry__Group__17955);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3859:1: rule__WhereEntry__Group__1__Impl : ( ( rule__WhereEntry__Group_1__0 )? ) ;
    public final void rule__WhereEntry__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3863:1: ( ( ( rule__WhereEntry__Group_1__0 )? ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3864:1: ( ( rule__WhereEntry__Group_1__0 )? )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3864:1: ( ( rule__WhereEntry__Group_1__0 )? )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3865:1: ( rule__WhereEntry__Group_1__0 )?
            {
             before(grammarAccess.getWhereEntryAccess().getGroup_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3866:1: ( rule__WhereEntry__Group_1__0 )?
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==34) ) {
                alt29=1;
            }
            switch (alt29) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3866:2: rule__WhereEntry__Group_1__0
                    {
                    pushFollow(FOLLOW_rule__WhereEntry__Group_1__0_in_rule__WhereEntry__Group__1__Impl7982);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3880:1: rule__WhereEntry__Group_1__0 : rule__WhereEntry__Group_1__0__Impl rule__WhereEntry__Group_1__1 ;
    public final void rule__WhereEntry__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3884:1: ( rule__WhereEntry__Group_1__0__Impl rule__WhereEntry__Group_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3885:2: rule__WhereEntry__Group_1__0__Impl rule__WhereEntry__Group_1__1
            {
            pushFollow(FOLLOW_rule__WhereEntry__Group_1__0__Impl_in_rule__WhereEntry__Group_1__08017);
            rule__WhereEntry__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__WhereEntry__Group_1__1_in_rule__WhereEntry__Group_1__08020);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3892:1: rule__WhereEntry__Group_1__0__Impl : ( () ) ;
    public final void rule__WhereEntry__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3896:1: ( ( () ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3897:1: ( () )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3897:1: ( () )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3898:1: ()
            {
             before(grammarAccess.getWhereEntryAccess().getOrWhereEntryEntriesAction_1_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3899:1: ()
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3901:1: 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3911:1: rule__WhereEntry__Group_1__1 : rule__WhereEntry__Group_1__1__Impl ;
    public final void rule__WhereEntry__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3915:1: ( rule__WhereEntry__Group_1__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3916:2: rule__WhereEntry__Group_1__1__Impl
            {
            pushFollow(FOLLOW_rule__WhereEntry__Group_1__1__Impl_in_rule__WhereEntry__Group_1__18078);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3922:1: rule__WhereEntry__Group_1__1__Impl : ( ( ( rule__WhereEntry__Group_1_1__0 ) ) ( ( rule__WhereEntry__Group_1_1__0 )* ) ) ;
    public final void rule__WhereEntry__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3926:1: ( ( ( ( rule__WhereEntry__Group_1_1__0 ) ) ( ( rule__WhereEntry__Group_1_1__0 )* ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3927:1: ( ( ( rule__WhereEntry__Group_1_1__0 ) ) ( ( rule__WhereEntry__Group_1_1__0 )* ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3927:1: ( ( ( rule__WhereEntry__Group_1_1__0 ) ) ( ( rule__WhereEntry__Group_1_1__0 )* ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3928:1: ( ( rule__WhereEntry__Group_1_1__0 ) ) ( ( rule__WhereEntry__Group_1_1__0 )* )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3928:1: ( ( rule__WhereEntry__Group_1_1__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3929:1: ( rule__WhereEntry__Group_1_1__0 )
            {
             before(grammarAccess.getWhereEntryAccess().getGroup_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3930:1: ( rule__WhereEntry__Group_1_1__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3930:2: rule__WhereEntry__Group_1_1__0
            {
            pushFollow(FOLLOW_rule__WhereEntry__Group_1_1__0_in_rule__WhereEntry__Group_1__1__Impl8107);
            rule__WhereEntry__Group_1_1__0();

            state._fsp--;


            }

             after(grammarAccess.getWhereEntryAccess().getGroup_1_1()); 

            }

            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3933:1: ( ( rule__WhereEntry__Group_1_1__0 )* )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3934:1: ( rule__WhereEntry__Group_1_1__0 )*
            {
             before(grammarAccess.getWhereEntryAccess().getGroup_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3935:1: ( rule__WhereEntry__Group_1_1__0 )*
            loop30:
            do {
                int alt30=2;
                int LA30_0 = input.LA(1);

                if ( (LA30_0==34) ) {
                    alt30=1;
                }


                switch (alt30) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3935:2: rule__WhereEntry__Group_1_1__0
            	    {
            	    pushFollow(FOLLOW_rule__WhereEntry__Group_1_1__0_in_rule__WhereEntry__Group_1__1__Impl8119);
            	    rule__WhereEntry__Group_1_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop30;
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3950:1: rule__WhereEntry__Group_1_1__0 : rule__WhereEntry__Group_1_1__0__Impl rule__WhereEntry__Group_1_1__1 ;
    public final void rule__WhereEntry__Group_1_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3954:1: ( rule__WhereEntry__Group_1_1__0__Impl rule__WhereEntry__Group_1_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3955:2: rule__WhereEntry__Group_1_1__0__Impl rule__WhereEntry__Group_1_1__1
            {
            pushFollow(FOLLOW_rule__WhereEntry__Group_1_1__0__Impl_in_rule__WhereEntry__Group_1_1__08156);
            rule__WhereEntry__Group_1_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__WhereEntry__Group_1_1__1_in_rule__WhereEntry__Group_1_1__08159);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3962:1: rule__WhereEntry__Group_1_1__0__Impl : ( 'OR' ) ;
    public final void rule__WhereEntry__Group_1_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3966:1: ( ( 'OR' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3967:1: ( 'OR' )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3967:1: ( 'OR' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3968:1: 'OR'
            {
             before(grammarAccess.getWhereEntryAccess().getORKeyword_1_1_0()); 
            match(input,34,FOLLOW_34_in_rule__WhereEntry__Group_1_1__0__Impl8187); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3981:1: rule__WhereEntry__Group_1_1__1 : rule__WhereEntry__Group_1_1__1__Impl ;
    public final void rule__WhereEntry__Group_1_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3985:1: ( rule__WhereEntry__Group_1_1__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3986:2: rule__WhereEntry__Group_1_1__1__Impl
            {
            pushFollow(FOLLOW_rule__WhereEntry__Group_1_1__1__Impl_in_rule__WhereEntry__Group_1_1__18218);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3992:1: rule__WhereEntry__Group_1_1__1__Impl : ( ( rule__WhereEntry__EntriesAssignment_1_1_1 ) ) ;
    public final void rule__WhereEntry__Group_1_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3996:1: ( ( ( rule__WhereEntry__EntriesAssignment_1_1_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3997:1: ( ( rule__WhereEntry__EntriesAssignment_1_1_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3997:1: ( ( rule__WhereEntry__EntriesAssignment_1_1_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3998:1: ( rule__WhereEntry__EntriesAssignment_1_1_1 )
            {
             before(grammarAccess.getWhereEntryAccess().getEntriesAssignment_1_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3999:1: ( rule__WhereEntry__EntriesAssignment_1_1_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:3999:2: rule__WhereEntry__EntriesAssignment_1_1_1
            {
            pushFollow(FOLLOW_rule__WhereEntry__EntriesAssignment_1_1_1_in_rule__WhereEntry__Group_1_1__1__Impl8245);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4013:1: rule__AndWhereEntry__Group__0 : rule__AndWhereEntry__Group__0__Impl rule__AndWhereEntry__Group__1 ;
    public final void rule__AndWhereEntry__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4017:1: ( rule__AndWhereEntry__Group__0__Impl rule__AndWhereEntry__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4018:2: rule__AndWhereEntry__Group__0__Impl rule__AndWhereEntry__Group__1
            {
            pushFollow(FOLLOW_rule__AndWhereEntry__Group__0__Impl_in_rule__AndWhereEntry__Group__08279);
            rule__AndWhereEntry__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__AndWhereEntry__Group__1_in_rule__AndWhereEntry__Group__08282);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4025:1: rule__AndWhereEntry__Group__0__Impl : ( ruleConcreteWhereEntry ) ;
    public final void rule__AndWhereEntry__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4029:1: ( ( ruleConcreteWhereEntry ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4030:1: ( ruleConcreteWhereEntry )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4030:1: ( ruleConcreteWhereEntry )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4031:1: ruleConcreteWhereEntry
            {
             before(grammarAccess.getAndWhereEntryAccess().getConcreteWhereEntryParserRuleCall_0()); 
            pushFollow(FOLLOW_ruleConcreteWhereEntry_in_rule__AndWhereEntry__Group__0__Impl8309);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4042:1: rule__AndWhereEntry__Group__1 : rule__AndWhereEntry__Group__1__Impl ;
    public final void rule__AndWhereEntry__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4046:1: ( rule__AndWhereEntry__Group__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4047:2: rule__AndWhereEntry__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__AndWhereEntry__Group__1__Impl_in_rule__AndWhereEntry__Group__18338);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4053:1: rule__AndWhereEntry__Group__1__Impl : ( ( rule__AndWhereEntry__Group_1__0 )? ) ;
    public final void rule__AndWhereEntry__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4057:1: ( ( ( rule__AndWhereEntry__Group_1__0 )? ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4058:1: ( ( rule__AndWhereEntry__Group_1__0 )? )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4058:1: ( ( rule__AndWhereEntry__Group_1__0 )? )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4059:1: ( rule__AndWhereEntry__Group_1__0 )?
            {
             before(grammarAccess.getAndWhereEntryAccess().getGroup_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4060:1: ( rule__AndWhereEntry__Group_1__0 )?
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( (LA31_0==35) ) {
                alt31=1;
            }
            switch (alt31) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4060:2: rule__AndWhereEntry__Group_1__0
                    {
                    pushFollow(FOLLOW_rule__AndWhereEntry__Group_1__0_in_rule__AndWhereEntry__Group__1__Impl8365);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4074:1: rule__AndWhereEntry__Group_1__0 : rule__AndWhereEntry__Group_1__0__Impl rule__AndWhereEntry__Group_1__1 ;
    public final void rule__AndWhereEntry__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4078:1: ( rule__AndWhereEntry__Group_1__0__Impl rule__AndWhereEntry__Group_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4079:2: rule__AndWhereEntry__Group_1__0__Impl rule__AndWhereEntry__Group_1__1
            {
            pushFollow(FOLLOW_rule__AndWhereEntry__Group_1__0__Impl_in_rule__AndWhereEntry__Group_1__08400);
            rule__AndWhereEntry__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__AndWhereEntry__Group_1__1_in_rule__AndWhereEntry__Group_1__08403);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4086:1: rule__AndWhereEntry__Group_1__0__Impl : ( () ) ;
    public final void rule__AndWhereEntry__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4090:1: ( ( () ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4091:1: ( () )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4091:1: ( () )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4092:1: ()
            {
             before(grammarAccess.getAndWhereEntryAccess().getAndWhereEntryEntriesAction_1_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4093:1: ()
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4095:1: 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4105:1: rule__AndWhereEntry__Group_1__1 : rule__AndWhereEntry__Group_1__1__Impl ;
    public final void rule__AndWhereEntry__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4109:1: ( rule__AndWhereEntry__Group_1__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4110:2: rule__AndWhereEntry__Group_1__1__Impl
            {
            pushFollow(FOLLOW_rule__AndWhereEntry__Group_1__1__Impl_in_rule__AndWhereEntry__Group_1__18461);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4116:1: rule__AndWhereEntry__Group_1__1__Impl : ( ( ( rule__AndWhereEntry__Group_1_1__0 ) ) ( ( rule__AndWhereEntry__Group_1_1__0 )* ) ) ;
    public final void rule__AndWhereEntry__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4120:1: ( ( ( ( rule__AndWhereEntry__Group_1_1__0 ) ) ( ( rule__AndWhereEntry__Group_1_1__0 )* ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4121:1: ( ( ( rule__AndWhereEntry__Group_1_1__0 ) ) ( ( rule__AndWhereEntry__Group_1_1__0 )* ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4121:1: ( ( ( rule__AndWhereEntry__Group_1_1__0 ) ) ( ( rule__AndWhereEntry__Group_1_1__0 )* ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4122:1: ( ( rule__AndWhereEntry__Group_1_1__0 ) ) ( ( rule__AndWhereEntry__Group_1_1__0 )* )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4122:1: ( ( rule__AndWhereEntry__Group_1_1__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4123:1: ( rule__AndWhereEntry__Group_1_1__0 )
            {
             before(grammarAccess.getAndWhereEntryAccess().getGroup_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4124:1: ( rule__AndWhereEntry__Group_1_1__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4124:2: rule__AndWhereEntry__Group_1_1__0
            {
            pushFollow(FOLLOW_rule__AndWhereEntry__Group_1_1__0_in_rule__AndWhereEntry__Group_1__1__Impl8490);
            rule__AndWhereEntry__Group_1_1__0();

            state._fsp--;


            }

             after(grammarAccess.getAndWhereEntryAccess().getGroup_1_1()); 

            }

            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4127:1: ( ( rule__AndWhereEntry__Group_1_1__0 )* )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4128:1: ( rule__AndWhereEntry__Group_1_1__0 )*
            {
             before(grammarAccess.getAndWhereEntryAccess().getGroup_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4129:1: ( rule__AndWhereEntry__Group_1_1__0 )*
            loop32:
            do {
                int alt32=2;
                int LA32_0 = input.LA(1);

                if ( (LA32_0==35) ) {
                    alt32=1;
                }


                switch (alt32) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4129:2: rule__AndWhereEntry__Group_1_1__0
            	    {
            	    pushFollow(FOLLOW_rule__AndWhereEntry__Group_1_1__0_in_rule__AndWhereEntry__Group_1__1__Impl8502);
            	    rule__AndWhereEntry__Group_1_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop32;
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4144:1: rule__AndWhereEntry__Group_1_1__0 : rule__AndWhereEntry__Group_1_1__0__Impl rule__AndWhereEntry__Group_1_1__1 ;
    public final void rule__AndWhereEntry__Group_1_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4148:1: ( rule__AndWhereEntry__Group_1_1__0__Impl rule__AndWhereEntry__Group_1_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4149:2: rule__AndWhereEntry__Group_1_1__0__Impl rule__AndWhereEntry__Group_1_1__1
            {
            pushFollow(FOLLOW_rule__AndWhereEntry__Group_1_1__0__Impl_in_rule__AndWhereEntry__Group_1_1__08539);
            rule__AndWhereEntry__Group_1_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__AndWhereEntry__Group_1_1__1_in_rule__AndWhereEntry__Group_1_1__08542);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4156:1: rule__AndWhereEntry__Group_1_1__0__Impl : ( 'AND' ) ;
    public final void rule__AndWhereEntry__Group_1_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4160:1: ( ( 'AND' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4161:1: ( 'AND' )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4161:1: ( 'AND' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4162:1: 'AND'
            {
             before(grammarAccess.getAndWhereEntryAccess().getANDKeyword_1_1_0()); 
            match(input,35,FOLLOW_35_in_rule__AndWhereEntry__Group_1_1__0__Impl8570); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4175:1: rule__AndWhereEntry__Group_1_1__1 : rule__AndWhereEntry__Group_1_1__1__Impl ;
    public final void rule__AndWhereEntry__Group_1_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4179:1: ( rule__AndWhereEntry__Group_1_1__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4180:2: rule__AndWhereEntry__Group_1_1__1__Impl
            {
            pushFollow(FOLLOW_rule__AndWhereEntry__Group_1_1__1__Impl_in_rule__AndWhereEntry__Group_1_1__18601);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4186:1: rule__AndWhereEntry__Group_1_1__1__Impl : ( ( rule__AndWhereEntry__EntriesAssignment_1_1_1 ) ) ;
    public final void rule__AndWhereEntry__Group_1_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4190:1: ( ( ( rule__AndWhereEntry__EntriesAssignment_1_1_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4191:1: ( ( rule__AndWhereEntry__EntriesAssignment_1_1_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4191:1: ( ( rule__AndWhereEntry__EntriesAssignment_1_1_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4192:1: ( rule__AndWhereEntry__EntriesAssignment_1_1_1 )
            {
             before(grammarAccess.getAndWhereEntryAccess().getEntriesAssignment_1_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4193:1: ( rule__AndWhereEntry__EntriesAssignment_1_1_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4193:2: rule__AndWhereEntry__EntriesAssignment_1_1_1
            {
            pushFollow(FOLLOW_rule__AndWhereEntry__EntriesAssignment_1_1_1_in_rule__AndWhereEntry__Group_1_1__1__Impl8628);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4207:1: rule__ParWhereEntry__Group__0 : rule__ParWhereEntry__Group__0__Impl rule__ParWhereEntry__Group__1 ;
    public final void rule__ParWhereEntry__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4211:1: ( rule__ParWhereEntry__Group__0__Impl rule__ParWhereEntry__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4212:2: rule__ParWhereEntry__Group__0__Impl rule__ParWhereEntry__Group__1
            {
            pushFollow(FOLLOW_rule__ParWhereEntry__Group__0__Impl_in_rule__ParWhereEntry__Group__08662);
            rule__ParWhereEntry__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ParWhereEntry__Group__1_in_rule__ParWhereEntry__Group__08665);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4219:1: rule__ParWhereEntry__Group__0__Impl : ( '(' ) ;
    public final void rule__ParWhereEntry__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4223:1: ( ( '(' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4224:1: ( '(' )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4224:1: ( '(' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4225:1: '('
            {
             before(grammarAccess.getParWhereEntryAccess().getLeftParenthesisKeyword_0()); 
            match(input,36,FOLLOW_36_in_rule__ParWhereEntry__Group__0__Impl8693); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4238:1: rule__ParWhereEntry__Group__1 : rule__ParWhereEntry__Group__1__Impl rule__ParWhereEntry__Group__2 ;
    public final void rule__ParWhereEntry__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4242:1: ( rule__ParWhereEntry__Group__1__Impl rule__ParWhereEntry__Group__2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4243:2: rule__ParWhereEntry__Group__1__Impl rule__ParWhereEntry__Group__2
            {
            pushFollow(FOLLOW_rule__ParWhereEntry__Group__1__Impl_in_rule__ParWhereEntry__Group__18724);
            rule__ParWhereEntry__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ParWhereEntry__Group__2_in_rule__ParWhereEntry__Group__18727);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4250:1: rule__ParWhereEntry__Group__1__Impl : ( ruleWhereEntry ) ;
    public final void rule__ParWhereEntry__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4254:1: ( ( ruleWhereEntry ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4255:1: ( ruleWhereEntry )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4255:1: ( ruleWhereEntry )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4256:1: ruleWhereEntry
            {
             before(grammarAccess.getParWhereEntryAccess().getWhereEntryParserRuleCall_1()); 
            pushFollow(FOLLOW_ruleWhereEntry_in_rule__ParWhereEntry__Group__1__Impl8754);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4267:1: rule__ParWhereEntry__Group__2 : rule__ParWhereEntry__Group__2__Impl ;
    public final void rule__ParWhereEntry__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4271:1: ( rule__ParWhereEntry__Group__2__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4272:2: rule__ParWhereEntry__Group__2__Impl
            {
            pushFollow(FOLLOW_rule__ParWhereEntry__Group__2__Impl_in_rule__ParWhereEntry__Group__28783);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4278:1: rule__ParWhereEntry__Group__2__Impl : ( ')' ) ;
    public final void rule__ParWhereEntry__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4282:1: ( ( ')' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4283:1: ( ')' )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4283:1: ( ')' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4284:1: ')'
            {
             before(grammarAccess.getParWhereEntryAccess().getRightParenthesisKeyword_2()); 
            match(input,37,FOLLOW_37_in_rule__ParWhereEntry__Group__2__Impl8811); 
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


    // $ANTLR start "rule__HavingEntry__Group__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4303:1: rule__HavingEntry__Group__0 : rule__HavingEntry__Group__0__Impl rule__HavingEntry__Group__1 ;
    public final void rule__HavingEntry__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4307:1: ( rule__HavingEntry__Group__0__Impl rule__HavingEntry__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4308:2: rule__HavingEntry__Group__0__Impl rule__HavingEntry__Group__1
            {
            pushFollow(FOLLOW_rule__HavingEntry__Group__0__Impl_in_rule__HavingEntry__Group__08848);
            rule__HavingEntry__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__HavingEntry__Group__1_in_rule__HavingEntry__Group__08851);
            rule__HavingEntry__Group__1();

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
    // $ANTLR end "rule__HavingEntry__Group__0"


    // $ANTLR start "rule__HavingEntry__Group__0__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4315:1: rule__HavingEntry__Group__0__Impl : ( ruleAndHavingEntry ) ;
    public final void rule__HavingEntry__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4319:1: ( ( ruleAndHavingEntry ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4320:1: ( ruleAndHavingEntry )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4320:1: ( ruleAndHavingEntry )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4321:1: ruleAndHavingEntry
            {
             before(grammarAccess.getHavingEntryAccess().getAndHavingEntryParserRuleCall_0()); 
            pushFollow(FOLLOW_ruleAndHavingEntry_in_rule__HavingEntry__Group__0__Impl8878);
            ruleAndHavingEntry();

            state._fsp--;

             after(grammarAccess.getHavingEntryAccess().getAndHavingEntryParserRuleCall_0()); 

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
    // $ANTLR end "rule__HavingEntry__Group__0__Impl"


    // $ANTLR start "rule__HavingEntry__Group__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4332:1: rule__HavingEntry__Group__1 : rule__HavingEntry__Group__1__Impl ;
    public final void rule__HavingEntry__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4336:1: ( rule__HavingEntry__Group__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4337:2: rule__HavingEntry__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__HavingEntry__Group__1__Impl_in_rule__HavingEntry__Group__18907);
            rule__HavingEntry__Group__1__Impl();

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
    // $ANTLR end "rule__HavingEntry__Group__1"


    // $ANTLR start "rule__HavingEntry__Group__1__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4343:1: rule__HavingEntry__Group__1__Impl : ( ( rule__HavingEntry__Group_1__0 )? ) ;
    public final void rule__HavingEntry__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4347:1: ( ( ( rule__HavingEntry__Group_1__0 )? ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4348:1: ( ( rule__HavingEntry__Group_1__0 )? )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4348:1: ( ( rule__HavingEntry__Group_1__0 )? )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4349:1: ( rule__HavingEntry__Group_1__0 )?
            {
             before(grammarAccess.getHavingEntryAccess().getGroup_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4350:1: ( rule__HavingEntry__Group_1__0 )?
            int alt33=2;
            int LA33_0 = input.LA(1);

            if ( (LA33_0==34) ) {
                alt33=1;
            }
            switch (alt33) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4350:2: rule__HavingEntry__Group_1__0
                    {
                    pushFollow(FOLLOW_rule__HavingEntry__Group_1__0_in_rule__HavingEntry__Group__1__Impl8934);
                    rule__HavingEntry__Group_1__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getHavingEntryAccess().getGroup_1()); 

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
    // $ANTLR end "rule__HavingEntry__Group__1__Impl"


    // $ANTLR start "rule__HavingEntry__Group_1__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4364:1: rule__HavingEntry__Group_1__0 : rule__HavingEntry__Group_1__0__Impl rule__HavingEntry__Group_1__1 ;
    public final void rule__HavingEntry__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4368:1: ( rule__HavingEntry__Group_1__0__Impl rule__HavingEntry__Group_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4369:2: rule__HavingEntry__Group_1__0__Impl rule__HavingEntry__Group_1__1
            {
            pushFollow(FOLLOW_rule__HavingEntry__Group_1__0__Impl_in_rule__HavingEntry__Group_1__08969);
            rule__HavingEntry__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__HavingEntry__Group_1__1_in_rule__HavingEntry__Group_1__08972);
            rule__HavingEntry__Group_1__1();

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
    // $ANTLR end "rule__HavingEntry__Group_1__0"


    // $ANTLR start "rule__HavingEntry__Group_1__0__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4376:1: rule__HavingEntry__Group_1__0__Impl : ( () ) ;
    public final void rule__HavingEntry__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4380:1: ( ( () ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4381:1: ( () )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4381:1: ( () )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4382:1: ()
            {
             before(grammarAccess.getHavingEntryAccess().getOrHavingEntryEntriesAction_1_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4383:1: ()
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4385:1: 
            {
            }

             after(grammarAccess.getHavingEntryAccess().getOrHavingEntryEntriesAction_1_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__HavingEntry__Group_1__0__Impl"


    // $ANTLR start "rule__HavingEntry__Group_1__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4395:1: rule__HavingEntry__Group_1__1 : rule__HavingEntry__Group_1__1__Impl ;
    public final void rule__HavingEntry__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4399:1: ( rule__HavingEntry__Group_1__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4400:2: rule__HavingEntry__Group_1__1__Impl
            {
            pushFollow(FOLLOW_rule__HavingEntry__Group_1__1__Impl_in_rule__HavingEntry__Group_1__19030);
            rule__HavingEntry__Group_1__1__Impl();

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
    // $ANTLR end "rule__HavingEntry__Group_1__1"


    // $ANTLR start "rule__HavingEntry__Group_1__1__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4406:1: rule__HavingEntry__Group_1__1__Impl : ( ( ( rule__HavingEntry__Group_1_1__0 ) ) ( ( rule__HavingEntry__Group_1_1__0 )* ) ) ;
    public final void rule__HavingEntry__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4410:1: ( ( ( ( rule__HavingEntry__Group_1_1__0 ) ) ( ( rule__HavingEntry__Group_1_1__0 )* ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4411:1: ( ( ( rule__HavingEntry__Group_1_1__0 ) ) ( ( rule__HavingEntry__Group_1_1__0 )* ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4411:1: ( ( ( rule__HavingEntry__Group_1_1__0 ) ) ( ( rule__HavingEntry__Group_1_1__0 )* ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4412:1: ( ( rule__HavingEntry__Group_1_1__0 ) ) ( ( rule__HavingEntry__Group_1_1__0 )* )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4412:1: ( ( rule__HavingEntry__Group_1_1__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4413:1: ( rule__HavingEntry__Group_1_1__0 )
            {
             before(grammarAccess.getHavingEntryAccess().getGroup_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4414:1: ( rule__HavingEntry__Group_1_1__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4414:2: rule__HavingEntry__Group_1_1__0
            {
            pushFollow(FOLLOW_rule__HavingEntry__Group_1_1__0_in_rule__HavingEntry__Group_1__1__Impl9059);
            rule__HavingEntry__Group_1_1__0();

            state._fsp--;


            }

             after(grammarAccess.getHavingEntryAccess().getGroup_1_1()); 

            }

            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4417:1: ( ( rule__HavingEntry__Group_1_1__0 )* )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4418:1: ( rule__HavingEntry__Group_1_1__0 )*
            {
             before(grammarAccess.getHavingEntryAccess().getGroup_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4419:1: ( rule__HavingEntry__Group_1_1__0 )*
            loop34:
            do {
                int alt34=2;
                int LA34_0 = input.LA(1);

                if ( (LA34_0==34) ) {
                    alt34=1;
                }


                switch (alt34) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4419:2: rule__HavingEntry__Group_1_1__0
            	    {
            	    pushFollow(FOLLOW_rule__HavingEntry__Group_1_1__0_in_rule__HavingEntry__Group_1__1__Impl9071);
            	    rule__HavingEntry__Group_1_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop34;
                }
            } while (true);

             after(grammarAccess.getHavingEntryAccess().getGroup_1_1()); 

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
    // $ANTLR end "rule__HavingEntry__Group_1__1__Impl"


    // $ANTLR start "rule__HavingEntry__Group_1_1__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4434:1: rule__HavingEntry__Group_1_1__0 : rule__HavingEntry__Group_1_1__0__Impl rule__HavingEntry__Group_1_1__1 ;
    public final void rule__HavingEntry__Group_1_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4438:1: ( rule__HavingEntry__Group_1_1__0__Impl rule__HavingEntry__Group_1_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4439:2: rule__HavingEntry__Group_1_1__0__Impl rule__HavingEntry__Group_1_1__1
            {
            pushFollow(FOLLOW_rule__HavingEntry__Group_1_1__0__Impl_in_rule__HavingEntry__Group_1_1__09108);
            rule__HavingEntry__Group_1_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__HavingEntry__Group_1_1__1_in_rule__HavingEntry__Group_1_1__09111);
            rule__HavingEntry__Group_1_1__1();

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
    // $ANTLR end "rule__HavingEntry__Group_1_1__0"


    // $ANTLR start "rule__HavingEntry__Group_1_1__0__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4446:1: rule__HavingEntry__Group_1_1__0__Impl : ( 'OR' ) ;
    public final void rule__HavingEntry__Group_1_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4450:1: ( ( 'OR' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4451:1: ( 'OR' )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4451:1: ( 'OR' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4452:1: 'OR'
            {
             before(grammarAccess.getHavingEntryAccess().getORKeyword_1_1_0()); 
            match(input,34,FOLLOW_34_in_rule__HavingEntry__Group_1_1__0__Impl9139); 
             after(grammarAccess.getHavingEntryAccess().getORKeyword_1_1_0()); 

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
    // $ANTLR end "rule__HavingEntry__Group_1_1__0__Impl"


    // $ANTLR start "rule__HavingEntry__Group_1_1__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4465:1: rule__HavingEntry__Group_1_1__1 : rule__HavingEntry__Group_1_1__1__Impl ;
    public final void rule__HavingEntry__Group_1_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4469:1: ( rule__HavingEntry__Group_1_1__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4470:2: rule__HavingEntry__Group_1_1__1__Impl
            {
            pushFollow(FOLLOW_rule__HavingEntry__Group_1_1__1__Impl_in_rule__HavingEntry__Group_1_1__19170);
            rule__HavingEntry__Group_1_1__1__Impl();

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
    // $ANTLR end "rule__HavingEntry__Group_1_1__1"


    // $ANTLR start "rule__HavingEntry__Group_1_1__1__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4476:1: rule__HavingEntry__Group_1_1__1__Impl : ( ( rule__HavingEntry__EntriesAssignment_1_1_1 ) ) ;
    public final void rule__HavingEntry__Group_1_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4480:1: ( ( ( rule__HavingEntry__EntriesAssignment_1_1_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4481:1: ( ( rule__HavingEntry__EntriesAssignment_1_1_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4481:1: ( ( rule__HavingEntry__EntriesAssignment_1_1_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4482:1: ( rule__HavingEntry__EntriesAssignment_1_1_1 )
            {
             before(grammarAccess.getHavingEntryAccess().getEntriesAssignment_1_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4483:1: ( rule__HavingEntry__EntriesAssignment_1_1_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4483:2: rule__HavingEntry__EntriesAssignment_1_1_1
            {
            pushFollow(FOLLOW_rule__HavingEntry__EntriesAssignment_1_1_1_in_rule__HavingEntry__Group_1_1__1__Impl9197);
            rule__HavingEntry__EntriesAssignment_1_1_1();

            state._fsp--;


            }

             after(grammarAccess.getHavingEntryAccess().getEntriesAssignment_1_1_1()); 

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
    // $ANTLR end "rule__HavingEntry__Group_1_1__1__Impl"


    // $ANTLR start "rule__AndHavingEntry__Group__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4497:1: rule__AndHavingEntry__Group__0 : rule__AndHavingEntry__Group__0__Impl rule__AndHavingEntry__Group__1 ;
    public final void rule__AndHavingEntry__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4501:1: ( rule__AndHavingEntry__Group__0__Impl rule__AndHavingEntry__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4502:2: rule__AndHavingEntry__Group__0__Impl rule__AndHavingEntry__Group__1
            {
            pushFollow(FOLLOW_rule__AndHavingEntry__Group__0__Impl_in_rule__AndHavingEntry__Group__09231);
            rule__AndHavingEntry__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__AndHavingEntry__Group__1_in_rule__AndHavingEntry__Group__09234);
            rule__AndHavingEntry__Group__1();

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
    // $ANTLR end "rule__AndHavingEntry__Group__0"


    // $ANTLR start "rule__AndHavingEntry__Group__0__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4509:1: rule__AndHavingEntry__Group__0__Impl : ( ruleConcreteHavingEntry ) ;
    public final void rule__AndHavingEntry__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4513:1: ( ( ruleConcreteHavingEntry ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4514:1: ( ruleConcreteHavingEntry )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4514:1: ( ruleConcreteHavingEntry )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4515:1: ruleConcreteHavingEntry
            {
             before(grammarAccess.getAndHavingEntryAccess().getConcreteHavingEntryParserRuleCall_0()); 
            pushFollow(FOLLOW_ruleConcreteHavingEntry_in_rule__AndHavingEntry__Group__0__Impl9261);
            ruleConcreteHavingEntry();

            state._fsp--;

             after(grammarAccess.getAndHavingEntryAccess().getConcreteHavingEntryParserRuleCall_0()); 

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
    // $ANTLR end "rule__AndHavingEntry__Group__0__Impl"


    // $ANTLR start "rule__AndHavingEntry__Group__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4526:1: rule__AndHavingEntry__Group__1 : rule__AndHavingEntry__Group__1__Impl ;
    public final void rule__AndHavingEntry__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4530:1: ( rule__AndHavingEntry__Group__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4531:2: rule__AndHavingEntry__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__AndHavingEntry__Group__1__Impl_in_rule__AndHavingEntry__Group__19290);
            rule__AndHavingEntry__Group__1__Impl();

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
    // $ANTLR end "rule__AndHavingEntry__Group__1"


    // $ANTLR start "rule__AndHavingEntry__Group__1__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4537:1: rule__AndHavingEntry__Group__1__Impl : ( ( rule__AndHavingEntry__Group_1__0 )? ) ;
    public final void rule__AndHavingEntry__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4541:1: ( ( ( rule__AndHavingEntry__Group_1__0 )? ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4542:1: ( ( rule__AndHavingEntry__Group_1__0 )? )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4542:1: ( ( rule__AndHavingEntry__Group_1__0 )? )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4543:1: ( rule__AndHavingEntry__Group_1__0 )?
            {
             before(grammarAccess.getAndHavingEntryAccess().getGroup_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4544:1: ( rule__AndHavingEntry__Group_1__0 )?
            int alt35=2;
            int LA35_0 = input.LA(1);

            if ( (LA35_0==35) ) {
                alt35=1;
            }
            switch (alt35) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4544:2: rule__AndHavingEntry__Group_1__0
                    {
                    pushFollow(FOLLOW_rule__AndHavingEntry__Group_1__0_in_rule__AndHavingEntry__Group__1__Impl9317);
                    rule__AndHavingEntry__Group_1__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getAndHavingEntryAccess().getGroup_1()); 

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
    // $ANTLR end "rule__AndHavingEntry__Group__1__Impl"


    // $ANTLR start "rule__AndHavingEntry__Group_1__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4558:1: rule__AndHavingEntry__Group_1__0 : rule__AndHavingEntry__Group_1__0__Impl rule__AndHavingEntry__Group_1__1 ;
    public final void rule__AndHavingEntry__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4562:1: ( rule__AndHavingEntry__Group_1__0__Impl rule__AndHavingEntry__Group_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4563:2: rule__AndHavingEntry__Group_1__0__Impl rule__AndHavingEntry__Group_1__1
            {
            pushFollow(FOLLOW_rule__AndHavingEntry__Group_1__0__Impl_in_rule__AndHavingEntry__Group_1__09352);
            rule__AndHavingEntry__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__AndHavingEntry__Group_1__1_in_rule__AndHavingEntry__Group_1__09355);
            rule__AndHavingEntry__Group_1__1();

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
    // $ANTLR end "rule__AndHavingEntry__Group_1__0"


    // $ANTLR start "rule__AndHavingEntry__Group_1__0__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4570:1: rule__AndHavingEntry__Group_1__0__Impl : ( () ) ;
    public final void rule__AndHavingEntry__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4574:1: ( ( () ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4575:1: ( () )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4575:1: ( () )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4576:1: ()
            {
             before(grammarAccess.getAndHavingEntryAccess().getAndHavingEntryEntriesAction_1_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4577:1: ()
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4579:1: 
            {
            }

             after(grammarAccess.getAndHavingEntryAccess().getAndHavingEntryEntriesAction_1_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AndHavingEntry__Group_1__0__Impl"


    // $ANTLR start "rule__AndHavingEntry__Group_1__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4589:1: rule__AndHavingEntry__Group_1__1 : rule__AndHavingEntry__Group_1__1__Impl ;
    public final void rule__AndHavingEntry__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4593:1: ( rule__AndHavingEntry__Group_1__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4594:2: rule__AndHavingEntry__Group_1__1__Impl
            {
            pushFollow(FOLLOW_rule__AndHavingEntry__Group_1__1__Impl_in_rule__AndHavingEntry__Group_1__19413);
            rule__AndHavingEntry__Group_1__1__Impl();

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
    // $ANTLR end "rule__AndHavingEntry__Group_1__1"


    // $ANTLR start "rule__AndHavingEntry__Group_1__1__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4600:1: rule__AndHavingEntry__Group_1__1__Impl : ( ( ( rule__AndHavingEntry__Group_1_1__0 ) ) ( ( rule__AndHavingEntry__Group_1_1__0 )* ) ) ;
    public final void rule__AndHavingEntry__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4604:1: ( ( ( ( rule__AndHavingEntry__Group_1_1__0 ) ) ( ( rule__AndHavingEntry__Group_1_1__0 )* ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4605:1: ( ( ( rule__AndHavingEntry__Group_1_1__0 ) ) ( ( rule__AndHavingEntry__Group_1_1__0 )* ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4605:1: ( ( ( rule__AndHavingEntry__Group_1_1__0 ) ) ( ( rule__AndHavingEntry__Group_1_1__0 )* ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4606:1: ( ( rule__AndHavingEntry__Group_1_1__0 ) ) ( ( rule__AndHavingEntry__Group_1_1__0 )* )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4606:1: ( ( rule__AndHavingEntry__Group_1_1__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4607:1: ( rule__AndHavingEntry__Group_1_1__0 )
            {
             before(grammarAccess.getAndHavingEntryAccess().getGroup_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4608:1: ( rule__AndHavingEntry__Group_1_1__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4608:2: rule__AndHavingEntry__Group_1_1__0
            {
            pushFollow(FOLLOW_rule__AndHavingEntry__Group_1_1__0_in_rule__AndHavingEntry__Group_1__1__Impl9442);
            rule__AndHavingEntry__Group_1_1__0();

            state._fsp--;


            }

             after(grammarAccess.getAndHavingEntryAccess().getGroup_1_1()); 

            }

            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4611:1: ( ( rule__AndHavingEntry__Group_1_1__0 )* )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4612:1: ( rule__AndHavingEntry__Group_1_1__0 )*
            {
             before(grammarAccess.getAndHavingEntryAccess().getGroup_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4613:1: ( rule__AndHavingEntry__Group_1_1__0 )*
            loop36:
            do {
                int alt36=2;
                int LA36_0 = input.LA(1);

                if ( (LA36_0==35) ) {
                    alt36=1;
                }


                switch (alt36) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4613:2: rule__AndHavingEntry__Group_1_1__0
            	    {
            	    pushFollow(FOLLOW_rule__AndHavingEntry__Group_1_1__0_in_rule__AndHavingEntry__Group_1__1__Impl9454);
            	    rule__AndHavingEntry__Group_1_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop36;
                }
            } while (true);

             after(grammarAccess.getAndHavingEntryAccess().getGroup_1_1()); 

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
    // $ANTLR end "rule__AndHavingEntry__Group_1__1__Impl"


    // $ANTLR start "rule__AndHavingEntry__Group_1_1__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4628:1: rule__AndHavingEntry__Group_1_1__0 : rule__AndHavingEntry__Group_1_1__0__Impl rule__AndHavingEntry__Group_1_1__1 ;
    public final void rule__AndHavingEntry__Group_1_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4632:1: ( rule__AndHavingEntry__Group_1_1__0__Impl rule__AndHavingEntry__Group_1_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4633:2: rule__AndHavingEntry__Group_1_1__0__Impl rule__AndHavingEntry__Group_1_1__1
            {
            pushFollow(FOLLOW_rule__AndHavingEntry__Group_1_1__0__Impl_in_rule__AndHavingEntry__Group_1_1__09491);
            rule__AndHavingEntry__Group_1_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__AndHavingEntry__Group_1_1__1_in_rule__AndHavingEntry__Group_1_1__09494);
            rule__AndHavingEntry__Group_1_1__1();

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
    // $ANTLR end "rule__AndHavingEntry__Group_1_1__0"


    // $ANTLR start "rule__AndHavingEntry__Group_1_1__0__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4640:1: rule__AndHavingEntry__Group_1_1__0__Impl : ( 'AND' ) ;
    public final void rule__AndHavingEntry__Group_1_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4644:1: ( ( 'AND' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4645:1: ( 'AND' )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4645:1: ( 'AND' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4646:1: 'AND'
            {
             before(grammarAccess.getAndHavingEntryAccess().getANDKeyword_1_1_0()); 
            match(input,35,FOLLOW_35_in_rule__AndHavingEntry__Group_1_1__0__Impl9522); 
             after(grammarAccess.getAndHavingEntryAccess().getANDKeyword_1_1_0()); 

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
    // $ANTLR end "rule__AndHavingEntry__Group_1_1__0__Impl"


    // $ANTLR start "rule__AndHavingEntry__Group_1_1__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4659:1: rule__AndHavingEntry__Group_1_1__1 : rule__AndHavingEntry__Group_1_1__1__Impl ;
    public final void rule__AndHavingEntry__Group_1_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4663:1: ( rule__AndHavingEntry__Group_1_1__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4664:2: rule__AndHavingEntry__Group_1_1__1__Impl
            {
            pushFollow(FOLLOW_rule__AndHavingEntry__Group_1_1__1__Impl_in_rule__AndHavingEntry__Group_1_1__19553);
            rule__AndHavingEntry__Group_1_1__1__Impl();

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
    // $ANTLR end "rule__AndHavingEntry__Group_1_1__1"


    // $ANTLR start "rule__AndHavingEntry__Group_1_1__1__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4670:1: rule__AndHavingEntry__Group_1_1__1__Impl : ( ( rule__AndHavingEntry__EntriesAssignment_1_1_1 ) ) ;
    public final void rule__AndHavingEntry__Group_1_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4674:1: ( ( ( rule__AndHavingEntry__EntriesAssignment_1_1_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4675:1: ( ( rule__AndHavingEntry__EntriesAssignment_1_1_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4675:1: ( ( rule__AndHavingEntry__EntriesAssignment_1_1_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4676:1: ( rule__AndHavingEntry__EntriesAssignment_1_1_1 )
            {
             before(grammarAccess.getAndHavingEntryAccess().getEntriesAssignment_1_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4677:1: ( rule__AndHavingEntry__EntriesAssignment_1_1_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4677:2: rule__AndHavingEntry__EntriesAssignment_1_1_1
            {
            pushFollow(FOLLOW_rule__AndHavingEntry__EntriesAssignment_1_1_1_in_rule__AndHavingEntry__Group_1_1__1__Impl9580);
            rule__AndHavingEntry__EntriesAssignment_1_1_1();

            state._fsp--;


            }

             after(grammarAccess.getAndHavingEntryAccess().getEntriesAssignment_1_1_1()); 

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
    // $ANTLR end "rule__AndHavingEntry__Group_1_1__1__Impl"


    // $ANTLR start "rule__ParHavingEntry__Group__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4691:1: rule__ParHavingEntry__Group__0 : rule__ParHavingEntry__Group__0__Impl rule__ParHavingEntry__Group__1 ;
    public final void rule__ParHavingEntry__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4695:1: ( rule__ParHavingEntry__Group__0__Impl rule__ParHavingEntry__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4696:2: rule__ParHavingEntry__Group__0__Impl rule__ParHavingEntry__Group__1
            {
            pushFollow(FOLLOW_rule__ParHavingEntry__Group__0__Impl_in_rule__ParHavingEntry__Group__09614);
            rule__ParHavingEntry__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ParHavingEntry__Group__1_in_rule__ParHavingEntry__Group__09617);
            rule__ParHavingEntry__Group__1();

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
    // $ANTLR end "rule__ParHavingEntry__Group__0"


    // $ANTLR start "rule__ParHavingEntry__Group__0__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4703:1: rule__ParHavingEntry__Group__0__Impl : ( '(' ) ;
    public final void rule__ParHavingEntry__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4707:1: ( ( '(' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4708:1: ( '(' )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4708:1: ( '(' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4709:1: '('
            {
             before(grammarAccess.getParHavingEntryAccess().getLeftParenthesisKeyword_0()); 
            match(input,36,FOLLOW_36_in_rule__ParHavingEntry__Group__0__Impl9645); 
             after(grammarAccess.getParHavingEntryAccess().getLeftParenthesisKeyword_0()); 

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
    // $ANTLR end "rule__ParHavingEntry__Group__0__Impl"


    // $ANTLR start "rule__ParHavingEntry__Group__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4722:1: rule__ParHavingEntry__Group__1 : rule__ParHavingEntry__Group__1__Impl rule__ParHavingEntry__Group__2 ;
    public final void rule__ParHavingEntry__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4726:1: ( rule__ParHavingEntry__Group__1__Impl rule__ParHavingEntry__Group__2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4727:2: rule__ParHavingEntry__Group__1__Impl rule__ParHavingEntry__Group__2
            {
            pushFollow(FOLLOW_rule__ParHavingEntry__Group__1__Impl_in_rule__ParHavingEntry__Group__19676);
            rule__ParHavingEntry__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ParHavingEntry__Group__2_in_rule__ParHavingEntry__Group__19679);
            rule__ParHavingEntry__Group__2();

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
    // $ANTLR end "rule__ParHavingEntry__Group__1"


    // $ANTLR start "rule__ParHavingEntry__Group__1__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4734:1: rule__ParHavingEntry__Group__1__Impl : ( ruleHavingEntry ) ;
    public final void rule__ParHavingEntry__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4738:1: ( ( ruleHavingEntry ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4739:1: ( ruleHavingEntry )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4739:1: ( ruleHavingEntry )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4740:1: ruleHavingEntry
            {
             before(grammarAccess.getParHavingEntryAccess().getHavingEntryParserRuleCall_1()); 
            pushFollow(FOLLOW_ruleHavingEntry_in_rule__ParHavingEntry__Group__1__Impl9706);
            ruleHavingEntry();

            state._fsp--;

             after(grammarAccess.getParHavingEntryAccess().getHavingEntryParserRuleCall_1()); 

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
    // $ANTLR end "rule__ParHavingEntry__Group__1__Impl"


    // $ANTLR start "rule__ParHavingEntry__Group__2"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4751:1: rule__ParHavingEntry__Group__2 : rule__ParHavingEntry__Group__2__Impl ;
    public final void rule__ParHavingEntry__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4755:1: ( rule__ParHavingEntry__Group__2__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4756:2: rule__ParHavingEntry__Group__2__Impl
            {
            pushFollow(FOLLOW_rule__ParHavingEntry__Group__2__Impl_in_rule__ParHavingEntry__Group__29735);
            rule__ParHavingEntry__Group__2__Impl();

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
    // $ANTLR end "rule__ParHavingEntry__Group__2"


    // $ANTLR start "rule__ParHavingEntry__Group__2__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4762:1: rule__ParHavingEntry__Group__2__Impl : ( ')' ) ;
    public final void rule__ParHavingEntry__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4766:1: ( ( ')' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4767:1: ( ')' )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4767:1: ( ')' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4768:1: ')'
            {
             before(grammarAccess.getParHavingEntryAccess().getRightParenthesisKeyword_2()); 
            match(input,37,FOLLOW_37_in_rule__ParHavingEntry__Group__2__Impl9763); 
             after(grammarAccess.getParHavingEntryAccess().getRightParenthesisKeyword_2()); 

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
    // $ANTLR end "rule__ParHavingEntry__Group__2__Impl"


    // $ANTLR start "rule__SingleExpressionWhereEntry__Group__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4787:1: rule__SingleExpressionWhereEntry__Group__0 : rule__SingleExpressionWhereEntry__Group__0__Impl rule__SingleExpressionWhereEntry__Group__1 ;
    public final void rule__SingleExpressionWhereEntry__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4791:1: ( rule__SingleExpressionWhereEntry__Group__0__Impl rule__SingleExpressionWhereEntry__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4792:2: rule__SingleExpressionWhereEntry__Group__0__Impl rule__SingleExpressionWhereEntry__Group__1
            {
            pushFollow(FOLLOW_rule__SingleExpressionWhereEntry__Group__0__Impl_in_rule__SingleExpressionWhereEntry__Group__09800);
            rule__SingleExpressionWhereEntry__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__SingleExpressionWhereEntry__Group__1_in_rule__SingleExpressionWhereEntry__Group__09803);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4799:1: rule__SingleExpressionWhereEntry__Group__0__Impl : ( ( rule__SingleExpressionWhereEntry__NameAssignment_0 ) ) ;
    public final void rule__SingleExpressionWhereEntry__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4803:1: ( ( ( rule__SingleExpressionWhereEntry__NameAssignment_0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4804:1: ( ( rule__SingleExpressionWhereEntry__NameAssignment_0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4804:1: ( ( rule__SingleExpressionWhereEntry__NameAssignment_0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4805:1: ( rule__SingleExpressionWhereEntry__NameAssignment_0 )
            {
             before(grammarAccess.getSingleExpressionWhereEntryAccess().getNameAssignment_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4806:1: ( rule__SingleExpressionWhereEntry__NameAssignment_0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4806:2: rule__SingleExpressionWhereEntry__NameAssignment_0
            {
            pushFollow(FOLLOW_rule__SingleExpressionWhereEntry__NameAssignment_0_in_rule__SingleExpressionWhereEntry__Group__0__Impl9830);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4816:1: rule__SingleExpressionWhereEntry__Group__1 : rule__SingleExpressionWhereEntry__Group__1__Impl rule__SingleExpressionWhereEntry__Group__2 ;
    public final void rule__SingleExpressionWhereEntry__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4820:1: ( rule__SingleExpressionWhereEntry__Group__1__Impl rule__SingleExpressionWhereEntry__Group__2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4821:2: rule__SingleExpressionWhereEntry__Group__1__Impl rule__SingleExpressionWhereEntry__Group__2
            {
            pushFollow(FOLLOW_rule__SingleExpressionWhereEntry__Group__1__Impl_in_rule__SingleExpressionWhereEntry__Group__19860);
            rule__SingleExpressionWhereEntry__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__SingleExpressionWhereEntry__Group__2_in_rule__SingleExpressionWhereEntry__Group__19863);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4828:1: rule__SingleExpressionWhereEntry__Group__1__Impl : ( ( rule__SingleExpressionWhereEntry__OperatorAssignment_1 ) ) ;
    public final void rule__SingleExpressionWhereEntry__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4832:1: ( ( ( rule__SingleExpressionWhereEntry__OperatorAssignment_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4833:1: ( ( rule__SingleExpressionWhereEntry__OperatorAssignment_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4833:1: ( ( rule__SingleExpressionWhereEntry__OperatorAssignment_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4834:1: ( rule__SingleExpressionWhereEntry__OperatorAssignment_1 )
            {
             before(grammarAccess.getSingleExpressionWhereEntryAccess().getOperatorAssignment_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4835:1: ( rule__SingleExpressionWhereEntry__OperatorAssignment_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4835:2: rule__SingleExpressionWhereEntry__OperatorAssignment_1
            {
            pushFollow(FOLLOW_rule__SingleExpressionWhereEntry__OperatorAssignment_1_in_rule__SingleExpressionWhereEntry__Group__1__Impl9890);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4845:1: rule__SingleExpressionWhereEntry__Group__2 : rule__SingleExpressionWhereEntry__Group__2__Impl ;
    public final void rule__SingleExpressionWhereEntry__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4849:1: ( rule__SingleExpressionWhereEntry__Group__2__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4850:2: rule__SingleExpressionWhereEntry__Group__2__Impl
            {
            pushFollow(FOLLOW_rule__SingleExpressionWhereEntry__Group__2__Impl_in_rule__SingleExpressionWhereEntry__Group__29920);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4856:1: rule__SingleExpressionWhereEntry__Group__2__Impl : ( ( rule__SingleExpressionWhereEntry__RhsAssignment_2 ) ) ;
    public final void rule__SingleExpressionWhereEntry__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4860:1: ( ( ( rule__SingleExpressionWhereEntry__RhsAssignment_2 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4861:1: ( ( rule__SingleExpressionWhereEntry__RhsAssignment_2 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4861:1: ( ( rule__SingleExpressionWhereEntry__RhsAssignment_2 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4862:1: ( rule__SingleExpressionWhereEntry__RhsAssignment_2 )
            {
             before(grammarAccess.getSingleExpressionWhereEntryAccess().getRhsAssignment_2()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4863:1: ( rule__SingleExpressionWhereEntry__RhsAssignment_2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4863:2: rule__SingleExpressionWhereEntry__RhsAssignment_2
            {
            pushFollow(FOLLOW_rule__SingleExpressionWhereEntry__RhsAssignment_2_in_rule__SingleExpressionWhereEntry__Group__2__Impl9947);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4879:1: rule__MultiExpressionWhereEntry__Group__0 : rule__MultiExpressionWhereEntry__Group__0__Impl rule__MultiExpressionWhereEntry__Group__1 ;
    public final void rule__MultiExpressionWhereEntry__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4883:1: ( rule__MultiExpressionWhereEntry__Group__0__Impl rule__MultiExpressionWhereEntry__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4884:2: rule__MultiExpressionWhereEntry__Group__0__Impl rule__MultiExpressionWhereEntry__Group__1
            {
            pushFollow(FOLLOW_rule__MultiExpressionWhereEntry__Group__0__Impl_in_rule__MultiExpressionWhereEntry__Group__09983);
            rule__MultiExpressionWhereEntry__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__MultiExpressionWhereEntry__Group__1_in_rule__MultiExpressionWhereEntry__Group__09986);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4891:1: rule__MultiExpressionWhereEntry__Group__0__Impl : ( ( rule__MultiExpressionWhereEntry__NameAssignment_0 ) ) ;
    public final void rule__MultiExpressionWhereEntry__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4895:1: ( ( ( rule__MultiExpressionWhereEntry__NameAssignment_0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4896:1: ( ( rule__MultiExpressionWhereEntry__NameAssignment_0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4896:1: ( ( rule__MultiExpressionWhereEntry__NameAssignment_0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4897:1: ( rule__MultiExpressionWhereEntry__NameAssignment_0 )
            {
             before(grammarAccess.getMultiExpressionWhereEntryAccess().getNameAssignment_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4898:1: ( rule__MultiExpressionWhereEntry__NameAssignment_0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4898:2: rule__MultiExpressionWhereEntry__NameAssignment_0
            {
            pushFollow(FOLLOW_rule__MultiExpressionWhereEntry__NameAssignment_0_in_rule__MultiExpressionWhereEntry__Group__0__Impl10013);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4908:1: rule__MultiExpressionWhereEntry__Group__1 : rule__MultiExpressionWhereEntry__Group__1__Impl rule__MultiExpressionWhereEntry__Group__2 ;
    public final void rule__MultiExpressionWhereEntry__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4912:1: ( rule__MultiExpressionWhereEntry__Group__1__Impl rule__MultiExpressionWhereEntry__Group__2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4913:2: rule__MultiExpressionWhereEntry__Group__1__Impl rule__MultiExpressionWhereEntry__Group__2
            {
            pushFollow(FOLLOW_rule__MultiExpressionWhereEntry__Group__1__Impl_in_rule__MultiExpressionWhereEntry__Group__110043);
            rule__MultiExpressionWhereEntry__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__MultiExpressionWhereEntry__Group__2_in_rule__MultiExpressionWhereEntry__Group__110046);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4920:1: rule__MultiExpressionWhereEntry__Group__1__Impl : ( ( rule__MultiExpressionWhereEntry__OperatorAssignment_1 ) ) ;
    public final void rule__MultiExpressionWhereEntry__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4924:1: ( ( ( rule__MultiExpressionWhereEntry__OperatorAssignment_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4925:1: ( ( rule__MultiExpressionWhereEntry__OperatorAssignment_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4925:1: ( ( rule__MultiExpressionWhereEntry__OperatorAssignment_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4926:1: ( rule__MultiExpressionWhereEntry__OperatorAssignment_1 )
            {
             before(grammarAccess.getMultiExpressionWhereEntryAccess().getOperatorAssignment_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4927:1: ( rule__MultiExpressionWhereEntry__OperatorAssignment_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4927:2: rule__MultiExpressionWhereEntry__OperatorAssignment_1
            {
            pushFollow(FOLLOW_rule__MultiExpressionWhereEntry__OperatorAssignment_1_in_rule__MultiExpressionWhereEntry__Group__1__Impl10073);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4937:1: rule__MultiExpressionWhereEntry__Group__2 : rule__MultiExpressionWhereEntry__Group__2__Impl ;
    public final void rule__MultiExpressionWhereEntry__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4941:1: ( rule__MultiExpressionWhereEntry__Group__2__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4942:2: rule__MultiExpressionWhereEntry__Group__2__Impl
            {
            pushFollow(FOLLOW_rule__MultiExpressionWhereEntry__Group__2__Impl_in_rule__MultiExpressionWhereEntry__Group__210103);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4948:1: rule__MultiExpressionWhereEntry__Group__2__Impl : ( ( rule__MultiExpressionWhereEntry__RhsAssignment_2 ) ) ;
    public final void rule__MultiExpressionWhereEntry__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4952:1: ( ( ( rule__MultiExpressionWhereEntry__RhsAssignment_2 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4953:1: ( ( rule__MultiExpressionWhereEntry__RhsAssignment_2 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4953:1: ( ( rule__MultiExpressionWhereEntry__RhsAssignment_2 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4954:1: ( rule__MultiExpressionWhereEntry__RhsAssignment_2 )
            {
             before(grammarAccess.getMultiExpressionWhereEntryAccess().getRhsAssignment_2()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4955:1: ( rule__MultiExpressionWhereEntry__RhsAssignment_2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4955:2: rule__MultiExpressionWhereEntry__RhsAssignment_2
            {
            pushFollow(FOLLOW_rule__MultiExpressionWhereEntry__RhsAssignment_2_in_rule__MultiExpressionWhereEntry__Group__2__Impl10130);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4971:1: rule__DoubleArrayExpression__Group__0 : rule__DoubleArrayExpression__Group__0__Impl rule__DoubleArrayExpression__Group__1 ;
    public final void rule__DoubleArrayExpression__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4975:1: ( rule__DoubleArrayExpression__Group__0__Impl rule__DoubleArrayExpression__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4976:2: rule__DoubleArrayExpression__Group__0__Impl rule__DoubleArrayExpression__Group__1
            {
            pushFollow(FOLLOW_rule__DoubleArrayExpression__Group__0__Impl_in_rule__DoubleArrayExpression__Group__010166);
            rule__DoubleArrayExpression__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__DoubleArrayExpression__Group__1_in_rule__DoubleArrayExpression__Group__010169);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4983:1: rule__DoubleArrayExpression__Group__0__Impl : ( '[' ) ;
    public final void rule__DoubleArrayExpression__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4987:1: ( ( '[' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4988:1: ( '[' )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4988:1: ( '[' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:4989:1: '['
            {
             before(grammarAccess.getDoubleArrayExpressionAccess().getLeftSquareBracketKeyword_0()); 
            match(input,38,FOLLOW_38_in_rule__DoubleArrayExpression__Group__0__Impl10197); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5002:1: rule__DoubleArrayExpression__Group__1 : rule__DoubleArrayExpression__Group__1__Impl rule__DoubleArrayExpression__Group__2 ;
    public final void rule__DoubleArrayExpression__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5006:1: ( rule__DoubleArrayExpression__Group__1__Impl rule__DoubleArrayExpression__Group__2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5007:2: rule__DoubleArrayExpression__Group__1__Impl rule__DoubleArrayExpression__Group__2
            {
            pushFollow(FOLLOW_rule__DoubleArrayExpression__Group__1__Impl_in_rule__DoubleArrayExpression__Group__110228);
            rule__DoubleArrayExpression__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__DoubleArrayExpression__Group__2_in_rule__DoubleArrayExpression__Group__110231);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5014:1: rule__DoubleArrayExpression__Group__1__Impl : ( ( rule__DoubleArrayExpression__ValuesAssignment_1 ) ) ;
    public final void rule__DoubleArrayExpression__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5018:1: ( ( ( rule__DoubleArrayExpression__ValuesAssignment_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5019:1: ( ( rule__DoubleArrayExpression__ValuesAssignment_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5019:1: ( ( rule__DoubleArrayExpression__ValuesAssignment_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5020:1: ( rule__DoubleArrayExpression__ValuesAssignment_1 )
            {
             before(grammarAccess.getDoubleArrayExpressionAccess().getValuesAssignment_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5021:1: ( rule__DoubleArrayExpression__ValuesAssignment_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5021:2: rule__DoubleArrayExpression__ValuesAssignment_1
            {
            pushFollow(FOLLOW_rule__DoubleArrayExpression__ValuesAssignment_1_in_rule__DoubleArrayExpression__Group__1__Impl10258);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5031:1: rule__DoubleArrayExpression__Group__2 : rule__DoubleArrayExpression__Group__2__Impl rule__DoubleArrayExpression__Group__3 ;
    public final void rule__DoubleArrayExpression__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5035:1: ( rule__DoubleArrayExpression__Group__2__Impl rule__DoubleArrayExpression__Group__3 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5036:2: rule__DoubleArrayExpression__Group__2__Impl rule__DoubleArrayExpression__Group__3
            {
            pushFollow(FOLLOW_rule__DoubleArrayExpression__Group__2__Impl_in_rule__DoubleArrayExpression__Group__210288);
            rule__DoubleArrayExpression__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__DoubleArrayExpression__Group__3_in_rule__DoubleArrayExpression__Group__210291);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5043:1: rule__DoubleArrayExpression__Group__2__Impl : ( ( rule__DoubleArrayExpression__Group_2__0 )* ) ;
    public final void rule__DoubleArrayExpression__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5047:1: ( ( ( rule__DoubleArrayExpression__Group_2__0 )* ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5048:1: ( ( rule__DoubleArrayExpression__Group_2__0 )* )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5048:1: ( ( rule__DoubleArrayExpression__Group_2__0 )* )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5049:1: ( rule__DoubleArrayExpression__Group_2__0 )*
            {
             before(grammarAccess.getDoubleArrayExpressionAccess().getGroup_2()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5050:1: ( rule__DoubleArrayExpression__Group_2__0 )*
            loop37:
            do {
                int alt37=2;
                int LA37_0 = input.LA(1);

                if ( (LA37_0==31) ) {
                    alt37=1;
                }


                switch (alt37) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5050:2: rule__DoubleArrayExpression__Group_2__0
            	    {
            	    pushFollow(FOLLOW_rule__DoubleArrayExpression__Group_2__0_in_rule__DoubleArrayExpression__Group__2__Impl10318);
            	    rule__DoubleArrayExpression__Group_2__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop37;
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5060:1: rule__DoubleArrayExpression__Group__3 : rule__DoubleArrayExpression__Group__3__Impl ;
    public final void rule__DoubleArrayExpression__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5064:1: ( rule__DoubleArrayExpression__Group__3__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5065:2: rule__DoubleArrayExpression__Group__3__Impl
            {
            pushFollow(FOLLOW_rule__DoubleArrayExpression__Group__3__Impl_in_rule__DoubleArrayExpression__Group__310349);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5071:1: rule__DoubleArrayExpression__Group__3__Impl : ( ']' ) ;
    public final void rule__DoubleArrayExpression__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5075:1: ( ( ']' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5076:1: ( ']' )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5076:1: ( ']' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5077:1: ']'
            {
             before(grammarAccess.getDoubleArrayExpressionAccess().getRightSquareBracketKeyword_3()); 
            match(input,39,FOLLOW_39_in_rule__DoubleArrayExpression__Group__3__Impl10377); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5098:1: rule__DoubleArrayExpression__Group_2__0 : rule__DoubleArrayExpression__Group_2__0__Impl rule__DoubleArrayExpression__Group_2__1 ;
    public final void rule__DoubleArrayExpression__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5102:1: ( rule__DoubleArrayExpression__Group_2__0__Impl rule__DoubleArrayExpression__Group_2__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5103:2: rule__DoubleArrayExpression__Group_2__0__Impl rule__DoubleArrayExpression__Group_2__1
            {
            pushFollow(FOLLOW_rule__DoubleArrayExpression__Group_2__0__Impl_in_rule__DoubleArrayExpression__Group_2__010416);
            rule__DoubleArrayExpression__Group_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__DoubleArrayExpression__Group_2__1_in_rule__DoubleArrayExpression__Group_2__010419);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5110:1: rule__DoubleArrayExpression__Group_2__0__Impl : ( ',' ) ;
    public final void rule__DoubleArrayExpression__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5114:1: ( ( ',' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5115:1: ( ',' )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5115:1: ( ',' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5116:1: ','
            {
             before(grammarAccess.getDoubleArrayExpressionAccess().getCommaKeyword_2_0()); 
            match(input,31,FOLLOW_31_in_rule__DoubleArrayExpression__Group_2__0__Impl10447); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5129:1: rule__DoubleArrayExpression__Group_2__1 : rule__DoubleArrayExpression__Group_2__1__Impl ;
    public final void rule__DoubleArrayExpression__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5133:1: ( rule__DoubleArrayExpression__Group_2__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5134:2: rule__DoubleArrayExpression__Group_2__1__Impl
            {
            pushFollow(FOLLOW_rule__DoubleArrayExpression__Group_2__1__Impl_in_rule__DoubleArrayExpression__Group_2__110478);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5140:1: rule__DoubleArrayExpression__Group_2__1__Impl : ( ( rule__DoubleArrayExpression__ValuesAssignment_2_1 ) ) ;
    public final void rule__DoubleArrayExpression__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5144:1: ( ( ( rule__DoubleArrayExpression__ValuesAssignment_2_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5145:1: ( ( rule__DoubleArrayExpression__ValuesAssignment_2_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5145:1: ( ( rule__DoubleArrayExpression__ValuesAssignment_2_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5146:1: ( rule__DoubleArrayExpression__ValuesAssignment_2_1 )
            {
             before(grammarAccess.getDoubleArrayExpressionAccess().getValuesAssignment_2_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5147:1: ( rule__DoubleArrayExpression__ValuesAssignment_2_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5147:2: rule__DoubleArrayExpression__ValuesAssignment_2_1
            {
            pushFollow(FOLLOW_rule__DoubleArrayExpression__ValuesAssignment_2_1_in_rule__DoubleArrayExpression__Group_2__1__Impl10505);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5161:1: rule__LongArrayExpression__Group__0 : rule__LongArrayExpression__Group__0__Impl rule__LongArrayExpression__Group__1 ;
    public final void rule__LongArrayExpression__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5165:1: ( rule__LongArrayExpression__Group__0__Impl rule__LongArrayExpression__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5166:2: rule__LongArrayExpression__Group__0__Impl rule__LongArrayExpression__Group__1
            {
            pushFollow(FOLLOW_rule__LongArrayExpression__Group__0__Impl_in_rule__LongArrayExpression__Group__010539);
            rule__LongArrayExpression__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__LongArrayExpression__Group__1_in_rule__LongArrayExpression__Group__010542);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5173:1: rule__LongArrayExpression__Group__0__Impl : ( '[' ) ;
    public final void rule__LongArrayExpression__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5177:1: ( ( '[' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5178:1: ( '[' )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5178:1: ( '[' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5179:1: '['
            {
             before(grammarAccess.getLongArrayExpressionAccess().getLeftSquareBracketKeyword_0()); 
            match(input,38,FOLLOW_38_in_rule__LongArrayExpression__Group__0__Impl10570); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5192:1: rule__LongArrayExpression__Group__1 : rule__LongArrayExpression__Group__1__Impl rule__LongArrayExpression__Group__2 ;
    public final void rule__LongArrayExpression__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5196:1: ( rule__LongArrayExpression__Group__1__Impl rule__LongArrayExpression__Group__2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5197:2: rule__LongArrayExpression__Group__1__Impl rule__LongArrayExpression__Group__2
            {
            pushFollow(FOLLOW_rule__LongArrayExpression__Group__1__Impl_in_rule__LongArrayExpression__Group__110601);
            rule__LongArrayExpression__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__LongArrayExpression__Group__2_in_rule__LongArrayExpression__Group__110604);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5204:1: rule__LongArrayExpression__Group__1__Impl : ( ( rule__LongArrayExpression__ValuesAssignment_1 ) ) ;
    public final void rule__LongArrayExpression__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5208:1: ( ( ( rule__LongArrayExpression__ValuesAssignment_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5209:1: ( ( rule__LongArrayExpression__ValuesAssignment_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5209:1: ( ( rule__LongArrayExpression__ValuesAssignment_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5210:1: ( rule__LongArrayExpression__ValuesAssignment_1 )
            {
             before(grammarAccess.getLongArrayExpressionAccess().getValuesAssignment_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5211:1: ( rule__LongArrayExpression__ValuesAssignment_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5211:2: rule__LongArrayExpression__ValuesAssignment_1
            {
            pushFollow(FOLLOW_rule__LongArrayExpression__ValuesAssignment_1_in_rule__LongArrayExpression__Group__1__Impl10631);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5221:1: rule__LongArrayExpression__Group__2 : rule__LongArrayExpression__Group__2__Impl rule__LongArrayExpression__Group__3 ;
    public final void rule__LongArrayExpression__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5225:1: ( rule__LongArrayExpression__Group__2__Impl rule__LongArrayExpression__Group__3 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5226:2: rule__LongArrayExpression__Group__2__Impl rule__LongArrayExpression__Group__3
            {
            pushFollow(FOLLOW_rule__LongArrayExpression__Group__2__Impl_in_rule__LongArrayExpression__Group__210661);
            rule__LongArrayExpression__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__LongArrayExpression__Group__3_in_rule__LongArrayExpression__Group__210664);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5233:1: rule__LongArrayExpression__Group__2__Impl : ( ( rule__LongArrayExpression__Group_2__0 )* ) ;
    public final void rule__LongArrayExpression__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5237:1: ( ( ( rule__LongArrayExpression__Group_2__0 )* ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5238:1: ( ( rule__LongArrayExpression__Group_2__0 )* )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5238:1: ( ( rule__LongArrayExpression__Group_2__0 )* )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5239:1: ( rule__LongArrayExpression__Group_2__0 )*
            {
             before(grammarAccess.getLongArrayExpressionAccess().getGroup_2()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5240:1: ( rule__LongArrayExpression__Group_2__0 )*
            loop38:
            do {
                int alt38=2;
                int LA38_0 = input.LA(1);

                if ( (LA38_0==31) ) {
                    alt38=1;
                }


                switch (alt38) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5240:2: rule__LongArrayExpression__Group_2__0
            	    {
            	    pushFollow(FOLLOW_rule__LongArrayExpression__Group_2__0_in_rule__LongArrayExpression__Group__2__Impl10691);
            	    rule__LongArrayExpression__Group_2__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop38;
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5250:1: rule__LongArrayExpression__Group__3 : rule__LongArrayExpression__Group__3__Impl ;
    public final void rule__LongArrayExpression__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5254:1: ( rule__LongArrayExpression__Group__3__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5255:2: rule__LongArrayExpression__Group__3__Impl
            {
            pushFollow(FOLLOW_rule__LongArrayExpression__Group__3__Impl_in_rule__LongArrayExpression__Group__310722);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5261:1: rule__LongArrayExpression__Group__3__Impl : ( ']' ) ;
    public final void rule__LongArrayExpression__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5265:1: ( ( ']' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5266:1: ( ']' )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5266:1: ( ']' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5267:1: ']'
            {
             before(grammarAccess.getLongArrayExpressionAccess().getRightSquareBracketKeyword_3()); 
            match(input,39,FOLLOW_39_in_rule__LongArrayExpression__Group__3__Impl10750); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5288:1: rule__LongArrayExpression__Group_2__0 : rule__LongArrayExpression__Group_2__0__Impl rule__LongArrayExpression__Group_2__1 ;
    public final void rule__LongArrayExpression__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5292:1: ( rule__LongArrayExpression__Group_2__0__Impl rule__LongArrayExpression__Group_2__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5293:2: rule__LongArrayExpression__Group_2__0__Impl rule__LongArrayExpression__Group_2__1
            {
            pushFollow(FOLLOW_rule__LongArrayExpression__Group_2__0__Impl_in_rule__LongArrayExpression__Group_2__010789);
            rule__LongArrayExpression__Group_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__LongArrayExpression__Group_2__1_in_rule__LongArrayExpression__Group_2__010792);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5300:1: rule__LongArrayExpression__Group_2__0__Impl : ( ',' ) ;
    public final void rule__LongArrayExpression__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5304:1: ( ( ',' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5305:1: ( ',' )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5305:1: ( ',' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5306:1: ','
            {
             before(grammarAccess.getLongArrayExpressionAccess().getCommaKeyword_2_0()); 
            match(input,31,FOLLOW_31_in_rule__LongArrayExpression__Group_2__0__Impl10820); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5319:1: rule__LongArrayExpression__Group_2__1 : rule__LongArrayExpression__Group_2__1__Impl ;
    public final void rule__LongArrayExpression__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5323:1: ( rule__LongArrayExpression__Group_2__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5324:2: rule__LongArrayExpression__Group_2__1__Impl
            {
            pushFollow(FOLLOW_rule__LongArrayExpression__Group_2__1__Impl_in_rule__LongArrayExpression__Group_2__110851);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5330:1: rule__LongArrayExpression__Group_2__1__Impl : ( ( rule__LongArrayExpression__ValuesAssignment_2_1 ) ) ;
    public final void rule__LongArrayExpression__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5334:1: ( ( ( rule__LongArrayExpression__ValuesAssignment_2_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5335:1: ( ( rule__LongArrayExpression__ValuesAssignment_2_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5335:1: ( ( rule__LongArrayExpression__ValuesAssignment_2_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5336:1: ( rule__LongArrayExpression__ValuesAssignment_2_1 )
            {
             before(grammarAccess.getLongArrayExpressionAccess().getValuesAssignment_2_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5337:1: ( rule__LongArrayExpression__ValuesAssignment_2_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5337:2: rule__LongArrayExpression__ValuesAssignment_2_1
            {
            pushFollow(FOLLOW_rule__LongArrayExpression__ValuesAssignment_2_1_in_rule__LongArrayExpression__Group_2__1__Impl10878);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5351:1: rule__StringArrayExpression__Group__0 : rule__StringArrayExpression__Group__0__Impl rule__StringArrayExpression__Group__1 ;
    public final void rule__StringArrayExpression__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5355:1: ( rule__StringArrayExpression__Group__0__Impl rule__StringArrayExpression__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5356:2: rule__StringArrayExpression__Group__0__Impl rule__StringArrayExpression__Group__1
            {
            pushFollow(FOLLOW_rule__StringArrayExpression__Group__0__Impl_in_rule__StringArrayExpression__Group__010912);
            rule__StringArrayExpression__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__StringArrayExpression__Group__1_in_rule__StringArrayExpression__Group__010915);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5363:1: rule__StringArrayExpression__Group__0__Impl : ( '[' ) ;
    public final void rule__StringArrayExpression__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5367:1: ( ( '[' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5368:1: ( '[' )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5368:1: ( '[' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5369:1: '['
            {
             before(grammarAccess.getStringArrayExpressionAccess().getLeftSquareBracketKeyword_0()); 
            match(input,38,FOLLOW_38_in_rule__StringArrayExpression__Group__0__Impl10943); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5382:1: rule__StringArrayExpression__Group__1 : rule__StringArrayExpression__Group__1__Impl rule__StringArrayExpression__Group__2 ;
    public final void rule__StringArrayExpression__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5386:1: ( rule__StringArrayExpression__Group__1__Impl rule__StringArrayExpression__Group__2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5387:2: rule__StringArrayExpression__Group__1__Impl rule__StringArrayExpression__Group__2
            {
            pushFollow(FOLLOW_rule__StringArrayExpression__Group__1__Impl_in_rule__StringArrayExpression__Group__110974);
            rule__StringArrayExpression__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__StringArrayExpression__Group__2_in_rule__StringArrayExpression__Group__110977);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5394:1: rule__StringArrayExpression__Group__1__Impl : ( ( rule__StringArrayExpression__ValuesAssignment_1 ) ) ;
    public final void rule__StringArrayExpression__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5398:1: ( ( ( rule__StringArrayExpression__ValuesAssignment_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5399:1: ( ( rule__StringArrayExpression__ValuesAssignment_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5399:1: ( ( rule__StringArrayExpression__ValuesAssignment_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5400:1: ( rule__StringArrayExpression__ValuesAssignment_1 )
            {
             before(grammarAccess.getStringArrayExpressionAccess().getValuesAssignment_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5401:1: ( rule__StringArrayExpression__ValuesAssignment_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5401:2: rule__StringArrayExpression__ValuesAssignment_1
            {
            pushFollow(FOLLOW_rule__StringArrayExpression__ValuesAssignment_1_in_rule__StringArrayExpression__Group__1__Impl11004);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5411:1: rule__StringArrayExpression__Group__2 : rule__StringArrayExpression__Group__2__Impl rule__StringArrayExpression__Group__3 ;
    public final void rule__StringArrayExpression__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5415:1: ( rule__StringArrayExpression__Group__2__Impl rule__StringArrayExpression__Group__3 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5416:2: rule__StringArrayExpression__Group__2__Impl rule__StringArrayExpression__Group__3
            {
            pushFollow(FOLLOW_rule__StringArrayExpression__Group__2__Impl_in_rule__StringArrayExpression__Group__211034);
            rule__StringArrayExpression__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__StringArrayExpression__Group__3_in_rule__StringArrayExpression__Group__211037);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5423:1: rule__StringArrayExpression__Group__2__Impl : ( ( rule__StringArrayExpression__Group_2__0 )* ) ;
    public final void rule__StringArrayExpression__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5427:1: ( ( ( rule__StringArrayExpression__Group_2__0 )* ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5428:1: ( ( rule__StringArrayExpression__Group_2__0 )* )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5428:1: ( ( rule__StringArrayExpression__Group_2__0 )* )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5429:1: ( rule__StringArrayExpression__Group_2__0 )*
            {
             before(grammarAccess.getStringArrayExpressionAccess().getGroup_2()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5430:1: ( rule__StringArrayExpression__Group_2__0 )*
            loop39:
            do {
                int alt39=2;
                int LA39_0 = input.LA(1);

                if ( (LA39_0==31) ) {
                    alt39=1;
                }


                switch (alt39) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5430:2: rule__StringArrayExpression__Group_2__0
            	    {
            	    pushFollow(FOLLOW_rule__StringArrayExpression__Group_2__0_in_rule__StringArrayExpression__Group__2__Impl11064);
            	    rule__StringArrayExpression__Group_2__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop39;
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5440:1: rule__StringArrayExpression__Group__3 : rule__StringArrayExpression__Group__3__Impl ;
    public final void rule__StringArrayExpression__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5444:1: ( rule__StringArrayExpression__Group__3__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5445:2: rule__StringArrayExpression__Group__3__Impl
            {
            pushFollow(FOLLOW_rule__StringArrayExpression__Group__3__Impl_in_rule__StringArrayExpression__Group__311095);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5451:1: rule__StringArrayExpression__Group__3__Impl : ( ']' ) ;
    public final void rule__StringArrayExpression__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5455:1: ( ( ']' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5456:1: ( ']' )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5456:1: ( ']' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5457:1: ']'
            {
             before(grammarAccess.getStringArrayExpressionAccess().getRightSquareBracketKeyword_3()); 
            match(input,39,FOLLOW_39_in_rule__StringArrayExpression__Group__3__Impl11123); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5478:1: rule__StringArrayExpression__Group_2__0 : rule__StringArrayExpression__Group_2__0__Impl rule__StringArrayExpression__Group_2__1 ;
    public final void rule__StringArrayExpression__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5482:1: ( rule__StringArrayExpression__Group_2__0__Impl rule__StringArrayExpression__Group_2__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5483:2: rule__StringArrayExpression__Group_2__0__Impl rule__StringArrayExpression__Group_2__1
            {
            pushFollow(FOLLOW_rule__StringArrayExpression__Group_2__0__Impl_in_rule__StringArrayExpression__Group_2__011162);
            rule__StringArrayExpression__Group_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__StringArrayExpression__Group_2__1_in_rule__StringArrayExpression__Group_2__011165);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5490:1: rule__StringArrayExpression__Group_2__0__Impl : ( ',' ) ;
    public final void rule__StringArrayExpression__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5494:1: ( ( ',' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5495:1: ( ',' )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5495:1: ( ',' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5496:1: ','
            {
             before(grammarAccess.getStringArrayExpressionAccess().getCommaKeyword_2_0()); 
            match(input,31,FOLLOW_31_in_rule__StringArrayExpression__Group_2__0__Impl11193); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5509:1: rule__StringArrayExpression__Group_2__1 : rule__StringArrayExpression__Group_2__1__Impl ;
    public final void rule__StringArrayExpression__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5513:1: ( rule__StringArrayExpression__Group_2__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5514:2: rule__StringArrayExpression__Group_2__1__Impl
            {
            pushFollow(FOLLOW_rule__StringArrayExpression__Group_2__1__Impl_in_rule__StringArrayExpression__Group_2__111224);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5520:1: rule__StringArrayExpression__Group_2__1__Impl : ( ( rule__StringArrayExpression__ValuesAssignment_2_1 ) ) ;
    public final void rule__StringArrayExpression__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5524:1: ( ( ( rule__StringArrayExpression__ValuesAssignment_2_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5525:1: ( ( rule__StringArrayExpression__ValuesAssignment_2_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5525:1: ( ( rule__StringArrayExpression__ValuesAssignment_2_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5526:1: ( rule__StringArrayExpression__ValuesAssignment_2_1 )
            {
             before(grammarAccess.getStringArrayExpressionAccess().getValuesAssignment_2_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5527:1: ( rule__StringArrayExpression__ValuesAssignment_2_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5527:2: rule__StringArrayExpression__ValuesAssignment_2_1
            {
            pushFollow(FOLLOW_rule__StringArrayExpression__ValuesAssignment_2_1_in_rule__StringArrayExpression__Group_2__1__Impl11251);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5541:1: rule__NullArrayExpression__Group__0 : rule__NullArrayExpression__Group__0__Impl rule__NullArrayExpression__Group__1 ;
    public final void rule__NullArrayExpression__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5545:1: ( rule__NullArrayExpression__Group__0__Impl rule__NullArrayExpression__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5546:2: rule__NullArrayExpression__Group__0__Impl rule__NullArrayExpression__Group__1
            {
            pushFollow(FOLLOW_rule__NullArrayExpression__Group__0__Impl_in_rule__NullArrayExpression__Group__011285);
            rule__NullArrayExpression__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__NullArrayExpression__Group__1_in_rule__NullArrayExpression__Group__011288);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5553:1: rule__NullArrayExpression__Group__0__Impl : ( '[' ) ;
    public final void rule__NullArrayExpression__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5557:1: ( ( '[' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5558:1: ( '[' )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5558:1: ( '[' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5559:1: '['
            {
             before(grammarAccess.getNullArrayExpressionAccess().getLeftSquareBracketKeyword_0()); 
            match(input,38,FOLLOW_38_in_rule__NullArrayExpression__Group__0__Impl11316); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5572:1: rule__NullArrayExpression__Group__1 : rule__NullArrayExpression__Group__1__Impl rule__NullArrayExpression__Group__2 ;
    public final void rule__NullArrayExpression__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5576:1: ( rule__NullArrayExpression__Group__1__Impl rule__NullArrayExpression__Group__2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5577:2: rule__NullArrayExpression__Group__1__Impl rule__NullArrayExpression__Group__2
            {
            pushFollow(FOLLOW_rule__NullArrayExpression__Group__1__Impl_in_rule__NullArrayExpression__Group__111347);
            rule__NullArrayExpression__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__NullArrayExpression__Group__2_in_rule__NullArrayExpression__Group__111350);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5584:1: rule__NullArrayExpression__Group__1__Impl : ( ( rule__NullArrayExpression__ValuesAssignment_1 ) ) ;
    public final void rule__NullArrayExpression__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5588:1: ( ( ( rule__NullArrayExpression__ValuesAssignment_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5589:1: ( ( rule__NullArrayExpression__ValuesAssignment_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5589:1: ( ( rule__NullArrayExpression__ValuesAssignment_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5590:1: ( rule__NullArrayExpression__ValuesAssignment_1 )
            {
             before(grammarAccess.getNullArrayExpressionAccess().getValuesAssignment_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5591:1: ( rule__NullArrayExpression__ValuesAssignment_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5591:2: rule__NullArrayExpression__ValuesAssignment_1
            {
            pushFollow(FOLLOW_rule__NullArrayExpression__ValuesAssignment_1_in_rule__NullArrayExpression__Group__1__Impl11377);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5601:1: rule__NullArrayExpression__Group__2 : rule__NullArrayExpression__Group__2__Impl rule__NullArrayExpression__Group__3 ;
    public final void rule__NullArrayExpression__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5605:1: ( rule__NullArrayExpression__Group__2__Impl rule__NullArrayExpression__Group__3 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5606:2: rule__NullArrayExpression__Group__2__Impl rule__NullArrayExpression__Group__3
            {
            pushFollow(FOLLOW_rule__NullArrayExpression__Group__2__Impl_in_rule__NullArrayExpression__Group__211407);
            rule__NullArrayExpression__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__NullArrayExpression__Group__3_in_rule__NullArrayExpression__Group__211410);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5613:1: rule__NullArrayExpression__Group__2__Impl : ( ( rule__NullArrayExpression__Group_2__0 )* ) ;
    public final void rule__NullArrayExpression__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5617:1: ( ( ( rule__NullArrayExpression__Group_2__0 )* ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5618:1: ( ( rule__NullArrayExpression__Group_2__0 )* )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5618:1: ( ( rule__NullArrayExpression__Group_2__0 )* )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5619:1: ( rule__NullArrayExpression__Group_2__0 )*
            {
             before(grammarAccess.getNullArrayExpressionAccess().getGroup_2()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5620:1: ( rule__NullArrayExpression__Group_2__0 )*
            loop40:
            do {
                int alt40=2;
                int LA40_0 = input.LA(1);

                if ( (LA40_0==31) ) {
                    alt40=1;
                }


                switch (alt40) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5620:2: rule__NullArrayExpression__Group_2__0
            	    {
            	    pushFollow(FOLLOW_rule__NullArrayExpression__Group_2__0_in_rule__NullArrayExpression__Group__2__Impl11437);
            	    rule__NullArrayExpression__Group_2__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop40;
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5630:1: rule__NullArrayExpression__Group__3 : rule__NullArrayExpression__Group__3__Impl ;
    public final void rule__NullArrayExpression__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5634:1: ( rule__NullArrayExpression__Group__3__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5635:2: rule__NullArrayExpression__Group__3__Impl
            {
            pushFollow(FOLLOW_rule__NullArrayExpression__Group__3__Impl_in_rule__NullArrayExpression__Group__311468);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5641:1: rule__NullArrayExpression__Group__3__Impl : ( ']' ) ;
    public final void rule__NullArrayExpression__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5645:1: ( ( ']' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5646:1: ( ']' )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5646:1: ( ']' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5647:1: ']'
            {
             before(grammarAccess.getNullArrayExpressionAccess().getRightSquareBracketKeyword_3()); 
            match(input,39,FOLLOW_39_in_rule__NullArrayExpression__Group__3__Impl11496); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5668:1: rule__NullArrayExpression__Group_2__0 : rule__NullArrayExpression__Group_2__0__Impl rule__NullArrayExpression__Group_2__1 ;
    public final void rule__NullArrayExpression__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5672:1: ( rule__NullArrayExpression__Group_2__0__Impl rule__NullArrayExpression__Group_2__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5673:2: rule__NullArrayExpression__Group_2__0__Impl rule__NullArrayExpression__Group_2__1
            {
            pushFollow(FOLLOW_rule__NullArrayExpression__Group_2__0__Impl_in_rule__NullArrayExpression__Group_2__011535);
            rule__NullArrayExpression__Group_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__NullArrayExpression__Group_2__1_in_rule__NullArrayExpression__Group_2__011538);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5680:1: rule__NullArrayExpression__Group_2__0__Impl : ( ',' ) ;
    public final void rule__NullArrayExpression__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5684:1: ( ( ',' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5685:1: ( ',' )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5685:1: ( ',' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5686:1: ','
            {
             before(grammarAccess.getNullArrayExpressionAccess().getCommaKeyword_2_0()); 
            match(input,31,FOLLOW_31_in_rule__NullArrayExpression__Group_2__0__Impl11566); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5699:1: rule__NullArrayExpression__Group_2__1 : rule__NullArrayExpression__Group_2__1__Impl ;
    public final void rule__NullArrayExpression__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5703:1: ( rule__NullArrayExpression__Group_2__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5704:2: rule__NullArrayExpression__Group_2__1__Impl
            {
            pushFollow(FOLLOW_rule__NullArrayExpression__Group_2__1__Impl_in_rule__NullArrayExpression__Group_2__111597);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5710:1: rule__NullArrayExpression__Group_2__1__Impl : ( ( rule__NullArrayExpression__ValuesAssignment_2_1 ) ) ;
    public final void rule__NullArrayExpression__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5714:1: ( ( ( rule__NullArrayExpression__ValuesAssignment_2_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5715:1: ( ( rule__NullArrayExpression__ValuesAssignment_2_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5715:1: ( ( rule__NullArrayExpression__ValuesAssignment_2_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5716:1: ( rule__NullArrayExpression__ValuesAssignment_2_1 )
            {
             before(grammarAccess.getNullArrayExpressionAccess().getValuesAssignment_2_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5717:1: ( rule__NullArrayExpression__ValuesAssignment_2_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5717:2: rule__NullArrayExpression__ValuesAssignment_2_1
            {
            pushFollow(FOLLOW_rule__NullArrayExpression__ValuesAssignment_2_1_in_rule__NullArrayExpression__Group_2__1__Impl11624);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5731:1: rule__DateArrayExpression__Group__0 : rule__DateArrayExpression__Group__0__Impl rule__DateArrayExpression__Group__1 ;
    public final void rule__DateArrayExpression__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5735:1: ( rule__DateArrayExpression__Group__0__Impl rule__DateArrayExpression__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5736:2: rule__DateArrayExpression__Group__0__Impl rule__DateArrayExpression__Group__1
            {
            pushFollow(FOLLOW_rule__DateArrayExpression__Group__0__Impl_in_rule__DateArrayExpression__Group__011658);
            rule__DateArrayExpression__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__DateArrayExpression__Group__1_in_rule__DateArrayExpression__Group__011661);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5743:1: rule__DateArrayExpression__Group__0__Impl : ( '[' ) ;
    public final void rule__DateArrayExpression__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5747:1: ( ( '[' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5748:1: ( '[' )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5748:1: ( '[' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5749:1: '['
            {
             before(grammarAccess.getDateArrayExpressionAccess().getLeftSquareBracketKeyword_0()); 
            match(input,38,FOLLOW_38_in_rule__DateArrayExpression__Group__0__Impl11689); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5762:1: rule__DateArrayExpression__Group__1 : rule__DateArrayExpression__Group__1__Impl rule__DateArrayExpression__Group__2 ;
    public final void rule__DateArrayExpression__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5766:1: ( rule__DateArrayExpression__Group__1__Impl rule__DateArrayExpression__Group__2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5767:2: rule__DateArrayExpression__Group__1__Impl rule__DateArrayExpression__Group__2
            {
            pushFollow(FOLLOW_rule__DateArrayExpression__Group__1__Impl_in_rule__DateArrayExpression__Group__111720);
            rule__DateArrayExpression__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__DateArrayExpression__Group__2_in_rule__DateArrayExpression__Group__111723);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5774:1: rule__DateArrayExpression__Group__1__Impl : ( ( rule__DateArrayExpression__ValuesAssignment_1 ) ) ;
    public final void rule__DateArrayExpression__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5778:1: ( ( ( rule__DateArrayExpression__ValuesAssignment_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5779:1: ( ( rule__DateArrayExpression__ValuesAssignment_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5779:1: ( ( rule__DateArrayExpression__ValuesAssignment_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5780:1: ( rule__DateArrayExpression__ValuesAssignment_1 )
            {
             before(grammarAccess.getDateArrayExpressionAccess().getValuesAssignment_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5781:1: ( rule__DateArrayExpression__ValuesAssignment_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5781:2: rule__DateArrayExpression__ValuesAssignment_1
            {
            pushFollow(FOLLOW_rule__DateArrayExpression__ValuesAssignment_1_in_rule__DateArrayExpression__Group__1__Impl11750);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5791:1: rule__DateArrayExpression__Group__2 : rule__DateArrayExpression__Group__2__Impl rule__DateArrayExpression__Group__3 ;
    public final void rule__DateArrayExpression__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5795:1: ( rule__DateArrayExpression__Group__2__Impl rule__DateArrayExpression__Group__3 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5796:2: rule__DateArrayExpression__Group__2__Impl rule__DateArrayExpression__Group__3
            {
            pushFollow(FOLLOW_rule__DateArrayExpression__Group__2__Impl_in_rule__DateArrayExpression__Group__211780);
            rule__DateArrayExpression__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__DateArrayExpression__Group__3_in_rule__DateArrayExpression__Group__211783);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5803:1: rule__DateArrayExpression__Group__2__Impl : ( ( rule__DateArrayExpression__Group_2__0 )* ) ;
    public final void rule__DateArrayExpression__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5807:1: ( ( ( rule__DateArrayExpression__Group_2__0 )* ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5808:1: ( ( rule__DateArrayExpression__Group_2__0 )* )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5808:1: ( ( rule__DateArrayExpression__Group_2__0 )* )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5809:1: ( rule__DateArrayExpression__Group_2__0 )*
            {
             before(grammarAccess.getDateArrayExpressionAccess().getGroup_2()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5810:1: ( rule__DateArrayExpression__Group_2__0 )*
            loop41:
            do {
                int alt41=2;
                int LA41_0 = input.LA(1);

                if ( (LA41_0==31) ) {
                    alt41=1;
                }


                switch (alt41) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5810:2: rule__DateArrayExpression__Group_2__0
            	    {
            	    pushFollow(FOLLOW_rule__DateArrayExpression__Group_2__0_in_rule__DateArrayExpression__Group__2__Impl11810);
            	    rule__DateArrayExpression__Group_2__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop41;
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5820:1: rule__DateArrayExpression__Group__3 : rule__DateArrayExpression__Group__3__Impl ;
    public final void rule__DateArrayExpression__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5824:1: ( rule__DateArrayExpression__Group__3__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5825:2: rule__DateArrayExpression__Group__3__Impl
            {
            pushFollow(FOLLOW_rule__DateArrayExpression__Group__3__Impl_in_rule__DateArrayExpression__Group__311841);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5831:1: rule__DateArrayExpression__Group__3__Impl : ( ']' ) ;
    public final void rule__DateArrayExpression__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5835:1: ( ( ']' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5836:1: ( ']' )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5836:1: ( ']' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5837:1: ']'
            {
             before(grammarAccess.getDateArrayExpressionAccess().getRightSquareBracketKeyword_3()); 
            match(input,39,FOLLOW_39_in_rule__DateArrayExpression__Group__3__Impl11869); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5858:1: rule__DateArrayExpression__Group_2__0 : rule__DateArrayExpression__Group_2__0__Impl rule__DateArrayExpression__Group_2__1 ;
    public final void rule__DateArrayExpression__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5862:1: ( rule__DateArrayExpression__Group_2__0__Impl rule__DateArrayExpression__Group_2__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5863:2: rule__DateArrayExpression__Group_2__0__Impl rule__DateArrayExpression__Group_2__1
            {
            pushFollow(FOLLOW_rule__DateArrayExpression__Group_2__0__Impl_in_rule__DateArrayExpression__Group_2__011908);
            rule__DateArrayExpression__Group_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__DateArrayExpression__Group_2__1_in_rule__DateArrayExpression__Group_2__011911);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5870:1: rule__DateArrayExpression__Group_2__0__Impl : ( ',' ) ;
    public final void rule__DateArrayExpression__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5874:1: ( ( ',' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5875:1: ( ',' )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5875:1: ( ',' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5876:1: ','
            {
             before(grammarAccess.getDateArrayExpressionAccess().getCommaKeyword_2_0()); 
            match(input,31,FOLLOW_31_in_rule__DateArrayExpression__Group_2__0__Impl11939); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5889:1: rule__DateArrayExpression__Group_2__1 : rule__DateArrayExpression__Group_2__1__Impl ;
    public final void rule__DateArrayExpression__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5893:1: ( rule__DateArrayExpression__Group_2__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5894:2: rule__DateArrayExpression__Group_2__1__Impl
            {
            pushFollow(FOLLOW_rule__DateArrayExpression__Group_2__1__Impl_in_rule__DateArrayExpression__Group_2__111970);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5900:1: rule__DateArrayExpression__Group_2__1__Impl : ( ( rule__DateArrayExpression__ValuesAssignment_2_1 ) ) ;
    public final void rule__DateArrayExpression__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5904:1: ( ( ( rule__DateArrayExpression__ValuesAssignment_2_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5905:1: ( ( rule__DateArrayExpression__ValuesAssignment_2_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5905:1: ( ( rule__DateArrayExpression__ValuesAssignment_2_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5906:1: ( rule__DateArrayExpression__ValuesAssignment_2_1 )
            {
             before(grammarAccess.getDateArrayExpressionAccess().getValuesAssignment_2_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5907:1: ( rule__DateArrayExpression__ValuesAssignment_2_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5907:2: rule__DateArrayExpression__ValuesAssignment_2_1
            {
            pushFollow(FOLLOW_rule__DateArrayExpression__ValuesAssignment_2_1_in_rule__DateArrayExpression__Group_2__1__Impl11997);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5921:1: rule__BooleanArrayExpression__Group__0 : rule__BooleanArrayExpression__Group__0__Impl rule__BooleanArrayExpression__Group__1 ;
    public final void rule__BooleanArrayExpression__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5925:1: ( rule__BooleanArrayExpression__Group__0__Impl rule__BooleanArrayExpression__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5926:2: rule__BooleanArrayExpression__Group__0__Impl rule__BooleanArrayExpression__Group__1
            {
            pushFollow(FOLLOW_rule__BooleanArrayExpression__Group__0__Impl_in_rule__BooleanArrayExpression__Group__012031);
            rule__BooleanArrayExpression__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__BooleanArrayExpression__Group__1_in_rule__BooleanArrayExpression__Group__012034);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5933:1: rule__BooleanArrayExpression__Group__0__Impl : ( '[' ) ;
    public final void rule__BooleanArrayExpression__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5937:1: ( ( '[' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5938:1: ( '[' )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5938:1: ( '[' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5939:1: '['
            {
             before(grammarAccess.getBooleanArrayExpressionAccess().getLeftSquareBracketKeyword_0()); 
            match(input,38,FOLLOW_38_in_rule__BooleanArrayExpression__Group__0__Impl12062); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5952:1: rule__BooleanArrayExpression__Group__1 : rule__BooleanArrayExpression__Group__1__Impl rule__BooleanArrayExpression__Group__2 ;
    public final void rule__BooleanArrayExpression__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5956:1: ( rule__BooleanArrayExpression__Group__1__Impl rule__BooleanArrayExpression__Group__2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5957:2: rule__BooleanArrayExpression__Group__1__Impl rule__BooleanArrayExpression__Group__2
            {
            pushFollow(FOLLOW_rule__BooleanArrayExpression__Group__1__Impl_in_rule__BooleanArrayExpression__Group__112093);
            rule__BooleanArrayExpression__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__BooleanArrayExpression__Group__2_in_rule__BooleanArrayExpression__Group__112096);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5964:1: rule__BooleanArrayExpression__Group__1__Impl : ( ( rule__BooleanArrayExpression__ValuesAssignment_1 ) ) ;
    public final void rule__BooleanArrayExpression__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5968:1: ( ( ( rule__BooleanArrayExpression__ValuesAssignment_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5969:1: ( ( rule__BooleanArrayExpression__ValuesAssignment_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5969:1: ( ( rule__BooleanArrayExpression__ValuesAssignment_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5970:1: ( rule__BooleanArrayExpression__ValuesAssignment_1 )
            {
             before(grammarAccess.getBooleanArrayExpressionAccess().getValuesAssignment_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5971:1: ( rule__BooleanArrayExpression__ValuesAssignment_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5971:2: rule__BooleanArrayExpression__ValuesAssignment_1
            {
            pushFollow(FOLLOW_rule__BooleanArrayExpression__ValuesAssignment_1_in_rule__BooleanArrayExpression__Group__1__Impl12123);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5981:1: rule__BooleanArrayExpression__Group__2 : rule__BooleanArrayExpression__Group__2__Impl rule__BooleanArrayExpression__Group__3 ;
    public final void rule__BooleanArrayExpression__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5985:1: ( rule__BooleanArrayExpression__Group__2__Impl rule__BooleanArrayExpression__Group__3 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5986:2: rule__BooleanArrayExpression__Group__2__Impl rule__BooleanArrayExpression__Group__3
            {
            pushFollow(FOLLOW_rule__BooleanArrayExpression__Group__2__Impl_in_rule__BooleanArrayExpression__Group__212153);
            rule__BooleanArrayExpression__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__BooleanArrayExpression__Group__3_in_rule__BooleanArrayExpression__Group__212156);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5993:1: rule__BooleanArrayExpression__Group__2__Impl : ( ( rule__BooleanArrayExpression__Group_2__0 )* ) ;
    public final void rule__BooleanArrayExpression__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5997:1: ( ( ( rule__BooleanArrayExpression__Group_2__0 )* ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5998:1: ( ( rule__BooleanArrayExpression__Group_2__0 )* )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5998:1: ( ( rule__BooleanArrayExpression__Group_2__0 )* )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:5999:1: ( rule__BooleanArrayExpression__Group_2__0 )*
            {
             before(grammarAccess.getBooleanArrayExpressionAccess().getGroup_2()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6000:1: ( rule__BooleanArrayExpression__Group_2__0 )*
            loop42:
            do {
                int alt42=2;
                int LA42_0 = input.LA(1);

                if ( (LA42_0==31) ) {
                    alt42=1;
                }


                switch (alt42) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6000:2: rule__BooleanArrayExpression__Group_2__0
            	    {
            	    pushFollow(FOLLOW_rule__BooleanArrayExpression__Group_2__0_in_rule__BooleanArrayExpression__Group__2__Impl12183);
            	    rule__BooleanArrayExpression__Group_2__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop42;
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6010:1: rule__BooleanArrayExpression__Group__3 : rule__BooleanArrayExpression__Group__3__Impl ;
    public final void rule__BooleanArrayExpression__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6014:1: ( rule__BooleanArrayExpression__Group__3__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6015:2: rule__BooleanArrayExpression__Group__3__Impl
            {
            pushFollow(FOLLOW_rule__BooleanArrayExpression__Group__3__Impl_in_rule__BooleanArrayExpression__Group__312214);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6021:1: rule__BooleanArrayExpression__Group__3__Impl : ( ']' ) ;
    public final void rule__BooleanArrayExpression__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6025:1: ( ( ']' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6026:1: ( ']' )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6026:1: ( ']' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6027:1: ']'
            {
             before(grammarAccess.getBooleanArrayExpressionAccess().getRightSquareBracketKeyword_3()); 
            match(input,39,FOLLOW_39_in_rule__BooleanArrayExpression__Group__3__Impl12242); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6048:1: rule__BooleanArrayExpression__Group_2__0 : rule__BooleanArrayExpression__Group_2__0__Impl rule__BooleanArrayExpression__Group_2__1 ;
    public final void rule__BooleanArrayExpression__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6052:1: ( rule__BooleanArrayExpression__Group_2__0__Impl rule__BooleanArrayExpression__Group_2__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6053:2: rule__BooleanArrayExpression__Group_2__0__Impl rule__BooleanArrayExpression__Group_2__1
            {
            pushFollow(FOLLOW_rule__BooleanArrayExpression__Group_2__0__Impl_in_rule__BooleanArrayExpression__Group_2__012281);
            rule__BooleanArrayExpression__Group_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__BooleanArrayExpression__Group_2__1_in_rule__BooleanArrayExpression__Group_2__012284);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6060:1: rule__BooleanArrayExpression__Group_2__0__Impl : ( ',' ) ;
    public final void rule__BooleanArrayExpression__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6064:1: ( ( ',' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6065:1: ( ',' )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6065:1: ( ',' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6066:1: ','
            {
             before(grammarAccess.getBooleanArrayExpressionAccess().getCommaKeyword_2_0()); 
            match(input,31,FOLLOW_31_in_rule__BooleanArrayExpression__Group_2__0__Impl12312); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6079:1: rule__BooleanArrayExpression__Group_2__1 : rule__BooleanArrayExpression__Group_2__1__Impl ;
    public final void rule__BooleanArrayExpression__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6083:1: ( rule__BooleanArrayExpression__Group_2__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6084:2: rule__BooleanArrayExpression__Group_2__1__Impl
            {
            pushFollow(FOLLOW_rule__BooleanArrayExpression__Group_2__1__Impl_in_rule__BooleanArrayExpression__Group_2__112343);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6090:1: rule__BooleanArrayExpression__Group_2__1__Impl : ( ( rule__BooleanArrayExpression__ValuesAssignment_2_1 ) ) ;
    public final void rule__BooleanArrayExpression__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6094:1: ( ( ( rule__BooleanArrayExpression__ValuesAssignment_2_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6095:1: ( ( rule__BooleanArrayExpression__ValuesAssignment_2_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6095:1: ( ( rule__BooleanArrayExpression__ValuesAssignment_2_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6096:1: ( rule__BooleanArrayExpression__ValuesAssignment_2_1 )
            {
             before(grammarAccess.getBooleanArrayExpressionAccess().getValuesAssignment_2_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6097:1: ( rule__BooleanArrayExpression__ValuesAssignment_2_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6097:2: rule__BooleanArrayExpression__ValuesAssignment_2_1
            {
            pushFollow(FOLLOW_rule__BooleanArrayExpression__ValuesAssignment_2_1_in_rule__BooleanArrayExpression__Group_2__1__Impl12370);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6112:1: rule__Model__ColAssignment_1 : ( ruleColumns ) ;
    public final void rule__Model__ColAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6116:1: ( ( ruleColumns ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6117:1: ( ruleColumns )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6117:1: ( ruleColumns )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6118:1: ruleColumns
            {
             before(grammarAccess.getModelAccess().getColColumnsParserRuleCall_1_0()); 
            pushFollow(FOLLOW_ruleColumns_in_rule__Model__ColAssignment_112409);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6127:1: rule__Model__TblAssignment_3 : ( ruleTables ) ;
    public final void rule__Model__TblAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6131:1: ( ( ruleTables ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6132:1: ( ruleTables )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6132:1: ( ruleTables )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6133:1: ruleTables
            {
             before(grammarAccess.getModelAccess().getTblTablesParserRuleCall_3_0()); 
            pushFollow(FOLLOW_ruleTables_in_rule__Model__TblAssignment_312440);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6142:1: rule__Model__WhereEntryAssignment_4_1 : ( ruleWhereEntry ) ;
    public final void rule__Model__WhereEntryAssignment_4_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6146:1: ( ( ruleWhereEntry ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6147:1: ( ruleWhereEntry )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6147:1: ( ruleWhereEntry )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6148:1: ruleWhereEntry
            {
             before(grammarAccess.getModelAccess().getWhereEntryWhereEntryParserRuleCall_4_1_0()); 
            pushFollow(FOLLOW_ruleWhereEntry_in_rule__Model__WhereEntryAssignment_4_112471);
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


    // $ANTLR start "rule__Model__GroupByEntryAssignment_5_1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6157:1: rule__Model__GroupByEntryAssignment_5_1 : ( ruleGroupByColumns ) ;
    public final void rule__Model__GroupByEntryAssignment_5_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6161:1: ( ( ruleGroupByColumns ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6162:1: ( ruleGroupByColumns )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6162:1: ( ruleGroupByColumns )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6163:1: ruleGroupByColumns
            {
             before(grammarAccess.getModelAccess().getGroupByEntryGroupByColumnsParserRuleCall_5_1_0()); 
            pushFollow(FOLLOW_ruleGroupByColumns_in_rule__Model__GroupByEntryAssignment_5_112502);
            ruleGroupByColumns();

            state._fsp--;

             after(grammarAccess.getModelAccess().getGroupByEntryGroupByColumnsParserRuleCall_5_1_0()); 

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
    // $ANTLR end "rule__Model__GroupByEntryAssignment_5_1"


    // $ANTLR start "rule__Model__HavingEntryAssignment_6_1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6172:1: rule__Model__HavingEntryAssignment_6_1 : ( ruleHavingEntry ) ;
    public final void rule__Model__HavingEntryAssignment_6_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6176:1: ( ( ruleHavingEntry ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6177:1: ( ruleHavingEntry )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6177:1: ( ruleHavingEntry )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6178:1: ruleHavingEntry
            {
             before(grammarAccess.getModelAccess().getHavingEntryHavingEntryParserRuleCall_6_1_0()); 
            pushFollow(FOLLOW_ruleHavingEntry_in_rule__Model__HavingEntryAssignment_6_112533);
            ruleHavingEntry();

            state._fsp--;

             after(grammarAccess.getModelAccess().getHavingEntryHavingEntryParserRuleCall_6_1_0()); 

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
    // $ANTLR end "rule__Model__HavingEntryAssignment_6_1"


    // $ANTLR start "rule__Model__OrderByEntryAssignment_7_1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6187:1: rule__Model__OrderByEntryAssignment_7_1 : ( ruleOrderByColumns ) ;
    public final void rule__Model__OrderByEntryAssignment_7_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6191:1: ( ( ruleOrderByColumns ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6192:1: ( ruleOrderByColumns )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6192:1: ( ruleOrderByColumns )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6193:1: ruleOrderByColumns
            {
             before(grammarAccess.getModelAccess().getOrderByEntryOrderByColumnsParserRuleCall_7_1_0()); 
            pushFollow(FOLLOW_ruleOrderByColumns_in_rule__Model__OrderByEntryAssignment_7_112564);
            ruleOrderByColumns();

            state._fsp--;

             after(grammarAccess.getModelAccess().getOrderByEntryOrderByColumnsParserRuleCall_7_1_0()); 

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
    // $ANTLR end "rule__Model__OrderByEntryAssignment_7_1"


    // $ANTLR start "rule__OrderByColumns__EntriesAssignment_1_1_1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6202:1: rule__OrderByColumns__EntriesAssignment_1_1_1 : ( ruleOrderByColumnFull ) ;
    public final void rule__OrderByColumns__EntriesAssignment_1_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6206:1: ( ( ruleOrderByColumnFull ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6207:1: ( ruleOrderByColumnFull )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6207:1: ( ruleOrderByColumnFull )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6208:1: ruleOrderByColumnFull
            {
             before(grammarAccess.getOrderByColumnsAccess().getEntriesOrderByColumnFullParserRuleCall_1_1_1_0()); 
            pushFollow(FOLLOW_ruleOrderByColumnFull_in_rule__OrderByColumns__EntriesAssignment_1_1_112595);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6217:1: rule__OrderByColumnFull__ColOrderAssignment_0 : ( ruleColumn ) ;
    public final void rule__OrderByColumnFull__ColOrderAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6221:1: ( ( ruleColumn ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6222:1: ( ruleColumn )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6222:1: ( ruleColumn )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6223:1: ruleColumn
            {
             before(grammarAccess.getOrderByColumnFullAccess().getColOrderColumnParserRuleCall_0_0()); 
            pushFollow(FOLLOW_ruleColumn_in_rule__OrderByColumnFull__ColOrderAssignment_012626);
            ruleColumn();

            state._fsp--;

             after(grammarAccess.getOrderByColumnFullAccess().getColOrderColumnParserRuleCall_0_0()); 

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


    // $ANTLR start "rule__OrderByColumnFull__ColOrderAssignment_1_2"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6232:1: rule__OrderByColumnFull__ColOrderAssignment_1_2 : ( ruleColumn ) ;
    public final void rule__OrderByColumnFull__ColOrderAssignment_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6236:1: ( ( ruleColumn ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6237:1: ( ruleColumn )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6237:1: ( ruleColumn )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6238:1: ruleColumn
            {
             before(grammarAccess.getOrderByColumnFullAccess().getColOrderColumnParserRuleCall_1_2_0()); 
            pushFollow(FOLLOW_ruleColumn_in_rule__OrderByColumnFull__ColOrderAssignment_1_212657);
            ruleColumn();

            state._fsp--;

             after(grammarAccess.getOrderByColumnFullAccess().getColOrderColumnParserRuleCall_1_2_0()); 

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
    // $ANTLR end "rule__OrderByColumnFull__ColOrderAssignment_1_2"


    // $ANTLR start "rule__GroupByColumns__EntriesAssignment_1_1_1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6247:1: rule__GroupByColumns__EntriesAssignment_1_1_1 : ( ruleGroupByColumnFull ) ;
    public final void rule__GroupByColumns__EntriesAssignment_1_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6251:1: ( ( ruleGroupByColumnFull ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6252:1: ( ruleGroupByColumnFull )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6252:1: ( ruleGroupByColumnFull )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6253:1: ruleGroupByColumnFull
            {
             before(grammarAccess.getGroupByColumnsAccess().getEntriesGroupByColumnFullParserRuleCall_1_1_1_0()); 
            pushFollow(FOLLOW_ruleGroupByColumnFull_in_rule__GroupByColumns__EntriesAssignment_1_1_112688);
            ruleGroupByColumnFull();

            state._fsp--;

             after(grammarAccess.getGroupByColumnsAccess().getEntriesGroupByColumnFullParserRuleCall_1_1_1_0()); 

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
    // $ANTLR end "rule__GroupByColumns__EntriesAssignment_1_1_1"


    // $ANTLR start "rule__GroupByColumnFull__GroupByColumnAssignment_0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6262:1: rule__GroupByColumnFull__GroupByColumnAssignment_0 : ( ruleColumn ) ;
    public final void rule__GroupByColumnFull__GroupByColumnAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6266:1: ( ( ruleColumn ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6267:1: ( ruleColumn )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6267:1: ( ruleColumn )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6268:1: ruleColumn
            {
             before(grammarAccess.getGroupByColumnFullAccess().getGroupByColumnColumnParserRuleCall_0_0()); 
            pushFollow(FOLLOW_ruleColumn_in_rule__GroupByColumnFull__GroupByColumnAssignment_012719);
            ruleColumn();

            state._fsp--;

             after(grammarAccess.getGroupByColumnFullAccess().getGroupByColumnColumnParserRuleCall_0_0()); 

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
    // $ANTLR end "rule__GroupByColumnFull__GroupByColumnAssignment_0"


    // $ANTLR start "rule__GroupByColumnFull__GroupByColumnAssignment_1_2"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6277:1: rule__GroupByColumnFull__GroupByColumnAssignment_1_2 : ( ruleColumn ) ;
    public final void rule__GroupByColumnFull__GroupByColumnAssignment_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6281:1: ( ( ruleColumn ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6282:1: ( ruleColumn )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6282:1: ( ruleColumn )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6283:1: ruleColumn
            {
             before(grammarAccess.getGroupByColumnFullAccess().getGroupByColumnColumnParserRuleCall_1_2_0()); 
            pushFollow(FOLLOW_ruleColumn_in_rule__GroupByColumnFull__GroupByColumnAssignment_1_212750);
            ruleColumn();

            state._fsp--;

             after(grammarAccess.getGroupByColumnFullAccess().getGroupByColumnColumnParserRuleCall_1_2_0()); 

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
    // $ANTLR end "rule__GroupByColumnFull__GroupByColumnAssignment_1_2"


    // $ANTLR start "rule__Columns__EntriesAssignment_1_1_1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6292:1: rule__Columns__EntriesAssignment_1_1_1 : ( ruleColumnOrAlias ) ;
    public final void rule__Columns__EntriesAssignment_1_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6296:1: ( ( ruleColumnOrAlias ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6297:1: ( ruleColumnOrAlias )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6297:1: ( ruleColumnOrAlias )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6298:1: ruleColumnOrAlias
            {
             before(grammarAccess.getColumnsAccess().getEntriesColumnOrAliasParserRuleCall_1_1_1_0()); 
            pushFollow(FOLLOW_ruleColumnOrAlias_in_rule__Columns__EntriesAssignment_1_1_112781);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6307:1: rule__ColumnOrAlias__ColAliasAssignment_1_2 : ( ruleColumnAlias ) ;
    public final void rule__ColumnOrAlias__ColAliasAssignment_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6311:1: ( ( ruleColumnAlias ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6312:1: ( ruleColumnAlias )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6312:1: ( ruleColumnAlias )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6313:1: ruleColumnAlias
            {
             before(grammarAccess.getColumnOrAliasAccess().getColAliasColumnAliasParserRuleCall_1_2_0()); 
            pushFollow(FOLLOW_ruleColumnAlias_in_rule__ColumnOrAlias__ColAliasAssignment_1_212812);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6322:1: rule__ColumnOrAlias__ColAliasAssignment_2_1 : ( ruleColumnAlias ) ;
    public final void rule__ColumnOrAlias__ColAliasAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6326:1: ( ( ruleColumnAlias ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6327:1: ( ruleColumnAlias )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6327:1: ( ruleColumnAlias )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6328:1: ruleColumnAlias
            {
             before(grammarAccess.getColumnOrAliasAccess().getColAliasColumnAliasParserRuleCall_2_1_0()); 
            pushFollow(FOLLOW_ruleColumnAlias_in_rule__ColumnOrAlias__ColAliasAssignment_2_112843);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6337:1: rule__ColumnFull__ColNameAssignment_0 : ( ruleColumn ) ;
    public final void rule__ColumnFull__ColNameAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6341:1: ( ( ruleColumn ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6342:1: ( ruleColumn )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6342:1: ( ruleColumn )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6343:1: ruleColumn
            {
             before(grammarAccess.getColumnFullAccess().getColNameColumnParserRuleCall_0_0()); 
            pushFollow(FOLLOW_ruleColumn_in_rule__ColumnFull__ColNameAssignment_012874);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6352:1: rule__ColumnFull__ColNameAssignment_1_2 : ( ruleColumn ) ;
    public final void rule__ColumnFull__ColNameAssignment_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6356:1: ( ( ruleColumn ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6357:1: ( ruleColumn )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6357:1: ( ruleColumn )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6358:1: ruleColumn
            {
             before(grammarAccess.getColumnFullAccess().getColNameColumnParserRuleCall_1_2_0()); 
            pushFollow(FOLLOW_ruleColumn_in_rule__ColumnFull__ColNameAssignment_1_212905);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6367:1: rule__ColumnAlias__ColAliasAssignment : ( RULE_ID ) ;
    public final void rule__ColumnAlias__ColAliasAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6371:1: ( ( RULE_ID ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6372:1: ( RULE_ID )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6372:1: ( RULE_ID )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6373:1: RULE_ID
            {
             before(grammarAccess.getColumnAliasAccess().getColAliasIDTerminalRuleCall_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__ColumnAlias__ColAliasAssignment12936); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6382:1: rule__Column__ColNameAssignment : ( RULE_ID ) ;
    public final void rule__Column__ColNameAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6386:1: ( ( RULE_ID ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6387:1: ( RULE_ID )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6387:1: ( RULE_ID )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6388:1: RULE_ID
            {
             before(grammarAccess.getColumnAccess().getColNameIDTerminalRuleCall_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__Column__ColNameAssignment12967); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6397:1: rule__Tables__EntriesAssignment_1_1_1 : ( ruleTableOrAlias ) ;
    public final void rule__Tables__EntriesAssignment_1_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6401:1: ( ( ruleTableOrAlias ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6402:1: ( ruleTableOrAlias )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6402:1: ( ruleTableOrAlias )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6403:1: ruleTableOrAlias
            {
             before(grammarAccess.getTablesAccess().getEntriesTableOrAliasParserRuleCall_1_1_1_0()); 
            pushFollow(FOLLOW_ruleTableOrAlias_in_rule__Tables__EntriesAssignment_1_1_112998);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6412:1: rule__TableOrAlias__TblAliasAssignment_1_2 : ( ruleTableAlias ) ;
    public final void rule__TableOrAlias__TblAliasAssignment_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6416:1: ( ( ruleTableAlias ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6417:1: ( ruleTableAlias )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6417:1: ( ruleTableAlias )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6418:1: ruleTableAlias
            {
             before(grammarAccess.getTableOrAliasAccess().getTblAliasTableAliasParserRuleCall_1_2_0()); 
            pushFollow(FOLLOW_ruleTableAlias_in_rule__TableOrAlias__TblAliasAssignment_1_213029);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6427:1: rule__TableOrAlias__TblAliasAssignment_2_1 : ( ruleTableAlias ) ;
    public final void rule__TableOrAlias__TblAliasAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6431:1: ( ( ruleTableAlias ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6432:1: ( ruleTableAlias )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6432:1: ( ruleTableAlias )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6433:1: ruleTableAlias
            {
             before(grammarAccess.getTableOrAliasAccess().getTblAliasTableAliasParserRuleCall_2_1_0()); 
            pushFollow(FOLLOW_ruleTableAlias_in_rule__TableOrAlias__TblAliasAssignment_2_113060);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6442:1: rule__TableFull__TblAssignment_0_2 : ( ruleTable ) ;
    public final void rule__TableFull__TblAssignment_0_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6446:1: ( ( ruleTable ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6447:1: ( ruleTable )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6447:1: ( ruleTable )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6448:1: ruleTable
            {
             before(grammarAccess.getTableFullAccess().getTblTableParserRuleCall_0_2_0()); 
            pushFollow(FOLLOW_ruleTable_in_rule__TableFull__TblAssignment_0_213091);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6457:1: rule__TableFull__TblAssignment_1 : ( ruleTable ) ;
    public final void rule__TableFull__TblAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6461:1: ( ( ruleTable ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6462:1: ( ruleTable )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6462:1: ( ruleTable )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6463:1: ruleTable
            {
             before(grammarAccess.getTableFullAccess().getTblTableParserRuleCall_1_0()); 
            pushFollow(FOLLOW_ruleTable_in_rule__TableFull__TblAssignment_113122);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6472:1: rule__Table__TblAssignment : ( RULE_ID ) ;
    public final void rule__Table__TblAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6476:1: ( ( RULE_ID ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6477:1: ( RULE_ID )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6477:1: ( RULE_ID )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6478:1: RULE_ID
            {
             before(grammarAccess.getTableAccess().getTblIDTerminalRuleCall_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__Table__TblAssignment13153); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6487:1: rule__TableAlias__TblAliasAssignment : ( RULE_ID ) ;
    public final void rule__TableAlias__TblAliasAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6491:1: ( ( RULE_ID ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6492:1: ( RULE_ID )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6492:1: ( RULE_ID )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6493:1: RULE_ID
            {
             before(grammarAccess.getTableAliasAccess().getTblAliasIDTerminalRuleCall_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__TableAlias__TblAliasAssignment13184); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6502:1: rule__Schema__SchemAssignment_0_2 : ( RULE_ID ) ;
    public final void rule__Schema__SchemAssignment_0_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6506:1: ( ( RULE_ID ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6507:1: ( RULE_ID )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6507:1: ( RULE_ID )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6508:1: RULE_ID
            {
             before(grammarAccess.getSchemaAccess().getSchemIDTerminalRuleCall_0_2_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__Schema__SchemAssignment_0_213215); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6517:1: rule__Schema__SchemAssignment_1 : ( RULE_ID ) ;
    public final void rule__Schema__SchemAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6521:1: ( ( RULE_ID ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6522:1: ( RULE_ID )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6522:1: ( RULE_ID )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6523:1: RULE_ID
            {
             before(grammarAccess.getSchemaAccess().getSchemIDTerminalRuleCall_1_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__Schema__SchemAssignment_113246); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6532:1: rule__Database__DbNameAssignment : ( RULE_ID ) ;
    public final void rule__Database__DbNameAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6536:1: ( ( RULE_ID ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6537:1: ( RULE_ID )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6537:1: ( RULE_ID )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6538:1: RULE_ID
            {
             before(grammarAccess.getDatabaseAccess().getDbNameIDTerminalRuleCall_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__Database__DbNameAssignment13277); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6547:1: rule__WhereEntry__EntriesAssignment_1_1_1 : ( ruleAndWhereEntry ) ;
    public final void rule__WhereEntry__EntriesAssignment_1_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6551:1: ( ( ruleAndWhereEntry ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6552:1: ( ruleAndWhereEntry )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6552:1: ( ruleAndWhereEntry )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6553:1: ruleAndWhereEntry
            {
             before(grammarAccess.getWhereEntryAccess().getEntriesAndWhereEntryParserRuleCall_1_1_1_0()); 
            pushFollow(FOLLOW_ruleAndWhereEntry_in_rule__WhereEntry__EntriesAssignment_1_1_113308);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6562:1: rule__AndWhereEntry__EntriesAssignment_1_1_1 : ( ruleConcreteWhereEntry ) ;
    public final void rule__AndWhereEntry__EntriesAssignment_1_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6566:1: ( ( ruleConcreteWhereEntry ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6567:1: ( ruleConcreteWhereEntry )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6567:1: ( ruleConcreteWhereEntry )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6568:1: ruleConcreteWhereEntry
            {
             before(grammarAccess.getAndWhereEntryAccess().getEntriesConcreteWhereEntryParserRuleCall_1_1_1_0()); 
            pushFollow(FOLLOW_ruleConcreteWhereEntry_in_rule__AndWhereEntry__EntriesAssignment_1_1_113339);
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


    // $ANTLR start "rule__HavingEntry__EntriesAssignment_1_1_1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6577:1: rule__HavingEntry__EntriesAssignment_1_1_1 : ( ruleAndHavingEntry ) ;
    public final void rule__HavingEntry__EntriesAssignment_1_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6581:1: ( ( ruleAndHavingEntry ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6582:1: ( ruleAndHavingEntry )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6582:1: ( ruleAndHavingEntry )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6583:1: ruleAndHavingEntry
            {
             before(grammarAccess.getHavingEntryAccess().getEntriesAndHavingEntryParserRuleCall_1_1_1_0()); 
            pushFollow(FOLLOW_ruleAndHavingEntry_in_rule__HavingEntry__EntriesAssignment_1_1_113370);
            ruleAndHavingEntry();

            state._fsp--;

             after(grammarAccess.getHavingEntryAccess().getEntriesAndHavingEntryParserRuleCall_1_1_1_0()); 

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
    // $ANTLR end "rule__HavingEntry__EntriesAssignment_1_1_1"


    // $ANTLR start "rule__AndHavingEntry__EntriesAssignment_1_1_1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6592:1: rule__AndHavingEntry__EntriesAssignment_1_1_1 : ( ruleConcreteHavingEntry ) ;
    public final void rule__AndHavingEntry__EntriesAssignment_1_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6596:1: ( ( ruleConcreteHavingEntry ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6597:1: ( ruleConcreteHavingEntry )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6597:1: ( ruleConcreteHavingEntry )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6598:1: ruleConcreteHavingEntry
            {
             before(grammarAccess.getAndHavingEntryAccess().getEntriesConcreteHavingEntryParserRuleCall_1_1_1_0()); 
            pushFollow(FOLLOW_ruleConcreteHavingEntry_in_rule__AndHavingEntry__EntriesAssignment_1_1_113401);
            ruleConcreteHavingEntry();

            state._fsp--;

             after(grammarAccess.getAndHavingEntryAccess().getEntriesConcreteHavingEntryParserRuleCall_1_1_1_0()); 

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
    // $ANTLR end "rule__AndHavingEntry__EntriesAssignment_1_1_1"


    // $ANTLR start "rule__SingleExpressionWhereEntry__NameAssignment_0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6607:1: rule__SingleExpressionWhereEntry__NameAssignment_0 : ( RULE_ID ) ;
    public final void rule__SingleExpressionWhereEntry__NameAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6611:1: ( ( RULE_ID ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6612:1: ( RULE_ID )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6612:1: ( RULE_ID )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6613:1: RULE_ID
            {
             before(grammarAccess.getSingleExpressionWhereEntryAccess().getNameIDTerminalRuleCall_0_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__SingleExpressionWhereEntry__NameAssignment_013432); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6622:1: rule__SingleExpressionWhereEntry__OperatorAssignment_1 : ( ruleOperator ) ;
    public final void rule__SingleExpressionWhereEntry__OperatorAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6626:1: ( ( ruleOperator ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6627:1: ( ruleOperator )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6627:1: ( ruleOperator )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6628:1: ruleOperator
            {
             before(grammarAccess.getSingleExpressionWhereEntryAccess().getOperatorOperatorEnumRuleCall_1_0()); 
            pushFollow(FOLLOW_ruleOperator_in_rule__SingleExpressionWhereEntry__OperatorAssignment_113463);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6637:1: rule__SingleExpressionWhereEntry__RhsAssignment_2 : ( ruleExpression ) ;
    public final void rule__SingleExpressionWhereEntry__RhsAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6641:1: ( ( ruleExpression ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6642:1: ( ruleExpression )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6642:1: ( ruleExpression )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6643:1: ruleExpression
            {
             before(grammarAccess.getSingleExpressionWhereEntryAccess().getRhsExpressionParserRuleCall_2_0()); 
            pushFollow(FOLLOW_ruleExpression_in_rule__SingleExpressionWhereEntry__RhsAssignment_213494);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6652:1: rule__ReplacableValue__ValueAssignment : ( ( '?' ) ) ;
    public final void rule__ReplacableValue__ValueAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6656:1: ( ( ( '?' ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6657:1: ( ( '?' ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6657:1: ( ( '?' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6658:1: ( '?' )
            {
             before(grammarAccess.getReplacableValueAccess().getValueQuestionMarkKeyword_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6659:1: ( '?' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6660:1: '?'
            {
             before(grammarAccess.getReplacableValueAccess().getValueQuestionMarkKeyword_0()); 
            match(input,40,FOLLOW_40_in_rule__ReplacableValue__ValueAssignment13530); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6675:1: rule__DoubleExpression__ValueAssignment : ( RULE_SIGNED_DOUBLE ) ;
    public final void rule__DoubleExpression__ValueAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6679:1: ( ( RULE_SIGNED_DOUBLE ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6680:1: ( RULE_SIGNED_DOUBLE )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6680:1: ( RULE_SIGNED_DOUBLE )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6681:1: RULE_SIGNED_DOUBLE
            {
             before(grammarAccess.getDoubleExpressionAccess().getValueSIGNED_DOUBLETerminalRuleCall_0()); 
            match(input,RULE_SIGNED_DOUBLE,FOLLOW_RULE_SIGNED_DOUBLE_in_rule__DoubleExpression__ValueAssignment13569); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6690:1: rule__LongExpression__ValueAssignment : ( RULE_SINGED_LONG ) ;
    public final void rule__LongExpression__ValueAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6694:1: ( ( RULE_SINGED_LONG ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6695:1: ( RULE_SINGED_LONG )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6695:1: ( RULE_SINGED_LONG )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6696:1: RULE_SINGED_LONG
            {
             before(grammarAccess.getLongExpressionAccess().getValueSINGED_LONGTerminalRuleCall_0()); 
            match(input,RULE_SINGED_LONG,FOLLOW_RULE_SINGED_LONG_in_rule__LongExpression__ValueAssignment13600); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6705:1: rule__StringExpression__ValueAssignment : ( RULE_STRING ) ;
    public final void rule__StringExpression__ValueAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6709:1: ( ( RULE_STRING ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6710:1: ( RULE_STRING )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6710:1: ( RULE_STRING )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6711:1: RULE_STRING
            {
             before(grammarAccess.getStringExpressionAccess().getValueSTRINGTerminalRuleCall_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__StringExpression__ValueAssignment13631); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6720:1: rule__NullExpression__ValueAssignment : ( ( 'null' ) ) ;
    public final void rule__NullExpression__ValueAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6724:1: ( ( ( 'null' ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6725:1: ( ( 'null' ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6725:1: ( ( 'null' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6726:1: ( 'null' )
            {
             before(grammarAccess.getNullExpressionAccess().getValueNullKeyword_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6727:1: ( 'null' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6728:1: 'null'
            {
             before(grammarAccess.getNullExpressionAccess().getValueNullKeyword_0()); 
            match(input,41,FOLLOW_41_in_rule__NullExpression__ValueAssignment13667); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6743:1: rule__DateExpression__ValueAssignment : ( RULE_DATE ) ;
    public final void rule__DateExpression__ValueAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6747:1: ( ( RULE_DATE ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6748:1: ( RULE_DATE )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6748:1: ( RULE_DATE )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6749:1: RULE_DATE
            {
             before(grammarAccess.getDateExpressionAccess().getValueDATETerminalRuleCall_0()); 
            match(input,RULE_DATE,FOLLOW_RULE_DATE_in_rule__DateExpression__ValueAssignment13706); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6758:1: rule__BooleanExpression__TrueAssignment_0 : ( ( 'true' ) ) ;
    public final void rule__BooleanExpression__TrueAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6762:1: ( ( ( 'true' ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6763:1: ( ( 'true' ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6763:1: ( ( 'true' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6764:1: ( 'true' )
            {
             before(grammarAccess.getBooleanExpressionAccess().getTrueTrueKeyword_0_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6765:1: ( 'true' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6766:1: 'true'
            {
             before(grammarAccess.getBooleanExpressionAccess().getTrueTrueKeyword_0_0()); 
            match(input,42,FOLLOW_42_in_rule__BooleanExpression__TrueAssignment_013742); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6781:1: rule__BooleanExpression__TrueAssignment_1 : ( ( 'false' ) ) ;
    public final void rule__BooleanExpression__TrueAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6785:1: ( ( ( 'false' ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6786:1: ( ( 'false' ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6786:1: ( ( 'false' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6787:1: ( 'false' )
            {
             before(grammarAccess.getBooleanExpressionAccess().getTrueFalseKeyword_1_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6788:1: ( 'false' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6789:1: 'false'
            {
             before(grammarAccess.getBooleanExpressionAccess().getTrueFalseKeyword_1_0()); 
            match(input,43,FOLLOW_43_in_rule__BooleanExpression__TrueAssignment_113786); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6804:1: rule__MultiExpressionWhereEntry__NameAssignment_0 : ( RULE_ID ) ;
    public final void rule__MultiExpressionWhereEntry__NameAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6808:1: ( ( RULE_ID ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6809:1: ( RULE_ID )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6809:1: ( RULE_ID )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6810:1: RULE_ID
            {
             before(grammarAccess.getMultiExpressionWhereEntryAccess().getNameIDTerminalRuleCall_0_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__MultiExpressionWhereEntry__NameAssignment_013825); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6819:1: rule__MultiExpressionWhereEntry__OperatorAssignment_1 : ( ruleArrayOperator ) ;
    public final void rule__MultiExpressionWhereEntry__OperatorAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6823:1: ( ( ruleArrayOperator ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6824:1: ( ruleArrayOperator )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6824:1: ( ruleArrayOperator )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6825:1: ruleArrayOperator
            {
             before(grammarAccess.getMultiExpressionWhereEntryAccess().getOperatorArrayOperatorEnumRuleCall_1_0()); 
            pushFollow(FOLLOW_ruleArrayOperator_in_rule__MultiExpressionWhereEntry__OperatorAssignment_113856);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6834:1: rule__MultiExpressionWhereEntry__RhsAssignment_2 : ( ruleArrayExpression ) ;
    public final void rule__MultiExpressionWhereEntry__RhsAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6838:1: ( ( ruleArrayExpression ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6839:1: ( ruleArrayExpression )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6839:1: ( ruleArrayExpression )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6840:1: ruleArrayExpression
            {
             before(grammarAccess.getMultiExpressionWhereEntryAccess().getRhsArrayExpressionParserRuleCall_2_0()); 
            pushFollow(FOLLOW_ruleArrayExpression_in_rule__MultiExpressionWhereEntry__RhsAssignment_213887);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6849:1: rule__DoubleArrayExpression__ValuesAssignment_1 : ( RULE_SIGNED_DOUBLE ) ;
    public final void rule__DoubleArrayExpression__ValuesAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6853:1: ( ( RULE_SIGNED_DOUBLE ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6854:1: ( RULE_SIGNED_DOUBLE )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6854:1: ( RULE_SIGNED_DOUBLE )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6855:1: RULE_SIGNED_DOUBLE
            {
             before(grammarAccess.getDoubleArrayExpressionAccess().getValuesSIGNED_DOUBLETerminalRuleCall_1_0()); 
            match(input,RULE_SIGNED_DOUBLE,FOLLOW_RULE_SIGNED_DOUBLE_in_rule__DoubleArrayExpression__ValuesAssignment_113918); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6864:1: rule__DoubleArrayExpression__ValuesAssignment_2_1 : ( RULE_SIGNED_DOUBLE ) ;
    public final void rule__DoubleArrayExpression__ValuesAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6868:1: ( ( RULE_SIGNED_DOUBLE ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6869:1: ( RULE_SIGNED_DOUBLE )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6869:1: ( RULE_SIGNED_DOUBLE )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6870:1: RULE_SIGNED_DOUBLE
            {
             before(grammarAccess.getDoubleArrayExpressionAccess().getValuesSIGNED_DOUBLETerminalRuleCall_2_1_0()); 
            match(input,RULE_SIGNED_DOUBLE,FOLLOW_RULE_SIGNED_DOUBLE_in_rule__DoubleArrayExpression__ValuesAssignment_2_113949); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6879:1: rule__LongArrayExpression__ValuesAssignment_1 : ( RULE_SINGED_LONG ) ;
    public final void rule__LongArrayExpression__ValuesAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6883:1: ( ( RULE_SINGED_LONG ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6884:1: ( RULE_SINGED_LONG )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6884:1: ( RULE_SINGED_LONG )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6885:1: RULE_SINGED_LONG
            {
             before(grammarAccess.getLongArrayExpressionAccess().getValuesSINGED_LONGTerminalRuleCall_1_0()); 
            match(input,RULE_SINGED_LONG,FOLLOW_RULE_SINGED_LONG_in_rule__LongArrayExpression__ValuesAssignment_113980); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6894:1: rule__LongArrayExpression__ValuesAssignment_2_1 : ( RULE_SINGED_LONG ) ;
    public final void rule__LongArrayExpression__ValuesAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6898:1: ( ( RULE_SINGED_LONG ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6899:1: ( RULE_SINGED_LONG )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6899:1: ( RULE_SINGED_LONG )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6900:1: RULE_SINGED_LONG
            {
             before(grammarAccess.getLongArrayExpressionAccess().getValuesSINGED_LONGTerminalRuleCall_2_1_0()); 
            match(input,RULE_SINGED_LONG,FOLLOW_RULE_SINGED_LONG_in_rule__LongArrayExpression__ValuesAssignment_2_114011); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6909:1: rule__StringArrayExpression__ValuesAssignment_1 : ( RULE_STRING ) ;
    public final void rule__StringArrayExpression__ValuesAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6913:1: ( ( RULE_STRING ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6914:1: ( RULE_STRING )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6914:1: ( RULE_STRING )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6915:1: RULE_STRING
            {
             before(grammarAccess.getStringArrayExpressionAccess().getValuesSTRINGTerminalRuleCall_1_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__StringArrayExpression__ValuesAssignment_114042); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6924:1: rule__StringArrayExpression__ValuesAssignment_2_1 : ( RULE_STRING ) ;
    public final void rule__StringArrayExpression__ValuesAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6928:1: ( ( RULE_STRING ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6929:1: ( RULE_STRING )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6929:1: ( RULE_STRING )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6930:1: RULE_STRING
            {
             before(grammarAccess.getStringArrayExpressionAccess().getValuesSTRINGTerminalRuleCall_2_1_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__StringArrayExpression__ValuesAssignment_2_114073); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6939:1: rule__NullArrayExpression__ValuesAssignment_1 : ( ( 'null' ) ) ;
    public final void rule__NullArrayExpression__ValuesAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6943:1: ( ( ( 'null' ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6944:1: ( ( 'null' ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6944:1: ( ( 'null' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6945:1: ( 'null' )
            {
             before(grammarAccess.getNullArrayExpressionAccess().getValuesNullKeyword_1_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6946:1: ( 'null' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6947:1: 'null'
            {
             before(grammarAccess.getNullArrayExpressionAccess().getValuesNullKeyword_1_0()); 
            match(input,41,FOLLOW_41_in_rule__NullArrayExpression__ValuesAssignment_114109); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6962:1: rule__NullArrayExpression__ValuesAssignment_2_1 : ( ( 'null' ) ) ;
    public final void rule__NullArrayExpression__ValuesAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6966:1: ( ( ( 'null' ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6967:1: ( ( 'null' ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6967:1: ( ( 'null' ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6968:1: ( 'null' )
            {
             before(grammarAccess.getNullArrayExpressionAccess().getValuesNullKeyword_2_1_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6969:1: ( 'null' )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6970:1: 'null'
            {
             before(grammarAccess.getNullArrayExpressionAccess().getValuesNullKeyword_2_1_0()); 
            match(input,41,FOLLOW_41_in_rule__NullArrayExpression__ValuesAssignment_2_114153); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6985:1: rule__DateArrayExpression__ValuesAssignment_1 : ( RULE_DATE ) ;
    public final void rule__DateArrayExpression__ValuesAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6989:1: ( ( RULE_DATE ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6990:1: ( RULE_DATE )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6990:1: ( RULE_DATE )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:6991:1: RULE_DATE
            {
             before(grammarAccess.getDateArrayExpressionAccess().getValuesDATETerminalRuleCall_1_0()); 
            match(input,RULE_DATE,FOLLOW_RULE_DATE_in_rule__DateArrayExpression__ValuesAssignment_114192); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:7000:1: rule__DateArrayExpression__ValuesAssignment_2_1 : ( RULE_DATE ) ;
    public final void rule__DateArrayExpression__ValuesAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:7004:1: ( ( RULE_DATE ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:7005:1: ( RULE_DATE )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:7005:1: ( RULE_DATE )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:7006:1: RULE_DATE
            {
             before(grammarAccess.getDateArrayExpressionAccess().getValuesDATETerminalRuleCall_2_1_0()); 
            match(input,RULE_DATE,FOLLOW_RULE_DATE_in_rule__DateArrayExpression__ValuesAssignment_2_114223); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:7015:1: rule__BooleanArrayExpression__ValuesAssignment_1 : ( RULE_BOOL ) ;
    public final void rule__BooleanArrayExpression__ValuesAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:7019:1: ( ( RULE_BOOL ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:7020:1: ( RULE_BOOL )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:7020:1: ( RULE_BOOL )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:7021:1: RULE_BOOL
            {
             before(grammarAccess.getBooleanArrayExpressionAccess().getValuesBOOLTerminalRuleCall_1_0()); 
            match(input,RULE_BOOL,FOLLOW_RULE_BOOL_in_rule__BooleanArrayExpression__ValuesAssignment_114254); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:7030:1: rule__BooleanArrayExpression__ValuesAssignment_2_1 : ( RULE_BOOL ) ;
    public final void rule__BooleanArrayExpression__ValuesAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:7034:1: ( ( RULE_BOOL ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:7035:1: ( RULE_BOOL )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:7035:1: ( RULE_BOOL )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSql.g:7036:1: RULE_BOOL
            {
             before(grammarAccess.getBooleanArrayExpressionAccess().getValuesBOOLTerminalRuleCall_2_1_0()); 
            match(input,RULE_BOOL,FOLLOW_RULE_BOOL_in_rule__BooleanArrayExpression__ValuesAssignment_2_114285); 
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


    protected DFA3 dfa3 = new DFA3(this);
    static final String DFA3_eotS =
        "\13\uffff";
    static final String DFA3_eofS =
        "\1\uffff\1\5\4\uffff\1\5\1\uffff\1\5\1\uffff\1\5";
    static final String DFA3_minS =
        "\3\4\3\uffff\5\4";
    static final String DFA3_maxS =
        "\1\4\1\41\1\4\3\uffff\1\41\1\4\1\41\1\4\1\41";
    static final String DFA3_acceptS =
        "\3\uffff\1\3\1\2\1\1\5\uffff";
    static final String DFA3_specialS =
        "\13\uffff}>";
    static final String[] DFA3_transitionS = {
            "\1\1",
            "\1\3\25\uffff\1\5\4\uffff\1\5\1\2\1\4",
            "\1\6",
            "",
            "",
            "",
            "\1\3\25\uffff\1\5\4\uffff\1\5\1\7\1\4",
            "\1\10",
            "\1\3\25\uffff\1\5\4\uffff\1\5\1\11\1\4",
            "\1\12",
            "\1\3\25\uffff\1\5\4\uffff\1\5\1\uffff\1\4"
    };

    static final short[] DFA3_eot = DFA.unpackEncodedString(DFA3_eotS);
    static final short[] DFA3_eof = DFA.unpackEncodedString(DFA3_eofS);
    static final char[] DFA3_min = DFA.unpackEncodedStringToUnsignedChars(DFA3_minS);
    static final char[] DFA3_max = DFA.unpackEncodedStringToUnsignedChars(DFA3_maxS);
    static final short[] DFA3_accept = DFA.unpackEncodedString(DFA3_acceptS);
    static final short[] DFA3_special = DFA.unpackEncodedString(DFA3_specialS);
    static final short[][] DFA3_transition;

    static {
        int numStates = DFA3_transitionS.length;
        DFA3_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA3_transition[i] = DFA.unpackEncodedString(DFA3_transitionS[i]);
        }
    }

    class DFA3 extends DFA {

        public DFA3(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 3;
            this.eot = DFA3_eot;
            this.eof = DFA3_eof;
            this.min = DFA3_min;
            this.max = DFA3_max;
            this.accept = DFA3_accept;
            this.special = DFA3_special;
            this.transition = DFA3_transition;
        }
        public String getDescription() {
            return "1346:1: rule__ColumnOrAlias__Alternatives : ( ( ruleColumnFull ) | ( ( rule__ColumnOrAlias__Group_1__0 ) ) | ( ( rule__ColumnOrAlias__Group_2__0 ) ) );";
        }
    }
 

    public static final BitSet FOLLOW_ruleModel_in_entryRuleModel61 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleModel68 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Model__Group__0_in_ruleModel94 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOrderByColumns_in_entryRuleOrderByColumns121 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOrderByColumns128 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OrderByColumns__Group__0_in_ruleOrderByColumns154 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOrderByColumnFull_in_entryRuleOrderByColumnFull181 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOrderByColumnFull188 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OrderByColumnFull__Alternatives_in_ruleOrderByColumnFull214 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleGroupByColumns_in_entryRuleGroupByColumns241 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleGroupByColumns248 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__GroupByColumns__Group__0_in_ruleGroupByColumns274 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleGroupByColumnFull_in_entryRuleGroupByColumnFull301 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleGroupByColumnFull308 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__GroupByColumnFull__Alternatives_in_ruleGroupByColumnFull334 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumns_in_entryRuleColumns361 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleColumns368 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Columns__Group__0_in_ruleColumns394 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnOrAlias_in_entryRuleColumnOrAlias421 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleColumnOrAlias428 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ColumnOrAlias__Alternatives_in_ruleColumnOrAlias454 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnFull_in_entryRuleColumnFull481 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleColumnFull488 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ColumnFull__Alternatives_in_ruleColumnFull514 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnAlias_in_entryRuleColumnAlias541 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleColumnAlias548 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ColumnAlias__ColAliasAssignment_in_ruleColumnAlias574 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumn_in_entryRuleColumn601 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleColumn608 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Column__ColNameAssignment_in_ruleColumn634 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTables_in_entryRuleTables661 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleTables668 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Tables__Group__0_in_ruleTables694 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTableOrAlias_in_entryRuleTableOrAlias721 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleTableOrAlias728 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TableOrAlias__Alternatives_in_ruleTableOrAlias754 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTableFull_in_entryRuleTableFull781 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleTableFull788 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TableFull__Alternatives_in_ruleTableFull814 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTable_in_entryRuleTable841 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleTable848 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Table__TblAssignment_in_ruleTable874 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTableAlias_in_entryRuleTableAlias901 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleTableAlias908 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TableAlias__TblAliasAssignment_in_ruleTableAlias934 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSchema_in_entryRuleSchema961 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSchema968 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Schema__Alternatives_in_ruleSchema994 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDatabase_in_entryRuleDatabase1021 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleDatabase1028 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Database__DbNameAssignment_in_ruleDatabase1054 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleWhereEntry_in_entryRuleWhereEntry1081 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleWhereEntry1088 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__WhereEntry__Group__0_in_ruleWhereEntry1114 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAndWhereEntry_in_entryRuleAndWhereEntry1141 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAndWhereEntry1148 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__AndWhereEntry__Group__0_in_ruleAndWhereEntry1174 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleConcreteWhereEntry_in_entryRuleConcreteWhereEntry1201 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleConcreteWhereEntry1208 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ConcreteWhereEntry__Alternatives_in_ruleConcreteWhereEntry1234 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleParWhereEntry_in_entryRuleParWhereEntry1261 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleParWhereEntry1268 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ParWhereEntry__Group__0_in_ruleParWhereEntry1294 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleHavingEntry_in_entryRuleHavingEntry1321 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleHavingEntry1328 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__HavingEntry__Group__0_in_ruleHavingEntry1354 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAndHavingEntry_in_entryRuleAndHavingEntry1381 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAndHavingEntry1388 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__AndHavingEntry__Group__0_in_ruleAndHavingEntry1414 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleConcreteHavingEntry_in_entryRuleConcreteHavingEntry1441 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleConcreteHavingEntry1448 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ConcreteHavingEntry__Alternatives_in_ruleConcreteHavingEntry1474 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleParHavingEntry_in_entryRuleParHavingEntry1501 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleParHavingEntry1508 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ParHavingEntry__Group__0_in_ruleParHavingEntry1534 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpressionWhereEntry_in_entryRuleExpressionWhereEntry1561 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpressionWhereEntry1568 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ExpressionWhereEntry__Alternatives_in_ruleExpressionWhereEntry1594 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSingleExpressionWhereEntry_in_entryRuleSingleExpressionWhereEntry1621 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSingleExpressionWhereEntry1628 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SingleExpressionWhereEntry__Group__0_in_ruleSingleExpressionWhereEntry1654 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression_in_entryRuleExpression1681 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpression1688 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Expression__Alternatives_in_ruleExpression1714 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleReplacableValue_in_entryRuleReplacableValue1741 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleReplacableValue1748 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ReplacableValue__ValueAssignment_in_ruleReplacableValue1774 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDoubleExpression_in_entryRuleDoubleExpression1801 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleDoubleExpression1808 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__DoubleExpression__ValueAssignment_in_ruleDoubleExpression1834 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLongExpression_in_entryRuleLongExpression1861 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleLongExpression1868 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__LongExpression__ValueAssignment_in_ruleLongExpression1894 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleStringExpression_in_entryRuleStringExpression1921 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleStringExpression1928 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__StringExpression__ValueAssignment_in_ruleStringExpression1954 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNullExpression_in_entryRuleNullExpression1981 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleNullExpression1988 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NullExpression__ValueAssignment_in_ruleNullExpression2014 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDateExpression_in_entryRuleDateExpression2041 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleDateExpression2048 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__DateExpression__ValueAssignment_in_ruleDateExpression2074 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBooleanExpression_in_entryRuleBooleanExpression2101 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleBooleanExpression2108 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__BooleanExpression__Alternatives_in_ruleBooleanExpression2134 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleMultiExpressionWhereEntry_in_entryRuleMultiExpressionWhereEntry2161 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleMultiExpressionWhereEntry2168 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__MultiExpressionWhereEntry__Group__0_in_ruleMultiExpressionWhereEntry2194 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleArrayExpression_in_entryRuleArrayExpression2221 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleArrayExpression2228 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ArrayExpression__Alternatives_in_ruleArrayExpression2254 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDoubleArrayExpression_in_entryRuleDoubleArrayExpression2281 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleDoubleArrayExpression2288 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__DoubleArrayExpression__Group__0_in_ruleDoubleArrayExpression2314 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLongArrayExpression_in_entryRuleLongArrayExpression2341 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleLongArrayExpression2348 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__LongArrayExpression__Group__0_in_ruleLongArrayExpression2374 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleStringArrayExpression_in_entryRuleStringArrayExpression2401 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleStringArrayExpression2408 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__StringArrayExpression__Group__0_in_ruleStringArrayExpression2434 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNullArrayExpression_in_entryRuleNullArrayExpression2461 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleNullArrayExpression2468 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NullArrayExpression__Group__0_in_ruleNullArrayExpression2494 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDateArrayExpression_in_entryRuleDateArrayExpression2521 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleDateArrayExpression2528 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__DateArrayExpression__Group__0_in_ruleDateArrayExpression2554 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBooleanArrayExpression_in_entryRuleBooleanArrayExpression2581 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleBooleanArrayExpression2588 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__BooleanArrayExpression__Group__0_in_ruleBooleanArrayExpression2614 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ArrayOperator__Alternatives_in_ruleArrayOperator2651 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Operator__Alternatives_in_ruleOperator2687 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OrderByColumnFull__ColOrderAssignment_0_in_rule__OrderByColumnFull__Alternatives2722 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OrderByColumnFull__Group_1__0_in_rule__OrderByColumnFull__Alternatives2740 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__GroupByColumnFull__GroupByColumnAssignment_0_in_rule__GroupByColumnFull__Alternatives2773 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__GroupByColumnFull__Group_1__0_in_rule__GroupByColumnFull__Alternatives2791 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnFull_in_rule__ColumnOrAlias__Alternatives2824 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ColumnOrAlias__Group_1__0_in_rule__ColumnOrAlias__Alternatives2841 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ColumnOrAlias__Group_2__0_in_rule__ColumnOrAlias__Alternatives2859 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ColumnFull__ColNameAssignment_0_in_rule__ColumnFull__Alternatives2892 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ColumnFull__Group_1__0_in_rule__ColumnFull__Alternatives2910 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTableFull_in_rule__TableOrAlias__Alternatives2943 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TableOrAlias__Group_1__0_in_rule__TableOrAlias__Alternatives2960 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TableOrAlias__Group_2__0_in_rule__TableOrAlias__Alternatives2978 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TableFull__Group_0__0_in_rule__TableFull__Alternatives3011 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TableFull__TblAssignment_1_in_rule__TableFull__Alternatives3029 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Schema__Group_0__0_in_rule__Schema__Alternatives3062 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Schema__SchemAssignment_1_in_rule__Schema__Alternatives3080 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleParWhereEntry_in_rule__ConcreteWhereEntry__Alternatives3113 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpressionWhereEntry_in_rule__ConcreteWhereEntry__Alternatives3130 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleParHavingEntry_in_rule__ConcreteHavingEntry__Alternatives3162 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpressionWhereEntry_in_rule__ConcreteHavingEntry__Alternatives3179 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSingleExpressionWhereEntry_in_rule__ExpressionWhereEntry__Alternatives3211 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleMultiExpressionWhereEntry_in_rule__ExpressionWhereEntry__Alternatives3228 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDoubleExpression_in_rule__Expression__Alternatives3260 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLongExpression_in_rule__Expression__Alternatives3277 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleStringExpression_in_rule__Expression__Alternatives3294 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNullExpression_in_rule__Expression__Alternatives3311 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDateExpression_in_rule__Expression__Alternatives3328 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBooleanExpression_in_rule__Expression__Alternatives3345 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleReplacableValue_in_rule__Expression__Alternatives3362 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__BooleanExpression__TrueAssignment_0_in_rule__BooleanExpression__Alternatives3394 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__BooleanExpression__TrueAssignment_1_in_rule__BooleanExpression__Alternatives3412 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDoubleArrayExpression_in_rule__ArrayExpression__Alternatives3445 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLongArrayExpression_in_rule__ArrayExpression__Alternatives3462 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleStringArrayExpression_in_rule__ArrayExpression__Alternatives3479 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNullArrayExpression_in_rule__ArrayExpression__Alternatives3496 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDateArrayExpression_in_rule__ArrayExpression__Alternatives3513 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBooleanArrayExpression_in_rule__ArrayExpression__Alternatives3530 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_rule__ArrayOperator__Alternatives3563 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_rule__ArrayOperator__Alternatives3584 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_17_in_rule__Operator__Alternatives3620 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_rule__Operator__Alternatives3641 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_rule__Operator__Alternatives3662 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_rule__Operator__Alternatives3683 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_rule__Operator__Alternatives3704 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_22_in_rule__Operator__Alternatives3725 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_rule__Operator__Alternatives3746 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_rule__Operator__Alternatives3767 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_rule__Operator__Alternatives3788 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_rule__Operator__Alternatives3809 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Model__Group__0__Impl_in_rule__Model__Group__03842 = new BitSet(new long[]{0x0000000004000010L});
    public static final BitSet FOLLOW_rule__Model__Group__1_in_rule__Model__Group__03845 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_rule__Model__Group__0__Impl3873 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Model__Group__1__Impl_in_rule__Model__Group__13904 = new BitSet(new long[]{0x0000000004000010L});
    public static final BitSet FOLLOW_rule__Model__Group__2_in_rule__Model__Group__13907 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Model__ColAssignment_1_in_rule__Model__Group__1__Impl3934 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Model__Group__2__Impl_in_rule__Model__Group__23965 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__Model__Group__3_in_rule__Model__Group__23968 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_rule__Model__Group__2__Impl3996 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Model__Group__3__Impl_in_rule__Model__Group__34027 = new BitSet(new long[]{0x0000000078000000L});
    public static final BitSet FOLLOW_rule__Model__Group__4_in_rule__Model__Group__34030 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Model__TblAssignment_3_in_rule__Model__Group__3__Impl4057 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Model__Group__4__Impl_in_rule__Model__Group__44087 = new BitSet(new long[]{0x0000000078000000L});
    public static final BitSet FOLLOW_rule__Model__Group__5_in_rule__Model__Group__44090 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Model__Group_4__0_in_rule__Model__Group__4__Impl4117 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Model__Group__5__Impl_in_rule__Model__Group__54148 = new BitSet(new long[]{0x0000000078000000L});
    public static final BitSet FOLLOW_rule__Model__Group__6_in_rule__Model__Group__54151 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Model__Group_5__0_in_rule__Model__Group__5__Impl4178 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Model__Group__6__Impl_in_rule__Model__Group__64209 = new BitSet(new long[]{0x0000000078000000L});
    public static final BitSet FOLLOW_rule__Model__Group__7_in_rule__Model__Group__64212 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Model__Group_6__0_in_rule__Model__Group__6__Impl4239 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Model__Group__7__Impl_in_rule__Model__Group__74270 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Model__Group_7__0_in_rule__Model__Group__7__Impl4297 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Model__Group_4__0__Impl_in_rule__Model__Group_4__04344 = new BitSet(new long[]{0x0000001000000010L});
    public static final BitSet FOLLOW_rule__Model__Group_4__1_in_rule__Model__Group_4__04347 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_27_in_rule__Model__Group_4__0__Impl4375 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Model__Group_4__1__Impl_in_rule__Model__Group_4__14406 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Model__WhereEntryAssignment_4_1_in_rule__Model__Group_4__1__Impl4433 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Model__Group_5__0__Impl_in_rule__Model__Group_5__04467 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__Model__Group_5__1_in_rule__Model__Group_5__04470 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_rule__Model__Group_5__0__Impl4498 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Model__Group_5__1__Impl_in_rule__Model__Group_5__14529 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Model__GroupByEntryAssignment_5_1_in_rule__Model__Group_5__1__Impl4556 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Model__Group_6__0__Impl_in_rule__Model__Group_6__04590 = new BitSet(new long[]{0x0000001000000010L});
    public static final BitSet FOLLOW_rule__Model__Group_6__1_in_rule__Model__Group_6__04593 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_29_in_rule__Model__Group_6__0__Impl4621 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Model__Group_6__1__Impl_in_rule__Model__Group_6__14652 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Model__HavingEntryAssignment_6_1_in_rule__Model__Group_6__1__Impl4679 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Model__Group_7__0__Impl_in_rule__Model__Group_7__04713 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__Model__Group_7__1_in_rule__Model__Group_7__04716 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_30_in_rule__Model__Group_7__0__Impl4744 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Model__Group_7__1__Impl_in_rule__Model__Group_7__14775 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Model__OrderByEntryAssignment_7_1_in_rule__Model__Group_7__1__Impl4802 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OrderByColumns__Group__0__Impl_in_rule__OrderByColumns__Group__04836 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_rule__OrderByColumns__Group__1_in_rule__OrderByColumns__Group__04839 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOrderByColumnFull_in_rule__OrderByColumns__Group__0__Impl4866 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OrderByColumns__Group__1__Impl_in_rule__OrderByColumns__Group__14895 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OrderByColumns__Group_1__0_in_rule__OrderByColumns__Group__1__Impl4922 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OrderByColumns__Group_1__0__Impl_in_rule__OrderByColumns__Group_1__04957 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_rule__OrderByColumns__Group_1__1_in_rule__OrderByColumns__Group_1__04960 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OrderByColumns__Group_1__1__Impl_in_rule__OrderByColumns__Group_1__15018 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OrderByColumns__Group_1_1__0_in_rule__OrderByColumns__Group_1__1__Impl5047 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_rule__OrderByColumns__Group_1_1__0_in_rule__OrderByColumns__Group_1__1__Impl5059 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_rule__OrderByColumns__Group_1_1__0__Impl_in_rule__OrderByColumns__Group_1_1__05096 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__OrderByColumns__Group_1_1__1_in_rule__OrderByColumns__Group_1_1__05099 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_rule__OrderByColumns__Group_1_1__0__Impl5127 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OrderByColumns__Group_1_1__1__Impl_in_rule__OrderByColumns__Group_1_1__15158 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OrderByColumns__EntriesAssignment_1_1_1_in_rule__OrderByColumns__Group_1_1__1__Impl5185 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OrderByColumnFull__Group_1__0__Impl_in_rule__OrderByColumnFull__Group_1__05219 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_rule__OrderByColumnFull__Group_1__1_in_rule__OrderByColumnFull__Group_1__05222 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTableFull_in_rule__OrderByColumnFull__Group_1__0__Impl5249 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OrderByColumnFull__Group_1__1__Impl_in_rule__OrderByColumnFull__Group_1__15278 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__OrderByColumnFull__Group_1__2_in_rule__OrderByColumnFull__Group_1__15281 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_rule__OrderByColumnFull__Group_1__1__Impl5309 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OrderByColumnFull__Group_1__2__Impl_in_rule__OrderByColumnFull__Group_1__25340 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OrderByColumnFull__ColOrderAssignment_1_2_in_rule__OrderByColumnFull__Group_1__2__Impl5367 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__GroupByColumns__Group__0__Impl_in_rule__GroupByColumns__Group__05403 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_rule__GroupByColumns__Group__1_in_rule__GroupByColumns__Group__05406 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleGroupByColumnFull_in_rule__GroupByColumns__Group__0__Impl5433 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__GroupByColumns__Group__1__Impl_in_rule__GroupByColumns__Group__15462 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__GroupByColumns__Group_1__0_in_rule__GroupByColumns__Group__1__Impl5489 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__GroupByColumns__Group_1__0__Impl_in_rule__GroupByColumns__Group_1__05524 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_rule__GroupByColumns__Group_1__1_in_rule__GroupByColumns__Group_1__05527 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__GroupByColumns__Group_1__1__Impl_in_rule__GroupByColumns__Group_1__15585 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__GroupByColumns__Group_1_1__0_in_rule__GroupByColumns__Group_1__1__Impl5614 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_rule__GroupByColumns__Group_1_1__0_in_rule__GroupByColumns__Group_1__1__Impl5626 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_rule__GroupByColumns__Group_1_1__0__Impl_in_rule__GroupByColumns__Group_1_1__05663 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__GroupByColumns__Group_1_1__1_in_rule__GroupByColumns__Group_1_1__05666 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_rule__GroupByColumns__Group_1_1__0__Impl5694 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__GroupByColumns__Group_1_1__1__Impl_in_rule__GroupByColumns__Group_1_1__15725 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__GroupByColumns__EntriesAssignment_1_1_1_in_rule__GroupByColumns__Group_1_1__1__Impl5752 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__GroupByColumnFull__Group_1__0__Impl_in_rule__GroupByColumnFull__Group_1__05786 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_rule__GroupByColumnFull__Group_1__1_in_rule__GroupByColumnFull__Group_1__05789 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTableFull_in_rule__GroupByColumnFull__Group_1__0__Impl5816 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__GroupByColumnFull__Group_1__1__Impl_in_rule__GroupByColumnFull__Group_1__15845 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__GroupByColumnFull__Group_1__2_in_rule__GroupByColumnFull__Group_1__15848 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_rule__GroupByColumnFull__Group_1__1__Impl5876 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__GroupByColumnFull__Group_1__2__Impl_in_rule__GroupByColumnFull__Group_1__25907 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__GroupByColumnFull__GroupByColumnAssignment_1_2_in_rule__GroupByColumnFull__Group_1__2__Impl5934 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Columns__Group__0__Impl_in_rule__Columns__Group__05970 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_rule__Columns__Group__1_in_rule__Columns__Group__05973 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnOrAlias_in_rule__Columns__Group__0__Impl6000 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Columns__Group__1__Impl_in_rule__Columns__Group__16029 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Columns__Group_1__0_in_rule__Columns__Group__1__Impl6056 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Columns__Group_1__0__Impl_in_rule__Columns__Group_1__06091 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_rule__Columns__Group_1__1_in_rule__Columns__Group_1__06094 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Columns__Group_1__1__Impl_in_rule__Columns__Group_1__16152 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Columns__Group_1_1__0_in_rule__Columns__Group_1__1__Impl6181 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_rule__Columns__Group_1_1__0_in_rule__Columns__Group_1__1__Impl6193 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_rule__Columns__Group_1_1__0__Impl_in_rule__Columns__Group_1_1__06230 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__Columns__Group_1_1__1_in_rule__Columns__Group_1_1__06233 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_rule__Columns__Group_1_1__0__Impl6261 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Columns__Group_1_1__1__Impl_in_rule__Columns__Group_1_1__16292 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Columns__EntriesAssignment_1_1_1_in_rule__Columns__Group_1_1__1__Impl6319 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ColumnOrAlias__Group_1__0__Impl_in_rule__ColumnOrAlias__Group_1__06353 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_rule__ColumnOrAlias__Group_1__1_in_rule__ColumnOrAlias__Group_1__06356 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnFull_in_rule__ColumnOrAlias__Group_1__0__Impl6383 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ColumnOrAlias__Group_1__1__Impl_in_rule__ColumnOrAlias__Group_1__16412 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__ColumnOrAlias__Group_1__2_in_rule__ColumnOrAlias__Group_1__16415 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_33_in_rule__ColumnOrAlias__Group_1__1__Impl6443 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ColumnOrAlias__Group_1__2__Impl_in_rule__ColumnOrAlias__Group_1__26474 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ColumnOrAlias__ColAliasAssignment_1_2_in_rule__ColumnOrAlias__Group_1__2__Impl6501 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ColumnOrAlias__Group_2__0__Impl_in_rule__ColumnOrAlias__Group_2__06537 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__ColumnOrAlias__Group_2__1_in_rule__ColumnOrAlias__Group_2__06540 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnFull_in_rule__ColumnOrAlias__Group_2__0__Impl6567 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ColumnOrAlias__Group_2__1__Impl_in_rule__ColumnOrAlias__Group_2__16596 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ColumnOrAlias__ColAliasAssignment_2_1_in_rule__ColumnOrAlias__Group_2__1__Impl6623 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ColumnFull__Group_1__0__Impl_in_rule__ColumnFull__Group_1__06657 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_rule__ColumnFull__Group_1__1_in_rule__ColumnFull__Group_1__06660 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTableFull_in_rule__ColumnFull__Group_1__0__Impl6687 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ColumnFull__Group_1__1__Impl_in_rule__ColumnFull__Group_1__16716 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__ColumnFull__Group_1__2_in_rule__ColumnFull__Group_1__16719 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_rule__ColumnFull__Group_1__1__Impl6747 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ColumnFull__Group_1__2__Impl_in_rule__ColumnFull__Group_1__26778 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ColumnFull__ColNameAssignment_1_2_in_rule__ColumnFull__Group_1__2__Impl6805 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Tables__Group__0__Impl_in_rule__Tables__Group__06841 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_rule__Tables__Group__1_in_rule__Tables__Group__06844 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTableOrAlias_in_rule__Tables__Group__0__Impl6871 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Tables__Group__1__Impl_in_rule__Tables__Group__16900 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Tables__Group_1__0_in_rule__Tables__Group__1__Impl6927 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Tables__Group_1__0__Impl_in_rule__Tables__Group_1__06962 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_rule__Tables__Group_1__1_in_rule__Tables__Group_1__06965 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Tables__Group_1__1__Impl_in_rule__Tables__Group_1__17023 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Tables__Group_1_1__0_in_rule__Tables__Group_1__1__Impl7052 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_rule__Tables__Group_1_1__0_in_rule__Tables__Group_1__1__Impl7064 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_rule__Tables__Group_1_1__0__Impl_in_rule__Tables__Group_1_1__07101 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__Tables__Group_1_1__1_in_rule__Tables__Group_1_1__07104 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_rule__Tables__Group_1_1__0__Impl7132 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Tables__Group_1_1__1__Impl_in_rule__Tables__Group_1_1__17163 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Tables__EntriesAssignment_1_1_1_in_rule__Tables__Group_1_1__1__Impl7190 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TableOrAlias__Group_1__0__Impl_in_rule__TableOrAlias__Group_1__07224 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_rule__TableOrAlias__Group_1__1_in_rule__TableOrAlias__Group_1__07227 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTableFull_in_rule__TableOrAlias__Group_1__0__Impl7254 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TableOrAlias__Group_1__1__Impl_in_rule__TableOrAlias__Group_1__17283 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__TableOrAlias__Group_1__2_in_rule__TableOrAlias__Group_1__17286 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_33_in_rule__TableOrAlias__Group_1__1__Impl7314 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TableOrAlias__Group_1__2__Impl_in_rule__TableOrAlias__Group_1__27345 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TableOrAlias__TblAliasAssignment_1_2_in_rule__TableOrAlias__Group_1__2__Impl7372 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TableOrAlias__Group_2__0__Impl_in_rule__TableOrAlias__Group_2__07408 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__TableOrAlias__Group_2__1_in_rule__TableOrAlias__Group_2__07411 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTableFull_in_rule__TableOrAlias__Group_2__0__Impl7438 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TableOrAlias__Group_2__1__Impl_in_rule__TableOrAlias__Group_2__17467 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TableOrAlias__TblAliasAssignment_2_1_in_rule__TableOrAlias__Group_2__1__Impl7494 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TableFull__Group_0__0__Impl_in_rule__TableFull__Group_0__07528 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_rule__TableFull__Group_0__1_in_rule__TableFull__Group_0__07531 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSchema_in_rule__TableFull__Group_0__0__Impl7558 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TableFull__Group_0__1__Impl_in_rule__TableFull__Group_0__17587 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__TableFull__Group_0__2_in_rule__TableFull__Group_0__17590 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_rule__TableFull__Group_0__1__Impl7618 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TableFull__Group_0__2__Impl_in_rule__TableFull__Group_0__27649 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TableFull__TblAssignment_0_2_in_rule__TableFull__Group_0__2__Impl7676 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Schema__Group_0__0__Impl_in_rule__Schema__Group_0__07712 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_rule__Schema__Group_0__1_in_rule__Schema__Group_0__07715 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDatabase_in_rule__Schema__Group_0__0__Impl7742 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Schema__Group_0__1__Impl_in_rule__Schema__Group_0__17771 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__Schema__Group_0__2_in_rule__Schema__Group_0__17774 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_rule__Schema__Group_0__1__Impl7802 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Schema__Group_0__2__Impl_in_rule__Schema__Group_0__27833 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Schema__SchemAssignment_0_2_in_rule__Schema__Group_0__2__Impl7860 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__WhereEntry__Group__0__Impl_in_rule__WhereEntry__Group__07896 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_rule__WhereEntry__Group__1_in_rule__WhereEntry__Group__07899 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAndWhereEntry_in_rule__WhereEntry__Group__0__Impl7926 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__WhereEntry__Group__1__Impl_in_rule__WhereEntry__Group__17955 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__WhereEntry__Group_1__0_in_rule__WhereEntry__Group__1__Impl7982 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__WhereEntry__Group_1__0__Impl_in_rule__WhereEntry__Group_1__08017 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_rule__WhereEntry__Group_1__1_in_rule__WhereEntry__Group_1__08020 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__WhereEntry__Group_1__1__Impl_in_rule__WhereEntry__Group_1__18078 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__WhereEntry__Group_1_1__0_in_rule__WhereEntry__Group_1__1__Impl8107 = new BitSet(new long[]{0x0000000400000002L});
    public static final BitSet FOLLOW_rule__WhereEntry__Group_1_1__0_in_rule__WhereEntry__Group_1__1__Impl8119 = new BitSet(new long[]{0x0000000400000002L});
    public static final BitSet FOLLOW_rule__WhereEntry__Group_1_1__0__Impl_in_rule__WhereEntry__Group_1_1__08156 = new BitSet(new long[]{0x0000001000000010L});
    public static final BitSet FOLLOW_rule__WhereEntry__Group_1_1__1_in_rule__WhereEntry__Group_1_1__08159 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_34_in_rule__WhereEntry__Group_1_1__0__Impl8187 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__WhereEntry__Group_1_1__1__Impl_in_rule__WhereEntry__Group_1_1__18218 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__WhereEntry__EntriesAssignment_1_1_1_in_rule__WhereEntry__Group_1_1__1__Impl8245 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__AndWhereEntry__Group__0__Impl_in_rule__AndWhereEntry__Group__08279 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_rule__AndWhereEntry__Group__1_in_rule__AndWhereEntry__Group__08282 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleConcreteWhereEntry_in_rule__AndWhereEntry__Group__0__Impl8309 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__AndWhereEntry__Group__1__Impl_in_rule__AndWhereEntry__Group__18338 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__AndWhereEntry__Group_1__0_in_rule__AndWhereEntry__Group__1__Impl8365 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__AndWhereEntry__Group_1__0__Impl_in_rule__AndWhereEntry__Group_1__08400 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_rule__AndWhereEntry__Group_1__1_in_rule__AndWhereEntry__Group_1__08403 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__AndWhereEntry__Group_1__1__Impl_in_rule__AndWhereEntry__Group_1__18461 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__AndWhereEntry__Group_1_1__0_in_rule__AndWhereEntry__Group_1__1__Impl8490 = new BitSet(new long[]{0x0000000800000002L});
    public static final BitSet FOLLOW_rule__AndWhereEntry__Group_1_1__0_in_rule__AndWhereEntry__Group_1__1__Impl8502 = new BitSet(new long[]{0x0000000800000002L});
    public static final BitSet FOLLOW_rule__AndWhereEntry__Group_1_1__0__Impl_in_rule__AndWhereEntry__Group_1_1__08539 = new BitSet(new long[]{0x0000001000000010L});
    public static final BitSet FOLLOW_rule__AndWhereEntry__Group_1_1__1_in_rule__AndWhereEntry__Group_1_1__08542 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_35_in_rule__AndWhereEntry__Group_1_1__0__Impl8570 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__AndWhereEntry__Group_1_1__1__Impl_in_rule__AndWhereEntry__Group_1_1__18601 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__AndWhereEntry__EntriesAssignment_1_1_1_in_rule__AndWhereEntry__Group_1_1__1__Impl8628 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ParWhereEntry__Group__0__Impl_in_rule__ParWhereEntry__Group__08662 = new BitSet(new long[]{0x0000001000000010L});
    public static final BitSet FOLLOW_rule__ParWhereEntry__Group__1_in_rule__ParWhereEntry__Group__08665 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_36_in_rule__ParWhereEntry__Group__0__Impl8693 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ParWhereEntry__Group__1__Impl_in_rule__ParWhereEntry__Group__18724 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_rule__ParWhereEntry__Group__2_in_rule__ParWhereEntry__Group__18727 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleWhereEntry_in_rule__ParWhereEntry__Group__1__Impl8754 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ParWhereEntry__Group__2__Impl_in_rule__ParWhereEntry__Group__28783 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_37_in_rule__ParWhereEntry__Group__2__Impl8811 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__HavingEntry__Group__0__Impl_in_rule__HavingEntry__Group__08848 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_rule__HavingEntry__Group__1_in_rule__HavingEntry__Group__08851 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAndHavingEntry_in_rule__HavingEntry__Group__0__Impl8878 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__HavingEntry__Group__1__Impl_in_rule__HavingEntry__Group__18907 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__HavingEntry__Group_1__0_in_rule__HavingEntry__Group__1__Impl8934 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__HavingEntry__Group_1__0__Impl_in_rule__HavingEntry__Group_1__08969 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_rule__HavingEntry__Group_1__1_in_rule__HavingEntry__Group_1__08972 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__HavingEntry__Group_1__1__Impl_in_rule__HavingEntry__Group_1__19030 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__HavingEntry__Group_1_1__0_in_rule__HavingEntry__Group_1__1__Impl9059 = new BitSet(new long[]{0x0000000400000002L});
    public static final BitSet FOLLOW_rule__HavingEntry__Group_1_1__0_in_rule__HavingEntry__Group_1__1__Impl9071 = new BitSet(new long[]{0x0000000400000002L});
    public static final BitSet FOLLOW_rule__HavingEntry__Group_1_1__0__Impl_in_rule__HavingEntry__Group_1_1__09108 = new BitSet(new long[]{0x0000001000000010L});
    public static final BitSet FOLLOW_rule__HavingEntry__Group_1_1__1_in_rule__HavingEntry__Group_1_1__09111 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_34_in_rule__HavingEntry__Group_1_1__0__Impl9139 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__HavingEntry__Group_1_1__1__Impl_in_rule__HavingEntry__Group_1_1__19170 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__HavingEntry__EntriesAssignment_1_1_1_in_rule__HavingEntry__Group_1_1__1__Impl9197 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__AndHavingEntry__Group__0__Impl_in_rule__AndHavingEntry__Group__09231 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_rule__AndHavingEntry__Group__1_in_rule__AndHavingEntry__Group__09234 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleConcreteHavingEntry_in_rule__AndHavingEntry__Group__0__Impl9261 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__AndHavingEntry__Group__1__Impl_in_rule__AndHavingEntry__Group__19290 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__AndHavingEntry__Group_1__0_in_rule__AndHavingEntry__Group__1__Impl9317 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__AndHavingEntry__Group_1__0__Impl_in_rule__AndHavingEntry__Group_1__09352 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_rule__AndHavingEntry__Group_1__1_in_rule__AndHavingEntry__Group_1__09355 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__AndHavingEntry__Group_1__1__Impl_in_rule__AndHavingEntry__Group_1__19413 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__AndHavingEntry__Group_1_1__0_in_rule__AndHavingEntry__Group_1__1__Impl9442 = new BitSet(new long[]{0x0000000800000002L});
    public static final BitSet FOLLOW_rule__AndHavingEntry__Group_1_1__0_in_rule__AndHavingEntry__Group_1__1__Impl9454 = new BitSet(new long[]{0x0000000800000002L});
    public static final BitSet FOLLOW_rule__AndHavingEntry__Group_1_1__0__Impl_in_rule__AndHavingEntry__Group_1_1__09491 = new BitSet(new long[]{0x0000001000000010L});
    public static final BitSet FOLLOW_rule__AndHavingEntry__Group_1_1__1_in_rule__AndHavingEntry__Group_1_1__09494 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_35_in_rule__AndHavingEntry__Group_1_1__0__Impl9522 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__AndHavingEntry__Group_1_1__1__Impl_in_rule__AndHavingEntry__Group_1_1__19553 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__AndHavingEntry__EntriesAssignment_1_1_1_in_rule__AndHavingEntry__Group_1_1__1__Impl9580 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ParHavingEntry__Group__0__Impl_in_rule__ParHavingEntry__Group__09614 = new BitSet(new long[]{0x0000001000000010L});
    public static final BitSet FOLLOW_rule__ParHavingEntry__Group__1_in_rule__ParHavingEntry__Group__09617 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_36_in_rule__ParHavingEntry__Group__0__Impl9645 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ParHavingEntry__Group__1__Impl_in_rule__ParHavingEntry__Group__19676 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_rule__ParHavingEntry__Group__2_in_rule__ParHavingEntry__Group__19679 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleHavingEntry_in_rule__ParHavingEntry__Group__1__Impl9706 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ParHavingEntry__Group__2__Impl_in_rule__ParHavingEntry__Group__29735 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_37_in_rule__ParHavingEntry__Group__2__Impl9763 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SingleExpressionWhereEntry__Group__0__Impl_in_rule__SingleExpressionWhereEntry__Group__09800 = new BitSet(new long[]{0x0000000001FF8000L});
    public static final BitSet FOLLOW_rule__SingleExpressionWhereEntry__Group__1_in_rule__SingleExpressionWhereEntry__Group__09803 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SingleExpressionWhereEntry__NameAssignment_0_in_rule__SingleExpressionWhereEntry__Group__0__Impl9830 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SingleExpressionWhereEntry__Group__1__Impl_in_rule__SingleExpressionWhereEntry__Group__19860 = new BitSet(new long[]{0x00000F00000001E0L});
    public static final BitSet FOLLOW_rule__SingleExpressionWhereEntry__Group__2_in_rule__SingleExpressionWhereEntry__Group__19863 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SingleExpressionWhereEntry__OperatorAssignment_1_in_rule__SingleExpressionWhereEntry__Group__1__Impl9890 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SingleExpressionWhereEntry__Group__2__Impl_in_rule__SingleExpressionWhereEntry__Group__29920 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SingleExpressionWhereEntry__RhsAssignment_2_in_rule__SingleExpressionWhereEntry__Group__2__Impl9947 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__MultiExpressionWhereEntry__Group__0__Impl_in_rule__MultiExpressionWhereEntry__Group__09983 = new BitSet(new long[]{0x0000000000018000L});
    public static final BitSet FOLLOW_rule__MultiExpressionWhereEntry__Group__1_in_rule__MultiExpressionWhereEntry__Group__09986 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__MultiExpressionWhereEntry__NameAssignment_0_in_rule__MultiExpressionWhereEntry__Group__0__Impl10013 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__MultiExpressionWhereEntry__Group__1__Impl_in_rule__MultiExpressionWhereEntry__Group__110043 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_rule__MultiExpressionWhereEntry__Group__2_in_rule__MultiExpressionWhereEntry__Group__110046 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__MultiExpressionWhereEntry__OperatorAssignment_1_in_rule__MultiExpressionWhereEntry__Group__1__Impl10073 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__MultiExpressionWhereEntry__Group__2__Impl_in_rule__MultiExpressionWhereEntry__Group__210103 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__MultiExpressionWhereEntry__RhsAssignment_2_in_rule__MultiExpressionWhereEntry__Group__2__Impl10130 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__DoubleArrayExpression__Group__0__Impl_in_rule__DoubleArrayExpression__Group__010166 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__DoubleArrayExpression__Group__1_in_rule__DoubleArrayExpression__Group__010169 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_38_in_rule__DoubleArrayExpression__Group__0__Impl10197 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__DoubleArrayExpression__Group__1__Impl_in_rule__DoubleArrayExpression__Group__110228 = new BitSet(new long[]{0x0000008080000000L});
    public static final BitSet FOLLOW_rule__DoubleArrayExpression__Group__2_in_rule__DoubleArrayExpression__Group__110231 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__DoubleArrayExpression__ValuesAssignment_1_in_rule__DoubleArrayExpression__Group__1__Impl10258 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__DoubleArrayExpression__Group__2__Impl_in_rule__DoubleArrayExpression__Group__210288 = new BitSet(new long[]{0x0000008080000000L});
    public static final BitSet FOLLOW_rule__DoubleArrayExpression__Group__3_in_rule__DoubleArrayExpression__Group__210291 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__DoubleArrayExpression__Group_2__0_in_rule__DoubleArrayExpression__Group__2__Impl10318 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_rule__DoubleArrayExpression__Group__3__Impl_in_rule__DoubleArrayExpression__Group__310349 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_39_in_rule__DoubleArrayExpression__Group__3__Impl10377 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__DoubleArrayExpression__Group_2__0__Impl_in_rule__DoubleArrayExpression__Group_2__010416 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__DoubleArrayExpression__Group_2__1_in_rule__DoubleArrayExpression__Group_2__010419 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_rule__DoubleArrayExpression__Group_2__0__Impl10447 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__DoubleArrayExpression__Group_2__1__Impl_in_rule__DoubleArrayExpression__Group_2__110478 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__DoubleArrayExpression__ValuesAssignment_2_1_in_rule__DoubleArrayExpression__Group_2__1__Impl10505 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__LongArrayExpression__Group__0__Impl_in_rule__LongArrayExpression__Group__010539 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_rule__LongArrayExpression__Group__1_in_rule__LongArrayExpression__Group__010542 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_38_in_rule__LongArrayExpression__Group__0__Impl10570 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__LongArrayExpression__Group__1__Impl_in_rule__LongArrayExpression__Group__110601 = new BitSet(new long[]{0x0000008080000000L});
    public static final BitSet FOLLOW_rule__LongArrayExpression__Group__2_in_rule__LongArrayExpression__Group__110604 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__LongArrayExpression__ValuesAssignment_1_in_rule__LongArrayExpression__Group__1__Impl10631 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__LongArrayExpression__Group__2__Impl_in_rule__LongArrayExpression__Group__210661 = new BitSet(new long[]{0x0000008080000000L});
    public static final BitSet FOLLOW_rule__LongArrayExpression__Group__3_in_rule__LongArrayExpression__Group__210664 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__LongArrayExpression__Group_2__0_in_rule__LongArrayExpression__Group__2__Impl10691 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_rule__LongArrayExpression__Group__3__Impl_in_rule__LongArrayExpression__Group__310722 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_39_in_rule__LongArrayExpression__Group__3__Impl10750 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__LongArrayExpression__Group_2__0__Impl_in_rule__LongArrayExpression__Group_2__010789 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_rule__LongArrayExpression__Group_2__1_in_rule__LongArrayExpression__Group_2__010792 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_rule__LongArrayExpression__Group_2__0__Impl10820 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__LongArrayExpression__Group_2__1__Impl_in_rule__LongArrayExpression__Group_2__110851 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__LongArrayExpression__ValuesAssignment_2_1_in_rule__LongArrayExpression__Group_2__1__Impl10878 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__StringArrayExpression__Group__0__Impl_in_rule__StringArrayExpression__Group__010912 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_rule__StringArrayExpression__Group__1_in_rule__StringArrayExpression__Group__010915 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_38_in_rule__StringArrayExpression__Group__0__Impl10943 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__StringArrayExpression__Group__1__Impl_in_rule__StringArrayExpression__Group__110974 = new BitSet(new long[]{0x0000008080000000L});
    public static final BitSet FOLLOW_rule__StringArrayExpression__Group__2_in_rule__StringArrayExpression__Group__110977 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__StringArrayExpression__ValuesAssignment_1_in_rule__StringArrayExpression__Group__1__Impl11004 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__StringArrayExpression__Group__2__Impl_in_rule__StringArrayExpression__Group__211034 = new BitSet(new long[]{0x0000008080000000L});
    public static final BitSet FOLLOW_rule__StringArrayExpression__Group__3_in_rule__StringArrayExpression__Group__211037 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__StringArrayExpression__Group_2__0_in_rule__StringArrayExpression__Group__2__Impl11064 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_rule__StringArrayExpression__Group__3__Impl_in_rule__StringArrayExpression__Group__311095 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_39_in_rule__StringArrayExpression__Group__3__Impl11123 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__StringArrayExpression__Group_2__0__Impl_in_rule__StringArrayExpression__Group_2__011162 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_rule__StringArrayExpression__Group_2__1_in_rule__StringArrayExpression__Group_2__011165 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_rule__StringArrayExpression__Group_2__0__Impl11193 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__StringArrayExpression__Group_2__1__Impl_in_rule__StringArrayExpression__Group_2__111224 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__StringArrayExpression__ValuesAssignment_2_1_in_rule__StringArrayExpression__Group_2__1__Impl11251 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NullArrayExpression__Group__0__Impl_in_rule__NullArrayExpression__Group__011285 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_rule__NullArrayExpression__Group__1_in_rule__NullArrayExpression__Group__011288 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_38_in_rule__NullArrayExpression__Group__0__Impl11316 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NullArrayExpression__Group__1__Impl_in_rule__NullArrayExpression__Group__111347 = new BitSet(new long[]{0x0000008080000000L});
    public static final BitSet FOLLOW_rule__NullArrayExpression__Group__2_in_rule__NullArrayExpression__Group__111350 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NullArrayExpression__ValuesAssignment_1_in_rule__NullArrayExpression__Group__1__Impl11377 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NullArrayExpression__Group__2__Impl_in_rule__NullArrayExpression__Group__211407 = new BitSet(new long[]{0x0000008080000000L});
    public static final BitSet FOLLOW_rule__NullArrayExpression__Group__3_in_rule__NullArrayExpression__Group__211410 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NullArrayExpression__Group_2__0_in_rule__NullArrayExpression__Group__2__Impl11437 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_rule__NullArrayExpression__Group__3__Impl_in_rule__NullArrayExpression__Group__311468 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_39_in_rule__NullArrayExpression__Group__3__Impl11496 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NullArrayExpression__Group_2__0__Impl_in_rule__NullArrayExpression__Group_2__011535 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_rule__NullArrayExpression__Group_2__1_in_rule__NullArrayExpression__Group_2__011538 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_rule__NullArrayExpression__Group_2__0__Impl11566 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NullArrayExpression__Group_2__1__Impl_in_rule__NullArrayExpression__Group_2__111597 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NullArrayExpression__ValuesAssignment_2_1_in_rule__NullArrayExpression__Group_2__1__Impl11624 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__DateArrayExpression__Group__0__Impl_in_rule__DateArrayExpression__Group__011658 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_rule__DateArrayExpression__Group__1_in_rule__DateArrayExpression__Group__011661 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_38_in_rule__DateArrayExpression__Group__0__Impl11689 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__DateArrayExpression__Group__1__Impl_in_rule__DateArrayExpression__Group__111720 = new BitSet(new long[]{0x0000008080000000L});
    public static final BitSet FOLLOW_rule__DateArrayExpression__Group__2_in_rule__DateArrayExpression__Group__111723 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__DateArrayExpression__ValuesAssignment_1_in_rule__DateArrayExpression__Group__1__Impl11750 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__DateArrayExpression__Group__2__Impl_in_rule__DateArrayExpression__Group__211780 = new BitSet(new long[]{0x0000008080000000L});
    public static final BitSet FOLLOW_rule__DateArrayExpression__Group__3_in_rule__DateArrayExpression__Group__211783 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__DateArrayExpression__Group_2__0_in_rule__DateArrayExpression__Group__2__Impl11810 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_rule__DateArrayExpression__Group__3__Impl_in_rule__DateArrayExpression__Group__311841 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_39_in_rule__DateArrayExpression__Group__3__Impl11869 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__DateArrayExpression__Group_2__0__Impl_in_rule__DateArrayExpression__Group_2__011908 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_rule__DateArrayExpression__Group_2__1_in_rule__DateArrayExpression__Group_2__011911 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_rule__DateArrayExpression__Group_2__0__Impl11939 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__DateArrayExpression__Group_2__1__Impl_in_rule__DateArrayExpression__Group_2__111970 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__DateArrayExpression__ValuesAssignment_2_1_in_rule__DateArrayExpression__Group_2__1__Impl11997 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__BooleanArrayExpression__Group__0__Impl_in_rule__BooleanArrayExpression__Group__012031 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_rule__BooleanArrayExpression__Group__1_in_rule__BooleanArrayExpression__Group__012034 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_38_in_rule__BooleanArrayExpression__Group__0__Impl12062 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__BooleanArrayExpression__Group__1__Impl_in_rule__BooleanArrayExpression__Group__112093 = new BitSet(new long[]{0x0000008080000000L});
    public static final BitSet FOLLOW_rule__BooleanArrayExpression__Group__2_in_rule__BooleanArrayExpression__Group__112096 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__BooleanArrayExpression__ValuesAssignment_1_in_rule__BooleanArrayExpression__Group__1__Impl12123 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__BooleanArrayExpression__Group__2__Impl_in_rule__BooleanArrayExpression__Group__212153 = new BitSet(new long[]{0x0000008080000000L});
    public static final BitSet FOLLOW_rule__BooleanArrayExpression__Group__3_in_rule__BooleanArrayExpression__Group__212156 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__BooleanArrayExpression__Group_2__0_in_rule__BooleanArrayExpression__Group__2__Impl12183 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_rule__BooleanArrayExpression__Group__3__Impl_in_rule__BooleanArrayExpression__Group__312214 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_39_in_rule__BooleanArrayExpression__Group__3__Impl12242 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__BooleanArrayExpression__Group_2__0__Impl_in_rule__BooleanArrayExpression__Group_2__012281 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_rule__BooleanArrayExpression__Group_2__1_in_rule__BooleanArrayExpression__Group_2__012284 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_rule__BooleanArrayExpression__Group_2__0__Impl12312 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__BooleanArrayExpression__Group_2__1__Impl_in_rule__BooleanArrayExpression__Group_2__112343 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__BooleanArrayExpression__ValuesAssignment_2_1_in_rule__BooleanArrayExpression__Group_2__1__Impl12370 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumns_in_rule__Model__ColAssignment_112409 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTables_in_rule__Model__TblAssignment_312440 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleWhereEntry_in_rule__Model__WhereEntryAssignment_4_112471 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleGroupByColumns_in_rule__Model__GroupByEntryAssignment_5_112502 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleHavingEntry_in_rule__Model__HavingEntryAssignment_6_112533 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOrderByColumns_in_rule__Model__OrderByEntryAssignment_7_112564 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOrderByColumnFull_in_rule__OrderByColumns__EntriesAssignment_1_1_112595 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumn_in_rule__OrderByColumnFull__ColOrderAssignment_012626 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumn_in_rule__OrderByColumnFull__ColOrderAssignment_1_212657 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleGroupByColumnFull_in_rule__GroupByColumns__EntriesAssignment_1_1_112688 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumn_in_rule__GroupByColumnFull__GroupByColumnAssignment_012719 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumn_in_rule__GroupByColumnFull__GroupByColumnAssignment_1_212750 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnOrAlias_in_rule__Columns__EntriesAssignment_1_1_112781 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnAlias_in_rule__ColumnOrAlias__ColAliasAssignment_1_212812 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnAlias_in_rule__ColumnOrAlias__ColAliasAssignment_2_112843 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumn_in_rule__ColumnFull__ColNameAssignment_012874 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumn_in_rule__ColumnFull__ColNameAssignment_1_212905 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__ColumnAlias__ColAliasAssignment12936 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__Column__ColNameAssignment12967 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTableOrAlias_in_rule__Tables__EntriesAssignment_1_1_112998 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTableAlias_in_rule__TableOrAlias__TblAliasAssignment_1_213029 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTableAlias_in_rule__TableOrAlias__TblAliasAssignment_2_113060 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTable_in_rule__TableFull__TblAssignment_0_213091 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTable_in_rule__TableFull__TblAssignment_113122 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__Table__TblAssignment13153 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__TableAlias__TblAliasAssignment13184 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__Schema__SchemAssignment_0_213215 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__Schema__SchemAssignment_113246 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__Database__DbNameAssignment13277 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAndWhereEntry_in_rule__WhereEntry__EntriesAssignment_1_1_113308 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleConcreteWhereEntry_in_rule__AndWhereEntry__EntriesAssignment_1_1_113339 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAndHavingEntry_in_rule__HavingEntry__EntriesAssignment_1_1_113370 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleConcreteHavingEntry_in_rule__AndHavingEntry__EntriesAssignment_1_1_113401 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__SingleExpressionWhereEntry__NameAssignment_013432 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperator_in_rule__SingleExpressionWhereEntry__OperatorAssignment_113463 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression_in_rule__SingleExpressionWhereEntry__RhsAssignment_213494 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_40_in_rule__ReplacableValue__ValueAssignment13530 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_SIGNED_DOUBLE_in_rule__DoubleExpression__ValueAssignment13569 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_SINGED_LONG_in_rule__LongExpression__ValueAssignment13600 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__StringExpression__ValueAssignment13631 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_41_in_rule__NullExpression__ValueAssignment13667 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_DATE_in_rule__DateExpression__ValueAssignment13706 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_42_in_rule__BooleanExpression__TrueAssignment_013742 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_43_in_rule__BooleanExpression__TrueAssignment_113786 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__MultiExpressionWhereEntry__NameAssignment_013825 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleArrayOperator_in_rule__MultiExpressionWhereEntry__OperatorAssignment_113856 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleArrayExpression_in_rule__MultiExpressionWhereEntry__RhsAssignment_213887 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_SIGNED_DOUBLE_in_rule__DoubleArrayExpression__ValuesAssignment_113918 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_SIGNED_DOUBLE_in_rule__DoubleArrayExpression__ValuesAssignment_2_113949 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_SINGED_LONG_in_rule__LongArrayExpression__ValuesAssignment_113980 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_SINGED_LONG_in_rule__LongArrayExpression__ValuesAssignment_2_114011 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__StringArrayExpression__ValuesAssignment_114042 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__StringArrayExpression__ValuesAssignment_2_114073 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_41_in_rule__NullArrayExpression__ValuesAssignment_114109 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_41_in_rule__NullArrayExpression__ValuesAssignment_2_114153 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_DATE_in_rule__DateArrayExpression__ValuesAssignment_114192 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_DATE_in_rule__DateArrayExpression__ValuesAssignment_2_114223 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_BOOL_in_rule__BooleanArrayExpression__ValuesAssignment_114254 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_BOOL_in_rule__BooleanArrayExpression__ValuesAssignment_2_114285 = new BitSet(new long[]{0x0000000000000002L});

}