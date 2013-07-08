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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "KEYWORD_44", "KEYWORD_42", "KEYWORD_43", "KEYWORD_41", "KEYWORD_39", "KEYWORD_40", "KEYWORD_34", "KEYWORD_35", "KEYWORD_36", "KEYWORD_37", "KEYWORD_38", "KEYWORD_32", "KEYWORD_33", "KEYWORD_30", "KEYWORD_31", "KEYWORD_28", "KEYWORD_29", "KEYWORD_23", "KEYWORD_24", "KEYWORD_25", "KEYWORD_26", "KEYWORD_27", "KEYWORD_19", "KEYWORD_20", "KEYWORD_21", "KEYWORD_22", "KEYWORD_12", "KEYWORD_13", "KEYWORD_14", "KEYWORD_15", "KEYWORD_16", "KEYWORD_17", "KEYWORD_18", "KEYWORD_1", "KEYWORD_2", "KEYWORD_3", "KEYWORD_4", "KEYWORD_5", "KEYWORD_6", "KEYWORD_7", "KEYWORD_8", "KEYWORD_9", "KEYWORD_10", "KEYWORD_11", "RULE_STAR", "RULE_INT", "RULE_DATE", "RULE_TIME", "RULE_TIMESTAMP", "RULE_SIGNED_DOUBLE", "RULE_SL_COMMENT", "RULE_ID", "RULE_STRING", "RULE_ML_COMMENT", "RULE_WS", "RULE_ANY_OTHER"
    };
    public static final int RULE_ID=55;
    public static final int RULE_DATE=50;
    public static final int RULE_ANY_OTHER=59;
    public static final int KEYWORD_19=26;
    public static final int KEYWORD_17=35;
    public static final int KEYWORD_18=36;
    public static final int KEYWORD_15=33;
    public static final int KEYWORD_16=34;
    public static final int KEYWORD_13=31;
    public static final int KEYWORD_14=32;
    public static final int KEYWORD_11=47;
    public static final int KEYWORD_12=30;
    public static final int EOF=-1;
    public static final int KEYWORD_10=46;
    public static final int KEYWORD_6=42;
    public static final int KEYWORD_7=43;
    public static final int KEYWORD_8=44;
    public static final int KEYWORD_9=45;
    public static final int RULE_TIME=51;
    public static final int KEYWORD_28=19;
    public static final int KEYWORD_29=20;
    public static final int RULE_SIGNED_DOUBLE=53;
    public static final int RULE_TIMESTAMP=52;
    public static final int RULE_INT=49;
    public static final int KEYWORD_24=22;
    public static final int KEYWORD_25=23;
    public static final int KEYWORD_26=24;
    public static final int KEYWORD_27=25;
    public static final int KEYWORD_20=27;
    public static final int KEYWORD_21=28;
    public static final int KEYWORD_22=29;
    public static final int KEYWORD_23=21;
    public static final int KEYWORD_30=17;
    public static final int KEYWORD_1=37;
    public static final int KEYWORD_34=10;
    public static final int KEYWORD_5=41;
    public static final int KEYWORD_33=16;
    public static final int KEYWORD_4=40;
    public static final int KEYWORD_32=15;
    public static final int KEYWORD_3=39;
    public static final int KEYWORD_2=38;
    public static final int KEYWORD_31=18;
    public static final int KEYWORD_38=14;
    public static final int KEYWORD_37=13;
    public static final int RULE_SL_COMMENT=54;
    public static final int KEYWORD_36=12;
    public static final int KEYWORD_35=11;
    public static final int RULE_ML_COMMENT=57;
    public static final int KEYWORD_39=8;
    public static final int RULE_STRING=56;
    public static final int RULE_STAR=48;
    public static final int KEYWORD_41=7;
    public static final int KEYWORD_40=9;
    public static final int KEYWORD_43=6;
    public static final int KEYWORD_42=5;
    public static final int KEYWORD_44=4;
    public static final int RULE_WS=58;

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
    		tokenNameToValue.put("KEYWORD_1", "'('");
    		tokenNameToValue.put("KEYWORD_2", "')'");
    		tokenNameToValue.put("KEYWORD_3", "'+'");
    		tokenNameToValue.put("KEYWORD_4", "','");
    		tokenNameToValue.put("KEYWORD_5", "'-'");
    		tokenNameToValue.put("KEYWORD_6", "'.'");
    		tokenNameToValue.put("KEYWORD_7", "'/'");
    		tokenNameToValue.put("KEYWORD_8", "'<'");
    		tokenNameToValue.put("KEYWORD_9", "'='");
    		tokenNameToValue.put("KEYWORD_10", "'>'");
    		tokenNameToValue.put("KEYWORD_11", "'}'");
    		tokenNameToValue.put("KEYWORD_12", "'<='");
    		tokenNameToValue.put("KEYWORD_13", "'<>'");
    		tokenNameToValue.put("KEYWORD_14", "'>='");
    		tokenNameToValue.put("KEYWORD_15", "'AS'");
    		tokenNameToValue.put("KEYWORD_16", "'ON'");
    		tokenNameToValue.put("KEYWORD_17", "'OR'");
    		tokenNameToValue.put("KEYWORD_18", "'||'");
    		tokenNameToValue.put("KEYWORD_19", "'\u0024P{'");
    		tokenNameToValue.put("KEYWORD_20", "'\u0024X{'");
    		tokenNameToValue.put("KEYWORD_21", "'AND'");
    		tokenNameToValue.put("KEYWORD_22", "'ASC'");
    		tokenNameToValue.put("KEYWORD_23", "'\u0024P!{'");
    		tokenNameToValue.put("KEYWORD_24", "'DESC'");
    		tokenNameToValue.put("KEYWORD_25", "'FROM'");
    		tokenNameToValue.put("KEYWORD_26", "'IN ('");
    		tokenNameToValue.put("KEYWORD_27", "'LIKE'");
    		tokenNameToValue.put("KEYWORD_28", "'UNION'");
    		tokenNameToValue.put("KEYWORD_29", "'WHERE'");
    		tokenNameToValue.put("KEYWORD_30", "'HAVING'");
    		tokenNameToValue.put("KEYWORD_31", "'SELECT'");
    		tokenNameToValue.put("KEYWORD_32", "'BETWEEN'");
    		tokenNameToValue.put("KEYWORD_33", "'IS NULL'");
    		tokenNameToValue.put("KEYWORD_34", "'DISTINCT'");
    		tokenNameToValue.put("KEYWORD_35", "'GROUP BY'");
    		tokenNameToValue.put("KEYWORD_36", "'NOT IN ('");
    		tokenNameToValue.put("KEYWORD_37", "'NOT LIKE'");
    		tokenNameToValue.put("KEYWORD_38", "'ORDER BY'");
    		tokenNameToValue.put("KEYWORD_39", "'CROSS JOIN'");
    		tokenNameToValue.put("KEYWORD_40", "'INNER JOIN'");
    		tokenNameToValue.put("KEYWORD_41", "'IS NOT NULL'");
    		tokenNameToValue.put("KEYWORD_42", "'FULL OUTER JOIN'");
    		tokenNameToValue.put("KEYWORD_43", "'LEFT OUTER JOIN'");
    		tokenNameToValue.put("KEYWORD_44", "'RIGHT OUTER JOIN'");
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:107:1: entryRuleModel : ruleModel EOF ;
    public final void entryRuleModel() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:108:1: ( ruleModel EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:109:1: ruleModel EOF
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:116:1: ruleModel : ( ( rule__Model__Group__0 ) ) ;
    public final void ruleModel() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:120:5: ( ( ( rule__Model__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:121:1: ( ( rule__Model__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:121:1: ( ( rule__Model__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:122:1: ( rule__Model__Group__0 )
            {
             before(grammarAccess.getModelAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:123:1: ( rule__Model__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:123:2: rule__Model__Group__0
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:135:1: entryRuleSelect : ruleSelect EOF ;
    public final void entryRuleSelect() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:136:1: ( ruleSelect EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:137:1: ruleSelect EOF
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:144:1: ruleSelect : ( ( rule__Select__Group__0 ) ) ;
    public final void ruleSelect() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:148:5: ( ( ( rule__Select__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:149:1: ( ( rule__Select__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:149:1: ( ( rule__Select__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:150:1: ( rule__Select__Group__0 )
            {
             before(grammarAccess.getSelectAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:151:1: ( rule__Select__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:151:2: rule__Select__Group__0
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:163:1: entryRuleColumns : ruleColumns EOF ;
    public final void entryRuleColumns() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:164:1: ( ruleColumns EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:165:1: ruleColumns EOF
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:172:1: ruleColumns : ( ( rule__Columns__Group__0 ) ) ;
    public final void ruleColumns() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:176:5: ( ( ( rule__Columns__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:177:1: ( ( rule__Columns__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:177:1: ( ( rule__Columns__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:178:1: ( rule__Columns__Group__0 )
            {
             before(grammarAccess.getColumnsAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:179:1: ( rule__Columns__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:179:2: rule__Columns__Group__0
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:191:1: entryRuleColumnOrAlias : ruleColumnOrAlias EOF ;
    public final void entryRuleColumnOrAlias() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:192:1: ( ruleColumnOrAlias EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:193:1: ruleColumnOrAlias EOF
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:200:1: ruleColumnOrAlias : ( ( rule__ColumnOrAlias__Alternatives ) ) ;
    public final void ruleColumnOrAlias() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:204:5: ( ( ( rule__ColumnOrAlias__Alternatives ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:205:1: ( ( rule__ColumnOrAlias__Alternatives ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:205:1: ( ( rule__ColumnOrAlias__Alternatives ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:206:1: ( rule__ColumnOrAlias__Alternatives )
            {
             before(grammarAccess.getColumnOrAliasAccess().getAlternatives()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:207:1: ( rule__ColumnOrAlias__Alternatives )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:207:2: rule__ColumnOrAlias__Alternatives
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:219:1: entryRuleColumnFull : ruleColumnFull EOF ;
    public final void entryRuleColumnFull() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:220:1: ( ruleColumnFull EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:221:1: ruleColumnFull EOF
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:228:1: ruleColumnFull : ( ( rule__ColumnFull__Group__0 ) ) ;
    public final void ruleColumnFull() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:232:5: ( ( ( rule__ColumnFull__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:233:1: ( ( rule__ColumnFull__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:233:1: ( ( rule__ColumnFull__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:234:1: ( rule__ColumnFull__Group__0 )
            {
             before(grammarAccess.getColumnFullAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:235:1: ( rule__ColumnFull__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:235:2: rule__ColumnFull__Group__0
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


    // $ANTLR start "entryRuleTables"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:247:1: entryRuleTables : ruleTables EOF ;
    public final void entryRuleTables() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:248:1: ( ruleTables EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:249:1: ruleTables EOF
            {
             before(grammarAccess.getTablesRule()); 
            pushFollow(FOLLOW_ruleTables_in_entryRuleTables374);
            ruleTables();

            state._fsp--;

             after(grammarAccess.getTablesRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleTables381); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:256:1: ruleTables : ( ( rule__Tables__Group__0 ) ) ;
    public final void ruleTables() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:260:5: ( ( ( rule__Tables__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:261:1: ( ( rule__Tables__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:261:1: ( ( rule__Tables__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:262:1: ( rule__Tables__Group__0 )
            {
             before(grammarAccess.getTablesAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:263:1: ( rule__Tables__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:263:2: rule__Tables__Group__0
            {
            pushFollow(FOLLOW_rule__Tables__Group__0_in_ruleTables411);
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


    // $ANTLR start "entryRuleFromTable"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:275:1: entryRuleFromTable : ruleFromTable EOF ;
    public final void entryRuleFromTable() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:276:1: ( ruleFromTable EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:277:1: ruleFromTable EOF
            {
             before(grammarAccess.getFromTableRule()); 
            pushFollow(FOLLOW_ruleFromTable_in_entryRuleFromTable438);
            ruleFromTable();

            state._fsp--;

             after(grammarAccess.getFromTableRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleFromTable445); 

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
    // $ANTLR end "entryRuleFromTable"


    // $ANTLR start "ruleFromTable"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:284:1: ruleFromTable : ( ( rule__FromTable__Group__0 ) ) ;
    public final void ruleFromTable() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:288:5: ( ( ( rule__FromTable__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:289:1: ( ( rule__FromTable__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:289:1: ( ( rule__FromTable__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:290:1: ( rule__FromTable__Group__0 )
            {
             before(grammarAccess.getFromTableAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:291:1: ( rule__FromTable__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:291:2: rule__FromTable__Group__0
            {
            pushFollow(FOLLOW_rule__FromTable__Group__0_in_ruleFromTable475);
            rule__FromTable__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getFromTableAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleFromTable"


    // $ANTLR start "entryRuleTableOrAlias"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:303:1: entryRuleTableOrAlias : ruleTableOrAlias EOF ;
    public final void entryRuleTableOrAlias() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:304:1: ( ruleTableOrAlias EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:305:1: ruleTableOrAlias EOF
            {
             before(grammarAccess.getTableOrAliasRule()); 
            pushFollow(FOLLOW_ruleTableOrAlias_in_entryRuleTableOrAlias502);
            ruleTableOrAlias();

            state._fsp--;

             after(grammarAccess.getTableOrAliasRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleTableOrAlias509); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:312:1: ruleTableOrAlias : ( ( rule__TableOrAlias__Group__0 ) ) ;
    public final void ruleTableOrAlias() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:316:5: ( ( ( rule__TableOrAlias__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:317:1: ( ( rule__TableOrAlias__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:317:1: ( ( rule__TableOrAlias__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:318:1: ( rule__TableOrAlias__Group__0 )
            {
             before(grammarAccess.getTableOrAliasAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:319:1: ( rule__TableOrAlias__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:319:2: rule__TableOrAlias__Group__0
            {
            pushFollow(FOLLOW_rule__TableOrAlias__Group__0_in_ruleTableOrAlias539);
            rule__TableOrAlias__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getTableOrAliasAccess().getGroup()); 

            }


            }

        }
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:331:1: entryRuleTableFull : ruleTableFull EOF ;
    public final void entryRuleTableFull() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:332:1: ( ruleTableFull EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:333:1: ruleTableFull EOF
            {
             before(grammarAccess.getTableFullRule()); 
            pushFollow(FOLLOW_ruleTableFull_in_entryRuleTableFull566);
            ruleTableFull();

            state._fsp--;

             after(grammarAccess.getTableFullRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleTableFull573); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:340:1: ruleTableFull : ( ( rule__TableFull__Group__0 ) ) ;
    public final void ruleTableFull() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:344:5: ( ( ( rule__TableFull__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:345:1: ( ( rule__TableFull__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:345:1: ( ( rule__TableFull__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:346:1: ( rule__TableFull__Group__0 )
            {
             before(grammarAccess.getTableFullAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:347:1: ( rule__TableFull__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:347:2: rule__TableFull__Group__0
            {
            pushFollow(FOLLOW_rule__TableFull__Group__0_in_ruleTableFull603);
            rule__TableFull__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getTableFullAccess().getGroup()); 

            }


            }

        }
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


    // $ANTLR start "entryRuleDbObjectName"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:359:1: entryRuleDbObjectName : ruleDbObjectName EOF ;
    public final void entryRuleDbObjectName() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:360:1: ( ruleDbObjectName EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:361:1: ruleDbObjectName EOF
            {
             before(grammarAccess.getDbObjectNameRule()); 
            pushFollow(FOLLOW_ruleDbObjectName_in_entryRuleDbObjectName630);
            ruleDbObjectName();

            state._fsp--;

             after(grammarAccess.getDbObjectNameRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleDbObjectName637); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:368:1: ruleDbObjectName : ( ( rule__DbObjectName__DbnameAssignment ) ) ;
    public final void ruleDbObjectName() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:372:5: ( ( ( rule__DbObjectName__DbnameAssignment ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:373:1: ( ( rule__DbObjectName__DbnameAssignment ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:373:1: ( ( rule__DbObjectName__DbnameAssignment ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:374:1: ( rule__DbObjectName__DbnameAssignment )
            {
             before(grammarAccess.getDbObjectNameAccess().getDbnameAssignment()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:375:1: ( rule__DbObjectName__DbnameAssignment )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:375:2: rule__DbObjectName__DbnameAssignment
            {
            pushFollow(FOLLOW_rule__DbObjectName__DbnameAssignment_in_ruleDbObjectName667);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:387:1: entryRuleOrderByColumns : ruleOrderByColumns EOF ;
    public final void entryRuleOrderByColumns() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:388:1: ( ruleOrderByColumns EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:389:1: ruleOrderByColumns EOF
            {
             before(grammarAccess.getOrderByColumnsRule()); 
            pushFollow(FOLLOW_ruleOrderByColumns_in_entryRuleOrderByColumns694);
            ruleOrderByColumns();

            state._fsp--;

             after(grammarAccess.getOrderByColumnsRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOrderByColumns701); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:396:1: ruleOrderByColumns : ( ( rule__OrderByColumns__Group__0 ) ) ;
    public final void ruleOrderByColumns() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:400:5: ( ( ( rule__OrderByColumns__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:401:1: ( ( rule__OrderByColumns__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:401:1: ( ( rule__OrderByColumns__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:402:1: ( rule__OrderByColumns__Group__0 )
            {
             before(grammarAccess.getOrderByColumnsAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:403:1: ( rule__OrderByColumns__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:403:2: rule__OrderByColumns__Group__0
            {
            pushFollow(FOLLOW_rule__OrderByColumns__Group__0_in_ruleOrderByColumns731);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:415:1: entryRuleOrderByColumnFull : ruleOrderByColumnFull EOF ;
    public final void entryRuleOrderByColumnFull() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:416:1: ( ruleOrderByColumnFull EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:417:1: ruleOrderByColumnFull EOF
            {
             before(grammarAccess.getOrderByColumnFullRule()); 
            pushFollow(FOLLOW_ruleOrderByColumnFull_in_entryRuleOrderByColumnFull758);
            ruleOrderByColumnFull();

            state._fsp--;

             after(grammarAccess.getOrderByColumnFullRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOrderByColumnFull765); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:424:1: ruleOrderByColumnFull : ( ( rule__OrderByColumnFull__Group__0 ) ) ;
    public final void ruleOrderByColumnFull() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:428:5: ( ( ( rule__OrderByColumnFull__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:429:1: ( ( rule__OrderByColumnFull__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:429:1: ( ( rule__OrderByColumnFull__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:430:1: ( rule__OrderByColumnFull__Group__0 )
            {
             before(grammarAccess.getOrderByColumnFullAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:431:1: ( rule__OrderByColumnFull__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:431:2: rule__OrderByColumnFull__Group__0
            {
            pushFollow(FOLLOW_rule__OrderByColumnFull__Group__0_in_ruleOrderByColumnFull795);
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


    // $ANTLR start "entryRuleGroupByColumns"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:443:1: entryRuleGroupByColumns : ruleGroupByColumns EOF ;
    public final void entryRuleGroupByColumns() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:444:1: ( ruleGroupByColumns EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:445:1: ruleGroupByColumns EOF
            {
             before(grammarAccess.getGroupByColumnsRule()); 
            pushFollow(FOLLOW_ruleGroupByColumns_in_entryRuleGroupByColumns822);
            ruleGroupByColumns();

            state._fsp--;

             after(grammarAccess.getGroupByColumnsRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleGroupByColumns829); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:452:1: ruleGroupByColumns : ( ( rule__GroupByColumns__Group__0 ) ) ;
    public final void ruleGroupByColumns() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:456:5: ( ( ( rule__GroupByColumns__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:457:1: ( ( rule__GroupByColumns__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:457:1: ( ( rule__GroupByColumns__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:458:1: ( rule__GroupByColumns__Group__0 )
            {
             before(grammarAccess.getGroupByColumnsAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:459:1: ( rule__GroupByColumns__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:459:2: rule__GroupByColumns__Group__0
            {
            pushFollow(FOLLOW_rule__GroupByColumns__Group__0_in_ruleGroupByColumns859);
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


    // $ANTLR start "entryRuleFullExpression"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:471:1: entryRuleFullExpression : ruleFullExpression EOF ;
    public final void entryRuleFullExpression() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:472:1: ( ruleFullExpression EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:473:1: ruleFullExpression EOF
            {
             before(grammarAccess.getFullExpressionRule()); 
            pushFollow(FOLLOW_ruleFullExpression_in_entryRuleFullExpression886);
            ruleFullExpression();

            state._fsp--;

             after(grammarAccess.getFullExpressionRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleFullExpression893); 

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
    // $ANTLR end "entryRuleFullExpression"


    // $ANTLR start "ruleFullExpression"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:480:1: ruleFullExpression : ( ( rule__FullExpression__Group__0 ) ) ;
    public final void ruleFullExpression() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:484:5: ( ( ( rule__FullExpression__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:485:1: ( ( rule__FullExpression__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:485:1: ( ( rule__FullExpression__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:486:1: ( rule__FullExpression__Group__0 )
            {
             before(grammarAccess.getFullExpressionAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:487:1: ( rule__FullExpression__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:487:2: rule__FullExpression__Group__0
            {
            pushFollow(FOLLOW_rule__FullExpression__Group__0_in_ruleFullExpression923);
            rule__FullExpression__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getFullExpressionAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleFullExpression"


    // $ANTLR start "entryRuleExpressionFragment"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:499:1: entryRuleExpressionFragment : ruleExpressionFragment EOF ;
    public final void entryRuleExpressionFragment() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:500:1: ( ruleExpressionFragment EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:501:1: ruleExpressionFragment EOF
            {
             before(grammarAccess.getExpressionFragmentRule()); 
            pushFollow(FOLLOW_ruleExpressionFragment_in_entryRuleExpressionFragment950);
            ruleExpressionFragment();

            state._fsp--;

             after(grammarAccess.getExpressionFragmentRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpressionFragment957); 

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
    // $ANTLR end "entryRuleExpressionFragment"


    // $ANTLR start "ruleExpressionFragment"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:508:1: ruleExpressionFragment : ( ( rule__ExpressionFragment__Alternatives ) ) ;
    public final void ruleExpressionFragment() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:512:5: ( ( ( rule__ExpressionFragment__Alternatives ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:513:1: ( ( rule__ExpressionFragment__Alternatives ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:513:1: ( ( rule__ExpressionFragment__Alternatives ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:514:1: ( rule__ExpressionFragment__Alternatives )
            {
             before(grammarAccess.getExpressionFragmentAccess().getAlternatives()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:515:1: ( rule__ExpressionFragment__Alternatives )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:515:2: rule__ExpressionFragment__Alternatives
            {
            pushFollow(FOLLOW_rule__ExpressionFragment__Alternatives_in_ruleExpressionFragment987);
            rule__ExpressionFragment__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getExpressionFragmentAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleExpressionFragment"


    // $ANTLR start "entryRuleExpressionGroup"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:527:1: entryRuleExpressionGroup : ruleExpressionGroup EOF ;
    public final void entryRuleExpressionGroup() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:528:1: ( ruleExpressionGroup EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:529:1: ruleExpressionGroup EOF
            {
             before(grammarAccess.getExpressionGroupRule()); 
            pushFollow(FOLLOW_ruleExpressionGroup_in_entryRuleExpressionGroup1014);
            ruleExpressionGroup();

            state._fsp--;

             after(grammarAccess.getExpressionGroupRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpressionGroup1021); 

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
    // $ANTLR end "entryRuleExpressionGroup"


    // $ANTLR start "ruleExpressionGroup"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:536:1: ruleExpressionGroup : ( ( rule__ExpressionGroup__Group__0 ) ) ;
    public final void ruleExpressionGroup() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:540:5: ( ( ( rule__ExpressionGroup__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:541:1: ( ( rule__ExpressionGroup__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:541:1: ( ( rule__ExpressionGroup__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:542:1: ( rule__ExpressionGroup__Group__0 )
            {
             before(grammarAccess.getExpressionGroupAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:543:1: ( rule__ExpressionGroup__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:543:2: rule__ExpressionGroup__Group__0
            {
            pushFollow(FOLLOW_rule__ExpressionGroup__Group__0_in_ruleExpressionGroup1051);
            rule__ExpressionGroup__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getExpressionGroupAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleExpressionGroup"


    // $ANTLR start "entryRuleXExpression"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:555:1: entryRuleXExpression : ruleXExpression EOF ;
    public final void entryRuleXExpression() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:556:1: ( ruleXExpression EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:557:1: ruleXExpression EOF
            {
             before(grammarAccess.getXExpressionRule()); 
            pushFollow(FOLLOW_ruleXExpression_in_entryRuleXExpression1078);
            ruleXExpression();

            state._fsp--;

             after(grammarAccess.getXExpressionRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleXExpression1085); 

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
    // $ANTLR end "entryRuleXExpression"


    // $ANTLR start "ruleXExpression"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:564:1: ruleXExpression : ( ( rule__XExpression__Group__0 ) ) ;
    public final void ruleXExpression() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:568:5: ( ( ( rule__XExpression__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:569:1: ( ( rule__XExpression__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:569:1: ( ( rule__XExpression__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:570:1: ( rule__XExpression__Group__0 )
            {
             before(grammarAccess.getXExpressionAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:571:1: ( rule__XExpression__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:571:2: rule__XExpression__Group__0
            {
            pushFollow(FOLLOW_rule__XExpression__Group__0_in_ruleXExpression1115);
            rule__XExpression__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getXExpressionAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleXExpression"


    // $ANTLR start "entryRuleExpression"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:583:1: entryRuleExpression : ruleExpression EOF ;
    public final void entryRuleExpression() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:584:1: ( ruleExpression EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:585:1: ruleExpression EOF
            {
             before(grammarAccess.getExpressionRule()); 
            pushFollow(FOLLOW_ruleExpression_in_entryRuleExpression1142);
            ruleExpression();

            state._fsp--;

             after(grammarAccess.getExpressionRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpression1149); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:592:1: ruleExpression : ( ( rule__Expression__Group__0 ) ) ;
    public final void ruleExpression() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:596:5: ( ( ( rule__Expression__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:597:1: ( ( rule__Expression__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:597:1: ( ( rule__Expression__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:598:1: ( rule__Expression__Group__0 )
            {
             before(grammarAccess.getExpressionAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:599:1: ( rule__Expression__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:599:2: rule__Expression__Group__0
            {
            pushFollow(FOLLOW_rule__Expression__Group__0_in_ruleExpression1179);
            rule__Expression__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getExpressionAccess().getGroup()); 

            }


            }

        }
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


    // $ANTLR start "entryRuleComparison"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:611:1: entryRuleComparison : ruleComparison EOF ;
    public final void entryRuleComparison() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:612:1: ( ruleComparison EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:613:1: ruleComparison EOF
            {
             before(grammarAccess.getComparisonRule()); 
            pushFollow(FOLLOW_ruleComparison_in_entryRuleComparison1206);
            ruleComparison();

            state._fsp--;

             after(grammarAccess.getComparisonRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleComparison1213); 

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
    // $ANTLR end "entryRuleComparison"


    // $ANTLR start "ruleComparison"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:620:1: ruleComparison : ( ( rule__Comparison__Group__0 ) ) ;
    public final void ruleComparison() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:624:5: ( ( ( rule__Comparison__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:625:1: ( ( rule__Comparison__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:625:1: ( ( rule__Comparison__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:626:1: ( rule__Comparison__Group__0 )
            {
             before(grammarAccess.getComparisonAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:627:1: ( rule__Comparison__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:627:2: rule__Comparison__Group__0
            {
            pushFollow(FOLLOW_rule__Comparison__Group__0_in_ruleComparison1243);
            rule__Comparison__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getComparisonAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleComparison"


    // $ANTLR start "entryRuleLike"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:639:1: entryRuleLike : ruleLike EOF ;
    public final void entryRuleLike() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:640:1: ( ruleLike EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:641:1: ruleLike EOF
            {
             before(grammarAccess.getLikeRule()); 
            pushFollow(FOLLOW_ruleLike_in_entryRuleLike1270);
            ruleLike();

            state._fsp--;

             after(grammarAccess.getLikeRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleLike1277); 

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
    // $ANTLR end "entryRuleLike"


    // $ANTLR start "ruleLike"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:648:1: ruleLike : ( ( rule__Like__Group__0 ) ) ;
    public final void ruleLike() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:652:5: ( ( ( rule__Like__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:653:1: ( ( rule__Like__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:653:1: ( ( rule__Like__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:654:1: ( rule__Like__Group__0 )
            {
             before(grammarAccess.getLikeAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:655:1: ( rule__Like__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:655:2: rule__Like__Group__0
            {
            pushFollow(FOLLOW_rule__Like__Group__0_in_ruleLike1307);
            rule__Like__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getLikeAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleLike"


    // $ANTLR start "entryRuleBetween"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:667:1: entryRuleBetween : ruleBetween EOF ;
    public final void entryRuleBetween() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:668:1: ( ruleBetween EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:669:1: ruleBetween EOF
            {
             before(grammarAccess.getBetweenRule()); 
            pushFollow(FOLLOW_ruleBetween_in_entryRuleBetween1334);
            ruleBetween();

            state._fsp--;

             after(grammarAccess.getBetweenRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleBetween1341); 

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
    // $ANTLR end "entryRuleBetween"


    // $ANTLR start "ruleBetween"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:676:1: ruleBetween : ( ( rule__Between__Group__0 ) ) ;
    public final void ruleBetween() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:680:5: ( ( ( rule__Between__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:681:1: ( ( rule__Between__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:681:1: ( ( rule__Between__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:682:1: ( rule__Between__Group__0 )
            {
             before(grammarAccess.getBetweenAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:683:1: ( rule__Between__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:683:2: rule__Between__Group__0
            {
            pushFollow(FOLLOW_rule__Between__Group__0_in_ruleBetween1371);
            rule__Between__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getBetweenAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleBetween"


    // $ANTLR start "entryRuleInOperator"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:695:1: entryRuleInOperator : ruleInOperator EOF ;
    public final void entryRuleInOperator() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:696:1: ( ruleInOperator EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:697:1: ruleInOperator EOF
            {
             before(grammarAccess.getInOperatorRule()); 
            pushFollow(FOLLOW_ruleInOperator_in_entryRuleInOperator1398);
            ruleInOperator();

            state._fsp--;

             after(grammarAccess.getInOperatorRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleInOperator1405); 

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
    // $ANTLR end "entryRuleInOperator"


    // $ANTLR start "ruleInOperator"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:704:1: ruleInOperator : ( ( rule__InOperator__Group__0 ) ) ;
    public final void ruleInOperator() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:708:5: ( ( ( rule__InOperator__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:709:1: ( ( rule__InOperator__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:709:1: ( ( rule__InOperator__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:710:1: ( rule__InOperator__Group__0 )
            {
             before(grammarAccess.getInOperatorAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:711:1: ( rule__InOperator__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:711:2: rule__InOperator__Group__0
            {
            pushFollow(FOLLOW_rule__InOperator__Group__0_in_ruleInOperator1435);
            rule__InOperator__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getInOperatorAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleInOperator"


    // $ANTLR start "entryRuleOperand"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:723:1: entryRuleOperand : ruleOperand EOF ;
    public final void entryRuleOperand() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:724:1: ( ruleOperand EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:725:1: ruleOperand EOF
            {
             before(grammarAccess.getOperandRule()); 
            pushFollow(FOLLOW_ruleOperand_in_entryRuleOperand1462);
            ruleOperand();

            state._fsp--;

             after(grammarAccess.getOperandRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOperand1469); 

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
    // $ANTLR end "entryRuleOperand"


    // $ANTLR start "ruleOperand"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:732:1: ruleOperand : ( ( rule__Operand__Group__0 ) ) ;
    public final void ruleOperand() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:736:5: ( ( ( rule__Operand__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:737:1: ( ( rule__Operand__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:737:1: ( ( rule__Operand__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:738:1: ( rule__Operand__Group__0 )
            {
             before(grammarAccess.getOperandAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:739:1: ( rule__Operand__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:739:2: rule__Operand__Group__0
            {
            pushFollow(FOLLOW_rule__Operand__Group__0_in_ruleOperand1499);
            rule__Operand__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getOperandAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleOperand"


    // $ANTLR start "entryRuleOperandFragment"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:751:1: entryRuleOperandFragment : ruleOperandFragment EOF ;
    public final void entryRuleOperandFragment() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:752:1: ( ruleOperandFragment EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:753:1: ruleOperandFragment EOF
            {
             before(grammarAccess.getOperandFragmentRule()); 
            pushFollow(FOLLOW_ruleOperandFragment_in_entryRuleOperandFragment1526);
            ruleOperandFragment();

            state._fsp--;

             after(grammarAccess.getOperandFragmentRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOperandFragment1533); 

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
    // $ANTLR end "entryRuleOperandFragment"


    // $ANTLR start "ruleOperandFragment"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:760:1: ruleOperandFragment : ( ( rule__OperandFragment__Alternatives ) ) ;
    public final void ruleOperandFragment() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:764:5: ( ( ( rule__OperandFragment__Alternatives ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:765:1: ( ( rule__OperandFragment__Alternatives ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:765:1: ( ( rule__OperandFragment__Alternatives ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:766:1: ( rule__OperandFragment__Alternatives )
            {
             before(grammarAccess.getOperandFragmentAccess().getAlternatives()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:767:1: ( rule__OperandFragment__Alternatives )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:767:2: rule__OperandFragment__Alternatives
            {
            pushFollow(FOLLOW_rule__OperandFragment__Alternatives_in_ruleOperandFragment1563);
            rule__OperandFragment__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getOperandFragmentAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleOperandFragment"


    // $ANTLR start "entryRuleXOperandFragment"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:781:1: entryRuleXOperandFragment : ruleXOperandFragment EOF ;
    public final void entryRuleXOperandFragment() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:782:1: ( ruleXOperandFragment EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:783:1: ruleXOperandFragment EOF
            {
             before(grammarAccess.getXOperandFragmentRule()); 
            pushFollow(FOLLOW_ruleXOperandFragment_in_entryRuleXOperandFragment1592);
            ruleXOperandFragment();

            state._fsp--;

             after(grammarAccess.getXOperandFragmentRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleXOperandFragment1599); 

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
    // $ANTLR end "entryRuleXOperandFragment"


    // $ANTLR start "ruleXOperandFragment"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:790:1: ruleXOperandFragment : ( ( rule__XOperandFragment__Alternatives ) ) ;
    public final void ruleXOperandFragment() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:794:5: ( ( ( rule__XOperandFragment__Alternatives ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:795:1: ( ( rule__XOperandFragment__Alternatives ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:795:1: ( ( rule__XOperandFragment__Alternatives ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:796:1: ( rule__XOperandFragment__Alternatives )
            {
             before(grammarAccess.getXOperandFragmentAccess().getAlternatives()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:797:1: ( rule__XOperandFragment__Alternatives )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:797:2: rule__XOperandFragment__Alternatives
            {
            pushFollow(FOLLOW_rule__XOperandFragment__Alternatives_in_ruleXOperandFragment1629);
            rule__XOperandFragment__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getXOperandFragmentAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleXOperandFragment"


    // $ANTLR start "entryRuleParameterOperand"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:809:1: entryRuleParameterOperand : ruleParameterOperand EOF ;
    public final void entryRuleParameterOperand() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:810:1: ( ruleParameterOperand EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:811:1: ruleParameterOperand EOF
            {
             before(grammarAccess.getParameterOperandRule()); 
            pushFollow(FOLLOW_ruleParameterOperand_in_entryRuleParameterOperand1656);
            ruleParameterOperand();

            state._fsp--;

             after(grammarAccess.getParameterOperandRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleParameterOperand1663); 

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
    // $ANTLR end "entryRuleParameterOperand"


    // $ANTLR start "ruleParameterOperand"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:818:1: ruleParameterOperand : ( ( rule__ParameterOperand__Group__0 ) ) ;
    public final void ruleParameterOperand() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:822:5: ( ( ( rule__ParameterOperand__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:823:1: ( ( rule__ParameterOperand__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:823:1: ( ( rule__ParameterOperand__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:824:1: ( rule__ParameterOperand__Group__0 )
            {
             before(grammarAccess.getParameterOperandAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:825:1: ( rule__ParameterOperand__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:825:2: rule__ParameterOperand__Group__0
            {
            pushFollow(FOLLOW_rule__ParameterOperand__Group__0_in_ruleParameterOperand1693);
            rule__ParameterOperand__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getParameterOperandAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleParameterOperand"


    // $ANTLR start "entryRuleExclamationParameterOperand"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:837:1: entryRuleExclamationParameterOperand : ruleExclamationParameterOperand EOF ;
    public final void entryRuleExclamationParameterOperand() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:838:1: ( ruleExclamationParameterOperand EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:839:1: ruleExclamationParameterOperand EOF
            {
             before(grammarAccess.getExclamationParameterOperandRule()); 
            pushFollow(FOLLOW_ruleExclamationParameterOperand_in_entryRuleExclamationParameterOperand1720);
            ruleExclamationParameterOperand();

            state._fsp--;

             after(grammarAccess.getExclamationParameterOperandRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleExclamationParameterOperand1727); 

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
    // $ANTLR end "entryRuleExclamationParameterOperand"


    // $ANTLR start "ruleExclamationParameterOperand"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:846:1: ruleExclamationParameterOperand : ( ( rule__ExclamationParameterOperand__Group__0 ) ) ;
    public final void ruleExclamationParameterOperand() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:850:5: ( ( ( rule__ExclamationParameterOperand__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:851:1: ( ( rule__ExclamationParameterOperand__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:851:1: ( ( rule__ExclamationParameterOperand__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:852:1: ( rule__ExclamationParameterOperand__Group__0 )
            {
             before(grammarAccess.getExclamationParameterOperandAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:853:1: ( rule__ExclamationParameterOperand__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:853:2: rule__ExclamationParameterOperand__Group__0
            {
            pushFollow(FOLLOW_rule__ExclamationParameterOperand__Group__0_in_ruleExclamationParameterOperand1757);
            rule__ExclamationParameterOperand__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getExclamationParameterOperandAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleExclamationParameterOperand"


    // $ANTLR start "entryRuleColumnOperand"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:865:1: entryRuleColumnOperand : ruleColumnOperand EOF ;
    public final void entryRuleColumnOperand() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:866:1: ( ruleColumnOperand EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:867:1: ruleColumnOperand EOF
            {
             before(grammarAccess.getColumnOperandRule()); 
            pushFollow(FOLLOW_ruleColumnOperand_in_entryRuleColumnOperand1784);
            ruleColumnOperand();

            state._fsp--;

             after(grammarAccess.getColumnOperandRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleColumnOperand1791); 

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
    // $ANTLR end "entryRuleColumnOperand"


    // $ANTLR start "ruleColumnOperand"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:874:1: ruleColumnOperand : ( ruleColumnFull ) ;
    public final void ruleColumnOperand() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:878:5: ( ( ruleColumnFull ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:879:1: ( ruleColumnFull )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:879:1: ( ruleColumnFull )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:880:1: ruleColumnFull
            {
             before(grammarAccess.getColumnOperandAccess().getColumnFullParserRuleCall()); 
            pushFollow(FOLLOW_ruleColumnFull_in_ruleColumnOperand1821);
            ruleColumnFull();

            state._fsp--;

             after(grammarAccess.getColumnOperandAccess().getColumnFullParserRuleCall()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleColumnOperand"


    // $ANTLR start "entryRuleSubQueryOperand"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:893:1: entryRuleSubQueryOperand : ruleSubQueryOperand EOF ;
    public final void entryRuleSubQueryOperand() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:894:1: ( ruleSubQueryOperand EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:895:1: ruleSubQueryOperand EOF
            {
             before(grammarAccess.getSubQueryOperandRule()); 
            pushFollow(FOLLOW_ruleSubQueryOperand_in_entryRuleSubQueryOperand1847);
            ruleSubQueryOperand();

            state._fsp--;

             after(grammarAccess.getSubQueryOperandRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSubQueryOperand1854); 

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
    // $ANTLR end "entryRuleSubQueryOperand"


    // $ANTLR start "ruleSubQueryOperand"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:902:1: ruleSubQueryOperand : ( ( rule__SubQueryOperand__Group__0 ) ) ;
    public final void ruleSubQueryOperand() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:906:5: ( ( ( rule__SubQueryOperand__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:907:1: ( ( rule__SubQueryOperand__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:907:1: ( ( rule__SubQueryOperand__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:908:1: ( rule__SubQueryOperand__Group__0 )
            {
             before(grammarAccess.getSubQueryOperandAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:909:1: ( rule__SubQueryOperand__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:909:2: rule__SubQueryOperand__Group__0
            {
            pushFollow(FOLLOW_rule__SubQueryOperand__Group__0_in_ruleSubQueryOperand1884);
            rule__SubQueryOperand__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getSubQueryOperandAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleSubQueryOperand"


    // $ANTLR start "entryRuleScalarOperand"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:921:1: entryRuleScalarOperand : ruleScalarOperand EOF ;
    public final void entryRuleScalarOperand() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:922:1: ( ruleScalarOperand EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:923:1: ruleScalarOperand EOF
            {
             before(grammarAccess.getScalarOperandRule()); 
            pushFollow(FOLLOW_ruleScalarOperand_in_entryRuleScalarOperand1911);
            ruleScalarOperand();

            state._fsp--;

             after(grammarAccess.getScalarOperandRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleScalarOperand1918); 

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
    // $ANTLR end "entryRuleScalarOperand"


    // $ANTLR start "ruleScalarOperand"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:930:1: ruleScalarOperand : ( ( rule__ScalarOperand__Alternatives ) ) ;
    public final void ruleScalarOperand() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:934:5: ( ( ( rule__ScalarOperand__Alternatives ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:935:1: ( ( rule__ScalarOperand__Alternatives ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:935:1: ( ( rule__ScalarOperand__Alternatives ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:936:1: ( rule__ScalarOperand__Alternatives )
            {
             before(grammarAccess.getScalarOperandAccess().getAlternatives()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:937:1: ( rule__ScalarOperand__Alternatives )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:937:2: rule__ScalarOperand__Alternatives
            {
            pushFollow(FOLLOW_rule__ScalarOperand__Alternatives_in_ruleScalarOperand1948);
            rule__ScalarOperand__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getScalarOperandAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleScalarOperand"


    // $ANTLR start "entryRuleStringOperand"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:949:1: entryRuleStringOperand : ruleStringOperand EOF ;
    public final void entryRuleStringOperand() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:950:1: ( ruleStringOperand EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:951:1: ruleStringOperand EOF
            {
             before(grammarAccess.getStringOperandRule()); 
            pushFollow(FOLLOW_ruleStringOperand_in_entryRuleStringOperand1975);
            ruleStringOperand();

            state._fsp--;

             after(grammarAccess.getStringOperandRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleStringOperand1982); 

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
    // $ANTLR end "entryRuleStringOperand"


    // $ANTLR start "ruleStringOperand"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:958:1: ruleStringOperand : ( RULE_STRING ) ;
    public final void ruleStringOperand() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:962:5: ( ( RULE_STRING ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:963:1: ( RULE_STRING )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:963:1: ( RULE_STRING )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:964:1: RULE_STRING
            {
             before(grammarAccess.getStringOperandAccess().getSTRINGTerminalRuleCall()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleStringOperand2012); 
             after(grammarAccess.getStringOperandAccess().getSTRINGTerminalRuleCall()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleStringOperand"


    // $ANTLR start "ruleJoinType"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:978:1: ruleJoinType : ( ( rule__JoinType__Alternatives ) ) ;
    public final void ruleJoinType() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:982:1: ( ( ( rule__JoinType__Alternatives ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:983:1: ( ( rule__JoinType__Alternatives ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:983:1: ( ( rule__JoinType__Alternatives ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:984:1: ( rule__JoinType__Alternatives )
            {
             before(grammarAccess.getJoinTypeAccess().getAlternatives()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:985:1: ( rule__JoinType__Alternatives )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:985:2: rule__JoinType__Alternatives
            {
            pushFollow(FOLLOW_rule__JoinType__Alternatives_in_ruleJoinType2048);
            rule__JoinType__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getJoinTypeAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleJoinType"


    // $ANTLR start "rule__ColumnOrAlias__Alternatives"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:996:1: rule__ColumnOrAlias__Alternatives : ( ( ( rule__ColumnOrAlias__Group_0__0 ) ) | ( ( rule__ColumnOrAlias__AllColsAssignment_1 ) ) );
    public final void rule__ColumnOrAlias__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1000:1: ( ( ( rule__ColumnOrAlias__Group_0__0 ) ) | ( ( rule__ColumnOrAlias__AllColsAssignment_1 ) ) )
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
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1001:1: ( ( rule__ColumnOrAlias__Group_0__0 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1001:1: ( ( rule__ColumnOrAlias__Group_0__0 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1002:1: ( rule__ColumnOrAlias__Group_0__0 )
                    {
                     before(grammarAccess.getColumnOrAliasAccess().getGroup_0()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1003:1: ( rule__ColumnOrAlias__Group_0__0 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1003:2: rule__ColumnOrAlias__Group_0__0
                    {
                    pushFollow(FOLLOW_rule__ColumnOrAlias__Group_0__0_in_rule__ColumnOrAlias__Alternatives2083);
                    rule__ColumnOrAlias__Group_0__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getColumnOrAliasAccess().getGroup_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1007:6: ( ( rule__ColumnOrAlias__AllColsAssignment_1 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1007:6: ( ( rule__ColumnOrAlias__AllColsAssignment_1 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1008:1: ( rule__ColumnOrAlias__AllColsAssignment_1 )
                    {
                     before(grammarAccess.getColumnOrAliasAccess().getAllColsAssignment_1()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1009:1: ( rule__ColumnOrAlias__AllColsAssignment_1 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1009:2: rule__ColumnOrAlias__AllColsAssignment_1
                    {
                    pushFollow(FOLLOW_rule__ColumnOrAlias__AllColsAssignment_1_in_rule__ColumnOrAlias__Alternatives2101);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1018:1: rule__OrderByColumnFull__Alternatives_1 : ( ( KEYWORD_22 ) | ( KEYWORD_24 ) );
    public final void rule__OrderByColumnFull__Alternatives_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1022:1: ( ( KEYWORD_22 ) | ( KEYWORD_24 ) )
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==KEYWORD_22) ) {
                alt2=1;
            }
            else if ( (LA2_0==KEYWORD_24) ) {
                alt2=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }
            switch (alt2) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1023:1: ( KEYWORD_22 )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1023:1: ( KEYWORD_22 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1024:1: KEYWORD_22
                    {
                     before(grammarAccess.getOrderByColumnFullAccess().getASCKeyword_1_0()); 
                    match(input,KEYWORD_22,FOLLOW_KEYWORD_22_in_rule__OrderByColumnFull__Alternatives_12135); 
                     after(grammarAccess.getOrderByColumnFullAccess().getASCKeyword_1_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1031:6: ( KEYWORD_24 )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1031:6: ( KEYWORD_24 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1032:1: KEYWORD_24
                    {
                     before(grammarAccess.getOrderByColumnFullAccess().getDESCKeyword_1_1()); 
                    match(input,KEYWORD_24,FOLLOW_KEYWORD_24_in_rule__OrderByColumnFull__Alternatives_12155); 
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


    // $ANTLR start "rule__FullExpression__Alternatives_1_1_0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1044:1: rule__FullExpression__Alternatives_1_1_0 : ( ( KEYWORD_21 ) | ( KEYWORD_17 ) );
    public final void rule__FullExpression__Alternatives_1_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1048:1: ( ( KEYWORD_21 ) | ( KEYWORD_17 ) )
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==KEYWORD_21) ) {
                alt3=1;
            }
            else if ( (LA3_0==KEYWORD_17) ) {
                alt3=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }
            switch (alt3) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1049:1: ( KEYWORD_21 )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1049:1: ( KEYWORD_21 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1050:1: KEYWORD_21
                    {
                     before(grammarAccess.getFullExpressionAccess().getANDKeyword_1_1_0_0()); 
                    match(input,KEYWORD_21,FOLLOW_KEYWORD_21_in_rule__FullExpression__Alternatives_1_1_02190); 
                     after(grammarAccess.getFullExpressionAccess().getANDKeyword_1_1_0_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1057:6: ( KEYWORD_17 )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1057:6: ( KEYWORD_17 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1058:1: KEYWORD_17
                    {
                     before(grammarAccess.getFullExpressionAccess().getORKeyword_1_1_0_1()); 
                    match(input,KEYWORD_17,FOLLOW_KEYWORD_17_in_rule__FullExpression__Alternatives_1_1_02210); 
                     after(grammarAccess.getFullExpressionAccess().getORKeyword_1_1_0_1()); 

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
    // $ANTLR end "rule__FullExpression__Alternatives_1_1_0"


    // $ANTLR start "rule__ExpressionFragment__Alternatives"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1070:1: rule__ExpressionFragment__Alternatives : ( ( ruleExpressionGroup ) | ( ruleExpression ) | ( ruleXExpression ) );
    public final void rule__ExpressionFragment__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1074:1: ( ( ruleExpressionGroup ) | ( ruleExpression ) | ( ruleXExpression ) )
            int alt4=3;
            switch ( input.LA(1) ) {
            case KEYWORD_1:
                {
                int LA4_1 = input.LA(2);

                if ( (LA4_1==KEYWORD_23||(LA4_1>=KEYWORD_19 && LA4_1<=KEYWORD_20)||LA4_1==KEYWORD_1||(LA4_1>=RULE_INT && LA4_1<=RULE_SIGNED_DOUBLE)||(LA4_1>=RULE_ID && LA4_1<=RULE_STRING)) ) {
                    alt4=1;
                }
                else if ( (LA4_1==KEYWORD_31) ) {
                    alt4=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 4, 1, input);

                    throw nvae;
                }
                }
                break;
            case KEYWORD_23:
            case KEYWORD_19:
            case RULE_INT:
            case RULE_DATE:
            case RULE_TIME:
            case RULE_TIMESTAMP:
            case RULE_SIGNED_DOUBLE:
            case RULE_ID:
            case RULE_STRING:
                {
                alt4=2;
                }
                break;
            case KEYWORD_20:
                {
                alt4=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }

            switch (alt4) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1075:1: ( ruleExpressionGroup )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1075:1: ( ruleExpressionGroup )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1076:1: ruleExpressionGroup
                    {
                     before(grammarAccess.getExpressionFragmentAccess().getExpressionGroupParserRuleCall_0()); 
                    pushFollow(FOLLOW_ruleExpressionGroup_in_rule__ExpressionFragment__Alternatives2244);
                    ruleExpressionGroup();

                    state._fsp--;

                     after(grammarAccess.getExpressionFragmentAccess().getExpressionGroupParserRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1081:6: ( ruleExpression )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1081:6: ( ruleExpression )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1082:1: ruleExpression
                    {
                     before(grammarAccess.getExpressionFragmentAccess().getExpressionParserRuleCall_1()); 
                    pushFollow(FOLLOW_ruleExpression_in_rule__ExpressionFragment__Alternatives2261);
                    ruleExpression();

                    state._fsp--;

                     after(grammarAccess.getExpressionFragmentAccess().getExpressionParserRuleCall_1()); 

                    }


                    }
                    break;
                case 3 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1087:6: ( ruleXExpression )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1087:6: ( ruleXExpression )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1088:1: ruleXExpression
                    {
                     before(grammarAccess.getExpressionFragmentAccess().getXExpressionParserRuleCall_2()); 
                    pushFollow(FOLLOW_ruleXExpression_in_rule__ExpressionFragment__Alternatives2278);
                    ruleXExpression();

                    state._fsp--;

                     after(grammarAccess.getExpressionFragmentAccess().getXExpressionParserRuleCall_2()); 

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
    // $ANTLR end "rule__ExpressionFragment__Alternatives"


    // $ANTLR start "rule__Expression__Alternatives_1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1098:1: rule__Expression__Alternatives_1 : ( ( ( rule__Expression__Alternatives_1_0 ) ) | ( ( rule__Expression__InAssignment_1_1 ) ) | ( ( rule__Expression__BetweenAssignment_1_2 ) ) | ( ( rule__Expression__LikeAssignment_1_3 ) ) | ( ( rule__Expression__CompAssignment_1_4 ) ) );
    public final void rule__Expression__Alternatives_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1102:1: ( ( ( rule__Expression__Alternatives_1_0 ) ) | ( ( rule__Expression__InAssignment_1_1 ) ) | ( ( rule__Expression__BetweenAssignment_1_2 ) ) | ( ( rule__Expression__LikeAssignment_1_3 ) ) | ( ( rule__Expression__CompAssignment_1_4 ) ) )
            int alt5=5;
            switch ( input.LA(1) ) {
            case KEYWORD_41:
            case KEYWORD_33:
                {
                alt5=1;
                }
                break;
            case KEYWORD_36:
            case KEYWORD_26:
                {
                alt5=2;
                }
                break;
            case KEYWORD_32:
                {
                alt5=3;
                }
                break;
            case KEYWORD_37:
            case KEYWORD_27:
                {
                alt5=4;
                }
                break;
            case KEYWORD_12:
            case KEYWORD_13:
            case KEYWORD_14:
            case KEYWORD_8:
            case KEYWORD_9:
            case KEYWORD_10:
                {
                alt5=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }

            switch (alt5) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1103:1: ( ( rule__Expression__Alternatives_1_0 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1103:1: ( ( rule__Expression__Alternatives_1_0 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1104:1: ( rule__Expression__Alternatives_1_0 )
                    {
                     before(grammarAccess.getExpressionAccess().getAlternatives_1_0()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1105:1: ( rule__Expression__Alternatives_1_0 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1105:2: rule__Expression__Alternatives_1_0
                    {
                    pushFollow(FOLLOW_rule__Expression__Alternatives_1_0_in_rule__Expression__Alternatives_12310);
                    rule__Expression__Alternatives_1_0();

                    state._fsp--;


                    }

                     after(grammarAccess.getExpressionAccess().getAlternatives_1_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1109:6: ( ( rule__Expression__InAssignment_1_1 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1109:6: ( ( rule__Expression__InAssignment_1_1 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1110:1: ( rule__Expression__InAssignment_1_1 )
                    {
                     before(grammarAccess.getExpressionAccess().getInAssignment_1_1()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1111:1: ( rule__Expression__InAssignment_1_1 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1111:2: rule__Expression__InAssignment_1_1
                    {
                    pushFollow(FOLLOW_rule__Expression__InAssignment_1_1_in_rule__Expression__Alternatives_12328);
                    rule__Expression__InAssignment_1_1();

                    state._fsp--;


                    }

                     after(grammarAccess.getExpressionAccess().getInAssignment_1_1()); 

                    }


                    }
                    break;
                case 3 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1115:6: ( ( rule__Expression__BetweenAssignment_1_2 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1115:6: ( ( rule__Expression__BetweenAssignment_1_2 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1116:1: ( rule__Expression__BetweenAssignment_1_2 )
                    {
                     before(grammarAccess.getExpressionAccess().getBetweenAssignment_1_2()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1117:1: ( rule__Expression__BetweenAssignment_1_2 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1117:2: rule__Expression__BetweenAssignment_1_2
                    {
                    pushFollow(FOLLOW_rule__Expression__BetweenAssignment_1_2_in_rule__Expression__Alternatives_12346);
                    rule__Expression__BetweenAssignment_1_2();

                    state._fsp--;


                    }

                     after(grammarAccess.getExpressionAccess().getBetweenAssignment_1_2()); 

                    }


                    }
                    break;
                case 4 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1121:6: ( ( rule__Expression__LikeAssignment_1_3 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1121:6: ( ( rule__Expression__LikeAssignment_1_3 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1122:1: ( rule__Expression__LikeAssignment_1_3 )
                    {
                     before(grammarAccess.getExpressionAccess().getLikeAssignment_1_3()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1123:1: ( rule__Expression__LikeAssignment_1_3 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1123:2: rule__Expression__LikeAssignment_1_3
                    {
                    pushFollow(FOLLOW_rule__Expression__LikeAssignment_1_3_in_rule__Expression__Alternatives_12364);
                    rule__Expression__LikeAssignment_1_3();

                    state._fsp--;


                    }

                     after(grammarAccess.getExpressionAccess().getLikeAssignment_1_3()); 

                    }


                    }
                    break;
                case 5 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1127:6: ( ( rule__Expression__CompAssignment_1_4 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1127:6: ( ( rule__Expression__CompAssignment_1_4 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1128:1: ( rule__Expression__CompAssignment_1_4 )
                    {
                     before(grammarAccess.getExpressionAccess().getCompAssignment_1_4()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1129:1: ( rule__Expression__CompAssignment_1_4 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1129:2: rule__Expression__CompAssignment_1_4
                    {
                    pushFollow(FOLLOW_rule__Expression__CompAssignment_1_4_in_rule__Expression__Alternatives_12382);
                    rule__Expression__CompAssignment_1_4();

                    state._fsp--;


                    }

                     after(grammarAccess.getExpressionAccess().getCompAssignment_1_4()); 

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
    // $ANTLR end "rule__Expression__Alternatives_1"


    // $ANTLR start "rule__Expression__Alternatives_1_0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1138:1: rule__Expression__Alternatives_1_0 : ( ( KEYWORD_33 ) | ( KEYWORD_41 ) );
    public final void rule__Expression__Alternatives_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1142:1: ( ( KEYWORD_33 ) | ( KEYWORD_41 ) )
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==KEYWORD_33) ) {
                alt6=1;
            }
            else if ( (LA6_0==KEYWORD_41) ) {
                alt6=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }
            switch (alt6) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1143:1: ( KEYWORD_33 )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1143:1: ( KEYWORD_33 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1144:1: KEYWORD_33
                    {
                     before(grammarAccess.getExpressionAccess().getISNULLKeyword_1_0_0()); 
                    match(input,KEYWORD_33,FOLLOW_KEYWORD_33_in_rule__Expression__Alternatives_1_02416); 
                     after(grammarAccess.getExpressionAccess().getISNULLKeyword_1_0_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1151:6: ( KEYWORD_41 )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1151:6: ( KEYWORD_41 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1152:1: KEYWORD_41
                    {
                     before(grammarAccess.getExpressionAccess().getISNOTNULLKeyword_1_0_1()); 
                    match(input,KEYWORD_41,FOLLOW_KEYWORD_41_in_rule__Expression__Alternatives_1_02436); 
                     after(grammarAccess.getExpressionAccess().getISNOTNULLKeyword_1_0_1()); 

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
    // $ANTLR end "rule__Expression__Alternatives_1_0"


    // $ANTLR start "rule__Comparison__Alternatives_0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1164:1: rule__Comparison__Alternatives_0 : ( ( KEYWORD_10 ) | ( KEYWORD_14 ) | ( KEYWORD_8 ) | ( KEYWORD_12 ) | ( KEYWORD_9 ) | ( KEYWORD_13 ) );
    public final void rule__Comparison__Alternatives_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1168:1: ( ( KEYWORD_10 ) | ( KEYWORD_14 ) | ( KEYWORD_8 ) | ( KEYWORD_12 ) | ( KEYWORD_9 ) | ( KEYWORD_13 ) )
            int alt7=6;
            switch ( input.LA(1) ) {
            case KEYWORD_10:
                {
                alt7=1;
                }
                break;
            case KEYWORD_14:
                {
                alt7=2;
                }
                break;
            case KEYWORD_8:
                {
                alt7=3;
                }
                break;
            case KEYWORD_12:
                {
                alt7=4;
                }
                break;
            case KEYWORD_9:
                {
                alt7=5;
                }
                break;
            case KEYWORD_13:
                {
                alt7=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;
            }

            switch (alt7) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1169:1: ( KEYWORD_10 )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1169:1: ( KEYWORD_10 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1170:1: KEYWORD_10
                    {
                     before(grammarAccess.getComparisonAccess().getGreaterThanSignKeyword_0_0()); 
                    match(input,KEYWORD_10,FOLLOW_KEYWORD_10_in_rule__Comparison__Alternatives_02471); 
                     after(grammarAccess.getComparisonAccess().getGreaterThanSignKeyword_0_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1177:6: ( KEYWORD_14 )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1177:6: ( KEYWORD_14 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1178:1: KEYWORD_14
                    {
                     before(grammarAccess.getComparisonAccess().getGreaterThanSignEqualsSignKeyword_0_1()); 
                    match(input,KEYWORD_14,FOLLOW_KEYWORD_14_in_rule__Comparison__Alternatives_02491); 
                     after(grammarAccess.getComparisonAccess().getGreaterThanSignEqualsSignKeyword_0_1()); 

                    }


                    }
                    break;
                case 3 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1185:6: ( KEYWORD_8 )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1185:6: ( KEYWORD_8 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1186:1: KEYWORD_8
                    {
                     before(grammarAccess.getComparisonAccess().getLessThanSignKeyword_0_2()); 
                    match(input,KEYWORD_8,FOLLOW_KEYWORD_8_in_rule__Comparison__Alternatives_02511); 
                     after(grammarAccess.getComparisonAccess().getLessThanSignKeyword_0_2()); 

                    }


                    }
                    break;
                case 4 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1193:6: ( KEYWORD_12 )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1193:6: ( KEYWORD_12 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1194:1: KEYWORD_12
                    {
                     before(grammarAccess.getComparisonAccess().getLessThanSignEqualsSignKeyword_0_3()); 
                    match(input,KEYWORD_12,FOLLOW_KEYWORD_12_in_rule__Comparison__Alternatives_02531); 
                     after(grammarAccess.getComparisonAccess().getLessThanSignEqualsSignKeyword_0_3()); 

                    }


                    }
                    break;
                case 5 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1201:6: ( KEYWORD_9 )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1201:6: ( KEYWORD_9 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1202:1: KEYWORD_9
                    {
                     before(grammarAccess.getComparisonAccess().getEqualsSignKeyword_0_4()); 
                    match(input,KEYWORD_9,FOLLOW_KEYWORD_9_in_rule__Comparison__Alternatives_02551); 
                     after(grammarAccess.getComparisonAccess().getEqualsSignKeyword_0_4()); 

                    }


                    }
                    break;
                case 6 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1209:6: ( KEYWORD_13 )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1209:6: ( KEYWORD_13 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1210:1: KEYWORD_13
                    {
                     before(grammarAccess.getComparisonAccess().getLessThanSignGreaterThanSignKeyword_0_5()); 
                    match(input,KEYWORD_13,FOLLOW_KEYWORD_13_in_rule__Comparison__Alternatives_02571); 
                     after(grammarAccess.getComparisonAccess().getLessThanSignGreaterThanSignKeyword_0_5()); 

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
    // $ANTLR end "rule__Comparison__Alternatives_0"


    // $ANTLR start "rule__Like__Alternatives_0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1222:1: rule__Like__Alternatives_0 : ( ( KEYWORD_27 ) | ( KEYWORD_37 ) );
    public final void rule__Like__Alternatives_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1226:1: ( ( KEYWORD_27 ) | ( KEYWORD_37 ) )
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==KEYWORD_27) ) {
                alt8=1;
            }
            else if ( (LA8_0==KEYWORD_37) ) {
                alt8=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;
            }
            switch (alt8) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1227:1: ( KEYWORD_27 )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1227:1: ( KEYWORD_27 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1228:1: KEYWORD_27
                    {
                     before(grammarAccess.getLikeAccess().getLIKEKeyword_0_0()); 
                    match(input,KEYWORD_27,FOLLOW_KEYWORD_27_in_rule__Like__Alternatives_02606); 
                     after(grammarAccess.getLikeAccess().getLIKEKeyword_0_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1235:6: ( KEYWORD_37 )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1235:6: ( KEYWORD_37 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1236:1: KEYWORD_37
                    {
                     before(grammarAccess.getLikeAccess().getNOTLIKEKeyword_0_1()); 
                    match(input,KEYWORD_37,FOLLOW_KEYWORD_37_in_rule__Like__Alternatives_02626); 
                     after(grammarAccess.getLikeAccess().getNOTLIKEKeyword_0_1()); 

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
    // $ANTLR end "rule__Like__Alternatives_0"


    // $ANTLR start "rule__InOperator__Alternatives_1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1248:1: rule__InOperator__Alternatives_1 : ( ( KEYWORD_36 ) | ( KEYWORD_26 ) );
    public final void rule__InOperator__Alternatives_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1252:1: ( ( KEYWORD_36 ) | ( KEYWORD_26 ) )
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==KEYWORD_36) ) {
                alt9=1;
            }
            else if ( (LA9_0==KEYWORD_26) ) {
                alt9=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                throw nvae;
            }
            switch (alt9) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1253:1: ( KEYWORD_36 )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1253:1: ( KEYWORD_36 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1254:1: KEYWORD_36
                    {
                     before(grammarAccess.getInOperatorAccess().getNOTINKeyword_1_0()); 
                    match(input,KEYWORD_36,FOLLOW_KEYWORD_36_in_rule__InOperator__Alternatives_12661); 
                     after(grammarAccess.getInOperatorAccess().getNOTINKeyword_1_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1261:6: ( KEYWORD_26 )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1261:6: ( KEYWORD_26 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1262:1: KEYWORD_26
                    {
                     before(grammarAccess.getInOperatorAccess().getINKeyword_1_1()); 
                    match(input,KEYWORD_26,FOLLOW_KEYWORD_26_in_rule__InOperator__Alternatives_12681); 
                     after(grammarAccess.getInOperatorAccess().getINKeyword_1_1()); 

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
    // $ANTLR end "rule__InOperator__Alternatives_1"


    // $ANTLR start "rule__InOperator__Alternatives_2"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1274:1: rule__InOperator__Alternatives_2 : ( ( ( rule__InOperator__SubqueryAssignment_2_0 ) ) | ( ( rule__InOperator__Group_2_1__0 )? ) );
    public final void rule__InOperator__Alternatives_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1278:1: ( ( ( rule__InOperator__SubqueryAssignment_2_0 ) ) | ( ( rule__InOperator__Group_2_1__0 )? ) )
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==KEYWORD_1) ) {
                alt11=1;
            }
            else if ( (LA11_0==KEYWORD_2||LA11_0==KEYWORD_4) ) {
                alt11=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 11, 0, input);

                throw nvae;
            }
            switch (alt11) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1279:1: ( ( rule__InOperator__SubqueryAssignment_2_0 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1279:1: ( ( rule__InOperator__SubqueryAssignment_2_0 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1280:1: ( rule__InOperator__SubqueryAssignment_2_0 )
                    {
                     before(grammarAccess.getInOperatorAccess().getSubqueryAssignment_2_0()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1281:1: ( rule__InOperator__SubqueryAssignment_2_0 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1281:2: rule__InOperator__SubqueryAssignment_2_0
                    {
                    pushFollow(FOLLOW_rule__InOperator__SubqueryAssignment_2_0_in_rule__InOperator__Alternatives_22715);
                    rule__InOperator__SubqueryAssignment_2_0();

                    state._fsp--;


                    }

                     after(grammarAccess.getInOperatorAccess().getSubqueryAssignment_2_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1285:6: ( ( rule__InOperator__Group_2_1__0 )? )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1285:6: ( ( rule__InOperator__Group_2_1__0 )? )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1286:1: ( rule__InOperator__Group_2_1__0 )?
                    {
                     before(grammarAccess.getInOperatorAccess().getGroup_2_1()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1287:1: ( rule__InOperator__Group_2_1__0 )?
                    int alt10=2;
                    int LA10_0 = input.LA(1);

                    if ( (LA10_0==KEYWORD_4) ) {
                        alt10=1;
                    }
                    switch (alt10) {
                        case 1 :
                            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1287:2: rule__InOperator__Group_2_1__0
                            {
                            pushFollow(FOLLOW_rule__InOperator__Group_2_1__0_in_rule__InOperator__Alternatives_22733);
                            rule__InOperator__Group_2_1__0();

                            state._fsp--;


                            }
                            break;

                    }

                     after(grammarAccess.getInOperatorAccess().getGroup_2_1()); 

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
    // $ANTLR end "rule__InOperator__Alternatives_2"


    // $ANTLR start "rule__Operand__Alternatives_1_1_0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1296:1: rule__Operand__Alternatives_1_1_0 : ( ( KEYWORD_3 ) | ( KEYWORD_5 ) | ( RULE_STAR ) | ( KEYWORD_7 ) | ( KEYWORD_18 ) );
    public final void rule__Operand__Alternatives_1_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1300:1: ( ( KEYWORD_3 ) | ( KEYWORD_5 ) | ( RULE_STAR ) | ( KEYWORD_7 ) | ( KEYWORD_18 ) )
            int alt12=5;
            switch ( input.LA(1) ) {
            case KEYWORD_3:
                {
                alt12=1;
                }
                break;
            case KEYWORD_5:
                {
                alt12=2;
                }
                break;
            case RULE_STAR:
                {
                alt12=3;
                }
                break;
            case KEYWORD_7:
                {
                alt12=4;
                }
                break;
            case KEYWORD_18:
                {
                alt12=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 12, 0, input);

                throw nvae;
            }

            switch (alt12) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1301:1: ( KEYWORD_3 )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1301:1: ( KEYWORD_3 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1302:1: KEYWORD_3
                    {
                     before(grammarAccess.getOperandAccess().getPlusSignKeyword_1_1_0_0()); 
                    match(input,KEYWORD_3,FOLLOW_KEYWORD_3_in_rule__Operand__Alternatives_1_1_02768); 
                     after(grammarAccess.getOperandAccess().getPlusSignKeyword_1_1_0_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1309:6: ( KEYWORD_5 )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1309:6: ( KEYWORD_5 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1310:1: KEYWORD_5
                    {
                     before(grammarAccess.getOperandAccess().getHyphenMinusKeyword_1_1_0_1()); 
                    match(input,KEYWORD_5,FOLLOW_KEYWORD_5_in_rule__Operand__Alternatives_1_1_02788); 
                     after(grammarAccess.getOperandAccess().getHyphenMinusKeyword_1_1_0_1()); 

                    }


                    }
                    break;
                case 3 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1317:6: ( RULE_STAR )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1317:6: ( RULE_STAR )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1318:1: RULE_STAR
                    {
                     before(grammarAccess.getOperandAccess().getSTARTerminalRuleCall_1_1_0_2()); 
                    match(input,RULE_STAR,FOLLOW_RULE_STAR_in_rule__Operand__Alternatives_1_1_02807); 
                     after(grammarAccess.getOperandAccess().getSTARTerminalRuleCall_1_1_0_2()); 

                    }


                    }
                    break;
                case 4 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1323:6: ( KEYWORD_7 )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1323:6: ( KEYWORD_7 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1324:1: KEYWORD_7
                    {
                     before(grammarAccess.getOperandAccess().getSolidusKeyword_1_1_0_3()); 
                    match(input,KEYWORD_7,FOLLOW_KEYWORD_7_in_rule__Operand__Alternatives_1_1_02825); 
                     after(grammarAccess.getOperandAccess().getSolidusKeyword_1_1_0_3()); 

                    }


                    }
                    break;
                case 5 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1331:6: ( KEYWORD_18 )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1331:6: ( KEYWORD_18 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1332:1: KEYWORD_18
                    {
                     before(grammarAccess.getOperandAccess().getVerticalLineVerticalLineKeyword_1_1_0_4()); 
                    match(input,KEYWORD_18,FOLLOW_KEYWORD_18_in_rule__Operand__Alternatives_1_1_02845); 
                     after(grammarAccess.getOperandAccess().getVerticalLineVerticalLineKeyword_1_1_0_4()); 

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
    // $ANTLR end "rule__Operand__Alternatives_1_1_0"


    // $ANTLR start "rule__OperandFragment__Alternatives"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1344:1: rule__OperandFragment__Alternatives : ( ( ruleColumnOperand ) | ( ruleXOperandFragment ) | ( ruleSubQueryOperand ) );
    public final void rule__OperandFragment__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1348:1: ( ( ruleColumnOperand ) | ( ruleXOperandFragment ) | ( ruleSubQueryOperand ) )
            int alt13=3;
            switch ( input.LA(1) ) {
            case RULE_ID:
                {
                alt13=1;
                }
                break;
            case KEYWORD_23:
            case KEYWORD_19:
            case RULE_INT:
            case RULE_DATE:
            case RULE_TIME:
            case RULE_TIMESTAMP:
            case RULE_SIGNED_DOUBLE:
            case RULE_STRING:
                {
                alt13=2;
                }
                break;
            case KEYWORD_1:
                {
                alt13=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 13, 0, input);

                throw nvae;
            }

            switch (alt13) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1349:1: ( ruleColumnOperand )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1349:1: ( ruleColumnOperand )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1350:1: ruleColumnOperand
                    {
                     before(grammarAccess.getOperandFragmentAccess().getColumnOperandParserRuleCall_0()); 
                    pushFollow(FOLLOW_ruleColumnOperand_in_rule__OperandFragment__Alternatives2879);
                    ruleColumnOperand();

                    state._fsp--;

                     after(grammarAccess.getOperandFragmentAccess().getColumnOperandParserRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1355:6: ( ruleXOperandFragment )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1355:6: ( ruleXOperandFragment )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1356:1: ruleXOperandFragment
                    {
                     before(grammarAccess.getOperandFragmentAccess().getXOperandFragmentParserRuleCall_1()); 
                    pushFollow(FOLLOW_ruleXOperandFragment_in_rule__OperandFragment__Alternatives2896);
                    ruleXOperandFragment();

                    state._fsp--;

                     after(grammarAccess.getOperandFragmentAccess().getXOperandFragmentParserRuleCall_1()); 

                    }


                    }
                    break;
                case 3 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1361:6: ( ruleSubQueryOperand )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1361:6: ( ruleSubQueryOperand )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1362:1: ruleSubQueryOperand
                    {
                     before(grammarAccess.getOperandFragmentAccess().getSubQueryOperandParserRuleCall_2()); 
                    pushFollow(FOLLOW_ruleSubQueryOperand_in_rule__OperandFragment__Alternatives2913);
                    ruleSubQueryOperand();

                    state._fsp--;

                     after(grammarAccess.getOperandFragmentAccess().getSubQueryOperandParserRuleCall_2()); 

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
    // $ANTLR end "rule__OperandFragment__Alternatives"


    // $ANTLR start "rule__XOperandFragment__Alternatives"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1372:1: rule__XOperandFragment__Alternatives : ( ( ruleParameterOperand ) | ( ruleExclamationParameterOperand ) | ( ( rule__XOperandFragment__ScalarAssignment_2 ) ) );
    public final void rule__XOperandFragment__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1376:1: ( ( ruleParameterOperand ) | ( ruleExclamationParameterOperand ) | ( ( rule__XOperandFragment__ScalarAssignment_2 ) ) )
            int alt14=3;
            switch ( input.LA(1) ) {
            case KEYWORD_19:
                {
                alt14=1;
                }
                break;
            case KEYWORD_23:
                {
                alt14=2;
                }
                break;
            case RULE_INT:
            case RULE_DATE:
            case RULE_TIME:
            case RULE_TIMESTAMP:
            case RULE_SIGNED_DOUBLE:
            case RULE_STRING:
                {
                alt14=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 14, 0, input);

                throw nvae;
            }

            switch (alt14) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1377:1: ( ruleParameterOperand )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1377:1: ( ruleParameterOperand )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1378:1: ruleParameterOperand
                    {
                     before(grammarAccess.getXOperandFragmentAccess().getParameterOperandParserRuleCall_0()); 
                    pushFollow(FOLLOW_ruleParameterOperand_in_rule__XOperandFragment__Alternatives2945);
                    ruleParameterOperand();

                    state._fsp--;

                     after(grammarAccess.getXOperandFragmentAccess().getParameterOperandParserRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1383:6: ( ruleExclamationParameterOperand )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1383:6: ( ruleExclamationParameterOperand )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1384:1: ruleExclamationParameterOperand
                    {
                     before(grammarAccess.getXOperandFragmentAccess().getExclamationParameterOperandParserRuleCall_1()); 
                    pushFollow(FOLLOW_ruleExclamationParameterOperand_in_rule__XOperandFragment__Alternatives2962);
                    ruleExclamationParameterOperand();

                    state._fsp--;

                     after(grammarAccess.getXOperandFragmentAccess().getExclamationParameterOperandParserRuleCall_1()); 

                    }


                    }
                    break;
                case 3 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1389:6: ( ( rule__XOperandFragment__ScalarAssignment_2 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1389:6: ( ( rule__XOperandFragment__ScalarAssignment_2 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1390:1: ( rule__XOperandFragment__ScalarAssignment_2 )
                    {
                     before(grammarAccess.getXOperandFragmentAccess().getScalarAssignment_2()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1391:1: ( rule__XOperandFragment__ScalarAssignment_2 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1391:2: rule__XOperandFragment__ScalarAssignment_2
                    {
                    pushFollow(FOLLOW_rule__XOperandFragment__ScalarAssignment_2_in_rule__XOperandFragment__Alternatives2979);
                    rule__XOperandFragment__ScalarAssignment_2();

                    state._fsp--;


                    }

                     after(grammarAccess.getXOperandFragmentAccess().getScalarAssignment_2()); 

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
    // $ANTLR end "rule__XOperandFragment__Alternatives"


    // $ANTLR start "rule__ScalarOperand__Alternatives"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1400:1: rule__ScalarOperand__Alternatives : ( ( RULE_INT ) | ( ruleStringOperand ) | ( RULE_SIGNED_DOUBLE ) | ( RULE_DATE ) | ( RULE_TIME ) | ( RULE_TIMESTAMP ) );
    public final void rule__ScalarOperand__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1404:1: ( ( RULE_INT ) | ( ruleStringOperand ) | ( RULE_SIGNED_DOUBLE ) | ( RULE_DATE ) | ( RULE_TIME ) | ( RULE_TIMESTAMP ) )
            int alt15=6;
            switch ( input.LA(1) ) {
            case RULE_INT:
                {
                alt15=1;
                }
                break;
            case RULE_STRING:
                {
                alt15=2;
                }
                break;
            case RULE_SIGNED_DOUBLE:
                {
                alt15=3;
                }
                break;
            case RULE_DATE:
                {
                alt15=4;
                }
                break;
            case RULE_TIME:
                {
                alt15=5;
                }
                break;
            case RULE_TIMESTAMP:
                {
                alt15=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 15, 0, input);

                throw nvae;
            }

            switch (alt15) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1405:1: ( RULE_INT )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1405:1: ( RULE_INT )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1406:1: RULE_INT
                    {
                     before(grammarAccess.getScalarOperandAccess().getINTTerminalRuleCall_0()); 
                    match(input,RULE_INT,FOLLOW_RULE_INT_in_rule__ScalarOperand__Alternatives3012); 
                     after(grammarAccess.getScalarOperandAccess().getINTTerminalRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1411:6: ( ruleStringOperand )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1411:6: ( ruleStringOperand )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1412:1: ruleStringOperand
                    {
                     before(grammarAccess.getScalarOperandAccess().getStringOperandParserRuleCall_1()); 
                    pushFollow(FOLLOW_ruleStringOperand_in_rule__ScalarOperand__Alternatives3029);
                    ruleStringOperand();

                    state._fsp--;

                     after(grammarAccess.getScalarOperandAccess().getStringOperandParserRuleCall_1()); 

                    }


                    }
                    break;
                case 3 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1417:6: ( RULE_SIGNED_DOUBLE )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1417:6: ( RULE_SIGNED_DOUBLE )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1418:1: RULE_SIGNED_DOUBLE
                    {
                     before(grammarAccess.getScalarOperandAccess().getSIGNED_DOUBLETerminalRuleCall_2()); 
                    match(input,RULE_SIGNED_DOUBLE,FOLLOW_RULE_SIGNED_DOUBLE_in_rule__ScalarOperand__Alternatives3046); 
                     after(grammarAccess.getScalarOperandAccess().getSIGNED_DOUBLETerminalRuleCall_2()); 

                    }


                    }
                    break;
                case 4 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1423:6: ( RULE_DATE )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1423:6: ( RULE_DATE )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1424:1: RULE_DATE
                    {
                     before(grammarAccess.getScalarOperandAccess().getDATETerminalRuleCall_3()); 
                    match(input,RULE_DATE,FOLLOW_RULE_DATE_in_rule__ScalarOperand__Alternatives3063); 
                     after(grammarAccess.getScalarOperandAccess().getDATETerminalRuleCall_3()); 

                    }


                    }
                    break;
                case 5 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1429:6: ( RULE_TIME )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1429:6: ( RULE_TIME )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1430:1: RULE_TIME
                    {
                     before(grammarAccess.getScalarOperandAccess().getTIMETerminalRuleCall_4()); 
                    match(input,RULE_TIME,FOLLOW_RULE_TIME_in_rule__ScalarOperand__Alternatives3080); 
                     after(grammarAccess.getScalarOperandAccess().getTIMETerminalRuleCall_4()); 

                    }


                    }
                    break;
                case 6 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1435:6: ( RULE_TIMESTAMP )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1435:6: ( RULE_TIMESTAMP )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1436:1: RULE_TIMESTAMP
                    {
                     before(grammarAccess.getScalarOperandAccess().getTIMESTAMPTerminalRuleCall_5()); 
                    match(input,RULE_TIMESTAMP,FOLLOW_RULE_TIMESTAMP_in_rule__ScalarOperand__Alternatives3097); 
                     after(grammarAccess.getScalarOperandAccess().getTIMESTAMPTerminalRuleCall_5()); 

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
    // $ANTLR end "rule__ScalarOperand__Alternatives"


    // $ANTLR start "rule__JoinType__Alternatives"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1446:1: rule__JoinType__Alternatives : ( ( ( KEYWORD_40 ) ) | ( ( KEYWORD_43 ) ) | ( ( KEYWORD_44 ) ) | ( ( KEYWORD_42 ) ) | ( ( KEYWORD_39 ) ) );
    public final void rule__JoinType__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1450:1: ( ( ( KEYWORD_40 ) ) | ( ( KEYWORD_43 ) ) | ( ( KEYWORD_44 ) ) | ( ( KEYWORD_42 ) ) | ( ( KEYWORD_39 ) ) )
            int alt16=5;
            switch ( input.LA(1) ) {
            case KEYWORD_40:
                {
                alt16=1;
                }
                break;
            case KEYWORD_43:
                {
                alt16=2;
                }
                break;
            case KEYWORD_44:
                {
                alt16=3;
                }
                break;
            case KEYWORD_42:
                {
                alt16=4;
                }
                break;
            case KEYWORD_39:
                {
                alt16=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 16, 0, input);

                throw nvae;
            }

            switch (alt16) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1451:1: ( ( KEYWORD_40 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1451:1: ( ( KEYWORD_40 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1452:1: ( KEYWORD_40 )
                    {
                     before(grammarAccess.getJoinTypeAccess().getInnerJoinEnumLiteralDeclaration_0()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1453:1: ( KEYWORD_40 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1453:3: KEYWORD_40
                    {
                    match(input,KEYWORD_40,FOLLOW_KEYWORD_40_in_rule__JoinType__Alternatives3130); 

                    }

                     after(grammarAccess.getJoinTypeAccess().getInnerJoinEnumLiteralDeclaration_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1458:6: ( ( KEYWORD_43 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1458:6: ( ( KEYWORD_43 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1459:1: ( KEYWORD_43 )
                    {
                     before(grammarAccess.getJoinTypeAccess().getLeftOuterJoinEnumLiteralDeclaration_1()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1460:1: ( KEYWORD_43 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1460:3: KEYWORD_43
                    {
                    match(input,KEYWORD_43,FOLLOW_KEYWORD_43_in_rule__JoinType__Alternatives3150); 

                    }

                     after(grammarAccess.getJoinTypeAccess().getLeftOuterJoinEnumLiteralDeclaration_1()); 

                    }


                    }
                    break;
                case 3 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1465:6: ( ( KEYWORD_44 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1465:6: ( ( KEYWORD_44 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1466:1: ( KEYWORD_44 )
                    {
                     before(grammarAccess.getJoinTypeAccess().getRightOuterJoinEnumLiteralDeclaration_2()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1467:1: ( KEYWORD_44 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1467:3: KEYWORD_44
                    {
                    match(input,KEYWORD_44,FOLLOW_KEYWORD_44_in_rule__JoinType__Alternatives3170); 

                    }

                     after(grammarAccess.getJoinTypeAccess().getRightOuterJoinEnumLiteralDeclaration_2()); 

                    }


                    }
                    break;
                case 4 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1472:6: ( ( KEYWORD_42 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1472:6: ( ( KEYWORD_42 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1473:1: ( KEYWORD_42 )
                    {
                     before(grammarAccess.getJoinTypeAccess().getFullOuterJoinEnumLiteralDeclaration_3()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1474:1: ( KEYWORD_42 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1474:3: KEYWORD_42
                    {
                    match(input,KEYWORD_42,FOLLOW_KEYWORD_42_in_rule__JoinType__Alternatives3190); 

                    }

                     after(grammarAccess.getJoinTypeAccess().getFullOuterJoinEnumLiteralDeclaration_3()); 

                    }


                    }
                    break;
                case 5 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1479:6: ( ( KEYWORD_39 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1479:6: ( ( KEYWORD_39 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1480:1: ( KEYWORD_39 )
                    {
                     before(grammarAccess.getJoinTypeAccess().getCrossJoinEnumLiteralDeclaration_4()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1481:1: ( KEYWORD_39 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1481:3: KEYWORD_39
                    {
                    match(input,KEYWORD_39,FOLLOW_KEYWORD_39_in_rule__JoinType__Alternatives3210); 

                    }

                     after(grammarAccess.getJoinTypeAccess().getCrossJoinEnumLiteralDeclaration_4()); 

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
    // $ANTLR end "rule__JoinType__Alternatives"


    // $ANTLR start "rule__Model__Group__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1493:1: rule__Model__Group__0 : rule__Model__Group__0__Impl rule__Model__Group__1 ;
    public final void rule__Model__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1497:1: ( rule__Model__Group__0__Impl rule__Model__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1498:2: rule__Model__Group__0__Impl rule__Model__Group__1
            {
            pushFollow(FOLLOW_rule__Model__Group__0__Impl_in_rule__Model__Group__03242);
            rule__Model__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Model__Group__1_in_rule__Model__Group__03245);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1505:1: rule__Model__Group__0__Impl : ( ruleSelect ) ;
    public final void rule__Model__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1509:1: ( ( ruleSelect ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1510:1: ( ruleSelect )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1510:1: ( ruleSelect )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1511:1: ruleSelect
            {
             before(grammarAccess.getModelAccess().getSelectParserRuleCall_0()); 
            pushFollow(FOLLOW_ruleSelect_in_rule__Model__Group__0__Impl3272);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1522:1: rule__Model__Group__1 : rule__Model__Group__1__Impl rule__Model__Group__2 ;
    public final void rule__Model__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1526:1: ( rule__Model__Group__1__Impl rule__Model__Group__2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1527:2: rule__Model__Group__1__Impl rule__Model__Group__2
            {
            pushFollow(FOLLOW_rule__Model__Group__1__Impl_in_rule__Model__Group__13301);
            rule__Model__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Model__Group__2_in_rule__Model__Group__13304);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1534:1: rule__Model__Group__1__Impl : ( ( rule__Model__Group_1__0 )? ) ;
    public final void rule__Model__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1538:1: ( ( ( rule__Model__Group_1__0 )? ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1539:1: ( ( rule__Model__Group_1__0 )? )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1539:1: ( ( rule__Model__Group_1__0 )? )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1540:1: ( rule__Model__Group_1__0 )?
            {
             before(grammarAccess.getModelAccess().getGroup_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1541:1: ( rule__Model__Group_1__0 )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==KEYWORD_28) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1541:2: rule__Model__Group_1__0
                    {
                    pushFollow(FOLLOW_rule__Model__Group_1__0_in_rule__Model__Group__1__Impl3331);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1551:1: rule__Model__Group__2 : rule__Model__Group__2__Impl ;
    public final void rule__Model__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1555:1: ( rule__Model__Group__2__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1556:2: rule__Model__Group__2__Impl
            {
            pushFollow(FOLLOW_rule__Model__Group__2__Impl_in_rule__Model__Group__23362);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1562:1: rule__Model__Group__2__Impl : ( ( rule__Model__Group_2__0 )? ) ;
    public final void rule__Model__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1566:1: ( ( ( rule__Model__Group_2__0 )? ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1567:1: ( ( rule__Model__Group_2__0 )? )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1567:1: ( ( rule__Model__Group_2__0 )? )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1568:1: ( rule__Model__Group_2__0 )?
            {
             before(grammarAccess.getModelAccess().getGroup_2()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1569:1: ( rule__Model__Group_2__0 )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==KEYWORD_38) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1569:2: rule__Model__Group_2__0
                    {
                    pushFollow(FOLLOW_rule__Model__Group_2__0_in_rule__Model__Group__2__Impl3389);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1585:1: rule__Model__Group_1__0 : rule__Model__Group_1__0__Impl rule__Model__Group_1__1 ;
    public final void rule__Model__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1589:1: ( rule__Model__Group_1__0__Impl rule__Model__Group_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1590:2: rule__Model__Group_1__0__Impl rule__Model__Group_1__1
            {
            pushFollow(FOLLOW_rule__Model__Group_1__0__Impl_in_rule__Model__Group_1__03426);
            rule__Model__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Model__Group_1__1_in_rule__Model__Group_1__03429);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1597:1: rule__Model__Group_1__0__Impl : ( () ) ;
    public final void rule__Model__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1601:1: ( ( () ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1602:1: ( () )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1602:1: ( () )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1603:1: ()
            {
             before(grammarAccess.getModelAccess().getOrSelectEntriesAction_1_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1604:1: ()
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1606:1: 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1616:1: rule__Model__Group_1__1 : rule__Model__Group_1__1__Impl ;
    public final void rule__Model__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1620:1: ( rule__Model__Group_1__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1621:2: rule__Model__Group_1__1__Impl
            {
            pushFollow(FOLLOW_rule__Model__Group_1__1__Impl_in_rule__Model__Group_1__13487);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1627:1: rule__Model__Group_1__1__Impl : ( ( ( rule__Model__Group_1_1__0 ) ) ( ( rule__Model__Group_1_1__0 )* ) ) ;
    public final void rule__Model__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1631:1: ( ( ( ( rule__Model__Group_1_1__0 ) ) ( ( rule__Model__Group_1_1__0 )* ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1632:1: ( ( ( rule__Model__Group_1_1__0 ) ) ( ( rule__Model__Group_1_1__0 )* ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1632:1: ( ( ( rule__Model__Group_1_1__0 ) ) ( ( rule__Model__Group_1_1__0 )* ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1633:1: ( ( rule__Model__Group_1_1__0 ) ) ( ( rule__Model__Group_1_1__0 )* )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1633:1: ( ( rule__Model__Group_1_1__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1634:1: ( rule__Model__Group_1_1__0 )
            {
             before(grammarAccess.getModelAccess().getGroup_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1635:1: ( rule__Model__Group_1_1__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1635:2: rule__Model__Group_1_1__0
            {
            pushFollow(FOLLOW_rule__Model__Group_1_1__0_in_rule__Model__Group_1__1__Impl3516);
            rule__Model__Group_1_1__0();

            state._fsp--;


            }

             after(grammarAccess.getModelAccess().getGroup_1_1()); 

            }

            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1638:1: ( ( rule__Model__Group_1_1__0 )* )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1639:1: ( rule__Model__Group_1_1__0 )*
            {
             before(grammarAccess.getModelAccess().getGroup_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1640:1: ( rule__Model__Group_1_1__0 )*
            loop19:
            do {
                int alt19=2;
                int LA19_0 = input.LA(1);

                if ( (LA19_0==KEYWORD_28) ) {
                    alt19=1;
                }


                switch (alt19) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1640:2: rule__Model__Group_1_1__0
            	    {
            	    pushFollow(FOLLOW_rule__Model__Group_1_1__0_in_rule__Model__Group_1__1__Impl3528);
            	    rule__Model__Group_1_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop19;
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1655:1: rule__Model__Group_1_1__0 : rule__Model__Group_1_1__0__Impl rule__Model__Group_1_1__1 ;
    public final void rule__Model__Group_1_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1659:1: ( rule__Model__Group_1_1__0__Impl rule__Model__Group_1_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1660:2: rule__Model__Group_1_1__0__Impl rule__Model__Group_1_1__1
            {
            pushFollow(FOLLOW_rule__Model__Group_1_1__0__Impl_in_rule__Model__Group_1_1__03565);
            rule__Model__Group_1_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Model__Group_1_1__1_in_rule__Model__Group_1_1__03568);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1667:1: rule__Model__Group_1_1__0__Impl : ( KEYWORD_28 ) ;
    public final void rule__Model__Group_1_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1671:1: ( ( KEYWORD_28 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1672:1: ( KEYWORD_28 )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1672:1: ( KEYWORD_28 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1673:1: KEYWORD_28
            {
             before(grammarAccess.getModelAccess().getUNIONKeyword_1_1_0()); 
            match(input,KEYWORD_28,FOLLOW_KEYWORD_28_in_rule__Model__Group_1_1__0__Impl3596); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1686:1: rule__Model__Group_1_1__1 : rule__Model__Group_1_1__1__Impl ;
    public final void rule__Model__Group_1_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1690:1: ( rule__Model__Group_1_1__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1691:2: rule__Model__Group_1_1__1__Impl
            {
            pushFollow(FOLLOW_rule__Model__Group_1_1__1__Impl_in_rule__Model__Group_1_1__13627);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1697:1: rule__Model__Group_1_1__1__Impl : ( ( rule__Model__EntriesAssignment_1_1_1 ) ) ;
    public final void rule__Model__Group_1_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1701:1: ( ( ( rule__Model__EntriesAssignment_1_1_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1702:1: ( ( rule__Model__EntriesAssignment_1_1_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1702:1: ( ( rule__Model__EntriesAssignment_1_1_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1703:1: ( rule__Model__EntriesAssignment_1_1_1 )
            {
             before(grammarAccess.getModelAccess().getEntriesAssignment_1_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1704:1: ( rule__Model__EntriesAssignment_1_1_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1704:2: rule__Model__EntriesAssignment_1_1_1
            {
            pushFollow(FOLLOW_rule__Model__EntriesAssignment_1_1_1_in_rule__Model__Group_1_1__1__Impl3654);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1718:1: rule__Model__Group_2__0 : rule__Model__Group_2__0__Impl rule__Model__Group_2__1 ;
    public final void rule__Model__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1722:1: ( rule__Model__Group_2__0__Impl rule__Model__Group_2__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1723:2: rule__Model__Group_2__0__Impl rule__Model__Group_2__1
            {
            pushFollow(FOLLOW_rule__Model__Group_2__0__Impl_in_rule__Model__Group_2__03688);
            rule__Model__Group_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Model__Group_2__1_in_rule__Model__Group_2__03691);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1730:1: rule__Model__Group_2__0__Impl : ( KEYWORD_38 ) ;
    public final void rule__Model__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1734:1: ( ( KEYWORD_38 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1735:1: ( KEYWORD_38 )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1735:1: ( KEYWORD_38 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1736:1: KEYWORD_38
            {
             before(grammarAccess.getModelAccess().getORDERBYKeyword_2_0()); 
            match(input,KEYWORD_38,FOLLOW_KEYWORD_38_in_rule__Model__Group_2__0__Impl3719); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1749:1: rule__Model__Group_2__1 : rule__Model__Group_2__1__Impl ;
    public final void rule__Model__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1753:1: ( rule__Model__Group_2__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1754:2: rule__Model__Group_2__1__Impl
            {
            pushFollow(FOLLOW_rule__Model__Group_2__1__Impl_in_rule__Model__Group_2__13750);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1760:1: rule__Model__Group_2__1__Impl : ( ( rule__Model__OrderByEntryAssignment_2_1 ) ) ;
    public final void rule__Model__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1764:1: ( ( ( rule__Model__OrderByEntryAssignment_2_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1765:1: ( ( rule__Model__OrderByEntryAssignment_2_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1765:1: ( ( rule__Model__OrderByEntryAssignment_2_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1766:1: ( rule__Model__OrderByEntryAssignment_2_1 )
            {
             before(grammarAccess.getModelAccess().getOrderByEntryAssignment_2_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1767:1: ( rule__Model__OrderByEntryAssignment_2_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1767:2: rule__Model__OrderByEntryAssignment_2_1
            {
            pushFollow(FOLLOW_rule__Model__OrderByEntryAssignment_2_1_in_rule__Model__Group_2__1__Impl3777);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1781:1: rule__Select__Group__0 : rule__Select__Group__0__Impl rule__Select__Group__1 ;
    public final void rule__Select__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1785:1: ( rule__Select__Group__0__Impl rule__Select__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1786:2: rule__Select__Group__0__Impl rule__Select__Group__1
            {
            pushFollow(FOLLOW_rule__Select__Group__0__Impl_in_rule__Select__Group__03811);
            rule__Select__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Select__Group__1_in_rule__Select__Group__03814);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1793:1: rule__Select__Group__0__Impl : ( ( rule__Select__SelectAssignment_0 ) ) ;
    public final void rule__Select__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1797:1: ( ( ( rule__Select__SelectAssignment_0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1798:1: ( ( rule__Select__SelectAssignment_0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1798:1: ( ( rule__Select__SelectAssignment_0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1799:1: ( rule__Select__SelectAssignment_0 )
            {
             before(grammarAccess.getSelectAccess().getSelectAssignment_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1800:1: ( rule__Select__SelectAssignment_0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1800:2: rule__Select__SelectAssignment_0
            {
            pushFollow(FOLLOW_rule__Select__SelectAssignment_0_in_rule__Select__Group__0__Impl3841);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1810:1: rule__Select__Group__1 : rule__Select__Group__1__Impl rule__Select__Group__2 ;
    public final void rule__Select__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1814:1: ( rule__Select__Group__1__Impl rule__Select__Group__2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1815:2: rule__Select__Group__1__Impl rule__Select__Group__2
            {
            pushFollow(FOLLOW_rule__Select__Group__1__Impl_in_rule__Select__Group__13871);
            rule__Select__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Select__Group__2_in_rule__Select__Group__13874);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1822:1: rule__Select__Group__1__Impl : ( ( KEYWORD_34 )? ) ;
    public final void rule__Select__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1826:1: ( ( ( KEYWORD_34 )? ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1827:1: ( ( KEYWORD_34 )? )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1827:1: ( ( KEYWORD_34 )? )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1828:1: ( KEYWORD_34 )?
            {
             before(grammarAccess.getSelectAccess().getDISTINCTKeyword_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1829:1: ( KEYWORD_34 )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==KEYWORD_34) ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1830:2: KEYWORD_34
                    {
                    match(input,KEYWORD_34,FOLLOW_KEYWORD_34_in_rule__Select__Group__1__Impl3903); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1841:1: rule__Select__Group__2 : rule__Select__Group__2__Impl rule__Select__Group__3 ;
    public final void rule__Select__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1845:1: ( rule__Select__Group__2__Impl rule__Select__Group__3 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1846:2: rule__Select__Group__2__Impl rule__Select__Group__3
            {
            pushFollow(FOLLOW_rule__Select__Group__2__Impl_in_rule__Select__Group__23936);
            rule__Select__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Select__Group__3_in_rule__Select__Group__23939);
            rule__Select__Group__3();

            state._fsp--;


            }

        }
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1853:1: rule__Select__Group__2__Impl : ( ( rule__Select__ColsAssignment_2 ) ) ;
    public final void rule__Select__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1857:1: ( ( ( rule__Select__ColsAssignment_2 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1858:1: ( ( rule__Select__ColsAssignment_2 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1858:1: ( ( rule__Select__ColsAssignment_2 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1859:1: ( rule__Select__ColsAssignment_2 )
            {
             before(grammarAccess.getSelectAccess().getColsAssignment_2()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1860:1: ( rule__Select__ColsAssignment_2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1860:2: rule__Select__ColsAssignment_2
            {
            pushFollow(FOLLOW_rule__Select__ColsAssignment_2_in_rule__Select__Group__2__Impl3966);
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


    // $ANTLR start "rule__Select__Group__3"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1870:1: rule__Select__Group__3 : rule__Select__Group__3__Impl rule__Select__Group__4 ;
    public final void rule__Select__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1874:1: ( rule__Select__Group__3__Impl rule__Select__Group__4 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1875:2: rule__Select__Group__3__Impl rule__Select__Group__4
            {
            pushFollow(FOLLOW_rule__Select__Group__3__Impl_in_rule__Select__Group__33996);
            rule__Select__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Select__Group__4_in_rule__Select__Group__33999);
            rule__Select__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Select__Group__3"


    // $ANTLR start "rule__Select__Group__3__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1882:1: rule__Select__Group__3__Impl : ( KEYWORD_25 ) ;
    public final void rule__Select__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1886:1: ( ( KEYWORD_25 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1887:1: ( KEYWORD_25 )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1887:1: ( KEYWORD_25 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1888:1: KEYWORD_25
            {
             before(grammarAccess.getSelectAccess().getFROMKeyword_3()); 
            match(input,KEYWORD_25,FOLLOW_KEYWORD_25_in_rule__Select__Group__3__Impl4027); 
             after(grammarAccess.getSelectAccess().getFROMKeyword_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Select__Group__3__Impl"


    // $ANTLR start "rule__Select__Group__4"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1901:1: rule__Select__Group__4 : rule__Select__Group__4__Impl rule__Select__Group__5 ;
    public final void rule__Select__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1905:1: ( rule__Select__Group__4__Impl rule__Select__Group__5 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1906:2: rule__Select__Group__4__Impl rule__Select__Group__5
            {
            pushFollow(FOLLOW_rule__Select__Group__4__Impl_in_rule__Select__Group__44058);
            rule__Select__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Select__Group__5_in_rule__Select__Group__44061);
            rule__Select__Group__5();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Select__Group__4"


    // $ANTLR start "rule__Select__Group__4__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1913:1: rule__Select__Group__4__Impl : ( ( rule__Select__TblAssignment_4 ) ) ;
    public final void rule__Select__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1917:1: ( ( ( rule__Select__TblAssignment_4 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1918:1: ( ( rule__Select__TblAssignment_4 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1918:1: ( ( rule__Select__TblAssignment_4 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1919:1: ( rule__Select__TblAssignment_4 )
            {
             before(grammarAccess.getSelectAccess().getTblAssignment_4()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1920:1: ( rule__Select__TblAssignment_4 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1920:2: rule__Select__TblAssignment_4
            {
            pushFollow(FOLLOW_rule__Select__TblAssignment_4_in_rule__Select__Group__4__Impl4088);
            rule__Select__TblAssignment_4();

            state._fsp--;


            }

             after(grammarAccess.getSelectAccess().getTblAssignment_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Select__Group__4__Impl"


    // $ANTLR start "rule__Select__Group__5"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1930:1: rule__Select__Group__5 : rule__Select__Group__5__Impl rule__Select__Group__6 ;
    public final void rule__Select__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1934:1: ( rule__Select__Group__5__Impl rule__Select__Group__6 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1935:2: rule__Select__Group__5__Impl rule__Select__Group__6
            {
            pushFollow(FOLLOW_rule__Select__Group__5__Impl_in_rule__Select__Group__54118);
            rule__Select__Group__5__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Select__Group__6_in_rule__Select__Group__54121);
            rule__Select__Group__6();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Select__Group__5"


    // $ANTLR start "rule__Select__Group__5__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1942:1: rule__Select__Group__5__Impl : ( ( rule__Select__Group_5__0 )? ) ;
    public final void rule__Select__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1946:1: ( ( ( rule__Select__Group_5__0 )? ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1947:1: ( ( rule__Select__Group_5__0 )? )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1947:1: ( ( rule__Select__Group_5__0 )? )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1948:1: ( rule__Select__Group_5__0 )?
            {
             before(grammarAccess.getSelectAccess().getGroup_5()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1949:1: ( rule__Select__Group_5__0 )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==KEYWORD_29) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1949:2: rule__Select__Group_5__0
                    {
                    pushFollow(FOLLOW_rule__Select__Group_5__0_in_rule__Select__Group__5__Impl4148);
                    rule__Select__Group_5__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getSelectAccess().getGroup_5()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Select__Group__5__Impl"


    // $ANTLR start "rule__Select__Group__6"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1959:1: rule__Select__Group__6 : rule__Select__Group__6__Impl rule__Select__Group__7 ;
    public final void rule__Select__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1963:1: ( rule__Select__Group__6__Impl rule__Select__Group__7 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1964:2: rule__Select__Group__6__Impl rule__Select__Group__7
            {
            pushFollow(FOLLOW_rule__Select__Group__6__Impl_in_rule__Select__Group__64179);
            rule__Select__Group__6__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Select__Group__7_in_rule__Select__Group__64182);
            rule__Select__Group__7();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Select__Group__6"


    // $ANTLR start "rule__Select__Group__6__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1971:1: rule__Select__Group__6__Impl : ( ( rule__Select__Group_6__0 )? ) ;
    public final void rule__Select__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1975:1: ( ( ( rule__Select__Group_6__0 )? ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1976:1: ( ( rule__Select__Group_6__0 )? )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1976:1: ( ( rule__Select__Group_6__0 )? )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1977:1: ( rule__Select__Group_6__0 )?
            {
             before(grammarAccess.getSelectAccess().getGroup_6()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1978:1: ( rule__Select__Group_6__0 )?
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==KEYWORD_35) ) {
                alt22=1;
            }
            switch (alt22) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1978:2: rule__Select__Group_6__0
                    {
                    pushFollow(FOLLOW_rule__Select__Group_6__0_in_rule__Select__Group__6__Impl4209);
                    rule__Select__Group_6__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getSelectAccess().getGroup_6()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Select__Group__6__Impl"


    // $ANTLR start "rule__Select__Group__7"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1988:1: rule__Select__Group__7 : rule__Select__Group__7__Impl ;
    public final void rule__Select__Group__7() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1992:1: ( rule__Select__Group__7__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1993:2: rule__Select__Group__7__Impl
            {
            pushFollow(FOLLOW_rule__Select__Group__7__Impl_in_rule__Select__Group__74240);
            rule__Select__Group__7__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Select__Group__7"


    // $ANTLR start "rule__Select__Group__7__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1999:1: rule__Select__Group__7__Impl : ( ( rule__Select__Group_7__0 )? ) ;
    public final void rule__Select__Group__7__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2003:1: ( ( ( rule__Select__Group_7__0 )? ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2004:1: ( ( rule__Select__Group_7__0 )? )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2004:1: ( ( rule__Select__Group_7__0 )? )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2005:1: ( rule__Select__Group_7__0 )?
            {
             before(grammarAccess.getSelectAccess().getGroup_7()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2006:1: ( rule__Select__Group_7__0 )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==KEYWORD_30) ) {
                alt23=1;
            }
            switch (alt23) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2006:2: rule__Select__Group_7__0
                    {
                    pushFollow(FOLLOW_rule__Select__Group_7__0_in_rule__Select__Group__7__Impl4267);
                    rule__Select__Group_7__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getSelectAccess().getGroup_7()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Select__Group__7__Impl"


    // $ANTLR start "rule__Select__Group_5__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2032:1: rule__Select__Group_5__0 : rule__Select__Group_5__0__Impl rule__Select__Group_5__1 ;
    public final void rule__Select__Group_5__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2036:1: ( rule__Select__Group_5__0__Impl rule__Select__Group_5__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2037:2: rule__Select__Group_5__0__Impl rule__Select__Group_5__1
            {
            pushFollow(FOLLOW_rule__Select__Group_5__0__Impl_in_rule__Select__Group_5__04314);
            rule__Select__Group_5__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Select__Group_5__1_in_rule__Select__Group_5__04317);
            rule__Select__Group_5__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Select__Group_5__0"


    // $ANTLR start "rule__Select__Group_5__0__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2044:1: rule__Select__Group_5__0__Impl : ( KEYWORD_29 ) ;
    public final void rule__Select__Group_5__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2048:1: ( ( KEYWORD_29 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2049:1: ( KEYWORD_29 )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2049:1: ( KEYWORD_29 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2050:1: KEYWORD_29
            {
             before(grammarAccess.getSelectAccess().getWHEREKeyword_5_0()); 
            match(input,KEYWORD_29,FOLLOW_KEYWORD_29_in_rule__Select__Group_5__0__Impl4345); 
             after(grammarAccess.getSelectAccess().getWHEREKeyword_5_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Select__Group_5__0__Impl"


    // $ANTLR start "rule__Select__Group_5__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2063:1: rule__Select__Group_5__1 : rule__Select__Group_5__1__Impl ;
    public final void rule__Select__Group_5__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2067:1: ( rule__Select__Group_5__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2068:2: rule__Select__Group_5__1__Impl
            {
            pushFollow(FOLLOW_rule__Select__Group_5__1__Impl_in_rule__Select__Group_5__14376);
            rule__Select__Group_5__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Select__Group_5__1"


    // $ANTLR start "rule__Select__Group_5__1__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2074:1: rule__Select__Group_5__1__Impl : ( ( rule__Select__WhereExpressionAssignment_5_1 ) ) ;
    public final void rule__Select__Group_5__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2078:1: ( ( ( rule__Select__WhereExpressionAssignment_5_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2079:1: ( ( rule__Select__WhereExpressionAssignment_5_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2079:1: ( ( rule__Select__WhereExpressionAssignment_5_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2080:1: ( rule__Select__WhereExpressionAssignment_5_1 )
            {
             before(grammarAccess.getSelectAccess().getWhereExpressionAssignment_5_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2081:1: ( rule__Select__WhereExpressionAssignment_5_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2081:2: rule__Select__WhereExpressionAssignment_5_1
            {
            pushFollow(FOLLOW_rule__Select__WhereExpressionAssignment_5_1_in_rule__Select__Group_5__1__Impl4403);
            rule__Select__WhereExpressionAssignment_5_1();

            state._fsp--;


            }

             after(grammarAccess.getSelectAccess().getWhereExpressionAssignment_5_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Select__Group_5__1__Impl"


    // $ANTLR start "rule__Select__Group_6__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2095:1: rule__Select__Group_6__0 : rule__Select__Group_6__0__Impl rule__Select__Group_6__1 ;
    public final void rule__Select__Group_6__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2099:1: ( rule__Select__Group_6__0__Impl rule__Select__Group_6__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2100:2: rule__Select__Group_6__0__Impl rule__Select__Group_6__1
            {
            pushFollow(FOLLOW_rule__Select__Group_6__0__Impl_in_rule__Select__Group_6__04437);
            rule__Select__Group_6__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Select__Group_6__1_in_rule__Select__Group_6__04440);
            rule__Select__Group_6__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Select__Group_6__0"


    // $ANTLR start "rule__Select__Group_6__0__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2107:1: rule__Select__Group_6__0__Impl : ( KEYWORD_35 ) ;
    public final void rule__Select__Group_6__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2111:1: ( ( KEYWORD_35 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2112:1: ( KEYWORD_35 )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2112:1: ( KEYWORD_35 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2113:1: KEYWORD_35
            {
             before(grammarAccess.getSelectAccess().getGROUPBYKeyword_6_0()); 
            match(input,KEYWORD_35,FOLLOW_KEYWORD_35_in_rule__Select__Group_6__0__Impl4468); 
             after(grammarAccess.getSelectAccess().getGROUPBYKeyword_6_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Select__Group_6__0__Impl"


    // $ANTLR start "rule__Select__Group_6__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2126:1: rule__Select__Group_6__1 : rule__Select__Group_6__1__Impl ;
    public final void rule__Select__Group_6__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2130:1: ( rule__Select__Group_6__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2131:2: rule__Select__Group_6__1__Impl
            {
            pushFollow(FOLLOW_rule__Select__Group_6__1__Impl_in_rule__Select__Group_6__14499);
            rule__Select__Group_6__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Select__Group_6__1"


    // $ANTLR start "rule__Select__Group_6__1__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2137:1: rule__Select__Group_6__1__Impl : ( ( rule__Select__GroupByEntryAssignment_6_1 ) ) ;
    public final void rule__Select__Group_6__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2141:1: ( ( ( rule__Select__GroupByEntryAssignment_6_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2142:1: ( ( rule__Select__GroupByEntryAssignment_6_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2142:1: ( ( rule__Select__GroupByEntryAssignment_6_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2143:1: ( rule__Select__GroupByEntryAssignment_6_1 )
            {
             before(grammarAccess.getSelectAccess().getGroupByEntryAssignment_6_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2144:1: ( rule__Select__GroupByEntryAssignment_6_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2144:2: rule__Select__GroupByEntryAssignment_6_1
            {
            pushFollow(FOLLOW_rule__Select__GroupByEntryAssignment_6_1_in_rule__Select__Group_6__1__Impl4526);
            rule__Select__GroupByEntryAssignment_6_1();

            state._fsp--;


            }

             after(grammarAccess.getSelectAccess().getGroupByEntryAssignment_6_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Select__Group_6__1__Impl"


    // $ANTLR start "rule__Select__Group_7__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2158:1: rule__Select__Group_7__0 : rule__Select__Group_7__0__Impl rule__Select__Group_7__1 ;
    public final void rule__Select__Group_7__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2162:1: ( rule__Select__Group_7__0__Impl rule__Select__Group_7__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2163:2: rule__Select__Group_7__0__Impl rule__Select__Group_7__1
            {
            pushFollow(FOLLOW_rule__Select__Group_7__0__Impl_in_rule__Select__Group_7__04560);
            rule__Select__Group_7__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Select__Group_7__1_in_rule__Select__Group_7__04563);
            rule__Select__Group_7__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Select__Group_7__0"


    // $ANTLR start "rule__Select__Group_7__0__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2170:1: rule__Select__Group_7__0__Impl : ( KEYWORD_30 ) ;
    public final void rule__Select__Group_7__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2174:1: ( ( KEYWORD_30 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2175:1: ( KEYWORD_30 )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2175:1: ( KEYWORD_30 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2176:1: KEYWORD_30
            {
             before(grammarAccess.getSelectAccess().getHAVINGKeyword_7_0()); 
            match(input,KEYWORD_30,FOLLOW_KEYWORD_30_in_rule__Select__Group_7__0__Impl4591); 
             after(grammarAccess.getSelectAccess().getHAVINGKeyword_7_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Select__Group_7__0__Impl"


    // $ANTLR start "rule__Select__Group_7__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2189:1: rule__Select__Group_7__1 : rule__Select__Group_7__1__Impl ;
    public final void rule__Select__Group_7__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2193:1: ( rule__Select__Group_7__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2194:2: rule__Select__Group_7__1__Impl
            {
            pushFollow(FOLLOW_rule__Select__Group_7__1__Impl_in_rule__Select__Group_7__14622);
            rule__Select__Group_7__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Select__Group_7__1"


    // $ANTLR start "rule__Select__Group_7__1__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2200:1: rule__Select__Group_7__1__Impl : ( ( rule__Select__HavingEntryAssignment_7_1 ) ) ;
    public final void rule__Select__Group_7__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2204:1: ( ( ( rule__Select__HavingEntryAssignment_7_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2205:1: ( ( rule__Select__HavingEntryAssignment_7_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2205:1: ( ( rule__Select__HavingEntryAssignment_7_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2206:1: ( rule__Select__HavingEntryAssignment_7_1 )
            {
             before(grammarAccess.getSelectAccess().getHavingEntryAssignment_7_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2207:1: ( rule__Select__HavingEntryAssignment_7_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2207:2: rule__Select__HavingEntryAssignment_7_1
            {
            pushFollow(FOLLOW_rule__Select__HavingEntryAssignment_7_1_in_rule__Select__Group_7__1__Impl4649);
            rule__Select__HavingEntryAssignment_7_1();

            state._fsp--;


            }

             after(grammarAccess.getSelectAccess().getHavingEntryAssignment_7_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Select__Group_7__1__Impl"


    // $ANTLR start "rule__Columns__Group__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2221:1: rule__Columns__Group__0 : rule__Columns__Group__0__Impl rule__Columns__Group__1 ;
    public final void rule__Columns__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2225:1: ( rule__Columns__Group__0__Impl rule__Columns__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2226:2: rule__Columns__Group__0__Impl rule__Columns__Group__1
            {
            pushFollow(FOLLOW_rule__Columns__Group__0__Impl_in_rule__Columns__Group__04683);
            rule__Columns__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Columns__Group__1_in_rule__Columns__Group__04686);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2233:1: rule__Columns__Group__0__Impl : ( ruleColumnOrAlias ) ;
    public final void rule__Columns__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2237:1: ( ( ruleColumnOrAlias ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2238:1: ( ruleColumnOrAlias )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2238:1: ( ruleColumnOrAlias )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2239:1: ruleColumnOrAlias
            {
             before(grammarAccess.getColumnsAccess().getColumnOrAliasParserRuleCall_0()); 
            pushFollow(FOLLOW_ruleColumnOrAlias_in_rule__Columns__Group__0__Impl4713);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2250:1: rule__Columns__Group__1 : rule__Columns__Group__1__Impl ;
    public final void rule__Columns__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2254:1: ( rule__Columns__Group__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2255:2: rule__Columns__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__Columns__Group__1__Impl_in_rule__Columns__Group__14742);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2261:1: rule__Columns__Group__1__Impl : ( ( rule__Columns__Group_1__0 )? ) ;
    public final void rule__Columns__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2265:1: ( ( ( rule__Columns__Group_1__0 )? ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2266:1: ( ( rule__Columns__Group_1__0 )? )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2266:1: ( ( rule__Columns__Group_1__0 )? )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2267:1: ( rule__Columns__Group_1__0 )?
            {
             before(grammarAccess.getColumnsAccess().getGroup_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2268:1: ( rule__Columns__Group_1__0 )?
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( (LA24_0==KEYWORD_4) ) {
                alt24=1;
            }
            switch (alt24) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2268:2: rule__Columns__Group_1__0
                    {
                    pushFollow(FOLLOW_rule__Columns__Group_1__0_in_rule__Columns__Group__1__Impl4769);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2282:1: rule__Columns__Group_1__0 : rule__Columns__Group_1__0__Impl rule__Columns__Group_1__1 ;
    public final void rule__Columns__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2286:1: ( rule__Columns__Group_1__0__Impl rule__Columns__Group_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2287:2: rule__Columns__Group_1__0__Impl rule__Columns__Group_1__1
            {
            pushFollow(FOLLOW_rule__Columns__Group_1__0__Impl_in_rule__Columns__Group_1__04804);
            rule__Columns__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Columns__Group_1__1_in_rule__Columns__Group_1__04807);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2294:1: rule__Columns__Group_1__0__Impl : ( () ) ;
    public final void rule__Columns__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2298:1: ( ( () ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2299:1: ( () )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2299:1: ( () )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2300:1: ()
            {
             before(grammarAccess.getColumnsAccess().getOrColumnEntriesAction_1_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2301:1: ()
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2303:1: 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2313:1: rule__Columns__Group_1__1 : rule__Columns__Group_1__1__Impl ;
    public final void rule__Columns__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2317:1: ( rule__Columns__Group_1__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2318:2: rule__Columns__Group_1__1__Impl
            {
            pushFollow(FOLLOW_rule__Columns__Group_1__1__Impl_in_rule__Columns__Group_1__14865);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2324:1: rule__Columns__Group_1__1__Impl : ( ( ( rule__Columns__Group_1_1__0 ) ) ( ( rule__Columns__Group_1_1__0 )* ) ) ;
    public final void rule__Columns__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2328:1: ( ( ( ( rule__Columns__Group_1_1__0 ) ) ( ( rule__Columns__Group_1_1__0 )* ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2329:1: ( ( ( rule__Columns__Group_1_1__0 ) ) ( ( rule__Columns__Group_1_1__0 )* ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2329:1: ( ( ( rule__Columns__Group_1_1__0 ) ) ( ( rule__Columns__Group_1_1__0 )* ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2330:1: ( ( rule__Columns__Group_1_1__0 ) ) ( ( rule__Columns__Group_1_1__0 )* )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2330:1: ( ( rule__Columns__Group_1_1__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2331:1: ( rule__Columns__Group_1_1__0 )
            {
             before(grammarAccess.getColumnsAccess().getGroup_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2332:1: ( rule__Columns__Group_1_1__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2332:2: rule__Columns__Group_1_1__0
            {
            pushFollow(FOLLOW_rule__Columns__Group_1_1__0_in_rule__Columns__Group_1__1__Impl4894);
            rule__Columns__Group_1_1__0();

            state._fsp--;


            }

             after(grammarAccess.getColumnsAccess().getGroup_1_1()); 

            }

            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2335:1: ( ( rule__Columns__Group_1_1__0 )* )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2336:1: ( rule__Columns__Group_1_1__0 )*
            {
             before(grammarAccess.getColumnsAccess().getGroup_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2337:1: ( rule__Columns__Group_1_1__0 )*
            loop25:
            do {
                int alt25=2;
                int LA25_0 = input.LA(1);

                if ( (LA25_0==KEYWORD_4) ) {
                    alt25=1;
                }


                switch (alt25) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2337:2: rule__Columns__Group_1_1__0
            	    {
            	    pushFollow(FOLLOW_rule__Columns__Group_1_1__0_in_rule__Columns__Group_1__1__Impl4906);
            	    rule__Columns__Group_1_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop25;
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2352:1: rule__Columns__Group_1_1__0 : rule__Columns__Group_1_1__0__Impl rule__Columns__Group_1_1__1 ;
    public final void rule__Columns__Group_1_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2356:1: ( rule__Columns__Group_1_1__0__Impl rule__Columns__Group_1_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2357:2: rule__Columns__Group_1_1__0__Impl rule__Columns__Group_1_1__1
            {
            pushFollow(FOLLOW_rule__Columns__Group_1_1__0__Impl_in_rule__Columns__Group_1_1__04943);
            rule__Columns__Group_1_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Columns__Group_1_1__1_in_rule__Columns__Group_1_1__04946);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2364:1: rule__Columns__Group_1_1__0__Impl : ( KEYWORD_4 ) ;
    public final void rule__Columns__Group_1_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2368:1: ( ( KEYWORD_4 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2369:1: ( KEYWORD_4 )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2369:1: ( KEYWORD_4 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2370:1: KEYWORD_4
            {
             before(grammarAccess.getColumnsAccess().getCommaKeyword_1_1_0()); 
            match(input,KEYWORD_4,FOLLOW_KEYWORD_4_in_rule__Columns__Group_1_1__0__Impl4974); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2383:1: rule__Columns__Group_1_1__1 : rule__Columns__Group_1_1__1__Impl ;
    public final void rule__Columns__Group_1_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2387:1: ( rule__Columns__Group_1_1__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2388:2: rule__Columns__Group_1_1__1__Impl
            {
            pushFollow(FOLLOW_rule__Columns__Group_1_1__1__Impl_in_rule__Columns__Group_1_1__15005);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2394:1: rule__Columns__Group_1_1__1__Impl : ( ( rule__Columns__EntriesAssignment_1_1_1 ) ) ;
    public final void rule__Columns__Group_1_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2398:1: ( ( ( rule__Columns__EntriesAssignment_1_1_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2399:1: ( ( rule__Columns__EntriesAssignment_1_1_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2399:1: ( ( rule__Columns__EntriesAssignment_1_1_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2400:1: ( rule__Columns__EntriesAssignment_1_1_1 )
            {
             before(grammarAccess.getColumnsAccess().getEntriesAssignment_1_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2401:1: ( rule__Columns__EntriesAssignment_1_1_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2401:2: rule__Columns__EntriesAssignment_1_1_1
            {
            pushFollow(FOLLOW_rule__Columns__EntriesAssignment_1_1_1_in_rule__Columns__Group_1_1__1__Impl5032);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2415:1: rule__ColumnOrAlias__Group_0__0 : rule__ColumnOrAlias__Group_0__0__Impl rule__ColumnOrAlias__Group_0__1 ;
    public final void rule__ColumnOrAlias__Group_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2419:1: ( rule__ColumnOrAlias__Group_0__0__Impl rule__ColumnOrAlias__Group_0__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2420:2: rule__ColumnOrAlias__Group_0__0__Impl rule__ColumnOrAlias__Group_0__1
            {
            pushFollow(FOLLOW_rule__ColumnOrAlias__Group_0__0__Impl_in_rule__ColumnOrAlias__Group_0__05066);
            rule__ColumnOrAlias__Group_0__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ColumnOrAlias__Group_0__1_in_rule__ColumnOrAlias__Group_0__05069);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2427:1: rule__ColumnOrAlias__Group_0__0__Impl : ( ruleColumnFull ) ;
    public final void rule__ColumnOrAlias__Group_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2431:1: ( ( ruleColumnFull ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2432:1: ( ruleColumnFull )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2432:1: ( ruleColumnFull )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2433:1: ruleColumnFull
            {
             before(grammarAccess.getColumnOrAliasAccess().getColumnFullParserRuleCall_0_0()); 
            pushFollow(FOLLOW_ruleColumnFull_in_rule__ColumnOrAlias__Group_0__0__Impl5096);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2444:1: rule__ColumnOrAlias__Group_0__1 : rule__ColumnOrAlias__Group_0__1__Impl rule__ColumnOrAlias__Group_0__2 ;
    public final void rule__ColumnOrAlias__Group_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2448:1: ( rule__ColumnOrAlias__Group_0__1__Impl rule__ColumnOrAlias__Group_0__2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2449:2: rule__ColumnOrAlias__Group_0__1__Impl rule__ColumnOrAlias__Group_0__2
            {
            pushFollow(FOLLOW_rule__ColumnOrAlias__Group_0__1__Impl_in_rule__ColumnOrAlias__Group_0__15125);
            rule__ColumnOrAlias__Group_0__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ColumnOrAlias__Group_0__2_in_rule__ColumnOrAlias__Group_0__15128);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2456:1: rule__ColumnOrAlias__Group_0__1__Impl : ( ( KEYWORD_15 )? ) ;
    public final void rule__ColumnOrAlias__Group_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2460:1: ( ( ( KEYWORD_15 )? ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2461:1: ( ( KEYWORD_15 )? )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2461:1: ( ( KEYWORD_15 )? )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2462:1: ( KEYWORD_15 )?
            {
             before(grammarAccess.getColumnOrAliasAccess().getASKeyword_0_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2463:1: ( KEYWORD_15 )?
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( (LA26_0==KEYWORD_15) ) {
                alt26=1;
            }
            switch (alt26) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2464:2: KEYWORD_15
                    {
                    match(input,KEYWORD_15,FOLLOW_KEYWORD_15_in_rule__ColumnOrAlias__Group_0__1__Impl5157); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2475:1: rule__ColumnOrAlias__Group_0__2 : rule__ColumnOrAlias__Group_0__2__Impl ;
    public final void rule__ColumnOrAlias__Group_0__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2479:1: ( rule__ColumnOrAlias__Group_0__2__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2480:2: rule__ColumnOrAlias__Group_0__2__Impl
            {
            pushFollow(FOLLOW_rule__ColumnOrAlias__Group_0__2__Impl_in_rule__ColumnOrAlias__Group_0__25190);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2486:1: rule__ColumnOrAlias__Group_0__2__Impl : ( ( rule__ColumnOrAlias__ColAliasAssignment_0_2 )? ) ;
    public final void rule__ColumnOrAlias__Group_0__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2490:1: ( ( ( rule__ColumnOrAlias__ColAliasAssignment_0_2 )? ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2491:1: ( ( rule__ColumnOrAlias__ColAliasAssignment_0_2 )? )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2491:1: ( ( rule__ColumnOrAlias__ColAliasAssignment_0_2 )? )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2492:1: ( rule__ColumnOrAlias__ColAliasAssignment_0_2 )?
            {
             before(grammarAccess.getColumnOrAliasAccess().getColAliasAssignment_0_2()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2493:1: ( rule__ColumnOrAlias__ColAliasAssignment_0_2 )?
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==RULE_ID) ) {
                alt27=1;
            }
            switch (alt27) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2493:2: rule__ColumnOrAlias__ColAliasAssignment_0_2
                    {
                    pushFollow(FOLLOW_rule__ColumnOrAlias__ColAliasAssignment_0_2_in_rule__ColumnOrAlias__Group_0__2__Impl5217);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2509:1: rule__ColumnFull__Group__0 : rule__ColumnFull__Group__0__Impl rule__ColumnFull__Group__1 ;
    public final void rule__ColumnFull__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2513:1: ( rule__ColumnFull__Group__0__Impl rule__ColumnFull__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2514:2: rule__ColumnFull__Group__0__Impl rule__ColumnFull__Group__1
            {
            pushFollow(FOLLOW_rule__ColumnFull__Group__0__Impl_in_rule__ColumnFull__Group__05254);
            rule__ColumnFull__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ColumnFull__Group__1_in_rule__ColumnFull__Group__05257);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2521:1: rule__ColumnFull__Group__0__Impl : ( ruleDbObjectName ) ;
    public final void rule__ColumnFull__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2525:1: ( ( ruleDbObjectName ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2526:1: ( ruleDbObjectName )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2526:1: ( ruleDbObjectName )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2527:1: ruleDbObjectName
            {
             before(grammarAccess.getColumnFullAccess().getDbObjectNameParserRuleCall_0()); 
            pushFollow(FOLLOW_ruleDbObjectName_in_rule__ColumnFull__Group__0__Impl5284);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2538:1: rule__ColumnFull__Group__1 : rule__ColumnFull__Group__1__Impl ;
    public final void rule__ColumnFull__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2542:1: ( rule__ColumnFull__Group__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2543:2: rule__ColumnFull__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__ColumnFull__Group__1__Impl_in_rule__ColumnFull__Group__15313);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2549:1: rule__ColumnFull__Group__1__Impl : ( ( rule__ColumnFull__Group_1__0 )? ) ;
    public final void rule__ColumnFull__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2553:1: ( ( ( rule__ColumnFull__Group_1__0 )? ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2554:1: ( ( rule__ColumnFull__Group_1__0 )? )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2554:1: ( ( rule__ColumnFull__Group_1__0 )? )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2555:1: ( rule__ColumnFull__Group_1__0 )?
            {
             before(grammarAccess.getColumnFullAccess().getGroup_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2556:1: ( rule__ColumnFull__Group_1__0 )?
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==KEYWORD_6) ) {
                alt28=1;
            }
            switch (alt28) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2556:2: rule__ColumnFull__Group_1__0
                    {
                    pushFollow(FOLLOW_rule__ColumnFull__Group_1__0_in_rule__ColumnFull__Group__1__Impl5340);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2570:1: rule__ColumnFull__Group_1__0 : rule__ColumnFull__Group_1__0__Impl rule__ColumnFull__Group_1__1 ;
    public final void rule__ColumnFull__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2574:1: ( rule__ColumnFull__Group_1__0__Impl rule__ColumnFull__Group_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2575:2: rule__ColumnFull__Group_1__0__Impl rule__ColumnFull__Group_1__1
            {
            pushFollow(FOLLOW_rule__ColumnFull__Group_1__0__Impl_in_rule__ColumnFull__Group_1__05375);
            rule__ColumnFull__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ColumnFull__Group_1__1_in_rule__ColumnFull__Group_1__05378);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2582:1: rule__ColumnFull__Group_1__0__Impl : ( () ) ;
    public final void rule__ColumnFull__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2586:1: ( ( () ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2587:1: ( () )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2587:1: ( () )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2588:1: ()
            {
             before(grammarAccess.getColumnFullAccess().getColEntriesAction_1_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2589:1: ()
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2591:1: 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2601:1: rule__ColumnFull__Group_1__1 : rule__ColumnFull__Group_1__1__Impl ;
    public final void rule__ColumnFull__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2605:1: ( rule__ColumnFull__Group_1__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2606:2: rule__ColumnFull__Group_1__1__Impl
            {
            pushFollow(FOLLOW_rule__ColumnFull__Group_1__1__Impl_in_rule__ColumnFull__Group_1__15436);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2612:1: rule__ColumnFull__Group_1__1__Impl : ( ( ( rule__ColumnFull__Group_1_1__0 ) ) ( ( rule__ColumnFull__Group_1_1__0 )* ) ) ;
    public final void rule__ColumnFull__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2616:1: ( ( ( ( rule__ColumnFull__Group_1_1__0 ) ) ( ( rule__ColumnFull__Group_1_1__0 )* ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2617:1: ( ( ( rule__ColumnFull__Group_1_1__0 ) ) ( ( rule__ColumnFull__Group_1_1__0 )* ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2617:1: ( ( ( rule__ColumnFull__Group_1_1__0 ) ) ( ( rule__ColumnFull__Group_1_1__0 )* ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2618:1: ( ( rule__ColumnFull__Group_1_1__0 ) ) ( ( rule__ColumnFull__Group_1_1__0 )* )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2618:1: ( ( rule__ColumnFull__Group_1_1__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2619:1: ( rule__ColumnFull__Group_1_1__0 )
            {
             before(grammarAccess.getColumnFullAccess().getGroup_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2620:1: ( rule__ColumnFull__Group_1_1__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2620:2: rule__ColumnFull__Group_1_1__0
            {
            pushFollow(FOLLOW_rule__ColumnFull__Group_1_1__0_in_rule__ColumnFull__Group_1__1__Impl5465);
            rule__ColumnFull__Group_1_1__0();

            state._fsp--;


            }

             after(grammarAccess.getColumnFullAccess().getGroup_1_1()); 

            }

            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2623:1: ( ( rule__ColumnFull__Group_1_1__0 )* )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2624:1: ( rule__ColumnFull__Group_1_1__0 )*
            {
             before(grammarAccess.getColumnFullAccess().getGroup_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2625:1: ( rule__ColumnFull__Group_1_1__0 )*
            loop29:
            do {
                int alt29=2;
                int LA29_0 = input.LA(1);

                if ( (LA29_0==KEYWORD_6) ) {
                    alt29=1;
                }


                switch (alt29) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2625:2: rule__ColumnFull__Group_1_1__0
            	    {
            	    pushFollow(FOLLOW_rule__ColumnFull__Group_1_1__0_in_rule__ColumnFull__Group_1__1__Impl5477);
            	    rule__ColumnFull__Group_1_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop29;
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2640:1: rule__ColumnFull__Group_1_1__0 : rule__ColumnFull__Group_1_1__0__Impl rule__ColumnFull__Group_1_1__1 ;
    public final void rule__ColumnFull__Group_1_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2644:1: ( rule__ColumnFull__Group_1_1__0__Impl rule__ColumnFull__Group_1_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2645:2: rule__ColumnFull__Group_1_1__0__Impl rule__ColumnFull__Group_1_1__1
            {
            pushFollow(FOLLOW_rule__ColumnFull__Group_1_1__0__Impl_in_rule__ColumnFull__Group_1_1__05514);
            rule__ColumnFull__Group_1_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ColumnFull__Group_1_1__1_in_rule__ColumnFull__Group_1_1__05517);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2652:1: rule__ColumnFull__Group_1_1__0__Impl : ( KEYWORD_6 ) ;
    public final void rule__ColumnFull__Group_1_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2656:1: ( ( KEYWORD_6 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2657:1: ( KEYWORD_6 )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2657:1: ( KEYWORD_6 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2658:1: KEYWORD_6
            {
             before(grammarAccess.getColumnFullAccess().getFullStopKeyword_1_1_0()); 
            match(input,KEYWORD_6,FOLLOW_KEYWORD_6_in_rule__ColumnFull__Group_1_1__0__Impl5545); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2671:1: rule__ColumnFull__Group_1_1__1 : rule__ColumnFull__Group_1_1__1__Impl ;
    public final void rule__ColumnFull__Group_1_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2675:1: ( rule__ColumnFull__Group_1_1__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2676:2: rule__ColumnFull__Group_1_1__1__Impl
            {
            pushFollow(FOLLOW_rule__ColumnFull__Group_1_1__1__Impl_in_rule__ColumnFull__Group_1_1__15576);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2682:1: rule__ColumnFull__Group_1_1__1__Impl : ( ( rule__ColumnFull__EntriesAssignment_1_1_1 ) ) ;
    public final void rule__ColumnFull__Group_1_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2686:1: ( ( ( rule__ColumnFull__EntriesAssignment_1_1_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2687:1: ( ( rule__ColumnFull__EntriesAssignment_1_1_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2687:1: ( ( rule__ColumnFull__EntriesAssignment_1_1_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2688:1: ( rule__ColumnFull__EntriesAssignment_1_1_1 )
            {
             before(grammarAccess.getColumnFullAccess().getEntriesAssignment_1_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2689:1: ( rule__ColumnFull__EntriesAssignment_1_1_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2689:2: rule__ColumnFull__EntriesAssignment_1_1_1
            {
            pushFollow(FOLLOW_rule__ColumnFull__EntriesAssignment_1_1_1_in_rule__ColumnFull__Group_1_1__1__Impl5603);
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


    // $ANTLR start "rule__Tables__Group__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2703:1: rule__Tables__Group__0 : rule__Tables__Group__0__Impl rule__Tables__Group__1 ;
    public final void rule__Tables__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2707:1: ( rule__Tables__Group__0__Impl rule__Tables__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2708:2: rule__Tables__Group__0__Impl rule__Tables__Group__1
            {
            pushFollow(FOLLOW_rule__Tables__Group__0__Impl_in_rule__Tables__Group__05637);
            rule__Tables__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Tables__Group__1_in_rule__Tables__Group__05640);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2715:1: rule__Tables__Group__0__Impl : ( ruleFromTable ) ;
    public final void rule__Tables__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2719:1: ( ( ruleFromTable ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2720:1: ( ruleFromTable )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2720:1: ( ruleFromTable )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2721:1: ruleFromTable
            {
             before(grammarAccess.getTablesAccess().getFromTableParserRuleCall_0()); 
            pushFollow(FOLLOW_ruleFromTable_in_rule__Tables__Group__0__Impl5667);
            ruleFromTable();

            state._fsp--;

             after(grammarAccess.getTablesAccess().getFromTableParserRuleCall_0()); 

            }


            }

        }
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2732:1: rule__Tables__Group__1 : rule__Tables__Group__1__Impl ;
    public final void rule__Tables__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2736:1: ( rule__Tables__Group__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2737:2: rule__Tables__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__Tables__Group__1__Impl_in_rule__Tables__Group__15696);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2743:1: rule__Tables__Group__1__Impl : ( ( rule__Tables__Group_1__0 )? ) ;
    public final void rule__Tables__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2747:1: ( ( ( rule__Tables__Group_1__0 )? ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2748:1: ( ( rule__Tables__Group_1__0 )? )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2748:1: ( ( rule__Tables__Group_1__0 )? )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2749:1: ( rule__Tables__Group_1__0 )?
            {
             before(grammarAccess.getTablesAccess().getGroup_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2750:1: ( rule__Tables__Group_1__0 )?
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( (LA30_0==KEYWORD_4) ) {
                alt30=1;
            }
            switch (alt30) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2750:2: rule__Tables__Group_1__0
                    {
                    pushFollow(FOLLOW_rule__Tables__Group_1__0_in_rule__Tables__Group__1__Impl5723);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2764:1: rule__Tables__Group_1__0 : rule__Tables__Group_1__0__Impl rule__Tables__Group_1__1 ;
    public final void rule__Tables__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2768:1: ( rule__Tables__Group_1__0__Impl rule__Tables__Group_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2769:2: rule__Tables__Group_1__0__Impl rule__Tables__Group_1__1
            {
            pushFollow(FOLLOW_rule__Tables__Group_1__0__Impl_in_rule__Tables__Group_1__05758);
            rule__Tables__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Tables__Group_1__1_in_rule__Tables__Group_1__05761);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2776:1: rule__Tables__Group_1__0__Impl : ( () ) ;
    public final void rule__Tables__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2780:1: ( ( () ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2781:1: ( () )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2781:1: ( () )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2782:1: ()
            {
             before(grammarAccess.getTablesAccess().getOrTableEntriesAction_1_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2783:1: ()
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2785:1: 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2795:1: rule__Tables__Group_1__1 : rule__Tables__Group_1__1__Impl ;
    public final void rule__Tables__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2799:1: ( rule__Tables__Group_1__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2800:2: rule__Tables__Group_1__1__Impl
            {
            pushFollow(FOLLOW_rule__Tables__Group_1__1__Impl_in_rule__Tables__Group_1__15819);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2806:1: rule__Tables__Group_1__1__Impl : ( ( ( rule__Tables__Group_1_1__0 ) ) ( ( rule__Tables__Group_1_1__0 )* ) ) ;
    public final void rule__Tables__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2810:1: ( ( ( ( rule__Tables__Group_1_1__0 ) ) ( ( rule__Tables__Group_1_1__0 )* ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2811:1: ( ( ( rule__Tables__Group_1_1__0 ) ) ( ( rule__Tables__Group_1_1__0 )* ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2811:1: ( ( ( rule__Tables__Group_1_1__0 ) ) ( ( rule__Tables__Group_1_1__0 )* ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2812:1: ( ( rule__Tables__Group_1_1__0 ) ) ( ( rule__Tables__Group_1_1__0 )* )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2812:1: ( ( rule__Tables__Group_1_1__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2813:1: ( rule__Tables__Group_1_1__0 )
            {
             before(grammarAccess.getTablesAccess().getGroup_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2814:1: ( rule__Tables__Group_1_1__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2814:2: rule__Tables__Group_1_1__0
            {
            pushFollow(FOLLOW_rule__Tables__Group_1_1__0_in_rule__Tables__Group_1__1__Impl5848);
            rule__Tables__Group_1_1__0();

            state._fsp--;


            }

             after(grammarAccess.getTablesAccess().getGroup_1_1()); 

            }

            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2817:1: ( ( rule__Tables__Group_1_1__0 )* )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2818:1: ( rule__Tables__Group_1_1__0 )*
            {
             before(grammarAccess.getTablesAccess().getGroup_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2819:1: ( rule__Tables__Group_1_1__0 )*
            loop31:
            do {
                int alt31=2;
                int LA31_0 = input.LA(1);

                if ( (LA31_0==KEYWORD_4) ) {
                    alt31=1;
                }


                switch (alt31) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2819:2: rule__Tables__Group_1_1__0
            	    {
            	    pushFollow(FOLLOW_rule__Tables__Group_1_1__0_in_rule__Tables__Group_1__1__Impl5860);
            	    rule__Tables__Group_1_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop31;
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2834:1: rule__Tables__Group_1_1__0 : rule__Tables__Group_1_1__0__Impl rule__Tables__Group_1_1__1 ;
    public final void rule__Tables__Group_1_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2838:1: ( rule__Tables__Group_1_1__0__Impl rule__Tables__Group_1_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2839:2: rule__Tables__Group_1_1__0__Impl rule__Tables__Group_1_1__1
            {
            pushFollow(FOLLOW_rule__Tables__Group_1_1__0__Impl_in_rule__Tables__Group_1_1__05897);
            rule__Tables__Group_1_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Tables__Group_1_1__1_in_rule__Tables__Group_1_1__05900);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2846:1: rule__Tables__Group_1_1__0__Impl : ( KEYWORD_4 ) ;
    public final void rule__Tables__Group_1_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2850:1: ( ( KEYWORD_4 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2851:1: ( KEYWORD_4 )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2851:1: ( KEYWORD_4 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2852:1: KEYWORD_4
            {
             before(grammarAccess.getTablesAccess().getCommaKeyword_1_1_0()); 
            match(input,KEYWORD_4,FOLLOW_KEYWORD_4_in_rule__Tables__Group_1_1__0__Impl5928); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2865:1: rule__Tables__Group_1_1__1 : rule__Tables__Group_1_1__1__Impl ;
    public final void rule__Tables__Group_1_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2869:1: ( rule__Tables__Group_1_1__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2870:2: rule__Tables__Group_1_1__1__Impl
            {
            pushFollow(FOLLOW_rule__Tables__Group_1_1__1__Impl_in_rule__Tables__Group_1_1__15959);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2876:1: rule__Tables__Group_1_1__1__Impl : ( ( rule__Tables__EntriesAssignment_1_1_1 ) ) ;
    public final void rule__Tables__Group_1_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2880:1: ( ( ( rule__Tables__EntriesAssignment_1_1_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2881:1: ( ( rule__Tables__EntriesAssignment_1_1_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2881:1: ( ( rule__Tables__EntriesAssignment_1_1_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2882:1: ( rule__Tables__EntriesAssignment_1_1_1 )
            {
             before(grammarAccess.getTablesAccess().getEntriesAssignment_1_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2883:1: ( rule__Tables__EntriesAssignment_1_1_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2883:2: rule__Tables__EntriesAssignment_1_1_1
            {
            pushFollow(FOLLOW_rule__Tables__EntriesAssignment_1_1_1_in_rule__Tables__Group_1_1__1__Impl5986);
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


    // $ANTLR start "rule__FromTable__Group__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2897:1: rule__FromTable__Group__0 : rule__FromTable__Group__0__Impl rule__FromTable__Group__1 ;
    public final void rule__FromTable__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2901:1: ( rule__FromTable__Group__0__Impl rule__FromTable__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2902:2: rule__FromTable__Group__0__Impl rule__FromTable__Group__1
            {
            pushFollow(FOLLOW_rule__FromTable__Group__0__Impl_in_rule__FromTable__Group__06020);
            rule__FromTable__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__FromTable__Group__1_in_rule__FromTable__Group__06023);
            rule__FromTable__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FromTable__Group__0"


    // $ANTLR start "rule__FromTable__Group__0__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2909:1: rule__FromTable__Group__0__Impl : ( ( rule__FromTable__TableAssignment_0 ) ) ;
    public final void rule__FromTable__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2913:1: ( ( ( rule__FromTable__TableAssignment_0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2914:1: ( ( rule__FromTable__TableAssignment_0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2914:1: ( ( rule__FromTable__TableAssignment_0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2915:1: ( rule__FromTable__TableAssignment_0 )
            {
             before(grammarAccess.getFromTableAccess().getTableAssignment_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2916:1: ( rule__FromTable__TableAssignment_0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2916:2: rule__FromTable__TableAssignment_0
            {
            pushFollow(FOLLOW_rule__FromTable__TableAssignment_0_in_rule__FromTable__Group__0__Impl6050);
            rule__FromTable__TableAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getFromTableAccess().getTableAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FromTable__Group__0__Impl"


    // $ANTLR start "rule__FromTable__Group__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2926:1: rule__FromTable__Group__1 : rule__FromTable__Group__1__Impl ;
    public final void rule__FromTable__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2930:1: ( rule__FromTable__Group__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2931:2: rule__FromTable__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__FromTable__Group__1__Impl_in_rule__FromTable__Group__16080);
            rule__FromTable__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FromTable__Group__1"


    // $ANTLR start "rule__FromTable__Group__1__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2937:1: rule__FromTable__Group__1__Impl : ( ( rule__FromTable__Group_1__0 )? ) ;
    public final void rule__FromTable__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2941:1: ( ( ( rule__FromTable__Group_1__0 )? ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2942:1: ( ( rule__FromTable__Group_1__0 )? )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2942:1: ( ( rule__FromTable__Group_1__0 )? )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2943:1: ( rule__FromTable__Group_1__0 )?
            {
             before(grammarAccess.getFromTableAccess().getGroup_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2944:1: ( rule__FromTable__Group_1__0 )?
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( ((LA32_0>=KEYWORD_44 && LA32_0<=KEYWORD_43)||(LA32_0>=KEYWORD_39 && LA32_0<=KEYWORD_40)) ) {
                alt32=1;
            }
            switch (alt32) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2944:2: rule__FromTable__Group_1__0
                    {
                    pushFollow(FOLLOW_rule__FromTable__Group_1__0_in_rule__FromTable__Group__1__Impl6107);
                    rule__FromTable__Group_1__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getFromTableAccess().getGroup_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FromTable__Group__1__Impl"


    // $ANTLR start "rule__FromTable__Group_1__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2958:1: rule__FromTable__Group_1__0 : rule__FromTable__Group_1__0__Impl rule__FromTable__Group_1__1 ;
    public final void rule__FromTable__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2962:1: ( rule__FromTable__Group_1__0__Impl rule__FromTable__Group_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2963:2: rule__FromTable__Group_1__0__Impl rule__FromTable__Group_1__1
            {
            pushFollow(FOLLOW_rule__FromTable__Group_1__0__Impl_in_rule__FromTable__Group_1__06142);
            rule__FromTable__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__FromTable__Group_1__1_in_rule__FromTable__Group_1__06145);
            rule__FromTable__Group_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FromTable__Group_1__0"


    // $ANTLR start "rule__FromTable__Group_1__0__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2970:1: rule__FromTable__Group_1__0__Impl : ( ( rule__FromTable__JoinAssignment_1_0 ) ) ;
    public final void rule__FromTable__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2974:1: ( ( ( rule__FromTable__JoinAssignment_1_0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2975:1: ( ( rule__FromTable__JoinAssignment_1_0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2975:1: ( ( rule__FromTable__JoinAssignment_1_0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2976:1: ( rule__FromTable__JoinAssignment_1_0 )
            {
             before(grammarAccess.getFromTableAccess().getJoinAssignment_1_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2977:1: ( rule__FromTable__JoinAssignment_1_0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2977:2: rule__FromTable__JoinAssignment_1_0
            {
            pushFollow(FOLLOW_rule__FromTable__JoinAssignment_1_0_in_rule__FromTable__Group_1__0__Impl6172);
            rule__FromTable__JoinAssignment_1_0();

            state._fsp--;


            }

             after(grammarAccess.getFromTableAccess().getJoinAssignment_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FromTable__Group_1__0__Impl"


    // $ANTLR start "rule__FromTable__Group_1__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2987:1: rule__FromTable__Group_1__1 : rule__FromTable__Group_1__1__Impl rule__FromTable__Group_1__2 ;
    public final void rule__FromTable__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2991:1: ( rule__FromTable__Group_1__1__Impl rule__FromTable__Group_1__2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2992:2: rule__FromTable__Group_1__1__Impl rule__FromTable__Group_1__2
            {
            pushFollow(FOLLOW_rule__FromTable__Group_1__1__Impl_in_rule__FromTable__Group_1__16202);
            rule__FromTable__Group_1__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__FromTable__Group_1__2_in_rule__FromTable__Group_1__16205);
            rule__FromTable__Group_1__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FromTable__Group_1__1"


    // $ANTLR start "rule__FromTable__Group_1__1__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2999:1: rule__FromTable__Group_1__1__Impl : ( ( rule__FromTable__OnTableAssignment_1_1 ) ) ;
    public final void rule__FromTable__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3003:1: ( ( ( rule__FromTable__OnTableAssignment_1_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3004:1: ( ( rule__FromTable__OnTableAssignment_1_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3004:1: ( ( rule__FromTable__OnTableAssignment_1_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3005:1: ( rule__FromTable__OnTableAssignment_1_1 )
            {
             before(grammarAccess.getFromTableAccess().getOnTableAssignment_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3006:1: ( rule__FromTable__OnTableAssignment_1_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3006:2: rule__FromTable__OnTableAssignment_1_1
            {
            pushFollow(FOLLOW_rule__FromTable__OnTableAssignment_1_1_in_rule__FromTable__Group_1__1__Impl6232);
            rule__FromTable__OnTableAssignment_1_1();

            state._fsp--;


            }

             after(grammarAccess.getFromTableAccess().getOnTableAssignment_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FromTable__Group_1__1__Impl"


    // $ANTLR start "rule__FromTable__Group_1__2"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3016:1: rule__FromTable__Group_1__2 : rule__FromTable__Group_1__2__Impl rule__FromTable__Group_1__3 ;
    public final void rule__FromTable__Group_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3020:1: ( rule__FromTable__Group_1__2__Impl rule__FromTable__Group_1__3 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3021:2: rule__FromTable__Group_1__2__Impl rule__FromTable__Group_1__3
            {
            pushFollow(FOLLOW_rule__FromTable__Group_1__2__Impl_in_rule__FromTable__Group_1__26262);
            rule__FromTable__Group_1__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__FromTable__Group_1__3_in_rule__FromTable__Group_1__26265);
            rule__FromTable__Group_1__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FromTable__Group_1__2"


    // $ANTLR start "rule__FromTable__Group_1__2__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3028:1: rule__FromTable__Group_1__2__Impl : ( KEYWORD_16 ) ;
    public final void rule__FromTable__Group_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3032:1: ( ( KEYWORD_16 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3033:1: ( KEYWORD_16 )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3033:1: ( KEYWORD_16 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3034:1: KEYWORD_16
            {
             before(grammarAccess.getFromTableAccess().getONKeyword_1_2()); 
            match(input,KEYWORD_16,FOLLOW_KEYWORD_16_in_rule__FromTable__Group_1__2__Impl6293); 
             after(grammarAccess.getFromTableAccess().getONKeyword_1_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FromTable__Group_1__2__Impl"


    // $ANTLR start "rule__FromTable__Group_1__3"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3047:1: rule__FromTable__Group_1__3 : rule__FromTable__Group_1__3__Impl ;
    public final void rule__FromTable__Group_1__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3051:1: ( rule__FromTable__Group_1__3__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3052:2: rule__FromTable__Group_1__3__Impl
            {
            pushFollow(FOLLOW_rule__FromTable__Group_1__3__Impl_in_rule__FromTable__Group_1__36324);
            rule__FromTable__Group_1__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FromTable__Group_1__3"


    // $ANTLR start "rule__FromTable__Group_1__3__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3058:1: rule__FromTable__Group_1__3__Impl : ( ( rule__FromTable__JoinExprAssignment_1_3 ) ) ;
    public final void rule__FromTable__Group_1__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3062:1: ( ( ( rule__FromTable__JoinExprAssignment_1_3 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3063:1: ( ( rule__FromTable__JoinExprAssignment_1_3 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3063:1: ( ( rule__FromTable__JoinExprAssignment_1_3 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3064:1: ( rule__FromTable__JoinExprAssignment_1_3 )
            {
             before(grammarAccess.getFromTableAccess().getJoinExprAssignment_1_3()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3065:1: ( rule__FromTable__JoinExprAssignment_1_3 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3065:2: rule__FromTable__JoinExprAssignment_1_3
            {
            pushFollow(FOLLOW_rule__FromTable__JoinExprAssignment_1_3_in_rule__FromTable__Group_1__3__Impl6351);
            rule__FromTable__JoinExprAssignment_1_3();

            state._fsp--;


            }

             after(grammarAccess.getFromTableAccess().getJoinExprAssignment_1_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FromTable__Group_1__3__Impl"


    // $ANTLR start "rule__TableOrAlias__Group__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3083:1: rule__TableOrAlias__Group__0 : rule__TableOrAlias__Group__0__Impl rule__TableOrAlias__Group__1 ;
    public final void rule__TableOrAlias__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3087:1: ( rule__TableOrAlias__Group__0__Impl rule__TableOrAlias__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3088:2: rule__TableOrAlias__Group__0__Impl rule__TableOrAlias__Group__1
            {
            pushFollow(FOLLOW_rule__TableOrAlias__Group__0__Impl_in_rule__TableOrAlias__Group__06389);
            rule__TableOrAlias__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__TableOrAlias__Group__1_in_rule__TableOrAlias__Group__06392);
            rule__TableOrAlias__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TableOrAlias__Group__0"


    // $ANTLR start "rule__TableOrAlias__Group__0__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3095:1: rule__TableOrAlias__Group__0__Impl : ( ( rule__TableOrAlias__TfullAssignment_0 ) ) ;
    public final void rule__TableOrAlias__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3099:1: ( ( ( rule__TableOrAlias__TfullAssignment_0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3100:1: ( ( rule__TableOrAlias__TfullAssignment_0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3100:1: ( ( rule__TableOrAlias__TfullAssignment_0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3101:1: ( rule__TableOrAlias__TfullAssignment_0 )
            {
             before(grammarAccess.getTableOrAliasAccess().getTfullAssignment_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3102:1: ( rule__TableOrAlias__TfullAssignment_0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3102:2: rule__TableOrAlias__TfullAssignment_0
            {
            pushFollow(FOLLOW_rule__TableOrAlias__TfullAssignment_0_in_rule__TableOrAlias__Group__0__Impl6419);
            rule__TableOrAlias__TfullAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getTableOrAliasAccess().getTfullAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TableOrAlias__Group__0__Impl"


    // $ANTLR start "rule__TableOrAlias__Group__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3112:1: rule__TableOrAlias__Group__1 : rule__TableOrAlias__Group__1__Impl rule__TableOrAlias__Group__2 ;
    public final void rule__TableOrAlias__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3116:1: ( rule__TableOrAlias__Group__1__Impl rule__TableOrAlias__Group__2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3117:2: rule__TableOrAlias__Group__1__Impl rule__TableOrAlias__Group__2
            {
            pushFollow(FOLLOW_rule__TableOrAlias__Group__1__Impl_in_rule__TableOrAlias__Group__16449);
            rule__TableOrAlias__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__TableOrAlias__Group__2_in_rule__TableOrAlias__Group__16452);
            rule__TableOrAlias__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TableOrAlias__Group__1"


    // $ANTLR start "rule__TableOrAlias__Group__1__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3124:1: rule__TableOrAlias__Group__1__Impl : ( ( rule__TableOrAlias__AliasAssignment_1 )? ) ;
    public final void rule__TableOrAlias__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3128:1: ( ( ( rule__TableOrAlias__AliasAssignment_1 )? ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3129:1: ( ( rule__TableOrAlias__AliasAssignment_1 )? )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3129:1: ( ( rule__TableOrAlias__AliasAssignment_1 )? )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3130:1: ( rule__TableOrAlias__AliasAssignment_1 )?
            {
             before(grammarAccess.getTableOrAliasAccess().getAliasAssignment_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3131:1: ( rule__TableOrAlias__AliasAssignment_1 )?
            int alt33=2;
            int LA33_0 = input.LA(1);

            if ( (LA33_0==KEYWORD_15) ) {
                alt33=1;
            }
            switch (alt33) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3131:2: rule__TableOrAlias__AliasAssignment_1
                    {
                    pushFollow(FOLLOW_rule__TableOrAlias__AliasAssignment_1_in_rule__TableOrAlias__Group__1__Impl6479);
                    rule__TableOrAlias__AliasAssignment_1();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getTableOrAliasAccess().getAliasAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TableOrAlias__Group__1__Impl"


    // $ANTLR start "rule__TableOrAlias__Group__2"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3141:1: rule__TableOrAlias__Group__2 : rule__TableOrAlias__Group__2__Impl ;
    public final void rule__TableOrAlias__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3145:1: ( rule__TableOrAlias__Group__2__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3146:2: rule__TableOrAlias__Group__2__Impl
            {
            pushFollow(FOLLOW_rule__TableOrAlias__Group__2__Impl_in_rule__TableOrAlias__Group__26510);
            rule__TableOrAlias__Group__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TableOrAlias__Group__2"


    // $ANTLR start "rule__TableOrAlias__Group__2__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3152:1: rule__TableOrAlias__Group__2__Impl : ( ( rule__TableOrAlias__TblAliasAssignment_2 )? ) ;
    public final void rule__TableOrAlias__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3156:1: ( ( ( rule__TableOrAlias__TblAliasAssignment_2 )? ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3157:1: ( ( rule__TableOrAlias__TblAliasAssignment_2 )? )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3157:1: ( ( rule__TableOrAlias__TblAliasAssignment_2 )? )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3158:1: ( rule__TableOrAlias__TblAliasAssignment_2 )?
            {
             before(grammarAccess.getTableOrAliasAccess().getTblAliasAssignment_2()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3159:1: ( rule__TableOrAlias__TblAliasAssignment_2 )?
            int alt34=2;
            int LA34_0 = input.LA(1);

            if ( (LA34_0==RULE_ID) ) {
                alt34=1;
            }
            switch (alt34) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3159:2: rule__TableOrAlias__TblAliasAssignment_2
                    {
                    pushFollow(FOLLOW_rule__TableOrAlias__TblAliasAssignment_2_in_rule__TableOrAlias__Group__2__Impl6537);
                    rule__TableOrAlias__TblAliasAssignment_2();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getTableOrAliasAccess().getTblAliasAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TableOrAlias__Group__2__Impl"


    // $ANTLR start "rule__TableFull__Group__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3175:1: rule__TableFull__Group__0 : rule__TableFull__Group__0__Impl rule__TableFull__Group__1 ;
    public final void rule__TableFull__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3179:1: ( rule__TableFull__Group__0__Impl rule__TableFull__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3180:2: rule__TableFull__Group__0__Impl rule__TableFull__Group__1
            {
            pushFollow(FOLLOW_rule__TableFull__Group__0__Impl_in_rule__TableFull__Group__06574);
            rule__TableFull__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__TableFull__Group__1_in_rule__TableFull__Group__06577);
            rule__TableFull__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TableFull__Group__0"


    // $ANTLR start "rule__TableFull__Group__0__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3187:1: rule__TableFull__Group__0__Impl : ( ruleDbObjectName ) ;
    public final void rule__TableFull__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3191:1: ( ( ruleDbObjectName ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3192:1: ( ruleDbObjectName )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3192:1: ( ruleDbObjectName )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3193:1: ruleDbObjectName
            {
             before(grammarAccess.getTableFullAccess().getDbObjectNameParserRuleCall_0()); 
            pushFollow(FOLLOW_ruleDbObjectName_in_rule__TableFull__Group__0__Impl6604);
            ruleDbObjectName();

            state._fsp--;

             after(grammarAccess.getTableFullAccess().getDbObjectNameParserRuleCall_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TableFull__Group__0__Impl"


    // $ANTLR start "rule__TableFull__Group__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3204:1: rule__TableFull__Group__1 : rule__TableFull__Group__1__Impl ;
    public final void rule__TableFull__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3208:1: ( rule__TableFull__Group__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3209:2: rule__TableFull__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__TableFull__Group__1__Impl_in_rule__TableFull__Group__16633);
            rule__TableFull__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TableFull__Group__1"


    // $ANTLR start "rule__TableFull__Group__1__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3215:1: rule__TableFull__Group__1__Impl : ( ( rule__TableFull__Group_1__0 )? ) ;
    public final void rule__TableFull__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3219:1: ( ( ( rule__TableFull__Group_1__0 )? ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3220:1: ( ( rule__TableFull__Group_1__0 )? )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3220:1: ( ( rule__TableFull__Group_1__0 )? )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3221:1: ( rule__TableFull__Group_1__0 )?
            {
             before(grammarAccess.getTableFullAccess().getGroup_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3222:1: ( rule__TableFull__Group_1__0 )?
            int alt35=2;
            int LA35_0 = input.LA(1);

            if ( (LA35_0==KEYWORD_6) ) {
                alt35=1;
            }
            switch (alt35) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3222:2: rule__TableFull__Group_1__0
                    {
                    pushFollow(FOLLOW_rule__TableFull__Group_1__0_in_rule__TableFull__Group__1__Impl6660);
                    rule__TableFull__Group_1__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getTableFullAccess().getGroup_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TableFull__Group__1__Impl"


    // $ANTLR start "rule__TableFull__Group_1__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3236:1: rule__TableFull__Group_1__0 : rule__TableFull__Group_1__0__Impl rule__TableFull__Group_1__1 ;
    public final void rule__TableFull__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3240:1: ( rule__TableFull__Group_1__0__Impl rule__TableFull__Group_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3241:2: rule__TableFull__Group_1__0__Impl rule__TableFull__Group_1__1
            {
            pushFollow(FOLLOW_rule__TableFull__Group_1__0__Impl_in_rule__TableFull__Group_1__06695);
            rule__TableFull__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__TableFull__Group_1__1_in_rule__TableFull__Group_1__06698);
            rule__TableFull__Group_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TableFull__Group_1__0"


    // $ANTLR start "rule__TableFull__Group_1__0__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3248:1: rule__TableFull__Group_1__0__Impl : ( () ) ;
    public final void rule__TableFull__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3252:1: ( ( () ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3253:1: ( () )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3253:1: ( () )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3254:1: ()
            {
             before(grammarAccess.getTableFullAccess().getTblsEntriesAction_1_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3255:1: ()
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3257:1: 
            {
            }

             after(grammarAccess.getTableFullAccess().getTblsEntriesAction_1_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TableFull__Group_1__0__Impl"


    // $ANTLR start "rule__TableFull__Group_1__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3267:1: rule__TableFull__Group_1__1 : rule__TableFull__Group_1__1__Impl ;
    public final void rule__TableFull__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3271:1: ( rule__TableFull__Group_1__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3272:2: rule__TableFull__Group_1__1__Impl
            {
            pushFollow(FOLLOW_rule__TableFull__Group_1__1__Impl_in_rule__TableFull__Group_1__16756);
            rule__TableFull__Group_1__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TableFull__Group_1__1"


    // $ANTLR start "rule__TableFull__Group_1__1__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3278:1: rule__TableFull__Group_1__1__Impl : ( ( ( rule__TableFull__Group_1_1__0 ) ) ( ( rule__TableFull__Group_1_1__0 )* ) ) ;
    public final void rule__TableFull__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3282:1: ( ( ( ( rule__TableFull__Group_1_1__0 ) ) ( ( rule__TableFull__Group_1_1__0 )* ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3283:1: ( ( ( rule__TableFull__Group_1_1__0 ) ) ( ( rule__TableFull__Group_1_1__0 )* ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3283:1: ( ( ( rule__TableFull__Group_1_1__0 ) ) ( ( rule__TableFull__Group_1_1__0 )* ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3284:1: ( ( rule__TableFull__Group_1_1__0 ) ) ( ( rule__TableFull__Group_1_1__0 )* )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3284:1: ( ( rule__TableFull__Group_1_1__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3285:1: ( rule__TableFull__Group_1_1__0 )
            {
             before(grammarAccess.getTableFullAccess().getGroup_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3286:1: ( rule__TableFull__Group_1_1__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3286:2: rule__TableFull__Group_1_1__0
            {
            pushFollow(FOLLOW_rule__TableFull__Group_1_1__0_in_rule__TableFull__Group_1__1__Impl6785);
            rule__TableFull__Group_1_1__0();

            state._fsp--;


            }

             after(grammarAccess.getTableFullAccess().getGroup_1_1()); 

            }

            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3289:1: ( ( rule__TableFull__Group_1_1__0 )* )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3290:1: ( rule__TableFull__Group_1_1__0 )*
            {
             before(grammarAccess.getTableFullAccess().getGroup_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3291:1: ( rule__TableFull__Group_1_1__0 )*
            loop36:
            do {
                int alt36=2;
                int LA36_0 = input.LA(1);

                if ( (LA36_0==KEYWORD_6) ) {
                    alt36=1;
                }


                switch (alt36) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3291:2: rule__TableFull__Group_1_1__0
            	    {
            	    pushFollow(FOLLOW_rule__TableFull__Group_1_1__0_in_rule__TableFull__Group_1__1__Impl6797);
            	    rule__TableFull__Group_1_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop36;
                }
            } while (true);

             after(grammarAccess.getTableFullAccess().getGroup_1_1()); 

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
    // $ANTLR end "rule__TableFull__Group_1__1__Impl"


    // $ANTLR start "rule__TableFull__Group_1_1__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3306:1: rule__TableFull__Group_1_1__0 : rule__TableFull__Group_1_1__0__Impl rule__TableFull__Group_1_1__1 ;
    public final void rule__TableFull__Group_1_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3310:1: ( rule__TableFull__Group_1_1__0__Impl rule__TableFull__Group_1_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3311:2: rule__TableFull__Group_1_1__0__Impl rule__TableFull__Group_1_1__1
            {
            pushFollow(FOLLOW_rule__TableFull__Group_1_1__0__Impl_in_rule__TableFull__Group_1_1__06834);
            rule__TableFull__Group_1_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__TableFull__Group_1_1__1_in_rule__TableFull__Group_1_1__06837);
            rule__TableFull__Group_1_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TableFull__Group_1_1__0"


    // $ANTLR start "rule__TableFull__Group_1_1__0__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3318:1: rule__TableFull__Group_1_1__0__Impl : ( KEYWORD_6 ) ;
    public final void rule__TableFull__Group_1_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3322:1: ( ( KEYWORD_6 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3323:1: ( KEYWORD_6 )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3323:1: ( KEYWORD_6 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3324:1: KEYWORD_6
            {
             before(grammarAccess.getTableFullAccess().getFullStopKeyword_1_1_0()); 
            match(input,KEYWORD_6,FOLLOW_KEYWORD_6_in_rule__TableFull__Group_1_1__0__Impl6865); 
             after(grammarAccess.getTableFullAccess().getFullStopKeyword_1_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TableFull__Group_1_1__0__Impl"


    // $ANTLR start "rule__TableFull__Group_1_1__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3337:1: rule__TableFull__Group_1_1__1 : rule__TableFull__Group_1_1__1__Impl ;
    public final void rule__TableFull__Group_1_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3341:1: ( rule__TableFull__Group_1_1__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3342:2: rule__TableFull__Group_1_1__1__Impl
            {
            pushFollow(FOLLOW_rule__TableFull__Group_1_1__1__Impl_in_rule__TableFull__Group_1_1__16896);
            rule__TableFull__Group_1_1__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TableFull__Group_1_1__1"


    // $ANTLR start "rule__TableFull__Group_1_1__1__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3348:1: rule__TableFull__Group_1_1__1__Impl : ( ( rule__TableFull__EntriesAssignment_1_1_1 ) ) ;
    public final void rule__TableFull__Group_1_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3352:1: ( ( ( rule__TableFull__EntriesAssignment_1_1_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3353:1: ( ( rule__TableFull__EntriesAssignment_1_1_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3353:1: ( ( rule__TableFull__EntriesAssignment_1_1_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3354:1: ( rule__TableFull__EntriesAssignment_1_1_1 )
            {
             before(grammarAccess.getTableFullAccess().getEntriesAssignment_1_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3355:1: ( rule__TableFull__EntriesAssignment_1_1_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3355:2: rule__TableFull__EntriesAssignment_1_1_1
            {
            pushFollow(FOLLOW_rule__TableFull__EntriesAssignment_1_1_1_in_rule__TableFull__Group_1_1__1__Impl6923);
            rule__TableFull__EntriesAssignment_1_1_1();

            state._fsp--;


            }

             after(grammarAccess.getTableFullAccess().getEntriesAssignment_1_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TableFull__Group_1_1__1__Impl"


    // $ANTLR start "rule__OrderByColumns__Group__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3369:1: rule__OrderByColumns__Group__0 : rule__OrderByColumns__Group__0__Impl rule__OrderByColumns__Group__1 ;
    public final void rule__OrderByColumns__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3373:1: ( rule__OrderByColumns__Group__0__Impl rule__OrderByColumns__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3374:2: rule__OrderByColumns__Group__0__Impl rule__OrderByColumns__Group__1
            {
            pushFollow(FOLLOW_rule__OrderByColumns__Group__0__Impl_in_rule__OrderByColumns__Group__06957);
            rule__OrderByColumns__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__OrderByColumns__Group__1_in_rule__OrderByColumns__Group__06960);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3381:1: rule__OrderByColumns__Group__0__Impl : ( ruleOrderByColumnFull ) ;
    public final void rule__OrderByColumns__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3385:1: ( ( ruleOrderByColumnFull ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3386:1: ( ruleOrderByColumnFull )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3386:1: ( ruleOrderByColumnFull )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3387:1: ruleOrderByColumnFull
            {
             before(grammarAccess.getOrderByColumnsAccess().getOrderByColumnFullParserRuleCall_0()); 
            pushFollow(FOLLOW_ruleOrderByColumnFull_in_rule__OrderByColumns__Group__0__Impl6987);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3398:1: rule__OrderByColumns__Group__1 : rule__OrderByColumns__Group__1__Impl ;
    public final void rule__OrderByColumns__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3402:1: ( rule__OrderByColumns__Group__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3403:2: rule__OrderByColumns__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__OrderByColumns__Group__1__Impl_in_rule__OrderByColumns__Group__17016);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3409:1: rule__OrderByColumns__Group__1__Impl : ( ( rule__OrderByColumns__Group_1__0 )? ) ;
    public final void rule__OrderByColumns__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3413:1: ( ( ( rule__OrderByColumns__Group_1__0 )? ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3414:1: ( ( rule__OrderByColumns__Group_1__0 )? )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3414:1: ( ( rule__OrderByColumns__Group_1__0 )? )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3415:1: ( rule__OrderByColumns__Group_1__0 )?
            {
             before(grammarAccess.getOrderByColumnsAccess().getGroup_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3416:1: ( rule__OrderByColumns__Group_1__0 )?
            int alt37=2;
            int LA37_0 = input.LA(1);

            if ( (LA37_0==KEYWORD_4) ) {
                alt37=1;
            }
            switch (alt37) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3416:2: rule__OrderByColumns__Group_1__0
                    {
                    pushFollow(FOLLOW_rule__OrderByColumns__Group_1__0_in_rule__OrderByColumns__Group__1__Impl7043);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3430:1: rule__OrderByColumns__Group_1__0 : rule__OrderByColumns__Group_1__0__Impl rule__OrderByColumns__Group_1__1 ;
    public final void rule__OrderByColumns__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3434:1: ( rule__OrderByColumns__Group_1__0__Impl rule__OrderByColumns__Group_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3435:2: rule__OrderByColumns__Group_1__0__Impl rule__OrderByColumns__Group_1__1
            {
            pushFollow(FOLLOW_rule__OrderByColumns__Group_1__0__Impl_in_rule__OrderByColumns__Group_1__07078);
            rule__OrderByColumns__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__OrderByColumns__Group_1__1_in_rule__OrderByColumns__Group_1__07081);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3442:1: rule__OrderByColumns__Group_1__0__Impl : ( () ) ;
    public final void rule__OrderByColumns__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3446:1: ( ( () ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3447:1: ( () )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3447:1: ( () )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3448:1: ()
            {
             before(grammarAccess.getOrderByColumnsAccess().getOrOrderByColumnEntriesAction_1_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3449:1: ()
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3451:1: 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3461:1: rule__OrderByColumns__Group_1__1 : rule__OrderByColumns__Group_1__1__Impl ;
    public final void rule__OrderByColumns__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3465:1: ( rule__OrderByColumns__Group_1__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3466:2: rule__OrderByColumns__Group_1__1__Impl
            {
            pushFollow(FOLLOW_rule__OrderByColumns__Group_1__1__Impl_in_rule__OrderByColumns__Group_1__17139);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3472:1: rule__OrderByColumns__Group_1__1__Impl : ( ( ( rule__OrderByColumns__Group_1_1__0 ) ) ( ( rule__OrderByColumns__Group_1_1__0 )* ) ) ;
    public final void rule__OrderByColumns__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3476:1: ( ( ( ( rule__OrderByColumns__Group_1_1__0 ) ) ( ( rule__OrderByColumns__Group_1_1__0 )* ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3477:1: ( ( ( rule__OrderByColumns__Group_1_1__0 ) ) ( ( rule__OrderByColumns__Group_1_1__0 )* ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3477:1: ( ( ( rule__OrderByColumns__Group_1_1__0 ) ) ( ( rule__OrderByColumns__Group_1_1__0 )* ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3478:1: ( ( rule__OrderByColumns__Group_1_1__0 ) ) ( ( rule__OrderByColumns__Group_1_1__0 )* )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3478:1: ( ( rule__OrderByColumns__Group_1_1__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3479:1: ( rule__OrderByColumns__Group_1_1__0 )
            {
             before(grammarAccess.getOrderByColumnsAccess().getGroup_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3480:1: ( rule__OrderByColumns__Group_1_1__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3480:2: rule__OrderByColumns__Group_1_1__0
            {
            pushFollow(FOLLOW_rule__OrderByColumns__Group_1_1__0_in_rule__OrderByColumns__Group_1__1__Impl7168);
            rule__OrderByColumns__Group_1_1__0();

            state._fsp--;


            }

             after(grammarAccess.getOrderByColumnsAccess().getGroup_1_1()); 

            }

            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3483:1: ( ( rule__OrderByColumns__Group_1_1__0 )* )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3484:1: ( rule__OrderByColumns__Group_1_1__0 )*
            {
             before(grammarAccess.getOrderByColumnsAccess().getGroup_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3485:1: ( rule__OrderByColumns__Group_1_1__0 )*
            loop38:
            do {
                int alt38=2;
                int LA38_0 = input.LA(1);

                if ( (LA38_0==KEYWORD_4) ) {
                    alt38=1;
                }


                switch (alt38) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3485:2: rule__OrderByColumns__Group_1_1__0
            	    {
            	    pushFollow(FOLLOW_rule__OrderByColumns__Group_1_1__0_in_rule__OrderByColumns__Group_1__1__Impl7180);
            	    rule__OrderByColumns__Group_1_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop38;
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3500:1: rule__OrderByColumns__Group_1_1__0 : rule__OrderByColumns__Group_1_1__0__Impl rule__OrderByColumns__Group_1_1__1 ;
    public final void rule__OrderByColumns__Group_1_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3504:1: ( rule__OrderByColumns__Group_1_1__0__Impl rule__OrderByColumns__Group_1_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3505:2: rule__OrderByColumns__Group_1_1__0__Impl rule__OrderByColumns__Group_1_1__1
            {
            pushFollow(FOLLOW_rule__OrderByColumns__Group_1_1__0__Impl_in_rule__OrderByColumns__Group_1_1__07217);
            rule__OrderByColumns__Group_1_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__OrderByColumns__Group_1_1__1_in_rule__OrderByColumns__Group_1_1__07220);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3512:1: rule__OrderByColumns__Group_1_1__0__Impl : ( KEYWORD_4 ) ;
    public final void rule__OrderByColumns__Group_1_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3516:1: ( ( KEYWORD_4 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3517:1: ( KEYWORD_4 )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3517:1: ( KEYWORD_4 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3518:1: KEYWORD_4
            {
             before(grammarAccess.getOrderByColumnsAccess().getCommaKeyword_1_1_0()); 
            match(input,KEYWORD_4,FOLLOW_KEYWORD_4_in_rule__OrderByColumns__Group_1_1__0__Impl7248); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3531:1: rule__OrderByColumns__Group_1_1__1 : rule__OrderByColumns__Group_1_1__1__Impl ;
    public final void rule__OrderByColumns__Group_1_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3535:1: ( rule__OrderByColumns__Group_1_1__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3536:2: rule__OrderByColumns__Group_1_1__1__Impl
            {
            pushFollow(FOLLOW_rule__OrderByColumns__Group_1_1__1__Impl_in_rule__OrderByColumns__Group_1_1__17279);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3542:1: rule__OrderByColumns__Group_1_1__1__Impl : ( ( rule__OrderByColumns__EntriesAssignment_1_1_1 ) ) ;
    public final void rule__OrderByColumns__Group_1_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3546:1: ( ( ( rule__OrderByColumns__EntriesAssignment_1_1_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3547:1: ( ( rule__OrderByColumns__EntriesAssignment_1_1_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3547:1: ( ( rule__OrderByColumns__EntriesAssignment_1_1_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3548:1: ( rule__OrderByColumns__EntriesAssignment_1_1_1 )
            {
             before(grammarAccess.getOrderByColumnsAccess().getEntriesAssignment_1_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3549:1: ( rule__OrderByColumns__EntriesAssignment_1_1_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3549:2: rule__OrderByColumns__EntriesAssignment_1_1_1
            {
            pushFollow(FOLLOW_rule__OrderByColumns__EntriesAssignment_1_1_1_in_rule__OrderByColumns__Group_1_1__1__Impl7306);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3563:1: rule__OrderByColumnFull__Group__0 : rule__OrderByColumnFull__Group__0__Impl rule__OrderByColumnFull__Group__1 ;
    public final void rule__OrderByColumnFull__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3567:1: ( rule__OrderByColumnFull__Group__0__Impl rule__OrderByColumnFull__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3568:2: rule__OrderByColumnFull__Group__0__Impl rule__OrderByColumnFull__Group__1
            {
            pushFollow(FOLLOW_rule__OrderByColumnFull__Group__0__Impl_in_rule__OrderByColumnFull__Group__07340);
            rule__OrderByColumnFull__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__OrderByColumnFull__Group__1_in_rule__OrderByColumnFull__Group__07343);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3575:1: rule__OrderByColumnFull__Group__0__Impl : ( ( rule__OrderByColumnFull__ColOrderAssignment_0 ) ) ;
    public final void rule__OrderByColumnFull__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3579:1: ( ( ( rule__OrderByColumnFull__ColOrderAssignment_0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3580:1: ( ( rule__OrderByColumnFull__ColOrderAssignment_0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3580:1: ( ( rule__OrderByColumnFull__ColOrderAssignment_0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3581:1: ( rule__OrderByColumnFull__ColOrderAssignment_0 )
            {
             before(grammarAccess.getOrderByColumnFullAccess().getColOrderAssignment_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3582:1: ( rule__OrderByColumnFull__ColOrderAssignment_0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3582:2: rule__OrderByColumnFull__ColOrderAssignment_0
            {
            pushFollow(FOLLOW_rule__OrderByColumnFull__ColOrderAssignment_0_in_rule__OrderByColumnFull__Group__0__Impl7370);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3592:1: rule__OrderByColumnFull__Group__1 : rule__OrderByColumnFull__Group__1__Impl ;
    public final void rule__OrderByColumnFull__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3596:1: ( rule__OrderByColumnFull__Group__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3597:2: rule__OrderByColumnFull__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__OrderByColumnFull__Group__1__Impl_in_rule__OrderByColumnFull__Group__17400);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3603:1: rule__OrderByColumnFull__Group__1__Impl : ( ( rule__OrderByColumnFull__Alternatives_1 )? ) ;
    public final void rule__OrderByColumnFull__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3607:1: ( ( ( rule__OrderByColumnFull__Alternatives_1 )? ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3608:1: ( ( rule__OrderByColumnFull__Alternatives_1 )? )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3608:1: ( ( rule__OrderByColumnFull__Alternatives_1 )? )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3609:1: ( rule__OrderByColumnFull__Alternatives_1 )?
            {
             before(grammarAccess.getOrderByColumnFullAccess().getAlternatives_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3610:1: ( rule__OrderByColumnFull__Alternatives_1 )?
            int alt39=2;
            int LA39_0 = input.LA(1);

            if ( (LA39_0==KEYWORD_24||LA39_0==KEYWORD_22) ) {
                alt39=1;
            }
            switch (alt39) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3610:2: rule__OrderByColumnFull__Alternatives_1
                    {
                    pushFollow(FOLLOW_rule__OrderByColumnFull__Alternatives_1_in_rule__OrderByColumnFull__Group__1__Impl7427);
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


    // $ANTLR start "rule__GroupByColumns__Group__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3624:1: rule__GroupByColumns__Group__0 : rule__GroupByColumns__Group__0__Impl rule__GroupByColumns__Group__1 ;
    public final void rule__GroupByColumns__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3628:1: ( rule__GroupByColumns__Group__0__Impl rule__GroupByColumns__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3629:2: rule__GroupByColumns__Group__0__Impl rule__GroupByColumns__Group__1
            {
            pushFollow(FOLLOW_rule__GroupByColumns__Group__0__Impl_in_rule__GroupByColumns__Group__07462);
            rule__GroupByColumns__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__GroupByColumns__Group__1_in_rule__GroupByColumns__Group__07465);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3636:1: rule__GroupByColumns__Group__0__Impl : ( ruleColumnFull ) ;
    public final void rule__GroupByColumns__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3640:1: ( ( ruleColumnFull ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3641:1: ( ruleColumnFull )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3641:1: ( ruleColumnFull )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3642:1: ruleColumnFull
            {
             before(grammarAccess.getGroupByColumnsAccess().getColumnFullParserRuleCall_0()); 
            pushFollow(FOLLOW_ruleColumnFull_in_rule__GroupByColumns__Group__0__Impl7492);
            ruleColumnFull();

            state._fsp--;

             after(grammarAccess.getGroupByColumnsAccess().getColumnFullParserRuleCall_0()); 

            }


            }

        }
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3653:1: rule__GroupByColumns__Group__1 : rule__GroupByColumns__Group__1__Impl ;
    public final void rule__GroupByColumns__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3657:1: ( rule__GroupByColumns__Group__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3658:2: rule__GroupByColumns__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__GroupByColumns__Group__1__Impl_in_rule__GroupByColumns__Group__17521);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3664:1: rule__GroupByColumns__Group__1__Impl : ( ( rule__GroupByColumns__Group_1__0 )? ) ;
    public final void rule__GroupByColumns__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3668:1: ( ( ( rule__GroupByColumns__Group_1__0 )? ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3669:1: ( ( rule__GroupByColumns__Group_1__0 )? )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3669:1: ( ( rule__GroupByColumns__Group_1__0 )? )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3670:1: ( rule__GroupByColumns__Group_1__0 )?
            {
             before(grammarAccess.getGroupByColumnsAccess().getGroup_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3671:1: ( rule__GroupByColumns__Group_1__0 )?
            int alt40=2;
            int LA40_0 = input.LA(1);

            if ( (LA40_0==KEYWORD_4) ) {
                alt40=1;
            }
            switch (alt40) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3671:2: rule__GroupByColumns__Group_1__0
                    {
                    pushFollow(FOLLOW_rule__GroupByColumns__Group_1__0_in_rule__GroupByColumns__Group__1__Impl7548);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3685:1: rule__GroupByColumns__Group_1__0 : rule__GroupByColumns__Group_1__0__Impl rule__GroupByColumns__Group_1__1 ;
    public final void rule__GroupByColumns__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3689:1: ( rule__GroupByColumns__Group_1__0__Impl rule__GroupByColumns__Group_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3690:2: rule__GroupByColumns__Group_1__0__Impl rule__GroupByColumns__Group_1__1
            {
            pushFollow(FOLLOW_rule__GroupByColumns__Group_1__0__Impl_in_rule__GroupByColumns__Group_1__07583);
            rule__GroupByColumns__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__GroupByColumns__Group_1__1_in_rule__GroupByColumns__Group_1__07586);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3697:1: rule__GroupByColumns__Group_1__0__Impl : ( () ) ;
    public final void rule__GroupByColumns__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3701:1: ( ( () ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3702:1: ( () )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3702:1: ( () )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3703:1: ()
            {
             before(grammarAccess.getGroupByColumnsAccess().getOrGroupByColumnEntriesAction_1_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3704:1: ()
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3706:1: 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3716:1: rule__GroupByColumns__Group_1__1 : rule__GroupByColumns__Group_1__1__Impl ;
    public final void rule__GroupByColumns__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3720:1: ( rule__GroupByColumns__Group_1__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3721:2: rule__GroupByColumns__Group_1__1__Impl
            {
            pushFollow(FOLLOW_rule__GroupByColumns__Group_1__1__Impl_in_rule__GroupByColumns__Group_1__17644);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3727:1: rule__GroupByColumns__Group_1__1__Impl : ( ( ( rule__GroupByColumns__Group_1_1__0 ) ) ( ( rule__GroupByColumns__Group_1_1__0 )* ) ) ;
    public final void rule__GroupByColumns__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3731:1: ( ( ( ( rule__GroupByColumns__Group_1_1__0 ) ) ( ( rule__GroupByColumns__Group_1_1__0 )* ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3732:1: ( ( ( rule__GroupByColumns__Group_1_1__0 ) ) ( ( rule__GroupByColumns__Group_1_1__0 )* ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3732:1: ( ( ( rule__GroupByColumns__Group_1_1__0 ) ) ( ( rule__GroupByColumns__Group_1_1__0 )* ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3733:1: ( ( rule__GroupByColumns__Group_1_1__0 ) ) ( ( rule__GroupByColumns__Group_1_1__0 )* )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3733:1: ( ( rule__GroupByColumns__Group_1_1__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3734:1: ( rule__GroupByColumns__Group_1_1__0 )
            {
             before(grammarAccess.getGroupByColumnsAccess().getGroup_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3735:1: ( rule__GroupByColumns__Group_1_1__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3735:2: rule__GroupByColumns__Group_1_1__0
            {
            pushFollow(FOLLOW_rule__GroupByColumns__Group_1_1__0_in_rule__GroupByColumns__Group_1__1__Impl7673);
            rule__GroupByColumns__Group_1_1__0();

            state._fsp--;


            }

             after(grammarAccess.getGroupByColumnsAccess().getGroup_1_1()); 

            }

            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3738:1: ( ( rule__GroupByColumns__Group_1_1__0 )* )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3739:1: ( rule__GroupByColumns__Group_1_1__0 )*
            {
             before(grammarAccess.getGroupByColumnsAccess().getGroup_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3740:1: ( rule__GroupByColumns__Group_1_1__0 )*
            loop41:
            do {
                int alt41=2;
                int LA41_0 = input.LA(1);

                if ( (LA41_0==KEYWORD_4) ) {
                    alt41=1;
                }


                switch (alt41) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3740:2: rule__GroupByColumns__Group_1_1__0
            	    {
            	    pushFollow(FOLLOW_rule__GroupByColumns__Group_1_1__0_in_rule__GroupByColumns__Group_1__1__Impl7685);
            	    rule__GroupByColumns__Group_1_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop41;
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3755:1: rule__GroupByColumns__Group_1_1__0 : rule__GroupByColumns__Group_1_1__0__Impl rule__GroupByColumns__Group_1_1__1 ;
    public final void rule__GroupByColumns__Group_1_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3759:1: ( rule__GroupByColumns__Group_1_1__0__Impl rule__GroupByColumns__Group_1_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3760:2: rule__GroupByColumns__Group_1_1__0__Impl rule__GroupByColumns__Group_1_1__1
            {
            pushFollow(FOLLOW_rule__GroupByColumns__Group_1_1__0__Impl_in_rule__GroupByColumns__Group_1_1__07722);
            rule__GroupByColumns__Group_1_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__GroupByColumns__Group_1_1__1_in_rule__GroupByColumns__Group_1_1__07725);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3767:1: rule__GroupByColumns__Group_1_1__0__Impl : ( KEYWORD_4 ) ;
    public final void rule__GroupByColumns__Group_1_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3771:1: ( ( KEYWORD_4 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3772:1: ( KEYWORD_4 )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3772:1: ( KEYWORD_4 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3773:1: KEYWORD_4
            {
             before(grammarAccess.getGroupByColumnsAccess().getCommaKeyword_1_1_0()); 
            match(input,KEYWORD_4,FOLLOW_KEYWORD_4_in_rule__GroupByColumns__Group_1_1__0__Impl7753); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3786:1: rule__GroupByColumns__Group_1_1__1 : rule__GroupByColumns__Group_1_1__1__Impl ;
    public final void rule__GroupByColumns__Group_1_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3790:1: ( rule__GroupByColumns__Group_1_1__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3791:2: rule__GroupByColumns__Group_1_1__1__Impl
            {
            pushFollow(FOLLOW_rule__GroupByColumns__Group_1_1__1__Impl_in_rule__GroupByColumns__Group_1_1__17784);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3797:1: rule__GroupByColumns__Group_1_1__1__Impl : ( ( rule__GroupByColumns__EntriesAssignment_1_1_1 ) ) ;
    public final void rule__GroupByColumns__Group_1_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3801:1: ( ( ( rule__GroupByColumns__EntriesAssignment_1_1_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3802:1: ( ( rule__GroupByColumns__EntriesAssignment_1_1_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3802:1: ( ( rule__GroupByColumns__EntriesAssignment_1_1_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3803:1: ( rule__GroupByColumns__EntriesAssignment_1_1_1 )
            {
             before(grammarAccess.getGroupByColumnsAccess().getEntriesAssignment_1_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3804:1: ( rule__GroupByColumns__EntriesAssignment_1_1_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3804:2: rule__GroupByColumns__EntriesAssignment_1_1_1
            {
            pushFollow(FOLLOW_rule__GroupByColumns__EntriesAssignment_1_1_1_in_rule__GroupByColumns__Group_1_1__1__Impl7811);
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


    // $ANTLR start "rule__FullExpression__Group__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3818:1: rule__FullExpression__Group__0 : rule__FullExpression__Group__0__Impl rule__FullExpression__Group__1 ;
    public final void rule__FullExpression__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3822:1: ( rule__FullExpression__Group__0__Impl rule__FullExpression__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3823:2: rule__FullExpression__Group__0__Impl rule__FullExpression__Group__1
            {
            pushFollow(FOLLOW_rule__FullExpression__Group__0__Impl_in_rule__FullExpression__Group__07845);
            rule__FullExpression__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__FullExpression__Group__1_in_rule__FullExpression__Group__07848);
            rule__FullExpression__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FullExpression__Group__0"


    // $ANTLR start "rule__FullExpression__Group__0__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3830:1: rule__FullExpression__Group__0__Impl : ( ruleExpressionFragment ) ;
    public final void rule__FullExpression__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3834:1: ( ( ruleExpressionFragment ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3835:1: ( ruleExpressionFragment )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3835:1: ( ruleExpressionFragment )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3836:1: ruleExpressionFragment
            {
             before(grammarAccess.getFullExpressionAccess().getExpressionFragmentParserRuleCall_0()); 
            pushFollow(FOLLOW_ruleExpressionFragment_in_rule__FullExpression__Group__0__Impl7875);
            ruleExpressionFragment();

            state._fsp--;

             after(grammarAccess.getFullExpressionAccess().getExpressionFragmentParserRuleCall_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FullExpression__Group__0__Impl"


    // $ANTLR start "rule__FullExpression__Group__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3847:1: rule__FullExpression__Group__1 : rule__FullExpression__Group__1__Impl ;
    public final void rule__FullExpression__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3851:1: ( rule__FullExpression__Group__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3852:2: rule__FullExpression__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__FullExpression__Group__1__Impl_in_rule__FullExpression__Group__17904);
            rule__FullExpression__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FullExpression__Group__1"


    // $ANTLR start "rule__FullExpression__Group__1__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3858:1: rule__FullExpression__Group__1__Impl : ( ( rule__FullExpression__Group_1__0 )? ) ;
    public final void rule__FullExpression__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3862:1: ( ( ( rule__FullExpression__Group_1__0 )? ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3863:1: ( ( rule__FullExpression__Group_1__0 )? )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3863:1: ( ( rule__FullExpression__Group_1__0 )? )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3864:1: ( rule__FullExpression__Group_1__0 )?
            {
             before(grammarAccess.getFullExpressionAccess().getGroup_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3865:1: ( rule__FullExpression__Group_1__0 )?
            int alt42=2;
            int LA42_0 = input.LA(1);

            if ( (LA42_0==KEYWORD_21||LA42_0==KEYWORD_17) ) {
                alt42=1;
            }
            switch (alt42) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3865:2: rule__FullExpression__Group_1__0
                    {
                    pushFollow(FOLLOW_rule__FullExpression__Group_1__0_in_rule__FullExpression__Group__1__Impl7931);
                    rule__FullExpression__Group_1__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getFullExpressionAccess().getGroup_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FullExpression__Group__1__Impl"


    // $ANTLR start "rule__FullExpression__Group_1__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3879:1: rule__FullExpression__Group_1__0 : rule__FullExpression__Group_1__0__Impl rule__FullExpression__Group_1__1 ;
    public final void rule__FullExpression__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3883:1: ( rule__FullExpression__Group_1__0__Impl rule__FullExpression__Group_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3884:2: rule__FullExpression__Group_1__0__Impl rule__FullExpression__Group_1__1
            {
            pushFollow(FOLLOW_rule__FullExpression__Group_1__0__Impl_in_rule__FullExpression__Group_1__07966);
            rule__FullExpression__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__FullExpression__Group_1__1_in_rule__FullExpression__Group_1__07969);
            rule__FullExpression__Group_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FullExpression__Group_1__0"


    // $ANTLR start "rule__FullExpression__Group_1__0__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3891:1: rule__FullExpression__Group_1__0__Impl : ( () ) ;
    public final void rule__FullExpression__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3895:1: ( ( () ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3896:1: ( () )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3896:1: ( () )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3897:1: ()
            {
             before(grammarAccess.getFullExpressionAccess().getFexprEntriesAction_1_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3898:1: ()
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3900:1: 
            {
            }

             after(grammarAccess.getFullExpressionAccess().getFexprEntriesAction_1_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FullExpression__Group_1__0__Impl"


    // $ANTLR start "rule__FullExpression__Group_1__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3910:1: rule__FullExpression__Group_1__1 : rule__FullExpression__Group_1__1__Impl ;
    public final void rule__FullExpression__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3914:1: ( rule__FullExpression__Group_1__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3915:2: rule__FullExpression__Group_1__1__Impl
            {
            pushFollow(FOLLOW_rule__FullExpression__Group_1__1__Impl_in_rule__FullExpression__Group_1__18027);
            rule__FullExpression__Group_1__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FullExpression__Group_1__1"


    // $ANTLR start "rule__FullExpression__Group_1__1__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3921:1: rule__FullExpression__Group_1__1__Impl : ( ( ( rule__FullExpression__Group_1_1__0 ) ) ( ( rule__FullExpression__Group_1_1__0 )* ) ) ;
    public final void rule__FullExpression__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3925:1: ( ( ( ( rule__FullExpression__Group_1_1__0 ) ) ( ( rule__FullExpression__Group_1_1__0 )* ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3926:1: ( ( ( rule__FullExpression__Group_1_1__0 ) ) ( ( rule__FullExpression__Group_1_1__0 )* ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3926:1: ( ( ( rule__FullExpression__Group_1_1__0 ) ) ( ( rule__FullExpression__Group_1_1__0 )* ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3927:1: ( ( rule__FullExpression__Group_1_1__0 ) ) ( ( rule__FullExpression__Group_1_1__0 )* )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3927:1: ( ( rule__FullExpression__Group_1_1__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3928:1: ( rule__FullExpression__Group_1_1__0 )
            {
             before(grammarAccess.getFullExpressionAccess().getGroup_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3929:1: ( rule__FullExpression__Group_1_1__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3929:2: rule__FullExpression__Group_1_1__0
            {
            pushFollow(FOLLOW_rule__FullExpression__Group_1_1__0_in_rule__FullExpression__Group_1__1__Impl8056);
            rule__FullExpression__Group_1_1__0();

            state._fsp--;


            }

             after(grammarAccess.getFullExpressionAccess().getGroup_1_1()); 

            }

            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3932:1: ( ( rule__FullExpression__Group_1_1__0 )* )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3933:1: ( rule__FullExpression__Group_1_1__0 )*
            {
             before(grammarAccess.getFullExpressionAccess().getGroup_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3934:1: ( rule__FullExpression__Group_1_1__0 )*
            loop43:
            do {
                int alt43=2;
                int LA43_0 = input.LA(1);

                if ( (LA43_0==KEYWORD_21||LA43_0==KEYWORD_17) ) {
                    alt43=1;
                }


                switch (alt43) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3934:2: rule__FullExpression__Group_1_1__0
            	    {
            	    pushFollow(FOLLOW_rule__FullExpression__Group_1_1__0_in_rule__FullExpression__Group_1__1__Impl8068);
            	    rule__FullExpression__Group_1_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop43;
                }
            } while (true);

             after(grammarAccess.getFullExpressionAccess().getGroup_1_1()); 

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
    // $ANTLR end "rule__FullExpression__Group_1__1__Impl"


    // $ANTLR start "rule__FullExpression__Group_1_1__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3949:1: rule__FullExpression__Group_1_1__0 : rule__FullExpression__Group_1_1__0__Impl rule__FullExpression__Group_1_1__1 ;
    public final void rule__FullExpression__Group_1_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3953:1: ( rule__FullExpression__Group_1_1__0__Impl rule__FullExpression__Group_1_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3954:2: rule__FullExpression__Group_1_1__0__Impl rule__FullExpression__Group_1_1__1
            {
            pushFollow(FOLLOW_rule__FullExpression__Group_1_1__0__Impl_in_rule__FullExpression__Group_1_1__08105);
            rule__FullExpression__Group_1_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__FullExpression__Group_1_1__1_in_rule__FullExpression__Group_1_1__08108);
            rule__FullExpression__Group_1_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FullExpression__Group_1_1__0"


    // $ANTLR start "rule__FullExpression__Group_1_1__0__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3961:1: rule__FullExpression__Group_1_1__0__Impl : ( ( rule__FullExpression__Alternatives_1_1_0 ) ) ;
    public final void rule__FullExpression__Group_1_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3965:1: ( ( ( rule__FullExpression__Alternatives_1_1_0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3966:1: ( ( rule__FullExpression__Alternatives_1_1_0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3966:1: ( ( rule__FullExpression__Alternatives_1_1_0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3967:1: ( rule__FullExpression__Alternatives_1_1_0 )
            {
             before(grammarAccess.getFullExpressionAccess().getAlternatives_1_1_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3968:1: ( rule__FullExpression__Alternatives_1_1_0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3968:2: rule__FullExpression__Alternatives_1_1_0
            {
            pushFollow(FOLLOW_rule__FullExpression__Alternatives_1_1_0_in_rule__FullExpression__Group_1_1__0__Impl8135);
            rule__FullExpression__Alternatives_1_1_0();

            state._fsp--;


            }

             after(grammarAccess.getFullExpressionAccess().getAlternatives_1_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FullExpression__Group_1_1__0__Impl"


    // $ANTLR start "rule__FullExpression__Group_1_1__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3978:1: rule__FullExpression__Group_1_1__1 : rule__FullExpression__Group_1_1__1__Impl ;
    public final void rule__FullExpression__Group_1_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3982:1: ( rule__FullExpression__Group_1_1__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3983:2: rule__FullExpression__Group_1_1__1__Impl
            {
            pushFollow(FOLLOW_rule__FullExpression__Group_1_1__1__Impl_in_rule__FullExpression__Group_1_1__18165);
            rule__FullExpression__Group_1_1__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FullExpression__Group_1_1__1"


    // $ANTLR start "rule__FullExpression__Group_1_1__1__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3989:1: rule__FullExpression__Group_1_1__1__Impl : ( ( rule__FullExpression__EntriesAssignment_1_1_1 ) ) ;
    public final void rule__FullExpression__Group_1_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3993:1: ( ( ( rule__FullExpression__EntriesAssignment_1_1_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3994:1: ( ( rule__FullExpression__EntriesAssignment_1_1_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3994:1: ( ( rule__FullExpression__EntriesAssignment_1_1_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3995:1: ( rule__FullExpression__EntriesAssignment_1_1_1 )
            {
             before(grammarAccess.getFullExpressionAccess().getEntriesAssignment_1_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3996:1: ( rule__FullExpression__EntriesAssignment_1_1_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3996:2: rule__FullExpression__EntriesAssignment_1_1_1
            {
            pushFollow(FOLLOW_rule__FullExpression__EntriesAssignment_1_1_1_in_rule__FullExpression__Group_1_1__1__Impl8192);
            rule__FullExpression__EntriesAssignment_1_1_1();

            state._fsp--;


            }

             after(grammarAccess.getFullExpressionAccess().getEntriesAssignment_1_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FullExpression__Group_1_1__1__Impl"


    // $ANTLR start "rule__ExpressionGroup__Group__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4010:1: rule__ExpressionGroup__Group__0 : rule__ExpressionGroup__Group__0__Impl rule__ExpressionGroup__Group__1 ;
    public final void rule__ExpressionGroup__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4014:1: ( rule__ExpressionGroup__Group__0__Impl rule__ExpressionGroup__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4015:2: rule__ExpressionGroup__Group__0__Impl rule__ExpressionGroup__Group__1
            {
            pushFollow(FOLLOW_rule__ExpressionGroup__Group__0__Impl_in_rule__ExpressionGroup__Group__08226);
            rule__ExpressionGroup__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ExpressionGroup__Group__1_in_rule__ExpressionGroup__Group__08229);
            rule__ExpressionGroup__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ExpressionGroup__Group__0"


    // $ANTLR start "rule__ExpressionGroup__Group__0__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4022:1: rule__ExpressionGroup__Group__0__Impl : ( () ) ;
    public final void rule__ExpressionGroup__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4026:1: ( ( () ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4027:1: ( () )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4027:1: ( () )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4028:1: ()
            {
             before(grammarAccess.getExpressionGroupAccess().getExprGroupAction_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4029:1: ()
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4031:1: 
            {
            }

             after(grammarAccess.getExpressionGroupAccess().getExprGroupAction_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ExpressionGroup__Group__0__Impl"


    // $ANTLR start "rule__ExpressionGroup__Group__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4041:1: rule__ExpressionGroup__Group__1 : rule__ExpressionGroup__Group__1__Impl rule__ExpressionGroup__Group__2 ;
    public final void rule__ExpressionGroup__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4045:1: ( rule__ExpressionGroup__Group__1__Impl rule__ExpressionGroup__Group__2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4046:2: rule__ExpressionGroup__Group__1__Impl rule__ExpressionGroup__Group__2
            {
            pushFollow(FOLLOW_rule__ExpressionGroup__Group__1__Impl_in_rule__ExpressionGroup__Group__18287);
            rule__ExpressionGroup__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ExpressionGroup__Group__2_in_rule__ExpressionGroup__Group__18290);
            rule__ExpressionGroup__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ExpressionGroup__Group__1"


    // $ANTLR start "rule__ExpressionGroup__Group__1__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4053:1: rule__ExpressionGroup__Group__1__Impl : ( KEYWORD_1 ) ;
    public final void rule__ExpressionGroup__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4057:1: ( ( KEYWORD_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4058:1: ( KEYWORD_1 )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4058:1: ( KEYWORD_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4059:1: KEYWORD_1
            {
             before(grammarAccess.getExpressionGroupAccess().getLeftParenthesisKeyword_1()); 
            match(input,KEYWORD_1,FOLLOW_KEYWORD_1_in_rule__ExpressionGroup__Group__1__Impl8318); 
             after(grammarAccess.getExpressionGroupAccess().getLeftParenthesisKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ExpressionGroup__Group__1__Impl"


    // $ANTLR start "rule__ExpressionGroup__Group__2"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4072:1: rule__ExpressionGroup__Group__2 : rule__ExpressionGroup__Group__2__Impl rule__ExpressionGroup__Group__3 ;
    public final void rule__ExpressionGroup__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4076:1: ( rule__ExpressionGroup__Group__2__Impl rule__ExpressionGroup__Group__3 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4077:2: rule__ExpressionGroup__Group__2__Impl rule__ExpressionGroup__Group__3
            {
            pushFollow(FOLLOW_rule__ExpressionGroup__Group__2__Impl_in_rule__ExpressionGroup__Group__28349);
            rule__ExpressionGroup__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ExpressionGroup__Group__3_in_rule__ExpressionGroup__Group__28352);
            rule__ExpressionGroup__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ExpressionGroup__Group__2"


    // $ANTLR start "rule__ExpressionGroup__Group__2__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4084:1: rule__ExpressionGroup__Group__2__Impl : ( ( rule__ExpressionGroup__ExprAssignment_2 ) ) ;
    public final void rule__ExpressionGroup__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4088:1: ( ( ( rule__ExpressionGroup__ExprAssignment_2 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4089:1: ( ( rule__ExpressionGroup__ExprAssignment_2 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4089:1: ( ( rule__ExpressionGroup__ExprAssignment_2 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4090:1: ( rule__ExpressionGroup__ExprAssignment_2 )
            {
             before(grammarAccess.getExpressionGroupAccess().getExprAssignment_2()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4091:1: ( rule__ExpressionGroup__ExprAssignment_2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4091:2: rule__ExpressionGroup__ExprAssignment_2
            {
            pushFollow(FOLLOW_rule__ExpressionGroup__ExprAssignment_2_in_rule__ExpressionGroup__Group__2__Impl8379);
            rule__ExpressionGroup__ExprAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getExpressionGroupAccess().getExprAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ExpressionGroup__Group__2__Impl"


    // $ANTLR start "rule__ExpressionGroup__Group__3"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4101:1: rule__ExpressionGroup__Group__3 : rule__ExpressionGroup__Group__3__Impl ;
    public final void rule__ExpressionGroup__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4105:1: ( rule__ExpressionGroup__Group__3__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4106:2: rule__ExpressionGroup__Group__3__Impl
            {
            pushFollow(FOLLOW_rule__ExpressionGroup__Group__3__Impl_in_rule__ExpressionGroup__Group__38409);
            rule__ExpressionGroup__Group__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ExpressionGroup__Group__3"


    // $ANTLR start "rule__ExpressionGroup__Group__3__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4112:1: rule__ExpressionGroup__Group__3__Impl : ( KEYWORD_2 ) ;
    public final void rule__ExpressionGroup__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4116:1: ( ( KEYWORD_2 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4117:1: ( KEYWORD_2 )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4117:1: ( KEYWORD_2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4118:1: KEYWORD_2
            {
             before(grammarAccess.getExpressionGroupAccess().getRightParenthesisKeyword_3()); 
            match(input,KEYWORD_2,FOLLOW_KEYWORD_2_in_rule__ExpressionGroup__Group__3__Impl8437); 
             after(grammarAccess.getExpressionGroupAccess().getRightParenthesisKeyword_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ExpressionGroup__Group__3__Impl"


    // $ANTLR start "rule__XExpression__Group__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4139:1: rule__XExpression__Group__0 : rule__XExpression__Group__0__Impl rule__XExpression__Group__1 ;
    public final void rule__XExpression__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4143:1: ( rule__XExpression__Group__0__Impl rule__XExpression__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4144:2: rule__XExpression__Group__0__Impl rule__XExpression__Group__1
            {
            pushFollow(FOLLOW_rule__XExpression__Group__0__Impl_in_rule__XExpression__Group__08476);
            rule__XExpression__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__XExpression__Group__1_in_rule__XExpression__Group__08479);
            rule__XExpression__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XExpression__Group__0"


    // $ANTLR start "rule__XExpression__Group__0__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4151:1: rule__XExpression__Group__0__Impl : ( () ) ;
    public final void rule__XExpression__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4155:1: ( ( () ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4156:1: ( () )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4156:1: ( () )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4157:1: ()
            {
             before(grammarAccess.getXExpressionAccess().getXexprAction_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4158:1: ()
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4160:1: 
            {
            }

             after(grammarAccess.getXExpressionAccess().getXexprAction_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XExpression__Group__0__Impl"


    // $ANTLR start "rule__XExpression__Group__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4170:1: rule__XExpression__Group__1 : rule__XExpression__Group__1__Impl rule__XExpression__Group__2 ;
    public final void rule__XExpression__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4174:1: ( rule__XExpression__Group__1__Impl rule__XExpression__Group__2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4175:2: rule__XExpression__Group__1__Impl rule__XExpression__Group__2
            {
            pushFollow(FOLLOW_rule__XExpression__Group__1__Impl_in_rule__XExpression__Group__18537);
            rule__XExpression__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__XExpression__Group__2_in_rule__XExpression__Group__18540);
            rule__XExpression__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XExpression__Group__1"


    // $ANTLR start "rule__XExpression__Group__1__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4182:1: rule__XExpression__Group__1__Impl : ( KEYWORD_20 ) ;
    public final void rule__XExpression__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4186:1: ( ( KEYWORD_20 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4187:1: ( KEYWORD_20 )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4187:1: ( KEYWORD_20 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4188:1: KEYWORD_20
            {
             before(grammarAccess.getXExpressionAccess().getXKeyword_1()); 
            match(input,KEYWORD_20,FOLLOW_KEYWORD_20_in_rule__XExpression__Group__1__Impl8568); 
             after(grammarAccess.getXExpressionAccess().getXKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XExpression__Group__1__Impl"


    // $ANTLR start "rule__XExpression__Group__2"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4201:1: rule__XExpression__Group__2 : rule__XExpression__Group__2__Impl rule__XExpression__Group__3 ;
    public final void rule__XExpression__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4205:1: ( rule__XExpression__Group__2__Impl rule__XExpression__Group__3 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4206:2: rule__XExpression__Group__2__Impl rule__XExpression__Group__3
            {
            pushFollow(FOLLOW_rule__XExpression__Group__2__Impl_in_rule__XExpression__Group__28599);
            rule__XExpression__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__XExpression__Group__3_in_rule__XExpression__Group__28602);
            rule__XExpression__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XExpression__Group__2"


    // $ANTLR start "rule__XExpression__Group__2__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4213:1: rule__XExpression__Group__2__Impl : ( RULE_ID ) ;
    public final void rule__XExpression__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4217:1: ( ( RULE_ID ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4218:1: ( RULE_ID )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4218:1: ( RULE_ID )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4219:1: RULE_ID
            {
             before(grammarAccess.getXExpressionAccess().getIDTerminalRuleCall_2()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__XExpression__Group__2__Impl8629); 
             after(grammarAccess.getXExpressionAccess().getIDTerminalRuleCall_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XExpression__Group__2__Impl"


    // $ANTLR start "rule__XExpression__Group__3"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4230:1: rule__XExpression__Group__3 : rule__XExpression__Group__3__Impl rule__XExpression__Group__4 ;
    public final void rule__XExpression__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4234:1: ( rule__XExpression__Group__3__Impl rule__XExpression__Group__4 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4235:2: rule__XExpression__Group__3__Impl rule__XExpression__Group__4
            {
            pushFollow(FOLLOW_rule__XExpression__Group__3__Impl_in_rule__XExpression__Group__38658);
            rule__XExpression__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__XExpression__Group__4_in_rule__XExpression__Group__38661);
            rule__XExpression__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XExpression__Group__3"


    // $ANTLR start "rule__XExpression__Group__3__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4242:1: rule__XExpression__Group__3__Impl : ( ( rule__XExpression__Group_3__0 )* ) ;
    public final void rule__XExpression__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4246:1: ( ( ( rule__XExpression__Group_3__0 )* ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4247:1: ( ( rule__XExpression__Group_3__0 )* )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4247:1: ( ( rule__XExpression__Group_3__0 )* )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4248:1: ( rule__XExpression__Group_3__0 )*
            {
             before(grammarAccess.getXExpressionAccess().getGroup_3()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4249:1: ( rule__XExpression__Group_3__0 )*
            loop44:
            do {
                int alt44=2;
                int LA44_0 = input.LA(1);

                if ( (LA44_0==KEYWORD_4) ) {
                    alt44=1;
                }


                switch (alt44) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4249:2: rule__XExpression__Group_3__0
            	    {
            	    pushFollow(FOLLOW_rule__XExpression__Group_3__0_in_rule__XExpression__Group__3__Impl8688);
            	    rule__XExpression__Group_3__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop44;
                }
            } while (true);

             after(grammarAccess.getXExpressionAccess().getGroup_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XExpression__Group__3__Impl"


    // $ANTLR start "rule__XExpression__Group__4"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4259:1: rule__XExpression__Group__4 : rule__XExpression__Group__4__Impl ;
    public final void rule__XExpression__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4263:1: ( rule__XExpression__Group__4__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4264:2: rule__XExpression__Group__4__Impl
            {
            pushFollow(FOLLOW_rule__XExpression__Group__4__Impl_in_rule__XExpression__Group__48719);
            rule__XExpression__Group__4__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XExpression__Group__4"


    // $ANTLR start "rule__XExpression__Group__4__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4270:1: rule__XExpression__Group__4__Impl : ( KEYWORD_11 ) ;
    public final void rule__XExpression__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4274:1: ( ( KEYWORD_11 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4275:1: ( KEYWORD_11 )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4275:1: ( KEYWORD_11 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4276:1: KEYWORD_11
            {
             before(grammarAccess.getXExpressionAccess().getRightCurlyBracketKeyword_4()); 
            match(input,KEYWORD_11,FOLLOW_KEYWORD_11_in_rule__XExpression__Group__4__Impl8747); 
             after(grammarAccess.getXExpressionAccess().getRightCurlyBracketKeyword_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XExpression__Group__4__Impl"


    // $ANTLR start "rule__XExpression__Group_3__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4299:1: rule__XExpression__Group_3__0 : rule__XExpression__Group_3__0__Impl rule__XExpression__Group_3__1 ;
    public final void rule__XExpression__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4303:1: ( rule__XExpression__Group_3__0__Impl rule__XExpression__Group_3__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4304:2: rule__XExpression__Group_3__0__Impl rule__XExpression__Group_3__1
            {
            pushFollow(FOLLOW_rule__XExpression__Group_3__0__Impl_in_rule__XExpression__Group_3__08788);
            rule__XExpression__Group_3__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__XExpression__Group_3__1_in_rule__XExpression__Group_3__08791);
            rule__XExpression__Group_3__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XExpression__Group_3__0"


    // $ANTLR start "rule__XExpression__Group_3__0__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4311:1: rule__XExpression__Group_3__0__Impl : ( KEYWORD_4 ) ;
    public final void rule__XExpression__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4315:1: ( ( KEYWORD_4 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4316:1: ( KEYWORD_4 )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4316:1: ( KEYWORD_4 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4317:1: KEYWORD_4
            {
             before(grammarAccess.getXExpressionAccess().getCommaKeyword_3_0()); 
            match(input,KEYWORD_4,FOLLOW_KEYWORD_4_in_rule__XExpression__Group_3__0__Impl8819); 
             after(grammarAccess.getXExpressionAccess().getCommaKeyword_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XExpression__Group_3__0__Impl"


    // $ANTLR start "rule__XExpression__Group_3__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4330:1: rule__XExpression__Group_3__1 : rule__XExpression__Group_3__1__Impl ;
    public final void rule__XExpression__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4334:1: ( rule__XExpression__Group_3__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4335:2: rule__XExpression__Group_3__1__Impl
            {
            pushFollow(FOLLOW_rule__XExpression__Group_3__1__Impl_in_rule__XExpression__Group_3__18850);
            rule__XExpression__Group_3__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XExpression__Group_3__1"


    // $ANTLR start "rule__XExpression__Group_3__1__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4341:1: rule__XExpression__Group_3__1__Impl : ( RULE_ID ) ;
    public final void rule__XExpression__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4345:1: ( ( RULE_ID ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4346:1: ( RULE_ID )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4346:1: ( RULE_ID )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4347:1: RULE_ID
            {
             before(grammarAccess.getXExpressionAccess().getIDTerminalRuleCall_3_1()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__XExpression__Group_3__1__Impl8877); 
             after(grammarAccess.getXExpressionAccess().getIDTerminalRuleCall_3_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XExpression__Group_3__1__Impl"


    // $ANTLR start "rule__Expression__Group__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4362:1: rule__Expression__Group__0 : rule__Expression__Group__0__Impl rule__Expression__Group__1 ;
    public final void rule__Expression__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4366:1: ( rule__Expression__Group__0__Impl rule__Expression__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4367:2: rule__Expression__Group__0__Impl rule__Expression__Group__1
            {
            pushFollow(FOLLOW_rule__Expression__Group__0__Impl_in_rule__Expression__Group__08910);
            rule__Expression__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Expression__Group__1_in_rule__Expression__Group__08913);
            rule__Expression__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Expression__Group__0"


    // $ANTLR start "rule__Expression__Group__0__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4374:1: rule__Expression__Group__0__Impl : ( ( rule__Expression__Op1Assignment_0 ) ) ;
    public final void rule__Expression__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4378:1: ( ( ( rule__Expression__Op1Assignment_0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4379:1: ( ( rule__Expression__Op1Assignment_0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4379:1: ( ( rule__Expression__Op1Assignment_0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4380:1: ( rule__Expression__Op1Assignment_0 )
            {
             before(grammarAccess.getExpressionAccess().getOp1Assignment_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4381:1: ( rule__Expression__Op1Assignment_0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4381:2: rule__Expression__Op1Assignment_0
            {
            pushFollow(FOLLOW_rule__Expression__Op1Assignment_0_in_rule__Expression__Group__0__Impl8940);
            rule__Expression__Op1Assignment_0();

            state._fsp--;


            }

             after(grammarAccess.getExpressionAccess().getOp1Assignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Expression__Group__0__Impl"


    // $ANTLR start "rule__Expression__Group__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4391:1: rule__Expression__Group__1 : rule__Expression__Group__1__Impl ;
    public final void rule__Expression__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4395:1: ( rule__Expression__Group__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4396:2: rule__Expression__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__Expression__Group__1__Impl_in_rule__Expression__Group__18970);
            rule__Expression__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Expression__Group__1"


    // $ANTLR start "rule__Expression__Group__1__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4402:1: rule__Expression__Group__1__Impl : ( ( rule__Expression__Alternatives_1 ) ) ;
    public final void rule__Expression__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4406:1: ( ( ( rule__Expression__Alternatives_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4407:1: ( ( rule__Expression__Alternatives_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4407:1: ( ( rule__Expression__Alternatives_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4408:1: ( rule__Expression__Alternatives_1 )
            {
             before(grammarAccess.getExpressionAccess().getAlternatives_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4409:1: ( rule__Expression__Alternatives_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4409:2: rule__Expression__Alternatives_1
            {
            pushFollow(FOLLOW_rule__Expression__Alternatives_1_in_rule__Expression__Group__1__Impl8997);
            rule__Expression__Alternatives_1();

            state._fsp--;


            }

             after(grammarAccess.getExpressionAccess().getAlternatives_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Expression__Group__1__Impl"


    // $ANTLR start "rule__Comparison__Group__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4423:1: rule__Comparison__Group__0 : rule__Comparison__Group__0__Impl rule__Comparison__Group__1 ;
    public final void rule__Comparison__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4427:1: ( rule__Comparison__Group__0__Impl rule__Comparison__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4428:2: rule__Comparison__Group__0__Impl rule__Comparison__Group__1
            {
            pushFollow(FOLLOW_rule__Comparison__Group__0__Impl_in_rule__Comparison__Group__09031);
            rule__Comparison__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Comparison__Group__1_in_rule__Comparison__Group__09034);
            rule__Comparison__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Comparison__Group__0"


    // $ANTLR start "rule__Comparison__Group__0__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4435:1: rule__Comparison__Group__0__Impl : ( ( rule__Comparison__Alternatives_0 ) ) ;
    public final void rule__Comparison__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4439:1: ( ( ( rule__Comparison__Alternatives_0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4440:1: ( ( rule__Comparison__Alternatives_0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4440:1: ( ( rule__Comparison__Alternatives_0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4441:1: ( rule__Comparison__Alternatives_0 )
            {
             before(grammarAccess.getComparisonAccess().getAlternatives_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4442:1: ( rule__Comparison__Alternatives_0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4442:2: rule__Comparison__Alternatives_0
            {
            pushFollow(FOLLOW_rule__Comparison__Alternatives_0_in_rule__Comparison__Group__0__Impl9061);
            rule__Comparison__Alternatives_0();

            state._fsp--;


            }

             after(grammarAccess.getComparisonAccess().getAlternatives_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Comparison__Group__0__Impl"


    // $ANTLR start "rule__Comparison__Group__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4452:1: rule__Comparison__Group__1 : rule__Comparison__Group__1__Impl ;
    public final void rule__Comparison__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4456:1: ( rule__Comparison__Group__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4457:2: rule__Comparison__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__Comparison__Group__1__Impl_in_rule__Comparison__Group__19091);
            rule__Comparison__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Comparison__Group__1"


    // $ANTLR start "rule__Comparison__Group__1__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4463:1: rule__Comparison__Group__1__Impl : ( ( rule__Comparison__Op2Assignment_1 ) ) ;
    public final void rule__Comparison__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4467:1: ( ( ( rule__Comparison__Op2Assignment_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4468:1: ( ( rule__Comparison__Op2Assignment_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4468:1: ( ( rule__Comparison__Op2Assignment_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4469:1: ( rule__Comparison__Op2Assignment_1 )
            {
             before(grammarAccess.getComparisonAccess().getOp2Assignment_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4470:1: ( rule__Comparison__Op2Assignment_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4470:2: rule__Comparison__Op2Assignment_1
            {
            pushFollow(FOLLOW_rule__Comparison__Op2Assignment_1_in_rule__Comparison__Group__1__Impl9118);
            rule__Comparison__Op2Assignment_1();

            state._fsp--;


            }

             after(grammarAccess.getComparisonAccess().getOp2Assignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Comparison__Group__1__Impl"


    // $ANTLR start "rule__Like__Group__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4484:1: rule__Like__Group__0 : rule__Like__Group__0__Impl rule__Like__Group__1 ;
    public final void rule__Like__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4488:1: ( rule__Like__Group__0__Impl rule__Like__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4489:2: rule__Like__Group__0__Impl rule__Like__Group__1
            {
            pushFollow(FOLLOW_rule__Like__Group__0__Impl_in_rule__Like__Group__09152);
            rule__Like__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Like__Group__1_in_rule__Like__Group__09155);
            rule__Like__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Like__Group__0"


    // $ANTLR start "rule__Like__Group__0__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4496:1: rule__Like__Group__0__Impl : ( ( rule__Like__Alternatives_0 ) ) ;
    public final void rule__Like__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4500:1: ( ( ( rule__Like__Alternatives_0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4501:1: ( ( rule__Like__Alternatives_0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4501:1: ( ( rule__Like__Alternatives_0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4502:1: ( rule__Like__Alternatives_0 )
            {
             before(grammarAccess.getLikeAccess().getAlternatives_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4503:1: ( rule__Like__Alternatives_0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4503:2: rule__Like__Alternatives_0
            {
            pushFollow(FOLLOW_rule__Like__Alternatives_0_in_rule__Like__Group__0__Impl9182);
            rule__Like__Alternatives_0();

            state._fsp--;


            }

             after(grammarAccess.getLikeAccess().getAlternatives_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Like__Group__0__Impl"


    // $ANTLR start "rule__Like__Group__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4513:1: rule__Like__Group__1 : rule__Like__Group__1__Impl ;
    public final void rule__Like__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4517:1: ( rule__Like__Group__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4518:2: rule__Like__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__Like__Group__1__Impl_in_rule__Like__Group__19212);
            rule__Like__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Like__Group__1"


    // $ANTLR start "rule__Like__Group__1__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4524:1: rule__Like__Group__1__Impl : ( ruleStringOperand ) ;
    public final void rule__Like__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4528:1: ( ( ruleStringOperand ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4529:1: ( ruleStringOperand )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4529:1: ( ruleStringOperand )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4530:1: ruleStringOperand
            {
             before(grammarAccess.getLikeAccess().getStringOperandParserRuleCall_1()); 
            pushFollow(FOLLOW_ruleStringOperand_in_rule__Like__Group__1__Impl9239);
            ruleStringOperand();

            state._fsp--;

             after(grammarAccess.getLikeAccess().getStringOperandParserRuleCall_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Like__Group__1__Impl"


    // $ANTLR start "rule__Between__Group__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4545:1: rule__Between__Group__0 : rule__Between__Group__0__Impl rule__Between__Group__1 ;
    public final void rule__Between__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4549:1: ( rule__Between__Group__0__Impl rule__Between__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4550:2: rule__Between__Group__0__Impl rule__Between__Group__1
            {
            pushFollow(FOLLOW_rule__Between__Group__0__Impl_in_rule__Between__Group__09272);
            rule__Between__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Between__Group__1_in_rule__Between__Group__09275);
            rule__Between__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Between__Group__0"


    // $ANTLR start "rule__Between__Group__0__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4557:1: rule__Between__Group__0__Impl : ( KEYWORD_32 ) ;
    public final void rule__Between__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4561:1: ( ( KEYWORD_32 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4562:1: ( KEYWORD_32 )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4562:1: ( KEYWORD_32 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4563:1: KEYWORD_32
            {
             before(grammarAccess.getBetweenAccess().getBETWEENKeyword_0()); 
            match(input,KEYWORD_32,FOLLOW_KEYWORD_32_in_rule__Between__Group__0__Impl9303); 
             after(grammarAccess.getBetweenAccess().getBETWEENKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Between__Group__0__Impl"


    // $ANTLR start "rule__Between__Group__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4576:1: rule__Between__Group__1 : rule__Between__Group__1__Impl rule__Between__Group__2 ;
    public final void rule__Between__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4580:1: ( rule__Between__Group__1__Impl rule__Between__Group__2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4581:2: rule__Between__Group__1__Impl rule__Between__Group__2
            {
            pushFollow(FOLLOW_rule__Between__Group__1__Impl_in_rule__Between__Group__19334);
            rule__Between__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Between__Group__2_in_rule__Between__Group__19337);
            rule__Between__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Between__Group__1"


    // $ANTLR start "rule__Between__Group__1__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4588:1: rule__Between__Group__1__Impl : ( ( rule__Between__Op1Assignment_1 ) ) ;
    public final void rule__Between__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4592:1: ( ( ( rule__Between__Op1Assignment_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4593:1: ( ( rule__Between__Op1Assignment_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4593:1: ( ( rule__Between__Op1Assignment_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4594:1: ( rule__Between__Op1Assignment_1 )
            {
             before(grammarAccess.getBetweenAccess().getOp1Assignment_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4595:1: ( rule__Between__Op1Assignment_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4595:2: rule__Between__Op1Assignment_1
            {
            pushFollow(FOLLOW_rule__Between__Op1Assignment_1_in_rule__Between__Group__1__Impl9364);
            rule__Between__Op1Assignment_1();

            state._fsp--;


            }

             after(grammarAccess.getBetweenAccess().getOp1Assignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Between__Group__1__Impl"


    // $ANTLR start "rule__Between__Group__2"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4605:1: rule__Between__Group__2 : rule__Between__Group__2__Impl rule__Between__Group__3 ;
    public final void rule__Between__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4609:1: ( rule__Between__Group__2__Impl rule__Between__Group__3 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4610:2: rule__Between__Group__2__Impl rule__Between__Group__3
            {
            pushFollow(FOLLOW_rule__Between__Group__2__Impl_in_rule__Between__Group__29394);
            rule__Between__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Between__Group__3_in_rule__Between__Group__29397);
            rule__Between__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Between__Group__2"


    // $ANTLR start "rule__Between__Group__2__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4617:1: rule__Between__Group__2__Impl : ( KEYWORD_21 ) ;
    public final void rule__Between__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4621:1: ( ( KEYWORD_21 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4622:1: ( KEYWORD_21 )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4622:1: ( KEYWORD_21 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4623:1: KEYWORD_21
            {
             before(grammarAccess.getBetweenAccess().getANDKeyword_2()); 
            match(input,KEYWORD_21,FOLLOW_KEYWORD_21_in_rule__Between__Group__2__Impl9425); 
             after(grammarAccess.getBetweenAccess().getANDKeyword_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Between__Group__2__Impl"


    // $ANTLR start "rule__Between__Group__3"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4636:1: rule__Between__Group__3 : rule__Between__Group__3__Impl ;
    public final void rule__Between__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4640:1: ( rule__Between__Group__3__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4641:2: rule__Between__Group__3__Impl
            {
            pushFollow(FOLLOW_rule__Between__Group__3__Impl_in_rule__Between__Group__39456);
            rule__Between__Group__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Between__Group__3"


    // $ANTLR start "rule__Between__Group__3__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4647:1: rule__Between__Group__3__Impl : ( ( rule__Between__Op2Assignment_3 ) ) ;
    public final void rule__Between__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4651:1: ( ( ( rule__Between__Op2Assignment_3 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4652:1: ( ( rule__Between__Op2Assignment_3 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4652:1: ( ( rule__Between__Op2Assignment_3 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4653:1: ( rule__Between__Op2Assignment_3 )
            {
             before(grammarAccess.getBetweenAccess().getOp2Assignment_3()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4654:1: ( rule__Between__Op2Assignment_3 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4654:2: rule__Between__Op2Assignment_3
            {
            pushFollow(FOLLOW_rule__Between__Op2Assignment_3_in_rule__Between__Group__3__Impl9483);
            rule__Between__Op2Assignment_3();

            state._fsp--;


            }

             after(grammarAccess.getBetweenAccess().getOp2Assignment_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Between__Group__3__Impl"


    // $ANTLR start "rule__InOperator__Group__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4672:1: rule__InOperator__Group__0 : rule__InOperator__Group__0__Impl rule__InOperator__Group__1 ;
    public final void rule__InOperator__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4676:1: ( rule__InOperator__Group__0__Impl rule__InOperator__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4677:2: rule__InOperator__Group__0__Impl rule__InOperator__Group__1
            {
            pushFollow(FOLLOW_rule__InOperator__Group__0__Impl_in_rule__InOperator__Group__09521);
            rule__InOperator__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__InOperator__Group__1_in_rule__InOperator__Group__09524);
            rule__InOperator__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InOperator__Group__0"


    // $ANTLR start "rule__InOperator__Group__0__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4684:1: rule__InOperator__Group__0__Impl : ( () ) ;
    public final void rule__InOperator__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4688:1: ( ( () ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4689:1: ( () )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4689:1: ( () )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4690:1: ()
            {
             before(grammarAccess.getInOperatorAccess().getInopAction_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4691:1: ()
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4693:1: 
            {
            }

             after(grammarAccess.getInOperatorAccess().getInopAction_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InOperator__Group__0__Impl"


    // $ANTLR start "rule__InOperator__Group__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4703:1: rule__InOperator__Group__1 : rule__InOperator__Group__1__Impl rule__InOperator__Group__2 ;
    public final void rule__InOperator__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4707:1: ( rule__InOperator__Group__1__Impl rule__InOperator__Group__2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4708:2: rule__InOperator__Group__1__Impl rule__InOperator__Group__2
            {
            pushFollow(FOLLOW_rule__InOperator__Group__1__Impl_in_rule__InOperator__Group__19582);
            rule__InOperator__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__InOperator__Group__2_in_rule__InOperator__Group__19585);
            rule__InOperator__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InOperator__Group__1"


    // $ANTLR start "rule__InOperator__Group__1__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4715:1: rule__InOperator__Group__1__Impl : ( ( rule__InOperator__Alternatives_1 ) ) ;
    public final void rule__InOperator__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4719:1: ( ( ( rule__InOperator__Alternatives_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4720:1: ( ( rule__InOperator__Alternatives_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4720:1: ( ( rule__InOperator__Alternatives_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4721:1: ( rule__InOperator__Alternatives_1 )
            {
             before(grammarAccess.getInOperatorAccess().getAlternatives_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4722:1: ( rule__InOperator__Alternatives_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4722:2: rule__InOperator__Alternatives_1
            {
            pushFollow(FOLLOW_rule__InOperator__Alternatives_1_in_rule__InOperator__Group__1__Impl9612);
            rule__InOperator__Alternatives_1();

            state._fsp--;


            }

             after(grammarAccess.getInOperatorAccess().getAlternatives_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InOperator__Group__1__Impl"


    // $ANTLR start "rule__InOperator__Group__2"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4732:1: rule__InOperator__Group__2 : rule__InOperator__Group__2__Impl rule__InOperator__Group__3 ;
    public final void rule__InOperator__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4736:1: ( rule__InOperator__Group__2__Impl rule__InOperator__Group__3 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4737:2: rule__InOperator__Group__2__Impl rule__InOperator__Group__3
            {
            pushFollow(FOLLOW_rule__InOperator__Group__2__Impl_in_rule__InOperator__Group__29642);
            rule__InOperator__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__InOperator__Group__3_in_rule__InOperator__Group__29645);
            rule__InOperator__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InOperator__Group__2"


    // $ANTLR start "rule__InOperator__Group__2__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4744:1: rule__InOperator__Group__2__Impl : ( ( rule__InOperator__Alternatives_2 ) ) ;
    public final void rule__InOperator__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4748:1: ( ( ( rule__InOperator__Alternatives_2 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4749:1: ( ( rule__InOperator__Alternatives_2 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4749:1: ( ( rule__InOperator__Alternatives_2 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4750:1: ( rule__InOperator__Alternatives_2 )
            {
             before(grammarAccess.getInOperatorAccess().getAlternatives_2()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4751:1: ( rule__InOperator__Alternatives_2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4751:2: rule__InOperator__Alternatives_2
            {
            pushFollow(FOLLOW_rule__InOperator__Alternatives_2_in_rule__InOperator__Group__2__Impl9672);
            rule__InOperator__Alternatives_2();

            state._fsp--;


            }

             after(grammarAccess.getInOperatorAccess().getAlternatives_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InOperator__Group__2__Impl"


    // $ANTLR start "rule__InOperator__Group__3"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4761:1: rule__InOperator__Group__3 : rule__InOperator__Group__3__Impl ;
    public final void rule__InOperator__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4765:1: ( rule__InOperator__Group__3__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4766:2: rule__InOperator__Group__3__Impl
            {
            pushFollow(FOLLOW_rule__InOperator__Group__3__Impl_in_rule__InOperator__Group__39702);
            rule__InOperator__Group__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InOperator__Group__3"


    // $ANTLR start "rule__InOperator__Group__3__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4772:1: rule__InOperator__Group__3__Impl : ( KEYWORD_2 ) ;
    public final void rule__InOperator__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4776:1: ( ( KEYWORD_2 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4777:1: ( KEYWORD_2 )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4777:1: ( KEYWORD_2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4778:1: KEYWORD_2
            {
             before(grammarAccess.getInOperatorAccess().getRightParenthesisKeyword_3()); 
            match(input,KEYWORD_2,FOLLOW_KEYWORD_2_in_rule__InOperator__Group__3__Impl9730); 
             after(grammarAccess.getInOperatorAccess().getRightParenthesisKeyword_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InOperator__Group__3__Impl"


    // $ANTLR start "rule__InOperator__Group_2_1__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4799:1: rule__InOperator__Group_2_1__0 : rule__InOperator__Group_2_1__0__Impl rule__InOperator__Group_2_1__1 ;
    public final void rule__InOperator__Group_2_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4803:1: ( rule__InOperator__Group_2_1__0__Impl rule__InOperator__Group_2_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4804:2: rule__InOperator__Group_2_1__0__Impl rule__InOperator__Group_2_1__1
            {
            pushFollow(FOLLOW_rule__InOperator__Group_2_1__0__Impl_in_rule__InOperator__Group_2_1__09769);
            rule__InOperator__Group_2_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__InOperator__Group_2_1__1_in_rule__InOperator__Group_2_1__09772);
            rule__InOperator__Group_2_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InOperator__Group_2_1__0"


    // $ANTLR start "rule__InOperator__Group_2_1__0__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4811:1: rule__InOperator__Group_2_1__0__Impl : ( () ) ;
    public final void rule__InOperator__Group_2_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4815:1: ( ( () ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4816:1: ( () )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4816:1: ( () )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4817:1: ()
            {
             before(grammarAccess.getInOperatorAccess().getXopEntriesAction_2_1_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4818:1: ()
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4820:1: 
            {
            }

             after(grammarAccess.getInOperatorAccess().getXopEntriesAction_2_1_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InOperator__Group_2_1__0__Impl"


    // $ANTLR start "rule__InOperator__Group_2_1__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4830:1: rule__InOperator__Group_2_1__1 : rule__InOperator__Group_2_1__1__Impl ;
    public final void rule__InOperator__Group_2_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4834:1: ( rule__InOperator__Group_2_1__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4835:2: rule__InOperator__Group_2_1__1__Impl
            {
            pushFollow(FOLLOW_rule__InOperator__Group_2_1__1__Impl_in_rule__InOperator__Group_2_1__19830);
            rule__InOperator__Group_2_1__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InOperator__Group_2_1__1"


    // $ANTLR start "rule__InOperator__Group_2_1__1__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4841:1: rule__InOperator__Group_2_1__1__Impl : ( ( ( rule__InOperator__Group_2_1_1__0 ) ) ( ( rule__InOperator__Group_2_1_1__0 )* ) ) ;
    public final void rule__InOperator__Group_2_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4845:1: ( ( ( ( rule__InOperator__Group_2_1_1__0 ) ) ( ( rule__InOperator__Group_2_1_1__0 )* ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4846:1: ( ( ( rule__InOperator__Group_2_1_1__0 ) ) ( ( rule__InOperator__Group_2_1_1__0 )* ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4846:1: ( ( ( rule__InOperator__Group_2_1_1__0 ) ) ( ( rule__InOperator__Group_2_1_1__0 )* ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4847:1: ( ( rule__InOperator__Group_2_1_1__0 ) ) ( ( rule__InOperator__Group_2_1_1__0 )* )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4847:1: ( ( rule__InOperator__Group_2_1_1__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4848:1: ( rule__InOperator__Group_2_1_1__0 )
            {
             before(grammarAccess.getInOperatorAccess().getGroup_2_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4849:1: ( rule__InOperator__Group_2_1_1__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4849:2: rule__InOperator__Group_2_1_1__0
            {
            pushFollow(FOLLOW_rule__InOperator__Group_2_1_1__0_in_rule__InOperator__Group_2_1__1__Impl9859);
            rule__InOperator__Group_2_1_1__0();

            state._fsp--;


            }

             after(grammarAccess.getInOperatorAccess().getGroup_2_1_1()); 

            }

            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4852:1: ( ( rule__InOperator__Group_2_1_1__0 )* )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4853:1: ( rule__InOperator__Group_2_1_1__0 )*
            {
             before(grammarAccess.getInOperatorAccess().getGroup_2_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4854:1: ( rule__InOperator__Group_2_1_1__0 )*
            loop45:
            do {
                int alt45=2;
                int LA45_0 = input.LA(1);

                if ( (LA45_0==KEYWORD_4) ) {
                    alt45=1;
                }


                switch (alt45) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4854:2: rule__InOperator__Group_2_1_1__0
            	    {
            	    pushFollow(FOLLOW_rule__InOperator__Group_2_1_1__0_in_rule__InOperator__Group_2_1__1__Impl9871);
            	    rule__InOperator__Group_2_1_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop45;
                }
            } while (true);

             after(grammarAccess.getInOperatorAccess().getGroup_2_1_1()); 

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
    // $ANTLR end "rule__InOperator__Group_2_1__1__Impl"


    // $ANTLR start "rule__InOperator__Group_2_1_1__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4869:1: rule__InOperator__Group_2_1_1__0 : rule__InOperator__Group_2_1_1__0__Impl rule__InOperator__Group_2_1_1__1 ;
    public final void rule__InOperator__Group_2_1_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4873:1: ( rule__InOperator__Group_2_1_1__0__Impl rule__InOperator__Group_2_1_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4874:2: rule__InOperator__Group_2_1_1__0__Impl rule__InOperator__Group_2_1_1__1
            {
            pushFollow(FOLLOW_rule__InOperator__Group_2_1_1__0__Impl_in_rule__InOperator__Group_2_1_1__09908);
            rule__InOperator__Group_2_1_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__InOperator__Group_2_1_1__1_in_rule__InOperator__Group_2_1_1__09911);
            rule__InOperator__Group_2_1_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InOperator__Group_2_1_1__0"


    // $ANTLR start "rule__InOperator__Group_2_1_1__0__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4881:1: rule__InOperator__Group_2_1_1__0__Impl : ( KEYWORD_4 ) ;
    public final void rule__InOperator__Group_2_1_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4885:1: ( ( KEYWORD_4 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4886:1: ( KEYWORD_4 )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4886:1: ( KEYWORD_4 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4887:1: KEYWORD_4
            {
             before(grammarAccess.getInOperatorAccess().getCommaKeyword_2_1_1_0()); 
            match(input,KEYWORD_4,FOLLOW_KEYWORD_4_in_rule__InOperator__Group_2_1_1__0__Impl9939); 
             after(grammarAccess.getInOperatorAccess().getCommaKeyword_2_1_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InOperator__Group_2_1_1__0__Impl"


    // $ANTLR start "rule__InOperator__Group_2_1_1__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4900:1: rule__InOperator__Group_2_1_1__1 : rule__InOperator__Group_2_1_1__1__Impl ;
    public final void rule__InOperator__Group_2_1_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4904:1: ( rule__InOperator__Group_2_1_1__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4905:2: rule__InOperator__Group_2_1_1__1__Impl
            {
            pushFollow(FOLLOW_rule__InOperator__Group_2_1_1__1__Impl_in_rule__InOperator__Group_2_1_1__19970);
            rule__InOperator__Group_2_1_1__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InOperator__Group_2_1_1__1"


    // $ANTLR start "rule__InOperator__Group_2_1_1__1__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4911:1: rule__InOperator__Group_2_1_1__1__Impl : ( ( rule__InOperator__EntriesAssignment_2_1_1_1 ) ) ;
    public final void rule__InOperator__Group_2_1_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4915:1: ( ( ( rule__InOperator__EntriesAssignment_2_1_1_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4916:1: ( ( rule__InOperator__EntriesAssignment_2_1_1_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4916:1: ( ( rule__InOperator__EntriesAssignment_2_1_1_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4917:1: ( rule__InOperator__EntriesAssignment_2_1_1_1 )
            {
             before(grammarAccess.getInOperatorAccess().getEntriesAssignment_2_1_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4918:1: ( rule__InOperator__EntriesAssignment_2_1_1_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4918:2: rule__InOperator__EntriesAssignment_2_1_1_1
            {
            pushFollow(FOLLOW_rule__InOperator__EntriesAssignment_2_1_1_1_in_rule__InOperator__Group_2_1_1__1__Impl9997);
            rule__InOperator__EntriesAssignment_2_1_1_1();

            state._fsp--;


            }

             after(grammarAccess.getInOperatorAccess().getEntriesAssignment_2_1_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InOperator__Group_2_1_1__1__Impl"


    // $ANTLR start "rule__Operand__Group__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4932:1: rule__Operand__Group__0 : rule__Operand__Group__0__Impl rule__Operand__Group__1 ;
    public final void rule__Operand__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4936:1: ( rule__Operand__Group__0__Impl rule__Operand__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4937:2: rule__Operand__Group__0__Impl rule__Operand__Group__1
            {
            pushFollow(FOLLOW_rule__Operand__Group__0__Impl_in_rule__Operand__Group__010031);
            rule__Operand__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Operand__Group__1_in_rule__Operand__Group__010034);
            rule__Operand__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operand__Group__0"


    // $ANTLR start "rule__Operand__Group__0__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4944:1: rule__Operand__Group__0__Impl : ( ruleOperandFragment ) ;
    public final void rule__Operand__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4948:1: ( ( ruleOperandFragment ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4949:1: ( ruleOperandFragment )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4949:1: ( ruleOperandFragment )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4950:1: ruleOperandFragment
            {
             before(grammarAccess.getOperandAccess().getOperandFragmentParserRuleCall_0()); 
            pushFollow(FOLLOW_ruleOperandFragment_in_rule__Operand__Group__0__Impl10061);
            ruleOperandFragment();

            state._fsp--;

             after(grammarAccess.getOperandAccess().getOperandFragmentParserRuleCall_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operand__Group__0__Impl"


    // $ANTLR start "rule__Operand__Group__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4961:1: rule__Operand__Group__1 : rule__Operand__Group__1__Impl ;
    public final void rule__Operand__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4965:1: ( rule__Operand__Group__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4966:2: rule__Operand__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__Operand__Group__1__Impl_in_rule__Operand__Group__110090);
            rule__Operand__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operand__Group__1"


    // $ANTLR start "rule__Operand__Group__1__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4972:1: rule__Operand__Group__1__Impl : ( ( rule__Operand__Group_1__0 )? ) ;
    public final void rule__Operand__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4976:1: ( ( ( rule__Operand__Group_1__0 )? ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4977:1: ( ( rule__Operand__Group_1__0 )? )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4977:1: ( ( rule__Operand__Group_1__0 )? )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4978:1: ( rule__Operand__Group_1__0 )?
            {
             before(grammarAccess.getOperandAccess().getGroup_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4979:1: ( rule__Operand__Group_1__0 )?
            int alt46=2;
            int LA46_0 = input.LA(1);

            if ( (LA46_0==KEYWORD_18||LA46_0==KEYWORD_3||LA46_0==KEYWORD_5||LA46_0==KEYWORD_7||LA46_0==RULE_STAR) ) {
                alt46=1;
            }
            switch (alt46) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4979:2: rule__Operand__Group_1__0
                    {
                    pushFollow(FOLLOW_rule__Operand__Group_1__0_in_rule__Operand__Group__1__Impl10117);
                    rule__Operand__Group_1__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getOperandAccess().getGroup_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operand__Group__1__Impl"


    // $ANTLR start "rule__Operand__Group_1__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4993:1: rule__Operand__Group_1__0 : rule__Operand__Group_1__0__Impl rule__Operand__Group_1__1 ;
    public final void rule__Operand__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4997:1: ( rule__Operand__Group_1__0__Impl rule__Operand__Group_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4998:2: rule__Operand__Group_1__0__Impl rule__Operand__Group_1__1
            {
            pushFollow(FOLLOW_rule__Operand__Group_1__0__Impl_in_rule__Operand__Group_1__010152);
            rule__Operand__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Operand__Group_1__1_in_rule__Operand__Group_1__010155);
            rule__Operand__Group_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operand__Group_1__0"


    // $ANTLR start "rule__Operand__Group_1__0__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5005:1: rule__Operand__Group_1__0__Impl : ( () ) ;
    public final void rule__Operand__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5009:1: ( ( () ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5010:1: ( () )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5010:1: ( () )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5011:1: ()
            {
             before(grammarAccess.getOperandAccess().getOperandsEntriesAction_1_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5012:1: ()
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5014:1: 
            {
            }

             after(grammarAccess.getOperandAccess().getOperandsEntriesAction_1_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operand__Group_1__0__Impl"


    // $ANTLR start "rule__Operand__Group_1__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5024:1: rule__Operand__Group_1__1 : rule__Operand__Group_1__1__Impl ;
    public final void rule__Operand__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5028:1: ( rule__Operand__Group_1__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5029:2: rule__Operand__Group_1__1__Impl
            {
            pushFollow(FOLLOW_rule__Operand__Group_1__1__Impl_in_rule__Operand__Group_1__110213);
            rule__Operand__Group_1__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operand__Group_1__1"


    // $ANTLR start "rule__Operand__Group_1__1__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5035:1: rule__Operand__Group_1__1__Impl : ( ( ( rule__Operand__Group_1_1__0 ) ) ( ( rule__Operand__Group_1_1__0 )* ) ) ;
    public final void rule__Operand__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5039:1: ( ( ( ( rule__Operand__Group_1_1__0 ) ) ( ( rule__Operand__Group_1_1__0 )* ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5040:1: ( ( ( rule__Operand__Group_1_1__0 ) ) ( ( rule__Operand__Group_1_1__0 )* ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5040:1: ( ( ( rule__Operand__Group_1_1__0 ) ) ( ( rule__Operand__Group_1_1__0 )* ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5041:1: ( ( rule__Operand__Group_1_1__0 ) ) ( ( rule__Operand__Group_1_1__0 )* )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5041:1: ( ( rule__Operand__Group_1_1__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5042:1: ( rule__Operand__Group_1_1__0 )
            {
             before(grammarAccess.getOperandAccess().getGroup_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5043:1: ( rule__Operand__Group_1_1__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5043:2: rule__Operand__Group_1_1__0
            {
            pushFollow(FOLLOW_rule__Operand__Group_1_1__0_in_rule__Operand__Group_1__1__Impl10242);
            rule__Operand__Group_1_1__0();

            state._fsp--;


            }

             after(grammarAccess.getOperandAccess().getGroup_1_1()); 

            }

            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5046:1: ( ( rule__Operand__Group_1_1__0 )* )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5047:1: ( rule__Operand__Group_1_1__0 )*
            {
             before(grammarAccess.getOperandAccess().getGroup_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5048:1: ( rule__Operand__Group_1_1__0 )*
            loop47:
            do {
                int alt47=2;
                int LA47_0 = input.LA(1);

                if ( (LA47_0==KEYWORD_18||LA47_0==KEYWORD_3||LA47_0==KEYWORD_5||LA47_0==KEYWORD_7||LA47_0==RULE_STAR) ) {
                    alt47=1;
                }


                switch (alt47) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5048:2: rule__Operand__Group_1_1__0
            	    {
            	    pushFollow(FOLLOW_rule__Operand__Group_1_1__0_in_rule__Operand__Group_1__1__Impl10254);
            	    rule__Operand__Group_1_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop47;
                }
            } while (true);

             after(grammarAccess.getOperandAccess().getGroup_1_1()); 

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
    // $ANTLR end "rule__Operand__Group_1__1__Impl"


    // $ANTLR start "rule__Operand__Group_1_1__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5063:1: rule__Operand__Group_1_1__0 : rule__Operand__Group_1_1__0__Impl rule__Operand__Group_1_1__1 ;
    public final void rule__Operand__Group_1_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5067:1: ( rule__Operand__Group_1_1__0__Impl rule__Operand__Group_1_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5068:2: rule__Operand__Group_1_1__0__Impl rule__Operand__Group_1_1__1
            {
            pushFollow(FOLLOW_rule__Operand__Group_1_1__0__Impl_in_rule__Operand__Group_1_1__010291);
            rule__Operand__Group_1_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Operand__Group_1_1__1_in_rule__Operand__Group_1_1__010294);
            rule__Operand__Group_1_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operand__Group_1_1__0"


    // $ANTLR start "rule__Operand__Group_1_1__0__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5075:1: rule__Operand__Group_1_1__0__Impl : ( ( rule__Operand__Alternatives_1_1_0 ) ) ;
    public final void rule__Operand__Group_1_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5079:1: ( ( ( rule__Operand__Alternatives_1_1_0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5080:1: ( ( rule__Operand__Alternatives_1_1_0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5080:1: ( ( rule__Operand__Alternatives_1_1_0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5081:1: ( rule__Operand__Alternatives_1_1_0 )
            {
             before(grammarAccess.getOperandAccess().getAlternatives_1_1_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5082:1: ( rule__Operand__Alternatives_1_1_0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5082:2: rule__Operand__Alternatives_1_1_0
            {
            pushFollow(FOLLOW_rule__Operand__Alternatives_1_1_0_in_rule__Operand__Group_1_1__0__Impl10321);
            rule__Operand__Alternatives_1_1_0();

            state._fsp--;


            }

             after(grammarAccess.getOperandAccess().getAlternatives_1_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operand__Group_1_1__0__Impl"


    // $ANTLR start "rule__Operand__Group_1_1__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5092:1: rule__Operand__Group_1_1__1 : rule__Operand__Group_1_1__1__Impl ;
    public final void rule__Operand__Group_1_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5096:1: ( rule__Operand__Group_1_1__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5097:2: rule__Operand__Group_1_1__1__Impl
            {
            pushFollow(FOLLOW_rule__Operand__Group_1_1__1__Impl_in_rule__Operand__Group_1_1__110351);
            rule__Operand__Group_1_1__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operand__Group_1_1__1"


    // $ANTLR start "rule__Operand__Group_1_1__1__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5103:1: rule__Operand__Group_1_1__1__Impl : ( ( rule__Operand__EntriesAssignment_1_1_1 ) ) ;
    public final void rule__Operand__Group_1_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5107:1: ( ( ( rule__Operand__EntriesAssignment_1_1_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5108:1: ( ( rule__Operand__EntriesAssignment_1_1_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5108:1: ( ( rule__Operand__EntriesAssignment_1_1_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5109:1: ( rule__Operand__EntriesAssignment_1_1_1 )
            {
             before(grammarAccess.getOperandAccess().getEntriesAssignment_1_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5110:1: ( rule__Operand__EntriesAssignment_1_1_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5110:2: rule__Operand__EntriesAssignment_1_1_1
            {
            pushFollow(FOLLOW_rule__Operand__EntriesAssignment_1_1_1_in_rule__Operand__Group_1_1__1__Impl10378);
            rule__Operand__EntriesAssignment_1_1_1();

            state._fsp--;


            }

             after(grammarAccess.getOperandAccess().getEntriesAssignment_1_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operand__Group_1_1__1__Impl"


    // $ANTLR start "rule__ParameterOperand__Group__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5125:1: rule__ParameterOperand__Group__0 : rule__ParameterOperand__Group__0__Impl rule__ParameterOperand__Group__1 ;
    public final void rule__ParameterOperand__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5129:1: ( rule__ParameterOperand__Group__0__Impl rule__ParameterOperand__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5130:2: rule__ParameterOperand__Group__0__Impl rule__ParameterOperand__Group__1
            {
            pushFollow(FOLLOW_rule__ParameterOperand__Group__0__Impl_in_rule__ParameterOperand__Group__010413);
            rule__ParameterOperand__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ParameterOperand__Group__1_in_rule__ParameterOperand__Group__010416);
            rule__ParameterOperand__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ParameterOperand__Group__0"


    // $ANTLR start "rule__ParameterOperand__Group__0__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5137:1: rule__ParameterOperand__Group__0__Impl : ( () ) ;
    public final void rule__ParameterOperand__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5141:1: ( ( () ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5142:1: ( () )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5142:1: ( () )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5143:1: ()
            {
             before(grammarAccess.getParameterOperandAccess().getPoperandAction_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5144:1: ()
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5146:1: 
            {
            }

             after(grammarAccess.getParameterOperandAccess().getPoperandAction_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ParameterOperand__Group__0__Impl"


    // $ANTLR start "rule__ParameterOperand__Group__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5156:1: rule__ParameterOperand__Group__1 : rule__ParameterOperand__Group__1__Impl rule__ParameterOperand__Group__2 ;
    public final void rule__ParameterOperand__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5160:1: ( rule__ParameterOperand__Group__1__Impl rule__ParameterOperand__Group__2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5161:2: rule__ParameterOperand__Group__1__Impl rule__ParameterOperand__Group__2
            {
            pushFollow(FOLLOW_rule__ParameterOperand__Group__1__Impl_in_rule__ParameterOperand__Group__110474);
            rule__ParameterOperand__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ParameterOperand__Group__2_in_rule__ParameterOperand__Group__110477);
            rule__ParameterOperand__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ParameterOperand__Group__1"


    // $ANTLR start "rule__ParameterOperand__Group__1__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5168:1: rule__ParameterOperand__Group__1__Impl : ( KEYWORD_19 ) ;
    public final void rule__ParameterOperand__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5172:1: ( ( KEYWORD_19 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5173:1: ( KEYWORD_19 )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5173:1: ( KEYWORD_19 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5174:1: KEYWORD_19
            {
             before(grammarAccess.getParameterOperandAccess().getPKeyword_1()); 
            match(input,KEYWORD_19,FOLLOW_KEYWORD_19_in_rule__ParameterOperand__Group__1__Impl10505); 
             after(grammarAccess.getParameterOperandAccess().getPKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ParameterOperand__Group__1__Impl"


    // $ANTLR start "rule__ParameterOperand__Group__2"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5187:1: rule__ParameterOperand__Group__2 : rule__ParameterOperand__Group__2__Impl rule__ParameterOperand__Group__3 ;
    public final void rule__ParameterOperand__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5191:1: ( rule__ParameterOperand__Group__2__Impl rule__ParameterOperand__Group__3 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5192:2: rule__ParameterOperand__Group__2__Impl rule__ParameterOperand__Group__3
            {
            pushFollow(FOLLOW_rule__ParameterOperand__Group__2__Impl_in_rule__ParameterOperand__Group__210536);
            rule__ParameterOperand__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ParameterOperand__Group__3_in_rule__ParameterOperand__Group__210539);
            rule__ParameterOperand__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ParameterOperand__Group__2"


    // $ANTLR start "rule__ParameterOperand__Group__2__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5199:1: rule__ParameterOperand__Group__2__Impl : ( RULE_ID ) ;
    public final void rule__ParameterOperand__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5203:1: ( ( RULE_ID ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5204:1: ( RULE_ID )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5204:1: ( RULE_ID )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5205:1: RULE_ID
            {
             before(grammarAccess.getParameterOperandAccess().getIDTerminalRuleCall_2()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__ParameterOperand__Group__2__Impl10566); 
             after(grammarAccess.getParameterOperandAccess().getIDTerminalRuleCall_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ParameterOperand__Group__2__Impl"


    // $ANTLR start "rule__ParameterOperand__Group__3"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5216:1: rule__ParameterOperand__Group__3 : rule__ParameterOperand__Group__3__Impl ;
    public final void rule__ParameterOperand__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5220:1: ( rule__ParameterOperand__Group__3__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5221:2: rule__ParameterOperand__Group__3__Impl
            {
            pushFollow(FOLLOW_rule__ParameterOperand__Group__3__Impl_in_rule__ParameterOperand__Group__310595);
            rule__ParameterOperand__Group__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ParameterOperand__Group__3"


    // $ANTLR start "rule__ParameterOperand__Group__3__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5227:1: rule__ParameterOperand__Group__3__Impl : ( KEYWORD_11 ) ;
    public final void rule__ParameterOperand__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5231:1: ( ( KEYWORD_11 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5232:1: ( KEYWORD_11 )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5232:1: ( KEYWORD_11 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5233:1: KEYWORD_11
            {
             before(grammarAccess.getParameterOperandAccess().getRightCurlyBracketKeyword_3()); 
            match(input,KEYWORD_11,FOLLOW_KEYWORD_11_in_rule__ParameterOperand__Group__3__Impl10623); 
             after(grammarAccess.getParameterOperandAccess().getRightCurlyBracketKeyword_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ParameterOperand__Group__3__Impl"


    // $ANTLR start "rule__ExclamationParameterOperand__Group__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5254:1: rule__ExclamationParameterOperand__Group__0 : rule__ExclamationParameterOperand__Group__0__Impl rule__ExclamationParameterOperand__Group__1 ;
    public final void rule__ExclamationParameterOperand__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5258:1: ( rule__ExclamationParameterOperand__Group__0__Impl rule__ExclamationParameterOperand__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5259:2: rule__ExclamationParameterOperand__Group__0__Impl rule__ExclamationParameterOperand__Group__1
            {
            pushFollow(FOLLOW_rule__ExclamationParameterOperand__Group__0__Impl_in_rule__ExclamationParameterOperand__Group__010662);
            rule__ExclamationParameterOperand__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ExclamationParameterOperand__Group__1_in_rule__ExclamationParameterOperand__Group__010665);
            rule__ExclamationParameterOperand__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ExclamationParameterOperand__Group__0"


    // $ANTLR start "rule__ExclamationParameterOperand__Group__0__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5266:1: rule__ExclamationParameterOperand__Group__0__Impl : ( () ) ;
    public final void rule__ExclamationParameterOperand__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5270:1: ( ( () ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5271:1: ( () )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5271:1: ( () )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5272:1: ()
            {
             before(grammarAccess.getExclamationParameterOperandAccess().getExpoperandAction_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5273:1: ()
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5275:1: 
            {
            }

             after(grammarAccess.getExclamationParameterOperandAccess().getExpoperandAction_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ExclamationParameterOperand__Group__0__Impl"


    // $ANTLR start "rule__ExclamationParameterOperand__Group__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5285:1: rule__ExclamationParameterOperand__Group__1 : rule__ExclamationParameterOperand__Group__1__Impl rule__ExclamationParameterOperand__Group__2 ;
    public final void rule__ExclamationParameterOperand__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5289:1: ( rule__ExclamationParameterOperand__Group__1__Impl rule__ExclamationParameterOperand__Group__2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5290:2: rule__ExclamationParameterOperand__Group__1__Impl rule__ExclamationParameterOperand__Group__2
            {
            pushFollow(FOLLOW_rule__ExclamationParameterOperand__Group__1__Impl_in_rule__ExclamationParameterOperand__Group__110723);
            rule__ExclamationParameterOperand__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ExclamationParameterOperand__Group__2_in_rule__ExclamationParameterOperand__Group__110726);
            rule__ExclamationParameterOperand__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ExclamationParameterOperand__Group__1"


    // $ANTLR start "rule__ExclamationParameterOperand__Group__1__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5297:1: rule__ExclamationParameterOperand__Group__1__Impl : ( KEYWORD_23 ) ;
    public final void rule__ExclamationParameterOperand__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5301:1: ( ( KEYWORD_23 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5302:1: ( KEYWORD_23 )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5302:1: ( KEYWORD_23 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5303:1: KEYWORD_23
            {
             before(grammarAccess.getExclamationParameterOperandAccess().getPKeyword_1()); 
            match(input,KEYWORD_23,FOLLOW_KEYWORD_23_in_rule__ExclamationParameterOperand__Group__1__Impl10754); 
             after(grammarAccess.getExclamationParameterOperandAccess().getPKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ExclamationParameterOperand__Group__1__Impl"


    // $ANTLR start "rule__ExclamationParameterOperand__Group__2"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5316:1: rule__ExclamationParameterOperand__Group__2 : rule__ExclamationParameterOperand__Group__2__Impl rule__ExclamationParameterOperand__Group__3 ;
    public final void rule__ExclamationParameterOperand__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5320:1: ( rule__ExclamationParameterOperand__Group__2__Impl rule__ExclamationParameterOperand__Group__3 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5321:2: rule__ExclamationParameterOperand__Group__2__Impl rule__ExclamationParameterOperand__Group__3
            {
            pushFollow(FOLLOW_rule__ExclamationParameterOperand__Group__2__Impl_in_rule__ExclamationParameterOperand__Group__210785);
            rule__ExclamationParameterOperand__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ExclamationParameterOperand__Group__3_in_rule__ExclamationParameterOperand__Group__210788);
            rule__ExclamationParameterOperand__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ExclamationParameterOperand__Group__2"


    // $ANTLR start "rule__ExclamationParameterOperand__Group__2__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5328:1: rule__ExclamationParameterOperand__Group__2__Impl : ( RULE_ID ) ;
    public final void rule__ExclamationParameterOperand__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5332:1: ( ( RULE_ID ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5333:1: ( RULE_ID )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5333:1: ( RULE_ID )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5334:1: RULE_ID
            {
             before(grammarAccess.getExclamationParameterOperandAccess().getIDTerminalRuleCall_2()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__ExclamationParameterOperand__Group__2__Impl10815); 
             after(grammarAccess.getExclamationParameterOperandAccess().getIDTerminalRuleCall_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ExclamationParameterOperand__Group__2__Impl"


    // $ANTLR start "rule__ExclamationParameterOperand__Group__3"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5345:1: rule__ExclamationParameterOperand__Group__3 : rule__ExclamationParameterOperand__Group__3__Impl ;
    public final void rule__ExclamationParameterOperand__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5349:1: ( rule__ExclamationParameterOperand__Group__3__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5350:2: rule__ExclamationParameterOperand__Group__3__Impl
            {
            pushFollow(FOLLOW_rule__ExclamationParameterOperand__Group__3__Impl_in_rule__ExclamationParameterOperand__Group__310844);
            rule__ExclamationParameterOperand__Group__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ExclamationParameterOperand__Group__3"


    // $ANTLR start "rule__ExclamationParameterOperand__Group__3__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5356:1: rule__ExclamationParameterOperand__Group__3__Impl : ( KEYWORD_11 ) ;
    public final void rule__ExclamationParameterOperand__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5360:1: ( ( KEYWORD_11 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5361:1: ( KEYWORD_11 )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5361:1: ( KEYWORD_11 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5362:1: KEYWORD_11
            {
             before(grammarAccess.getExclamationParameterOperandAccess().getRightCurlyBracketKeyword_3()); 
            match(input,KEYWORD_11,FOLLOW_KEYWORD_11_in_rule__ExclamationParameterOperand__Group__3__Impl10872); 
             after(grammarAccess.getExclamationParameterOperandAccess().getRightCurlyBracketKeyword_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ExclamationParameterOperand__Group__3__Impl"


    // $ANTLR start "rule__SubQueryOperand__Group__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5383:1: rule__SubQueryOperand__Group__0 : rule__SubQueryOperand__Group__0__Impl rule__SubQueryOperand__Group__1 ;
    public final void rule__SubQueryOperand__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5387:1: ( rule__SubQueryOperand__Group__0__Impl rule__SubQueryOperand__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5388:2: rule__SubQueryOperand__Group__0__Impl rule__SubQueryOperand__Group__1
            {
            pushFollow(FOLLOW_rule__SubQueryOperand__Group__0__Impl_in_rule__SubQueryOperand__Group__010911);
            rule__SubQueryOperand__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__SubQueryOperand__Group__1_in_rule__SubQueryOperand__Group__010914);
            rule__SubQueryOperand__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SubQueryOperand__Group__0"


    // $ANTLR start "rule__SubQueryOperand__Group__0__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5395:1: rule__SubQueryOperand__Group__0__Impl : ( () ) ;
    public final void rule__SubQueryOperand__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5399:1: ( ( () ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5400:1: ( () )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5400:1: ( () )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5401:1: ()
            {
             before(grammarAccess.getSubQueryOperandAccess().getSubqueryAction_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5402:1: ()
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5404:1: 
            {
            }

             after(grammarAccess.getSubQueryOperandAccess().getSubqueryAction_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SubQueryOperand__Group__0__Impl"


    // $ANTLR start "rule__SubQueryOperand__Group__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5414:1: rule__SubQueryOperand__Group__1 : rule__SubQueryOperand__Group__1__Impl rule__SubQueryOperand__Group__2 ;
    public final void rule__SubQueryOperand__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5418:1: ( rule__SubQueryOperand__Group__1__Impl rule__SubQueryOperand__Group__2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5419:2: rule__SubQueryOperand__Group__1__Impl rule__SubQueryOperand__Group__2
            {
            pushFollow(FOLLOW_rule__SubQueryOperand__Group__1__Impl_in_rule__SubQueryOperand__Group__110972);
            rule__SubQueryOperand__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__SubQueryOperand__Group__2_in_rule__SubQueryOperand__Group__110975);
            rule__SubQueryOperand__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SubQueryOperand__Group__1"


    // $ANTLR start "rule__SubQueryOperand__Group__1__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5426:1: rule__SubQueryOperand__Group__1__Impl : ( KEYWORD_1 ) ;
    public final void rule__SubQueryOperand__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5430:1: ( ( KEYWORD_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5431:1: ( KEYWORD_1 )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5431:1: ( KEYWORD_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5432:1: KEYWORD_1
            {
             before(grammarAccess.getSubQueryOperandAccess().getLeftParenthesisKeyword_1()); 
            match(input,KEYWORD_1,FOLLOW_KEYWORD_1_in_rule__SubQueryOperand__Group__1__Impl11003); 
             after(grammarAccess.getSubQueryOperandAccess().getLeftParenthesisKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SubQueryOperand__Group__1__Impl"


    // $ANTLR start "rule__SubQueryOperand__Group__2"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5445:1: rule__SubQueryOperand__Group__2 : rule__SubQueryOperand__Group__2__Impl rule__SubQueryOperand__Group__3 ;
    public final void rule__SubQueryOperand__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5449:1: ( rule__SubQueryOperand__Group__2__Impl rule__SubQueryOperand__Group__3 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5450:2: rule__SubQueryOperand__Group__2__Impl rule__SubQueryOperand__Group__3
            {
            pushFollow(FOLLOW_rule__SubQueryOperand__Group__2__Impl_in_rule__SubQueryOperand__Group__211034);
            rule__SubQueryOperand__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__SubQueryOperand__Group__3_in_rule__SubQueryOperand__Group__211037);
            rule__SubQueryOperand__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SubQueryOperand__Group__2"


    // $ANTLR start "rule__SubQueryOperand__Group__2__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5457:1: rule__SubQueryOperand__Group__2__Impl : ( ( rule__SubQueryOperand__SelAssignment_2 ) ) ;
    public final void rule__SubQueryOperand__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5461:1: ( ( ( rule__SubQueryOperand__SelAssignment_2 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5462:1: ( ( rule__SubQueryOperand__SelAssignment_2 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5462:1: ( ( rule__SubQueryOperand__SelAssignment_2 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5463:1: ( rule__SubQueryOperand__SelAssignment_2 )
            {
             before(grammarAccess.getSubQueryOperandAccess().getSelAssignment_2()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5464:1: ( rule__SubQueryOperand__SelAssignment_2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5464:2: rule__SubQueryOperand__SelAssignment_2
            {
            pushFollow(FOLLOW_rule__SubQueryOperand__SelAssignment_2_in_rule__SubQueryOperand__Group__2__Impl11064);
            rule__SubQueryOperand__SelAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getSubQueryOperandAccess().getSelAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SubQueryOperand__Group__2__Impl"


    // $ANTLR start "rule__SubQueryOperand__Group__3"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5474:1: rule__SubQueryOperand__Group__3 : rule__SubQueryOperand__Group__3__Impl ;
    public final void rule__SubQueryOperand__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5478:1: ( rule__SubQueryOperand__Group__3__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5479:2: rule__SubQueryOperand__Group__3__Impl
            {
            pushFollow(FOLLOW_rule__SubQueryOperand__Group__3__Impl_in_rule__SubQueryOperand__Group__311094);
            rule__SubQueryOperand__Group__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SubQueryOperand__Group__3"


    // $ANTLR start "rule__SubQueryOperand__Group__3__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5485:1: rule__SubQueryOperand__Group__3__Impl : ( KEYWORD_2 ) ;
    public final void rule__SubQueryOperand__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5489:1: ( ( KEYWORD_2 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5490:1: ( KEYWORD_2 )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5490:1: ( KEYWORD_2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5491:1: KEYWORD_2
            {
             before(grammarAccess.getSubQueryOperandAccess().getRightParenthesisKeyword_3()); 
            match(input,KEYWORD_2,FOLLOW_KEYWORD_2_in_rule__SubQueryOperand__Group__3__Impl11122); 
             after(grammarAccess.getSubQueryOperandAccess().getRightParenthesisKeyword_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SubQueryOperand__Group__3__Impl"


    // $ANTLR start "rule__Model__EntriesAssignment_1_1_1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5513:1: rule__Model__EntriesAssignment_1_1_1 : ( ruleSelect ) ;
    public final void rule__Model__EntriesAssignment_1_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5517:1: ( ( ruleSelect ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5518:1: ( ruleSelect )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5518:1: ( ruleSelect )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5519:1: ruleSelect
            {
             before(grammarAccess.getModelAccess().getEntriesSelectParserRuleCall_1_1_1_0()); 
            pushFollow(FOLLOW_ruleSelect_in_rule__Model__EntriesAssignment_1_1_111166);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5528:1: rule__Model__OrderByEntryAssignment_2_1 : ( ruleOrderByColumns ) ;
    public final void rule__Model__OrderByEntryAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5532:1: ( ( ruleOrderByColumns ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5533:1: ( ruleOrderByColumns )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5533:1: ( ruleOrderByColumns )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5534:1: ruleOrderByColumns
            {
             before(grammarAccess.getModelAccess().getOrderByEntryOrderByColumnsParserRuleCall_2_1_0()); 
            pushFollow(FOLLOW_ruleOrderByColumns_in_rule__Model__OrderByEntryAssignment_2_111197);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5543:1: rule__Select__SelectAssignment_0 : ( ( KEYWORD_31 ) ) ;
    public final void rule__Select__SelectAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5547:1: ( ( ( KEYWORD_31 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5548:1: ( ( KEYWORD_31 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5548:1: ( ( KEYWORD_31 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5549:1: ( KEYWORD_31 )
            {
             before(grammarAccess.getSelectAccess().getSelectSELECTKeyword_0_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5550:1: ( KEYWORD_31 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5551:1: KEYWORD_31
            {
             before(grammarAccess.getSelectAccess().getSelectSELECTKeyword_0_0()); 
            match(input,KEYWORD_31,FOLLOW_KEYWORD_31_in_rule__Select__SelectAssignment_011233); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5566:1: rule__Select__ColsAssignment_2 : ( ruleColumns ) ;
    public final void rule__Select__ColsAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5570:1: ( ( ruleColumns ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5571:1: ( ruleColumns )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5571:1: ( ruleColumns )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5572:1: ruleColumns
            {
             before(grammarAccess.getSelectAccess().getColsColumnsParserRuleCall_2_0()); 
            pushFollow(FOLLOW_ruleColumns_in_rule__Select__ColsAssignment_211272);
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


    // $ANTLR start "rule__Select__TblAssignment_4"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5581:1: rule__Select__TblAssignment_4 : ( ruleTables ) ;
    public final void rule__Select__TblAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5585:1: ( ( ruleTables ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5586:1: ( ruleTables )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5586:1: ( ruleTables )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5587:1: ruleTables
            {
             before(grammarAccess.getSelectAccess().getTblTablesParserRuleCall_4_0()); 
            pushFollow(FOLLOW_ruleTables_in_rule__Select__TblAssignment_411303);
            ruleTables();

            state._fsp--;

             after(grammarAccess.getSelectAccess().getTblTablesParserRuleCall_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Select__TblAssignment_4"


    // $ANTLR start "rule__Select__WhereExpressionAssignment_5_1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5596:1: rule__Select__WhereExpressionAssignment_5_1 : ( ruleFullExpression ) ;
    public final void rule__Select__WhereExpressionAssignment_5_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5600:1: ( ( ruleFullExpression ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5601:1: ( ruleFullExpression )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5601:1: ( ruleFullExpression )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5602:1: ruleFullExpression
            {
             before(grammarAccess.getSelectAccess().getWhereExpressionFullExpressionParserRuleCall_5_1_0()); 
            pushFollow(FOLLOW_ruleFullExpression_in_rule__Select__WhereExpressionAssignment_5_111334);
            ruleFullExpression();

            state._fsp--;

             after(grammarAccess.getSelectAccess().getWhereExpressionFullExpressionParserRuleCall_5_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Select__WhereExpressionAssignment_5_1"


    // $ANTLR start "rule__Select__GroupByEntryAssignment_6_1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5611:1: rule__Select__GroupByEntryAssignment_6_1 : ( ruleGroupByColumns ) ;
    public final void rule__Select__GroupByEntryAssignment_6_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5615:1: ( ( ruleGroupByColumns ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5616:1: ( ruleGroupByColumns )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5616:1: ( ruleGroupByColumns )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5617:1: ruleGroupByColumns
            {
             before(grammarAccess.getSelectAccess().getGroupByEntryGroupByColumnsParserRuleCall_6_1_0()); 
            pushFollow(FOLLOW_ruleGroupByColumns_in_rule__Select__GroupByEntryAssignment_6_111365);
            ruleGroupByColumns();

            state._fsp--;

             after(grammarAccess.getSelectAccess().getGroupByEntryGroupByColumnsParserRuleCall_6_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Select__GroupByEntryAssignment_6_1"


    // $ANTLR start "rule__Select__HavingEntryAssignment_7_1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5626:1: rule__Select__HavingEntryAssignment_7_1 : ( ruleFullExpression ) ;
    public final void rule__Select__HavingEntryAssignment_7_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5630:1: ( ( ruleFullExpression ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5631:1: ( ruleFullExpression )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5631:1: ( ruleFullExpression )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5632:1: ruleFullExpression
            {
             before(grammarAccess.getSelectAccess().getHavingEntryFullExpressionParserRuleCall_7_1_0()); 
            pushFollow(FOLLOW_ruleFullExpression_in_rule__Select__HavingEntryAssignment_7_111396);
            ruleFullExpression();

            state._fsp--;

             after(grammarAccess.getSelectAccess().getHavingEntryFullExpressionParserRuleCall_7_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Select__HavingEntryAssignment_7_1"


    // $ANTLR start "rule__Columns__EntriesAssignment_1_1_1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5641:1: rule__Columns__EntriesAssignment_1_1_1 : ( ruleColumnOrAlias ) ;
    public final void rule__Columns__EntriesAssignment_1_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5645:1: ( ( ruleColumnOrAlias ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5646:1: ( ruleColumnOrAlias )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5646:1: ( ruleColumnOrAlias )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5647:1: ruleColumnOrAlias
            {
             before(grammarAccess.getColumnsAccess().getEntriesColumnOrAliasParserRuleCall_1_1_1_0()); 
            pushFollow(FOLLOW_ruleColumnOrAlias_in_rule__Columns__EntriesAssignment_1_1_111427);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5656:1: rule__ColumnOrAlias__ColAliasAssignment_0_2 : ( ruleDbObjectName ) ;
    public final void rule__ColumnOrAlias__ColAliasAssignment_0_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5660:1: ( ( ruleDbObjectName ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5661:1: ( ruleDbObjectName )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5661:1: ( ruleDbObjectName )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5662:1: ruleDbObjectName
            {
             before(grammarAccess.getColumnOrAliasAccess().getColAliasDbObjectNameParserRuleCall_0_2_0()); 
            pushFollow(FOLLOW_ruleDbObjectName_in_rule__ColumnOrAlias__ColAliasAssignment_0_211458);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5671:1: rule__ColumnOrAlias__AllColsAssignment_1 : ( RULE_STAR ) ;
    public final void rule__ColumnOrAlias__AllColsAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5675:1: ( ( RULE_STAR ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5676:1: ( RULE_STAR )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5676:1: ( RULE_STAR )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5677:1: RULE_STAR
            {
             before(grammarAccess.getColumnOrAliasAccess().getAllColsSTARTerminalRuleCall_1_0()); 
            match(input,RULE_STAR,FOLLOW_RULE_STAR_in_rule__ColumnOrAlias__AllColsAssignment_111489); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5686:1: rule__ColumnFull__EntriesAssignment_1_1_1 : ( ruleDbObjectName ) ;
    public final void rule__ColumnFull__EntriesAssignment_1_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5690:1: ( ( ruleDbObjectName ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5691:1: ( ruleDbObjectName )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5691:1: ( ruleDbObjectName )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5692:1: ruleDbObjectName
            {
             before(grammarAccess.getColumnFullAccess().getEntriesDbObjectNameParserRuleCall_1_1_1_0()); 
            pushFollow(FOLLOW_ruleDbObjectName_in_rule__ColumnFull__EntriesAssignment_1_1_111520);
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


    // $ANTLR start "rule__Tables__EntriesAssignment_1_1_1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5701:1: rule__Tables__EntriesAssignment_1_1_1 : ( ruleFromTable ) ;
    public final void rule__Tables__EntriesAssignment_1_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5705:1: ( ( ruleFromTable ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5706:1: ( ruleFromTable )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5706:1: ( ruleFromTable )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5707:1: ruleFromTable
            {
             before(grammarAccess.getTablesAccess().getEntriesFromTableParserRuleCall_1_1_1_0()); 
            pushFollow(FOLLOW_ruleFromTable_in_rule__Tables__EntriesAssignment_1_1_111551);
            ruleFromTable();

            state._fsp--;

             after(grammarAccess.getTablesAccess().getEntriesFromTableParserRuleCall_1_1_1_0()); 

            }


            }

        }
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


    // $ANTLR start "rule__FromTable__TableAssignment_0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5716:1: rule__FromTable__TableAssignment_0 : ( ruleTableOrAlias ) ;
    public final void rule__FromTable__TableAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5720:1: ( ( ruleTableOrAlias ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5721:1: ( ruleTableOrAlias )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5721:1: ( ruleTableOrAlias )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5722:1: ruleTableOrAlias
            {
             before(grammarAccess.getFromTableAccess().getTableTableOrAliasParserRuleCall_0_0()); 
            pushFollow(FOLLOW_ruleTableOrAlias_in_rule__FromTable__TableAssignment_011582);
            ruleTableOrAlias();

            state._fsp--;

             after(grammarAccess.getFromTableAccess().getTableTableOrAliasParserRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FromTable__TableAssignment_0"


    // $ANTLR start "rule__FromTable__JoinAssignment_1_0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5731:1: rule__FromTable__JoinAssignment_1_0 : ( ruleJoinType ) ;
    public final void rule__FromTable__JoinAssignment_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5735:1: ( ( ruleJoinType ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5736:1: ( ruleJoinType )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5736:1: ( ruleJoinType )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5737:1: ruleJoinType
            {
             before(grammarAccess.getFromTableAccess().getJoinJoinTypeEnumRuleCall_1_0_0()); 
            pushFollow(FOLLOW_ruleJoinType_in_rule__FromTable__JoinAssignment_1_011613);
            ruleJoinType();

            state._fsp--;

             after(grammarAccess.getFromTableAccess().getJoinJoinTypeEnumRuleCall_1_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FromTable__JoinAssignment_1_0"


    // $ANTLR start "rule__FromTable__OnTableAssignment_1_1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5746:1: rule__FromTable__OnTableAssignment_1_1 : ( ruleTableOrAlias ) ;
    public final void rule__FromTable__OnTableAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5750:1: ( ( ruleTableOrAlias ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5751:1: ( ruleTableOrAlias )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5751:1: ( ruleTableOrAlias )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5752:1: ruleTableOrAlias
            {
             before(grammarAccess.getFromTableAccess().getOnTableTableOrAliasParserRuleCall_1_1_0()); 
            pushFollow(FOLLOW_ruleTableOrAlias_in_rule__FromTable__OnTableAssignment_1_111644);
            ruleTableOrAlias();

            state._fsp--;

             after(grammarAccess.getFromTableAccess().getOnTableTableOrAliasParserRuleCall_1_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FromTable__OnTableAssignment_1_1"


    // $ANTLR start "rule__FromTable__JoinExprAssignment_1_3"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5761:1: rule__FromTable__JoinExprAssignment_1_3 : ( ruleFullExpression ) ;
    public final void rule__FromTable__JoinExprAssignment_1_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5765:1: ( ( ruleFullExpression ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5766:1: ( ruleFullExpression )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5766:1: ( ruleFullExpression )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5767:1: ruleFullExpression
            {
             before(grammarAccess.getFromTableAccess().getJoinExprFullExpressionParserRuleCall_1_3_0()); 
            pushFollow(FOLLOW_ruleFullExpression_in_rule__FromTable__JoinExprAssignment_1_311675);
            ruleFullExpression();

            state._fsp--;

             after(grammarAccess.getFromTableAccess().getJoinExprFullExpressionParserRuleCall_1_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FromTable__JoinExprAssignment_1_3"


    // $ANTLR start "rule__TableOrAlias__TfullAssignment_0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5776:1: rule__TableOrAlias__TfullAssignment_0 : ( ruleTableFull ) ;
    public final void rule__TableOrAlias__TfullAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5780:1: ( ( ruleTableFull ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5781:1: ( ruleTableFull )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5781:1: ( ruleTableFull )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5782:1: ruleTableFull
            {
             before(grammarAccess.getTableOrAliasAccess().getTfullTableFullParserRuleCall_0_0()); 
            pushFollow(FOLLOW_ruleTableFull_in_rule__TableOrAlias__TfullAssignment_011706);
            ruleTableFull();

            state._fsp--;

             after(grammarAccess.getTableOrAliasAccess().getTfullTableFullParserRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TableOrAlias__TfullAssignment_0"


    // $ANTLR start "rule__TableOrAlias__AliasAssignment_1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5791:1: rule__TableOrAlias__AliasAssignment_1 : ( ( KEYWORD_15 ) ) ;
    public final void rule__TableOrAlias__AliasAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5795:1: ( ( ( KEYWORD_15 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5796:1: ( ( KEYWORD_15 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5796:1: ( ( KEYWORD_15 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5797:1: ( KEYWORD_15 )
            {
             before(grammarAccess.getTableOrAliasAccess().getAliasASKeyword_1_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5798:1: ( KEYWORD_15 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5799:1: KEYWORD_15
            {
             before(grammarAccess.getTableOrAliasAccess().getAliasASKeyword_1_0()); 
            match(input,KEYWORD_15,FOLLOW_KEYWORD_15_in_rule__TableOrAlias__AliasAssignment_111742); 
             after(grammarAccess.getTableOrAliasAccess().getAliasASKeyword_1_0()); 

            }

             after(grammarAccess.getTableOrAliasAccess().getAliasASKeyword_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TableOrAlias__AliasAssignment_1"


    // $ANTLR start "rule__TableOrAlias__TblAliasAssignment_2"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5814:1: rule__TableOrAlias__TblAliasAssignment_2 : ( ruleDbObjectName ) ;
    public final void rule__TableOrAlias__TblAliasAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5818:1: ( ( ruleDbObjectName ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5819:1: ( ruleDbObjectName )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5819:1: ( ruleDbObjectName )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5820:1: ruleDbObjectName
            {
             before(grammarAccess.getTableOrAliasAccess().getTblAliasDbObjectNameParserRuleCall_2_0()); 
            pushFollow(FOLLOW_ruleDbObjectName_in_rule__TableOrAlias__TblAliasAssignment_211781);
            ruleDbObjectName();

            state._fsp--;

             after(grammarAccess.getTableOrAliasAccess().getTblAliasDbObjectNameParserRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TableOrAlias__TblAliasAssignment_2"


    // $ANTLR start "rule__TableFull__EntriesAssignment_1_1_1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5829:1: rule__TableFull__EntriesAssignment_1_1_1 : ( ruleDbObjectName ) ;
    public final void rule__TableFull__EntriesAssignment_1_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5833:1: ( ( ruleDbObjectName ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5834:1: ( ruleDbObjectName )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5834:1: ( ruleDbObjectName )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5835:1: ruleDbObjectName
            {
             before(grammarAccess.getTableFullAccess().getEntriesDbObjectNameParserRuleCall_1_1_1_0()); 
            pushFollow(FOLLOW_ruleDbObjectName_in_rule__TableFull__EntriesAssignment_1_1_111812);
            ruleDbObjectName();

            state._fsp--;

             after(grammarAccess.getTableFullAccess().getEntriesDbObjectNameParserRuleCall_1_1_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TableFull__EntriesAssignment_1_1_1"


    // $ANTLR start "rule__DbObjectName__DbnameAssignment"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5844:1: rule__DbObjectName__DbnameAssignment : ( RULE_ID ) ;
    public final void rule__DbObjectName__DbnameAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5848:1: ( ( RULE_ID ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5849:1: ( RULE_ID )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5849:1: ( RULE_ID )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5850:1: RULE_ID
            {
             before(grammarAccess.getDbObjectNameAccess().getDbnameIDTerminalRuleCall_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__DbObjectName__DbnameAssignment11843); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5859:1: rule__OrderByColumns__EntriesAssignment_1_1_1 : ( ruleOrderByColumnFull ) ;
    public final void rule__OrderByColumns__EntriesAssignment_1_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5863:1: ( ( ruleOrderByColumnFull ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5864:1: ( ruleOrderByColumnFull )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5864:1: ( ruleOrderByColumnFull )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5865:1: ruleOrderByColumnFull
            {
             before(grammarAccess.getOrderByColumnsAccess().getEntriesOrderByColumnFullParserRuleCall_1_1_1_0()); 
            pushFollow(FOLLOW_ruleOrderByColumnFull_in_rule__OrderByColumns__EntriesAssignment_1_1_111874);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5874:1: rule__OrderByColumnFull__ColOrderAssignment_0 : ( ruleColumnFull ) ;
    public final void rule__OrderByColumnFull__ColOrderAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5878:1: ( ( ruleColumnFull ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5879:1: ( ruleColumnFull )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5879:1: ( ruleColumnFull )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5880:1: ruleColumnFull
            {
             before(grammarAccess.getOrderByColumnFullAccess().getColOrderColumnFullParserRuleCall_0_0()); 
            pushFollow(FOLLOW_ruleColumnFull_in_rule__OrderByColumnFull__ColOrderAssignment_011905);
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


    // $ANTLR start "rule__GroupByColumns__EntriesAssignment_1_1_1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5889:1: rule__GroupByColumns__EntriesAssignment_1_1_1 : ( ruleColumnFull ) ;
    public final void rule__GroupByColumns__EntriesAssignment_1_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5893:1: ( ( ruleColumnFull ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5894:1: ( ruleColumnFull )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5894:1: ( ruleColumnFull )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5895:1: ruleColumnFull
            {
             before(grammarAccess.getGroupByColumnsAccess().getEntriesColumnFullParserRuleCall_1_1_1_0()); 
            pushFollow(FOLLOW_ruleColumnFull_in_rule__GroupByColumns__EntriesAssignment_1_1_111936);
            ruleColumnFull();

            state._fsp--;

             after(grammarAccess.getGroupByColumnsAccess().getEntriesColumnFullParserRuleCall_1_1_1_0()); 

            }


            }

        }
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


    // $ANTLR start "rule__FullExpression__EntriesAssignment_1_1_1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5904:1: rule__FullExpression__EntriesAssignment_1_1_1 : ( ruleExpressionFragment ) ;
    public final void rule__FullExpression__EntriesAssignment_1_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5908:1: ( ( ruleExpressionFragment ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5909:1: ( ruleExpressionFragment )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5909:1: ( ruleExpressionFragment )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5910:1: ruleExpressionFragment
            {
             before(grammarAccess.getFullExpressionAccess().getEntriesExpressionFragmentParserRuleCall_1_1_1_0()); 
            pushFollow(FOLLOW_ruleExpressionFragment_in_rule__FullExpression__EntriesAssignment_1_1_111967);
            ruleExpressionFragment();

            state._fsp--;

             after(grammarAccess.getFullExpressionAccess().getEntriesExpressionFragmentParserRuleCall_1_1_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FullExpression__EntriesAssignment_1_1_1"


    // $ANTLR start "rule__ExpressionGroup__ExprAssignment_2"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5919:1: rule__ExpressionGroup__ExprAssignment_2 : ( ruleFullExpression ) ;
    public final void rule__ExpressionGroup__ExprAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5923:1: ( ( ruleFullExpression ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5924:1: ( ruleFullExpression )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5924:1: ( ruleFullExpression )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5925:1: ruleFullExpression
            {
             before(grammarAccess.getExpressionGroupAccess().getExprFullExpressionParserRuleCall_2_0()); 
            pushFollow(FOLLOW_ruleFullExpression_in_rule__ExpressionGroup__ExprAssignment_211998);
            ruleFullExpression();

            state._fsp--;

             after(grammarAccess.getExpressionGroupAccess().getExprFullExpressionParserRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ExpressionGroup__ExprAssignment_2"


    // $ANTLR start "rule__Expression__Op1Assignment_0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5934:1: rule__Expression__Op1Assignment_0 : ( ruleOperand ) ;
    public final void rule__Expression__Op1Assignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5938:1: ( ( ruleOperand ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5939:1: ( ruleOperand )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5939:1: ( ruleOperand )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5940:1: ruleOperand
            {
             before(grammarAccess.getExpressionAccess().getOp1OperandParserRuleCall_0_0()); 
            pushFollow(FOLLOW_ruleOperand_in_rule__Expression__Op1Assignment_012029);
            ruleOperand();

            state._fsp--;

             after(grammarAccess.getExpressionAccess().getOp1OperandParserRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Expression__Op1Assignment_0"


    // $ANTLR start "rule__Expression__InAssignment_1_1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5949:1: rule__Expression__InAssignment_1_1 : ( ruleInOperator ) ;
    public final void rule__Expression__InAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5953:1: ( ( ruleInOperator ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5954:1: ( ruleInOperator )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5954:1: ( ruleInOperator )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5955:1: ruleInOperator
            {
             before(grammarAccess.getExpressionAccess().getInInOperatorParserRuleCall_1_1_0()); 
            pushFollow(FOLLOW_ruleInOperator_in_rule__Expression__InAssignment_1_112060);
            ruleInOperator();

            state._fsp--;

             after(grammarAccess.getExpressionAccess().getInInOperatorParserRuleCall_1_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Expression__InAssignment_1_1"


    // $ANTLR start "rule__Expression__BetweenAssignment_1_2"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5964:1: rule__Expression__BetweenAssignment_1_2 : ( ruleBetween ) ;
    public final void rule__Expression__BetweenAssignment_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5968:1: ( ( ruleBetween ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5969:1: ( ruleBetween )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5969:1: ( ruleBetween )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5970:1: ruleBetween
            {
             before(grammarAccess.getExpressionAccess().getBetweenBetweenParserRuleCall_1_2_0()); 
            pushFollow(FOLLOW_ruleBetween_in_rule__Expression__BetweenAssignment_1_212091);
            ruleBetween();

            state._fsp--;

             after(grammarAccess.getExpressionAccess().getBetweenBetweenParserRuleCall_1_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Expression__BetweenAssignment_1_2"


    // $ANTLR start "rule__Expression__LikeAssignment_1_3"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5979:1: rule__Expression__LikeAssignment_1_3 : ( ruleLike ) ;
    public final void rule__Expression__LikeAssignment_1_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5983:1: ( ( ruleLike ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5984:1: ( ruleLike )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5984:1: ( ruleLike )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5985:1: ruleLike
            {
             before(grammarAccess.getExpressionAccess().getLikeLikeParserRuleCall_1_3_0()); 
            pushFollow(FOLLOW_ruleLike_in_rule__Expression__LikeAssignment_1_312122);
            ruleLike();

            state._fsp--;

             after(grammarAccess.getExpressionAccess().getLikeLikeParserRuleCall_1_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Expression__LikeAssignment_1_3"


    // $ANTLR start "rule__Expression__CompAssignment_1_4"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5994:1: rule__Expression__CompAssignment_1_4 : ( ruleComparison ) ;
    public final void rule__Expression__CompAssignment_1_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5998:1: ( ( ruleComparison ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5999:1: ( ruleComparison )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5999:1: ( ruleComparison )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6000:1: ruleComparison
            {
             before(grammarAccess.getExpressionAccess().getCompComparisonParserRuleCall_1_4_0()); 
            pushFollow(FOLLOW_ruleComparison_in_rule__Expression__CompAssignment_1_412153);
            ruleComparison();

            state._fsp--;

             after(grammarAccess.getExpressionAccess().getCompComparisonParserRuleCall_1_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Expression__CompAssignment_1_4"


    // $ANTLR start "rule__Comparison__Op2Assignment_1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6009:1: rule__Comparison__Op2Assignment_1 : ( ruleOperand ) ;
    public final void rule__Comparison__Op2Assignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6013:1: ( ( ruleOperand ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6014:1: ( ruleOperand )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6014:1: ( ruleOperand )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6015:1: ruleOperand
            {
             before(grammarAccess.getComparisonAccess().getOp2OperandParserRuleCall_1_0()); 
            pushFollow(FOLLOW_ruleOperand_in_rule__Comparison__Op2Assignment_112184);
            ruleOperand();

            state._fsp--;

             after(grammarAccess.getComparisonAccess().getOp2OperandParserRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Comparison__Op2Assignment_1"


    // $ANTLR start "rule__Between__Op1Assignment_1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6024:1: rule__Between__Op1Assignment_1 : ( ruleOperand ) ;
    public final void rule__Between__Op1Assignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6028:1: ( ( ruleOperand ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6029:1: ( ruleOperand )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6029:1: ( ruleOperand )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6030:1: ruleOperand
            {
             before(grammarAccess.getBetweenAccess().getOp1OperandParserRuleCall_1_0()); 
            pushFollow(FOLLOW_ruleOperand_in_rule__Between__Op1Assignment_112215);
            ruleOperand();

            state._fsp--;

             after(grammarAccess.getBetweenAccess().getOp1OperandParserRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Between__Op1Assignment_1"


    // $ANTLR start "rule__Between__Op2Assignment_3"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6039:1: rule__Between__Op2Assignment_3 : ( ruleOperand ) ;
    public final void rule__Between__Op2Assignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6043:1: ( ( ruleOperand ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6044:1: ( ruleOperand )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6044:1: ( ruleOperand )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6045:1: ruleOperand
            {
             before(grammarAccess.getBetweenAccess().getOp2OperandParserRuleCall_3_0()); 
            pushFollow(FOLLOW_ruleOperand_in_rule__Between__Op2Assignment_312246);
            ruleOperand();

            state._fsp--;

             after(grammarAccess.getBetweenAccess().getOp2OperandParserRuleCall_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Between__Op2Assignment_3"


    // $ANTLR start "rule__InOperator__SubqueryAssignment_2_0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6054:1: rule__InOperator__SubqueryAssignment_2_0 : ( ruleSubQueryOperand ) ;
    public final void rule__InOperator__SubqueryAssignment_2_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6058:1: ( ( ruleSubQueryOperand ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6059:1: ( ruleSubQueryOperand )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6059:1: ( ruleSubQueryOperand )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6060:1: ruleSubQueryOperand
            {
             before(grammarAccess.getInOperatorAccess().getSubquerySubQueryOperandParserRuleCall_2_0_0()); 
            pushFollow(FOLLOW_ruleSubQueryOperand_in_rule__InOperator__SubqueryAssignment_2_012277);
            ruleSubQueryOperand();

            state._fsp--;

             after(grammarAccess.getInOperatorAccess().getSubquerySubQueryOperandParserRuleCall_2_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InOperator__SubqueryAssignment_2_0"


    // $ANTLR start "rule__InOperator__EntriesAssignment_2_1_1_1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6069:1: rule__InOperator__EntriesAssignment_2_1_1_1 : ( ruleXOperandFragment ) ;
    public final void rule__InOperator__EntriesAssignment_2_1_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6073:1: ( ( ruleXOperandFragment ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6074:1: ( ruleXOperandFragment )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6074:1: ( ruleXOperandFragment )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6075:1: ruleXOperandFragment
            {
             before(grammarAccess.getInOperatorAccess().getEntriesXOperandFragmentParserRuleCall_2_1_1_1_0()); 
            pushFollow(FOLLOW_ruleXOperandFragment_in_rule__InOperator__EntriesAssignment_2_1_1_112308);
            ruleXOperandFragment();

            state._fsp--;

             after(grammarAccess.getInOperatorAccess().getEntriesXOperandFragmentParserRuleCall_2_1_1_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InOperator__EntriesAssignment_2_1_1_1"


    // $ANTLR start "rule__Operand__EntriesAssignment_1_1_1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6084:1: rule__Operand__EntriesAssignment_1_1_1 : ( ruleOperandFragment ) ;
    public final void rule__Operand__EntriesAssignment_1_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6088:1: ( ( ruleOperandFragment ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6089:1: ( ruleOperandFragment )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6089:1: ( ruleOperandFragment )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6090:1: ruleOperandFragment
            {
             before(grammarAccess.getOperandAccess().getEntriesOperandFragmentParserRuleCall_1_1_1_0()); 
            pushFollow(FOLLOW_ruleOperandFragment_in_rule__Operand__EntriesAssignment_1_1_112339);
            ruleOperandFragment();

            state._fsp--;

             after(grammarAccess.getOperandAccess().getEntriesOperandFragmentParserRuleCall_1_1_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operand__EntriesAssignment_1_1_1"


    // $ANTLR start "rule__XOperandFragment__ScalarAssignment_2"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6100:1: rule__XOperandFragment__ScalarAssignment_2 : ( ruleScalarOperand ) ;
    public final void rule__XOperandFragment__ScalarAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6104:1: ( ( ruleScalarOperand ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6105:1: ( ruleScalarOperand )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6105:1: ( ruleScalarOperand )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6106:1: ruleScalarOperand
            {
             before(grammarAccess.getXOperandFragmentAccess().getScalarScalarOperandParserRuleCall_2_0()); 
            pushFollow(FOLLOW_ruleScalarOperand_in_rule__XOperandFragment__ScalarAssignment_212371);
            ruleScalarOperand();

            state._fsp--;

             after(grammarAccess.getXOperandFragmentAccess().getScalarScalarOperandParserRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XOperandFragment__ScalarAssignment_2"


    // $ANTLR start "rule__SubQueryOperand__SelAssignment_2"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6115:1: rule__SubQueryOperand__SelAssignment_2 : ( ruleSelect ) ;
    public final void rule__SubQueryOperand__SelAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6119:1: ( ( ruleSelect ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6120:1: ( ruleSelect )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6120:1: ( ruleSelect )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6121:1: ruleSelect
            {
             before(grammarAccess.getSubQueryOperandAccess().getSelSelectParserRuleCall_2_0()); 
            pushFollow(FOLLOW_ruleSelect_in_rule__SubQueryOperand__SelAssignment_212402);
            ruleSelect();

            state._fsp--;

             after(grammarAccess.getSubQueryOperandAccess().getSelSelectParserRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SubQueryOperand__SelAssignment_2"

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
    public static final BitSet FOLLOW_ruleTables_in_entryRuleTables374 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleTables381 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Tables__Group__0_in_ruleTables411 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFromTable_in_entryRuleFromTable438 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleFromTable445 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FromTable__Group__0_in_ruleFromTable475 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTableOrAlias_in_entryRuleTableOrAlias502 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleTableOrAlias509 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TableOrAlias__Group__0_in_ruleTableOrAlias539 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTableFull_in_entryRuleTableFull566 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleTableFull573 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TableFull__Group__0_in_ruleTableFull603 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDbObjectName_in_entryRuleDbObjectName630 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleDbObjectName637 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__DbObjectName__DbnameAssignment_in_ruleDbObjectName667 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOrderByColumns_in_entryRuleOrderByColumns694 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOrderByColumns701 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OrderByColumns__Group__0_in_ruleOrderByColumns731 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOrderByColumnFull_in_entryRuleOrderByColumnFull758 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOrderByColumnFull765 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OrderByColumnFull__Group__0_in_ruleOrderByColumnFull795 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleGroupByColumns_in_entryRuleGroupByColumns822 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleGroupByColumns829 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__GroupByColumns__Group__0_in_ruleGroupByColumns859 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFullExpression_in_entryRuleFullExpression886 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleFullExpression893 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FullExpression__Group__0_in_ruleFullExpression923 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpressionFragment_in_entryRuleExpressionFragment950 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpressionFragment957 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ExpressionFragment__Alternatives_in_ruleExpressionFragment987 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpressionGroup_in_entryRuleExpressionGroup1014 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpressionGroup1021 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ExpressionGroup__Group__0_in_ruleExpressionGroup1051 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXExpression_in_entryRuleXExpression1078 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleXExpression1085 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__XExpression__Group__0_in_ruleXExpression1115 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression_in_entryRuleExpression1142 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpression1149 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Expression__Group__0_in_ruleExpression1179 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleComparison_in_entryRuleComparison1206 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleComparison1213 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Comparison__Group__0_in_ruleComparison1243 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLike_in_entryRuleLike1270 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleLike1277 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Like__Group__0_in_ruleLike1307 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBetween_in_entryRuleBetween1334 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleBetween1341 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Between__Group__0_in_ruleBetween1371 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInOperator_in_entryRuleInOperator1398 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleInOperator1405 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__InOperator__Group__0_in_ruleInOperator1435 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperand_in_entryRuleOperand1462 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOperand1469 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Operand__Group__0_in_ruleOperand1499 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperandFragment_in_entryRuleOperandFragment1526 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOperandFragment1533 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OperandFragment__Alternatives_in_ruleOperandFragment1563 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXOperandFragment_in_entryRuleXOperandFragment1592 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleXOperandFragment1599 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__XOperandFragment__Alternatives_in_ruleXOperandFragment1629 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleParameterOperand_in_entryRuleParameterOperand1656 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleParameterOperand1663 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ParameterOperand__Group__0_in_ruleParameterOperand1693 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExclamationParameterOperand_in_entryRuleExclamationParameterOperand1720 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExclamationParameterOperand1727 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ExclamationParameterOperand__Group__0_in_ruleExclamationParameterOperand1757 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnOperand_in_entryRuleColumnOperand1784 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleColumnOperand1791 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnFull_in_ruleColumnOperand1821 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSubQueryOperand_in_entryRuleSubQueryOperand1847 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSubQueryOperand1854 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SubQueryOperand__Group__0_in_ruleSubQueryOperand1884 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleScalarOperand_in_entryRuleScalarOperand1911 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleScalarOperand1918 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ScalarOperand__Alternatives_in_ruleScalarOperand1948 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleStringOperand_in_entryRuleStringOperand1975 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleStringOperand1982 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleStringOperand2012 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__JoinType__Alternatives_in_ruleJoinType2048 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ColumnOrAlias__Group_0__0_in_rule__ColumnOrAlias__Alternatives2083 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ColumnOrAlias__AllColsAssignment_1_in_rule__ColumnOrAlias__Alternatives2101 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_22_in_rule__OrderByColumnFull__Alternatives_12135 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_24_in_rule__OrderByColumnFull__Alternatives_12155 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_21_in_rule__FullExpression__Alternatives_1_1_02190 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_17_in_rule__FullExpression__Alternatives_1_1_02210 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpressionGroup_in_rule__ExpressionFragment__Alternatives2244 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression_in_rule__ExpressionFragment__Alternatives2261 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXExpression_in_rule__ExpressionFragment__Alternatives2278 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Expression__Alternatives_1_0_in_rule__Expression__Alternatives_12310 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Expression__InAssignment_1_1_in_rule__Expression__Alternatives_12328 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Expression__BetweenAssignment_1_2_in_rule__Expression__Alternatives_12346 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Expression__LikeAssignment_1_3_in_rule__Expression__Alternatives_12364 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Expression__CompAssignment_1_4_in_rule__Expression__Alternatives_12382 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_33_in_rule__Expression__Alternatives_1_02416 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_41_in_rule__Expression__Alternatives_1_02436 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_10_in_rule__Comparison__Alternatives_02471 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_14_in_rule__Comparison__Alternatives_02491 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_8_in_rule__Comparison__Alternatives_02511 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_12_in_rule__Comparison__Alternatives_02531 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_9_in_rule__Comparison__Alternatives_02551 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_13_in_rule__Comparison__Alternatives_02571 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_27_in_rule__Like__Alternatives_02606 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_37_in_rule__Like__Alternatives_02626 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_36_in_rule__InOperator__Alternatives_12661 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_26_in_rule__InOperator__Alternatives_12681 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__InOperator__SubqueryAssignment_2_0_in_rule__InOperator__Alternatives_22715 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__InOperator__Group_2_1__0_in_rule__InOperator__Alternatives_22733 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_3_in_rule__Operand__Alternatives_1_1_02768 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_5_in_rule__Operand__Alternatives_1_1_02788 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STAR_in_rule__Operand__Alternatives_1_1_02807 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_7_in_rule__Operand__Alternatives_1_1_02825 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_18_in_rule__Operand__Alternatives_1_1_02845 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnOperand_in_rule__OperandFragment__Alternatives2879 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXOperandFragment_in_rule__OperandFragment__Alternatives2896 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSubQueryOperand_in_rule__OperandFragment__Alternatives2913 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleParameterOperand_in_rule__XOperandFragment__Alternatives2945 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExclamationParameterOperand_in_rule__XOperandFragment__Alternatives2962 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__XOperandFragment__ScalarAssignment_2_in_rule__XOperandFragment__Alternatives2979 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_rule__ScalarOperand__Alternatives3012 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleStringOperand_in_rule__ScalarOperand__Alternatives3029 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_SIGNED_DOUBLE_in_rule__ScalarOperand__Alternatives3046 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_DATE_in_rule__ScalarOperand__Alternatives3063 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_TIME_in_rule__ScalarOperand__Alternatives3080 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_TIMESTAMP_in_rule__ScalarOperand__Alternatives3097 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_40_in_rule__JoinType__Alternatives3130 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_43_in_rule__JoinType__Alternatives3150 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_44_in_rule__JoinType__Alternatives3170 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_42_in_rule__JoinType__Alternatives3190 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_39_in_rule__JoinType__Alternatives3210 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Model__Group__0__Impl_in_rule__Model__Group__03242 = new BitSet(new long[]{0x0000000000084000L});
    public static final BitSet FOLLOW_rule__Model__Group__1_in_rule__Model__Group__03245 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSelect_in_rule__Model__Group__0__Impl3272 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Model__Group__1__Impl_in_rule__Model__Group__13301 = new BitSet(new long[]{0x0000000000084000L});
    public static final BitSet FOLLOW_rule__Model__Group__2_in_rule__Model__Group__13304 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Model__Group_1__0_in_rule__Model__Group__1__Impl3331 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Model__Group__2__Impl_in_rule__Model__Group__23362 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Model__Group_2__0_in_rule__Model__Group__2__Impl3389 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Model__Group_1__0__Impl_in_rule__Model__Group_1__03426 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_rule__Model__Group_1__1_in_rule__Model__Group_1__03429 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Model__Group_1__1__Impl_in_rule__Model__Group_1__13487 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Model__Group_1_1__0_in_rule__Model__Group_1__1__Impl3516 = new BitSet(new long[]{0x0000000000080002L});
    public static final BitSet FOLLOW_rule__Model__Group_1_1__0_in_rule__Model__Group_1__1__Impl3528 = new BitSet(new long[]{0x0000000000080002L});
    public static final BitSet FOLLOW_rule__Model__Group_1_1__0__Impl_in_rule__Model__Group_1_1__03565 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_rule__Model__Group_1_1__1_in_rule__Model__Group_1_1__03568 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_28_in_rule__Model__Group_1_1__0__Impl3596 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Model__Group_1_1__1__Impl_in_rule__Model__Group_1_1__13627 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Model__EntriesAssignment_1_1_1_in_rule__Model__Group_1_1__1__Impl3654 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Model__Group_2__0__Impl_in_rule__Model__Group_2__03688 = new BitSet(new long[]{0x0080000000000000L});
    public static final BitSet FOLLOW_rule__Model__Group_2__1_in_rule__Model__Group_2__03691 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_38_in_rule__Model__Group_2__0__Impl3719 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Model__Group_2__1__Impl_in_rule__Model__Group_2__13750 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Model__OrderByEntryAssignment_2_1_in_rule__Model__Group_2__1__Impl3777 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Select__Group__0__Impl_in_rule__Select__Group__03811 = new BitSet(new long[]{0x0081000000000400L});
    public static final BitSet FOLLOW_rule__Select__Group__1_in_rule__Select__Group__03814 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Select__SelectAssignment_0_in_rule__Select__Group__0__Impl3841 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Select__Group__1__Impl_in_rule__Select__Group__13871 = new BitSet(new long[]{0x0081000000000400L});
    public static final BitSet FOLLOW_rule__Select__Group__2_in_rule__Select__Group__13874 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_34_in_rule__Select__Group__1__Impl3903 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Select__Group__2__Impl_in_rule__Select__Group__23936 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_rule__Select__Group__3_in_rule__Select__Group__23939 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Select__ColsAssignment_2_in_rule__Select__Group__2__Impl3966 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Select__Group__3__Impl_in_rule__Select__Group__33996 = new BitSet(new long[]{0x0080000000000000L});
    public static final BitSet FOLLOW_rule__Select__Group__4_in_rule__Select__Group__33999 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_25_in_rule__Select__Group__3__Impl4027 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Select__Group__4__Impl_in_rule__Select__Group__44058 = new BitSet(new long[]{0x0000000000120800L});
    public static final BitSet FOLLOW_rule__Select__Group__5_in_rule__Select__Group__44061 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Select__TblAssignment_4_in_rule__Select__Group__4__Impl4088 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Select__Group__5__Impl_in_rule__Select__Group__54118 = new BitSet(new long[]{0x0000000000120800L});
    public static final BitSet FOLLOW_rule__Select__Group__6_in_rule__Select__Group__54121 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Select__Group_5__0_in_rule__Select__Group__5__Impl4148 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Select__Group__6__Impl_in_rule__Select__Group__64179 = new BitSet(new long[]{0x0000000000120800L});
    public static final BitSet FOLLOW_rule__Select__Group__7_in_rule__Select__Group__64182 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Select__Group_6__0_in_rule__Select__Group__6__Impl4209 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Select__Group__7__Impl_in_rule__Select__Group__74240 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Select__Group_7__0_in_rule__Select__Group__7__Impl4267 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Select__Group_5__0__Impl_in_rule__Select__Group_5__04314 = new BitSet(new long[]{0x01BE00200C200000L});
    public static final BitSet FOLLOW_rule__Select__Group_5__1_in_rule__Select__Group_5__04317 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_29_in_rule__Select__Group_5__0__Impl4345 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Select__Group_5__1__Impl_in_rule__Select__Group_5__14376 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Select__WhereExpressionAssignment_5_1_in_rule__Select__Group_5__1__Impl4403 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Select__Group_6__0__Impl_in_rule__Select__Group_6__04437 = new BitSet(new long[]{0x0080000000000000L});
    public static final BitSet FOLLOW_rule__Select__Group_6__1_in_rule__Select__Group_6__04440 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_35_in_rule__Select__Group_6__0__Impl4468 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Select__Group_6__1__Impl_in_rule__Select__Group_6__14499 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Select__GroupByEntryAssignment_6_1_in_rule__Select__Group_6__1__Impl4526 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Select__Group_7__0__Impl_in_rule__Select__Group_7__04560 = new BitSet(new long[]{0x01BE00200C200000L});
    public static final BitSet FOLLOW_rule__Select__Group_7__1_in_rule__Select__Group_7__04563 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_30_in_rule__Select__Group_7__0__Impl4591 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Select__Group_7__1__Impl_in_rule__Select__Group_7__14622 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Select__HavingEntryAssignment_7_1_in_rule__Select__Group_7__1__Impl4649 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Columns__Group__0__Impl_in_rule__Columns__Group__04683 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_rule__Columns__Group__1_in_rule__Columns__Group__04686 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnOrAlias_in_rule__Columns__Group__0__Impl4713 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Columns__Group__1__Impl_in_rule__Columns__Group__14742 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Columns__Group_1__0_in_rule__Columns__Group__1__Impl4769 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Columns__Group_1__0__Impl_in_rule__Columns__Group_1__04804 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_rule__Columns__Group_1__1_in_rule__Columns__Group_1__04807 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Columns__Group_1__1__Impl_in_rule__Columns__Group_1__14865 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Columns__Group_1_1__0_in_rule__Columns__Group_1__1__Impl4894 = new BitSet(new long[]{0x0000010000000002L});
    public static final BitSet FOLLOW_rule__Columns__Group_1_1__0_in_rule__Columns__Group_1__1__Impl4906 = new BitSet(new long[]{0x0000010000000002L});
    public static final BitSet FOLLOW_rule__Columns__Group_1_1__0__Impl_in_rule__Columns__Group_1_1__04943 = new BitSet(new long[]{0x0081000000000400L});
    public static final BitSet FOLLOW_rule__Columns__Group_1_1__1_in_rule__Columns__Group_1_1__04946 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_4_in_rule__Columns__Group_1_1__0__Impl4974 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Columns__Group_1_1__1__Impl_in_rule__Columns__Group_1_1__15005 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Columns__EntriesAssignment_1_1_1_in_rule__Columns__Group_1_1__1__Impl5032 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ColumnOrAlias__Group_0__0__Impl_in_rule__ColumnOrAlias__Group_0__05066 = new BitSet(new long[]{0x0080000200000000L});
    public static final BitSet FOLLOW_rule__ColumnOrAlias__Group_0__1_in_rule__ColumnOrAlias__Group_0__05069 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnFull_in_rule__ColumnOrAlias__Group_0__0__Impl5096 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ColumnOrAlias__Group_0__1__Impl_in_rule__ColumnOrAlias__Group_0__15125 = new BitSet(new long[]{0x0080000200000000L});
    public static final BitSet FOLLOW_rule__ColumnOrAlias__Group_0__2_in_rule__ColumnOrAlias__Group_0__15128 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_15_in_rule__ColumnOrAlias__Group_0__1__Impl5157 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ColumnOrAlias__Group_0__2__Impl_in_rule__ColumnOrAlias__Group_0__25190 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ColumnOrAlias__ColAliasAssignment_0_2_in_rule__ColumnOrAlias__Group_0__2__Impl5217 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ColumnFull__Group__0__Impl_in_rule__ColumnFull__Group__05254 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_rule__ColumnFull__Group__1_in_rule__ColumnFull__Group__05257 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDbObjectName_in_rule__ColumnFull__Group__0__Impl5284 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ColumnFull__Group__1__Impl_in_rule__ColumnFull__Group__15313 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ColumnFull__Group_1__0_in_rule__ColumnFull__Group__1__Impl5340 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ColumnFull__Group_1__0__Impl_in_rule__ColumnFull__Group_1__05375 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_rule__ColumnFull__Group_1__1_in_rule__ColumnFull__Group_1__05378 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ColumnFull__Group_1__1__Impl_in_rule__ColumnFull__Group_1__15436 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ColumnFull__Group_1_1__0_in_rule__ColumnFull__Group_1__1__Impl5465 = new BitSet(new long[]{0x0000040000000002L});
    public static final BitSet FOLLOW_rule__ColumnFull__Group_1_1__0_in_rule__ColumnFull__Group_1__1__Impl5477 = new BitSet(new long[]{0x0000040000000002L});
    public static final BitSet FOLLOW_rule__ColumnFull__Group_1_1__0__Impl_in_rule__ColumnFull__Group_1_1__05514 = new BitSet(new long[]{0x0080000000000000L});
    public static final BitSet FOLLOW_rule__ColumnFull__Group_1_1__1_in_rule__ColumnFull__Group_1_1__05517 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_6_in_rule__ColumnFull__Group_1_1__0__Impl5545 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ColumnFull__Group_1_1__1__Impl_in_rule__ColumnFull__Group_1_1__15576 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ColumnFull__EntriesAssignment_1_1_1_in_rule__ColumnFull__Group_1_1__1__Impl5603 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Tables__Group__0__Impl_in_rule__Tables__Group__05637 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_rule__Tables__Group__1_in_rule__Tables__Group__05640 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFromTable_in_rule__Tables__Group__0__Impl5667 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Tables__Group__1__Impl_in_rule__Tables__Group__15696 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Tables__Group_1__0_in_rule__Tables__Group__1__Impl5723 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Tables__Group_1__0__Impl_in_rule__Tables__Group_1__05758 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_rule__Tables__Group_1__1_in_rule__Tables__Group_1__05761 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Tables__Group_1__1__Impl_in_rule__Tables__Group_1__15819 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Tables__Group_1_1__0_in_rule__Tables__Group_1__1__Impl5848 = new BitSet(new long[]{0x0000010000000002L});
    public static final BitSet FOLLOW_rule__Tables__Group_1_1__0_in_rule__Tables__Group_1__1__Impl5860 = new BitSet(new long[]{0x0000010000000002L});
    public static final BitSet FOLLOW_rule__Tables__Group_1_1__0__Impl_in_rule__Tables__Group_1_1__05897 = new BitSet(new long[]{0x0080000000000000L});
    public static final BitSet FOLLOW_rule__Tables__Group_1_1__1_in_rule__Tables__Group_1_1__05900 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_4_in_rule__Tables__Group_1_1__0__Impl5928 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Tables__Group_1_1__1__Impl_in_rule__Tables__Group_1_1__15959 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Tables__EntriesAssignment_1_1_1_in_rule__Tables__Group_1_1__1__Impl5986 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FromTable__Group__0__Impl_in_rule__FromTable__Group__06020 = new BitSet(new long[]{0x0000000000000370L});
    public static final BitSet FOLLOW_rule__FromTable__Group__1_in_rule__FromTable__Group__06023 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FromTable__TableAssignment_0_in_rule__FromTable__Group__0__Impl6050 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FromTable__Group__1__Impl_in_rule__FromTable__Group__16080 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FromTable__Group_1__0_in_rule__FromTable__Group__1__Impl6107 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FromTable__Group_1__0__Impl_in_rule__FromTable__Group_1__06142 = new BitSet(new long[]{0x0080000000000000L});
    public static final BitSet FOLLOW_rule__FromTable__Group_1__1_in_rule__FromTable__Group_1__06145 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FromTable__JoinAssignment_1_0_in_rule__FromTable__Group_1__0__Impl6172 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FromTable__Group_1__1__Impl_in_rule__FromTable__Group_1__16202 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_rule__FromTable__Group_1__2_in_rule__FromTable__Group_1__16205 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FromTable__OnTableAssignment_1_1_in_rule__FromTable__Group_1__1__Impl6232 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FromTable__Group_1__2__Impl_in_rule__FromTable__Group_1__26262 = new BitSet(new long[]{0x01BE00200C200000L});
    public static final BitSet FOLLOW_rule__FromTable__Group_1__3_in_rule__FromTable__Group_1__26265 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_16_in_rule__FromTable__Group_1__2__Impl6293 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FromTable__Group_1__3__Impl_in_rule__FromTable__Group_1__36324 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FromTable__JoinExprAssignment_1_3_in_rule__FromTable__Group_1__3__Impl6351 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TableOrAlias__Group__0__Impl_in_rule__TableOrAlias__Group__06389 = new BitSet(new long[]{0x0080000200000000L});
    public static final BitSet FOLLOW_rule__TableOrAlias__Group__1_in_rule__TableOrAlias__Group__06392 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TableOrAlias__TfullAssignment_0_in_rule__TableOrAlias__Group__0__Impl6419 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TableOrAlias__Group__1__Impl_in_rule__TableOrAlias__Group__16449 = new BitSet(new long[]{0x0080000200000000L});
    public static final BitSet FOLLOW_rule__TableOrAlias__Group__2_in_rule__TableOrAlias__Group__16452 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TableOrAlias__AliasAssignment_1_in_rule__TableOrAlias__Group__1__Impl6479 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TableOrAlias__Group__2__Impl_in_rule__TableOrAlias__Group__26510 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TableOrAlias__TblAliasAssignment_2_in_rule__TableOrAlias__Group__2__Impl6537 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TableFull__Group__0__Impl_in_rule__TableFull__Group__06574 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_rule__TableFull__Group__1_in_rule__TableFull__Group__06577 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDbObjectName_in_rule__TableFull__Group__0__Impl6604 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TableFull__Group__1__Impl_in_rule__TableFull__Group__16633 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TableFull__Group_1__0_in_rule__TableFull__Group__1__Impl6660 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TableFull__Group_1__0__Impl_in_rule__TableFull__Group_1__06695 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_rule__TableFull__Group_1__1_in_rule__TableFull__Group_1__06698 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TableFull__Group_1__1__Impl_in_rule__TableFull__Group_1__16756 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TableFull__Group_1_1__0_in_rule__TableFull__Group_1__1__Impl6785 = new BitSet(new long[]{0x0000040000000002L});
    public static final BitSet FOLLOW_rule__TableFull__Group_1_1__0_in_rule__TableFull__Group_1__1__Impl6797 = new BitSet(new long[]{0x0000040000000002L});
    public static final BitSet FOLLOW_rule__TableFull__Group_1_1__0__Impl_in_rule__TableFull__Group_1_1__06834 = new BitSet(new long[]{0x0080000000000000L});
    public static final BitSet FOLLOW_rule__TableFull__Group_1_1__1_in_rule__TableFull__Group_1_1__06837 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_6_in_rule__TableFull__Group_1_1__0__Impl6865 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TableFull__Group_1_1__1__Impl_in_rule__TableFull__Group_1_1__16896 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TableFull__EntriesAssignment_1_1_1_in_rule__TableFull__Group_1_1__1__Impl6923 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OrderByColumns__Group__0__Impl_in_rule__OrderByColumns__Group__06957 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_rule__OrderByColumns__Group__1_in_rule__OrderByColumns__Group__06960 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOrderByColumnFull_in_rule__OrderByColumns__Group__0__Impl6987 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OrderByColumns__Group__1__Impl_in_rule__OrderByColumns__Group__17016 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OrderByColumns__Group_1__0_in_rule__OrderByColumns__Group__1__Impl7043 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OrderByColumns__Group_1__0__Impl_in_rule__OrderByColumns__Group_1__07078 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_rule__OrderByColumns__Group_1__1_in_rule__OrderByColumns__Group_1__07081 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OrderByColumns__Group_1__1__Impl_in_rule__OrderByColumns__Group_1__17139 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OrderByColumns__Group_1_1__0_in_rule__OrderByColumns__Group_1__1__Impl7168 = new BitSet(new long[]{0x0000010000000002L});
    public static final BitSet FOLLOW_rule__OrderByColumns__Group_1_1__0_in_rule__OrderByColumns__Group_1__1__Impl7180 = new BitSet(new long[]{0x0000010000000002L});
    public static final BitSet FOLLOW_rule__OrderByColumns__Group_1_1__0__Impl_in_rule__OrderByColumns__Group_1_1__07217 = new BitSet(new long[]{0x0080000000000000L});
    public static final BitSet FOLLOW_rule__OrderByColumns__Group_1_1__1_in_rule__OrderByColumns__Group_1_1__07220 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_4_in_rule__OrderByColumns__Group_1_1__0__Impl7248 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OrderByColumns__Group_1_1__1__Impl_in_rule__OrderByColumns__Group_1_1__17279 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OrderByColumns__EntriesAssignment_1_1_1_in_rule__OrderByColumns__Group_1_1__1__Impl7306 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OrderByColumnFull__Group__0__Impl_in_rule__OrderByColumnFull__Group__07340 = new BitSet(new long[]{0x0000000020400000L});
    public static final BitSet FOLLOW_rule__OrderByColumnFull__Group__1_in_rule__OrderByColumnFull__Group__07343 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OrderByColumnFull__ColOrderAssignment_0_in_rule__OrderByColumnFull__Group__0__Impl7370 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OrderByColumnFull__Group__1__Impl_in_rule__OrderByColumnFull__Group__17400 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OrderByColumnFull__Alternatives_1_in_rule__OrderByColumnFull__Group__1__Impl7427 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__GroupByColumns__Group__0__Impl_in_rule__GroupByColumns__Group__07462 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_rule__GroupByColumns__Group__1_in_rule__GroupByColumns__Group__07465 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnFull_in_rule__GroupByColumns__Group__0__Impl7492 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__GroupByColumns__Group__1__Impl_in_rule__GroupByColumns__Group__17521 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__GroupByColumns__Group_1__0_in_rule__GroupByColumns__Group__1__Impl7548 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__GroupByColumns__Group_1__0__Impl_in_rule__GroupByColumns__Group_1__07583 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_rule__GroupByColumns__Group_1__1_in_rule__GroupByColumns__Group_1__07586 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__GroupByColumns__Group_1__1__Impl_in_rule__GroupByColumns__Group_1__17644 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__GroupByColumns__Group_1_1__0_in_rule__GroupByColumns__Group_1__1__Impl7673 = new BitSet(new long[]{0x0000010000000002L});
    public static final BitSet FOLLOW_rule__GroupByColumns__Group_1_1__0_in_rule__GroupByColumns__Group_1__1__Impl7685 = new BitSet(new long[]{0x0000010000000002L});
    public static final BitSet FOLLOW_rule__GroupByColumns__Group_1_1__0__Impl_in_rule__GroupByColumns__Group_1_1__07722 = new BitSet(new long[]{0x0080000000000000L});
    public static final BitSet FOLLOW_rule__GroupByColumns__Group_1_1__1_in_rule__GroupByColumns__Group_1_1__07725 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_4_in_rule__GroupByColumns__Group_1_1__0__Impl7753 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__GroupByColumns__Group_1_1__1__Impl_in_rule__GroupByColumns__Group_1_1__17784 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__GroupByColumns__EntriesAssignment_1_1_1_in_rule__GroupByColumns__Group_1_1__1__Impl7811 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FullExpression__Group__0__Impl_in_rule__FullExpression__Group__07845 = new BitSet(new long[]{0x0000000810000000L});
    public static final BitSet FOLLOW_rule__FullExpression__Group__1_in_rule__FullExpression__Group__07848 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpressionFragment_in_rule__FullExpression__Group__0__Impl7875 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FullExpression__Group__1__Impl_in_rule__FullExpression__Group__17904 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FullExpression__Group_1__0_in_rule__FullExpression__Group__1__Impl7931 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FullExpression__Group_1__0__Impl_in_rule__FullExpression__Group_1__07966 = new BitSet(new long[]{0x0000000810000000L});
    public static final BitSet FOLLOW_rule__FullExpression__Group_1__1_in_rule__FullExpression__Group_1__07969 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FullExpression__Group_1__1__Impl_in_rule__FullExpression__Group_1__18027 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FullExpression__Group_1_1__0_in_rule__FullExpression__Group_1__1__Impl8056 = new BitSet(new long[]{0x0000000810000002L});
    public static final BitSet FOLLOW_rule__FullExpression__Group_1_1__0_in_rule__FullExpression__Group_1__1__Impl8068 = new BitSet(new long[]{0x0000000810000002L});
    public static final BitSet FOLLOW_rule__FullExpression__Group_1_1__0__Impl_in_rule__FullExpression__Group_1_1__08105 = new BitSet(new long[]{0x01BE00200C200000L});
    public static final BitSet FOLLOW_rule__FullExpression__Group_1_1__1_in_rule__FullExpression__Group_1_1__08108 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FullExpression__Alternatives_1_1_0_in_rule__FullExpression__Group_1_1__0__Impl8135 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FullExpression__Group_1_1__1__Impl_in_rule__FullExpression__Group_1_1__18165 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FullExpression__EntriesAssignment_1_1_1_in_rule__FullExpression__Group_1_1__1__Impl8192 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ExpressionGroup__Group__0__Impl_in_rule__ExpressionGroup__Group__08226 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_rule__ExpressionGroup__Group__1_in_rule__ExpressionGroup__Group__08229 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ExpressionGroup__Group__1__Impl_in_rule__ExpressionGroup__Group__18287 = new BitSet(new long[]{0x01BE00200C200000L});
    public static final BitSet FOLLOW_rule__ExpressionGroup__Group__2_in_rule__ExpressionGroup__Group__18290 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_1_in_rule__ExpressionGroup__Group__1__Impl8318 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ExpressionGroup__Group__2__Impl_in_rule__ExpressionGroup__Group__28349 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_rule__ExpressionGroup__Group__3_in_rule__ExpressionGroup__Group__28352 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ExpressionGroup__ExprAssignment_2_in_rule__ExpressionGroup__Group__2__Impl8379 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ExpressionGroup__Group__3__Impl_in_rule__ExpressionGroup__Group__38409 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_2_in_rule__ExpressionGroup__Group__3__Impl8437 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__XExpression__Group__0__Impl_in_rule__XExpression__Group__08476 = new BitSet(new long[]{0x01BE00200C200000L});
    public static final BitSet FOLLOW_rule__XExpression__Group__1_in_rule__XExpression__Group__08479 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__XExpression__Group__1__Impl_in_rule__XExpression__Group__18537 = new BitSet(new long[]{0x0080000000000000L});
    public static final BitSet FOLLOW_rule__XExpression__Group__2_in_rule__XExpression__Group__18540 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_20_in_rule__XExpression__Group__1__Impl8568 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__XExpression__Group__2__Impl_in_rule__XExpression__Group__28599 = new BitSet(new long[]{0x0000810000000000L});
    public static final BitSet FOLLOW_rule__XExpression__Group__3_in_rule__XExpression__Group__28602 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__XExpression__Group__2__Impl8629 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__XExpression__Group__3__Impl_in_rule__XExpression__Group__38658 = new BitSet(new long[]{0x0000810000000000L});
    public static final BitSet FOLLOW_rule__XExpression__Group__4_in_rule__XExpression__Group__38661 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__XExpression__Group_3__0_in_rule__XExpression__Group__3__Impl8688 = new BitSet(new long[]{0x0000010000000002L});
    public static final BitSet FOLLOW_rule__XExpression__Group__4__Impl_in_rule__XExpression__Group__48719 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_11_in_rule__XExpression__Group__4__Impl8747 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__XExpression__Group_3__0__Impl_in_rule__XExpression__Group_3__08788 = new BitSet(new long[]{0x0080000000000000L});
    public static final BitSet FOLLOW_rule__XExpression__Group_3__1_in_rule__XExpression__Group_3__08791 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_4_in_rule__XExpression__Group_3__0__Impl8819 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__XExpression__Group_3__1__Impl_in_rule__XExpression__Group_3__18850 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__XExpression__Group_3__1__Impl8877 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Expression__Group__0__Impl_in_rule__Expression__Group__08910 = new BitSet(new long[]{0x00007001C301B080L});
    public static final BitSet FOLLOW_rule__Expression__Group__1_in_rule__Expression__Group__08913 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Expression__Op1Assignment_0_in_rule__Expression__Group__0__Impl8940 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Expression__Group__1__Impl_in_rule__Expression__Group__18970 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Expression__Alternatives_1_in_rule__Expression__Group__1__Impl8997 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Comparison__Group__0__Impl_in_rule__Comparison__Group__09031 = new BitSet(new long[]{0x01BE002004200000L});
    public static final BitSet FOLLOW_rule__Comparison__Group__1_in_rule__Comparison__Group__09034 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Comparison__Alternatives_0_in_rule__Comparison__Group__0__Impl9061 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Comparison__Group__1__Impl_in_rule__Comparison__Group__19091 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Comparison__Op2Assignment_1_in_rule__Comparison__Group__1__Impl9118 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Like__Group__0__Impl_in_rule__Like__Group__09152 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_rule__Like__Group__1_in_rule__Like__Group__09155 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Like__Alternatives_0_in_rule__Like__Group__0__Impl9182 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Like__Group__1__Impl_in_rule__Like__Group__19212 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleStringOperand_in_rule__Like__Group__1__Impl9239 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Between__Group__0__Impl_in_rule__Between__Group__09272 = new BitSet(new long[]{0x01BE002004200000L});
    public static final BitSet FOLLOW_rule__Between__Group__1_in_rule__Between__Group__09275 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_32_in_rule__Between__Group__0__Impl9303 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Between__Group__1__Impl_in_rule__Between__Group__19334 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_rule__Between__Group__2_in_rule__Between__Group__19337 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Between__Op1Assignment_1_in_rule__Between__Group__1__Impl9364 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Between__Group__2__Impl_in_rule__Between__Group__29394 = new BitSet(new long[]{0x01BE002004200000L});
    public static final BitSet FOLLOW_rule__Between__Group__3_in_rule__Between__Group__29397 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_21_in_rule__Between__Group__2__Impl9425 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Between__Group__3__Impl_in_rule__Between__Group__39456 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Between__Op2Assignment_3_in_rule__Between__Group__3__Impl9483 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__InOperator__Group__0__Impl_in_rule__InOperator__Group__09521 = new BitSet(new long[]{0x0000000001001000L});
    public static final BitSet FOLLOW_rule__InOperator__Group__1_in_rule__InOperator__Group__09524 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__InOperator__Group__1__Impl_in_rule__InOperator__Group__19582 = new BitSet(new long[]{0x01BE012004200000L});
    public static final BitSet FOLLOW_rule__InOperator__Group__2_in_rule__InOperator__Group__19585 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__InOperator__Alternatives_1_in_rule__InOperator__Group__1__Impl9612 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__InOperator__Group__2__Impl_in_rule__InOperator__Group__29642 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_rule__InOperator__Group__3_in_rule__InOperator__Group__29645 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__InOperator__Alternatives_2_in_rule__InOperator__Group__2__Impl9672 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__InOperator__Group__3__Impl_in_rule__InOperator__Group__39702 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_2_in_rule__InOperator__Group__3__Impl9730 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__InOperator__Group_2_1__0__Impl_in_rule__InOperator__Group_2_1__09769 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_rule__InOperator__Group_2_1__1_in_rule__InOperator__Group_2_1__09772 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__InOperator__Group_2_1__1__Impl_in_rule__InOperator__Group_2_1__19830 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__InOperator__Group_2_1_1__0_in_rule__InOperator__Group_2_1__1__Impl9859 = new BitSet(new long[]{0x0000010000000002L});
    public static final BitSet FOLLOW_rule__InOperator__Group_2_1_1__0_in_rule__InOperator__Group_2_1__1__Impl9871 = new BitSet(new long[]{0x0000010000000002L});
    public static final BitSet FOLLOW_rule__InOperator__Group_2_1_1__0__Impl_in_rule__InOperator__Group_2_1_1__09908 = new BitSet(new long[]{0x013E000004200000L});
    public static final BitSet FOLLOW_rule__InOperator__Group_2_1_1__1_in_rule__InOperator__Group_2_1_1__09911 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_4_in_rule__InOperator__Group_2_1_1__0__Impl9939 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__InOperator__Group_2_1_1__1__Impl_in_rule__InOperator__Group_2_1_1__19970 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__InOperator__EntriesAssignment_2_1_1_1_in_rule__InOperator__Group_2_1_1__1__Impl9997 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Operand__Group__0__Impl_in_rule__Operand__Group__010031 = new BitSet(new long[]{0x00010A9000000000L});
    public static final BitSet FOLLOW_rule__Operand__Group__1_in_rule__Operand__Group__010034 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperandFragment_in_rule__Operand__Group__0__Impl10061 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Operand__Group__1__Impl_in_rule__Operand__Group__110090 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Operand__Group_1__0_in_rule__Operand__Group__1__Impl10117 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Operand__Group_1__0__Impl_in_rule__Operand__Group_1__010152 = new BitSet(new long[]{0x00010A9000000000L});
    public static final BitSet FOLLOW_rule__Operand__Group_1__1_in_rule__Operand__Group_1__010155 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Operand__Group_1__1__Impl_in_rule__Operand__Group_1__110213 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Operand__Group_1_1__0_in_rule__Operand__Group_1__1__Impl10242 = new BitSet(new long[]{0x00010A9000000002L});
    public static final BitSet FOLLOW_rule__Operand__Group_1_1__0_in_rule__Operand__Group_1__1__Impl10254 = new BitSet(new long[]{0x00010A9000000002L});
    public static final BitSet FOLLOW_rule__Operand__Group_1_1__0__Impl_in_rule__Operand__Group_1_1__010291 = new BitSet(new long[]{0x01BE002004200000L});
    public static final BitSet FOLLOW_rule__Operand__Group_1_1__1_in_rule__Operand__Group_1_1__010294 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Operand__Alternatives_1_1_0_in_rule__Operand__Group_1_1__0__Impl10321 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Operand__Group_1_1__1__Impl_in_rule__Operand__Group_1_1__110351 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Operand__EntriesAssignment_1_1_1_in_rule__Operand__Group_1_1__1__Impl10378 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ParameterOperand__Group__0__Impl_in_rule__ParameterOperand__Group__010413 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_rule__ParameterOperand__Group__1_in_rule__ParameterOperand__Group__010416 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ParameterOperand__Group__1__Impl_in_rule__ParameterOperand__Group__110474 = new BitSet(new long[]{0x0080000000000000L});
    public static final BitSet FOLLOW_rule__ParameterOperand__Group__2_in_rule__ParameterOperand__Group__110477 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_19_in_rule__ParameterOperand__Group__1__Impl10505 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ParameterOperand__Group__2__Impl_in_rule__ParameterOperand__Group__210536 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_rule__ParameterOperand__Group__3_in_rule__ParameterOperand__Group__210539 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__ParameterOperand__Group__2__Impl10566 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ParameterOperand__Group__3__Impl_in_rule__ParameterOperand__Group__310595 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_11_in_rule__ParameterOperand__Group__3__Impl10623 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ExclamationParameterOperand__Group__0__Impl_in_rule__ExclamationParameterOperand__Group__010662 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_rule__ExclamationParameterOperand__Group__1_in_rule__ExclamationParameterOperand__Group__010665 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ExclamationParameterOperand__Group__1__Impl_in_rule__ExclamationParameterOperand__Group__110723 = new BitSet(new long[]{0x0080000000000000L});
    public static final BitSet FOLLOW_rule__ExclamationParameterOperand__Group__2_in_rule__ExclamationParameterOperand__Group__110726 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_23_in_rule__ExclamationParameterOperand__Group__1__Impl10754 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ExclamationParameterOperand__Group__2__Impl_in_rule__ExclamationParameterOperand__Group__210785 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_rule__ExclamationParameterOperand__Group__3_in_rule__ExclamationParameterOperand__Group__210788 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__ExclamationParameterOperand__Group__2__Impl10815 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ExclamationParameterOperand__Group__3__Impl_in_rule__ExclamationParameterOperand__Group__310844 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_11_in_rule__ExclamationParameterOperand__Group__3__Impl10872 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SubQueryOperand__Group__0__Impl_in_rule__SubQueryOperand__Group__010911 = new BitSet(new long[]{0x01BE002004200000L});
    public static final BitSet FOLLOW_rule__SubQueryOperand__Group__1_in_rule__SubQueryOperand__Group__010914 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SubQueryOperand__Group__1__Impl_in_rule__SubQueryOperand__Group__110972 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_rule__SubQueryOperand__Group__2_in_rule__SubQueryOperand__Group__110975 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_1_in_rule__SubQueryOperand__Group__1__Impl11003 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SubQueryOperand__Group__2__Impl_in_rule__SubQueryOperand__Group__211034 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_rule__SubQueryOperand__Group__3_in_rule__SubQueryOperand__Group__211037 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SubQueryOperand__SelAssignment_2_in_rule__SubQueryOperand__Group__2__Impl11064 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SubQueryOperand__Group__3__Impl_in_rule__SubQueryOperand__Group__311094 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_2_in_rule__SubQueryOperand__Group__3__Impl11122 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSelect_in_rule__Model__EntriesAssignment_1_1_111166 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOrderByColumns_in_rule__Model__OrderByEntryAssignment_2_111197 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_31_in_rule__Select__SelectAssignment_011233 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumns_in_rule__Select__ColsAssignment_211272 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTables_in_rule__Select__TblAssignment_411303 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFullExpression_in_rule__Select__WhereExpressionAssignment_5_111334 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleGroupByColumns_in_rule__Select__GroupByEntryAssignment_6_111365 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFullExpression_in_rule__Select__HavingEntryAssignment_7_111396 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnOrAlias_in_rule__Columns__EntriesAssignment_1_1_111427 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDbObjectName_in_rule__ColumnOrAlias__ColAliasAssignment_0_211458 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STAR_in_rule__ColumnOrAlias__AllColsAssignment_111489 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDbObjectName_in_rule__ColumnFull__EntriesAssignment_1_1_111520 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFromTable_in_rule__Tables__EntriesAssignment_1_1_111551 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTableOrAlias_in_rule__FromTable__TableAssignment_011582 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleJoinType_in_rule__FromTable__JoinAssignment_1_011613 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTableOrAlias_in_rule__FromTable__OnTableAssignment_1_111644 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFullExpression_in_rule__FromTable__JoinExprAssignment_1_311675 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTableFull_in_rule__TableOrAlias__TfullAssignment_011706 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_15_in_rule__TableOrAlias__AliasAssignment_111742 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDbObjectName_in_rule__TableOrAlias__TblAliasAssignment_211781 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDbObjectName_in_rule__TableFull__EntriesAssignment_1_1_111812 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__DbObjectName__DbnameAssignment11843 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOrderByColumnFull_in_rule__OrderByColumns__EntriesAssignment_1_1_111874 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnFull_in_rule__OrderByColumnFull__ColOrderAssignment_011905 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnFull_in_rule__GroupByColumns__EntriesAssignment_1_1_111936 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpressionFragment_in_rule__FullExpression__EntriesAssignment_1_1_111967 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFullExpression_in_rule__ExpressionGroup__ExprAssignment_211998 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperand_in_rule__Expression__Op1Assignment_012029 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInOperator_in_rule__Expression__InAssignment_1_112060 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBetween_in_rule__Expression__BetweenAssignment_1_212091 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLike_in_rule__Expression__LikeAssignment_1_312122 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleComparison_in_rule__Expression__CompAssignment_1_412153 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperand_in_rule__Comparison__Op2Assignment_112184 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperand_in_rule__Between__Op1Assignment_112215 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperand_in_rule__Between__Op2Assignment_312246 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSubQueryOperand_in_rule__InOperator__SubqueryAssignment_2_012277 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXOperandFragment_in_rule__InOperator__EntriesAssignment_2_1_1_112308 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperandFragment_in_rule__Operand__EntriesAssignment_1_1_112339 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleScalarOperand_in_rule__XOperandFragment__ScalarAssignment_212371 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSelect_in_rule__SubQueryOperand__SelAssignment_212402 = new BitSet(new long[]{0x0000000000000002L});

}