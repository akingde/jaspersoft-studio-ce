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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "KEYWORD_56", "KEYWORD_54", "KEYWORD_55", "KEYWORD_52", "KEYWORD_53", "KEYWORD_50", "KEYWORD_51", "KEYWORD_49", "KEYWORD_40", "KEYWORD_41", "KEYWORD_42", "KEYWORD_43", "KEYWORD_44", "KEYWORD_45", "KEYWORD_46", "KEYWORD_47", "KEYWORD_48", "KEYWORD_37", "KEYWORD_38", "KEYWORD_39", "KEYWORD_35", "KEYWORD_36", "KEYWORD_30", "KEYWORD_31", "KEYWORD_32", "KEYWORD_33", "KEYWORD_34", "KEYWORD_24", "KEYWORD_25", "KEYWORD_26", "KEYWORD_27", "KEYWORD_28", "KEYWORD_29", "KEYWORD_20", "KEYWORD_21", "KEYWORD_22", "KEYWORD_23", "KEYWORD_12", "KEYWORD_13", "KEYWORD_14", "KEYWORD_15", "KEYWORD_16", "KEYWORD_17", "KEYWORD_18", "KEYWORD_19", "KEYWORD_1", "KEYWORD_2", "KEYWORD_3", "KEYWORD_4", "KEYWORD_5", "KEYWORD_6", "KEYWORD_7", "KEYWORD_8", "KEYWORD_9", "KEYWORD_10", "KEYWORD_11", "RULE_STAR", "RULE_SIGNED_INT", "RULE_DATE", "RULE_TIME", "RULE_TIMESTAMP", "RULE_SIGNED_DOUBLE", "RULE_SL_COMMENT", "RULE_ID", "RULE_INT", "RULE_STRING", "RULE_ML_COMMENT", "RULE_WS", "RULE_ANY_OTHER"
    };
    public static final int RULE_ID=67;
    public static final int RULE_DATE=62;
    public static final int RULE_ANY_OTHER=72;
    public static final int KEYWORD_56=4;
    public static final int KEYWORD_19=48;
    public static final int KEYWORD_55=6;
    public static final int KEYWORD_54=5;
    public static final int KEYWORD_17=46;
    public static final int KEYWORD_53=8;
    public static final int KEYWORD_18=47;
    public static final int KEYWORD_52=7;
    public static final int KEYWORD_15=44;
    public static final int KEYWORD_51=10;
    public static final int KEYWORD_16=45;
    public static final int KEYWORD_50=9;
    public static final int KEYWORD_13=42;
    public static final int KEYWORD_14=43;
    public static final int KEYWORD_11=59;
    public static final int EOF=-1;
    public static final int KEYWORD_12=41;
    public static final int KEYWORD_10=58;
    public static final int KEYWORD_6=54;
    public static final int KEYWORD_7=55;
    public static final int KEYWORD_8=56;
    public static final int KEYWORD_9=57;
    public static final int RULE_TIME=63;
    public static final int KEYWORD_28=35;
    public static final int KEYWORD_29=36;
    public static final int RULE_INT=68;
    public static final int RULE_SIGNED_DOUBLE=65;
    public static final int RULE_TIMESTAMP=64;
    public static final int RULE_SIGNED_INT=61;
    public static final int KEYWORD_24=31;
    public static final int KEYWORD_25=32;
    public static final int KEYWORD_26=33;
    public static final int KEYWORD_27=34;
    public static final int KEYWORD_20=37;
    public static final int KEYWORD_21=38;
    public static final int KEYWORD_22=39;
    public static final int KEYWORD_23=40;
    public static final int KEYWORD_30=26;
    public static final int KEYWORD_1=49;
    public static final int KEYWORD_34=30;
    public static final int KEYWORD_5=53;
    public static final int KEYWORD_33=29;
    public static final int KEYWORD_4=52;
    public static final int KEYWORD_32=28;
    public static final int KEYWORD_3=51;
    public static final int KEYWORD_31=27;
    public static final int KEYWORD_2=50;
    public static final int KEYWORD_38=22;
    public static final int KEYWORD_37=21;
    public static final int RULE_SL_COMMENT=66;
    public static final int KEYWORD_36=25;
    public static final int KEYWORD_35=24;
    public static final int RULE_ML_COMMENT=70;
    public static final int KEYWORD_39=23;
    public static final int RULE_STRING=69;
    public static final int RULE_STAR=60;
    public static final int KEYWORD_41=13;
    public static final int KEYWORD_40=12;
    public static final int KEYWORD_43=15;
    public static final int KEYWORD_42=14;
    public static final int KEYWORD_45=17;
    public static final int KEYWORD_44=16;
    public static final int KEYWORD_47=19;
    public static final int RULE_WS=71;
    public static final int KEYWORD_46=18;
    public static final int KEYWORD_49=11;
    public static final int KEYWORD_48=20;

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
    		tokenNameToValue.put("KEYWORD_16", "'IN'");
    		tokenNameToValue.put("KEYWORD_17", "'ON'");
    		tokenNameToValue.put("KEYWORD_18", "'OR'");
    		tokenNameToValue.put("KEYWORD_19", "'||'");
    		tokenNameToValue.put("KEYWORD_20", "'\u0024P{'");
    		tokenNameToValue.put("KEYWORD_21", "'\u0024X{'");
    		tokenNameToValue.put("KEYWORD_22", "'AND'");
    		tokenNameToValue.put("KEYWORD_23", "'ASC'");
    		tokenNameToValue.put("KEYWORD_24", "'\u0024P!{'");
    		tokenNameToValue.put("KEYWORD_25", "'DESC'");
    		tokenNameToValue.put("KEYWORD_26", "'FROM'");
    		tokenNameToValue.put("KEYWORD_27", "'IN ('");
    		tokenNameToValue.put("KEYWORD_28", "'LESS'");
    		tokenNameToValue.put("KEYWORD_29", "'LIKE'");
    		tokenNameToValue.put("KEYWORD_30", "'EQUAL'");
    		tokenNameToValue.put("KEYWORD_31", "'LESS]'");
    		tokenNameToValue.put("KEYWORD_32", "'NOTIN'");
    		tokenNameToValue.put("KEYWORD_33", "'UNION'");
    		tokenNameToValue.put("KEYWORD_34", "'WHERE'");
    		tokenNameToValue.put("KEYWORD_35", "'HAVING'");
    		tokenNameToValue.put("KEYWORD_36", "'SELECT'");
    		tokenNameToValue.put("KEYWORD_37", "'BETWEEN'");
    		tokenNameToValue.put("KEYWORD_38", "'GREATER'");
    		tokenNameToValue.put("KEYWORD_39", "'IS NULL'");
    		tokenNameToValue.put("KEYWORD_40", "'BETWEEN]'");
    		tokenNameToValue.put("KEYWORD_41", "'DISTINCT'");
    		tokenNameToValue.put("KEYWORD_42", "'GROUP BY'");
    		tokenNameToValue.put("KEYWORD_43", "'NOT IN ('");
    		tokenNameToValue.put("KEYWORD_44", "'NOT LIKE'");
    		tokenNameToValue.put("KEYWORD_45", "'NOTEQUAL'");
    		tokenNameToValue.put("KEYWORD_46", "'ORDER BY'");
    		tokenNameToValue.put("KEYWORD_47", "'[BETWEEN'");
    		tokenNameToValue.put("KEYWORD_48", "'[GREATER'");
    		tokenNameToValue.put("KEYWORD_49", "'[BETWEEN]'");
    		tokenNameToValue.put("KEYWORD_50", "'CROSS JOIN'");
    		tokenNameToValue.put("KEYWORD_51", "'INNER JOIN'");
    		tokenNameToValue.put("KEYWORD_52", "'IS NOT NULL'");
    		tokenNameToValue.put("KEYWORD_53", "'NOT BETWEEN'");
    		tokenNameToValue.put("KEYWORD_54", "'FULL OUTER JOIN'");
    		tokenNameToValue.put("KEYWORD_55", "'LEFT OUTER JOIN'");
    		tokenNameToValue.put("KEYWORD_56", "'RIGHT OUTER JOIN'");
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:119:1: entryRuleModel : ruleModel EOF ;
    public final void entryRuleModel() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:120:1: ( ruleModel EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:121:1: ruleModel EOF
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:128:1: ruleModel : ( ( rule__Model__Group__0 ) ) ;
    public final void ruleModel() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:132:5: ( ( ( rule__Model__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:133:1: ( ( rule__Model__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:133:1: ( ( rule__Model__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:134:1: ( rule__Model__Group__0 )
            {
             before(grammarAccess.getModelAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:135:1: ( rule__Model__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:135:2: rule__Model__Group__0
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:147:1: entryRuleSelect : ruleSelect EOF ;
    public final void entryRuleSelect() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:148:1: ( ruleSelect EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:149:1: ruleSelect EOF
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:156:1: ruleSelect : ( ( rule__Select__Group__0 ) ) ;
    public final void ruleSelect() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:160:5: ( ( ( rule__Select__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:161:1: ( ( rule__Select__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:161:1: ( ( rule__Select__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:162:1: ( rule__Select__Group__0 )
            {
             before(grammarAccess.getSelectAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:163:1: ( rule__Select__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:163:2: rule__Select__Group__0
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:175:1: entryRuleColumns : ruleColumns EOF ;
    public final void entryRuleColumns() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:176:1: ( ruleColumns EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:177:1: ruleColumns EOF
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:184:1: ruleColumns : ( ( rule__Columns__Group__0 ) ) ;
    public final void ruleColumns() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:188:5: ( ( ( rule__Columns__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:189:1: ( ( rule__Columns__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:189:1: ( ( rule__Columns__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:190:1: ( rule__Columns__Group__0 )
            {
             before(grammarAccess.getColumnsAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:191:1: ( rule__Columns__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:191:2: rule__Columns__Group__0
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:203:1: entryRuleColumnOrAlias : ruleColumnOrAlias EOF ;
    public final void entryRuleColumnOrAlias() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:204:1: ( ruleColumnOrAlias EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:205:1: ruleColumnOrAlias EOF
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:212:1: ruleColumnOrAlias : ( ( rule__ColumnOrAlias__Alternatives ) ) ;
    public final void ruleColumnOrAlias() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:216:5: ( ( ( rule__ColumnOrAlias__Alternatives ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:217:1: ( ( rule__ColumnOrAlias__Alternatives ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:217:1: ( ( rule__ColumnOrAlias__Alternatives ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:218:1: ( rule__ColumnOrAlias__Alternatives )
            {
             before(grammarAccess.getColumnOrAliasAccess().getAlternatives()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:219:1: ( rule__ColumnOrAlias__Alternatives )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:219:2: rule__ColumnOrAlias__Alternatives
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:231:1: entryRuleColumnFull : ruleColumnFull EOF ;
    public final void entryRuleColumnFull() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:232:1: ( ruleColumnFull EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:233:1: ruleColumnFull EOF
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:240:1: ruleColumnFull : ( ( rule__ColumnFull__Group__0 ) ) ;
    public final void ruleColumnFull() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:244:5: ( ( ( rule__ColumnFull__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:245:1: ( ( rule__ColumnFull__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:245:1: ( ( rule__ColumnFull__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:246:1: ( rule__ColumnFull__Group__0 )
            {
             before(grammarAccess.getColumnFullAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:247:1: ( rule__ColumnFull__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:247:2: rule__ColumnFull__Group__0
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:259:1: entryRuleTables : ruleTables EOF ;
    public final void entryRuleTables() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:260:1: ( ruleTables EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:261:1: ruleTables EOF
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:268:1: ruleTables : ( ( rule__Tables__Group__0 ) ) ;
    public final void ruleTables() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:272:5: ( ( ( rule__Tables__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:273:1: ( ( rule__Tables__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:273:1: ( ( rule__Tables__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:274:1: ( rule__Tables__Group__0 )
            {
             before(grammarAccess.getTablesAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:275:1: ( rule__Tables__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:275:2: rule__Tables__Group__0
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:287:1: entryRuleFromTable : ruleFromTable EOF ;
    public final void entryRuleFromTable() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:288:1: ( ruleFromTable EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:289:1: ruleFromTable EOF
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:296:1: ruleFromTable : ( ( rule__FromTable__Group__0 ) ) ;
    public final void ruleFromTable() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:300:5: ( ( ( rule__FromTable__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:301:1: ( ( rule__FromTable__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:301:1: ( ( rule__FromTable__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:302:1: ( rule__FromTable__Group__0 )
            {
             before(grammarAccess.getFromTableAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:303:1: ( rule__FromTable__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:303:2: rule__FromTable__Group__0
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:315:1: entryRuleTableOrAlias : ruleTableOrAlias EOF ;
    public final void entryRuleTableOrAlias() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:316:1: ( ruleTableOrAlias EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:317:1: ruleTableOrAlias EOF
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:324:1: ruleTableOrAlias : ( ( rule__TableOrAlias__Group__0 ) ) ;
    public final void ruleTableOrAlias() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:328:5: ( ( ( rule__TableOrAlias__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:329:1: ( ( rule__TableOrAlias__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:329:1: ( ( rule__TableOrAlias__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:330:1: ( rule__TableOrAlias__Group__0 )
            {
             before(grammarAccess.getTableOrAliasAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:331:1: ( rule__TableOrAlias__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:331:2: rule__TableOrAlias__Group__0
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:343:1: entryRuleTableFull : ruleTableFull EOF ;
    public final void entryRuleTableFull() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:344:1: ( ruleTableFull EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:345:1: ruleTableFull EOF
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:352:1: ruleTableFull : ( ( rule__TableFull__Group__0 ) ) ;
    public final void ruleTableFull() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:356:5: ( ( ( rule__TableFull__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:357:1: ( ( rule__TableFull__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:357:1: ( ( rule__TableFull__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:358:1: ( rule__TableFull__Group__0 )
            {
             before(grammarAccess.getTableFullAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:359:1: ( rule__TableFull__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:359:2: rule__TableFull__Group__0
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:371:1: entryRuleDbObjectName : ruleDbObjectName EOF ;
    public final void entryRuleDbObjectName() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:372:1: ( ruleDbObjectName EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:373:1: ruleDbObjectName EOF
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:380:1: ruleDbObjectName : ( ( rule__DbObjectName__DbnameAssignment ) ) ;
    public final void ruleDbObjectName() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:384:5: ( ( ( rule__DbObjectName__DbnameAssignment ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:385:1: ( ( rule__DbObjectName__DbnameAssignment ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:385:1: ( ( rule__DbObjectName__DbnameAssignment ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:386:1: ( rule__DbObjectName__DbnameAssignment )
            {
             before(grammarAccess.getDbObjectNameAccess().getDbnameAssignment()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:387:1: ( rule__DbObjectName__DbnameAssignment )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:387:2: rule__DbObjectName__DbnameAssignment
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:399:1: entryRuleOrderByColumns : ruleOrderByColumns EOF ;
    public final void entryRuleOrderByColumns() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:400:1: ( ruleOrderByColumns EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:401:1: ruleOrderByColumns EOF
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:408:1: ruleOrderByColumns : ( ( rule__OrderByColumns__Group__0 ) ) ;
    public final void ruleOrderByColumns() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:412:5: ( ( ( rule__OrderByColumns__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:413:1: ( ( rule__OrderByColumns__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:413:1: ( ( rule__OrderByColumns__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:414:1: ( rule__OrderByColumns__Group__0 )
            {
             before(grammarAccess.getOrderByColumnsAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:415:1: ( rule__OrderByColumns__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:415:2: rule__OrderByColumns__Group__0
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:427:1: entryRuleOrderByColumnFull : ruleOrderByColumnFull EOF ;
    public final void entryRuleOrderByColumnFull() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:428:1: ( ruleOrderByColumnFull EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:429:1: ruleOrderByColumnFull EOF
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:436:1: ruleOrderByColumnFull : ( ( rule__OrderByColumnFull__Group__0 ) ) ;
    public final void ruleOrderByColumnFull() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:440:5: ( ( ( rule__OrderByColumnFull__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:441:1: ( ( rule__OrderByColumnFull__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:441:1: ( ( rule__OrderByColumnFull__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:442:1: ( rule__OrderByColumnFull__Group__0 )
            {
             before(grammarAccess.getOrderByColumnFullAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:443:1: ( rule__OrderByColumnFull__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:443:2: rule__OrderByColumnFull__Group__0
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:455:1: entryRuleGroupByColumns : ruleGroupByColumns EOF ;
    public final void entryRuleGroupByColumns() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:456:1: ( ruleGroupByColumns EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:457:1: ruleGroupByColumns EOF
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:464:1: ruleGroupByColumns : ( ( rule__GroupByColumns__Group__0 ) ) ;
    public final void ruleGroupByColumns() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:468:5: ( ( ( rule__GroupByColumns__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:469:1: ( ( rule__GroupByColumns__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:469:1: ( ( rule__GroupByColumns__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:470:1: ( rule__GroupByColumns__Group__0 )
            {
             before(grammarAccess.getGroupByColumnsAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:471:1: ( rule__GroupByColumns__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:471:2: rule__GroupByColumns__Group__0
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:483:1: entryRuleFullExpression : ruleFullExpression EOF ;
    public final void entryRuleFullExpression() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:484:1: ( ruleFullExpression EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:485:1: ruleFullExpression EOF
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:492:1: ruleFullExpression : ( ( rule__FullExpression__Group__0 ) ) ;
    public final void ruleFullExpression() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:496:5: ( ( ( rule__FullExpression__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:497:1: ( ( rule__FullExpression__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:497:1: ( ( rule__FullExpression__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:498:1: ( rule__FullExpression__Group__0 )
            {
             before(grammarAccess.getFullExpressionAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:499:1: ( rule__FullExpression__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:499:2: rule__FullExpression__Group__0
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


    // $ANTLR start "entryRuleExpressionFragmentSecond"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:511:1: entryRuleExpressionFragmentSecond : ruleExpressionFragmentSecond EOF ;
    public final void entryRuleExpressionFragmentSecond() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:512:1: ( ruleExpressionFragmentSecond EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:513:1: ruleExpressionFragmentSecond EOF
            {
             before(grammarAccess.getExpressionFragmentSecondRule()); 
            pushFollow(FOLLOW_ruleExpressionFragmentSecond_in_entryRuleExpressionFragmentSecond950);
            ruleExpressionFragmentSecond();

            state._fsp--;

             after(grammarAccess.getExpressionFragmentSecondRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpressionFragmentSecond957); 

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
    // $ANTLR end "entryRuleExpressionFragmentSecond"


    // $ANTLR start "ruleExpressionFragmentSecond"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:520:1: ruleExpressionFragmentSecond : ( ( rule__ExpressionFragmentSecond__Group__0 ) ) ;
    public final void ruleExpressionFragmentSecond() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:524:5: ( ( ( rule__ExpressionFragmentSecond__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:525:1: ( ( rule__ExpressionFragmentSecond__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:525:1: ( ( rule__ExpressionFragmentSecond__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:526:1: ( rule__ExpressionFragmentSecond__Group__0 )
            {
             before(grammarAccess.getExpressionFragmentSecondAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:527:1: ( rule__ExpressionFragmentSecond__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:527:2: rule__ExpressionFragmentSecond__Group__0
            {
            pushFollow(FOLLOW_rule__ExpressionFragmentSecond__Group__0_in_ruleExpressionFragmentSecond987);
            rule__ExpressionFragmentSecond__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getExpressionFragmentSecondAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleExpressionFragmentSecond"


    // $ANTLR start "entryRuleExpressionFragment"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:539:1: entryRuleExpressionFragment : ruleExpressionFragment EOF ;
    public final void entryRuleExpressionFragment() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:540:1: ( ruleExpressionFragment EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:541:1: ruleExpressionFragment EOF
            {
             before(grammarAccess.getExpressionFragmentRule()); 
            pushFollow(FOLLOW_ruleExpressionFragment_in_entryRuleExpressionFragment1014);
            ruleExpressionFragment();

            state._fsp--;

             after(grammarAccess.getExpressionFragmentRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpressionFragment1021); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:548:1: ruleExpressionFragment : ( ( rule__ExpressionFragment__Alternatives ) ) ;
    public final void ruleExpressionFragment() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:552:5: ( ( ( rule__ExpressionFragment__Alternatives ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:553:1: ( ( rule__ExpressionFragment__Alternatives ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:553:1: ( ( rule__ExpressionFragment__Alternatives ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:554:1: ( rule__ExpressionFragment__Alternatives )
            {
             before(grammarAccess.getExpressionFragmentAccess().getAlternatives()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:555:1: ( rule__ExpressionFragment__Alternatives )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:555:2: rule__ExpressionFragment__Alternatives
            {
            pushFollow(FOLLOW_rule__ExpressionFragment__Alternatives_in_ruleExpressionFragment1051);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:567:1: entryRuleExpressionGroup : ruleExpressionGroup EOF ;
    public final void entryRuleExpressionGroup() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:568:1: ( ruleExpressionGroup EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:569:1: ruleExpressionGroup EOF
            {
             before(grammarAccess.getExpressionGroupRule()); 
            pushFollow(FOLLOW_ruleExpressionGroup_in_entryRuleExpressionGroup1078);
            ruleExpressionGroup();

            state._fsp--;

             after(grammarAccess.getExpressionGroupRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpressionGroup1085); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:576:1: ruleExpressionGroup : ( ( rule__ExpressionGroup__Group__0 ) ) ;
    public final void ruleExpressionGroup() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:580:5: ( ( ( rule__ExpressionGroup__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:581:1: ( ( rule__ExpressionGroup__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:581:1: ( ( rule__ExpressionGroup__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:582:1: ( rule__ExpressionGroup__Group__0 )
            {
             before(grammarAccess.getExpressionGroupAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:583:1: ( rule__ExpressionGroup__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:583:2: rule__ExpressionGroup__Group__0
            {
            pushFollow(FOLLOW_rule__ExpressionGroup__Group__0_in_ruleExpressionGroup1115);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:595:1: entryRuleXExpression : ruleXExpression EOF ;
    public final void entryRuleXExpression() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:596:1: ( ruleXExpression EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:597:1: ruleXExpression EOF
            {
             before(grammarAccess.getXExpressionRule()); 
            pushFollow(FOLLOW_ruleXExpression_in_entryRuleXExpression1142);
            ruleXExpression();

            state._fsp--;

             after(grammarAccess.getXExpressionRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleXExpression1149); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:604:1: ruleXExpression : ( ( rule__XExpression__Group__0 ) ) ;
    public final void ruleXExpression() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:608:5: ( ( ( rule__XExpression__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:609:1: ( ( rule__XExpression__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:609:1: ( ( rule__XExpression__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:610:1: ( rule__XExpression__Group__0 )
            {
             before(grammarAccess.getXExpressionAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:611:1: ( rule__XExpression__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:611:2: rule__XExpression__Group__0
            {
            pushFollow(FOLLOW_rule__XExpression__Group__0_in_ruleXExpression1179);
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


    // $ANTLR start "entryRuleXExpressionParams"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:623:1: entryRuleXExpressionParams : ruleXExpressionParams EOF ;
    public final void entryRuleXExpressionParams() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:624:1: ( ruleXExpressionParams EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:625:1: ruleXExpressionParams EOF
            {
             before(grammarAccess.getXExpressionParamsRule()); 
            pushFollow(FOLLOW_ruleXExpressionParams_in_entryRuleXExpressionParams1206);
            ruleXExpressionParams();

            state._fsp--;

             after(grammarAccess.getXExpressionParamsRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleXExpressionParams1213); 

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
    // $ANTLR end "entryRuleXExpressionParams"


    // $ANTLR start "ruleXExpressionParams"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:632:1: ruleXExpressionParams : ( ( rule__XExpressionParams__Group__0 ) ) ;
    public final void ruleXExpressionParams() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:636:5: ( ( ( rule__XExpressionParams__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:637:1: ( ( rule__XExpressionParams__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:637:1: ( ( rule__XExpressionParams__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:638:1: ( rule__XExpressionParams__Group__0 )
            {
             before(grammarAccess.getXExpressionParamsAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:639:1: ( rule__XExpressionParams__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:639:2: rule__XExpressionParams__Group__0
            {
            pushFollow(FOLLOW_rule__XExpressionParams__Group__0_in_ruleXExpressionParams1243);
            rule__XExpressionParams__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getXExpressionParamsAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleXExpressionParams"


    // $ANTLR start "entryRuleJRParameter"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:651:1: entryRuleJRParameter : ruleJRParameter EOF ;
    public final void entryRuleJRParameter() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:652:1: ( ruleJRParameter EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:653:1: ruleJRParameter EOF
            {
             before(grammarAccess.getJRParameterRule()); 
            pushFollow(FOLLOW_ruleJRParameter_in_entryRuleJRParameter1270);
            ruleJRParameter();

            state._fsp--;

             after(grammarAccess.getJRParameterRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleJRParameter1277); 

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
    // $ANTLR end "entryRuleJRParameter"


    // $ANTLR start "ruleJRParameter"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:660:1: ruleJRParameter : ( ( rule__JRParameter__JrprmAssignment ) ) ;
    public final void ruleJRParameter() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:664:5: ( ( ( rule__JRParameter__JrprmAssignment ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:665:1: ( ( rule__JRParameter__JrprmAssignment ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:665:1: ( ( rule__JRParameter__JrprmAssignment ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:666:1: ( rule__JRParameter__JrprmAssignment )
            {
             before(grammarAccess.getJRParameterAccess().getJrprmAssignment()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:667:1: ( rule__JRParameter__JrprmAssignment )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:667:2: rule__JRParameter__JrprmAssignment
            {
            pushFollow(FOLLOW_rule__JRParameter__JrprmAssignment_in_ruleJRParameter1307);
            rule__JRParameter__JrprmAssignment();

            state._fsp--;


            }

             after(grammarAccess.getJRParameterAccess().getJrprmAssignment()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleJRParameter"


    // $ANTLR start "entryRuleExpression"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:679:1: entryRuleExpression : ruleExpression EOF ;
    public final void entryRuleExpression() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:680:1: ( ruleExpression EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:681:1: ruleExpression EOF
            {
             before(grammarAccess.getExpressionRule()); 
            pushFollow(FOLLOW_ruleExpression_in_entryRuleExpression1334);
            ruleExpression();

            state._fsp--;

             after(grammarAccess.getExpressionRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpression1341); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:688:1: ruleExpression : ( ( rule__Expression__Group__0 ) ) ;
    public final void ruleExpression() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:692:5: ( ( ( rule__Expression__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:693:1: ( ( rule__Expression__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:693:1: ( ( rule__Expression__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:694:1: ( rule__Expression__Group__0 )
            {
             before(grammarAccess.getExpressionAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:695:1: ( rule__Expression__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:695:2: rule__Expression__Group__0
            {
            pushFollow(FOLLOW_rule__Expression__Group__0_in_ruleExpression1371);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:707:1: entryRuleComparison : ruleComparison EOF ;
    public final void entryRuleComparison() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:708:1: ( ruleComparison EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:709:1: ruleComparison EOF
            {
             before(grammarAccess.getComparisonRule()); 
            pushFollow(FOLLOW_ruleComparison_in_entryRuleComparison1398);
            ruleComparison();

            state._fsp--;

             after(grammarAccess.getComparisonRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleComparison1405); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:716:1: ruleComparison : ( ( rule__Comparison__Group__0 ) ) ;
    public final void ruleComparison() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:720:5: ( ( ( rule__Comparison__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:721:1: ( ( rule__Comparison__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:721:1: ( ( rule__Comparison__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:722:1: ( rule__Comparison__Group__0 )
            {
             before(grammarAccess.getComparisonAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:723:1: ( rule__Comparison__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:723:2: rule__Comparison__Group__0
            {
            pushFollow(FOLLOW_rule__Comparison__Group__0_in_ruleComparison1435);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:735:1: entryRuleLike : ruleLike EOF ;
    public final void entryRuleLike() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:736:1: ( ruleLike EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:737:1: ruleLike EOF
            {
             before(grammarAccess.getLikeRule()); 
            pushFollow(FOLLOW_ruleLike_in_entryRuleLike1462);
            ruleLike();

            state._fsp--;

             after(grammarAccess.getLikeRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleLike1469); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:744:1: ruleLike : ( ( rule__Like__Group__0 ) ) ;
    public final void ruleLike() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:748:5: ( ( ( rule__Like__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:749:1: ( ( rule__Like__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:749:1: ( ( rule__Like__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:750:1: ( rule__Like__Group__0 )
            {
             before(grammarAccess.getLikeAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:751:1: ( rule__Like__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:751:2: rule__Like__Group__0
            {
            pushFollow(FOLLOW_rule__Like__Group__0_in_ruleLike1499);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:763:1: entryRuleBetween : ruleBetween EOF ;
    public final void entryRuleBetween() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:764:1: ( ruleBetween EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:765:1: ruleBetween EOF
            {
             before(grammarAccess.getBetweenRule()); 
            pushFollow(FOLLOW_ruleBetween_in_entryRuleBetween1526);
            ruleBetween();

            state._fsp--;

             after(grammarAccess.getBetweenRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleBetween1533); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:772:1: ruleBetween : ( ( rule__Between__Group__0 ) ) ;
    public final void ruleBetween() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:776:5: ( ( ( rule__Between__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:777:1: ( ( rule__Between__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:777:1: ( ( rule__Between__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:778:1: ( rule__Between__Group__0 )
            {
             before(grammarAccess.getBetweenAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:779:1: ( rule__Between__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:779:2: rule__Between__Group__0
            {
            pushFollow(FOLLOW_rule__Between__Group__0_in_ruleBetween1563);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:791:1: entryRuleInOperator : ruleInOperator EOF ;
    public final void entryRuleInOperator() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:792:1: ( ruleInOperator EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:793:1: ruleInOperator EOF
            {
             before(grammarAccess.getInOperatorRule()); 
            pushFollow(FOLLOW_ruleInOperator_in_entryRuleInOperator1590);
            ruleInOperator();

            state._fsp--;

             after(grammarAccess.getInOperatorRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleInOperator1597); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:800:1: ruleInOperator : ( ( rule__InOperator__Group__0 ) ) ;
    public final void ruleInOperator() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:804:5: ( ( ( rule__InOperator__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:805:1: ( ( rule__InOperator__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:805:1: ( ( rule__InOperator__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:806:1: ( rule__InOperator__Group__0 )
            {
             before(grammarAccess.getInOperatorAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:807:1: ( rule__InOperator__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:807:2: rule__InOperator__Group__0
            {
            pushFollow(FOLLOW_rule__InOperator__Group__0_in_ruleInOperator1627);
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


    // $ANTLR start "entryRuleOperandList"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:819:1: entryRuleOperandList : ruleOperandList EOF ;
    public final void entryRuleOperandList() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:820:1: ( ruleOperandList EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:821:1: ruleOperandList EOF
            {
             before(grammarAccess.getOperandListRule()); 
            pushFollow(FOLLOW_ruleOperandList_in_entryRuleOperandList1654);
            ruleOperandList();

            state._fsp--;

             after(grammarAccess.getOperandListRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOperandList1661); 

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
    // $ANTLR end "entryRuleOperandList"


    // $ANTLR start "ruleOperandList"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:828:1: ruleOperandList : ( ( rule__OperandList__Group__0 ) ) ;
    public final void ruleOperandList() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:832:5: ( ( ( rule__OperandList__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:833:1: ( ( rule__OperandList__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:833:1: ( ( rule__OperandList__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:834:1: ( rule__OperandList__Group__0 )
            {
             before(grammarAccess.getOperandListAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:835:1: ( rule__OperandList__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:835:2: rule__OperandList__Group__0
            {
            pushFollow(FOLLOW_rule__OperandList__Group__0_in_ruleOperandList1691);
            rule__OperandList__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getOperandListAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleOperandList"


    // $ANTLR start "entryRuleOperand"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:847:1: entryRuleOperand : ruleOperand EOF ;
    public final void entryRuleOperand() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:848:1: ( ruleOperand EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:849:1: ruleOperand EOF
            {
             before(grammarAccess.getOperandRule()); 
            pushFollow(FOLLOW_ruleOperand_in_entryRuleOperand1718);
            ruleOperand();

            state._fsp--;

             after(grammarAccess.getOperandRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOperand1725); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:856:1: ruleOperand : ( ( rule__Operand__Group__0 ) ) ;
    public final void ruleOperand() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:860:5: ( ( ( rule__Operand__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:861:1: ( ( rule__Operand__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:861:1: ( ( rule__Operand__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:862:1: ( rule__Operand__Group__0 )
            {
             before(grammarAccess.getOperandAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:863:1: ( rule__Operand__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:863:2: rule__Operand__Group__0
            {
            pushFollow(FOLLOW_rule__Operand__Group__0_in_ruleOperand1755);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:875:1: entryRuleOperandFragment : ruleOperandFragment EOF ;
    public final void entryRuleOperandFragment() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:876:1: ( ruleOperandFragment EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:877:1: ruleOperandFragment EOF
            {
             before(grammarAccess.getOperandFragmentRule()); 
            pushFollow(FOLLOW_ruleOperandFragment_in_entryRuleOperandFragment1782);
            ruleOperandFragment();

            state._fsp--;

             after(grammarAccess.getOperandFragmentRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOperandFragment1789); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:884:1: ruleOperandFragment : ( ( rule__OperandFragment__Alternatives ) ) ;
    public final void ruleOperandFragment() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:888:5: ( ( ( rule__OperandFragment__Alternatives ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:889:1: ( ( rule__OperandFragment__Alternatives ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:889:1: ( ( rule__OperandFragment__Alternatives ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:890:1: ( rule__OperandFragment__Alternatives )
            {
             before(grammarAccess.getOperandFragmentAccess().getAlternatives()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:891:1: ( rule__OperandFragment__Alternatives )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:891:2: rule__OperandFragment__Alternatives
            {
            pushFollow(FOLLOW_rule__OperandFragment__Alternatives_in_ruleOperandFragment1819);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:903:1: entryRuleXOperandFragment : ruleXOperandFragment EOF ;
    public final void entryRuleXOperandFragment() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:904:1: ( ruleXOperandFragment EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:905:1: ruleXOperandFragment EOF
            {
             before(grammarAccess.getXOperandFragmentRule()); 
            pushFollow(FOLLOW_ruleXOperandFragment_in_entryRuleXOperandFragment1846);
            ruleXOperandFragment();

            state._fsp--;

             after(grammarAccess.getXOperandFragmentRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleXOperandFragment1853); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:912:1: ruleXOperandFragment : ( ( rule__XOperandFragment__Alternatives ) ) ;
    public final void ruleXOperandFragment() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:916:5: ( ( ( rule__XOperandFragment__Alternatives ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:917:1: ( ( rule__XOperandFragment__Alternatives ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:917:1: ( ( rule__XOperandFragment__Alternatives ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:918:1: ( rule__XOperandFragment__Alternatives )
            {
             before(grammarAccess.getXOperandFragmentAccess().getAlternatives()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:919:1: ( rule__XOperandFragment__Alternatives )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:919:2: rule__XOperandFragment__Alternatives
            {
            pushFollow(FOLLOW_rule__XOperandFragment__Alternatives_in_ruleXOperandFragment1883);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:931:1: entryRuleParameterOperand : ruleParameterOperand EOF ;
    public final void entryRuleParameterOperand() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:932:1: ( ruleParameterOperand EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:933:1: ruleParameterOperand EOF
            {
             before(grammarAccess.getParameterOperandRule()); 
            pushFollow(FOLLOW_ruleParameterOperand_in_entryRuleParameterOperand1910);
            ruleParameterOperand();

            state._fsp--;

             after(grammarAccess.getParameterOperandRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleParameterOperand1917); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:940:1: ruleParameterOperand : ( ( rule__ParameterOperand__Group__0 ) ) ;
    public final void ruleParameterOperand() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:944:5: ( ( ( rule__ParameterOperand__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:945:1: ( ( rule__ParameterOperand__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:945:1: ( ( rule__ParameterOperand__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:946:1: ( rule__ParameterOperand__Group__0 )
            {
             before(grammarAccess.getParameterOperandAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:947:1: ( rule__ParameterOperand__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:947:2: rule__ParameterOperand__Group__0
            {
            pushFollow(FOLLOW_rule__ParameterOperand__Group__0_in_ruleParameterOperand1947);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:959:1: entryRuleExclamationParameterOperand : ruleExclamationParameterOperand EOF ;
    public final void entryRuleExclamationParameterOperand() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:960:1: ( ruleExclamationParameterOperand EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:961:1: ruleExclamationParameterOperand EOF
            {
             before(grammarAccess.getExclamationParameterOperandRule()); 
            pushFollow(FOLLOW_ruleExclamationParameterOperand_in_entryRuleExclamationParameterOperand1974);
            ruleExclamationParameterOperand();

            state._fsp--;

             after(grammarAccess.getExclamationParameterOperandRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleExclamationParameterOperand1981); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:968:1: ruleExclamationParameterOperand : ( ( rule__ExclamationParameterOperand__Group__0 ) ) ;
    public final void ruleExclamationParameterOperand() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:972:5: ( ( ( rule__ExclamationParameterOperand__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:973:1: ( ( rule__ExclamationParameterOperand__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:973:1: ( ( rule__ExclamationParameterOperand__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:974:1: ( rule__ExclamationParameterOperand__Group__0 )
            {
             before(grammarAccess.getExclamationParameterOperandAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:975:1: ( rule__ExclamationParameterOperand__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:975:2: rule__ExclamationParameterOperand__Group__0
            {
            pushFollow(FOLLOW_rule__ExclamationParameterOperand__Group__0_in_ruleExclamationParameterOperand2011);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:987:1: entryRuleColumnOperand : ruleColumnOperand EOF ;
    public final void entryRuleColumnOperand() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:988:1: ( ruleColumnOperand EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:989:1: ruleColumnOperand EOF
            {
             before(grammarAccess.getColumnOperandRule()); 
            pushFollow(FOLLOW_ruleColumnOperand_in_entryRuleColumnOperand2038);
            ruleColumnOperand();

            state._fsp--;

             after(grammarAccess.getColumnOperandRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleColumnOperand2045); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:996:1: ruleColumnOperand : ( ( rule__ColumnOperand__CfullAssignment ) ) ;
    public final void ruleColumnOperand() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1000:5: ( ( ( rule__ColumnOperand__CfullAssignment ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1001:1: ( ( rule__ColumnOperand__CfullAssignment ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1001:1: ( ( rule__ColumnOperand__CfullAssignment ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1002:1: ( rule__ColumnOperand__CfullAssignment )
            {
             before(grammarAccess.getColumnOperandAccess().getCfullAssignment()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1003:1: ( rule__ColumnOperand__CfullAssignment )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1003:2: rule__ColumnOperand__CfullAssignment
            {
            pushFollow(FOLLOW_rule__ColumnOperand__CfullAssignment_in_ruleColumnOperand2075);
            rule__ColumnOperand__CfullAssignment();

            state._fsp--;


            }

             after(grammarAccess.getColumnOperandAccess().getCfullAssignment()); 

            }


            }

        }
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1015:1: entryRuleSubQueryOperand : ruleSubQueryOperand EOF ;
    public final void entryRuleSubQueryOperand() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1016:1: ( ruleSubQueryOperand EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1017:1: ruleSubQueryOperand EOF
            {
             before(grammarAccess.getSubQueryOperandRule()); 
            pushFollow(FOLLOW_ruleSubQueryOperand_in_entryRuleSubQueryOperand2102);
            ruleSubQueryOperand();

            state._fsp--;

             after(grammarAccess.getSubQueryOperandRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSubQueryOperand2109); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1024:1: ruleSubQueryOperand : ( ( rule__SubQueryOperand__Group__0 ) ) ;
    public final void ruleSubQueryOperand() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1028:5: ( ( ( rule__SubQueryOperand__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1029:1: ( ( rule__SubQueryOperand__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1029:1: ( ( rule__SubQueryOperand__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1030:1: ( rule__SubQueryOperand__Group__0 )
            {
             before(grammarAccess.getSubQueryOperandAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1031:1: ( rule__SubQueryOperand__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1031:2: rule__SubQueryOperand__Group__0
            {
            pushFollow(FOLLOW_rule__SubQueryOperand__Group__0_in_ruleSubQueryOperand2139);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1043:1: entryRuleScalarOperand : ruleScalarOperand EOF ;
    public final void entryRuleScalarOperand() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1044:1: ( ruleScalarOperand EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1045:1: ruleScalarOperand EOF
            {
             before(grammarAccess.getScalarOperandRule()); 
            pushFollow(FOLLOW_ruleScalarOperand_in_entryRuleScalarOperand2166);
            ruleScalarOperand();

            state._fsp--;

             after(grammarAccess.getScalarOperandRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleScalarOperand2173); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1052:1: ruleScalarOperand : ( ( rule__ScalarOperand__Alternatives ) ) ;
    public final void ruleScalarOperand() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1056:5: ( ( ( rule__ScalarOperand__Alternatives ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1057:1: ( ( rule__ScalarOperand__Alternatives ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1057:1: ( ( rule__ScalarOperand__Alternatives ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1058:1: ( rule__ScalarOperand__Alternatives )
            {
             before(grammarAccess.getScalarOperandAccess().getAlternatives()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1059:1: ( rule__ScalarOperand__Alternatives )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1059:2: rule__ScalarOperand__Alternatives
            {
            pushFollow(FOLLOW_rule__ScalarOperand__Alternatives_in_ruleScalarOperand2203);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1071:1: entryRuleStringOperand : ruleStringOperand EOF ;
    public final void entryRuleStringOperand() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1072:1: ( ruleStringOperand EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1073:1: ruleStringOperand EOF
            {
             before(grammarAccess.getStringOperandRule()); 
            pushFollow(FOLLOW_ruleStringOperand_in_entryRuleStringOperand2230);
            ruleStringOperand();

            state._fsp--;

             after(grammarAccess.getStringOperandRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleStringOperand2237); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1080:1: ruleStringOperand : ( RULE_STRING ) ;
    public final void ruleStringOperand() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1084:5: ( ( RULE_STRING ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1085:1: ( RULE_STRING )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1085:1: ( RULE_STRING )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1086:1: RULE_STRING
            {
             before(grammarAccess.getStringOperandAccess().getSTRINGTerminalRuleCall()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleStringOperand2267); 
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


    // $ANTLR start "ruleXFunction"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1100:1: ruleXFunction : ( ( rule__XFunction__Alternatives ) ) ;
    public final void ruleXFunction() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1104:1: ( ( ( rule__XFunction__Alternatives ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1105:1: ( ( rule__XFunction__Alternatives ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1105:1: ( ( rule__XFunction__Alternatives ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1106:1: ( rule__XFunction__Alternatives )
            {
             before(grammarAccess.getXFunctionAccess().getAlternatives()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1107:1: ( rule__XFunction__Alternatives )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1107:2: rule__XFunction__Alternatives
            {
            pushFollow(FOLLOW_rule__XFunction__Alternatives_in_ruleXFunction2303);
            rule__XFunction__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getXFunctionAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleXFunction"


    // $ANTLR start "ruleJoinType"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1119:1: ruleJoinType : ( ( rule__JoinType__Alternatives ) ) ;
    public final void ruleJoinType() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1123:1: ( ( ( rule__JoinType__Alternatives ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1124:1: ( ( rule__JoinType__Alternatives ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1124:1: ( ( rule__JoinType__Alternatives ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1125:1: ( rule__JoinType__Alternatives )
            {
             before(grammarAccess.getJoinTypeAccess().getAlternatives()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1126:1: ( rule__JoinType__Alternatives )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1126:2: rule__JoinType__Alternatives
            {
            pushFollow(FOLLOW_rule__JoinType__Alternatives_in_ruleJoinType2339);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1137:1: rule__ColumnOrAlias__Alternatives : ( ( ( rule__ColumnOrAlias__Group_0__0 ) ) | ( ( rule__ColumnOrAlias__AllColsAssignment_1 ) ) );
    public final void rule__ColumnOrAlias__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1141:1: ( ( ( rule__ColumnOrAlias__Group_0__0 ) ) | ( ( rule__ColumnOrAlias__AllColsAssignment_1 ) ) )
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
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1142:1: ( ( rule__ColumnOrAlias__Group_0__0 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1142:1: ( ( rule__ColumnOrAlias__Group_0__0 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1143:1: ( rule__ColumnOrAlias__Group_0__0 )
                    {
                     before(grammarAccess.getColumnOrAliasAccess().getGroup_0()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1144:1: ( rule__ColumnOrAlias__Group_0__0 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1144:2: rule__ColumnOrAlias__Group_0__0
                    {
                    pushFollow(FOLLOW_rule__ColumnOrAlias__Group_0__0_in_rule__ColumnOrAlias__Alternatives2374);
                    rule__ColumnOrAlias__Group_0__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getColumnOrAliasAccess().getGroup_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1148:6: ( ( rule__ColumnOrAlias__AllColsAssignment_1 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1148:6: ( ( rule__ColumnOrAlias__AllColsAssignment_1 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1149:1: ( rule__ColumnOrAlias__AllColsAssignment_1 )
                    {
                     before(grammarAccess.getColumnOrAliasAccess().getAllColsAssignment_1()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1150:1: ( rule__ColumnOrAlias__AllColsAssignment_1 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1150:2: rule__ColumnOrAlias__AllColsAssignment_1
                    {
                    pushFollow(FOLLOW_rule__ColumnOrAlias__AllColsAssignment_1_in_rule__ColumnOrAlias__Alternatives2392);
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


    // $ANTLR start "rule__OrderByColumnFull__DirectionAlternatives_1_0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1159:1: rule__OrderByColumnFull__DirectionAlternatives_1_0 : ( ( KEYWORD_23 ) | ( KEYWORD_25 ) );
    public final void rule__OrderByColumnFull__DirectionAlternatives_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1163:1: ( ( KEYWORD_23 ) | ( KEYWORD_25 ) )
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==KEYWORD_23) ) {
                alt2=1;
            }
            else if ( (LA2_0==KEYWORD_25) ) {
                alt2=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }
            switch (alt2) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1164:1: ( KEYWORD_23 )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1164:1: ( KEYWORD_23 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1165:1: KEYWORD_23
                    {
                     before(grammarAccess.getOrderByColumnFullAccess().getDirectionASCKeyword_1_0_0()); 
                    match(input,KEYWORD_23,FOLLOW_KEYWORD_23_in_rule__OrderByColumnFull__DirectionAlternatives_1_02426); 
                     after(grammarAccess.getOrderByColumnFullAccess().getDirectionASCKeyword_1_0_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1172:6: ( KEYWORD_25 )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1172:6: ( KEYWORD_25 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1173:1: KEYWORD_25
                    {
                     before(grammarAccess.getOrderByColumnFullAccess().getDirectionDESCKeyword_1_0_1()); 
                    match(input,KEYWORD_25,FOLLOW_KEYWORD_25_in_rule__OrderByColumnFull__DirectionAlternatives_1_02446); 
                     after(grammarAccess.getOrderByColumnFullAccess().getDirectionDESCKeyword_1_0_1()); 

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
    // $ANTLR end "rule__OrderByColumnFull__DirectionAlternatives_1_0"


    // $ANTLR start "rule__ExpressionFragmentSecond__CAlternatives_0_0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1185:1: rule__ExpressionFragmentSecond__CAlternatives_0_0 : ( ( KEYWORD_22 ) | ( KEYWORD_18 ) );
    public final void rule__ExpressionFragmentSecond__CAlternatives_0_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1189:1: ( ( KEYWORD_22 ) | ( KEYWORD_18 ) )
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==KEYWORD_22) ) {
                alt3=1;
            }
            else if ( (LA3_0==KEYWORD_18) ) {
                alt3=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }
            switch (alt3) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1190:1: ( KEYWORD_22 )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1190:1: ( KEYWORD_22 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1191:1: KEYWORD_22
                    {
                     before(grammarAccess.getExpressionFragmentSecondAccess().getCANDKeyword_0_0_0()); 
                    match(input,KEYWORD_22,FOLLOW_KEYWORD_22_in_rule__ExpressionFragmentSecond__CAlternatives_0_02481); 
                     after(grammarAccess.getExpressionFragmentSecondAccess().getCANDKeyword_0_0_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1198:6: ( KEYWORD_18 )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1198:6: ( KEYWORD_18 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1199:1: KEYWORD_18
                    {
                     before(grammarAccess.getExpressionFragmentSecondAccess().getCORKeyword_0_0_1()); 
                    match(input,KEYWORD_18,FOLLOW_KEYWORD_18_in_rule__ExpressionFragmentSecond__CAlternatives_0_02501); 
                     after(grammarAccess.getExpressionFragmentSecondAccess().getCORKeyword_0_0_1()); 

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
    // $ANTLR end "rule__ExpressionFragmentSecond__CAlternatives_0_0"


    // $ANTLR start "rule__ExpressionFragment__Alternatives"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1211:1: rule__ExpressionFragment__Alternatives : ( ( ( rule__ExpressionFragment__ExpgroupAssignment_0 ) ) | ( ( rule__ExpressionFragment__ExpAssignment_1 ) ) | ( ( rule__ExpressionFragment__XexpAssignment_2 ) ) );
    public final void rule__ExpressionFragment__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1215:1: ( ( ( rule__ExpressionFragment__ExpgroupAssignment_0 ) ) | ( ( rule__ExpressionFragment__ExpAssignment_1 ) ) | ( ( rule__ExpressionFragment__XexpAssignment_2 ) ) )
            int alt4=3;
            switch ( input.LA(1) ) {
            case KEYWORD_1:
                {
                int LA4_1 = input.LA(2);

                if ( (LA4_1==KEYWORD_24||(LA4_1>=KEYWORD_20 && LA4_1<=KEYWORD_21)||LA4_1==KEYWORD_1||(LA4_1>=RULE_SIGNED_INT && LA4_1<=RULE_SIGNED_DOUBLE)||LA4_1==RULE_ID||LA4_1==RULE_STRING) ) {
                    alt4=1;
                }
                else if ( (LA4_1==KEYWORD_36) ) {
                    alt4=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 4, 1, input);

                    throw nvae;
                }
                }
                break;
            case KEYWORD_24:
            case KEYWORD_20:
            case RULE_SIGNED_INT:
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
            case KEYWORD_21:
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
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1216:1: ( ( rule__ExpressionFragment__ExpgroupAssignment_0 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1216:1: ( ( rule__ExpressionFragment__ExpgroupAssignment_0 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1217:1: ( rule__ExpressionFragment__ExpgroupAssignment_0 )
                    {
                     before(grammarAccess.getExpressionFragmentAccess().getExpgroupAssignment_0()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1218:1: ( rule__ExpressionFragment__ExpgroupAssignment_0 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1218:2: rule__ExpressionFragment__ExpgroupAssignment_0
                    {
                    pushFollow(FOLLOW_rule__ExpressionFragment__ExpgroupAssignment_0_in_rule__ExpressionFragment__Alternatives2535);
                    rule__ExpressionFragment__ExpgroupAssignment_0();

                    state._fsp--;


                    }

                     after(grammarAccess.getExpressionFragmentAccess().getExpgroupAssignment_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1222:6: ( ( rule__ExpressionFragment__ExpAssignment_1 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1222:6: ( ( rule__ExpressionFragment__ExpAssignment_1 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1223:1: ( rule__ExpressionFragment__ExpAssignment_1 )
                    {
                     before(grammarAccess.getExpressionFragmentAccess().getExpAssignment_1()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1224:1: ( rule__ExpressionFragment__ExpAssignment_1 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1224:2: rule__ExpressionFragment__ExpAssignment_1
                    {
                    pushFollow(FOLLOW_rule__ExpressionFragment__ExpAssignment_1_in_rule__ExpressionFragment__Alternatives2553);
                    rule__ExpressionFragment__ExpAssignment_1();

                    state._fsp--;


                    }

                     after(grammarAccess.getExpressionFragmentAccess().getExpAssignment_1()); 

                    }


                    }
                    break;
                case 3 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1228:6: ( ( rule__ExpressionFragment__XexpAssignment_2 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1228:6: ( ( rule__ExpressionFragment__XexpAssignment_2 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1229:1: ( rule__ExpressionFragment__XexpAssignment_2 )
                    {
                     before(grammarAccess.getExpressionFragmentAccess().getXexpAssignment_2()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1230:1: ( rule__ExpressionFragment__XexpAssignment_2 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1230:2: rule__ExpressionFragment__XexpAssignment_2
                    {
                    pushFollow(FOLLOW_rule__ExpressionFragment__XexpAssignment_2_in_rule__ExpressionFragment__Alternatives2571);
                    rule__ExpressionFragment__XexpAssignment_2();

                    state._fsp--;


                    }

                     after(grammarAccess.getExpressionFragmentAccess().getXexpAssignment_2()); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1239:1: rule__Expression__Alternatives_1 : ( ( ( rule__Expression__IsnullAssignment_1_0 ) ) | ( ( rule__Expression__InAssignment_1_1 ) ) | ( ( rule__Expression__BetweenAssignment_1_2 ) ) | ( ( rule__Expression__LikeAssignment_1_3 ) ) | ( ( rule__Expression__CompAssignment_1_4 ) ) );
    public final void rule__Expression__Alternatives_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1243:1: ( ( ( rule__Expression__IsnullAssignment_1_0 ) ) | ( ( rule__Expression__InAssignment_1_1 ) ) | ( ( rule__Expression__BetweenAssignment_1_2 ) ) | ( ( rule__Expression__LikeAssignment_1_3 ) ) | ( ( rule__Expression__CompAssignment_1_4 ) ) )
            int alt5=5;
            switch ( input.LA(1) ) {
            case KEYWORD_52:
            case KEYWORD_39:
                {
                alt5=1;
                }
                break;
            case KEYWORD_43:
            case KEYWORD_27:
                {
                alt5=2;
                }
                break;
            case KEYWORD_53:
            case KEYWORD_37:
                {
                alt5=3;
                }
                break;
            case KEYWORD_44:
            case KEYWORD_29:
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
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1244:1: ( ( rule__Expression__IsnullAssignment_1_0 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1244:1: ( ( rule__Expression__IsnullAssignment_1_0 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1245:1: ( rule__Expression__IsnullAssignment_1_0 )
                    {
                     before(grammarAccess.getExpressionAccess().getIsnullAssignment_1_0()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1246:1: ( rule__Expression__IsnullAssignment_1_0 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1246:2: rule__Expression__IsnullAssignment_1_0
                    {
                    pushFollow(FOLLOW_rule__Expression__IsnullAssignment_1_0_in_rule__Expression__Alternatives_12604);
                    rule__Expression__IsnullAssignment_1_0();

                    state._fsp--;


                    }

                     after(grammarAccess.getExpressionAccess().getIsnullAssignment_1_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1250:6: ( ( rule__Expression__InAssignment_1_1 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1250:6: ( ( rule__Expression__InAssignment_1_1 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1251:1: ( rule__Expression__InAssignment_1_1 )
                    {
                     before(grammarAccess.getExpressionAccess().getInAssignment_1_1()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1252:1: ( rule__Expression__InAssignment_1_1 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1252:2: rule__Expression__InAssignment_1_1
                    {
                    pushFollow(FOLLOW_rule__Expression__InAssignment_1_1_in_rule__Expression__Alternatives_12622);
                    rule__Expression__InAssignment_1_1();

                    state._fsp--;


                    }

                     after(grammarAccess.getExpressionAccess().getInAssignment_1_1()); 

                    }


                    }
                    break;
                case 3 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1256:6: ( ( rule__Expression__BetweenAssignment_1_2 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1256:6: ( ( rule__Expression__BetweenAssignment_1_2 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1257:1: ( rule__Expression__BetweenAssignment_1_2 )
                    {
                     before(grammarAccess.getExpressionAccess().getBetweenAssignment_1_2()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1258:1: ( rule__Expression__BetweenAssignment_1_2 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1258:2: rule__Expression__BetweenAssignment_1_2
                    {
                    pushFollow(FOLLOW_rule__Expression__BetweenAssignment_1_2_in_rule__Expression__Alternatives_12640);
                    rule__Expression__BetweenAssignment_1_2();

                    state._fsp--;


                    }

                     after(grammarAccess.getExpressionAccess().getBetweenAssignment_1_2()); 

                    }


                    }
                    break;
                case 4 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1262:6: ( ( rule__Expression__LikeAssignment_1_3 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1262:6: ( ( rule__Expression__LikeAssignment_1_3 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1263:1: ( rule__Expression__LikeAssignment_1_3 )
                    {
                     before(grammarAccess.getExpressionAccess().getLikeAssignment_1_3()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1264:1: ( rule__Expression__LikeAssignment_1_3 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1264:2: rule__Expression__LikeAssignment_1_3
                    {
                    pushFollow(FOLLOW_rule__Expression__LikeAssignment_1_3_in_rule__Expression__Alternatives_12658);
                    rule__Expression__LikeAssignment_1_3();

                    state._fsp--;


                    }

                     after(grammarAccess.getExpressionAccess().getLikeAssignment_1_3()); 

                    }


                    }
                    break;
                case 5 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1268:6: ( ( rule__Expression__CompAssignment_1_4 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1268:6: ( ( rule__Expression__CompAssignment_1_4 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1269:1: ( rule__Expression__CompAssignment_1_4 )
                    {
                     before(grammarAccess.getExpressionAccess().getCompAssignment_1_4()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1270:1: ( rule__Expression__CompAssignment_1_4 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1270:2: rule__Expression__CompAssignment_1_4
                    {
                    pushFollow(FOLLOW_rule__Expression__CompAssignment_1_4_in_rule__Expression__Alternatives_12676);
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


    // $ANTLR start "rule__Expression__IsnullAlternatives_1_0_0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1279:1: rule__Expression__IsnullAlternatives_1_0_0 : ( ( KEYWORD_39 ) | ( KEYWORD_52 ) );
    public final void rule__Expression__IsnullAlternatives_1_0_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1283:1: ( ( KEYWORD_39 ) | ( KEYWORD_52 ) )
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==KEYWORD_39) ) {
                alt6=1;
            }
            else if ( (LA6_0==KEYWORD_52) ) {
                alt6=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }
            switch (alt6) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1284:1: ( KEYWORD_39 )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1284:1: ( KEYWORD_39 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1285:1: KEYWORD_39
                    {
                     before(grammarAccess.getExpressionAccess().getIsnullISNULLKeyword_1_0_0_0()); 
                    match(input,KEYWORD_39,FOLLOW_KEYWORD_39_in_rule__Expression__IsnullAlternatives_1_0_02710); 
                     after(grammarAccess.getExpressionAccess().getIsnullISNULLKeyword_1_0_0_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1292:6: ( KEYWORD_52 )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1292:6: ( KEYWORD_52 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1293:1: KEYWORD_52
                    {
                     before(grammarAccess.getExpressionAccess().getIsnullISNOTNULLKeyword_1_0_0_1()); 
                    match(input,KEYWORD_52,FOLLOW_KEYWORD_52_in_rule__Expression__IsnullAlternatives_1_0_02730); 
                     after(grammarAccess.getExpressionAccess().getIsnullISNOTNULLKeyword_1_0_0_1()); 

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
    // $ANTLR end "rule__Expression__IsnullAlternatives_1_0_0"


    // $ANTLR start "rule__Comparison__OperatorAlternatives_0_0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1305:1: rule__Comparison__OperatorAlternatives_0_0 : ( ( KEYWORD_10 ) | ( KEYWORD_14 ) | ( KEYWORD_8 ) | ( KEYWORD_12 ) | ( KEYWORD_9 ) | ( KEYWORD_13 ) );
    public final void rule__Comparison__OperatorAlternatives_0_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1309:1: ( ( KEYWORD_10 ) | ( KEYWORD_14 ) | ( KEYWORD_8 ) | ( KEYWORD_12 ) | ( KEYWORD_9 ) | ( KEYWORD_13 ) )
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
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1310:1: ( KEYWORD_10 )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1310:1: ( KEYWORD_10 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1311:1: KEYWORD_10
                    {
                     before(grammarAccess.getComparisonAccess().getOperatorGreaterThanSignKeyword_0_0_0()); 
                    match(input,KEYWORD_10,FOLLOW_KEYWORD_10_in_rule__Comparison__OperatorAlternatives_0_02765); 
                     after(grammarAccess.getComparisonAccess().getOperatorGreaterThanSignKeyword_0_0_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1318:6: ( KEYWORD_14 )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1318:6: ( KEYWORD_14 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1319:1: KEYWORD_14
                    {
                     before(grammarAccess.getComparisonAccess().getOperatorGreaterThanSignEqualsSignKeyword_0_0_1()); 
                    match(input,KEYWORD_14,FOLLOW_KEYWORD_14_in_rule__Comparison__OperatorAlternatives_0_02785); 
                     after(grammarAccess.getComparisonAccess().getOperatorGreaterThanSignEqualsSignKeyword_0_0_1()); 

                    }


                    }
                    break;
                case 3 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1326:6: ( KEYWORD_8 )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1326:6: ( KEYWORD_8 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1327:1: KEYWORD_8
                    {
                     before(grammarAccess.getComparisonAccess().getOperatorLessThanSignKeyword_0_0_2()); 
                    match(input,KEYWORD_8,FOLLOW_KEYWORD_8_in_rule__Comparison__OperatorAlternatives_0_02805); 
                     after(grammarAccess.getComparisonAccess().getOperatorLessThanSignKeyword_0_0_2()); 

                    }


                    }
                    break;
                case 4 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1334:6: ( KEYWORD_12 )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1334:6: ( KEYWORD_12 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1335:1: KEYWORD_12
                    {
                     before(grammarAccess.getComparisonAccess().getOperatorLessThanSignEqualsSignKeyword_0_0_3()); 
                    match(input,KEYWORD_12,FOLLOW_KEYWORD_12_in_rule__Comparison__OperatorAlternatives_0_02825); 
                     after(grammarAccess.getComparisonAccess().getOperatorLessThanSignEqualsSignKeyword_0_0_3()); 

                    }


                    }
                    break;
                case 5 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1342:6: ( KEYWORD_9 )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1342:6: ( KEYWORD_9 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1343:1: KEYWORD_9
                    {
                     before(grammarAccess.getComparisonAccess().getOperatorEqualsSignKeyword_0_0_4()); 
                    match(input,KEYWORD_9,FOLLOW_KEYWORD_9_in_rule__Comparison__OperatorAlternatives_0_02845); 
                     after(grammarAccess.getComparisonAccess().getOperatorEqualsSignKeyword_0_0_4()); 

                    }


                    }
                    break;
                case 6 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1350:6: ( KEYWORD_13 )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1350:6: ( KEYWORD_13 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1351:1: KEYWORD_13
                    {
                     before(grammarAccess.getComparisonAccess().getOperatorLessThanSignGreaterThanSignKeyword_0_0_5()); 
                    match(input,KEYWORD_13,FOLLOW_KEYWORD_13_in_rule__Comparison__OperatorAlternatives_0_02865); 
                     after(grammarAccess.getComparisonAccess().getOperatorLessThanSignGreaterThanSignKeyword_0_0_5()); 

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
    // $ANTLR end "rule__Comparison__OperatorAlternatives_0_0"


    // $ANTLR start "rule__Like__OpLikeAlternatives_0_0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1363:1: rule__Like__OpLikeAlternatives_0_0 : ( ( KEYWORD_29 ) | ( KEYWORD_44 ) );
    public final void rule__Like__OpLikeAlternatives_0_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1367:1: ( ( KEYWORD_29 ) | ( KEYWORD_44 ) )
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==KEYWORD_29) ) {
                alt8=1;
            }
            else if ( (LA8_0==KEYWORD_44) ) {
                alt8=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;
            }
            switch (alt8) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1368:1: ( KEYWORD_29 )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1368:1: ( KEYWORD_29 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1369:1: KEYWORD_29
                    {
                     before(grammarAccess.getLikeAccess().getOpLikeLIKEKeyword_0_0_0()); 
                    match(input,KEYWORD_29,FOLLOW_KEYWORD_29_in_rule__Like__OpLikeAlternatives_0_02900); 
                     after(grammarAccess.getLikeAccess().getOpLikeLIKEKeyword_0_0_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1376:6: ( KEYWORD_44 )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1376:6: ( KEYWORD_44 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1377:1: KEYWORD_44
                    {
                     before(grammarAccess.getLikeAccess().getOpLikeNOTLIKEKeyword_0_0_1()); 
                    match(input,KEYWORD_44,FOLLOW_KEYWORD_44_in_rule__Like__OpLikeAlternatives_0_02920); 
                     after(grammarAccess.getLikeAccess().getOpLikeNOTLIKEKeyword_0_0_1()); 

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
    // $ANTLR end "rule__Like__OpLikeAlternatives_0_0"


    // $ANTLR start "rule__Between__OpBetweenAlternatives_0_0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1389:1: rule__Between__OpBetweenAlternatives_0_0 : ( ( KEYWORD_37 ) | ( KEYWORD_53 ) );
    public final void rule__Between__OpBetweenAlternatives_0_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1393:1: ( ( KEYWORD_37 ) | ( KEYWORD_53 ) )
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==KEYWORD_37) ) {
                alt9=1;
            }
            else if ( (LA9_0==KEYWORD_53) ) {
                alt9=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                throw nvae;
            }
            switch (alt9) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1394:1: ( KEYWORD_37 )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1394:1: ( KEYWORD_37 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1395:1: KEYWORD_37
                    {
                     before(grammarAccess.getBetweenAccess().getOpBetweenBETWEENKeyword_0_0_0()); 
                    match(input,KEYWORD_37,FOLLOW_KEYWORD_37_in_rule__Between__OpBetweenAlternatives_0_02955); 
                     after(grammarAccess.getBetweenAccess().getOpBetweenBETWEENKeyword_0_0_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1402:6: ( KEYWORD_53 )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1402:6: ( KEYWORD_53 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1403:1: KEYWORD_53
                    {
                     before(grammarAccess.getBetweenAccess().getOpBetweenNOTBETWEENKeyword_0_0_1()); 
                    match(input,KEYWORD_53,FOLLOW_KEYWORD_53_in_rule__Between__OpBetweenAlternatives_0_02975); 
                     after(grammarAccess.getBetweenAccess().getOpBetweenNOTBETWEENKeyword_0_0_1()); 

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
    // $ANTLR end "rule__Between__OpBetweenAlternatives_0_0"


    // $ANTLR start "rule__InOperator__OpAlternatives_1_0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1415:1: rule__InOperator__OpAlternatives_1_0 : ( ( KEYWORD_43 ) | ( KEYWORD_27 ) );
    public final void rule__InOperator__OpAlternatives_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1419:1: ( ( KEYWORD_43 ) | ( KEYWORD_27 ) )
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==KEYWORD_43) ) {
                alt10=1;
            }
            else if ( (LA10_0==KEYWORD_27) ) {
                alt10=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 10, 0, input);

                throw nvae;
            }
            switch (alt10) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1420:1: ( KEYWORD_43 )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1420:1: ( KEYWORD_43 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1421:1: KEYWORD_43
                    {
                     before(grammarAccess.getInOperatorAccess().getOpNOTINKeyword_1_0_0()); 
                    match(input,KEYWORD_43,FOLLOW_KEYWORD_43_in_rule__InOperator__OpAlternatives_1_03010); 
                     after(grammarAccess.getInOperatorAccess().getOpNOTINKeyword_1_0_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1428:6: ( KEYWORD_27 )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1428:6: ( KEYWORD_27 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1429:1: KEYWORD_27
                    {
                     before(grammarAccess.getInOperatorAccess().getOpINKeyword_1_0_1()); 
                    match(input,KEYWORD_27,FOLLOW_KEYWORD_27_in_rule__InOperator__OpAlternatives_1_03030); 
                     after(grammarAccess.getInOperatorAccess().getOpINKeyword_1_0_1()); 

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
    // $ANTLR end "rule__InOperator__OpAlternatives_1_0"


    // $ANTLR start "rule__InOperator__Alternatives_2"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1441:1: rule__InOperator__Alternatives_2 : ( ( ( rule__InOperator__SubqueryAssignment_2_0 ) ) | ( ( rule__InOperator__OpListAssignment_2_1 ) ) );
    public final void rule__InOperator__Alternatives_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1445:1: ( ( ( rule__InOperator__SubqueryAssignment_2_0 ) ) | ( ( rule__InOperator__OpListAssignment_2_1 ) ) )
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==KEYWORD_1) ) {
                alt11=1;
            }
            else if ( (LA11_0==KEYWORD_24||LA11_0==KEYWORD_20||(LA11_0>=RULE_SIGNED_INT && LA11_0<=RULE_SIGNED_DOUBLE)||LA11_0==RULE_STRING) ) {
                alt11=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 11, 0, input);

                throw nvae;
            }
            switch (alt11) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1446:1: ( ( rule__InOperator__SubqueryAssignment_2_0 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1446:1: ( ( rule__InOperator__SubqueryAssignment_2_0 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1447:1: ( rule__InOperator__SubqueryAssignment_2_0 )
                    {
                     before(grammarAccess.getInOperatorAccess().getSubqueryAssignment_2_0()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1448:1: ( rule__InOperator__SubqueryAssignment_2_0 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1448:2: rule__InOperator__SubqueryAssignment_2_0
                    {
                    pushFollow(FOLLOW_rule__InOperator__SubqueryAssignment_2_0_in_rule__InOperator__Alternatives_23064);
                    rule__InOperator__SubqueryAssignment_2_0();

                    state._fsp--;


                    }

                     after(grammarAccess.getInOperatorAccess().getSubqueryAssignment_2_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1452:6: ( ( rule__InOperator__OpListAssignment_2_1 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1452:6: ( ( rule__InOperator__OpListAssignment_2_1 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1453:1: ( rule__InOperator__OpListAssignment_2_1 )
                    {
                     before(grammarAccess.getInOperatorAccess().getOpListAssignment_2_1()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1454:1: ( rule__InOperator__OpListAssignment_2_1 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1454:2: rule__InOperator__OpListAssignment_2_1
                    {
                    pushFollow(FOLLOW_rule__InOperator__OpListAssignment_2_1_in_rule__InOperator__Alternatives_23082);
                    rule__InOperator__OpListAssignment_2_1();

                    state._fsp--;


                    }

                     after(grammarAccess.getInOperatorAccess().getOpListAssignment_2_1()); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1463:1: rule__Operand__Alternatives_1_1_0 : ( ( KEYWORD_3 ) | ( KEYWORD_5 ) | ( RULE_STAR ) | ( KEYWORD_7 ) | ( KEYWORD_19 ) );
    public final void rule__Operand__Alternatives_1_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1467:1: ( ( KEYWORD_3 ) | ( KEYWORD_5 ) | ( RULE_STAR ) | ( KEYWORD_7 ) | ( KEYWORD_19 ) )
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
            case KEYWORD_19:
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
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1468:1: ( KEYWORD_3 )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1468:1: ( KEYWORD_3 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1469:1: KEYWORD_3
                    {
                     before(grammarAccess.getOperandAccess().getPlusSignKeyword_1_1_0_0()); 
                    match(input,KEYWORD_3,FOLLOW_KEYWORD_3_in_rule__Operand__Alternatives_1_1_03116); 
                     after(grammarAccess.getOperandAccess().getPlusSignKeyword_1_1_0_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1476:6: ( KEYWORD_5 )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1476:6: ( KEYWORD_5 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1477:1: KEYWORD_5
                    {
                     before(grammarAccess.getOperandAccess().getHyphenMinusKeyword_1_1_0_1()); 
                    match(input,KEYWORD_5,FOLLOW_KEYWORD_5_in_rule__Operand__Alternatives_1_1_03136); 
                     after(grammarAccess.getOperandAccess().getHyphenMinusKeyword_1_1_0_1()); 

                    }


                    }
                    break;
                case 3 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1484:6: ( RULE_STAR )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1484:6: ( RULE_STAR )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1485:1: RULE_STAR
                    {
                     before(grammarAccess.getOperandAccess().getSTARTerminalRuleCall_1_1_0_2()); 
                    match(input,RULE_STAR,FOLLOW_RULE_STAR_in_rule__Operand__Alternatives_1_1_03155); 
                     after(grammarAccess.getOperandAccess().getSTARTerminalRuleCall_1_1_0_2()); 

                    }


                    }
                    break;
                case 4 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1490:6: ( KEYWORD_7 )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1490:6: ( KEYWORD_7 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1491:1: KEYWORD_7
                    {
                     before(grammarAccess.getOperandAccess().getSolidusKeyword_1_1_0_3()); 
                    match(input,KEYWORD_7,FOLLOW_KEYWORD_7_in_rule__Operand__Alternatives_1_1_03173); 
                     after(grammarAccess.getOperandAccess().getSolidusKeyword_1_1_0_3()); 

                    }


                    }
                    break;
                case 5 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1498:6: ( KEYWORD_19 )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1498:6: ( KEYWORD_19 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1499:1: KEYWORD_19
                    {
                     before(grammarAccess.getOperandAccess().getVerticalLineVerticalLineKeyword_1_1_0_4()); 
                    match(input,KEYWORD_19,FOLLOW_KEYWORD_19_in_rule__Operand__Alternatives_1_1_03193); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1511:1: rule__OperandFragment__Alternatives : ( ( ( rule__OperandFragment__ColumnAssignment_0 ) ) | ( ( rule__OperandFragment__XopAssignment_1 ) ) | ( ( rule__OperandFragment__SubqAssignment_2 ) ) );
    public final void rule__OperandFragment__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1515:1: ( ( ( rule__OperandFragment__ColumnAssignment_0 ) ) | ( ( rule__OperandFragment__XopAssignment_1 ) ) | ( ( rule__OperandFragment__SubqAssignment_2 ) ) )
            int alt13=3;
            switch ( input.LA(1) ) {
            case RULE_ID:
                {
                alt13=1;
                }
                break;
            case KEYWORD_24:
            case KEYWORD_20:
            case RULE_SIGNED_INT:
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
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1516:1: ( ( rule__OperandFragment__ColumnAssignment_0 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1516:1: ( ( rule__OperandFragment__ColumnAssignment_0 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1517:1: ( rule__OperandFragment__ColumnAssignment_0 )
                    {
                     before(grammarAccess.getOperandFragmentAccess().getColumnAssignment_0()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1518:1: ( rule__OperandFragment__ColumnAssignment_0 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1518:2: rule__OperandFragment__ColumnAssignment_0
                    {
                    pushFollow(FOLLOW_rule__OperandFragment__ColumnAssignment_0_in_rule__OperandFragment__Alternatives3227);
                    rule__OperandFragment__ColumnAssignment_0();

                    state._fsp--;


                    }

                     after(grammarAccess.getOperandFragmentAccess().getColumnAssignment_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1522:6: ( ( rule__OperandFragment__XopAssignment_1 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1522:6: ( ( rule__OperandFragment__XopAssignment_1 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1523:1: ( rule__OperandFragment__XopAssignment_1 )
                    {
                     before(grammarAccess.getOperandFragmentAccess().getXopAssignment_1()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1524:1: ( rule__OperandFragment__XopAssignment_1 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1524:2: rule__OperandFragment__XopAssignment_1
                    {
                    pushFollow(FOLLOW_rule__OperandFragment__XopAssignment_1_in_rule__OperandFragment__Alternatives3245);
                    rule__OperandFragment__XopAssignment_1();

                    state._fsp--;


                    }

                     after(grammarAccess.getOperandFragmentAccess().getXopAssignment_1()); 

                    }


                    }
                    break;
                case 3 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1528:6: ( ( rule__OperandFragment__SubqAssignment_2 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1528:6: ( ( rule__OperandFragment__SubqAssignment_2 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1529:1: ( rule__OperandFragment__SubqAssignment_2 )
                    {
                     before(grammarAccess.getOperandFragmentAccess().getSubqAssignment_2()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1530:1: ( rule__OperandFragment__SubqAssignment_2 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1530:2: rule__OperandFragment__SubqAssignment_2
                    {
                    pushFollow(FOLLOW_rule__OperandFragment__SubqAssignment_2_in_rule__OperandFragment__Alternatives3263);
                    rule__OperandFragment__SubqAssignment_2();

                    state._fsp--;


                    }

                     after(grammarAccess.getOperandFragmentAccess().getSubqAssignment_2()); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1539:1: rule__XOperandFragment__Alternatives : ( ( ( rule__XOperandFragment__ParamAssignment_0 ) ) | ( ( rule__XOperandFragment__EparamAssignment_1 ) ) | ( ( rule__XOperandFragment__ScalarAssignment_2 ) ) );
    public final void rule__XOperandFragment__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1543:1: ( ( ( rule__XOperandFragment__ParamAssignment_0 ) ) | ( ( rule__XOperandFragment__EparamAssignment_1 ) ) | ( ( rule__XOperandFragment__ScalarAssignment_2 ) ) )
            int alt14=3;
            switch ( input.LA(1) ) {
            case KEYWORD_20:
                {
                alt14=1;
                }
                break;
            case KEYWORD_24:
                {
                alt14=2;
                }
                break;
            case RULE_SIGNED_INT:
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
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1544:1: ( ( rule__XOperandFragment__ParamAssignment_0 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1544:1: ( ( rule__XOperandFragment__ParamAssignment_0 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1545:1: ( rule__XOperandFragment__ParamAssignment_0 )
                    {
                     before(grammarAccess.getXOperandFragmentAccess().getParamAssignment_0()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1546:1: ( rule__XOperandFragment__ParamAssignment_0 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1546:2: rule__XOperandFragment__ParamAssignment_0
                    {
                    pushFollow(FOLLOW_rule__XOperandFragment__ParamAssignment_0_in_rule__XOperandFragment__Alternatives3296);
                    rule__XOperandFragment__ParamAssignment_0();

                    state._fsp--;


                    }

                     after(grammarAccess.getXOperandFragmentAccess().getParamAssignment_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1550:6: ( ( rule__XOperandFragment__EparamAssignment_1 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1550:6: ( ( rule__XOperandFragment__EparamAssignment_1 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1551:1: ( rule__XOperandFragment__EparamAssignment_1 )
                    {
                     before(grammarAccess.getXOperandFragmentAccess().getEparamAssignment_1()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1552:1: ( rule__XOperandFragment__EparamAssignment_1 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1552:2: rule__XOperandFragment__EparamAssignment_1
                    {
                    pushFollow(FOLLOW_rule__XOperandFragment__EparamAssignment_1_in_rule__XOperandFragment__Alternatives3314);
                    rule__XOperandFragment__EparamAssignment_1();

                    state._fsp--;


                    }

                     after(grammarAccess.getXOperandFragmentAccess().getEparamAssignment_1()); 

                    }


                    }
                    break;
                case 3 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1556:6: ( ( rule__XOperandFragment__ScalarAssignment_2 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1556:6: ( ( rule__XOperandFragment__ScalarAssignment_2 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1557:1: ( rule__XOperandFragment__ScalarAssignment_2 )
                    {
                     before(grammarAccess.getXOperandFragmentAccess().getScalarAssignment_2()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1558:1: ( rule__XOperandFragment__ScalarAssignment_2 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1558:2: rule__XOperandFragment__ScalarAssignment_2
                    {
                    pushFollow(FOLLOW_rule__XOperandFragment__ScalarAssignment_2_in_rule__XOperandFragment__Alternatives3332);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1567:1: rule__ScalarOperand__Alternatives : ( ( ( rule__ScalarOperand__SointAssignment_0 ) ) | ( ( rule__ScalarOperand__SostrAssignment_1 ) ) | ( ( rule__ScalarOperand__SodblAssignment_2 ) ) | ( ( rule__ScalarOperand__SodateAssignment_3 ) ) | ( ( rule__ScalarOperand__SotimeAssignment_4 ) ) | ( ( rule__ScalarOperand__SodtAssignment_5 ) ) );
    public final void rule__ScalarOperand__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1571:1: ( ( ( rule__ScalarOperand__SointAssignment_0 ) ) | ( ( rule__ScalarOperand__SostrAssignment_1 ) ) | ( ( rule__ScalarOperand__SodblAssignment_2 ) ) | ( ( rule__ScalarOperand__SodateAssignment_3 ) ) | ( ( rule__ScalarOperand__SotimeAssignment_4 ) ) | ( ( rule__ScalarOperand__SodtAssignment_5 ) ) )
            int alt15=6;
            switch ( input.LA(1) ) {
            case RULE_SIGNED_INT:
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
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1572:1: ( ( rule__ScalarOperand__SointAssignment_0 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1572:1: ( ( rule__ScalarOperand__SointAssignment_0 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1573:1: ( rule__ScalarOperand__SointAssignment_0 )
                    {
                     before(grammarAccess.getScalarOperandAccess().getSointAssignment_0()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1574:1: ( rule__ScalarOperand__SointAssignment_0 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1574:2: rule__ScalarOperand__SointAssignment_0
                    {
                    pushFollow(FOLLOW_rule__ScalarOperand__SointAssignment_0_in_rule__ScalarOperand__Alternatives3365);
                    rule__ScalarOperand__SointAssignment_0();

                    state._fsp--;


                    }

                     after(grammarAccess.getScalarOperandAccess().getSointAssignment_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1578:6: ( ( rule__ScalarOperand__SostrAssignment_1 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1578:6: ( ( rule__ScalarOperand__SostrAssignment_1 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1579:1: ( rule__ScalarOperand__SostrAssignment_1 )
                    {
                     before(grammarAccess.getScalarOperandAccess().getSostrAssignment_1()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1580:1: ( rule__ScalarOperand__SostrAssignment_1 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1580:2: rule__ScalarOperand__SostrAssignment_1
                    {
                    pushFollow(FOLLOW_rule__ScalarOperand__SostrAssignment_1_in_rule__ScalarOperand__Alternatives3383);
                    rule__ScalarOperand__SostrAssignment_1();

                    state._fsp--;


                    }

                     after(grammarAccess.getScalarOperandAccess().getSostrAssignment_1()); 

                    }


                    }
                    break;
                case 3 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1584:6: ( ( rule__ScalarOperand__SodblAssignment_2 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1584:6: ( ( rule__ScalarOperand__SodblAssignment_2 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1585:1: ( rule__ScalarOperand__SodblAssignment_2 )
                    {
                     before(grammarAccess.getScalarOperandAccess().getSodblAssignment_2()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1586:1: ( rule__ScalarOperand__SodblAssignment_2 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1586:2: rule__ScalarOperand__SodblAssignment_2
                    {
                    pushFollow(FOLLOW_rule__ScalarOperand__SodblAssignment_2_in_rule__ScalarOperand__Alternatives3401);
                    rule__ScalarOperand__SodblAssignment_2();

                    state._fsp--;


                    }

                     after(grammarAccess.getScalarOperandAccess().getSodblAssignment_2()); 

                    }


                    }
                    break;
                case 4 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1590:6: ( ( rule__ScalarOperand__SodateAssignment_3 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1590:6: ( ( rule__ScalarOperand__SodateAssignment_3 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1591:1: ( rule__ScalarOperand__SodateAssignment_3 )
                    {
                     before(grammarAccess.getScalarOperandAccess().getSodateAssignment_3()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1592:1: ( rule__ScalarOperand__SodateAssignment_3 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1592:2: rule__ScalarOperand__SodateAssignment_3
                    {
                    pushFollow(FOLLOW_rule__ScalarOperand__SodateAssignment_3_in_rule__ScalarOperand__Alternatives3419);
                    rule__ScalarOperand__SodateAssignment_3();

                    state._fsp--;


                    }

                     after(grammarAccess.getScalarOperandAccess().getSodateAssignment_3()); 

                    }


                    }
                    break;
                case 5 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1596:6: ( ( rule__ScalarOperand__SotimeAssignment_4 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1596:6: ( ( rule__ScalarOperand__SotimeAssignment_4 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1597:1: ( rule__ScalarOperand__SotimeAssignment_4 )
                    {
                     before(grammarAccess.getScalarOperandAccess().getSotimeAssignment_4()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1598:1: ( rule__ScalarOperand__SotimeAssignment_4 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1598:2: rule__ScalarOperand__SotimeAssignment_4
                    {
                    pushFollow(FOLLOW_rule__ScalarOperand__SotimeAssignment_4_in_rule__ScalarOperand__Alternatives3437);
                    rule__ScalarOperand__SotimeAssignment_4();

                    state._fsp--;


                    }

                     after(grammarAccess.getScalarOperandAccess().getSotimeAssignment_4()); 

                    }


                    }
                    break;
                case 6 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1602:6: ( ( rule__ScalarOperand__SodtAssignment_5 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1602:6: ( ( rule__ScalarOperand__SodtAssignment_5 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1603:1: ( rule__ScalarOperand__SodtAssignment_5 )
                    {
                     before(grammarAccess.getScalarOperandAccess().getSodtAssignment_5()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1604:1: ( rule__ScalarOperand__SodtAssignment_5 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1604:2: rule__ScalarOperand__SodtAssignment_5
                    {
                    pushFollow(FOLLOW_rule__ScalarOperand__SodtAssignment_5_in_rule__ScalarOperand__Alternatives3455);
                    rule__ScalarOperand__SodtAssignment_5();

                    state._fsp--;


                    }

                     after(grammarAccess.getScalarOperandAccess().getSodtAssignment_5()); 

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


    // $ANTLR start "rule__XFunction__Alternatives"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1613:1: rule__XFunction__Alternatives : ( ( ( KEYWORD_16 ) ) | ( ( KEYWORD_32 ) ) | ( ( KEYWORD_30 ) ) | ( ( KEYWORD_45 ) ) | ( ( KEYWORD_28 ) ) | ( ( KEYWORD_38 ) ) | ( ( KEYWORD_31 ) ) | ( ( KEYWORD_48 ) ) | ( ( KEYWORD_37 ) ) | ( ( KEYWORD_49 ) ) | ( ( KEYWORD_47 ) ) | ( ( KEYWORD_40 ) ) );
    public final void rule__XFunction__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1617:1: ( ( ( KEYWORD_16 ) ) | ( ( KEYWORD_32 ) ) | ( ( KEYWORD_30 ) ) | ( ( KEYWORD_45 ) ) | ( ( KEYWORD_28 ) ) | ( ( KEYWORD_38 ) ) | ( ( KEYWORD_31 ) ) | ( ( KEYWORD_48 ) ) | ( ( KEYWORD_37 ) ) | ( ( KEYWORD_49 ) ) | ( ( KEYWORD_47 ) ) | ( ( KEYWORD_40 ) ) )
            int alt16=12;
            switch ( input.LA(1) ) {
            case KEYWORD_16:
                {
                alt16=1;
                }
                break;
            case KEYWORD_32:
                {
                alt16=2;
                }
                break;
            case KEYWORD_30:
                {
                alt16=3;
                }
                break;
            case KEYWORD_45:
                {
                alt16=4;
                }
                break;
            case KEYWORD_28:
                {
                alt16=5;
                }
                break;
            case KEYWORD_38:
                {
                alt16=6;
                }
                break;
            case KEYWORD_31:
                {
                alt16=7;
                }
                break;
            case KEYWORD_48:
                {
                alt16=8;
                }
                break;
            case KEYWORD_37:
                {
                alt16=9;
                }
                break;
            case KEYWORD_49:
                {
                alt16=10;
                }
                break;
            case KEYWORD_47:
                {
                alt16=11;
                }
                break;
            case KEYWORD_40:
                {
                alt16=12;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 16, 0, input);

                throw nvae;
            }

            switch (alt16) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1618:1: ( ( KEYWORD_16 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1618:1: ( ( KEYWORD_16 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1619:1: ( KEYWORD_16 )
                    {
                     before(grammarAccess.getXFunctionAccess().getXinEnumLiteralDeclaration_0()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1620:1: ( KEYWORD_16 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1620:3: KEYWORD_16
                    {
                    match(input,KEYWORD_16,FOLLOW_KEYWORD_16_in_rule__XFunction__Alternatives3489); 

                    }

                     after(grammarAccess.getXFunctionAccess().getXinEnumLiteralDeclaration_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1625:6: ( ( KEYWORD_32 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1625:6: ( ( KEYWORD_32 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1626:1: ( KEYWORD_32 )
                    {
                     before(grammarAccess.getXFunctionAccess().getXnotinEnumLiteralDeclaration_1()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1627:1: ( KEYWORD_32 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1627:3: KEYWORD_32
                    {
                    match(input,KEYWORD_32,FOLLOW_KEYWORD_32_in_rule__XFunction__Alternatives3509); 

                    }

                     after(grammarAccess.getXFunctionAccess().getXnotinEnumLiteralDeclaration_1()); 

                    }


                    }
                    break;
                case 3 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1632:6: ( ( KEYWORD_30 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1632:6: ( ( KEYWORD_30 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1633:1: ( KEYWORD_30 )
                    {
                     before(grammarAccess.getXFunctionAccess().getXeqEnumLiteralDeclaration_2()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1634:1: ( KEYWORD_30 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1634:3: KEYWORD_30
                    {
                    match(input,KEYWORD_30,FOLLOW_KEYWORD_30_in_rule__XFunction__Alternatives3529); 

                    }

                     after(grammarAccess.getXFunctionAccess().getXeqEnumLiteralDeclaration_2()); 

                    }


                    }
                    break;
                case 4 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1639:6: ( ( KEYWORD_45 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1639:6: ( ( KEYWORD_45 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1640:1: ( KEYWORD_45 )
                    {
                     before(grammarAccess.getXFunctionAccess().getXnoteqEnumLiteralDeclaration_3()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1641:1: ( KEYWORD_45 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1641:3: KEYWORD_45
                    {
                    match(input,KEYWORD_45,FOLLOW_KEYWORD_45_in_rule__XFunction__Alternatives3549); 

                    }

                     after(grammarAccess.getXFunctionAccess().getXnoteqEnumLiteralDeclaration_3()); 

                    }


                    }
                    break;
                case 5 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1646:6: ( ( KEYWORD_28 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1646:6: ( ( KEYWORD_28 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1647:1: ( KEYWORD_28 )
                    {
                     before(grammarAccess.getXFunctionAccess().getXlsEnumLiteralDeclaration_4()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1648:1: ( KEYWORD_28 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1648:3: KEYWORD_28
                    {
                    match(input,KEYWORD_28,FOLLOW_KEYWORD_28_in_rule__XFunction__Alternatives3569); 

                    }

                     after(grammarAccess.getXFunctionAccess().getXlsEnumLiteralDeclaration_4()); 

                    }


                    }
                    break;
                case 6 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1653:6: ( ( KEYWORD_38 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1653:6: ( ( KEYWORD_38 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1654:1: ( KEYWORD_38 )
                    {
                     before(grammarAccess.getXFunctionAccess().getXgtEnumLiteralDeclaration_5()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1655:1: ( KEYWORD_38 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1655:3: KEYWORD_38
                    {
                    match(input,KEYWORD_38,FOLLOW_KEYWORD_38_in_rule__XFunction__Alternatives3589); 

                    }

                     after(grammarAccess.getXFunctionAccess().getXgtEnumLiteralDeclaration_5()); 

                    }


                    }
                    break;
                case 7 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1660:6: ( ( KEYWORD_31 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1660:6: ( ( KEYWORD_31 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1661:1: ( KEYWORD_31 )
                    {
                     before(grammarAccess.getXFunctionAccess().getXlsrEnumLiteralDeclaration_6()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1662:1: ( KEYWORD_31 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1662:3: KEYWORD_31
                    {
                    match(input,KEYWORD_31,FOLLOW_KEYWORD_31_in_rule__XFunction__Alternatives3609); 

                    }

                     after(grammarAccess.getXFunctionAccess().getXlsrEnumLiteralDeclaration_6()); 

                    }


                    }
                    break;
                case 8 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1667:6: ( ( KEYWORD_48 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1667:6: ( ( KEYWORD_48 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1668:1: ( KEYWORD_48 )
                    {
                     before(grammarAccess.getXFunctionAccess().getXgtlEnumLiteralDeclaration_7()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1669:1: ( KEYWORD_48 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1669:3: KEYWORD_48
                    {
                    match(input,KEYWORD_48,FOLLOW_KEYWORD_48_in_rule__XFunction__Alternatives3629); 

                    }

                     after(grammarAccess.getXFunctionAccess().getXgtlEnumLiteralDeclaration_7()); 

                    }


                    }
                    break;
                case 9 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1674:6: ( ( KEYWORD_37 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1674:6: ( ( KEYWORD_37 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1675:1: ( KEYWORD_37 )
                    {
                     before(grammarAccess.getXFunctionAccess().getXbwnEnumLiteralDeclaration_8()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1676:1: ( KEYWORD_37 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1676:3: KEYWORD_37
                    {
                    match(input,KEYWORD_37,FOLLOW_KEYWORD_37_in_rule__XFunction__Alternatives3649); 

                    }

                     after(grammarAccess.getXFunctionAccess().getXbwnEnumLiteralDeclaration_8()); 

                    }


                    }
                    break;
                case 10 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1681:6: ( ( KEYWORD_49 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1681:6: ( ( KEYWORD_49 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1682:1: ( KEYWORD_49 )
                    {
                     before(grammarAccess.getXFunctionAccess().getXbwncEnumLiteralDeclaration_9()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1683:1: ( KEYWORD_49 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1683:3: KEYWORD_49
                    {
                    match(input,KEYWORD_49,FOLLOW_KEYWORD_49_in_rule__XFunction__Alternatives3669); 

                    }

                     after(grammarAccess.getXFunctionAccess().getXbwncEnumLiteralDeclaration_9()); 

                    }


                    }
                    break;
                case 11 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1688:6: ( ( KEYWORD_47 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1688:6: ( ( KEYWORD_47 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1689:1: ( KEYWORD_47 )
                    {
                     before(grammarAccess.getXFunctionAccess().getXbwnlEnumLiteralDeclaration_10()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1690:1: ( KEYWORD_47 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1690:3: KEYWORD_47
                    {
                    match(input,KEYWORD_47,FOLLOW_KEYWORD_47_in_rule__XFunction__Alternatives3689); 

                    }

                     after(grammarAccess.getXFunctionAccess().getXbwnlEnumLiteralDeclaration_10()); 

                    }


                    }
                    break;
                case 12 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1695:6: ( ( KEYWORD_40 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1695:6: ( ( KEYWORD_40 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1696:1: ( KEYWORD_40 )
                    {
                     before(grammarAccess.getXFunctionAccess().getXbwnrEnumLiteralDeclaration_11()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1697:1: ( KEYWORD_40 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1697:3: KEYWORD_40
                    {
                    match(input,KEYWORD_40,FOLLOW_KEYWORD_40_in_rule__XFunction__Alternatives3709); 

                    }

                     after(grammarAccess.getXFunctionAccess().getXbwnrEnumLiteralDeclaration_11()); 

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
    // $ANTLR end "rule__XFunction__Alternatives"


    // $ANTLR start "rule__JoinType__Alternatives"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1707:1: rule__JoinType__Alternatives : ( ( ( KEYWORD_51 ) ) | ( ( KEYWORD_55 ) ) | ( ( KEYWORD_56 ) ) | ( ( KEYWORD_54 ) ) | ( ( KEYWORD_50 ) ) );
    public final void rule__JoinType__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1711:1: ( ( ( KEYWORD_51 ) ) | ( ( KEYWORD_55 ) ) | ( ( KEYWORD_56 ) ) | ( ( KEYWORD_54 ) ) | ( ( KEYWORD_50 ) ) )
            int alt17=5;
            switch ( input.LA(1) ) {
            case KEYWORD_51:
                {
                alt17=1;
                }
                break;
            case KEYWORD_55:
                {
                alt17=2;
                }
                break;
            case KEYWORD_56:
                {
                alt17=3;
                }
                break;
            case KEYWORD_54:
                {
                alt17=4;
                }
                break;
            case KEYWORD_50:
                {
                alt17=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 17, 0, input);

                throw nvae;
            }

            switch (alt17) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1712:1: ( ( KEYWORD_51 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1712:1: ( ( KEYWORD_51 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1713:1: ( KEYWORD_51 )
                    {
                     before(grammarAccess.getJoinTypeAccess().getInnerJoinEnumLiteralDeclaration_0()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1714:1: ( KEYWORD_51 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1714:3: KEYWORD_51
                    {
                    match(input,KEYWORD_51,FOLLOW_KEYWORD_51_in_rule__JoinType__Alternatives3744); 

                    }

                     after(grammarAccess.getJoinTypeAccess().getInnerJoinEnumLiteralDeclaration_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1719:6: ( ( KEYWORD_55 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1719:6: ( ( KEYWORD_55 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1720:1: ( KEYWORD_55 )
                    {
                     before(grammarAccess.getJoinTypeAccess().getLeftOuterJoinEnumLiteralDeclaration_1()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1721:1: ( KEYWORD_55 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1721:3: KEYWORD_55
                    {
                    match(input,KEYWORD_55,FOLLOW_KEYWORD_55_in_rule__JoinType__Alternatives3764); 

                    }

                     after(grammarAccess.getJoinTypeAccess().getLeftOuterJoinEnumLiteralDeclaration_1()); 

                    }


                    }
                    break;
                case 3 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1726:6: ( ( KEYWORD_56 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1726:6: ( ( KEYWORD_56 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1727:1: ( KEYWORD_56 )
                    {
                     before(grammarAccess.getJoinTypeAccess().getRightOuterJoinEnumLiteralDeclaration_2()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1728:1: ( KEYWORD_56 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1728:3: KEYWORD_56
                    {
                    match(input,KEYWORD_56,FOLLOW_KEYWORD_56_in_rule__JoinType__Alternatives3784); 

                    }

                     after(grammarAccess.getJoinTypeAccess().getRightOuterJoinEnumLiteralDeclaration_2()); 

                    }


                    }
                    break;
                case 4 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1733:6: ( ( KEYWORD_54 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1733:6: ( ( KEYWORD_54 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1734:1: ( KEYWORD_54 )
                    {
                     before(grammarAccess.getJoinTypeAccess().getFullOuterJoinEnumLiteralDeclaration_3()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1735:1: ( KEYWORD_54 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1735:3: KEYWORD_54
                    {
                    match(input,KEYWORD_54,FOLLOW_KEYWORD_54_in_rule__JoinType__Alternatives3804); 

                    }

                     after(grammarAccess.getJoinTypeAccess().getFullOuterJoinEnumLiteralDeclaration_3()); 

                    }


                    }
                    break;
                case 5 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1740:6: ( ( KEYWORD_50 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1740:6: ( ( KEYWORD_50 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1741:1: ( KEYWORD_50 )
                    {
                     before(grammarAccess.getJoinTypeAccess().getCrossJoinEnumLiteralDeclaration_4()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1742:1: ( KEYWORD_50 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1742:3: KEYWORD_50
                    {
                    match(input,KEYWORD_50,FOLLOW_KEYWORD_50_in_rule__JoinType__Alternatives3824); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1754:1: rule__Model__Group__0 : rule__Model__Group__0__Impl rule__Model__Group__1 ;
    public final void rule__Model__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1758:1: ( rule__Model__Group__0__Impl rule__Model__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1759:2: rule__Model__Group__0__Impl rule__Model__Group__1
            {
            pushFollow(FOLLOW_rule__Model__Group__0__Impl_in_rule__Model__Group__03856);
            rule__Model__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Model__Group__1_in_rule__Model__Group__03859);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1766:1: rule__Model__Group__0__Impl : ( ruleSelect ) ;
    public final void rule__Model__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1770:1: ( ( ruleSelect ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1771:1: ( ruleSelect )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1771:1: ( ruleSelect )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1772:1: ruleSelect
            {
             before(grammarAccess.getModelAccess().getSelectParserRuleCall_0()); 
            pushFollow(FOLLOW_ruleSelect_in_rule__Model__Group__0__Impl3886);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1783:1: rule__Model__Group__1 : rule__Model__Group__1__Impl rule__Model__Group__2 ;
    public final void rule__Model__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1787:1: ( rule__Model__Group__1__Impl rule__Model__Group__2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1788:2: rule__Model__Group__1__Impl rule__Model__Group__2
            {
            pushFollow(FOLLOW_rule__Model__Group__1__Impl_in_rule__Model__Group__13915);
            rule__Model__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Model__Group__2_in_rule__Model__Group__13918);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1795:1: rule__Model__Group__1__Impl : ( ( rule__Model__Group_1__0 )? ) ;
    public final void rule__Model__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1799:1: ( ( ( rule__Model__Group_1__0 )? ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1800:1: ( ( rule__Model__Group_1__0 )? )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1800:1: ( ( rule__Model__Group_1__0 )? )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1801:1: ( rule__Model__Group_1__0 )?
            {
             before(grammarAccess.getModelAccess().getGroup_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1802:1: ( rule__Model__Group_1__0 )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==KEYWORD_33) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1802:2: rule__Model__Group_1__0
                    {
                    pushFollow(FOLLOW_rule__Model__Group_1__0_in_rule__Model__Group__1__Impl3945);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1812:1: rule__Model__Group__2 : rule__Model__Group__2__Impl ;
    public final void rule__Model__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1816:1: ( rule__Model__Group__2__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1817:2: rule__Model__Group__2__Impl
            {
            pushFollow(FOLLOW_rule__Model__Group__2__Impl_in_rule__Model__Group__23976);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1823:1: rule__Model__Group__2__Impl : ( ( rule__Model__Group_2__0 )? ) ;
    public final void rule__Model__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1827:1: ( ( ( rule__Model__Group_2__0 )? ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1828:1: ( ( rule__Model__Group_2__0 )? )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1828:1: ( ( rule__Model__Group_2__0 )? )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1829:1: ( rule__Model__Group_2__0 )?
            {
             before(grammarAccess.getModelAccess().getGroup_2()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1830:1: ( rule__Model__Group_2__0 )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==KEYWORD_46) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1830:2: rule__Model__Group_2__0
                    {
                    pushFollow(FOLLOW_rule__Model__Group_2__0_in_rule__Model__Group__2__Impl4003);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1846:1: rule__Model__Group_1__0 : rule__Model__Group_1__0__Impl rule__Model__Group_1__1 ;
    public final void rule__Model__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1850:1: ( rule__Model__Group_1__0__Impl rule__Model__Group_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1851:2: rule__Model__Group_1__0__Impl rule__Model__Group_1__1
            {
            pushFollow(FOLLOW_rule__Model__Group_1__0__Impl_in_rule__Model__Group_1__04040);
            rule__Model__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Model__Group_1__1_in_rule__Model__Group_1__04043);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1858:1: rule__Model__Group_1__0__Impl : ( () ) ;
    public final void rule__Model__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1862:1: ( ( () ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1863:1: ( () )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1863:1: ( () )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1864:1: ()
            {
             before(grammarAccess.getModelAccess().getOrSelectEntriesAction_1_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1865:1: ()
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1867:1: 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1877:1: rule__Model__Group_1__1 : rule__Model__Group_1__1__Impl ;
    public final void rule__Model__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1881:1: ( rule__Model__Group_1__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1882:2: rule__Model__Group_1__1__Impl
            {
            pushFollow(FOLLOW_rule__Model__Group_1__1__Impl_in_rule__Model__Group_1__14101);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1888:1: rule__Model__Group_1__1__Impl : ( ( ( rule__Model__Group_1_1__0 ) ) ( ( rule__Model__Group_1_1__0 )* ) ) ;
    public final void rule__Model__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1892:1: ( ( ( ( rule__Model__Group_1_1__0 ) ) ( ( rule__Model__Group_1_1__0 )* ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1893:1: ( ( ( rule__Model__Group_1_1__0 ) ) ( ( rule__Model__Group_1_1__0 )* ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1893:1: ( ( ( rule__Model__Group_1_1__0 ) ) ( ( rule__Model__Group_1_1__0 )* ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1894:1: ( ( rule__Model__Group_1_1__0 ) ) ( ( rule__Model__Group_1_1__0 )* )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1894:1: ( ( rule__Model__Group_1_1__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1895:1: ( rule__Model__Group_1_1__0 )
            {
             before(grammarAccess.getModelAccess().getGroup_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1896:1: ( rule__Model__Group_1_1__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1896:2: rule__Model__Group_1_1__0
            {
            pushFollow(FOLLOW_rule__Model__Group_1_1__0_in_rule__Model__Group_1__1__Impl4130);
            rule__Model__Group_1_1__0();

            state._fsp--;


            }

             after(grammarAccess.getModelAccess().getGroup_1_1()); 

            }

            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1899:1: ( ( rule__Model__Group_1_1__0 )* )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1900:1: ( rule__Model__Group_1_1__0 )*
            {
             before(grammarAccess.getModelAccess().getGroup_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1901:1: ( rule__Model__Group_1_1__0 )*
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( (LA20_0==KEYWORD_33) ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1901:2: rule__Model__Group_1_1__0
            	    {
            	    pushFollow(FOLLOW_rule__Model__Group_1_1__0_in_rule__Model__Group_1__1__Impl4142);
            	    rule__Model__Group_1_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop20;
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1916:1: rule__Model__Group_1_1__0 : rule__Model__Group_1_1__0__Impl rule__Model__Group_1_1__1 ;
    public final void rule__Model__Group_1_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1920:1: ( rule__Model__Group_1_1__0__Impl rule__Model__Group_1_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1921:2: rule__Model__Group_1_1__0__Impl rule__Model__Group_1_1__1
            {
            pushFollow(FOLLOW_rule__Model__Group_1_1__0__Impl_in_rule__Model__Group_1_1__04179);
            rule__Model__Group_1_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Model__Group_1_1__1_in_rule__Model__Group_1_1__04182);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1928:1: rule__Model__Group_1_1__0__Impl : ( KEYWORD_33 ) ;
    public final void rule__Model__Group_1_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1932:1: ( ( KEYWORD_33 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1933:1: ( KEYWORD_33 )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1933:1: ( KEYWORD_33 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1934:1: KEYWORD_33
            {
             before(grammarAccess.getModelAccess().getUNIONKeyword_1_1_0()); 
            match(input,KEYWORD_33,FOLLOW_KEYWORD_33_in_rule__Model__Group_1_1__0__Impl4210); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1947:1: rule__Model__Group_1_1__1 : rule__Model__Group_1_1__1__Impl ;
    public final void rule__Model__Group_1_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1951:1: ( rule__Model__Group_1_1__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1952:2: rule__Model__Group_1_1__1__Impl
            {
            pushFollow(FOLLOW_rule__Model__Group_1_1__1__Impl_in_rule__Model__Group_1_1__14241);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1958:1: rule__Model__Group_1_1__1__Impl : ( ( rule__Model__EntriesAssignment_1_1_1 ) ) ;
    public final void rule__Model__Group_1_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1962:1: ( ( ( rule__Model__EntriesAssignment_1_1_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1963:1: ( ( rule__Model__EntriesAssignment_1_1_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1963:1: ( ( rule__Model__EntriesAssignment_1_1_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1964:1: ( rule__Model__EntriesAssignment_1_1_1 )
            {
             before(grammarAccess.getModelAccess().getEntriesAssignment_1_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1965:1: ( rule__Model__EntriesAssignment_1_1_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1965:2: rule__Model__EntriesAssignment_1_1_1
            {
            pushFollow(FOLLOW_rule__Model__EntriesAssignment_1_1_1_in_rule__Model__Group_1_1__1__Impl4268);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1979:1: rule__Model__Group_2__0 : rule__Model__Group_2__0__Impl rule__Model__Group_2__1 ;
    public final void rule__Model__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1983:1: ( rule__Model__Group_2__0__Impl rule__Model__Group_2__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1984:2: rule__Model__Group_2__0__Impl rule__Model__Group_2__1
            {
            pushFollow(FOLLOW_rule__Model__Group_2__0__Impl_in_rule__Model__Group_2__04302);
            rule__Model__Group_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Model__Group_2__1_in_rule__Model__Group_2__04305);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1991:1: rule__Model__Group_2__0__Impl : ( KEYWORD_46 ) ;
    public final void rule__Model__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1995:1: ( ( KEYWORD_46 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1996:1: ( KEYWORD_46 )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1996:1: ( KEYWORD_46 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1997:1: KEYWORD_46
            {
             before(grammarAccess.getModelAccess().getORDERBYKeyword_2_0()); 
            match(input,KEYWORD_46,FOLLOW_KEYWORD_46_in_rule__Model__Group_2__0__Impl4333); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2010:1: rule__Model__Group_2__1 : rule__Model__Group_2__1__Impl ;
    public final void rule__Model__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2014:1: ( rule__Model__Group_2__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2015:2: rule__Model__Group_2__1__Impl
            {
            pushFollow(FOLLOW_rule__Model__Group_2__1__Impl_in_rule__Model__Group_2__14364);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2021:1: rule__Model__Group_2__1__Impl : ( ( rule__Model__OrderByEntryAssignment_2_1 ) ) ;
    public final void rule__Model__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2025:1: ( ( ( rule__Model__OrderByEntryAssignment_2_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2026:1: ( ( rule__Model__OrderByEntryAssignment_2_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2026:1: ( ( rule__Model__OrderByEntryAssignment_2_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2027:1: ( rule__Model__OrderByEntryAssignment_2_1 )
            {
             before(grammarAccess.getModelAccess().getOrderByEntryAssignment_2_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2028:1: ( rule__Model__OrderByEntryAssignment_2_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2028:2: rule__Model__OrderByEntryAssignment_2_1
            {
            pushFollow(FOLLOW_rule__Model__OrderByEntryAssignment_2_1_in_rule__Model__Group_2__1__Impl4391);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2042:1: rule__Select__Group__0 : rule__Select__Group__0__Impl rule__Select__Group__1 ;
    public final void rule__Select__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2046:1: ( rule__Select__Group__0__Impl rule__Select__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2047:2: rule__Select__Group__0__Impl rule__Select__Group__1
            {
            pushFollow(FOLLOW_rule__Select__Group__0__Impl_in_rule__Select__Group__04425);
            rule__Select__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Select__Group__1_in_rule__Select__Group__04428);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2054:1: rule__Select__Group__0__Impl : ( ( rule__Select__SelectAssignment_0 ) ) ;
    public final void rule__Select__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2058:1: ( ( ( rule__Select__SelectAssignment_0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2059:1: ( ( rule__Select__SelectAssignment_0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2059:1: ( ( rule__Select__SelectAssignment_0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2060:1: ( rule__Select__SelectAssignment_0 )
            {
             before(grammarAccess.getSelectAccess().getSelectAssignment_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2061:1: ( rule__Select__SelectAssignment_0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2061:2: rule__Select__SelectAssignment_0
            {
            pushFollow(FOLLOW_rule__Select__SelectAssignment_0_in_rule__Select__Group__0__Impl4455);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2071:1: rule__Select__Group__1 : rule__Select__Group__1__Impl rule__Select__Group__2 ;
    public final void rule__Select__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2075:1: ( rule__Select__Group__1__Impl rule__Select__Group__2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2076:2: rule__Select__Group__1__Impl rule__Select__Group__2
            {
            pushFollow(FOLLOW_rule__Select__Group__1__Impl_in_rule__Select__Group__14485);
            rule__Select__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Select__Group__2_in_rule__Select__Group__14488);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2083:1: rule__Select__Group__1__Impl : ( ( KEYWORD_41 )? ) ;
    public final void rule__Select__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2087:1: ( ( ( KEYWORD_41 )? ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2088:1: ( ( KEYWORD_41 )? )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2088:1: ( ( KEYWORD_41 )? )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2089:1: ( KEYWORD_41 )?
            {
             before(grammarAccess.getSelectAccess().getDISTINCTKeyword_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2090:1: ( KEYWORD_41 )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==KEYWORD_41) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2091:2: KEYWORD_41
                    {
                    match(input,KEYWORD_41,FOLLOW_KEYWORD_41_in_rule__Select__Group__1__Impl4517); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2102:1: rule__Select__Group__2 : rule__Select__Group__2__Impl rule__Select__Group__3 ;
    public final void rule__Select__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2106:1: ( rule__Select__Group__2__Impl rule__Select__Group__3 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2107:2: rule__Select__Group__2__Impl rule__Select__Group__3
            {
            pushFollow(FOLLOW_rule__Select__Group__2__Impl_in_rule__Select__Group__24550);
            rule__Select__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Select__Group__3_in_rule__Select__Group__24553);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2114:1: rule__Select__Group__2__Impl : ( ( rule__Select__ColsAssignment_2 ) ) ;
    public final void rule__Select__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2118:1: ( ( ( rule__Select__ColsAssignment_2 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2119:1: ( ( rule__Select__ColsAssignment_2 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2119:1: ( ( rule__Select__ColsAssignment_2 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2120:1: ( rule__Select__ColsAssignment_2 )
            {
             before(grammarAccess.getSelectAccess().getColsAssignment_2()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2121:1: ( rule__Select__ColsAssignment_2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2121:2: rule__Select__ColsAssignment_2
            {
            pushFollow(FOLLOW_rule__Select__ColsAssignment_2_in_rule__Select__Group__2__Impl4580);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2131:1: rule__Select__Group__3 : rule__Select__Group__3__Impl rule__Select__Group__4 ;
    public final void rule__Select__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2135:1: ( rule__Select__Group__3__Impl rule__Select__Group__4 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2136:2: rule__Select__Group__3__Impl rule__Select__Group__4
            {
            pushFollow(FOLLOW_rule__Select__Group__3__Impl_in_rule__Select__Group__34610);
            rule__Select__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Select__Group__4_in_rule__Select__Group__34613);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2143:1: rule__Select__Group__3__Impl : ( KEYWORD_26 ) ;
    public final void rule__Select__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2147:1: ( ( KEYWORD_26 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2148:1: ( KEYWORD_26 )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2148:1: ( KEYWORD_26 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2149:1: KEYWORD_26
            {
             before(grammarAccess.getSelectAccess().getFROMKeyword_3()); 
            match(input,KEYWORD_26,FOLLOW_KEYWORD_26_in_rule__Select__Group__3__Impl4641); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2162:1: rule__Select__Group__4 : rule__Select__Group__4__Impl rule__Select__Group__5 ;
    public final void rule__Select__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2166:1: ( rule__Select__Group__4__Impl rule__Select__Group__5 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2167:2: rule__Select__Group__4__Impl rule__Select__Group__5
            {
            pushFollow(FOLLOW_rule__Select__Group__4__Impl_in_rule__Select__Group__44672);
            rule__Select__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Select__Group__5_in_rule__Select__Group__44675);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2174:1: rule__Select__Group__4__Impl : ( ( rule__Select__TblAssignment_4 ) ) ;
    public final void rule__Select__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2178:1: ( ( ( rule__Select__TblAssignment_4 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2179:1: ( ( rule__Select__TblAssignment_4 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2179:1: ( ( rule__Select__TblAssignment_4 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2180:1: ( rule__Select__TblAssignment_4 )
            {
             before(grammarAccess.getSelectAccess().getTblAssignment_4()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2181:1: ( rule__Select__TblAssignment_4 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2181:2: rule__Select__TblAssignment_4
            {
            pushFollow(FOLLOW_rule__Select__TblAssignment_4_in_rule__Select__Group__4__Impl4702);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2191:1: rule__Select__Group__5 : rule__Select__Group__5__Impl rule__Select__Group__6 ;
    public final void rule__Select__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2195:1: ( rule__Select__Group__5__Impl rule__Select__Group__6 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2196:2: rule__Select__Group__5__Impl rule__Select__Group__6
            {
            pushFollow(FOLLOW_rule__Select__Group__5__Impl_in_rule__Select__Group__54732);
            rule__Select__Group__5__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Select__Group__6_in_rule__Select__Group__54735);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2203:1: rule__Select__Group__5__Impl : ( ( rule__Select__Group_5__0 )? ) ;
    public final void rule__Select__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2207:1: ( ( ( rule__Select__Group_5__0 )? ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2208:1: ( ( rule__Select__Group_5__0 )? )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2208:1: ( ( rule__Select__Group_5__0 )? )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2209:1: ( rule__Select__Group_5__0 )?
            {
             before(grammarAccess.getSelectAccess().getGroup_5()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2210:1: ( rule__Select__Group_5__0 )?
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==KEYWORD_34) ) {
                alt22=1;
            }
            switch (alt22) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2210:2: rule__Select__Group_5__0
                    {
                    pushFollow(FOLLOW_rule__Select__Group_5__0_in_rule__Select__Group__5__Impl4762);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2220:1: rule__Select__Group__6 : rule__Select__Group__6__Impl rule__Select__Group__7 ;
    public final void rule__Select__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2224:1: ( rule__Select__Group__6__Impl rule__Select__Group__7 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2225:2: rule__Select__Group__6__Impl rule__Select__Group__7
            {
            pushFollow(FOLLOW_rule__Select__Group__6__Impl_in_rule__Select__Group__64793);
            rule__Select__Group__6__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Select__Group__7_in_rule__Select__Group__64796);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2232:1: rule__Select__Group__6__Impl : ( ( rule__Select__Group_6__0 )? ) ;
    public final void rule__Select__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2236:1: ( ( ( rule__Select__Group_6__0 )? ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2237:1: ( ( rule__Select__Group_6__0 )? )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2237:1: ( ( rule__Select__Group_6__0 )? )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2238:1: ( rule__Select__Group_6__0 )?
            {
             before(grammarAccess.getSelectAccess().getGroup_6()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2239:1: ( rule__Select__Group_6__0 )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==KEYWORD_42) ) {
                alt23=1;
            }
            switch (alt23) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2239:2: rule__Select__Group_6__0
                    {
                    pushFollow(FOLLOW_rule__Select__Group_6__0_in_rule__Select__Group__6__Impl4823);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2249:1: rule__Select__Group__7 : rule__Select__Group__7__Impl ;
    public final void rule__Select__Group__7() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2253:1: ( rule__Select__Group__7__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2254:2: rule__Select__Group__7__Impl
            {
            pushFollow(FOLLOW_rule__Select__Group__7__Impl_in_rule__Select__Group__74854);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2260:1: rule__Select__Group__7__Impl : ( ( rule__Select__Group_7__0 )? ) ;
    public final void rule__Select__Group__7__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2264:1: ( ( ( rule__Select__Group_7__0 )? ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2265:1: ( ( rule__Select__Group_7__0 )? )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2265:1: ( ( rule__Select__Group_7__0 )? )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2266:1: ( rule__Select__Group_7__0 )?
            {
             before(grammarAccess.getSelectAccess().getGroup_7()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2267:1: ( rule__Select__Group_7__0 )?
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( (LA24_0==KEYWORD_35) ) {
                alt24=1;
            }
            switch (alt24) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2267:2: rule__Select__Group_7__0
                    {
                    pushFollow(FOLLOW_rule__Select__Group_7__0_in_rule__Select__Group__7__Impl4881);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2293:1: rule__Select__Group_5__0 : rule__Select__Group_5__0__Impl rule__Select__Group_5__1 ;
    public final void rule__Select__Group_5__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2297:1: ( rule__Select__Group_5__0__Impl rule__Select__Group_5__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2298:2: rule__Select__Group_5__0__Impl rule__Select__Group_5__1
            {
            pushFollow(FOLLOW_rule__Select__Group_5__0__Impl_in_rule__Select__Group_5__04928);
            rule__Select__Group_5__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Select__Group_5__1_in_rule__Select__Group_5__04931);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2305:1: rule__Select__Group_5__0__Impl : ( KEYWORD_34 ) ;
    public final void rule__Select__Group_5__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2309:1: ( ( KEYWORD_34 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2310:1: ( KEYWORD_34 )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2310:1: ( KEYWORD_34 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2311:1: KEYWORD_34
            {
             before(grammarAccess.getSelectAccess().getWHEREKeyword_5_0()); 
            match(input,KEYWORD_34,FOLLOW_KEYWORD_34_in_rule__Select__Group_5__0__Impl4959); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2324:1: rule__Select__Group_5__1 : rule__Select__Group_5__1__Impl ;
    public final void rule__Select__Group_5__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2328:1: ( rule__Select__Group_5__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2329:2: rule__Select__Group_5__1__Impl
            {
            pushFollow(FOLLOW_rule__Select__Group_5__1__Impl_in_rule__Select__Group_5__14990);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2335:1: rule__Select__Group_5__1__Impl : ( ( rule__Select__WhereExpressionAssignment_5_1 ) ) ;
    public final void rule__Select__Group_5__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2339:1: ( ( ( rule__Select__WhereExpressionAssignment_5_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2340:1: ( ( rule__Select__WhereExpressionAssignment_5_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2340:1: ( ( rule__Select__WhereExpressionAssignment_5_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2341:1: ( rule__Select__WhereExpressionAssignment_5_1 )
            {
             before(grammarAccess.getSelectAccess().getWhereExpressionAssignment_5_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2342:1: ( rule__Select__WhereExpressionAssignment_5_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2342:2: rule__Select__WhereExpressionAssignment_5_1
            {
            pushFollow(FOLLOW_rule__Select__WhereExpressionAssignment_5_1_in_rule__Select__Group_5__1__Impl5017);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2356:1: rule__Select__Group_6__0 : rule__Select__Group_6__0__Impl rule__Select__Group_6__1 ;
    public final void rule__Select__Group_6__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2360:1: ( rule__Select__Group_6__0__Impl rule__Select__Group_6__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2361:2: rule__Select__Group_6__0__Impl rule__Select__Group_6__1
            {
            pushFollow(FOLLOW_rule__Select__Group_6__0__Impl_in_rule__Select__Group_6__05051);
            rule__Select__Group_6__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Select__Group_6__1_in_rule__Select__Group_6__05054);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2368:1: rule__Select__Group_6__0__Impl : ( KEYWORD_42 ) ;
    public final void rule__Select__Group_6__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2372:1: ( ( KEYWORD_42 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2373:1: ( KEYWORD_42 )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2373:1: ( KEYWORD_42 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2374:1: KEYWORD_42
            {
             before(grammarAccess.getSelectAccess().getGROUPBYKeyword_6_0()); 
            match(input,KEYWORD_42,FOLLOW_KEYWORD_42_in_rule__Select__Group_6__0__Impl5082); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2387:1: rule__Select__Group_6__1 : rule__Select__Group_6__1__Impl ;
    public final void rule__Select__Group_6__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2391:1: ( rule__Select__Group_6__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2392:2: rule__Select__Group_6__1__Impl
            {
            pushFollow(FOLLOW_rule__Select__Group_6__1__Impl_in_rule__Select__Group_6__15113);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2398:1: rule__Select__Group_6__1__Impl : ( ( rule__Select__GroupByEntryAssignment_6_1 ) ) ;
    public final void rule__Select__Group_6__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2402:1: ( ( ( rule__Select__GroupByEntryAssignment_6_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2403:1: ( ( rule__Select__GroupByEntryAssignment_6_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2403:1: ( ( rule__Select__GroupByEntryAssignment_6_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2404:1: ( rule__Select__GroupByEntryAssignment_6_1 )
            {
             before(grammarAccess.getSelectAccess().getGroupByEntryAssignment_6_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2405:1: ( rule__Select__GroupByEntryAssignment_6_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2405:2: rule__Select__GroupByEntryAssignment_6_1
            {
            pushFollow(FOLLOW_rule__Select__GroupByEntryAssignment_6_1_in_rule__Select__Group_6__1__Impl5140);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2419:1: rule__Select__Group_7__0 : rule__Select__Group_7__0__Impl rule__Select__Group_7__1 ;
    public final void rule__Select__Group_7__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2423:1: ( rule__Select__Group_7__0__Impl rule__Select__Group_7__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2424:2: rule__Select__Group_7__0__Impl rule__Select__Group_7__1
            {
            pushFollow(FOLLOW_rule__Select__Group_7__0__Impl_in_rule__Select__Group_7__05174);
            rule__Select__Group_7__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Select__Group_7__1_in_rule__Select__Group_7__05177);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2431:1: rule__Select__Group_7__0__Impl : ( KEYWORD_35 ) ;
    public final void rule__Select__Group_7__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2435:1: ( ( KEYWORD_35 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2436:1: ( KEYWORD_35 )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2436:1: ( KEYWORD_35 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2437:1: KEYWORD_35
            {
             before(grammarAccess.getSelectAccess().getHAVINGKeyword_7_0()); 
            match(input,KEYWORD_35,FOLLOW_KEYWORD_35_in_rule__Select__Group_7__0__Impl5205); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2450:1: rule__Select__Group_7__1 : rule__Select__Group_7__1__Impl ;
    public final void rule__Select__Group_7__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2454:1: ( rule__Select__Group_7__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2455:2: rule__Select__Group_7__1__Impl
            {
            pushFollow(FOLLOW_rule__Select__Group_7__1__Impl_in_rule__Select__Group_7__15236);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2461:1: rule__Select__Group_7__1__Impl : ( ( rule__Select__HavingEntryAssignment_7_1 ) ) ;
    public final void rule__Select__Group_7__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2465:1: ( ( ( rule__Select__HavingEntryAssignment_7_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2466:1: ( ( rule__Select__HavingEntryAssignment_7_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2466:1: ( ( rule__Select__HavingEntryAssignment_7_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2467:1: ( rule__Select__HavingEntryAssignment_7_1 )
            {
             before(grammarAccess.getSelectAccess().getHavingEntryAssignment_7_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2468:1: ( rule__Select__HavingEntryAssignment_7_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2468:2: rule__Select__HavingEntryAssignment_7_1
            {
            pushFollow(FOLLOW_rule__Select__HavingEntryAssignment_7_1_in_rule__Select__Group_7__1__Impl5263);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2482:1: rule__Columns__Group__0 : rule__Columns__Group__0__Impl rule__Columns__Group__1 ;
    public final void rule__Columns__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2486:1: ( rule__Columns__Group__0__Impl rule__Columns__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2487:2: rule__Columns__Group__0__Impl rule__Columns__Group__1
            {
            pushFollow(FOLLOW_rule__Columns__Group__0__Impl_in_rule__Columns__Group__05297);
            rule__Columns__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Columns__Group__1_in_rule__Columns__Group__05300);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2494:1: rule__Columns__Group__0__Impl : ( ruleColumnOrAlias ) ;
    public final void rule__Columns__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2498:1: ( ( ruleColumnOrAlias ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2499:1: ( ruleColumnOrAlias )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2499:1: ( ruleColumnOrAlias )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2500:1: ruleColumnOrAlias
            {
             before(grammarAccess.getColumnsAccess().getColumnOrAliasParserRuleCall_0()); 
            pushFollow(FOLLOW_ruleColumnOrAlias_in_rule__Columns__Group__0__Impl5327);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2511:1: rule__Columns__Group__1 : rule__Columns__Group__1__Impl ;
    public final void rule__Columns__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2515:1: ( rule__Columns__Group__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2516:2: rule__Columns__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__Columns__Group__1__Impl_in_rule__Columns__Group__15356);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2522:1: rule__Columns__Group__1__Impl : ( ( rule__Columns__Group_1__0 )? ) ;
    public final void rule__Columns__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2526:1: ( ( ( rule__Columns__Group_1__0 )? ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2527:1: ( ( rule__Columns__Group_1__0 )? )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2527:1: ( ( rule__Columns__Group_1__0 )? )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2528:1: ( rule__Columns__Group_1__0 )?
            {
             before(grammarAccess.getColumnsAccess().getGroup_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2529:1: ( rule__Columns__Group_1__0 )?
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==KEYWORD_4) ) {
                alt25=1;
            }
            switch (alt25) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2529:2: rule__Columns__Group_1__0
                    {
                    pushFollow(FOLLOW_rule__Columns__Group_1__0_in_rule__Columns__Group__1__Impl5383);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2543:1: rule__Columns__Group_1__0 : rule__Columns__Group_1__0__Impl rule__Columns__Group_1__1 ;
    public final void rule__Columns__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2547:1: ( rule__Columns__Group_1__0__Impl rule__Columns__Group_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2548:2: rule__Columns__Group_1__0__Impl rule__Columns__Group_1__1
            {
            pushFollow(FOLLOW_rule__Columns__Group_1__0__Impl_in_rule__Columns__Group_1__05418);
            rule__Columns__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Columns__Group_1__1_in_rule__Columns__Group_1__05421);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2555:1: rule__Columns__Group_1__0__Impl : ( () ) ;
    public final void rule__Columns__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2559:1: ( ( () ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2560:1: ( () )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2560:1: ( () )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2561:1: ()
            {
             before(grammarAccess.getColumnsAccess().getOrColumnEntriesAction_1_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2562:1: ()
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2564:1: 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2574:1: rule__Columns__Group_1__1 : rule__Columns__Group_1__1__Impl ;
    public final void rule__Columns__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2578:1: ( rule__Columns__Group_1__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2579:2: rule__Columns__Group_1__1__Impl
            {
            pushFollow(FOLLOW_rule__Columns__Group_1__1__Impl_in_rule__Columns__Group_1__15479);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2585:1: rule__Columns__Group_1__1__Impl : ( ( ( rule__Columns__Group_1_1__0 ) ) ( ( rule__Columns__Group_1_1__0 )* ) ) ;
    public final void rule__Columns__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2589:1: ( ( ( ( rule__Columns__Group_1_1__0 ) ) ( ( rule__Columns__Group_1_1__0 )* ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2590:1: ( ( ( rule__Columns__Group_1_1__0 ) ) ( ( rule__Columns__Group_1_1__0 )* ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2590:1: ( ( ( rule__Columns__Group_1_1__0 ) ) ( ( rule__Columns__Group_1_1__0 )* ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2591:1: ( ( rule__Columns__Group_1_1__0 ) ) ( ( rule__Columns__Group_1_1__0 )* )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2591:1: ( ( rule__Columns__Group_1_1__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2592:1: ( rule__Columns__Group_1_1__0 )
            {
             before(grammarAccess.getColumnsAccess().getGroup_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2593:1: ( rule__Columns__Group_1_1__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2593:2: rule__Columns__Group_1_1__0
            {
            pushFollow(FOLLOW_rule__Columns__Group_1_1__0_in_rule__Columns__Group_1__1__Impl5508);
            rule__Columns__Group_1_1__0();

            state._fsp--;


            }

             after(grammarAccess.getColumnsAccess().getGroup_1_1()); 

            }

            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2596:1: ( ( rule__Columns__Group_1_1__0 )* )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2597:1: ( rule__Columns__Group_1_1__0 )*
            {
             before(grammarAccess.getColumnsAccess().getGroup_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2598:1: ( rule__Columns__Group_1_1__0 )*
            loop26:
            do {
                int alt26=2;
                int LA26_0 = input.LA(1);

                if ( (LA26_0==KEYWORD_4) ) {
                    alt26=1;
                }


                switch (alt26) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2598:2: rule__Columns__Group_1_1__0
            	    {
            	    pushFollow(FOLLOW_rule__Columns__Group_1_1__0_in_rule__Columns__Group_1__1__Impl5520);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2613:1: rule__Columns__Group_1_1__0 : rule__Columns__Group_1_1__0__Impl rule__Columns__Group_1_1__1 ;
    public final void rule__Columns__Group_1_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2617:1: ( rule__Columns__Group_1_1__0__Impl rule__Columns__Group_1_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2618:2: rule__Columns__Group_1_1__0__Impl rule__Columns__Group_1_1__1
            {
            pushFollow(FOLLOW_rule__Columns__Group_1_1__0__Impl_in_rule__Columns__Group_1_1__05557);
            rule__Columns__Group_1_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Columns__Group_1_1__1_in_rule__Columns__Group_1_1__05560);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2625:1: rule__Columns__Group_1_1__0__Impl : ( KEYWORD_4 ) ;
    public final void rule__Columns__Group_1_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2629:1: ( ( KEYWORD_4 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2630:1: ( KEYWORD_4 )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2630:1: ( KEYWORD_4 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2631:1: KEYWORD_4
            {
             before(grammarAccess.getColumnsAccess().getCommaKeyword_1_1_0()); 
            match(input,KEYWORD_4,FOLLOW_KEYWORD_4_in_rule__Columns__Group_1_1__0__Impl5588); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2644:1: rule__Columns__Group_1_1__1 : rule__Columns__Group_1_1__1__Impl ;
    public final void rule__Columns__Group_1_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2648:1: ( rule__Columns__Group_1_1__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2649:2: rule__Columns__Group_1_1__1__Impl
            {
            pushFollow(FOLLOW_rule__Columns__Group_1_1__1__Impl_in_rule__Columns__Group_1_1__15619);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2655:1: rule__Columns__Group_1_1__1__Impl : ( ( rule__Columns__EntriesAssignment_1_1_1 ) ) ;
    public final void rule__Columns__Group_1_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2659:1: ( ( ( rule__Columns__EntriesAssignment_1_1_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2660:1: ( ( rule__Columns__EntriesAssignment_1_1_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2660:1: ( ( rule__Columns__EntriesAssignment_1_1_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2661:1: ( rule__Columns__EntriesAssignment_1_1_1 )
            {
             before(grammarAccess.getColumnsAccess().getEntriesAssignment_1_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2662:1: ( rule__Columns__EntriesAssignment_1_1_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2662:2: rule__Columns__EntriesAssignment_1_1_1
            {
            pushFollow(FOLLOW_rule__Columns__EntriesAssignment_1_1_1_in_rule__Columns__Group_1_1__1__Impl5646);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2676:1: rule__ColumnOrAlias__Group_0__0 : rule__ColumnOrAlias__Group_0__0__Impl rule__ColumnOrAlias__Group_0__1 ;
    public final void rule__ColumnOrAlias__Group_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2680:1: ( rule__ColumnOrAlias__Group_0__0__Impl rule__ColumnOrAlias__Group_0__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2681:2: rule__ColumnOrAlias__Group_0__0__Impl rule__ColumnOrAlias__Group_0__1
            {
            pushFollow(FOLLOW_rule__ColumnOrAlias__Group_0__0__Impl_in_rule__ColumnOrAlias__Group_0__05680);
            rule__ColumnOrAlias__Group_0__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ColumnOrAlias__Group_0__1_in_rule__ColumnOrAlias__Group_0__05683);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2688:1: rule__ColumnOrAlias__Group_0__0__Impl : ( ( rule__ColumnOrAlias__CfullAssignment_0_0 ) ) ;
    public final void rule__ColumnOrAlias__Group_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2692:1: ( ( ( rule__ColumnOrAlias__CfullAssignment_0_0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2693:1: ( ( rule__ColumnOrAlias__CfullAssignment_0_0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2693:1: ( ( rule__ColumnOrAlias__CfullAssignment_0_0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2694:1: ( rule__ColumnOrAlias__CfullAssignment_0_0 )
            {
             before(grammarAccess.getColumnOrAliasAccess().getCfullAssignment_0_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2695:1: ( rule__ColumnOrAlias__CfullAssignment_0_0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2695:2: rule__ColumnOrAlias__CfullAssignment_0_0
            {
            pushFollow(FOLLOW_rule__ColumnOrAlias__CfullAssignment_0_0_in_rule__ColumnOrAlias__Group_0__0__Impl5710);
            rule__ColumnOrAlias__CfullAssignment_0_0();

            state._fsp--;


            }

             after(grammarAccess.getColumnOrAliasAccess().getCfullAssignment_0_0()); 

            }


            }

        }
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2705:1: rule__ColumnOrAlias__Group_0__1 : rule__ColumnOrAlias__Group_0__1__Impl rule__ColumnOrAlias__Group_0__2 ;
    public final void rule__ColumnOrAlias__Group_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2709:1: ( rule__ColumnOrAlias__Group_0__1__Impl rule__ColumnOrAlias__Group_0__2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2710:2: rule__ColumnOrAlias__Group_0__1__Impl rule__ColumnOrAlias__Group_0__2
            {
            pushFollow(FOLLOW_rule__ColumnOrAlias__Group_0__1__Impl_in_rule__ColumnOrAlias__Group_0__15740);
            rule__ColumnOrAlias__Group_0__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ColumnOrAlias__Group_0__2_in_rule__ColumnOrAlias__Group_0__15743);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2717:1: rule__ColumnOrAlias__Group_0__1__Impl : ( ( rule__ColumnOrAlias__AliasAssignment_0_1 )? ) ;
    public final void rule__ColumnOrAlias__Group_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2721:1: ( ( ( rule__ColumnOrAlias__AliasAssignment_0_1 )? ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2722:1: ( ( rule__ColumnOrAlias__AliasAssignment_0_1 )? )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2722:1: ( ( rule__ColumnOrAlias__AliasAssignment_0_1 )? )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2723:1: ( rule__ColumnOrAlias__AliasAssignment_0_1 )?
            {
             before(grammarAccess.getColumnOrAliasAccess().getAliasAssignment_0_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2724:1: ( rule__ColumnOrAlias__AliasAssignment_0_1 )?
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==KEYWORD_15) ) {
                alt27=1;
            }
            switch (alt27) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2724:2: rule__ColumnOrAlias__AliasAssignment_0_1
                    {
                    pushFollow(FOLLOW_rule__ColumnOrAlias__AliasAssignment_0_1_in_rule__ColumnOrAlias__Group_0__1__Impl5770);
                    rule__ColumnOrAlias__AliasAssignment_0_1();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getColumnOrAliasAccess().getAliasAssignment_0_1()); 

            }


            }

        }
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2734:1: rule__ColumnOrAlias__Group_0__2 : rule__ColumnOrAlias__Group_0__2__Impl ;
    public final void rule__ColumnOrAlias__Group_0__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2738:1: ( rule__ColumnOrAlias__Group_0__2__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2739:2: rule__ColumnOrAlias__Group_0__2__Impl
            {
            pushFollow(FOLLOW_rule__ColumnOrAlias__Group_0__2__Impl_in_rule__ColumnOrAlias__Group_0__25801);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2745:1: rule__ColumnOrAlias__Group_0__2__Impl : ( ( rule__ColumnOrAlias__ColAliasAssignment_0_2 )? ) ;
    public final void rule__ColumnOrAlias__Group_0__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2749:1: ( ( ( rule__ColumnOrAlias__ColAliasAssignment_0_2 )? ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2750:1: ( ( rule__ColumnOrAlias__ColAliasAssignment_0_2 )? )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2750:1: ( ( rule__ColumnOrAlias__ColAliasAssignment_0_2 )? )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2751:1: ( rule__ColumnOrAlias__ColAliasAssignment_0_2 )?
            {
             before(grammarAccess.getColumnOrAliasAccess().getColAliasAssignment_0_2()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2752:1: ( rule__ColumnOrAlias__ColAliasAssignment_0_2 )?
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==RULE_ID) ) {
                alt28=1;
            }
            switch (alt28) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2752:2: rule__ColumnOrAlias__ColAliasAssignment_0_2
                    {
                    pushFollow(FOLLOW_rule__ColumnOrAlias__ColAliasAssignment_0_2_in_rule__ColumnOrAlias__Group_0__2__Impl5828);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2768:1: rule__ColumnFull__Group__0 : rule__ColumnFull__Group__0__Impl rule__ColumnFull__Group__1 ;
    public final void rule__ColumnFull__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2772:1: ( rule__ColumnFull__Group__0__Impl rule__ColumnFull__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2773:2: rule__ColumnFull__Group__0__Impl rule__ColumnFull__Group__1
            {
            pushFollow(FOLLOW_rule__ColumnFull__Group__0__Impl_in_rule__ColumnFull__Group__05865);
            rule__ColumnFull__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ColumnFull__Group__1_in_rule__ColumnFull__Group__05868);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2780:1: rule__ColumnFull__Group__0__Impl : ( ruleDbObjectName ) ;
    public final void rule__ColumnFull__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2784:1: ( ( ruleDbObjectName ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2785:1: ( ruleDbObjectName )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2785:1: ( ruleDbObjectName )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2786:1: ruleDbObjectName
            {
             before(grammarAccess.getColumnFullAccess().getDbObjectNameParserRuleCall_0()); 
            pushFollow(FOLLOW_ruleDbObjectName_in_rule__ColumnFull__Group__0__Impl5895);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2797:1: rule__ColumnFull__Group__1 : rule__ColumnFull__Group__1__Impl ;
    public final void rule__ColumnFull__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2801:1: ( rule__ColumnFull__Group__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2802:2: rule__ColumnFull__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__ColumnFull__Group__1__Impl_in_rule__ColumnFull__Group__15924);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2808:1: rule__ColumnFull__Group__1__Impl : ( ( rule__ColumnFull__Group_1__0 )? ) ;
    public final void rule__ColumnFull__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2812:1: ( ( ( rule__ColumnFull__Group_1__0 )? ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2813:1: ( ( rule__ColumnFull__Group_1__0 )? )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2813:1: ( ( rule__ColumnFull__Group_1__0 )? )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2814:1: ( rule__ColumnFull__Group_1__0 )?
            {
             before(grammarAccess.getColumnFullAccess().getGroup_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2815:1: ( rule__ColumnFull__Group_1__0 )?
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==KEYWORD_6) ) {
                alt29=1;
            }
            switch (alt29) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2815:2: rule__ColumnFull__Group_1__0
                    {
                    pushFollow(FOLLOW_rule__ColumnFull__Group_1__0_in_rule__ColumnFull__Group__1__Impl5951);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2829:1: rule__ColumnFull__Group_1__0 : rule__ColumnFull__Group_1__0__Impl rule__ColumnFull__Group_1__1 ;
    public final void rule__ColumnFull__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2833:1: ( rule__ColumnFull__Group_1__0__Impl rule__ColumnFull__Group_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2834:2: rule__ColumnFull__Group_1__0__Impl rule__ColumnFull__Group_1__1
            {
            pushFollow(FOLLOW_rule__ColumnFull__Group_1__0__Impl_in_rule__ColumnFull__Group_1__05986);
            rule__ColumnFull__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ColumnFull__Group_1__1_in_rule__ColumnFull__Group_1__05989);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2841:1: rule__ColumnFull__Group_1__0__Impl : ( () ) ;
    public final void rule__ColumnFull__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2845:1: ( ( () ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2846:1: ( () )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2846:1: ( () )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2847:1: ()
            {
             before(grammarAccess.getColumnFullAccess().getColEntriesAction_1_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2848:1: ()
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2850:1: 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2860:1: rule__ColumnFull__Group_1__1 : rule__ColumnFull__Group_1__1__Impl ;
    public final void rule__ColumnFull__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2864:1: ( rule__ColumnFull__Group_1__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2865:2: rule__ColumnFull__Group_1__1__Impl
            {
            pushFollow(FOLLOW_rule__ColumnFull__Group_1__1__Impl_in_rule__ColumnFull__Group_1__16047);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2871:1: rule__ColumnFull__Group_1__1__Impl : ( ( ( rule__ColumnFull__Group_1_1__0 ) ) ( ( rule__ColumnFull__Group_1_1__0 )* ) ) ;
    public final void rule__ColumnFull__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2875:1: ( ( ( ( rule__ColumnFull__Group_1_1__0 ) ) ( ( rule__ColumnFull__Group_1_1__0 )* ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2876:1: ( ( ( rule__ColumnFull__Group_1_1__0 ) ) ( ( rule__ColumnFull__Group_1_1__0 )* ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2876:1: ( ( ( rule__ColumnFull__Group_1_1__0 ) ) ( ( rule__ColumnFull__Group_1_1__0 )* ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2877:1: ( ( rule__ColumnFull__Group_1_1__0 ) ) ( ( rule__ColumnFull__Group_1_1__0 )* )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2877:1: ( ( rule__ColumnFull__Group_1_1__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2878:1: ( rule__ColumnFull__Group_1_1__0 )
            {
             before(grammarAccess.getColumnFullAccess().getGroup_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2879:1: ( rule__ColumnFull__Group_1_1__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2879:2: rule__ColumnFull__Group_1_1__0
            {
            pushFollow(FOLLOW_rule__ColumnFull__Group_1_1__0_in_rule__ColumnFull__Group_1__1__Impl6076);
            rule__ColumnFull__Group_1_1__0();

            state._fsp--;


            }

             after(grammarAccess.getColumnFullAccess().getGroup_1_1()); 

            }

            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2882:1: ( ( rule__ColumnFull__Group_1_1__0 )* )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2883:1: ( rule__ColumnFull__Group_1_1__0 )*
            {
             before(grammarAccess.getColumnFullAccess().getGroup_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2884:1: ( rule__ColumnFull__Group_1_1__0 )*
            loop30:
            do {
                int alt30=2;
                int LA30_0 = input.LA(1);

                if ( (LA30_0==KEYWORD_6) ) {
                    alt30=1;
                }


                switch (alt30) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2884:2: rule__ColumnFull__Group_1_1__0
            	    {
            	    pushFollow(FOLLOW_rule__ColumnFull__Group_1_1__0_in_rule__ColumnFull__Group_1__1__Impl6088);
            	    rule__ColumnFull__Group_1_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop30;
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2899:1: rule__ColumnFull__Group_1_1__0 : rule__ColumnFull__Group_1_1__0__Impl rule__ColumnFull__Group_1_1__1 ;
    public final void rule__ColumnFull__Group_1_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2903:1: ( rule__ColumnFull__Group_1_1__0__Impl rule__ColumnFull__Group_1_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2904:2: rule__ColumnFull__Group_1_1__0__Impl rule__ColumnFull__Group_1_1__1
            {
            pushFollow(FOLLOW_rule__ColumnFull__Group_1_1__0__Impl_in_rule__ColumnFull__Group_1_1__06125);
            rule__ColumnFull__Group_1_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ColumnFull__Group_1_1__1_in_rule__ColumnFull__Group_1_1__06128);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2911:1: rule__ColumnFull__Group_1_1__0__Impl : ( KEYWORD_6 ) ;
    public final void rule__ColumnFull__Group_1_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2915:1: ( ( KEYWORD_6 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2916:1: ( KEYWORD_6 )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2916:1: ( KEYWORD_6 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2917:1: KEYWORD_6
            {
             before(grammarAccess.getColumnFullAccess().getFullStopKeyword_1_1_0()); 
            match(input,KEYWORD_6,FOLLOW_KEYWORD_6_in_rule__ColumnFull__Group_1_1__0__Impl6156); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2930:1: rule__ColumnFull__Group_1_1__1 : rule__ColumnFull__Group_1_1__1__Impl ;
    public final void rule__ColumnFull__Group_1_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2934:1: ( rule__ColumnFull__Group_1_1__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2935:2: rule__ColumnFull__Group_1_1__1__Impl
            {
            pushFollow(FOLLOW_rule__ColumnFull__Group_1_1__1__Impl_in_rule__ColumnFull__Group_1_1__16187);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2941:1: rule__ColumnFull__Group_1_1__1__Impl : ( ( rule__ColumnFull__EntriesAssignment_1_1_1 ) ) ;
    public final void rule__ColumnFull__Group_1_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2945:1: ( ( ( rule__ColumnFull__EntriesAssignment_1_1_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2946:1: ( ( rule__ColumnFull__EntriesAssignment_1_1_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2946:1: ( ( rule__ColumnFull__EntriesAssignment_1_1_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2947:1: ( rule__ColumnFull__EntriesAssignment_1_1_1 )
            {
             before(grammarAccess.getColumnFullAccess().getEntriesAssignment_1_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2948:1: ( rule__ColumnFull__EntriesAssignment_1_1_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2948:2: rule__ColumnFull__EntriesAssignment_1_1_1
            {
            pushFollow(FOLLOW_rule__ColumnFull__EntriesAssignment_1_1_1_in_rule__ColumnFull__Group_1_1__1__Impl6214);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2962:1: rule__Tables__Group__0 : rule__Tables__Group__0__Impl rule__Tables__Group__1 ;
    public final void rule__Tables__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2966:1: ( rule__Tables__Group__0__Impl rule__Tables__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2967:2: rule__Tables__Group__0__Impl rule__Tables__Group__1
            {
            pushFollow(FOLLOW_rule__Tables__Group__0__Impl_in_rule__Tables__Group__06248);
            rule__Tables__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Tables__Group__1_in_rule__Tables__Group__06251);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2974:1: rule__Tables__Group__0__Impl : ( ruleFromTable ) ;
    public final void rule__Tables__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2978:1: ( ( ruleFromTable ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2979:1: ( ruleFromTable )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2979:1: ( ruleFromTable )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2980:1: ruleFromTable
            {
             before(grammarAccess.getTablesAccess().getFromTableParserRuleCall_0()); 
            pushFollow(FOLLOW_ruleFromTable_in_rule__Tables__Group__0__Impl6278);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2991:1: rule__Tables__Group__1 : rule__Tables__Group__1__Impl ;
    public final void rule__Tables__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2995:1: ( rule__Tables__Group__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2996:2: rule__Tables__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__Tables__Group__1__Impl_in_rule__Tables__Group__16307);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3002:1: rule__Tables__Group__1__Impl : ( ( rule__Tables__Group_1__0 )? ) ;
    public final void rule__Tables__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3006:1: ( ( ( rule__Tables__Group_1__0 )? ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3007:1: ( ( rule__Tables__Group_1__0 )? )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3007:1: ( ( rule__Tables__Group_1__0 )? )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3008:1: ( rule__Tables__Group_1__0 )?
            {
             before(grammarAccess.getTablesAccess().getGroup_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3009:1: ( rule__Tables__Group_1__0 )?
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( (LA31_0==KEYWORD_4) ) {
                alt31=1;
            }
            switch (alt31) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3009:2: rule__Tables__Group_1__0
                    {
                    pushFollow(FOLLOW_rule__Tables__Group_1__0_in_rule__Tables__Group__1__Impl6334);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3023:1: rule__Tables__Group_1__0 : rule__Tables__Group_1__0__Impl rule__Tables__Group_1__1 ;
    public final void rule__Tables__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3027:1: ( rule__Tables__Group_1__0__Impl rule__Tables__Group_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3028:2: rule__Tables__Group_1__0__Impl rule__Tables__Group_1__1
            {
            pushFollow(FOLLOW_rule__Tables__Group_1__0__Impl_in_rule__Tables__Group_1__06369);
            rule__Tables__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Tables__Group_1__1_in_rule__Tables__Group_1__06372);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3035:1: rule__Tables__Group_1__0__Impl : ( () ) ;
    public final void rule__Tables__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3039:1: ( ( () ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3040:1: ( () )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3040:1: ( () )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3041:1: ()
            {
             before(grammarAccess.getTablesAccess().getOrTableEntriesAction_1_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3042:1: ()
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3044:1: 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3054:1: rule__Tables__Group_1__1 : rule__Tables__Group_1__1__Impl ;
    public final void rule__Tables__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3058:1: ( rule__Tables__Group_1__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3059:2: rule__Tables__Group_1__1__Impl
            {
            pushFollow(FOLLOW_rule__Tables__Group_1__1__Impl_in_rule__Tables__Group_1__16430);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3065:1: rule__Tables__Group_1__1__Impl : ( ( ( rule__Tables__Group_1_1__0 ) ) ( ( rule__Tables__Group_1_1__0 )* ) ) ;
    public final void rule__Tables__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3069:1: ( ( ( ( rule__Tables__Group_1_1__0 ) ) ( ( rule__Tables__Group_1_1__0 )* ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3070:1: ( ( ( rule__Tables__Group_1_1__0 ) ) ( ( rule__Tables__Group_1_1__0 )* ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3070:1: ( ( ( rule__Tables__Group_1_1__0 ) ) ( ( rule__Tables__Group_1_1__0 )* ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3071:1: ( ( rule__Tables__Group_1_1__0 ) ) ( ( rule__Tables__Group_1_1__0 )* )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3071:1: ( ( rule__Tables__Group_1_1__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3072:1: ( rule__Tables__Group_1_1__0 )
            {
             before(grammarAccess.getTablesAccess().getGroup_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3073:1: ( rule__Tables__Group_1_1__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3073:2: rule__Tables__Group_1_1__0
            {
            pushFollow(FOLLOW_rule__Tables__Group_1_1__0_in_rule__Tables__Group_1__1__Impl6459);
            rule__Tables__Group_1_1__0();

            state._fsp--;


            }

             after(grammarAccess.getTablesAccess().getGroup_1_1()); 

            }

            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3076:1: ( ( rule__Tables__Group_1_1__0 )* )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3077:1: ( rule__Tables__Group_1_1__0 )*
            {
             before(grammarAccess.getTablesAccess().getGroup_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3078:1: ( rule__Tables__Group_1_1__0 )*
            loop32:
            do {
                int alt32=2;
                int LA32_0 = input.LA(1);

                if ( (LA32_0==KEYWORD_4) ) {
                    alt32=1;
                }


                switch (alt32) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3078:2: rule__Tables__Group_1_1__0
            	    {
            	    pushFollow(FOLLOW_rule__Tables__Group_1_1__0_in_rule__Tables__Group_1__1__Impl6471);
            	    rule__Tables__Group_1_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop32;
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3093:1: rule__Tables__Group_1_1__0 : rule__Tables__Group_1_1__0__Impl rule__Tables__Group_1_1__1 ;
    public final void rule__Tables__Group_1_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3097:1: ( rule__Tables__Group_1_1__0__Impl rule__Tables__Group_1_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3098:2: rule__Tables__Group_1_1__0__Impl rule__Tables__Group_1_1__1
            {
            pushFollow(FOLLOW_rule__Tables__Group_1_1__0__Impl_in_rule__Tables__Group_1_1__06508);
            rule__Tables__Group_1_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Tables__Group_1_1__1_in_rule__Tables__Group_1_1__06511);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3105:1: rule__Tables__Group_1_1__0__Impl : ( KEYWORD_4 ) ;
    public final void rule__Tables__Group_1_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3109:1: ( ( KEYWORD_4 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3110:1: ( KEYWORD_4 )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3110:1: ( KEYWORD_4 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3111:1: KEYWORD_4
            {
             before(grammarAccess.getTablesAccess().getCommaKeyword_1_1_0()); 
            match(input,KEYWORD_4,FOLLOW_KEYWORD_4_in_rule__Tables__Group_1_1__0__Impl6539); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3124:1: rule__Tables__Group_1_1__1 : rule__Tables__Group_1_1__1__Impl ;
    public final void rule__Tables__Group_1_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3128:1: ( rule__Tables__Group_1_1__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3129:2: rule__Tables__Group_1_1__1__Impl
            {
            pushFollow(FOLLOW_rule__Tables__Group_1_1__1__Impl_in_rule__Tables__Group_1_1__16570);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3135:1: rule__Tables__Group_1_1__1__Impl : ( ( rule__Tables__EntriesAssignment_1_1_1 ) ) ;
    public final void rule__Tables__Group_1_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3139:1: ( ( ( rule__Tables__EntriesAssignment_1_1_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3140:1: ( ( rule__Tables__EntriesAssignment_1_1_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3140:1: ( ( rule__Tables__EntriesAssignment_1_1_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3141:1: ( rule__Tables__EntriesAssignment_1_1_1 )
            {
             before(grammarAccess.getTablesAccess().getEntriesAssignment_1_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3142:1: ( rule__Tables__EntriesAssignment_1_1_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3142:2: rule__Tables__EntriesAssignment_1_1_1
            {
            pushFollow(FOLLOW_rule__Tables__EntriesAssignment_1_1_1_in_rule__Tables__Group_1_1__1__Impl6597);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3156:1: rule__FromTable__Group__0 : rule__FromTable__Group__0__Impl rule__FromTable__Group__1 ;
    public final void rule__FromTable__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3160:1: ( rule__FromTable__Group__0__Impl rule__FromTable__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3161:2: rule__FromTable__Group__0__Impl rule__FromTable__Group__1
            {
            pushFollow(FOLLOW_rule__FromTable__Group__0__Impl_in_rule__FromTable__Group__06631);
            rule__FromTable__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__FromTable__Group__1_in_rule__FromTable__Group__06634);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3168:1: rule__FromTable__Group__0__Impl : ( ( rule__FromTable__TableAssignment_0 ) ) ;
    public final void rule__FromTable__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3172:1: ( ( ( rule__FromTable__TableAssignment_0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3173:1: ( ( rule__FromTable__TableAssignment_0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3173:1: ( ( rule__FromTable__TableAssignment_0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3174:1: ( rule__FromTable__TableAssignment_0 )
            {
             before(grammarAccess.getFromTableAccess().getTableAssignment_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3175:1: ( rule__FromTable__TableAssignment_0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3175:2: rule__FromTable__TableAssignment_0
            {
            pushFollow(FOLLOW_rule__FromTable__TableAssignment_0_in_rule__FromTable__Group__0__Impl6661);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3185:1: rule__FromTable__Group__1 : rule__FromTable__Group__1__Impl ;
    public final void rule__FromTable__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3189:1: ( rule__FromTable__Group__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3190:2: rule__FromTable__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__FromTable__Group__1__Impl_in_rule__FromTable__Group__16691);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3196:1: rule__FromTable__Group__1__Impl : ( ( rule__FromTable__Group_1__0 )? ) ;
    public final void rule__FromTable__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3200:1: ( ( ( rule__FromTable__Group_1__0 )? ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3201:1: ( ( rule__FromTable__Group_1__0 )? )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3201:1: ( ( rule__FromTable__Group_1__0 )? )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3202:1: ( rule__FromTable__Group_1__0 )?
            {
             before(grammarAccess.getFromTableAccess().getGroup_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3203:1: ( rule__FromTable__Group_1__0 )?
            int alt33=2;
            int LA33_0 = input.LA(1);

            if ( ((LA33_0>=KEYWORD_56 && LA33_0<=KEYWORD_55)||(LA33_0>=KEYWORD_50 && LA33_0<=KEYWORD_51)) ) {
                alt33=1;
            }
            switch (alt33) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3203:2: rule__FromTable__Group_1__0
                    {
                    pushFollow(FOLLOW_rule__FromTable__Group_1__0_in_rule__FromTable__Group__1__Impl6718);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3217:1: rule__FromTable__Group_1__0 : rule__FromTable__Group_1__0__Impl rule__FromTable__Group_1__1 ;
    public final void rule__FromTable__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3221:1: ( rule__FromTable__Group_1__0__Impl rule__FromTable__Group_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3222:2: rule__FromTable__Group_1__0__Impl rule__FromTable__Group_1__1
            {
            pushFollow(FOLLOW_rule__FromTable__Group_1__0__Impl_in_rule__FromTable__Group_1__06753);
            rule__FromTable__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__FromTable__Group_1__1_in_rule__FromTable__Group_1__06756);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3229:1: rule__FromTable__Group_1__0__Impl : ( ( rule__FromTable__JoinAssignment_1_0 ) ) ;
    public final void rule__FromTable__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3233:1: ( ( ( rule__FromTable__JoinAssignment_1_0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3234:1: ( ( rule__FromTable__JoinAssignment_1_0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3234:1: ( ( rule__FromTable__JoinAssignment_1_0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3235:1: ( rule__FromTable__JoinAssignment_1_0 )
            {
             before(grammarAccess.getFromTableAccess().getJoinAssignment_1_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3236:1: ( rule__FromTable__JoinAssignment_1_0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3236:2: rule__FromTable__JoinAssignment_1_0
            {
            pushFollow(FOLLOW_rule__FromTable__JoinAssignment_1_0_in_rule__FromTable__Group_1__0__Impl6783);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3246:1: rule__FromTable__Group_1__1 : rule__FromTable__Group_1__1__Impl rule__FromTable__Group_1__2 ;
    public final void rule__FromTable__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3250:1: ( rule__FromTable__Group_1__1__Impl rule__FromTable__Group_1__2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3251:2: rule__FromTable__Group_1__1__Impl rule__FromTable__Group_1__2
            {
            pushFollow(FOLLOW_rule__FromTable__Group_1__1__Impl_in_rule__FromTable__Group_1__16813);
            rule__FromTable__Group_1__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__FromTable__Group_1__2_in_rule__FromTable__Group_1__16816);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3258:1: rule__FromTable__Group_1__1__Impl : ( ( rule__FromTable__OnTableAssignment_1_1 ) ) ;
    public final void rule__FromTable__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3262:1: ( ( ( rule__FromTable__OnTableAssignment_1_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3263:1: ( ( rule__FromTable__OnTableAssignment_1_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3263:1: ( ( rule__FromTable__OnTableAssignment_1_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3264:1: ( rule__FromTable__OnTableAssignment_1_1 )
            {
             before(grammarAccess.getFromTableAccess().getOnTableAssignment_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3265:1: ( rule__FromTable__OnTableAssignment_1_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3265:2: rule__FromTable__OnTableAssignment_1_1
            {
            pushFollow(FOLLOW_rule__FromTable__OnTableAssignment_1_1_in_rule__FromTable__Group_1__1__Impl6843);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3275:1: rule__FromTable__Group_1__2 : rule__FromTable__Group_1__2__Impl rule__FromTable__Group_1__3 ;
    public final void rule__FromTable__Group_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3279:1: ( rule__FromTable__Group_1__2__Impl rule__FromTable__Group_1__3 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3280:2: rule__FromTable__Group_1__2__Impl rule__FromTable__Group_1__3
            {
            pushFollow(FOLLOW_rule__FromTable__Group_1__2__Impl_in_rule__FromTable__Group_1__26873);
            rule__FromTable__Group_1__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__FromTable__Group_1__3_in_rule__FromTable__Group_1__26876);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3287:1: rule__FromTable__Group_1__2__Impl : ( KEYWORD_17 ) ;
    public final void rule__FromTable__Group_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3291:1: ( ( KEYWORD_17 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3292:1: ( KEYWORD_17 )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3292:1: ( KEYWORD_17 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3293:1: KEYWORD_17
            {
             before(grammarAccess.getFromTableAccess().getONKeyword_1_2()); 
            match(input,KEYWORD_17,FOLLOW_KEYWORD_17_in_rule__FromTable__Group_1__2__Impl6904); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3306:1: rule__FromTable__Group_1__3 : rule__FromTable__Group_1__3__Impl ;
    public final void rule__FromTable__Group_1__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3310:1: ( rule__FromTable__Group_1__3__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3311:2: rule__FromTable__Group_1__3__Impl
            {
            pushFollow(FOLLOW_rule__FromTable__Group_1__3__Impl_in_rule__FromTable__Group_1__36935);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3317:1: rule__FromTable__Group_1__3__Impl : ( ( rule__FromTable__JoinExprAssignment_1_3 ) ) ;
    public final void rule__FromTable__Group_1__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3321:1: ( ( ( rule__FromTable__JoinExprAssignment_1_3 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3322:1: ( ( rule__FromTable__JoinExprAssignment_1_3 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3322:1: ( ( rule__FromTable__JoinExprAssignment_1_3 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3323:1: ( rule__FromTable__JoinExprAssignment_1_3 )
            {
             before(grammarAccess.getFromTableAccess().getJoinExprAssignment_1_3()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3324:1: ( rule__FromTable__JoinExprAssignment_1_3 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3324:2: rule__FromTable__JoinExprAssignment_1_3
            {
            pushFollow(FOLLOW_rule__FromTable__JoinExprAssignment_1_3_in_rule__FromTable__Group_1__3__Impl6962);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3342:1: rule__TableOrAlias__Group__0 : rule__TableOrAlias__Group__0__Impl rule__TableOrAlias__Group__1 ;
    public final void rule__TableOrAlias__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3346:1: ( rule__TableOrAlias__Group__0__Impl rule__TableOrAlias__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3347:2: rule__TableOrAlias__Group__0__Impl rule__TableOrAlias__Group__1
            {
            pushFollow(FOLLOW_rule__TableOrAlias__Group__0__Impl_in_rule__TableOrAlias__Group__07000);
            rule__TableOrAlias__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__TableOrAlias__Group__1_in_rule__TableOrAlias__Group__07003);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3354:1: rule__TableOrAlias__Group__0__Impl : ( ( rule__TableOrAlias__TfullAssignment_0 ) ) ;
    public final void rule__TableOrAlias__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3358:1: ( ( ( rule__TableOrAlias__TfullAssignment_0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3359:1: ( ( rule__TableOrAlias__TfullAssignment_0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3359:1: ( ( rule__TableOrAlias__TfullAssignment_0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3360:1: ( rule__TableOrAlias__TfullAssignment_0 )
            {
             before(grammarAccess.getTableOrAliasAccess().getTfullAssignment_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3361:1: ( rule__TableOrAlias__TfullAssignment_0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3361:2: rule__TableOrAlias__TfullAssignment_0
            {
            pushFollow(FOLLOW_rule__TableOrAlias__TfullAssignment_0_in_rule__TableOrAlias__Group__0__Impl7030);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3371:1: rule__TableOrAlias__Group__1 : rule__TableOrAlias__Group__1__Impl rule__TableOrAlias__Group__2 ;
    public final void rule__TableOrAlias__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3375:1: ( rule__TableOrAlias__Group__1__Impl rule__TableOrAlias__Group__2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3376:2: rule__TableOrAlias__Group__1__Impl rule__TableOrAlias__Group__2
            {
            pushFollow(FOLLOW_rule__TableOrAlias__Group__1__Impl_in_rule__TableOrAlias__Group__17060);
            rule__TableOrAlias__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__TableOrAlias__Group__2_in_rule__TableOrAlias__Group__17063);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3383:1: rule__TableOrAlias__Group__1__Impl : ( ( rule__TableOrAlias__AliasAssignment_1 )? ) ;
    public final void rule__TableOrAlias__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3387:1: ( ( ( rule__TableOrAlias__AliasAssignment_1 )? ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3388:1: ( ( rule__TableOrAlias__AliasAssignment_1 )? )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3388:1: ( ( rule__TableOrAlias__AliasAssignment_1 )? )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3389:1: ( rule__TableOrAlias__AliasAssignment_1 )?
            {
             before(grammarAccess.getTableOrAliasAccess().getAliasAssignment_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3390:1: ( rule__TableOrAlias__AliasAssignment_1 )?
            int alt34=2;
            int LA34_0 = input.LA(1);

            if ( (LA34_0==KEYWORD_15) ) {
                alt34=1;
            }
            switch (alt34) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3390:2: rule__TableOrAlias__AliasAssignment_1
                    {
                    pushFollow(FOLLOW_rule__TableOrAlias__AliasAssignment_1_in_rule__TableOrAlias__Group__1__Impl7090);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3400:1: rule__TableOrAlias__Group__2 : rule__TableOrAlias__Group__2__Impl ;
    public final void rule__TableOrAlias__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3404:1: ( rule__TableOrAlias__Group__2__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3405:2: rule__TableOrAlias__Group__2__Impl
            {
            pushFollow(FOLLOW_rule__TableOrAlias__Group__2__Impl_in_rule__TableOrAlias__Group__27121);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3411:1: rule__TableOrAlias__Group__2__Impl : ( ( rule__TableOrAlias__TblAliasAssignment_2 )? ) ;
    public final void rule__TableOrAlias__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3415:1: ( ( ( rule__TableOrAlias__TblAliasAssignment_2 )? ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3416:1: ( ( rule__TableOrAlias__TblAliasAssignment_2 )? )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3416:1: ( ( rule__TableOrAlias__TblAliasAssignment_2 )? )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3417:1: ( rule__TableOrAlias__TblAliasAssignment_2 )?
            {
             before(grammarAccess.getTableOrAliasAccess().getTblAliasAssignment_2()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3418:1: ( rule__TableOrAlias__TblAliasAssignment_2 )?
            int alt35=2;
            int LA35_0 = input.LA(1);

            if ( (LA35_0==RULE_ID) ) {
                alt35=1;
            }
            switch (alt35) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3418:2: rule__TableOrAlias__TblAliasAssignment_2
                    {
                    pushFollow(FOLLOW_rule__TableOrAlias__TblAliasAssignment_2_in_rule__TableOrAlias__Group__2__Impl7148);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3434:1: rule__TableFull__Group__0 : rule__TableFull__Group__0__Impl rule__TableFull__Group__1 ;
    public final void rule__TableFull__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3438:1: ( rule__TableFull__Group__0__Impl rule__TableFull__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3439:2: rule__TableFull__Group__0__Impl rule__TableFull__Group__1
            {
            pushFollow(FOLLOW_rule__TableFull__Group__0__Impl_in_rule__TableFull__Group__07185);
            rule__TableFull__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__TableFull__Group__1_in_rule__TableFull__Group__07188);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3446:1: rule__TableFull__Group__0__Impl : ( ruleDbObjectName ) ;
    public final void rule__TableFull__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3450:1: ( ( ruleDbObjectName ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3451:1: ( ruleDbObjectName )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3451:1: ( ruleDbObjectName )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3452:1: ruleDbObjectName
            {
             before(grammarAccess.getTableFullAccess().getDbObjectNameParserRuleCall_0()); 
            pushFollow(FOLLOW_ruleDbObjectName_in_rule__TableFull__Group__0__Impl7215);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3463:1: rule__TableFull__Group__1 : rule__TableFull__Group__1__Impl ;
    public final void rule__TableFull__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3467:1: ( rule__TableFull__Group__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3468:2: rule__TableFull__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__TableFull__Group__1__Impl_in_rule__TableFull__Group__17244);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3474:1: rule__TableFull__Group__1__Impl : ( ( rule__TableFull__Group_1__0 )? ) ;
    public final void rule__TableFull__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3478:1: ( ( ( rule__TableFull__Group_1__0 )? ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3479:1: ( ( rule__TableFull__Group_1__0 )? )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3479:1: ( ( rule__TableFull__Group_1__0 )? )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3480:1: ( rule__TableFull__Group_1__0 )?
            {
             before(grammarAccess.getTableFullAccess().getGroup_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3481:1: ( rule__TableFull__Group_1__0 )?
            int alt36=2;
            int LA36_0 = input.LA(1);

            if ( (LA36_0==KEYWORD_6) ) {
                alt36=1;
            }
            switch (alt36) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3481:2: rule__TableFull__Group_1__0
                    {
                    pushFollow(FOLLOW_rule__TableFull__Group_1__0_in_rule__TableFull__Group__1__Impl7271);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3495:1: rule__TableFull__Group_1__0 : rule__TableFull__Group_1__0__Impl rule__TableFull__Group_1__1 ;
    public final void rule__TableFull__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3499:1: ( rule__TableFull__Group_1__0__Impl rule__TableFull__Group_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3500:2: rule__TableFull__Group_1__0__Impl rule__TableFull__Group_1__1
            {
            pushFollow(FOLLOW_rule__TableFull__Group_1__0__Impl_in_rule__TableFull__Group_1__07306);
            rule__TableFull__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__TableFull__Group_1__1_in_rule__TableFull__Group_1__07309);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3507:1: rule__TableFull__Group_1__0__Impl : ( () ) ;
    public final void rule__TableFull__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3511:1: ( ( () ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3512:1: ( () )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3512:1: ( () )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3513:1: ()
            {
             before(grammarAccess.getTableFullAccess().getTblsEntriesAction_1_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3514:1: ()
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3516:1: 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3526:1: rule__TableFull__Group_1__1 : rule__TableFull__Group_1__1__Impl ;
    public final void rule__TableFull__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3530:1: ( rule__TableFull__Group_1__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3531:2: rule__TableFull__Group_1__1__Impl
            {
            pushFollow(FOLLOW_rule__TableFull__Group_1__1__Impl_in_rule__TableFull__Group_1__17367);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3537:1: rule__TableFull__Group_1__1__Impl : ( ( ( rule__TableFull__Group_1_1__0 ) ) ( ( rule__TableFull__Group_1_1__0 )* ) ) ;
    public final void rule__TableFull__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3541:1: ( ( ( ( rule__TableFull__Group_1_1__0 ) ) ( ( rule__TableFull__Group_1_1__0 )* ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3542:1: ( ( ( rule__TableFull__Group_1_1__0 ) ) ( ( rule__TableFull__Group_1_1__0 )* ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3542:1: ( ( ( rule__TableFull__Group_1_1__0 ) ) ( ( rule__TableFull__Group_1_1__0 )* ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3543:1: ( ( rule__TableFull__Group_1_1__0 ) ) ( ( rule__TableFull__Group_1_1__0 )* )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3543:1: ( ( rule__TableFull__Group_1_1__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3544:1: ( rule__TableFull__Group_1_1__0 )
            {
             before(grammarAccess.getTableFullAccess().getGroup_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3545:1: ( rule__TableFull__Group_1_1__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3545:2: rule__TableFull__Group_1_1__0
            {
            pushFollow(FOLLOW_rule__TableFull__Group_1_1__0_in_rule__TableFull__Group_1__1__Impl7396);
            rule__TableFull__Group_1_1__0();

            state._fsp--;


            }

             after(grammarAccess.getTableFullAccess().getGroup_1_1()); 

            }

            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3548:1: ( ( rule__TableFull__Group_1_1__0 )* )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3549:1: ( rule__TableFull__Group_1_1__0 )*
            {
             before(grammarAccess.getTableFullAccess().getGroup_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3550:1: ( rule__TableFull__Group_1_1__0 )*
            loop37:
            do {
                int alt37=2;
                int LA37_0 = input.LA(1);

                if ( (LA37_0==KEYWORD_6) ) {
                    alt37=1;
                }


                switch (alt37) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3550:2: rule__TableFull__Group_1_1__0
            	    {
            	    pushFollow(FOLLOW_rule__TableFull__Group_1_1__0_in_rule__TableFull__Group_1__1__Impl7408);
            	    rule__TableFull__Group_1_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop37;
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3565:1: rule__TableFull__Group_1_1__0 : rule__TableFull__Group_1_1__0__Impl rule__TableFull__Group_1_1__1 ;
    public final void rule__TableFull__Group_1_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3569:1: ( rule__TableFull__Group_1_1__0__Impl rule__TableFull__Group_1_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3570:2: rule__TableFull__Group_1_1__0__Impl rule__TableFull__Group_1_1__1
            {
            pushFollow(FOLLOW_rule__TableFull__Group_1_1__0__Impl_in_rule__TableFull__Group_1_1__07445);
            rule__TableFull__Group_1_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__TableFull__Group_1_1__1_in_rule__TableFull__Group_1_1__07448);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3577:1: rule__TableFull__Group_1_1__0__Impl : ( KEYWORD_6 ) ;
    public final void rule__TableFull__Group_1_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3581:1: ( ( KEYWORD_6 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3582:1: ( KEYWORD_6 )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3582:1: ( KEYWORD_6 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3583:1: KEYWORD_6
            {
             before(grammarAccess.getTableFullAccess().getFullStopKeyword_1_1_0()); 
            match(input,KEYWORD_6,FOLLOW_KEYWORD_6_in_rule__TableFull__Group_1_1__0__Impl7476); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3596:1: rule__TableFull__Group_1_1__1 : rule__TableFull__Group_1_1__1__Impl ;
    public final void rule__TableFull__Group_1_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3600:1: ( rule__TableFull__Group_1_1__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3601:2: rule__TableFull__Group_1_1__1__Impl
            {
            pushFollow(FOLLOW_rule__TableFull__Group_1_1__1__Impl_in_rule__TableFull__Group_1_1__17507);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3607:1: rule__TableFull__Group_1_1__1__Impl : ( ( rule__TableFull__EntriesAssignment_1_1_1 ) ) ;
    public final void rule__TableFull__Group_1_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3611:1: ( ( ( rule__TableFull__EntriesAssignment_1_1_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3612:1: ( ( rule__TableFull__EntriesAssignment_1_1_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3612:1: ( ( rule__TableFull__EntriesAssignment_1_1_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3613:1: ( rule__TableFull__EntriesAssignment_1_1_1 )
            {
             before(grammarAccess.getTableFullAccess().getEntriesAssignment_1_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3614:1: ( rule__TableFull__EntriesAssignment_1_1_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3614:2: rule__TableFull__EntriesAssignment_1_1_1
            {
            pushFollow(FOLLOW_rule__TableFull__EntriesAssignment_1_1_1_in_rule__TableFull__Group_1_1__1__Impl7534);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3628:1: rule__OrderByColumns__Group__0 : rule__OrderByColumns__Group__0__Impl rule__OrderByColumns__Group__1 ;
    public final void rule__OrderByColumns__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3632:1: ( rule__OrderByColumns__Group__0__Impl rule__OrderByColumns__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3633:2: rule__OrderByColumns__Group__0__Impl rule__OrderByColumns__Group__1
            {
            pushFollow(FOLLOW_rule__OrderByColumns__Group__0__Impl_in_rule__OrderByColumns__Group__07568);
            rule__OrderByColumns__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__OrderByColumns__Group__1_in_rule__OrderByColumns__Group__07571);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3640:1: rule__OrderByColumns__Group__0__Impl : ( ruleOrderByColumnFull ) ;
    public final void rule__OrderByColumns__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3644:1: ( ( ruleOrderByColumnFull ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3645:1: ( ruleOrderByColumnFull )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3645:1: ( ruleOrderByColumnFull )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3646:1: ruleOrderByColumnFull
            {
             before(grammarAccess.getOrderByColumnsAccess().getOrderByColumnFullParserRuleCall_0()); 
            pushFollow(FOLLOW_ruleOrderByColumnFull_in_rule__OrderByColumns__Group__0__Impl7598);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3657:1: rule__OrderByColumns__Group__1 : rule__OrderByColumns__Group__1__Impl ;
    public final void rule__OrderByColumns__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3661:1: ( rule__OrderByColumns__Group__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3662:2: rule__OrderByColumns__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__OrderByColumns__Group__1__Impl_in_rule__OrderByColumns__Group__17627);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3668:1: rule__OrderByColumns__Group__1__Impl : ( ( rule__OrderByColumns__Group_1__0 )? ) ;
    public final void rule__OrderByColumns__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3672:1: ( ( ( rule__OrderByColumns__Group_1__0 )? ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3673:1: ( ( rule__OrderByColumns__Group_1__0 )? )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3673:1: ( ( rule__OrderByColumns__Group_1__0 )? )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3674:1: ( rule__OrderByColumns__Group_1__0 )?
            {
             before(grammarAccess.getOrderByColumnsAccess().getGroup_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3675:1: ( rule__OrderByColumns__Group_1__0 )?
            int alt38=2;
            int LA38_0 = input.LA(1);

            if ( (LA38_0==KEYWORD_4) ) {
                alt38=1;
            }
            switch (alt38) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3675:2: rule__OrderByColumns__Group_1__0
                    {
                    pushFollow(FOLLOW_rule__OrderByColumns__Group_1__0_in_rule__OrderByColumns__Group__1__Impl7654);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3689:1: rule__OrderByColumns__Group_1__0 : rule__OrderByColumns__Group_1__0__Impl rule__OrderByColumns__Group_1__1 ;
    public final void rule__OrderByColumns__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3693:1: ( rule__OrderByColumns__Group_1__0__Impl rule__OrderByColumns__Group_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3694:2: rule__OrderByColumns__Group_1__0__Impl rule__OrderByColumns__Group_1__1
            {
            pushFollow(FOLLOW_rule__OrderByColumns__Group_1__0__Impl_in_rule__OrderByColumns__Group_1__07689);
            rule__OrderByColumns__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__OrderByColumns__Group_1__1_in_rule__OrderByColumns__Group_1__07692);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3701:1: rule__OrderByColumns__Group_1__0__Impl : ( () ) ;
    public final void rule__OrderByColumns__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3705:1: ( ( () ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3706:1: ( () )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3706:1: ( () )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3707:1: ()
            {
             before(grammarAccess.getOrderByColumnsAccess().getOrOrderByColumnEntriesAction_1_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3708:1: ()
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3710:1: 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3720:1: rule__OrderByColumns__Group_1__1 : rule__OrderByColumns__Group_1__1__Impl ;
    public final void rule__OrderByColumns__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3724:1: ( rule__OrderByColumns__Group_1__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3725:2: rule__OrderByColumns__Group_1__1__Impl
            {
            pushFollow(FOLLOW_rule__OrderByColumns__Group_1__1__Impl_in_rule__OrderByColumns__Group_1__17750);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3731:1: rule__OrderByColumns__Group_1__1__Impl : ( ( ( rule__OrderByColumns__Group_1_1__0 ) ) ( ( rule__OrderByColumns__Group_1_1__0 )* ) ) ;
    public final void rule__OrderByColumns__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3735:1: ( ( ( ( rule__OrderByColumns__Group_1_1__0 ) ) ( ( rule__OrderByColumns__Group_1_1__0 )* ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3736:1: ( ( ( rule__OrderByColumns__Group_1_1__0 ) ) ( ( rule__OrderByColumns__Group_1_1__0 )* ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3736:1: ( ( ( rule__OrderByColumns__Group_1_1__0 ) ) ( ( rule__OrderByColumns__Group_1_1__0 )* ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3737:1: ( ( rule__OrderByColumns__Group_1_1__0 ) ) ( ( rule__OrderByColumns__Group_1_1__0 )* )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3737:1: ( ( rule__OrderByColumns__Group_1_1__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3738:1: ( rule__OrderByColumns__Group_1_1__0 )
            {
             before(grammarAccess.getOrderByColumnsAccess().getGroup_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3739:1: ( rule__OrderByColumns__Group_1_1__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3739:2: rule__OrderByColumns__Group_1_1__0
            {
            pushFollow(FOLLOW_rule__OrderByColumns__Group_1_1__0_in_rule__OrderByColumns__Group_1__1__Impl7779);
            rule__OrderByColumns__Group_1_1__0();

            state._fsp--;


            }

             after(grammarAccess.getOrderByColumnsAccess().getGroup_1_1()); 

            }

            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3742:1: ( ( rule__OrderByColumns__Group_1_1__0 )* )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3743:1: ( rule__OrderByColumns__Group_1_1__0 )*
            {
             before(grammarAccess.getOrderByColumnsAccess().getGroup_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3744:1: ( rule__OrderByColumns__Group_1_1__0 )*
            loop39:
            do {
                int alt39=2;
                int LA39_0 = input.LA(1);

                if ( (LA39_0==KEYWORD_4) ) {
                    alt39=1;
                }


                switch (alt39) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3744:2: rule__OrderByColumns__Group_1_1__0
            	    {
            	    pushFollow(FOLLOW_rule__OrderByColumns__Group_1_1__0_in_rule__OrderByColumns__Group_1__1__Impl7791);
            	    rule__OrderByColumns__Group_1_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop39;
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3759:1: rule__OrderByColumns__Group_1_1__0 : rule__OrderByColumns__Group_1_1__0__Impl rule__OrderByColumns__Group_1_1__1 ;
    public final void rule__OrderByColumns__Group_1_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3763:1: ( rule__OrderByColumns__Group_1_1__0__Impl rule__OrderByColumns__Group_1_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3764:2: rule__OrderByColumns__Group_1_1__0__Impl rule__OrderByColumns__Group_1_1__1
            {
            pushFollow(FOLLOW_rule__OrderByColumns__Group_1_1__0__Impl_in_rule__OrderByColumns__Group_1_1__07828);
            rule__OrderByColumns__Group_1_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__OrderByColumns__Group_1_1__1_in_rule__OrderByColumns__Group_1_1__07831);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3771:1: rule__OrderByColumns__Group_1_1__0__Impl : ( KEYWORD_4 ) ;
    public final void rule__OrderByColumns__Group_1_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3775:1: ( ( KEYWORD_4 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3776:1: ( KEYWORD_4 )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3776:1: ( KEYWORD_4 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3777:1: KEYWORD_4
            {
             before(grammarAccess.getOrderByColumnsAccess().getCommaKeyword_1_1_0()); 
            match(input,KEYWORD_4,FOLLOW_KEYWORD_4_in_rule__OrderByColumns__Group_1_1__0__Impl7859); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3790:1: rule__OrderByColumns__Group_1_1__1 : rule__OrderByColumns__Group_1_1__1__Impl ;
    public final void rule__OrderByColumns__Group_1_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3794:1: ( rule__OrderByColumns__Group_1_1__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3795:2: rule__OrderByColumns__Group_1_1__1__Impl
            {
            pushFollow(FOLLOW_rule__OrderByColumns__Group_1_1__1__Impl_in_rule__OrderByColumns__Group_1_1__17890);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3801:1: rule__OrderByColumns__Group_1_1__1__Impl : ( ( rule__OrderByColumns__EntriesAssignment_1_1_1 ) ) ;
    public final void rule__OrderByColumns__Group_1_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3805:1: ( ( ( rule__OrderByColumns__EntriesAssignment_1_1_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3806:1: ( ( rule__OrderByColumns__EntriesAssignment_1_1_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3806:1: ( ( rule__OrderByColumns__EntriesAssignment_1_1_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3807:1: ( rule__OrderByColumns__EntriesAssignment_1_1_1 )
            {
             before(grammarAccess.getOrderByColumnsAccess().getEntriesAssignment_1_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3808:1: ( rule__OrderByColumns__EntriesAssignment_1_1_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3808:2: rule__OrderByColumns__EntriesAssignment_1_1_1
            {
            pushFollow(FOLLOW_rule__OrderByColumns__EntriesAssignment_1_1_1_in_rule__OrderByColumns__Group_1_1__1__Impl7917);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3822:1: rule__OrderByColumnFull__Group__0 : rule__OrderByColumnFull__Group__0__Impl rule__OrderByColumnFull__Group__1 ;
    public final void rule__OrderByColumnFull__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3826:1: ( rule__OrderByColumnFull__Group__0__Impl rule__OrderByColumnFull__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3827:2: rule__OrderByColumnFull__Group__0__Impl rule__OrderByColumnFull__Group__1
            {
            pushFollow(FOLLOW_rule__OrderByColumnFull__Group__0__Impl_in_rule__OrderByColumnFull__Group__07951);
            rule__OrderByColumnFull__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__OrderByColumnFull__Group__1_in_rule__OrderByColumnFull__Group__07954);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3834:1: rule__OrderByColumnFull__Group__0__Impl : ( ( rule__OrderByColumnFull__ColOrderAssignment_0 ) ) ;
    public final void rule__OrderByColumnFull__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3838:1: ( ( ( rule__OrderByColumnFull__ColOrderAssignment_0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3839:1: ( ( rule__OrderByColumnFull__ColOrderAssignment_0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3839:1: ( ( rule__OrderByColumnFull__ColOrderAssignment_0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3840:1: ( rule__OrderByColumnFull__ColOrderAssignment_0 )
            {
             before(grammarAccess.getOrderByColumnFullAccess().getColOrderAssignment_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3841:1: ( rule__OrderByColumnFull__ColOrderAssignment_0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3841:2: rule__OrderByColumnFull__ColOrderAssignment_0
            {
            pushFollow(FOLLOW_rule__OrderByColumnFull__ColOrderAssignment_0_in_rule__OrderByColumnFull__Group__0__Impl7981);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3851:1: rule__OrderByColumnFull__Group__1 : rule__OrderByColumnFull__Group__1__Impl ;
    public final void rule__OrderByColumnFull__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3855:1: ( rule__OrderByColumnFull__Group__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3856:2: rule__OrderByColumnFull__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__OrderByColumnFull__Group__1__Impl_in_rule__OrderByColumnFull__Group__18011);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3862:1: rule__OrderByColumnFull__Group__1__Impl : ( ( rule__OrderByColumnFull__DirectionAssignment_1 )? ) ;
    public final void rule__OrderByColumnFull__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3866:1: ( ( ( rule__OrderByColumnFull__DirectionAssignment_1 )? ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3867:1: ( ( rule__OrderByColumnFull__DirectionAssignment_1 )? )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3867:1: ( ( rule__OrderByColumnFull__DirectionAssignment_1 )? )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3868:1: ( rule__OrderByColumnFull__DirectionAssignment_1 )?
            {
             before(grammarAccess.getOrderByColumnFullAccess().getDirectionAssignment_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3869:1: ( rule__OrderByColumnFull__DirectionAssignment_1 )?
            int alt40=2;
            int LA40_0 = input.LA(1);

            if ( (LA40_0==KEYWORD_25||LA40_0==KEYWORD_23) ) {
                alt40=1;
            }
            switch (alt40) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3869:2: rule__OrderByColumnFull__DirectionAssignment_1
                    {
                    pushFollow(FOLLOW_rule__OrderByColumnFull__DirectionAssignment_1_in_rule__OrderByColumnFull__Group__1__Impl8038);
                    rule__OrderByColumnFull__DirectionAssignment_1();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getOrderByColumnFullAccess().getDirectionAssignment_1()); 

            }


            }

        }
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3883:1: rule__GroupByColumns__Group__0 : rule__GroupByColumns__Group__0__Impl rule__GroupByColumns__Group__1 ;
    public final void rule__GroupByColumns__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3887:1: ( rule__GroupByColumns__Group__0__Impl rule__GroupByColumns__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3888:2: rule__GroupByColumns__Group__0__Impl rule__GroupByColumns__Group__1
            {
            pushFollow(FOLLOW_rule__GroupByColumns__Group__0__Impl_in_rule__GroupByColumns__Group__08073);
            rule__GroupByColumns__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__GroupByColumns__Group__1_in_rule__GroupByColumns__Group__08076);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3895:1: rule__GroupByColumns__Group__0__Impl : ( ruleColumnFull ) ;
    public final void rule__GroupByColumns__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3899:1: ( ( ruleColumnFull ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3900:1: ( ruleColumnFull )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3900:1: ( ruleColumnFull )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3901:1: ruleColumnFull
            {
             before(grammarAccess.getGroupByColumnsAccess().getColumnFullParserRuleCall_0()); 
            pushFollow(FOLLOW_ruleColumnFull_in_rule__GroupByColumns__Group__0__Impl8103);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3912:1: rule__GroupByColumns__Group__1 : rule__GroupByColumns__Group__1__Impl ;
    public final void rule__GroupByColumns__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3916:1: ( rule__GroupByColumns__Group__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3917:2: rule__GroupByColumns__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__GroupByColumns__Group__1__Impl_in_rule__GroupByColumns__Group__18132);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3923:1: rule__GroupByColumns__Group__1__Impl : ( ( rule__GroupByColumns__Group_1__0 )? ) ;
    public final void rule__GroupByColumns__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3927:1: ( ( ( rule__GroupByColumns__Group_1__0 )? ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3928:1: ( ( rule__GroupByColumns__Group_1__0 )? )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3928:1: ( ( rule__GroupByColumns__Group_1__0 )? )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3929:1: ( rule__GroupByColumns__Group_1__0 )?
            {
             before(grammarAccess.getGroupByColumnsAccess().getGroup_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3930:1: ( rule__GroupByColumns__Group_1__0 )?
            int alt41=2;
            int LA41_0 = input.LA(1);

            if ( (LA41_0==KEYWORD_4) ) {
                alt41=1;
            }
            switch (alt41) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3930:2: rule__GroupByColumns__Group_1__0
                    {
                    pushFollow(FOLLOW_rule__GroupByColumns__Group_1__0_in_rule__GroupByColumns__Group__1__Impl8159);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3944:1: rule__GroupByColumns__Group_1__0 : rule__GroupByColumns__Group_1__0__Impl rule__GroupByColumns__Group_1__1 ;
    public final void rule__GroupByColumns__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3948:1: ( rule__GroupByColumns__Group_1__0__Impl rule__GroupByColumns__Group_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3949:2: rule__GroupByColumns__Group_1__0__Impl rule__GroupByColumns__Group_1__1
            {
            pushFollow(FOLLOW_rule__GroupByColumns__Group_1__0__Impl_in_rule__GroupByColumns__Group_1__08194);
            rule__GroupByColumns__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__GroupByColumns__Group_1__1_in_rule__GroupByColumns__Group_1__08197);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3956:1: rule__GroupByColumns__Group_1__0__Impl : ( () ) ;
    public final void rule__GroupByColumns__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3960:1: ( ( () ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3961:1: ( () )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3961:1: ( () )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3962:1: ()
            {
             before(grammarAccess.getGroupByColumnsAccess().getOrGroupByColumnEntriesAction_1_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3963:1: ()
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3965:1: 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3975:1: rule__GroupByColumns__Group_1__1 : rule__GroupByColumns__Group_1__1__Impl ;
    public final void rule__GroupByColumns__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3979:1: ( rule__GroupByColumns__Group_1__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3980:2: rule__GroupByColumns__Group_1__1__Impl
            {
            pushFollow(FOLLOW_rule__GroupByColumns__Group_1__1__Impl_in_rule__GroupByColumns__Group_1__18255);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3986:1: rule__GroupByColumns__Group_1__1__Impl : ( ( ( rule__GroupByColumns__Group_1_1__0 ) ) ( ( rule__GroupByColumns__Group_1_1__0 )* ) ) ;
    public final void rule__GroupByColumns__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3990:1: ( ( ( ( rule__GroupByColumns__Group_1_1__0 ) ) ( ( rule__GroupByColumns__Group_1_1__0 )* ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3991:1: ( ( ( rule__GroupByColumns__Group_1_1__0 ) ) ( ( rule__GroupByColumns__Group_1_1__0 )* ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3991:1: ( ( ( rule__GroupByColumns__Group_1_1__0 ) ) ( ( rule__GroupByColumns__Group_1_1__0 )* ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3992:1: ( ( rule__GroupByColumns__Group_1_1__0 ) ) ( ( rule__GroupByColumns__Group_1_1__0 )* )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3992:1: ( ( rule__GroupByColumns__Group_1_1__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3993:1: ( rule__GroupByColumns__Group_1_1__0 )
            {
             before(grammarAccess.getGroupByColumnsAccess().getGroup_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3994:1: ( rule__GroupByColumns__Group_1_1__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3994:2: rule__GroupByColumns__Group_1_1__0
            {
            pushFollow(FOLLOW_rule__GroupByColumns__Group_1_1__0_in_rule__GroupByColumns__Group_1__1__Impl8284);
            rule__GroupByColumns__Group_1_1__0();

            state._fsp--;


            }

             after(grammarAccess.getGroupByColumnsAccess().getGroup_1_1()); 

            }

            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3997:1: ( ( rule__GroupByColumns__Group_1_1__0 )* )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3998:1: ( rule__GroupByColumns__Group_1_1__0 )*
            {
             before(grammarAccess.getGroupByColumnsAccess().getGroup_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3999:1: ( rule__GroupByColumns__Group_1_1__0 )*
            loop42:
            do {
                int alt42=2;
                int LA42_0 = input.LA(1);

                if ( (LA42_0==KEYWORD_4) ) {
                    alt42=1;
                }


                switch (alt42) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3999:2: rule__GroupByColumns__Group_1_1__0
            	    {
            	    pushFollow(FOLLOW_rule__GroupByColumns__Group_1_1__0_in_rule__GroupByColumns__Group_1__1__Impl8296);
            	    rule__GroupByColumns__Group_1_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop42;
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4014:1: rule__GroupByColumns__Group_1_1__0 : rule__GroupByColumns__Group_1_1__0__Impl rule__GroupByColumns__Group_1_1__1 ;
    public final void rule__GroupByColumns__Group_1_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4018:1: ( rule__GroupByColumns__Group_1_1__0__Impl rule__GroupByColumns__Group_1_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4019:2: rule__GroupByColumns__Group_1_1__0__Impl rule__GroupByColumns__Group_1_1__1
            {
            pushFollow(FOLLOW_rule__GroupByColumns__Group_1_1__0__Impl_in_rule__GroupByColumns__Group_1_1__08333);
            rule__GroupByColumns__Group_1_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__GroupByColumns__Group_1_1__1_in_rule__GroupByColumns__Group_1_1__08336);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4026:1: rule__GroupByColumns__Group_1_1__0__Impl : ( KEYWORD_4 ) ;
    public final void rule__GroupByColumns__Group_1_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4030:1: ( ( KEYWORD_4 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4031:1: ( KEYWORD_4 )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4031:1: ( KEYWORD_4 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4032:1: KEYWORD_4
            {
             before(grammarAccess.getGroupByColumnsAccess().getCommaKeyword_1_1_0()); 
            match(input,KEYWORD_4,FOLLOW_KEYWORD_4_in_rule__GroupByColumns__Group_1_1__0__Impl8364); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4045:1: rule__GroupByColumns__Group_1_1__1 : rule__GroupByColumns__Group_1_1__1__Impl ;
    public final void rule__GroupByColumns__Group_1_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4049:1: ( rule__GroupByColumns__Group_1_1__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4050:2: rule__GroupByColumns__Group_1_1__1__Impl
            {
            pushFollow(FOLLOW_rule__GroupByColumns__Group_1_1__1__Impl_in_rule__GroupByColumns__Group_1_1__18395);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4056:1: rule__GroupByColumns__Group_1_1__1__Impl : ( ( rule__GroupByColumns__EntriesAssignment_1_1_1 ) ) ;
    public final void rule__GroupByColumns__Group_1_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4060:1: ( ( ( rule__GroupByColumns__EntriesAssignment_1_1_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4061:1: ( ( rule__GroupByColumns__EntriesAssignment_1_1_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4061:1: ( ( rule__GroupByColumns__EntriesAssignment_1_1_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4062:1: ( rule__GroupByColumns__EntriesAssignment_1_1_1 )
            {
             before(grammarAccess.getGroupByColumnsAccess().getEntriesAssignment_1_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4063:1: ( rule__GroupByColumns__EntriesAssignment_1_1_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4063:2: rule__GroupByColumns__EntriesAssignment_1_1_1
            {
            pushFollow(FOLLOW_rule__GroupByColumns__EntriesAssignment_1_1_1_in_rule__GroupByColumns__Group_1_1__1__Impl8422);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4077:1: rule__FullExpression__Group__0 : rule__FullExpression__Group__0__Impl rule__FullExpression__Group__1 ;
    public final void rule__FullExpression__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4081:1: ( rule__FullExpression__Group__0__Impl rule__FullExpression__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4082:2: rule__FullExpression__Group__0__Impl rule__FullExpression__Group__1
            {
            pushFollow(FOLLOW_rule__FullExpression__Group__0__Impl_in_rule__FullExpression__Group__08456);
            rule__FullExpression__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__FullExpression__Group__1_in_rule__FullExpression__Group__08459);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4089:1: rule__FullExpression__Group__0__Impl : ( ruleExpressionFragment ) ;
    public final void rule__FullExpression__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4093:1: ( ( ruleExpressionFragment ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4094:1: ( ruleExpressionFragment )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4094:1: ( ruleExpressionFragment )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4095:1: ruleExpressionFragment
            {
             before(grammarAccess.getFullExpressionAccess().getExpressionFragmentParserRuleCall_0()); 
            pushFollow(FOLLOW_ruleExpressionFragment_in_rule__FullExpression__Group__0__Impl8486);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4106:1: rule__FullExpression__Group__1 : rule__FullExpression__Group__1__Impl ;
    public final void rule__FullExpression__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4110:1: ( rule__FullExpression__Group__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4111:2: rule__FullExpression__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__FullExpression__Group__1__Impl_in_rule__FullExpression__Group__18515);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4117:1: rule__FullExpression__Group__1__Impl : ( ( rule__FullExpression__Group_1__0 )? ) ;
    public final void rule__FullExpression__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4121:1: ( ( ( rule__FullExpression__Group_1__0 )? ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4122:1: ( ( rule__FullExpression__Group_1__0 )? )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4122:1: ( ( rule__FullExpression__Group_1__0 )? )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4123:1: ( rule__FullExpression__Group_1__0 )?
            {
             before(grammarAccess.getFullExpressionAccess().getGroup_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4124:1: ( rule__FullExpression__Group_1__0 )?
            int alt43=2;
            int LA43_0 = input.LA(1);

            if ( (LA43_0==KEYWORD_22||LA43_0==KEYWORD_18) ) {
                alt43=1;
            }
            switch (alt43) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4124:2: rule__FullExpression__Group_1__0
                    {
                    pushFollow(FOLLOW_rule__FullExpression__Group_1__0_in_rule__FullExpression__Group__1__Impl8542);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4138:1: rule__FullExpression__Group_1__0 : rule__FullExpression__Group_1__0__Impl rule__FullExpression__Group_1__1 ;
    public final void rule__FullExpression__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4142:1: ( rule__FullExpression__Group_1__0__Impl rule__FullExpression__Group_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4143:2: rule__FullExpression__Group_1__0__Impl rule__FullExpression__Group_1__1
            {
            pushFollow(FOLLOW_rule__FullExpression__Group_1__0__Impl_in_rule__FullExpression__Group_1__08577);
            rule__FullExpression__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__FullExpression__Group_1__1_in_rule__FullExpression__Group_1__08580);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4150:1: rule__FullExpression__Group_1__0__Impl : ( () ) ;
    public final void rule__FullExpression__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4154:1: ( ( () ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4155:1: ( () )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4155:1: ( () )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4156:1: ()
            {
             before(grammarAccess.getFullExpressionAccess().getOrExprEntriesAction_1_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4157:1: ()
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4159:1: 
            {
            }

             after(grammarAccess.getFullExpressionAccess().getOrExprEntriesAction_1_0()); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4169:1: rule__FullExpression__Group_1__1 : rule__FullExpression__Group_1__1__Impl ;
    public final void rule__FullExpression__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4173:1: ( rule__FullExpression__Group_1__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4174:2: rule__FullExpression__Group_1__1__Impl
            {
            pushFollow(FOLLOW_rule__FullExpression__Group_1__1__Impl_in_rule__FullExpression__Group_1__18638);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4180:1: rule__FullExpression__Group_1__1__Impl : ( ( ( rule__FullExpression__EntriesAssignment_1_1 ) ) ( ( rule__FullExpression__EntriesAssignment_1_1 )* ) ) ;
    public final void rule__FullExpression__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4184:1: ( ( ( ( rule__FullExpression__EntriesAssignment_1_1 ) ) ( ( rule__FullExpression__EntriesAssignment_1_1 )* ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4185:1: ( ( ( rule__FullExpression__EntriesAssignment_1_1 ) ) ( ( rule__FullExpression__EntriesAssignment_1_1 )* ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4185:1: ( ( ( rule__FullExpression__EntriesAssignment_1_1 ) ) ( ( rule__FullExpression__EntriesAssignment_1_1 )* ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4186:1: ( ( rule__FullExpression__EntriesAssignment_1_1 ) ) ( ( rule__FullExpression__EntriesAssignment_1_1 )* )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4186:1: ( ( rule__FullExpression__EntriesAssignment_1_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4187:1: ( rule__FullExpression__EntriesAssignment_1_1 )
            {
             before(grammarAccess.getFullExpressionAccess().getEntriesAssignment_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4188:1: ( rule__FullExpression__EntriesAssignment_1_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4188:2: rule__FullExpression__EntriesAssignment_1_1
            {
            pushFollow(FOLLOW_rule__FullExpression__EntriesAssignment_1_1_in_rule__FullExpression__Group_1__1__Impl8667);
            rule__FullExpression__EntriesAssignment_1_1();

            state._fsp--;


            }

             after(grammarAccess.getFullExpressionAccess().getEntriesAssignment_1_1()); 

            }

            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4191:1: ( ( rule__FullExpression__EntriesAssignment_1_1 )* )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4192:1: ( rule__FullExpression__EntriesAssignment_1_1 )*
            {
             before(grammarAccess.getFullExpressionAccess().getEntriesAssignment_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4193:1: ( rule__FullExpression__EntriesAssignment_1_1 )*
            loop44:
            do {
                int alt44=2;
                int LA44_0 = input.LA(1);

                if ( (LA44_0==KEYWORD_22||LA44_0==KEYWORD_18) ) {
                    alt44=1;
                }


                switch (alt44) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4193:2: rule__FullExpression__EntriesAssignment_1_1
            	    {
            	    pushFollow(FOLLOW_rule__FullExpression__EntriesAssignment_1_1_in_rule__FullExpression__Group_1__1__Impl8679);
            	    rule__FullExpression__EntriesAssignment_1_1();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop44;
                }
            } while (true);

             after(grammarAccess.getFullExpressionAccess().getEntriesAssignment_1_1()); 

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


    // $ANTLR start "rule__ExpressionFragmentSecond__Group__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4208:1: rule__ExpressionFragmentSecond__Group__0 : rule__ExpressionFragmentSecond__Group__0__Impl rule__ExpressionFragmentSecond__Group__1 ;
    public final void rule__ExpressionFragmentSecond__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4212:1: ( rule__ExpressionFragmentSecond__Group__0__Impl rule__ExpressionFragmentSecond__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4213:2: rule__ExpressionFragmentSecond__Group__0__Impl rule__ExpressionFragmentSecond__Group__1
            {
            pushFollow(FOLLOW_rule__ExpressionFragmentSecond__Group__0__Impl_in_rule__ExpressionFragmentSecond__Group__08716);
            rule__ExpressionFragmentSecond__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ExpressionFragmentSecond__Group__1_in_rule__ExpressionFragmentSecond__Group__08719);
            rule__ExpressionFragmentSecond__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ExpressionFragmentSecond__Group__0"


    // $ANTLR start "rule__ExpressionFragmentSecond__Group__0__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4220:1: rule__ExpressionFragmentSecond__Group__0__Impl : ( ( rule__ExpressionFragmentSecond__CAssignment_0 ) ) ;
    public final void rule__ExpressionFragmentSecond__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4224:1: ( ( ( rule__ExpressionFragmentSecond__CAssignment_0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4225:1: ( ( rule__ExpressionFragmentSecond__CAssignment_0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4225:1: ( ( rule__ExpressionFragmentSecond__CAssignment_0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4226:1: ( rule__ExpressionFragmentSecond__CAssignment_0 )
            {
             before(grammarAccess.getExpressionFragmentSecondAccess().getCAssignment_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4227:1: ( rule__ExpressionFragmentSecond__CAssignment_0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4227:2: rule__ExpressionFragmentSecond__CAssignment_0
            {
            pushFollow(FOLLOW_rule__ExpressionFragmentSecond__CAssignment_0_in_rule__ExpressionFragmentSecond__Group__0__Impl8746);
            rule__ExpressionFragmentSecond__CAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getExpressionFragmentSecondAccess().getCAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ExpressionFragmentSecond__Group__0__Impl"


    // $ANTLR start "rule__ExpressionFragmentSecond__Group__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4237:1: rule__ExpressionFragmentSecond__Group__1 : rule__ExpressionFragmentSecond__Group__1__Impl ;
    public final void rule__ExpressionFragmentSecond__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4241:1: ( rule__ExpressionFragmentSecond__Group__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4242:2: rule__ExpressionFragmentSecond__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__ExpressionFragmentSecond__Group__1__Impl_in_rule__ExpressionFragmentSecond__Group__18776);
            rule__ExpressionFragmentSecond__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ExpressionFragmentSecond__Group__1"


    // $ANTLR start "rule__ExpressionFragmentSecond__Group__1__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4248:1: rule__ExpressionFragmentSecond__Group__1__Impl : ( ( rule__ExpressionFragmentSecond__EfragAssignment_1 ) ) ;
    public final void rule__ExpressionFragmentSecond__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4252:1: ( ( ( rule__ExpressionFragmentSecond__EfragAssignment_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4253:1: ( ( rule__ExpressionFragmentSecond__EfragAssignment_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4253:1: ( ( rule__ExpressionFragmentSecond__EfragAssignment_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4254:1: ( rule__ExpressionFragmentSecond__EfragAssignment_1 )
            {
             before(grammarAccess.getExpressionFragmentSecondAccess().getEfragAssignment_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4255:1: ( rule__ExpressionFragmentSecond__EfragAssignment_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4255:2: rule__ExpressionFragmentSecond__EfragAssignment_1
            {
            pushFollow(FOLLOW_rule__ExpressionFragmentSecond__EfragAssignment_1_in_rule__ExpressionFragmentSecond__Group__1__Impl8803);
            rule__ExpressionFragmentSecond__EfragAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getExpressionFragmentSecondAccess().getEfragAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ExpressionFragmentSecond__Group__1__Impl"


    // $ANTLR start "rule__ExpressionGroup__Group__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4269:1: rule__ExpressionGroup__Group__0 : rule__ExpressionGroup__Group__0__Impl rule__ExpressionGroup__Group__1 ;
    public final void rule__ExpressionGroup__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4273:1: ( rule__ExpressionGroup__Group__0__Impl rule__ExpressionGroup__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4274:2: rule__ExpressionGroup__Group__0__Impl rule__ExpressionGroup__Group__1
            {
            pushFollow(FOLLOW_rule__ExpressionGroup__Group__0__Impl_in_rule__ExpressionGroup__Group__08837);
            rule__ExpressionGroup__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ExpressionGroup__Group__1_in_rule__ExpressionGroup__Group__08840);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4281:1: rule__ExpressionGroup__Group__0__Impl : ( () ) ;
    public final void rule__ExpressionGroup__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4285:1: ( ( () ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4286:1: ( () )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4286:1: ( () )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4287:1: ()
            {
             before(grammarAccess.getExpressionGroupAccess().getExprGroupAction_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4288:1: ()
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4290:1: 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4300:1: rule__ExpressionGroup__Group__1 : rule__ExpressionGroup__Group__1__Impl rule__ExpressionGroup__Group__2 ;
    public final void rule__ExpressionGroup__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4304:1: ( rule__ExpressionGroup__Group__1__Impl rule__ExpressionGroup__Group__2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4305:2: rule__ExpressionGroup__Group__1__Impl rule__ExpressionGroup__Group__2
            {
            pushFollow(FOLLOW_rule__ExpressionGroup__Group__1__Impl_in_rule__ExpressionGroup__Group__18898);
            rule__ExpressionGroup__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ExpressionGroup__Group__2_in_rule__ExpressionGroup__Group__18901);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4312:1: rule__ExpressionGroup__Group__1__Impl : ( KEYWORD_1 ) ;
    public final void rule__ExpressionGroup__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4316:1: ( ( KEYWORD_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4317:1: ( KEYWORD_1 )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4317:1: ( KEYWORD_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4318:1: KEYWORD_1
            {
             before(grammarAccess.getExpressionGroupAccess().getLeftParenthesisKeyword_1()); 
            match(input,KEYWORD_1,FOLLOW_KEYWORD_1_in_rule__ExpressionGroup__Group__1__Impl8929); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4331:1: rule__ExpressionGroup__Group__2 : rule__ExpressionGroup__Group__2__Impl rule__ExpressionGroup__Group__3 ;
    public final void rule__ExpressionGroup__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4335:1: ( rule__ExpressionGroup__Group__2__Impl rule__ExpressionGroup__Group__3 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4336:2: rule__ExpressionGroup__Group__2__Impl rule__ExpressionGroup__Group__3
            {
            pushFollow(FOLLOW_rule__ExpressionGroup__Group__2__Impl_in_rule__ExpressionGroup__Group__28960);
            rule__ExpressionGroup__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ExpressionGroup__Group__3_in_rule__ExpressionGroup__Group__28963);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4343:1: rule__ExpressionGroup__Group__2__Impl : ( ( rule__ExpressionGroup__ExprAssignment_2 ) ) ;
    public final void rule__ExpressionGroup__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4347:1: ( ( ( rule__ExpressionGroup__ExprAssignment_2 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4348:1: ( ( rule__ExpressionGroup__ExprAssignment_2 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4348:1: ( ( rule__ExpressionGroup__ExprAssignment_2 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4349:1: ( rule__ExpressionGroup__ExprAssignment_2 )
            {
             before(grammarAccess.getExpressionGroupAccess().getExprAssignment_2()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4350:1: ( rule__ExpressionGroup__ExprAssignment_2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4350:2: rule__ExpressionGroup__ExprAssignment_2
            {
            pushFollow(FOLLOW_rule__ExpressionGroup__ExprAssignment_2_in_rule__ExpressionGroup__Group__2__Impl8990);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4360:1: rule__ExpressionGroup__Group__3 : rule__ExpressionGroup__Group__3__Impl ;
    public final void rule__ExpressionGroup__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4364:1: ( rule__ExpressionGroup__Group__3__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4365:2: rule__ExpressionGroup__Group__3__Impl
            {
            pushFollow(FOLLOW_rule__ExpressionGroup__Group__3__Impl_in_rule__ExpressionGroup__Group__39020);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4371:1: rule__ExpressionGroup__Group__3__Impl : ( KEYWORD_2 ) ;
    public final void rule__ExpressionGroup__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4375:1: ( ( KEYWORD_2 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4376:1: ( KEYWORD_2 )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4376:1: ( KEYWORD_2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4377:1: KEYWORD_2
            {
             before(grammarAccess.getExpressionGroupAccess().getRightParenthesisKeyword_3()); 
            match(input,KEYWORD_2,FOLLOW_KEYWORD_2_in_rule__ExpressionGroup__Group__3__Impl9048); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4398:1: rule__XExpression__Group__0 : rule__XExpression__Group__0__Impl rule__XExpression__Group__1 ;
    public final void rule__XExpression__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4402:1: ( rule__XExpression__Group__0__Impl rule__XExpression__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4403:2: rule__XExpression__Group__0__Impl rule__XExpression__Group__1
            {
            pushFollow(FOLLOW_rule__XExpression__Group__0__Impl_in_rule__XExpression__Group__09087);
            rule__XExpression__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__XExpression__Group__1_in_rule__XExpression__Group__09090);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4410:1: rule__XExpression__Group__0__Impl : ( () ) ;
    public final void rule__XExpression__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4414:1: ( ( () ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4415:1: ( () )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4415:1: ( () )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4416:1: ()
            {
             before(grammarAccess.getXExpressionAccess().getXExprAction_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4417:1: ()
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4419:1: 
            {
            }

             after(grammarAccess.getXExpressionAccess().getXExprAction_0()); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4429:1: rule__XExpression__Group__1 : rule__XExpression__Group__1__Impl rule__XExpression__Group__2 ;
    public final void rule__XExpression__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4433:1: ( rule__XExpression__Group__1__Impl rule__XExpression__Group__2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4434:2: rule__XExpression__Group__1__Impl rule__XExpression__Group__2
            {
            pushFollow(FOLLOW_rule__XExpression__Group__1__Impl_in_rule__XExpression__Group__19148);
            rule__XExpression__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__XExpression__Group__2_in_rule__XExpression__Group__19151);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4441:1: rule__XExpression__Group__1__Impl : ( KEYWORD_21 ) ;
    public final void rule__XExpression__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4445:1: ( ( KEYWORD_21 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4446:1: ( KEYWORD_21 )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4446:1: ( KEYWORD_21 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4447:1: KEYWORD_21
            {
             before(grammarAccess.getXExpressionAccess().getXKeyword_1()); 
            match(input,KEYWORD_21,FOLLOW_KEYWORD_21_in_rule__XExpression__Group__1__Impl9179); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4460:1: rule__XExpression__Group__2 : rule__XExpression__Group__2__Impl rule__XExpression__Group__3 ;
    public final void rule__XExpression__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4464:1: ( rule__XExpression__Group__2__Impl rule__XExpression__Group__3 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4465:2: rule__XExpression__Group__2__Impl rule__XExpression__Group__3
            {
            pushFollow(FOLLOW_rule__XExpression__Group__2__Impl_in_rule__XExpression__Group__29210);
            rule__XExpression__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__XExpression__Group__3_in_rule__XExpression__Group__29213);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4472:1: rule__XExpression__Group__2__Impl : ( ( rule__XExpression__XfAssignment_2 ) ) ;
    public final void rule__XExpression__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4476:1: ( ( ( rule__XExpression__XfAssignment_2 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4477:1: ( ( rule__XExpression__XfAssignment_2 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4477:1: ( ( rule__XExpression__XfAssignment_2 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4478:1: ( rule__XExpression__XfAssignment_2 )
            {
             before(grammarAccess.getXExpressionAccess().getXfAssignment_2()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4479:1: ( rule__XExpression__XfAssignment_2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4479:2: rule__XExpression__XfAssignment_2
            {
            pushFollow(FOLLOW_rule__XExpression__XfAssignment_2_in_rule__XExpression__Group__2__Impl9240);
            rule__XExpression__XfAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getXExpressionAccess().getXfAssignment_2()); 

            }


            }

        }
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4489:1: rule__XExpression__Group__3 : rule__XExpression__Group__3__Impl rule__XExpression__Group__4 ;
    public final void rule__XExpression__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4493:1: ( rule__XExpression__Group__3__Impl rule__XExpression__Group__4 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4494:2: rule__XExpression__Group__3__Impl rule__XExpression__Group__4
            {
            pushFollow(FOLLOW_rule__XExpression__Group__3__Impl_in_rule__XExpression__Group__39270);
            rule__XExpression__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__XExpression__Group__4_in_rule__XExpression__Group__39273);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4501:1: rule__XExpression__Group__3__Impl : ( KEYWORD_4 ) ;
    public final void rule__XExpression__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4505:1: ( ( KEYWORD_4 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4506:1: ( KEYWORD_4 )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4506:1: ( KEYWORD_4 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4507:1: KEYWORD_4
            {
             before(grammarAccess.getXExpressionAccess().getCommaKeyword_3()); 
            match(input,KEYWORD_4,FOLLOW_KEYWORD_4_in_rule__XExpression__Group__3__Impl9301); 
             after(grammarAccess.getXExpressionAccess().getCommaKeyword_3()); 

            }


            }

        }
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4520:1: rule__XExpression__Group__4 : rule__XExpression__Group__4__Impl rule__XExpression__Group__5 ;
    public final void rule__XExpression__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4524:1: ( rule__XExpression__Group__4__Impl rule__XExpression__Group__5 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4525:2: rule__XExpression__Group__4__Impl rule__XExpression__Group__5
            {
            pushFollow(FOLLOW_rule__XExpression__Group__4__Impl_in_rule__XExpression__Group__49332);
            rule__XExpression__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__XExpression__Group__5_in_rule__XExpression__Group__49335);
            rule__XExpression__Group__5();

            state._fsp--;


            }

        }
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4532:1: rule__XExpression__Group__4__Impl : ( ( rule__XExpression__ColAssignment_4 ) ) ;
    public final void rule__XExpression__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4536:1: ( ( ( rule__XExpression__ColAssignment_4 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4537:1: ( ( rule__XExpression__ColAssignment_4 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4537:1: ( ( rule__XExpression__ColAssignment_4 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4538:1: ( rule__XExpression__ColAssignment_4 )
            {
             before(grammarAccess.getXExpressionAccess().getColAssignment_4()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4539:1: ( rule__XExpression__ColAssignment_4 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4539:2: rule__XExpression__ColAssignment_4
            {
            pushFollow(FOLLOW_rule__XExpression__ColAssignment_4_in_rule__XExpression__Group__4__Impl9362);
            rule__XExpression__ColAssignment_4();

            state._fsp--;


            }

             after(grammarAccess.getXExpressionAccess().getColAssignment_4()); 

            }


            }

        }
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


    // $ANTLR start "rule__XExpression__Group__5"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4549:1: rule__XExpression__Group__5 : rule__XExpression__Group__5__Impl rule__XExpression__Group__6 ;
    public final void rule__XExpression__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4553:1: ( rule__XExpression__Group__5__Impl rule__XExpression__Group__6 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4554:2: rule__XExpression__Group__5__Impl rule__XExpression__Group__6
            {
            pushFollow(FOLLOW_rule__XExpression__Group__5__Impl_in_rule__XExpression__Group__59392);
            rule__XExpression__Group__5__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__XExpression__Group__6_in_rule__XExpression__Group__59395);
            rule__XExpression__Group__6();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XExpression__Group__5"


    // $ANTLR start "rule__XExpression__Group__5__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4561:1: rule__XExpression__Group__5__Impl : ( ( rule__XExpression__Group_5__0 )? ) ;
    public final void rule__XExpression__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4565:1: ( ( ( rule__XExpression__Group_5__0 )? ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4566:1: ( ( rule__XExpression__Group_5__0 )? )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4566:1: ( ( rule__XExpression__Group_5__0 )? )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4567:1: ( rule__XExpression__Group_5__0 )?
            {
             before(grammarAccess.getXExpressionAccess().getGroup_5()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4568:1: ( rule__XExpression__Group_5__0 )?
            int alt45=2;
            int LA45_0 = input.LA(1);

            if ( (LA45_0==KEYWORD_4) ) {
                alt45=1;
            }
            switch (alt45) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4568:2: rule__XExpression__Group_5__0
                    {
                    pushFollow(FOLLOW_rule__XExpression__Group_5__0_in_rule__XExpression__Group__5__Impl9422);
                    rule__XExpression__Group_5__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getXExpressionAccess().getGroup_5()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XExpression__Group__5__Impl"


    // $ANTLR start "rule__XExpression__Group__6"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4578:1: rule__XExpression__Group__6 : rule__XExpression__Group__6__Impl ;
    public final void rule__XExpression__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4582:1: ( rule__XExpression__Group__6__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4583:2: rule__XExpression__Group__6__Impl
            {
            pushFollow(FOLLOW_rule__XExpression__Group__6__Impl_in_rule__XExpression__Group__69453);
            rule__XExpression__Group__6__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XExpression__Group__6"


    // $ANTLR start "rule__XExpression__Group__6__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4589:1: rule__XExpression__Group__6__Impl : ( KEYWORD_11 ) ;
    public final void rule__XExpression__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4593:1: ( ( KEYWORD_11 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4594:1: ( KEYWORD_11 )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4594:1: ( KEYWORD_11 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4595:1: KEYWORD_11
            {
             before(grammarAccess.getXExpressionAccess().getRightCurlyBracketKeyword_6()); 
            match(input,KEYWORD_11,FOLLOW_KEYWORD_11_in_rule__XExpression__Group__6__Impl9481); 
             after(grammarAccess.getXExpressionAccess().getRightCurlyBracketKeyword_6()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XExpression__Group__6__Impl"


    // $ANTLR start "rule__XExpression__Group_5__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4622:1: rule__XExpression__Group_5__0 : rule__XExpression__Group_5__0__Impl rule__XExpression__Group_5__1 ;
    public final void rule__XExpression__Group_5__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4626:1: ( rule__XExpression__Group_5__0__Impl rule__XExpression__Group_5__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4627:2: rule__XExpression__Group_5__0__Impl rule__XExpression__Group_5__1
            {
            pushFollow(FOLLOW_rule__XExpression__Group_5__0__Impl_in_rule__XExpression__Group_5__09526);
            rule__XExpression__Group_5__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__XExpression__Group_5__1_in_rule__XExpression__Group_5__09529);
            rule__XExpression__Group_5__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XExpression__Group_5__0"


    // $ANTLR start "rule__XExpression__Group_5__0__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4634:1: rule__XExpression__Group_5__0__Impl : ( KEYWORD_4 ) ;
    public final void rule__XExpression__Group_5__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4638:1: ( ( KEYWORD_4 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4639:1: ( KEYWORD_4 )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4639:1: ( KEYWORD_4 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4640:1: KEYWORD_4
            {
             before(grammarAccess.getXExpressionAccess().getCommaKeyword_5_0()); 
            match(input,KEYWORD_4,FOLLOW_KEYWORD_4_in_rule__XExpression__Group_5__0__Impl9557); 
             after(grammarAccess.getXExpressionAccess().getCommaKeyword_5_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XExpression__Group_5__0__Impl"


    // $ANTLR start "rule__XExpression__Group_5__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4653:1: rule__XExpression__Group_5__1 : rule__XExpression__Group_5__1__Impl ;
    public final void rule__XExpression__Group_5__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4657:1: ( rule__XExpression__Group_5__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4658:2: rule__XExpression__Group_5__1__Impl
            {
            pushFollow(FOLLOW_rule__XExpression__Group_5__1__Impl_in_rule__XExpression__Group_5__19588);
            rule__XExpression__Group_5__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XExpression__Group_5__1"


    // $ANTLR start "rule__XExpression__Group_5__1__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4664:1: rule__XExpression__Group_5__1__Impl : ( ( rule__XExpression__PrmAssignment_5_1 ) ) ;
    public final void rule__XExpression__Group_5__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4668:1: ( ( ( rule__XExpression__PrmAssignment_5_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4669:1: ( ( rule__XExpression__PrmAssignment_5_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4669:1: ( ( rule__XExpression__PrmAssignment_5_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4670:1: ( rule__XExpression__PrmAssignment_5_1 )
            {
             before(grammarAccess.getXExpressionAccess().getPrmAssignment_5_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4671:1: ( rule__XExpression__PrmAssignment_5_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4671:2: rule__XExpression__PrmAssignment_5_1
            {
            pushFollow(FOLLOW_rule__XExpression__PrmAssignment_5_1_in_rule__XExpression__Group_5__1__Impl9615);
            rule__XExpression__PrmAssignment_5_1();

            state._fsp--;


            }

             after(grammarAccess.getXExpressionAccess().getPrmAssignment_5_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XExpression__Group_5__1__Impl"


    // $ANTLR start "rule__XExpressionParams__Group__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4685:1: rule__XExpressionParams__Group__0 : rule__XExpressionParams__Group__0__Impl rule__XExpressionParams__Group__1 ;
    public final void rule__XExpressionParams__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4689:1: ( rule__XExpressionParams__Group__0__Impl rule__XExpressionParams__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4690:2: rule__XExpressionParams__Group__0__Impl rule__XExpressionParams__Group__1
            {
            pushFollow(FOLLOW_rule__XExpressionParams__Group__0__Impl_in_rule__XExpressionParams__Group__09649);
            rule__XExpressionParams__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__XExpressionParams__Group__1_in_rule__XExpressionParams__Group__09652);
            rule__XExpressionParams__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XExpressionParams__Group__0"


    // $ANTLR start "rule__XExpressionParams__Group__0__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4697:1: rule__XExpressionParams__Group__0__Impl : ( ruleJRParameter ) ;
    public final void rule__XExpressionParams__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4701:1: ( ( ruleJRParameter ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4702:1: ( ruleJRParameter )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4702:1: ( ruleJRParameter )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4703:1: ruleJRParameter
            {
             before(grammarAccess.getXExpressionParamsAccess().getJRParameterParserRuleCall_0()); 
            pushFollow(FOLLOW_ruleJRParameter_in_rule__XExpressionParams__Group__0__Impl9679);
            ruleJRParameter();

            state._fsp--;

             after(grammarAccess.getXExpressionParamsAccess().getJRParameterParserRuleCall_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XExpressionParams__Group__0__Impl"


    // $ANTLR start "rule__XExpressionParams__Group__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4714:1: rule__XExpressionParams__Group__1 : rule__XExpressionParams__Group__1__Impl ;
    public final void rule__XExpressionParams__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4718:1: ( rule__XExpressionParams__Group__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4719:2: rule__XExpressionParams__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__XExpressionParams__Group__1__Impl_in_rule__XExpressionParams__Group__19708);
            rule__XExpressionParams__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XExpressionParams__Group__1"


    // $ANTLR start "rule__XExpressionParams__Group__1__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4725:1: rule__XExpressionParams__Group__1__Impl : ( ( rule__XExpressionParams__Group_1__0 )? ) ;
    public final void rule__XExpressionParams__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4729:1: ( ( ( rule__XExpressionParams__Group_1__0 )? ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4730:1: ( ( rule__XExpressionParams__Group_1__0 )? )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4730:1: ( ( rule__XExpressionParams__Group_1__0 )? )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4731:1: ( rule__XExpressionParams__Group_1__0 )?
            {
             before(grammarAccess.getXExpressionParamsAccess().getGroup_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4732:1: ( rule__XExpressionParams__Group_1__0 )?
            int alt46=2;
            int LA46_0 = input.LA(1);

            if ( (LA46_0==KEYWORD_4) ) {
                alt46=1;
            }
            switch (alt46) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4732:2: rule__XExpressionParams__Group_1__0
                    {
                    pushFollow(FOLLOW_rule__XExpressionParams__Group_1__0_in_rule__XExpressionParams__Group__1__Impl9735);
                    rule__XExpressionParams__Group_1__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getXExpressionParamsAccess().getGroup_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XExpressionParams__Group__1__Impl"


    // $ANTLR start "rule__XExpressionParams__Group_1__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4746:1: rule__XExpressionParams__Group_1__0 : rule__XExpressionParams__Group_1__0__Impl rule__XExpressionParams__Group_1__1 ;
    public final void rule__XExpressionParams__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4750:1: ( rule__XExpressionParams__Group_1__0__Impl rule__XExpressionParams__Group_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4751:2: rule__XExpressionParams__Group_1__0__Impl rule__XExpressionParams__Group_1__1
            {
            pushFollow(FOLLOW_rule__XExpressionParams__Group_1__0__Impl_in_rule__XExpressionParams__Group_1__09770);
            rule__XExpressionParams__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__XExpressionParams__Group_1__1_in_rule__XExpressionParams__Group_1__09773);
            rule__XExpressionParams__Group_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XExpressionParams__Group_1__0"


    // $ANTLR start "rule__XExpressionParams__Group_1__0__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4758:1: rule__XExpressionParams__Group_1__0__Impl : ( () ) ;
    public final void rule__XExpressionParams__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4762:1: ( ( () ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4763:1: ( () )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4763:1: ( () )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4764:1: ()
            {
             before(grammarAccess.getXExpressionParamsAccess().getPrmsEntriesAction_1_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4765:1: ()
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4767:1: 
            {
            }

             after(grammarAccess.getXExpressionParamsAccess().getPrmsEntriesAction_1_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XExpressionParams__Group_1__0__Impl"


    // $ANTLR start "rule__XExpressionParams__Group_1__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4777:1: rule__XExpressionParams__Group_1__1 : rule__XExpressionParams__Group_1__1__Impl ;
    public final void rule__XExpressionParams__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4781:1: ( rule__XExpressionParams__Group_1__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4782:2: rule__XExpressionParams__Group_1__1__Impl
            {
            pushFollow(FOLLOW_rule__XExpressionParams__Group_1__1__Impl_in_rule__XExpressionParams__Group_1__19831);
            rule__XExpressionParams__Group_1__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XExpressionParams__Group_1__1"


    // $ANTLR start "rule__XExpressionParams__Group_1__1__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4788:1: rule__XExpressionParams__Group_1__1__Impl : ( ( ( rule__XExpressionParams__Group_1_1__0 ) ) ( ( rule__XExpressionParams__Group_1_1__0 )* ) ) ;
    public final void rule__XExpressionParams__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4792:1: ( ( ( ( rule__XExpressionParams__Group_1_1__0 ) ) ( ( rule__XExpressionParams__Group_1_1__0 )* ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4793:1: ( ( ( rule__XExpressionParams__Group_1_1__0 ) ) ( ( rule__XExpressionParams__Group_1_1__0 )* ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4793:1: ( ( ( rule__XExpressionParams__Group_1_1__0 ) ) ( ( rule__XExpressionParams__Group_1_1__0 )* ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4794:1: ( ( rule__XExpressionParams__Group_1_1__0 ) ) ( ( rule__XExpressionParams__Group_1_1__0 )* )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4794:1: ( ( rule__XExpressionParams__Group_1_1__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4795:1: ( rule__XExpressionParams__Group_1_1__0 )
            {
             before(grammarAccess.getXExpressionParamsAccess().getGroup_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4796:1: ( rule__XExpressionParams__Group_1_1__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4796:2: rule__XExpressionParams__Group_1_1__0
            {
            pushFollow(FOLLOW_rule__XExpressionParams__Group_1_1__0_in_rule__XExpressionParams__Group_1__1__Impl9860);
            rule__XExpressionParams__Group_1_1__0();

            state._fsp--;


            }

             after(grammarAccess.getXExpressionParamsAccess().getGroup_1_1()); 

            }

            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4799:1: ( ( rule__XExpressionParams__Group_1_1__0 )* )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4800:1: ( rule__XExpressionParams__Group_1_1__0 )*
            {
             before(grammarAccess.getXExpressionParamsAccess().getGroup_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4801:1: ( rule__XExpressionParams__Group_1_1__0 )*
            loop47:
            do {
                int alt47=2;
                int LA47_0 = input.LA(1);

                if ( (LA47_0==KEYWORD_4) ) {
                    alt47=1;
                }


                switch (alt47) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4801:2: rule__XExpressionParams__Group_1_1__0
            	    {
            	    pushFollow(FOLLOW_rule__XExpressionParams__Group_1_1__0_in_rule__XExpressionParams__Group_1__1__Impl9872);
            	    rule__XExpressionParams__Group_1_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop47;
                }
            } while (true);

             after(grammarAccess.getXExpressionParamsAccess().getGroup_1_1()); 

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
    // $ANTLR end "rule__XExpressionParams__Group_1__1__Impl"


    // $ANTLR start "rule__XExpressionParams__Group_1_1__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4816:1: rule__XExpressionParams__Group_1_1__0 : rule__XExpressionParams__Group_1_1__0__Impl rule__XExpressionParams__Group_1_1__1 ;
    public final void rule__XExpressionParams__Group_1_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4820:1: ( rule__XExpressionParams__Group_1_1__0__Impl rule__XExpressionParams__Group_1_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4821:2: rule__XExpressionParams__Group_1_1__0__Impl rule__XExpressionParams__Group_1_1__1
            {
            pushFollow(FOLLOW_rule__XExpressionParams__Group_1_1__0__Impl_in_rule__XExpressionParams__Group_1_1__09909);
            rule__XExpressionParams__Group_1_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__XExpressionParams__Group_1_1__1_in_rule__XExpressionParams__Group_1_1__09912);
            rule__XExpressionParams__Group_1_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XExpressionParams__Group_1_1__0"


    // $ANTLR start "rule__XExpressionParams__Group_1_1__0__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4828:1: rule__XExpressionParams__Group_1_1__0__Impl : ( KEYWORD_4 ) ;
    public final void rule__XExpressionParams__Group_1_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4832:1: ( ( KEYWORD_4 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4833:1: ( KEYWORD_4 )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4833:1: ( KEYWORD_4 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4834:1: KEYWORD_4
            {
             before(grammarAccess.getXExpressionParamsAccess().getCommaKeyword_1_1_0()); 
            match(input,KEYWORD_4,FOLLOW_KEYWORD_4_in_rule__XExpressionParams__Group_1_1__0__Impl9940); 
             after(grammarAccess.getXExpressionParamsAccess().getCommaKeyword_1_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XExpressionParams__Group_1_1__0__Impl"


    // $ANTLR start "rule__XExpressionParams__Group_1_1__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4847:1: rule__XExpressionParams__Group_1_1__1 : rule__XExpressionParams__Group_1_1__1__Impl ;
    public final void rule__XExpressionParams__Group_1_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4851:1: ( rule__XExpressionParams__Group_1_1__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4852:2: rule__XExpressionParams__Group_1_1__1__Impl
            {
            pushFollow(FOLLOW_rule__XExpressionParams__Group_1_1__1__Impl_in_rule__XExpressionParams__Group_1_1__19971);
            rule__XExpressionParams__Group_1_1__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XExpressionParams__Group_1_1__1"


    // $ANTLR start "rule__XExpressionParams__Group_1_1__1__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4858:1: rule__XExpressionParams__Group_1_1__1__Impl : ( ( rule__XExpressionParams__EntriesAssignment_1_1_1 ) ) ;
    public final void rule__XExpressionParams__Group_1_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4862:1: ( ( ( rule__XExpressionParams__EntriesAssignment_1_1_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4863:1: ( ( rule__XExpressionParams__EntriesAssignment_1_1_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4863:1: ( ( rule__XExpressionParams__EntriesAssignment_1_1_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4864:1: ( rule__XExpressionParams__EntriesAssignment_1_1_1 )
            {
             before(grammarAccess.getXExpressionParamsAccess().getEntriesAssignment_1_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4865:1: ( rule__XExpressionParams__EntriesAssignment_1_1_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4865:2: rule__XExpressionParams__EntriesAssignment_1_1_1
            {
            pushFollow(FOLLOW_rule__XExpressionParams__EntriesAssignment_1_1_1_in_rule__XExpressionParams__Group_1_1__1__Impl9998);
            rule__XExpressionParams__EntriesAssignment_1_1_1();

            state._fsp--;


            }

             after(grammarAccess.getXExpressionParamsAccess().getEntriesAssignment_1_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XExpressionParams__Group_1_1__1__Impl"


    // $ANTLR start "rule__Expression__Group__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4879:1: rule__Expression__Group__0 : rule__Expression__Group__0__Impl rule__Expression__Group__1 ;
    public final void rule__Expression__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4883:1: ( rule__Expression__Group__0__Impl rule__Expression__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4884:2: rule__Expression__Group__0__Impl rule__Expression__Group__1
            {
            pushFollow(FOLLOW_rule__Expression__Group__0__Impl_in_rule__Expression__Group__010032);
            rule__Expression__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Expression__Group__1_in_rule__Expression__Group__010035);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4891:1: rule__Expression__Group__0__Impl : ( ( rule__Expression__Op1Assignment_0 ) ) ;
    public final void rule__Expression__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4895:1: ( ( ( rule__Expression__Op1Assignment_0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4896:1: ( ( rule__Expression__Op1Assignment_0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4896:1: ( ( rule__Expression__Op1Assignment_0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4897:1: ( rule__Expression__Op1Assignment_0 )
            {
             before(grammarAccess.getExpressionAccess().getOp1Assignment_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4898:1: ( rule__Expression__Op1Assignment_0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4898:2: rule__Expression__Op1Assignment_0
            {
            pushFollow(FOLLOW_rule__Expression__Op1Assignment_0_in_rule__Expression__Group__0__Impl10062);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4908:1: rule__Expression__Group__1 : rule__Expression__Group__1__Impl ;
    public final void rule__Expression__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4912:1: ( rule__Expression__Group__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4913:2: rule__Expression__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__Expression__Group__1__Impl_in_rule__Expression__Group__110092);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4919:1: rule__Expression__Group__1__Impl : ( ( rule__Expression__Alternatives_1 ) ) ;
    public final void rule__Expression__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4923:1: ( ( ( rule__Expression__Alternatives_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4924:1: ( ( rule__Expression__Alternatives_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4924:1: ( ( rule__Expression__Alternatives_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4925:1: ( rule__Expression__Alternatives_1 )
            {
             before(grammarAccess.getExpressionAccess().getAlternatives_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4926:1: ( rule__Expression__Alternatives_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4926:2: rule__Expression__Alternatives_1
            {
            pushFollow(FOLLOW_rule__Expression__Alternatives_1_in_rule__Expression__Group__1__Impl10119);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4940:1: rule__Comparison__Group__0 : rule__Comparison__Group__0__Impl rule__Comparison__Group__1 ;
    public final void rule__Comparison__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4944:1: ( rule__Comparison__Group__0__Impl rule__Comparison__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4945:2: rule__Comparison__Group__0__Impl rule__Comparison__Group__1
            {
            pushFollow(FOLLOW_rule__Comparison__Group__0__Impl_in_rule__Comparison__Group__010153);
            rule__Comparison__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Comparison__Group__1_in_rule__Comparison__Group__010156);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4952:1: rule__Comparison__Group__0__Impl : ( ( rule__Comparison__OperatorAssignment_0 ) ) ;
    public final void rule__Comparison__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4956:1: ( ( ( rule__Comparison__OperatorAssignment_0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4957:1: ( ( rule__Comparison__OperatorAssignment_0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4957:1: ( ( rule__Comparison__OperatorAssignment_0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4958:1: ( rule__Comparison__OperatorAssignment_0 )
            {
             before(grammarAccess.getComparisonAccess().getOperatorAssignment_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4959:1: ( rule__Comparison__OperatorAssignment_0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4959:2: rule__Comparison__OperatorAssignment_0
            {
            pushFollow(FOLLOW_rule__Comparison__OperatorAssignment_0_in_rule__Comparison__Group__0__Impl10183);
            rule__Comparison__OperatorAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getComparisonAccess().getOperatorAssignment_0()); 

            }


            }

        }
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4969:1: rule__Comparison__Group__1 : rule__Comparison__Group__1__Impl ;
    public final void rule__Comparison__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4973:1: ( rule__Comparison__Group__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4974:2: rule__Comparison__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__Comparison__Group__1__Impl_in_rule__Comparison__Group__110213);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4980:1: rule__Comparison__Group__1__Impl : ( ( rule__Comparison__Op2Assignment_1 ) ) ;
    public final void rule__Comparison__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4984:1: ( ( ( rule__Comparison__Op2Assignment_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4985:1: ( ( rule__Comparison__Op2Assignment_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4985:1: ( ( rule__Comparison__Op2Assignment_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4986:1: ( rule__Comparison__Op2Assignment_1 )
            {
             before(grammarAccess.getComparisonAccess().getOp2Assignment_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4987:1: ( rule__Comparison__Op2Assignment_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4987:2: rule__Comparison__Op2Assignment_1
            {
            pushFollow(FOLLOW_rule__Comparison__Op2Assignment_1_in_rule__Comparison__Group__1__Impl10240);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5001:1: rule__Like__Group__0 : rule__Like__Group__0__Impl rule__Like__Group__1 ;
    public final void rule__Like__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5005:1: ( rule__Like__Group__0__Impl rule__Like__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5006:2: rule__Like__Group__0__Impl rule__Like__Group__1
            {
            pushFollow(FOLLOW_rule__Like__Group__0__Impl_in_rule__Like__Group__010274);
            rule__Like__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Like__Group__1_in_rule__Like__Group__010277);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5013:1: rule__Like__Group__0__Impl : ( ( rule__Like__OpLikeAssignment_0 ) ) ;
    public final void rule__Like__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5017:1: ( ( ( rule__Like__OpLikeAssignment_0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5018:1: ( ( rule__Like__OpLikeAssignment_0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5018:1: ( ( rule__Like__OpLikeAssignment_0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5019:1: ( rule__Like__OpLikeAssignment_0 )
            {
             before(grammarAccess.getLikeAccess().getOpLikeAssignment_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5020:1: ( rule__Like__OpLikeAssignment_0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5020:2: rule__Like__OpLikeAssignment_0
            {
            pushFollow(FOLLOW_rule__Like__OpLikeAssignment_0_in_rule__Like__Group__0__Impl10304);
            rule__Like__OpLikeAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getLikeAccess().getOpLikeAssignment_0()); 

            }


            }

        }
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5030:1: rule__Like__Group__1 : rule__Like__Group__1__Impl ;
    public final void rule__Like__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5034:1: ( rule__Like__Group__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5035:2: rule__Like__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__Like__Group__1__Impl_in_rule__Like__Group__110334);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5041:1: rule__Like__Group__1__Impl : ( ( rule__Like__Op2Assignment_1 ) ) ;
    public final void rule__Like__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5045:1: ( ( ( rule__Like__Op2Assignment_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5046:1: ( ( rule__Like__Op2Assignment_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5046:1: ( ( rule__Like__Op2Assignment_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5047:1: ( rule__Like__Op2Assignment_1 )
            {
             before(grammarAccess.getLikeAccess().getOp2Assignment_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5048:1: ( rule__Like__Op2Assignment_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5048:2: rule__Like__Op2Assignment_1
            {
            pushFollow(FOLLOW_rule__Like__Op2Assignment_1_in_rule__Like__Group__1__Impl10361);
            rule__Like__Op2Assignment_1();

            state._fsp--;


            }

             after(grammarAccess.getLikeAccess().getOp2Assignment_1()); 

            }


            }

        }
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5062:1: rule__Between__Group__0 : rule__Between__Group__0__Impl rule__Between__Group__1 ;
    public final void rule__Between__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5066:1: ( rule__Between__Group__0__Impl rule__Between__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5067:2: rule__Between__Group__0__Impl rule__Between__Group__1
            {
            pushFollow(FOLLOW_rule__Between__Group__0__Impl_in_rule__Between__Group__010395);
            rule__Between__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Between__Group__1_in_rule__Between__Group__010398);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5074:1: rule__Between__Group__0__Impl : ( ( rule__Between__OpBetweenAssignment_0 ) ) ;
    public final void rule__Between__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5078:1: ( ( ( rule__Between__OpBetweenAssignment_0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5079:1: ( ( rule__Between__OpBetweenAssignment_0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5079:1: ( ( rule__Between__OpBetweenAssignment_0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5080:1: ( rule__Between__OpBetweenAssignment_0 )
            {
             before(grammarAccess.getBetweenAccess().getOpBetweenAssignment_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5081:1: ( rule__Between__OpBetweenAssignment_0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5081:2: rule__Between__OpBetweenAssignment_0
            {
            pushFollow(FOLLOW_rule__Between__OpBetweenAssignment_0_in_rule__Between__Group__0__Impl10425);
            rule__Between__OpBetweenAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getBetweenAccess().getOpBetweenAssignment_0()); 

            }


            }

        }
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5091:1: rule__Between__Group__1 : rule__Between__Group__1__Impl rule__Between__Group__2 ;
    public final void rule__Between__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5095:1: ( rule__Between__Group__1__Impl rule__Between__Group__2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5096:2: rule__Between__Group__1__Impl rule__Between__Group__2
            {
            pushFollow(FOLLOW_rule__Between__Group__1__Impl_in_rule__Between__Group__110455);
            rule__Between__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Between__Group__2_in_rule__Between__Group__110458);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5103:1: rule__Between__Group__1__Impl : ( ( rule__Between__Op2Assignment_1 ) ) ;
    public final void rule__Between__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5107:1: ( ( ( rule__Between__Op2Assignment_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5108:1: ( ( rule__Between__Op2Assignment_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5108:1: ( ( rule__Between__Op2Assignment_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5109:1: ( rule__Between__Op2Assignment_1 )
            {
             before(grammarAccess.getBetweenAccess().getOp2Assignment_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5110:1: ( rule__Between__Op2Assignment_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5110:2: rule__Between__Op2Assignment_1
            {
            pushFollow(FOLLOW_rule__Between__Op2Assignment_1_in_rule__Between__Group__1__Impl10485);
            rule__Between__Op2Assignment_1();

            state._fsp--;


            }

             after(grammarAccess.getBetweenAccess().getOp2Assignment_1()); 

            }


            }

        }
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5120:1: rule__Between__Group__2 : rule__Between__Group__2__Impl rule__Between__Group__3 ;
    public final void rule__Between__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5124:1: ( rule__Between__Group__2__Impl rule__Between__Group__3 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5125:2: rule__Between__Group__2__Impl rule__Between__Group__3
            {
            pushFollow(FOLLOW_rule__Between__Group__2__Impl_in_rule__Between__Group__210515);
            rule__Between__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Between__Group__3_in_rule__Between__Group__210518);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5132:1: rule__Between__Group__2__Impl : ( KEYWORD_22 ) ;
    public final void rule__Between__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5136:1: ( ( KEYWORD_22 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5137:1: ( KEYWORD_22 )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5137:1: ( KEYWORD_22 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5138:1: KEYWORD_22
            {
             before(grammarAccess.getBetweenAccess().getANDKeyword_2()); 
            match(input,KEYWORD_22,FOLLOW_KEYWORD_22_in_rule__Between__Group__2__Impl10546); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5151:1: rule__Between__Group__3 : rule__Between__Group__3__Impl ;
    public final void rule__Between__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5155:1: ( rule__Between__Group__3__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5156:2: rule__Between__Group__3__Impl
            {
            pushFollow(FOLLOW_rule__Between__Group__3__Impl_in_rule__Between__Group__310577);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5162:1: rule__Between__Group__3__Impl : ( ( rule__Between__Op3Assignment_3 ) ) ;
    public final void rule__Between__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5166:1: ( ( ( rule__Between__Op3Assignment_3 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5167:1: ( ( rule__Between__Op3Assignment_3 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5167:1: ( ( rule__Between__Op3Assignment_3 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5168:1: ( rule__Between__Op3Assignment_3 )
            {
             before(grammarAccess.getBetweenAccess().getOp3Assignment_3()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5169:1: ( rule__Between__Op3Assignment_3 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5169:2: rule__Between__Op3Assignment_3
            {
            pushFollow(FOLLOW_rule__Between__Op3Assignment_3_in_rule__Between__Group__3__Impl10604);
            rule__Between__Op3Assignment_3();

            state._fsp--;


            }

             after(grammarAccess.getBetweenAccess().getOp3Assignment_3()); 

            }


            }

        }
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5187:1: rule__InOperator__Group__0 : rule__InOperator__Group__0__Impl rule__InOperator__Group__1 ;
    public final void rule__InOperator__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5191:1: ( rule__InOperator__Group__0__Impl rule__InOperator__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5192:2: rule__InOperator__Group__0__Impl rule__InOperator__Group__1
            {
            pushFollow(FOLLOW_rule__InOperator__Group__0__Impl_in_rule__InOperator__Group__010642);
            rule__InOperator__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__InOperator__Group__1_in_rule__InOperator__Group__010645);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5199:1: rule__InOperator__Group__0__Impl : ( () ) ;
    public final void rule__InOperator__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5203:1: ( ( () ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5204:1: ( () )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5204:1: ( () )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5205:1: ()
            {
             before(grammarAccess.getInOperatorAccess().getInOperAction_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5206:1: ()
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5208:1: 
            {
            }

             after(grammarAccess.getInOperatorAccess().getInOperAction_0()); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5218:1: rule__InOperator__Group__1 : rule__InOperator__Group__1__Impl rule__InOperator__Group__2 ;
    public final void rule__InOperator__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5222:1: ( rule__InOperator__Group__1__Impl rule__InOperator__Group__2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5223:2: rule__InOperator__Group__1__Impl rule__InOperator__Group__2
            {
            pushFollow(FOLLOW_rule__InOperator__Group__1__Impl_in_rule__InOperator__Group__110703);
            rule__InOperator__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__InOperator__Group__2_in_rule__InOperator__Group__110706);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5230:1: rule__InOperator__Group__1__Impl : ( ( rule__InOperator__OpAssignment_1 ) ) ;
    public final void rule__InOperator__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5234:1: ( ( ( rule__InOperator__OpAssignment_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5235:1: ( ( rule__InOperator__OpAssignment_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5235:1: ( ( rule__InOperator__OpAssignment_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5236:1: ( rule__InOperator__OpAssignment_1 )
            {
             before(grammarAccess.getInOperatorAccess().getOpAssignment_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5237:1: ( rule__InOperator__OpAssignment_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5237:2: rule__InOperator__OpAssignment_1
            {
            pushFollow(FOLLOW_rule__InOperator__OpAssignment_1_in_rule__InOperator__Group__1__Impl10733);
            rule__InOperator__OpAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getInOperatorAccess().getOpAssignment_1()); 

            }


            }

        }
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5247:1: rule__InOperator__Group__2 : rule__InOperator__Group__2__Impl rule__InOperator__Group__3 ;
    public final void rule__InOperator__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5251:1: ( rule__InOperator__Group__2__Impl rule__InOperator__Group__3 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5252:2: rule__InOperator__Group__2__Impl rule__InOperator__Group__3
            {
            pushFollow(FOLLOW_rule__InOperator__Group__2__Impl_in_rule__InOperator__Group__210763);
            rule__InOperator__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__InOperator__Group__3_in_rule__InOperator__Group__210766);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5259:1: rule__InOperator__Group__2__Impl : ( ( rule__InOperator__Alternatives_2 ) ) ;
    public final void rule__InOperator__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5263:1: ( ( ( rule__InOperator__Alternatives_2 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5264:1: ( ( rule__InOperator__Alternatives_2 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5264:1: ( ( rule__InOperator__Alternatives_2 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5265:1: ( rule__InOperator__Alternatives_2 )
            {
             before(grammarAccess.getInOperatorAccess().getAlternatives_2()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5266:1: ( rule__InOperator__Alternatives_2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5266:2: rule__InOperator__Alternatives_2
            {
            pushFollow(FOLLOW_rule__InOperator__Alternatives_2_in_rule__InOperator__Group__2__Impl10793);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5276:1: rule__InOperator__Group__3 : rule__InOperator__Group__3__Impl ;
    public final void rule__InOperator__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5280:1: ( rule__InOperator__Group__3__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5281:2: rule__InOperator__Group__3__Impl
            {
            pushFollow(FOLLOW_rule__InOperator__Group__3__Impl_in_rule__InOperator__Group__310823);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5287:1: rule__InOperator__Group__3__Impl : ( KEYWORD_2 ) ;
    public final void rule__InOperator__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5291:1: ( ( KEYWORD_2 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5292:1: ( KEYWORD_2 )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5292:1: ( KEYWORD_2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5293:1: KEYWORD_2
            {
             before(grammarAccess.getInOperatorAccess().getRightParenthesisKeyword_3()); 
            match(input,KEYWORD_2,FOLLOW_KEYWORD_2_in_rule__InOperator__Group__3__Impl10851); 
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


    // $ANTLR start "rule__OperandList__Group__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5314:1: rule__OperandList__Group__0 : rule__OperandList__Group__0__Impl rule__OperandList__Group__1 ;
    public final void rule__OperandList__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5318:1: ( rule__OperandList__Group__0__Impl rule__OperandList__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5319:2: rule__OperandList__Group__0__Impl rule__OperandList__Group__1
            {
            pushFollow(FOLLOW_rule__OperandList__Group__0__Impl_in_rule__OperandList__Group__010890);
            rule__OperandList__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__OperandList__Group__1_in_rule__OperandList__Group__010893);
            rule__OperandList__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OperandList__Group__0"


    // $ANTLR start "rule__OperandList__Group__0__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5326:1: rule__OperandList__Group__0__Impl : ( ruleXOperandFragment ) ;
    public final void rule__OperandList__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5330:1: ( ( ruleXOperandFragment ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5331:1: ( ruleXOperandFragment )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5331:1: ( ruleXOperandFragment )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5332:1: ruleXOperandFragment
            {
             before(grammarAccess.getOperandListAccess().getXOperandFragmentParserRuleCall_0()); 
            pushFollow(FOLLOW_ruleXOperandFragment_in_rule__OperandList__Group__0__Impl10920);
            ruleXOperandFragment();

            state._fsp--;

             after(grammarAccess.getOperandListAccess().getXOperandFragmentParserRuleCall_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OperandList__Group__0__Impl"


    // $ANTLR start "rule__OperandList__Group__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5343:1: rule__OperandList__Group__1 : rule__OperandList__Group__1__Impl ;
    public final void rule__OperandList__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5347:1: ( rule__OperandList__Group__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5348:2: rule__OperandList__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__OperandList__Group__1__Impl_in_rule__OperandList__Group__110949);
            rule__OperandList__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OperandList__Group__1"


    // $ANTLR start "rule__OperandList__Group__1__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5354:1: rule__OperandList__Group__1__Impl : ( ( rule__OperandList__Group_1__0 )? ) ;
    public final void rule__OperandList__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5358:1: ( ( ( rule__OperandList__Group_1__0 )? ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5359:1: ( ( rule__OperandList__Group_1__0 )? )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5359:1: ( ( rule__OperandList__Group_1__0 )? )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5360:1: ( rule__OperandList__Group_1__0 )?
            {
             before(grammarAccess.getOperandListAccess().getGroup_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5361:1: ( rule__OperandList__Group_1__0 )?
            int alt48=2;
            int LA48_0 = input.LA(1);

            if ( (LA48_0==KEYWORD_4) ) {
                alt48=1;
            }
            switch (alt48) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5361:2: rule__OperandList__Group_1__0
                    {
                    pushFollow(FOLLOW_rule__OperandList__Group_1__0_in_rule__OperandList__Group__1__Impl10976);
                    rule__OperandList__Group_1__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getOperandListAccess().getGroup_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OperandList__Group__1__Impl"


    // $ANTLR start "rule__OperandList__Group_1__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5375:1: rule__OperandList__Group_1__0 : rule__OperandList__Group_1__0__Impl rule__OperandList__Group_1__1 ;
    public final void rule__OperandList__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5379:1: ( rule__OperandList__Group_1__0__Impl rule__OperandList__Group_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5380:2: rule__OperandList__Group_1__0__Impl rule__OperandList__Group_1__1
            {
            pushFollow(FOLLOW_rule__OperandList__Group_1__0__Impl_in_rule__OperandList__Group_1__011011);
            rule__OperandList__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__OperandList__Group_1__1_in_rule__OperandList__Group_1__011014);
            rule__OperandList__Group_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OperandList__Group_1__0"


    // $ANTLR start "rule__OperandList__Group_1__0__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5387:1: rule__OperandList__Group_1__0__Impl : ( () ) ;
    public final void rule__OperandList__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5391:1: ( ( () ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5392:1: ( () )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5392:1: ( () )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5393:1: ()
            {
             before(grammarAccess.getOperandListAccess().getOpListEntriesAction_1_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5394:1: ()
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5396:1: 
            {
            }

             after(grammarAccess.getOperandListAccess().getOpListEntriesAction_1_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OperandList__Group_1__0__Impl"


    // $ANTLR start "rule__OperandList__Group_1__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5406:1: rule__OperandList__Group_1__1 : rule__OperandList__Group_1__1__Impl ;
    public final void rule__OperandList__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5410:1: ( rule__OperandList__Group_1__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5411:2: rule__OperandList__Group_1__1__Impl
            {
            pushFollow(FOLLOW_rule__OperandList__Group_1__1__Impl_in_rule__OperandList__Group_1__111072);
            rule__OperandList__Group_1__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OperandList__Group_1__1"


    // $ANTLR start "rule__OperandList__Group_1__1__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5417:1: rule__OperandList__Group_1__1__Impl : ( ( ( rule__OperandList__Group_1_1__0 ) ) ( ( rule__OperandList__Group_1_1__0 )* ) ) ;
    public final void rule__OperandList__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5421:1: ( ( ( ( rule__OperandList__Group_1_1__0 ) ) ( ( rule__OperandList__Group_1_1__0 )* ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5422:1: ( ( ( rule__OperandList__Group_1_1__0 ) ) ( ( rule__OperandList__Group_1_1__0 )* ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5422:1: ( ( ( rule__OperandList__Group_1_1__0 ) ) ( ( rule__OperandList__Group_1_1__0 )* ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5423:1: ( ( rule__OperandList__Group_1_1__0 ) ) ( ( rule__OperandList__Group_1_1__0 )* )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5423:1: ( ( rule__OperandList__Group_1_1__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5424:1: ( rule__OperandList__Group_1_1__0 )
            {
             before(grammarAccess.getOperandListAccess().getGroup_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5425:1: ( rule__OperandList__Group_1_1__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5425:2: rule__OperandList__Group_1_1__0
            {
            pushFollow(FOLLOW_rule__OperandList__Group_1_1__0_in_rule__OperandList__Group_1__1__Impl11101);
            rule__OperandList__Group_1_1__0();

            state._fsp--;


            }

             after(grammarAccess.getOperandListAccess().getGroup_1_1()); 

            }

            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5428:1: ( ( rule__OperandList__Group_1_1__0 )* )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5429:1: ( rule__OperandList__Group_1_1__0 )*
            {
             before(grammarAccess.getOperandListAccess().getGroup_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5430:1: ( rule__OperandList__Group_1_1__0 )*
            loop49:
            do {
                int alt49=2;
                int LA49_0 = input.LA(1);

                if ( (LA49_0==KEYWORD_4) ) {
                    alt49=1;
                }


                switch (alt49) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5430:2: rule__OperandList__Group_1_1__0
            	    {
            	    pushFollow(FOLLOW_rule__OperandList__Group_1_1__0_in_rule__OperandList__Group_1__1__Impl11113);
            	    rule__OperandList__Group_1_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop49;
                }
            } while (true);

             after(grammarAccess.getOperandListAccess().getGroup_1_1()); 

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
    // $ANTLR end "rule__OperandList__Group_1__1__Impl"


    // $ANTLR start "rule__OperandList__Group_1_1__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5445:1: rule__OperandList__Group_1_1__0 : rule__OperandList__Group_1_1__0__Impl rule__OperandList__Group_1_1__1 ;
    public final void rule__OperandList__Group_1_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5449:1: ( rule__OperandList__Group_1_1__0__Impl rule__OperandList__Group_1_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5450:2: rule__OperandList__Group_1_1__0__Impl rule__OperandList__Group_1_1__1
            {
            pushFollow(FOLLOW_rule__OperandList__Group_1_1__0__Impl_in_rule__OperandList__Group_1_1__011150);
            rule__OperandList__Group_1_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__OperandList__Group_1_1__1_in_rule__OperandList__Group_1_1__011153);
            rule__OperandList__Group_1_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OperandList__Group_1_1__0"


    // $ANTLR start "rule__OperandList__Group_1_1__0__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5457:1: rule__OperandList__Group_1_1__0__Impl : ( KEYWORD_4 ) ;
    public final void rule__OperandList__Group_1_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5461:1: ( ( KEYWORD_4 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5462:1: ( KEYWORD_4 )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5462:1: ( KEYWORD_4 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5463:1: KEYWORD_4
            {
             before(grammarAccess.getOperandListAccess().getCommaKeyword_1_1_0()); 
            match(input,KEYWORD_4,FOLLOW_KEYWORD_4_in_rule__OperandList__Group_1_1__0__Impl11181); 
             after(grammarAccess.getOperandListAccess().getCommaKeyword_1_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OperandList__Group_1_1__0__Impl"


    // $ANTLR start "rule__OperandList__Group_1_1__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5476:1: rule__OperandList__Group_1_1__1 : rule__OperandList__Group_1_1__1__Impl ;
    public final void rule__OperandList__Group_1_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5480:1: ( rule__OperandList__Group_1_1__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5481:2: rule__OperandList__Group_1_1__1__Impl
            {
            pushFollow(FOLLOW_rule__OperandList__Group_1_1__1__Impl_in_rule__OperandList__Group_1_1__111212);
            rule__OperandList__Group_1_1__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OperandList__Group_1_1__1"


    // $ANTLR start "rule__OperandList__Group_1_1__1__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5487:1: rule__OperandList__Group_1_1__1__Impl : ( ( rule__OperandList__EntriesAssignment_1_1_1 ) ) ;
    public final void rule__OperandList__Group_1_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5491:1: ( ( ( rule__OperandList__EntriesAssignment_1_1_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5492:1: ( ( rule__OperandList__EntriesAssignment_1_1_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5492:1: ( ( rule__OperandList__EntriesAssignment_1_1_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5493:1: ( rule__OperandList__EntriesAssignment_1_1_1 )
            {
             before(grammarAccess.getOperandListAccess().getEntriesAssignment_1_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5494:1: ( rule__OperandList__EntriesAssignment_1_1_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5494:2: rule__OperandList__EntriesAssignment_1_1_1
            {
            pushFollow(FOLLOW_rule__OperandList__EntriesAssignment_1_1_1_in_rule__OperandList__Group_1_1__1__Impl11239);
            rule__OperandList__EntriesAssignment_1_1_1();

            state._fsp--;


            }

             after(grammarAccess.getOperandListAccess().getEntriesAssignment_1_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OperandList__Group_1_1__1__Impl"


    // $ANTLR start "rule__Operand__Group__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5508:1: rule__Operand__Group__0 : rule__Operand__Group__0__Impl rule__Operand__Group__1 ;
    public final void rule__Operand__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5512:1: ( rule__Operand__Group__0__Impl rule__Operand__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5513:2: rule__Operand__Group__0__Impl rule__Operand__Group__1
            {
            pushFollow(FOLLOW_rule__Operand__Group__0__Impl_in_rule__Operand__Group__011273);
            rule__Operand__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Operand__Group__1_in_rule__Operand__Group__011276);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5520:1: rule__Operand__Group__0__Impl : ( ruleOperandFragment ) ;
    public final void rule__Operand__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5524:1: ( ( ruleOperandFragment ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5525:1: ( ruleOperandFragment )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5525:1: ( ruleOperandFragment )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5526:1: ruleOperandFragment
            {
             before(grammarAccess.getOperandAccess().getOperandFragmentParserRuleCall_0()); 
            pushFollow(FOLLOW_ruleOperandFragment_in_rule__Operand__Group__0__Impl11303);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5537:1: rule__Operand__Group__1 : rule__Operand__Group__1__Impl ;
    public final void rule__Operand__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5541:1: ( rule__Operand__Group__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5542:2: rule__Operand__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__Operand__Group__1__Impl_in_rule__Operand__Group__111332);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5548:1: rule__Operand__Group__1__Impl : ( ( rule__Operand__Group_1__0 )? ) ;
    public final void rule__Operand__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5552:1: ( ( ( rule__Operand__Group_1__0 )? ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5553:1: ( ( rule__Operand__Group_1__0 )? )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5553:1: ( ( rule__Operand__Group_1__0 )? )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5554:1: ( rule__Operand__Group_1__0 )?
            {
             before(grammarAccess.getOperandAccess().getGroup_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5555:1: ( rule__Operand__Group_1__0 )?
            int alt50=2;
            int LA50_0 = input.LA(1);

            if ( (LA50_0==KEYWORD_19||LA50_0==KEYWORD_3||LA50_0==KEYWORD_5||LA50_0==KEYWORD_7||LA50_0==RULE_STAR) ) {
                alt50=1;
            }
            switch (alt50) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5555:2: rule__Operand__Group_1__0
                    {
                    pushFollow(FOLLOW_rule__Operand__Group_1__0_in_rule__Operand__Group__1__Impl11359);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5569:1: rule__Operand__Group_1__0 : rule__Operand__Group_1__0__Impl rule__Operand__Group_1__1 ;
    public final void rule__Operand__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5573:1: ( rule__Operand__Group_1__0__Impl rule__Operand__Group_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5574:2: rule__Operand__Group_1__0__Impl rule__Operand__Group_1__1
            {
            pushFollow(FOLLOW_rule__Operand__Group_1__0__Impl_in_rule__Operand__Group_1__011394);
            rule__Operand__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Operand__Group_1__1_in_rule__Operand__Group_1__011397);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5581:1: rule__Operand__Group_1__0__Impl : ( () ) ;
    public final void rule__Operand__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5585:1: ( ( () ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5586:1: ( () )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5586:1: ( () )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5587:1: ()
            {
             before(grammarAccess.getOperandAccess().getOperandsEntriesAction_1_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5588:1: ()
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5590:1: 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5600:1: rule__Operand__Group_1__1 : rule__Operand__Group_1__1__Impl ;
    public final void rule__Operand__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5604:1: ( rule__Operand__Group_1__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5605:2: rule__Operand__Group_1__1__Impl
            {
            pushFollow(FOLLOW_rule__Operand__Group_1__1__Impl_in_rule__Operand__Group_1__111455);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5611:1: rule__Operand__Group_1__1__Impl : ( ( ( rule__Operand__Group_1_1__0 ) ) ( ( rule__Operand__Group_1_1__0 )* ) ) ;
    public final void rule__Operand__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5615:1: ( ( ( ( rule__Operand__Group_1_1__0 ) ) ( ( rule__Operand__Group_1_1__0 )* ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5616:1: ( ( ( rule__Operand__Group_1_1__0 ) ) ( ( rule__Operand__Group_1_1__0 )* ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5616:1: ( ( ( rule__Operand__Group_1_1__0 ) ) ( ( rule__Operand__Group_1_1__0 )* ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5617:1: ( ( rule__Operand__Group_1_1__0 ) ) ( ( rule__Operand__Group_1_1__0 )* )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5617:1: ( ( rule__Operand__Group_1_1__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5618:1: ( rule__Operand__Group_1_1__0 )
            {
             before(grammarAccess.getOperandAccess().getGroup_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5619:1: ( rule__Operand__Group_1_1__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5619:2: rule__Operand__Group_1_1__0
            {
            pushFollow(FOLLOW_rule__Operand__Group_1_1__0_in_rule__Operand__Group_1__1__Impl11484);
            rule__Operand__Group_1_1__0();

            state._fsp--;


            }

             after(grammarAccess.getOperandAccess().getGroup_1_1()); 

            }

            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5622:1: ( ( rule__Operand__Group_1_1__0 )* )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5623:1: ( rule__Operand__Group_1_1__0 )*
            {
             before(grammarAccess.getOperandAccess().getGroup_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5624:1: ( rule__Operand__Group_1_1__0 )*
            loop51:
            do {
                int alt51=2;
                int LA51_0 = input.LA(1);

                if ( (LA51_0==KEYWORD_19||LA51_0==KEYWORD_3||LA51_0==KEYWORD_5||LA51_0==KEYWORD_7||LA51_0==RULE_STAR) ) {
                    alt51=1;
                }


                switch (alt51) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5624:2: rule__Operand__Group_1_1__0
            	    {
            	    pushFollow(FOLLOW_rule__Operand__Group_1_1__0_in_rule__Operand__Group_1__1__Impl11496);
            	    rule__Operand__Group_1_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop51;
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5639:1: rule__Operand__Group_1_1__0 : rule__Operand__Group_1_1__0__Impl rule__Operand__Group_1_1__1 ;
    public final void rule__Operand__Group_1_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5643:1: ( rule__Operand__Group_1_1__0__Impl rule__Operand__Group_1_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5644:2: rule__Operand__Group_1_1__0__Impl rule__Operand__Group_1_1__1
            {
            pushFollow(FOLLOW_rule__Operand__Group_1_1__0__Impl_in_rule__Operand__Group_1_1__011533);
            rule__Operand__Group_1_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Operand__Group_1_1__1_in_rule__Operand__Group_1_1__011536);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5651:1: rule__Operand__Group_1_1__0__Impl : ( ( rule__Operand__Alternatives_1_1_0 ) ) ;
    public final void rule__Operand__Group_1_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5655:1: ( ( ( rule__Operand__Alternatives_1_1_0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5656:1: ( ( rule__Operand__Alternatives_1_1_0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5656:1: ( ( rule__Operand__Alternatives_1_1_0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5657:1: ( rule__Operand__Alternatives_1_1_0 )
            {
             before(grammarAccess.getOperandAccess().getAlternatives_1_1_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5658:1: ( rule__Operand__Alternatives_1_1_0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5658:2: rule__Operand__Alternatives_1_1_0
            {
            pushFollow(FOLLOW_rule__Operand__Alternatives_1_1_0_in_rule__Operand__Group_1_1__0__Impl11563);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5668:1: rule__Operand__Group_1_1__1 : rule__Operand__Group_1_1__1__Impl ;
    public final void rule__Operand__Group_1_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5672:1: ( rule__Operand__Group_1_1__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5673:2: rule__Operand__Group_1_1__1__Impl
            {
            pushFollow(FOLLOW_rule__Operand__Group_1_1__1__Impl_in_rule__Operand__Group_1_1__111593);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5679:1: rule__Operand__Group_1_1__1__Impl : ( ( rule__Operand__EntriesAssignment_1_1_1 ) ) ;
    public final void rule__Operand__Group_1_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5683:1: ( ( ( rule__Operand__EntriesAssignment_1_1_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5684:1: ( ( rule__Operand__EntriesAssignment_1_1_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5684:1: ( ( rule__Operand__EntriesAssignment_1_1_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5685:1: ( rule__Operand__EntriesAssignment_1_1_1 )
            {
             before(grammarAccess.getOperandAccess().getEntriesAssignment_1_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5686:1: ( rule__Operand__EntriesAssignment_1_1_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5686:2: rule__Operand__EntriesAssignment_1_1_1
            {
            pushFollow(FOLLOW_rule__Operand__EntriesAssignment_1_1_1_in_rule__Operand__Group_1_1__1__Impl11620);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5700:1: rule__ParameterOperand__Group__0 : rule__ParameterOperand__Group__0__Impl rule__ParameterOperand__Group__1 ;
    public final void rule__ParameterOperand__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5704:1: ( rule__ParameterOperand__Group__0__Impl rule__ParameterOperand__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5705:2: rule__ParameterOperand__Group__0__Impl rule__ParameterOperand__Group__1
            {
            pushFollow(FOLLOW_rule__ParameterOperand__Group__0__Impl_in_rule__ParameterOperand__Group__011654);
            rule__ParameterOperand__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ParameterOperand__Group__1_in_rule__ParameterOperand__Group__011657);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5712:1: rule__ParameterOperand__Group__0__Impl : ( () ) ;
    public final void rule__ParameterOperand__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5716:1: ( ( () ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5717:1: ( () )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5717:1: ( () )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5718:1: ()
            {
             before(grammarAccess.getParameterOperandAccess().getPOperandAction_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5719:1: ()
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5721:1: 
            {
            }

             after(grammarAccess.getParameterOperandAccess().getPOperandAction_0()); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5731:1: rule__ParameterOperand__Group__1 : rule__ParameterOperand__Group__1__Impl rule__ParameterOperand__Group__2 ;
    public final void rule__ParameterOperand__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5735:1: ( rule__ParameterOperand__Group__1__Impl rule__ParameterOperand__Group__2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5736:2: rule__ParameterOperand__Group__1__Impl rule__ParameterOperand__Group__2
            {
            pushFollow(FOLLOW_rule__ParameterOperand__Group__1__Impl_in_rule__ParameterOperand__Group__111715);
            rule__ParameterOperand__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ParameterOperand__Group__2_in_rule__ParameterOperand__Group__111718);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5743:1: rule__ParameterOperand__Group__1__Impl : ( KEYWORD_20 ) ;
    public final void rule__ParameterOperand__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5747:1: ( ( KEYWORD_20 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5748:1: ( KEYWORD_20 )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5748:1: ( KEYWORD_20 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5749:1: KEYWORD_20
            {
             before(grammarAccess.getParameterOperandAccess().getPKeyword_1()); 
            match(input,KEYWORD_20,FOLLOW_KEYWORD_20_in_rule__ParameterOperand__Group__1__Impl11746); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5762:1: rule__ParameterOperand__Group__2 : rule__ParameterOperand__Group__2__Impl rule__ParameterOperand__Group__3 ;
    public final void rule__ParameterOperand__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5766:1: ( rule__ParameterOperand__Group__2__Impl rule__ParameterOperand__Group__3 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5767:2: rule__ParameterOperand__Group__2__Impl rule__ParameterOperand__Group__3
            {
            pushFollow(FOLLOW_rule__ParameterOperand__Group__2__Impl_in_rule__ParameterOperand__Group__211777);
            rule__ParameterOperand__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ParameterOperand__Group__3_in_rule__ParameterOperand__Group__211780);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5774:1: rule__ParameterOperand__Group__2__Impl : ( ( rule__ParameterOperand__PrmAssignment_2 ) ) ;
    public final void rule__ParameterOperand__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5778:1: ( ( ( rule__ParameterOperand__PrmAssignment_2 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5779:1: ( ( rule__ParameterOperand__PrmAssignment_2 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5779:1: ( ( rule__ParameterOperand__PrmAssignment_2 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5780:1: ( rule__ParameterOperand__PrmAssignment_2 )
            {
             before(grammarAccess.getParameterOperandAccess().getPrmAssignment_2()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5781:1: ( rule__ParameterOperand__PrmAssignment_2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5781:2: rule__ParameterOperand__PrmAssignment_2
            {
            pushFollow(FOLLOW_rule__ParameterOperand__PrmAssignment_2_in_rule__ParameterOperand__Group__2__Impl11807);
            rule__ParameterOperand__PrmAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getParameterOperandAccess().getPrmAssignment_2()); 

            }


            }

        }
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5791:1: rule__ParameterOperand__Group__3 : rule__ParameterOperand__Group__3__Impl ;
    public final void rule__ParameterOperand__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5795:1: ( rule__ParameterOperand__Group__3__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5796:2: rule__ParameterOperand__Group__3__Impl
            {
            pushFollow(FOLLOW_rule__ParameterOperand__Group__3__Impl_in_rule__ParameterOperand__Group__311837);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5802:1: rule__ParameterOperand__Group__3__Impl : ( KEYWORD_11 ) ;
    public final void rule__ParameterOperand__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5806:1: ( ( KEYWORD_11 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5807:1: ( KEYWORD_11 )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5807:1: ( KEYWORD_11 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5808:1: KEYWORD_11
            {
             before(grammarAccess.getParameterOperandAccess().getRightCurlyBracketKeyword_3()); 
            match(input,KEYWORD_11,FOLLOW_KEYWORD_11_in_rule__ParameterOperand__Group__3__Impl11865); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5829:1: rule__ExclamationParameterOperand__Group__0 : rule__ExclamationParameterOperand__Group__0__Impl rule__ExclamationParameterOperand__Group__1 ;
    public final void rule__ExclamationParameterOperand__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5833:1: ( rule__ExclamationParameterOperand__Group__0__Impl rule__ExclamationParameterOperand__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5834:2: rule__ExclamationParameterOperand__Group__0__Impl rule__ExclamationParameterOperand__Group__1
            {
            pushFollow(FOLLOW_rule__ExclamationParameterOperand__Group__0__Impl_in_rule__ExclamationParameterOperand__Group__011904);
            rule__ExclamationParameterOperand__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ExclamationParameterOperand__Group__1_in_rule__ExclamationParameterOperand__Group__011907);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5841:1: rule__ExclamationParameterOperand__Group__0__Impl : ( () ) ;
    public final void rule__ExclamationParameterOperand__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5845:1: ( ( () ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5846:1: ( () )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5846:1: ( () )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5847:1: ()
            {
             before(grammarAccess.getExclamationParameterOperandAccess().getExpPperandAction_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5848:1: ()
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5850:1: 
            {
            }

             after(grammarAccess.getExclamationParameterOperandAccess().getExpPperandAction_0()); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5860:1: rule__ExclamationParameterOperand__Group__1 : rule__ExclamationParameterOperand__Group__1__Impl rule__ExclamationParameterOperand__Group__2 ;
    public final void rule__ExclamationParameterOperand__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5864:1: ( rule__ExclamationParameterOperand__Group__1__Impl rule__ExclamationParameterOperand__Group__2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5865:2: rule__ExclamationParameterOperand__Group__1__Impl rule__ExclamationParameterOperand__Group__2
            {
            pushFollow(FOLLOW_rule__ExclamationParameterOperand__Group__1__Impl_in_rule__ExclamationParameterOperand__Group__111965);
            rule__ExclamationParameterOperand__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ExclamationParameterOperand__Group__2_in_rule__ExclamationParameterOperand__Group__111968);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5872:1: rule__ExclamationParameterOperand__Group__1__Impl : ( KEYWORD_24 ) ;
    public final void rule__ExclamationParameterOperand__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5876:1: ( ( KEYWORD_24 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5877:1: ( KEYWORD_24 )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5877:1: ( KEYWORD_24 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5878:1: KEYWORD_24
            {
             before(grammarAccess.getExclamationParameterOperandAccess().getPKeyword_1()); 
            match(input,KEYWORD_24,FOLLOW_KEYWORD_24_in_rule__ExclamationParameterOperand__Group__1__Impl11996); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5891:1: rule__ExclamationParameterOperand__Group__2 : rule__ExclamationParameterOperand__Group__2__Impl rule__ExclamationParameterOperand__Group__3 ;
    public final void rule__ExclamationParameterOperand__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5895:1: ( rule__ExclamationParameterOperand__Group__2__Impl rule__ExclamationParameterOperand__Group__3 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5896:2: rule__ExclamationParameterOperand__Group__2__Impl rule__ExclamationParameterOperand__Group__3
            {
            pushFollow(FOLLOW_rule__ExclamationParameterOperand__Group__2__Impl_in_rule__ExclamationParameterOperand__Group__212027);
            rule__ExclamationParameterOperand__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ExclamationParameterOperand__Group__3_in_rule__ExclamationParameterOperand__Group__212030);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5903:1: rule__ExclamationParameterOperand__Group__2__Impl : ( ( rule__ExclamationParameterOperand__PrmAssignment_2 ) ) ;
    public final void rule__ExclamationParameterOperand__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5907:1: ( ( ( rule__ExclamationParameterOperand__PrmAssignment_2 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5908:1: ( ( rule__ExclamationParameterOperand__PrmAssignment_2 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5908:1: ( ( rule__ExclamationParameterOperand__PrmAssignment_2 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5909:1: ( rule__ExclamationParameterOperand__PrmAssignment_2 )
            {
             before(grammarAccess.getExclamationParameterOperandAccess().getPrmAssignment_2()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5910:1: ( rule__ExclamationParameterOperand__PrmAssignment_2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5910:2: rule__ExclamationParameterOperand__PrmAssignment_2
            {
            pushFollow(FOLLOW_rule__ExclamationParameterOperand__PrmAssignment_2_in_rule__ExclamationParameterOperand__Group__2__Impl12057);
            rule__ExclamationParameterOperand__PrmAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getExclamationParameterOperandAccess().getPrmAssignment_2()); 

            }


            }

        }
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5920:1: rule__ExclamationParameterOperand__Group__3 : rule__ExclamationParameterOperand__Group__3__Impl ;
    public final void rule__ExclamationParameterOperand__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5924:1: ( rule__ExclamationParameterOperand__Group__3__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5925:2: rule__ExclamationParameterOperand__Group__3__Impl
            {
            pushFollow(FOLLOW_rule__ExclamationParameterOperand__Group__3__Impl_in_rule__ExclamationParameterOperand__Group__312087);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5931:1: rule__ExclamationParameterOperand__Group__3__Impl : ( KEYWORD_11 ) ;
    public final void rule__ExclamationParameterOperand__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5935:1: ( ( KEYWORD_11 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5936:1: ( KEYWORD_11 )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5936:1: ( KEYWORD_11 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5937:1: KEYWORD_11
            {
             before(grammarAccess.getExclamationParameterOperandAccess().getRightCurlyBracketKeyword_3()); 
            match(input,KEYWORD_11,FOLLOW_KEYWORD_11_in_rule__ExclamationParameterOperand__Group__3__Impl12115); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5958:1: rule__SubQueryOperand__Group__0 : rule__SubQueryOperand__Group__0__Impl rule__SubQueryOperand__Group__1 ;
    public final void rule__SubQueryOperand__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5962:1: ( rule__SubQueryOperand__Group__0__Impl rule__SubQueryOperand__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5963:2: rule__SubQueryOperand__Group__0__Impl rule__SubQueryOperand__Group__1
            {
            pushFollow(FOLLOW_rule__SubQueryOperand__Group__0__Impl_in_rule__SubQueryOperand__Group__012154);
            rule__SubQueryOperand__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__SubQueryOperand__Group__1_in_rule__SubQueryOperand__Group__012157);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5970:1: rule__SubQueryOperand__Group__0__Impl : ( () ) ;
    public final void rule__SubQueryOperand__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5974:1: ( ( () ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5975:1: ( () )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5975:1: ( () )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5976:1: ()
            {
             before(grammarAccess.getSubQueryOperandAccess().getSubqueryAction_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5977:1: ()
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5979:1: 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5989:1: rule__SubQueryOperand__Group__1 : rule__SubQueryOperand__Group__1__Impl rule__SubQueryOperand__Group__2 ;
    public final void rule__SubQueryOperand__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5993:1: ( rule__SubQueryOperand__Group__1__Impl rule__SubQueryOperand__Group__2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5994:2: rule__SubQueryOperand__Group__1__Impl rule__SubQueryOperand__Group__2
            {
            pushFollow(FOLLOW_rule__SubQueryOperand__Group__1__Impl_in_rule__SubQueryOperand__Group__112215);
            rule__SubQueryOperand__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__SubQueryOperand__Group__2_in_rule__SubQueryOperand__Group__112218);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6001:1: rule__SubQueryOperand__Group__1__Impl : ( KEYWORD_1 ) ;
    public final void rule__SubQueryOperand__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6005:1: ( ( KEYWORD_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6006:1: ( KEYWORD_1 )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6006:1: ( KEYWORD_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6007:1: KEYWORD_1
            {
             before(grammarAccess.getSubQueryOperandAccess().getLeftParenthesisKeyword_1()); 
            match(input,KEYWORD_1,FOLLOW_KEYWORD_1_in_rule__SubQueryOperand__Group__1__Impl12246); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6020:1: rule__SubQueryOperand__Group__2 : rule__SubQueryOperand__Group__2__Impl rule__SubQueryOperand__Group__3 ;
    public final void rule__SubQueryOperand__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6024:1: ( rule__SubQueryOperand__Group__2__Impl rule__SubQueryOperand__Group__3 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6025:2: rule__SubQueryOperand__Group__2__Impl rule__SubQueryOperand__Group__3
            {
            pushFollow(FOLLOW_rule__SubQueryOperand__Group__2__Impl_in_rule__SubQueryOperand__Group__212277);
            rule__SubQueryOperand__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__SubQueryOperand__Group__3_in_rule__SubQueryOperand__Group__212280);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6032:1: rule__SubQueryOperand__Group__2__Impl : ( ( rule__SubQueryOperand__SelAssignment_2 ) ) ;
    public final void rule__SubQueryOperand__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6036:1: ( ( ( rule__SubQueryOperand__SelAssignment_2 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6037:1: ( ( rule__SubQueryOperand__SelAssignment_2 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6037:1: ( ( rule__SubQueryOperand__SelAssignment_2 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6038:1: ( rule__SubQueryOperand__SelAssignment_2 )
            {
             before(grammarAccess.getSubQueryOperandAccess().getSelAssignment_2()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6039:1: ( rule__SubQueryOperand__SelAssignment_2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6039:2: rule__SubQueryOperand__SelAssignment_2
            {
            pushFollow(FOLLOW_rule__SubQueryOperand__SelAssignment_2_in_rule__SubQueryOperand__Group__2__Impl12307);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6049:1: rule__SubQueryOperand__Group__3 : rule__SubQueryOperand__Group__3__Impl ;
    public final void rule__SubQueryOperand__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6053:1: ( rule__SubQueryOperand__Group__3__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6054:2: rule__SubQueryOperand__Group__3__Impl
            {
            pushFollow(FOLLOW_rule__SubQueryOperand__Group__3__Impl_in_rule__SubQueryOperand__Group__312337);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6060:1: rule__SubQueryOperand__Group__3__Impl : ( KEYWORD_2 ) ;
    public final void rule__SubQueryOperand__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6064:1: ( ( KEYWORD_2 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6065:1: ( KEYWORD_2 )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6065:1: ( KEYWORD_2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6066:1: KEYWORD_2
            {
             before(grammarAccess.getSubQueryOperandAccess().getRightParenthesisKeyword_3()); 
            match(input,KEYWORD_2,FOLLOW_KEYWORD_2_in_rule__SubQueryOperand__Group__3__Impl12365); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6088:1: rule__Model__EntriesAssignment_1_1_1 : ( ruleSelect ) ;
    public final void rule__Model__EntriesAssignment_1_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6092:1: ( ( ruleSelect ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6093:1: ( ruleSelect )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6093:1: ( ruleSelect )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6094:1: ruleSelect
            {
             before(grammarAccess.getModelAccess().getEntriesSelectParserRuleCall_1_1_1_0()); 
            pushFollow(FOLLOW_ruleSelect_in_rule__Model__EntriesAssignment_1_1_112409);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6103:1: rule__Model__OrderByEntryAssignment_2_1 : ( ruleOrderByColumns ) ;
    public final void rule__Model__OrderByEntryAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6107:1: ( ( ruleOrderByColumns ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6108:1: ( ruleOrderByColumns )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6108:1: ( ruleOrderByColumns )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6109:1: ruleOrderByColumns
            {
             before(grammarAccess.getModelAccess().getOrderByEntryOrderByColumnsParserRuleCall_2_1_0()); 
            pushFollow(FOLLOW_ruleOrderByColumns_in_rule__Model__OrderByEntryAssignment_2_112440);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6118:1: rule__Select__SelectAssignment_0 : ( ( KEYWORD_36 ) ) ;
    public final void rule__Select__SelectAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6122:1: ( ( ( KEYWORD_36 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6123:1: ( ( KEYWORD_36 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6123:1: ( ( KEYWORD_36 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6124:1: ( KEYWORD_36 )
            {
             before(grammarAccess.getSelectAccess().getSelectSELECTKeyword_0_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6125:1: ( KEYWORD_36 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6126:1: KEYWORD_36
            {
             before(grammarAccess.getSelectAccess().getSelectSELECTKeyword_0_0()); 
            match(input,KEYWORD_36,FOLLOW_KEYWORD_36_in_rule__Select__SelectAssignment_012476); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6141:1: rule__Select__ColsAssignment_2 : ( ruleColumns ) ;
    public final void rule__Select__ColsAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6145:1: ( ( ruleColumns ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6146:1: ( ruleColumns )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6146:1: ( ruleColumns )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6147:1: ruleColumns
            {
             before(grammarAccess.getSelectAccess().getColsColumnsParserRuleCall_2_0()); 
            pushFollow(FOLLOW_ruleColumns_in_rule__Select__ColsAssignment_212515);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6156:1: rule__Select__TblAssignment_4 : ( ruleTables ) ;
    public final void rule__Select__TblAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6160:1: ( ( ruleTables ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6161:1: ( ruleTables )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6161:1: ( ruleTables )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6162:1: ruleTables
            {
             before(grammarAccess.getSelectAccess().getTblTablesParserRuleCall_4_0()); 
            pushFollow(FOLLOW_ruleTables_in_rule__Select__TblAssignment_412546);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6171:1: rule__Select__WhereExpressionAssignment_5_1 : ( ruleFullExpression ) ;
    public final void rule__Select__WhereExpressionAssignment_5_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6175:1: ( ( ruleFullExpression ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6176:1: ( ruleFullExpression )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6176:1: ( ruleFullExpression )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6177:1: ruleFullExpression
            {
             before(grammarAccess.getSelectAccess().getWhereExpressionFullExpressionParserRuleCall_5_1_0()); 
            pushFollow(FOLLOW_ruleFullExpression_in_rule__Select__WhereExpressionAssignment_5_112577);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6186:1: rule__Select__GroupByEntryAssignment_6_1 : ( ruleGroupByColumns ) ;
    public final void rule__Select__GroupByEntryAssignment_6_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6190:1: ( ( ruleGroupByColumns ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6191:1: ( ruleGroupByColumns )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6191:1: ( ruleGroupByColumns )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6192:1: ruleGroupByColumns
            {
             before(grammarAccess.getSelectAccess().getGroupByEntryGroupByColumnsParserRuleCall_6_1_0()); 
            pushFollow(FOLLOW_ruleGroupByColumns_in_rule__Select__GroupByEntryAssignment_6_112608);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6201:1: rule__Select__HavingEntryAssignment_7_1 : ( ruleFullExpression ) ;
    public final void rule__Select__HavingEntryAssignment_7_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6205:1: ( ( ruleFullExpression ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6206:1: ( ruleFullExpression )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6206:1: ( ruleFullExpression )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6207:1: ruleFullExpression
            {
             before(grammarAccess.getSelectAccess().getHavingEntryFullExpressionParserRuleCall_7_1_0()); 
            pushFollow(FOLLOW_ruleFullExpression_in_rule__Select__HavingEntryAssignment_7_112639);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6216:1: rule__Columns__EntriesAssignment_1_1_1 : ( ruleColumnOrAlias ) ;
    public final void rule__Columns__EntriesAssignment_1_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6220:1: ( ( ruleColumnOrAlias ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6221:1: ( ruleColumnOrAlias )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6221:1: ( ruleColumnOrAlias )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6222:1: ruleColumnOrAlias
            {
             before(grammarAccess.getColumnsAccess().getEntriesColumnOrAliasParserRuleCall_1_1_1_0()); 
            pushFollow(FOLLOW_ruleColumnOrAlias_in_rule__Columns__EntriesAssignment_1_1_112670);
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


    // $ANTLR start "rule__ColumnOrAlias__CfullAssignment_0_0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6231:1: rule__ColumnOrAlias__CfullAssignment_0_0 : ( ruleColumnFull ) ;
    public final void rule__ColumnOrAlias__CfullAssignment_0_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6235:1: ( ( ruleColumnFull ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6236:1: ( ruleColumnFull )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6236:1: ( ruleColumnFull )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6237:1: ruleColumnFull
            {
             before(grammarAccess.getColumnOrAliasAccess().getCfullColumnFullParserRuleCall_0_0_0()); 
            pushFollow(FOLLOW_ruleColumnFull_in_rule__ColumnOrAlias__CfullAssignment_0_012701);
            ruleColumnFull();

            state._fsp--;

             after(grammarAccess.getColumnOrAliasAccess().getCfullColumnFullParserRuleCall_0_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnOrAlias__CfullAssignment_0_0"


    // $ANTLR start "rule__ColumnOrAlias__AliasAssignment_0_1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6246:1: rule__ColumnOrAlias__AliasAssignment_0_1 : ( ( KEYWORD_15 ) ) ;
    public final void rule__ColumnOrAlias__AliasAssignment_0_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6250:1: ( ( ( KEYWORD_15 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6251:1: ( ( KEYWORD_15 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6251:1: ( ( KEYWORD_15 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6252:1: ( KEYWORD_15 )
            {
             before(grammarAccess.getColumnOrAliasAccess().getAliasASKeyword_0_1_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6253:1: ( KEYWORD_15 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6254:1: KEYWORD_15
            {
             before(grammarAccess.getColumnOrAliasAccess().getAliasASKeyword_0_1_0()); 
            match(input,KEYWORD_15,FOLLOW_KEYWORD_15_in_rule__ColumnOrAlias__AliasAssignment_0_112737); 
             after(grammarAccess.getColumnOrAliasAccess().getAliasASKeyword_0_1_0()); 

            }

             after(grammarAccess.getColumnOrAliasAccess().getAliasASKeyword_0_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnOrAlias__AliasAssignment_0_1"


    // $ANTLR start "rule__ColumnOrAlias__ColAliasAssignment_0_2"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6269:1: rule__ColumnOrAlias__ColAliasAssignment_0_2 : ( ruleDbObjectName ) ;
    public final void rule__ColumnOrAlias__ColAliasAssignment_0_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6273:1: ( ( ruleDbObjectName ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6274:1: ( ruleDbObjectName )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6274:1: ( ruleDbObjectName )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6275:1: ruleDbObjectName
            {
             before(grammarAccess.getColumnOrAliasAccess().getColAliasDbObjectNameParserRuleCall_0_2_0()); 
            pushFollow(FOLLOW_ruleDbObjectName_in_rule__ColumnOrAlias__ColAliasAssignment_0_212776);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6284:1: rule__ColumnOrAlias__AllColsAssignment_1 : ( RULE_STAR ) ;
    public final void rule__ColumnOrAlias__AllColsAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6288:1: ( ( RULE_STAR ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6289:1: ( RULE_STAR )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6289:1: ( RULE_STAR )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6290:1: RULE_STAR
            {
             before(grammarAccess.getColumnOrAliasAccess().getAllColsSTARTerminalRuleCall_1_0()); 
            match(input,RULE_STAR,FOLLOW_RULE_STAR_in_rule__ColumnOrAlias__AllColsAssignment_112807); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6299:1: rule__ColumnFull__EntriesAssignment_1_1_1 : ( ruleDbObjectName ) ;
    public final void rule__ColumnFull__EntriesAssignment_1_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6303:1: ( ( ruleDbObjectName ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6304:1: ( ruleDbObjectName )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6304:1: ( ruleDbObjectName )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6305:1: ruleDbObjectName
            {
             before(grammarAccess.getColumnFullAccess().getEntriesDbObjectNameParserRuleCall_1_1_1_0()); 
            pushFollow(FOLLOW_ruleDbObjectName_in_rule__ColumnFull__EntriesAssignment_1_1_112838);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6314:1: rule__Tables__EntriesAssignment_1_1_1 : ( ruleFromTable ) ;
    public final void rule__Tables__EntriesAssignment_1_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6318:1: ( ( ruleFromTable ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6319:1: ( ruleFromTable )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6319:1: ( ruleFromTable )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6320:1: ruleFromTable
            {
             before(grammarAccess.getTablesAccess().getEntriesFromTableParserRuleCall_1_1_1_0()); 
            pushFollow(FOLLOW_ruleFromTable_in_rule__Tables__EntriesAssignment_1_1_112869);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6329:1: rule__FromTable__TableAssignment_0 : ( ruleTableOrAlias ) ;
    public final void rule__FromTable__TableAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6333:1: ( ( ruleTableOrAlias ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6334:1: ( ruleTableOrAlias )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6334:1: ( ruleTableOrAlias )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6335:1: ruleTableOrAlias
            {
             before(grammarAccess.getFromTableAccess().getTableTableOrAliasParserRuleCall_0_0()); 
            pushFollow(FOLLOW_ruleTableOrAlias_in_rule__FromTable__TableAssignment_012900);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6344:1: rule__FromTable__JoinAssignment_1_0 : ( ruleJoinType ) ;
    public final void rule__FromTable__JoinAssignment_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6348:1: ( ( ruleJoinType ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6349:1: ( ruleJoinType )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6349:1: ( ruleJoinType )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6350:1: ruleJoinType
            {
             before(grammarAccess.getFromTableAccess().getJoinJoinTypeEnumRuleCall_1_0_0()); 
            pushFollow(FOLLOW_ruleJoinType_in_rule__FromTable__JoinAssignment_1_012931);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6359:1: rule__FromTable__OnTableAssignment_1_1 : ( ruleTableOrAlias ) ;
    public final void rule__FromTable__OnTableAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6363:1: ( ( ruleTableOrAlias ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6364:1: ( ruleTableOrAlias )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6364:1: ( ruleTableOrAlias )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6365:1: ruleTableOrAlias
            {
             before(grammarAccess.getFromTableAccess().getOnTableTableOrAliasParserRuleCall_1_1_0()); 
            pushFollow(FOLLOW_ruleTableOrAlias_in_rule__FromTable__OnTableAssignment_1_112962);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6374:1: rule__FromTable__JoinExprAssignment_1_3 : ( ruleFullExpression ) ;
    public final void rule__FromTable__JoinExprAssignment_1_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6378:1: ( ( ruleFullExpression ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6379:1: ( ruleFullExpression )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6379:1: ( ruleFullExpression )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6380:1: ruleFullExpression
            {
             before(grammarAccess.getFromTableAccess().getJoinExprFullExpressionParserRuleCall_1_3_0()); 
            pushFollow(FOLLOW_ruleFullExpression_in_rule__FromTable__JoinExprAssignment_1_312993);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6389:1: rule__TableOrAlias__TfullAssignment_0 : ( ruleTableFull ) ;
    public final void rule__TableOrAlias__TfullAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6393:1: ( ( ruleTableFull ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6394:1: ( ruleTableFull )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6394:1: ( ruleTableFull )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6395:1: ruleTableFull
            {
             before(grammarAccess.getTableOrAliasAccess().getTfullTableFullParserRuleCall_0_0()); 
            pushFollow(FOLLOW_ruleTableFull_in_rule__TableOrAlias__TfullAssignment_013024);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6404:1: rule__TableOrAlias__AliasAssignment_1 : ( ( KEYWORD_15 ) ) ;
    public final void rule__TableOrAlias__AliasAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6408:1: ( ( ( KEYWORD_15 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6409:1: ( ( KEYWORD_15 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6409:1: ( ( KEYWORD_15 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6410:1: ( KEYWORD_15 )
            {
             before(grammarAccess.getTableOrAliasAccess().getAliasASKeyword_1_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6411:1: ( KEYWORD_15 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6412:1: KEYWORD_15
            {
             before(grammarAccess.getTableOrAliasAccess().getAliasASKeyword_1_0()); 
            match(input,KEYWORD_15,FOLLOW_KEYWORD_15_in_rule__TableOrAlias__AliasAssignment_113060); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6427:1: rule__TableOrAlias__TblAliasAssignment_2 : ( ruleDbObjectName ) ;
    public final void rule__TableOrAlias__TblAliasAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6431:1: ( ( ruleDbObjectName ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6432:1: ( ruleDbObjectName )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6432:1: ( ruleDbObjectName )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6433:1: ruleDbObjectName
            {
             before(grammarAccess.getTableOrAliasAccess().getTblAliasDbObjectNameParserRuleCall_2_0()); 
            pushFollow(FOLLOW_ruleDbObjectName_in_rule__TableOrAlias__TblAliasAssignment_213099);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6442:1: rule__TableFull__EntriesAssignment_1_1_1 : ( ruleDbObjectName ) ;
    public final void rule__TableFull__EntriesAssignment_1_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6446:1: ( ( ruleDbObjectName ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6447:1: ( ruleDbObjectName )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6447:1: ( ruleDbObjectName )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6448:1: ruleDbObjectName
            {
             before(grammarAccess.getTableFullAccess().getEntriesDbObjectNameParserRuleCall_1_1_1_0()); 
            pushFollow(FOLLOW_ruleDbObjectName_in_rule__TableFull__EntriesAssignment_1_1_113130);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6457:1: rule__DbObjectName__DbnameAssignment : ( RULE_ID ) ;
    public final void rule__DbObjectName__DbnameAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6461:1: ( ( RULE_ID ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6462:1: ( RULE_ID )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6462:1: ( RULE_ID )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6463:1: RULE_ID
            {
             before(grammarAccess.getDbObjectNameAccess().getDbnameIDTerminalRuleCall_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__DbObjectName__DbnameAssignment13161); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6472:1: rule__OrderByColumns__EntriesAssignment_1_1_1 : ( ruleOrderByColumnFull ) ;
    public final void rule__OrderByColumns__EntriesAssignment_1_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6476:1: ( ( ruleOrderByColumnFull ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6477:1: ( ruleOrderByColumnFull )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6477:1: ( ruleOrderByColumnFull )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6478:1: ruleOrderByColumnFull
            {
             before(grammarAccess.getOrderByColumnsAccess().getEntriesOrderByColumnFullParserRuleCall_1_1_1_0()); 
            pushFollow(FOLLOW_ruleOrderByColumnFull_in_rule__OrderByColumns__EntriesAssignment_1_1_113192);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6487:1: rule__OrderByColumnFull__ColOrderAssignment_0 : ( ruleColumnFull ) ;
    public final void rule__OrderByColumnFull__ColOrderAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6491:1: ( ( ruleColumnFull ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6492:1: ( ruleColumnFull )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6492:1: ( ruleColumnFull )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6493:1: ruleColumnFull
            {
             before(grammarAccess.getOrderByColumnFullAccess().getColOrderColumnFullParserRuleCall_0_0()); 
            pushFollow(FOLLOW_ruleColumnFull_in_rule__OrderByColumnFull__ColOrderAssignment_013223);
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


    // $ANTLR start "rule__OrderByColumnFull__DirectionAssignment_1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6502:1: rule__OrderByColumnFull__DirectionAssignment_1 : ( ( rule__OrderByColumnFull__DirectionAlternatives_1_0 ) ) ;
    public final void rule__OrderByColumnFull__DirectionAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6506:1: ( ( ( rule__OrderByColumnFull__DirectionAlternatives_1_0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6507:1: ( ( rule__OrderByColumnFull__DirectionAlternatives_1_0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6507:1: ( ( rule__OrderByColumnFull__DirectionAlternatives_1_0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6508:1: ( rule__OrderByColumnFull__DirectionAlternatives_1_0 )
            {
             before(grammarAccess.getOrderByColumnFullAccess().getDirectionAlternatives_1_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6509:1: ( rule__OrderByColumnFull__DirectionAlternatives_1_0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6509:2: rule__OrderByColumnFull__DirectionAlternatives_1_0
            {
            pushFollow(FOLLOW_rule__OrderByColumnFull__DirectionAlternatives_1_0_in_rule__OrderByColumnFull__DirectionAssignment_113254);
            rule__OrderByColumnFull__DirectionAlternatives_1_0();

            state._fsp--;


            }

             after(grammarAccess.getOrderByColumnFullAccess().getDirectionAlternatives_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OrderByColumnFull__DirectionAssignment_1"


    // $ANTLR start "rule__GroupByColumns__EntriesAssignment_1_1_1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6518:1: rule__GroupByColumns__EntriesAssignment_1_1_1 : ( ruleColumnFull ) ;
    public final void rule__GroupByColumns__EntriesAssignment_1_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6522:1: ( ( ruleColumnFull ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6523:1: ( ruleColumnFull )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6523:1: ( ruleColumnFull )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6524:1: ruleColumnFull
            {
             before(grammarAccess.getGroupByColumnsAccess().getEntriesColumnFullParserRuleCall_1_1_1_0()); 
            pushFollow(FOLLOW_ruleColumnFull_in_rule__GroupByColumns__EntriesAssignment_1_1_113287);
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


    // $ANTLR start "rule__FullExpression__EntriesAssignment_1_1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6533:1: rule__FullExpression__EntriesAssignment_1_1 : ( ruleExpressionFragmentSecond ) ;
    public final void rule__FullExpression__EntriesAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6537:1: ( ( ruleExpressionFragmentSecond ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6538:1: ( ruleExpressionFragmentSecond )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6538:1: ( ruleExpressionFragmentSecond )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6539:1: ruleExpressionFragmentSecond
            {
             before(grammarAccess.getFullExpressionAccess().getEntriesExpressionFragmentSecondParserRuleCall_1_1_0()); 
            pushFollow(FOLLOW_ruleExpressionFragmentSecond_in_rule__FullExpression__EntriesAssignment_1_113318);
            ruleExpressionFragmentSecond();

            state._fsp--;

             after(grammarAccess.getFullExpressionAccess().getEntriesExpressionFragmentSecondParserRuleCall_1_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FullExpression__EntriesAssignment_1_1"


    // $ANTLR start "rule__ExpressionFragmentSecond__CAssignment_0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6548:1: rule__ExpressionFragmentSecond__CAssignment_0 : ( ( rule__ExpressionFragmentSecond__CAlternatives_0_0 ) ) ;
    public final void rule__ExpressionFragmentSecond__CAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6552:1: ( ( ( rule__ExpressionFragmentSecond__CAlternatives_0_0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6553:1: ( ( rule__ExpressionFragmentSecond__CAlternatives_0_0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6553:1: ( ( rule__ExpressionFragmentSecond__CAlternatives_0_0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6554:1: ( rule__ExpressionFragmentSecond__CAlternatives_0_0 )
            {
             before(grammarAccess.getExpressionFragmentSecondAccess().getCAlternatives_0_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6555:1: ( rule__ExpressionFragmentSecond__CAlternatives_0_0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6555:2: rule__ExpressionFragmentSecond__CAlternatives_0_0
            {
            pushFollow(FOLLOW_rule__ExpressionFragmentSecond__CAlternatives_0_0_in_rule__ExpressionFragmentSecond__CAssignment_013349);
            rule__ExpressionFragmentSecond__CAlternatives_0_0();

            state._fsp--;


            }

             after(grammarAccess.getExpressionFragmentSecondAccess().getCAlternatives_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ExpressionFragmentSecond__CAssignment_0"


    // $ANTLR start "rule__ExpressionFragmentSecond__EfragAssignment_1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6564:1: rule__ExpressionFragmentSecond__EfragAssignment_1 : ( ruleExpressionFragment ) ;
    public final void rule__ExpressionFragmentSecond__EfragAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6568:1: ( ( ruleExpressionFragment ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6569:1: ( ruleExpressionFragment )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6569:1: ( ruleExpressionFragment )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6570:1: ruleExpressionFragment
            {
             before(grammarAccess.getExpressionFragmentSecondAccess().getEfragExpressionFragmentParserRuleCall_1_0()); 
            pushFollow(FOLLOW_ruleExpressionFragment_in_rule__ExpressionFragmentSecond__EfragAssignment_113382);
            ruleExpressionFragment();

            state._fsp--;

             after(grammarAccess.getExpressionFragmentSecondAccess().getEfragExpressionFragmentParserRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ExpressionFragmentSecond__EfragAssignment_1"


    // $ANTLR start "rule__ExpressionFragment__ExpgroupAssignment_0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6579:1: rule__ExpressionFragment__ExpgroupAssignment_0 : ( ruleExpressionGroup ) ;
    public final void rule__ExpressionFragment__ExpgroupAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6583:1: ( ( ruleExpressionGroup ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6584:1: ( ruleExpressionGroup )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6584:1: ( ruleExpressionGroup )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6585:1: ruleExpressionGroup
            {
             before(grammarAccess.getExpressionFragmentAccess().getExpgroupExpressionGroupParserRuleCall_0_0()); 
            pushFollow(FOLLOW_ruleExpressionGroup_in_rule__ExpressionFragment__ExpgroupAssignment_013413);
            ruleExpressionGroup();

            state._fsp--;

             after(grammarAccess.getExpressionFragmentAccess().getExpgroupExpressionGroupParserRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ExpressionFragment__ExpgroupAssignment_0"


    // $ANTLR start "rule__ExpressionFragment__ExpAssignment_1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6594:1: rule__ExpressionFragment__ExpAssignment_1 : ( ruleExpression ) ;
    public final void rule__ExpressionFragment__ExpAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6598:1: ( ( ruleExpression ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6599:1: ( ruleExpression )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6599:1: ( ruleExpression )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6600:1: ruleExpression
            {
             before(grammarAccess.getExpressionFragmentAccess().getExpExpressionParserRuleCall_1_0()); 
            pushFollow(FOLLOW_ruleExpression_in_rule__ExpressionFragment__ExpAssignment_113444);
            ruleExpression();

            state._fsp--;

             after(grammarAccess.getExpressionFragmentAccess().getExpExpressionParserRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ExpressionFragment__ExpAssignment_1"


    // $ANTLR start "rule__ExpressionFragment__XexpAssignment_2"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6609:1: rule__ExpressionFragment__XexpAssignment_2 : ( ruleXExpression ) ;
    public final void rule__ExpressionFragment__XexpAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6613:1: ( ( ruleXExpression ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6614:1: ( ruleXExpression )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6614:1: ( ruleXExpression )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6615:1: ruleXExpression
            {
             before(grammarAccess.getExpressionFragmentAccess().getXexpXExpressionParserRuleCall_2_0()); 
            pushFollow(FOLLOW_ruleXExpression_in_rule__ExpressionFragment__XexpAssignment_213475);
            ruleXExpression();

            state._fsp--;

             after(grammarAccess.getExpressionFragmentAccess().getXexpXExpressionParserRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ExpressionFragment__XexpAssignment_2"


    // $ANTLR start "rule__ExpressionGroup__ExprAssignment_2"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6624:1: rule__ExpressionGroup__ExprAssignment_2 : ( ruleFullExpression ) ;
    public final void rule__ExpressionGroup__ExprAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6628:1: ( ( ruleFullExpression ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6629:1: ( ruleFullExpression )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6629:1: ( ruleFullExpression )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6630:1: ruleFullExpression
            {
             before(grammarAccess.getExpressionGroupAccess().getExprFullExpressionParserRuleCall_2_0()); 
            pushFollow(FOLLOW_ruleFullExpression_in_rule__ExpressionGroup__ExprAssignment_213506);
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


    // $ANTLR start "rule__XExpression__XfAssignment_2"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6639:1: rule__XExpression__XfAssignment_2 : ( ruleXFunction ) ;
    public final void rule__XExpression__XfAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6643:1: ( ( ruleXFunction ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6644:1: ( ruleXFunction )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6644:1: ( ruleXFunction )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6645:1: ruleXFunction
            {
             before(grammarAccess.getXExpressionAccess().getXfXFunctionEnumRuleCall_2_0()); 
            pushFollow(FOLLOW_ruleXFunction_in_rule__XExpression__XfAssignment_213537);
            ruleXFunction();

            state._fsp--;

             after(grammarAccess.getXExpressionAccess().getXfXFunctionEnumRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XExpression__XfAssignment_2"


    // $ANTLR start "rule__XExpression__ColAssignment_4"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6654:1: rule__XExpression__ColAssignment_4 : ( ruleColumnOperand ) ;
    public final void rule__XExpression__ColAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6658:1: ( ( ruleColumnOperand ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6659:1: ( ruleColumnOperand )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6659:1: ( ruleColumnOperand )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6660:1: ruleColumnOperand
            {
             before(grammarAccess.getXExpressionAccess().getColColumnOperandParserRuleCall_4_0()); 
            pushFollow(FOLLOW_ruleColumnOperand_in_rule__XExpression__ColAssignment_413568);
            ruleColumnOperand();

            state._fsp--;

             after(grammarAccess.getXExpressionAccess().getColColumnOperandParserRuleCall_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XExpression__ColAssignment_4"


    // $ANTLR start "rule__XExpression__PrmAssignment_5_1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6669:1: rule__XExpression__PrmAssignment_5_1 : ( ruleXExpressionParams ) ;
    public final void rule__XExpression__PrmAssignment_5_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6673:1: ( ( ruleXExpressionParams ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6674:1: ( ruleXExpressionParams )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6674:1: ( ruleXExpressionParams )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6675:1: ruleXExpressionParams
            {
             before(grammarAccess.getXExpressionAccess().getPrmXExpressionParamsParserRuleCall_5_1_0()); 
            pushFollow(FOLLOW_ruleXExpressionParams_in_rule__XExpression__PrmAssignment_5_113599);
            ruleXExpressionParams();

            state._fsp--;

             after(grammarAccess.getXExpressionAccess().getPrmXExpressionParamsParserRuleCall_5_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XExpression__PrmAssignment_5_1"


    // $ANTLR start "rule__XExpressionParams__EntriesAssignment_1_1_1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6684:1: rule__XExpressionParams__EntriesAssignment_1_1_1 : ( ruleJRParameter ) ;
    public final void rule__XExpressionParams__EntriesAssignment_1_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6688:1: ( ( ruleJRParameter ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6689:1: ( ruleJRParameter )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6689:1: ( ruleJRParameter )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6690:1: ruleJRParameter
            {
             before(grammarAccess.getXExpressionParamsAccess().getEntriesJRParameterParserRuleCall_1_1_1_0()); 
            pushFollow(FOLLOW_ruleJRParameter_in_rule__XExpressionParams__EntriesAssignment_1_1_113630);
            ruleJRParameter();

            state._fsp--;

             after(grammarAccess.getXExpressionParamsAccess().getEntriesJRParameterParserRuleCall_1_1_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XExpressionParams__EntriesAssignment_1_1_1"


    // $ANTLR start "rule__JRParameter__JrprmAssignment"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6699:1: rule__JRParameter__JrprmAssignment : ( RULE_ID ) ;
    public final void rule__JRParameter__JrprmAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6703:1: ( ( RULE_ID ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6704:1: ( RULE_ID )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6704:1: ( RULE_ID )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6705:1: RULE_ID
            {
             before(grammarAccess.getJRParameterAccess().getJrprmIDTerminalRuleCall_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__JRParameter__JrprmAssignment13661); 
             after(grammarAccess.getJRParameterAccess().getJrprmIDTerminalRuleCall_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JRParameter__JrprmAssignment"


    // $ANTLR start "rule__Expression__Op1Assignment_0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6714:1: rule__Expression__Op1Assignment_0 : ( ruleOperand ) ;
    public final void rule__Expression__Op1Assignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6718:1: ( ( ruleOperand ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6719:1: ( ruleOperand )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6719:1: ( ruleOperand )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6720:1: ruleOperand
            {
             before(grammarAccess.getExpressionAccess().getOp1OperandParserRuleCall_0_0()); 
            pushFollow(FOLLOW_ruleOperand_in_rule__Expression__Op1Assignment_013692);
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


    // $ANTLR start "rule__Expression__IsnullAssignment_1_0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6729:1: rule__Expression__IsnullAssignment_1_0 : ( ( rule__Expression__IsnullAlternatives_1_0_0 ) ) ;
    public final void rule__Expression__IsnullAssignment_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6733:1: ( ( ( rule__Expression__IsnullAlternatives_1_0_0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6734:1: ( ( rule__Expression__IsnullAlternatives_1_0_0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6734:1: ( ( rule__Expression__IsnullAlternatives_1_0_0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6735:1: ( rule__Expression__IsnullAlternatives_1_0_0 )
            {
             before(grammarAccess.getExpressionAccess().getIsnullAlternatives_1_0_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6736:1: ( rule__Expression__IsnullAlternatives_1_0_0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6736:2: rule__Expression__IsnullAlternatives_1_0_0
            {
            pushFollow(FOLLOW_rule__Expression__IsnullAlternatives_1_0_0_in_rule__Expression__IsnullAssignment_1_013723);
            rule__Expression__IsnullAlternatives_1_0_0();

            state._fsp--;


            }

             after(grammarAccess.getExpressionAccess().getIsnullAlternatives_1_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Expression__IsnullAssignment_1_0"


    // $ANTLR start "rule__Expression__InAssignment_1_1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6745:1: rule__Expression__InAssignment_1_1 : ( ruleInOperator ) ;
    public final void rule__Expression__InAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6749:1: ( ( ruleInOperator ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6750:1: ( ruleInOperator )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6750:1: ( ruleInOperator )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6751:1: ruleInOperator
            {
             before(grammarAccess.getExpressionAccess().getInInOperatorParserRuleCall_1_1_0()); 
            pushFollow(FOLLOW_ruleInOperator_in_rule__Expression__InAssignment_1_113756);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6760:1: rule__Expression__BetweenAssignment_1_2 : ( ruleBetween ) ;
    public final void rule__Expression__BetweenAssignment_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6764:1: ( ( ruleBetween ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6765:1: ( ruleBetween )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6765:1: ( ruleBetween )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6766:1: ruleBetween
            {
             before(grammarAccess.getExpressionAccess().getBetweenBetweenParserRuleCall_1_2_0()); 
            pushFollow(FOLLOW_ruleBetween_in_rule__Expression__BetweenAssignment_1_213787);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6775:1: rule__Expression__LikeAssignment_1_3 : ( ruleLike ) ;
    public final void rule__Expression__LikeAssignment_1_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6779:1: ( ( ruleLike ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6780:1: ( ruleLike )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6780:1: ( ruleLike )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6781:1: ruleLike
            {
             before(grammarAccess.getExpressionAccess().getLikeLikeParserRuleCall_1_3_0()); 
            pushFollow(FOLLOW_ruleLike_in_rule__Expression__LikeAssignment_1_313818);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6790:1: rule__Expression__CompAssignment_1_4 : ( ruleComparison ) ;
    public final void rule__Expression__CompAssignment_1_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6794:1: ( ( ruleComparison ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6795:1: ( ruleComparison )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6795:1: ( ruleComparison )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6796:1: ruleComparison
            {
             before(grammarAccess.getExpressionAccess().getCompComparisonParserRuleCall_1_4_0()); 
            pushFollow(FOLLOW_ruleComparison_in_rule__Expression__CompAssignment_1_413849);
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


    // $ANTLR start "rule__Comparison__OperatorAssignment_0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6805:1: rule__Comparison__OperatorAssignment_0 : ( ( rule__Comparison__OperatorAlternatives_0_0 ) ) ;
    public final void rule__Comparison__OperatorAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6809:1: ( ( ( rule__Comparison__OperatorAlternatives_0_0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6810:1: ( ( rule__Comparison__OperatorAlternatives_0_0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6810:1: ( ( rule__Comparison__OperatorAlternatives_0_0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6811:1: ( rule__Comparison__OperatorAlternatives_0_0 )
            {
             before(grammarAccess.getComparisonAccess().getOperatorAlternatives_0_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6812:1: ( rule__Comparison__OperatorAlternatives_0_0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6812:2: rule__Comparison__OperatorAlternatives_0_0
            {
            pushFollow(FOLLOW_rule__Comparison__OperatorAlternatives_0_0_in_rule__Comparison__OperatorAssignment_013880);
            rule__Comparison__OperatorAlternatives_0_0();

            state._fsp--;


            }

             after(grammarAccess.getComparisonAccess().getOperatorAlternatives_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Comparison__OperatorAssignment_0"


    // $ANTLR start "rule__Comparison__Op2Assignment_1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6821:1: rule__Comparison__Op2Assignment_1 : ( ruleOperand ) ;
    public final void rule__Comparison__Op2Assignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6825:1: ( ( ruleOperand ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6826:1: ( ruleOperand )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6826:1: ( ruleOperand )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6827:1: ruleOperand
            {
             before(grammarAccess.getComparisonAccess().getOp2OperandParserRuleCall_1_0()); 
            pushFollow(FOLLOW_ruleOperand_in_rule__Comparison__Op2Assignment_113913);
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


    // $ANTLR start "rule__Like__OpLikeAssignment_0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6836:1: rule__Like__OpLikeAssignment_0 : ( ( rule__Like__OpLikeAlternatives_0_0 ) ) ;
    public final void rule__Like__OpLikeAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6840:1: ( ( ( rule__Like__OpLikeAlternatives_0_0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6841:1: ( ( rule__Like__OpLikeAlternatives_0_0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6841:1: ( ( rule__Like__OpLikeAlternatives_0_0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6842:1: ( rule__Like__OpLikeAlternatives_0_0 )
            {
             before(grammarAccess.getLikeAccess().getOpLikeAlternatives_0_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6843:1: ( rule__Like__OpLikeAlternatives_0_0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6843:2: rule__Like__OpLikeAlternatives_0_0
            {
            pushFollow(FOLLOW_rule__Like__OpLikeAlternatives_0_0_in_rule__Like__OpLikeAssignment_013944);
            rule__Like__OpLikeAlternatives_0_0();

            state._fsp--;


            }

             after(grammarAccess.getLikeAccess().getOpLikeAlternatives_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Like__OpLikeAssignment_0"


    // $ANTLR start "rule__Like__Op2Assignment_1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6852:1: rule__Like__Op2Assignment_1 : ( ruleStringOperand ) ;
    public final void rule__Like__Op2Assignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6856:1: ( ( ruleStringOperand ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6857:1: ( ruleStringOperand )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6857:1: ( ruleStringOperand )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6858:1: ruleStringOperand
            {
             before(grammarAccess.getLikeAccess().getOp2StringOperandParserRuleCall_1_0()); 
            pushFollow(FOLLOW_ruleStringOperand_in_rule__Like__Op2Assignment_113977);
            ruleStringOperand();

            state._fsp--;

             after(grammarAccess.getLikeAccess().getOp2StringOperandParserRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Like__Op2Assignment_1"


    // $ANTLR start "rule__Between__OpBetweenAssignment_0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6867:1: rule__Between__OpBetweenAssignment_0 : ( ( rule__Between__OpBetweenAlternatives_0_0 ) ) ;
    public final void rule__Between__OpBetweenAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6871:1: ( ( ( rule__Between__OpBetweenAlternatives_0_0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6872:1: ( ( rule__Between__OpBetweenAlternatives_0_0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6872:1: ( ( rule__Between__OpBetweenAlternatives_0_0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6873:1: ( rule__Between__OpBetweenAlternatives_0_0 )
            {
             before(grammarAccess.getBetweenAccess().getOpBetweenAlternatives_0_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6874:1: ( rule__Between__OpBetweenAlternatives_0_0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6874:2: rule__Between__OpBetweenAlternatives_0_0
            {
            pushFollow(FOLLOW_rule__Between__OpBetweenAlternatives_0_0_in_rule__Between__OpBetweenAssignment_014008);
            rule__Between__OpBetweenAlternatives_0_0();

            state._fsp--;


            }

             after(grammarAccess.getBetweenAccess().getOpBetweenAlternatives_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Between__OpBetweenAssignment_0"


    // $ANTLR start "rule__Between__Op2Assignment_1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6883:1: rule__Between__Op2Assignment_1 : ( ruleOperand ) ;
    public final void rule__Between__Op2Assignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6887:1: ( ( ruleOperand ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6888:1: ( ruleOperand )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6888:1: ( ruleOperand )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6889:1: ruleOperand
            {
             before(grammarAccess.getBetweenAccess().getOp2OperandParserRuleCall_1_0()); 
            pushFollow(FOLLOW_ruleOperand_in_rule__Between__Op2Assignment_114041);
            ruleOperand();

            state._fsp--;

             after(grammarAccess.getBetweenAccess().getOp2OperandParserRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Between__Op2Assignment_1"


    // $ANTLR start "rule__Between__Op3Assignment_3"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6898:1: rule__Between__Op3Assignment_3 : ( ruleOperand ) ;
    public final void rule__Between__Op3Assignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6902:1: ( ( ruleOperand ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6903:1: ( ruleOperand )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6903:1: ( ruleOperand )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6904:1: ruleOperand
            {
             before(grammarAccess.getBetweenAccess().getOp3OperandParserRuleCall_3_0()); 
            pushFollow(FOLLOW_ruleOperand_in_rule__Between__Op3Assignment_314072);
            ruleOperand();

            state._fsp--;

             after(grammarAccess.getBetweenAccess().getOp3OperandParserRuleCall_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Between__Op3Assignment_3"


    // $ANTLR start "rule__InOperator__OpAssignment_1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6913:1: rule__InOperator__OpAssignment_1 : ( ( rule__InOperator__OpAlternatives_1_0 ) ) ;
    public final void rule__InOperator__OpAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6917:1: ( ( ( rule__InOperator__OpAlternatives_1_0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6918:1: ( ( rule__InOperator__OpAlternatives_1_0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6918:1: ( ( rule__InOperator__OpAlternatives_1_0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6919:1: ( rule__InOperator__OpAlternatives_1_0 )
            {
             before(grammarAccess.getInOperatorAccess().getOpAlternatives_1_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6920:1: ( rule__InOperator__OpAlternatives_1_0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6920:2: rule__InOperator__OpAlternatives_1_0
            {
            pushFollow(FOLLOW_rule__InOperator__OpAlternatives_1_0_in_rule__InOperator__OpAssignment_114103);
            rule__InOperator__OpAlternatives_1_0();

            state._fsp--;


            }

             after(grammarAccess.getInOperatorAccess().getOpAlternatives_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InOperator__OpAssignment_1"


    // $ANTLR start "rule__InOperator__SubqueryAssignment_2_0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6929:1: rule__InOperator__SubqueryAssignment_2_0 : ( ruleSubQueryOperand ) ;
    public final void rule__InOperator__SubqueryAssignment_2_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6933:1: ( ( ruleSubQueryOperand ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6934:1: ( ruleSubQueryOperand )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6934:1: ( ruleSubQueryOperand )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6935:1: ruleSubQueryOperand
            {
             before(grammarAccess.getInOperatorAccess().getSubquerySubQueryOperandParserRuleCall_2_0_0()); 
            pushFollow(FOLLOW_ruleSubQueryOperand_in_rule__InOperator__SubqueryAssignment_2_014136);
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


    // $ANTLR start "rule__InOperator__OpListAssignment_2_1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6944:1: rule__InOperator__OpListAssignment_2_1 : ( ruleOperandList ) ;
    public final void rule__InOperator__OpListAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6948:1: ( ( ruleOperandList ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6949:1: ( ruleOperandList )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6949:1: ( ruleOperandList )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6950:1: ruleOperandList
            {
             before(grammarAccess.getInOperatorAccess().getOpListOperandListParserRuleCall_2_1_0()); 
            pushFollow(FOLLOW_ruleOperandList_in_rule__InOperator__OpListAssignment_2_114167);
            ruleOperandList();

            state._fsp--;

             after(grammarAccess.getInOperatorAccess().getOpListOperandListParserRuleCall_2_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InOperator__OpListAssignment_2_1"


    // $ANTLR start "rule__OperandList__EntriesAssignment_1_1_1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6959:1: rule__OperandList__EntriesAssignment_1_1_1 : ( ruleXOperandFragment ) ;
    public final void rule__OperandList__EntriesAssignment_1_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6963:1: ( ( ruleXOperandFragment ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6964:1: ( ruleXOperandFragment )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6964:1: ( ruleXOperandFragment )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6965:1: ruleXOperandFragment
            {
             before(grammarAccess.getOperandListAccess().getEntriesXOperandFragmentParserRuleCall_1_1_1_0()); 
            pushFollow(FOLLOW_ruleXOperandFragment_in_rule__OperandList__EntriesAssignment_1_1_114198);
            ruleXOperandFragment();

            state._fsp--;

             after(grammarAccess.getOperandListAccess().getEntriesXOperandFragmentParserRuleCall_1_1_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OperandList__EntriesAssignment_1_1_1"


    // $ANTLR start "rule__Operand__EntriesAssignment_1_1_1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6974:1: rule__Operand__EntriesAssignment_1_1_1 : ( ruleOperandFragment ) ;
    public final void rule__Operand__EntriesAssignment_1_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6978:1: ( ( ruleOperandFragment ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6979:1: ( ruleOperandFragment )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6979:1: ( ruleOperandFragment )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6980:1: ruleOperandFragment
            {
             before(grammarAccess.getOperandAccess().getEntriesOperandFragmentParserRuleCall_1_1_1_0()); 
            pushFollow(FOLLOW_ruleOperandFragment_in_rule__Operand__EntriesAssignment_1_1_114229);
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


    // $ANTLR start "rule__OperandFragment__ColumnAssignment_0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6989:1: rule__OperandFragment__ColumnAssignment_0 : ( ruleColumnOperand ) ;
    public final void rule__OperandFragment__ColumnAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6993:1: ( ( ruleColumnOperand ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6994:1: ( ruleColumnOperand )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6994:1: ( ruleColumnOperand )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6995:1: ruleColumnOperand
            {
             before(grammarAccess.getOperandFragmentAccess().getColumnColumnOperandParserRuleCall_0_0()); 
            pushFollow(FOLLOW_ruleColumnOperand_in_rule__OperandFragment__ColumnAssignment_014260);
            ruleColumnOperand();

            state._fsp--;

             after(grammarAccess.getOperandFragmentAccess().getColumnColumnOperandParserRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OperandFragment__ColumnAssignment_0"


    // $ANTLR start "rule__OperandFragment__XopAssignment_1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7004:1: rule__OperandFragment__XopAssignment_1 : ( ruleXOperandFragment ) ;
    public final void rule__OperandFragment__XopAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7008:1: ( ( ruleXOperandFragment ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7009:1: ( ruleXOperandFragment )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7009:1: ( ruleXOperandFragment )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7010:1: ruleXOperandFragment
            {
             before(grammarAccess.getOperandFragmentAccess().getXopXOperandFragmentParserRuleCall_1_0()); 
            pushFollow(FOLLOW_ruleXOperandFragment_in_rule__OperandFragment__XopAssignment_114291);
            ruleXOperandFragment();

            state._fsp--;

             after(grammarAccess.getOperandFragmentAccess().getXopXOperandFragmentParserRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OperandFragment__XopAssignment_1"


    // $ANTLR start "rule__OperandFragment__SubqAssignment_2"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7019:1: rule__OperandFragment__SubqAssignment_2 : ( ruleSubQueryOperand ) ;
    public final void rule__OperandFragment__SubqAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7023:1: ( ( ruleSubQueryOperand ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7024:1: ( ruleSubQueryOperand )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7024:1: ( ruleSubQueryOperand )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7025:1: ruleSubQueryOperand
            {
             before(grammarAccess.getOperandFragmentAccess().getSubqSubQueryOperandParserRuleCall_2_0()); 
            pushFollow(FOLLOW_ruleSubQueryOperand_in_rule__OperandFragment__SubqAssignment_214322);
            ruleSubQueryOperand();

            state._fsp--;

             after(grammarAccess.getOperandFragmentAccess().getSubqSubQueryOperandParserRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OperandFragment__SubqAssignment_2"


    // $ANTLR start "rule__XOperandFragment__ParamAssignment_0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7034:1: rule__XOperandFragment__ParamAssignment_0 : ( ruleParameterOperand ) ;
    public final void rule__XOperandFragment__ParamAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7038:1: ( ( ruleParameterOperand ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7039:1: ( ruleParameterOperand )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7039:1: ( ruleParameterOperand )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7040:1: ruleParameterOperand
            {
             before(grammarAccess.getXOperandFragmentAccess().getParamParameterOperandParserRuleCall_0_0()); 
            pushFollow(FOLLOW_ruleParameterOperand_in_rule__XOperandFragment__ParamAssignment_014353);
            ruleParameterOperand();

            state._fsp--;

             after(grammarAccess.getXOperandFragmentAccess().getParamParameterOperandParserRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XOperandFragment__ParamAssignment_0"


    // $ANTLR start "rule__XOperandFragment__EparamAssignment_1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7049:1: rule__XOperandFragment__EparamAssignment_1 : ( ruleExclamationParameterOperand ) ;
    public final void rule__XOperandFragment__EparamAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7053:1: ( ( ruleExclamationParameterOperand ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7054:1: ( ruleExclamationParameterOperand )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7054:1: ( ruleExclamationParameterOperand )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7055:1: ruleExclamationParameterOperand
            {
             before(grammarAccess.getXOperandFragmentAccess().getEparamExclamationParameterOperandParserRuleCall_1_0()); 
            pushFollow(FOLLOW_ruleExclamationParameterOperand_in_rule__XOperandFragment__EparamAssignment_114384);
            ruleExclamationParameterOperand();

            state._fsp--;

             after(grammarAccess.getXOperandFragmentAccess().getEparamExclamationParameterOperandParserRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XOperandFragment__EparamAssignment_1"


    // $ANTLR start "rule__XOperandFragment__ScalarAssignment_2"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7064:1: rule__XOperandFragment__ScalarAssignment_2 : ( ruleScalarOperand ) ;
    public final void rule__XOperandFragment__ScalarAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7068:1: ( ( ruleScalarOperand ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7069:1: ( ruleScalarOperand )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7069:1: ( ruleScalarOperand )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7070:1: ruleScalarOperand
            {
             before(grammarAccess.getXOperandFragmentAccess().getScalarScalarOperandParserRuleCall_2_0()); 
            pushFollow(FOLLOW_ruleScalarOperand_in_rule__XOperandFragment__ScalarAssignment_214415);
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


    // $ANTLR start "rule__ParameterOperand__PrmAssignment_2"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7079:1: rule__ParameterOperand__PrmAssignment_2 : ( RULE_ID ) ;
    public final void rule__ParameterOperand__PrmAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7083:1: ( ( RULE_ID ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7084:1: ( RULE_ID )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7084:1: ( RULE_ID )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7085:1: RULE_ID
            {
             before(grammarAccess.getParameterOperandAccess().getPrmIDTerminalRuleCall_2_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__ParameterOperand__PrmAssignment_214446); 
             after(grammarAccess.getParameterOperandAccess().getPrmIDTerminalRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ParameterOperand__PrmAssignment_2"


    // $ANTLR start "rule__ExclamationParameterOperand__PrmAssignment_2"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7094:1: rule__ExclamationParameterOperand__PrmAssignment_2 : ( RULE_ID ) ;
    public final void rule__ExclamationParameterOperand__PrmAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7098:1: ( ( RULE_ID ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7099:1: ( RULE_ID )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7099:1: ( RULE_ID )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7100:1: RULE_ID
            {
             before(grammarAccess.getExclamationParameterOperandAccess().getPrmIDTerminalRuleCall_2_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__ExclamationParameterOperand__PrmAssignment_214477); 
             after(grammarAccess.getExclamationParameterOperandAccess().getPrmIDTerminalRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ExclamationParameterOperand__PrmAssignment_2"


    // $ANTLR start "rule__ColumnOperand__CfullAssignment"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7109:1: rule__ColumnOperand__CfullAssignment : ( ruleColumnFull ) ;
    public final void rule__ColumnOperand__CfullAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7113:1: ( ( ruleColumnFull ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7114:1: ( ruleColumnFull )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7114:1: ( ruleColumnFull )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7115:1: ruleColumnFull
            {
             before(grammarAccess.getColumnOperandAccess().getCfullColumnFullParserRuleCall_0()); 
            pushFollow(FOLLOW_ruleColumnFull_in_rule__ColumnOperand__CfullAssignment14508);
            ruleColumnFull();

            state._fsp--;

             after(grammarAccess.getColumnOperandAccess().getCfullColumnFullParserRuleCall_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnOperand__CfullAssignment"


    // $ANTLR start "rule__SubQueryOperand__SelAssignment_2"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7124:1: rule__SubQueryOperand__SelAssignment_2 : ( ruleSelect ) ;
    public final void rule__SubQueryOperand__SelAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7128:1: ( ( ruleSelect ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7129:1: ( ruleSelect )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7129:1: ( ruleSelect )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7130:1: ruleSelect
            {
             before(grammarAccess.getSubQueryOperandAccess().getSelSelectParserRuleCall_2_0()); 
            pushFollow(FOLLOW_ruleSelect_in_rule__SubQueryOperand__SelAssignment_214539);
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


    // $ANTLR start "rule__ScalarOperand__SointAssignment_0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7139:1: rule__ScalarOperand__SointAssignment_0 : ( RULE_SIGNED_INT ) ;
    public final void rule__ScalarOperand__SointAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7143:1: ( ( RULE_SIGNED_INT ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7144:1: ( RULE_SIGNED_INT )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7144:1: ( RULE_SIGNED_INT )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7145:1: RULE_SIGNED_INT
            {
             before(grammarAccess.getScalarOperandAccess().getSointSIGNED_INTTerminalRuleCall_0_0()); 
            match(input,RULE_SIGNED_INT,FOLLOW_RULE_SIGNED_INT_in_rule__ScalarOperand__SointAssignment_014570); 
             after(grammarAccess.getScalarOperandAccess().getSointSIGNED_INTTerminalRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ScalarOperand__SointAssignment_0"


    // $ANTLR start "rule__ScalarOperand__SostrAssignment_1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7154:1: rule__ScalarOperand__SostrAssignment_1 : ( ruleStringOperand ) ;
    public final void rule__ScalarOperand__SostrAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7158:1: ( ( ruleStringOperand ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7159:1: ( ruleStringOperand )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7159:1: ( ruleStringOperand )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7160:1: ruleStringOperand
            {
             before(grammarAccess.getScalarOperandAccess().getSostrStringOperandParserRuleCall_1_0()); 
            pushFollow(FOLLOW_ruleStringOperand_in_rule__ScalarOperand__SostrAssignment_114601);
            ruleStringOperand();

            state._fsp--;

             after(grammarAccess.getScalarOperandAccess().getSostrStringOperandParserRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ScalarOperand__SostrAssignment_1"


    // $ANTLR start "rule__ScalarOperand__SodblAssignment_2"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7169:1: rule__ScalarOperand__SodblAssignment_2 : ( RULE_SIGNED_DOUBLE ) ;
    public final void rule__ScalarOperand__SodblAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7173:1: ( ( RULE_SIGNED_DOUBLE ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7174:1: ( RULE_SIGNED_DOUBLE )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7174:1: ( RULE_SIGNED_DOUBLE )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7175:1: RULE_SIGNED_DOUBLE
            {
             before(grammarAccess.getScalarOperandAccess().getSodblSIGNED_DOUBLETerminalRuleCall_2_0()); 
            match(input,RULE_SIGNED_DOUBLE,FOLLOW_RULE_SIGNED_DOUBLE_in_rule__ScalarOperand__SodblAssignment_214632); 
             after(grammarAccess.getScalarOperandAccess().getSodblSIGNED_DOUBLETerminalRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ScalarOperand__SodblAssignment_2"


    // $ANTLR start "rule__ScalarOperand__SodateAssignment_3"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7184:1: rule__ScalarOperand__SodateAssignment_3 : ( RULE_DATE ) ;
    public final void rule__ScalarOperand__SodateAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7188:1: ( ( RULE_DATE ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7189:1: ( RULE_DATE )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7189:1: ( RULE_DATE )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7190:1: RULE_DATE
            {
             before(grammarAccess.getScalarOperandAccess().getSodateDATETerminalRuleCall_3_0()); 
            match(input,RULE_DATE,FOLLOW_RULE_DATE_in_rule__ScalarOperand__SodateAssignment_314663); 
             after(grammarAccess.getScalarOperandAccess().getSodateDATETerminalRuleCall_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ScalarOperand__SodateAssignment_3"


    // $ANTLR start "rule__ScalarOperand__SotimeAssignment_4"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7199:1: rule__ScalarOperand__SotimeAssignment_4 : ( RULE_TIME ) ;
    public final void rule__ScalarOperand__SotimeAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7203:1: ( ( RULE_TIME ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7204:1: ( RULE_TIME )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7204:1: ( RULE_TIME )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7205:1: RULE_TIME
            {
             before(grammarAccess.getScalarOperandAccess().getSotimeTIMETerminalRuleCall_4_0()); 
            match(input,RULE_TIME,FOLLOW_RULE_TIME_in_rule__ScalarOperand__SotimeAssignment_414694); 
             after(grammarAccess.getScalarOperandAccess().getSotimeTIMETerminalRuleCall_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ScalarOperand__SotimeAssignment_4"


    // $ANTLR start "rule__ScalarOperand__SodtAssignment_5"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7214:1: rule__ScalarOperand__SodtAssignment_5 : ( RULE_TIMESTAMP ) ;
    public final void rule__ScalarOperand__SodtAssignment_5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7218:1: ( ( RULE_TIMESTAMP ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7219:1: ( RULE_TIMESTAMP )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7219:1: ( RULE_TIMESTAMP )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7220:1: RULE_TIMESTAMP
            {
             before(grammarAccess.getScalarOperandAccess().getSodtTIMESTAMPTerminalRuleCall_5_0()); 
            match(input,RULE_TIMESTAMP,FOLLOW_RULE_TIMESTAMP_in_rule__ScalarOperand__SodtAssignment_514725); 
             after(grammarAccess.getScalarOperandAccess().getSodtTIMESTAMPTerminalRuleCall_5_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ScalarOperand__SodtAssignment_5"

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
    public static final BitSet FOLLOW_ruleExpressionFragmentSecond_in_entryRuleExpressionFragmentSecond950 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpressionFragmentSecond957 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ExpressionFragmentSecond__Group__0_in_ruleExpressionFragmentSecond987 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpressionFragment_in_entryRuleExpressionFragment1014 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpressionFragment1021 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ExpressionFragment__Alternatives_in_ruleExpressionFragment1051 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpressionGroup_in_entryRuleExpressionGroup1078 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpressionGroup1085 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ExpressionGroup__Group__0_in_ruleExpressionGroup1115 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXExpression_in_entryRuleXExpression1142 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleXExpression1149 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__XExpression__Group__0_in_ruleXExpression1179 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXExpressionParams_in_entryRuleXExpressionParams1206 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleXExpressionParams1213 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__XExpressionParams__Group__0_in_ruleXExpressionParams1243 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleJRParameter_in_entryRuleJRParameter1270 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleJRParameter1277 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__JRParameter__JrprmAssignment_in_ruleJRParameter1307 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression_in_entryRuleExpression1334 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpression1341 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Expression__Group__0_in_ruleExpression1371 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleComparison_in_entryRuleComparison1398 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleComparison1405 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Comparison__Group__0_in_ruleComparison1435 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLike_in_entryRuleLike1462 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleLike1469 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Like__Group__0_in_ruleLike1499 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBetween_in_entryRuleBetween1526 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleBetween1533 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Between__Group__0_in_ruleBetween1563 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInOperator_in_entryRuleInOperator1590 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleInOperator1597 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__InOperator__Group__0_in_ruleInOperator1627 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperandList_in_entryRuleOperandList1654 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOperandList1661 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OperandList__Group__0_in_ruleOperandList1691 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperand_in_entryRuleOperand1718 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOperand1725 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Operand__Group__0_in_ruleOperand1755 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperandFragment_in_entryRuleOperandFragment1782 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOperandFragment1789 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OperandFragment__Alternatives_in_ruleOperandFragment1819 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXOperandFragment_in_entryRuleXOperandFragment1846 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleXOperandFragment1853 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__XOperandFragment__Alternatives_in_ruleXOperandFragment1883 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleParameterOperand_in_entryRuleParameterOperand1910 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleParameterOperand1917 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ParameterOperand__Group__0_in_ruleParameterOperand1947 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExclamationParameterOperand_in_entryRuleExclamationParameterOperand1974 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExclamationParameterOperand1981 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ExclamationParameterOperand__Group__0_in_ruleExclamationParameterOperand2011 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnOperand_in_entryRuleColumnOperand2038 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleColumnOperand2045 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ColumnOperand__CfullAssignment_in_ruleColumnOperand2075 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSubQueryOperand_in_entryRuleSubQueryOperand2102 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSubQueryOperand2109 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SubQueryOperand__Group__0_in_ruleSubQueryOperand2139 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleScalarOperand_in_entryRuleScalarOperand2166 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleScalarOperand2173 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ScalarOperand__Alternatives_in_ruleScalarOperand2203 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleStringOperand_in_entryRuleStringOperand2230 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleStringOperand2237 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleStringOperand2267 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__XFunction__Alternatives_in_ruleXFunction2303 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__JoinType__Alternatives_in_ruleJoinType2339 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ColumnOrAlias__Group_0__0_in_rule__ColumnOrAlias__Alternatives2374 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ColumnOrAlias__AllColsAssignment_1_in_rule__ColumnOrAlias__Alternatives2392 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_23_in_rule__OrderByColumnFull__DirectionAlternatives_1_02426 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_25_in_rule__OrderByColumnFull__DirectionAlternatives_1_02446 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_22_in_rule__ExpressionFragmentSecond__CAlternatives_0_02481 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_18_in_rule__ExpressionFragmentSecond__CAlternatives_0_02501 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ExpressionFragment__ExpgroupAssignment_0_in_rule__ExpressionFragment__Alternatives2535 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ExpressionFragment__ExpAssignment_1_in_rule__ExpressionFragment__Alternatives2553 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ExpressionFragment__XexpAssignment_2_in_rule__ExpressionFragment__Alternatives2571 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Expression__IsnullAssignment_1_0_in_rule__Expression__Alternatives_12604 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Expression__InAssignment_1_1_in_rule__Expression__Alternatives_12622 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Expression__BetweenAssignment_1_2_in_rule__Expression__Alternatives_12640 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Expression__LikeAssignment_1_3_in_rule__Expression__Alternatives_12658 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Expression__CompAssignment_1_4_in_rule__Expression__Alternatives_12676 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_39_in_rule__Expression__IsnullAlternatives_1_0_02710 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_52_in_rule__Expression__IsnullAlternatives_1_0_02730 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_10_in_rule__Comparison__OperatorAlternatives_0_02765 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_14_in_rule__Comparison__OperatorAlternatives_0_02785 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_8_in_rule__Comparison__OperatorAlternatives_0_02805 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_12_in_rule__Comparison__OperatorAlternatives_0_02825 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_9_in_rule__Comparison__OperatorAlternatives_0_02845 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_13_in_rule__Comparison__OperatorAlternatives_0_02865 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_29_in_rule__Like__OpLikeAlternatives_0_02900 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_44_in_rule__Like__OpLikeAlternatives_0_02920 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_37_in_rule__Between__OpBetweenAlternatives_0_02955 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_53_in_rule__Between__OpBetweenAlternatives_0_02975 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_43_in_rule__InOperator__OpAlternatives_1_03010 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_27_in_rule__InOperator__OpAlternatives_1_03030 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__InOperator__SubqueryAssignment_2_0_in_rule__InOperator__Alternatives_23064 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__InOperator__OpListAssignment_2_1_in_rule__InOperator__Alternatives_23082 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_3_in_rule__Operand__Alternatives_1_1_03116 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_5_in_rule__Operand__Alternatives_1_1_03136 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STAR_in_rule__Operand__Alternatives_1_1_03155 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_7_in_rule__Operand__Alternatives_1_1_03173 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_19_in_rule__Operand__Alternatives_1_1_03193 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OperandFragment__ColumnAssignment_0_in_rule__OperandFragment__Alternatives3227 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OperandFragment__XopAssignment_1_in_rule__OperandFragment__Alternatives3245 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OperandFragment__SubqAssignment_2_in_rule__OperandFragment__Alternatives3263 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__XOperandFragment__ParamAssignment_0_in_rule__XOperandFragment__Alternatives3296 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__XOperandFragment__EparamAssignment_1_in_rule__XOperandFragment__Alternatives3314 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__XOperandFragment__ScalarAssignment_2_in_rule__XOperandFragment__Alternatives3332 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ScalarOperand__SointAssignment_0_in_rule__ScalarOperand__Alternatives3365 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ScalarOperand__SostrAssignment_1_in_rule__ScalarOperand__Alternatives3383 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ScalarOperand__SodblAssignment_2_in_rule__ScalarOperand__Alternatives3401 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ScalarOperand__SodateAssignment_3_in_rule__ScalarOperand__Alternatives3419 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ScalarOperand__SotimeAssignment_4_in_rule__ScalarOperand__Alternatives3437 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ScalarOperand__SodtAssignment_5_in_rule__ScalarOperand__Alternatives3455 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_16_in_rule__XFunction__Alternatives3489 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_32_in_rule__XFunction__Alternatives3509 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_30_in_rule__XFunction__Alternatives3529 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_45_in_rule__XFunction__Alternatives3549 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_28_in_rule__XFunction__Alternatives3569 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_38_in_rule__XFunction__Alternatives3589 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_31_in_rule__XFunction__Alternatives3609 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_48_in_rule__XFunction__Alternatives3629 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_37_in_rule__XFunction__Alternatives3649 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_49_in_rule__XFunction__Alternatives3669 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_47_in_rule__XFunction__Alternatives3689 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_40_in_rule__XFunction__Alternatives3709 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_51_in_rule__JoinType__Alternatives3744 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_55_in_rule__JoinType__Alternatives3764 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_56_in_rule__JoinType__Alternatives3784 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_54_in_rule__JoinType__Alternatives3804 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_50_in_rule__JoinType__Alternatives3824 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Model__Group__0__Impl_in_rule__Model__Group__03856 = new BitSet(new long[]{0x0000000020040000L});
    public static final BitSet FOLLOW_rule__Model__Group__1_in_rule__Model__Group__03859 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSelect_in_rule__Model__Group__0__Impl3886 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Model__Group__1__Impl_in_rule__Model__Group__13915 = new BitSet(new long[]{0x0000000020040000L});
    public static final BitSet FOLLOW_rule__Model__Group__2_in_rule__Model__Group__13918 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Model__Group_1__0_in_rule__Model__Group__1__Impl3945 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Model__Group__2__Impl_in_rule__Model__Group__23976 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Model__Group_2__0_in_rule__Model__Group__2__Impl4003 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Model__Group_1__0__Impl_in_rule__Model__Group_1__04040 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_rule__Model__Group_1__1_in_rule__Model__Group_1__04043 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Model__Group_1__1__Impl_in_rule__Model__Group_1__14101 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Model__Group_1_1__0_in_rule__Model__Group_1__1__Impl4130 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_rule__Model__Group_1_1__0_in_rule__Model__Group_1__1__Impl4142 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_rule__Model__Group_1_1__0__Impl_in_rule__Model__Group_1_1__04179 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_rule__Model__Group_1_1__1_in_rule__Model__Group_1_1__04182 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_33_in_rule__Model__Group_1_1__0__Impl4210 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Model__Group_1_1__1__Impl_in_rule__Model__Group_1_1__14241 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Model__EntriesAssignment_1_1_1_in_rule__Model__Group_1_1__1__Impl4268 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Model__Group_2__0__Impl_in_rule__Model__Group_2__04302 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_rule__Model__Group_2__1_in_rule__Model__Group_2__04305 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_46_in_rule__Model__Group_2__0__Impl4333 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Model__Group_2__1__Impl_in_rule__Model__Group_2__14364 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Model__OrderByEntryAssignment_2_1_in_rule__Model__Group_2__1__Impl4391 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Select__Group__0__Impl_in_rule__Select__Group__04425 = new BitSet(new long[]{0x1000000000002000L,0x0000000000000008L});
    public static final BitSet FOLLOW_rule__Select__Group__1_in_rule__Select__Group__04428 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Select__SelectAssignment_0_in_rule__Select__Group__0__Impl4455 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Select__Group__1__Impl_in_rule__Select__Group__14485 = new BitSet(new long[]{0x1000000000002000L,0x0000000000000008L});
    public static final BitSet FOLLOW_rule__Select__Group__2_in_rule__Select__Group__14488 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_41_in_rule__Select__Group__1__Impl4517 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Select__Group__2__Impl_in_rule__Select__Group__24550 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_rule__Select__Group__3_in_rule__Select__Group__24553 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Select__ColsAssignment_2_in_rule__Select__Group__2__Impl4580 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Select__Group__3__Impl_in_rule__Select__Group__34610 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_rule__Select__Group__4_in_rule__Select__Group__34613 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_26_in_rule__Select__Group__3__Impl4641 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Select__Group__4__Impl_in_rule__Select__Group__44672 = new BitSet(new long[]{0x0000000041004000L});
    public static final BitSet FOLLOW_rule__Select__Group__5_in_rule__Select__Group__44675 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Select__TblAssignment_4_in_rule__Select__Group__4__Impl4702 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Select__Group__5__Impl_in_rule__Select__Group__54732 = new BitSet(new long[]{0x0000000041004000L});
    public static final BitSet FOLLOW_rule__Select__Group__6_in_rule__Select__Group__54735 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Select__Group_5__0_in_rule__Select__Group__5__Impl4762 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Select__Group__6__Impl_in_rule__Select__Group__64793 = new BitSet(new long[]{0x0000000041004000L});
    public static final BitSet FOLLOW_rule__Select__Group__7_in_rule__Select__Group__64796 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Select__Group_6__0_in_rule__Select__Group__6__Impl4823 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Select__Group__7__Impl_in_rule__Select__Group__74854 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Select__Group_7__0_in_rule__Select__Group__7__Impl4881 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Select__Group_5__0__Impl_in_rule__Select__Group_5__04928 = new BitSet(new long[]{0xE002006080000000L,0x000000000000002BL});
    public static final BitSet FOLLOW_rule__Select__Group_5__1_in_rule__Select__Group_5__04931 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_34_in_rule__Select__Group_5__0__Impl4959 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Select__Group_5__1__Impl_in_rule__Select__Group_5__14990 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Select__WhereExpressionAssignment_5_1_in_rule__Select__Group_5__1__Impl5017 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Select__Group_6__0__Impl_in_rule__Select__Group_6__05051 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_rule__Select__Group_6__1_in_rule__Select__Group_6__05054 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_42_in_rule__Select__Group_6__0__Impl5082 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Select__Group_6__1__Impl_in_rule__Select__Group_6__15113 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Select__GroupByEntryAssignment_6_1_in_rule__Select__Group_6__1__Impl5140 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Select__Group_7__0__Impl_in_rule__Select__Group_7__05174 = new BitSet(new long[]{0xE002006080000000L,0x000000000000002BL});
    public static final BitSet FOLLOW_rule__Select__Group_7__1_in_rule__Select__Group_7__05177 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_35_in_rule__Select__Group_7__0__Impl5205 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Select__Group_7__1__Impl_in_rule__Select__Group_7__15236 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Select__HavingEntryAssignment_7_1_in_rule__Select__Group_7__1__Impl5263 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Columns__Group__0__Impl_in_rule__Columns__Group__05297 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_rule__Columns__Group__1_in_rule__Columns__Group__05300 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnOrAlias_in_rule__Columns__Group__0__Impl5327 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Columns__Group__1__Impl_in_rule__Columns__Group__15356 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Columns__Group_1__0_in_rule__Columns__Group__1__Impl5383 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Columns__Group_1__0__Impl_in_rule__Columns__Group_1__05418 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_rule__Columns__Group_1__1_in_rule__Columns__Group_1__05421 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Columns__Group_1__1__Impl_in_rule__Columns__Group_1__15479 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Columns__Group_1_1__0_in_rule__Columns__Group_1__1__Impl5508 = new BitSet(new long[]{0x0010000000000002L});
    public static final BitSet FOLLOW_rule__Columns__Group_1_1__0_in_rule__Columns__Group_1__1__Impl5520 = new BitSet(new long[]{0x0010000000000002L});
    public static final BitSet FOLLOW_rule__Columns__Group_1_1__0__Impl_in_rule__Columns__Group_1_1__05557 = new BitSet(new long[]{0x1000000000002000L,0x0000000000000008L});
    public static final BitSet FOLLOW_rule__Columns__Group_1_1__1_in_rule__Columns__Group_1_1__05560 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_4_in_rule__Columns__Group_1_1__0__Impl5588 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Columns__Group_1_1__1__Impl_in_rule__Columns__Group_1_1__15619 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Columns__EntriesAssignment_1_1_1_in_rule__Columns__Group_1_1__1__Impl5646 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ColumnOrAlias__Group_0__0__Impl_in_rule__ColumnOrAlias__Group_0__05680 = new BitSet(new long[]{0x0000100000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_rule__ColumnOrAlias__Group_0__1_in_rule__ColumnOrAlias__Group_0__05683 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ColumnOrAlias__CfullAssignment_0_0_in_rule__ColumnOrAlias__Group_0__0__Impl5710 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ColumnOrAlias__Group_0__1__Impl_in_rule__ColumnOrAlias__Group_0__15740 = new BitSet(new long[]{0x0000100000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_rule__ColumnOrAlias__Group_0__2_in_rule__ColumnOrAlias__Group_0__15743 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ColumnOrAlias__AliasAssignment_0_1_in_rule__ColumnOrAlias__Group_0__1__Impl5770 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ColumnOrAlias__Group_0__2__Impl_in_rule__ColumnOrAlias__Group_0__25801 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ColumnOrAlias__ColAliasAssignment_0_2_in_rule__ColumnOrAlias__Group_0__2__Impl5828 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ColumnFull__Group__0__Impl_in_rule__ColumnFull__Group__05865 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_rule__ColumnFull__Group__1_in_rule__ColumnFull__Group__05868 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDbObjectName_in_rule__ColumnFull__Group__0__Impl5895 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ColumnFull__Group__1__Impl_in_rule__ColumnFull__Group__15924 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ColumnFull__Group_1__0_in_rule__ColumnFull__Group__1__Impl5951 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ColumnFull__Group_1__0__Impl_in_rule__ColumnFull__Group_1__05986 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_rule__ColumnFull__Group_1__1_in_rule__ColumnFull__Group_1__05989 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ColumnFull__Group_1__1__Impl_in_rule__ColumnFull__Group_1__16047 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ColumnFull__Group_1_1__0_in_rule__ColumnFull__Group_1__1__Impl6076 = new BitSet(new long[]{0x0040000000000002L});
    public static final BitSet FOLLOW_rule__ColumnFull__Group_1_1__0_in_rule__ColumnFull__Group_1__1__Impl6088 = new BitSet(new long[]{0x0040000000000002L});
    public static final BitSet FOLLOW_rule__ColumnFull__Group_1_1__0__Impl_in_rule__ColumnFull__Group_1_1__06125 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_rule__ColumnFull__Group_1_1__1_in_rule__ColumnFull__Group_1_1__06128 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_6_in_rule__ColumnFull__Group_1_1__0__Impl6156 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ColumnFull__Group_1_1__1__Impl_in_rule__ColumnFull__Group_1_1__16187 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ColumnFull__EntriesAssignment_1_1_1_in_rule__ColumnFull__Group_1_1__1__Impl6214 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Tables__Group__0__Impl_in_rule__Tables__Group__06248 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_rule__Tables__Group__1_in_rule__Tables__Group__06251 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFromTable_in_rule__Tables__Group__0__Impl6278 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Tables__Group__1__Impl_in_rule__Tables__Group__16307 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Tables__Group_1__0_in_rule__Tables__Group__1__Impl6334 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Tables__Group_1__0__Impl_in_rule__Tables__Group_1__06369 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_rule__Tables__Group_1__1_in_rule__Tables__Group_1__06372 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Tables__Group_1__1__Impl_in_rule__Tables__Group_1__16430 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Tables__Group_1_1__0_in_rule__Tables__Group_1__1__Impl6459 = new BitSet(new long[]{0x0010000000000002L});
    public static final BitSet FOLLOW_rule__Tables__Group_1_1__0_in_rule__Tables__Group_1__1__Impl6471 = new BitSet(new long[]{0x0010000000000002L});
    public static final BitSet FOLLOW_rule__Tables__Group_1_1__0__Impl_in_rule__Tables__Group_1_1__06508 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_rule__Tables__Group_1_1__1_in_rule__Tables__Group_1_1__06511 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_4_in_rule__Tables__Group_1_1__0__Impl6539 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Tables__Group_1_1__1__Impl_in_rule__Tables__Group_1_1__16570 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Tables__EntriesAssignment_1_1_1_in_rule__Tables__Group_1_1__1__Impl6597 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FromTable__Group__0__Impl_in_rule__FromTable__Group__06631 = new BitSet(new long[]{0x0000000000000670L});
    public static final BitSet FOLLOW_rule__FromTable__Group__1_in_rule__FromTable__Group__06634 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FromTable__TableAssignment_0_in_rule__FromTable__Group__0__Impl6661 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FromTable__Group__1__Impl_in_rule__FromTable__Group__16691 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FromTable__Group_1__0_in_rule__FromTable__Group__1__Impl6718 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FromTable__Group_1__0__Impl_in_rule__FromTable__Group_1__06753 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_rule__FromTable__Group_1__1_in_rule__FromTable__Group_1__06756 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FromTable__JoinAssignment_1_0_in_rule__FromTable__Group_1__0__Impl6783 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FromTable__Group_1__1__Impl_in_rule__FromTable__Group_1__16813 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_rule__FromTable__Group_1__2_in_rule__FromTable__Group_1__16816 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FromTable__OnTableAssignment_1_1_in_rule__FromTable__Group_1__1__Impl6843 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FromTable__Group_1__2__Impl_in_rule__FromTable__Group_1__26873 = new BitSet(new long[]{0xE002006080000000L,0x000000000000002BL});
    public static final BitSet FOLLOW_rule__FromTable__Group_1__3_in_rule__FromTable__Group_1__26876 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_17_in_rule__FromTable__Group_1__2__Impl6904 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FromTable__Group_1__3__Impl_in_rule__FromTable__Group_1__36935 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FromTable__JoinExprAssignment_1_3_in_rule__FromTable__Group_1__3__Impl6962 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TableOrAlias__Group__0__Impl_in_rule__TableOrAlias__Group__07000 = new BitSet(new long[]{0x0000100000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_rule__TableOrAlias__Group__1_in_rule__TableOrAlias__Group__07003 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TableOrAlias__TfullAssignment_0_in_rule__TableOrAlias__Group__0__Impl7030 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TableOrAlias__Group__1__Impl_in_rule__TableOrAlias__Group__17060 = new BitSet(new long[]{0x0000100000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_rule__TableOrAlias__Group__2_in_rule__TableOrAlias__Group__17063 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TableOrAlias__AliasAssignment_1_in_rule__TableOrAlias__Group__1__Impl7090 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TableOrAlias__Group__2__Impl_in_rule__TableOrAlias__Group__27121 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TableOrAlias__TblAliasAssignment_2_in_rule__TableOrAlias__Group__2__Impl7148 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TableFull__Group__0__Impl_in_rule__TableFull__Group__07185 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_rule__TableFull__Group__1_in_rule__TableFull__Group__07188 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDbObjectName_in_rule__TableFull__Group__0__Impl7215 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TableFull__Group__1__Impl_in_rule__TableFull__Group__17244 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TableFull__Group_1__0_in_rule__TableFull__Group__1__Impl7271 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TableFull__Group_1__0__Impl_in_rule__TableFull__Group_1__07306 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_rule__TableFull__Group_1__1_in_rule__TableFull__Group_1__07309 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TableFull__Group_1__1__Impl_in_rule__TableFull__Group_1__17367 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TableFull__Group_1_1__0_in_rule__TableFull__Group_1__1__Impl7396 = new BitSet(new long[]{0x0040000000000002L});
    public static final BitSet FOLLOW_rule__TableFull__Group_1_1__0_in_rule__TableFull__Group_1__1__Impl7408 = new BitSet(new long[]{0x0040000000000002L});
    public static final BitSet FOLLOW_rule__TableFull__Group_1_1__0__Impl_in_rule__TableFull__Group_1_1__07445 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_rule__TableFull__Group_1_1__1_in_rule__TableFull__Group_1_1__07448 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_6_in_rule__TableFull__Group_1_1__0__Impl7476 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TableFull__Group_1_1__1__Impl_in_rule__TableFull__Group_1_1__17507 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TableFull__EntriesAssignment_1_1_1_in_rule__TableFull__Group_1_1__1__Impl7534 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OrderByColumns__Group__0__Impl_in_rule__OrderByColumns__Group__07568 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_rule__OrderByColumns__Group__1_in_rule__OrderByColumns__Group__07571 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOrderByColumnFull_in_rule__OrderByColumns__Group__0__Impl7598 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OrderByColumns__Group__1__Impl_in_rule__OrderByColumns__Group__17627 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OrderByColumns__Group_1__0_in_rule__OrderByColumns__Group__1__Impl7654 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OrderByColumns__Group_1__0__Impl_in_rule__OrderByColumns__Group_1__07689 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_rule__OrderByColumns__Group_1__1_in_rule__OrderByColumns__Group_1__07692 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OrderByColumns__Group_1__1__Impl_in_rule__OrderByColumns__Group_1__17750 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OrderByColumns__Group_1_1__0_in_rule__OrderByColumns__Group_1__1__Impl7779 = new BitSet(new long[]{0x0010000000000002L});
    public static final BitSet FOLLOW_rule__OrderByColumns__Group_1_1__0_in_rule__OrderByColumns__Group_1__1__Impl7791 = new BitSet(new long[]{0x0010000000000002L});
    public static final BitSet FOLLOW_rule__OrderByColumns__Group_1_1__0__Impl_in_rule__OrderByColumns__Group_1_1__07828 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_rule__OrderByColumns__Group_1_1__1_in_rule__OrderByColumns__Group_1_1__07831 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_4_in_rule__OrderByColumns__Group_1_1__0__Impl7859 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OrderByColumns__Group_1_1__1__Impl_in_rule__OrderByColumns__Group_1_1__17890 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OrderByColumns__EntriesAssignment_1_1_1_in_rule__OrderByColumns__Group_1_1__1__Impl7917 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OrderByColumnFull__Group__0__Impl_in_rule__OrderByColumnFull__Group__07951 = new BitSet(new long[]{0x0000010100000000L});
    public static final BitSet FOLLOW_rule__OrderByColumnFull__Group__1_in_rule__OrderByColumnFull__Group__07954 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OrderByColumnFull__ColOrderAssignment_0_in_rule__OrderByColumnFull__Group__0__Impl7981 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OrderByColumnFull__Group__1__Impl_in_rule__OrderByColumnFull__Group__18011 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OrderByColumnFull__DirectionAssignment_1_in_rule__OrderByColumnFull__Group__1__Impl8038 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__GroupByColumns__Group__0__Impl_in_rule__GroupByColumns__Group__08073 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_rule__GroupByColumns__Group__1_in_rule__GroupByColumns__Group__08076 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnFull_in_rule__GroupByColumns__Group__0__Impl8103 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__GroupByColumns__Group__1__Impl_in_rule__GroupByColumns__Group__18132 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__GroupByColumns__Group_1__0_in_rule__GroupByColumns__Group__1__Impl8159 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__GroupByColumns__Group_1__0__Impl_in_rule__GroupByColumns__Group_1__08194 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_rule__GroupByColumns__Group_1__1_in_rule__GroupByColumns__Group_1__08197 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__GroupByColumns__Group_1__1__Impl_in_rule__GroupByColumns__Group_1__18255 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__GroupByColumns__Group_1_1__0_in_rule__GroupByColumns__Group_1__1__Impl8284 = new BitSet(new long[]{0x0010000000000002L});
    public static final BitSet FOLLOW_rule__GroupByColumns__Group_1_1__0_in_rule__GroupByColumns__Group_1__1__Impl8296 = new BitSet(new long[]{0x0010000000000002L});
    public static final BitSet FOLLOW_rule__GroupByColumns__Group_1_1__0__Impl_in_rule__GroupByColumns__Group_1_1__08333 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_rule__GroupByColumns__Group_1_1__1_in_rule__GroupByColumns__Group_1_1__08336 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_4_in_rule__GroupByColumns__Group_1_1__0__Impl8364 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__GroupByColumns__Group_1_1__1__Impl_in_rule__GroupByColumns__Group_1_1__18395 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__GroupByColumns__EntriesAssignment_1_1_1_in_rule__GroupByColumns__Group_1_1__1__Impl8422 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FullExpression__Group__0__Impl_in_rule__FullExpression__Group__08456 = new BitSet(new long[]{0x0000808000000000L});
    public static final BitSet FOLLOW_rule__FullExpression__Group__1_in_rule__FullExpression__Group__08459 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpressionFragment_in_rule__FullExpression__Group__0__Impl8486 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FullExpression__Group__1__Impl_in_rule__FullExpression__Group__18515 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FullExpression__Group_1__0_in_rule__FullExpression__Group__1__Impl8542 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FullExpression__Group_1__0__Impl_in_rule__FullExpression__Group_1__08577 = new BitSet(new long[]{0x0000808000000000L});
    public static final BitSet FOLLOW_rule__FullExpression__Group_1__1_in_rule__FullExpression__Group_1__08580 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FullExpression__Group_1__1__Impl_in_rule__FullExpression__Group_1__18638 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FullExpression__EntriesAssignment_1_1_in_rule__FullExpression__Group_1__1__Impl8667 = new BitSet(new long[]{0x0000808000000002L});
    public static final BitSet FOLLOW_rule__FullExpression__EntriesAssignment_1_1_in_rule__FullExpression__Group_1__1__Impl8679 = new BitSet(new long[]{0x0000808000000002L});
    public static final BitSet FOLLOW_rule__ExpressionFragmentSecond__Group__0__Impl_in_rule__ExpressionFragmentSecond__Group__08716 = new BitSet(new long[]{0xE002006080000000L,0x000000000000002BL});
    public static final BitSet FOLLOW_rule__ExpressionFragmentSecond__Group__1_in_rule__ExpressionFragmentSecond__Group__08719 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ExpressionFragmentSecond__CAssignment_0_in_rule__ExpressionFragmentSecond__Group__0__Impl8746 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ExpressionFragmentSecond__Group__1__Impl_in_rule__ExpressionFragmentSecond__Group__18776 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ExpressionFragmentSecond__EfragAssignment_1_in_rule__ExpressionFragmentSecond__Group__1__Impl8803 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ExpressionGroup__Group__0__Impl_in_rule__ExpressionGroup__Group__08837 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_rule__ExpressionGroup__Group__1_in_rule__ExpressionGroup__Group__08840 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ExpressionGroup__Group__1__Impl_in_rule__ExpressionGroup__Group__18898 = new BitSet(new long[]{0xE002006080000000L,0x000000000000002BL});
    public static final BitSet FOLLOW_rule__ExpressionGroup__Group__2_in_rule__ExpressionGroup__Group__18901 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_1_in_rule__ExpressionGroup__Group__1__Impl8929 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ExpressionGroup__Group__2__Impl_in_rule__ExpressionGroup__Group__28960 = new BitSet(new long[]{0x0004000000000000L});
    public static final BitSet FOLLOW_rule__ExpressionGroup__Group__3_in_rule__ExpressionGroup__Group__28963 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ExpressionGroup__ExprAssignment_2_in_rule__ExpressionGroup__Group__2__Impl8990 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ExpressionGroup__Group__3__Impl_in_rule__ExpressionGroup__Group__39020 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_2_in_rule__ExpressionGroup__Group__3__Impl9048 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__XExpression__Group__0__Impl_in_rule__XExpression__Group__09087 = new BitSet(new long[]{0xE002006080000000L,0x000000000000002BL});
    public static final BitSet FOLLOW_rule__XExpression__Group__1_in_rule__XExpression__Group__09090 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__XExpression__Group__1__Impl_in_rule__XExpression__Group__19148 = new BitSet(new long[]{0x000020081C7A1800L});
    public static final BitSet FOLLOW_rule__XExpression__Group__2_in_rule__XExpression__Group__19151 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_21_in_rule__XExpression__Group__1__Impl9179 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__XExpression__Group__2__Impl_in_rule__XExpression__Group__29210 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_rule__XExpression__Group__3_in_rule__XExpression__Group__29213 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__XExpression__XfAssignment_2_in_rule__XExpression__Group__2__Impl9240 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__XExpression__Group__3__Impl_in_rule__XExpression__Group__39270 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_rule__XExpression__Group__4_in_rule__XExpression__Group__39273 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_4_in_rule__XExpression__Group__3__Impl9301 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__XExpression__Group__4__Impl_in_rule__XExpression__Group__49332 = new BitSet(new long[]{0x0810000000000000L});
    public static final BitSet FOLLOW_rule__XExpression__Group__5_in_rule__XExpression__Group__49335 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__XExpression__ColAssignment_4_in_rule__XExpression__Group__4__Impl9362 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__XExpression__Group__5__Impl_in_rule__XExpression__Group__59392 = new BitSet(new long[]{0x0810000000000000L});
    public static final BitSet FOLLOW_rule__XExpression__Group__6_in_rule__XExpression__Group__59395 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__XExpression__Group_5__0_in_rule__XExpression__Group__5__Impl9422 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__XExpression__Group__6__Impl_in_rule__XExpression__Group__69453 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_11_in_rule__XExpression__Group__6__Impl9481 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__XExpression__Group_5__0__Impl_in_rule__XExpression__Group_5__09526 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_rule__XExpression__Group_5__1_in_rule__XExpression__Group_5__09529 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_4_in_rule__XExpression__Group_5__0__Impl9557 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__XExpression__Group_5__1__Impl_in_rule__XExpression__Group_5__19588 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__XExpression__PrmAssignment_5_1_in_rule__XExpression__Group_5__1__Impl9615 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__XExpressionParams__Group__0__Impl_in_rule__XExpressionParams__Group__09649 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_rule__XExpressionParams__Group__1_in_rule__XExpressionParams__Group__09652 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleJRParameter_in_rule__XExpressionParams__Group__0__Impl9679 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__XExpressionParams__Group__1__Impl_in_rule__XExpressionParams__Group__19708 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__XExpressionParams__Group_1__0_in_rule__XExpressionParams__Group__1__Impl9735 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__XExpressionParams__Group_1__0__Impl_in_rule__XExpressionParams__Group_1__09770 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_rule__XExpressionParams__Group_1__1_in_rule__XExpressionParams__Group_1__09773 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__XExpressionParams__Group_1__1__Impl_in_rule__XExpressionParams__Group_1__19831 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__XExpressionParams__Group_1_1__0_in_rule__XExpressionParams__Group_1__1__Impl9860 = new BitSet(new long[]{0x0010000000000002L});
    public static final BitSet FOLLOW_rule__XExpressionParams__Group_1_1__0_in_rule__XExpressionParams__Group_1__1__Impl9872 = new BitSet(new long[]{0x0010000000000002L});
    public static final BitSet FOLLOW_rule__XExpressionParams__Group_1_1__0__Impl_in_rule__XExpressionParams__Group_1_1__09909 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_rule__XExpressionParams__Group_1_1__1_in_rule__XExpressionParams__Group_1_1__09912 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_4_in_rule__XExpressionParams__Group_1_1__0__Impl9940 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__XExpressionParams__Group_1_1__1__Impl_in_rule__XExpressionParams__Group_1_1__19971 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__XExpressionParams__EntriesAssignment_1_1_1_in_rule__XExpressionParams__Group_1_1__1__Impl9998 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Expression__Group__0__Impl_in_rule__Expression__Group__010032 = new BitSet(new long[]{0x07000E1400A18180L});
    public static final BitSet FOLLOW_rule__Expression__Group__1_in_rule__Expression__Group__010035 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Expression__Op1Assignment_0_in_rule__Expression__Group__0__Impl10062 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Expression__Group__1__Impl_in_rule__Expression__Group__110092 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Expression__Alternatives_1_in_rule__Expression__Group__1__Impl10119 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Comparison__Group__0__Impl_in_rule__Comparison__Group__010153 = new BitSet(new long[]{0xE002002080000000L,0x000000000000002BL});
    public static final BitSet FOLLOW_rule__Comparison__Group__1_in_rule__Comparison__Group__010156 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Comparison__OperatorAssignment_0_in_rule__Comparison__Group__0__Impl10183 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Comparison__Group__1__Impl_in_rule__Comparison__Group__110213 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Comparison__Op2Assignment_1_in_rule__Comparison__Group__1__Impl10240 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Like__Group__0__Impl_in_rule__Like__Group__010274 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_rule__Like__Group__1_in_rule__Like__Group__010277 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Like__OpLikeAssignment_0_in_rule__Like__Group__0__Impl10304 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Like__Group__1__Impl_in_rule__Like__Group__110334 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Like__Op2Assignment_1_in_rule__Like__Group__1__Impl10361 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Between__Group__0__Impl_in_rule__Between__Group__010395 = new BitSet(new long[]{0xE002002080000000L,0x000000000000002BL});
    public static final BitSet FOLLOW_rule__Between__Group__1_in_rule__Between__Group__010398 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Between__OpBetweenAssignment_0_in_rule__Between__Group__0__Impl10425 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Between__Group__1__Impl_in_rule__Between__Group__110455 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_rule__Between__Group__2_in_rule__Between__Group__110458 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Between__Op2Assignment_1_in_rule__Between__Group__1__Impl10485 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Between__Group__2__Impl_in_rule__Between__Group__210515 = new BitSet(new long[]{0xE002002080000000L,0x000000000000002BL});
    public static final BitSet FOLLOW_rule__Between__Group__3_in_rule__Between__Group__210518 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_22_in_rule__Between__Group__2__Impl10546 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Between__Group__3__Impl_in_rule__Between__Group__310577 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Between__Op3Assignment_3_in_rule__Between__Group__3__Impl10604 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__InOperator__Group__0__Impl_in_rule__InOperator__Group__010642 = new BitSet(new long[]{0x0000000400008000L});
    public static final BitSet FOLLOW_rule__InOperator__Group__1_in_rule__InOperator__Group__010645 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__InOperator__Group__1__Impl_in_rule__InOperator__Group__110703 = new BitSet(new long[]{0xE002002080000000L,0x000000000000002BL});
    public static final BitSet FOLLOW_rule__InOperator__Group__2_in_rule__InOperator__Group__110706 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__InOperator__OpAssignment_1_in_rule__InOperator__Group__1__Impl10733 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__InOperator__Group__2__Impl_in_rule__InOperator__Group__210763 = new BitSet(new long[]{0x0004000000000000L});
    public static final BitSet FOLLOW_rule__InOperator__Group__3_in_rule__InOperator__Group__210766 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__InOperator__Alternatives_2_in_rule__InOperator__Group__2__Impl10793 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__InOperator__Group__3__Impl_in_rule__InOperator__Group__310823 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_2_in_rule__InOperator__Group__3__Impl10851 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OperandList__Group__0__Impl_in_rule__OperandList__Group__010890 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_rule__OperandList__Group__1_in_rule__OperandList__Group__010893 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXOperandFragment_in_rule__OperandList__Group__0__Impl10920 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OperandList__Group__1__Impl_in_rule__OperandList__Group__110949 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OperandList__Group_1__0_in_rule__OperandList__Group__1__Impl10976 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OperandList__Group_1__0__Impl_in_rule__OperandList__Group_1__011011 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_rule__OperandList__Group_1__1_in_rule__OperandList__Group_1__011014 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OperandList__Group_1__1__Impl_in_rule__OperandList__Group_1__111072 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OperandList__Group_1_1__0_in_rule__OperandList__Group_1__1__Impl11101 = new BitSet(new long[]{0x0010000000000002L});
    public static final BitSet FOLLOW_rule__OperandList__Group_1_1__0_in_rule__OperandList__Group_1__1__Impl11113 = new BitSet(new long[]{0x0010000000000002L});
    public static final BitSet FOLLOW_rule__OperandList__Group_1_1__0__Impl_in_rule__OperandList__Group_1_1__011150 = new BitSet(new long[]{0xE000002080000000L,0x0000000000000023L});
    public static final BitSet FOLLOW_rule__OperandList__Group_1_1__1_in_rule__OperandList__Group_1_1__011153 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_4_in_rule__OperandList__Group_1_1__0__Impl11181 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OperandList__Group_1_1__1__Impl_in_rule__OperandList__Group_1_1__111212 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OperandList__EntriesAssignment_1_1_1_in_rule__OperandList__Group_1_1__1__Impl11239 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Operand__Group__0__Impl_in_rule__Operand__Group__011273 = new BitSet(new long[]{0x10A9000000000000L});
    public static final BitSet FOLLOW_rule__Operand__Group__1_in_rule__Operand__Group__011276 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperandFragment_in_rule__Operand__Group__0__Impl11303 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Operand__Group__1__Impl_in_rule__Operand__Group__111332 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Operand__Group_1__0_in_rule__Operand__Group__1__Impl11359 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Operand__Group_1__0__Impl_in_rule__Operand__Group_1__011394 = new BitSet(new long[]{0x10A9000000000000L});
    public static final BitSet FOLLOW_rule__Operand__Group_1__1_in_rule__Operand__Group_1__011397 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Operand__Group_1__1__Impl_in_rule__Operand__Group_1__111455 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Operand__Group_1_1__0_in_rule__Operand__Group_1__1__Impl11484 = new BitSet(new long[]{0x10A9000000000002L});
    public static final BitSet FOLLOW_rule__Operand__Group_1_1__0_in_rule__Operand__Group_1__1__Impl11496 = new BitSet(new long[]{0x10A9000000000002L});
    public static final BitSet FOLLOW_rule__Operand__Group_1_1__0__Impl_in_rule__Operand__Group_1_1__011533 = new BitSet(new long[]{0xE002002080000000L,0x000000000000002BL});
    public static final BitSet FOLLOW_rule__Operand__Group_1_1__1_in_rule__Operand__Group_1_1__011536 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Operand__Alternatives_1_1_0_in_rule__Operand__Group_1_1__0__Impl11563 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Operand__Group_1_1__1__Impl_in_rule__Operand__Group_1_1__111593 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Operand__EntriesAssignment_1_1_1_in_rule__Operand__Group_1_1__1__Impl11620 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ParameterOperand__Group__0__Impl_in_rule__ParameterOperand__Group__011654 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_rule__ParameterOperand__Group__1_in_rule__ParameterOperand__Group__011657 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ParameterOperand__Group__1__Impl_in_rule__ParameterOperand__Group__111715 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_rule__ParameterOperand__Group__2_in_rule__ParameterOperand__Group__111718 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_20_in_rule__ParameterOperand__Group__1__Impl11746 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ParameterOperand__Group__2__Impl_in_rule__ParameterOperand__Group__211777 = new BitSet(new long[]{0x0800000000000000L});
    public static final BitSet FOLLOW_rule__ParameterOperand__Group__3_in_rule__ParameterOperand__Group__211780 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ParameterOperand__PrmAssignment_2_in_rule__ParameterOperand__Group__2__Impl11807 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ParameterOperand__Group__3__Impl_in_rule__ParameterOperand__Group__311837 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_11_in_rule__ParameterOperand__Group__3__Impl11865 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ExclamationParameterOperand__Group__0__Impl_in_rule__ExclamationParameterOperand__Group__011904 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_rule__ExclamationParameterOperand__Group__1_in_rule__ExclamationParameterOperand__Group__011907 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ExclamationParameterOperand__Group__1__Impl_in_rule__ExclamationParameterOperand__Group__111965 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_rule__ExclamationParameterOperand__Group__2_in_rule__ExclamationParameterOperand__Group__111968 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_24_in_rule__ExclamationParameterOperand__Group__1__Impl11996 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ExclamationParameterOperand__Group__2__Impl_in_rule__ExclamationParameterOperand__Group__212027 = new BitSet(new long[]{0x0800000000000000L});
    public static final BitSet FOLLOW_rule__ExclamationParameterOperand__Group__3_in_rule__ExclamationParameterOperand__Group__212030 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ExclamationParameterOperand__PrmAssignment_2_in_rule__ExclamationParameterOperand__Group__2__Impl12057 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ExclamationParameterOperand__Group__3__Impl_in_rule__ExclamationParameterOperand__Group__312087 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_11_in_rule__ExclamationParameterOperand__Group__3__Impl12115 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SubQueryOperand__Group__0__Impl_in_rule__SubQueryOperand__Group__012154 = new BitSet(new long[]{0xE002002080000000L,0x000000000000002BL});
    public static final BitSet FOLLOW_rule__SubQueryOperand__Group__1_in_rule__SubQueryOperand__Group__012157 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SubQueryOperand__Group__1__Impl_in_rule__SubQueryOperand__Group__112215 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_rule__SubQueryOperand__Group__2_in_rule__SubQueryOperand__Group__112218 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_1_in_rule__SubQueryOperand__Group__1__Impl12246 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SubQueryOperand__Group__2__Impl_in_rule__SubQueryOperand__Group__212277 = new BitSet(new long[]{0x0004000000000000L});
    public static final BitSet FOLLOW_rule__SubQueryOperand__Group__3_in_rule__SubQueryOperand__Group__212280 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SubQueryOperand__SelAssignment_2_in_rule__SubQueryOperand__Group__2__Impl12307 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SubQueryOperand__Group__3__Impl_in_rule__SubQueryOperand__Group__312337 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_2_in_rule__SubQueryOperand__Group__3__Impl12365 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSelect_in_rule__Model__EntriesAssignment_1_1_112409 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOrderByColumns_in_rule__Model__OrderByEntryAssignment_2_112440 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_36_in_rule__Select__SelectAssignment_012476 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumns_in_rule__Select__ColsAssignment_212515 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTables_in_rule__Select__TblAssignment_412546 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFullExpression_in_rule__Select__WhereExpressionAssignment_5_112577 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleGroupByColumns_in_rule__Select__GroupByEntryAssignment_6_112608 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFullExpression_in_rule__Select__HavingEntryAssignment_7_112639 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnOrAlias_in_rule__Columns__EntriesAssignment_1_1_112670 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnFull_in_rule__ColumnOrAlias__CfullAssignment_0_012701 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_15_in_rule__ColumnOrAlias__AliasAssignment_0_112737 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDbObjectName_in_rule__ColumnOrAlias__ColAliasAssignment_0_212776 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STAR_in_rule__ColumnOrAlias__AllColsAssignment_112807 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDbObjectName_in_rule__ColumnFull__EntriesAssignment_1_1_112838 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFromTable_in_rule__Tables__EntriesAssignment_1_1_112869 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTableOrAlias_in_rule__FromTable__TableAssignment_012900 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleJoinType_in_rule__FromTable__JoinAssignment_1_012931 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTableOrAlias_in_rule__FromTable__OnTableAssignment_1_112962 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFullExpression_in_rule__FromTable__JoinExprAssignment_1_312993 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTableFull_in_rule__TableOrAlias__TfullAssignment_013024 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_15_in_rule__TableOrAlias__AliasAssignment_113060 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDbObjectName_in_rule__TableOrAlias__TblAliasAssignment_213099 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDbObjectName_in_rule__TableFull__EntriesAssignment_1_1_113130 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__DbObjectName__DbnameAssignment13161 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOrderByColumnFull_in_rule__OrderByColumns__EntriesAssignment_1_1_113192 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnFull_in_rule__OrderByColumnFull__ColOrderAssignment_013223 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OrderByColumnFull__DirectionAlternatives_1_0_in_rule__OrderByColumnFull__DirectionAssignment_113254 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnFull_in_rule__GroupByColumns__EntriesAssignment_1_1_113287 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpressionFragmentSecond_in_rule__FullExpression__EntriesAssignment_1_113318 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ExpressionFragmentSecond__CAlternatives_0_0_in_rule__ExpressionFragmentSecond__CAssignment_013349 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpressionFragment_in_rule__ExpressionFragmentSecond__EfragAssignment_113382 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpressionGroup_in_rule__ExpressionFragment__ExpgroupAssignment_013413 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression_in_rule__ExpressionFragment__ExpAssignment_113444 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXExpression_in_rule__ExpressionFragment__XexpAssignment_213475 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFullExpression_in_rule__ExpressionGroup__ExprAssignment_213506 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXFunction_in_rule__XExpression__XfAssignment_213537 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnOperand_in_rule__XExpression__ColAssignment_413568 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXExpressionParams_in_rule__XExpression__PrmAssignment_5_113599 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleJRParameter_in_rule__XExpressionParams__EntriesAssignment_1_1_113630 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__JRParameter__JrprmAssignment13661 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperand_in_rule__Expression__Op1Assignment_013692 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Expression__IsnullAlternatives_1_0_0_in_rule__Expression__IsnullAssignment_1_013723 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInOperator_in_rule__Expression__InAssignment_1_113756 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBetween_in_rule__Expression__BetweenAssignment_1_213787 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLike_in_rule__Expression__LikeAssignment_1_313818 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleComparison_in_rule__Expression__CompAssignment_1_413849 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Comparison__OperatorAlternatives_0_0_in_rule__Comparison__OperatorAssignment_013880 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperand_in_rule__Comparison__Op2Assignment_113913 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Like__OpLikeAlternatives_0_0_in_rule__Like__OpLikeAssignment_013944 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleStringOperand_in_rule__Like__Op2Assignment_113977 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Between__OpBetweenAlternatives_0_0_in_rule__Between__OpBetweenAssignment_014008 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperand_in_rule__Between__Op2Assignment_114041 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperand_in_rule__Between__Op3Assignment_314072 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__InOperator__OpAlternatives_1_0_in_rule__InOperator__OpAssignment_114103 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSubQueryOperand_in_rule__InOperator__SubqueryAssignment_2_014136 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperandList_in_rule__InOperator__OpListAssignment_2_114167 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXOperandFragment_in_rule__OperandList__EntriesAssignment_1_1_114198 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperandFragment_in_rule__Operand__EntriesAssignment_1_1_114229 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnOperand_in_rule__OperandFragment__ColumnAssignment_014260 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXOperandFragment_in_rule__OperandFragment__XopAssignment_114291 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSubQueryOperand_in_rule__OperandFragment__SubqAssignment_214322 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleParameterOperand_in_rule__XOperandFragment__ParamAssignment_014353 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExclamationParameterOperand_in_rule__XOperandFragment__EparamAssignment_114384 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleScalarOperand_in_rule__XOperandFragment__ScalarAssignment_214415 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__ParameterOperand__PrmAssignment_214446 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__ExclamationParameterOperand__PrmAssignment_214477 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnFull_in_rule__ColumnOperand__CfullAssignment14508 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSelect_in_rule__SubQueryOperand__SelAssignment_214539 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_SIGNED_INT_in_rule__ScalarOperand__SointAssignment_014570 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleStringOperand_in_rule__ScalarOperand__SostrAssignment_114601 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_SIGNED_DOUBLE_in_rule__ScalarOperand__SodblAssignment_214632 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_DATE_in_rule__ScalarOperand__SodateAssignment_314663 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_TIME_in_rule__ScalarOperand__SotimeAssignment_414694 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_TIMESTAMP_in_rule__ScalarOperand__SodtAssignment_514725 = new BitSet(new long[]{0x0000000000000002L});

}