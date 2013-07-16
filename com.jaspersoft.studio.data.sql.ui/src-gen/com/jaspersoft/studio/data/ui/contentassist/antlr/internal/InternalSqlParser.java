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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "KEYWORD_56", "KEYWORD_54", "KEYWORD_55", "KEYWORD_52", "KEYWORD_53", "KEYWORD_50", "KEYWORD_51", "KEYWORD_49", "KEYWORD_41", "KEYWORD_42", "KEYWORD_43", "KEYWORD_44", "KEYWORD_45", "KEYWORD_46", "KEYWORD_47", "KEYWORD_48", "KEYWORD_38", "KEYWORD_39", "KEYWORD_40", "KEYWORD_35", "KEYWORD_36", "KEYWORD_37", "KEYWORD_30", "KEYWORD_31", "KEYWORD_32", "KEYWORD_33", "KEYWORD_34", "KEYWORD_26", "KEYWORD_27", "KEYWORD_28", "KEYWORD_29", "KEYWORD_23", "KEYWORD_24", "KEYWORD_25", "KEYWORD_13", "KEYWORD_14", "KEYWORD_15", "KEYWORD_16", "KEYWORD_17", "KEYWORD_18", "KEYWORD_19", "KEYWORD_20", "KEYWORD_21", "KEYWORD_22", "KEYWORD_1", "KEYWORD_2", "KEYWORD_3", "KEYWORD_4", "KEYWORD_5", "KEYWORD_6", "KEYWORD_7", "KEYWORD_8", "KEYWORD_9", "KEYWORD_10", "KEYWORD_11", "KEYWORD_12", "RULE_STAR", "RULE_SIGNED_INT", "RULE_DATE", "RULE_TIME", "RULE_TIMESTAMP", "RULE_SIGNED_DOUBLE", "RULE_SL_COMMENT", "RULE_ID", "RULE_INT", "RULE_STRING", "RULE_ML_COMMENT", "RULE_WS", "RULE_ANY_OTHER"
    };
    public static final int RULE_ID=67;
    public static final int RULE_DATE=62;
    public static final int RULE_ANY_OTHER=72;
    public static final int KEYWORD_56=4;
    public static final int KEYWORD_19=44;
    public static final int KEYWORD_55=6;
    public static final int KEYWORD_17=42;
    public static final int KEYWORD_54=5;
    public static final int KEYWORD_53=8;
    public static final int KEYWORD_18=43;
    public static final int KEYWORD_15=40;
    public static final int KEYWORD_52=7;
    public static final int KEYWORD_16=41;
    public static final int KEYWORD_51=10;
    public static final int KEYWORD_50=9;
    public static final int KEYWORD_13=38;
    public static final int KEYWORD_14=39;
    public static final int KEYWORD_11=58;
    public static final int EOF=-1;
    public static final int KEYWORD_12=59;
    public static final int KEYWORD_10=57;
    public static final int KEYWORD_6=53;
    public static final int KEYWORD_7=54;
    public static final int KEYWORD_8=55;
    public static final int KEYWORD_9=56;
    public static final int RULE_TIME=63;
    public static final int KEYWORD_28=33;
    public static final int KEYWORD_29=34;
    public static final int RULE_INT=68;
    public static final int RULE_SIGNED_DOUBLE=65;
    public static final int RULE_TIMESTAMP=64;
    public static final int RULE_SIGNED_INT=61;
    public static final int KEYWORD_24=36;
    public static final int KEYWORD_25=37;
    public static final int KEYWORD_26=31;
    public static final int KEYWORD_27=32;
    public static final int KEYWORD_20=45;
    public static final int KEYWORD_21=46;
    public static final int KEYWORD_22=47;
    public static final int KEYWORD_23=35;
    public static final int KEYWORD_30=26;
    public static final int KEYWORD_1=48;
    public static final int KEYWORD_34=30;
    public static final int KEYWORD_5=52;
    public static final int KEYWORD_33=29;
    public static final int KEYWORD_4=51;
    public static final int KEYWORD_32=28;
    public static final int KEYWORD_3=50;
    public static final int KEYWORD_31=27;
    public static final int KEYWORD_2=49;
    public static final int KEYWORD_38=20;
    public static final int KEYWORD_37=25;
    public static final int RULE_SL_COMMENT=66;
    public static final int KEYWORD_36=24;
    public static final int KEYWORD_35=23;
    public static final int RULE_ML_COMMENT=70;
    public static final int KEYWORD_39=21;
    public static final int RULE_STRING=69;
    public static final int RULE_STAR=60;
    public static final int KEYWORD_41=12;
    public static final int KEYWORD_40=22;
    public static final int KEYWORD_43=14;
    public static final int KEYWORD_42=13;
    public static final int KEYWORD_45=16;
    public static final int KEYWORD_44=15;
    public static final int KEYWORD_47=18;
    public static final int RULE_WS=71;
    public static final int KEYWORD_46=17;
    public static final int KEYWORD_49=11;
    public static final int KEYWORD_48=19;

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
    		tokenNameToValue.put("KEYWORD_11", "'{'");
    		tokenNameToValue.put("KEYWORD_12", "'}'");
    		tokenNameToValue.put("KEYWORD_13", "'\u0024P'");
    		tokenNameToValue.put("KEYWORD_14", "'\u0024X'");
    		tokenNameToValue.put("KEYWORD_15", "'<='");
    		tokenNameToValue.put("KEYWORD_16", "'<>'");
    		tokenNameToValue.put("KEYWORD_17", "'>='");
    		tokenNameToValue.put("KEYWORD_18", "'AS'");
    		tokenNameToValue.put("KEYWORD_19", "'IN'");
    		tokenNameToValue.put("KEYWORD_20", "'ON'");
    		tokenNameToValue.put("KEYWORD_21", "'OR'");
    		tokenNameToValue.put("KEYWORD_22", "'||'");
    		tokenNameToValue.put("KEYWORD_23", "'\u0024P!'");
    		tokenNameToValue.put("KEYWORD_24", "'AND'");
    		tokenNameToValue.put("KEYWORD_25", "'ASC'");
    		tokenNameToValue.put("KEYWORD_26", "'DESC'");
    		tokenNameToValue.put("KEYWORD_27", "'FROM'");
    		tokenNameToValue.put("KEYWORD_28", "'LESS'");
    		tokenNameToValue.put("KEYWORD_29", "'LIKE'");
    		tokenNameToValue.put("KEYWORD_30", "'EQUAL'");
    		tokenNameToValue.put("KEYWORD_31", "'LESS]'");
    		tokenNameToValue.put("KEYWORD_32", "'NOTIN'");
    		tokenNameToValue.put("KEYWORD_33", "'UNION'");
    		tokenNameToValue.put("KEYWORD_34", "'WHERE'");
    		tokenNameToValue.put("KEYWORD_35", "'HAVING'");
    		tokenNameToValue.put("KEYWORD_36", "'NOT IN'");
    		tokenNameToValue.put("KEYWORD_37", "'SELECT'");
    		tokenNameToValue.put("KEYWORD_38", "'BETWEEN'");
    		tokenNameToValue.put("KEYWORD_39", "'GREATER'");
    		tokenNameToValue.put("KEYWORD_40", "'IS NULL'");
    		tokenNameToValue.put("KEYWORD_41", "'BETWEEN]'");
    		tokenNameToValue.put("KEYWORD_42", "'DISTINCT'");
    		tokenNameToValue.put("KEYWORD_43", "'GROUP BY'");
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


    // $ANTLR start "entryRuleGroupByColumnFull"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:483:1: entryRuleGroupByColumnFull : ruleGroupByColumnFull EOF ;
    public final void entryRuleGroupByColumnFull() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:484:1: ( ruleGroupByColumnFull EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:485:1: ruleGroupByColumnFull EOF
            {
             before(grammarAccess.getGroupByColumnFullRule()); 
            pushFollow(FOLLOW_ruleGroupByColumnFull_in_entryRuleGroupByColumnFull886);
            ruleGroupByColumnFull();

            state._fsp--;

             after(grammarAccess.getGroupByColumnFullRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleGroupByColumnFull893); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:492:1: ruleGroupByColumnFull : ( ( rule__GroupByColumnFull__ColGrByAssignment ) ) ;
    public final void ruleGroupByColumnFull() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:496:5: ( ( ( rule__GroupByColumnFull__ColGrByAssignment ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:497:1: ( ( rule__GroupByColumnFull__ColGrByAssignment ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:497:1: ( ( rule__GroupByColumnFull__ColGrByAssignment ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:498:1: ( rule__GroupByColumnFull__ColGrByAssignment )
            {
             before(grammarAccess.getGroupByColumnFullAccess().getColGrByAssignment()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:499:1: ( rule__GroupByColumnFull__ColGrByAssignment )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:499:2: rule__GroupByColumnFull__ColGrByAssignment
            {
            pushFollow(FOLLOW_rule__GroupByColumnFull__ColGrByAssignment_in_ruleGroupByColumnFull923);
            rule__GroupByColumnFull__ColGrByAssignment();

            state._fsp--;


            }

             after(grammarAccess.getGroupByColumnFullAccess().getColGrByAssignment()); 

            }


            }

        }
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


    // $ANTLR start "entryRuleFullExpression"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:511:1: entryRuleFullExpression : ruleFullExpression EOF ;
    public final void entryRuleFullExpression() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:512:1: ( ruleFullExpression EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:513:1: ruleFullExpression EOF
            {
             before(grammarAccess.getFullExpressionRule()); 
            pushFollow(FOLLOW_ruleFullExpression_in_entryRuleFullExpression950);
            ruleFullExpression();

            state._fsp--;

             after(grammarAccess.getFullExpressionRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleFullExpression957); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:520:1: ruleFullExpression : ( ( rule__FullExpression__Group__0 ) ) ;
    public final void ruleFullExpression() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:524:5: ( ( ( rule__FullExpression__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:525:1: ( ( rule__FullExpression__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:525:1: ( ( rule__FullExpression__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:526:1: ( rule__FullExpression__Group__0 )
            {
             before(grammarAccess.getFullExpressionAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:527:1: ( rule__FullExpression__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:527:2: rule__FullExpression__Group__0
            {
            pushFollow(FOLLOW_rule__FullExpression__Group__0_in_ruleFullExpression987);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:539:1: entryRuleExpressionFragmentSecond : ruleExpressionFragmentSecond EOF ;
    public final void entryRuleExpressionFragmentSecond() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:540:1: ( ruleExpressionFragmentSecond EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:541:1: ruleExpressionFragmentSecond EOF
            {
             before(grammarAccess.getExpressionFragmentSecondRule()); 
            pushFollow(FOLLOW_ruleExpressionFragmentSecond_in_entryRuleExpressionFragmentSecond1014);
            ruleExpressionFragmentSecond();

            state._fsp--;

             after(grammarAccess.getExpressionFragmentSecondRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpressionFragmentSecond1021); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:548:1: ruleExpressionFragmentSecond : ( ( rule__ExpressionFragmentSecond__Group__0 ) ) ;
    public final void ruleExpressionFragmentSecond() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:552:5: ( ( ( rule__ExpressionFragmentSecond__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:553:1: ( ( rule__ExpressionFragmentSecond__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:553:1: ( ( rule__ExpressionFragmentSecond__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:554:1: ( rule__ExpressionFragmentSecond__Group__0 )
            {
             before(grammarAccess.getExpressionFragmentSecondAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:555:1: ( rule__ExpressionFragmentSecond__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:555:2: rule__ExpressionFragmentSecond__Group__0
            {
            pushFollow(FOLLOW_rule__ExpressionFragmentSecond__Group__0_in_ruleExpressionFragmentSecond1051);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:567:1: entryRuleExpressionFragment : ruleExpressionFragment EOF ;
    public final void entryRuleExpressionFragment() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:568:1: ( ruleExpressionFragment EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:569:1: ruleExpressionFragment EOF
            {
             before(grammarAccess.getExpressionFragmentRule()); 
            pushFollow(FOLLOW_ruleExpressionFragment_in_entryRuleExpressionFragment1078);
            ruleExpressionFragment();

            state._fsp--;

             after(grammarAccess.getExpressionFragmentRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpressionFragment1085); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:576:1: ruleExpressionFragment : ( ( rule__ExpressionFragment__Alternatives ) ) ;
    public final void ruleExpressionFragment() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:580:5: ( ( ( rule__ExpressionFragment__Alternatives ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:581:1: ( ( rule__ExpressionFragment__Alternatives ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:581:1: ( ( rule__ExpressionFragment__Alternatives ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:582:1: ( rule__ExpressionFragment__Alternatives )
            {
             before(grammarAccess.getExpressionFragmentAccess().getAlternatives()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:583:1: ( rule__ExpressionFragment__Alternatives )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:583:2: rule__ExpressionFragment__Alternatives
            {
            pushFollow(FOLLOW_rule__ExpressionFragment__Alternatives_in_ruleExpressionFragment1115);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:595:1: entryRuleExpressionGroup : ruleExpressionGroup EOF ;
    public final void entryRuleExpressionGroup() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:596:1: ( ruleExpressionGroup EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:597:1: ruleExpressionGroup EOF
            {
             before(grammarAccess.getExpressionGroupRule()); 
            pushFollow(FOLLOW_ruleExpressionGroup_in_entryRuleExpressionGroup1142);
            ruleExpressionGroup();

            state._fsp--;

             after(grammarAccess.getExpressionGroupRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpressionGroup1149); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:604:1: ruleExpressionGroup : ( ( rule__ExpressionGroup__Group__0 ) ) ;
    public final void ruleExpressionGroup() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:608:5: ( ( ( rule__ExpressionGroup__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:609:1: ( ( rule__ExpressionGroup__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:609:1: ( ( rule__ExpressionGroup__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:610:1: ( rule__ExpressionGroup__Group__0 )
            {
             before(grammarAccess.getExpressionGroupAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:611:1: ( rule__ExpressionGroup__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:611:2: rule__ExpressionGroup__Group__0
            {
            pushFollow(FOLLOW_rule__ExpressionGroup__Group__0_in_ruleExpressionGroup1179);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:623:1: entryRuleXExpression : ruleXExpression EOF ;
    public final void entryRuleXExpression() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:624:1: ( ruleXExpression EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:625:1: ruleXExpression EOF
            {
             before(grammarAccess.getXExpressionRule()); 
            pushFollow(FOLLOW_ruleXExpression_in_entryRuleXExpression1206);
            ruleXExpression();

            state._fsp--;

             after(grammarAccess.getXExpressionRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleXExpression1213); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:632:1: ruleXExpression : ( ( rule__XExpression__Group__0 ) ) ;
    public final void ruleXExpression() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:636:5: ( ( ( rule__XExpression__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:637:1: ( ( rule__XExpression__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:637:1: ( ( rule__XExpression__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:638:1: ( rule__XExpression__Group__0 )
            {
             before(grammarAccess.getXExpressionAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:639:1: ( rule__XExpression__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:639:2: rule__XExpression__Group__0
            {
            pushFollow(FOLLOW_rule__XExpression__Group__0_in_ruleXExpression1243);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:651:1: entryRuleXExpressionParams : ruleXExpressionParams EOF ;
    public final void entryRuleXExpressionParams() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:652:1: ( ruleXExpressionParams EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:653:1: ruleXExpressionParams EOF
            {
             before(grammarAccess.getXExpressionParamsRule()); 
            pushFollow(FOLLOW_ruleXExpressionParams_in_entryRuleXExpressionParams1270);
            ruleXExpressionParams();

            state._fsp--;

             after(grammarAccess.getXExpressionParamsRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleXExpressionParams1277); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:660:1: ruleXExpressionParams : ( ( rule__XExpressionParams__Group__0 ) ) ;
    public final void ruleXExpressionParams() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:664:5: ( ( ( rule__XExpressionParams__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:665:1: ( ( rule__XExpressionParams__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:665:1: ( ( rule__XExpressionParams__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:666:1: ( rule__XExpressionParams__Group__0 )
            {
             before(grammarAccess.getXExpressionParamsAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:667:1: ( rule__XExpressionParams__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:667:2: rule__XExpressionParams__Group__0
            {
            pushFollow(FOLLOW_rule__XExpressionParams__Group__0_in_ruleXExpressionParams1307);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:679:1: entryRuleJRParameter : ruleJRParameter EOF ;
    public final void entryRuleJRParameter() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:680:1: ( ruleJRParameter EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:681:1: ruleJRParameter EOF
            {
             before(grammarAccess.getJRParameterRule()); 
            pushFollow(FOLLOW_ruleJRParameter_in_entryRuleJRParameter1334);
            ruleJRParameter();

            state._fsp--;

             after(grammarAccess.getJRParameterRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleJRParameter1341); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:688:1: ruleJRParameter : ( ( rule__JRParameter__JrprmAssignment ) ) ;
    public final void ruleJRParameter() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:692:5: ( ( ( rule__JRParameter__JrprmAssignment ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:693:1: ( ( rule__JRParameter__JrprmAssignment ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:693:1: ( ( rule__JRParameter__JrprmAssignment ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:694:1: ( rule__JRParameter__JrprmAssignment )
            {
             before(grammarAccess.getJRParameterAccess().getJrprmAssignment()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:695:1: ( rule__JRParameter__JrprmAssignment )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:695:2: rule__JRParameter__JrprmAssignment
            {
            pushFollow(FOLLOW_rule__JRParameter__JrprmAssignment_in_ruleJRParameter1371);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:707:1: entryRuleExpression : ruleExpression EOF ;
    public final void entryRuleExpression() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:708:1: ( ruleExpression EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:709:1: ruleExpression EOF
            {
             before(grammarAccess.getExpressionRule()); 
            pushFollow(FOLLOW_ruleExpression_in_entryRuleExpression1398);
            ruleExpression();

            state._fsp--;

             after(grammarAccess.getExpressionRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpression1405); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:716:1: ruleExpression : ( ( rule__Expression__Group__0 ) ) ;
    public final void ruleExpression() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:720:5: ( ( ( rule__Expression__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:721:1: ( ( rule__Expression__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:721:1: ( ( rule__Expression__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:722:1: ( rule__Expression__Group__0 )
            {
             before(grammarAccess.getExpressionAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:723:1: ( rule__Expression__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:723:2: rule__Expression__Group__0
            {
            pushFollow(FOLLOW_rule__Expression__Group__0_in_ruleExpression1435);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:735:1: entryRuleComparison : ruleComparison EOF ;
    public final void entryRuleComparison() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:736:1: ( ruleComparison EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:737:1: ruleComparison EOF
            {
             before(grammarAccess.getComparisonRule()); 
            pushFollow(FOLLOW_ruleComparison_in_entryRuleComparison1462);
            ruleComparison();

            state._fsp--;

             after(grammarAccess.getComparisonRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleComparison1469); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:744:1: ruleComparison : ( ( rule__Comparison__Group__0 ) ) ;
    public final void ruleComparison() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:748:5: ( ( ( rule__Comparison__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:749:1: ( ( rule__Comparison__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:749:1: ( ( rule__Comparison__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:750:1: ( rule__Comparison__Group__0 )
            {
             before(grammarAccess.getComparisonAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:751:1: ( rule__Comparison__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:751:2: rule__Comparison__Group__0
            {
            pushFollow(FOLLOW_rule__Comparison__Group__0_in_ruleComparison1499);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:763:1: entryRuleLike : ruleLike EOF ;
    public final void entryRuleLike() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:764:1: ( ruleLike EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:765:1: ruleLike EOF
            {
             before(grammarAccess.getLikeRule()); 
            pushFollow(FOLLOW_ruleLike_in_entryRuleLike1526);
            ruleLike();

            state._fsp--;

             after(grammarAccess.getLikeRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleLike1533); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:772:1: ruleLike : ( ( rule__Like__Group__0 ) ) ;
    public final void ruleLike() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:776:5: ( ( ( rule__Like__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:777:1: ( ( rule__Like__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:777:1: ( ( rule__Like__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:778:1: ( rule__Like__Group__0 )
            {
             before(grammarAccess.getLikeAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:779:1: ( rule__Like__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:779:2: rule__Like__Group__0
            {
            pushFollow(FOLLOW_rule__Like__Group__0_in_ruleLike1563);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:791:1: entryRuleBetween : ruleBetween EOF ;
    public final void entryRuleBetween() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:792:1: ( ruleBetween EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:793:1: ruleBetween EOF
            {
             before(grammarAccess.getBetweenRule()); 
            pushFollow(FOLLOW_ruleBetween_in_entryRuleBetween1590);
            ruleBetween();

            state._fsp--;

             after(grammarAccess.getBetweenRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleBetween1597); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:800:1: ruleBetween : ( ( rule__Between__Group__0 ) ) ;
    public final void ruleBetween() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:804:5: ( ( ( rule__Between__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:805:1: ( ( rule__Between__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:805:1: ( ( rule__Between__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:806:1: ( rule__Between__Group__0 )
            {
             before(grammarAccess.getBetweenAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:807:1: ( rule__Between__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:807:2: rule__Between__Group__0
            {
            pushFollow(FOLLOW_rule__Between__Group__0_in_ruleBetween1627);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:819:1: entryRuleInOperator : ruleInOperator EOF ;
    public final void entryRuleInOperator() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:820:1: ( ruleInOperator EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:821:1: ruleInOperator EOF
            {
             before(grammarAccess.getInOperatorRule()); 
            pushFollow(FOLLOW_ruleInOperator_in_entryRuleInOperator1654);
            ruleInOperator();

            state._fsp--;

             after(grammarAccess.getInOperatorRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleInOperator1661); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:828:1: ruleInOperator : ( ( rule__InOperator__Group__0 ) ) ;
    public final void ruleInOperator() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:832:5: ( ( ( rule__InOperator__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:833:1: ( ( rule__InOperator__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:833:1: ( ( rule__InOperator__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:834:1: ( rule__InOperator__Group__0 )
            {
             before(grammarAccess.getInOperatorAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:835:1: ( rule__InOperator__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:835:2: rule__InOperator__Group__0
            {
            pushFollow(FOLLOW_rule__InOperator__Group__0_in_ruleInOperator1691);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:847:1: entryRuleOperandList : ruleOperandList EOF ;
    public final void entryRuleOperandList() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:848:1: ( ruleOperandList EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:849:1: ruleOperandList EOF
            {
             before(grammarAccess.getOperandListRule()); 
            pushFollow(FOLLOW_ruleOperandList_in_entryRuleOperandList1718);
            ruleOperandList();

            state._fsp--;

             after(grammarAccess.getOperandListRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOperandList1725); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:856:1: ruleOperandList : ( ( rule__OperandList__Group__0 ) ) ;
    public final void ruleOperandList() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:860:5: ( ( ( rule__OperandList__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:861:1: ( ( rule__OperandList__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:861:1: ( ( rule__OperandList__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:862:1: ( rule__OperandList__Group__0 )
            {
             before(grammarAccess.getOperandListAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:863:1: ( rule__OperandList__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:863:2: rule__OperandList__Group__0
            {
            pushFollow(FOLLOW_rule__OperandList__Group__0_in_ruleOperandList1755);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:875:1: entryRuleOperand : ruleOperand EOF ;
    public final void entryRuleOperand() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:876:1: ( ruleOperand EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:877:1: ruleOperand EOF
            {
             before(grammarAccess.getOperandRule()); 
            pushFollow(FOLLOW_ruleOperand_in_entryRuleOperand1782);
            ruleOperand();

            state._fsp--;

             after(grammarAccess.getOperandRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOperand1789); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:884:1: ruleOperand : ( ( rule__Operand__Group__0 ) ) ;
    public final void ruleOperand() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:888:5: ( ( ( rule__Operand__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:889:1: ( ( rule__Operand__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:889:1: ( ( rule__Operand__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:890:1: ( rule__Operand__Group__0 )
            {
             before(grammarAccess.getOperandAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:891:1: ( rule__Operand__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:891:2: rule__Operand__Group__0
            {
            pushFollow(FOLLOW_rule__Operand__Group__0_in_ruleOperand1819);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:903:1: entryRuleOperandFragment : ruleOperandFragment EOF ;
    public final void entryRuleOperandFragment() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:904:1: ( ruleOperandFragment EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:905:1: ruleOperandFragment EOF
            {
             before(grammarAccess.getOperandFragmentRule()); 
            pushFollow(FOLLOW_ruleOperandFragment_in_entryRuleOperandFragment1846);
            ruleOperandFragment();

            state._fsp--;

             after(grammarAccess.getOperandFragmentRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOperandFragment1853); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:912:1: ruleOperandFragment : ( ( rule__OperandFragment__Alternatives ) ) ;
    public final void ruleOperandFragment() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:916:5: ( ( ( rule__OperandFragment__Alternatives ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:917:1: ( ( rule__OperandFragment__Alternatives ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:917:1: ( ( rule__OperandFragment__Alternatives ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:918:1: ( rule__OperandFragment__Alternatives )
            {
             before(grammarAccess.getOperandFragmentAccess().getAlternatives()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:919:1: ( rule__OperandFragment__Alternatives )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:919:2: rule__OperandFragment__Alternatives
            {
            pushFollow(FOLLOW_rule__OperandFragment__Alternatives_in_ruleOperandFragment1883);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:931:1: entryRuleXOperandFragment : ruleXOperandFragment EOF ;
    public final void entryRuleXOperandFragment() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:932:1: ( ruleXOperandFragment EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:933:1: ruleXOperandFragment EOF
            {
             before(grammarAccess.getXOperandFragmentRule()); 
            pushFollow(FOLLOW_ruleXOperandFragment_in_entryRuleXOperandFragment1910);
            ruleXOperandFragment();

            state._fsp--;

             after(grammarAccess.getXOperandFragmentRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleXOperandFragment1917); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:940:1: ruleXOperandFragment : ( ( rule__XOperandFragment__Alternatives ) ) ;
    public final void ruleXOperandFragment() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:944:5: ( ( ( rule__XOperandFragment__Alternatives ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:945:1: ( ( rule__XOperandFragment__Alternatives ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:945:1: ( ( rule__XOperandFragment__Alternatives ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:946:1: ( rule__XOperandFragment__Alternatives )
            {
             before(grammarAccess.getXOperandFragmentAccess().getAlternatives()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:947:1: ( rule__XOperandFragment__Alternatives )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:947:2: rule__XOperandFragment__Alternatives
            {
            pushFollow(FOLLOW_rule__XOperandFragment__Alternatives_in_ruleXOperandFragment1947);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:959:1: entryRuleParameterOperand : ruleParameterOperand EOF ;
    public final void entryRuleParameterOperand() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:960:1: ( ruleParameterOperand EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:961:1: ruleParameterOperand EOF
            {
             before(grammarAccess.getParameterOperandRule()); 
            pushFollow(FOLLOW_ruleParameterOperand_in_entryRuleParameterOperand1974);
            ruleParameterOperand();

            state._fsp--;

             after(grammarAccess.getParameterOperandRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleParameterOperand1981); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:968:1: ruleParameterOperand : ( ( rule__ParameterOperand__Group__0 ) ) ;
    public final void ruleParameterOperand() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:972:5: ( ( ( rule__ParameterOperand__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:973:1: ( ( rule__ParameterOperand__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:973:1: ( ( rule__ParameterOperand__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:974:1: ( rule__ParameterOperand__Group__0 )
            {
             before(grammarAccess.getParameterOperandAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:975:1: ( rule__ParameterOperand__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:975:2: rule__ParameterOperand__Group__0
            {
            pushFollow(FOLLOW_rule__ParameterOperand__Group__0_in_ruleParameterOperand2011);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:987:1: entryRuleExclamationParameterOperand : ruleExclamationParameterOperand EOF ;
    public final void entryRuleExclamationParameterOperand() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:988:1: ( ruleExclamationParameterOperand EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:989:1: ruleExclamationParameterOperand EOF
            {
             before(grammarAccess.getExclamationParameterOperandRule()); 
            pushFollow(FOLLOW_ruleExclamationParameterOperand_in_entryRuleExclamationParameterOperand2038);
            ruleExclamationParameterOperand();

            state._fsp--;

             after(grammarAccess.getExclamationParameterOperandRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleExclamationParameterOperand2045); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:996:1: ruleExclamationParameterOperand : ( ( rule__ExclamationParameterOperand__Group__0 ) ) ;
    public final void ruleExclamationParameterOperand() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1000:5: ( ( ( rule__ExclamationParameterOperand__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1001:1: ( ( rule__ExclamationParameterOperand__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1001:1: ( ( rule__ExclamationParameterOperand__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1002:1: ( rule__ExclamationParameterOperand__Group__0 )
            {
             before(grammarAccess.getExclamationParameterOperandAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1003:1: ( rule__ExclamationParameterOperand__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1003:2: rule__ExclamationParameterOperand__Group__0
            {
            pushFollow(FOLLOW_rule__ExclamationParameterOperand__Group__0_in_ruleExclamationParameterOperand2075);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1015:1: entryRuleColumnOperand : ruleColumnOperand EOF ;
    public final void entryRuleColumnOperand() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1016:1: ( ruleColumnOperand EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1017:1: ruleColumnOperand EOF
            {
             before(grammarAccess.getColumnOperandRule()); 
            pushFollow(FOLLOW_ruleColumnOperand_in_entryRuleColumnOperand2102);
            ruleColumnOperand();

            state._fsp--;

             after(grammarAccess.getColumnOperandRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleColumnOperand2109); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1024:1: ruleColumnOperand : ( ( rule__ColumnOperand__CfullAssignment ) ) ;
    public final void ruleColumnOperand() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1028:5: ( ( ( rule__ColumnOperand__CfullAssignment ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1029:1: ( ( rule__ColumnOperand__CfullAssignment ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1029:1: ( ( rule__ColumnOperand__CfullAssignment ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1030:1: ( rule__ColumnOperand__CfullAssignment )
            {
             before(grammarAccess.getColumnOperandAccess().getCfullAssignment()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1031:1: ( rule__ColumnOperand__CfullAssignment )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1031:2: rule__ColumnOperand__CfullAssignment
            {
            pushFollow(FOLLOW_rule__ColumnOperand__CfullAssignment_in_ruleColumnOperand2139);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1043:1: entryRuleSubQueryOperand : ruleSubQueryOperand EOF ;
    public final void entryRuleSubQueryOperand() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1044:1: ( ruleSubQueryOperand EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1045:1: ruleSubQueryOperand EOF
            {
             before(grammarAccess.getSubQueryOperandRule()); 
            pushFollow(FOLLOW_ruleSubQueryOperand_in_entryRuleSubQueryOperand2166);
            ruleSubQueryOperand();

            state._fsp--;

             after(grammarAccess.getSubQueryOperandRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSubQueryOperand2173); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1052:1: ruleSubQueryOperand : ( ( rule__SubQueryOperand__Group__0 ) ) ;
    public final void ruleSubQueryOperand() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1056:5: ( ( ( rule__SubQueryOperand__Group__0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1057:1: ( ( rule__SubQueryOperand__Group__0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1057:1: ( ( rule__SubQueryOperand__Group__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1058:1: ( rule__SubQueryOperand__Group__0 )
            {
             before(grammarAccess.getSubQueryOperandAccess().getGroup()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1059:1: ( rule__SubQueryOperand__Group__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1059:2: rule__SubQueryOperand__Group__0
            {
            pushFollow(FOLLOW_rule__SubQueryOperand__Group__0_in_ruleSubQueryOperand2203);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1071:1: entryRuleScalarOperand : ruleScalarOperand EOF ;
    public final void entryRuleScalarOperand() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1072:1: ( ruleScalarOperand EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1073:1: ruleScalarOperand EOF
            {
             before(grammarAccess.getScalarOperandRule()); 
            pushFollow(FOLLOW_ruleScalarOperand_in_entryRuleScalarOperand2230);
            ruleScalarOperand();

            state._fsp--;

             after(grammarAccess.getScalarOperandRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleScalarOperand2237); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1080:1: ruleScalarOperand : ( ( rule__ScalarOperand__Alternatives ) ) ;
    public final void ruleScalarOperand() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1084:5: ( ( ( rule__ScalarOperand__Alternatives ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1085:1: ( ( rule__ScalarOperand__Alternatives ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1085:1: ( ( rule__ScalarOperand__Alternatives ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1086:1: ( rule__ScalarOperand__Alternatives )
            {
             before(grammarAccess.getScalarOperandAccess().getAlternatives()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1087:1: ( rule__ScalarOperand__Alternatives )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1087:2: rule__ScalarOperand__Alternatives
            {
            pushFollow(FOLLOW_rule__ScalarOperand__Alternatives_in_ruleScalarOperand2267);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1099:1: entryRuleStringOperand : ruleStringOperand EOF ;
    public final void entryRuleStringOperand() throws RecognitionException {
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1100:1: ( ruleStringOperand EOF )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1101:1: ruleStringOperand EOF
            {
             before(grammarAccess.getStringOperandRule()); 
            pushFollow(FOLLOW_ruleStringOperand_in_entryRuleStringOperand2294);
            ruleStringOperand();

            state._fsp--;

             after(grammarAccess.getStringOperandRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleStringOperand2301); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1108:1: ruleStringOperand : ( RULE_STRING ) ;
    public final void ruleStringOperand() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1112:5: ( ( RULE_STRING ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1113:1: ( RULE_STRING )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1113:1: ( RULE_STRING )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1114:1: RULE_STRING
            {
             before(grammarAccess.getStringOperandAccess().getSTRINGTerminalRuleCall()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleStringOperand2331); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1128:1: ruleXFunction : ( ( rule__XFunction__Alternatives ) ) ;
    public final void ruleXFunction() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1132:1: ( ( ( rule__XFunction__Alternatives ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1133:1: ( ( rule__XFunction__Alternatives ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1133:1: ( ( rule__XFunction__Alternatives ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1134:1: ( rule__XFunction__Alternatives )
            {
             before(grammarAccess.getXFunctionAccess().getAlternatives()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1135:1: ( rule__XFunction__Alternatives )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1135:2: rule__XFunction__Alternatives
            {
            pushFollow(FOLLOW_rule__XFunction__Alternatives_in_ruleXFunction2367);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1147:1: ruleJoinType : ( ( rule__JoinType__Alternatives ) ) ;
    public final void ruleJoinType() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1151:1: ( ( ( rule__JoinType__Alternatives ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1152:1: ( ( rule__JoinType__Alternatives ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1152:1: ( ( rule__JoinType__Alternatives ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1153:1: ( rule__JoinType__Alternatives )
            {
             before(grammarAccess.getJoinTypeAccess().getAlternatives()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1154:1: ( rule__JoinType__Alternatives )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1154:2: rule__JoinType__Alternatives
            {
            pushFollow(FOLLOW_rule__JoinType__Alternatives_in_ruleJoinType2403);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1165:1: rule__ColumnOrAlias__Alternatives : ( ( ( rule__ColumnOrAlias__Group_0__0 ) ) | ( ( rule__ColumnOrAlias__AllColsAssignment_1 ) ) );
    public final void rule__ColumnOrAlias__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1169:1: ( ( ( rule__ColumnOrAlias__Group_0__0 ) ) | ( ( rule__ColumnOrAlias__AllColsAssignment_1 ) ) )
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
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1170:1: ( ( rule__ColumnOrAlias__Group_0__0 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1170:1: ( ( rule__ColumnOrAlias__Group_0__0 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1171:1: ( rule__ColumnOrAlias__Group_0__0 )
                    {
                     before(grammarAccess.getColumnOrAliasAccess().getGroup_0()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1172:1: ( rule__ColumnOrAlias__Group_0__0 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1172:2: rule__ColumnOrAlias__Group_0__0
                    {
                    pushFollow(FOLLOW_rule__ColumnOrAlias__Group_0__0_in_rule__ColumnOrAlias__Alternatives2438);
                    rule__ColumnOrAlias__Group_0__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getColumnOrAliasAccess().getGroup_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1176:6: ( ( rule__ColumnOrAlias__AllColsAssignment_1 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1176:6: ( ( rule__ColumnOrAlias__AllColsAssignment_1 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1177:1: ( rule__ColumnOrAlias__AllColsAssignment_1 )
                    {
                     before(grammarAccess.getColumnOrAliasAccess().getAllColsAssignment_1()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1178:1: ( rule__ColumnOrAlias__AllColsAssignment_1 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1178:2: rule__ColumnOrAlias__AllColsAssignment_1
                    {
                    pushFollow(FOLLOW_rule__ColumnOrAlias__AllColsAssignment_1_in_rule__ColumnOrAlias__Alternatives2456);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1187:1: rule__OrderByColumnFull__DirectionAlternatives_1_0 : ( ( KEYWORD_25 ) | ( KEYWORD_26 ) );
    public final void rule__OrderByColumnFull__DirectionAlternatives_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1191:1: ( ( KEYWORD_25 ) | ( KEYWORD_26 ) )
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==KEYWORD_25) ) {
                alt2=1;
            }
            else if ( (LA2_0==KEYWORD_26) ) {
                alt2=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }
            switch (alt2) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1192:1: ( KEYWORD_25 )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1192:1: ( KEYWORD_25 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1193:1: KEYWORD_25
                    {
                     before(grammarAccess.getOrderByColumnFullAccess().getDirectionASCKeyword_1_0_0()); 
                    match(input,KEYWORD_25,FOLLOW_KEYWORD_25_in_rule__OrderByColumnFull__DirectionAlternatives_1_02490); 
                     after(grammarAccess.getOrderByColumnFullAccess().getDirectionASCKeyword_1_0_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1200:6: ( KEYWORD_26 )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1200:6: ( KEYWORD_26 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1201:1: KEYWORD_26
                    {
                     before(grammarAccess.getOrderByColumnFullAccess().getDirectionDESCKeyword_1_0_1()); 
                    match(input,KEYWORD_26,FOLLOW_KEYWORD_26_in_rule__OrderByColumnFull__DirectionAlternatives_1_02510); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1213:1: rule__ExpressionFragmentSecond__CAlternatives_0_0 : ( ( KEYWORD_24 ) | ( KEYWORD_21 ) );
    public final void rule__ExpressionFragmentSecond__CAlternatives_0_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1217:1: ( ( KEYWORD_24 ) | ( KEYWORD_21 ) )
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==KEYWORD_24) ) {
                alt3=1;
            }
            else if ( (LA3_0==KEYWORD_21) ) {
                alt3=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }
            switch (alt3) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1218:1: ( KEYWORD_24 )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1218:1: ( KEYWORD_24 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1219:1: KEYWORD_24
                    {
                     before(grammarAccess.getExpressionFragmentSecondAccess().getCANDKeyword_0_0_0()); 
                    match(input,KEYWORD_24,FOLLOW_KEYWORD_24_in_rule__ExpressionFragmentSecond__CAlternatives_0_02545); 
                     after(grammarAccess.getExpressionFragmentSecondAccess().getCANDKeyword_0_0_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1226:6: ( KEYWORD_21 )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1226:6: ( KEYWORD_21 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1227:1: KEYWORD_21
                    {
                     before(grammarAccess.getExpressionFragmentSecondAccess().getCORKeyword_0_0_1()); 
                    match(input,KEYWORD_21,FOLLOW_KEYWORD_21_in_rule__ExpressionFragmentSecond__CAlternatives_0_02565); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1239:1: rule__ExpressionFragment__Alternatives : ( ( ( rule__ExpressionFragment__ExpgroupAssignment_0 ) ) | ( ( rule__ExpressionFragment__ExpAssignment_1 ) ) | ( ( rule__ExpressionFragment__XexpAssignment_2 ) ) );
    public final void rule__ExpressionFragment__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1243:1: ( ( ( rule__ExpressionFragment__ExpgroupAssignment_0 ) ) | ( ( rule__ExpressionFragment__ExpAssignment_1 ) ) | ( ( rule__ExpressionFragment__XexpAssignment_2 ) ) )
            int alt4=3;
            switch ( input.LA(1) ) {
            case KEYWORD_1:
                {
                int LA4_1 = input.LA(2);

                if ( (LA4_1==KEYWORD_37) ) {
                    alt4=2;
                }
                else if ( (LA4_1==KEYWORD_23||(LA4_1>=KEYWORD_13 && LA4_1<=KEYWORD_14)||LA4_1==KEYWORD_1||(LA4_1>=RULE_SIGNED_INT && LA4_1<=RULE_SIGNED_DOUBLE)||LA4_1==RULE_ID||LA4_1==RULE_STRING) ) {
                    alt4=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 4, 1, input);

                    throw nvae;
                }
                }
                break;
            case KEYWORD_23:
            case KEYWORD_13:
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
            case KEYWORD_14:
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
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1244:1: ( ( rule__ExpressionFragment__ExpgroupAssignment_0 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1244:1: ( ( rule__ExpressionFragment__ExpgroupAssignment_0 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1245:1: ( rule__ExpressionFragment__ExpgroupAssignment_0 )
                    {
                     before(grammarAccess.getExpressionFragmentAccess().getExpgroupAssignment_0()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1246:1: ( rule__ExpressionFragment__ExpgroupAssignment_0 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1246:2: rule__ExpressionFragment__ExpgroupAssignment_0
                    {
                    pushFollow(FOLLOW_rule__ExpressionFragment__ExpgroupAssignment_0_in_rule__ExpressionFragment__Alternatives2599);
                    rule__ExpressionFragment__ExpgroupAssignment_0();

                    state._fsp--;


                    }

                     after(grammarAccess.getExpressionFragmentAccess().getExpgroupAssignment_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1250:6: ( ( rule__ExpressionFragment__ExpAssignment_1 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1250:6: ( ( rule__ExpressionFragment__ExpAssignment_1 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1251:1: ( rule__ExpressionFragment__ExpAssignment_1 )
                    {
                     before(grammarAccess.getExpressionFragmentAccess().getExpAssignment_1()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1252:1: ( rule__ExpressionFragment__ExpAssignment_1 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1252:2: rule__ExpressionFragment__ExpAssignment_1
                    {
                    pushFollow(FOLLOW_rule__ExpressionFragment__ExpAssignment_1_in_rule__ExpressionFragment__Alternatives2617);
                    rule__ExpressionFragment__ExpAssignment_1();

                    state._fsp--;


                    }

                     after(grammarAccess.getExpressionFragmentAccess().getExpAssignment_1()); 

                    }


                    }
                    break;
                case 3 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1256:6: ( ( rule__ExpressionFragment__XexpAssignment_2 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1256:6: ( ( rule__ExpressionFragment__XexpAssignment_2 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1257:1: ( rule__ExpressionFragment__XexpAssignment_2 )
                    {
                     before(grammarAccess.getExpressionFragmentAccess().getXexpAssignment_2()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1258:1: ( rule__ExpressionFragment__XexpAssignment_2 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1258:2: rule__ExpressionFragment__XexpAssignment_2
                    {
                    pushFollow(FOLLOW_rule__ExpressionFragment__XexpAssignment_2_in_rule__ExpressionFragment__Alternatives2635);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1267:1: rule__Expression__Alternatives_1 : ( ( ( rule__Expression__IsnullAssignment_1_0 ) ) | ( ( rule__Expression__InAssignment_1_1 ) ) | ( ( rule__Expression__BetweenAssignment_1_2 ) ) | ( ( rule__Expression__LikeAssignment_1_3 ) ) | ( ( rule__Expression__CompAssignment_1_4 ) ) );
    public final void rule__Expression__Alternatives_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1271:1: ( ( ( rule__Expression__IsnullAssignment_1_0 ) ) | ( ( rule__Expression__InAssignment_1_1 ) ) | ( ( rule__Expression__BetweenAssignment_1_2 ) ) | ( ( rule__Expression__LikeAssignment_1_3 ) ) | ( ( rule__Expression__CompAssignment_1_4 ) ) )
            int alt5=5;
            switch ( input.LA(1) ) {
            case KEYWORD_52:
            case KEYWORD_40:
                {
                alt5=1;
                }
                break;
            case KEYWORD_36:
            case KEYWORD_19:
                {
                alt5=2;
                }
                break;
            case KEYWORD_53:
            case KEYWORD_38:
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
            case KEYWORD_15:
            case KEYWORD_16:
            case KEYWORD_17:
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
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1272:1: ( ( rule__Expression__IsnullAssignment_1_0 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1272:1: ( ( rule__Expression__IsnullAssignment_1_0 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1273:1: ( rule__Expression__IsnullAssignment_1_0 )
                    {
                     before(grammarAccess.getExpressionAccess().getIsnullAssignment_1_0()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1274:1: ( rule__Expression__IsnullAssignment_1_0 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1274:2: rule__Expression__IsnullAssignment_1_0
                    {
                    pushFollow(FOLLOW_rule__Expression__IsnullAssignment_1_0_in_rule__Expression__Alternatives_12668);
                    rule__Expression__IsnullAssignment_1_0();

                    state._fsp--;


                    }

                     after(grammarAccess.getExpressionAccess().getIsnullAssignment_1_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1278:6: ( ( rule__Expression__InAssignment_1_1 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1278:6: ( ( rule__Expression__InAssignment_1_1 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1279:1: ( rule__Expression__InAssignment_1_1 )
                    {
                     before(grammarAccess.getExpressionAccess().getInAssignment_1_1()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1280:1: ( rule__Expression__InAssignment_1_1 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1280:2: rule__Expression__InAssignment_1_1
                    {
                    pushFollow(FOLLOW_rule__Expression__InAssignment_1_1_in_rule__Expression__Alternatives_12686);
                    rule__Expression__InAssignment_1_1();

                    state._fsp--;


                    }

                     after(grammarAccess.getExpressionAccess().getInAssignment_1_1()); 

                    }


                    }
                    break;
                case 3 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1284:6: ( ( rule__Expression__BetweenAssignment_1_2 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1284:6: ( ( rule__Expression__BetweenAssignment_1_2 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1285:1: ( rule__Expression__BetweenAssignment_1_2 )
                    {
                     before(grammarAccess.getExpressionAccess().getBetweenAssignment_1_2()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1286:1: ( rule__Expression__BetweenAssignment_1_2 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1286:2: rule__Expression__BetweenAssignment_1_2
                    {
                    pushFollow(FOLLOW_rule__Expression__BetweenAssignment_1_2_in_rule__Expression__Alternatives_12704);
                    rule__Expression__BetweenAssignment_1_2();

                    state._fsp--;


                    }

                     after(grammarAccess.getExpressionAccess().getBetweenAssignment_1_2()); 

                    }


                    }
                    break;
                case 4 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1290:6: ( ( rule__Expression__LikeAssignment_1_3 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1290:6: ( ( rule__Expression__LikeAssignment_1_3 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1291:1: ( rule__Expression__LikeAssignment_1_3 )
                    {
                     before(grammarAccess.getExpressionAccess().getLikeAssignment_1_3()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1292:1: ( rule__Expression__LikeAssignment_1_3 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1292:2: rule__Expression__LikeAssignment_1_3
                    {
                    pushFollow(FOLLOW_rule__Expression__LikeAssignment_1_3_in_rule__Expression__Alternatives_12722);
                    rule__Expression__LikeAssignment_1_3();

                    state._fsp--;


                    }

                     after(grammarAccess.getExpressionAccess().getLikeAssignment_1_3()); 

                    }


                    }
                    break;
                case 5 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1296:6: ( ( rule__Expression__CompAssignment_1_4 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1296:6: ( ( rule__Expression__CompAssignment_1_4 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1297:1: ( rule__Expression__CompAssignment_1_4 )
                    {
                     before(grammarAccess.getExpressionAccess().getCompAssignment_1_4()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1298:1: ( rule__Expression__CompAssignment_1_4 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1298:2: rule__Expression__CompAssignment_1_4
                    {
                    pushFollow(FOLLOW_rule__Expression__CompAssignment_1_4_in_rule__Expression__Alternatives_12740);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1307:1: rule__Expression__IsnullAlternatives_1_0_0 : ( ( KEYWORD_40 ) | ( KEYWORD_52 ) );
    public final void rule__Expression__IsnullAlternatives_1_0_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1311:1: ( ( KEYWORD_40 ) | ( KEYWORD_52 ) )
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==KEYWORD_40) ) {
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
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1312:1: ( KEYWORD_40 )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1312:1: ( KEYWORD_40 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1313:1: KEYWORD_40
                    {
                     before(grammarAccess.getExpressionAccess().getIsnullISNULLKeyword_1_0_0_0()); 
                    match(input,KEYWORD_40,FOLLOW_KEYWORD_40_in_rule__Expression__IsnullAlternatives_1_0_02774); 
                     after(grammarAccess.getExpressionAccess().getIsnullISNULLKeyword_1_0_0_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1320:6: ( KEYWORD_52 )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1320:6: ( KEYWORD_52 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1321:1: KEYWORD_52
                    {
                     before(grammarAccess.getExpressionAccess().getIsnullISNOTNULLKeyword_1_0_0_1()); 
                    match(input,KEYWORD_52,FOLLOW_KEYWORD_52_in_rule__Expression__IsnullAlternatives_1_0_02794); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1333:1: rule__Comparison__OperatorAlternatives_0_0 : ( ( KEYWORD_10 ) | ( KEYWORD_17 ) | ( KEYWORD_8 ) | ( KEYWORD_15 ) | ( KEYWORD_9 ) | ( KEYWORD_16 ) );
    public final void rule__Comparison__OperatorAlternatives_0_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1337:1: ( ( KEYWORD_10 ) | ( KEYWORD_17 ) | ( KEYWORD_8 ) | ( KEYWORD_15 ) | ( KEYWORD_9 ) | ( KEYWORD_16 ) )
            int alt7=6;
            switch ( input.LA(1) ) {
            case KEYWORD_10:
                {
                alt7=1;
                }
                break;
            case KEYWORD_17:
                {
                alt7=2;
                }
                break;
            case KEYWORD_8:
                {
                alt7=3;
                }
                break;
            case KEYWORD_15:
                {
                alt7=4;
                }
                break;
            case KEYWORD_9:
                {
                alt7=5;
                }
                break;
            case KEYWORD_16:
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
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1338:1: ( KEYWORD_10 )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1338:1: ( KEYWORD_10 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1339:1: KEYWORD_10
                    {
                     before(grammarAccess.getComparisonAccess().getOperatorGreaterThanSignKeyword_0_0_0()); 
                    match(input,KEYWORD_10,FOLLOW_KEYWORD_10_in_rule__Comparison__OperatorAlternatives_0_02829); 
                     after(grammarAccess.getComparisonAccess().getOperatorGreaterThanSignKeyword_0_0_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1346:6: ( KEYWORD_17 )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1346:6: ( KEYWORD_17 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1347:1: KEYWORD_17
                    {
                     before(grammarAccess.getComparisonAccess().getOperatorGreaterThanSignEqualsSignKeyword_0_0_1()); 
                    match(input,KEYWORD_17,FOLLOW_KEYWORD_17_in_rule__Comparison__OperatorAlternatives_0_02849); 
                     after(grammarAccess.getComparisonAccess().getOperatorGreaterThanSignEqualsSignKeyword_0_0_1()); 

                    }


                    }
                    break;
                case 3 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1354:6: ( KEYWORD_8 )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1354:6: ( KEYWORD_8 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1355:1: KEYWORD_8
                    {
                     before(grammarAccess.getComparisonAccess().getOperatorLessThanSignKeyword_0_0_2()); 
                    match(input,KEYWORD_8,FOLLOW_KEYWORD_8_in_rule__Comparison__OperatorAlternatives_0_02869); 
                     after(grammarAccess.getComparisonAccess().getOperatorLessThanSignKeyword_0_0_2()); 

                    }


                    }
                    break;
                case 4 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1362:6: ( KEYWORD_15 )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1362:6: ( KEYWORD_15 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1363:1: KEYWORD_15
                    {
                     before(grammarAccess.getComparisonAccess().getOperatorLessThanSignEqualsSignKeyword_0_0_3()); 
                    match(input,KEYWORD_15,FOLLOW_KEYWORD_15_in_rule__Comparison__OperatorAlternatives_0_02889); 
                     after(grammarAccess.getComparisonAccess().getOperatorLessThanSignEqualsSignKeyword_0_0_3()); 

                    }


                    }
                    break;
                case 5 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1370:6: ( KEYWORD_9 )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1370:6: ( KEYWORD_9 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1371:1: KEYWORD_9
                    {
                     before(grammarAccess.getComparisonAccess().getOperatorEqualsSignKeyword_0_0_4()); 
                    match(input,KEYWORD_9,FOLLOW_KEYWORD_9_in_rule__Comparison__OperatorAlternatives_0_02909); 
                     after(grammarAccess.getComparisonAccess().getOperatorEqualsSignKeyword_0_0_4()); 

                    }


                    }
                    break;
                case 6 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1378:6: ( KEYWORD_16 )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1378:6: ( KEYWORD_16 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1379:1: KEYWORD_16
                    {
                     before(grammarAccess.getComparisonAccess().getOperatorLessThanSignGreaterThanSignKeyword_0_0_5()); 
                    match(input,KEYWORD_16,FOLLOW_KEYWORD_16_in_rule__Comparison__OperatorAlternatives_0_02929); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1391:1: rule__Like__OpLikeAlternatives_0_0 : ( ( KEYWORD_29 ) | ( KEYWORD_44 ) );
    public final void rule__Like__OpLikeAlternatives_0_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1395:1: ( ( KEYWORD_29 ) | ( KEYWORD_44 ) )
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
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1396:1: ( KEYWORD_29 )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1396:1: ( KEYWORD_29 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1397:1: KEYWORD_29
                    {
                     before(grammarAccess.getLikeAccess().getOpLikeLIKEKeyword_0_0_0()); 
                    match(input,KEYWORD_29,FOLLOW_KEYWORD_29_in_rule__Like__OpLikeAlternatives_0_02964); 
                     after(grammarAccess.getLikeAccess().getOpLikeLIKEKeyword_0_0_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1404:6: ( KEYWORD_44 )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1404:6: ( KEYWORD_44 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1405:1: KEYWORD_44
                    {
                     before(grammarAccess.getLikeAccess().getOpLikeNOTLIKEKeyword_0_0_1()); 
                    match(input,KEYWORD_44,FOLLOW_KEYWORD_44_in_rule__Like__OpLikeAlternatives_0_02984); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1417:1: rule__Between__OpBetweenAlternatives_0_0 : ( ( KEYWORD_38 ) | ( KEYWORD_53 ) );
    public final void rule__Between__OpBetweenAlternatives_0_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1421:1: ( ( KEYWORD_38 ) | ( KEYWORD_53 ) )
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==KEYWORD_38) ) {
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
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1422:1: ( KEYWORD_38 )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1422:1: ( KEYWORD_38 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1423:1: KEYWORD_38
                    {
                     before(grammarAccess.getBetweenAccess().getOpBetweenBETWEENKeyword_0_0_0()); 
                    match(input,KEYWORD_38,FOLLOW_KEYWORD_38_in_rule__Between__OpBetweenAlternatives_0_03019); 
                     after(grammarAccess.getBetweenAccess().getOpBetweenBETWEENKeyword_0_0_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1430:6: ( KEYWORD_53 )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1430:6: ( KEYWORD_53 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1431:1: KEYWORD_53
                    {
                     before(grammarAccess.getBetweenAccess().getOpBetweenNOTBETWEENKeyword_0_0_1()); 
                    match(input,KEYWORD_53,FOLLOW_KEYWORD_53_in_rule__Between__OpBetweenAlternatives_0_03039); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1443:1: rule__InOperator__OpAlternatives_1_0 : ( ( KEYWORD_36 ) | ( KEYWORD_19 ) );
    public final void rule__InOperator__OpAlternatives_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1447:1: ( ( KEYWORD_36 ) | ( KEYWORD_19 ) )
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==KEYWORD_36) ) {
                alt10=1;
            }
            else if ( (LA10_0==KEYWORD_19) ) {
                alt10=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 10, 0, input);

                throw nvae;
            }
            switch (alt10) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1448:1: ( KEYWORD_36 )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1448:1: ( KEYWORD_36 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1449:1: KEYWORD_36
                    {
                     before(grammarAccess.getInOperatorAccess().getOpNOTINKeyword_1_0_0()); 
                    match(input,KEYWORD_36,FOLLOW_KEYWORD_36_in_rule__InOperator__OpAlternatives_1_03074); 
                     after(grammarAccess.getInOperatorAccess().getOpNOTINKeyword_1_0_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1456:6: ( KEYWORD_19 )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1456:6: ( KEYWORD_19 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1457:1: KEYWORD_19
                    {
                     before(grammarAccess.getInOperatorAccess().getOpINKeyword_1_0_1()); 
                    match(input,KEYWORD_19,FOLLOW_KEYWORD_19_in_rule__InOperator__OpAlternatives_1_03094); 
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


    // $ANTLR start "rule__InOperator__Alternatives_3"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1469:1: rule__InOperator__Alternatives_3 : ( ( ( rule__InOperator__SubqueryAssignment_3_0 ) ) | ( ( rule__InOperator__OpListAssignment_3_1 ) ) );
    public final void rule__InOperator__Alternatives_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1473:1: ( ( ( rule__InOperator__SubqueryAssignment_3_0 ) ) | ( ( rule__InOperator__OpListAssignment_3_1 ) ) )
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==KEYWORD_1) ) {
                alt11=1;
            }
            else if ( (LA11_0==KEYWORD_23||LA11_0==KEYWORD_13||(LA11_0>=RULE_SIGNED_INT && LA11_0<=RULE_SIGNED_DOUBLE)||LA11_0==RULE_STRING) ) {
                alt11=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 11, 0, input);

                throw nvae;
            }
            switch (alt11) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1474:1: ( ( rule__InOperator__SubqueryAssignment_3_0 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1474:1: ( ( rule__InOperator__SubqueryAssignment_3_0 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1475:1: ( rule__InOperator__SubqueryAssignment_3_0 )
                    {
                     before(grammarAccess.getInOperatorAccess().getSubqueryAssignment_3_0()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1476:1: ( rule__InOperator__SubqueryAssignment_3_0 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1476:2: rule__InOperator__SubqueryAssignment_3_0
                    {
                    pushFollow(FOLLOW_rule__InOperator__SubqueryAssignment_3_0_in_rule__InOperator__Alternatives_33128);
                    rule__InOperator__SubqueryAssignment_3_0();

                    state._fsp--;


                    }

                     after(grammarAccess.getInOperatorAccess().getSubqueryAssignment_3_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1480:6: ( ( rule__InOperator__OpListAssignment_3_1 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1480:6: ( ( rule__InOperator__OpListAssignment_3_1 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1481:1: ( rule__InOperator__OpListAssignment_3_1 )
                    {
                     before(grammarAccess.getInOperatorAccess().getOpListAssignment_3_1()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1482:1: ( rule__InOperator__OpListAssignment_3_1 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1482:2: rule__InOperator__OpListAssignment_3_1
                    {
                    pushFollow(FOLLOW_rule__InOperator__OpListAssignment_3_1_in_rule__InOperator__Alternatives_33146);
                    rule__InOperator__OpListAssignment_3_1();

                    state._fsp--;


                    }

                     after(grammarAccess.getInOperatorAccess().getOpListAssignment_3_1()); 

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
    // $ANTLR end "rule__InOperator__Alternatives_3"


    // $ANTLR start "rule__Operand__Alternatives_1_1_0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1491:1: rule__Operand__Alternatives_1_1_0 : ( ( KEYWORD_3 ) | ( KEYWORD_5 ) | ( RULE_STAR ) | ( KEYWORD_7 ) | ( KEYWORD_22 ) );
    public final void rule__Operand__Alternatives_1_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1495:1: ( ( KEYWORD_3 ) | ( KEYWORD_5 ) | ( RULE_STAR ) | ( KEYWORD_7 ) | ( KEYWORD_22 ) )
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
            case KEYWORD_22:
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
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1496:1: ( KEYWORD_3 )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1496:1: ( KEYWORD_3 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1497:1: KEYWORD_3
                    {
                     before(grammarAccess.getOperandAccess().getPlusSignKeyword_1_1_0_0()); 
                    match(input,KEYWORD_3,FOLLOW_KEYWORD_3_in_rule__Operand__Alternatives_1_1_03180); 
                     after(grammarAccess.getOperandAccess().getPlusSignKeyword_1_1_0_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1504:6: ( KEYWORD_5 )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1504:6: ( KEYWORD_5 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1505:1: KEYWORD_5
                    {
                     before(grammarAccess.getOperandAccess().getHyphenMinusKeyword_1_1_0_1()); 
                    match(input,KEYWORD_5,FOLLOW_KEYWORD_5_in_rule__Operand__Alternatives_1_1_03200); 
                     after(grammarAccess.getOperandAccess().getHyphenMinusKeyword_1_1_0_1()); 

                    }


                    }
                    break;
                case 3 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1512:6: ( RULE_STAR )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1512:6: ( RULE_STAR )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1513:1: RULE_STAR
                    {
                     before(grammarAccess.getOperandAccess().getSTARTerminalRuleCall_1_1_0_2()); 
                    match(input,RULE_STAR,FOLLOW_RULE_STAR_in_rule__Operand__Alternatives_1_1_03219); 
                     after(grammarAccess.getOperandAccess().getSTARTerminalRuleCall_1_1_0_2()); 

                    }


                    }
                    break;
                case 4 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1518:6: ( KEYWORD_7 )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1518:6: ( KEYWORD_7 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1519:1: KEYWORD_7
                    {
                     before(grammarAccess.getOperandAccess().getSolidusKeyword_1_1_0_3()); 
                    match(input,KEYWORD_7,FOLLOW_KEYWORD_7_in_rule__Operand__Alternatives_1_1_03237); 
                     after(grammarAccess.getOperandAccess().getSolidusKeyword_1_1_0_3()); 

                    }


                    }
                    break;
                case 5 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1526:6: ( KEYWORD_22 )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1526:6: ( KEYWORD_22 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1527:1: KEYWORD_22
                    {
                     before(grammarAccess.getOperandAccess().getVerticalLineVerticalLineKeyword_1_1_0_4()); 
                    match(input,KEYWORD_22,FOLLOW_KEYWORD_22_in_rule__Operand__Alternatives_1_1_03257); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1539:1: rule__OperandFragment__Alternatives : ( ( ( rule__OperandFragment__ColumnAssignment_0 ) ) | ( ( rule__OperandFragment__XopAssignment_1 ) ) | ( ( rule__OperandFragment__SubqAssignment_2 ) ) );
    public final void rule__OperandFragment__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1543:1: ( ( ( rule__OperandFragment__ColumnAssignment_0 ) ) | ( ( rule__OperandFragment__XopAssignment_1 ) ) | ( ( rule__OperandFragment__SubqAssignment_2 ) ) )
            int alt13=3;
            switch ( input.LA(1) ) {
            case RULE_ID:
                {
                alt13=1;
                }
                break;
            case KEYWORD_23:
            case KEYWORD_13:
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
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1544:1: ( ( rule__OperandFragment__ColumnAssignment_0 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1544:1: ( ( rule__OperandFragment__ColumnAssignment_0 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1545:1: ( rule__OperandFragment__ColumnAssignment_0 )
                    {
                     before(grammarAccess.getOperandFragmentAccess().getColumnAssignment_0()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1546:1: ( rule__OperandFragment__ColumnAssignment_0 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1546:2: rule__OperandFragment__ColumnAssignment_0
                    {
                    pushFollow(FOLLOW_rule__OperandFragment__ColumnAssignment_0_in_rule__OperandFragment__Alternatives3291);
                    rule__OperandFragment__ColumnAssignment_0();

                    state._fsp--;


                    }

                     after(grammarAccess.getOperandFragmentAccess().getColumnAssignment_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1550:6: ( ( rule__OperandFragment__XopAssignment_1 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1550:6: ( ( rule__OperandFragment__XopAssignment_1 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1551:1: ( rule__OperandFragment__XopAssignment_1 )
                    {
                     before(grammarAccess.getOperandFragmentAccess().getXopAssignment_1()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1552:1: ( rule__OperandFragment__XopAssignment_1 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1552:2: rule__OperandFragment__XopAssignment_1
                    {
                    pushFollow(FOLLOW_rule__OperandFragment__XopAssignment_1_in_rule__OperandFragment__Alternatives3309);
                    rule__OperandFragment__XopAssignment_1();

                    state._fsp--;


                    }

                     after(grammarAccess.getOperandFragmentAccess().getXopAssignment_1()); 

                    }


                    }
                    break;
                case 3 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1556:6: ( ( rule__OperandFragment__SubqAssignment_2 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1556:6: ( ( rule__OperandFragment__SubqAssignment_2 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1557:1: ( rule__OperandFragment__SubqAssignment_2 )
                    {
                     before(grammarAccess.getOperandFragmentAccess().getSubqAssignment_2()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1558:1: ( rule__OperandFragment__SubqAssignment_2 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1558:2: rule__OperandFragment__SubqAssignment_2
                    {
                    pushFollow(FOLLOW_rule__OperandFragment__SubqAssignment_2_in_rule__OperandFragment__Alternatives3327);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1567:1: rule__XOperandFragment__Alternatives : ( ( ( rule__XOperandFragment__ParamAssignment_0 ) ) | ( ( rule__XOperandFragment__EparamAssignment_1 ) ) | ( ( rule__XOperandFragment__ScalarAssignment_2 ) ) );
    public final void rule__XOperandFragment__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1571:1: ( ( ( rule__XOperandFragment__ParamAssignment_0 ) ) | ( ( rule__XOperandFragment__EparamAssignment_1 ) ) | ( ( rule__XOperandFragment__ScalarAssignment_2 ) ) )
            int alt14=3;
            switch ( input.LA(1) ) {
            case KEYWORD_13:
                {
                alt14=1;
                }
                break;
            case KEYWORD_23:
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
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1572:1: ( ( rule__XOperandFragment__ParamAssignment_0 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1572:1: ( ( rule__XOperandFragment__ParamAssignment_0 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1573:1: ( rule__XOperandFragment__ParamAssignment_0 )
                    {
                     before(grammarAccess.getXOperandFragmentAccess().getParamAssignment_0()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1574:1: ( rule__XOperandFragment__ParamAssignment_0 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1574:2: rule__XOperandFragment__ParamAssignment_0
                    {
                    pushFollow(FOLLOW_rule__XOperandFragment__ParamAssignment_0_in_rule__XOperandFragment__Alternatives3360);
                    rule__XOperandFragment__ParamAssignment_0();

                    state._fsp--;


                    }

                     after(grammarAccess.getXOperandFragmentAccess().getParamAssignment_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1578:6: ( ( rule__XOperandFragment__EparamAssignment_1 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1578:6: ( ( rule__XOperandFragment__EparamAssignment_1 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1579:1: ( rule__XOperandFragment__EparamAssignment_1 )
                    {
                     before(grammarAccess.getXOperandFragmentAccess().getEparamAssignment_1()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1580:1: ( rule__XOperandFragment__EparamAssignment_1 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1580:2: rule__XOperandFragment__EparamAssignment_1
                    {
                    pushFollow(FOLLOW_rule__XOperandFragment__EparamAssignment_1_in_rule__XOperandFragment__Alternatives3378);
                    rule__XOperandFragment__EparamAssignment_1();

                    state._fsp--;


                    }

                     after(grammarAccess.getXOperandFragmentAccess().getEparamAssignment_1()); 

                    }


                    }
                    break;
                case 3 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1584:6: ( ( rule__XOperandFragment__ScalarAssignment_2 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1584:6: ( ( rule__XOperandFragment__ScalarAssignment_2 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1585:1: ( rule__XOperandFragment__ScalarAssignment_2 )
                    {
                     before(grammarAccess.getXOperandFragmentAccess().getScalarAssignment_2()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1586:1: ( rule__XOperandFragment__ScalarAssignment_2 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1586:2: rule__XOperandFragment__ScalarAssignment_2
                    {
                    pushFollow(FOLLOW_rule__XOperandFragment__ScalarAssignment_2_in_rule__XOperandFragment__Alternatives3396);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1595:1: rule__ScalarOperand__Alternatives : ( ( ( rule__ScalarOperand__SointAssignment_0 ) ) | ( ( rule__ScalarOperand__SostrAssignment_1 ) ) | ( ( rule__ScalarOperand__SodblAssignment_2 ) ) | ( ( rule__ScalarOperand__SodateAssignment_3 ) ) | ( ( rule__ScalarOperand__SotimeAssignment_4 ) ) | ( ( rule__ScalarOperand__SodtAssignment_5 ) ) );
    public final void rule__ScalarOperand__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1599:1: ( ( ( rule__ScalarOperand__SointAssignment_0 ) ) | ( ( rule__ScalarOperand__SostrAssignment_1 ) ) | ( ( rule__ScalarOperand__SodblAssignment_2 ) ) | ( ( rule__ScalarOperand__SodateAssignment_3 ) ) | ( ( rule__ScalarOperand__SotimeAssignment_4 ) ) | ( ( rule__ScalarOperand__SodtAssignment_5 ) ) )
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
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1600:1: ( ( rule__ScalarOperand__SointAssignment_0 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1600:1: ( ( rule__ScalarOperand__SointAssignment_0 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1601:1: ( rule__ScalarOperand__SointAssignment_0 )
                    {
                     before(grammarAccess.getScalarOperandAccess().getSointAssignment_0()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1602:1: ( rule__ScalarOperand__SointAssignment_0 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1602:2: rule__ScalarOperand__SointAssignment_0
                    {
                    pushFollow(FOLLOW_rule__ScalarOperand__SointAssignment_0_in_rule__ScalarOperand__Alternatives3429);
                    rule__ScalarOperand__SointAssignment_0();

                    state._fsp--;


                    }

                     after(grammarAccess.getScalarOperandAccess().getSointAssignment_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1606:6: ( ( rule__ScalarOperand__SostrAssignment_1 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1606:6: ( ( rule__ScalarOperand__SostrAssignment_1 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1607:1: ( rule__ScalarOperand__SostrAssignment_1 )
                    {
                     before(grammarAccess.getScalarOperandAccess().getSostrAssignment_1()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1608:1: ( rule__ScalarOperand__SostrAssignment_1 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1608:2: rule__ScalarOperand__SostrAssignment_1
                    {
                    pushFollow(FOLLOW_rule__ScalarOperand__SostrAssignment_1_in_rule__ScalarOperand__Alternatives3447);
                    rule__ScalarOperand__SostrAssignment_1();

                    state._fsp--;


                    }

                     after(grammarAccess.getScalarOperandAccess().getSostrAssignment_1()); 

                    }


                    }
                    break;
                case 3 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1612:6: ( ( rule__ScalarOperand__SodblAssignment_2 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1612:6: ( ( rule__ScalarOperand__SodblAssignment_2 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1613:1: ( rule__ScalarOperand__SodblAssignment_2 )
                    {
                     before(grammarAccess.getScalarOperandAccess().getSodblAssignment_2()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1614:1: ( rule__ScalarOperand__SodblAssignment_2 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1614:2: rule__ScalarOperand__SodblAssignment_2
                    {
                    pushFollow(FOLLOW_rule__ScalarOperand__SodblAssignment_2_in_rule__ScalarOperand__Alternatives3465);
                    rule__ScalarOperand__SodblAssignment_2();

                    state._fsp--;


                    }

                     after(grammarAccess.getScalarOperandAccess().getSodblAssignment_2()); 

                    }


                    }
                    break;
                case 4 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1618:6: ( ( rule__ScalarOperand__SodateAssignment_3 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1618:6: ( ( rule__ScalarOperand__SodateAssignment_3 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1619:1: ( rule__ScalarOperand__SodateAssignment_3 )
                    {
                     before(grammarAccess.getScalarOperandAccess().getSodateAssignment_3()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1620:1: ( rule__ScalarOperand__SodateAssignment_3 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1620:2: rule__ScalarOperand__SodateAssignment_3
                    {
                    pushFollow(FOLLOW_rule__ScalarOperand__SodateAssignment_3_in_rule__ScalarOperand__Alternatives3483);
                    rule__ScalarOperand__SodateAssignment_3();

                    state._fsp--;


                    }

                     after(grammarAccess.getScalarOperandAccess().getSodateAssignment_3()); 

                    }


                    }
                    break;
                case 5 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1624:6: ( ( rule__ScalarOperand__SotimeAssignment_4 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1624:6: ( ( rule__ScalarOperand__SotimeAssignment_4 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1625:1: ( rule__ScalarOperand__SotimeAssignment_4 )
                    {
                     before(grammarAccess.getScalarOperandAccess().getSotimeAssignment_4()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1626:1: ( rule__ScalarOperand__SotimeAssignment_4 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1626:2: rule__ScalarOperand__SotimeAssignment_4
                    {
                    pushFollow(FOLLOW_rule__ScalarOperand__SotimeAssignment_4_in_rule__ScalarOperand__Alternatives3501);
                    rule__ScalarOperand__SotimeAssignment_4();

                    state._fsp--;


                    }

                     after(grammarAccess.getScalarOperandAccess().getSotimeAssignment_4()); 

                    }


                    }
                    break;
                case 6 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1630:6: ( ( rule__ScalarOperand__SodtAssignment_5 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1630:6: ( ( rule__ScalarOperand__SodtAssignment_5 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1631:1: ( rule__ScalarOperand__SodtAssignment_5 )
                    {
                     before(grammarAccess.getScalarOperandAccess().getSodtAssignment_5()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1632:1: ( rule__ScalarOperand__SodtAssignment_5 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1632:2: rule__ScalarOperand__SodtAssignment_5
                    {
                    pushFollow(FOLLOW_rule__ScalarOperand__SodtAssignment_5_in_rule__ScalarOperand__Alternatives3519);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1641:1: rule__XFunction__Alternatives : ( ( ( KEYWORD_19 ) ) | ( ( KEYWORD_32 ) ) | ( ( KEYWORD_30 ) ) | ( ( KEYWORD_45 ) ) | ( ( KEYWORD_28 ) ) | ( ( KEYWORD_39 ) ) | ( ( KEYWORD_31 ) ) | ( ( KEYWORD_48 ) ) | ( ( KEYWORD_38 ) ) | ( ( KEYWORD_49 ) ) | ( ( KEYWORD_47 ) ) | ( ( KEYWORD_41 ) ) );
    public final void rule__XFunction__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1645:1: ( ( ( KEYWORD_19 ) ) | ( ( KEYWORD_32 ) ) | ( ( KEYWORD_30 ) ) | ( ( KEYWORD_45 ) ) | ( ( KEYWORD_28 ) ) | ( ( KEYWORD_39 ) ) | ( ( KEYWORD_31 ) ) | ( ( KEYWORD_48 ) ) | ( ( KEYWORD_38 ) ) | ( ( KEYWORD_49 ) ) | ( ( KEYWORD_47 ) ) | ( ( KEYWORD_41 ) ) )
            int alt16=12;
            switch ( input.LA(1) ) {
            case KEYWORD_19:
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
            case KEYWORD_39:
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
            case KEYWORD_38:
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
            case KEYWORD_41:
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
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1646:1: ( ( KEYWORD_19 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1646:1: ( ( KEYWORD_19 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1647:1: ( KEYWORD_19 )
                    {
                     before(grammarAccess.getXFunctionAccess().getXinEnumLiteralDeclaration_0()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1648:1: ( KEYWORD_19 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1648:3: KEYWORD_19
                    {
                    match(input,KEYWORD_19,FOLLOW_KEYWORD_19_in_rule__XFunction__Alternatives3553); 

                    }

                     after(grammarAccess.getXFunctionAccess().getXinEnumLiteralDeclaration_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1653:6: ( ( KEYWORD_32 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1653:6: ( ( KEYWORD_32 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1654:1: ( KEYWORD_32 )
                    {
                     before(grammarAccess.getXFunctionAccess().getXnotinEnumLiteralDeclaration_1()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1655:1: ( KEYWORD_32 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1655:3: KEYWORD_32
                    {
                    match(input,KEYWORD_32,FOLLOW_KEYWORD_32_in_rule__XFunction__Alternatives3573); 

                    }

                     after(grammarAccess.getXFunctionAccess().getXnotinEnumLiteralDeclaration_1()); 

                    }


                    }
                    break;
                case 3 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1660:6: ( ( KEYWORD_30 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1660:6: ( ( KEYWORD_30 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1661:1: ( KEYWORD_30 )
                    {
                     before(grammarAccess.getXFunctionAccess().getXeqEnumLiteralDeclaration_2()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1662:1: ( KEYWORD_30 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1662:3: KEYWORD_30
                    {
                    match(input,KEYWORD_30,FOLLOW_KEYWORD_30_in_rule__XFunction__Alternatives3593); 

                    }

                     after(grammarAccess.getXFunctionAccess().getXeqEnumLiteralDeclaration_2()); 

                    }


                    }
                    break;
                case 4 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1667:6: ( ( KEYWORD_45 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1667:6: ( ( KEYWORD_45 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1668:1: ( KEYWORD_45 )
                    {
                     before(grammarAccess.getXFunctionAccess().getXnoteqEnumLiteralDeclaration_3()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1669:1: ( KEYWORD_45 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1669:3: KEYWORD_45
                    {
                    match(input,KEYWORD_45,FOLLOW_KEYWORD_45_in_rule__XFunction__Alternatives3613); 

                    }

                     after(grammarAccess.getXFunctionAccess().getXnoteqEnumLiteralDeclaration_3()); 

                    }


                    }
                    break;
                case 5 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1674:6: ( ( KEYWORD_28 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1674:6: ( ( KEYWORD_28 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1675:1: ( KEYWORD_28 )
                    {
                     before(grammarAccess.getXFunctionAccess().getXlsEnumLiteralDeclaration_4()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1676:1: ( KEYWORD_28 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1676:3: KEYWORD_28
                    {
                    match(input,KEYWORD_28,FOLLOW_KEYWORD_28_in_rule__XFunction__Alternatives3633); 

                    }

                     after(grammarAccess.getXFunctionAccess().getXlsEnumLiteralDeclaration_4()); 

                    }


                    }
                    break;
                case 6 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1681:6: ( ( KEYWORD_39 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1681:6: ( ( KEYWORD_39 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1682:1: ( KEYWORD_39 )
                    {
                     before(grammarAccess.getXFunctionAccess().getXgtEnumLiteralDeclaration_5()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1683:1: ( KEYWORD_39 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1683:3: KEYWORD_39
                    {
                    match(input,KEYWORD_39,FOLLOW_KEYWORD_39_in_rule__XFunction__Alternatives3653); 

                    }

                     after(grammarAccess.getXFunctionAccess().getXgtEnumLiteralDeclaration_5()); 

                    }


                    }
                    break;
                case 7 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1688:6: ( ( KEYWORD_31 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1688:6: ( ( KEYWORD_31 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1689:1: ( KEYWORD_31 )
                    {
                     before(grammarAccess.getXFunctionAccess().getXlsrEnumLiteralDeclaration_6()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1690:1: ( KEYWORD_31 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1690:3: KEYWORD_31
                    {
                    match(input,KEYWORD_31,FOLLOW_KEYWORD_31_in_rule__XFunction__Alternatives3673); 

                    }

                     after(grammarAccess.getXFunctionAccess().getXlsrEnumLiteralDeclaration_6()); 

                    }


                    }
                    break;
                case 8 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1695:6: ( ( KEYWORD_48 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1695:6: ( ( KEYWORD_48 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1696:1: ( KEYWORD_48 )
                    {
                     before(grammarAccess.getXFunctionAccess().getXgtlEnumLiteralDeclaration_7()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1697:1: ( KEYWORD_48 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1697:3: KEYWORD_48
                    {
                    match(input,KEYWORD_48,FOLLOW_KEYWORD_48_in_rule__XFunction__Alternatives3693); 

                    }

                     after(grammarAccess.getXFunctionAccess().getXgtlEnumLiteralDeclaration_7()); 

                    }


                    }
                    break;
                case 9 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1702:6: ( ( KEYWORD_38 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1702:6: ( ( KEYWORD_38 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1703:1: ( KEYWORD_38 )
                    {
                     before(grammarAccess.getXFunctionAccess().getXbwnEnumLiteralDeclaration_8()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1704:1: ( KEYWORD_38 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1704:3: KEYWORD_38
                    {
                    match(input,KEYWORD_38,FOLLOW_KEYWORD_38_in_rule__XFunction__Alternatives3713); 

                    }

                     after(grammarAccess.getXFunctionAccess().getXbwnEnumLiteralDeclaration_8()); 

                    }


                    }
                    break;
                case 10 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1709:6: ( ( KEYWORD_49 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1709:6: ( ( KEYWORD_49 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1710:1: ( KEYWORD_49 )
                    {
                     before(grammarAccess.getXFunctionAccess().getXbwncEnumLiteralDeclaration_9()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1711:1: ( KEYWORD_49 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1711:3: KEYWORD_49
                    {
                    match(input,KEYWORD_49,FOLLOW_KEYWORD_49_in_rule__XFunction__Alternatives3733); 

                    }

                     after(grammarAccess.getXFunctionAccess().getXbwncEnumLiteralDeclaration_9()); 

                    }


                    }
                    break;
                case 11 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1716:6: ( ( KEYWORD_47 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1716:6: ( ( KEYWORD_47 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1717:1: ( KEYWORD_47 )
                    {
                     before(grammarAccess.getXFunctionAccess().getXbwnlEnumLiteralDeclaration_10()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1718:1: ( KEYWORD_47 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1718:3: KEYWORD_47
                    {
                    match(input,KEYWORD_47,FOLLOW_KEYWORD_47_in_rule__XFunction__Alternatives3753); 

                    }

                     after(grammarAccess.getXFunctionAccess().getXbwnlEnumLiteralDeclaration_10()); 

                    }


                    }
                    break;
                case 12 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1723:6: ( ( KEYWORD_41 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1723:6: ( ( KEYWORD_41 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1724:1: ( KEYWORD_41 )
                    {
                     before(grammarAccess.getXFunctionAccess().getXbwnrEnumLiteralDeclaration_11()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1725:1: ( KEYWORD_41 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1725:3: KEYWORD_41
                    {
                    match(input,KEYWORD_41,FOLLOW_KEYWORD_41_in_rule__XFunction__Alternatives3773); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1735:1: rule__JoinType__Alternatives : ( ( ( KEYWORD_51 ) ) | ( ( KEYWORD_55 ) ) | ( ( KEYWORD_56 ) ) | ( ( KEYWORD_54 ) ) | ( ( KEYWORD_50 ) ) );
    public final void rule__JoinType__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1739:1: ( ( ( KEYWORD_51 ) ) | ( ( KEYWORD_55 ) ) | ( ( KEYWORD_56 ) ) | ( ( KEYWORD_54 ) ) | ( ( KEYWORD_50 ) ) )
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
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1740:1: ( ( KEYWORD_51 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1740:1: ( ( KEYWORD_51 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1741:1: ( KEYWORD_51 )
                    {
                     before(grammarAccess.getJoinTypeAccess().getInnerJoinEnumLiteralDeclaration_0()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1742:1: ( KEYWORD_51 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1742:3: KEYWORD_51
                    {
                    match(input,KEYWORD_51,FOLLOW_KEYWORD_51_in_rule__JoinType__Alternatives3808); 

                    }

                     after(grammarAccess.getJoinTypeAccess().getInnerJoinEnumLiteralDeclaration_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1747:6: ( ( KEYWORD_55 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1747:6: ( ( KEYWORD_55 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1748:1: ( KEYWORD_55 )
                    {
                     before(grammarAccess.getJoinTypeAccess().getLeftOuterJoinEnumLiteralDeclaration_1()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1749:1: ( KEYWORD_55 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1749:3: KEYWORD_55
                    {
                    match(input,KEYWORD_55,FOLLOW_KEYWORD_55_in_rule__JoinType__Alternatives3828); 

                    }

                     after(grammarAccess.getJoinTypeAccess().getLeftOuterJoinEnumLiteralDeclaration_1()); 

                    }


                    }
                    break;
                case 3 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1754:6: ( ( KEYWORD_56 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1754:6: ( ( KEYWORD_56 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1755:1: ( KEYWORD_56 )
                    {
                     before(grammarAccess.getJoinTypeAccess().getRightOuterJoinEnumLiteralDeclaration_2()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1756:1: ( KEYWORD_56 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1756:3: KEYWORD_56
                    {
                    match(input,KEYWORD_56,FOLLOW_KEYWORD_56_in_rule__JoinType__Alternatives3848); 

                    }

                     after(grammarAccess.getJoinTypeAccess().getRightOuterJoinEnumLiteralDeclaration_2()); 

                    }


                    }
                    break;
                case 4 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1761:6: ( ( KEYWORD_54 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1761:6: ( ( KEYWORD_54 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1762:1: ( KEYWORD_54 )
                    {
                     before(grammarAccess.getJoinTypeAccess().getFullOuterJoinEnumLiteralDeclaration_3()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1763:1: ( KEYWORD_54 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1763:3: KEYWORD_54
                    {
                    match(input,KEYWORD_54,FOLLOW_KEYWORD_54_in_rule__JoinType__Alternatives3868); 

                    }

                     after(grammarAccess.getJoinTypeAccess().getFullOuterJoinEnumLiteralDeclaration_3()); 

                    }


                    }
                    break;
                case 5 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1768:6: ( ( KEYWORD_50 ) )
                    {
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1768:6: ( ( KEYWORD_50 ) )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1769:1: ( KEYWORD_50 )
                    {
                     before(grammarAccess.getJoinTypeAccess().getCrossJoinEnumLiteralDeclaration_4()); 
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1770:1: ( KEYWORD_50 )
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1770:3: KEYWORD_50
                    {
                    match(input,KEYWORD_50,FOLLOW_KEYWORD_50_in_rule__JoinType__Alternatives3888); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1782:1: rule__Model__Group__0 : rule__Model__Group__0__Impl rule__Model__Group__1 ;
    public final void rule__Model__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1786:1: ( rule__Model__Group__0__Impl rule__Model__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1787:2: rule__Model__Group__0__Impl rule__Model__Group__1
            {
            pushFollow(FOLLOW_rule__Model__Group__0__Impl_in_rule__Model__Group__03920);
            rule__Model__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Model__Group__1_in_rule__Model__Group__03923);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1794:1: rule__Model__Group__0__Impl : ( ruleSelect ) ;
    public final void rule__Model__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1798:1: ( ( ruleSelect ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1799:1: ( ruleSelect )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1799:1: ( ruleSelect )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1800:1: ruleSelect
            {
             before(grammarAccess.getModelAccess().getSelectParserRuleCall_0()); 
            pushFollow(FOLLOW_ruleSelect_in_rule__Model__Group__0__Impl3950);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1811:1: rule__Model__Group__1 : rule__Model__Group__1__Impl rule__Model__Group__2 ;
    public final void rule__Model__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1815:1: ( rule__Model__Group__1__Impl rule__Model__Group__2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1816:2: rule__Model__Group__1__Impl rule__Model__Group__2
            {
            pushFollow(FOLLOW_rule__Model__Group__1__Impl_in_rule__Model__Group__13979);
            rule__Model__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Model__Group__2_in_rule__Model__Group__13982);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1823:1: rule__Model__Group__1__Impl : ( ( rule__Model__Group_1__0 )? ) ;
    public final void rule__Model__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1827:1: ( ( ( rule__Model__Group_1__0 )? ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1828:1: ( ( rule__Model__Group_1__0 )? )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1828:1: ( ( rule__Model__Group_1__0 )? )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1829:1: ( rule__Model__Group_1__0 )?
            {
             before(grammarAccess.getModelAccess().getGroup_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1830:1: ( rule__Model__Group_1__0 )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==KEYWORD_33) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1830:2: rule__Model__Group_1__0
                    {
                    pushFollow(FOLLOW_rule__Model__Group_1__0_in_rule__Model__Group__1__Impl4009);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1840:1: rule__Model__Group__2 : rule__Model__Group__2__Impl ;
    public final void rule__Model__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1844:1: ( rule__Model__Group__2__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1845:2: rule__Model__Group__2__Impl
            {
            pushFollow(FOLLOW_rule__Model__Group__2__Impl_in_rule__Model__Group__24040);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1851:1: rule__Model__Group__2__Impl : ( ( rule__Model__Group_2__0 )? ) ;
    public final void rule__Model__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1855:1: ( ( ( rule__Model__Group_2__0 )? ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1856:1: ( ( rule__Model__Group_2__0 )? )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1856:1: ( ( rule__Model__Group_2__0 )? )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1857:1: ( rule__Model__Group_2__0 )?
            {
             before(grammarAccess.getModelAccess().getGroup_2()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1858:1: ( rule__Model__Group_2__0 )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==KEYWORD_46) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1858:2: rule__Model__Group_2__0
                    {
                    pushFollow(FOLLOW_rule__Model__Group_2__0_in_rule__Model__Group__2__Impl4067);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1874:1: rule__Model__Group_1__0 : rule__Model__Group_1__0__Impl rule__Model__Group_1__1 ;
    public final void rule__Model__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1878:1: ( rule__Model__Group_1__0__Impl rule__Model__Group_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1879:2: rule__Model__Group_1__0__Impl rule__Model__Group_1__1
            {
            pushFollow(FOLLOW_rule__Model__Group_1__0__Impl_in_rule__Model__Group_1__04104);
            rule__Model__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Model__Group_1__1_in_rule__Model__Group_1__04107);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1886:1: rule__Model__Group_1__0__Impl : ( () ) ;
    public final void rule__Model__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1890:1: ( ( () ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1891:1: ( () )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1891:1: ( () )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1892:1: ()
            {
             before(grammarAccess.getModelAccess().getOrSelectEntriesAction_1_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1893:1: ()
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1895:1: 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1905:1: rule__Model__Group_1__1 : rule__Model__Group_1__1__Impl ;
    public final void rule__Model__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1909:1: ( rule__Model__Group_1__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1910:2: rule__Model__Group_1__1__Impl
            {
            pushFollow(FOLLOW_rule__Model__Group_1__1__Impl_in_rule__Model__Group_1__14165);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1916:1: rule__Model__Group_1__1__Impl : ( ( ( rule__Model__Group_1_1__0 ) ) ( ( rule__Model__Group_1_1__0 )* ) ) ;
    public final void rule__Model__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1920:1: ( ( ( ( rule__Model__Group_1_1__0 ) ) ( ( rule__Model__Group_1_1__0 )* ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1921:1: ( ( ( rule__Model__Group_1_1__0 ) ) ( ( rule__Model__Group_1_1__0 )* ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1921:1: ( ( ( rule__Model__Group_1_1__0 ) ) ( ( rule__Model__Group_1_1__0 )* ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1922:1: ( ( rule__Model__Group_1_1__0 ) ) ( ( rule__Model__Group_1_1__0 )* )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1922:1: ( ( rule__Model__Group_1_1__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1923:1: ( rule__Model__Group_1_1__0 )
            {
             before(grammarAccess.getModelAccess().getGroup_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1924:1: ( rule__Model__Group_1_1__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1924:2: rule__Model__Group_1_1__0
            {
            pushFollow(FOLLOW_rule__Model__Group_1_1__0_in_rule__Model__Group_1__1__Impl4194);
            rule__Model__Group_1_1__0();

            state._fsp--;


            }

             after(grammarAccess.getModelAccess().getGroup_1_1()); 

            }

            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1927:1: ( ( rule__Model__Group_1_1__0 )* )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1928:1: ( rule__Model__Group_1_1__0 )*
            {
             before(grammarAccess.getModelAccess().getGroup_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1929:1: ( rule__Model__Group_1_1__0 )*
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( (LA20_0==KEYWORD_33) ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1929:2: rule__Model__Group_1_1__0
            	    {
            	    pushFollow(FOLLOW_rule__Model__Group_1_1__0_in_rule__Model__Group_1__1__Impl4206);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1944:1: rule__Model__Group_1_1__0 : rule__Model__Group_1_1__0__Impl rule__Model__Group_1_1__1 ;
    public final void rule__Model__Group_1_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1948:1: ( rule__Model__Group_1_1__0__Impl rule__Model__Group_1_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1949:2: rule__Model__Group_1_1__0__Impl rule__Model__Group_1_1__1
            {
            pushFollow(FOLLOW_rule__Model__Group_1_1__0__Impl_in_rule__Model__Group_1_1__04243);
            rule__Model__Group_1_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Model__Group_1_1__1_in_rule__Model__Group_1_1__04246);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1956:1: rule__Model__Group_1_1__0__Impl : ( KEYWORD_33 ) ;
    public final void rule__Model__Group_1_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1960:1: ( ( KEYWORD_33 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1961:1: ( KEYWORD_33 )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1961:1: ( KEYWORD_33 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1962:1: KEYWORD_33
            {
             before(grammarAccess.getModelAccess().getUNIONKeyword_1_1_0()); 
            match(input,KEYWORD_33,FOLLOW_KEYWORD_33_in_rule__Model__Group_1_1__0__Impl4274); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1975:1: rule__Model__Group_1_1__1 : rule__Model__Group_1_1__1__Impl ;
    public final void rule__Model__Group_1_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1979:1: ( rule__Model__Group_1_1__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1980:2: rule__Model__Group_1_1__1__Impl
            {
            pushFollow(FOLLOW_rule__Model__Group_1_1__1__Impl_in_rule__Model__Group_1_1__14305);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1986:1: rule__Model__Group_1_1__1__Impl : ( ( rule__Model__EntriesAssignment_1_1_1 ) ) ;
    public final void rule__Model__Group_1_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1990:1: ( ( ( rule__Model__EntriesAssignment_1_1_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1991:1: ( ( rule__Model__EntriesAssignment_1_1_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1991:1: ( ( rule__Model__EntriesAssignment_1_1_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1992:1: ( rule__Model__EntriesAssignment_1_1_1 )
            {
             before(grammarAccess.getModelAccess().getEntriesAssignment_1_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1993:1: ( rule__Model__EntriesAssignment_1_1_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:1993:2: rule__Model__EntriesAssignment_1_1_1
            {
            pushFollow(FOLLOW_rule__Model__EntriesAssignment_1_1_1_in_rule__Model__Group_1_1__1__Impl4332);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2007:1: rule__Model__Group_2__0 : rule__Model__Group_2__0__Impl rule__Model__Group_2__1 ;
    public final void rule__Model__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2011:1: ( rule__Model__Group_2__0__Impl rule__Model__Group_2__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2012:2: rule__Model__Group_2__0__Impl rule__Model__Group_2__1
            {
            pushFollow(FOLLOW_rule__Model__Group_2__0__Impl_in_rule__Model__Group_2__04366);
            rule__Model__Group_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Model__Group_2__1_in_rule__Model__Group_2__04369);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2019:1: rule__Model__Group_2__0__Impl : ( KEYWORD_46 ) ;
    public final void rule__Model__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2023:1: ( ( KEYWORD_46 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2024:1: ( KEYWORD_46 )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2024:1: ( KEYWORD_46 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2025:1: KEYWORD_46
            {
             before(grammarAccess.getModelAccess().getORDERBYKeyword_2_0()); 
            match(input,KEYWORD_46,FOLLOW_KEYWORD_46_in_rule__Model__Group_2__0__Impl4397); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2038:1: rule__Model__Group_2__1 : rule__Model__Group_2__1__Impl ;
    public final void rule__Model__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2042:1: ( rule__Model__Group_2__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2043:2: rule__Model__Group_2__1__Impl
            {
            pushFollow(FOLLOW_rule__Model__Group_2__1__Impl_in_rule__Model__Group_2__14428);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2049:1: rule__Model__Group_2__1__Impl : ( ( rule__Model__OrderByEntryAssignment_2_1 ) ) ;
    public final void rule__Model__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2053:1: ( ( ( rule__Model__OrderByEntryAssignment_2_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2054:1: ( ( rule__Model__OrderByEntryAssignment_2_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2054:1: ( ( rule__Model__OrderByEntryAssignment_2_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2055:1: ( rule__Model__OrderByEntryAssignment_2_1 )
            {
             before(grammarAccess.getModelAccess().getOrderByEntryAssignment_2_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2056:1: ( rule__Model__OrderByEntryAssignment_2_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2056:2: rule__Model__OrderByEntryAssignment_2_1
            {
            pushFollow(FOLLOW_rule__Model__OrderByEntryAssignment_2_1_in_rule__Model__Group_2__1__Impl4455);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2070:1: rule__Select__Group__0 : rule__Select__Group__0__Impl rule__Select__Group__1 ;
    public final void rule__Select__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2074:1: ( rule__Select__Group__0__Impl rule__Select__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2075:2: rule__Select__Group__0__Impl rule__Select__Group__1
            {
            pushFollow(FOLLOW_rule__Select__Group__0__Impl_in_rule__Select__Group__04489);
            rule__Select__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Select__Group__1_in_rule__Select__Group__04492);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2082:1: rule__Select__Group__0__Impl : ( ( rule__Select__SelectAssignment_0 ) ) ;
    public final void rule__Select__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2086:1: ( ( ( rule__Select__SelectAssignment_0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2087:1: ( ( rule__Select__SelectAssignment_0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2087:1: ( ( rule__Select__SelectAssignment_0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2088:1: ( rule__Select__SelectAssignment_0 )
            {
             before(grammarAccess.getSelectAccess().getSelectAssignment_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2089:1: ( rule__Select__SelectAssignment_0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2089:2: rule__Select__SelectAssignment_0
            {
            pushFollow(FOLLOW_rule__Select__SelectAssignment_0_in_rule__Select__Group__0__Impl4519);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2099:1: rule__Select__Group__1 : rule__Select__Group__1__Impl rule__Select__Group__2 ;
    public final void rule__Select__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2103:1: ( rule__Select__Group__1__Impl rule__Select__Group__2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2104:2: rule__Select__Group__1__Impl rule__Select__Group__2
            {
            pushFollow(FOLLOW_rule__Select__Group__1__Impl_in_rule__Select__Group__14549);
            rule__Select__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Select__Group__2_in_rule__Select__Group__14552);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2111:1: rule__Select__Group__1__Impl : ( ( KEYWORD_42 )? ) ;
    public final void rule__Select__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2115:1: ( ( ( KEYWORD_42 )? ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2116:1: ( ( KEYWORD_42 )? )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2116:1: ( ( KEYWORD_42 )? )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2117:1: ( KEYWORD_42 )?
            {
             before(grammarAccess.getSelectAccess().getDISTINCTKeyword_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2118:1: ( KEYWORD_42 )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==KEYWORD_42) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2119:2: KEYWORD_42
                    {
                    match(input,KEYWORD_42,FOLLOW_KEYWORD_42_in_rule__Select__Group__1__Impl4581); 

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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2130:1: rule__Select__Group__2 : rule__Select__Group__2__Impl rule__Select__Group__3 ;
    public final void rule__Select__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2134:1: ( rule__Select__Group__2__Impl rule__Select__Group__3 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2135:2: rule__Select__Group__2__Impl rule__Select__Group__3
            {
            pushFollow(FOLLOW_rule__Select__Group__2__Impl_in_rule__Select__Group__24614);
            rule__Select__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Select__Group__3_in_rule__Select__Group__24617);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2142:1: rule__Select__Group__2__Impl : ( ( rule__Select__ColsAssignment_2 ) ) ;
    public final void rule__Select__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2146:1: ( ( ( rule__Select__ColsAssignment_2 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2147:1: ( ( rule__Select__ColsAssignment_2 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2147:1: ( ( rule__Select__ColsAssignment_2 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2148:1: ( rule__Select__ColsAssignment_2 )
            {
             before(grammarAccess.getSelectAccess().getColsAssignment_2()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2149:1: ( rule__Select__ColsAssignment_2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2149:2: rule__Select__ColsAssignment_2
            {
            pushFollow(FOLLOW_rule__Select__ColsAssignment_2_in_rule__Select__Group__2__Impl4644);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2159:1: rule__Select__Group__3 : rule__Select__Group__3__Impl rule__Select__Group__4 ;
    public final void rule__Select__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2163:1: ( rule__Select__Group__3__Impl rule__Select__Group__4 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2164:2: rule__Select__Group__3__Impl rule__Select__Group__4
            {
            pushFollow(FOLLOW_rule__Select__Group__3__Impl_in_rule__Select__Group__34674);
            rule__Select__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Select__Group__4_in_rule__Select__Group__34677);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2171:1: rule__Select__Group__3__Impl : ( KEYWORD_27 ) ;
    public final void rule__Select__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2175:1: ( ( KEYWORD_27 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2176:1: ( KEYWORD_27 )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2176:1: ( KEYWORD_27 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2177:1: KEYWORD_27
            {
             before(grammarAccess.getSelectAccess().getFROMKeyword_3()); 
            match(input,KEYWORD_27,FOLLOW_KEYWORD_27_in_rule__Select__Group__3__Impl4705); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2190:1: rule__Select__Group__4 : rule__Select__Group__4__Impl rule__Select__Group__5 ;
    public final void rule__Select__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2194:1: ( rule__Select__Group__4__Impl rule__Select__Group__5 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2195:2: rule__Select__Group__4__Impl rule__Select__Group__5
            {
            pushFollow(FOLLOW_rule__Select__Group__4__Impl_in_rule__Select__Group__44736);
            rule__Select__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Select__Group__5_in_rule__Select__Group__44739);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2202:1: rule__Select__Group__4__Impl : ( ( rule__Select__TblAssignment_4 ) ) ;
    public final void rule__Select__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2206:1: ( ( ( rule__Select__TblAssignment_4 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2207:1: ( ( rule__Select__TblAssignment_4 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2207:1: ( ( rule__Select__TblAssignment_4 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2208:1: ( rule__Select__TblAssignment_4 )
            {
             before(grammarAccess.getSelectAccess().getTblAssignment_4()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2209:1: ( rule__Select__TblAssignment_4 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2209:2: rule__Select__TblAssignment_4
            {
            pushFollow(FOLLOW_rule__Select__TblAssignment_4_in_rule__Select__Group__4__Impl4766);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2219:1: rule__Select__Group__5 : rule__Select__Group__5__Impl rule__Select__Group__6 ;
    public final void rule__Select__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2223:1: ( rule__Select__Group__5__Impl rule__Select__Group__6 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2224:2: rule__Select__Group__5__Impl rule__Select__Group__6
            {
            pushFollow(FOLLOW_rule__Select__Group__5__Impl_in_rule__Select__Group__54796);
            rule__Select__Group__5__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Select__Group__6_in_rule__Select__Group__54799);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2231:1: rule__Select__Group__5__Impl : ( ( rule__Select__Group_5__0 )? ) ;
    public final void rule__Select__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2235:1: ( ( ( rule__Select__Group_5__0 )? ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2236:1: ( ( rule__Select__Group_5__0 )? )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2236:1: ( ( rule__Select__Group_5__0 )? )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2237:1: ( rule__Select__Group_5__0 )?
            {
             before(grammarAccess.getSelectAccess().getGroup_5()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2238:1: ( rule__Select__Group_5__0 )?
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==KEYWORD_34) ) {
                alt22=1;
            }
            switch (alt22) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2238:2: rule__Select__Group_5__0
                    {
                    pushFollow(FOLLOW_rule__Select__Group_5__0_in_rule__Select__Group__5__Impl4826);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2248:1: rule__Select__Group__6 : rule__Select__Group__6__Impl rule__Select__Group__7 ;
    public final void rule__Select__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2252:1: ( rule__Select__Group__6__Impl rule__Select__Group__7 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2253:2: rule__Select__Group__6__Impl rule__Select__Group__7
            {
            pushFollow(FOLLOW_rule__Select__Group__6__Impl_in_rule__Select__Group__64857);
            rule__Select__Group__6__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Select__Group__7_in_rule__Select__Group__64860);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2260:1: rule__Select__Group__6__Impl : ( ( rule__Select__Group_6__0 )? ) ;
    public final void rule__Select__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2264:1: ( ( ( rule__Select__Group_6__0 )? ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2265:1: ( ( rule__Select__Group_6__0 )? )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2265:1: ( ( rule__Select__Group_6__0 )? )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2266:1: ( rule__Select__Group_6__0 )?
            {
             before(grammarAccess.getSelectAccess().getGroup_6()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2267:1: ( rule__Select__Group_6__0 )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==KEYWORD_43) ) {
                alt23=1;
            }
            switch (alt23) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2267:2: rule__Select__Group_6__0
                    {
                    pushFollow(FOLLOW_rule__Select__Group_6__0_in_rule__Select__Group__6__Impl4887);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2277:1: rule__Select__Group__7 : rule__Select__Group__7__Impl ;
    public final void rule__Select__Group__7() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2281:1: ( rule__Select__Group__7__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2282:2: rule__Select__Group__7__Impl
            {
            pushFollow(FOLLOW_rule__Select__Group__7__Impl_in_rule__Select__Group__74918);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2288:1: rule__Select__Group__7__Impl : ( ( rule__Select__Group_7__0 )? ) ;
    public final void rule__Select__Group__7__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2292:1: ( ( ( rule__Select__Group_7__0 )? ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2293:1: ( ( rule__Select__Group_7__0 )? )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2293:1: ( ( rule__Select__Group_7__0 )? )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2294:1: ( rule__Select__Group_7__0 )?
            {
             before(grammarAccess.getSelectAccess().getGroup_7()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2295:1: ( rule__Select__Group_7__0 )?
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( (LA24_0==KEYWORD_35) ) {
                alt24=1;
            }
            switch (alt24) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2295:2: rule__Select__Group_7__0
                    {
                    pushFollow(FOLLOW_rule__Select__Group_7__0_in_rule__Select__Group__7__Impl4945);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2321:1: rule__Select__Group_5__0 : rule__Select__Group_5__0__Impl rule__Select__Group_5__1 ;
    public final void rule__Select__Group_5__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2325:1: ( rule__Select__Group_5__0__Impl rule__Select__Group_5__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2326:2: rule__Select__Group_5__0__Impl rule__Select__Group_5__1
            {
            pushFollow(FOLLOW_rule__Select__Group_5__0__Impl_in_rule__Select__Group_5__04992);
            rule__Select__Group_5__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Select__Group_5__1_in_rule__Select__Group_5__04995);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2333:1: rule__Select__Group_5__0__Impl : ( KEYWORD_34 ) ;
    public final void rule__Select__Group_5__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2337:1: ( ( KEYWORD_34 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2338:1: ( KEYWORD_34 )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2338:1: ( KEYWORD_34 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2339:1: KEYWORD_34
            {
             before(grammarAccess.getSelectAccess().getWHEREKeyword_5_0()); 
            match(input,KEYWORD_34,FOLLOW_KEYWORD_34_in_rule__Select__Group_5__0__Impl5023); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2352:1: rule__Select__Group_5__1 : rule__Select__Group_5__1__Impl ;
    public final void rule__Select__Group_5__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2356:1: ( rule__Select__Group_5__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2357:2: rule__Select__Group_5__1__Impl
            {
            pushFollow(FOLLOW_rule__Select__Group_5__1__Impl_in_rule__Select__Group_5__15054);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2363:1: rule__Select__Group_5__1__Impl : ( ( rule__Select__WhereExpressionAssignment_5_1 ) ) ;
    public final void rule__Select__Group_5__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2367:1: ( ( ( rule__Select__WhereExpressionAssignment_5_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2368:1: ( ( rule__Select__WhereExpressionAssignment_5_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2368:1: ( ( rule__Select__WhereExpressionAssignment_5_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2369:1: ( rule__Select__WhereExpressionAssignment_5_1 )
            {
             before(grammarAccess.getSelectAccess().getWhereExpressionAssignment_5_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2370:1: ( rule__Select__WhereExpressionAssignment_5_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2370:2: rule__Select__WhereExpressionAssignment_5_1
            {
            pushFollow(FOLLOW_rule__Select__WhereExpressionAssignment_5_1_in_rule__Select__Group_5__1__Impl5081);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2384:1: rule__Select__Group_6__0 : rule__Select__Group_6__0__Impl rule__Select__Group_6__1 ;
    public final void rule__Select__Group_6__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2388:1: ( rule__Select__Group_6__0__Impl rule__Select__Group_6__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2389:2: rule__Select__Group_6__0__Impl rule__Select__Group_6__1
            {
            pushFollow(FOLLOW_rule__Select__Group_6__0__Impl_in_rule__Select__Group_6__05115);
            rule__Select__Group_6__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Select__Group_6__1_in_rule__Select__Group_6__05118);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2396:1: rule__Select__Group_6__0__Impl : ( KEYWORD_43 ) ;
    public final void rule__Select__Group_6__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2400:1: ( ( KEYWORD_43 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2401:1: ( KEYWORD_43 )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2401:1: ( KEYWORD_43 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2402:1: KEYWORD_43
            {
             before(grammarAccess.getSelectAccess().getGROUPBYKeyword_6_0()); 
            match(input,KEYWORD_43,FOLLOW_KEYWORD_43_in_rule__Select__Group_6__0__Impl5146); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2415:1: rule__Select__Group_6__1 : rule__Select__Group_6__1__Impl ;
    public final void rule__Select__Group_6__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2419:1: ( rule__Select__Group_6__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2420:2: rule__Select__Group_6__1__Impl
            {
            pushFollow(FOLLOW_rule__Select__Group_6__1__Impl_in_rule__Select__Group_6__15177);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2426:1: rule__Select__Group_6__1__Impl : ( ( rule__Select__GroupByEntryAssignment_6_1 ) ) ;
    public final void rule__Select__Group_6__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2430:1: ( ( ( rule__Select__GroupByEntryAssignment_6_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2431:1: ( ( rule__Select__GroupByEntryAssignment_6_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2431:1: ( ( rule__Select__GroupByEntryAssignment_6_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2432:1: ( rule__Select__GroupByEntryAssignment_6_1 )
            {
             before(grammarAccess.getSelectAccess().getGroupByEntryAssignment_6_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2433:1: ( rule__Select__GroupByEntryAssignment_6_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2433:2: rule__Select__GroupByEntryAssignment_6_1
            {
            pushFollow(FOLLOW_rule__Select__GroupByEntryAssignment_6_1_in_rule__Select__Group_6__1__Impl5204);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2447:1: rule__Select__Group_7__0 : rule__Select__Group_7__0__Impl rule__Select__Group_7__1 ;
    public final void rule__Select__Group_7__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2451:1: ( rule__Select__Group_7__0__Impl rule__Select__Group_7__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2452:2: rule__Select__Group_7__0__Impl rule__Select__Group_7__1
            {
            pushFollow(FOLLOW_rule__Select__Group_7__0__Impl_in_rule__Select__Group_7__05238);
            rule__Select__Group_7__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Select__Group_7__1_in_rule__Select__Group_7__05241);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2459:1: rule__Select__Group_7__0__Impl : ( KEYWORD_35 ) ;
    public final void rule__Select__Group_7__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2463:1: ( ( KEYWORD_35 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2464:1: ( KEYWORD_35 )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2464:1: ( KEYWORD_35 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2465:1: KEYWORD_35
            {
             before(grammarAccess.getSelectAccess().getHAVINGKeyword_7_0()); 
            match(input,KEYWORD_35,FOLLOW_KEYWORD_35_in_rule__Select__Group_7__0__Impl5269); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2478:1: rule__Select__Group_7__1 : rule__Select__Group_7__1__Impl ;
    public final void rule__Select__Group_7__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2482:1: ( rule__Select__Group_7__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2483:2: rule__Select__Group_7__1__Impl
            {
            pushFollow(FOLLOW_rule__Select__Group_7__1__Impl_in_rule__Select__Group_7__15300);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2489:1: rule__Select__Group_7__1__Impl : ( ( rule__Select__HavingEntryAssignment_7_1 ) ) ;
    public final void rule__Select__Group_7__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2493:1: ( ( ( rule__Select__HavingEntryAssignment_7_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2494:1: ( ( rule__Select__HavingEntryAssignment_7_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2494:1: ( ( rule__Select__HavingEntryAssignment_7_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2495:1: ( rule__Select__HavingEntryAssignment_7_1 )
            {
             before(grammarAccess.getSelectAccess().getHavingEntryAssignment_7_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2496:1: ( rule__Select__HavingEntryAssignment_7_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2496:2: rule__Select__HavingEntryAssignment_7_1
            {
            pushFollow(FOLLOW_rule__Select__HavingEntryAssignment_7_1_in_rule__Select__Group_7__1__Impl5327);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2510:1: rule__Columns__Group__0 : rule__Columns__Group__0__Impl rule__Columns__Group__1 ;
    public final void rule__Columns__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2514:1: ( rule__Columns__Group__0__Impl rule__Columns__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2515:2: rule__Columns__Group__0__Impl rule__Columns__Group__1
            {
            pushFollow(FOLLOW_rule__Columns__Group__0__Impl_in_rule__Columns__Group__05361);
            rule__Columns__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Columns__Group__1_in_rule__Columns__Group__05364);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2522:1: rule__Columns__Group__0__Impl : ( ruleColumnOrAlias ) ;
    public final void rule__Columns__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2526:1: ( ( ruleColumnOrAlias ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2527:1: ( ruleColumnOrAlias )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2527:1: ( ruleColumnOrAlias )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2528:1: ruleColumnOrAlias
            {
             before(grammarAccess.getColumnsAccess().getColumnOrAliasParserRuleCall_0()); 
            pushFollow(FOLLOW_ruleColumnOrAlias_in_rule__Columns__Group__0__Impl5391);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2539:1: rule__Columns__Group__1 : rule__Columns__Group__1__Impl ;
    public final void rule__Columns__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2543:1: ( rule__Columns__Group__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2544:2: rule__Columns__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__Columns__Group__1__Impl_in_rule__Columns__Group__15420);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2550:1: rule__Columns__Group__1__Impl : ( ( rule__Columns__Group_1__0 )? ) ;
    public final void rule__Columns__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2554:1: ( ( ( rule__Columns__Group_1__0 )? ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2555:1: ( ( rule__Columns__Group_1__0 )? )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2555:1: ( ( rule__Columns__Group_1__0 )? )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2556:1: ( rule__Columns__Group_1__0 )?
            {
             before(grammarAccess.getColumnsAccess().getGroup_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2557:1: ( rule__Columns__Group_1__0 )?
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==KEYWORD_4) ) {
                alt25=1;
            }
            switch (alt25) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2557:2: rule__Columns__Group_1__0
                    {
                    pushFollow(FOLLOW_rule__Columns__Group_1__0_in_rule__Columns__Group__1__Impl5447);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2571:1: rule__Columns__Group_1__0 : rule__Columns__Group_1__0__Impl rule__Columns__Group_1__1 ;
    public final void rule__Columns__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2575:1: ( rule__Columns__Group_1__0__Impl rule__Columns__Group_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2576:2: rule__Columns__Group_1__0__Impl rule__Columns__Group_1__1
            {
            pushFollow(FOLLOW_rule__Columns__Group_1__0__Impl_in_rule__Columns__Group_1__05482);
            rule__Columns__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Columns__Group_1__1_in_rule__Columns__Group_1__05485);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2583:1: rule__Columns__Group_1__0__Impl : ( () ) ;
    public final void rule__Columns__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2587:1: ( ( () ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2588:1: ( () )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2588:1: ( () )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2589:1: ()
            {
             before(grammarAccess.getColumnsAccess().getOrColumnEntriesAction_1_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2590:1: ()
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2592:1: 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2602:1: rule__Columns__Group_1__1 : rule__Columns__Group_1__1__Impl ;
    public final void rule__Columns__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2606:1: ( rule__Columns__Group_1__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2607:2: rule__Columns__Group_1__1__Impl
            {
            pushFollow(FOLLOW_rule__Columns__Group_1__1__Impl_in_rule__Columns__Group_1__15543);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2613:1: rule__Columns__Group_1__1__Impl : ( ( ( rule__Columns__Group_1_1__0 ) ) ( ( rule__Columns__Group_1_1__0 )* ) ) ;
    public final void rule__Columns__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2617:1: ( ( ( ( rule__Columns__Group_1_1__0 ) ) ( ( rule__Columns__Group_1_1__0 )* ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2618:1: ( ( ( rule__Columns__Group_1_1__0 ) ) ( ( rule__Columns__Group_1_1__0 )* ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2618:1: ( ( ( rule__Columns__Group_1_1__0 ) ) ( ( rule__Columns__Group_1_1__0 )* ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2619:1: ( ( rule__Columns__Group_1_1__0 ) ) ( ( rule__Columns__Group_1_1__0 )* )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2619:1: ( ( rule__Columns__Group_1_1__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2620:1: ( rule__Columns__Group_1_1__0 )
            {
             before(grammarAccess.getColumnsAccess().getGroup_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2621:1: ( rule__Columns__Group_1_1__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2621:2: rule__Columns__Group_1_1__0
            {
            pushFollow(FOLLOW_rule__Columns__Group_1_1__0_in_rule__Columns__Group_1__1__Impl5572);
            rule__Columns__Group_1_1__0();

            state._fsp--;


            }

             after(grammarAccess.getColumnsAccess().getGroup_1_1()); 

            }

            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2624:1: ( ( rule__Columns__Group_1_1__0 )* )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2625:1: ( rule__Columns__Group_1_1__0 )*
            {
             before(grammarAccess.getColumnsAccess().getGroup_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2626:1: ( rule__Columns__Group_1_1__0 )*
            loop26:
            do {
                int alt26=2;
                int LA26_0 = input.LA(1);

                if ( (LA26_0==KEYWORD_4) ) {
                    alt26=1;
                }


                switch (alt26) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2626:2: rule__Columns__Group_1_1__0
            	    {
            	    pushFollow(FOLLOW_rule__Columns__Group_1_1__0_in_rule__Columns__Group_1__1__Impl5584);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2641:1: rule__Columns__Group_1_1__0 : rule__Columns__Group_1_1__0__Impl rule__Columns__Group_1_1__1 ;
    public final void rule__Columns__Group_1_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2645:1: ( rule__Columns__Group_1_1__0__Impl rule__Columns__Group_1_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2646:2: rule__Columns__Group_1_1__0__Impl rule__Columns__Group_1_1__1
            {
            pushFollow(FOLLOW_rule__Columns__Group_1_1__0__Impl_in_rule__Columns__Group_1_1__05621);
            rule__Columns__Group_1_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Columns__Group_1_1__1_in_rule__Columns__Group_1_1__05624);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2653:1: rule__Columns__Group_1_1__0__Impl : ( KEYWORD_4 ) ;
    public final void rule__Columns__Group_1_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2657:1: ( ( KEYWORD_4 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2658:1: ( KEYWORD_4 )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2658:1: ( KEYWORD_4 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2659:1: KEYWORD_4
            {
             before(grammarAccess.getColumnsAccess().getCommaKeyword_1_1_0()); 
            match(input,KEYWORD_4,FOLLOW_KEYWORD_4_in_rule__Columns__Group_1_1__0__Impl5652); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2672:1: rule__Columns__Group_1_1__1 : rule__Columns__Group_1_1__1__Impl ;
    public final void rule__Columns__Group_1_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2676:1: ( rule__Columns__Group_1_1__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2677:2: rule__Columns__Group_1_1__1__Impl
            {
            pushFollow(FOLLOW_rule__Columns__Group_1_1__1__Impl_in_rule__Columns__Group_1_1__15683);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2683:1: rule__Columns__Group_1_1__1__Impl : ( ( rule__Columns__EntriesAssignment_1_1_1 ) ) ;
    public final void rule__Columns__Group_1_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2687:1: ( ( ( rule__Columns__EntriesAssignment_1_1_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2688:1: ( ( rule__Columns__EntriesAssignment_1_1_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2688:1: ( ( rule__Columns__EntriesAssignment_1_1_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2689:1: ( rule__Columns__EntriesAssignment_1_1_1 )
            {
             before(grammarAccess.getColumnsAccess().getEntriesAssignment_1_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2690:1: ( rule__Columns__EntriesAssignment_1_1_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2690:2: rule__Columns__EntriesAssignment_1_1_1
            {
            pushFollow(FOLLOW_rule__Columns__EntriesAssignment_1_1_1_in_rule__Columns__Group_1_1__1__Impl5710);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2704:1: rule__ColumnOrAlias__Group_0__0 : rule__ColumnOrAlias__Group_0__0__Impl rule__ColumnOrAlias__Group_0__1 ;
    public final void rule__ColumnOrAlias__Group_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2708:1: ( rule__ColumnOrAlias__Group_0__0__Impl rule__ColumnOrAlias__Group_0__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2709:2: rule__ColumnOrAlias__Group_0__0__Impl rule__ColumnOrAlias__Group_0__1
            {
            pushFollow(FOLLOW_rule__ColumnOrAlias__Group_0__0__Impl_in_rule__ColumnOrAlias__Group_0__05744);
            rule__ColumnOrAlias__Group_0__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ColumnOrAlias__Group_0__1_in_rule__ColumnOrAlias__Group_0__05747);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2716:1: rule__ColumnOrAlias__Group_0__0__Impl : ( ( rule__ColumnOrAlias__CfullAssignment_0_0 ) ) ;
    public final void rule__ColumnOrAlias__Group_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2720:1: ( ( ( rule__ColumnOrAlias__CfullAssignment_0_0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2721:1: ( ( rule__ColumnOrAlias__CfullAssignment_0_0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2721:1: ( ( rule__ColumnOrAlias__CfullAssignment_0_0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2722:1: ( rule__ColumnOrAlias__CfullAssignment_0_0 )
            {
             before(grammarAccess.getColumnOrAliasAccess().getCfullAssignment_0_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2723:1: ( rule__ColumnOrAlias__CfullAssignment_0_0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2723:2: rule__ColumnOrAlias__CfullAssignment_0_0
            {
            pushFollow(FOLLOW_rule__ColumnOrAlias__CfullAssignment_0_0_in_rule__ColumnOrAlias__Group_0__0__Impl5774);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2733:1: rule__ColumnOrAlias__Group_0__1 : rule__ColumnOrAlias__Group_0__1__Impl rule__ColumnOrAlias__Group_0__2 ;
    public final void rule__ColumnOrAlias__Group_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2737:1: ( rule__ColumnOrAlias__Group_0__1__Impl rule__ColumnOrAlias__Group_0__2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2738:2: rule__ColumnOrAlias__Group_0__1__Impl rule__ColumnOrAlias__Group_0__2
            {
            pushFollow(FOLLOW_rule__ColumnOrAlias__Group_0__1__Impl_in_rule__ColumnOrAlias__Group_0__15804);
            rule__ColumnOrAlias__Group_0__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ColumnOrAlias__Group_0__2_in_rule__ColumnOrAlias__Group_0__15807);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2745:1: rule__ColumnOrAlias__Group_0__1__Impl : ( ( rule__ColumnOrAlias__AliasAssignment_0_1 )? ) ;
    public final void rule__ColumnOrAlias__Group_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2749:1: ( ( ( rule__ColumnOrAlias__AliasAssignment_0_1 )? ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2750:1: ( ( rule__ColumnOrAlias__AliasAssignment_0_1 )? )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2750:1: ( ( rule__ColumnOrAlias__AliasAssignment_0_1 )? )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2751:1: ( rule__ColumnOrAlias__AliasAssignment_0_1 )?
            {
             before(grammarAccess.getColumnOrAliasAccess().getAliasAssignment_0_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2752:1: ( rule__ColumnOrAlias__AliasAssignment_0_1 )?
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==KEYWORD_18) ) {
                alt27=1;
            }
            switch (alt27) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2752:2: rule__ColumnOrAlias__AliasAssignment_0_1
                    {
                    pushFollow(FOLLOW_rule__ColumnOrAlias__AliasAssignment_0_1_in_rule__ColumnOrAlias__Group_0__1__Impl5834);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2762:1: rule__ColumnOrAlias__Group_0__2 : rule__ColumnOrAlias__Group_0__2__Impl ;
    public final void rule__ColumnOrAlias__Group_0__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2766:1: ( rule__ColumnOrAlias__Group_0__2__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2767:2: rule__ColumnOrAlias__Group_0__2__Impl
            {
            pushFollow(FOLLOW_rule__ColumnOrAlias__Group_0__2__Impl_in_rule__ColumnOrAlias__Group_0__25865);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2773:1: rule__ColumnOrAlias__Group_0__2__Impl : ( ( rule__ColumnOrAlias__ColAliasAssignment_0_2 )? ) ;
    public final void rule__ColumnOrAlias__Group_0__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2777:1: ( ( ( rule__ColumnOrAlias__ColAliasAssignment_0_2 )? ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2778:1: ( ( rule__ColumnOrAlias__ColAliasAssignment_0_2 )? )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2778:1: ( ( rule__ColumnOrAlias__ColAliasAssignment_0_2 )? )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2779:1: ( rule__ColumnOrAlias__ColAliasAssignment_0_2 )?
            {
             before(grammarAccess.getColumnOrAliasAccess().getColAliasAssignment_0_2()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2780:1: ( rule__ColumnOrAlias__ColAliasAssignment_0_2 )?
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==RULE_ID) ) {
                alt28=1;
            }
            switch (alt28) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2780:2: rule__ColumnOrAlias__ColAliasAssignment_0_2
                    {
                    pushFollow(FOLLOW_rule__ColumnOrAlias__ColAliasAssignment_0_2_in_rule__ColumnOrAlias__Group_0__2__Impl5892);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2796:1: rule__ColumnFull__Group__0 : rule__ColumnFull__Group__0__Impl rule__ColumnFull__Group__1 ;
    public final void rule__ColumnFull__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2800:1: ( rule__ColumnFull__Group__0__Impl rule__ColumnFull__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2801:2: rule__ColumnFull__Group__0__Impl rule__ColumnFull__Group__1
            {
            pushFollow(FOLLOW_rule__ColumnFull__Group__0__Impl_in_rule__ColumnFull__Group__05929);
            rule__ColumnFull__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ColumnFull__Group__1_in_rule__ColumnFull__Group__05932);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2808:1: rule__ColumnFull__Group__0__Impl : ( ruleDbObjectName ) ;
    public final void rule__ColumnFull__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2812:1: ( ( ruleDbObjectName ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2813:1: ( ruleDbObjectName )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2813:1: ( ruleDbObjectName )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2814:1: ruleDbObjectName
            {
             before(grammarAccess.getColumnFullAccess().getDbObjectNameParserRuleCall_0()); 
            pushFollow(FOLLOW_ruleDbObjectName_in_rule__ColumnFull__Group__0__Impl5959);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2825:1: rule__ColumnFull__Group__1 : rule__ColumnFull__Group__1__Impl ;
    public final void rule__ColumnFull__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2829:1: ( rule__ColumnFull__Group__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2830:2: rule__ColumnFull__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__ColumnFull__Group__1__Impl_in_rule__ColumnFull__Group__15988);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2836:1: rule__ColumnFull__Group__1__Impl : ( ( rule__ColumnFull__Group_1__0 )? ) ;
    public final void rule__ColumnFull__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2840:1: ( ( ( rule__ColumnFull__Group_1__0 )? ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2841:1: ( ( rule__ColumnFull__Group_1__0 )? )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2841:1: ( ( rule__ColumnFull__Group_1__0 )? )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2842:1: ( rule__ColumnFull__Group_1__0 )?
            {
             before(grammarAccess.getColumnFullAccess().getGroup_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2843:1: ( rule__ColumnFull__Group_1__0 )?
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==KEYWORD_6) ) {
                alt29=1;
            }
            switch (alt29) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2843:2: rule__ColumnFull__Group_1__0
                    {
                    pushFollow(FOLLOW_rule__ColumnFull__Group_1__0_in_rule__ColumnFull__Group__1__Impl6015);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2857:1: rule__ColumnFull__Group_1__0 : rule__ColumnFull__Group_1__0__Impl rule__ColumnFull__Group_1__1 ;
    public final void rule__ColumnFull__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2861:1: ( rule__ColumnFull__Group_1__0__Impl rule__ColumnFull__Group_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2862:2: rule__ColumnFull__Group_1__0__Impl rule__ColumnFull__Group_1__1
            {
            pushFollow(FOLLOW_rule__ColumnFull__Group_1__0__Impl_in_rule__ColumnFull__Group_1__06050);
            rule__ColumnFull__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ColumnFull__Group_1__1_in_rule__ColumnFull__Group_1__06053);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2869:1: rule__ColumnFull__Group_1__0__Impl : ( () ) ;
    public final void rule__ColumnFull__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2873:1: ( ( () ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2874:1: ( () )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2874:1: ( () )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2875:1: ()
            {
             before(grammarAccess.getColumnFullAccess().getColEntriesAction_1_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2876:1: ()
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2878:1: 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2888:1: rule__ColumnFull__Group_1__1 : rule__ColumnFull__Group_1__1__Impl ;
    public final void rule__ColumnFull__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2892:1: ( rule__ColumnFull__Group_1__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2893:2: rule__ColumnFull__Group_1__1__Impl
            {
            pushFollow(FOLLOW_rule__ColumnFull__Group_1__1__Impl_in_rule__ColumnFull__Group_1__16111);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2899:1: rule__ColumnFull__Group_1__1__Impl : ( ( ( rule__ColumnFull__Group_1_1__0 ) ) ( ( rule__ColumnFull__Group_1_1__0 )* ) ) ;
    public final void rule__ColumnFull__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2903:1: ( ( ( ( rule__ColumnFull__Group_1_1__0 ) ) ( ( rule__ColumnFull__Group_1_1__0 )* ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2904:1: ( ( ( rule__ColumnFull__Group_1_1__0 ) ) ( ( rule__ColumnFull__Group_1_1__0 )* ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2904:1: ( ( ( rule__ColumnFull__Group_1_1__0 ) ) ( ( rule__ColumnFull__Group_1_1__0 )* ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2905:1: ( ( rule__ColumnFull__Group_1_1__0 ) ) ( ( rule__ColumnFull__Group_1_1__0 )* )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2905:1: ( ( rule__ColumnFull__Group_1_1__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2906:1: ( rule__ColumnFull__Group_1_1__0 )
            {
             before(grammarAccess.getColumnFullAccess().getGroup_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2907:1: ( rule__ColumnFull__Group_1_1__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2907:2: rule__ColumnFull__Group_1_1__0
            {
            pushFollow(FOLLOW_rule__ColumnFull__Group_1_1__0_in_rule__ColumnFull__Group_1__1__Impl6140);
            rule__ColumnFull__Group_1_1__0();

            state._fsp--;


            }

             after(grammarAccess.getColumnFullAccess().getGroup_1_1()); 

            }

            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2910:1: ( ( rule__ColumnFull__Group_1_1__0 )* )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2911:1: ( rule__ColumnFull__Group_1_1__0 )*
            {
             before(grammarAccess.getColumnFullAccess().getGroup_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2912:1: ( rule__ColumnFull__Group_1_1__0 )*
            loop30:
            do {
                int alt30=2;
                int LA30_0 = input.LA(1);

                if ( (LA30_0==KEYWORD_6) ) {
                    alt30=1;
                }


                switch (alt30) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2912:2: rule__ColumnFull__Group_1_1__0
            	    {
            	    pushFollow(FOLLOW_rule__ColumnFull__Group_1_1__0_in_rule__ColumnFull__Group_1__1__Impl6152);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2927:1: rule__ColumnFull__Group_1_1__0 : rule__ColumnFull__Group_1_1__0__Impl rule__ColumnFull__Group_1_1__1 ;
    public final void rule__ColumnFull__Group_1_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2931:1: ( rule__ColumnFull__Group_1_1__0__Impl rule__ColumnFull__Group_1_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2932:2: rule__ColumnFull__Group_1_1__0__Impl rule__ColumnFull__Group_1_1__1
            {
            pushFollow(FOLLOW_rule__ColumnFull__Group_1_1__0__Impl_in_rule__ColumnFull__Group_1_1__06189);
            rule__ColumnFull__Group_1_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ColumnFull__Group_1_1__1_in_rule__ColumnFull__Group_1_1__06192);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2939:1: rule__ColumnFull__Group_1_1__0__Impl : ( KEYWORD_6 ) ;
    public final void rule__ColumnFull__Group_1_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2943:1: ( ( KEYWORD_6 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2944:1: ( KEYWORD_6 )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2944:1: ( KEYWORD_6 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2945:1: KEYWORD_6
            {
             before(grammarAccess.getColumnFullAccess().getFullStopKeyword_1_1_0()); 
            match(input,KEYWORD_6,FOLLOW_KEYWORD_6_in_rule__ColumnFull__Group_1_1__0__Impl6220); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2958:1: rule__ColumnFull__Group_1_1__1 : rule__ColumnFull__Group_1_1__1__Impl ;
    public final void rule__ColumnFull__Group_1_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2962:1: ( rule__ColumnFull__Group_1_1__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2963:2: rule__ColumnFull__Group_1_1__1__Impl
            {
            pushFollow(FOLLOW_rule__ColumnFull__Group_1_1__1__Impl_in_rule__ColumnFull__Group_1_1__16251);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2969:1: rule__ColumnFull__Group_1_1__1__Impl : ( ( rule__ColumnFull__EntriesAssignment_1_1_1 ) ) ;
    public final void rule__ColumnFull__Group_1_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2973:1: ( ( ( rule__ColumnFull__EntriesAssignment_1_1_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2974:1: ( ( rule__ColumnFull__EntriesAssignment_1_1_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2974:1: ( ( rule__ColumnFull__EntriesAssignment_1_1_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2975:1: ( rule__ColumnFull__EntriesAssignment_1_1_1 )
            {
             before(grammarAccess.getColumnFullAccess().getEntriesAssignment_1_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2976:1: ( rule__ColumnFull__EntriesAssignment_1_1_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2976:2: rule__ColumnFull__EntriesAssignment_1_1_1
            {
            pushFollow(FOLLOW_rule__ColumnFull__EntriesAssignment_1_1_1_in_rule__ColumnFull__Group_1_1__1__Impl6278);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2990:1: rule__Tables__Group__0 : rule__Tables__Group__0__Impl rule__Tables__Group__1 ;
    public final void rule__Tables__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2994:1: ( rule__Tables__Group__0__Impl rule__Tables__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:2995:2: rule__Tables__Group__0__Impl rule__Tables__Group__1
            {
            pushFollow(FOLLOW_rule__Tables__Group__0__Impl_in_rule__Tables__Group__06312);
            rule__Tables__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Tables__Group__1_in_rule__Tables__Group__06315);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3002:1: rule__Tables__Group__0__Impl : ( ruleFromTable ) ;
    public final void rule__Tables__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3006:1: ( ( ruleFromTable ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3007:1: ( ruleFromTable )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3007:1: ( ruleFromTable )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3008:1: ruleFromTable
            {
             before(grammarAccess.getTablesAccess().getFromTableParserRuleCall_0()); 
            pushFollow(FOLLOW_ruleFromTable_in_rule__Tables__Group__0__Impl6342);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3019:1: rule__Tables__Group__1 : rule__Tables__Group__1__Impl ;
    public final void rule__Tables__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3023:1: ( rule__Tables__Group__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3024:2: rule__Tables__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__Tables__Group__1__Impl_in_rule__Tables__Group__16371);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3030:1: rule__Tables__Group__1__Impl : ( ( rule__Tables__Group_1__0 )? ) ;
    public final void rule__Tables__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3034:1: ( ( ( rule__Tables__Group_1__0 )? ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3035:1: ( ( rule__Tables__Group_1__0 )? )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3035:1: ( ( rule__Tables__Group_1__0 )? )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3036:1: ( rule__Tables__Group_1__0 )?
            {
             before(grammarAccess.getTablesAccess().getGroup_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3037:1: ( rule__Tables__Group_1__0 )?
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( (LA31_0==KEYWORD_4) ) {
                alt31=1;
            }
            switch (alt31) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3037:2: rule__Tables__Group_1__0
                    {
                    pushFollow(FOLLOW_rule__Tables__Group_1__0_in_rule__Tables__Group__1__Impl6398);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3051:1: rule__Tables__Group_1__0 : rule__Tables__Group_1__0__Impl rule__Tables__Group_1__1 ;
    public final void rule__Tables__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3055:1: ( rule__Tables__Group_1__0__Impl rule__Tables__Group_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3056:2: rule__Tables__Group_1__0__Impl rule__Tables__Group_1__1
            {
            pushFollow(FOLLOW_rule__Tables__Group_1__0__Impl_in_rule__Tables__Group_1__06433);
            rule__Tables__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Tables__Group_1__1_in_rule__Tables__Group_1__06436);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3063:1: rule__Tables__Group_1__0__Impl : ( () ) ;
    public final void rule__Tables__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3067:1: ( ( () ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3068:1: ( () )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3068:1: ( () )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3069:1: ()
            {
             before(grammarAccess.getTablesAccess().getOrTableEntriesAction_1_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3070:1: ()
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3072:1: 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3082:1: rule__Tables__Group_1__1 : rule__Tables__Group_1__1__Impl ;
    public final void rule__Tables__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3086:1: ( rule__Tables__Group_1__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3087:2: rule__Tables__Group_1__1__Impl
            {
            pushFollow(FOLLOW_rule__Tables__Group_1__1__Impl_in_rule__Tables__Group_1__16494);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3093:1: rule__Tables__Group_1__1__Impl : ( ( ( rule__Tables__Group_1_1__0 ) ) ( ( rule__Tables__Group_1_1__0 )* ) ) ;
    public final void rule__Tables__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3097:1: ( ( ( ( rule__Tables__Group_1_1__0 ) ) ( ( rule__Tables__Group_1_1__0 )* ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3098:1: ( ( ( rule__Tables__Group_1_1__0 ) ) ( ( rule__Tables__Group_1_1__0 )* ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3098:1: ( ( ( rule__Tables__Group_1_1__0 ) ) ( ( rule__Tables__Group_1_1__0 )* ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3099:1: ( ( rule__Tables__Group_1_1__0 ) ) ( ( rule__Tables__Group_1_1__0 )* )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3099:1: ( ( rule__Tables__Group_1_1__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3100:1: ( rule__Tables__Group_1_1__0 )
            {
             before(grammarAccess.getTablesAccess().getGroup_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3101:1: ( rule__Tables__Group_1_1__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3101:2: rule__Tables__Group_1_1__0
            {
            pushFollow(FOLLOW_rule__Tables__Group_1_1__0_in_rule__Tables__Group_1__1__Impl6523);
            rule__Tables__Group_1_1__0();

            state._fsp--;


            }

             after(grammarAccess.getTablesAccess().getGroup_1_1()); 

            }

            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3104:1: ( ( rule__Tables__Group_1_1__0 )* )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3105:1: ( rule__Tables__Group_1_1__0 )*
            {
             before(grammarAccess.getTablesAccess().getGroup_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3106:1: ( rule__Tables__Group_1_1__0 )*
            loop32:
            do {
                int alt32=2;
                int LA32_0 = input.LA(1);

                if ( (LA32_0==KEYWORD_4) ) {
                    alt32=1;
                }


                switch (alt32) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3106:2: rule__Tables__Group_1_1__0
            	    {
            	    pushFollow(FOLLOW_rule__Tables__Group_1_1__0_in_rule__Tables__Group_1__1__Impl6535);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3121:1: rule__Tables__Group_1_1__0 : rule__Tables__Group_1_1__0__Impl rule__Tables__Group_1_1__1 ;
    public final void rule__Tables__Group_1_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3125:1: ( rule__Tables__Group_1_1__0__Impl rule__Tables__Group_1_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3126:2: rule__Tables__Group_1_1__0__Impl rule__Tables__Group_1_1__1
            {
            pushFollow(FOLLOW_rule__Tables__Group_1_1__0__Impl_in_rule__Tables__Group_1_1__06572);
            rule__Tables__Group_1_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Tables__Group_1_1__1_in_rule__Tables__Group_1_1__06575);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3133:1: rule__Tables__Group_1_1__0__Impl : ( KEYWORD_4 ) ;
    public final void rule__Tables__Group_1_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3137:1: ( ( KEYWORD_4 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3138:1: ( KEYWORD_4 )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3138:1: ( KEYWORD_4 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3139:1: KEYWORD_4
            {
             before(grammarAccess.getTablesAccess().getCommaKeyword_1_1_0()); 
            match(input,KEYWORD_4,FOLLOW_KEYWORD_4_in_rule__Tables__Group_1_1__0__Impl6603); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3152:1: rule__Tables__Group_1_1__1 : rule__Tables__Group_1_1__1__Impl ;
    public final void rule__Tables__Group_1_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3156:1: ( rule__Tables__Group_1_1__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3157:2: rule__Tables__Group_1_1__1__Impl
            {
            pushFollow(FOLLOW_rule__Tables__Group_1_1__1__Impl_in_rule__Tables__Group_1_1__16634);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3163:1: rule__Tables__Group_1_1__1__Impl : ( ( rule__Tables__EntriesAssignment_1_1_1 ) ) ;
    public final void rule__Tables__Group_1_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3167:1: ( ( ( rule__Tables__EntriesAssignment_1_1_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3168:1: ( ( rule__Tables__EntriesAssignment_1_1_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3168:1: ( ( rule__Tables__EntriesAssignment_1_1_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3169:1: ( rule__Tables__EntriesAssignment_1_1_1 )
            {
             before(grammarAccess.getTablesAccess().getEntriesAssignment_1_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3170:1: ( rule__Tables__EntriesAssignment_1_1_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3170:2: rule__Tables__EntriesAssignment_1_1_1
            {
            pushFollow(FOLLOW_rule__Tables__EntriesAssignment_1_1_1_in_rule__Tables__Group_1_1__1__Impl6661);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3184:1: rule__FromTable__Group__0 : rule__FromTable__Group__0__Impl rule__FromTable__Group__1 ;
    public final void rule__FromTable__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3188:1: ( rule__FromTable__Group__0__Impl rule__FromTable__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3189:2: rule__FromTable__Group__0__Impl rule__FromTable__Group__1
            {
            pushFollow(FOLLOW_rule__FromTable__Group__0__Impl_in_rule__FromTable__Group__06695);
            rule__FromTable__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__FromTable__Group__1_in_rule__FromTable__Group__06698);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3196:1: rule__FromTable__Group__0__Impl : ( ( rule__FromTable__TableAssignment_0 ) ) ;
    public final void rule__FromTable__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3200:1: ( ( ( rule__FromTable__TableAssignment_0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3201:1: ( ( rule__FromTable__TableAssignment_0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3201:1: ( ( rule__FromTable__TableAssignment_0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3202:1: ( rule__FromTable__TableAssignment_0 )
            {
             before(grammarAccess.getFromTableAccess().getTableAssignment_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3203:1: ( rule__FromTable__TableAssignment_0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3203:2: rule__FromTable__TableAssignment_0
            {
            pushFollow(FOLLOW_rule__FromTable__TableAssignment_0_in_rule__FromTable__Group__0__Impl6725);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3213:1: rule__FromTable__Group__1 : rule__FromTable__Group__1__Impl ;
    public final void rule__FromTable__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3217:1: ( rule__FromTable__Group__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3218:2: rule__FromTable__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__FromTable__Group__1__Impl_in_rule__FromTable__Group__16755);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3224:1: rule__FromTable__Group__1__Impl : ( ( rule__FromTable__Group_1__0 )? ) ;
    public final void rule__FromTable__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3228:1: ( ( ( rule__FromTable__Group_1__0 )? ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3229:1: ( ( rule__FromTable__Group_1__0 )? )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3229:1: ( ( rule__FromTable__Group_1__0 )? )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3230:1: ( rule__FromTable__Group_1__0 )?
            {
             before(grammarAccess.getFromTableAccess().getGroup_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3231:1: ( rule__FromTable__Group_1__0 )?
            int alt33=2;
            int LA33_0 = input.LA(1);

            if ( ((LA33_0>=KEYWORD_56 && LA33_0<=KEYWORD_55)||(LA33_0>=KEYWORD_50 && LA33_0<=KEYWORD_51)) ) {
                alt33=1;
            }
            switch (alt33) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3231:2: rule__FromTable__Group_1__0
                    {
                    pushFollow(FOLLOW_rule__FromTable__Group_1__0_in_rule__FromTable__Group__1__Impl6782);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3245:1: rule__FromTable__Group_1__0 : rule__FromTable__Group_1__0__Impl rule__FromTable__Group_1__1 ;
    public final void rule__FromTable__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3249:1: ( rule__FromTable__Group_1__0__Impl rule__FromTable__Group_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3250:2: rule__FromTable__Group_1__0__Impl rule__FromTable__Group_1__1
            {
            pushFollow(FOLLOW_rule__FromTable__Group_1__0__Impl_in_rule__FromTable__Group_1__06817);
            rule__FromTable__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__FromTable__Group_1__1_in_rule__FromTable__Group_1__06820);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3257:1: rule__FromTable__Group_1__0__Impl : ( ( rule__FromTable__JoinAssignment_1_0 ) ) ;
    public final void rule__FromTable__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3261:1: ( ( ( rule__FromTable__JoinAssignment_1_0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3262:1: ( ( rule__FromTable__JoinAssignment_1_0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3262:1: ( ( rule__FromTable__JoinAssignment_1_0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3263:1: ( rule__FromTable__JoinAssignment_1_0 )
            {
             before(grammarAccess.getFromTableAccess().getJoinAssignment_1_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3264:1: ( rule__FromTable__JoinAssignment_1_0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3264:2: rule__FromTable__JoinAssignment_1_0
            {
            pushFollow(FOLLOW_rule__FromTable__JoinAssignment_1_0_in_rule__FromTable__Group_1__0__Impl6847);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3274:1: rule__FromTable__Group_1__1 : rule__FromTable__Group_1__1__Impl rule__FromTable__Group_1__2 ;
    public final void rule__FromTable__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3278:1: ( rule__FromTable__Group_1__1__Impl rule__FromTable__Group_1__2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3279:2: rule__FromTable__Group_1__1__Impl rule__FromTable__Group_1__2
            {
            pushFollow(FOLLOW_rule__FromTable__Group_1__1__Impl_in_rule__FromTable__Group_1__16877);
            rule__FromTable__Group_1__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__FromTable__Group_1__2_in_rule__FromTable__Group_1__16880);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3286:1: rule__FromTable__Group_1__1__Impl : ( ( rule__FromTable__OnTableAssignment_1_1 ) ) ;
    public final void rule__FromTable__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3290:1: ( ( ( rule__FromTable__OnTableAssignment_1_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3291:1: ( ( rule__FromTable__OnTableAssignment_1_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3291:1: ( ( rule__FromTable__OnTableAssignment_1_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3292:1: ( rule__FromTable__OnTableAssignment_1_1 )
            {
             before(grammarAccess.getFromTableAccess().getOnTableAssignment_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3293:1: ( rule__FromTable__OnTableAssignment_1_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3293:2: rule__FromTable__OnTableAssignment_1_1
            {
            pushFollow(FOLLOW_rule__FromTable__OnTableAssignment_1_1_in_rule__FromTable__Group_1__1__Impl6907);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3303:1: rule__FromTable__Group_1__2 : rule__FromTable__Group_1__2__Impl rule__FromTable__Group_1__3 ;
    public final void rule__FromTable__Group_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3307:1: ( rule__FromTable__Group_1__2__Impl rule__FromTable__Group_1__3 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3308:2: rule__FromTable__Group_1__2__Impl rule__FromTable__Group_1__3
            {
            pushFollow(FOLLOW_rule__FromTable__Group_1__2__Impl_in_rule__FromTable__Group_1__26937);
            rule__FromTable__Group_1__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__FromTable__Group_1__3_in_rule__FromTable__Group_1__26940);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3315:1: rule__FromTable__Group_1__2__Impl : ( KEYWORD_20 ) ;
    public final void rule__FromTable__Group_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3319:1: ( ( KEYWORD_20 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3320:1: ( KEYWORD_20 )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3320:1: ( KEYWORD_20 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3321:1: KEYWORD_20
            {
             before(grammarAccess.getFromTableAccess().getONKeyword_1_2()); 
            match(input,KEYWORD_20,FOLLOW_KEYWORD_20_in_rule__FromTable__Group_1__2__Impl6968); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3334:1: rule__FromTable__Group_1__3 : rule__FromTable__Group_1__3__Impl ;
    public final void rule__FromTable__Group_1__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3338:1: ( rule__FromTable__Group_1__3__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3339:2: rule__FromTable__Group_1__3__Impl
            {
            pushFollow(FOLLOW_rule__FromTable__Group_1__3__Impl_in_rule__FromTable__Group_1__36999);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3345:1: rule__FromTable__Group_1__3__Impl : ( ( rule__FromTable__JoinExprAssignment_1_3 ) ) ;
    public final void rule__FromTable__Group_1__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3349:1: ( ( ( rule__FromTable__JoinExprAssignment_1_3 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3350:1: ( ( rule__FromTable__JoinExprAssignment_1_3 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3350:1: ( ( rule__FromTable__JoinExprAssignment_1_3 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3351:1: ( rule__FromTable__JoinExprAssignment_1_3 )
            {
             before(grammarAccess.getFromTableAccess().getJoinExprAssignment_1_3()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3352:1: ( rule__FromTable__JoinExprAssignment_1_3 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3352:2: rule__FromTable__JoinExprAssignment_1_3
            {
            pushFollow(FOLLOW_rule__FromTable__JoinExprAssignment_1_3_in_rule__FromTable__Group_1__3__Impl7026);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3370:1: rule__TableOrAlias__Group__0 : rule__TableOrAlias__Group__0__Impl rule__TableOrAlias__Group__1 ;
    public final void rule__TableOrAlias__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3374:1: ( rule__TableOrAlias__Group__0__Impl rule__TableOrAlias__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3375:2: rule__TableOrAlias__Group__0__Impl rule__TableOrAlias__Group__1
            {
            pushFollow(FOLLOW_rule__TableOrAlias__Group__0__Impl_in_rule__TableOrAlias__Group__07064);
            rule__TableOrAlias__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__TableOrAlias__Group__1_in_rule__TableOrAlias__Group__07067);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3382:1: rule__TableOrAlias__Group__0__Impl : ( ( rule__TableOrAlias__TfullAssignment_0 ) ) ;
    public final void rule__TableOrAlias__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3386:1: ( ( ( rule__TableOrAlias__TfullAssignment_0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3387:1: ( ( rule__TableOrAlias__TfullAssignment_0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3387:1: ( ( rule__TableOrAlias__TfullAssignment_0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3388:1: ( rule__TableOrAlias__TfullAssignment_0 )
            {
             before(grammarAccess.getTableOrAliasAccess().getTfullAssignment_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3389:1: ( rule__TableOrAlias__TfullAssignment_0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3389:2: rule__TableOrAlias__TfullAssignment_0
            {
            pushFollow(FOLLOW_rule__TableOrAlias__TfullAssignment_0_in_rule__TableOrAlias__Group__0__Impl7094);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3399:1: rule__TableOrAlias__Group__1 : rule__TableOrAlias__Group__1__Impl rule__TableOrAlias__Group__2 ;
    public final void rule__TableOrAlias__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3403:1: ( rule__TableOrAlias__Group__1__Impl rule__TableOrAlias__Group__2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3404:2: rule__TableOrAlias__Group__1__Impl rule__TableOrAlias__Group__2
            {
            pushFollow(FOLLOW_rule__TableOrAlias__Group__1__Impl_in_rule__TableOrAlias__Group__17124);
            rule__TableOrAlias__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__TableOrAlias__Group__2_in_rule__TableOrAlias__Group__17127);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3411:1: rule__TableOrAlias__Group__1__Impl : ( ( rule__TableOrAlias__AliasAssignment_1 )? ) ;
    public final void rule__TableOrAlias__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3415:1: ( ( ( rule__TableOrAlias__AliasAssignment_1 )? ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3416:1: ( ( rule__TableOrAlias__AliasAssignment_1 )? )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3416:1: ( ( rule__TableOrAlias__AliasAssignment_1 )? )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3417:1: ( rule__TableOrAlias__AliasAssignment_1 )?
            {
             before(grammarAccess.getTableOrAliasAccess().getAliasAssignment_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3418:1: ( rule__TableOrAlias__AliasAssignment_1 )?
            int alt34=2;
            int LA34_0 = input.LA(1);

            if ( (LA34_0==KEYWORD_18) ) {
                alt34=1;
            }
            switch (alt34) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3418:2: rule__TableOrAlias__AliasAssignment_1
                    {
                    pushFollow(FOLLOW_rule__TableOrAlias__AliasAssignment_1_in_rule__TableOrAlias__Group__1__Impl7154);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3428:1: rule__TableOrAlias__Group__2 : rule__TableOrAlias__Group__2__Impl ;
    public final void rule__TableOrAlias__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3432:1: ( rule__TableOrAlias__Group__2__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3433:2: rule__TableOrAlias__Group__2__Impl
            {
            pushFollow(FOLLOW_rule__TableOrAlias__Group__2__Impl_in_rule__TableOrAlias__Group__27185);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3439:1: rule__TableOrAlias__Group__2__Impl : ( ( rule__TableOrAlias__TblAliasAssignment_2 )? ) ;
    public final void rule__TableOrAlias__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3443:1: ( ( ( rule__TableOrAlias__TblAliasAssignment_2 )? ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3444:1: ( ( rule__TableOrAlias__TblAliasAssignment_2 )? )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3444:1: ( ( rule__TableOrAlias__TblAliasAssignment_2 )? )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3445:1: ( rule__TableOrAlias__TblAliasAssignment_2 )?
            {
             before(grammarAccess.getTableOrAliasAccess().getTblAliasAssignment_2()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3446:1: ( rule__TableOrAlias__TblAliasAssignment_2 )?
            int alt35=2;
            int LA35_0 = input.LA(1);

            if ( (LA35_0==RULE_ID) ) {
                alt35=1;
            }
            switch (alt35) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3446:2: rule__TableOrAlias__TblAliasAssignment_2
                    {
                    pushFollow(FOLLOW_rule__TableOrAlias__TblAliasAssignment_2_in_rule__TableOrAlias__Group__2__Impl7212);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3462:1: rule__TableFull__Group__0 : rule__TableFull__Group__0__Impl rule__TableFull__Group__1 ;
    public final void rule__TableFull__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3466:1: ( rule__TableFull__Group__0__Impl rule__TableFull__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3467:2: rule__TableFull__Group__0__Impl rule__TableFull__Group__1
            {
            pushFollow(FOLLOW_rule__TableFull__Group__0__Impl_in_rule__TableFull__Group__07249);
            rule__TableFull__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__TableFull__Group__1_in_rule__TableFull__Group__07252);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3474:1: rule__TableFull__Group__0__Impl : ( ruleDbObjectName ) ;
    public final void rule__TableFull__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3478:1: ( ( ruleDbObjectName ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3479:1: ( ruleDbObjectName )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3479:1: ( ruleDbObjectName )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3480:1: ruleDbObjectName
            {
             before(grammarAccess.getTableFullAccess().getDbObjectNameParserRuleCall_0()); 
            pushFollow(FOLLOW_ruleDbObjectName_in_rule__TableFull__Group__0__Impl7279);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3491:1: rule__TableFull__Group__1 : rule__TableFull__Group__1__Impl ;
    public final void rule__TableFull__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3495:1: ( rule__TableFull__Group__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3496:2: rule__TableFull__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__TableFull__Group__1__Impl_in_rule__TableFull__Group__17308);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3502:1: rule__TableFull__Group__1__Impl : ( ( rule__TableFull__Group_1__0 )? ) ;
    public final void rule__TableFull__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3506:1: ( ( ( rule__TableFull__Group_1__0 )? ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3507:1: ( ( rule__TableFull__Group_1__0 )? )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3507:1: ( ( rule__TableFull__Group_1__0 )? )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3508:1: ( rule__TableFull__Group_1__0 )?
            {
             before(grammarAccess.getTableFullAccess().getGroup_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3509:1: ( rule__TableFull__Group_1__0 )?
            int alt36=2;
            int LA36_0 = input.LA(1);

            if ( (LA36_0==KEYWORD_6) ) {
                alt36=1;
            }
            switch (alt36) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3509:2: rule__TableFull__Group_1__0
                    {
                    pushFollow(FOLLOW_rule__TableFull__Group_1__0_in_rule__TableFull__Group__1__Impl7335);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3523:1: rule__TableFull__Group_1__0 : rule__TableFull__Group_1__0__Impl rule__TableFull__Group_1__1 ;
    public final void rule__TableFull__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3527:1: ( rule__TableFull__Group_1__0__Impl rule__TableFull__Group_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3528:2: rule__TableFull__Group_1__0__Impl rule__TableFull__Group_1__1
            {
            pushFollow(FOLLOW_rule__TableFull__Group_1__0__Impl_in_rule__TableFull__Group_1__07370);
            rule__TableFull__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__TableFull__Group_1__1_in_rule__TableFull__Group_1__07373);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3535:1: rule__TableFull__Group_1__0__Impl : ( () ) ;
    public final void rule__TableFull__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3539:1: ( ( () ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3540:1: ( () )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3540:1: ( () )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3541:1: ()
            {
             before(grammarAccess.getTableFullAccess().getTblsEntriesAction_1_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3542:1: ()
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3544:1: 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3554:1: rule__TableFull__Group_1__1 : rule__TableFull__Group_1__1__Impl ;
    public final void rule__TableFull__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3558:1: ( rule__TableFull__Group_1__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3559:2: rule__TableFull__Group_1__1__Impl
            {
            pushFollow(FOLLOW_rule__TableFull__Group_1__1__Impl_in_rule__TableFull__Group_1__17431);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3565:1: rule__TableFull__Group_1__1__Impl : ( ( ( rule__TableFull__Group_1_1__0 ) ) ( ( rule__TableFull__Group_1_1__0 )* ) ) ;
    public final void rule__TableFull__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3569:1: ( ( ( ( rule__TableFull__Group_1_1__0 ) ) ( ( rule__TableFull__Group_1_1__0 )* ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3570:1: ( ( ( rule__TableFull__Group_1_1__0 ) ) ( ( rule__TableFull__Group_1_1__0 )* ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3570:1: ( ( ( rule__TableFull__Group_1_1__0 ) ) ( ( rule__TableFull__Group_1_1__0 )* ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3571:1: ( ( rule__TableFull__Group_1_1__0 ) ) ( ( rule__TableFull__Group_1_1__0 )* )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3571:1: ( ( rule__TableFull__Group_1_1__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3572:1: ( rule__TableFull__Group_1_1__0 )
            {
             before(grammarAccess.getTableFullAccess().getGroup_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3573:1: ( rule__TableFull__Group_1_1__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3573:2: rule__TableFull__Group_1_1__0
            {
            pushFollow(FOLLOW_rule__TableFull__Group_1_1__0_in_rule__TableFull__Group_1__1__Impl7460);
            rule__TableFull__Group_1_1__0();

            state._fsp--;


            }

             after(grammarAccess.getTableFullAccess().getGroup_1_1()); 

            }

            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3576:1: ( ( rule__TableFull__Group_1_1__0 )* )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3577:1: ( rule__TableFull__Group_1_1__0 )*
            {
             before(grammarAccess.getTableFullAccess().getGroup_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3578:1: ( rule__TableFull__Group_1_1__0 )*
            loop37:
            do {
                int alt37=2;
                int LA37_0 = input.LA(1);

                if ( (LA37_0==KEYWORD_6) ) {
                    alt37=1;
                }


                switch (alt37) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3578:2: rule__TableFull__Group_1_1__0
            	    {
            	    pushFollow(FOLLOW_rule__TableFull__Group_1_1__0_in_rule__TableFull__Group_1__1__Impl7472);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3593:1: rule__TableFull__Group_1_1__0 : rule__TableFull__Group_1_1__0__Impl rule__TableFull__Group_1_1__1 ;
    public final void rule__TableFull__Group_1_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3597:1: ( rule__TableFull__Group_1_1__0__Impl rule__TableFull__Group_1_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3598:2: rule__TableFull__Group_1_1__0__Impl rule__TableFull__Group_1_1__1
            {
            pushFollow(FOLLOW_rule__TableFull__Group_1_1__0__Impl_in_rule__TableFull__Group_1_1__07509);
            rule__TableFull__Group_1_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__TableFull__Group_1_1__1_in_rule__TableFull__Group_1_1__07512);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3605:1: rule__TableFull__Group_1_1__0__Impl : ( KEYWORD_6 ) ;
    public final void rule__TableFull__Group_1_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3609:1: ( ( KEYWORD_6 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3610:1: ( KEYWORD_6 )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3610:1: ( KEYWORD_6 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3611:1: KEYWORD_6
            {
             before(grammarAccess.getTableFullAccess().getFullStopKeyword_1_1_0()); 
            match(input,KEYWORD_6,FOLLOW_KEYWORD_6_in_rule__TableFull__Group_1_1__0__Impl7540); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3624:1: rule__TableFull__Group_1_1__1 : rule__TableFull__Group_1_1__1__Impl ;
    public final void rule__TableFull__Group_1_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3628:1: ( rule__TableFull__Group_1_1__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3629:2: rule__TableFull__Group_1_1__1__Impl
            {
            pushFollow(FOLLOW_rule__TableFull__Group_1_1__1__Impl_in_rule__TableFull__Group_1_1__17571);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3635:1: rule__TableFull__Group_1_1__1__Impl : ( ( rule__TableFull__EntriesAssignment_1_1_1 ) ) ;
    public final void rule__TableFull__Group_1_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3639:1: ( ( ( rule__TableFull__EntriesAssignment_1_1_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3640:1: ( ( rule__TableFull__EntriesAssignment_1_1_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3640:1: ( ( rule__TableFull__EntriesAssignment_1_1_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3641:1: ( rule__TableFull__EntriesAssignment_1_1_1 )
            {
             before(grammarAccess.getTableFullAccess().getEntriesAssignment_1_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3642:1: ( rule__TableFull__EntriesAssignment_1_1_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3642:2: rule__TableFull__EntriesAssignment_1_1_1
            {
            pushFollow(FOLLOW_rule__TableFull__EntriesAssignment_1_1_1_in_rule__TableFull__Group_1_1__1__Impl7598);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3656:1: rule__OrderByColumns__Group__0 : rule__OrderByColumns__Group__0__Impl rule__OrderByColumns__Group__1 ;
    public final void rule__OrderByColumns__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3660:1: ( rule__OrderByColumns__Group__0__Impl rule__OrderByColumns__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3661:2: rule__OrderByColumns__Group__0__Impl rule__OrderByColumns__Group__1
            {
            pushFollow(FOLLOW_rule__OrderByColumns__Group__0__Impl_in_rule__OrderByColumns__Group__07632);
            rule__OrderByColumns__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__OrderByColumns__Group__1_in_rule__OrderByColumns__Group__07635);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3668:1: rule__OrderByColumns__Group__0__Impl : ( ruleOrderByColumnFull ) ;
    public final void rule__OrderByColumns__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3672:1: ( ( ruleOrderByColumnFull ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3673:1: ( ruleOrderByColumnFull )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3673:1: ( ruleOrderByColumnFull )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3674:1: ruleOrderByColumnFull
            {
             before(grammarAccess.getOrderByColumnsAccess().getOrderByColumnFullParserRuleCall_0()); 
            pushFollow(FOLLOW_ruleOrderByColumnFull_in_rule__OrderByColumns__Group__0__Impl7662);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3685:1: rule__OrderByColumns__Group__1 : rule__OrderByColumns__Group__1__Impl ;
    public final void rule__OrderByColumns__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3689:1: ( rule__OrderByColumns__Group__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3690:2: rule__OrderByColumns__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__OrderByColumns__Group__1__Impl_in_rule__OrderByColumns__Group__17691);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3696:1: rule__OrderByColumns__Group__1__Impl : ( ( rule__OrderByColumns__Group_1__0 )? ) ;
    public final void rule__OrderByColumns__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3700:1: ( ( ( rule__OrderByColumns__Group_1__0 )? ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3701:1: ( ( rule__OrderByColumns__Group_1__0 )? )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3701:1: ( ( rule__OrderByColumns__Group_1__0 )? )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3702:1: ( rule__OrderByColumns__Group_1__0 )?
            {
             before(grammarAccess.getOrderByColumnsAccess().getGroup_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3703:1: ( rule__OrderByColumns__Group_1__0 )?
            int alt38=2;
            int LA38_0 = input.LA(1);

            if ( (LA38_0==KEYWORD_4) ) {
                alt38=1;
            }
            switch (alt38) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3703:2: rule__OrderByColumns__Group_1__0
                    {
                    pushFollow(FOLLOW_rule__OrderByColumns__Group_1__0_in_rule__OrderByColumns__Group__1__Impl7718);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3717:1: rule__OrderByColumns__Group_1__0 : rule__OrderByColumns__Group_1__0__Impl rule__OrderByColumns__Group_1__1 ;
    public final void rule__OrderByColumns__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3721:1: ( rule__OrderByColumns__Group_1__0__Impl rule__OrderByColumns__Group_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3722:2: rule__OrderByColumns__Group_1__0__Impl rule__OrderByColumns__Group_1__1
            {
            pushFollow(FOLLOW_rule__OrderByColumns__Group_1__0__Impl_in_rule__OrderByColumns__Group_1__07753);
            rule__OrderByColumns__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__OrderByColumns__Group_1__1_in_rule__OrderByColumns__Group_1__07756);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3729:1: rule__OrderByColumns__Group_1__0__Impl : ( () ) ;
    public final void rule__OrderByColumns__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3733:1: ( ( () ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3734:1: ( () )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3734:1: ( () )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3735:1: ()
            {
             before(grammarAccess.getOrderByColumnsAccess().getOrOrderByColumnEntriesAction_1_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3736:1: ()
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3738:1: 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3748:1: rule__OrderByColumns__Group_1__1 : rule__OrderByColumns__Group_1__1__Impl ;
    public final void rule__OrderByColumns__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3752:1: ( rule__OrderByColumns__Group_1__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3753:2: rule__OrderByColumns__Group_1__1__Impl
            {
            pushFollow(FOLLOW_rule__OrderByColumns__Group_1__1__Impl_in_rule__OrderByColumns__Group_1__17814);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3759:1: rule__OrderByColumns__Group_1__1__Impl : ( ( ( rule__OrderByColumns__Group_1_1__0 ) ) ( ( rule__OrderByColumns__Group_1_1__0 )* ) ) ;
    public final void rule__OrderByColumns__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3763:1: ( ( ( ( rule__OrderByColumns__Group_1_1__0 ) ) ( ( rule__OrderByColumns__Group_1_1__0 )* ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3764:1: ( ( ( rule__OrderByColumns__Group_1_1__0 ) ) ( ( rule__OrderByColumns__Group_1_1__0 )* ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3764:1: ( ( ( rule__OrderByColumns__Group_1_1__0 ) ) ( ( rule__OrderByColumns__Group_1_1__0 )* ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3765:1: ( ( rule__OrderByColumns__Group_1_1__0 ) ) ( ( rule__OrderByColumns__Group_1_1__0 )* )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3765:1: ( ( rule__OrderByColumns__Group_1_1__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3766:1: ( rule__OrderByColumns__Group_1_1__0 )
            {
             before(grammarAccess.getOrderByColumnsAccess().getGroup_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3767:1: ( rule__OrderByColumns__Group_1_1__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3767:2: rule__OrderByColumns__Group_1_1__0
            {
            pushFollow(FOLLOW_rule__OrderByColumns__Group_1_1__0_in_rule__OrderByColumns__Group_1__1__Impl7843);
            rule__OrderByColumns__Group_1_1__0();

            state._fsp--;


            }

             after(grammarAccess.getOrderByColumnsAccess().getGroup_1_1()); 

            }

            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3770:1: ( ( rule__OrderByColumns__Group_1_1__0 )* )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3771:1: ( rule__OrderByColumns__Group_1_1__0 )*
            {
             before(grammarAccess.getOrderByColumnsAccess().getGroup_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3772:1: ( rule__OrderByColumns__Group_1_1__0 )*
            loop39:
            do {
                int alt39=2;
                int LA39_0 = input.LA(1);

                if ( (LA39_0==KEYWORD_4) ) {
                    alt39=1;
                }


                switch (alt39) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3772:2: rule__OrderByColumns__Group_1_1__0
            	    {
            	    pushFollow(FOLLOW_rule__OrderByColumns__Group_1_1__0_in_rule__OrderByColumns__Group_1__1__Impl7855);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3787:1: rule__OrderByColumns__Group_1_1__0 : rule__OrderByColumns__Group_1_1__0__Impl rule__OrderByColumns__Group_1_1__1 ;
    public final void rule__OrderByColumns__Group_1_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3791:1: ( rule__OrderByColumns__Group_1_1__0__Impl rule__OrderByColumns__Group_1_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3792:2: rule__OrderByColumns__Group_1_1__0__Impl rule__OrderByColumns__Group_1_1__1
            {
            pushFollow(FOLLOW_rule__OrderByColumns__Group_1_1__0__Impl_in_rule__OrderByColumns__Group_1_1__07892);
            rule__OrderByColumns__Group_1_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__OrderByColumns__Group_1_1__1_in_rule__OrderByColumns__Group_1_1__07895);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3799:1: rule__OrderByColumns__Group_1_1__0__Impl : ( KEYWORD_4 ) ;
    public final void rule__OrderByColumns__Group_1_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3803:1: ( ( KEYWORD_4 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3804:1: ( KEYWORD_4 )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3804:1: ( KEYWORD_4 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3805:1: KEYWORD_4
            {
             before(grammarAccess.getOrderByColumnsAccess().getCommaKeyword_1_1_0()); 
            match(input,KEYWORD_4,FOLLOW_KEYWORD_4_in_rule__OrderByColumns__Group_1_1__0__Impl7923); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3818:1: rule__OrderByColumns__Group_1_1__1 : rule__OrderByColumns__Group_1_1__1__Impl ;
    public final void rule__OrderByColumns__Group_1_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3822:1: ( rule__OrderByColumns__Group_1_1__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3823:2: rule__OrderByColumns__Group_1_1__1__Impl
            {
            pushFollow(FOLLOW_rule__OrderByColumns__Group_1_1__1__Impl_in_rule__OrderByColumns__Group_1_1__17954);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3829:1: rule__OrderByColumns__Group_1_1__1__Impl : ( ( rule__OrderByColumns__EntriesAssignment_1_1_1 ) ) ;
    public final void rule__OrderByColumns__Group_1_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3833:1: ( ( ( rule__OrderByColumns__EntriesAssignment_1_1_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3834:1: ( ( rule__OrderByColumns__EntriesAssignment_1_1_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3834:1: ( ( rule__OrderByColumns__EntriesAssignment_1_1_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3835:1: ( rule__OrderByColumns__EntriesAssignment_1_1_1 )
            {
             before(grammarAccess.getOrderByColumnsAccess().getEntriesAssignment_1_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3836:1: ( rule__OrderByColumns__EntriesAssignment_1_1_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3836:2: rule__OrderByColumns__EntriesAssignment_1_1_1
            {
            pushFollow(FOLLOW_rule__OrderByColumns__EntriesAssignment_1_1_1_in_rule__OrderByColumns__Group_1_1__1__Impl7981);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3850:1: rule__OrderByColumnFull__Group__0 : rule__OrderByColumnFull__Group__0__Impl rule__OrderByColumnFull__Group__1 ;
    public final void rule__OrderByColumnFull__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3854:1: ( rule__OrderByColumnFull__Group__0__Impl rule__OrderByColumnFull__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3855:2: rule__OrderByColumnFull__Group__0__Impl rule__OrderByColumnFull__Group__1
            {
            pushFollow(FOLLOW_rule__OrderByColumnFull__Group__0__Impl_in_rule__OrderByColumnFull__Group__08015);
            rule__OrderByColumnFull__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__OrderByColumnFull__Group__1_in_rule__OrderByColumnFull__Group__08018);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3862:1: rule__OrderByColumnFull__Group__0__Impl : ( ( rule__OrderByColumnFull__ColOrderAssignment_0 ) ) ;
    public final void rule__OrderByColumnFull__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3866:1: ( ( ( rule__OrderByColumnFull__ColOrderAssignment_0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3867:1: ( ( rule__OrderByColumnFull__ColOrderAssignment_0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3867:1: ( ( rule__OrderByColumnFull__ColOrderAssignment_0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3868:1: ( rule__OrderByColumnFull__ColOrderAssignment_0 )
            {
             before(grammarAccess.getOrderByColumnFullAccess().getColOrderAssignment_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3869:1: ( rule__OrderByColumnFull__ColOrderAssignment_0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3869:2: rule__OrderByColumnFull__ColOrderAssignment_0
            {
            pushFollow(FOLLOW_rule__OrderByColumnFull__ColOrderAssignment_0_in_rule__OrderByColumnFull__Group__0__Impl8045);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3879:1: rule__OrderByColumnFull__Group__1 : rule__OrderByColumnFull__Group__1__Impl ;
    public final void rule__OrderByColumnFull__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3883:1: ( rule__OrderByColumnFull__Group__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3884:2: rule__OrderByColumnFull__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__OrderByColumnFull__Group__1__Impl_in_rule__OrderByColumnFull__Group__18075);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3890:1: rule__OrderByColumnFull__Group__1__Impl : ( ( rule__OrderByColumnFull__DirectionAssignment_1 )? ) ;
    public final void rule__OrderByColumnFull__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3894:1: ( ( ( rule__OrderByColumnFull__DirectionAssignment_1 )? ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3895:1: ( ( rule__OrderByColumnFull__DirectionAssignment_1 )? )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3895:1: ( ( rule__OrderByColumnFull__DirectionAssignment_1 )? )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3896:1: ( rule__OrderByColumnFull__DirectionAssignment_1 )?
            {
             before(grammarAccess.getOrderByColumnFullAccess().getDirectionAssignment_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3897:1: ( rule__OrderByColumnFull__DirectionAssignment_1 )?
            int alt40=2;
            int LA40_0 = input.LA(1);

            if ( (LA40_0==KEYWORD_26||LA40_0==KEYWORD_25) ) {
                alt40=1;
            }
            switch (alt40) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3897:2: rule__OrderByColumnFull__DirectionAssignment_1
                    {
                    pushFollow(FOLLOW_rule__OrderByColumnFull__DirectionAssignment_1_in_rule__OrderByColumnFull__Group__1__Impl8102);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3911:1: rule__GroupByColumns__Group__0 : rule__GroupByColumns__Group__0__Impl rule__GroupByColumns__Group__1 ;
    public final void rule__GroupByColumns__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3915:1: ( rule__GroupByColumns__Group__0__Impl rule__GroupByColumns__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3916:2: rule__GroupByColumns__Group__0__Impl rule__GroupByColumns__Group__1
            {
            pushFollow(FOLLOW_rule__GroupByColumns__Group__0__Impl_in_rule__GroupByColumns__Group__08137);
            rule__GroupByColumns__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__GroupByColumns__Group__1_in_rule__GroupByColumns__Group__08140);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3923:1: rule__GroupByColumns__Group__0__Impl : ( ruleGroupByColumnFull ) ;
    public final void rule__GroupByColumns__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3927:1: ( ( ruleGroupByColumnFull ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3928:1: ( ruleGroupByColumnFull )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3928:1: ( ruleGroupByColumnFull )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3929:1: ruleGroupByColumnFull
            {
             before(grammarAccess.getGroupByColumnsAccess().getGroupByColumnFullParserRuleCall_0()); 
            pushFollow(FOLLOW_ruleGroupByColumnFull_in_rule__GroupByColumns__Group__0__Impl8167);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3940:1: rule__GroupByColumns__Group__1 : rule__GroupByColumns__Group__1__Impl ;
    public final void rule__GroupByColumns__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3944:1: ( rule__GroupByColumns__Group__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3945:2: rule__GroupByColumns__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__GroupByColumns__Group__1__Impl_in_rule__GroupByColumns__Group__18196);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3951:1: rule__GroupByColumns__Group__1__Impl : ( ( rule__GroupByColumns__Group_1__0 )? ) ;
    public final void rule__GroupByColumns__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3955:1: ( ( ( rule__GroupByColumns__Group_1__0 )? ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3956:1: ( ( rule__GroupByColumns__Group_1__0 )? )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3956:1: ( ( rule__GroupByColumns__Group_1__0 )? )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3957:1: ( rule__GroupByColumns__Group_1__0 )?
            {
             before(grammarAccess.getGroupByColumnsAccess().getGroup_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3958:1: ( rule__GroupByColumns__Group_1__0 )?
            int alt41=2;
            int LA41_0 = input.LA(1);

            if ( (LA41_0==KEYWORD_4) ) {
                alt41=1;
            }
            switch (alt41) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3958:2: rule__GroupByColumns__Group_1__0
                    {
                    pushFollow(FOLLOW_rule__GroupByColumns__Group_1__0_in_rule__GroupByColumns__Group__1__Impl8223);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3972:1: rule__GroupByColumns__Group_1__0 : rule__GroupByColumns__Group_1__0__Impl rule__GroupByColumns__Group_1__1 ;
    public final void rule__GroupByColumns__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3976:1: ( rule__GroupByColumns__Group_1__0__Impl rule__GroupByColumns__Group_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3977:2: rule__GroupByColumns__Group_1__0__Impl rule__GroupByColumns__Group_1__1
            {
            pushFollow(FOLLOW_rule__GroupByColumns__Group_1__0__Impl_in_rule__GroupByColumns__Group_1__08258);
            rule__GroupByColumns__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__GroupByColumns__Group_1__1_in_rule__GroupByColumns__Group_1__08261);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3984:1: rule__GroupByColumns__Group_1__0__Impl : ( () ) ;
    public final void rule__GroupByColumns__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3988:1: ( ( () ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3989:1: ( () )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3989:1: ( () )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3990:1: ()
            {
             before(grammarAccess.getGroupByColumnsAccess().getOrGroupByColumnEntriesAction_1_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3991:1: ()
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:3993:1: 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4003:1: rule__GroupByColumns__Group_1__1 : rule__GroupByColumns__Group_1__1__Impl ;
    public final void rule__GroupByColumns__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4007:1: ( rule__GroupByColumns__Group_1__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4008:2: rule__GroupByColumns__Group_1__1__Impl
            {
            pushFollow(FOLLOW_rule__GroupByColumns__Group_1__1__Impl_in_rule__GroupByColumns__Group_1__18319);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4014:1: rule__GroupByColumns__Group_1__1__Impl : ( ( ( rule__GroupByColumns__Group_1_1__0 ) ) ( ( rule__GroupByColumns__Group_1_1__0 )* ) ) ;
    public final void rule__GroupByColumns__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4018:1: ( ( ( ( rule__GroupByColumns__Group_1_1__0 ) ) ( ( rule__GroupByColumns__Group_1_1__0 )* ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4019:1: ( ( ( rule__GroupByColumns__Group_1_1__0 ) ) ( ( rule__GroupByColumns__Group_1_1__0 )* ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4019:1: ( ( ( rule__GroupByColumns__Group_1_1__0 ) ) ( ( rule__GroupByColumns__Group_1_1__0 )* ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4020:1: ( ( rule__GroupByColumns__Group_1_1__0 ) ) ( ( rule__GroupByColumns__Group_1_1__0 )* )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4020:1: ( ( rule__GroupByColumns__Group_1_1__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4021:1: ( rule__GroupByColumns__Group_1_1__0 )
            {
             before(grammarAccess.getGroupByColumnsAccess().getGroup_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4022:1: ( rule__GroupByColumns__Group_1_1__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4022:2: rule__GroupByColumns__Group_1_1__0
            {
            pushFollow(FOLLOW_rule__GroupByColumns__Group_1_1__0_in_rule__GroupByColumns__Group_1__1__Impl8348);
            rule__GroupByColumns__Group_1_1__0();

            state._fsp--;


            }

             after(grammarAccess.getGroupByColumnsAccess().getGroup_1_1()); 

            }

            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4025:1: ( ( rule__GroupByColumns__Group_1_1__0 )* )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4026:1: ( rule__GroupByColumns__Group_1_1__0 )*
            {
             before(grammarAccess.getGroupByColumnsAccess().getGroup_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4027:1: ( rule__GroupByColumns__Group_1_1__0 )*
            loop42:
            do {
                int alt42=2;
                int LA42_0 = input.LA(1);

                if ( (LA42_0==KEYWORD_4) ) {
                    alt42=1;
                }


                switch (alt42) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4027:2: rule__GroupByColumns__Group_1_1__0
            	    {
            	    pushFollow(FOLLOW_rule__GroupByColumns__Group_1_1__0_in_rule__GroupByColumns__Group_1__1__Impl8360);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4042:1: rule__GroupByColumns__Group_1_1__0 : rule__GroupByColumns__Group_1_1__0__Impl rule__GroupByColumns__Group_1_1__1 ;
    public final void rule__GroupByColumns__Group_1_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4046:1: ( rule__GroupByColumns__Group_1_1__0__Impl rule__GroupByColumns__Group_1_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4047:2: rule__GroupByColumns__Group_1_1__0__Impl rule__GroupByColumns__Group_1_1__1
            {
            pushFollow(FOLLOW_rule__GroupByColumns__Group_1_1__0__Impl_in_rule__GroupByColumns__Group_1_1__08397);
            rule__GroupByColumns__Group_1_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__GroupByColumns__Group_1_1__1_in_rule__GroupByColumns__Group_1_1__08400);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4054:1: rule__GroupByColumns__Group_1_1__0__Impl : ( KEYWORD_4 ) ;
    public final void rule__GroupByColumns__Group_1_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4058:1: ( ( KEYWORD_4 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4059:1: ( KEYWORD_4 )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4059:1: ( KEYWORD_4 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4060:1: KEYWORD_4
            {
             before(grammarAccess.getGroupByColumnsAccess().getCommaKeyword_1_1_0()); 
            match(input,KEYWORD_4,FOLLOW_KEYWORD_4_in_rule__GroupByColumns__Group_1_1__0__Impl8428); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4073:1: rule__GroupByColumns__Group_1_1__1 : rule__GroupByColumns__Group_1_1__1__Impl ;
    public final void rule__GroupByColumns__Group_1_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4077:1: ( rule__GroupByColumns__Group_1_1__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4078:2: rule__GroupByColumns__Group_1_1__1__Impl
            {
            pushFollow(FOLLOW_rule__GroupByColumns__Group_1_1__1__Impl_in_rule__GroupByColumns__Group_1_1__18459);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4084:1: rule__GroupByColumns__Group_1_1__1__Impl : ( ( rule__GroupByColumns__EntriesAssignment_1_1_1 ) ) ;
    public final void rule__GroupByColumns__Group_1_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4088:1: ( ( ( rule__GroupByColumns__EntriesAssignment_1_1_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4089:1: ( ( rule__GroupByColumns__EntriesAssignment_1_1_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4089:1: ( ( rule__GroupByColumns__EntriesAssignment_1_1_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4090:1: ( rule__GroupByColumns__EntriesAssignment_1_1_1 )
            {
             before(grammarAccess.getGroupByColumnsAccess().getEntriesAssignment_1_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4091:1: ( rule__GroupByColumns__EntriesAssignment_1_1_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4091:2: rule__GroupByColumns__EntriesAssignment_1_1_1
            {
            pushFollow(FOLLOW_rule__GroupByColumns__EntriesAssignment_1_1_1_in_rule__GroupByColumns__Group_1_1__1__Impl8486);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4105:1: rule__FullExpression__Group__0 : rule__FullExpression__Group__0__Impl rule__FullExpression__Group__1 ;
    public final void rule__FullExpression__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4109:1: ( rule__FullExpression__Group__0__Impl rule__FullExpression__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4110:2: rule__FullExpression__Group__0__Impl rule__FullExpression__Group__1
            {
            pushFollow(FOLLOW_rule__FullExpression__Group__0__Impl_in_rule__FullExpression__Group__08520);
            rule__FullExpression__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__FullExpression__Group__1_in_rule__FullExpression__Group__08523);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4117:1: rule__FullExpression__Group__0__Impl : ( ruleExpressionFragment ) ;
    public final void rule__FullExpression__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4121:1: ( ( ruleExpressionFragment ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4122:1: ( ruleExpressionFragment )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4122:1: ( ruleExpressionFragment )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4123:1: ruleExpressionFragment
            {
             before(grammarAccess.getFullExpressionAccess().getExpressionFragmentParserRuleCall_0()); 
            pushFollow(FOLLOW_ruleExpressionFragment_in_rule__FullExpression__Group__0__Impl8550);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4134:1: rule__FullExpression__Group__1 : rule__FullExpression__Group__1__Impl ;
    public final void rule__FullExpression__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4138:1: ( rule__FullExpression__Group__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4139:2: rule__FullExpression__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__FullExpression__Group__1__Impl_in_rule__FullExpression__Group__18579);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4145:1: rule__FullExpression__Group__1__Impl : ( ( rule__FullExpression__Group_1__0 )? ) ;
    public final void rule__FullExpression__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4149:1: ( ( ( rule__FullExpression__Group_1__0 )? ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4150:1: ( ( rule__FullExpression__Group_1__0 )? )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4150:1: ( ( rule__FullExpression__Group_1__0 )? )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4151:1: ( rule__FullExpression__Group_1__0 )?
            {
             before(grammarAccess.getFullExpressionAccess().getGroup_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4152:1: ( rule__FullExpression__Group_1__0 )?
            int alt43=2;
            int LA43_0 = input.LA(1);

            if ( (LA43_0==KEYWORD_24||LA43_0==KEYWORD_21) ) {
                alt43=1;
            }
            switch (alt43) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4152:2: rule__FullExpression__Group_1__0
                    {
                    pushFollow(FOLLOW_rule__FullExpression__Group_1__0_in_rule__FullExpression__Group__1__Impl8606);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4166:1: rule__FullExpression__Group_1__0 : rule__FullExpression__Group_1__0__Impl rule__FullExpression__Group_1__1 ;
    public final void rule__FullExpression__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4170:1: ( rule__FullExpression__Group_1__0__Impl rule__FullExpression__Group_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4171:2: rule__FullExpression__Group_1__0__Impl rule__FullExpression__Group_1__1
            {
            pushFollow(FOLLOW_rule__FullExpression__Group_1__0__Impl_in_rule__FullExpression__Group_1__08641);
            rule__FullExpression__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__FullExpression__Group_1__1_in_rule__FullExpression__Group_1__08644);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4178:1: rule__FullExpression__Group_1__0__Impl : ( () ) ;
    public final void rule__FullExpression__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4182:1: ( ( () ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4183:1: ( () )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4183:1: ( () )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4184:1: ()
            {
             before(grammarAccess.getFullExpressionAccess().getOrExprEntriesAction_1_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4185:1: ()
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4187:1: 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4197:1: rule__FullExpression__Group_1__1 : rule__FullExpression__Group_1__1__Impl ;
    public final void rule__FullExpression__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4201:1: ( rule__FullExpression__Group_1__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4202:2: rule__FullExpression__Group_1__1__Impl
            {
            pushFollow(FOLLOW_rule__FullExpression__Group_1__1__Impl_in_rule__FullExpression__Group_1__18702);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4208:1: rule__FullExpression__Group_1__1__Impl : ( ( ( rule__FullExpression__EntriesAssignment_1_1 ) ) ( ( rule__FullExpression__EntriesAssignment_1_1 )* ) ) ;
    public final void rule__FullExpression__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4212:1: ( ( ( ( rule__FullExpression__EntriesAssignment_1_1 ) ) ( ( rule__FullExpression__EntriesAssignment_1_1 )* ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4213:1: ( ( ( rule__FullExpression__EntriesAssignment_1_1 ) ) ( ( rule__FullExpression__EntriesAssignment_1_1 )* ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4213:1: ( ( ( rule__FullExpression__EntriesAssignment_1_1 ) ) ( ( rule__FullExpression__EntriesAssignment_1_1 )* ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4214:1: ( ( rule__FullExpression__EntriesAssignment_1_1 ) ) ( ( rule__FullExpression__EntriesAssignment_1_1 )* )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4214:1: ( ( rule__FullExpression__EntriesAssignment_1_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4215:1: ( rule__FullExpression__EntriesAssignment_1_1 )
            {
             before(grammarAccess.getFullExpressionAccess().getEntriesAssignment_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4216:1: ( rule__FullExpression__EntriesAssignment_1_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4216:2: rule__FullExpression__EntriesAssignment_1_1
            {
            pushFollow(FOLLOW_rule__FullExpression__EntriesAssignment_1_1_in_rule__FullExpression__Group_1__1__Impl8731);
            rule__FullExpression__EntriesAssignment_1_1();

            state._fsp--;


            }

             after(grammarAccess.getFullExpressionAccess().getEntriesAssignment_1_1()); 

            }

            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4219:1: ( ( rule__FullExpression__EntriesAssignment_1_1 )* )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4220:1: ( rule__FullExpression__EntriesAssignment_1_1 )*
            {
             before(grammarAccess.getFullExpressionAccess().getEntriesAssignment_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4221:1: ( rule__FullExpression__EntriesAssignment_1_1 )*
            loop44:
            do {
                int alt44=2;
                int LA44_0 = input.LA(1);

                if ( (LA44_0==KEYWORD_24||LA44_0==KEYWORD_21) ) {
                    alt44=1;
                }


                switch (alt44) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4221:2: rule__FullExpression__EntriesAssignment_1_1
            	    {
            	    pushFollow(FOLLOW_rule__FullExpression__EntriesAssignment_1_1_in_rule__FullExpression__Group_1__1__Impl8743);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4236:1: rule__ExpressionFragmentSecond__Group__0 : rule__ExpressionFragmentSecond__Group__0__Impl rule__ExpressionFragmentSecond__Group__1 ;
    public final void rule__ExpressionFragmentSecond__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4240:1: ( rule__ExpressionFragmentSecond__Group__0__Impl rule__ExpressionFragmentSecond__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4241:2: rule__ExpressionFragmentSecond__Group__0__Impl rule__ExpressionFragmentSecond__Group__1
            {
            pushFollow(FOLLOW_rule__ExpressionFragmentSecond__Group__0__Impl_in_rule__ExpressionFragmentSecond__Group__08780);
            rule__ExpressionFragmentSecond__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ExpressionFragmentSecond__Group__1_in_rule__ExpressionFragmentSecond__Group__08783);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4248:1: rule__ExpressionFragmentSecond__Group__0__Impl : ( ( rule__ExpressionFragmentSecond__CAssignment_0 ) ) ;
    public final void rule__ExpressionFragmentSecond__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4252:1: ( ( ( rule__ExpressionFragmentSecond__CAssignment_0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4253:1: ( ( rule__ExpressionFragmentSecond__CAssignment_0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4253:1: ( ( rule__ExpressionFragmentSecond__CAssignment_0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4254:1: ( rule__ExpressionFragmentSecond__CAssignment_0 )
            {
             before(grammarAccess.getExpressionFragmentSecondAccess().getCAssignment_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4255:1: ( rule__ExpressionFragmentSecond__CAssignment_0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4255:2: rule__ExpressionFragmentSecond__CAssignment_0
            {
            pushFollow(FOLLOW_rule__ExpressionFragmentSecond__CAssignment_0_in_rule__ExpressionFragmentSecond__Group__0__Impl8810);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4265:1: rule__ExpressionFragmentSecond__Group__1 : rule__ExpressionFragmentSecond__Group__1__Impl ;
    public final void rule__ExpressionFragmentSecond__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4269:1: ( rule__ExpressionFragmentSecond__Group__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4270:2: rule__ExpressionFragmentSecond__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__ExpressionFragmentSecond__Group__1__Impl_in_rule__ExpressionFragmentSecond__Group__18840);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4276:1: rule__ExpressionFragmentSecond__Group__1__Impl : ( ( rule__ExpressionFragmentSecond__EfragAssignment_1 ) ) ;
    public final void rule__ExpressionFragmentSecond__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4280:1: ( ( ( rule__ExpressionFragmentSecond__EfragAssignment_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4281:1: ( ( rule__ExpressionFragmentSecond__EfragAssignment_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4281:1: ( ( rule__ExpressionFragmentSecond__EfragAssignment_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4282:1: ( rule__ExpressionFragmentSecond__EfragAssignment_1 )
            {
             before(grammarAccess.getExpressionFragmentSecondAccess().getEfragAssignment_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4283:1: ( rule__ExpressionFragmentSecond__EfragAssignment_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4283:2: rule__ExpressionFragmentSecond__EfragAssignment_1
            {
            pushFollow(FOLLOW_rule__ExpressionFragmentSecond__EfragAssignment_1_in_rule__ExpressionFragmentSecond__Group__1__Impl8867);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4297:1: rule__ExpressionGroup__Group__0 : rule__ExpressionGroup__Group__0__Impl rule__ExpressionGroup__Group__1 ;
    public final void rule__ExpressionGroup__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4301:1: ( rule__ExpressionGroup__Group__0__Impl rule__ExpressionGroup__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4302:2: rule__ExpressionGroup__Group__0__Impl rule__ExpressionGroup__Group__1
            {
            pushFollow(FOLLOW_rule__ExpressionGroup__Group__0__Impl_in_rule__ExpressionGroup__Group__08901);
            rule__ExpressionGroup__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ExpressionGroup__Group__1_in_rule__ExpressionGroup__Group__08904);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4309:1: rule__ExpressionGroup__Group__0__Impl : ( () ) ;
    public final void rule__ExpressionGroup__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4313:1: ( ( () ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4314:1: ( () )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4314:1: ( () )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4315:1: ()
            {
             before(grammarAccess.getExpressionGroupAccess().getExprGroupAction_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4316:1: ()
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4318:1: 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4328:1: rule__ExpressionGroup__Group__1 : rule__ExpressionGroup__Group__1__Impl rule__ExpressionGroup__Group__2 ;
    public final void rule__ExpressionGroup__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4332:1: ( rule__ExpressionGroup__Group__1__Impl rule__ExpressionGroup__Group__2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4333:2: rule__ExpressionGroup__Group__1__Impl rule__ExpressionGroup__Group__2
            {
            pushFollow(FOLLOW_rule__ExpressionGroup__Group__1__Impl_in_rule__ExpressionGroup__Group__18962);
            rule__ExpressionGroup__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ExpressionGroup__Group__2_in_rule__ExpressionGroup__Group__18965);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4340:1: rule__ExpressionGroup__Group__1__Impl : ( KEYWORD_1 ) ;
    public final void rule__ExpressionGroup__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4344:1: ( ( KEYWORD_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4345:1: ( KEYWORD_1 )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4345:1: ( KEYWORD_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4346:1: KEYWORD_1
            {
             before(grammarAccess.getExpressionGroupAccess().getLeftParenthesisKeyword_1()); 
            match(input,KEYWORD_1,FOLLOW_KEYWORD_1_in_rule__ExpressionGroup__Group__1__Impl8993); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4359:1: rule__ExpressionGroup__Group__2 : rule__ExpressionGroup__Group__2__Impl rule__ExpressionGroup__Group__3 ;
    public final void rule__ExpressionGroup__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4363:1: ( rule__ExpressionGroup__Group__2__Impl rule__ExpressionGroup__Group__3 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4364:2: rule__ExpressionGroup__Group__2__Impl rule__ExpressionGroup__Group__3
            {
            pushFollow(FOLLOW_rule__ExpressionGroup__Group__2__Impl_in_rule__ExpressionGroup__Group__29024);
            rule__ExpressionGroup__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ExpressionGroup__Group__3_in_rule__ExpressionGroup__Group__29027);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4371:1: rule__ExpressionGroup__Group__2__Impl : ( ( rule__ExpressionGroup__ExprAssignment_2 ) ) ;
    public final void rule__ExpressionGroup__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4375:1: ( ( ( rule__ExpressionGroup__ExprAssignment_2 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4376:1: ( ( rule__ExpressionGroup__ExprAssignment_2 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4376:1: ( ( rule__ExpressionGroup__ExprAssignment_2 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4377:1: ( rule__ExpressionGroup__ExprAssignment_2 )
            {
             before(grammarAccess.getExpressionGroupAccess().getExprAssignment_2()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4378:1: ( rule__ExpressionGroup__ExprAssignment_2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4378:2: rule__ExpressionGroup__ExprAssignment_2
            {
            pushFollow(FOLLOW_rule__ExpressionGroup__ExprAssignment_2_in_rule__ExpressionGroup__Group__2__Impl9054);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4388:1: rule__ExpressionGroup__Group__3 : rule__ExpressionGroup__Group__3__Impl ;
    public final void rule__ExpressionGroup__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4392:1: ( rule__ExpressionGroup__Group__3__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4393:2: rule__ExpressionGroup__Group__3__Impl
            {
            pushFollow(FOLLOW_rule__ExpressionGroup__Group__3__Impl_in_rule__ExpressionGroup__Group__39084);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4399:1: rule__ExpressionGroup__Group__3__Impl : ( KEYWORD_2 ) ;
    public final void rule__ExpressionGroup__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4403:1: ( ( KEYWORD_2 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4404:1: ( KEYWORD_2 )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4404:1: ( KEYWORD_2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4405:1: KEYWORD_2
            {
             before(grammarAccess.getExpressionGroupAccess().getRightParenthesisKeyword_3()); 
            match(input,KEYWORD_2,FOLLOW_KEYWORD_2_in_rule__ExpressionGroup__Group__3__Impl9112); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4426:1: rule__XExpression__Group__0 : rule__XExpression__Group__0__Impl rule__XExpression__Group__1 ;
    public final void rule__XExpression__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4430:1: ( rule__XExpression__Group__0__Impl rule__XExpression__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4431:2: rule__XExpression__Group__0__Impl rule__XExpression__Group__1
            {
            pushFollow(FOLLOW_rule__XExpression__Group__0__Impl_in_rule__XExpression__Group__09151);
            rule__XExpression__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__XExpression__Group__1_in_rule__XExpression__Group__09154);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4438:1: rule__XExpression__Group__0__Impl : ( KEYWORD_14 ) ;
    public final void rule__XExpression__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4442:1: ( ( KEYWORD_14 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4443:1: ( KEYWORD_14 )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4443:1: ( KEYWORD_14 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4444:1: KEYWORD_14
            {
             before(grammarAccess.getXExpressionAccess().getXKeyword_0()); 
            match(input,KEYWORD_14,FOLLOW_KEYWORD_14_in_rule__XExpression__Group__0__Impl9182); 
             after(grammarAccess.getXExpressionAccess().getXKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XExpression__Group__0__Impl"


    // $ANTLR start "rule__XExpression__Group__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4457:1: rule__XExpression__Group__1 : rule__XExpression__Group__1__Impl rule__XExpression__Group__2 ;
    public final void rule__XExpression__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4461:1: ( rule__XExpression__Group__1__Impl rule__XExpression__Group__2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4462:2: rule__XExpression__Group__1__Impl rule__XExpression__Group__2
            {
            pushFollow(FOLLOW_rule__XExpression__Group__1__Impl_in_rule__XExpression__Group__19213);
            rule__XExpression__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__XExpression__Group__2_in_rule__XExpression__Group__19216);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4469:1: rule__XExpression__Group__1__Impl : ( () ) ;
    public final void rule__XExpression__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4473:1: ( ( () ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4474:1: ( () )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4474:1: ( () )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4475:1: ()
            {
             before(grammarAccess.getXExpressionAccess().getXExprAction_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4476:1: ()
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4478:1: 
            {
            }

             after(grammarAccess.getXExpressionAccess().getXExprAction_1()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XExpression__Group__1__Impl"


    // $ANTLR start "rule__XExpression__Group__2"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4488:1: rule__XExpression__Group__2 : rule__XExpression__Group__2__Impl rule__XExpression__Group__3 ;
    public final void rule__XExpression__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4492:1: ( rule__XExpression__Group__2__Impl rule__XExpression__Group__3 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4493:2: rule__XExpression__Group__2__Impl rule__XExpression__Group__3
            {
            pushFollow(FOLLOW_rule__XExpression__Group__2__Impl_in_rule__XExpression__Group__29274);
            rule__XExpression__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__XExpression__Group__3_in_rule__XExpression__Group__29277);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4500:1: rule__XExpression__Group__2__Impl : ( KEYWORD_11 ) ;
    public final void rule__XExpression__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4504:1: ( ( KEYWORD_11 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4505:1: ( KEYWORD_11 )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4505:1: ( KEYWORD_11 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4506:1: KEYWORD_11
            {
             before(grammarAccess.getXExpressionAccess().getLeftCurlyBracketKeyword_2()); 
            match(input,KEYWORD_11,FOLLOW_KEYWORD_11_in_rule__XExpression__Group__2__Impl9305); 
             after(grammarAccess.getXExpressionAccess().getLeftCurlyBracketKeyword_2()); 

            }


            }

        }
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4519:1: rule__XExpression__Group__3 : rule__XExpression__Group__3__Impl rule__XExpression__Group__4 ;
    public final void rule__XExpression__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4523:1: ( rule__XExpression__Group__3__Impl rule__XExpression__Group__4 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4524:2: rule__XExpression__Group__3__Impl rule__XExpression__Group__4
            {
            pushFollow(FOLLOW_rule__XExpression__Group__3__Impl_in_rule__XExpression__Group__39336);
            rule__XExpression__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__XExpression__Group__4_in_rule__XExpression__Group__39339);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4531:1: rule__XExpression__Group__3__Impl : ( ( rule__XExpression__XfAssignment_3 ) ) ;
    public final void rule__XExpression__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4535:1: ( ( ( rule__XExpression__XfAssignment_3 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4536:1: ( ( rule__XExpression__XfAssignment_3 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4536:1: ( ( rule__XExpression__XfAssignment_3 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4537:1: ( rule__XExpression__XfAssignment_3 )
            {
             before(grammarAccess.getXExpressionAccess().getXfAssignment_3()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4538:1: ( rule__XExpression__XfAssignment_3 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4538:2: rule__XExpression__XfAssignment_3
            {
            pushFollow(FOLLOW_rule__XExpression__XfAssignment_3_in_rule__XExpression__Group__3__Impl9366);
            rule__XExpression__XfAssignment_3();

            state._fsp--;


            }

             after(grammarAccess.getXExpressionAccess().getXfAssignment_3()); 

            }


            }

        }
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4548:1: rule__XExpression__Group__4 : rule__XExpression__Group__4__Impl rule__XExpression__Group__5 ;
    public final void rule__XExpression__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4552:1: ( rule__XExpression__Group__4__Impl rule__XExpression__Group__5 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4553:2: rule__XExpression__Group__4__Impl rule__XExpression__Group__5
            {
            pushFollow(FOLLOW_rule__XExpression__Group__4__Impl_in_rule__XExpression__Group__49396);
            rule__XExpression__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__XExpression__Group__5_in_rule__XExpression__Group__49399);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4560:1: rule__XExpression__Group__4__Impl : ( KEYWORD_4 ) ;
    public final void rule__XExpression__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4564:1: ( ( KEYWORD_4 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4565:1: ( KEYWORD_4 )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4565:1: ( KEYWORD_4 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4566:1: KEYWORD_4
            {
             before(grammarAccess.getXExpressionAccess().getCommaKeyword_4()); 
            match(input,KEYWORD_4,FOLLOW_KEYWORD_4_in_rule__XExpression__Group__4__Impl9427); 
             after(grammarAccess.getXExpressionAccess().getCommaKeyword_4()); 

            }


            }

        }
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4579:1: rule__XExpression__Group__5 : rule__XExpression__Group__5__Impl rule__XExpression__Group__6 ;
    public final void rule__XExpression__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4583:1: ( rule__XExpression__Group__5__Impl rule__XExpression__Group__6 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4584:2: rule__XExpression__Group__5__Impl rule__XExpression__Group__6
            {
            pushFollow(FOLLOW_rule__XExpression__Group__5__Impl_in_rule__XExpression__Group__59458);
            rule__XExpression__Group__5__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__XExpression__Group__6_in_rule__XExpression__Group__59461);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4591:1: rule__XExpression__Group__5__Impl : ( ( rule__XExpression__ColAssignment_5 ) ) ;
    public final void rule__XExpression__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4595:1: ( ( ( rule__XExpression__ColAssignment_5 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4596:1: ( ( rule__XExpression__ColAssignment_5 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4596:1: ( ( rule__XExpression__ColAssignment_5 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4597:1: ( rule__XExpression__ColAssignment_5 )
            {
             before(grammarAccess.getXExpressionAccess().getColAssignment_5()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4598:1: ( rule__XExpression__ColAssignment_5 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4598:2: rule__XExpression__ColAssignment_5
            {
            pushFollow(FOLLOW_rule__XExpression__ColAssignment_5_in_rule__XExpression__Group__5__Impl9488);
            rule__XExpression__ColAssignment_5();

            state._fsp--;


            }

             after(grammarAccess.getXExpressionAccess().getColAssignment_5()); 

            }


            }

        }
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4608:1: rule__XExpression__Group__6 : rule__XExpression__Group__6__Impl rule__XExpression__Group__7 ;
    public final void rule__XExpression__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4612:1: ( rule__XExpression__Group__6__Impl rule__XExpression__Group__7 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4613:2: rule__XExpression__Group__6__Impl rule__XExpression__Group__7
            {
            pushFollow(FOLLOW_rule__XExpression__Group__6__Impl_in_rule__XExpression__Group__69518);
            rule__XExpression__Group__6__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__XExpression__Group__7_in_rule__XExpression__Group__69521);
            rule__XExpression__Group__7();

            state._fsp--;


            }

        }
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4620:1: rule__XExpression__Group__6__Impl : ( ( rule__XExpression__Group_6__0 )? ) ;
    public final void rule__XExpression__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4624:1: ( ( ( rule__XExpression__Group_6__0 )? ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4625:1: ( ( rule__XExpression__Group_6__0 )? )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4625:1: ( ( rule__XExpression__Group_6__0 )? )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4626:1: ( rule__XExpression__Group_6__0 )?
            {
             before(grammarAccess.getXExpressionAccess().getGroup_6()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4627:1: ( rule__XExpression__Group_6__0 )?
            int alt45=2;
            int LA45_0 = input.LA(1);

            if ( (LA45_0==KEYWORD_4) ) {
                alt45=1;
            }
            switch (alt45) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4627:2: rule__XExpression__Group_6__0
                    {
                    pushFollow(FOLLOW_rule__XExpression__Group_6__0_in_rule__XExpression__Group__6__Impl9548);
                    rule__XExpression__Group_6__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getXExpressionAccess().getGroup_6()); 

            }


            }

        }
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


    // $ANTLR start "rule__XExpression__Group__7"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4637:1: rule__XExpression__Group__7 : rule__XExpression__Group__7__Impl ;
    public final void rule__XExpression__Group__7() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4641:1: ( rule__XExpression__Group__7__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4642:2: rule__XExpression__Group__7__Impl
            {
            pushFollow(FOLLOW_rule__XExpression__Group__7__Impl_in_rule__XExpression__Group__79579);
            rule__XExpression__Group__7__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XExpression__Group__7"


    // $ANTLR start "rule__XExpression__Group__7__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4648:1: rule__XExpression__Group__7__Impl : ( KEYWORD_12 ) ;
    public final void rule__XExpression__Group__7__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4652:1: ( ( KEYWORD_12 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4653:1: ( KEYWORD_12 )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4653:1: ( KEYWORD_12 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4654:1: KEYWORD_12
            {
             before(grammarAccess.getXExpressionAccess().getRightCurlyBracketKeyword_7()); 
            match(input,KEYWORD_12,FOLLOW_KEYWORD_12_in_rule__XExpression__Group__7__Impl9607); 
             after(grammarAccess.getXExpressionAccess().getRightCurlyBracketKeyword_7()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XExpression__Group__7__Impl"


    // $ANTLR start "rule__XExpression__Group_6__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4683:1: rule__XExpression__Group_6__0 : rule__XExpression__Group_6__0__Impl rule__XExpression__Group_6__1 ;
    public final void rule__XExpression__Group_6__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4687:1: ( rule__XExpression__Group_6__0__Impl rule__XExpression__Group_6__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4688:2: rule__XExpression__Group_6__0__Impl rule__XExpression__Group_6__1
            {
            pushFollow(FOLLOW_rule__XExpression__Group_6__0__Impl_in_rule__XExpression__Group_6__09654);
            rule__XExpression__Group_6__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__XExpression__Group_6__1_in_rule__XExpression__Group_6__09657);
            rule__XExpression__Group_6__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XExpression__Group_6__0"


    // $ANTLR start "rule__XExpression__Group_6__0__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4695:1: rule__XExpression__Group_6__0__Impl : ( KEYWORD_4 ) ;
    public final void rule__XExpression__Group_6__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4699:1: ( ( KEYWORD_4 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4700:1: ( KEYWORD_4 )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4700:1: ( KEYWORD_4 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4701:1: KEYWORD_4
            {
             before(grammarAccess.getXExpressionAccess().getCommaKeyword_6_0()); 
            match(input,KEYWORD_4,FOLLOW_KEYWORD_4_in_rule__XExpression__Group_6__0__Impl9685); 
             after(grammarAccess.getXExpressionAccess().getCommaKeyword_6_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XExpression__Group_6__0__Impl"


    // $ANTLR start "rule__XExpression__Group_6__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4714:1: rule__XExpression__Group_6__1 : rule__XExpression__Group_6__1__Impl ;
    public final void rule__XExpression__Group_6__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4718:1: ( rule__XExpression__Group_6__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4719:2: rule__XExpression__Group_6__1__Impl
            {
            pushFollow(FOLLOW_rule__XExpression__Group_6__1__Impl_in_rule__XExpression__Group_6__19716);
            rule__XExpression__Group_6__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XExpression__Group_6__1"


    // $ANTLR start "rule__XExpression__Group_6__1__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4725:1: rule__XExpression__Group_6__1__Impl : ( ( rule__XExpression__PrmAssignment_6_1 ) ) ;
    public final void rule__XExpression__Group_6__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4729:1: ( ( ( rule__XExpression__PrmAssignment_6_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4730:1: ( ( rule__XExpression__PrmAssignment_6_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4730:1: ( ( rule__XExpression__PrmAssignment_6_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4731:1: ( rule__XExpression__PrmAssignment_6_1 )
            {
             before(grammarAccess.getXExpressionAccess().getPrmAssignment_6_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4732:1: ( rule__XExpression__PrmAssignment_6_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4732:2: rule__XExpression__PrmAssignment_6_1
            {
            pushFollow(FOLLOW_rule__XExpression__PrmAssignment_6_1_in_rule__XExpression__Group_6__1__Impl9743);
            rule__XExpression__PrmAssignment_6_1();

            state._fsp--;


            }

             after(grammarAccess.getXExpressionAccess().getPrmAssignment_6_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XExpression__Group_6__1__Impl"


    // $ANTLR start "rule__XExpressionParams__Group__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4746:1: rule__XExpressionParams__Group__0 : rule__XExpressionParams__Group__0__Impl rule__XExpressionParams__Group__1 ;
    public final void rule__XExpressionParams__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4750:1: ( rule__XExpressionParams__Group__0__Impl rule__XExpressionParams__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4751:2: rule__XExpressionParams__Group__0__Impl rule__XExpressionParams__Group__1
            {
            pushFollow(FOLLOW_rule__XExpressionParams__Group__0__Impl_in_rule__XExpressionParams__Group__09777);
            rule__XExpressionParams__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__XExpressionParams__Group__1_in_rule__XExpressionParams__Group__09780);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4758:1: rule__XExpressionParams__Group__0__Impl : ( ruleJRParameter ) ;
    public final void rule__XExpressionParams__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4762:1: ( ( ruleJRParameter ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4763:1: ( ruleJRParameter )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4763:1: ( ruleJRParameter )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4764:1: ruleJRParameter
            {
             before(grammarAccess.getXExpressionParamsAccess().getJRParameterParserRuleCall_0()); 
            pushFollow(FOLLOW_ruleJRParameter_in_rule__XExpressionParams__Group__0__Impl9807);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4775:1: rule__XExpressionParams__Group__1 : rule__XExpressionParams__Group__1__Impl ;
    public final void rule__XExpressionParams__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4779:1: ( rule__XExpressionParams__Group__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4780:2: rule__XExpressionParams__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__XExpressionParams__Group__1__Impl_in_rule__XExpressionParams__Group__19836);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4786:1: rule__XExpressionParams__Group__1__Impl : ( ( rule__XExpressionParams__Group_1__0 )? ) ;
    public final void rule__XExpressionParams__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4790:1: ( ( ( rule__XExpressionParams__Group_1__0 )? ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4791:1: ( ( rule__XExpressionParams__Group_1__0 )? )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4791:1: ( ( rule__XExpressionParams__Group_1__0 )? )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4792:1: ( rule__XExpressionParams__Group_1__0 )?
            {
             before(grammarAccess.getXExpressionParamsAccess().getGroup_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4793:1: ( rule__XExpressionParams__Group_1__0 )?
            int alt46=2;
            int LA46_0 = input.LA(1);

            if ( (LA46_0==KEYWORD_4) ) {
                alt46=1;
            }
            switch (alt46) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4793:2: rule__XExpressionParams__Group_1__0
                    {
                    pushFollow(FOLLOW_rule__XExpressionParams__Group_1__0_in_rule__XExpressionParams__Group__1__Impl9863);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4807:1: rule__XExpressionParams__Group_1__0 : rule__XExpressionParams__Group_1__0__Impl rule__XExpressionParams__Group_1__1 ;
    public final void rule__XExpressionParams__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4811:1: ( rule__XExpressionParams__Group_1__0__Impl rule__XExpressionParams__Group_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4812:2: rule__XExpressionParams__Group_1__0__Impl rule__XExpressionParams__Group_1__1
            {
            pushFollow(FOLLOW_rule__XExpressionParams__Group_1__0__Impl_in_rule__XExpressionParams__Group_1__09898);
            rule__XExpressionParams__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__XExpressionParams__Group_1__1_in_rule__XExpressionParams__Group_1__09901);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4819:1: rule__XExpressionParams__Group_1__0__Impl : ( () ) ;
    public final void rule__XExpressionParams__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4823:1: ( ( () ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4824:1: ( () )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4824:1: ( () )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4825:1: ()
            {
             before(grammarAccess.getXExpressionParamsAccess().getPrmsEntriesAction_1_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4826:1: ()
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4828:1: 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4838:1: rule__XExpressionParams__Group_1__1 : rule__XExpressionParams__Group_1__1__Impl ;
    public final void rule__XExpressionParams__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4842:1: ( rule__XExpressionParams__Group_1__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4843:2: rule__XExpressionParams__Group_1__1__Impl
            {
            pushFollow(FOLLOW_rule__XExpressionParams__Group_1__1__Impl_in_rule__XExpressionParams__Group_1__19959);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4849:1: rule__XExpressionParams__Group_1__1__Impl : ( ( ( rule__XExpressionParams__Group_1_1__0 ) ) ( ( rule__XExpressionParams__Group_1_1__0 )* ) ) ;
    public final void rule__XExpressionParams__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4853:1: ( ( ( ( rule__XExpressionParams__Group_1_1__0 ) ) ( ( rule__XExpressionParams__Group_1_1__0 )* ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4854:1: ( ( ( rule__XExpressionParams__Group_1_1__0 ) ) ( ( rule__XExpressionParams__Group_1_1__0 )* ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4854:1: ( ( ( rule__XExpressionParams__Group_1_1__0 ) ) ( ( rule__XExpressionParams__Group_1_1__0 )* ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4855:1: ( ( rule__XExpressionParams__Group_1_1__0 ) ) ( ( rule__XExpressionParams__Group_1_1__0 )* )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4855:1: ( ( rule__XExpressionParams__Group_1_1__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4856:1: ( rule__XExpressionParams__Group_1_1__0 )
            {
             before(grammarAccess.getXExpressionParamsAccess().getGroup_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4857:1: ( rule__XExpressionParams__Group_1_1__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4857:2: rule__XExpressionParams__Group_1_1__0
            {
            pushFollow(FOLLOW_rule__XExpressionParams__Group_1_1__0_in_rule__XExpressionParams__Group_1__1__Impl9988);
            rule__XExpressionParams__Group_1_1__0();

            state._fsp--;


            }

             after(grammarAccess.getXExpressionParamsAccess().getGroup_1_1()); 

            }

            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4860:1: ( ( rule__XExpressionParams__Group_1_1__0 )* )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4861:1: ( rule__XExpressionParams__Group_1_1__0 )*
            {
             before(grammarAccess.getXExpressionParamsAccess().getGroup_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4862:1: ( rule__XExpressionParams__Group_1_1__0 )*
            loop47:
            do {
                int alt47=2;
                int LA47_0 = input.LA(1);

                if ( (LA47_0==KEYWORD_4) ) {
                    alt47=1;
                }


                switch (alt47) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4862:2: rule__XExpressionParams__Group_1_1__0
            	    {
            	    pushFollow(FOLLOW_rule__XExpressionParams__Group_1_1__0_in_rule__XExpressionParams__Group_1__1__Impl10000);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4877:1: rule__XExpressionParams__Group_1_1__0 : rule__XExpressionParams__Group_1_1__0__Impl rule__XExpressionParams__Group_1_1__1 ;
    public final void rule__XExpressionParams__Group_1_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4881:1: ( rule__XExpressionParams__Group_1_1__0__Impl rule__XExpressionParams__Group_1_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4882:2: rule__XExpressionParams__Group_1_1__0__Impl rule__XExpressionParams__Group_1_1__1
            {
            pushFollow(FOLLOW_rule__XExpressionParams__Group_1_1__0__Impl_in_rule__XExpressionParams__Group_1_1__010037);
            rule__XExpressionParams__Group_1_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__XExpressionParams__Group_1_1__1_in_rule__XExpressionParams__Group_1_1__010040);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4889:1: rule__XExpressionParams__Group_1_1__0__Impl : ( KEYWORD_4 ) ;
    public final void rule__XExpressionParams__Group_1_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4893:1: ( ( KEYWORD_4 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4894:1: ( KEYWORD_4 )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4894:1: ( KEYWORD_4 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4895:1: KEYWORD_4
            {
             before(grammarAccess.getXExpressionParamsAccess().getCommaKeyword_1_1_0()); 
            match(input,KEYWORD_4,FOLLOW_KEYWORD_4_in_rule__XExpressionParams__Group_1_1__0__Impl10068); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4908:1: rule__XExpressionParams__Group_1_1__1 : rule__XExpressionParams__Group_1_1__1__Impl ;
    public final void rule__XExpressionParams__Group_1_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4912:1: ( rule__XExpressionParams__Group_1_1__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4913:2: rule__XExpressionParams__Group_1_1__1__Impl
            {
            pushFollow(FOLLOW_rule__XExpressionParams__Group_1_1__1__Impl_in_rule__XExpressionParams__Group_1_1__110099);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4919:1: rule__XExpressionParams__Group_1_1__1__Impl : ( ( rule__XExpressionParams__EntriesAssignment_1_1_1 ) ) ;
    public final void rule__XExpressionParams__Group_1_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4923:1: ( ( ( rule__XExpressionParams__EntriesAssignment_1_1_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4924:1: ( ( rule__XExpressionParams__EntriesAssignment_1_1_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4924:1: ( ( rule__XExpressionParams__EntriesAssignment_1_1_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4925:1: ( rule__XExpressionParams__EntriesAssignment_1_1_1 )
            {
             before(grammarAccess.getXExpressionParamsAccess().getEntriesAssignment_1_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4926:1: ( rule__XExpressionParams__EntriesAssignment_1_1_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4926:2: rule__XExpressionParams__EntriesAssignment_1_1_1
            {
            pushFollow(FOLLOW_rule__XExpressionParams__EntriesAssignment_1_1_1_in_rule__XExpressionParams__Group_1_1__1__Impl10126);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4940:1: rule__Expression__Group__0 : rule__Expression__Group__0__Impl rule__Expression__Group__1 ;
    public final void rule__Expression__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4944:1: ( rule__Expression__Group__0__Impl rule__Expression__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4945:2: rule__Expression__Group__0__Impl rule__Expression__Group__1
            {
            pushFollow(FOLLOW_rule__Expression__Group__0__Impl_in_rule__Expression__Group__010160);
            rule__Expression__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Expression__Group__1_in_rule__Expression__Group__010163);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4952:1: rule__Expression__Group__0__Impl : ( ( rule__Expression__Op1Assignment_0 ) ) ;
    public final void rule__Expression__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4956:1: ( ( ( rule__Expression__Op1Assignment_0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4957:1: ( ( rule__Expression__Op1Assignment_0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4957:1: ( ( rule__Expression__Op1Assignment_0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4958:1: ( rule__Expression__Op1Assignment_0 )
            {
             before(grammarAccess.getExpressionAccess().getOp1Assignment_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4959:1: ( rule__Expression__Op1Assignment_0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4959:2: rule__Expression__Op1Assignment_0
            {
            pushFollow(FOLLOW_rule__Expression__Op1Assignment_0_in_rule__Expression__Group__0__Impl10190);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4969:1: rule__Expression__Group__1 : rule__Expression__Group__1__Impl ;
    public final void rule__Expression__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4973:1: ( rule__Expression__Group__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4974:2: rule__Expression__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__Expression__Group__1__Impl_in_rule__Expression__Group__110220);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4980:1: rule__Expression__Group__1__Impl : ( ( rule__Expression__Alternatives_1 ) ) ;
    public final void rule__Expression__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4984:1: ( ( ( rule__Expression__Alternatives_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4985:1: ( ( rule__Expression__Alternatives_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4985:1: ( ( rule__Expression__Alternatives_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4986:1: ( rule__Expression__Alternatives_1 )
            {
             before(grammarAccess.getExpressionAccess().getAlternatives_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4987:1: ( rule__Expression__Alternatives_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:4987:2: rule__Expression__Alternatives_1
            {
            pushFollow(FOLLOW_rule__Expression__Alternatives_1_in_rule__Expression__Group__1__Impl10247);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5001:1: rule__Comparison__Group__0 : rule__Comparison__Group__0__Impl rule__Comparison__Group__1 ;
    public final void rule__Comparison__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5005:1: ( rule__Comparison__Group__0__Impl rule__Comparison__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5006:2: rule__Comparison__Group__0__Impl rule__Comparison__Group__1
            {
            pushFollow(FOLLOW_rule__Comparison__Group__0__Impl_in_rule__Comparison__Group__010281);
            rule__Comparison__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Comparison__Group__1_in_rule__Comparison__Group__010284);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5013:1: rule__Comparison__Group__0__Impl : ( ( rule__Comparison__OperatorAssignment_0 ) ) ;
    public final void rule__Comparison__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5017:1: ( ( ( rule__Comparison__OperatorAssignment_0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5018:1: ( ( rule__Comparison__OperatorAssignment_0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5018:1: ( ( rule__Comparison__OperatorAssignment_0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5019:1: ( rule__Comparison__OperatorAssignment_0 )
            {
             before(grammarAccess.getComparisonAccess().getOperatorAssignment_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5020:1: ( rule__Comparison__OperatorAssignment_0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5020:2: rule__Comparison__OperatorAssignment_0
            {
            pushFollow(FOLLOW_rule__Comparison__OperatorAssignment_0_in_rule__Comparison__Group__0__Impl10311);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5030:1: rule__Comparison__Group__1 : rule__Comparison__Group__1__Impl ;
    public final void rule__Comparison__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5034:1: ( rule__Comparison__Group__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5035:2: rule__Comparison__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__Comparison__Group__1__Impl_in_rule__Comparison__Group__110341);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5041:1: rule__Comparison__Group__1__Impl : ( ( rule__Comparison__Op2Assignment_1 ) ) ;
    public final void rule__Comparison__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5045:1: ( ( ( rule__Comparison__Op2Assignment_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5046:1: ( ( rule__Comparison__Op2Assignment_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5046:1: ( ( rule__Comparison__Op2Assignment_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5047:1: ( rule__Comparison__Op2Assignment_1 )
            {
             before(grammarAccess.getComparisonAccess().getOp2Assignment_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5048:1: ( rule__Comparison__Op2Assignment_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5048:2: rule__Comparison__Op2Assignment_1
            {
            pushFollow(FOLLOW_rule__Comparison__Op2Assignment_1_in_rule__Comparison__Group__1__Impl10368);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5062:1: rule__Like__Group__0 : rule__Like__Group__0__Impl rule__Like__Group__1 ;
    public final void rule__Like__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5066:1: ( rule__Like__Group__0__Impl rule__Like__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5067:2: rule__Like__Group__0__Impl rule__Like__Group__1
            {
            pushFollow(FOLLOW_rule__Like__Group__0__Impl_in_rule__Like__Group__010402);
            rule__Like__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Like__Group__1_in_rule__Like__Group__010405);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5074:1: rule__Like__Group__0__Impl : ( ( rule__Like__OpLikeAssignment_0 ) ) ;
    public final void rule__Like__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5078:1: ( ( ( rule__Like__OpLikeAssignment_0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5079:1: ( ( rule__Like__OpLikeAssignment_0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5079:1: ( ( rule__Like__OpLikeAssignment_0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5080:1: ( rule__Like__OpLikeAssignment_0 )
            {
             before(grammarAccess.getLikeAccess().getOpLikeAssignment_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5081:1: ( rule__Like__OpLikeAssignment_0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5081:2: rule__Like__OpLikeAssignment_0
            {
            pushFollow(FOLLOW_rule__Like__OpLikeAssignment_0_in_rule__Like__Group__0__Impl10432);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5091:1: rule__Like__Group__1 : rule__Like__Group__1__Impl ;
    public final void rule__Like__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5095:1: ( rule__Like__Group__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5096:2: rule__Like__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__Like__Group__1__Impl_in_rule__Like__Group__110462);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5102:1: rule__Like__Group__1__Impl : ( ( rule__Like__Op2Assignment_1 ) ) ;
    public final void rule__Like__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5106:1: ( ( ( rule__Like__Op2Assignment_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5107:1: ( ( rule__Like__Op2Assignment_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5107:1: ( ( rule__Like__Op2Assignment_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5108:1: ( rule__Like__Op2Assignment_1 )
            {
             before(grammarAccess.getLikeAccess().getOp2Assignment_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5109:1: ( rule__Like__Op2Assignment_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5109:2: rule__Like__Op2Assignment_1
            {
            pushFollow(FOLLOW_rule__Like__Op2Assignment_1_in_rule__Like__Group__1__Impl10489);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5123:1: rule__Between__Group__0 : rule__Between__Group__0__Impl rule__Between__Group__1 ;
    public final void rule__Between__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5127:1: ( rule__Between__Group__0__Impl rule__Between__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5128:2: rule__Between__Group__0__Impl rule__Between__Group__1
            {
            pushFollow(FOLLOW_rule__Between__Group__0__Impl_in_rule__Between__Group__010523);
            rule__Between__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Between__Group__1_in_rule__Between__Group__010526);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5135:1: rule__Between__Group__0__Impl : ( ( rule__Between__OpBetweenAssignment_0 ) ) ;
    public final void rule__Between__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5139:1: ( ( ( rule__Between__OpBetweenAssignment_0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5140:1: ( ( rule__Between__OpBetweenAssignment_0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5140:1: ( ( rule__Between__OpBetweenAssignment_0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5141:1: ( rule__Between__OpBetweenAssignment_0 )
            {
             before(grammarAccess.getBetweenAccess().getOpBetweenAssignment_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5142:1: ( rule__Between__OpBetweenAssignment_0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5142:2: rule__Between__OpBetweenAssignment_0
            {
            pushFollow(FOLLOW_rule__Between__OpBetweenAssignment_0_in_rule__Between__Group__0__Impl10553);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5152:1: rule__Between__Group__1 : rule__Between__Group__1__Impl rule__Between__Group__2 ;
    public final void rule__Between__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5156:1: ( rule__Between__Group__1__Impl rule__Between__Group__2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5157:2: rule__Between__Group__1__Impl rule__Between__Group__2
            {
            pushFollow(FOLLOW_rule__Between__Group__1__Impl_in_rule__Between__Group__110583);
            rule__Between__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Between__Group__2_in_rule__Between__Group__110586);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5164:1: rule__Between__Group__1__Impl : ( ( rule__Between__Op2Assignment_1 ) ) ;
    public final void rule__Between__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5168:1: ( ( ( rule__Between__Op2Assignment_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5169:1: ( ( rule__Between__Op2Assignment_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5169:1: ( ( rule__Between__Op2Assignment_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5170:1: ( rule__Between__Op2Assignment_1 )
            {
             before(grammarAccess.getBetweenAccess().getOp2Assignment_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5171:1: ( rule__Between__Op2Assignment_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5171:2: rule__Between__Op2Assignment_1
            {
            pushFollow(FOLLOW_rule__Between__Op2Assignment_1_in_rule__Between__Group__1__Impl10613);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5181:1: rule__Between__Group__2 : rule__Between__Group__2__Impl rule__Between__Group__3 ;
    public final void rule__Between__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5185:1: ( rule__Between__Group__2__Impl rule__Between__Group__3 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5186:2: rule__Between__Group__2__Impl rule__Between__Group__3
            {
            pushFollow(FOLLOW_rule__Between__Group__2__Impl_in_rule__Between__Group__210643);
            rule__Between__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Between__Group__3_in_rule__Between__Group__210646);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5193:1: rule__Between__Group__2__Impl : ( KEYWORD_24 ) ;
    public final void rule__Between__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5197:1: ( ( KEYWORD_24 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5198:1: ( KEYWORD_24 )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5198:1: ( KEYWORD_24 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5199:1: KEYWORD_24
            {
             before(grammarAccess.getBetweenAccess().getANDKeyword_2()); 
            match(input,KEYWORD_24,FOLLOW_KEYWORD_24_in_rule__Between__Group__2__Impl10674); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5212:1: rule__Between__Group__3 : rule__Between__Group__3__Impl ;
    public final void rule__Between__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5216:1: ( rule__Between__Group__3__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5217:2: rule__Between__Group__3__Impl
            {
            pushFollow(FOLLOW_rule__Between__Group__3__Impl_in_rule__Between__Group__310705);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5223:1: rule__Between__Group__3__Impl : ( ( rule__Between__Op3Assignment_3 ) ) ;
    public final void rule__Between__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5227:1: ( ( ( rule__Between__Op3Assignment_3 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5228:1: ( ( rule__Between__Op3Assignment_3 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5228:1: ( ( rule__Between__Op3Assignment_3 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5229:1: ( rule__Between__Op3Assignment_3 )
            {
             before(grammarAccess.getBetweenAccess().getOp3Assignment_3()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5230:1: ( rule__Between__Op3Assignment_3 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5230:2: rule__Between__Op3Assignment_3
            {
            pushFollow(FOLLOW_rule__Between__Op3Assignment_3_in_rule__Between__Group__3__Impl10732);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5248:1: rule__InOperator__Group__0 : rule__InOperator__Group__0__Impl rule__InOperator__Group__1 ;
    public final void rule__InOperator__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5252:1: ( rule__InOperator__Group__0__Impl rule__InOperator__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5253:2: rule__InOperator__Group__0__Impl rule__InOperator__Group__1
            {
            pushFollow(FOLLOW_rule__InOperator__Group__0__Impl_in_rule__InOperator__Group__010770);
            rule__InOperator__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__InOperator__Group__1_in_rule__InOperator__Group__010773);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5260:1: rule__InOperator__Group__0__Impl : ( () ) ;
    public final void rule__InOperator__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5264:1: ( ( () ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5265:1: ( () )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5265:1: ( () )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5266:1: ()
            {
             before(grammarAccess.getInOperatorAccess().getInOperAction_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5267:1: ()
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5269:1: 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5279:1: rule__InOperator__Group__1 : rule__InOperator__Group__1__Impl rule__InOperator__Group__2 ;
    public final void rule__InOperator__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5283:1: ( rule__InOperator__Group__1__Impl rule__InOperator__Group__2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5284:2: rule__InOperator__Group__1__Impl rule__InOperator__Group__2
            {
            pushFollow(FOLLOW_rule__InOperator__Group__1__Impl_in_rule__InOperator__Group__110831);
            rule__InOperator__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__InOperator__Group__2_in_rule__InOperator__Group__110834);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5291:1: rule__InOperator__Group__1__Impl : ( ( rule__InOperator__OpAssignment_1 ) ) ;
    public final void rule__InOperator__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5295:1: ( ( ( rule__InOperator__OpAssignment_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5296:1: ( ( rule__InOperator__OpAssignment_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5296:1: ( ( rule__InOperator__OpAssignment_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5297:1: ( rule__InOperator__OpAssignment_1 )
            {
             before(grammarAccess.getInOperatorAccess().getOpAssignment_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5298:1: ( rule__InOperator__OpAssignment_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5298:2: rule__InOperator__OpAssignment_1
            {
            pushFollow(FOLLOW_rule__InOperator__OpAssignment_1_in_rule__InOperator__Group__1__Impl10861);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5308:1: rule__InOperator__Group__2 : rule__InOperator__Group__2__Impl rule__InOperator__Group__3 ;
    public final void rule__InOperator__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5312:1: ( rule__InOperator__Group__2__Impl rule__InOperator__Group__3 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5313:2: rule__InOperator__Group__2__Impl rule__InOperator__Group__3
            {
            pushFollow(FOLLOW_rule__InOperator__Group__2__Impl_in_rule__InOperator__Group__210891);
            rule__InOperator__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__InOperator__Group__3_in_rule__InOperator__Group__210894);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5320:1: rule__InOperator__Group__2__Impl : ( KEYWORD_1 ) ;
    public final void rule__InOperator__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5324:1: ( ( KEYWORD_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5325:1: ( KEYWORD_1 )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5325:1: ( KEYWORD_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5326:1: KEYWORD_1
            {
             before(grammarAccess.getInOperatorAccess().getLeftParenthesisKeyword_2()); 
            match(input,KEYWORD_1,FOLLOW_KEYWORD_1_in_rule__InOperator__Group__2__Impl10922); 
             after(grammarAccess.getInOperatorAccess().getLeftParenthesisKeyword_2()); 

            }


            }

        }
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5339:1: rule__InOperator__Group__3 : rule__InOperator__Group__3__Impl rule__InOperator__Group__4 ;
    public final void rule__InOperator__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5343:1: ( rule__InOperator__Group__3__Impl rule__InOperator__Group__4 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5344:2: rule__InOperator__Group__3__Impl rule__InOperator__Group__4
            {
            pushFollow(FOLLOW_rule__InOperator__Group__3__Impl_in_rule__InOperator__Group__310953);
            rule__InOperator__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__InOperator__Group__4_in_rule__InOperator__Group__310956);
            rule__InOperator__Group__4();

            state._fsp--;


            }

        }
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5351:1: rule__InOperator__Group__3__Impl : ( ( rule__InOperator__Alternatives_3 ) ) ;
    public final void rule__InOperator__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5355:1: ( ( ( rule__InOperator__Alternatives_3 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5356:1: ( ( rule__InOperator__Alternatives_3 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5356:1: ( ( rule__InOperator__Alternatives_3 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5357:1: ( rule__InOperator__Alternatives_3 )
            {
             before(grammarAccess.getInOperatorAccess().getAlternatives_3()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5358:1: ( rule__InOperator__Alternatives_3 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5358:2: rule__InOperator__Alternatives_3
            {
            pushFollow(FOLLOW_rule__InOperator__Alternatives_3_in_rule__InOperator__Group__3__Impl10983);
            rule__InOperator__Alternatives_3();

            state._fsp--;


            }

             after(grammarAccess.getInOperatorAccess().getAlternatives_3()); 

            }


            }

        }
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


    // $ANTLR start "rule__InOperator__Group__4"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5368:1: rule__InOperator__Group__4 : rule__InOperator__Group__4__Impl ;
    public final void rule__InOperator__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5372:1: ( rule__InOperator__Group__4__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5373:2: rule__InOperator__Group__4__Impl
            {
            pushFollow(FOLLOW_rule__InOperator__Group__4__Impl_in_rule__InOperator__Group__411013);
            rule__InOperator__Group__4__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InOperator__Group__4"


    // $ANTLR start "rule__InOperator__Group__4__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5379:1: rule__InOperator__Group__4__Impl : ( KEYWORD_2 ) ;
    public final void rule__InOperator__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5383:1: ( ( KEYWORD_2 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5384:1: ( KEYWORD_2 )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5384:1: ( KEYWORD_2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5385:1: KEYWORD_2
            {
             before(grammarAccess.getInOperatorAccess().getRightParenthesisKeyword_4()); 
            match(input,KEYWORD_2,FOLLOW_KEYWORD_2_in_rule__InOperator__Group__4__Impl11041); 
             after(grammarAccess.getInOperatorAccess().getRightParenthesisKeyword_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InOperator__Group__4__Impl"


    // $ANTLR start "rule__OperandList__Group__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5408:1: rule__OperandList__Group__0 : rule__OperandList__Group__0__Impl rule__OperandList__Group__1 ;
    public final void rule__OperandList__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5412:1: ( rule__OperandList__Group__0__Impl rule__OperandList__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5413:2: rule__OperandList__Group__0__Impl rule__OperandList__Group__1
            {
            pushFollow(FOLLOW_rule__OperandList__Group__0__Impl_in_rule__OperandList__Group__011082);
            rule__OperandList__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__OperandList__Group__1_in_rule__OperandList__Group__011085);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5420:1: rule__OperandList__Group__0__Impl : ( ruleXOperandFragment ) ;
    public final void rule__OperandList__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5424:1: ( ( ruleXOperandFragment ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5425:1: ( ruleXOperandFragment )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5425:1: ( ruleXOperandFragment )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5426:1: ruleXOperandFragment
            {
             before(grammarAccess.getOperandListAccess().getXOperandFragmentParserRuleCall_0()); 
            pushFollow(FOLLOW_ruleXOperandFragment_in_rule__OperandList__Group__0__Impl11112);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5437:1: rule__OperandList__Group__1 : rule__OperandList__Group__1__Impl ;
    public final void rule__OperandList__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5441:1: ( rule__OperandList__Group__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5442:2: rule__OperandList__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__OperandList__Group__1__Impl_in_rule__OperandList__Group__111141);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5448:1: rule__OperandList__Group__1__Impl : ( ( rule__OperandList__Group_1__0 )? ) ;
    public final void rule__OperandList__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5452:1: ( ( ( rule__OperandList__Group_1__0 )? ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5453:1: ( ( rule__OperandList__Group_1__0 )? )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5453:1: ( ( rule__OperandList__Group_1__0 )? )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5454:1: ( rule__OperandList__Group_1__0 )?
            {
             before(grammarAccess.getOperandListAccess().getGroup_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5455:1: ( rule__OperandList__Group_1__0 )?
            int alt48=2;
            int LA48_0 = input.LA(1);

            if ( (LA48_0==KEYWORD_4) ) {
                alt48=1;
            }
            switch (alt48) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5455:2: rule__OperandList__Group_1__0
                    {
                    pushFollow(FOLLOW_rule__OperandList__Group_1__0_in_rule__OperandList__Group__1__Impl11168);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5469:1: rule__OperandList__Group_1__0 : rule__OperandList__Group_1__0__Impl rule__OperandList__Group_1__1 ;
    public final void rule__OperandList__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5473:1: ( rule__OperandList__Group_1__0__Impl rule__OperandList__Group_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5474:2: rule__OperandList__Group_1__0__Impl rule__OperandList__Group_1__1
            {
            pushFollow(FOLLOW_rule__OperandList__Group_1__0__Impl_in_rule__OperandList__Group_1__011203);
            rule__OperandList__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__OperandList__Group_1__1_in_rule__OperandList__Group_1__011206);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5481:1: rule__OperandList__Group_1__0__Impl : ( () ) ;
    public final void rule__OperandList__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5485:1: ( ( () ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5486:1: ( () )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5486:1: ( () )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5487:1: ()
            {
             before(grammarAccess.getOperandListAccess().getOpListEntriesAction_1_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5488:1: ()
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5490:1: 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5500:1: rule__OperandList__Group_1__1 : rule__OperandList__Group_1__1__Impl ;
    public final void rule__OperandList__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5504:1: ( rule__OperandList__Group_1__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5505:2: rule__OperandList__Group_1__1__Impl
            {
            pushFollow(FOLLOW_rule__OperandList__Group_1__1__Impl_in_rule__OperandList__Group_1__111264);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5511:1: rule__OperandList__Group_1__1__Impl : ( ( ( rule__OperandList__Group_1_1__0 ) ) ( ( rule__OperandList__Group_1_1__0 )* ) ) ;
    public final void rule__OperandList__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5515:1: ( ( ( ( rule__OperandList__Group_1_1__0 ) ) ( ( rule__OperandList__Group_1_1__0 )* ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5516:1: ( ( ( rule__OperandList__Group_1_1__0 ) ) ( ( rule__OperandList__Group_1_1__0 )* ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5516:1: ( ( ( rule__OperandList__Group_1_1__0 ) ) ( ( rule__OperandList__Group_1_1__0 )* ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5517:1: ( ( rule__OperandList__Group_1_1__0 ) ) ( ( rule__OperandList__Group_1_1__0 )* )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5517:1: ( ( rule__OperandList__Group_1_1__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5518:1: ( rule__OperandList__Group_1_1__0 )
            {
             before(grammarAccess.getOperandListAccess().getGroup_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5519:1: ( rule__OperandList__Group_1_1__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5519:2: rule__OperandList__Group_1_1__0
            {
            pushFollow(FOLLOW_rule__OperandList__Group_1_1__0_in_rule__OperandList__Group_1__1__Impl11293);
            rule__OperandList__Group_1_1__0();

            state._fsp--;


            }

             after(grammarAccess.getOperandListAccess().getGroup_1_1()); 

            }

            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5522:1: ( ( rule__OperandList__Group_1_1__0 )* )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5523:1: ( rule__OperandList__Group_1_1__0 )*
            {
             before(grammarAccess.getOperandListAccess().getGroup_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5524:1: ( rule__OperandList__Group_1_1__0 )*
            loop49:
            do {
                int alt49=2;
                int LA49_0 = input.LA(1);

                if ( (LA49_0==KEYWORD_4) ) {
                    alt49=1;
                }


                switch (alt49) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5524:2: rule__OperandList__Group_1_1__0
            	    {
            	    pushFollow(FOLLOW_rule__OperandList__Group_1_1__0_in_rule__OperandList__Group_1__1__Impl11305);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5539:1: rule__OperandList__Group_1_1__0 : rule__OperandList__Group_1_1__0__Impl rule__OperandList__Group_1_1__1 ;
    public final void rule__OperandList__Group_1_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5543:1: ( rule__OperandList__Group_1_1__0__Impl rule__OperandList__Group_1_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5544:2: rule__OperandList__Group_1_1__0__Impl rule__OperandList__Group_1_1__1
            {
            pushFollow(FOLLOW_rule__OperandList__Group_1_1__0__Impl_in_rule__OperandList__Group_1_1__011342);
            rule__OperandList__Group_1_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__OperandList__Group_1_1__1_in_rule__OperandList__Group_1_1__011345);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5551:1: rule__OperandList__Group_1_1__0__Impl : ( KEYWORD_4 ) ;
    public final void rule__OperandList__Group_1_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5555:1: ( ( KEYWORD_4 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5556:1: ( KEYWORD_4 )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5556:1: ( KEYWORD_4 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5557:1: KEYWORD_4
            {
             before(grammarAccess.getOperandListAccess().getCommaKeyword_1_1_0()); 
            match(input,KEYWORD_4,FOLLOW_KEYWORD_4_in_rule__OperandList__Group_1_1__0__Impl11373); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5570:1: rule__OperandList__Group_1_1__1 : rule__OperandList__Group_1_1__1__Impl ;
    public final void rule__OperandList__Group_1_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5574:1: ( rule__OperandList__Group_1_1__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5575:2: rule__OperandList__Group_1_1__1__Impl
            {
            pushFollow(FOLLOW_rule__OperandList__Group_1_1__1__Impl_in_rule__OperandList__Group_1_1__111404);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5581:1: rule__OperandList__Group_1_1__1__Impl : ( ( rule__OperandList__EntriesAssignment_1_1_1 ) ) ;
    public final void rule__OperandList__Group_1_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5585:1: ( ( ( rule__OperandList__EntriesAssignment_1_1_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5586:1: ( ( rule__OperandList__EntriesAssignment_1_1_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5586:1: ( ( rule__OperandList__EntriesAssignment_1_1_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5587:1: ( rule__OperandList__EntriesAssignment_1_1_1 )
            {
             before(grammarAccess.getOperandListAccess().getEntriesAssignment_1_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5588:1: ( rule__OperandList__EntriesAssignment_1_1_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5588:2: rule__OperandList__EntriesAssignment_1_1_1
            {
            pushFollow(FOLLOW_rule__OperandList__EntriesAssignment_1_1_1_in_rule__OperandList__Group_1_1__1__Impl11431);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5602:1: rule__Operand__Group__0 : rule__Operand__Group__0__Impl rule__Operand__Group__1 ;
    public final void rule__Operand__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5606:1: ( rule__Operand__Group__0__Impl rule__Operand__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5607:2: rule__Operand__Group__0__Impl rule__Operand__Group__1
            {
            pushFollow(FOLLOW_rule__Operand__Group__0__Impl_in_rule__Operand__Group__011465);
            rule__Operand__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Operand__Group__1_in_rule__Operand__Group__011468);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5614:1: rule__Operand__Group__0__Impl : ( ruleOperandFragment ) ;
    public final void rule__Operand__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5618:1: ( ( ruleOperandFragment ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5619:1: ( ruleOperandFragment )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5619:1: ( ruleOperandFragment )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5620:1: ruleOperandFragment
            {
             before(grammarAccess.getOperandAccess().getOperandFragmentParserRuleCall_0()); 
            pushFollow(FOLLOW_ruleOperandFragment_in_rule__Operand__Group__0__Impl11495);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5631:1: rule__Operand__Group__1 : rule__Operand__Group__1__Impl ;
    public final void rule__Operand__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5635:1: ( rule__Operand__Group__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5636:2: rule__Operand__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__Operand__Group__1__Impl_in_rule__Operand__Group__111524);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5642:1: rule__Operand__Group__1__Impl : ( ( rule__Operand__Group_1__0 )? ) ;
    public final void rule__Operand__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5646:1: ( ( ( rule__Operand__Group_1__0 )? ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5647:1: ( ( rule__Operand__Group_1__0 )? )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5647:1: ( ( rule__Operand__Group_1__0 )? )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5648:1: ( rule__Operand__Group_1__0 )?
            {
             before(grammarAccess.getOperandAccess().getGroup_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5649:1: ( rule__Operand__Group_1__0 )?
            int alt50=2;
            int LA50_0 = input.LA(1);

            if ( (LA50_0==KEYWORD_22||LA50_0==KEYWORD_3||LA50_0==KEYWORD_5||LA50_0==KEYWORD_7||LA50_0==RULE_STAR) ) {
                alt50=1;
            }
            switch (alt50) {
                case 1 :
                    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5649:2: rule__Operand__Group_1__0
                    {
                    pushFollow(FOLLOW_rule__Operand__Group_1__0_in_rule__Operand__Group__1__Impl11551);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5663:1: rule__Operand__Group_1__0 : rule__Operand__Group_1__0__Impl rule__Operand__Group_1__1 ;
    public final void rule__Operand__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5667:1: ( rule__Operand__Group_1__0__Impl rule__Operand__Group_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5668:2: rule__Operand__Group_1__0__Impl rule__Operand__Group_1__1
            {
            pushFollow(FOLLOW_rule__Operand__Group_1__0__Impl_in_rule__Operand__Group_1__011586);
            rule__Operand__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Operand__Group_1__1_in_rule__Operand__Group_1__011589);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5675:1: rule__Operand__Group_1__0__Impl : ( () ) ;
    public final void rule__Operand__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5679:1: ( ( () ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5680:1: ( () )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5680:1: ( () )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5681:1: ()
            {
             before(grammarAccess.getOperandAccess().getOperandsEntriesAction_1_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5682:1: ()
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5684:1: 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5694:1: rule__Operand__Group_1__1 : rule__Operand__Group_1__1__Impl ;
    public final void rule__Operand__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5698:1: ( rule__Operand__Group_1__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5699:2: rule__Operand__Group_1__1__Impl
            {
            pushFollow(FOLLOW_rule__Operand__Group_1__1__Impl_in_rule__Operand__Group_1__111647);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5705:1: rule__Operand__Group_1__1__Impl : ( ( ( rule__Operand__Group_1_1__0 ) ) ( ( rule__Operand__Group_1_1__0 )* ) ) ;
    public final void rule__Operand__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5709:1: ( ( ( ( rule__Operand__Group_1_1__0 ) ) ( ( rule__Operand__Group_1_1__0 )* ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5710:1: ( ( ( rule__Operand__Group_1_1__0 ) ) ( ( rule__Operand__Group_1_1__0 )* ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5710:1: ( ( ( rule__Operand__Group_1_1__0 ) ) ( ( rule__Operand__Group_1_1__0 )* ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5711:1: ( ( rule__Operand__Group_1_1__0 ) ) ( ( rule__Operand__Group_1_1__0 )* )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5711:1: ( ( rule__Operand__Group_1_1__0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5712:1: ( rule__Operand__Group_1_1__0 )
            {
             before(grammarAccess.getOperandAccess().getGroup_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5713:1: ( rule__Operand__Group_1_1__0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5713:2: rule__Operand__Group_1_1__0
            {
            pushFollow(FOLLOW_rule__Operand__Group_1_1__0_in_rule__Operand__Group_1__1__Impl11676);
            rule__Operand__Group_1_1__0();

            state._fsp--;


            }

             after(grammarAccess.getOperandAccess().getGroup_1_1()); 

            }

            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5716:1: ( ( rule__Operand__Group_1_1__0 )* )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5717:1: ( rule__Operand__Group_1_1__0 )*
            {
             before(grammarAccess.getOperandAccess().getGroup_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5718:1: ( rule__Operand__Group_1_1__0 )*
            loop51:
            do {
                int alt51=2;
                int LA51_0 = input.LA(1);

                if ( (LA51_0==KEYWORD_22||LA51_0==KEYWORD_3||LA51_0==KEYWORD_5||LA51_0==KEYWORD_7||LA51_0==RULE_STAR) ) {
                    alt51=1;
                }


                switch (alt51) {
            	case 1 :
            	    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5718:2: rule__Operand__Group_1_1__0
            	    {
            	    pushFollow(FOLLOW_rule__Operand__Group_1_1__0_in_rule__Operand__Group_1__1__Impl11688);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5733:1: rule__Operand__Group_1_1__0 : rule__Operand__Group_1_1__0__Impl rule__Operand__Group_1_1__1 ;
    public final void rule__Operand__Group_1_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5737:1: ( rule__Operand__Group_1_1__0__Impl rule__Operand__Group_1_1__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5738:2: rule__Operand__Group_1_1__0__Impl rule__Operand__Group_1_1__1
            {
            pushFollow(FOLLOW_rule__Operand__Group_1_1__0__Impl_in_rule__Operand__Group_1_1__011725);
            rule__Operand__Group_1_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Operand__Group_1_1__1_in_rule__Operand__Group_1_1__011728);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5745:1: rule__Operand__Group_1_1__0__Impl : ( ( rule__Operand__Alternatives_1_1_0 ) ) ;
    public final void rule__Operand__Group_1_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5749:1: ( ( ( rule__Operand__Alternatives_1_1_0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5750:1: ( ( rule__Operand__Alternatives_1_1_0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5750:1: ( ( rule__Operand__Alternatives_1_1_0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5751:1: ( rule__Operand__Alternatives_1_1_0 )
            {
             before(grammarAccess.getOperandAccess().getAlternatives_1_1_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5752:1: ( rule__Operand__Alternatives_1_1_0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5752:2: rule__Operand__Alternatives_1_1_0
            {
            pushFollow(FOLLOW_rule__Operand__Alternatives_1_1_0_in_rule__Operand__Group_1_1__0__Impl11755);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5762:1: rule__Operand__Group_1_1__1 : rule__Operand__Group_1_1__1__Impl ;
    public final void rule__Operand__Group_1_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5766:1: ( rule__Operand__Group_1_1__1__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5767:2: rule__Operand__Group_1_1__1__Impl
            {
            pushFollow(FOLLOW_rule__Operand__Group_1_1__1__Impl_in_rule__Operand__Group_1_1__111785);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5773:1: rule__Operand__Group_1_1__1__Impl : ( ( rule__Operand__EntriesAssignment_1_1_1 ) ) ;
    public final void rule__Operand__Group_1_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5777:1: ( ( ( rule__Operand__EntriesAssignment_1_1_1 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5778:1: ( ( rule__Operand__EntriesAssignment_1_1_1 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5778:1: ( ( rule__Operand__EntriesAssignment_1_1_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5779:1: ( rule__Operand__EntriesAssignment_1_1_1 )
            {
             before(grammarAccess.getOperandAccess().getEntriesAssignment_1_1_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5780:1: ( rule__Operand__EntriesAssignment_1_1_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5780:2: rule__Operand__EntriesAssignment_1_1_1
            {
            pushFollow(FOLLOW_rule__Operand__EntriesAssignment_1_1_1_in_rule__Operand__Group_1_1__1__Impl11812);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5794:1: rule__ParameterOperand__Group__0 : rule__ParameterOperand__Group__0__Impl rule__ParameterOperand__Group__1 ;
    public final void rule__ParameterOperand__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5798:1: ( rule__ParameterOperand__Group__0__Impl rule__ParameterOperand__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5799:2: rule__ParameterOperand__Group__0__Impl rule__ParameterOperand__Group__1
            {
            pushFollow(FOLLOW_rule__ParameterOperand__Group__0__Impl_in_rule__ParameterOperand__Group__011846);
            rule__ParameterOperand__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ParameterOperand__Group__1_in_rule__ParameterOperand__Group__011849);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5806:1: rule__ParameterOperand__Group__0__Impl : ( KEYWORD_13 ) ;
    public final void rule__ParameterOperand__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5810:1: ( ( KEYWORD_13 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5811:1: ( KEYWORD_13 )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5811:1: ( KEYWORD_13 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5812:1: KEYWORD_13
            {
             before(grammarAccess.getParameterOperandAccess().getPKeyword_0()); 
            match(input,KEYWORD_13,FOLLOW_KEYWORD_13_in_rule__ParameterOperand__Group__0__Impl11877); 
             after(grammarAccess.getParameterOperandAccess().getPKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ParameterOperand__Group__0__Impl"


    // $ANTLR start "rule__ParameterOperand__Group__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5825:1: rule__ParameterOperand__Group__1 : rule__ParameterOperand__Group__1__Impl rule__ParameterOperand__Group__2 ;
    public final void rule__ParameterOperand__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5829:1: ( rule__ParameterOperand__Group__1__Impl rule__ParameterOperand__Group__2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5830:2: rule__ParameterOperand__Group__1__Impl rule__ParameterOperand__Group__2
            {
            pushFollow(FOLLOW_rule__ParameterOperand__Group__1__Impl_in_rule__ParameterOperand__Group__111908);
            rule__ParameterOperand__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ParameterOperand__Group__2_in_rule__ParameterOperand__Group__111911);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5837:1: rule__ParameterOperand__Group__1__Impl : ( () ) ;
    public final void rule__ParameterOperand__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5841:1: ( ( () ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5842:1: ( () )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5842:1: ( () )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5843:1: ()
            {
             before(grammarAccess.getParameterOperandAccess().getPOperandAction_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5844:1: ()
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5846:1: 
            {
            }

             after(grammarAccess.getParameterOperandAccess().getPOperandAction_1()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ParameterOperand__Group__1__Impl"


    // $ANTLR start "rule__ParameterOperand__Group__2"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5856:1: rule__ParameterOperand__Group__2 : rule__ParameterOperand__Group__2__Impl rule__ParameterOperand__Group__3 ;
    public final void rule__ParameterOperand__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5860:1: ( rule__ParameterOperand__Group__2__Impl rule__ParameterOperand__Group__3 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5861:2: rule__ParameterOperand__Group__2__Impl rule__ParameterOperand__Group__3
            {
            pushFollow(FOLLOW_rule__ParameterOperand__Group__2__Impl_in_rule__ParameterOperand__Group__211969);
            rule__ParameterOperand__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ParameterOperand__Group__3_in_rule__ParameterOperand__Group__211972);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5868:1: rule__ParameterOperand__Group__2__Impl : ( KEYWORD_11 ) ;
    public final void rule__ParameterOperand__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5872:1: ( ( KEYWORD_11 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5873:1: ( KEYWORD_11 )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5873:1: ( KEYWORD_11 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5874:1: KEYWORD_11
            {
             before(grammarAccess.getParameterOperandAccess().getLeftCurlyBracketKeyword_2()); 
            match(input,KEYWORD_11,FOLLOW_KEYWORD_11_in_rule__ParameterOperand__Group__2__Impl12000); 
             after(grammarAccess.getParameterOperandAccess().getLeftCurlyBracketKeyword_2()); 

            }


            }

        }
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5887:1: rule__ParameterOperand__Group__3 : rule__ParameterOperand__Group__3__Impl rule__ParameterOperand__Group__4 ;
    public final void rule__ParameterOperand__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5891:1: ( rule__ParameterOperand__Group__3__Impl rule__ParameterOperand__Group__4 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5892:2: rule__ParameterOperand__Group__3__Impl rule__ParameterOperand__Group__4
            {
            pushFollow(FOLLOW_rule__ParameterOperand__Group__3__Impl_in_rule__ParameterOperand__Group__312031);
            rule__ParameterOperand__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ParameterOperand__Group__4_in_rule__ParameterOperand__Group__312034);
            rule__ParameterOperand__Group__4();

            state._fsp--;


            }

        }
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5899:1: rule__ParameterOperand__Group__3__Impl : ( ( rule__ParameterOperand__PrmAssignment_3 ) ) ;
    public final void rule__ParameterOperand__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5903:1: ( ( ( rule__ParameterOperand__PrmAssignment_3 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5904:1: ( ( rule__ParameterOperand__PrmAssignment_3 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5904:1: ( ( rule__ParameterOperand__PrmAssignment_3 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5905:1: ( rule__ParameterOperand__PrmAssignment_3 )
            {
             before(grammarAccess.getParameterOperandAccess().getPrmAssignment_3()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5906:1: ( rule__ParameterOperand__PrmAssignment_3 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5906:2: rule__ParameterOperand__PrmAssignment_3
            {
            pushFollow(FOLLOW_rule__ParameterOperand__PrmAssignment_3_in_rule__ParameterOperand__Group__3__Impl12061);
            rule__ParameterOperand__PrmAssignment_3();

            state._fsp--;


            }

             after(grammarAccess.getParameterOperandAccess().getPrmAssignment_3()); 

            }


            }

        }
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


    // $ANTLR start "rule__ParameterOperand__Group__4"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5916:1: rule__ParameterOperand__Group__4 : rule__ParameterOperand__Group__4__Impl ;
    public final void rule__ParameterOperand__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5920:1: ( rule__ParameterOperand__Group__4__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5921:2: rule__ParameterOperand__Group__4__Impl
            {
            pushFollow(FOLLOW_rule__ParameterOperand__Group__4__Impl_in_rule__ParameterOperand__Group__412091);
            rule__ParameterOperand__Group__4__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ParameterOperand__Group__4"


    // $ANTLR start "rule__ParameterOperand__Group__4__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5927:1: rule__ParameterOperand__Group__4__Impl : ( KEYWORD_12 ) ;
    public final void rule__ParameterOperand__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5931:1: ( ( KEYWORD_12 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5932:1: ( KEYWORD_12 )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5932:1: ( KEYWORD_12 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5933:1: KEYWORD_12
            {
             before(grammarAccess.getParameterOperandAccess().getRightCurlyBracketKeyword_4()); 
            match(input,KEYWORD_12,FOLLOW_KEYWORD_12_in_rule__ParameterOperand__Group__4__Impl12119); 
             after(grammarAccess.getParameterOperandAccess().getRightCurlyBracketKeyword_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ParameterOperand__Group__4__Impl"


    // $ANTLR start "rule__ExclamationParameterOperand__Group__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5956:1: rule__ExclamationParameterOperand__Group__0 : rule__ExclamationParameterOperand__Group__0__Impl rule__ExclamationParameterOperand__Group__1 ;
    public final void rule__ExclamationParameterOperand__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5960:1: ( rule__ExclamationParameterOperand__Group__0__Impl rule__ExclamationParameterOperand__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5961:2: rule__ExclamationParameterOperand__Group__0__Impl rule__ExclamationParameterOperand__Group__1
            {
            pushFollow(FOLLOW_rule__ExclamationParameterOperand__Group__0__Impl_in_rule__ExclamationParameterOperand__Group__012160);
            rule__ExclamationParameterOperand__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ExclamationParameterOperand__Group__1_in_rule__ExclamationParameterOperand__Group__012163);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5968:1: rule__ExclamationParameterOperand__Group__0__Impl : ( KEYWORD_23 ) ;
    public final void rule__ExclamationParameterOperand__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5972:1: ( ( KEYWORD_23 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5973:1: ( KEYWORD_23 )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5973:1: ( KEYWORD_23 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5974:1: KEYWORD_23
            {
             before(grammarAccess.getExclamationParameterOperandAccess().getPKeyword_0()); 
            match(input,KEYWORD_23,FOLLOW_KEYWORD_23_in_rule__ExclamationParameterOperand__Group__0__Impl12191); 
             after(grammarAccess.getExclamationParameterOperandAccess().getPKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ExclamationParameterOperand__Group__0__Impl"


    // $ANTLR start "rule__ExclamationParameterOperand__Group__1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5987:1: rule__ExclamationParameterOperand__Group__1 : rule__ExclamationParameterOperand__Group__1__Impl rule__ExclamationParameterOperand__Group__2 ;
    public final void rule__ExclamationParameterOperand__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5991:1: ( rule__ExclamationParameterOperand__Group__1__Impl rule__ExclamationParameterOperand__Group__2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5992:2: rule__ExclamationParameterOperand__Group__1__Impl rule__ExclamationParameterOperand__Group__2
            {
            pushFollow(FOLLOW_rule__ExclamationParameterOperand__Group__1__Impl_in_rule__ExclamationParameterOperand__Group__112222);
            rule__ExclamationParameterOperand__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ExclamationParameterOperand__Group__2_in_rule__ExclamationParameterOperand__Group__112225);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:5999:1: rule__ExclamationParameterOperand__Group__1__Impl : ( () ) ;
    public final void rule__ExclamationParameterOperand__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6003:1: ( ( () ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6004:1: ( () )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6004:1: ( () )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6005:1: ()
            {
             before(grammarAccess.getExclamationParameterOperandAccess().getExpOperandAction_1()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6006:1: ()
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6008:1: 
            {
            }

             after(grammarAccess.getExclamationParameterOperandAccess().getExpOperandAction_1()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ExclamationParameterOperand__Group__1__Impl"


    // $ANTLR start "rule__ExclamationParameterOperand__Group__2"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6018:1: rule__ExclamationParameterOperand__Group__2 : rule__ExclamationParameterOperand__Group__2__Impl rule__ExclamationParameterOperand__Group__3 ;
    public final void rule__ExclamationParameterOperand__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6022:1: ( rule__ExclamationParameterOperand__Group__2__Impl rule__ExclamationParameterOperand__Group__3 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6023:2: rule__ExclamationParameterOperand__Group__2__Impl rule__ExclamationParameterOperand__Group__3
            {
            pushFollow(FOLLOW_rule__ExclamationParameterOperand__Group__2__Impl_in_rule__ExclamationParameterOperand__Group__212283);
            rule__ExclamationParameterOperand__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ExclamationParameterOperand__Group__3_in_rule__ExclamationParameterOperand__Group__212286);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6030:1: rule__ExclamationParameterOperand__Group__2__Impl : ( KEYWORD_11 ) ;
    public final void rule__ExclamationParameterOperand__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6034:1: ( ( KEYWORD_11 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6035:1: ( KEYWORD_11 )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6035:1: ( KEYWORD_11 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6036:1: KEYWORD_11
            {
             before(grammarAccess.getExclamationParameterOperandAccess().getLeftCurlyBracketKeyword_2()); 
            match(input,KEYWORD_11,FOLLOW_KEYWORD_11_in_rule__ExclamationParameterOperand__Group__2__Impl12314); 
             after(grammarAccess.getExclamationParameterOperandAccess().getLeftCurlyBracketKeyword_2()); 

            }


            }

        }
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6049:1: rule__ExclamationParameterOperand__Group__3 : rule__ExclamationParameterOperand__Group__3__Impl rule__ExclamationParameterOperand__Group__4 ;
    public final void rule__ExclamationParameterOperand__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6053:1: ( rule__ExclamationParameterOperand__Group__3__Impl rule__ExclamationParameterOperand__Group__4 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6054:2: rule__ExclamationParameterOperand__Group__3__Impl rule__ExclamationParameterOperand__Group__4
            {
            pushFollow(FOLLOW_rule__ExclamationParameterOperand__Group__3__Impl_in_rule__ExclamationParameterOperand__Group__312345);
            rule__ExclamationParameterOperand__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ExclamationParameterOperand__Group__4_in_rule__ExclamationParameterOperand__Group__312348);
            rule__ExclamationParameterOperand__Group__4();

            state._fsp--;


            }

        }
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6061:1: rule__ExclamationParameterOperand__Group__3__Impl : ( ( rule__ExclamationParameterOperand__PrmAssignment_3 ) ) ;
    public final void rule__ExclamationParameterOperand__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6065:1: ( ( ( rule__ExclamationParameterOperand__PrmAssignment_3 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6066:1: ( ( rule__ExclamationParameterOperand__PrmAssignment_3 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6066:1: ( ( rule__ExclamationParameterOperand__PrmAssignment_3 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6067:1: ( rule__ExclamationParameterOperand__PrmAssignment_3 )
            {
             before(grammarAccess.getExclamationParameterOperandAccess().getPrmAssignment_3()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6068:1: ( rule__ExclamationParameterOperand__PrmAssignment_3 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6068:2: rule__ExclamationParameterOperand__PrmAssignment_3
            {
            pushFollow(FOLLOW_rule__ExclamationParameterOperand__PrmAssignment_3_in_rule__ExclamationParameterOperand__Group__3__Impl12375);
            rule__ExclamationParameterOperand__PrmAssignment_3();

            state._fsp--;


            }

             after(grammarAccess.getExclamationParameterOperandAccess().getPrmAssignment_3()); 

            }


            }

        }
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


    // $ANTLR start "rule__ExclamationParameterOperand__Group__4"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6078:1: rule__ExclamationParameterOperand__Group__4 : rule__ExclamationParameterOperand__Group__4__Impl ;
    public final void rule__ExclamationParameterOperand__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6082:1: ( rule__ExclamationParameterOperand__Group__4__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6083:2: rule__ExclamationParameterOperand__Group__4__Impl
            {
            pushFollow(FOLLOW_rule__ExclamationParameterOperand__Group__4__Impl_in_rule__ExclamationParameterOperand__Group__412405);
            rule__ExclamationParameterOperand__Group__4__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ExclamationParameterOperand__Group__4"


    // $ANTLR start "rule__ExclamationParameterOperand__Group__4__Impl"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6089:1: rule__ExclamationParameterOperand__Group__4__Impl : ( KEYWORD_12 ) ;
    public final void rule__ExclamationParameterOperand__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6093:1: ( ( KEYWORD_12 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6094:1: ( KEYWORD_12 )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6094:1: ( KEYWORD_12 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6095:1: KEYWORD_12
            {
             before(grammarAccess.getExclamationParameterOperandAccess().getRightCurlyBracketKeyword_4()); 
            match(input,KEYWORD_12,FOLLOW_KEYWORD_12_in_rule__ExclamationParameterOperand__Group__4__Impl12433); 
             after(grammarAccess.getExclamationParameterOperandAccess().getRightCurlyBracketKeyword_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ExclamationParameterOperand__Group__4__Impl"


    // $ANTLR start "rule__SubQueryOperand__Group__0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6118:1: rule__SubQueryOperand__Group__0 : rule__SubQueryOperand__Group__0__Impl rule__SubQueryOperand__Group__1 ;
    public final void rule__SubQueryOperand__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6122:1: ( rule__SubQueryOperand__Group__0__Impl rule__SubQueryOperand__Group__1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6123:2: rule__SubQueryOperand__Group__0__Impl rule__SubQueryOperand__Group__1
            {
            pushFollow(FOLLOW_rule__SubQueryOperand__Group__0__Impl_in_rule__SubQueryOperand__Group__012474);
            rule__SubQueryOperand__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__SubQueryOperand__Group__1_in_rule__SubQueryOperand__Group__012477);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6130:1: rule__SubQueryOperand__Group__0__Impl : ( () ) ;
    public final void rule__SubQueryOperand__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6134:1: ( ( () ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6135:1: ( () )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6135:1: ( () )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6136:1: ()
            {
             before(grammarAccess.getSubQueryOperandAccess().getSubqueryAction_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6137:1: ()
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6139:1: 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6149:1: rule__SubQueryOperand__Group__1 : rule__SubQueryOperand__Group__1__Impl rule__SubQueryOperand__Group__2 ;
    public final void rule__SubQueryOperand__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6153:1: ( rule__SubQueryOperand__Group__1__Impl rule__SubQueryOperand__Group__2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6154:2: rule__SubQueryOperand__Group__1__Impl rule__SubQueryOperand__Group__2
            {
            pushFollow(FOLLOW_rule__SubQueryOperand__Group__1__Impl_in_rule__SubQueryOperand__Group__112535);
            rule__SubQueryOperand__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__SubQueryOperand__Group__2_in_rule__SubQueryOperand__Group__112538);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6161:1: rule__SubQueryOperand__Group__1__Impl : ( KEYWORD_1 ) ;
    public final void rule__SubQueryOperand__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6165:1: ( ( KEYWORD_1 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6166:1: ( KEYWORD_1 )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6166:1: ( KEYWORD_1 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6167:1: KEYWORD_1
            {
             before(grammarAccess.getSubQueryOperandAccess().getLeftParenthesisKeyword_1()); 
            match(input,KEYWORD_1,FOLLOW_KEYWORD_1_in_rule__SubQueryOperand__Group__1__Impl12566); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6180:1: rule__SubQueryOperand__Group__2 : rule__SubQueryOperand__Group__2__Impl rule__SubQueryOperand__Group__3 ;
    public final void rule__SubQueryOperand__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6184:1: ( rule__SubQueryOperand__Group__2__Impl rule__SubQueryOperand__Group__3 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6185:2: rule__SubQueryOperand__Group__2__Impl rule__SubQueryOperand__Group__3
            {
            pushFollow(FOLLOW_rule__SubQueryOperand__Group__2__Impl_in_rule__SubQueryOperand__Group__212597);
            rule__SubQueryOperand__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__SubQueryOperand__Group__3_in_rule__SubQueryOperand__Group__212600);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6192:1: rule__SubQueryOperand__Group__2__Impl : ( ( rule__SubQueryOperand__SelAssignment_2 ) ) ;
    public final void rule__SubQueryOperand__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6196:1: ( ( ( rule__SubQueryOperand__SelAssignment_2 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6197:1: ( ( rule__SubQueryOperand__SelAssignment_2 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6197:1: ( ( rule__SubQueryOperand__SelAssignment_2 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6198:1: ( rule__SubQueryOperand__SelAssignment_2 )
            {
             before(grammarAccess.getSubQueryOperandAccess().getSelAssignment_2()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6199:1: ( rule__SubQueryOperand__SelAssignment_2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6199:2: rule__SubQueryOperand__SelAssignment_2
            {
            pushFollow(FOLLOW_rule__SubQueryOperand__SelAssignment_2_in_rule__SubQueryOperand__Group__2__Impl12627);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6209:1: rule__SubQueryOperand__Group__3 : rule__SubQueryOperand__Group__3__Impl ;
    public final void rule__SubQueryOperand__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6213:1: ( rule__SubQueryOperand__Group__3__Impl )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6214:2: rule__SubQueryOperand__Group__3__Impl
            {
            pushFollow(FOLLOW_rule__SubQueryOperand__Group__3__Impl_in_rule__SubQueryOperand__Group__312657);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6220:1: rule__SubQueryOperand__Group__3__Impl : ( KEYWORD_2 ) ;
    public final void rule__SubQueryOperand__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6224:1: ( ( KEYWORD_2 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6225:1: ( KEYWORD_2 )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6225:1: ( KEYWORD_2 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6226:1: KEYWORD_2
            {
             before(grammarAccess.getSubQueryOperandAccess().getRightParenthesisKeyword_3()); 
            match(input,KEYWORD_2,FOLLOW_KEYWORD_2_in_rule__SubQueryOperand__Group__3__Impl12685); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6248:1: rule__Model__EntriesAssignment_1_1_1 : ( ruleSelect ) ;
    public final void rule__Model__EntriesAssignment_1_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6252:1: ( ( ruleSelect ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6253:1: ( ruleSelect )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6253:1: ( ruleSelect )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6254:1: ruleSelect
            {
             before(grammarAccess.getModelAccess().getEntriesSelectParserRuleCall_1_1_1_0()); 
            pushFollow(FOLLOW_ruleSelect_in_rule__Model__EntriesAssignment_1_1_112729);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6263:1: rule__Model__OrderByEntryAssignment_2_1 : ( ruleOrderByColumns ) ;
    public final void rule__Model__OrderByEntryAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6267:1: ( ( ruleOrderByColumns ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6268:1: ( ruleOrderByColumns )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6268:1: ( ruleOrderByColumns )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6269:1: ruleOrderByColumns
            {
             before(grammarAccess.getModelAccess().getOrderByEntryOrderByColumnsParserRuleCall_2_1_0()); 
            pushFollow(FOLLOW_ruleOrderByColumns_in_rule__Model__OrderByEntryAssignment_2_112760);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6278:1: rule__Select__SelectAssignment_0 : ( ( KEYWORD_37 ) ) ;
    public final void rule__Select__SelectAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6282:1: ( ( ( KEYWORD_37 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6283:1: ( ( KEYWORD_37 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6283:1: ( ( KEYWORD_37 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6284:1: ( KEYWORD_37 )
            {
             before(grammarAccess.getSelectAccess().getSelectSELECTKeyword_0_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6285:1: ( KEYWORD_37 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6286:1: KEYWORD_37
            {
             before(grammarAccess.getSelectAccess().getSelectSELECTKeyword_0_0()); 
            match(input,KEYWORD_37,FOLLOW_KEYWORD_37_in_rule__Select__SelectAssignment_012796); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6301:1: rule__Select__ColsAssignment_2 : ( ruleColumns ) ;
    public final void rule__Select__ColsAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6305:1: ( ( ruleColumns ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6306:1: ( ruleColumns )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6306:1: ( ruleColumns )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6307:1: ruleColumns
            {
             before(grammarAccess.getSelectAccess().getColsColumnsParserRuleCall_2_0()); 
            pushFollow(FOLLOW_ruleColumns_in_rule__Select__ColsAssignment_212835);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6316:1: rule__Select__TblAssignment_4 : ( ruleTables ) ;
    public final void rule__Select__TblAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6320:1: ( ( ruleTables ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6321:1: ( ruleTables )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6321:1: ( ruleTables )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6322:1: ruleTables
            {
             before(grammarAccess.getSelectAccess().getTblTablesParserRuleCall_4_0()); 
            pushFollow(FOLLOW_ruleTables_in_rule__Select__TblAssignment_412866);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6331:1: rule__Select__WhereExpressionAssignment_5_1 : ( ruleFullExpression ) ;
    public final void rule__Select__WhereExpressionAssignment_5_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6335:1: ( ( ruleFullExpression ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6336:1: ( ruleFullExpression )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6336:1: ( ruleFullExpression )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6337:1: ruleFullExpression
            {
             before(grammarAccess.getSelectAccess().getWhereExpressionFullExpressionParserRuleCall_5_1_0()); 
            pushFollow(FOLLOW_ruleFullExpression_in_rule__Select__WhereExpressionAssignment_5_112897);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6346:1: rule__Select__GroupByEntryAssignment_6_1 : ( ruleGroupByColumns ) ;
    public final void rule__Select__GroupByEntryAssignment_6_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6350:1: ( ( ruleGroupByColumns ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6351:1: ( ruleGroupByColumns )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6351:1: ( ruleGroupByColumns )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6352:1: ruleGroupByColumns
            {
             before(grammarAccess.getSelectAccess().getGroupByEntryGroupByColumnsParserRuleCall_6_1_0()); 
            pushFollow(FOLLOW_ruleGroupByColumns_in_rule__Select__GroupByEntryAssignment_6_112928);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6361:1: rule__Select__HavingEntryAssignment_7_1 : ( ruleFullExpression ) ;
    public final void rule__Select__HavingEntryAssignment_7_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6365:1: ( ( ruleFullExpression ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6366:1: ( ruleFullExpression )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6366:1: ( ruleFullExpression )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6367:1: ruleFullExpression
            {
             before(grammarAccess.getSelectAccess().getHavingEntryFullExpressionParserRuleCall_7_1_0()); 
            pushFollow(FOLLOW_ruleFullExpression_in_rule__Select__HavingEntryAssignment_7_112959);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6376:1: rule__Columns__EntriesAssignment_1_1_1 : ( ruleColumnOrAlias ) ;
    public final void rule__Columns__EntriesAssignment_1_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6380:1: ( ( ruleColumnOrAlias ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6381:1: ( ruleColumnOrAlias )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6381:1: ( ruleColumnOrAlias )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6382:1: ruleColumnOrAlias
            {
             before(grammarAccess.getColumnsAccess().getEntriesColumnOrAliasParserRuleCall_1_1_1_0()); 
            pushFollow(FOLLOW_ruleColumnOrAlias_in_rule__Columns__EntriesAssignment_1_1_112990);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6391:1: rule__ColumnOrAlias__CfullAssignment_0_0 : ( ruleColumnFull ) ;
    public final void rule__ColumnOrAlias__CfullAssignment_0_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6395:1: ( ( ruleColumnFull ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6396:1: ( ruleColumnFull )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6396:1: ( ruleColumnFull )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6397:1: ruleColumnFull
            {
             before(grammarAccess.getColumnOrAliasAccess().getCfullColumnFullParserRuleCall_0_0_0()); 
            pushFollow(FOLLOW_ruleColumnFull_in_rule__ColumnOrAlias__CfullAssignment_0_013021);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6406:1: rule__ColumnOrAlias__AliasAssignment_0_1 : ( ( KEYWORD_18 ) ) ;
    public final void rule__ColumnOrAlias__AliasAssignment_0_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6410:1: ( ( ( KEYWORD_18 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6411:1: ( ( KEYWORD_18 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6411:1: ( ( KEYWORD_18 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6412:1: ( KEYWORD_18 )
            {
             before(grammarAccess.getColumnOrAliasAccess().getAliasASKeyword_0_1_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6413:1: ( KEYWORD_18 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6414:1: KEYWORD_18
            {
             before(grammarAccess.getColumnOrAliasAccess().getAliasASKeyword_0_1_0()); 
            match(input,KEYWORD_18,FOLLOW_KEYWORD_18_in_rule__ColumnOrAlias__AliasAssignment_0_113057); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6429:1: rule__ColumnOrAlias__ColAliasAssignment_0_2 : ( ruleDbObjectName ) ;
    public final void rule__ColumnOrAlias__ColAliasAssignment_0_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6433:1: ( ( ruleDbObjectName ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6434:1: ( ruleDbObjectName )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6434:1: ( ruleDbObjectName )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6435:1: ruleDbObjectName
            {
             before(grammarAccess.getColumnOrAliasAccess().getColAliasDbObjectNameParserRuleCall_0_2_0()); 
            pushFollow(FOLLOW_ruleDbObjectName_in_rule__ColumnOrAlias__ColAliasAssignment_0_213096);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6444:1: rule__ColumnOrAlias__AllColsAssignment_1 : ( RULE_STAR ) ;
    public final void rule__ColumnOrAlias__AllColsAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6448:1: ( ( RULE_STAR ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6449:1: ( RULE_STAR )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6449:1: ( RULE_STAR )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6450:1: RULE_STAR
            {
             before(grammarAccess.getColumnOrAliasAccess().getAllColsSTARTerminalRuleCall_1_0()); 
            match(input,RULE_STAR,FOLLOW_RULE_STAR_in_rule__ColumnOrAlias__AllColsAssignment_113127); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6459:1: rule__ColumnFull__EntriesAssignment_1_1_1 : ( ruleDbObjectName ) ;
    public final void rule__ColumnFull__EntriesAssignment_1_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6463:1: ( ( ruleDbObjectName ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6464:1: ( ruleDbObjectName )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6464:1: ( ruleDbObjectName )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6465:1: ruleDbObjectName
            {
             before(grammarAccess.getColumnFullAccess().getEntriesDbObjectNameParserRuleCall_1_1_1_0()); 
            pushFollow(FOLLOW_ruleDbObjectName_in_rule__ColumnFull__EntriesAssignment_1_1_113158);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6474:1: rule__Tables__EntriesAssignment_1_1_1 : ( ruleFromTable ) ;
    public final void rule__Tables__EntriesAssignment_1_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6478:1: ( ( ruleFromTable ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6479:1: ( ruleFromTable )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6479:1: ( ruleFromTable )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6480:1: ruleFromTable
            {
             before(grammarAccess.getTablesAccess().getEntriesFromTableParserRuleCall_1_1_1_0()); 
            pushFollow(FOLLOW_ruleFromTable_in_rule__Tables__EntriesAssignment_1_1_113189);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6489:1: rule__FromTable__TableAssignment_0 : ( ruleTableOrAlias ) ;
    public final void rule__FromTable__TableAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6493:1: ( ( ruleTableOrAlias ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6494:1: ( ruleTableOrAlias )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6494:1: ( ruleTableOrAlias )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6495:1: ruleTableOrAlias
            {
             before(grammarAccess.getFromTableAccess().getTableTableOrAliasParserRuleCall_0_0()); 
            pushFollow(FOLLOW_ruleTableOrAlias_in_rule__FromTable__TableAssignment_013220);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6504:1: rule__FromTable__JoinAssignment_1_0 : ( ruleJoinType ) ;
    public final void rule__FromTable__JoinAssignment_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6508:1: ( ( ruleJoinType ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6509:1: ( ruleJoinType )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6509:1: ( ruleJoinType )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6510:1: ruleJoinType
            {
             before(grammarAccess.getFromTableAccess().getJoinJoinTypeEnumRuleCall_1_0_0()); 
            pushFollow(FOLLOW_ruleJoinType_in_rule__FromTable__JoinAssignment_1_013251);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6519:1: rule__FromTable__OnTableAssignment_1_1 : ( ruleTableOrAlias ) ;
    public final void rule__FromTable__OnTableAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6523:1: ( ( ruleTableOrAlias ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6524:1: ( ruleTableOrAlias )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6524:1: ( ruleTableOrAlias )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6525:1: ruleTableOrAlias
            {
             before(grammarAccess.getFromTableAccess().getOnTableTableOrAliasParserRuleCall_1_1_0()); 
            pushFollow(FOLLOW_ruleTableOrAlias_in_rule__FromTable__OnTableAssignment_1_113282);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6534:1: rule__FromTable__JoinExprAssignment_1_3 : ( ruleFullExpression ) ;
    public final void rule__FromTable__JoinExprAssignment_1_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6538:1: ( ( ruleFullExpression ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6539:1: ( ruleFullExpression )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6539:1: ( ruleFullExpression )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6540:1: ruleFullExpression
            {
             before(grammarAccess.getFromTableAccess().getJoinExprFullExpressionParserRuleCall_1_3_0()); 
            pushFollow(FOLLOW_ruleFullExpression_in_rule__FromTable__JoinExprAssignment_1_313313);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6549:1: rule__TableOrAlias__TfullAssignment_0 : ( ruleTableFull ) ;
    public final void rule__TableOrAlias__TfullAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6553:1: ( ( ruleTableFull ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6554:1: ( ruleTableFull )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6554:1: ( ruleTableFull )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6555:1: ruleTableFull
            {
             before(grammarAccess.getTableOrAliasAccess().getTfullTableFullParserRuleCall_0_0()); 
            pushFollow(FOLLOW_ruleTableFull_in_rule__TableOrAlias__TfullAssignment_013344);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6564:1: rule__TableOrAlias__AliasAssignment_1 : ( ( KEYWORD_18 ) ) ;
    public final void rule__TableOrAlias__AliasAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6568:1: ( ( ( KEYWORD_18 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6569:1: ( ( KEYWORD_18 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6569:1: ( ( KEYWORD_18 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6570:1: ( KEYWORD_18 )
            {
             before(grammarAccess.getTableOrAliasAccess().getAliasASKeyword_1_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6571:1: ( KEYWORD_18 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6572:1: KEYWORD_18
            {
             before(grammarAccess.getTableOrAliasAccess().getAliasASKeyword_1_0()); 
            match(input,KEYWORD_18,FOLLOW_KEYWORD_18_in_rule__TableOrAlias__AliasAssignment_113380); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6587:1: rule__TableOrAlias__TblAliasAssignment_2 : ( ruleDbObjectName ) ;
    public final void rule__TableOrAlias__TblAliasAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6591:1: ( ( ruleDbObjectName ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6592:1: ( ruleDbObjectName )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6592:1: ( ruleDbObjectName )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6593:1: ruleDbObjectName
            {
             before(grammarAccess.getTableOrAliasAccess().getTblAliasDbObjectNameParserRuleCall_2_0()); 
            pushFollow(FOLLOW_ruleDbObjectName_in_rule__TableOrAlias__TblAliasAssignment_213419);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6602:1: rule__TableFull__EntriesAssignment_1_1_1 : ( ruleDbObjectName ) ;
    public final void rule__TableFull__EntriesAssignment_1_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6606:1: ( ( ruleDbObjectName ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6607:1: ( ruleDbObjectName )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6607:1: ( ruleDbObjectName )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6608:1: ruleDbObjectName
            {
             before(grammarAccess.getTableFullAccess().getEntriesDbObjectNameParserRuleCall_1_1_1_0()); 
            pushFollow(FOLLOW_ruleDbObjectName_in_rule__TableFull__EntriesAssignment_1_1_113450);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6617:1: rule__DbObjectName__DbnameAssignment : ( RULE_ID ) ;
    public final void rule__DbObjectName__DbnameAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6621:1: ( ( RULE_ID ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6622:1: ( RULE_ID )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6622:1: ( RULE_ID )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6623:1: RULE_ID
            {
             before(grammarAccess.getDbObjectNameAccess().getDbnameIDTerminalRuleCall_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__DbObjectName__DbnameAssignment13481); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6632:1: rule__OrderByColumns__EntriesAssignment_1_1_1 : ( ruleOrderByColumnFull ) ;
    public final void rule__OrderByColumns__EntriesAssignment_1_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6636:1: ( ( ruleOrderByColumnFull ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6637:1: ( ruleOrderByColumnFull )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6637:1: ( ruleOrderByColumnFull )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6638:1: ruleOrderByColumnFull
            {
             before(grammarAccess.getOrderByColumnsAccess().getEntriesOrderByColumnFullParserRuleCall_1_1_1_0()); 
            pushFollow(FOLLOW_ruleOrderByColumnFull_in_rule__OrderByColumns__EntriesAssignment_1_1_113512);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6647:1: rule__OrderByColumnFull__ColOrderAssignment_0 : ( ruleColumnFull ) ;
    public final void rule__OrderByColumnFull__ColOrderAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6651:1: ( ( ruleColumnFull ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6652:1: ( ruleColumnFull )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6652:1: ( ruleColumnFull )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6653:1: ruleColumnFull
            {
             before(grammarAccess.getOrderByColumnFullAccess().getColOrderColumnFullParserRuleCall_0_0()); 
            pushFollow(FOLLOW_ruleColumnFull_in_rule__OrderByColumnFull__ColOrderAssignment_013543);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6662:1: rule__OrderByColumnFull__DirectionAssignment_1 : ( ( rule__OrderByColumnFull__DirectionAlternatives_1_0 ) ) ;
    public final void rule__OrderByColumnFull__DirectionAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6666:1: ( ( ( rule__OrderByColumnFull__DirectionAlternatives_1_0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6667:1: ( ( rule__OrderByColumnFull__DirectionAlternatives_1_0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6667:1: ( ( rule__OrderByColumnFull__DirectionAlternatives_1_0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6668:1: ( rule__OrderByColumnFull__DirectionAlternatives_1_0 )
            {
             before(grammarAccess.getOrderByColumnFullAccess().getDirectionAlternatives_1_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6669:1: ( rule__OrderByColumnFull__DirectionAlternatives_1_0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6669:2: rule__OrderByColumnFull__DirectionAlternatives_1_0
            {
            pushFollow(FOLLOW_rule__OrderByColumnFull__DirectionAlternatives_1_0_in_rule__OrderByColumnFull__DirectionAssignment_113574);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6678:1: rule__GroupByColumns__EntriesAssignment_1_1_1 : ( ruleGroupByColumnFull ) ;
    public final void rule__GroupByColumns__EntriesAssignment_1_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6682:1: ( ( ruleGroupByColumnFull ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6683:1: ( ruleGroupByColumnFull )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6683:1: ( ruleGroupByColumnFull )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6684:1: ruleGroupByColumnFull
            {
             before(grammarAccess.getGroupByColumnsAccess().getEntriesGroupByColumnFullParserRuleCall_1_1_1_0()); 
            pushFollow(FOLLOW_ruleGroupByColumnFull_in_rule__GroupByColumns__EntriesAssignment_1_1_113607);
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


    // $ANTLR start "rule__GroupByColumnFull__ColGrByAssignment"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6693:1: rule__GroupByColumnFull__ColGrByAssignment : ( ruleColumnFull ) ;
    public final void rule__GroupByColumnFull__ColGrByAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6697:1: ( ( ruleColumnFull ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6698:1: ( ruleColumnFull )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6698:1: ( ruleColumnFull )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6699:1: ruleColumnFull
            {
             before(grammarAccess.getGroupByColumnFullAccess().getColGrByColumnFullParserRuleCall_0()); 
            pushFollow(FOLLOW_ruleColumnFull_in_rule__GroupByColumnFull__ColGrByAssignment13638);
            ruleColumnFull();

            state._fsp--;

             after(grammarAccess.getGroupByColumnFullAccess().getColGrByColumnFullParserRuleCall_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__GroupByColumnFull__ColGrByAssignment"


    // $ANTLR start "rule__FullExpression__EntriesAssignment_1_1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6708:1: rule__FullExpression__EntriesAssignment_1_1 : ( ruleExpressionFragmentSecond ) ;
    public final void rule__FullExpression__EntriesAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6712:1: ( ( ruleExpressionFragmentSecond ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6713:1: ( ruleExpressionFragmentSecond )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6713:1: ( ruleExpressionFragmentSecond )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6714:1: ruleExpressionFragmentSecond
            {
             before(grammarAccess.getFullExpressionAccess().getEntriesExpressionFragmentSecondParserRuleCall_1_1_0()); 
            pushFollow(FOLLOW_ruleExpressionFragmentSecond_in_rule__FullExpression__EntriesAssignment_1_113669);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6723:1: rule__ExpressionFragmentSecond__CAssignment_0 : ( ( rule__ExpressionFragmentSecond__CAlternatives_0_0 ) ) ;
    public final void rule__ExpressionFragmentSecond__CAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6727:1: ( ( ( rule__ExpressionFragmentSecond__CAlternatives_0_0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6728:1: ( ( rule__ExpressionFragmentSecond__CAlternatives_0_0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6728:1: ( ( rule__ExpressionFragmentSecond__CAlternatives_0_0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6729:1: ( rule__ExpressionFragmentSecond__CAlternatives_0_0 )
            {
             before(grammarAccess.getExpressionFragmentSecondAccess().getCAlternatives_0_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6730:1: ( rule__ExpressionFragmentSecond__CAlternatives_0_0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6730:2: rule__ExpressionFragmentSecond__CAlternatives_0_0
            {
            pushFollow(FOLLOW_rule__ExpressionFragmentSecond__CAlternatives_0_0_in_rule__ExpressionFragmentSecond__CAssignment_013700);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6739:1: rule__ExpressionFragmentSecond__EfragAssignment_1 : ( ruleExpressionFragment ) ;
    public final void rule__ExpressionFragmentSecond__EfragAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6743:1: ( ( ruleExpressionFragment ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6744:1: ( ruleExpressionFragment )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6744:1: ( ruleExpressionFragment )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6745:1: ruleExpressionFragment
            {
             before(grammarAccess.getExpressionFragmentSecondAccess().getEfragExpressionFragmentParserRuleCall_1_0()); 
            pushFollow(FOLLOW_ruleExpressionFragment_in_rule__ExpressionFragmentSecond__EfragAssignment_113733);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6754:1: rule__ExpressionFragment__ExpgroupAssignment_0 : ( ruleExpressionGroup ) ;
    public final void rule__ExpressionFragment__ExpgroupAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6758:1: ( ( ruleExpressionGroup ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6759:1: ( ruleExpressionGroup )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6759:1: ( ruleExpressionGroup )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6760:1: ruleExpressionGroup
            {
             before(grammarAccess.getExpressionFragmentAccess().getExpgroupExpressionGroupParserRuleCall_0_0()); 
            pushFollow(FOLLOW_ruleExpressionGroup_in_rule__ExpressionFragment__ExpgroupAssignment_013764);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6769:1: rule__ExpressionFragment__ExpAssignment_1 : ( ruleExpression ) ;
    public final void rule__ExpressionFragment__ExpAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6773:1: ( ( ruleExpression ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6774:1: ( ruleExpression )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6774:1: ( ruleExpression )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6775:1: ruleExpression
            {
             before(grammarAccess.getExpressionFragmentAccess().getExpExpressionParserRuleCall_1_0()); 
            pushFollow(FOLLOW_ruleExpression_in_rule__ExpressionFragment__ExpAssignment_113795);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6784:1: rule__ExpressionFragment__XexpAssignment_2 : ( ruleXExpression ) ;
    public final void rule__ExpressionFragment__XexpAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6788:1: ( ( ruleXExpression ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6789:1: ( ruleXExpression )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6789:1: ( ruleXExpression )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6790:1: ruleXExpression
            {
             before(grammarAccess.getExpressionFragmentAccess().getXexpXExpressionParserRuleCall_2_0()); 
            pushFollow(FOLLOW_ruleXExpression_in_rule__ExpressionFragment__XexpAssignment_213826);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6799:1: rule__ExpressionGroup__ExprAssignment_2 : ( ruleFullExpression ) ;
    public final void rule__ExpressionGroup__ExprAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6803:1: ( ( ruleFullExpression ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6804:1: ( ruleFullExpression )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6804:1: ( ruleFullExpression )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6805:1: ruleFullExpression
            {
             before(grammarAccess.getExpressionGroupAccess().getExprFullExpressionParserRuleCall_2_0()); 
            pushFollow(FOLLOW_ruleFullExpression_in_rule__ExpressionGroup__ExprAssignment_213857);
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


    // $ANTLR start "rule__XExpression__XfAssignment_3"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6814:1: rule__XExpression__XfAssignment_3 : ( ruleXFunction ) ;
    public final void rule__XExpression__XfAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6818:1: ( ( ruleXFunction ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6819:1: ( ruleXFunction )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6819:1: ( ruleXFunction )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6820:1: ruleXFunction
            {
             before(grammarAccess.getXExpressionAccess().getXfXFunctionEnumRuleCall_3_0()); 
            pushFollow(FOLLOW_ruleXFunction_in_rule__XExpression__XfAssignment_313888);
            ruleXFunction();

            state._fsp--;

             after(grammarAccess.getXExpressionAccess().getXfXFunctionEnumRuleCall_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XExpression__XfAssignment_3"


    // $ANTLR start "rule__XExpression__ColAssignment_5"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6829:1: rule__XExpression__ColAssignment_5 : ( ruleColumnOperand ) ;
    public final void rule__XExpression__ColAssignment_5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6833:1: ( ( ruleColumnOperand ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6834:1: ( ruleColumnOperand )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6834:1: ( ruleColumnOperand )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6835:1: ruleColumnOperand
            {
             before(grammarAccess.getXExpressionAccess().getColColumnOperandParserRuleCall_5_0()); 
            pushFollow(FOLLOW_ruleColumnOperand_in_rule__XExpression__ColAssignment_513919);
            ruleColumnOperand();

            state._fsp--;

             after(grammarAccess.getXExpressionAccess().getColColumnOperandParserRuleCall_5_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XExpression__ColAssignment_5"


    // $ANTLR start "rule__XExpression__PrmAssignment_6_1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6844:1: rule__XExpression__PrmAssignment_6_1 : ( ruleXExpressionParams ) ;
    public final void rule__XExpression__PrmAssignment_6_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6848:1: ( ( ruleXExpressionParams ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6849:1: ( ruleXExpressionParams )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6849:1: ( ruleXExpressionParams )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6850:1: ruleXExpressionParams
            {
             before(grammarAccess.getXExpressionAccess().getPrmXExpressionParamsParserRuleCall_6_1_0()); 
            pushFollow(FOLLOW_ruleXExpressionParams_in_rule__XExpression__PrmAssignment_6_113950);
            ruleXExpressionParams();

            state._fsp--;

             after(grammarAccess.getXExpressionAccess().getPrmXExpressionParamsParserRuleCall_6_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XExpression__PrmAssignment_6_1"


    // $ANTLR start "rule__XExpressionParams__EntriesAssignment_1_1_1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6859:1: rule__XExpressionParams__EntriesAssignment_1_1_1 : ( ruleJRParameter ) ;
    public final void rule__XExpressionParams__EntriesAssignment_1_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6863:1: ( ( ruleJRParameter ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6864:1: ( ruleJRParameter )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6864:1: ( ruleJRParameter )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6865:1: ruleJRParameter
            {
             before(grammarAccess.getXExpressionParamsAccess().getEntriesJRParameterParserRuleCall_1_1_1_0()); 
            pushFollow(FOLLOW_ruleJRParameter_in_rule__XExpressionParams__EntriesAssignment_1_1_113981);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6874:1: rule__JRParameter__JrprmAssignment : ( RULE_ID ) ;
    public final void rule__JRParameter__JrprmAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6878:1: ( ( RULE_ID ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6879:1: ( RULE_ID )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6879:1: ( RULE_ID )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6880:1: RULE_ID
            {
             before(grammarAccess.getJRParameterAccess().getJrprmIDTerminalRuleCall_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__JRParameter__JrprmAssignment14012); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6889:1: rule__Expression__Op1Assignment_0 : ( ruleOperand ) ;
    public final void rule__Expression__Op1Assignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6893:1: ( ( ruleOperand ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6894:1: ( ruleOperand )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6894:1: ( ruleOperand )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6895:1: ruleOperand
            {
             before(grammarAccess.getExpressionAccess().getOp1OperandParserRuleCall_0_0()); 
            pushFollow(FOLLOW_ruleOperand_in_rule__Expression__Op1Assignment_014043);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6904:1: rule__Expression__IsnullAssignment_1_0 : ( ( rule__Expression__IsnullAlternatives_1_0_0 ) ) ;
    public final void rule__Expression__IsnullAssignment_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6908:1: ( ( ( rule__Expression__IsnullAlternatives_1_0_0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6909:1: ( ( rule__Expression__IsnullAlternatives_1_0_0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6909:1: ( ( rule__Expression__IsnullAlternatives_1_0_0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6910:1: ( rule__Expression__IsnullAlternatives_1_0_0 )
            {
             before(grammarAccess.getExpressionAccess().getIsnullAlternatives_1_0_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6911:1: ( rule__Expression__IsnullAlternatives_1_0_0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6911:2: rule__Expression__IsnullAlternatives_1_0_0
            {
            pushFollow(FOLLOW_rule__Expression__IsnullAlternatives_1_0_0_in_rule__Expression__IsnullAssignment_1_014074);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6920:1: rule__Expression__InAssignment_1_1 : ( ruleInOperator ) ;
    public final void rule__Expression__InAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6924:1: ( ( ruleInOperator ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6925:1: ( ruleInOperator )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6925:1: ( ruleInOperator )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6926:1: ruleInOperator
            {
             before(grammarAccess.getExpressionAccess().getInInOperatorParserRuleCall_1_1_0()); 
            pushFollow(FOLLOW_ruleInOperator_in_rule__Expression__InAssignment_1_114107);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6935:1: rule__Expression__BetweenAssignment_1_2 : ( ruleBetween ) ;
    public final void rule__Expression__BetweenAssignment_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6939:1: ( ( ruleBetween ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6940:1: ( ruleBetween )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6940:1: ( ruleBetween )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6941:1: ruleBetween
            {
             before(grammarAccess.getExpressionAccess().getBetweenBetweenParserRuleCall_1_2_0()); 
            pushFollow(FOLLOW_ruleBetween_in_rule__Expression__BetweenAssignment_1_214138);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6950:1: rule__Expression__LikeAssignment_1_3 : ( ruleLike ) ;
    public final void rule__Expression__LikeAssignment_1_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6954:1: ( ( ruleLike ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6955:1: ( ruleLike )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6955:1: ( ruleLike )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6956:1: ruleLike
            {
             before(grammarAccess.getExpressionAccess().getLikeLikeParserRuleCall_1_3_0()); 
            pushFollow(FOLLOW_ruleLike_in_rule__Expression__LikeAssignment_1_314169);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6965:1: rule__Expression__CompAssignment_1_4 : ( ruleComparison ) ;
    public final void rule__Expression__CompAssignment_1_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6969:1: ( ( ruleComparison ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6970:1: ( ruleComparison )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6970:1: ( ruleComparison )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6971:1: ruleComparison
            {
             before(grammarAccess.getExpressionAccess().getCompComparisonParserRuleCall_1_4_0()); 
            pushFollow(FOLLOW_ruleComparison_in_rule__Expression__CompAssignment_1_414200);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6980:1: rule__Comparison__OperatorAssignment_0 : ( ( rule__Comparison__OperatorAlternatives_0_0 ) ) ;
    public final void rule__Comparison__OperatorAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6984:1: ( ( ( rule__Comparison__OperatorAlternatives_0_0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6985:1: ( ( rule__Comparison__OperatorAlternatives_0_0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6985:1: ( ( rule__Comparison__OperatorAlternatives_0_0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6986:1: ( rule__Comparison__OperatorAlternatives_0_0 )
            {
             before(grammarAccess.getComparisonAccess().getOperatorAlternatives_0_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6987:1: ( rule__Comparison__OperatorAlternatives_0_0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6987:2: rule__Comparison__OperatorAlternatives_0_0
            {
            pushFollow(FOLLOW_rule__Comparison__OperatorAlternatives_0_0_in_rule__Comparison__OperatorAssignment_014231);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:6996:1: rule__Comparison__Op2Assignment_1 : ( ruleOperand ) ;
    public final void rule__Comparison__Op2Assignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7000:1: ( ( ruleOperand ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7001:1: ( ruleOperand )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7001:1: ( ruleOperand )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7002:1: ruleOperand
            {
             before(grammarAccess.getComparisonAccess().getOp2OperandParserRuleCall_1_0()); 
            pushFollow(FOLLOW_ruleOperand_in_rule__Comparison__Op2Assignment_114264);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7011:1: rule__Like__OpLikeAssignment_0 : ( ( rule__Like__OpLikeAlternatives_0_0 ) ) ;
    public final void rule__Like__OpLikeAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7015:1: ( ( ( rule__Like__OpLikeAlternatives_0_0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7016:1: ( ( rule__Like__OpLikeAlternatives_0_0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7016:1: ( ( rule__Like__OpLikeAlternatives_0_0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7017:1: ( rule__Like__OpLikeAlternatives_0_0 )
            {
             before(grammarAccess.getLikeAccess().getOpLikeAlternatives_0_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7018:1: ( rule__Like__OpLikeAlternatives_0_0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7018:2: rule__Like__OpLikeAlternatives_0_0
            {
            pushFollow(FOLLOW_rule__Like__OpLikeAlternatives_0_0_in_rule__Like__OpLikeAssignment_014295);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7027:1: rule__Like__Op2Assignment_1 : ( ruleStringOperand ) ;
    public final void rule__Like__Op2Assignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7031:1: ( ( ruleStringOperand ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7032:1: ( ruleStringOperand )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7032:1: ( ruleStringOperand )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7033:1: ruleStringOperand
            {
             before(grammarAccess.getLikeAccess().getOp2StringOperandParserRuleCall_1_0()); 
            pushFollow(FOLLOW_ruleStringOperand_in_rule__Like__Op2Assignment_114328);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7042:1: rule__Between__OpBetweenAssignment_0 : ( ( rule__Between__OpBetweenAlternatives_0_0 ) ) ;
    public final void rule__Between__OpBetweenAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7046:1: ( ( ( rule__Between__OpBetweenAlternatives_0_0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7047:1: ( ( rule__Between__OpBetweenAlternatives_0_0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7047:1: ( ( rule__Between__OpBetweenAlternatives_0_0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7048:1: ( rule__Between__OpBetweenAlternatives_0_0 )
            {
             before(grammarAccess.getBetweenAccess().getOpBetweenAlternatives_0_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7049:1: ( rule__Between__OpBetweenAlternatives_0_0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7049:2: rule__Between__OpBetweenAlternatives_0_0
            {
            pushFollow(FOLLOW_rule__Between__OpBetweenAlternatives_0_0_in_rule__Between__OpBetweenAssignment_014359);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7058:1: rule__Between__Op2Assignment_1 : ( ruleOperand ) ;
    public final void rule__Between__Op2Assignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7062:1: ( ( ruleOperand ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7063:1: ( ruleOperand )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7063:1: ( ruleOperand )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7064:1: ruleOperand
            {
             before(grammarAccess.getBetweenAccess().getOp2OperandParserRuleCall_1_0()); 
            pushFollow(FOLLOW_ruleOperand_in_rule__Between__Op2Assignment_114392);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7073:1: rule__Between__Op3Assignment_3 : ( ruleOperand ) ;
    public final void rule__Between__Op3Assignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7077:1: ( ( ruleOperand ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7078:1: ( ruleOperand )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7078:1: ( ruleOperand )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7079:1: ruleOperand
            {
             before(grammarAccess.getBetweenAccess().getOp3OperandParserRuleCall_3_0()); 
            pushFollow(FOLLOW_ruleOperand_in_rule__Between__Op3Assignment_314423);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7088:1: rule__InOperator__OpAssignment_1 : ( ( rule__InOperator__OpAlternatives_1_0 ) ) ;
    public final void rule__InOperator__OpAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7092:1: ( ( ( rule__InOperator__OpAlternatives_1_0 ) ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7093:1: ( ( rule__InOperator__OpAlternatives_1_0 ) )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7093:1: ( ( rule__InOperator__OpAlternatives_1_0 ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7094:1: ( rule__InOperator__OpAlternatives_1_0 )
            {
             before(grammarAccess.getInOperatorAccess().getOpAlternatives_1_0()); 
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7095:1: ( rule__InOperator__OpAlternatives_1_0 )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7095:2: rule__InOperator__OpAlternatives_1_0
            {
            pushFollow(FOLLOW_rule__InOperator__OpAlternatives_1_0_in_rule__InOperator__OpAssignment_114454);
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


    // $ANTLR start "rule__InOperator__SubqueryAssignment_3_0"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7104:1: rule__InOperator__SubqueryAssignment_3_0 : ( ruleSubQueryOperand ) ;
    public final void rule__InOperator__SubqueryAssignment_3_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7108:1: ( ( ruleSubQueryOperand ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7109:1: ( ruleSubQueryOperand )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7109:1: ( ruleSubQueryOperand )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7110:1: ruleSubQueryOperand
            {
             before(grammarAccess.getInOperatorAccess().getSubquerySubQueryOperandParserRuleCall_3_0_0()); 
            pushFollow(FOLLOW_ruleSubQueryOperand_in_rule__InOperator__SubqueryAssignment_3_014487);
            ruleSubQueryOperand();

            state._fsp--;

             after(grammarAccess.getInOperatorAccess().getSubquerySubQueryOperandParserRuleCall_3_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InOperator__SubqueryAssignment_3_0"


    // $ANTLR start "rule__InOperator__OpListAssignment_3_1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7119:1: rule__InOperator__OpListAssignment_3_1 : ( ruleOperandList ) ;
    public final void rule__InOperator__OpListAssignment_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7123:1: ( ( ruleOperandList ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7124:1: ( ruleOperandList )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7124:1: ( ruleOperandList )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7125:1: ruleOperandList
            {
             before(grammarAccess.getInOperatorAccess().getOpListOperandListParserRuleCall_3_1_0()); 
            pushFollow(FOLLOW_ruleOperandList_in_rule__InOperator__OpListAssignment_3_114518);
            ruleOperandList();

            state._fsp--;

             after(grammarAccess.getInOperatorAccess().getOpListOperandListParserRuleCall_3_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InOperator__OpListAssignment_3_1"


    // $ANTLR start "rule__OperandList__EntriesAssignment_1_1_1"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7134:1: rule__OperandList__EntriesAssignment_1_1_1 : ( ruleXOperandFragment ) ;
    public final void rule__OperandList__EntriesAssignment_1_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7138:1: ( ( ruleXOperandFragment ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7139:1: ( ruleXOperandFragment )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7139:1: ( ruleXOperandFragment )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7140:1: ruleXOperandFragment
            {
             before(grammarAccess.getOperandListAccess().getEntriesXOperandFragmentParserRuleCall_1_1_1_0()); 
            pushFollow(FOLLOW_ruleXOperandFragment_in_rule__OperandList__EntriesAssignment_1_1_114549);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7149:1: rule__Operand__EntriesAssignment_1_1_1 : ( ruleOperandFragment ) ;
    public final void rule__Operand__EntriesAssignment_1_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7153:1: ( ( ruleOperandFragment ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7154:1: ( ruleOperandFragment )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7154:1: ( ruleOperandFragment )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7155:1: ruleOperandFragment
            {
             before(grammarAccess.getOperandAccess().getEntriesOperandFragmentParserRuleCall_1_1_1_0()); 
            pushFollow(FOLLOW_ruleOperandFragment_in_rule__Operand__EntriesAssignment_1_1_114580);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7164:1: rule__OperandFragment__ColumnAssignment_0 : ( ruleColumnOperand ) ;
    public final void rule__OperandFragment__ColumnAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7168:1: ( ( ruleColumnOperand ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7169:1: ( ruleColumnOperand )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7169:1: ( ruleColumnOperand )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7170:1: ruleColumnOperand
            {
             before(grammarAccess.getOperandFragmentAccess().getColumnColumnOperandParserRuleCall_0_0()); 
            pushFollow(FOLLOW_ruleColumnOperand_in_rule__OperandFragment__ColumnAssignment_014611);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7179:1: rule__OperandFragment__XopAssignment_1 : ( ruleXOperandFragment ) ;
    public final void rule__OperandFragment__XopAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7183:1: ( ( ruleXOperandFragment ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7184:1: ( ruleXOperandFragment )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7184:1: ( ruleXOperandFragment )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7185:1: ruleXOperandFragment
            {
             before(grammarAccess.getOperandFragmentAccess().getXopXOperandFragmentParserRuleCall_1_0()); 
            pushFollow(FOLLOW_ruleXOperandFragment_in_rule__OperandFragment__XopAssignment_114642);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7194:1: rule__OperandFragment__SubqAssignment_2 : ( ruleSubQueryOperand ) ;
    public final void rule__OperandFragment__SubqAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7198:1: ( ( ruleSubQueryOperand ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7199:1: ( ruleSubQueryOperand )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7199:1: ( ruleSubQueryOperand )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7200:1: ruleSubQueryOperand
            {
             before(grammarAccess.getOperandFragmentAccess().getSubqSubQueryOperandParserRuleCall_2_0()); 
            pushFollow(FOLLOW_ruleSubQueryOperand_in_rule__OperandFragment__SubqAssignment_214673);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7209:1: rule__XOperandFragment__ParamAssignment_0 : ( ruleParameterOperand ) ;
    public final void rule__XOperandFragment__ParamAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7213:1: ( ( ruleParameterOperand ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7214:1: ( ruleParameterOperand )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7214:1: ( ruleParameterOperand )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7215:1: ruleParameterOperand
            {
             before(grammarAccess.getXOperandFragmentAccess().getParamParameterOperandParserRuleCall_0_0()); 
            pushFollow(FOLLOW_ruleParameterOperand_in_rule__XOperandFragment__ParamAssignment_014704);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7224:1: rule__XOperandFragment__EparamAssignment_1 : ( ruleExclamationParameterOperand ) ;
    public final void rule__XOperandFragment__EparamAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7228:1: ( ( ruleExclamationParameterOperand ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7229:1: ( ruleExclamationParameterOperand )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7229:1: ( ruleExclamationParameterOperand )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7230:1: ruleExclamationParameterOperand
            {
             before(grammarAccess.getXOperandFragmentAccess().getEparamExclamationParameterOperandParserRuleCall_1_0()); 
            pushFollow(FOLLOW_ruleExclamationParameterOperand_in_rule__XOperandFragment__EparamAssignment_114735);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7239:1: rule__XOperandFragment__ScalarAssignment_2 : ( ruleScalarOperand ) ;
    public final void rule__XOperandFragment__ScalarAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7243:1: ( ( ruleScalarOperand ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7244:1: ( ruleScalarOperand )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7244:1: ( ruleScalarOperand )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7245:1: ruleScalarOperand
            {
             before(grammarAccess.getXOperandFragmentAccess().getScalarScalarOperandParserRuleCall_2_0()); 
            pushFollow(FOLLOW_ruleScalarOperand_in_rule__XOperandFragment__ScalarAssignment_214766);
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


    // $ANTLR start "rule__ParameterOperand__PrmAssignment_3"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7254:1: rule__ParameterOperand__PrmAssignment_3 : ( RULE_ID ) ;
    public final void rule__ParameterOperand__PrmAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7258:1: ( ( RULE_ID ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7259:1: ( RULE_ID )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7259:1: ( RULE_ID )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7260:1: RULE_ID
            {
             before(grammarAccess.getParameterOperandAccess().getPrmIDTerminalRuleCall_3_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__ParameterOperand__PrmAssignment_314797); 
             after(grammarAccess.getParameterOperandAccess().getPrmIDTerminalRuleCall_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ParameterOperand__PrmAssignment_3"


    // $ANTLR start "rule__ExclamationParameterOperand__PrmAssignment_3"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7269:1: rule__ExclamationParameterOperand__PrmAssignment_3 : ( RULE_ID ) ;
    public final void rule__ExclamationParameterOperand__PrmAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7273:1: ( ( RULE_ID ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7274:1: ( RULE_ID )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7274:1: ( RULE_ID )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7275:1: RULE_ID
            {
             before(grammarAccess.getExclamationParameterOperandAccess().getPrmIDTerminalRuleCall_3_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__ExclamationParameterOperand__PrmAssignment_314828); 
             after(grammarAccess.getExclamationParameterOperandAccess().getPrmIDTerminalRuleCall_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ExclamationParameterOperand__PrmAssignment_3"


    // $ANTLR start "rule__ColumnOperand__CfullAssignment"
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7284:1: rule__ColumnOperand__CfullAssignment : ( ruleColumnFull ) ;
    public final void rule__ColumnOperand__CfullAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7288:1: ( ( ruleColumnFull ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7289:1: ( ruleColumnFull )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7289:1: ( ruleColumnFull )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7290:1: ruleColumnFull
            {
             before(grammarAccess.getColumnOperandAccess().getCfullColumnFullParserRuleCall_0()); 
            pushFollow(FOLLOW_ruleColumnFull_in_rule__ColumnOperand__CfullAssignment14859);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7299:1: rule__SubQueryOperand__SelAssignment_2 : ( ruleSelect ) ;
    public final void rule__SubQueryOperand__SelAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7303:1: ( ( ruleSelect ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7304:1: ( ruleSelect )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7304:1: ( ruleSelect )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7305:1: ruleSelect
            {
             before(grammarAccess.getSubQueryOperandAccess().getSelSelectParserRuleCall_2_0()); 
            pushFollow(FOLLOW_ruleSelect_in_rule__SubQueryOperand__SelAssignment_214890);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7314:1: rule__ScalarOperand__SointAssignment_0 : ( RULE_SIGNED_INT ) ;
    public final void rule__ScalarOperand__SointAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7318:1: ( ( RULE_SIGNED_INT ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7319:1: ( RULE_SIGNED_INT )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7319:1: ( RULE_SIGNED_INT )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7320:1: RULE_SIGNED_INT
            {
             before(grammarAccess.getScalarOperandAccess().getSointSIGNED_INTTerminalRuleCall_0_0()); 
            match(input,RULE_SIGNED_INT,FOLLOW_RULE_SIGNED_INT_in_rule__ScalarOperand__SointAssignment_014921); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7329:1: rule__ScalarOperand__SostrAssignment_1 : ( ruleStringOperand ) ;
    public final void rule__ScalarOperand__SostrAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7333:1: ( ( ruleStringOperand ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7334:1: ( ruleStringOperand )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7334:1: ( ruleStringOperand )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7335:1: ruleStringOperand
            {
             before(grammarAccess.getScalarOperandAccess().getSostrStringOperandParserRuleCall_1_0()); 
            pushFollow(FOLLOW_ruleStringOperand_in_rule__ScalarOperand__SostrAssignment_114952);
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7344:1: rule__ScalarOperand__SodblAssignment_2 : ( RULE_SIGNED_DOUBLE ) ;
    public final void rule__ScalarOperand__SodblAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7348:1: ( ( RULE_SIGNED_DOUBLE ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7349:1: ( RULE_SIGNED_DOUBLE )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7349:1: ( RULE_SIGNED_DOUBLE )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7350:1: RULE_SIGNED_DOUBLE
            {
             before(grammarAccess.getScalarOperandAccess().getSodblSIGNED_DOUBLETerminalRuleCall_2_0()); 
            match(input,RULE_SIGNED_DOUBLE,FOLLOW_RULE_SIGNED_DOUBLE_in_rule__ScalarOperand__SodblAssignment_214983); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7359:1: rule__ScalarOperand__SodateAssignment_3 : ( RULE_DATE ) ;
    public final void rule__ScalarOperand__SodateAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7363:1: ( ( RULE_DATE ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7364:1: ( RULE_DATE )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7364:1: ( RULE_DATE )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7365:1: RULE_DATE
            {
             before(grammarAccess.getScalarOperandAccess().getSodateDATETerminalRuleCall_3_0()); 
            match(input,RULE_DATE,FOLLOW_RULE_DATE_in_rule__ScalarOperand__SodateAssignment_315014); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7374:1: rule__ScalarOperand__SotimeAssignment_4 : ( RULE_TIME ) ;
    public final void rule__ScalarOperand__SotimeAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7378:1: ( ( RULE_TIME ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7379:1: ( RULE_TIME )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7379:1: ( RULE_TIME )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7380:1: RULE_TIME
            {
             before(grammarAccess.getScalarOperandAccess().getSotimeTIMETerminalRuleCall_4_0()); 
            match(input,RULE_TIME,FOLLOW_RULE_TIME_in_rule__ScalarOperand__SotimeAssignment_415045); 
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
    // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7389:1: rule__ScalarOperand__SodtAssignment_5 : ( RULE_TIMESTAMP ) ;
    public final void rule__ScalarOperand__SodtAssignment_5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7393:1: ( ( RULE_TIMESTAMP ) )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7394:1: ( RULE_TIMESTAMP )
            {
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7394:1: ( RULE_TIMESTAMP )
            // ../com.jaspersoft.studio.data.sql.ui/src-gen/com/jaspersoft/studio/data/ui/contentassist/antlr/internal/InternalSqlParser.g:7395:1: RULE_TIMESTAMP
            {
             before(grammarAccess.getScalarOperandAccess().getSodtTIMESTAMPTerminalRuleCall_5_0()); 
            match(input,RULE_TIMESTAMP,FOLLOW_RULE_TIMESTAMP_in_rule__ScalarOperand__SodtAssignment_515076); 
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
    public static final BitSet FOLLOW_ruleGroupByColumnFull_in_entryRuleGroupByColumnFull886 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleGroupByColumnFull893 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__GroupByColumnFull__ColGrByAssignment_in_ruleGroupByColumnFull923 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFullExpression_in_entryRuleFullExpression950 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleFullExpression957 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FullExpression__Group__0_in_ruleFullExpression987 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpressionFragmentSecond_in_entryRuleExpressionFragmentSecond1014 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpressionFragmentSecond1021 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ExpressionFragmentSecond__Group__0_in_ruleExpressionFragmentSecond1051 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpressionFragment_in_entryRuleExpressionFragment1078 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpressionFragment1085 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ExpressionFragment__Alternatives_in_ruleExpressionFragment1115 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpressionGroup_in_entryRuleExpressionGroup1142 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpressionGroup1149 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ExpressionGroup__Group__0_in_ruleExpressionGroup1179 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXExpression_in_entryRuleXExpression1206 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleXExpression1213 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__XExpression__Group__0_in_ruleXExpression1243 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXExpressionParams_in_entryRuleXExpressionParams1270 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleXExpressionParams1277 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__XExpressionParams__Group__0_in_ruleXExpressionParams1307 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleJRParameter_in_entryRuleJRParameter1334 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleJRParameter1341 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__JRParameter__JrprmAssignment_in_ruleJRParameter1371 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression_in_entryRuleExpression1398 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpression1405 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Expression__Group__0_in_ruleExpression1435 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleComparison_in_entryRuleComparison1462 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleComparison1469 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Comparison__Group__0_in_ruleComparison1499 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLike_in_entryRuleLike1526 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleLike1533 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Like__Group__0_in_ruleLike1563 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBetween_in_entryRuleBetween1590 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleBetween1597 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Between__Group__0_in_ruleBetween1627 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInOperator_in_entryRuleInOperator1654 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleInOperator1661 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__InOperator__Group__0_in_ruleInOperator1691 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperandList_in_entryRuleOperandList1718 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOperandList1725 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OperandList__Group__0_in_ruleOperandList1755 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperand_in_entryRuleOperand1782 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOperand1789 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Operand__Group__0_in_ruleOperand1819 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperandFragment_in_entryRuleOperandFragment1846 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOperandFragment1853 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OperandFragment__Alternatives_in_ruleOperandFragment1883 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXOperandFragment_in_entryRuleXOperandFragment1910 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleXOperandFragment1917 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__XOperandFragment__Alternatives_in_ruleXOperandFragment1947 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleParameterOperand_in_entryRuleParameterOperand1974 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleParameterOperand1981 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ParameterOperand__Group__0_in_ruleParameterOperand2011 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExclamationParameterOperand_in_entryRuleExclamationParameterOperand2038 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExclamationParameterOperand2045 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ExclamationParameterOperand__Group__0_in_ruleExclamationParameterOperand2075 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnOperand_in_entryRuleColumnOperand2102 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleColumnOperand2109 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ColumnOperand__CfullAssignment_in_ruleColumnOperand2139 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSubQueryOperand_in_entryRuleSubQueryOperand2166 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSubQueryOperand2173 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SubQueryOperand__Group__0_in_ruleSubQueryOperand2203 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleScalarOperand_in_entryRuleScalarOperand2230 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleScalarOperand2237 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ScalarOperand__Alternatives_in_ruleScalarOperand2267 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleStringOperand_in_entryRuleStringOperand2294 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleStringOperand2301 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleStringOperand2331 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__XFunction__Alternatives_in_ruleXFunction2367 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__JoinType__Alternatives_in_ruleJoinType2403 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ColumnOrAlias__Group_0__0_in_rule__ColumnOrAlias__Alternatives2438 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ColumnOrAlias__AllColsAssignment_1_in_rule__ColumnOrAlias__Alternatives2456 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_25_in_rule__OrderByColumnFull__DirectionAlternatives_1_02490 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_26_in_rule__OrderByColumnFull__DirectionAlternatives_1_02510 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_24_in_rule__ExpressionFragmentSecond__CAlternatives_0_02545 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_21_in_rule__ExpressionFragmentSecond__CAlternatives_0_02565 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ExpressionFragment__ExpgroupAssignment_0_in_rule__ExpressionFragment__Alternatives2599 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ExpressionFragment__ExpAssignment_1_in_rule__ExpressionFragment__Alternatives2617 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ExpressionFragment__XexpAssignment_2_in_rule__ExpressionFragment__Alternatives2635 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Expression__IsnullAssignment_1_0_in_rule__Expression__Alternatives_12668 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Expression__InAssignment_1_1_in_rule__Expression__Alternatives_12686 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Expression__BetweenAssignment_1_2_in_rule__Expression__Alternatives_12704 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Expression__LikeAssignment_1_3_in_rule__Expression__Alternatives_12722 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Expression__CompAssignment_1_4_in_rule__Expression__Alternatives_12740 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_40_in_rule__Expression__IsnullAlternatives_1_0_02774 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_52_in_rule__Expression__IsnullAlternatives_1_0_02794 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_10_in_rule__Comparison__OperatorAlternatives_0_02829 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_17_in_rule__Comparison__OperatorAlternatives_0_02849 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_8_in_rule__Comparison__OperatorAlternatives_0_02869 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_15_in_rule__Comparison__OperatorAlternatives_0_02889 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_9_in_rule__Comparison__OperatorAlternatives_0_02909 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_16_in_rule__Comparison__OperatorAlternatives_0_02929 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_29_in_rule__Like__OpLikeAlternatives_0_02964 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_44_in_rule__Like__OpLikeAlternatives_0_02984 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_38_in_rule__Between__OpBetweenAlternatives_0_03019 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_53_in_rule__Between__OpBetweenAlternatives_0_03039 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_36_in_rule__InOperator__OpAlternatives_1_03074 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_19_in_rule__InOperator__OpAlternatives_1_03094 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__InOperator__SubqueryAssignment_3_0_in_rule__InOperator__Alternatives_33128 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__InOperator__OpListAssignment_3_1_in_rule__InOperator__Alternatives_33146 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_3_in_rule__Operand__Alternatives_1_1_03180 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_5_in_rule__Operand__Alternatives_1_1_03200 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STAR_in_rule__Operand__Alternatives_1_1_03219 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_7_in_rule__Operand__Alternatives_1_1_03237 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_22_in_rule__Operand__Alternatives_1_1_03257 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OperandFragment__ColumnAssignment_0_in_rule__OperandFragment__Alternatives3291 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OperandFragment__XopAssignment_1_in_rule__OperandFragment__Alternatives3309 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OperandFragment__SubqAssignment_2_in_rule__OperandFragment__Alternatives3327 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__XOperandFragment__ParamAssignment_0_in_rule__XOperandFragment__Alternatives3360 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__XOperandFragment__EparamAssignment_1_in_rule__XOperandFragment__Alternatives3378 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__XOperandFragment__ScalarAssignment_2_in_rule__XOperandFragment__Alternatives3396 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ScalarOperand__SointAssignment_0_in_rule__ScalarOperand__Alternatives3429 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ScalarOperand__SostrAssignment_1_in_rule__ScalarOperand__Alternatives3447 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ScalarOperand__SodblAssignment_2_in_rule__ScalarOperand__Alternatives3465 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ScalarOperand__SodateAssignment_3_in_rule__ScalarOperand__Alternatives3483 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ScalarOperand__SotimeAssignment_4_in_rule__ScalarOperand__Alternatives3501 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ScalarOperand__SodtAssignment_5_in_rule__ScalarOperand__Alternatives3519 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_19_in_rule__XFunction__Alternatives3553 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_32_in_rule__XFunction__Alternatives3573 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_30_in_rule__XFunction__Alternatives3593 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_45_in_rule__XFunction__Alternatives3613 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_28_in_rule__XFunction__Alternatives3633 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_39_in_rule__XFunction__Alternatives3653 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_31_in_rule__XFunction__Alternatives3673 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_48_in_rule__XFunction__Alternatives3693 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_38_in_rule__XFunction__Alternatives3713 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_49_in_rule__XFunction__Alternatives3733 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_47_in_rule__XFunction__Alternatives3753 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_41_in_rule__XFunction__Alternatives3773 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_51_in_rule__JoinType__Alternatives3808 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_55_in_rule__JoinType__Alternatives3828 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_56_in_rule__JoinType__Alternatives3848 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_54_in_rule__JoinType__Alternatives3868 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_50_in_rule__JoinType__Alternatives3888 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Model__Group__0__Impl_in_rule__Model__Group__03920 = new BitSet(new long[]{0x0000000020020000L});
    public static final BitSet FOLLOW_rule__Model__Group__1_in_rule__Model__Group__03923 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSelect_in_rule__Model__Group__0__Impl3950 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Model__Group__1__Impl_in_rule__Model__Group__13979 = new BitSet(new long[]{0x0000000020020000L});
    public static final BitSet FOLLOW_rule__Model__Group__2_in_rule__Model__Group__13982 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Model__Group_1__0_in_rule__Model__Group__1__Impl4009 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Model__Group__2__Impl_in_rule__Model__Group__24040 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Model__Group_2__0_in_rule__Model__Group__2__Impl4067 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Model__Group_1__0__Impl_in_rule__Model__Group_1__04104 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_rule__Model__Group_1__1_in_rule__Model__Group_1__04107 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Model__Group_1__1__Impl_in_rule__Model__Group_1__14165 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Model__Group_1_1__0_in_rule__Model__Group_1__1__Impl4194 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_rule__Model__Group_1_1__0_in_rule__Model__Group_1__1__Impl4206 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_rule__Model__Group_1_1__0__Impl_in_rule__Model__Group_1_1__04243 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_rule__Model__Group_1_1__1_in_rule__Model__Group_1_1__04246 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_33_in_rule__Model__Group_1_1__0__Impl4274 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Model__Group_1_1__1__Impl_in_rule__Model__Group_1_1__14305 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Model__EntriesAssignment_1_1_1_in_rule__Model__Group_1_1__1__Impl4332 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Model__Group_2__0__Impl_in_rule__Model__Group_2__04366 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_rule__Model__Group_2__1_in_rule__Model__Group_2__04369 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_46_in_rule__Model__Group_2__0__Impl4397 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Model__Group_2__1__Impl_in_rule__Model__Group_2__14428 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Model__OrderByEntryAssignment_2_1_in_rule__Model__Group_2__1__Impl4455 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Select__Group__0__Impl_in_rule__Select__Group__04489 = new BitSet(new long[]{0x1000000000002000L,0x0000000000000008L});
    public static final BitSet FOLLOW_rule__Select__Group__1_in_rule__Select__Group__04492 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Select__SelectAssignment_0_in_rule__Select__Group__0__Impl4519 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Select__Group__1__Impl_in_rule__Select__Group__14549 = new BitSet(new long[]{0x1000000000002000L,0x0000000000000008L});
    public static final BitSet FOLLOW_rule__Select__Group__2_in_rule__Select__Group__14552 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_42_in_rule__Select__Group__1__Impl4581 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Select__Group__2__Impl_in_rule__Select__Group__24614 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_rule__Select__Group__3_in_rule__Select__Group__24617 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Select__ColsAssignment_2_in_rule__Select__Group__2__Impl4644 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Select__Group__3__Impl_in_rule__Select__Group__34674 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_rule__Select__Group__4_in_rule__Select__Group__34677 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_27_in_rule__Select__Group__3__Impl4705 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Select__Group__4__Impl_in_rule__Select__Group__44736 = new BitSet(new long[]{0x0000000040804000L});
    public static final BitSet FOLLOW_rule__Select__Group__5_in_rule__Select__Group__44739 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Select__TblAssignment_4_in_rule__Select__Group__4__Impl4766 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Select__Group__5__Impl_in_rule__Select__Group__54796 = new BitSet(new long[]{0x0000000040804000L});
    public static final BitSet FOLLOW_rule__Select__Group__6_in_rule__Select__Group__54799 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Select__Group_5__0_in_rule__Select__Group__5__Impl4826 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Select__Group__6__Impl_in_rule__Select__Group__64857 = new BitSet(new long[]{0x0000000040804000L});
    public static final BitSet FOLLOW_rule__Select__Group__7_in_rule__Select__Group__64860 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Select__Group_6__0_in_rule__Select__Group__6__Impl4887 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Select__Group__7__Impl_in_rule__Select__Group__74918 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Select__Group_7__0_in_rule__Select__Group__7__Impl4945 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Select__Group_5__0__Impl_in_rule__Select__Group_5__04992 = new BitSet(new long[]{0xE00100C800000000L,0x000000000000002BL});
    public static final BitSet FOLLOW_rule__Select__Group_5__1_in_rule__Select__Group_5__04995 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_34_in_rule__Select__Group_5__0__Impl5023 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Select__Group_5__1__Impl_in_rule__Select__Group_5__15054 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Select__WhereExpressionAssignment_5_1_in_rule__Select__Group_5__1__Impl5081 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Select__Group_6__0__Impl_in_rule__Select__Group_6__05115 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_rule__Select__Group_6__1_in_rule__Select__Group_6__05118 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_43_in_rule__Select__Group_6__0__Impl5146 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Select__Group_6__1__Impl_in_rule__Select__Group_6__15177 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Select__GroupByEntryAssignment_6_1_in_rule__Select__Group_6__1__Impl5204 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Select__Group_7__0__Impl_in_rule__Select__Group_7__05238 = new BitSet(new long[]{0xE00100C800000000L,0x000000000000002BL});
    public static final BitSet FOLLOW_rule__Select__Group_7__1_in_rule__Select__Group_7__05241 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_35_in_rule__Select__Group_7__0__Impl5269 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Select__Group_7__1__Impl_in_rule__Select__Group_7__15300 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Select__HavingEntryAssignment_7_1_in_rule__Select__Group_7__1__Impl5327 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Columns__Group__0__Impl_in_rule__Columns__Group__05361 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_rule__Columns__Group__1_in_rule__Columns__Group__05364 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnOrAlias_in_rule__Columns__Group__0__Impl5391 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Columns__Group__1__Impl_in_rule__Columns__Group__15420 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Columns__Group_1__0_in_rule__Columns__Group__1__Impl5447 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Columns__Group_1__0__Impl_in_rule__Columns__Group_1__05482 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_rule__Columns__Group_1__1_in_rule__Columns__Group_1__05485 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Columns__Group_1__1__Impl_in_rule__Columns__Group_1__15543 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Columns__Group_1_1__0_in_rule__Columns__Group_1__1__Impl5572 = new BitSet(new long[]{0x0008000000000002L});
    public static final BitSet FOLLOW_rule__Columns__Group_1_1__0_in_rule__Columns__Group_1__1__Impl5584 = new BitSet(new long[]{0x0008000000000002L});
    public static final BitSet FOLLOW_rule__Columns__Group_1_1__0__Impl_in_rule__Columns__Group_1_1__05621 = new BitSet(new long[]{0x1000000000002000L,0x0000000000000008L});
    public static final BitSet FOLLOW_rule__Columns__Group_1_1__1_in_rule__Columns__Group_1_1__05624 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_4_in_rule__Columns__Group_1_1__0__Impl5652 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Columns__Group_1_1__1__Impl_in_rule__Columns__Group_1_1__15683 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Columns__EntriesAssignment_1_1_1_in_rule__Columns__Group_1_1__1__Impl5710 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ColumnOrAlias__Group_0__0__Impl_in_rule__ColumnOrAlias__Group_0__05744 = new BitSet(new long[]{0x0000080000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_rule__ColumnOrAlias__Group_0__1_in_rule__ColumnOrAlias__Group_0__05747 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ColumnOrAlias__CfullAssignment_0_0_in_rule__ColumnOrAlias__Group_0__0__Impl5774 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ColumnOrAlias__Group_0__1__Impl_in_rule__ColumnOrAlias__Group_0__15804 = new BitSet(new long[]{0x0000080000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_rule__ColumnOrAlias__Group_0__2_in_rule__ColumnOrAlias__Group_0__15807 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ColumnOrAlias__AliasAssignment_0_1_in_rule__ColumnOrAlias__Group_0__1__Impl5834 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ColumnOrAlias__Group_0__2__Impl_in_rule__ColumnOrAlias__Group_0__25865 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ColumnOrAlias__ColAliasAssignment_0_2_in_rule__ColumnOrAlias__Group_0__2__Impl5892 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ColumnFull__Group__0__Impl_in_rule__ColumnFull__Group__05929 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_rule__ColumnFull__Group__1_in_rule__ColumnFull__Group__05932 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDbObjectName_in_rule__ColumnFull__Group__0__Impl5959 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ColumnFull__Group__1__Impl_in_rule__ColumnFull__Group__15988 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ColumnFull__Group_1__0_in_rule__ColumnFull__Group__1__Impl6015 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ColumnFull__Group_1__0__Impl_in_rule__ColumnFull__Group_1__06050 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_rule__ColumnFull__Group_1__1_in_rule__ColumnFull__Group_1__06053 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ColumnFull__Group_1__1__Impl_in_rule__ColumnFull__Group_1__16111 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ColumnFull__Group_1_1__0_in_rule__ColumnFull__Group_1__1__Impl6140 = new BitSet(new long[]{0x0020000000000002L});
    public static final BitSet FOLLOW_rule__ColumnFull__Group_1_1__0_in_rule__ColumnFull__Group_1__1__Impl6152 = new BitSet(new long[]{0x0020000000000002L});
    public static final BitSet FOLLOW_rule__ColumnFull__Group_1_1__0__Impl_in_rule__ColumnFull__Group_1_1__06189 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_rule__ColumnFull__Group_1_1__1_in_rule__ColumnFull__Group_1_1__06192 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_6_in_rule__ColumnFull__Group_1_1__0__Impl6220 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ColumnFull__Group_1_1__1__Impl_in_rule__ColumnFull__Group_1_1__16251 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ColumnFull__EntriesAssignment_1_1_1_in_rule__ColumnFull__Group_1_1__1__Impl6278 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Tables__Group__0__Impl_in_rule__Tables__Group__06312 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_rule__Tables__Group__1_in_rule__Tables__Group__06315 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFromTable_in_rule__Tables__Group__0__Impl6342 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Tables__Group__1__Impl_in_rule__Tables__Group__16371 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Tables__Group_1__0_in_rule__Tables__Group__1__Impl6398 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Tables__Group_1__0__Impl_in_rule__Tables__Group_1__06433 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_rule__Tables__Group_1__1_in_rule__Tables__Group_1__06436 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Tables__Group_1__1__Impl_in_rule__Tables__Group_1__16494 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Tables__Group_1_1__0_in_rule__Tables__Group_1__1__Impl6523 = new BitSet(new long[]{0x0008000000000002L});
    public static final BitSet FOLLOW_rule__Tables__Group_1_1__0_in_rule__Tables__Group_1__1__Impl6535 = new BitSet(new long[]{0x0008000000000002L});
    public static final BitSet FOLLOW_rule__Tables__Group_1_1__0__Impl_in_rule__Tables__Group_1_1__06572 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_rule__Tables__Group_1_1__1_in_rule__Tables__Group_1_1__06575 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_4_in_rule__Tables__Group_1_1__0__Impl6603 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Tables__Group_1_1__1__Impl_in_rule__Tables__Group_1_1__16634 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Tables__EntriesAssignment_1_1_1_in_rule__Tables__Group_1_1__1__Impl6661 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FromTable__Group__0__Impl_in_rule__FromTable__Group__06695 = new BitSet(new long[]{0x0000000000000670L});
    public static final BitSet FOLLOW_rule__FromTable__Group__1_in_rule__FromTable__Group__06698 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FromTable__TableAssignment_0_in_rule__FromTable__Group__0__Impl6725 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FromTable__Group__1__Impl_in_rule__FromTable__Group__16755 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FromTable__Group_1__0_in_rule__FromTable__Group__1__Impl6782 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FromTable__Group_1__0__Impl_in_rule__FromTable__Group_1__06817 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_rule__FromTable__Group_1__1_in_rule__FromTable__Group_1__06820 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FromTable__JoinAssignment_1_0_in_rule__FromTable__Group_1__0__Impl6847 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FromTable__Group_1__1__Impl_in_rule__FromTable__Group_1__16877 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_rule__FromTable__Group_1__2_in_rule__FromTable__Group_1__16880 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FromTable__OnTableAssignment_1_1_in_rule__FromTable__Group_1__1__Impl6907 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FromTable__Group_1__2__Impl_in_rule__FromTable__Group_1__26937 = new BitSet(new long[]{0xE00100C800000000L,0x000000000000002BL});
    public static final BitSet FOLLOW_rule__FromTable__Group_1__3_in_rule__FromTable__Group_1__26940 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_20_in_rule__FromTable__Group_1__2__Impl6968 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FromTable__Group_1__3__Impl_in_rule__FromTable__Group_1__36999 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FromTable__JoinExprAssignment_1_3_in_rule__FromTable__Group_1__3__Impl7026 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TableOrAlias__Group__0__Impl_in_rule__TableOrAlias__Group__07064 = new BitSet(new long[]{0x0000080000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_rule__TableOrAlias__Group__1_in_rule__TableOrAlias__Group__07067 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TableOrAlias__TfullAssignment_0_in_rule__TableOrAlias__Group__0__Impl7094 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TableOrAlias__Group__1__Impl_in_rule__TableOrAlias__Group__17124 = new BitSet(new long[]{0x0000080000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_rule__TableOrAlias__Group__2_in_rule__TableOrAlias__Group__17127 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TableOrAlias__AliasAssignment_1_in_rule__TableOrAlias__Group__1__Impl7154 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TableOrAlias__Group__2__Impl_in_rule__TableOrAlias__Group__27185 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TableOrAlias__TblAliasAssignment_2_in_rule__TableOrAlias__Group__2__Impl7212 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TableFull__Group__0__Impl_in_rule__TableFull__Group__07249 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_rule__TableFull__Group__1_in_rule__TableFull__Group__07252 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDbObjectName_in_rule__TableFull__Group__0__Impl7279 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TableFull__Group__1__Impl_in_rule__TableFull__Group__17308 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TableFull__Group_1__0_in_rule__TableFull__Group__1__Impl7335 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TableFull__Group_1__0__Impl_in_rule__TableFull__Group_1__07370 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_rule__TableFull__Group_1__1_in_rule__TableFull__Group_1__07373 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TableFull__Group_1__1__Impl_in_rule__TableFull__Group_1__17431 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TableFull__Group_1_1__0_in_rule__TableFull__Group_1__1__Impl7460 = new BitSet(new long[]{0x0020000000000002L});
    public static final BitSet FOLLOW_rule__TableFull__Group_1_1__0_in_rule__TableFull__Group_1__1__Impl7472 = new BitSet(new long[]{0x0020000000000002L});
    public static final BitSet FOLLOW_rule__TableFull__Group_1_1__0__Impl_in_rule__TableFull__Group_1_1__07509 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_rule__TableFull__Group_1_1__1_in_rule__TableFull__Group_1_1__07512 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_6_in_rule__TableFull__Group_1_1__0__Impl7540 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TableFull__Group_1_1__1__Impl_in_rule__TableFull__Group_1_1__17571 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TableFull__EntriesAssignment_1_1_1_in_rule__TableFull__Group_1_1__1__Impl7598 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OrderByColumns__Group__0__Impl_in_rule__OrderByColumns__Group__07632 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_rule__OrderByColumns__Group__1_in_rule__OrderByColumns__Group__07635 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOrderByColumnFull_in_rule__OrderByColumns__Group__0__Impl7662 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OrderByColumns__Group__1__Impl_in_rule__OrderByColumns__Group__17691 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OrderByColumns__Group_1__0_in_rule__OrderByColumns__Group__1__Impl7718 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OrderByColumns__Group_1__0__Impl_in_rule__OrderByColumns__Group_1__07753 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_rule__OrderByColumns__Group_1__1_in_rule__OrderByColumns__Group_1__07756 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OrderByColumns__Group_1__1__Impl_in_rule__OrderByColumns__Group_1__17814 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OrderByColumns__Group_1_1__0_in_rule__OrderByColumns__Group_1__1__Impl7843 = new BitSet(new long[]{0x0008000000000002L});
    public static final BitSet FOLLOW_rule__OrderByColumns__Group_1_1__0_in_rule__OrderByColumns__Group_1__1__Impl7855 = new BitSet(new long[]{0x0008000000000002L});
    public static final BitSet FOLLOW_rule__OrderByColumns__Group_1_1__0__Impl_in_rule__OrderByColumns__Group_1_1__07892 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_rule__OrderByColumns__Group_1_1__1_in_rule__OrderByColumns__Group_1_1__07895 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_4_in_rule__OrderByColumns__Group_1_1__0__Impl7923 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OrderByColumns__Group_1_1__1__Impl_in_rule__OrderByColumns__Group_1_1__17954 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OrderByColumns__EntriesAssignment_1_1_1_in_rule__OrderByColumns__Group_1_1__1__Impl7981 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OrderByColumnFull__Group__0__Impl_in_rule__OrderByColumnFull__Group__08015 = new BitSet(new long[]{0x0000002080000000L});
    public static final BitSet FOLLOW_rule__OrderByColumnFull__Group__1_in_rule__OrderByColumnFull__Group__08018 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OrderByColumnFull__ColOrderAssignment_0_in_rule__OrderByColumnFull__Group__0__Impl8045 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OrderByColumnFull__Group__1__Impl_in_rule__OrderByColumnFull__Group__18075 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OrderByColumnFull__DirectionAssignment_1_in_rule__OrderByColumnFull__Group__1__Impl8102 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__GroupByColumns__Group__0__Impl_in_rule__GroupByColumns__Group__08137 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_rule__GroupByColumns__Group__1_in_rule__GroupByColumns__Group__08140 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleGroupByColumnFull_in_rule__GroupByColumns__Group__0__Impl8167 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__GroupByColumns__Group__1__Impl_in_rule__GroupByColumns__Group__18196 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__GroupByColumns__Group_1__0_in_rule__GroupByColumns__Group__1__Impl8223 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__GroupByColumns__Group_1__0__Impl_in_rule__GroupByColumns__Group_1__08258 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_rule__GroupByColumns__Group_1__1_in_rule__GroupByColumns__Group_1__08261 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__GroupByColumns__Group_1__1__Impl_in_rule__GroupByColumns__Group_1__18319 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__GroupByColumns__Group_1_1__0_in_rule__GroupByColumns__Group_1__1__Impl8348 = new BitSet(new long[]{0x0008000000000002L});
    public static final BitSet FOLLOW_rule__GroupByColumns__Group_1_1__0_in_rule__GroupByColumns__Group_1__1__Impl8360 = new BitSet(new long[]{0x0008000000000002L});
    public static final BitSet FOLLOW_rule__GroupByColumns__Group_1_1__0__Impl_in_rule__GroupByColumns__Group_1_1__08397 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_rule__GroupByColumns__Group_1_1__1_in_rule__GroupByColumns__Group_1_1__08400 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_4_in_rule__GroupByColumns__Group_1_1__0__Impl8428 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__GroupByColumns__Group_1_1__1__Impl_in_rule__GroupByColumns__Group_1_1__18459 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__GroupByColumns__EntriesAssignment_1_1_1_in_rule__GroupByColumns__Group_1_1__1__Impl8486 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FullExpression__Group__0__Impl_in_rule__FullExpression__Group__08520 = new BitSet(new long[]{0x0000401000000000L});
    public static final BitSet FOLLOW_rule__FullExpression__Group__1_in_rule__FullExpression__Group__08523 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpressionFragment_in_rule__FullExpression__Group__0__Impl8550 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FullExpression__Group__1__Impl_in_rule__FullExpression__Group__18579 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FullExpression__Group_1__0_in_rule__FullExpression__Group__1__Impl8606 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FullExpression__Group_1__0__Impl_in_rule__FullExpression__Group_1__08641 = new BitSet(new long[]{0x0000401000000000L});
    public static final BitSet FOLLOW_rule__FullExpression__Group_1__1_in_rule__FullExpression__Group_1__08644 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FullExpression__Group_1__1__Impl_in_rule__FullExpression__Group_1__18702 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FullExpression__EntriesAssignment_1_1_in_rule__FullExpression__Group_1__1__Impl8731 = new BitSet(new long[]{0x0000401000000002L});
    public static final BitSet FOLLOW_rule__FullExpression__EntriesAssignment_1_1_in_rule__FullExpression__Group_1__1__Impl8743 = new BitSet(new long[]{0x0000401000000002L});
    public static final BitSet FOLLOW_rule__ExpressionFragmentSecond__Group__0__Impl_in_rule__ExpressionFragmentSecond__Group__08780 = new BitSet(new long[]{0xE00100C800000000L,0x000000000000002BL});
    public static final BitSet FOLLOW_rule__ExpressionFragmentSecond__Group__1_in_rule__ExpressionFragmentSecond__Group__08783 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ExpressionFragmentSecond__CAssignment_0_in_rule__ExpressionFragmentSecond__Group__0__Impl8810 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ExpressionFragmentSecond__Group__1__Impl_in_rule__ExpressionFragmentSecond__Group__18840 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ExpressionFragmentSecond__EfragAssignment_1_in_rule__ExpressionFragmentSecond__Group__1__Impl8867 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ExpressionGroup__Group__0__Impl_in_rule__ExpressionGroup__Group__08901 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_rule__ExpressionGroup__Group__1_in_rule__ExpressionGroup__Group__08904 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ExpressionGroup__Group__1__Impl_in_rule__ExpressionGroup__Group__18962 = new BitSet(new long[]{0xE00100C800000000L,0x000000000000002BL});
    public static final BitSet FOLLOW_rule__ExpressionGroup__Group__2_in_rule__ExpressionGroup__Group__18965 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_1_in_rule__ExpressionGroup__Group__1__Impl8993 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ExpressionGroup__Group__2__Impl_in_rule__ExpressionGroup__Group__29024 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_rule__ExpressionGroup__Group__3_in_rule__ExpressionGroup__Group__29027 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ExpressionGroup__ExprAssignment_2_in_rule__ExpressionGroup__Group__2__Impl9054 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ExpressionGroup__Group__3__Impl_in_rule__ExpressionGroup__Group__39084 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_2_in_rule__ExpressionGroup__Group__3__Impl9112 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__XExpression__Group__0__Impl_in_rule__XExpression__Group__09151 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_rule__XExpression__Group__1_in_rule__XExpression__Group__09154 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_14_in_rule__XExpression__Group__0__Impl9182 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__XExpression__Group__1__Impl_in_rule__XExpression__Group__19213 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_rule__XExpression__Group__2_in_rule__XExpression__Group__19216 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__XExpression__Group__2__Impl_in_rule__XExpression__Group__29274 = new BitSet(new long[]{0x000010021C3D1800L});
    public static final BitSet FOLLOW_rule__XExpression__Group__3_in_rule__XExpression__Group__29277 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_11_in_rule__XExpression__Group__2__Impl9305 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__XExpression__Group__3__Impl_in_rule__XExpression__Group__39336 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_rule__XExpression__Group__4_in_rule__XExpression__Group__39339 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__XExpression__XfAssignment_3_in_rule__XExpression__Group__3__Impl9366 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__XExpression__Group__4__Impl_in_rule__XExpression__Group__49396 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_rule__XExpression__Group__5_in_rule__XExpression__Group__49399 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_4_in_rule__XExpression__Group__4__Impl9427 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__XExpression__Group__5__Impl_in_rule__XExpression__Group__59458 = new BitSet(new long[]{0x0808000000000000L});
    public static final BitSet FOLLOW_rule__XExpression__Group__6_in_rule__XExpression__Group__59461 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__XExpression__ColAssignment_5_in_rule__XExpression__Group__5__Impl9488 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__XExpression__Group__6__Impl_in_rule__XExpression__Group__69518 = new BitSet(new long[]{0x0808000000000000L});
    public static final BitSet FOLLOW_rule__XExpression__Group__7_in_rule__XExpression__Group__69521 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__XExpression__Group_6__0_in_rule__XExpression__Group__6__Impl9548 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__XExpression__Group__7__Impl_in_rule__XExpression__Group__79579 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_12_in_rule__XExpression__Group__7__Impl9607 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__XExpression__Group_6__0__Impl_in_rule__XExpression__Group_6__09654 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_rule__XExpression__Group_6__1_in_rule__XExpression__Group_6__09657 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_4_in_rule__XExpression__Group_6__0__Impl9685 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__XExpression__Group_6__1__Impl_in_rule__XExpression__Group_6__19716 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__XExpression__PrmAssignment_6_1_in_rule__XExpression__Group_6__1__Impl9743 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__XExpressionParams__Group__0__Impl_in_rule__XExpressionParams__Group__09777 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_rule__XExpressionParams__Group__1_in_rule__XExpressionParams__Group__09780 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleJRParameter_in_rule__XExpressionParams__Group__0__Impl9807 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__XExpressionParams__Group__1__Impl_in_rule__XExpressionParams__Group__19836 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__XExpressionParams__Group_1__0_in_rule__XExpressionParams__Group__1__Impl9863 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__XExpressionParams__Group_1__0__Impl_in_rule__XExpressionParams__Group_1__09898 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_rule__XExpressionParams__Group_1__1_in_rule__XExpressionParams__Group_1__09901 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__XExpressionParams__Group_1__1__Impl_in_rule__XExpressionParams__Group_1__19959 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__XExpressionParams__Group_1_1__0_in_rule__XExpressionParams__Group_1__1__Impl9988 = new BitSet(new long[]{0x0008000000000002L});
    public static final BitSet FOLLOW_rule__XExpressionParams__Group_1_1__0_in_rule__XExpressionParams__Group_1__1__Impl10000 = new BitSet(new long[]{0x0008000000000002L});
    public static final BitSet FOLLOW_rule__XExpressionParams__Group_1_1__0__Impl_in_rule__XExpressionParams__Group_1_1__010037 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_rule__XExpressionParams__Group_1_1__1_in_rule__XExpressionParams__Group_1_1__010040 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_4_in_rule__XExpressionParams__Group_1_1__0__Impl10068 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__XExpressionParams__Group_1_1__1__Impl_in_rule__XExpressionParams__Group_1_1__110099 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__XExpressionParams__EntriesAssignment_1_1_1_in_rule__XExpressionParams__Group_1_1__1__Impl10126 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Expression__Group__0__Impl_in_rule__Expression__Group__010160 = new BitSet(new long[]{0x0380170401508180L});
    public static final BitSet FOLLOW_rule__Expression__Group__1_in_rule__Expression__Group__010163 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Expression__Op1Assignment_0_in_rule__Expression__Group__0__Impl10190 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Expression__Group__1__Impl_in_rule__Expression__Group__110220 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Expression__Alternatives_1_in_rule__Expression__Group__1__Impl10247 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Comparison__Group__0__Impl_in_rule__Comparison__Group__010281 = new BitSet(new long[]{0xE001004800000000L,0x000000000000002BL});
    public static final BitSet FOLLOW_rule__Comparison__Group__1_in_rule__Comparison__Group__010284 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Comparison__OperatorAssignment_0_in_rule__Comparison__Group__0__Impl10311 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Comparison__Group__1__Impl_in_rule__Comparison__Group__110341 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Comparison__Op2Assignment_1_in_rule__Comparison__Group__1__Impl10368 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Like__Group__0__Impl_in_rule__Like__Group__010402 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_rule__Like__Group__1_in_rule__Like__Group__010405 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Like__OpLikeAssignment_0_in_rule__Like__Group__0__Impl10432 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Like__Group__1__Impl_in_rule__Like__Group__110462 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Like__Op2Assignment_1_in_rule__Like__Group__1__Impl10489 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Between__Group__0__Impl_in_rule__Between__Group__010523 = new BitSet(new long[]{0xE001004800000000L,0x000000000000002BL});
    public static final BitSet FOLLOW_rule__Between__Group__1_in_rule__Between__Group__010526 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Between__OpBetweenAssignment_0_in_rule__Between__Group__0__Impl10553 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Between__Group__1__Impl_in_rule__Between__Group__110583 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_rule__Between__Group__2_in_rule__Between__Group__110586 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Between__Op2Assignment_1_in_rule__Between__Group__1__Impl10613 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Between__Group__2__Impl_in_rule__Between__Group__210643 = new BitSet(new long[]{0xE001004800000000L,0x000000000000002BL});
    public static final BitSet FOLLOW_rule__Between__Group__3_in_rule__Between__Group__210646 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_24_in_rule__Between__Group__2__Impl10674 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Between__Group__3__Impl_in_rule__Between__Group__310705 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Between__Op3Assignment_3_in_rule__Between__Group__3__Impl10732 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__InOperator__Group__0__Impl_in_rule__InOperator__Group__010770 = new BitSet(new long[]{0x0000100001000000L});
    public static final BitSet FOLLOW_rule__InOperator__Group__1_in_rule__InOperator__Group__010773 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__InOperator__Group__1__Impl_in_rule__InOperator__Group__110831 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_rule__InOperator__Group__2_in_rule__InOperator__Group__110834 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__InOperator__OpAssignment_1_in_rule__InOperator__Group__1__Impl10861 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__InOperator__Group__2__Impl_in_rule__InOperator__Group__210891 = new BitSet(new long[]{0xE001004800000000L,0x000000000000002BL});
    public static final BitSet FOLLOW_rule__InOperator__Group__3_in_rule__InOperator__Group__210894 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_1_in_rule__InOperator__Group__2__Impl10922 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__InOperator__Group__3__Impl_in_rule__InOperator__Group__310953 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_rule__InOperator__Group__4_in_rule__InOperator__Group__310956 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__InOperator__Alternatives_3_in_rule__InOperator__Group__3__Impl10983 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__InOperator__Group__4__Impl_in_rule__InOperator__Group__411013 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_2_in_rule__InOperator__Group__4__Impl11041 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OperandList__Group__0__Impl_in_rule__OperandList__Group__011082 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_rule__OperandList__Group__1_in_rule__OperandList__Group__011085 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXOperandFragment_in_rule__OperandList__Group__0__Impl11112 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OperandList__Group__1__Impl_in_rule__OperandList__Group__111141 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OperandList__Group_1__0_in_rule__OperandList__Group__1__Impl11168 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OperandList__Group_1__0__Impl_in_rule__OperandList__Group_1__011203 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_rule__OperandList__Group_1__1_in_rule__OperandList__Group_1__011206 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OperandList__Group_1__1__Impl_in_rule__OperandList__Group_1__111264 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OperandList__Group_1_1__0_in_rule__OperandList__Group_1__1__Impl11293 = new BitSet(new long[]{0x0008000000000002L});
    public static final BitSet FOLLOW_rule__OperandList__Group_1_1__0_in_rule__OperandList__Group_1__1__Impl11305 = new BitSet(new long[]{0x0008000000000002L});
    public static final BitSet FOLLOW_rule__OperandList__Group_1_1__0__Impl_in_rule__OperandList__Group_1_1__011342 = new BitSet(new long[]{0xE000004800000000L,0x0000000000000023L});
    public static final BitSet FOLLOW_rule__OperandList__Group_1_1__1_in_rule__OperandList__Group_1_1__011345 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_4_in_rule__OperandList__Group_1_1__0__Impl11373 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OperandList__Group_1_1__1__Impl_in_rule__OperandList__Group_1_1__111404 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OperandList__EntriesAssignment_1_1_1_in_rule__OperandList__Group_1_1__1__Impl11431 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Operand__Group__0__Impl_in_rule__Operand__Group__011465 = new BitSet(new long[]{0x1054800000000000L});
    public static final BitSet FOLLOW_rule__Operand__Group__1_in_rule__Operand__Group__011468 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperandFragment_in_rule__Operand__Group__0__Impl11495 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Operand__Group__1__Impl_in_rule__Operand__Group__111524 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Operand__Group_1__0_in_rule__Operand__Group__1__Impl11551 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Operand__Group_1__0__Impl_in_rule__Operand__Group_1__011586 = new BitSet(new long[]{0x1054800000000000L});
    public static final BitSet FOLLOW_rule__Operand__Group_1__1_in_rule__Operand__Group_1__011589 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Operand__Group_1__1__Impl_in_rule__Operand__Group_1__111647 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Operand__Group_1_1__0_in_rule__Operand__Group_1__1__Impl11676 = new BitSet(new long[]{0x1054800000000002L});
    public static final BitSet FOLLOW_rule__Operand__Group_1_1__0_in_rule__Operand__Group_1__1__Impl11688 = new BitSet(new long[]{0x1054800000000002L});
    public static final BitSet FOLLOW_rule__Operand__Group_1_1__0__Impl_in_rule__Operand__Group_1_1__011725 = new BitSet(new long[]{0xE001004800000000L,0x000000000000002BL});
    public static final BitSet FOLLOW_rule__Operand__Group_1_1__1_in_rule__Operand__Group_1_1__011728 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Operand__Alternatives_1_1_0_in_rule__Operand__Group_1_1__0__Impl11755 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Operand__Group_1_1__1__Impl_in_rule__Operand__Group_1_1__111785 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Operand__EntriesAssignment_1_1_1_in_rule__Operand__Group_1_1__1__Impl11812 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ParameterOperand__Group__0__Impl_in_rule__ParameterOperand__Group__011846 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_rule__ParameterOperand__Group__1_in_rule__ParameterOperand__Group__011849 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_13_in_rule__ParameterOperand__Group__0__Impl11877 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ParameterOperand__Group__1__Impl_in_rule__ParameterOperand__Group__111908 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_rule__ParameterOperand__Group__2_in_rule__ParameterOperand__Group__111911 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ParameterOperand__Group__2__Impl_in_rule__ParameterOperand__Group__211969 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_rule__ParameterOperand__Group__3_in_rule__ParameterOperand__Group__211972 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_11_in_rule__ParameterOperand__Group__2__Impl12000 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ParameterOperand__Group__3__Impl_in_rule__ParameterOperand__Group__312031 = new BitSet(new long[]{0x0800000000000000L});
    public static final BitSet FOLLOW_rule__ParameterOperand__Group__4_in_rule__ParameterOperand__Group__312034 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ParameterOperand__PrmAssignment_3_in_rule__ParameterOperand__Group__3__Impl12061 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ParameterOperand__Group__4__Impl_in_rule__ParameterOperand__Group__412091 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_12_in_rule__ParameterOperand__Group__4__Impl12119 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ExclamationParameterOperand__Group__0__Impl_in_rule__ExclamationParameterOperand__Group__012160 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_rule__ExclamationParameterOperand__Group__1_in_rule__ExclamationParameterOperand__Group__012163 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_23_in_rule__ExclamationParameterOperand__Group__0__Impl12191 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ExclamationParameterOperand__Group__1__Impl_in_rule__ExclamationParameterOperand__Group__112222 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_rule__ExclamationParameterOperand__Group__2_in_rule__ExclamationParameterOperand__Group__112225 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ExclamationParameterOperand__Group__2__Impl_in_rule__ExclamationParameterOperand__Group__212283 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_rule__ExclamationParameterOperand__Group__3_in_rule__ExclamationParameterOperand__Group__212286 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_11_in_rule__ExclamationParameterOperand__Group__2__Impl12314 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ExclamationParameterOperand__Group__3__Impl_in_rule__ExclamationParameterOperand__Group__312345 = new BitSet(new long[]{0x0800000000000000L});
    public static final BitSet FOLLOW_rule__ExclamationParameterOperand__Group__4_in_rule__ExclamationParameterOperand__Group__312348 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ExclamationParameterOperand__PrmAssignment_3_in_rule__ExclamationParameterOperand__Group__3__Impl12375 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ExclamationParameterOperand__Group__4__Impl_in_rule__ExclamationParameterOperand__Group__412405 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_12_in_rule__ExclamationParameterOperand__Group__4__Impl12433 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SubQueryOperand__Group__0__Impl_in_rule__SubQueryOperand__Group__012474 = new BitSet(new long[]{0xE001004800000000L,0x000000000000002BL});
    public static final BitSet FOLLOW_rule__SubQueryOperand__Group__1_in_rule__SubQueryOperand__Group__012477 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SubQueryOperand__Group__1__Impl_in_rule__SubQueryOperand__Group__112535 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_rule__SubQueryOperand__Group__2_in_rule__SubQueryOperand__Group__112538 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_1_in_rule__SubQueryOperand__Group__1__Impl12566 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SubQueryOperand__Group__2__Impl_in_rule__SubQueryOperand__Group__212597 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_rule__SubQueryOperand__Group__3_in_rule__SubQueryOperand__Group__212600 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SubQueryOperand__SelAssignment_2_in_rule__SubQueryOperand__Group__2__Impl12627 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SubQueryOperand__Group__3__Impl_in_rule__SubQueryOperand__Group__312657 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_2_in_rule__SubQueryOperand__Group__3__Impl12685 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSelect_in_rule__Model__EntriesAssignment_1_1_112729 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOrderByColumns_in_rule__Model__OrderByEntryAssignment_2_112760 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_37_in_rule__Select__SelectAssignment_012796 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumns_in_rule__Select__ColsAssignment_212835 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTables_in_rule__Select__TblAssignment_412866 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFullExpression_in_rule__Select__WhereExpressionAssignment_5_112897 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleGroupByColumns_in_rule__Select__GroupByEntryAssignment_6_112928 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFullExpression_in_rule__Select__HavingEntryAssignment_7_112959 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnOrAlias_in_rule__Columns__EntriesAssignment_1_1_112990 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnFull_in_rule__ColumnOrAlias__CfullAssignment_0_013021 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_18_in_rule__ColumnOrAlias__AliasAssignment_0_113057 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDbObjectName_in_rule__ColumnOrAlias__ColAliasAssignment_0_213096 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STAR_in_rule__ColumnOrAlias__AllColsAssignment_113127 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDbObjectName_in_rule__ColumnFull__EntriesAssignment_1_1_113158 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFromTable_in_rule__Tables__EntriesAssignment_1_1_113189 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTableOrAlias_in_rule__FromTable__TableAssignment_013220 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleJoinType_in_rule__FromTable__JoinAssignment_1_013251 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTableOrAlias_in_rule__FromTable__OnTableAssignment_1_113282 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFullExpression_in_rule__FromTable__JoinExprAssignment_1_313313 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTableFull_in_rule__TableOrAlias__TfullAssignment_013344 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_18_in_rule__TableOrAlias__AliasAssignment_113380 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDbObjectName_in_rule__TableOrAlias__TblAliasAssignment_213419 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDbObjectName_in_rule__TableFull__EntriesAssignment_1_1_113450 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__DbObjectName__DbnameAssignment13481 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOrderByColumnFull_in_rule__OrderByColumns__EntriesAssignment_1_1_113512 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnFull_in_rule__OrderByColumnFull__ColOrderAssignment_013543 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OrderByColumnFull__DirectionAlternatives_1_0_in_rule__OrderByColumnFull__DirectionAssignment_113574 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleGroupByColumnFull_in_rule__GroupByColumns__EntriesAssignment_1_1_113607 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnFull_in_rule__GroupByColumnFull__ColGrByAssignment13638 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpressionFragmentSecond_in_rule__FullExpression__EntriesAssignment_1_113669 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ExpressionFragmentSecond__CAlternatives_0_0_in_rule__ExpressionFragmentSecond__CAssignment_013700 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpressionFragment_in_rule__ExpressionFragmentSecond__EfragAssignment_113733 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpressionGroup_in_rule__ExpressionFragment__ExpgroupAssignment_013764 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression_in_rule__ExpressionFragment__ExpAssignment_113795 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXExpression_in_rule__ExpressionFragment__XexpAssignment_213826 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFullExpression_in_rule__ExpressionGroup__ExprAssignment_213857 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXFunction_in_rule__XExpression__XfAssignment_313888 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnOperand_in_rule__XExpression__ColAssignment_513919 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXExpressionParams_in_rule__XExpression__PrmAssignment_6_113950 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleJRParameter_in_rule__XExpressionParams__EntriesAssignment_1_1_113981 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__JRParameter__JrprmAssignment14012 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperand_in_rule__Expression__Op1Assignment_014043 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Expression__IsnullAlternatives_1_0_0_in_rule__Expression__IsnullAssignment_1_014074 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInOperator_in_rule__Expression__InAssignment_1_114107 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBetween_in_rule__Expression__BetweenAssignment_1_214138 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLike_in_rule__Expression__LikeAssignment_1_314169 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleComparison_in_rule__Expression__CompAssignment_1_414200 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Comparison__OperatorAlternatives_0_0_in_rule__Comparison__OperatorAssignment_014231 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperand_in_rule__Comparison__Op2Assignment_114264 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Like__OpLikeAlternatives_0_0_in_rule__Like__OpLikeAssignment_014295 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleStringOperand_in_rule__Like__Op2Assignment_114328 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Between__OpBetweenAlternatives_0_0_in_rule__Between__OpBetweenAssignment_014359 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperand_in_rule__Between__Op2Assignment_114392 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperand_in_rule__Between__Op3Assignment_314423 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__InOperator__OpAlternatives_1_0_in_rule__InOperator__OpAssignment_114454 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSubQueryOperand_in_rule__InOperator__SubqueryAssignment_3_014487 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperandList_in_rule__InOperator__OpListAssignment_3_114518 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXOperandFragment_in_rule__OperandList__EntriesAssignment_1_1_114549 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperandFragment_in_rule__Operand__EntriesAssignment_1_1_114580 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnOperand_in_rule__OperandFragment__ColumnAssignment_014611 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXOperandFragment_in_rule__OperandFragment__XopAssignment_114642 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSubQueryOperand_in_rule__OperandFragment__SubqAssignment_214673 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleParameterOperand_in_rule__XOperandFragment__ParamAssignment_014704 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExclamationParameterOperand_in_rule__XOperandFragment__EparamAssignment_114735 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleScalarOperand_in_rule__XOperandFragment__ScalarAssignment_214766 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__ParameterOperand__PrmAssignment_314797 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__ExclamationParameterOperand__PrmAssignment_314828 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnFull_in_rule__ColumnOperand__CfullAssignment14859 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSelect_in_rule__SubQueryOperand__SelAssignment_214890 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_SIGNED_INT_in_rule__ScalarOperand__SointAssignment_014921 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleStringOperand_in_rule__ScalarOperand__SostrAssignment_114952 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_SIGNED_DOUBLE_in_rule__ScalarOperand__SodblAssignment_214983 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_DATE_in_rule__ScalarOperand__SodateAssignment_315014 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_TIME_in_rule__ScalarOperand__SotimeAssignment_415045 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_TIMESTAMP_in_rule__ScalarOperand__SodtAssignment_515076 = new BitSet(new long[]{0x0000000000000002L});

}